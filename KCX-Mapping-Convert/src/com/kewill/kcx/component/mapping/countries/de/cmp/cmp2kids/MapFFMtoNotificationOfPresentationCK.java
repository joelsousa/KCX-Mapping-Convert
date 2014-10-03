package com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.FlightManifestMessage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCmpCompleteData;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ArrivalEvent;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentFFM;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportCargo;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportMovementFFM;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentation;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyNotificationOfPresentationKids;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: CMP<br>
 * Created		: 07.06.2013<br>
 * Description	: Mapping of CMP-Format into KIDS-Format of Manifest message.
 * 				: EI201540211: beznr: Flugnr + DepartureDate (DDMMJJ) + POL
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapFFMtoNotificationOfPresentationCK extends KidsMessageManifest20 {
	
	private BodyNotificationOfPresentationKids		body;
	private MsgNotificationOfPresentation			message;
	private MsgCmpCompleteData	cmp;	
	private boolean abbruchSender = false;   //EI20131129: fuer bestimmte Sender muss die Verarbeitung abgebrochen werden
											 //weil die noch ueber alten Konverter schicken	
	private String senderId = ""; 	    	
	private String destinationCode = ""; 
	private String originLocation = "";   //EI20140114	
	private String calculatedKcxId = "";  //EI20140116		
	private String abbruchText = "";      //EI20140212
	private boolean testmode = false;     //EI20140321
	private String mdtForKcxid = "";      //EI20140519
	
	public MapFFMtoNotificationOfPresentationCK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgNotificationOfPresentation(parser);	       
		this.encoding = encoding;
	}
	
	public MapFFMtoNotificationOfPresentationCK(MsgCmpCompleteData cmpMessage, String encoding) throws XMLStreamException {
		cmp = cmpMessage;
		message = new MsgNotificationOfPresentation();	       
		this.encoding = encoding;
	}
		
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	if (kidsHeader == null) {   //EI20140321
        		throw new KcxMappingException("KidsHeader is null");  //EI20140220        		
        	}
        	String cmpReceiver = kidsHeader.getReceiver().trim();  //EI20140321
            int len = cmpReceiver.length();            
            if (cmpReceiver.substring(len - 3, len).equals("TST")) {
             	testmode = true;             	
            } else {
             	testmode = false;             	
            }
            
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyNotificationOfPresentationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
               
            kidsHeader.setMessageName("NotificationOfPresentation");
            //EI20140116: erst in mapCmpToKids wird die richtige kcxId gesetzt:
            //kidsHeader.writeHeader();                      
          
            if (!this.mapCmpToKids()) {   //EI201301001            	
            	throw new KcxMappingException(abbruchText);               
            }
            if (abbruchSender) {            
            	throw new KcxMappingException(abbruchText);
            }
            
            kidsHeader.setReceiver(calculatedKcxId);
            this.getCommonFieldsDTO().setKcxId(calculatedKcxId); //EI20140123
            //if (!checkDBforCalculatedKcxid(calculatedKcxId)) {
            //	throw new KcxMappingException("KcxId could not be calculated!");  //EI20140220
            //}
            
            kidsHeader.writeHeader(); 
            
            body.setMessage(message); 
            body.setKidsHeader(kidsHeader);            
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("CmpToKids " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
	
	private boolean mapCmpToKids() {
	//private boolean mapCmpToKids() throws XMLStreamException {
		FlightManifestMessage ffm = null;
		TransportMovementFFM logisticsTransportMovement = null;
		//String referenceNumberAlt = "";    //EI20130813
		String referenceNumber = "";         //EI20140114
		String flightNumber = "";
		//String ddmmjjA = "";               //EI20140211
		String ddmmjjD = "";                 //EI20140211
		String departureDateTime = "";       //EI20140320
		String departureLocationCode = "";				
		
		ffm = cmp.getFlightManifestMessage();
		if (ffm == null) {
			abbruchText = "CmpToKids FFM: FlightManifestMessage is missing";
			return false;
		}
		
		if (ffm.getMessageHeaderDocument() == null) {		//EI20131129
			abbruchText = "CmpToKids FFM: MessageHeaderDocument is missing";			
			return false;
		} else if (ffm.getMessageHeaderDocument().getSenderPartyList() == null) {
			abbruchText = "CmpToKids FFM: MessageHeaderDocument.SenderPartyList is missing";			
			return false;
		} else if (ffm.getMessageHeaderDocument().getSenderPartyList().get(0) == null) {
			abbruchText = "CmpToKids FFM: MessageHeaderDocument.SenderPartyList is empty";			
			return false;
		} else {
			this.senderId = ffm.getMessageHeaderDocument().getSenderPartyList().get(0).getPrimaryId();
		}
		
		if (ffm.getBusinessHeaderDocument() == null) {
			abbruchText = "CmpToKids FFM: BusinessHeaderDocument is missing";
			return false;
		}	
		/*
		referenceNumberAlt = ffm.getBusinessHeaderDocument().getId();  //EI20130813			
		if (Utils.isStringEmpty(referenceNumberAlt)) {
			abbruchText = "CmpToKids: BusinessHeaderDocument.ID is missing";
			return false;
		}
		*/
		
		if (ffm.getLogisticsTransportMovement() == null) {
			abbruchText = "CmpToKids FFM: LogisticsTransportMovement is missing";
			return false; 
		}	
		if (ffm.getArrivalEventList() == null || ffm.getArrivalEventList().get(0) == null) {  //EI20140220
			abbruchText = "CmpToKids FFM: ArrivalEvent is missing";
			return false;	
		}
		
		logisticsTransportMovement = ffm.getLogisticsTransportMovement();
		flightNumber = logisticsTransportMovement.getId();
		
		if (logisticsTransportMovement.getDepartureEvent() != null) {
			departureDateTime = logisticsTransportMovement.getDepartureEvent().getDepartureOccurrenceDateTime();
			//EI20140606: ddmmjjD = this.calculateDate(departureDateTime, "MMDDYY");
			ddmmjjD = this.calculateDate(departureDateTime, "DDMMYY"); //EI20140606
			if (logisticsTransportMovement.getDepartureEvent().getOccurrenceDepartureLocation() != null) {
				departureLocationCode = logisticsTransportMovement.getDepartureEvent().getOccurrenceDepartureLocation().getId();				
			}
		}
		
		if (Utils.isStringEmpty(flightNumber) || Utils.isStringEmpty(ddmmjjD) || Utils.isStringEmpty(departureLocationCode)) {
			abbruchText = "CmpToKids FFM: FlightNumber/DepartureDate/DepartureLocationCode is missing. ReferenceNumber could not be calculated";
			return false;
		}	
		//SUMA: beznr = flughr + ddmmjjD + POL
		referenceNumber = this.calculateReferenceNumber(flightNumber, ddmmjjD, departureLocationCode); //EI20140210
		message.setReferenceNumber(referenceNumber);	
		Utils.log("CmpToKids FFM: ReferenceNumber: " + referenceNumber);
		message.setTypeOfPresentation("FFM");   //EI20140702
			
		/*	
		if (ffm.getArrivalEventList() != null && ffm.getArrivalEventList().get(0) != null) {
			arrivalDateTime = ffm.getArrivalEventList().get(0).getArrivalOccurrenceDateTime();
			//EI20140114: referenceNumber soll doch aufgebaut werden: Flugnr + ddmmjjA + DepartuerCode	
			//ddmmjjA = this.calculateDate(arrivalDateTime, "DDDDYY");				
		}
		*/		

		////////////////  				
		HeaderExtensions he = new HeaderExtensions();		
		he.setRegistrationID("E"); 		 		 	//fix E			suk.setErfkz 
		he.setAdvanceProcedureID("1"); 	 		 	//fix 1			suk.setKzvorz 
		he.setMaritimTrafficID("0");  			 	//fix 0			suk.setSeekz 
		message.setHeaderExtensions(he);
													//Presenter, Agent nicht fuellen
		message.setDateOfPresentation(this.getToday());  			//suk.setGstdat
													//presentation office nicht fuellen
		PreviousDocument previousDocument = new PreviousDocument();
		previousDocument.setType("OHNE");   //fix: der richtige suk.vorart wird ausserhalb von KCX in lhckcx (fss2fss) ermittelt
		message.setPreviousDocument(previousDocument);
		
		Transport transport = new Transport();
		transport.setTransportMode("04");  					//fix 04, 
		transport.setTransportationNumber(flightNumber);    //ist TransportationNumber, suk.befnum	//TODO-xls: fix empty, wird richtig in kids2fss gemapped		
		message.setTransport(transport);
		  
		message.setContainerQuantity("0"); 			//fix 0				suk.setAnzcon,	
					
		message.setPlaceOfLoading(departureLocationCode); 	//suk.belo
		//TODO-xls: POL = ArrivalEvent.IncludedMasterConsignment.OriginLocation.ID
				
		ContactPerson contact = new ContactPerson();		
		contact.setIdentity("FSS");
		message.setContact(contact);	
		
		message.setReferenceIdentifier("MID");    	 //fix MID			suk.idart 
		message.setMessageFunction("0");         	 //fix 0  			suk.akz / ChangeID
													 //nctsID wird in fss2fss ermittelt		
													 //nicht fuellen: vkzwg, andst, kzeindst			
		if (ffm.getArrivalEventList() != null && ffm.getArrivalEventList().get(0) != null) {
			String arrivalDateTime = ffm.getArrivalEventList().get(0).getArrivalOccurrenceDateTime();
			 
			//EI20140320: voruebergehend soll fuer suk.ankdat statt DateOfArrival DateOfDeparture genommen werden
			//message.setDateOfArrival(this.calculateDate(arrivalDateTime, "YYYYMMDD"));  //suk.ankdat
			CustomerProcedureDTO cProceduresDTO = Utils.getCustomerProceduresFromKcxId(this.kidsHeader.getReceiver(), this.kidsHeader.getProcedure().toUpperCase());
			if (cProceduresDTO != null && cProceduresDTO.getKidsRelease() != null &&   //EI20140320	
					cProceduresDTO.getKidsRelease().equalsIgnoreCase("ALT")) {
				message.setDateOfArrival(this.calculateDate(departureDateTime, "YYYYMMDD")); 
			} else { 
				message.setDateOfArrival(this.calculateDate(arrivalDateTime, "YYYYMMDD")); 
			}
		}		
		
		//ReferenceSatz/SUR:
		//message.setReference(this.mapReferenceSUR(flightReferenceNumber, departureCode));
		message.setReference(this.mapReferenceSUR());
		
		//Addresssatz/SUA - nicht fuellen:
		// der Verwahrer und Verwahrort wird immer aus den ZABIS Stammdaten/Bearbeitungssteuerung gezogen		
				
		
		//////// Items  ///////////////	
		///
		if (ffm.getArrivalEventList() != null) {
			int i = 0;
			int j = 0;	
			String arrivalLocationCodeForKcxid = "";
			for (ArrivalEvent itemEvent : ffm.getArrivalEventList()) {		//Master-AWB???
				//i = i + 1;
				// EI20140211 ??? - oder doch nur aus dastinationLocationCode:
				if (itemEvent.getOccurrenceArrivalLocation() != null) {   //EI20140210
					String arrvalId = itemEvent.getOccurrenceArrivalLocation().getId();
					if (!Utils.isStringEmpty(arrvalId)) {
						if (Utils.isStringEmpty(calculatedKcxId)) {
							arrivalLocationCodeForKcxid = arrvalId;
							calculatedKcxId = this.calculateKcxId(kidsHeader, arrivalLocationCodeForKcxid, testmode);   //EI20140116
							Utils.log("CmpToKids FFM)  KcxId calculated with ArrivalLocationCode (" + arrivalLocationCodeForKcxid + ") = " + calculatedKcxId);						
						}
						if (!arrvalId.equals(arrivalLocationCodeForKcxid)) {
							abbruchText = "CmpToKids FFM: ArrivalEvents with different OccurrenceArrivalLocationIds: kcxId could not be calculated";
							return false;
						}
					}
				}				
				
				//TransportCargo transportCargo = itemEvent.getAssociatedTransportCargo();  //eigentlich auch eine Liste
				if (itemEvent.getAssociatedTransportCargoList() == null) {
					continue;
				}
				for (TransportCargo transportCargo : itemEvent.getAssociatedTransportCargoList()) {  //EI20140210
					if (transportCargo == null) {
						continue;
					}
					
					if (transportCargo.getUtilizedUnitLoadTransportEquipment() != null &&   //EI20131129
							transportCargo.getUtilizedUnitLoadTransportEquipment().getOnCarriageTransportMovement() != null &&
							transportCargo.getUtilizedUnitLoadTransportEquipment().getOnCarriageTransportMovement().getArrivalDestinationEvent() != null &&
							transportCargo.getUtilizedUnitLoadTransportEquipment().getOnCarriageTransportMovement().
							getArrivalDestinationEvent().getOccurrenceDestinationLocation() != null) {
					
						this.destinationCode = transportCargo.getUtilizedUnitLoadTransportEquipment().getOnCarriageTransportMovement()
											.getArrivalDestinationEvent().getOccurrenceDestinationLocation().getId();									
					}
					
					if (!Utils.isStringEmpty(senderId) && !Utils.isStringEmpty(destinationCode)) {											
						if (senderId.equals("XXXFRA") && destinationCode.equals("FRA")) {	
							abbruchText = "FFM: Mapping was stopped for Sender/DestinationCode: " + senderId + " / " + destinationCode;							
							abbruchSender = true;
						}
					}				
					for (MasterConsignmentFFM itemConsignment : transportCargo.getIncludedMasterConsignmentList()) { //House-AWB???				
						j = j + 1;	
						if (itemConsignment != null) {
							message.addGoodsItemList(this.mapGoodsItem(itemConsignment));	
						}
					}
				} //EI20140210					
			}
			//if (!this.checkArrivalLocationCodes(ffm.getArrivalEventList())) {	return false; }						
	    }
				
		return true;
	}
	
	////////////
	private ManifestReference mapReferenceSUR() {
		//ReferenceSatz/SUR
		ManifestReference referenceSUR = new ManifestReference();		
		PreviousDocument prevDoc = new PreviousDocument();
		prevDoc.setType("OHNE");		
		referenceSUR.setPreviousDocument(prevDoc);	
		
		ReferencedSpecification rsSUR = new ReferencedSpecification();
		rsSUR.setTypeOfSpecificationID("AWB");		
		//refSpecificationSUR.setSpecificationID(awb1);   		
		referenceSUR.setReferencedSpecification(rsSUR);
		//wird doch nicht benötigt: referenceSUR.setPlaceOfCustodyCode("00");		
		
		/* wird in Zabis gefuellt
		TIN tin = new TIN();
		tin.setCustomerIdentifier("?");
		tin.setTIN("?");
		referenceSUR.setCustodianTIN(tin);
		*/
		
		return referenceSUR;		
	}
	
	private GoodsItem mapGoodsItem(MasterConsignmentFFM includedMasterConsignment) {
		if (includedMasterConsignment == null) {
			return null;
		}	
		GoodsItem item = new GoodsItem();
		
		//item.setItemNumber("" + j);  wird in Zabis ermittelt
		item.setItemNumber("0");    //EI20130813 DN email von 27.06.2013: Die Positionsnummer in der SUK ist nicht mit 0 besetzt
		ItemExtension itemExtension = new ItemExtension();
		itemExtension.setTemporaryStorageCode("0"); // fix=0  sup.setKzuvwm() Kennzeichen Unterdrueckung der Verwahrmitteilung
		item.setItemExtension(itemExtension);     			

		if (includedMasterConsignment.getOriginLocation() != null) {
			this.originLocation = includedMasterConsignment.getOriginLocation().getId();
			String vland = Utils.getLandFromAirport(this.originLocation);
			item.setCountryOfDispatch(vland);  //sup.setVland 
			
		}
		if (includedMasterConsignment.getFinalDestinationLocation() != null) {
			item.setDestinationPlace(includedMasterConsignment.getFinalDestinationLocation().getId());  //sup.setBesort
			//soll destinationCode nicht von hier genommen werden? und dem zu folge die kcx_id auch? - NEIN			
		}
		if (includedMasterConsignment.getAssociatedConsignmentCustomsProcedure() != null) {
			//EI20131025: die spezifische umsetztng findet im cmp2kids nicht erst in kids2fss statt:
			String status = includedMasterConsignment.getAssociatedConsignmentCustomsProcedure().getGoodsStatusCode();
			item.setCustomsStatusOfGoods(this.calculateCustomsStatus(status)); //sup.setZollst						
		}
		if (includedMasterConsignment.getTransportContractDocument() != null) {
			ReferencedSpecification referencedSpecification = new ReferencedSpecification();
			referencedSpecification.setTypeOfSpecificationID("AWB");   	// sup.setKzawb		
		
			String awb = includedMasterConsignment.getTransportContractDocument().getId();
			if (!Utils.isStringEmpty(awb)) {
				//awb.replace("-", ""); hier psaaiert nichts!???
				String a = "";
				String b = "";				
				a = awb.replace("", "-");	//ersetzt alle "" mit "-" - quatsch
				b = awb.replace("-", "");	//richtig! "-" ist weg!								
				awb = b;						
			}
			referencedSpecification.setSpecificationID(awb);   //sup.setSpo
			item.setReferencedSpecification(referencedSpecification);
			//if (i == 1) { awb1 = awb; } //war fuer SUR gedacht, aber hier ist wohl Liste - in zabis ging auch ohne durch
		} 
		
		Packages packages = new Packages();
		packages.setType("PK");  		 //fix PK				
		packages.setQuantity(includedMasterConsignment.getTotalPieceQuantity());	
		//Max-Excel war: FlightManifest/LogisticsTransportMovement/TotalPieceQuantity
		item.addPackagesList(packages); 	
		item.setGrossMass(includedMasterConsignment.getGrossWeightMeasure());	// sup.setRohm 
		item.setItemDescription(includedMasterConsignment.getSummaryDescription());	// sup.setWabes 		
		item.setPlaceOfCustodyCode("00");   			//fix 00,  sbp.setVrwort 
		
		//Rest ist NA
				
		return item;
	}
	
	public String getCalculatedKcxId() {   //EI20140116
		return calculatedKcxId;
	}
		
	// EI20140211: OccurrenceArrivalLocation.Id wird benoetigt, um KcxId zu bestimmen, wenn also mehrere OccurrenceArrivalLocation 
	// geben kann, muss geprueft werden ob alle IDs identisch sind:
	// EI2014012: wird doch direkt in der Schleife gemacht
	public boolean checkArrivalLocationCodes(ArrayList<ArrivalEvent> list) {
		if (list == null) {
			return true;
		}
		String arrivalLocationCode = "";
		for (ArrivalEvent event : list) {
			if (event.getOccurrenceArrivalLocation() != null && event.getOccurrenceArrivalLocation().getId() != null) {
				String id = event.getOccurrenceArrivalLocation().getId();
				if (Utils.isStringEmpty(arrivalLocationCode)) {
					arrivalLocationCode = id;
				} else {
					if (!arrivalLocationCode.equalsIgnoreCase(id)) {
						abbruchText = "checkArrivalLocationCodes: different ArrivalLocationIds  " + arrivalLocationCode + "/" + id;
						return false;
					}
				}
			}
		}
		return true;
			
	}
}
