package com.kewill.kcx.component.mapping.common.start.customs.out;

/*
 * Function    : KidsToGreeceMule.java
 * Titel       :
 * Date        : 18.07.2011
 * Author      : Kewill CSF / schmidt
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to Greece messages
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

import java.sql.Timestamp;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToGreeceConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;


/**
 * Module       : KidsToGreeceMule<br>
 * Created 		: 18.07.2011<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to Greece messages.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToGreeceMule extends KidsToGreeceConverter implements Callable {
    private MuleEventContext muleEventContext 	= null;

    
	public Object onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToGreeceMule onCall) payload = \n" + payload);
        }

        Utils.log("(KidsToGreeceMule onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        String filename = message.getStringProperty("originalFilename", "");
        
//        AuditUtils.dispatchCreateAuditPayload(muleEventContext, 
//                "<KidsToGreeceMule>" +  
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", "") +
//                "</KidsToGreeceMule>");
        
        Utils.createAuditContext(muleEventContext, 
                "<KidsToGreeceMule>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToGreeceMule>");

        
//        AuditUtils.dispatchAuditEventPayload(muleEventContext, 
//                "KidsToGreeceMule: File Name", 
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", ""));
        
        Utils.addAuditEvent(muleEventContext, "KidsToGreeceMule: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));
        
        
        
//        Object xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        Object xml = null;
        try {
            xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToGreeceMule onCall) Exception ", e, filename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(KidsToGreeceMule onCall) Error ", e, filename, message.getCorrelationId());
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToGreeceMule onCall) xml = \n" + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        // MS20110627
        if (commonFieldsDTO != null && commonFieldsDTO.getTargetMessageType() != null) {  //EI20120123
        	message.setStringProperty("MessageType", commonFieldsDTO.getTargetMessageType().trim());
        }
        if (commonFieldsDTO != null && commonFieldsDTO.getLocalId() != null) {    //EI20120123
        	message.setStringProperty("TraderID", commonFieldsDTO.getLocalId().trim());
        }
        
               
        Utils.log("(KidsToGreeceMuleExtern convert) createOutName = " + Config.isCreateOutName());
        if (Config.isCreateOutName()) {
            try {
                String toCountry     = commonFieldsDTO.getCountryCode().toUpperCase();
                String kcxId         = commonFieldsDTO.getKcxId().toUpperCase();
                String[] tokens      = kcxId.split("[.]");
                String fromCountry   = tokens[0];
                String company       = tokens[1];
                String branch        = "";
                if (tokens.length > 2) {
                    branch = tokens[2];
                }
                String timestamp     = Utils.timestampToString(new Timestamp(System.currentTimeMillis()));
                long lastnum = Db.getRunningNumberForKcxId(kcxId);
                String runningNumber = Utils.nFormat(lastnum, 9);
                String extension     = ".xml";
                if (commonFieldsDTO.isPdfTgz()) {
                    extension     = ".pdf";
                }
                
                String newName = fromCountry + "01" + toCountry + "01_" + company + "_" + branch + "_" + 
                                                                                 timestamp + runningNumber + extension;
                message.setStringProperty("filename", newName);   
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);

        return xml;
    }
	
	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String messageName = kidsHeader.getMessageName();
            Utils.addAuditEvent(muleEventContext, "KidsToGreeceMule: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "KidsToGreeceMule: Message Version",  kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToGreeceMule: Message ID",       kidsHeader.getMessageID());
            Utils.addAuditEvent(muleEventContext, "KidsToGreeceMule: Reference Number", 
                                                                                 commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToGreeceMule: Message State",    messageName + ": converted");
        }
    }

    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

}
