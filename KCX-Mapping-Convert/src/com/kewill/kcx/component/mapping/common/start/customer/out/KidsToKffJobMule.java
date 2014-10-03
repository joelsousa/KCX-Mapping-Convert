package com.kewill.kcx.component.mapping.common.start.customer.out;

/*
 * Function    : KidsToPort.java
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

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToKffJobConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;



/**
 * Module       : KidsToPort<br>
 * Created 		: 09.11.2011<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to JOB PORT/Import messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToKffJobMule extends KidsToKffJobConverter implements Callable {
    private MuleEventContext muleEventContext 	= null;

	public String onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToPort onCall) payload = \n" + payload);
        }

        Utils.log("(KidsToKffJob onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        String filename = message.getStringProperty("originalFilename", "");

        //AK20120330
        if (Utils.isStringEmpty(filename)) {
        	filename = commonFieldsDTO.getFilename();				
        }

        Utils.createAuditContext(muleEventContext, 
                "<KidsToKffJob>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToKffJob>");

        Utils.addAuditEvent(muleEventContext, "KidsToPort: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));

        
        
        String xml = readKids(payload, encoding, filename, EDirections.CountryToCustomer);
        if (Config.getLogXML()) {
            Utils.log("(KidsToKffJob onCall) xml = \n" + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        if (!filename.endsWith(".xml") && !filename.endsWith(".tgz")) { 		//AK20120330

			String newFilename = filename.trim() + ".xml";
			Utils.log("(KidsToKffJobMule onCall) .xml an Dateinamen angehängt = " + newFilename);

			message.setStringProperty("filename", newFilename);
		}		
        
        /********
        if (commonFieldsDTO.isPdfTgz()) {
        	message.setStringProperty("filename", commonFieldsDTO.getFilename());	
        } 
        ******/
        
        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
                
        return xml;
    }
	
	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String messageName = kidsHeader.getMessageName();
            Utils.addAuditEvent(muleEventContext, "KidsToKffJob: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "KidsToKffJob: Message Version",  kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToKffJob: Message ID",       kidsHeader.getMessageID());
            Utils.addAuditEvent(muleEventContext, "KidsToKffJob: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToKffJob: Message State",    messageName + ": converted");
        }
    }

    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }
    
    
    
}
