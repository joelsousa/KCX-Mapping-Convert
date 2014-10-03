package com.kewill.kcx.component.mapping.common.start.customs.in;

/*
 * Function    : FssToKidsule.java
 * Titel       :
 * Date        : 04.03.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert ZABIS-Fss messages in KIDS-Format
 * 				 evaluates the version and the procedure to start the
 * 				 mapping classes
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tools.ant.filters.StringInputStream;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.FssToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul : FssToKidsMule<br>
 * Erstellt : 04.03.2009<br>
 * Beschreibung : transformer called by Mule to convert ZABIS-FSS format to KIDS
 * format.
 * 
 * @author kron
 * @version 1.0.00
 */
public class FssToKidsMule extends FssToKidsConverter implements Callable {
    private MuleEventContext muleEventContext = null;
    private MuleMessage      muleMessage      = null;

    public String onCall(MuleEventContext muleEventContext) throws Exception {
// logger = Logger.getLogger("kcx");
        Config.configure("conf", "kcx.ini");
// reconfigureLog();
// Config.showConfiguration();
        getConfiguration();

        MuleMessage message = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        this.muleMessage = message;

// String auditId = message.getStringProperty(AuditUtils.AUDIT_ID, null);
        String auditId = AuditUtils.getAuditId(message);
        String payload = message.getPayloadAsString();
        
        // CK090430 Dateinamen extrahieren und - ohne ".xml" in
        // 			die MessageID im KIDS-Header einsetzen
        String fssFilename = muleMessage.getStringProperty("filename", "");
        String msgID = "";
        
        if (fssFilename.lastIndexOf(".xml") > 0) {
        	msgID = fssFilename.substring(0, fssFilename.lastIndexOf(".xml"));	
        } else {
        	msgID = fssFilename;
        }
        Utils.log("(FssToKids onCall) msgID = \n" + msgID);
        
        if (Config.getLogXML()) {
            Utils.log("(FssToKids onCall) payload = \n" + payload);
        }
        Utils.log("(FssToKids onCall) message.getEncoding() = " + message.getEncoding());
        InputStream ins = new StringInputStream(payload);
// InputStreamReader is = new InputStreamReader(ins, message.getEncoding());
//        InputStreamReader is = new InputStreamReader(ins);
        InputStreamReader is = new InputStreamReader(ins);
        BufferedReader reader = new BufferedReader(is);

//        AuditUtils.dispatchCreateAuditPayload(muleEventContext, 
//                "<FssToKids>" +  
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", "") +
//                "</FssToKids>");
//
//        AuditUtils.dispatchAuditEventPayload(muleEventContext, 
//                "FssToKids: File Name", 
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", ""));
        
        Utils.createAuditContext(muleEventContext, 
                "<FssToKids>" +  
                message.getStringProperty("directory", ".") + "/" +
                // message.getStringProperty("originalFilename", "") +
                fssFilename + 
                "</FssToKids>");

        Utils.addAuditEvent(muleEventContext, 
                "FssToKids: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                // message.getStringProperty("originalFilename", ""));
                fssFilename);
        
//        String xml = readFss(reader, auditId, msgID);
        // CK2011-08-04
        // String xml = readFss(reader, auditId, msgID, EDirections.CountryToCustomer);
        String xml = null;
        try {
            xml = readFss(reader, auditId, msgID, EDirections.CountryToCustomer);
        } catch (Exception e) {
            Utils.processException("(FssToKidsMule onCall) Exception ", e, fssFilename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(FssToKidsMule onCall) Error ", e, fssFilename, message.getCorrelationId());
        }
        if (Config.getLogXML()) {
            Utils.log("(FssToKids onCall) xml = " + xml);
        }
        
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        message.setBooleanProperty("IS_FUNCTIONAL_ACKNOWLEDGEMENT", commonFieldsDTO.isFunctionalAcknowledgement());
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
    
    public void logAudit(TsVOR tsVor, KcxEnvelope kcxEnvelope, 
            CommonFieldsDTO commonFieldsDTO) throws FssException {
        try {
            if (muleEventContext != null) {
                Utils.addAuditEvent(muleEventContext, "FssToKids: KCX-Header",       kcxEnvelope.toString());
                Utils.addAuditEvent(muleEventContext, "FssToKids: Message Type",     tsVor.getNatyp());
                Utils.addAuditEvent(muleEventContext, "FssToKids: Message Version",  tsVor.getVersnr());
                Utils.addAuditEvent(muleEventContext, "FssToKids: Message ID",       tsVor.getMsgid());
                Utils.addAuditEvent(muleEventContext, "FssToKids: Reference Number", 
                                                                                commonFieldsDTO.getReferenceNumber());
                Utils.addAuditEvent(muleEventContext, "FssToKids: Message State",    tsVor.getNatyp() + ": converted");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FssException(e.getMessage());
        }
        
    }



}
