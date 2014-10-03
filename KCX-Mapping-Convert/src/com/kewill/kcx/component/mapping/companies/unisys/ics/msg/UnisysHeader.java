package com.kewill.kcx.component.mapping.companies.unisys.ics.msg;

/*
 * Function    : FedexHeader.java
 * Titel       :
 * Date        : 04.11.2010
 * Author      : Kewill 
 * Description : FedexHeader composed of two parts: 
 *             : TEnveloppeConnexion and TEnveloppeMessage
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.kewill.kcx.component.mapping.companies.unisys.ics.EUnisysHeaderTags;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class UnisysHeader extends UidsMessage{
	
    //private XMLStreamWriter writer;                   
	private XMLEventReader 		parser	= null;	
	
	private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	// MS 20101201
	private HashMap<String, String> enumMap = null;        // Map to convert XML-Tags to valid enum values (no dashes etc.)
   
    public UnisysHeader() {
        // MS 20101201
        initEnumMap();
    }
    
    public UnisysHeader(XMLStreamWriter writer) {
        this.writer = writer;
        // MS 20101201
        initEnumMap();
    }
    
    public void setParser(XMLEventReader parser) {
		this.parser = parser;
		initEnumMap();
	}

	private String msgType;
	private String msgName;
	private String msgFunction;
	private String msgVersion;
	private String msgSender;
	private String msgRecipient;
	private String msgDateTime;
	private String msgId;
	private String msgRelated;
	private String msgRelatedDateTime;
	private String msgDocType;
	//private String action;
	//private String today = FssUtils.getDateTime();
	
	
    // MS 20101201
	private void initEnumMap() {
		if (enumMap == null) {
		    enumMap = new HashMap<String, String>();
		    enumMap.put("MSG-ENVELOPE", "MsgEnvelope");
		    enumMap.put("MSG-HEADER", "MsgHeader");
		    enumMap.put("MSG-TYPE", "MsgType");
		    enumMap.put("MSG-NAME", "MsgName");
		    enumMap.put("MSG-FUNCTION", "MsgFunction");
		    enumMap.put("MSG-VERSION", "MsgVersion");
		    enumMap.put("MSG-SENDER", "MsgSender");
		    enumMap.put("MSG-RECIPIENT", "MsgRecipient");
		    enumMap.put("MSG-DATE-TIME", "MsgDateTime");
		    enumMap.put("MSG-ID", "MsgId");
		    enumMap.put("MSG-RELATED", "MsgRelated");
		    enumMap.put("MSG-RELATED-DATE-TIME", "MsgRelatedDateTime");
		    enumMap.put("MSG-BODY", "MsgBody");
		    enumMap.put("MSG-RESPONSE", "MsgResponse");
		    enumMap.put("CUST-AWB-MSG", "CustAwbMsg");
		    enumMap.put("CUST-FLT-MSG", "CustFltMsg");
		    enumMap.put("CUST-MSG-ACTION", "CustMsgAction");
		    enumMap.put("MSG-DOC-TYPE", "MsgDocType");
		}
	}
	
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
                    //Utils.log("(FedexHeader setHeaderFields) START_ELEMENT: " + text);
                    
                    // MS 20101201
                    text = enumMap.get(text);
                    String dummy = "";
                    dummy = text;
               if (text == null || text.equals("")) {
            	   event = parser.nextEvent();
               } else {
                    switch(EUnisysHeaderTags.valueOf(text)) {
                		case MsgEnvelope:
                    		event = parser.nextEvent();
                		break;
                    	case MsgHeader:
                    		event = parser.nextEvent();
                    		Utils.log("(MsgHeader  START_ELEMENT: " + text);
                    		break;
                    	case MsgType:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.msgType = characters.getData();
                			break;
                    	case MsgName:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.msgName = characters.getData();
                			break;
                    	case MsgFunction:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.msgFunction = characters.getData();
                			break;
                    	case MsgVersion:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.msgVersion = characters.getData();
                			break;
                    	case MsgSender:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.msgSender = characters.getData();
                			break;
                    	case MsgRecipient:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.msgRecipient = characters.getData();
                			break;
                    	case MsgDateTime:
                   			event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.msgDateTime = characters.getData();
                   			break;
                    	case MsgId:
                   			event = parser.nextEvent();
                  			characters = event.asCharacters();
                   			this.msgId = characters.getData();
                   			break;
                    	case MsgRelated:
                   			event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.msgRelated = characters.getData();
                   			break;	
                    	case MsgRelatedDateTime:
                   			event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.msgRelatedDateTime = characters.getData();
                   			break;
                    	case MsgDocType:
                    		event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.msgDocType = characters.getData();
                   			break;
                   			/*
                    	case MsgBody:
                   			event = parser.nextEvent();
                   			break;
                    	case MsgResponse:
                   			event = parser.nextEvent();   
                   			this.action = "Response";
                   			break;
                    	case CustAwbMsg:
                   			event = parser.nextEvent();
                   			break;
                    	case CustMsgAction:
                   			event = parser.nextEvent();
                   			characters = event.asCharacters();
                   			this.action = characters.getData();
                   			break;
                         */
                    	default: break;                    	
                    }
               }
                    break;
               
                case XMLStreamConstants.END_ELEMENT:
                    endElement = event.asEndElement();
                    qname = endElement.getName();
                    text = qname.getLocalPart();
                    Utils.log("(UnisysHeader END_ELEMENT: " + text);
                    //if (text.equals("CUST-MSG-ACTION")) {
                    if (text.equals("MSG-HEADER")) {
                    	break parserloop;
                    }
                default: break;
            } // end switch
          } // end while
    }

  
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = Utils.checkNull(msgType);
	}

	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}
	
	public String getMsgFunction() {
		return msgFunction;
	}
	public void setMsgFunction(String msgFunction) {
		this.msgFunction = Utils.checkNull(msgFunction);
	}
	
	public String getMsgVersion() {
		return msgVersion;
	}
	public void setMsgVersion(String msgVersion) {
		this.msgVersion = Utils.checkNull(msgVersion);
	}
	
	public String getMsgSender() {
		return msgSender;
	}
	public void setMsgSender(String msgSender) {
		this.msgSender = Utils.checkNull(msgSender);
	}
	
	public String getMsgRecipient() {
		return msgRecipient;
	}
	public void setMsgRecipient(String msgRecipient) {
		this.msgRecipient = Utils.checkNull(msgRecipient);
	}

	public String getMsgDateTime() {
		return msgDateTime;
	}
	public void setActMsgDateTime() {
		String today = SDF.format(new Date());
		this.msgDateTime = today.substring(0, 10) + "T" + today.substring(11);
	}

	public String getMsgId() {
		return msgId;	
	}
	public void setMsgId(String msgId) {
		this.msgId = Utils.checkNull(msgId);
	}

	public String getMsgRelated() {
		return msgRelated;	
	}
	public void setMsgRelated(String msgRelated) {
		this.msgRelated = Utils.checkNull(msgRelated);
	}

	public String getMsgRelatedDateTime() {
		return msgRelatedDateTime;	
	}
	public void setMsgRelatedDateTime(String msgRelatedDateTime) {
		this.msgRelatedDateTime = Utils.checkNull(msgRelatedDateTime);
	}
	
	public String getMsgDocType() {
		return msgDocType;	
	}
	public void setMsgDocType(String argument) {
		this.msgDocType = Utils.checkNull(argument);
	}
	
	private String calculateDate(String dateTime) {		
		if (dateTime == null) {
			return "";
		}
		//if (dateTime.length() < 10) {
		//	return  "";
		//}
		String date = "";
		if (Utils.isStringEmpty(dateTime) || (dateTime.length() < 10)) {
			String today = SDF.format(new Date());
			date = today.substring(0, 4) + today.substring(5, 7) + today.substring(8, 10);	
		} else if (dateTime.length() == 11 && dateTime.substring(6, 7).equals("T")) {
			date = "20" + dateTime.substring(0, 2) + dateTime.substring(2, 6);
		}  else {
			date = dateTime.substring(0, 4) + dateTime.substring(5, 7) + dateTime.substring(8, 10);
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
	public String getDay() {
		String date = calculateDate(msgDateTime);	
		if (Utils.isStringEmpty(date)) {
			return "";
		} else if (date.length() == 8) {
			return date.substring(6, 8);
		} else {
			return "";
		}		
	}	
	public String getMonth() {
		String date = calculateDate(msgDateTime);	
		if (Utils.isStringEmpty(date)) {
			return "";
		} else if (date.length() == 8) {
			return date.substring(4, 6);
		} else {
			return "";
		}
	}
	public String getYear() {
		String date = calculateDate(msgDateTime);
		if (Utils.isStringEmpty(date)) {
			return "";
		} else if (date.length() == 8) {
			return date.substring(0, 4);
		} else {
			return "";
		}
	}

	public String getTime() {		
		return calculateTime(msgDateTime);	
	}
	
	public void writeHeader(String referenceNumber) {
	
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
	}

}
