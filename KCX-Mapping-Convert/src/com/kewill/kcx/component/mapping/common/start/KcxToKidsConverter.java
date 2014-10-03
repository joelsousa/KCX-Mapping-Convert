/*
 * Funktion    : KcxToKidsConverter.java
 * Titel       :
 * Erstellt    : 11.12.2008
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
package com.kewill.kcx.component.mapping.common.start;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KcxToKidsConverter<br>
 * Erstellt		: 11.12.2008<br>
 * Beschreibung	: Removes the KCX-Envelope from a KIDS message. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KcxToKidsConverter {
//    private CommonFieldsDTO commonFieldsDTO     = null;
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;
    
    public String removeKcxEnvelope(String payload, String encoding, EDirections direction) throws Exception {
        // MS20110929 Begin
//        String content = null;
//        
//        InputStream ins = new StringInputStream(payload);
////        InputStreamReader is = new InputStreamReader(ins, encoding);
//        InputStreamReader is = new InputStreamReader(ins);
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(is);
//
//        XmlMsgScanner scanner = new XmlMsgScanner(parser);
//        scanner.skipTo(Token.START_TAG, "Content", 0);
//        scanner.next();
//        content = scanner.getLexem().trim();
//        if (Config.getLogXML()) {
//            Utils.log("(KcxToKidsConverter removeKcxEnvelope) content = \n" + content);
//        }
        
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, encoding);
        // MS20110929 End
        
        // MS20101018 Begin
        KidsHeader kidsHeader = getKidsHeader(content);
        commonFieldsDTO = new CommonFieldsDTO();
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
        // MS20101018 End
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());             // 20110120MS
        
        return content.trim();
    }
    
    private KidsHeader getKidsHeader(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        KidsHeader kidsHeader = new KidsHeader();
        kidsHeader.setParser(parser);
        kidsHeader.setHeaderFields();
        return kidsHeader;
    }
    
    // CK27022012
    public KidsHeader getKidsHeader(String content, String encoding) throws Exception {
        InputStream ins = new StringInputStream(content);
        //InputStreamReader is = new InputStreamReader(ins);
        // CK27022012
        InputStreamReader is = new InputStreamReader(ins, encoding);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        KidsHeader kidsHeader = new KidsHeader();
        kidsHeader.setParser(parser);
        kidsHeader.setHeaderFields();
        return kidsHeader;
    }
}

