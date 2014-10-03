package com.kewill.kcx.component.mapping.countries.ie;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.db.CustomerHeadersDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.uids.common.EUidsHeaderTags;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : KidsToIreland<br>
 * Created : 23.05.2014<br>
 * Description : IrelandHeader.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class IrelandHeader extends IrelandMessage {
	
	private XMLEventReader parser	= null;	
	private String namespaceVersion = null;
	
	private String messageName = "";
	
	public IrelandHeader() {
    }
	public IrelandHeader(XMLStreamWriter writer) {
        this.writer = writer;
    }
	 public void setParser(XMLEventReader parser) {
		this.parser = parser;
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
            
            switch(event.getEventType()) {
            	case XMLStreamConstants.START_ELEMENT:
            		startElement = event.asStartElement();
            		qname = startElement.getName();
            		text = qname.getLocalPart();
                     
            		this.messageName = text;
            		break parserloop; 
            	/*	
                switch(EIrelandMessages.valueOf(text)) {
                case MessageAcknowledgement:
                	event = parser.nextEvent();
                	characters = event.asCharacters();
                	//this.messageName = characters.getData();
                	break;
                
                	//usw
                default: 
                	break;
                }
                
            	case XMLStreamConstants.END_ELEMENT:
            		endElement = event.asEndElement();
            		// if EndTag of "Header" is found: break because 
            		// the Header is complete
            		qname = endElement.getName();
            		text  = qname.getLocalPart();
            		if (text.equals("Header")) {
            			
            	}
            	default: 
            		break;
                 */
                                                          
            }
            
    	}
	}
	
	 public void writeHeader(CommonFieldsDTO commonFieldsDTO) {
	        String kcxId = null;
	        if (commonFieldsDTO.getDirection() == EDirections.CountryToCustomer) {
	            kcxId = commonFieldsDTO.getKcxId();
	        } else {
	            kcxId = commonFieldsDTO.getCountryCode();
	        }
	      /*
	        String procedure = commonFieldsDTO.getProcedure().toUpperCase();
	        CustomerHeadersDTO customerHeadersDTO = Db.getCustomerHeaders(kcxId, procedure);
	        Utils.log("(KidsHeader writeHeader) customerHeadersDTO = " + customerHeadersDTO);
	        if (customerHeadersDTO == null) {
	            writeHeaderV10();
	        } else {
	            String headerVersion = customerHeadersDTO.getUidsVersion();
	            Utils.log("(KidsHeader writeHeader) UIDS headerVersion = " + headerVersion);
	            if (headerVersion.equals("1.1")) {
	                writeHeaderV11();
	            } else {
	                if (headerVersion.equals("1.2")) {
	                    writeHeaderV12();
	                } else {
	                    writeHeaderV10();
	                }
	            }
	        }
	        */
	    }
	 public String getMessageName() {		
		 return messageName;
	 }
	 
	 public String getMesageReceiver() {
		 //todo
		 return "";
	 }
	 public String getMesageReferenceNumber() {
		 //todo
		 return "";
	 }
	 
	 public String getProcedure() {
		 //todo
		 return "";
	 }
	 public String getRelease() {
		 //todo
		 return "";
	 }
}
