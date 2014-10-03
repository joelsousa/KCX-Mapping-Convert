package com.kewill.kcx.component.mapping.common.start.customs.out;

/*
 * Function    : KidsToMaltaMule.java
 * Titel       :
 * Date        : 16.08.2013
 * Author      : krzoska
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to Malta messages
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
import com.kewill.kcx.component.mapping.common.start.KidsToMaltaConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;


/**
 * Module       : KidsToMaltaMule<br>
 * Created 		: 16.08.2013<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to Malta messages.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToMaltaMule extends KidsToMaltaConverter implements Callable {
    private MuleEventContext muleEventContext 	= null;

    
	public Object onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToMaltaMule onCall) payload = \n" + payload);
        }

        Utils.log("(KidsToMaltaMule onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        String filename = message.getStringProperty("originalFilename", "");
        
//        AuditUtils.dispatchCreateAuditPayload(muleEventContext, 
//                "<KidsToMaltaMule>" +  
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", "") +
//                "</KidsToMaltaMule>");
        
        Utils.createAuditContext(muleEventContext, 
                "<KidsToMaltaMule>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToMaltaMule>");

        
//        AuditUtils.dispatchAuditEventPayload(muleEventContext, 
//                "KidsToMaltaMule: File Name", 
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", ""));
        
        Utils.addAuditEvent(muleEventContext, "KidsToMaltaMule: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));
        
        
        
//        Object xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        Object xml = null;
        try {
            xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToMaltaMule onCall) Exception ", e, filename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(KidsToMaltaMule onCall) Error ", e, filename, message.getCorrelationId());
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToMaltaMule onCall) xml = \n" + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        

        if (commonFieldsDTO != null && commonFieldsDTO.getTargetMessageType() != null) {  //EI20120123
        	message.setStringProperty("MessageType", commonFieldsDTO.getTargetMessageType().trim());
        	message.setStringProperty("EORINumber", commonFieldsDTO.getLocalId().trim());
        }
        if (commonFieldsDTO != null && commonFieldsDTO.getLocalId() != null) {    //EI20120123
        	message.setStringProperty("TraderID", commonFieldsDTO.getLocalId().trim());
        }
        
               
        Utils.log("(KidsToMaltaMuleExtern convert) createOutName = " + Config.isCreateOutName());
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
            Utils.addAuditEvent(muleEventContext, "KidsToMaltaMule: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "KidsToMaltaMule: Message Version",  kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToMaltaMule: Message ID",       kidsHeader.getMessageID());
            Utils.addAuditEvent(muleEventContext, "KidsToMaltaMule: Reference Number", 
                                                                                 commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToMaltaMule: Message State",    messageName + ": converted");
        }
    }

    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

}
