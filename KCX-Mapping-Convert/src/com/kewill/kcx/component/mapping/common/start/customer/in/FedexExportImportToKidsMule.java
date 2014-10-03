package com.kewill.kcx.component.mapping.common.start.customer.in;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.FedexExportImportToKidsConverter;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.MsgDeclnInput;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul        : FedexExportImport<br>
 * Erstellt     : 28.11.2013<br>
 * Beschreibung : FedexExportImportToKidsMule called by MULE. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class FedexExportImportToKidsMule extends FedexExportImportToKidsConverter implements Callable {
    private MuleEventContext muleEventContext = null;
    private MuleMessage      muleMessage      = null;

    public String onCall(MuleEventContext muleEventContext) throws Exception {
        Config.configure("conf", "kcx.ini");
        getConfiguration();
        
        MuleMessage message   = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
        this.muleMessage      = message;
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(FedexExportImportToKidsMule onCall) FedexOriginalPayload = \n" + payload);
        }
        
        Utils.log("(FedexExportImportToKidsMule onCall) message.getEncoding() = " + message.getEncoding());
        String encoding = message.getEncoding();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, encoding);
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        MsgDeclnInput msgDecln = parseFedexMessage(parser);
        commonFieldsDTO.setMessageReferenceNumber(message.getStringProperty("originalFilename", "originalFilename"));
        KidsHeader kidsHeader = createKidsHeader(msgDecln, encoding, EDirections.CustomerToCountry);
                
        String xml = createKidsXMLFromFedexDeclaration(msgDecln, kidsHeader, encoding, EDirections.CustomerToCountry);
        
        if (Config.getLogXML()) {
            Utils.log("(FedexExportImportToKidsMule onCall) converted xml = " + xml);
        }
        Utils.log("(FedexExportImportToKidsMule nach read(): ) commonFieldsDTO.refnr = " + commonFieldsDTO.getMessageReferenceNumber());
        
        if (commonFieldsDTO != null && commonFieldsDTO.getMessageReferenceNumber() != null) {
        	Utils.log("(FedexExportImportToKidsMule onCall) commonFieldsDTO.refnr = " + commonFieldsDTO.getMessageReferenceNumber());
        	message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        }

        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);

        if (Config.isCreateInName()) {
            String filename = MuleUtils.createFileName(commonFieldsDTO); 
            message.setStringProperty("filename", filename);
        }

        MuleUtils.writeInFileMessage(message, payload, commonFieldsDTO);
        
        message.setStringProperty("Primary", kidsHeader.getCountryCode());
        message.setStringProperty("Secondary", kidsHeader.getProcedure().toUpperCase());
        
        return xml;
    }
    
    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

}
