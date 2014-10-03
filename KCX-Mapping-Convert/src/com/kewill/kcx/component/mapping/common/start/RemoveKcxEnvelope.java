package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : RemoveKcxEnvelope.java
 * Titel       :
 * Date        : 29.09.2011
 * Author      : Kewill GmbH / Michael Schmidt
 * Description : Remvoe a KCX envelope from a message. 
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
 * Module       : RemoveKcxEnvelope<br>
 * Created 		: 29.09.2011<br>
 * Description  : Remvoe a KCX envelope from a message. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class RemoveKcxEnvelope {
    
    public String removeEnvelope(String message, String encoding) throws Exception {
        if (Config.getLogXML()) {
            Utils.log("(RemoveKcxEnvelope removeEnvelope) message = \n" + message);
        }
        String content = null;

        InputStream ins = new ByteArrayInputStream(message.getBytes());
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        // MS20110929 Begin
        // Falls eine Nachricht requeued wird, fehlt unter Umständen bereits der KCX-Envelope.
        // Das ist daran erkennbar, das das Tag "Content" fehlt.
        // In diesem Fall wird einfach die komplette Nachricht zurückgegeben.
        // scanner.skipTo(Token.START_TAG, "Content", 0);
        if (!scanner.skipTo(Token.START_TAG, "Content", 0)) {
            Utils.log("(RemoveKcxEnvelope removeEnvelope) KCX-Envelope was already removed.");
            return message.trim();
        }
        // MS20110929 End
        scanner.next();
        content = scanner.getLexem().trim();
        if (Config.getLogXML()) {
            Utils.log("(RemoveKcxEnvelope removeEnvelope) content = \n" + content);
        }
        
        return content;
    }
}
