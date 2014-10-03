/*
 * Funktion    : KcxEnvelope.java
 * Titel       :
 * Erstellt    : 03.11.2008
 * Author      : CSF GmbH / schmidt
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
package com.kewill.kcx.component.mapping.formats.kcx;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.xml.XmlUtils;

/**
 * Modul		: KcxEnvelope<br>
 * Erstellt		: 03.11.2008<br>
 * Beschreibung	: KCX envelope value object. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KcxEnvelope {
    private String primary   = null;          // First part of the routing address
    private String secondary = null;          // Second part of the routing address
    private String tertiary  = null;          // Third part of the routing address
    private String auditId   = null;          // UUID of the message beloging to this header
    private String content   = null;          // Message content
    private String encoding  = "UTF-8";       // Message encoding
    
    public void setFields(String primary, String secondary, String tertiary, String auditId) {
        this.primary   = primary;
        this.secondary = secondary;
        this.tertiary  = tertiary;
        this.auditId   = auditId;
    }
    
//    public void writeHeader(XMLStreamWriter writer) {
//        XmlUtils xmlUtils = new XmlUtils(writer);
//        try {
//            writer.writeDTD("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
//            xmlUtils.openElement("KCX");
//                xmlUtils.writeElement("Primary", primary.trim());
//                xmlUtils.writeElement("Secondary", secondary);
//                xmlUtils.writeElement("Tertiatry", tertiary);
//                xmlUtils.writeElement("Audit_Id", auditId);
//                xmlUtils.openElement("Content");
//                    writer.writeCData(content);
//                writer.close();  // Content
//            writer.close();  // KCX
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        }
//    }
    
    public void writeHeader(XMLStreamWriter writer) {
        XmlUtils xmlUtils = new XmlUtils(writer);
        try {
            writer.writeDTD("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
            xmlUtils.openElement("KCX");
                xmlUtils.writeElement("Primary", primary.trim());
                xmlUtils.writeElement("Secondary", secondary);
                xmlUtils.writeElement("Tertiatry", tertiary);
                xmlUtils.writeElement("Audit_Id", auditId);
                xmlUtils.openElement("Content");
                    writer.writeCData(content);
                xmlUtils.closeElement();  // Content
            xmlUtils.closeElement();  // KCX
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
    
    public String toString() {
        return primary + "/" + secondary + "/" + tertiary + "/" + auditId; 
    }
    
    /* ****************************************
     * Getters
     * ****************************************/ 
    public String getPrimary() {
        return primary;
    }
    public String getSecondary() {
        return secondary;
    }
    public String getTertiary() {
        return tertiary;
    }
    public String getAuditId() {
        return auditId;
    }
    public String getContent() {
        return content;
    }
    public String getEncoding() {
        return encoding;
    }
    
    /* ****************************************
     * Setters
     * ****************************************/ 
    public void setPrimary(String primary) {
        this.primary = primary;
    }
    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }
    public void setTertiary(String tertiary) {
        this.tertiary = tertiary;
    }
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    
}
