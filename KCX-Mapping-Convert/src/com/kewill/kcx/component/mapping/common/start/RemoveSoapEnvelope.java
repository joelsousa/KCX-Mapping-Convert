package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : RemoveSoapEnvelope.java
 * Titel       :
 * Date        : 19.01.2012
 * Author      : Kewill GmbH / Michael Schmidt
 * Description : Remove a SOAP envelope from a message. 
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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.Token;


/**
 * Module       : RemoveSoapEnvelope<br>
 * Created 		: 19.01.2012<br>
 * Description  : emove a SOAP envelope from a message. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class RemoveSoapEnvelope {
    
    public static void main(String[] args) throws Exception {
        RemoveSoapEnvelope r = new RemoveSoapEnvelope();
        String msg = r.getTestMessage();
        r.removeEnvelope(msg);
    }
    
    public String removeEnvelope(String message) throws Exception {
        if (Config.getLogXML()) {
            Utils.log("(RemoveSoapEnvelope removeEnvelope) message = \n" + message);
        }
        String content = null;
        boolean skip = false;
        boolean headerSkip = false;

        InputStream ins = new ByteArrayInputStream(message.getBytes());
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        StringBuffer buf = new StringBuffer();
        buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buf.append("<Response>");
        while (scanner.hasNext()) {
            skip = false;
            Token token = scanner.next();
            content = scanner.getLexem();
            if (token == Token.START_TAG) {
                if (content.equalsIgnoreCase("Envelope")) {
                    skip = true;
                } 
                if (content.equalsIgnoreCase("Header")) {
                    if (!headerSkip) {
                        // 1. Header Tag
                        headerSkip = true;
                        skip = true;
                    } else {
                        // 2. Header Tag
                        headerSkip = false;
                        skip = false;
                    }
                } 
                if (content.equalsIgnoreCase("Body")) {
                    skip = true;
                } 
                if (!skip) {
                    buf.append("<" + content + ">");
                }
            }
            if (token == Token.VALUE) {
                if (!skip) {
                    buf.append(content);
                } else {
                    skip = false;
                }
            }
            if (token == Token.STOPP_TAG) {
                if (content.equalsIgnoreCase("Envelope")) {
                    skip = true;
                } 
                if (content.equalsIgnoreCase("Header")) {
                    if (!headerSkip) {
                        // 1. Header Tag
                        headerSkip = true;
                        skip = true;
                    } else {
                        // 2. Header Tag
                        headerSkip = false;
                        skip = false;
                    }
                } 
                if (content.equalsIgnoreCase("Body")) {
                    skip = true;
                } 
                if (!skip) {
                    buf.append("</" + content + ">");
                }
            }
        }
        buf.append("</Response>\n");
        String result = buf.toString().replaceAll("><", ">\n<");
        
        if (Config.getLogXML()) {
            Utils.log("(RemoveSoapEnvelope removeEnvelope) result = \n" + result);
        }
        
        return result;
    }
    
    
    private String getTestMessage() {
        String msg = "" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">" +
            "<soap:Header>" +
            "<Header>" +
            "<SentAt>" +
            "<Date>" +
            "<Year>2011</Year>" +
            "<Month>09</Month>" +
            "<Day>18</Day>" +
            "</Date>" +
            "<Time>102420</Time>" +
            "<TimeZone>+0200</TimeZone>" +
            "</SentAt>" +
            "<Transmitter>FR01.CSFALT.2144</Transmitter>" +
            "<Receiver>DE.CSF.ALT</Receiver>" +
            "<Method>EMB</Method>" +
            "<MessageTP>" +
            "<CountryCode>FR</CountryCode>" +
            "<Procedure>EMCS</Procedure>" +
            "<MessageName>EMCSValidDeclaration</MessageName>" +
            "<Release>2.0.00</Release>" +
            "<Direction>FROM_CUSTOMER</Direction>" +
            "</MessageTP>" +
            "<CustomsExchange>" +
            "<Codemapping>" +
            "<Map>0</Map>" +
            "<MapFrom>DE</MapFrom>" +
            "<MapTo>DE</MapTo>" +
            "</Codemapping>" +
            "</CustomsExchange>" +
            "<MessageID>KA-2011092614</MessageID>" +
            "</Header>" +
            "</soap:Header>" +
            "<soap:Body>" +
            "<EMCS>" +
            "<EMCSValidDeclaration>" +
            "<ReferenceNumber>KA-20110926-14</ReferenceNumber>" +
            "<CustomerOrderNumber>CustomerOrderNumber1</CustomerOrderNumber>" +
            "</EMCSValidDeclaration>" +
            "</EMCS>" +
            "</soap:Body>" +
            "</soap:Envelope>";            
        
        return msg;
        
    }
}
