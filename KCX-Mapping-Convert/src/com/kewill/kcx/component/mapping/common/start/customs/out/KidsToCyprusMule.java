package com.kewill.kcx.component.mapping.common.start.customs.out;

/*
 * Function    : KidsToCyprusMule.java
 * Titel       :
 * Date        : 02.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to UIDS messages
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Sven Heise
 * Date        : 08.09.2008
 * Label       : 
 * Description : InternalStatusInformation, Cancellation, Completion (25.09.08)
 *
 */

import java.sql.Timestamp;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToCyprusConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;


/**
 * Module       : KidsToCyprusMule<br>
 * Created 		: 08.06.2011<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to Cyprus messages.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToCyprusMule extends KidsToCyprusConverter implements Callable {
    private MuleEventContext muleEventContext 	= null;

    // Christine Kron: Muss hier definiert sein wg. filename-Aenderung bei tgz-Dateien
    // auskommentiert, da es "null" war nach readKids und commonFieldsDTO.getMessageReferenceNumber u.a.
    // nicht funktioniert haben
    // CK 10.12.2010
    // private CommonFieldsDTO commonFieldsDTO		= null;

    
	public Object onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToCyprusMule onCall) payload = \n" + payload);
        }

        Utils.log("(KidsToCyprusMule onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        String filename = message.getStringProperty("originalFilename", "");
        
//        AuditUtils.dispatchCreateAuditPayload(muleEventContext, 
//                "<KidsToCyprusMule>" +  
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", "") +
//                "</KidsToCyprusMule>");
        
        Utils.createAuditContext(muleEventContext, 
                "<KidsToCyprusMule>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToCyprusMule>");

        
//        AuditUtils.dispatchAuditEventPayload(muleEventContext, 
//                "KidsToCyprusMule: File Name", 
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", ""));
        
        Utils.addAuditEvent(muleEventContext, "KidsToCyprusMule: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));
        
        
        
//        Object xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        Object xml = null;
        try {
            xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToCyprusMule onCall) Exception ", e, filename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(KidsToCyprusMule onCall) Error ", e, filename, message.getCorrelationId());
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToCyprusMule onCall) xml = \n" + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        // MS20110627
        if (xml != null) {
            message.setStringProperty("MessageType", commonFieldsDTO.getTargetMessageType().trim());
            message.setStringProperty("EORINumber", commonFieldsDTO.getLocalId().trim());
        }
               
        Utils.log("(KidsToCyprusMuleExtern convert) createOutName = " + Config.isCreateOutName());
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
        } else {
            /***********************************************************************
             * TODO PL verarbeitet nur Dateinamen mit Endung .xml Bei CountryCode PL
             * wird .xml angehängt (Sollte nur eine Temporäre Lösung sein)
             **********************************************************************/
            if (!filename.endsWith(".xml") && 
                (commonFieldsDTO.getCountryCode().trim().equals("PL") || 
                 commonFieldsDTO.getKcxId().substring(0, 2).equals("PL"))) { //EI110520:CountryCode=DE,kcx_id=PL.SAPO...
                message.setStringProperty("filename", commonFieldsDTO.getFilename() + ".xml");
                Utils.log("(KidsToCyprusMule readKids) .xml an Dateinamen angehängt (for PL) = "
                                + commonFieldsDTO.getFilename() + ".xml");
            }
            // ******************************************************
            
            if (commonFieldsDTO.isPdfTgz()) {
                message.setStringProperty("filename", commonFieldsDTO.getFilename());   
            } 
        }

        if (xml != null) {
            MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
        }

        return xml;
    }
	
	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String messageName = kidsHeader.getMessageName();
            Utils.addAuditEvent(muleEventContext, "KidsToCyprusMule: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "KidsToCyprusMule: Message Version",  kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToCyprusMule: Message ID",       kidsHeader.getMessageID());
            Utils.addAuditEvent(muleEventContext, "KidsToCyprusMule: Reference Number", 
                                                                                commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToCyprusMule: Message State",    messageName + ": converted");
        }
    }

    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

}
