/*
 * Funktion    : FedexToKidsMule.java
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
import com.kewill.kcx.component.mapping.common.start.FedexToKidsConverter;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul        : FedexToKidsMule<br>
 * Erstellt     : 28.08.2009<br>
 * Beschreibung : FedexToKids called by MULE. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class FedexToKidsMule extends FedexToKidsConverter implements Callable {
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
        //if (Config.getLogXML()) {
            Utils.log("(FedexToKids onCall) FedexOriginalPayload = \n" + payload);
        //}
        
        Utils.log("(FedexToKids onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, encoding);
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        Utils.createAuditContext(muleEventContext, 
                "<FedexToKids>" +  
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", "") +
                "</FedexToKids>");
        
        Utils.addAuditEvent(muleEventContext, 
                "FedexToKids: File Name", 
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", ""));

        String xml = null;
        xml = readFedex(parser, auditId, encoding, EDirections.CustomerToCountry);
        
        if (Config.getLogXML()) {
            Utils.log("(FedexToKids onCall) xml = " + xml);
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
    
    public void logAudit(KcxEnvelope kcxEnvelope, FedexHeader fedexHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String messageName = fedexHeader.getSchemaID();
            Utils.addAuditEvent(muleEventContext, "FedexToKids: KCX-Header",       kcxEnvelope.toString());
            Utils.addAuditEvent(muleEventContext, "FedexToKids: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "FedexToKids: Message Version",  fedexHeader.getSchemaVersion());
            Utils.addAuditEvent(muleEventContext, "FedexToKids: Transmitter",  	   fedexHeader.getPartyId());
            Utils.addAuditEvent(muleEventContext, "FedexToKids: Message ID",       fedexHeader.getTransactionId());
            Utils.addAuditEvent(muleEventContext, "FedexToKids: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "FedexToKids: Message State",    messageName + ": converted");
        }
    }
    
}
