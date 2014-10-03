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
 * Module : FdxToKidsMule<br>
 * Created : 10.11.2011<br>
 * Description : Transformer called by Mule to convert Fedex formats (DECLN/ICSMessage) to KIDS format.
 *                Tries to detect the message format and the calls the correct transformer.  
 * @author iwaniuk
 * @version 1.0.00
 */
public class FdxToKidsMule_not_used implements Callable {

    public Object onCall(MuleEventContext muleEventContext) throws Exception {
        
    	Utils.log("(FdxToKidsMule onCall) check MessageFormat");
        boolean declnMessage = checkFile(muleEventContext);        
        
        if (declnMessage) {
        	Utils.log("(FdxToKidsMule onCall) is message a DECLlN-Message = " + declnMessage);
        	FedexExportImportToKidsMule declnToKidsMule = new FedexExportImportToKidsMule();
            return declnToKidsMule.onCall(muleEventContext);
        } else {
        	Utils.log("(FdxToKidsMule onCall) is message not DECLlN-Message = " + declnMessage);
        	FedexToKidsMule fedexToKidsMule = new FedexToKidsMule();        	        	
            return fedexToKidsMule.onCall(muleEventContext);
        }
    }
    
    private boolean checkFile(MuleEventContext muleEventContext) throws Exception {
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        return isDecln(payload);
    }

    private boolean isDecln(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        if (scanner.skipTo(Token.START_TAG, "DECLN_INPUT", 0)) {
        	return true;
        }
        return false;
    }    
}
