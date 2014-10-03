package com.kewill.kcx.component.mapping.common.start.customer.out;


/*
 * Function    : KidsToKff-Konverter.java
 * Titel       :
 * Date        : 10.11.2011
 * Author      : Kewill  / kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to KFF 
 * 				 copy from KidsToZabisMule.java
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

import com.kewill.kcx.component.mapping.common.start.RemoveKcxEnvelope;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.Token;

/**
 * Modul : KidsToKffMule<br>
 * Erstellt : 10.11.2011<br>
 * Beschreibung : Transformer called by Mule to convert KIDS format into KFF formats (PORT/KIDS).
 *                Tries to detect the message format and the calls the correct transformer. *
 *                 
 * @author kron
 * @version 1.0.00
 */
public class KidsToKffMule  implements Callable {
	public String onCall(MuleEventContext muleEventContext) throws Exception {
        String procedure = getProcedure(muleEventContext);		
        String method = getMethod(muleEventContext);             //EI20120228
        String messageName = getMessageName(muleEventContext);   //EI20120726
        String kcxId = getKcxId(muleEventContext);   //EI20130528
        boolean isKFF = false;   					//EI20130528
        //EI20130902:Utils.log("(KidsToKffMule onCall) procedure/method/kcx_id = " + procedure + "/" + method + "/" + kcxId);  
        Utils.log("(KidsToKffMule onCall) procedure/method/kcx_id/message = " + procedure + "/" + method + "/" + 
        							kcxId+ "/" + messageName);       
        
        if (Utils.isStringEmpty(procedure)) {
            Utils.log("(KidsToKffMule onCall) Could not determine customs procedure from KIDS header!");
            throw new KcxMappingException("(KidsToKffMule onCall) " +
            		                        "Could not determine customs procedure from KIDS header!");
        }
        if (procedure == null) {  //EI20120726
        	procedure = " ";
        }
        procedure = procedure.trim().toUpperCase();
        // CK20120229 
        if (method == null) {
        	method = " ";
        }
        if (messageName == null) {    //EI20120726
        	messageName = " ";
        }
        if (procedure.equalsIgnoreCase("IMPORT") && messageName.equalsIgnoreCase("ImportTaxAssessment")) {
        	isKFF = isKFF(kcxId);
        	Utils.log("(KidsToKffMule onCall) isKFF = " + isKFF);        	
        }
        if (procedure.equalsIgnoreCase("PORT")) {   
            KidsToKffJobMule kidsToPortMule = new KidsToKffJobMule();
            return kidsToPortMule.onCall(muleEventContext);
        //} else if (procedure.equalsIgnoreCase("IMPORT") && method.equalsIgnoreCase("JOB")) { //EI20120228
        //} else if (procedure.equalsIgnoreCase("IMPORT") && messageName.equalsIgnoreCase("ImportTaxAssessment")) { //EI20120726
        } else if (procedure.equalsIgnoreCase("IMPORT") && messageName.equalsIgnoreCase("ImportTaxAssessment") && isKFF) { //EI20130528        	
        	KidsToKffJobMule kidsToPortMule = new KidsToKffJobMule();
            return kidsToPortMule.onCall(muleEventContext);
        } else {
            KcxToKidsMule kcxToKidsMule = new KcxToKidsMule();
            return kcxToKidsMule.onCall(muleEventContext);
        }
	}
	
    private String getProcedure(MuleEventContext muleEventContext) throws Exception {	
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, null);
        String procedure = readKidsHeaderProcedure(content);
       
        return procedure;
    }

    private String readKidsHeaderProcedure(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        scanner.skipTo(Token.START_TAG, "Procedure", 0);
        scanner.next();
        String procedure = scanner.getLexem();
        scanner.close();
        
        return procedure;
    }
    private String getMethod(MuleEventContext muleEventContext) throws Exception {	
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, null);        
        String method = readKidsHeaderMethod(content);
        
        return method;
    }

    private String readKidsHeaderMethod(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        scanner.skipTo(Token.START_TAG, "Method", 0);
        scanner.next();
        String method = scanner.getLexem();
        scanner.close();
        
        return method;
    }
  //EI20120726
    private String getMessageName(MuleEventContext muleEventContext) throws Exception {	
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, null);        
        String messageName = readKidsHeaderMessageName(content);
        
        return messageName;
    }
  //EI20120726
    private String readKidsHeaderMessageName(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        scanner.skipTo(Token.START_TAG, "MessageName", 0);
        scanner.next();
        String messageName = scanner.getLexem();
        scanner.close();
        
        /* wenn es mehrere Messages werden sollte, die in JOB-Format konvertiert werden sollten:
        if(messageName != null) {
        	messageName = messageName.trim();
        	if (messageName..equalsIgnoreCase("ImportTaxAssessment")) {
        		messageName = "KidsToJob";
        	}
        } 
       */
        return messageName;           
    }
    private String getKcxId(MuleEventContext muleEventContext) throws Exception {	
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, null);        
        String kcxId = readKidsHeaderReceiver(content);
        
        return kcxId;
    }

    private String readKidsHeaderReceiver(String content) throws Exception {
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);
        scanner.skipTo(Token.START_TAG, "Receiver", 0);
        scanner.next();
        String kcxId = scanner.getLexem();
        scanner.close();
        
        return kcxId;
    }
    
    private boolean isKFF(String kcxId) {
    	String bob = Utils.getBobNameFromKcxId(kcxId);
    	if (bob != null) {    //EI20130902
    		bob = bob.trim();
    	}
    	return (bob != null && bob.equalsIgnoreCase("KFF"));    	
    }
}

