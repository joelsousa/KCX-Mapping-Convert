package com.kewill.kcx.component.mapping.companies.unisys.ics.msg;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KidsMessageICS<br>
 * Erstellt		: 09.12.2010<br>
 * Beschreibung	: Fields and methods to write ICS-KidsMessages .
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class UnisysMessageICS extends UidsMessage {	
	
	public UnisysHeader unisysHeader = null;
	
	public void mapHeaderKidsToUnisys(UnisysHeader unisysHeader, String docType, String type) {
		if (unisysHeader == null) {
			return;
		}

		unisysHeader.setMsgType(type);
		unisysHeader.setMsgDocType(docType);
		unisysHeader.setMsgName("DATA-FOR-CUSTOMER");
		//EI20110118: unisysHeader.setMsgFunction("REQUEST"); 
		unisysHeader.setMsgFunction("RESPONSE"); 
		unisysHeader.setMsgVersion("1.0");
		unisysHeader.setMsgSender(kidsHeader.getTransmitter());      
		unisysHeader.setMsgRecipient(kidsHeader.getReceiver());   
		unisysHeader.setActMsgDateTime();
		//is mapped to Message.referenceNumber
		unisysHeader.setMsgId(kidsHeader.getMessageID());  //EI20110131: ist doch richtig, oder???
		//unisysHeader.setMsgRelated();  //will be filled later with kids.referenceNumber, 
										 //kidsHeader.getInReplyTo() were also right
		unisysHeader.setMsgRelatedDateTime(kidsHeader.getSentAt());      
	}	
	public String getTimeHhMm(String argument) {
		String time = "";		
		if (argument == null) {
			return "0000";
		}
		if (!checkInt(argument)) {
			return "0000";
		}
		
		if (time.length() == 4) {
			return time;
		} else if (time.length() > 4) {
			return time.substring(0, 4);
		} else {
			return "0000";
		}	
	}
	private boolean checkInt(String argument) {       
        boolean ret = false;
        if (argument == null) {
			return ret;
		}
        try {
			int value = Integer.parseInt(argument);
			ret = true;
		} catch (NumberFormatException e) {
		   ret = false;
		}
		return ret;
	}	
	public String getUnisysHeaderDate(String kidsDateTime) {
		String unisysDate = "";
		if (kidsDateTime == null) {
			return unisysDate;
		}
		if (kidsDateTime.length() >= 8) {
			unisysDate = kidsDateTime.substring(0, 4) +  "-" + kidsDateTime.substring(4, 6) +  "-" + kidsDateTime.substring(6, 8);
		}
		return unisysDate;
	}
	public String getUnisysHeaderTime(String kidsDateTime) {
		String unisysTime = "";
		if (kidsDateTime == null) {
			return unisysTime;
		}
		
		if (kidsDateTime.length() == 12) {
			unisysTime = kidsDateTime.substring(8, 10) +  ":" + kidsDateTime.substring(10, 12) +  ":00" ;
		} else if (kidsDateTime.length() == 14) {
			unisysTime = kidsDateTime.substring(8, 10) +  ":" + kidsDateTime.substring(10, 12) +  ":" + kidsDateTime.substring(12, 14);
		}
		return unisysTime;
	}
	public String getUnisysTime(String datetime) {	
		String time = "";
		if (datetime == null) {
			return time;
		}
		
		if (datetime == null) {
			return "";
		}
		if (datetime.length() >= 12) {
			time = datetime.substring(8, 12);		
			if (!checkInt(time)) {			
				time = "";
			}
		} 		
		return time;
	}	
	public String getUnisysDate(String datetime) {
		String unisysDate = "";	
		if (datetime == null) {
			return unisysDate;
		}
						
		if (datetime.length() > 7) {		
			unisysDate = datetime.substring(6, 8) + datetime.substring(4, 6) + datetime.substring(2, 4);			 
			if (checkInt(unisysDate)) {			
				unisysDate = datetime.substring(6, 8) + getMonFromMm(datetime.substring(4, 6)) + datetime.substring(2, 4);
			} else {
				unisysDate = "";
			}
		}
		return unisysDate;
	}	
	
	//AK20110803  missing break in switch
	private String getMonFromMm(String mm) {
		if (mm == null) {
			return "";
		}
		String mon = "XXX";
		Utils.stringToInt(mm);
		int ii = Utils.stringToInt(mm);
		if (mm.length() == 2) {			
			switch (ii) {	
			case 1:
				mon = "JAN";
				break;
			case 2:
				mon = "FEB";
				break;
			case 3:
				mon = "MAR";
				break;
			case 4:
				mon = "APR";
				break;
			case 5:
				mon = "MAY";
				break;
			case 6:
				mon = "JUN";
				break;
			case 7:
				mon = "JUL";
				break;
			case 8:
				mon = "AUG";
				break;
			case 9:
				mon = "SEP";
				break;
			case 10:
				mon = "OCT";
				break;
			case 11:
				mon = "NOV";
				break;
			case 12:
				mon = "DEC";
				break;
			default:
				break;
			}
		}
		return mon;
	}
	private String getMmFromMon(String mon) {
		if (mon == null) {
			return "";
		}
		String mm = "XX";
		
		if (mon.equals("JAN")) {		
			mm = "01";
		}
		if (mon.equals("FEB")) {		
			mm = "02";
		}
		if (mon.equals("MAR")) {		
			mm = "03";
		}
		if (mon.equals("APR")) {		
			mm = "04";
		}
		if (mon.equals("MAY")) {		
			mm = "05";
		}
		if (mon.equals("JUN")) {		
			mm = "06";
		}
		if (mon.equals("JUL")) {		
			mm = "07";
		}
		if (mon.equals("AUG")) {		
			mm = "08";
		}
		if (mon.equals("SEP")) {		
			mm = "09";
		}
		if (mon.equals("OCT")) {		
			mm = "10";
		}
		if (mon.equals("NOV")) {		
			mm = "11";
		}
		if (mon.equals("DEC")) {		
			mm = "12";
		}	
		return mm;	
	}	
	public void writeDateAndTime(String kidsDateTime)  throws XMLStreamException {
		if (Utils.isStringEmpty(kidsDateTime)) {
    		return;
    	}		   	  
		writeElement("DATE", getUnisysDate(kidsDateTime));        		
		writeElement("TIME", getUnisysTime(kidsDateTime));    		    			
	}
	
	public void writeParticipant(String partyType, Party party) throws XMLStreamException {
		if (party == null) {
    	    return;
    	}
		if (Utils.isStringEmpty(partyType)) {
    	    return;
    	}
		
		openElement("PARTICIPANT");		   			
			writeElement("TYPE", partyType);
			if (party.getPartyTIN() != null && !Utils.isStringEmpty(party.getPartyTIN().getTIN())) {
				openElement("REFERENCE");
					writeElement("EORI", party.getPartyTIN().getTIN());	
				closeElement(); 
			}
			if (party.getAddress() != null) {
			writeElement("NAME", party.getAddress().getName());			
			writeElement("ADDR", party.getAddress().getStreet());		
			writeElement("CITY", party.getAddress().getCity());
			//writeElement("STATE", party.getAddress()
			writeElement("COUNTRY", party.getAddress().getCountry());
			writeElement("POSTCODE", party.getAddress().getPostalCode());
			}
        closeElement();  
    } 		
	public void writeCustRespRef(String beznr) throws XMLStreamException {	//EI20110118
		if (Utils.isStringEmpty(beznr)) {
			return;
		}
		openElement("CUST-RESP-REF");    
			writeElement("ENS-LREF", beznr);   
		closeElement();
	}
	
	public void writeCustReqInfo(String kidsDateTime) throws XMLStreamException {	
		if (Utils.isStringEmpty(kidsDateTime)) {
			return;
		}
		openElement("CUST-REQ-INFO");    
			writeDateAndTime(kidsDateTime);   
		closeElement();
	}
	
    public void writeCustAckInfo(String mrn, String kidsDateTime) throws XMLStreamException {
    	
    	if (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(kidsDateTime)) {
    		return;
    	}    	   
    	openElement("CUST-ACK-INFO");    
    		writeDateAndTime(kidsDateTime); 
    		if (!Utils.isStringEmpty(mrn)) {
    			writeElement("MRN", mrn); 	    
    		}
	    closeElement();
    } 
    
 	public void writeUldNumberList(List<String> list) throws XMLStreamException {
 		if (list == null || list.size() == 0) {
 			return;
 		} 		
 		openElement("ULDNUMBERS");
 			for (String number : list) { 			
 				writeElement("ULDID", number);
 			}
		closeElement();	
 	}
 	
 	public void writeDocumentList(List<IcsDocument> list) throws XMLStreamException {
 		if (list == null || list.size() == 0) {
 			return;
 		}
 		for (IcsDocument document : list) { 
 			if (document != null) {
 				openElement("DOCUMENTS");
 				writeElement("DOC-TYPE", document.getType());	
				writeElement("DOC-NUMBER", document.getReference());		
				closeElement();	
 			}
 		}
 	}
 
 	 public void writeDetail(String shipmentNumber, String conveyanceReference, String declDate, 
     		String  customsOfficeFirstEntry, TransportMeans transportBorder,
     		String  itemNumber, List<TransportMeans> itemBorderList,
     		List<String> containersList, List<IcsDocument> documentList) throws XMLStreamException {
     	//TODO
 		 openElement("DETAIL");  
	
 		 	writeElement("CUST-ITEM", itemNumber); 
 		 	//AK2011-01-27-1
 		 	if (containersList != null || documentList != null) {
 	 		 	openElement("CUST-AWB-INFO");
	 		 		writeUldNumberList(containersList);
	 		 		writeDocumentList(documentList);                				
	 		 	closeElement();
 		 	}

			openElement("CUST-GOODS-INFO");
				openElement("GDS-DETAIL");
					openElement("FLIGHT-INFO");
					if (transportBorder != null) {
						writeElement("MODE", getUnisysTransportMode(transportBorder.getTransportMode()));
						//AK2011-01-27-2
						if (!Utils.isStringEmpty(transportBorder.getTransportationCountry())) {
							openElement("NATIONALITY");
								writeElement("COUNTRY", transportBorder.getTransportationCountry());
							closeElement();
						}
 	                }
					closeElement();  //FLIGHT-INFO

					if (!Utils.isStringEmpty(declDate) || !Utils.isStringEmpty(customsOfficeFirstEntry)) {
						openElement("ARRIVAL");
							writeElement("PORT-CODE", customsOfficeFirstEntry);
							if (!Utils.isStringEmpty(declDate)) {
								openElement("STA");
									writeDateAndTime(declDate); 
								closeElement(); //STA
							}
						closeElement();  //ARRIVAL
					}
					if (!Utils.isStringEmpty(shipmentNumber) || !Utils.isStringEmpty(declDate) ||
							!Utils.isStringEmpty(conveyanceReference)) {
							openElement("CTRY-SPEC");
								openElement("CUSTOMS");
									writeElement("CGO-CTRL-NO", shipmentNumber);
									String dateA = "";
									if (declDate != null && declDate.length() >= 8) {
										dateA = declDate.substring(6, 8) + declDate.substring(4, 6) 
												+ declDate.substring(2, 4);
									}
									String convNo = conveyanceReference + dateA;
									writeElement("CONVEY-NO", convNo);
								closeElement(); //CUSTOMS
							closeElement(); //CTRY-SPEC
					}
				closeElement();  //GDS-DETAIL
			closeElement(); //CUST-GOODS-INFO

		 closeElement(); //DETAIL
 	 }
 	 private String getUnisysTransportMode(String kidsMode) throws XMLStreamException {
 		if (kidsMode == null) {
 			return "";
 		}
 		String mode = "";
		if (kidsMode.equals("4")) {   
			mode = "A";   //TODO: oder "Air" ???
		} else if (kidsMode.equals("3")) {
			mode = "T";  //TODO: oder "Truck" ???
		}
		return mode;
 	 }
 	 
 	public void writeErrorInfo(FunctionalError error) throws XMLStreamException {
 		if (error == null) {
 			return;
 		}
 		openElement("CUST-ERR-INFO"); 		
 			writeElement("ERR-TYPE", error.getErrorType());
 			writeElement("ERR-POINTER", error.getErrorPointer());
 			writeElement("ERR-REASON", error.getErrorReason());
 			writeElement("ATTRIBUTE", error.getOriginalAttributeValue()); 			
 		closeElement();
 	}
 	
 	public void writeErrorInfo(PositionError error) throws XMLStreamException {
 		if (error == null) {
 			return;
 		}
 		openElement("CUST-ERR-INFO");
			writeElement("ERR-TYPE", error.getKindOfError());
			writeElement("ERR-REASON", error.getErrorCode());
		closeElement();
 		openElement("CUST-REJ_INFO");
			writeElement("TEXT", error.getErrorText());
		closeElement();
 	}
}
