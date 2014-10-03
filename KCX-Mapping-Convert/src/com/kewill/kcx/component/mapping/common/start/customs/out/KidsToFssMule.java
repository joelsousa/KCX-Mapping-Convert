package com.kewill.kcx.component.mapping.common.start.customs.out;
/*
 * Function    : KidsToFssonverter.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to ZABIS Fss
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

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul		: KidsToFssConverter<br>
 * Erstellt		: 05.09.2008<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to ZABIS Fss.
 * 
 * @author kron
 * @version 1.0.00
 */
public class KidsToFssMule extends KidsToFssConverter implements Callable {
    private static Logger logger            = null;         // Logger
    private MuleEventContext muleEventContext = null;
    
	public String onCall(MuleEventContext muleEventContext) throws Exception {
        logger = Logger.getLogger("kcx");
        Config.configure("conf", "kcx.ini");
        getConfiguration();
        
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        
		String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToFss onCall) payload = \n" + payload);
        }
		Utils.log("(KidsToFss onCall) message.getEncoding() = " + message.getEncoding());
		
//        AuditUtils.dispatchCreateAuditPayload(muleEventContext, 
//                "<KidsToFss>" +  
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", "") +
//                "</KidsToFss>");
//
//        AuditUtils.dispatchAuditEventPayload(muleEventContext, 
//                "KidsToFss: File Name", 
//                message.getStringProperty("directory", ".") + "/" +
//                message.getStringProperty("originalFilename", ""));

        Utils.createAuditContext(muleEventContext, 
                "<KidsToFss>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToFss>");

        Utils.addAuditEvent(muleEventContext, 
                "KidsToFss: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));

        // CK20110803
        // String xml = readKids(payload, message.getEncoding(), EDirections.CustomerToCountry);
        String xml = null;
        try {
            xml = readKids(payload, message.getEncoding(), EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToFssMule onCall) Exception ", 
            						e, commonFieldsDTO.getFilename(), message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(KidsToFssMule onCall) Error ", 
            						e, commonFieldsDTO.getFilename(), message.getCorrelationId());
        }
        
        if (Config.getLogXML()) {
            Utils.log("(KidsToFss onCall) xml = " + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
        
      
        
        // MS20140219 Begin      
        if (Config.isCreateOutName()) {
            String fileName = MuleUtils.getShortFileName(message, commonFieldsDTO);
          //EI20140224 anfang
            Utils.log("(KidsToFssMule IWA) ShortFileName = " + fileName);
            if (commonFieldsDTO.getTargetMessageType() != null && commonFieldsDTO.getTargetMessageType().equals("CSN")) {             	
            	Utils.log("IWA-CSN TargetMessageType = " + commonFieldsDTO.getTargetMessageType());
            	fileName = commonFieldsDTO.getMessageReferenceNumber().trim();
            	 Utils.log("(KidsToFssMule IWA) CSN-FileName = " + fileName);
            }           
          //EI20140224 ende           
            message.setStringProperty("filename", fileName);                     
        }       
        // MS20140219 End

        return xml;
	}

	
    private static void getConfiguration() {
        logger.debug("Konfiguration wird geholt.");
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String msg = kidsHeader.getMessageName();
            Utils.addAuditEvent(muleEventContext, "KidsToFss: Message Name",      kidsHeader.getMessageName());
            Utils.addAuditEvent(muleEventContext, "KidsToFss: Message Version",   kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToFss: Message ID",        kidsHeader.getMessageID());
            Utils.addAuditEvent(muleEventContext, "KidsToFss: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToFss: Message State",     msg + ": converted");
        }
    }
    
}
