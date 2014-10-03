/*
 * Funktion    : IrelandToKidsMule.java
 * Titel       :
 * Erstellt    : 28.08.2009
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
import com.kewill.kcx.component.mapping.common.start.IrelandToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.ie.IrelandHeader;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul        : IrelandToKidsMule<br>
 * Erstellt     : 17.06.2014<br>
 * Beschreibung : IrelandToKids called by MULE. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class IrelandToKidsMule extends IrelandToKidsConverter implements Callable {
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
            Utils.log("(IrelandToKids onCall) OriginalPayload = \n" + payload);
        }
        
        Utils.log("(IrelandToKids onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, encoding);
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        Utils.createAuditContext(muleEventContext, 
                "<IrelandToKids>" +  
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", "") +
                "</IrelandToKids>");
        
        Utils.addAuditEvent(muleEventContext, 
                "IrelandToKids: File Name", 
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", ""));

        String xml = null;

        try {
            xml = readIreland(parser, auditId, encoding, EDirections.CountryToCustomer);
        } catch (Exception e) {
            Utils.processException("(IrelandToKids onCall) Exception ", e, filename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(IrelandToKids onCall) Error ", e, filename, message.getCorrelationId());
        }
        if (Config.getLogXML()) {
            Utils.log("(IrelandToKids onCall) xml = \n" + xml);
        }
        
        if (Config.getLogXML()) {
            Utils.log("(IrelandToKids onCall) xml = " + xml);
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

        MuleUtils.writeInFileMessage(message, payload, commonFieldsDTO);
        return xml;
    }
    
    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }
    
    public void logAudit(KcxEnvelope kcxEnvelope, IrelandHeader header, CommonFieldsDTO commonFieldsDTO) 
                                                                                                    throws Exception {
        if (muleEventContext != null) {
            String messageName     = header.getMessageName();
            String messageVersion  = messageName.substring(messageName.length());
            String referenceNumber = commonFieldsDTO.getReferenceNumber();
            Utils.addAuditEvent(muleEventContext, "IrelandToKids: KCX-Header",       kcxEnvelope.toString());
            Utils.addAuditEvent(muleEventContext, "IrelandToKids: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "IrelandToKids: Message Version",  messageVersion);
            Utils.addAuditEvent(muleEventContext, "IrelandToKids: Transmitter",  	 "todo");//header.getMesSenMES3());
            Utils.addAuditEvent(muleEventContext, "IrelandToKids: Message ID",       "todo");//header.getMesIdeMES19());
            Utils.addAuditEvent(muleEventContext, "IrelandToKids: Reference Number", referenceNumber); 
            Utils.addAuditEvent(muleEventContext, "IrelandToKids: Message State",    messageName + ": converted");
        }
    }
    
}
