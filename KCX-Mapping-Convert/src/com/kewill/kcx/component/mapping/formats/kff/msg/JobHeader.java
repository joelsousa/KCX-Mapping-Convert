package com.kewill.kcx.component.mapping.formats.kff.msg;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class JobHeader extends UidsMessage {
	
    //private XMLStreamWriter writer;                   
	private XMLEventReader 		parser	= null;	
	
	private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
    public JobHeader() {        
    }
    
    public JobHeader(XMLStreamWriter writer) {
        this.writer = writer;       
    }
    
    public void setParser(XMLEventReader parser) {
		this.parser = parser;	
	}
   
	private String documentFormat;
	private String formatIdentifier;
	private String formatVersion;
	private String transmissionDate;
	private String transmissionTime;
	private String transmissionTimeZone;
	private String batchNo;
	private String testFlag;
	private String senderID;
	private String senderUserID;
	
    public void setHeaderFields() throws Exception {

    	EndElement 		endElement 		= null;
    	Characters 		characters 		= null;
    	StartElement 	startElement 	= null;
    	QName 			qname			= null;
    	String			text			= null;
    	
    	XMLEvent event = null;
    	parserloop:
    	while (parser.hasNext()) {
            event = parser.nextEvent();
            //Utils.log("(FedexdHeader setHeaderFields) event = " + event);
            
            switch(event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                	startElement = event.asStartElement();
                    qname = startElement.getName();
                    text = qname.getLocalPart();
                    String dummy = "";
                    dummy = text;
               if (text == null || text.equals("")) {
            	   event = parser.nextEvent();
               } else {
                    switch(EJobHeaderTags.valueOf(text)) {
                		case Job:
                    		event = parser.nextEvent();
                		break;
                    	case DocumentInformation:
                    		event = parser.nextEvent();
                    		Utils.log("(MsgHeader  START_ELEMENT: " + text);
                    		break;
                    	case DocumentFormat:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.documentFormat = characters.getData();
                			break;
                    	case FormatIdentifier:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.formatIdentifier = characters.getData();
                			break;
                    	case FormatVersion:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.formatVersion = characters.getData();
                			break;
                    	case TransmissionDateTime: 
                    		event = parser.nextEvent();
                    		Utils.log("(MsgHeader  START_ELEMENT: " + text);
                    		break;
                    	case TransmissionDate:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.transmissionDate = characters.getData();
                			break;
                    	case TransmissionTime:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.transmissionTime = characters.getData();
                			break;
                    	case TransmissionTimeZone:
                   			event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.transmissionTimeZone = characters.getData();
                   			break;
                    	case BatchNo:
                   			event = parser.nextEvent();
                  			characters = event.asCharacters();
                   			this.batchNo = characters.getData();
                   			break;
                    	case TestFlag:
                   			event = parser.nextEvent();
                  			characters = event.asCharacters();
                   			this.testFlag = characters.getData();
                   			break;

                    	case UserInformation:
                    		event = parser.nextEvent();
                    		Utils.log("(MsgHeader  START_ELEMENT: " + text);
                    		break;
                    	case SenderID:
                   			event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.senderID = characters.getData();
                   			break;
                    	case SenderUserID:
                    		event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.senderUserID = characters.getData();
                   			break;                   		
                    	default: break;                    	
                    }
               }
                    break;
               
                case XMLStreamConstants.END_ELEMENT:
                    endElement = event.asEndElement();
                    qname = endElement.getName();
                    text = qname.getLocalPart();
                    Utils.log("(KffHeader END_ELEMENT: " + text);
                    //if (text.equals("CUST-MSG-ACTION")) {
                    if (text.equals("UserInformation")) {
                    	break parserloop;
                    }
                default: break;
            } // end switch
          } // end while
    }

 	

	public String getDocumentFormat() {
		return documentFormat;
	}
	public void setDocumentFormat(String value) {
		this.documentFormat = Utils.checkNull(value);
	}

	public String getFormatIdentifier() {
		return formatIdentifier;
	}
	public void setFormatIdentifier(String value) {
		this.formatIdentifier = Utils.checkNull(value);
	}
	
	public String getFormatVersion() {
		return formatVersion;
	}
	public void setFormatVersion(String value) {
		this.formatVersion = Utils.checkNull(value);
	}
	
	public String getTransmissionDate() {		
		return transmissionDate;
	}
	public void setTransmissionDate(String value) {
		this.transmissionDate = Utils.checkNull(value);
	}
	
	public String getTransmissionTime() {
		return transmissionTime;
	}
	public void setTransmissionTime(String value) {
		this.transmissionTime = Utils.checkNull(value);
	}
	
	public String getTransmissionTimeZone() {
		if (transmissionTimeZone.equalsIgnoreCase("Asia/Singapore")) {
			transmissionTimeZone = "-0800";  //8? TODO-PORT
		}	
		if (transmissionTimeZone.equalsIgnoreCase("Europe/Berlin")) {
			transmissionTimeZone = "+0100";  //1? TODO-PORT
		}
		return transmissionTimeZone;
	}
	public void setTransmissionTimeZone(String value) {
		this.transmissionTimeZone = Utils.checkNull(value);
	}

	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String value) {
		batchNo = value;
	}	 
   
	public String getSenderId() {
		return senderID;	
	}
	public void setSenderId(String msgId) {
		this.senderID = Utils.checkNull(msgId);
	}

	public String getSenderUserId() {
		return senderUserID;	
	}
	public void setSenderUserId(String msgRelated) {
		this.senderUserID = Utils.checkNull(msgRelated);
	}

