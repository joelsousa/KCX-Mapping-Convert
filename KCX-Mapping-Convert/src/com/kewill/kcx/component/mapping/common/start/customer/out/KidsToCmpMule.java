package com.kewill.kcx.component.mapping.common.start.customer.out;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToCmpConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;



/**
 * Module       : KidsToCMP<br>
 * Created 		: 18.06.2013<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to CMP messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToCmpMule extends KidsToCmpConverter implements Callable {
    private MuleEventContext muleEventContext 	= null;

	public String onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToCMP onCall) payload = \n" + payload);
        }

        Utils.log("(KidsToCmp onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        String filename = message.getStringProperty("originalFilename", "");

        //AK20120330
        if (Utils.isStringEmpty(filename)) {
        	filename = commonFieldsDTO.getFilename();				
        }

        Utils.createAuditContext(muleEventContext, 
                "<KidsToCmp>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToCmp>");

        Utils.addAuditEvent(muleEventContext, "KidsToPort: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));

        
        
        String xml = readKids(payload, encoding, filename, EDirections.CountryToCustomer);
        if (Config.getLogXML()) {
            Utils.log("(KidsToCmp onCall) xml = \n" + xml);
        }

        if (commonFieldsDTO != null && !Utils.isStringEmpty(commonFieldsDTO.getMessageReferenceNumber())) {
        	message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim()); 
        } else {
        	message.setStringProperty("MessageReferenceNumber", "");
        }
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        if (!filename.endsWith(".xml") && !filename.endsWith(".tgz")) { 		//AK20120330

			String newFilename = filename.trim() + ".xml";
			Utils.log("(KidsToCmpMule onCall) .xml an Dateinamen angehängt = " + newFilename);

			message.setStringProperty("filename", newFilename);
		}		              
        
        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
                
        return xml;
    }
	
	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (muleEventContext != null) {
            String messageName = kidsHeader.getMessageName();
            Utils.addAuditEvent(muleEventContext, "KidsToCmp: Message Name",     messageName);
            Utils.addAuditEvent(muleEventContext, "KidsToCmp: Message Version",  kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToCmp: Message ID",       kidsHeader.getMessageID());
            Utils.addAuditEvent(muleEventContext, "KidsToCmp: Reference Number", commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToCmp: Message State",    messageName + ": converted");
        }
    }

    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }
    
    
    
}
