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
import com.kewill.kcx.component.mapping.common.start.UidsToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul        : FranceToKidsMule<br>
 * Erstellt     : 28.11.2013<br>
 * Beschreibung : FranceToKids called by MULE. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 * 
 * 	TODO: in Abhaengigkeit von Format wird nach wie vor UidsToKidsMule oder der neue xxxToKidsMule aufgerufen.
 * 
 */
public class FranceToKidsMule extends UidsToKidsConverter implements Callable {
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
            Utils.log("(FranceToKids onCall) payload = \n" + payload);
        }
        Utils.log("(FranceToKids onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, encoding);
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        Utils.createAuditContext(muleEventContext, 
                "<FranceToKids>" +  
                muleMessage.getStringProperty("directory", ".") + "/" +
                // muleMessage.getStringProperty("originalFilename", "") +
                filename +
                "</FranceToKids>");
        
        Utils.addAuditEvent(muleEventContext, 
                "FranceToKids: File Name", 
                muleMessage.getStringProperty("directory", ".") + "/" +
                // muleMessage.getStringProperty("originalFilename", ""));
                filename);

        String xml = null;
        // CK20110804
        // xml = readUids(parser, auditId, encoding, EDirections.CountryToCustomer);
        try {
            xml = readUids(parser, auditId, encoding, EDirections.CountryToCustomer);
        } catch (Exception e) {
            Utils.processException("(FranceToKidsMule onCall) Exception ", e, filename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(FranceToKidsMule onCall) Error ", e, filename, message.getCorrelationId());
        }
        
        if (Config.getLogXML()) {
            Utils.log("(FranceToKids onCall) xml = " + xml);
        }
        
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        message.setBooleanProperty("IS_FUNCTIONAL_ACKNOWLEDGEMENT", commonFieldsDTO.isFunctionalAcknowledgement());
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
    
    public void logAudit(KcxEnvelope kcxEnvelope, UidsHeader uidsHeader, 
                         CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String messageName = uidsHeader.getMessageName();
            Utils.addAuditEvent(muleEventContext, "FranceToKids: KCX-Header",       kcxEnvelope.toString());
            Utils.addAuditEvent(muleEventContext, "FranceToKids: Message Type",     uidsHeader.getMessageType());
            Utils.addAuditEvent(muleEventContext, "FranceToKids: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "FranceToKids: Message Version",  uidsHeader.getMessageVersion());
            Utils.addAuditEvent(muleEventContext, "FranceToKids: Message ID",       uidsHeader.getMsgid());
            Utils.addAuditEvent(muleEventContext, "FranceToKids: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "FranceToKids: Message State",    messageName + ": converted");
        }
    }
    
    
}