//////////			
	public String getDay() {
		String date = calculateDate(transmissionDate);	
		if (Utils.isStringEmpty(date)) {
			return "";
		} else if (date.length() == 8) {
			return date.substring(6, 8);
		} else {
			return "";
		}		
	}	
	public String getMonth() {
		String date = calculateDate(transmissionDate);	
		if (Utils.isStringEmpty(date)) {
			return "";
		} else if (date.length() == 8) {
			return date.substring(4, 6);
		} else {
			return "";
		}
	}
	public String getYear() {
		String date = calculateDate(transmissionDate);
		if (Utils.isStringEmpty(date)) {
			return "";
		} else if (date.length() == 8) {
			return date.substring(0, 4);
		} else {
			return "";
		}
	}

	public String getTime() {		
		//return calculateTime(transmissionTime);	
		return transmissionTime;
	}
	
	private String calculateDate(String dateTime) {		
		if (dateTime == null) {
			return "";
		}
		
		String date = "";
		if (Utils.isStringEmpty(dateTime)) {
			String today = SDF.format(new Date());
			date = today.substring(0, 4) + today.substring(5, 7) + today.substring(8, 10);	
		} else if (dateTime.length() == 10) {
			date = dateTime.substring(0, 4) + dateTime.substring(5, 7) + dateTime.substring(8, 10);
		} else  {
			date = dateTime;
		} 
		return date;
	}
	private String calculateTime(String dateTime) {		
		if (dateTime == null) {
			return "";
		}
		if (Utils.isStringEmpty(dateTime)) {
			return "";
		}		
		String time = "";
		if (dateTime.length() == 19) {		
			time = dateTime.substring(11, 13) + dateTime.substring(14, 16) + dateTime.substring(17, 19);
		} else if (dateTime.length() == 11 && dateTime.substring(6, 7).equals("T")) {
			time = dateTime.substring(7, 9) + ":" + dateTime.substring(9, 11) + ":00";
		} else {
			time = "00:00:00";
		}
		return time;
	}
	
	public void writeHeader(String referenceNumber) {
	/*
	    try {
	            //openElement("soap:Header");
	                openElement("MSG-HEADER");
	                	//setAttribute("xmlns", "http://www.eurtradenet.com/schemas/header/200310");
	                	//setAttribute("soap:mustUnderstand", "true");
	                	writeElement("MSG-TYPE", msgType);
	                	writeElement("MSG-NAME", msgName);
	                	writeElement("MSG-FUNCTION", msgFunction);
	                	writeElement("MSG-VERSION", msgVersion);
	                	writeElement("MSG-SENDER", msgSender);
	                	writeElement("MSG-RECIPIENT", msgRecipient);	                	
	                	writeElement("MSG-DATE-TIME", msgDateTime);
	                	writeElement("MSG-ID", msgId);
	                	writeElement("MSG-DOC-TYPE", msgDocType);
	                	writeElement("MSG-RELATED", referenceNumber);
	                	writeElement("MSG-RELATED-DATE-TIME", msgRelatedDateTime);
	               	closeElement(); // Header
	            //closeElement(); // soap:Header
	    } catch (XMLStreamException e) {	           
	            e.printStackTrace();
	    }
	    */	        
	}

	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		this.testFlag = Utils.checkNull(testFlag);
	}

}
