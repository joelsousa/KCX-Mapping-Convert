package com.kewill.kcx.component.mapping.common.start.customer.in;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.Token;

/**
 * Modul : FdxImExToKidsMule<br>
 * Erstellt : 07.11.2013<br>
 * Beschreibung : voruebergehende loesung zum testen von FedexImoprtExport (normalerweise werden
 *  die Dateien ueber ActiveMQ kommen, jetzt kann man sie in KIDS rein werfen 
 *          
 * @author iwaniuk
 * @version 1.0.00
 */
public class FdxImExToKidsMule implements Callable {

    public Object onCall(MuleEventContext muleEventContext) throws Exception {
        
    	Utils.log("(FdxImExToKidsMule onCall) check Format");
        boolean isKidsMessage = getFormat(muleEventContext);
               
        if (isKidsMessage) {
        	 Utils.log("(FdxImExToKidsMule onCall) message is a Kids-Message");
        	KidsToKidsMule kidsToKidsMule = new KidsToKidsMule();        	        	
            return kidsToKidsMule.onCall(muleEventContext);            
        } else {
        	Utils.log("(FdxImExToKidsMule onCall) message is not Kids-Message");
        	FdxToKidsMule_not_used jobToKidsMule = new FdxToKidsMule_not_used();
            return jobToKidsMule.onCall(muleEventContext);
        }
    }
    
    private boolean getFormat(MuleEventContext muleEventContext) throws Exception {
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        return findXmlKidsTag(payload);
    }

    private boolean findXmlKidsTag(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        if (scanner.skipTo(Token.START_TAG, "Envelope", 0)) {
        	return true;
        }
        return false;
    }    
}
