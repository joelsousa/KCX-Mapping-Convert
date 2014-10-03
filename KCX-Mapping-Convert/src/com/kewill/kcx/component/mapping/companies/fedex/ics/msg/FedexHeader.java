package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.fedex.ics.EFedexHeaderTags;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class FedexHeader extends KidsMessage{
	
//    private XMLStreamWriter writer;                   
	private XMLEventReader 		parser	= null;	
	private String fedexNamespaceVersion = null;	
	private String countryCode  = "";      //TODO-ak: woher wird es gelesen? soll von receiver sein
	
	private String connexionId = "";            //TEnveloppeConnexion
	private String interchangeAgreementId = "";
	private String numEnveloppe = "";
	private String today = FssUtils.getToday();
	private String date = "";
	private String time = "";
	private String applicationId = ""; 
		
	private String schemaID  	    = "";    //TEnveloppeMessage
	private String schemaVersion 	= "";
	private String partyId			= "";
	private String transactionId	= "";	
	private String numseq			= "";
   
    public FedexHeader() {
    }
    
    public FedexHeader(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
	public String getUidsNamespaceVersion() {
		return fedexNamespaceVersion;
	}
	public void setUidsNamespaceVersion(String uidsNamespaceVersion) {
		this.fedexNamespaceVersion = uidsNamespaceVersion;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String argument) {
		this.countryCode = argument;
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
                    
                    switch(EFedexHeaderTags.valueOf(text)) {
                    	case EnveloppeConnexion:
                    		//Utils.log("(EnveloppeConnexion  START_ELEMENT: " + text);
                    		break;
                    	case connexionId:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.connexionId = characters.getData();
                			break;
                    	case interchangeAgreementId:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.interchangeAgreementId = characters.getData();
                			break;
                    	case numEnveloppe:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.numEnveloppe = characters.getData();
                			break;
                    	case DateTime:                    	
                			break;
                    	case date:
                    	case Date:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.date = characters.getData();
                			break;
                    	case time:
                    	case Time:
                    		event = parser.nextEvent();
                			characters = event.asCharacters();
                			this.time = characters.getData();
                			break;
                    	case applicationId:
                    		event = parser.nextEvent();
                    		if (applicationId.length() > 0) {
	                			characters = event.asCharacters();
	                			this.applicationId = characters.getData();
                    		}
                			break;
                    	case EnveloppeMessage:
                    		//Utils.log("(EnveloppeMessage  START_ELEMENT: " + text);
                    		break;                    	
                    	case schemaID:
                    			event = parser.nextEvent();
                    			characters = event.asCharacters();
                    			this.schemaID = characters.getData();
                    			break;
                    	case schemaVersion:
                    			event = parser.nextEvent();
                    			characters = event.asCharacters();
                    			this.schemaVersion = characters.getData();
                    			break;
                    	case partyId:
                    			event = parser.nextEvent();
                    			if (event != null && event.asCharacters() != null) {
                    				characters = event.asCharacters();
                    				this.partyId = characters.getData();
                    			}
                    			break;	
                    	case transactionId:
                    			event = parser.nextEvent();
                    			characters = event.asCharacters();
                    			this.transactionId = characters.getData();
                    			break;
                    	case numseq:
                    			event = parser.nextEvent();
                    			characters = event.asCharacters();
                    			this.numseq = characters.getData();
                    			break;
                    
	                	
                    		default: break;
                    	
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    endElement = event.asEndElement();
//                    Utils.log("(FedexHeader setHeaderFields) LocalName = /" + endElement.getName());
                    qname = endElement.getName();
                    text = qname.getLocalPart();
                    //Utils.log("(FedexHeader setHeaderFields) END_ELEMENT: " + text);
                    if (text.equals("EnveloppeMessage")) {
                    	break parserloop;
                    }
                default: break;
            } // end switch
          } // end while
    }

    public void setConnexionId(String argument) {
		this.connexionId = Utils.checkNull(argument);
	}
	public String getConnexionId() {
		return connexionId;
	}
	 
	public void setInterchangeAgreementId(String argument) {
		this.interchangeAgreementId = Utils.checkNull(argument);
	}
	public String getInterchangeAgreementId() {
		return interchangeAgreementId;
	}
	
	public void setNumEnveloppe(String argument) {
		this.numEnveloppe = Utils.checkNull(argument);
	}
	public String getNumEnveloppe() {
		return numEnveloppe;
	}
	
	public void setApplicationId(String argument) {
		this.applicationId = Utils.checkNull(argument);
	}
	public String getApplicationId() {
		return applicationId;
	}

	public void setSchemaID(String argument) {
		this.schemaID = Utils.checkNull(argument);
	}
	public String getSchemaID() {
		return schemaID;
	}
	
	public void setSchemaVersion(String argument) {
		this.schemaVersion = Utils.checkNull(argument);
	}
	public String getSchemaVersion() {
		return schemaVersion;
	}
	
	public void setPartyId(String argument) {
		this.partyId = Utils.checkNull(argument);
	}
	public String getPartyId() {
		return partyId;
	}
	
	public void setTransactionId(String argument) {
		this.transactionId = Utils.checkNull(argument);
	}
	public String getTransactionId() {
		return transactionId;
	}
	
	public void setNumseq(String argument) {
		this.numseq = Utils.checkNull(argument);
	}
	public String getNumseq() {
		return numseq;
	}
    /*
	public void setDateTime(String argument) {
		this.dateTime = Utils.checkNull(argument);
	}
	public String getDateTime() {
		return dateTime;
	}	
	*/
	public String getDay() {
		if (Utils.isStringEmpty(date)) {
			date = today;
		} 
		return date.substring(6, 8);
	}	
	public String getMonth() {
		if (Utils.isStringEmpty(date)) {
			date = today;
		} 
		return date.substring(4, 6);
	}
	public String getYear() {
		if (Utils.isStringEmpty(date)) {
			date = today;
		} 
		return this.date.substring(0, 4);
	}
	public String getTime() {
		if (Utils.isStringEmpty(time)) {
			time = "00:00:00";
		} 
		return this.time.substring(0, 8);
	}
	public String getTimezone() {
		if (Utils.isStringEmpty(time)) {
			return "";
		} else {
			return this.time.substring(8);
		}
	}
	
	public void mapHeaderKidsToFedex(KidsHeader kidsHeader ,FedexHeader fedexHeader, String docType) {
		if (fedexHeader == null) {
			return;
		}
		this.schemaID = docType;
		this.schemaVersion =  kidsHeader.getRelease();
		this.partyId = kidsHeader.getTransmitter();			
		this.transactionId = kidsHeader.getMessageID();
		
	}	
	
	
	public void writeHeader() {
		
		try {
			openElement("EnveloppeMessage");
				writeElement("schemaID", schemaID);
				writeElement("schemaVersion", schemaVersion);
				writeElement("partyId", partyId);
				writeElement("transactionId", transactionId);
//				writeElement("TesIndMES18, value) //needs to be added, also necessary for other countries
			closeElement();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
	    }

}
