package com.kewill.kcx.component.mapping.common.start.customer.in;


/*
 * Function    : FssToKidsMule.java
 * Titel       :
 * Date        : 10.11.2011
 * Author      : Kewill / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert KFF messages in KIDS-Format
 * 				 evaluates the version and the procedure to start the
 * 				 mapping classes
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
 * Modul : KffToKidsMule<br>
 * Erstellt : 10.11.2011<br>
 * Beschreibung : Transformer called by Mule to convert KFF formats (PORT/KIDS) to KIDS format.
 *                Tries to detect the message format and the calls the correct transformer. * 
 * @author kron
 * @version 1.0.00
 */
public class KffToKidsMule implements Callable {

    public Object onCall(MuleEventContext muleEventContext) throws Exception {
        
    	Utils.log("(KffToKidsMule onCall) check Procedure");
        boolean jobMessage = getProcedure(muleEventContext);
        Utils.log("(KffToKidsMule onCall) is message a JOB-Message = " + jobMessage);
        
        if (jobMessage) {
            KffJobToKidsMule jobToKidsMule = new KffJobToKidsMule();
            return jobToKidsMule.onCall(muleEventContext);
        } else {
        	KidsToKidsMule kidsToKidsMule = new KidsToKidsMule();
            return kidsToKidsMule.onCall(muleEventContext);
        }
    }
    
    private boolean getProcedure(MuleEventContext muleEventContext) throws Exception {
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        return findXmltagJob(payload);
    }

    private boolean findXmltagJob(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        if (scanner.skipTo(Token.START_TAG, "Job", 0)) {
        	return true;
        }
        return false;
    }    
}
