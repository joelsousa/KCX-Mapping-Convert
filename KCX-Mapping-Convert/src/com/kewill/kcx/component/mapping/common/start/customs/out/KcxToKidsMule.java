/*
 * Funktion    : KcxToKidsMule.java
 * Titel       :
 * Erstellt    : 11.12.2008
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.common.start.customs.out;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.KcxToKidsConverter;
import com.kewill.kcx.component.mapping.common.start.ReplaceKcxId;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul		: KcxToKidsMule<br>
 * Erstellt		: 11.12.2008<br>
 * Beschreibung	: Removes the KCX-Envelope from a KIDS message. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KcxToKidsMule extends KcxToKidsConverter implements Callable {
    private static Logger logger            = null;         // Logger

    
    public String onCall(MuleEventContext muleEventContext) throws Exception {
        logger = Logger.getLogger("kcx");
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KcxToKidsMule onCall) payload = \n" + payload);
        }
        Utils.log("(KcxToKidsMule onCall) message.getEncoding() = " + message.getEncoding());
        
        Utils.createAuditContext(muleEventContext, 
                "<KcxToKidsMule>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KcxToKidsMule>");

        Utils.addAuditEvent(muleEventContext, 
                "KcxToKidsMule: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));

        // CK20110803
        // String xml = removeKcxEnvelope(payload, message.getEncoding(), EDirections.CustomerToCountry);
        String xml = null;
        try {
        	xml = removeKcxEnvelope(payload, message.getEncoding(), EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KcxToKidsMule onCall) Exception ", e, 
            		message.getStringProperty("originalFilename", ""), message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(KcxToKidsMule onCall) Error ", e, 
            		message.getStringProperty("originalFilename", ""), message.getCorrelationId());
        }
        if (Config.getLogXML()) {
            Utils.log("(KcxToKidsMule onCall) xml = \n" + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        if (muleEventContext != null) {
//            AuditUtils.dispatchAuditEventPayload(muleEventContext, "KcxToKidsMule: Message State", "converted");
            Utils.addAuditEvent(muleEventContext, "KcxToKidsMule: Message State", "converted");
        }
        
        // BEGIN CK27022012 replace kcx-id by localID 
        
        // MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
        // return xml;
        
        // MS20140219 Begin
        if (Config.isCreateOutName()) {
            String fileName = MuleUtils.getShortFileName(message, commonFieldsDTO);
            message.setStringProperty("filename", fileName);
        }       
        // MS20140219 End

        String encoding = message.getEncoding();
		KidsHeader kidsHeader = getKidsHeader(xml, encoding);
        String kcxid = kidsHeader.getReceiver();
        String localId = Utils.getCustomerIdFromKewill(kcxid, "KIDS", kidsHeader.getCountryCode()).trim();

        // MS20140219 Begin
        // Wird schon unten im else-Zweig abgehandelt.
        // Datei würde zwei Mal geschrieben. 
//        if (localId == null) {
//            MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
//            return xml;
//        }
        // MS20140219 End
        
        if (!localId.equalsIgnoreCase(kcxid)) {
            String newxml     = new ReplaceKcxId().replaceKcxId(xml, localId);
            MuleUtils.writeFileMessage(message, newxml, commonFieldsDTO);
            return newxml;
        } else {
            MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
            return xml;
        }
        // END CK27022012
        
    }
    
    private static void getConfiguration() {
        logger.debug("Konfiguration wird geholt.");
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

}

