/*
 * Funktion    : UnisysToKidsMule.java
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
package com.kewill.kcx.component.mapping.common.start.customer.in;

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
import com.kewill.kcx.component.mapping.common.start.UnisysToKidsConverter;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul        : UnisysToKidsMule<br>
 * Erstellt     : 14.12.2010<br>
 * Beschreibung : UnisysToKids called by MULE. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class UnisysToKidsMule extends UnisysToKidsConverter implements Callable {
    private MuleEventContext muleEventContext = null;
    private MuleMessage      muleMessage      = null;

    public String onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();
        
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        this.muleMessage      = message;
        
        String auditId = AuditUtils.getAuditId(message);
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(UnisysToKids onCall) payload = \n" + payload);
        }
        Utils.log("(UnisysToKids onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, encoding);
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        Utils.createAuditContext(muleEventContext, 
                "<UnisysToKids>" +  
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", "") +
                "</UnisysToKids>");
        
        Utils.addAuditEvent(muleEventContext, 
                "UnisysToKids: File Name", 
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", ""));

        String xml = null;
        xml = readUnisys(parser, auditId, encoding, EDirections.CustomerToCountry);
        
        if (Config.getLogXML()) {
            Utils.log("(UnisysToKids onCall) xml = " + xml);
        }
        
        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        // MS20120207 Begin
        if (Config.isCreateInName()) {
            String filename = MuleUtils.createFileName(commonFieldsDTO); 
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
    
    public void logAudit(KcxEnvelope kcxEnvelope, UnisysHeader unisysHeader, 
                         CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String messageName = unisysHeader.getMsgName();
            Utils.addAuditEvent(muleEventContext, "UnisysToKids: KCX-Header",       kcxEnvelope.toString());
            Utils.addAuditEvent(muleEventContext, "UnisysToKids: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "UnisysToKids: Message Version",  unisysHeader.getMsgVersion());
            Utils.addAuditEvent(muleEventContext, "UnisysToKids: Transmitter",  	unisysHeader.getMsgSender());
            Utils.addAuditEvent(muleEventContext, "UnisysToKids: Message ID",       unisysHeader.getMsgId());
            Utils.addAuditEvent(muleEventContext, "UnisysToKids: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "UnisysToKids: Message State",    messageName + ": converted");
        }
    }
    
}
