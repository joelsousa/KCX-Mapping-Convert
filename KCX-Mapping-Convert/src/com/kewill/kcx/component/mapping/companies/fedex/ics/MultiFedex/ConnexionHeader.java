package com.kewill.kcx.component.mapping.companies.fedex.ics.MultiFedex;

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
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.companies.fedex.ics.EFedexHeaderTags;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class ConnexionHeader extends KidsMessage{
	
    private XMLStreamWriter writer;                   
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
   
    public ConnexionHeader() {
    }
    
    public ConnexionHeader(XMLStreamWriter writer) {
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
                    		Utils.log("(EnveloppeConnexion  START_ELEMENT: " + text);
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
                			characters = event.asCharacters();
                			this.applicationId = characters.getData();
                			break; 
                    	default:
        					break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    endElement = event.asEndElement();
//                    Utils.log("(KidsHeader setHeaderFields) LocalName = /" + endElement.getName());
                    qname = endElement.getName();
                    text = qname.getLocalPart();
                    Utils.log("(MFedexHeader setHeaderFields) END_ELEMENT: " + text);
                    if (text.equals("EnveloppeConnexion")) {
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
		if (Utils.isStringEmpty(date)) {	
			time = "00:00:00";
		} 
		return this.time.substring(0, 8);
	}
	public String getTimezone() {
		if (Utils.isStringEmpty(date)) {	
			return "";
		} else {
			return this.time.substring(8);
		}
	}
   
}
