package com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids;

import java.io.StringWriter;

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
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportCarriage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportEquipment;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgReExport;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemReexport;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.FlightDetails;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyReExportKids;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: CMP<br>
 * Created		: 23.08.2013<br>
 * Description	: Mapping of CMP-Format into KIDS-Format of Manifest ReExport message.
 * 				: EI201540211: beznr: Flugnr + DepartureDate (DDMMJJ) + POU
 * 
 * @author iwaniuk
 * @version 1.0.00
 * 
 */

public class MapFFMReExportCK extends KidsMessageManifest20 {
	
	private BodyReExportKids body;
	private MsgReExport message;
	private MsgCmpCompleteData	cmp;
	
	private String calculatedKcxId = "";  //EI20140116
	private String abbruchText = "";      //EI20140212
	private boolean testmode = false;     //EI20140321
	
	public MapFFMReExportCK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgReExport(parser);	       
		this.encoding = encoding;
	}
	
	public MapFFMReExportCK(MsgCmpCompleteData cmpMessage, String encoding) throws XMLStreamException {
		cmp = cmpMessage;
		message = new MsgReExport();	       
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
            body    = new BodyReExportKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                
            kidsHeader.setMessageName("ReExport");
            //EI20140116: erst in mapCmpToKids iwrd die richtige kcxId gesetzt:
            //kidsHeader.writeHeader();
                      
            if (!this.mapCmpToKids()) {                    
            	//throw new KcxMappingException("Missing mandatory Tags in CMP file ! mapping was stopped!");
            	throw new KcxMappingException(abbruchText);
            }
            
            kidsHeader.setReceiver(calculatedKcxId);
            this.getCommonFieldsDTO().setKcxId(calculatedKcxId); //EI20140123
            
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
	
	private boolean mapCmpToKids() {			  //hier finden der eigentliche Mapping statt
		
		FlightManifestMessage ffm = null;		
		String referenceNumber = "";    	//EI20130813
		String flightNumber = "";
		//String ddmmjjA = "";				//EI20140115
		String ddmmjjD = "";				//EI20140115
		String arrivalLocationCode = "";	//EI20140211
		
		String departureCode = "";
		String departureDateTime = "";
		String arrivalDateTime = "";   		//EI20140320 
			
		ffm = cmp.getFlightManifestMessage();
		if (ffm == null) {
			abbruchText = "CmpToKidsm Reexport/ffm: FreightWayBill is missing";
			return false;
		}
		if (ffm.getBusinessHeaderDocument() == null) {
			abbruchText = "CmpToKids Reexport/ffm: BusinessHeaderDocument is missing";
			return false;
		}		
		if (ffm.getLogisticsTransportMovement() == null) {
			abbruchText = "CmpToKids Reexport/ffm: LogisticsTransportMovement is missing";
			return false;
		}
		
		flightNumber = ffm.getLogisticsTransportMovement().getId();
		if (Utils.isStringEmpty(flightNumber)) {
			abbruchText = "CmpToKids Reexport/ffm: LogisticsTransportMovement.ID = FlightNumber is missing";
			return false;
		}		
		if (ffm.getLogisticsTransportMovement().getDepartureEvent() != null) {
			departureDateTime = ffm.getLogisticsTransportMovement().getDepartureEvent().getDepartureOccurrenceDateTime();
			//EI20140606; ddmmjjD = this.calculateDate(departureDateTime, "MMDDYY"); 
			ddmmjjD = this.calculateDate(departureDateTime, "DDMMYY");  //EI20140606
			if (ffm.getLogisticsTransportMovement().getDepartureEvent().getOccurrenceDepartureLocation() != null) {
				departureCode = ffm.getLogisticsTransportMovement().getDepartureEvent().getOccurrenceDepartureLocation().getId();							
			}
		}	
		if (Utils.isStringEmpty(departureCode)) {
			abbruchText = "KcxId could not be calculated, because of DepartureLocationCode is empty or null";			
			return false;	
		} 
		calculatedKcxId = this.calculateKcxId(kidsHeader, departureCode, testmode);   //EI20140116
		Utils.log("CmpToKids Reexport: KcxId calculated with DepartureLocationCode (" + departureCode + ") = " + calculatedKcxId); 		
		
		////////////////  				
		//EI20140115: message.setReferenceNumber(referenceNumber); 		
		message.setProcedureType("");                //sak.vrfart;  LHCKCXConverter: abhängig von  EU-Land
		message.setReferenceIdentifier("AWB");       //sak.idart
		
		HeaderExtensions he = new HeaderExtensions();				
		he.setConfirmationCode("1");  				 //sak.kzbest fix 1
		he.setFlightCompletionCode("0");  			 //sak.kzflab fix 0
		message.setHeaderExtensions(he);
		
		ContactPerson contact = new ContactPerson();
		contact.setIdentity("FSS");               	    //sak.sb
		message.setContact(contact);
			
		String number = "";
		String carrierCode = "";				
				
		if (ffm.getArrivalEventList() != null && ffm.getArrivalEventList().get(0) != null) {
			
			if (Utils.isStringEmpty(departureDateTime)) {
				departureDateTime = ffm.getArrivalEventList().get(0).getDepartureOccurrenceDateTime();
			} 
			
			if (ffm.getArrivalEventList().get(0).getOccurrenceArrivalLocation() != null) {
				arrivalLocationCode = ffm.getArrivalEventList().get(0).getOccurrenceArrivalLocation().getId();
				message.setPlaceOfUnloading(arrivalLocationCode);					//sak.elo				
			}
			//EI20140320:
			//arrivalDateTime = ffm.getArrivalEventList().get(0).getArrivalOccurrenceDateTime();  
			CustomerProcedureDTO cProceduresDTO = Utils.getCustomerProceduresFromKcxId(this.kidsHeader.getReceiver(), 
															this.kidsHeader.getProcedure().toUpperCase());
			if (cProceduresDTO != null && cProceduresDTO.getKidsRelease() != null &&   	
					cProceduresDTO.getKidsRelease().equalsIgnoreCase("ALT")) {
				arrivalDateTime = departureDateTime;
			} else { 
				arrivalDateTime = ffm.getArrivalEventList().get(0).getArrivalOccurrenceDateTime();  
			}  //EI20140320-end
			
			if (ffm.getArrivalEventList().get(0).getAssociatedTransportCargoList() != null && 
					ffm.getArrivalEventList().get(0).getAssociatedTransportCargoList().get(0) != null) {						
				if (ffm.getArrivalEventList().get(0).getAssociatedTransportCargoList().get(0).getUtilizedUnitLoadTransportEquipment() != null) {
					TransportCarriage tc = ffm.getArrivalEventList().get(0).getAssociatedTransportCargoList().get(0).
											   getUtilizedUnitLoadTransportEquipment().getOnCarriageTransportMovement();
					number = tc.getId();
					if (tc.getCarrierParty() != null) {
						carrierCode = tc.getCarrierParty().getPrimaryId();
					}
					if (tc.getArrivalDestinationEvent() != null && tc.getArrivalDestinationEvent().getOccurrenceDestinationLocation() != null) {
						String destinationLocation = tc.getArrivalDestinationEvent().getOccurrenceDestinationLocation().getId();
					}
				}
			}
		}	
														//calculated by station: vtrkdnr, vtreori (==KIDS: Agent)	
		Transport transport = new Transport();	
		transport.setTransportMode("04");   //EI20140218
		transport.setTransportationNumber(flightNumber); //sak.befnum nicht fuellen, dafuer sak.fltnum-aufbereitet: 		
		message.setTransport(transport);
		
		FlightDetails flightDetails = new FlightDetails();
		flightDetails.setFlightNumber(flightNumber);
		flightDetails.setCarrierCode(carrierCode);	
		flightDetails.setNumberOfFlight(number);				
		if (!Utils.isStringEmpty(number)) {
			int len = number.length();
			flightDetails.setNumberOfFlight(number.substring(0, len));
			if (!Utils.isSignNumeric(number.substring(len - 1, len))) {	
				flightDetails.setNumberOfFlight(number.substring(0, len - 1));
				flightDetails.setAdditionalQualifier(number.substring(len - 1, len).trim()); 
			}
		}
		flightDetails.setDepartureDateTime(departureDateTime);
		flightDetails.setAirportOfDeparture(departureCode);
		flightDetails.setArrivalDateTime(this.calculateDate(arrivalDateTime, "YYYYMMDD"));		
		flightDetails.setAirportOfArrival(arrivalLocationCode); //EI20140115
		message.setFlightDetails(flightDetails);
		
		//EI20140115:
		if (Utils.isStringEmpty(flightNumber) || Utils.isStringEmpty(ddmmjjD) || Utils.isStringEmpty(arrivalLocationCode)) {			
			Utils.log("CmpToKids Reexport: flightNumber/ddmmjj/arrivalLocationCode: " + flightNumber + "/" + ddmmjjD + "/" + arrivalLocationCode);
			abbruchText = "CmpTpKids Reexport: ReferenceNumber could not be calculated";
			return false;
		}	
		//NCTS und Reexport: beznr = flughr + ddmmjjD + POU	
		referenceNumber = this.calculateReferenceNumber(flightNumber, ddmmjjD, arrivalLocationCode); 
		message.setReferenceNumber(referenceNumber);	
		Utils.log("CmpToKids Reexport: ReferenceNumber: " + referenceNumber);
		
		
		//////// Items GoodsItems TsSAP  ///////////////	
		///
	if (ffm.getArrivalEventList() != null) {
		int i = 0;
		int j = 0;		
		for (ArrivalEvent itemEvent : ffm.getArrivalEventList()) {		//Master-AWB???
			//i = i + 1;
			if (itemEvent.getAssociatedTransportCargoList() == null) {  //EI20140210
				continue;
			}
			for (TransportCargo transportCargo : itemEvent.getAssociatedTransportCargoList()) {				
				if (transportCargo == null) {
					continue;
				}											
				for (MasterConsignmentFFM itemConsignment : transportCargo.getIncludedMasterConsignmentList()) { //House-AWB???				
					j = j + 1;	
					if (itemConsignment != null) {
						message.addGoodsItemList(this.mapGoodsItem(itemConsignment));	
					}
				}
			}
		}
		}
				
		return true;
	}
	private GoodsItemReexport mapGoodsItem(MasterConsignmentFFM includedMasterConsignment) {
		if (includedMasterConsignment == null) {
			return null;
		}	
		GoodsItemReexport item = new GoodsItemReexport();
		
		//item.setItemNumber("" + j);  wird in Zabis ermittelt
		item.setItemNumber("0");    
		
		Packages packages = new Packages();
		packages.setType("PK");  		 //fix PK?	 <== in FSS.SAP kommt nicht vor  
		packages.setQuantity(includedMasterConsignment.getTotalPieceQuantity());	//sap.colanz		
		item.addPackagesList(packages); 	
		
		if (includedMasterConsignment.getFinalDestinationLocation() != null) {
			item.setDestinationPlace(includedMasterConsignment.getFinalDestinationLocation().getId());  //sap.setBesort
		}
		item.setChangeFlag("0");  													//sap.kzaend
		
		ReferencedSpecification referencedSpecification = new ReferencedSpecification();
		referencedSpecification.setTypeOfSpecificationID("AWB");   	       // sap.idkzawb 		
		String awb = includedMasterConsignment.getTransportContractDocument().getId();
		if (!Utils.isStringEmpty(awb)) {									
			awb = awb.replace("-", "");	
			referencedSpecification.setSpecificationID(awb.trim());               //sap.idawbzzz 
		}		
		item.setReferencedSpecification(referencedSpecification);
		
		ManifestReference manifestReference = new ManifestReference();  
		manifestReference.setReferencedSpecification(referencedSpecification); //sap.Idkzawb, sap.Idawbzzz		
		item.setReference(manifestReference);
		
		//Rest ist NA
		
		return item;
	}
	
	public String getCalculatedKcxId() {   //EI20140116
		return calculatedKcxId;
	}
}
