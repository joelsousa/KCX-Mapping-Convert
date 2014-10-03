package com.kewill.kcx.component.mapping.common.start.customs.out;
/*
 * Function    : KidsToBdecMule.java
 * Titel       :
 * Date        : 03.11.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to Bell Davies ECustoms 
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
import com.kewill.kcx.component.mapping.common.start.KidsToBdecConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul		: KidsToBdecMule<br>
 * Erstellt		: 03.11.2009<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to Bell Davies ECustoms. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsToBdecMule extends KidsToBdecConverter implements Callable {
    private static Logger logger            = null;         
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
            Utils.log("(KidsToBdec onCall) payload = \n" + payload);
        }
        
        String encoding = message.getEncoding();
        String filename = message.getStringProperty("originalFilename", "");
		
        Utils.createAuditContext(muleEventContext, 
                "<KidsToBdec>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToBdec>");

        Utils.addAuditEvent(muleEventContext, 
                "KidsToBdec: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));

        // CK20110803
        // String xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        String xml = null;
        try {
        	xml = readKids(payload, encoding, filename, EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToBdecMule onCall) Exception ", e, payload, commonFieldsDTO.getFilename(), message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(KidsToBdecMule onCall) Error ", e, payload, commonFieldsDTO.getFilename(), message.getCorrelationId());
        }   
        
        if (Config.getLogXML()) {
            Utils.log("(KidsToBdec onCall) xml = " + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        message.setStringProperty("action", commonFieldsDTO.getActionType().trim());    // MS20110714
        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
        
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
            Utils.addAuditEvent(muleEventContext, "KidsToBdec: Message Name",      kidsHeader.getMessageName());
            Utils.addAuditEvent(muleEventContext, "KidsToBdec: Message Version",   kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToBdec: Message ID",        kidsHeader.getMessageID());
            Utils.addAuditEvent(muleEventContext, "KidsToBdec: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToBdec: Message State",     msg + ": converted");
        }
    }
    
}
