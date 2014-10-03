package com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCmpCompleteData;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ConsignmentItemTypeFWB;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.CustomsNote;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentFSU;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentFWB;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ReportedStatus;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ReportedStatusConsignment;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportMovementFSU;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentation;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentationConfirmed;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.CustomsNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyNotificationOfPresentationConfirmedKids;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyNotificationOfPresentationKids;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: CMP<br>
 * Created		: 07.06.2013<br>
 * Description	: Mapping of CMP-Format into KIDS-Format of Manifest message.
 * 				: CMP:FWB+RCF = ESA
 * 				: CMP:FWB+DIS = VSA und nach Bestätigung von Zoll ESA
  * 			: EI20140211: beznr: Flugnr + DepartureDate (DDMMJJ) + POL
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapFWBtoNotificationOfPresentationConfirmedCK extends KidsMessageManifest20 {
		
	private MsgNotificationOfPresentation messageVSA;
	private MsgNotificationOfPresentationConfirmed messageESA;
	private MsgCmpCompleteData	cmp;
	private String status = "";
	private String referenceNumber = "";
	private String flightNumber = "";  	//EI20140115
	private String ddmmjjA = "";			//EI20140115
	private String ddmmjjD = "";		//EI20140211	
	private String calculatedKcxId = ""; //EI20140116	
	private String abbruchText = "";      //EI20140212
	private boolean testmode = false;     //EI20140321		
	
	public MapFWBtoNotificationOfPresentationConfirmedCK(XMLEventReader parser, String encoding) throws XMLStreamException {
		messageVSA = new MsgNotificationOfPresentation(parser);	
		messageESA = new MsgNotificationOfPresentationConfirmed(parser);	       
		this.encoding = encoding;
	}
	
	public MapFWBtoNotificationOfPresentationConfirmedCK(MsgCmpCompleteData cmpMessage, String encoding) throws XMLStreamException {
		cmp = cmpMessage;
		messageVSA = new MsgNotificationOfPresentation();	
		messageESA = new MsgNotificationOfPresentationConfirmed();	       
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
            String dummy = cmpReceiver.substring(len - 3, len);
            if (cmpReceiver.substring(len - 3, len).equals("TST")) {
             	testmode = true;
            } else {
             	testmode = false;
            }
             
            writer = factory.createXMLStreamWriter(xmlOutputString);
            //body    = new BodyNotificationOfPresentationConfirmedKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                  
            //EI20140116: erst in mapCmpToKids iwrd die richtige kcxId gesetzt:
            //kidsHeader.writeHeader();
            
            cmp.parse(HeaderType.KIDS);  
            if (cmp.getFreightWayBill() == null) {
            	abbruchText = "CmpToKids FWB: Missing FreightWayBill in CMP file! mapping was stopped! ";
            	throw new KcxMappingException(abbruchText);
            }
            if (cmp.getFlightStatusUpdate() == null) {
            	abbruchText = "CmpToKids FWB: Missing FlightStatusUpdate in CMP file! mapping was stopped! ";            	
            	throw new KcxMappingException(abbruchText);
            }                        
            if (cmp.getFlightStatusUpdate().getMasterConsignment() != null) {            	
            	ArrayList<ReportedStatus> list = cmp.getFlightStatusUpdate().getMasterConsignment().getReportedStatusList();
            	//todo-iwa: Liste? wie soll das gehen? 
            	if (list != null && list.get(0) != null) {
            		status = list.get(0).getReasonCode();
            	}
            }               		 
            if (Utils.isStringEmpty(status))  {
            	abbruchText = "CmpToKids FWB/fsu: Missing mandatory Tag in FSU: ReportedStatus! mapping was stopped! ";
            	throw new KcxMappingException(abbruchText);
            }                       
            if (status.equalsIgnoreCase("RCF")) {
            	kidsHeader.setMessageName("NotificationOfPresentationConfirmed");
            	//EI20140116: kidsHeader.writeHeader();
            	if (!this.mapCmpToKidsESA()) {
            		//throw new KcxMappingException("Missing mandatory Tags in CMP file ! mapping was stopped!");
            		throw new KcxMappingException(abbruchText);
            	} 
            	
            	kidsHeader.setReceiver(calculatedKcxId);
            	this.getCommonFieldsDTO().setKcxId(calculatedKcxId); //EI29149123
            	
                kidsHeader.writeHeader(); //EI20140116: erst in mapCmpToKids wird die richtige kcxId gesetzt
                 
            	BodyNotificationOfPresentationConfirmedKids	body = new BodyNotificationOfPresentationConfirmedKids(writer);
            	body.setMessage(messageESA); 
                body.setKidsHeader(kidsHeader);                
                body.writeBody();
            }  else if (status.equalsIgnoreCase("DIS")) {
            	kidsHeader.setMessageName("NotificationOfPresentation");
            	//EI20140116: kidsHeader.writeHeader();
            	if (!this.mapCmpToKidsVSA()) {
            		//throw new KcxMappingException("Missing mandatory Tags in CMP file ! mapping was stopped!");
            		throw new KcxMappingException(abbruchText);
            	}  
            	kidsHeader.setReceiver(calculatedKcxId);
            	this.getCommonFieldsDTO().setKcxId(calculatedKcxId); //EI29149123
            	
                kidsHeader.writeHeader(); //EI20140116: erst in mapCmpToKids wird die richtige kcxId gesetzt
                 
            	BodyNotificationOfPresentationKids	body = new BodyNotificationOfPresentationKids(writer);
            	body.setMessage(messageVSA); 
                body.setKidsHeader(kidsHeader);                
                body.writeBody();
            } else {
            	throw new KcxMappingException("Unknown ReportedStatus (" + status + ")! mapping was stopped!");
            }
                        
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
	
	private boolean mapCmpToKidsESA() {
		if (cmp.getFreightWayBill() == null || cmp.getFlightStatusUpdate() == null) {
			abbruchText = "CmpToKids FWB: fwb/fsu is missing";			
			return false;
		}	
		MasterConsignmentFWB masterConsignmentFWB = cmp.getFreightWayBill().getMasterConsignment();
		MasterConsignmentFSU masterConsignmentFSU = cmp.getFlightStatusUpdate().getMasterConsignment();		
		String awbFSU = "";
		String awbFWB = "";
		String originLocation = ""; //EI20140115  ist departureCode, in LCAG: FWB+FSU gibt es kein ArivalEvent		
		String finalDestinationCode = "";
		String departureDateTime = "";          //EI20140320  
		String specifiedLocationCode = "";		//EI20140404
		ReportedStatusConsignment aStatusConsigmnent = null;   //EI20140505
		String pol = "";			  //EI20140611
		
		if (masterConsignmentFWB == null) {
			abbruchText = "CmpToKids FWB/ESA: FWB is missing";			
			return false;
		}
		if (masterConsignmentFSU == null) {
			abbruchText = "CmpToKids FWB/ESA: FSU is missing";			
			return false;
		}
		if (cmp.getFreightWayBill().getBusinessHeaderDocument() != null) {
			awbFWB = cmp.getFreightWayBill().getBusinessHeaderDocument().getId();
		} 		
		if (Utils.isStringEmpty(awbFWB)) {
			abbruchText = "CmpToKids FWB/ESA: FWB.BusinessHeaderDocument.ID == AWB is missing";			
			return false;	
		}
		if (masterConsignmentFSU.getTransportContractDocument() != null) {  	
			awbFSU = masterConsignmentFSU.getTransportContractDocument().getId();				
		}
		if (Utils.isStringEmpty(awbFSU)) {
			abbruchText = "CmpToKids FWB/ESA: FSU.MasterConsignmentFSU.TransportContractDocument.ID == AWB is missing";			
			return false;	
		}
		if (!awbFWB.equals(awbFSU)) {
			abbruchText = "CmpToKids FWB/ESA: AWBs of FWB and FSU are different: " + awbFWB + "/" + awbFSU;			
			return false;	
		}
		if (cmp.getFlightStatusUpdate().getBusinessHeaderDocument() != null &&
				cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId() != null) {   //EI20140611
			int len = cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId().length();
			if (len > 15) { 
				pol = cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId().substring(len - 3, len);
			}
		}
		//EI20140115: referenceNumber doch bauen:		
		//messageESA.setReferenceNumber(referenceNumber);
		messageESA.setDateOfPresentation(this.getToday());  //sbk.setGstdat
		
		HeaderExtensions he = new HeaderExtensions();				
		he.setMaritimTrafficID("0");  	 //sbk.setSeekz fix 0
		messageESA.setHeaderExtensions(he);
		
		PreviousDocument previousDocument = new PreviousDocument();
		previousDocument.setType("OHNE");  // oder ESUMA? wird in lhckcx(fss2fss) ermittelt		
		messageESA.setPreviousDocument(previousDocument);
		
		String arrivalDateTime = "";
		Transport transport = new Transport();
		transport.setTransportMode("04");   //sbk.setBefart
		if (masterConsignmentFSU.getReportedStatusList() != null && masterConsignmentFSU.getReportedStatusList().get(0) != null) {			
			ArrayList<ReportedStatusConsignment> aStatusList = masterConsignmentFSU.getReportedStatusList().get(0).getAssociatedStatusConsignmentList();
			if (aStatusList != null) {								
				//EI20140505: aStatus = aStatusList.get(0);  //EI20140505		
				aStatusConsigmnent = aStatusList.get(0);  //EI20140505		
				if (aStatusConsigmnent != null && aStatusConsigmnent.getSpecifiedLogisticsTransportMovementList() != null) {									
					TransportMovementFSU sMovement = aStatusConsigmnent.getSpecifiedLogisticsTransportMovementList().get(0);
					if (sMovement != null) {
						transport.setTransportationNumber(sMovement.getId());    //sbk.setBefkz und befnum
						this.flightNumber = sMovement.getId(); //EI20140115
						if (sMovement.getArrivalEvent() != null) {
							arrivalDateTime = sMovement.getArrivalEvent().getArrivalOccurrenceDateTime();
							//this.ddmmjjA = this.calculateDate(arrivalDateTime, "DDMMYY");   //EI20140115							
						}
						if (sMovement.getDepartureEvent() != null) {
							departureDateTime = sMovement.getDepartureEvent().getDepartureOccurrenceDateTime();
							//EI20140606: this.ddmmjjD = this.calculateDate(departureDateTime, "MMDDYY");   //EI20140211
							this.ddmmjjD = this.calculateDate(departureDateTime, "DDMMYY");   //EI20140606
						}
						if (sMovement.getSpecifiedLocation() != null) {  //EI20140404
							specifiedLocationCode = sMovement.getSpecifiedLocation().getId();
						}
					}				
				}			
			}
		}
		messageESA.setTransport(transport);	
		//EI20140320:
		//messageESA.setDateOfArrival(this.calculateDate(arrivalDateTime, "YYYYMMDD"));   		//sbk.setAnkdat
		CustomerProcedureDTO cProceduresDTO = Utils.getCustomerProceduresFromKcxId(this.kidsHeader.getReceiver(), this.kidsHeader.getProcedure().toUpperCase());
		if (cProceduresDTO != null && cProceduresDTO.getKidsRelease() != null &&   //EI20140320	
				cProceduresDTO.getKidsRelease().equalsIgnoreCase("ALT")) {
			messageESA.setDateOfArrival(this.calculateDate(departureDateTime, "YYYYMMDD")); 
		} else { 
			messageESA.setDateOfArrival(this.calculateDate(arrivalDateTime, "YYYYMMDD"));   
		}
		
		
		//messageESA.setContainerQuantity("0"); 			//sbk.setAnzcon für SBK = N/A
		
		if (masterConsignmentFSU.getOriginLocation() != null) {
			originLocation = masterConsignmentFSU.getOriginLocation().getId();
			//EI20140511: messageESA.setPlaceOfLoading(originLocation);  //sbk.setBelo	
			messageESA.setPlaceOfLoading(pol);   //EI20140511
		}
		if (masterConsignmentFSU.getFinalDestinationLocation() != null) { 
			finalDestinationCode = masterConsignmentFSU.getFinalDestinationLocation().getId();		//wird im GoodsItem gesetzt					
		} 
		if (Utils.isStringEmpty(specifiedLocationCode)) {  //EI20140404
			abbruchText = "KcxId could not be calculated, because of SpecifiedLocationID is null";			
			return false;	
		}
		//EI20140404: calculatedKcxId = this.calculateKcxId(kidsHeader, finalDestinationCode, testmode);  
		calculatedKcxId = this.calculateKcxId(kidsHeader, specifiedLocationCode, testmode);   //EI20140404
		Utils.log("CmpToKids FWB/ESA: KcxId calculated with SpecifiedLocationID (" + specifiedLocationCode + ") = " + calculatedKcxId);			
		
		ContactPerson contact = new ContactPerson();
		contact.setIdentity("FSS");               	//sbk.sb
		messageESA.setContact(contact);
		
		//N/A: messageESA.setReferenceIdentifier("?");    //sbk.idart 			
		//messageESA.setNctsID(this.calculateNctsKz());	//sbk.setKzncts
		//NA: vkzwg, andst, kzeindst
	
		//NA: agent, presenter	//TODO: die gibt es in FWB => Max-fragen?
		
		//ReferenceSatz/SBR:	  //EI29131029		
		//EI20140511: messageESA.setReference(this.mapReferenceSR(awbFSU, originLocation));	
		messageESA.setReference(this.mapReferenceSR(awbFSU, pol));	//EI20140611
		
		//////////////////////////
		//SBP goodsItemList - keine Liste, NUR EINE Position:	
		//		
		GoodsItem item = new GoodsItem();
		item.setItemNumber("0");
		item.setConfirmationCode("1");   //sbp.setKzbest();
		ItemExtension ie = new ItemExtension();
		ie.setTemporaryStorageCode("0"); //sbp.setKzuvwm
		item.setItemExtension(ie);
		
		//N/A: vland, besort
		item.setCountryOfDispatch(Utils.getLandFromAirport(originLocation));	//sup.setVland			
		item.setDestinationPlace(finalDestinationCode);  //sup.setBesort
		
				
		if (masterConsignmentFWB.getAssociatedConsignmentCustomsProcedure() != null) {	
			String status = masterConsignmentFWB.getAssociatedConsignmentCustomsProcedure().getGoodsStatusCode();
			item.setCustomsStatusOfGoods(this.calculateCustomsStatus(status)); //sbp.setZollst							   
		}
		
		if (masterConsignmentFSU.getTransportContractDocument() != null) {			
			ReferencedSpecification rs = new ReferencedSpecification();
			//rs.setTypeOfSpecificationID(masterConsignmentFSU.getTransportContractDocument().getTypeCode()); 
			rs.setTypeOfSpecificationID("AWB"); //sbp.setKzawb fix AWB?			
			if (!Utils.isStringEmpty(awbFSU)) {				
				String awb = awbFSU.replace("-", "");					
				rs.setSpecificationID(awb);  //sup.setSpo
			}				
			item.setReferencedSpecification(rs);
		}
		/* EI20140505 JIRA KCX-251
		 die sind wohl von Kopf-Tags gefüllt: TotalPieceQuantity, TotalGrossWeightMeasure <= spielt keine Rolle, denn es nur einen Item gibt
		 * 
		Packages pack = new Packages();   //specifiedLogisticsTransportMovement
		pack.setType("PK");  			//sbp.setColart		
		pack.setQuantity(masterConsignmentFSU.getTotalPieceQuantity());   //sbp.setColanz, 				
		item.addPackagesList(pack);
		
		item.setGrossMass(masterConsignmentFSU.getTotalGrossWeightMeasure());  // sbp.setRohm
		*/
		if (aStatusConsigmnent != null) { //EI20140505 JIRA KCX-251
			Packages pack = new Packages();   //specifiedLogisticsTransportMovement
			pack.setType("PK");  			//sbp.setColart		
			pack.setQuantity(aStatusConsigmnent.getPieceQuantity());   //sbp.setColanz, 				
			item.addPackagesList(pack);
			
			item.setGrossMass(aStatusConsigmnent.getGrossWeightMeasure());  // sbp.setRohm
		}
		
		if (masterConsignmentFWB.getApplicableRatingList() != null && masterConsignmentFWB.getApplicableRatingList().get(0) != null) {		
			ArrayList<ConsignmentItemTypeFWB> list = masterConsignmentFWB.getApplicableRatingList().get(0).getIncludedMasterConsignmentItemList();
			if (list != null && list.get(0) != null && list.get(0).getNatureIdentificationTransportCargo() != null) {				
				item.setItemDescription(list.get(0).getNatureIdentificationTransportCargo().getIdentification());
			}
		}
		if (masterConsignmentFWB.getIncludedCustomsNoteList() != null) {
			for (CustomsNote note : masterConsignmentFWB.getIncludedCustomsNoteList()) {
				if (note != null) {
					if (note.getContentCode() != null && note.getContentCode().equals("T") && 
						note.getSubjectCode() != null && (note.getSubjectCode().equals("BRK") || note.getSubjectCode().equals("STI"))) {
						
						String content = note.getContent();  
						TIN partyTin = null;
						if (!Utils.isStringEmpty(content)) {
							partyTin = new TIN();							
							String eori = content;
							String bo = "0000";
							
							int len = content.length();					
							if (content.startsWith("DE") &&  len > 6) {  //EI20140310
								eori = content.substring(0, len - 4);
								bo = content.substring(len - 4, len);
							} 
							partyTin.setTin(eori);
							partyTin.setBO(bo);
						}
						if (note.getSubjectCode().equals("BRK")) {		//Disposal											
							if (partyTin != null) {								
								Party disposal = new Party();								
								disposal.setPartyTIN(partyTin);
								item.setDisposal(disposal);
							
								/* EI20140513: JIRA KCX-266
								Party custodian = new Party();  //EI20140310
								custodian.setPartyTIN(partyTin);
								item.setCustodian(custodian);     
								 */ 
							}	
						}
						if (note.getSubjectCode().equals("STI")) {  	//Custodian  EI20140611
							if (partyTin != null) {	
								Party custodian = new Party();  
								custodian.setPartyTIN(partyTin);
								item.setCustodian(custodian);   
							}
						}
					} 
					
					if (note.getContent() != null && note.getContent().startsWith("VUB-") && note.getContent().length() > 4) {
						//item.setRangeOfGoodsCode("1");	
						item.setRangeOfGoodsCode(note.getContent().substring(4, 5));
						item.setRangeOfGoodsDescription(note.getContent().substring(5));	//also alles ab 4-ten position
					}				
				}
			}
		}						
		
		if (cmp.getCustomsStatusNotification() != null && cmp.getCustomsStatusNotification().getMasterConsignment() != null &&
				cmp.getCustomsStatusNotification().getMasterConsignment().getIncludedCustomsNoteList() != null) {
			CustomsNote note = cmp.getCustomsStatusNotification().getMasterConsignment().getIncludedCustomsNoteList().get(0);
			if (note != null) {
				CustomsNotification mrn = new CustomsNotification();
				mrn.setContent(note.getContent());				
				item.setCustomsNotification(mrn);
			}
		}	   	
		//NA: kzfrzone	 Kennzeichen Freizone	
		messageESA.addGoodsItemList(item);  //nur ein Item!
		
		// beznr = flughr + ddmmjjD + POL
		//POL = ist originLocation (ist departureCode) in LCAG: FWB+FSU gibt es kein ArivalEvent
		/* EI20140604-beginn: laut AKU soll jetzt DepartureDate und POL aus BusinessHeaderDocument.ID genommen werden 
		if (Utils.isStringEmpty(flightNumber) || Utils.isStringEmpty(ddmmjjD) || Utils.isStringEmpty(originLocation)) {
    		Utils.log("CmpToKids FWB/ESA: flightNumber/ddmmjjD/originLocation: " + flightNumber + "/" + ddmmjjD + "/" + originLocation);
    		abbruchText = "CmpToKids FWB/ESA: ReferenceNumber could not be calculated";
    		return false;
		}		
    	//referenceNumber = this.calculateReferenceNumber(flightNumber, ddmmjjA, originLocation); //EI20140115		
    	referenceNumber = this.calculateReferenceNumber(flightNumber, ddmmjjD, originLocation); //EI20140115
    	*/
		
		if (cmp.getFlightStatusUpdate().getBusinessHeaderDocument() == null) {
			Utils.log("CmpToKids FSU/RCF: BusinessHeaderDocument not provided");
    		abbruchText = "CmpToKids FSU/RCF: ReferenceNumber could not be calculated";
    		return false;
		} else {
			String businessId = cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId();
			if (Utils.isStringEmpty(businessId)) {
				Utils.log("CmpToKids FSU/RCF: BusinessHeaderDocument.Id not provided");
			    abbruchText = "CmpToKids FSU/RCF: ReferenceNumber could not be calculated";
			    return false;
			} else {
				int len = businessId.length();
				String pOL = businessId.substring(len - 3, len);
				String dateOL = this.calculateDate(businessId.substring(len - 11, len - 3), "YYYYMMDD", "DDMMYY");
				if (Utils.isStringEmpty(flightNumber) || Utils.isStringEmpty(dateOL) || Utils.isStringEmpty(pOL)) {
					Utils.log("CmpToKids FSU/RCF: flightNumber/dateOL/POL: " + flightNumber + "/" + dateOL + "/" + pOL);
					abbruchText = "CmpToKids FSU/RCF: ReferenceNumber could not be calculated";
					return false;
				}
				referenceNumber = this.calculateReferenceNumber(flightNumber, dateOL, pOL);				
			}
		}
		//EI20140604-end
		
    	Utils.log("CmpToKids FWB/ESA: ReferenceNumber: " + referenceNumber);
		messageESA.setReferenceNumber(referenceNumber); //EI20140115
		
		return true;
	}
	
	private boolean mapCmpToKidsVSA() {			
		if (cmp.getFreightWayBill() == null || cmp.getFlightStatusUpdate() == null) {
			abbruchText = "CmpToKids FWB/VSA:  FreightWayBill is missing";
			return false;
		}	
		MasterConsignmentFWB masterConsignmentFWB = cmp.getFreightWayBill().getMasterConsignment();
		MasterConsignmentFSU masterConsignmentFSU = cmp.getFlightStatusUpdate().getMasterConsignment();
		String awbFWB = "";
		String awbFSU = "";
		String arrivalDateTime = "";
		String originLocation = ""; //ist POL, ist departureCode, in LCAG: FWB+FSU gibt es kein ArivalEvent
		String finalDestinationCode = "";	
		String departureDateTime = "";          //EI20143020
		String specifiedLocationCode = "";		//EI20140404
		ReportedStatusConsignment aStatusConsigmnent = null;   //EI20140505
		String pol = "";
				
		if (masterConsignmentFWB == null || masterConsignmentFSU == null) {
			abbruchText = "CmpToKids FWB/VSA: MasterConsignmentFWB/-FSU is missing";			
			return false;
		}
		if (cmp.getFreightWayBill().getBusinessHeaderDocument() != null) {
			awbFWB = cmp.getFreightWayBill().getBusinessHeaderDocument().getId();
		} 		
		if (Utils.isStringEmpty(awbFWB)) {
			abbruchText = "CmpToKids FWB/VSA: FreightWayBill.BusinessHeaderDocument.ID == AWB is missing";			
			return false;	
		}
		if (masterConsignmentFSU.getTransportContractDocument() != null) {  	
			awbFSU = masterConsignmentFSU.getTransportContractDocument().getId();				
		}
		if (Utils.isStringEmpty(awbFSU)) {
			abbruchText = "CmpToKids FWB/VSA: FlightStatusUpdate.MasterConsignmentFSU.TransportContractDocument.ID == AWB is missing";			
			return false;	
		}	
		if (!awbFWB.equals(awbFSU)) {
			abbruchText = "CmpToKids FWB/VSA: FWB und FSU doesn't match: " + awbFWB + "/" + awbFSU;			
			return false;	
		}
		if (cmp.getFlightStatusUpdate().getBusinessHeaderDocument() != null &&
				cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId() != null) {   //EI20140611
			int len = cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId().length();
			if (len > 15) { 
				pol = cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId().substring(len - 3, len);
			}
		}
		
		//messageVSA.setReferenceNumber(referenceNumber);
		messageVSA.setDateOfPresentation(this.getToday());
		messageVSA.setTypeOfPresentation("FSU-DIS");   //EI20140702
		messageVSA.setMessageFunction("0");            //fix 0  suk.akz/ChangeID   //EI20140702
		  
		
		HeaderExtensions he = new HeaderExtensions();
		he.setRegistrationID("E"); 		 		 	//fix E			suk.setErfkz 
		//he.setAdvanceProcedureID("1");              //fix 1         suk.setKzvorz
		he.setAdvanceProcedureID("0");              //fix 1         suk.setKzvorz
		he.setMaritimTrafficID("0");  	 			//fix 0
		he.setConfirmationCode("9");				//fix 9 fuer FWB+DIS, wird aber in FSS nur in SUP gemapped
		messageVSA.setHeaderExtensions(he);
		
		PreviousDocument previousDocument = new PreviousDocument();
		previousDocument.setType("OHNE");   //fix: der richtige suk.vorart wird ausserhalb von KCX in fss2fss ermittelt
		messageVSA.setPreviousDocument(previousDocument);
				
		Transport transport = new Transport();
		transport.setTransportMode("04");              			
		if (masterConsignmentFSU.getReportedStatusList() != null && masterConsignmentFSU.getReportedStatusList().get(0) != null &&
			masterConsignmentFSU.getReportedStatusList().get(0).getAssociatedStatusConsignmentList() != null) {
			//EI20140505: ReportedStatusConsignment status = masterConsignmentFSU.getReportedStatusList().get(0).getAssociatedStatusConsignmentList().get(0);
			aStatusConsigmnent = masterConsignmentFSU.getReportedStatusList().get(0).getAssociatedStatusConsignmentList().get(0); //EI20140505:
			if (aStatusConsigmnent != null) {
				if (aStatusConsigmnent.getSpecifiedLogisticsTransportMovementList() != null) {								
					TransportMovementFSU movement = aStatusConsigmnent.getSpecifiedLogisticsTransportMovementList().get(0);
					if (movement != null) {									
						transport.setTransportationNumber(movement.getId());    //befkz und befnum	
						this.flightNumber = movement.getId(); //EI20140115
						if (movement.getArrivalEvent() != null) {	
							arrivalDateTime = movement.getArrivalEvent().getArrivalOccurrenceDateTime();
							//this.ddmmjjA = this.calculateDate(arrivalDateTime, "DDMMYY");   //EI20140115
						}
						if (movement.getDepartureEvent() != null) {	
							departureDateTime = movement.getDepartureEvent().getDepartureOccurrenceDateTime();
							//EI20140606: this.ddmmjjD = this.calculateDate(departureDateTime, "MMDDYY");   //EI20140115
							this.ddmmjjD = this.calculateDate(departureDateTime, "DDMMYY");   //EI20140606
						}
						if (movement.getSpecifiedLocation() != null) {  //EI20140404
							specifiedLocationCode = movement.getSpecifiedLocation().getId();
						}
					}							
				}				
			}
		}			
		messageVSA.setTransport(transport);
				
		messageVSA.setContainerQuantity("0"); 	//suk.setAnzcon
				
		if (masterConsignmentFSU.getOriginLocation() != null) {
			originLocation = masterConsignmentFSU.getOriginLocation().getId();		
			//EI20140611: messageVSA.setPlaceOfLoading(originLocation);
			messageVSA.setPlaceOfLoading(pol);   //EI20140611
		}
		if (masterConsignmentFSU.getFinalDestinationLocation() != null) {
			finalDestinationCode = masterConsignmentFSU.getFinalDestinationLocation().getId();		//wird im GoodsItem gesetzt						
		} 
		if (Utils.isStringEmpty(specifiedLocationCode)) {  //EI20140404
			abbruchText = "KcxId could not be calculated, because of SpecifiedLocationID is null";			
			return false;	
		}		
			
		calculatedKcxId = this.calculateKcxId(kidsHeader, specifiedLocationCode, testmode);  
		Utils.log("CmpToKids FWB/VSA:  KcxId calculated with SpecifiedLocationID (" + specifiedLocationCode + ") = " + calculatedKcxId); 	
		
		ContactPerson contact = new ContactPerson();
		contact.setIdentity("FSS");               	
		messageVSA.setContact(contact);
		
		messageVSA.setReferenceIdentifier("MID");    	
		//TODO: was ist mit MessageFunction in FWB/DIS??? weil in VSA == SUK ist fix 0  (suk.setAkz)    
		
		//EI20140320: 
		//messageVSA.setDateOfArrival(this.calculateDate(arrivalDateTime, "YYYYMMDD"));   
		CustomerProcedureDTO cProceduresDTO = Utils.getCustomerProceduresFromKcxId(this.kidsHeader.getReceiver(), this.kidsHeader.getProcedure().toUpperCase());
		if (cProceduresDTO != null && cProceduresDTO.getKidsRelease() != null &&   //EI20140320	
				cProceduresDTO.getKidsRelease().equalsIgnoreCase("ALT")) {
			messageVSA.setDateOfArrival(this.calculateDate(departureDateTime, "YYYYMMDD")); 
		} else { 
			messageVSA.setDateOfArrival(this.calculateDate(arrivalDateTime, "YYYYMMDD")); 
		}
		
		//SUR 				
		//EI20140511: messageVSA.setReference(this.mapReferenceSR(awbFSU, originLocation));		
		messageVSA.setReference(this.mapReferenceSR(awbFSU, pol));	//EI20140611
		
		///////       SUP goodsItemList - keine Liste, nur eine Position:		
		GoodsItem item = new GoodsItem();		
		//item.setItemNumber("" + j);  wird in Zabis ermittelt	
		item.setItemNumber("0");
		item.setConfirmationCode("9");   //fix 9 fuer FWB+DIS:    sup.setKzbest(); 
		
		ItemExtension itemExtension = new ItemExtension();
		itemExtension.setTemporaryStorageCode("0"); // fix=0  sup.setKzuvwm() Kennzeichen Unterdrueckung der Verwahrmitteilung
		item.setItemExtension(itemExtension);     
		
		item.setCountryOfDispatch(Utils.getLandFromAirport(originLocation));	//sup.setVland			
		item.setDestinationPlace(finalDestinationCode);  //sup.setBesort
		
		if (masterConsignmentFWB.getAssociatedConsignmentCustomsProcedure() != null) {	
			//sup.setZollst
			//item.setCustomsStatusOfGoods(masterConsignmentFWB.getAssociatedConsignmentCustomsProcedure().getGoodsStatusCode());
			item.setCustomsStatusOfGoods("N");  //Daggi schireb: fix N fuer FWB+DIS: 
		}
	
		if (masterConsignmentFSU.getTransportContractDocument() != null) {
			ReferencedSpecification rs = new ReferencedSpecification();
			rs.setTypeOfSpecificationID("AWB");   	// sup.setKzawb				
			if (!Utils.isStringEmpty(awbFSU)) {
				String awb = awbFSU.replace("-", "");	
				rs.setSpecificationID(awb);  //sup.setSpo
			}				
			item.setReferencedSpecification(rs);
			
		}
		//TODO-IWA ??? Max-fragen: in case of DiscrepancyDescriptionCode = "FDCA" wohin damit
		
		/* EI20140505 JIRA KCX-251
		Packages pack = new Packages();   
		pack.setType("PK");  			  //sup.setColart		
		pack.setQuantity(masterConsignmentFSU.getTotalPieceQuantity());   //sup.setColanz, 				
		item.addPackagesList(pack);
			
		item.setGrossMass(masterConsignmentFSU.getTotalGrossWeightMeasure());  // sup.setRohm
		*/
		if (aStatusConsigmnent != null) {   //EI20140505 JIRA KCX-251
			Packages pack = new Packages();   
			pack.setType("PK");  			  //sup.setColart		
			pack.setQuantity(aStatusConsigmnent.getPieceQuantity());   //sup.setColanz, 				
			item.addPackagesList(pack);
				
			item.setGrossMass(aStatusConsigmnent.getGrossWeightMeasure());  // sup.setRohm
		}
		
		if (masterConsignmentFWB != null) {
			if (masterConsignmentFWB.getApplicableRatingList() != null && masterConsignmentFWB.getApplicableRatingList().get(0) != null) {		
				ArrayList<ConsignmentItemTypeFWB> list = masterConsignmentFWB.getApplicableRatingList().get(0).getIncludedMasterConsignmentItemList();
				if (list != null && list.get(0) != null && list.get(0).getNatureIdentificationTransportCargo() != null) {				
					item.setItemDescription(list.get(0).getNatureIdentificationTransportCargo().getIdentification());
				}
			}
			if (masterConsignmentFWB.getIncludedCustomsNoteList() != null) {
				for (CustomsNote note : masterConsignmentFWB.getIncludedCustomsNoteList()) {
					if (note != null) {
						if (note.getContentCode() != null && note.getContentCode().equals("T") && 
							note.getSubjectCode() != null && (note.getSubjectCode().equals("BRK") || note.getSubjectCode().equals("STI"))) {
							
							String content = note.getContent();  
							TIN partyTin = null;
							if (!Utils.isStringEmpty(content)) {
								partyTin = new TIN();														
								String eori = content;
								String bo = "0000";	
								int len = content.length();	
								if (content.startsWith("DE") &&  len > 6) {  //EI20140310
									eori = content.substring(0, len - 4);
									bo = content.substring(len - 4, len);
								} 
								partyTin.setTin(eori);
								partyTin.setBO(bo);								
							}
							if (note.getSubjectCode().equals("BRK")) {  //Disposal								
								if (partyTin != null) {								
									Party disposal = new Party();
									disposal.setPartyTIN(partyTin);
									item.setDisposal(disposal);
								}
							}
							if (note.getSubjectCode().equals("STI")) {  //Custodian  EI20140611
								if (partyTin != null) {								
									Party custodian = new Party();
									custodian.setPartyTIN(partyTin);
									item.setCustodian(custodian);
								}
							}
						} 	
						
						if (note.getContent() != null && note.getContent().startsWith("VUB-") && note.getContent().length() > 4) {							
							item.setRangeOfGoodsCode(note.getContent().substring(4, 5));							
							item.setRangeOfGoodsDescription(note.getContent().substring(5));	//also alles ab 4-ten position	
						}				
					}
				}
			}
			if (cmp.getCustomsStatusNotification() != null && cmp.getCustomsStatusNotification().getMasterConsignment() != null &&
					cmp.getCustomsStatusNotification().getMasterConsignment().getIncludedCustomsNoteList() != null) {
				CustomsNote note = cmp.getCustomsStatusNotification().getMasterConsignment().getIncludedCustomsNoteList().get(0);
				if (note != null) {
					CustomsNotification mrn = new CustomsNotification();
					mrn.setContent(note.getContent());				
					item.setCustomsNotification(mrn);
				}
			}
		}
		
		//Rest ist NA
		messageVSA.addGoodsItemList(item);
		/* EI20140604-beginn: laut AKU soll jetzt DepartureDate und POL aus BusinessHeaderDocument.ID genommen werden 		
		if (Utils.isStringEmpty(flightNumber) || Utils.isStringEmpty(ddmmjjD) || Utils.isStringEmpty(originLocation)) {
    		Utils.log("CmpToKids FWB/VSA: flightNumber/ddmmjjD/originLocation: " + flightNumber + "/" + ddmmjjD + "/" + originLocation);
    		abbruchText = "CmpToKids FWB/VSA: ReferenceNumber could not be calculated";
    		return false;
		}
    	//referenceNumber = this.calculateReferenceNumber(flightNumber, ddmmjjA, originLocation); 	
		//POL = ist originLocation (ist departureCode) in LCAG: FWB+FSU gibt es kein ArivalEvent
		//SUMA: beznr = flughr + ddmmjjD + POL
		referenceNumber = this.calculateReferenceNumber(flightNumber, ddmmjjD, originLocation); 	
		*/
		
		if (cmp.getFlightStatusUpdate().getBusinessHeaderDocument() == null) {
			Utils.log("CmpToKids FSU/RCF: BusinessHeaderDocument not provided");
    		abbruchText = "CmpToKids FSU/RCF: ReferenceNumber could not be calculated";
    		return false;
		} else {
			String businessId = cmp.getFlightStatusUpdate().getBusinessHeaderDocument().getId();
			if (Utils.isStringEmpty(businessId)) {
				Utils.log("CmpToKids FSU/RCF: BusinessHeaderDocument.Id not provided");
			    abbruchText = "CmpToKids FSU/RCF: ReferenceNumber could not be calculated";
			    return false;
			} else {
				int len = businessId.length();
				String pOL = businessId.substring(len - 3, len);
				//String dateOL = businessId.substring(len - 9, len - 3);
				String dateOL = this.calculateDate(businessId.substring(len - 11, len - 3), "YYYYMMDD", "DDMMYY");
				if (Utils.isStringEmpty(flightNumber) || Utils.isStringEmpty(dateOL) || Utils.isStringEmpty(pOL)) {
					Utils.log("CmpToKids FSU/RCF: flightNumber/dateOL/POL: " + flightNumber + "/" + dateOL + "/" + pOL);
					abbruchText = "CmpToKids FSU/RCF: ReferenceNumber could not be calculated";
					return false;
				}
				referenceNumber = this.calculateReferenceNumber(flightNumber, dateOL, pOL);				
			}
		}
		//EI20140604-end
		
    	Utils.log("CmpToKids FWB/VSA: ReferenceNumber: " + referenceNumber);
		messageVSA.setReferenceNumber(referenceNumber); //EI20140115
		
		return true;
	}
		
////////////
	private ManifestReference mapReferenceSR(String awb, String loadingPlace) {   //EI20131029
		//ReferenceSatz: SUR/SBR		
		ManifestReference mr = new ManifestReference();		
		mr.setPlaceOfLoading(loadingPlace);
		
		PreviousDocument prevDoc = new PreviousDocument();
		prevDoc.setType("OHNE");		
		mr.setPreviousDocument(prevDoc);	
		
		ReferencedSpecification rs = new ReferencedSpecification();
		rs.setTypeOfSpecificationID("AWB");		
		if (!Utils.isStringEmpty(awb)) {
			awb = awb.replace("-", "");	
			rs.setSpecificationID(awb);
		}		
		mr.setReferencedSpecification(rs);
		mr.setPlaceOfCustodyCode("00");					
		
		return mr;		
	}
	
	public String getCalculatedKcxId() {   //EI20140116
		return calculatedKcxId;
	}	
}
