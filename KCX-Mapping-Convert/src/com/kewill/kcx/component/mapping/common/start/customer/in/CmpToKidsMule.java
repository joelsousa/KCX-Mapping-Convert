package com.kewill.kcx.component.mapping.common.start.customer.in;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.StringInputStream;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.CMPToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul : KidsToKids<br>
 * Erstellt : 14.06.2013<br>
 * Beschreibung : Transformer called by Mule to convert KIDS-Format to KIDS
 * messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CmpToKidsMule extends CMPToKidsConverter implements Callable {
	private static Logger logger = null; // Logger
	private MuleEventContext muleEventContext = null;	
		 
	public Object onCall(MuleEventContext muleEventContext) throws Exception {
		logger = Logger.getLogger("kcx");
		Config.configure("conf", "kcx.ini");
		getConfiguration();

		muleEventContext.transformMessageToString();
		MuleMessage message = muleEventContext.getMessage();
		this.muleEventContext = muleEventContext;

		String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(CmpToKids onCall) payloadString = \n" + payload);
        }
		Utils.log("(CmpToKids onCall) message.getEncoding() = "
				+ message.getEncoding());
		String encoding = message.getEncoding();
		String auditId = AuditUtils.getAuditId(message);

		InputStream ins = new StringInputStream(payload);		
		InputStreamReader is = new InputStreamReader(ins, encoding);
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader parser = factory.createXMLEventReader(is);

        Utils.createAuditContext(muleEventContext, 
                "<CmpToKids>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</CmpToKids>");

        Utils.addAuditEvent(muleEventContext, 
                "CmpToKids: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));
        
		String xml = readCmp(parser, payload, auditId, EDirections.CustomerToCountry);
        if (Config.getLogXML()) {
            Utils.log("(CmpToKids onCall) xml = " + xml);
        }

        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());        
		message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);

        if (Config.isCreateInName()) {
            String filename = MuleUtils.createFileName(commonFieldsDTO); 
            message.setStringProperty("filename", filename);
        }       
		MuleUtils.writeInFileMessage(message, payload, commonFieldsDTO);
		return xml;
	}

	private static void getConfiguration() {
		logger.debug("Konfiguration wird geholt.");
		Utils.setDebug(Config.getDebug());
		Utils.setLogLevel(Config.getLogLevel());
	}
	
	
    public void logAudit(KcxEnvelope kcxEnvelope, KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) 
                                                                                        throws Exception {
        if (muleEventContext != null) {
            String msg = kidsHeader.getMessageName();
            Utils.addAuditEvent(muleEventContext, "KidsToKidsConverter: KCX-Header",       kcxEnvelope.toString());
            Utils.addAuditEvent(muleEventContext, "KidsToKidsConverter: Message Name",     kidsHeader.getMessageName());
            Utils.addAuditEvent(muleEventContext, "KidsToKidsConverter: Message Version",  kidsHeader.getRelease());
            Utils.addAuditEvent(muleEventContext, "KidsToKidsConverter: Message ID",       kidsHeader.getMessageID());
//          Utils.addAuditEvent(muleEventContext, "KidsToKidsConverter: Reference Number", 
//                                                                    commonFieldsDTO.getReferenceNumber());
            Utils.addAuditEvent(muleEventContext, "KidsToKidsConverter: Message State",    msg + ": converted");
        }
    }	  
}
