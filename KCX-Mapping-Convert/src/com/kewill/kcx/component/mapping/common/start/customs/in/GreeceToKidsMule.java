/*
 * Funktion    : GreeceToKidsMule.java
 * Titel       :
 * Erstellt    : 18.07.2011
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
package com.kewill.kcx.component.mapping.common.start.customs.in;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.GreeceToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul        : GreeceToKidsExtern<br>
 * Erstellt     : 18.07.2011<br>
 * Beschreibung : GreeceToKids called by MULE. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class GreeceToKidsMule extends GreeceToKidsConverter implements Callable {
    private MuleEventContext muleEventContext = null;
    private MuleMessage      muleMessage      = null;

    public String onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();
        
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        this.muleMessage      = message;
        String filename       = message.getStringProperty("originalFilename", "");
        
        String auditId = AuditUtils.getAuditId(message);
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(GreeceToKidsMule onCall) OriginalPayload = \n" + payload);
        }
        
        Utils.log("(GreeceToKidsMule onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, encoding);
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        Utils.createAuditContext(muleEventContext, 
                "<GreeceToKids>" +  
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", "") +
                "</GreeceToKids>");
        
        Utils.addAuditEvent(muleEventContext, 
                "GreeceToKids: File Name", 
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", ""));

        String xml = null;
//        xml = readGreece(parser, auditId, encoding, EDirections.CustomerToCountry);
        try {
            xml = readGreece(parser, auditId, encoding, EDirections.CountryToCustomer);
        } catch (Exception e) {
            Utils.processException("(GreeceToKidsMule onCall) Exception ", e, filename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(GreeceToKidsMule onCall) Error ", e, filename, message.getCorrelationId());
        }
        
        if (Config.getLogXML()) {
            Utils.log("(GreeceToKidsMule onCall) xml = " + xml);
        }
        
        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        // MS20120207 Begin
        if (Config.isCreateInName()) {
            filename = MuleUtils.createFileName(commonFieldsDTO); 
            message.setStringProperty("filename", filename);
        }
        // MS20120207 End

        if (!commonFieldsDTO.isAlreadyReceived()) {
            MuleUtils.writeInFileMessage(message, payload, commonFieldsDTO);
        }
        return xml;
    }
    
    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }
    
    public void logAudit(KcxEnvelope kcxEnvelope, GreeceHeader header, CommonFieldsDTO commonFieldsDTO) 
                                                                                                    throws Exception {
        if (muleEventContext != null) {
            String messageName     = header.getMesTypMES20();
            String messageVersion  = messageName.substring(messageName.length());
            String referenceNumber = commonFieldsDTO.getReferenceNumber();
            Utils.addAuditEvent(muleEventContext, "GreeceToKids: KCX-Header",       kcxEnvelope.toString());
            Utils.addAuditEvent(muleEventContext, "GreeceToKids: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "GreeceToKids: Message Version",  messageVersion);
            Utils.addAuditEvent(muleEventContext, "GreeceToKids: Transmitter",  	header.getMesSenMES3());
            Utils.addAuditEvent(muleEventContext, "GreeceToKids: Message ID",       header.getMesIdeMES19());
            Utils.addAuditEvent(muleEventContext, "GreeceToKids: Reference Number", referenceNumber); 
            Utils.addAuditEvent(muleEventContext, "GreeceToKids: Message State",    messageName + ": converted");
        }
    }
    
}
