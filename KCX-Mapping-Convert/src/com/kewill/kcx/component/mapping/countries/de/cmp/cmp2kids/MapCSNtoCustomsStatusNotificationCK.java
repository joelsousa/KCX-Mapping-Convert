package com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.cmp.msg.CustomsStatusNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCmpCompleteData;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.CustomsNote;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportMovementFWB;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgCustomsStatusNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.CustomsNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyCustomsStatusNotificationKids;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: CMP<br>
 * Created		: 16.12.2013<br>
 * Description	: Mapping of CMP-Format into KIDS-Format of CustomsStatusNotification Message.
 * 
 * @author iwaniuk
 * @version 1.0.00
 * 
 */

public class MapCSNtoCustomsStatusNotificationCK extends KidsMessageManifest20 {
	
	private BodyCustomsStatusNotificationKids body;
	private MsgCustomsStatusNotification message;
	private MsgCmpCompleteData	cmp;
	private String abbruchText = "";      //EI20140212
	
	private String calculatedKcxId = "";  //EI20140116
	private String procedure = "";  	  //EI20140220
	private boolean testmode = false;     //EI20140321
	
	public MapCSNtoCustomsStatusNotificationCK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgCustomsStatusNotification(parser);	       
		this.encoding = encoding;
	}
	
	public MapCSNtoCustomsStatusNotificationCK(MsgCmpCompleteData cmpMessage, String encoding) throws XMLStreamException {
		cmp = cmpMessage;
		message = new MsgCustomsStatusNotification();	       
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
            body    = new BodyCustomsStatusNotificationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                
            kidsHeader.setMessageName("CustomsStatusNotification");            
            ///EI20140116: kidsHeader.writeHeader(); erst in mapCmpToKids wird die richtige kcxId gesetzt
              
            procedure = kidsHeader.getProcedure();
            if (!this.mapCmpToKids()) {                    
            	//throw new KcxMappingException("Missing mandatory Tags in CMP file ! mapping was stopped!");
            	throw new KcxMappingException(abbruchText);
            }
              
            kidsHeader.setReceiver(calculatedKcxId);  
            this.getCommonFieldsDTO().setKcxId(calculatedKcxId); //EI29149123
            
            kidsHeader.writeHeader(); //EI20140116: 
            
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
		
		CustomsStatusNotification csn = null;				
		csn = cmp.getCustomsStatusNotification();		
		String arrivalCode = "";
		String departureCode = "";
		String finalDestinationCode = "";
		String departureDate = "";			//EI20140320
		 
		if (csn == null) {
			abbruchText = "CmpToKids CSU: CustomsStatusNotification is missing";
			return false;
		}
		if (csn.getBusinessHeaderDocument() == null) {
			abbruchText = "CmpToKids CSU: BusinessHeaderDocument is missing";
			return false;
		}		
		if (csn.getMasterConsignment() == null) {
			abbruchText = "CmpToKids CSU: MasterConsignmen is missing";
			return false;
		}
		if (csn.getMasterConsignment().getSpecifiedLogisticsTransportMovement() == null) {
			abbruchText = "CmpToKids CSU: SpecifiedLogisticsTransportMovement is missing";
			return false;
		}	
		
		////////////////						
		TransportMovementFWB stm = csn.getMasterConsignment().getSpecifiedLogisticsTransportMovement();
		String flightNumber = stm.getId();		
		if (Utils.isStringEmpty(flightNumber)) {
			abbruchText = "CmpToKids CSU: FlightNumber is missing";
			return false;
		}
		/*EI20140220:  verschoben unterhalb calculatedKcxId:
		if (!Utils.isStringEmpty(flightNumber)) {
			Transport transport = new Transport();
			transport.setTransportationNumber(flightNumber);
			transport.setTransportMode("04");  					//fix 04, 
			message.setTransport(transport);			
		}
		*/
		
		if (stm.getDepartureEvent() != null &&  //EI20140123
								stm.getDepartureEvent().getOccurrenceDepartureLocation() != null) {
			departureCode = stm.getDepartureEvent().getOccurrenceDepartureLocation().getId();
			departureDate = stm.getDepartureEvent().getDepartureOccurrenceDateTime();  //EI20140320
			message.setAirportOfDeparture(departureCode);
		}
		
		if (stm.getArrivalEvent() != null && 
				stm.getArrivalEvent().getOccurrenceArrivalLocation() != null) {
			
			arrivalCode = stm.getArrivalEvent().getOccurrenceArrivalLocation().getId();				
			message.setAirportOfArrival(arrivalCode);	//basman_nl
			
			String arrivalDate = "";
			
			arrivalDate = stm.getArrivalEvent().getArrivalOccurrenceDateTime();
			if (Utils.isStringEmpty(arrivalDate)) {
				arrivalDate = stm.getArrivalEvent().getScheduledOccurrenceDateTime();
			}						
						
			message.setDateTimeOfArrival(this.calculateDate(arrivalDate, "YYYYMMDDhhmm")); 
		}
		
		
					
		if (csn.getMasterConsignment().getAssociatedReferenceDocument() != null) {
			String awb = csn.getMasterConsignment().getAssociatedReferenceDocument().getId();	
			
			if (!Utils.isStringEmpty(awb)) {	
			/* EI20140227:
			String tmp = "";								
			tmp = awb.replace("-", "");							
			awb = tmp;
			*/
			ReferencedSpecification rs = new ReferencedSpecification();
			rs.setTypeOfSpecificationID("AWB");
			rs.setSpecificationID(awb);                  
			message.setReferencedSpecification(rs);
			}		
		}			
		
		String date = csn.getBusinessHeaderDocument().getIssueDateTime();
		String eindst = csn.getBusinessHeaderDocument().getInformation(); //TODO-Max: only in case of LH own ESUMA? woher soll ich es wissen?
		message.setDateTimeOfReceipt(this.calculateDateTime(date, "YYYYMMDDhhmm")); 
		message.setOfficeOfEntry(eindst);
		
		if (csn.getMasterConsignment().getIncludedCustomsNoteList() != null && 
			csn.getMasterConsignment().getIncludedCustomsNoteList().get(0) != null) {
			CustomsNote cn = csn.getMasterConsignment().getIncludedCustomsNoteList().get(0);
			String code = cn.getContentCode();
			String mrn = cn.getContent();
			if (!Utils.isStringEmpty(mrn) && !Utils.isStringEmpty(code) && 
					code.equalsIgnoreCase("M")) {   //EI20140220
				CustomsNotification customsNotification = new CustomsNotification();
				customsNotification.setContent(mrn);
				customsNotification.setContentCode(cn.getContentCode());
				customsNotification.setContentSubCode(cn.getSubjectCode());
				customsNotification.setCountryCode(cn.getCountryId());
				message.setCustomsNotification(customsNotification);
			}
		}
		message.setCustomsId(csn.getMasterConsignment().getCustomsId());
		message.setTotalPieces(csn.getMasterConsignment().getTotalPieceQuantity());
		
		if (csn.getMasterConsignment().getOriginLocation() != null) {
			String vland = Utils.getLandFromAirport(csn.getMasterConsignment().getOriginLocation().getId());
			message.setCountryOfDispatch(vland);  		
		}
		if (csn.getMasterConsignment().getFinalDestinationLocation() != null) {
			finalDestinationCode = csn.getMasterConsignment().getFinalDestinationLocation().getId();
			message.setDestinationPlace(finalDestinationCode); 
		} else {
			abbruchText = "CmpToKids CSN: KcxId could not be calculated because of FinalDestinationLocation is null";
			return false;
		}
		// EI TODO ??? welche jetzt? arrivalCode oder finalDestinationCode?
		if (procedure.equals("NCTS")) {  //EI20140220		
			calculatedKcxId = this.calculateKcxId(kidsHeader, arrivalCode, testmode);   //EI20140116
			Utils.log("(CmpToKids CSN:  KcxId calculated with ArrivalLocationCode (" + arrivalCode + ") = " + calculatedKcxId); 			
		} else {
			calculatedKcxId = this.calculateKcxId(kidsHeader, finalDestinationCode, testmode);   //EI20140116
			Utils.log("CmpToKids CSN: KcxId calculated with FinalDestinationCode (" + finalDestinationCode + ") = " + calculatedKcxId);
		}
		String country = "DE";		
		Transport transport = new Transport();
		transport.setTransportationNumber(flightNumber);	
		transport.setTransportMode("04");
		message.setTransport(transport);					
		if (calculatedKcxId != null && calculatedKcxId.length() > 2 && 
			calculatedKcxId.substring(0, 2).equals("AT")) {
			country = "AT";			
		} else {
			country = "DE";		
		}
		if (procedure.equals("NCTS")) {  //EI20140220 //TODO-Max ist das richtig?
			if (country.equals("AT")) {
				transport.setTransportMode("3");
			} else {
				transport.setTransportMode("30");
			}			
		}
		
		return true;
	}
	
	public String getCalculatedKcxId() {   //EI20140116
		return calculatedKcxId;
	}
}
