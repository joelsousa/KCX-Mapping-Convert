/*
 * Funktion    : FedexEnvelope.java
 * Titel       :
 * Erstellt    : 21.01.2011
 * Author      : krzoska
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.companies.fedex.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.xml.XmlUtils;

/**
 * Modul		: FedexEnvelope<br>
 * Erstellt		: 21.01.2011<br>
 * Beschreibung	: Fedex envelope value object. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class FedexEnvelope {
    private String content   = null;          // Message content
    private String encoding  = "UTF-8";       // Message encoding
    
    

    public void writeHeader(XMLStreamWriter writer, String messageCode) {
        XmlUtils xmlUtils = new XmlUtils(writer);
        try {
            writer.writeDTD("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
            xmlUtils.openElement("TOM");
            writeTOMProperties(xmlUtils, messageCode);
	            xmlUtils.openElement("message");
	                    writer.writeCData(content);
	            xmlUtils.closeElement();  // message
            xmlUtils.closeElement();  // TOM
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
    
    protected void writeTOMProperties(XmlUtils xmlUtils, String messageCode) {
		try {
			xmlUtils.openElement("properties");
				xmlUtils.writeElement("dataName", "GeoCode");
				xmlUtils.writeElement("dataValue", "CDE");
				xmlUtils.closeElement();
			xmlUtils.openElement("properties");
				xmlUtils.writeElement("dataName", "RegProgram");
				xmlUtils.writeElement("dataValue", "DEKEWILL");
			xmlUtils.closeElement();
			xmlUtils.openElement("properties");
				xmlUtils.writeElement("dataName", "MsgType");
				xmlUtils.writeElement("dataValue", "RegFromCust");
			xmlUtils.closeElement();
			xmlUtils.openElement("properties");
				xmlUtils.writeElement("dataName", "TranType");
				xmlUtils.writeElement("dataValue", messageCode);
			xmlUtils.closeElement();
		}   catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
    
 
    /* ****************************************
     * Getters
     * ****************************************/ 
    public String getContent() {
        return content;
    }
    public String getEncoding() {
        return encoding;
    }
    
    /* ****************************************
     * Setters
     * ****************************************/ 
    public void setContent(String content) {
        this.content = content;
    }
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    
}
