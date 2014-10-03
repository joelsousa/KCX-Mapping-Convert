/*
 * Funktion    : XmlUtils.java
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
package com.kewill.kcx.component.mapping.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Modul		: XmlUtils<br>
 * Erstellt		: 03.11.2008<br>
 * Beschreibung	: Utility routines for XML writing. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class XmlUtils {
    private XMLStreamWriter writer = null;
    
    public XmlUtils(XMLStreamWriter writer) {
        this.writer = writer;
    }

    public void writeElement(String tag, String value) throws XMLStreamException {
        if (value != null) {
            if (!value.trim().equals("")) {
                writer.writeStartElement(tag);
                writer.writeCharacters(value);
                writer.writeEndElement();
            }
        } 
    }
    
    public void writeElements(String tag, String value) throws XMLStreamException {
        if (value == null || value.trim().equals("")) {
            writer.writeEmptyElement(tag);
        } else {
            writeElement(tag, value);
        }
    }    
    
    public void setAttribute(String tag, String value) throws XMLStreamException {
        writer.writeAttribute(tag, value);
    }
    
    public void openElement(String tag) throws XMLStreamException {
        writer.writeStartElement(tag);
    }

    public void closeElement() throws XMLStreamException {
        writer.writeEndElement();
    }

}
