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
import com.kewill.kcx.component.mapping.common.start.KexToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul        : KexToKidsMule<br>
 * Erstellt     : 10.11.2011<br>
 * Beschreibung : KexToKids called by MULE. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class KexToKidsMule extends KexToKidsConverter implements Callable {
    private MuleEventContext muleEventContext = null;
    private MuleMessage      muleMessage      = null;

    public String onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();
        
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        this.muleMessage      = message;
        String fileName = message.getStringProperty("filename", "");
        Utils.log("(KexToKidsMule onCall) filename = " + fileName);
        
        String auditId = AuditUtils.getAuditId(message);
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KexToKidsMule onCall) payload = \n" + payload);
        }
        Utils.log("(KexToKidsMule onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, encoding);
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
       

        Utils.createAuditContext(muleEventContext, 
                "<KexToKidsMule>" +  
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", "") +
                "</KffPortToKids>");
        
        Utils.addAuditEvent(muleEventContext, 
                "KexToKidsMule: File Name", 
                muleMessage.getStringProperty("directory", ".") + "/" +
                muleMessage.getStringProperty("originalFilename", ""));

        String xml = null;
        xml = readKex(parser, auditId, encoding, EDirections.CustomerToCountry);
        
        if (Config.getLogXML()) {
            Utils.log("(KexToKidsMule onCall) xml = " + xml);
        }
        
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
    
    @Override
    public void logAudit(KcxEnvelope kcxEnvelope, KidsHeader kexHeader, 
    		CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            //String messageName = kexHeader.getMsgName();
            String messageName = "Job";
            Utils.addAuditEvent(muleEventContext, "KexToKids: KCX-Header",       kcxEnvelope.toString());
            Utils.addAuditEvent(muleEventContext, "KexToKids: Message Name",     messageName);
/*            Utils.addAuditEvent(muleEventContext, "KexToKids: Message Version",  kexHeader.get());
            Utils.addAuditEvent(muleEventContext, "KexToKids: Transmitter",  	kexHeader.getSenderUserId());
            Utils.addAuditEvent(muleEventContext, "KexToKids: Message ID",       kexHeader.getBatchNo());
         Utils.addAuditEvent(muleEventContext, "KexToKids: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KexToKids: Message State",    messageName + ": converted");*/
        }
    }

	
}
