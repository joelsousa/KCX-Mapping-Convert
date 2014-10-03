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
package com.kewill.kcx.component.mapping.common.start.customer.out;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.KcxToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
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

        String xml = removeKcxEnvelope(payload, message.getEncoding(), EDirections.CountryToCustomer);
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

        // MS20111208 Begin
//        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
        if (Config.isWriteToCustomerDir()) {
            MuleUtils.writeCustomerMessage(message, xml, commonFieldsDTO);
        } else {
            MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
        }
        // MS20111208 End
        
        return xml;
    }
    
//    public String removeKcxEnvelope(String payload, String encoding) throws Exception {
//        String content = null;
//        
//        InputStream ins = new StringInputStream(payload);
////        InputStreamReader is = new InputStreamReader(ins, encoding);
//        InputStreamReader is = new InputStreamReader(ins);
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(is);
//        
//        XmlMsgScanner scanner = new XmlMsgScanner(parser);
//        scanner.skipTo(Token.START_TAG, "Content", 0);
//        scanner.next();
//        content = scanner.getLexem();
//        if (Config.getLogXML()) {
//            Utils.log("(KcxToKidsMule removeKcxEnvelope) content = \n" + content);
//        }
//        
//        return content.trim();
//    }
    
    private static void getConfiguration() {
        logger.debug("Konfiguration wird geholt.");
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

}

