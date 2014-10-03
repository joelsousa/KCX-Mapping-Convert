package com.kewill.kcx.component.mapping.common.start.customs.out;
/*
 * Function    : KidsToFssonverter.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to ZABIS Fss
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
 * Modul : KidsToZabisMule<br>
 * Erstellt : 27.05.2010<br>
 * Beschreibung : Transformer called by Mule to convert KIDS format into ZABIS formats (FSS/KIDS).
 *                Tries to detect the message format and the calls the correct transformer. *
 *                 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToZabisMule  implements Callable {
	public String onCall(MuleEventContext muleEventContext) throws Exception {
        String procedure = getProcedure(muleEventContext);
        String country = getCountryCode(muleEventContext);
        Utils.log("(KidsToZabisMule onCall) procedure = " + procedure);
        Utils.log("(KidsToZabisMule onCall) country = " + country);
        
        if (Utils.isStringEmpty(procedure)) {
            Utils.log("(KidsToZabisMule onCall) Could not determine customs procedure from KIDS header!");
            throw new KcxMappingException("(KidsToZabisMule onCall) " +
            		                        "Could not determine customs procedure from KIDS header!");
        }
        
        procedure = procedure.trim().toUpperCase();
//        if (procedure.equalsIgnoreCase("EXPORT") || procedure.equalsIgnoreCase("NCTS")) { MS20111010
        //EI20140121: if (procedure.equalsIgnoreCase("EXPORT") || procedure.equalsIgnoreCase("NCTS"))
        if (procedure.equalsIgnoreCase("EXPORT") || 
        		(procedure.equalsIgnoreCase("NCTS") && !country.equals("AT")) ||  //EI20140121: addet !AT bei NCTS
        	procedure.equalsIgnoreCase("IMPORT")  || procedure.equalsIgnoreCase("PORT") ||  // EI20111109
        	procedure.equalsIgnoreCase("MANIFEST")  ) {   // EI20130201
            KidsToFssMule kidsToFssMule = new KidsToFssMule();
            return kidsToFssMule.onCall(muleEventContext);
        } else {
            KcxToKidsMule kcxToKidsMule = new KcxToKidsMule();
            return kcxToKidsMule.onCall(muleEventContext);
        }
	}
	
    private String getProcedure(MuleEventContext muleEventContext) throws Exception {
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        // MS20110930 Begin
//        String content = removeKcxEnvelope(payload);
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, null);
        // MS20110930 End
        String procedure = readKidsHeader(content, "Procedure");
        
        return procedure;
    }
    private String getCountryCode(MuleEventContext muleEventContext) throws Exception {
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
        // MS20110930 Begin
//        String content = removeKcxEnvelope(payload);
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, null);
        // MS20110930 End
        String country = readKidsHeader(content, "CountryCode");
        
        return country;
    }

// MS20110930 Begin    
//    private String removeKcxEnvelope(String payload) throws Exception {
//        InputStream ins = new StringInputStream(payload);
//        InputStreamReader is = new InputStreamReader(ins);
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(is);
//        
//        XmlMsgScanner scanner = new XmlMsgScanner(parser);
//        scanner.skipTo(Token.START_TAG, "Content", 0);
//        scanner.next();
//        String content = scanner.getLexem();
//        scanner.close();
//        
//        return content.trim();
//    }
// MS20110930 End    

    private String readKidsHeader(String content) throws Exception {
    	String tagContent = "";
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);              
        scanner.skipTo(Token.START_TAG, "Procedure", 0);        	
        scanner.next();
        
        String procedure = scanner.getLexem();
        scanner.close();
        
        return tagContent;
    }

    private String readKidsHeader(String content, String tagName) throws Exception {
    	String tagContent = "";
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XmlMsgScanner scanner = new XmlMsgScanner(parser);       
        if (tagName != null && tagName.equals("Procedure")) {
        	scanner.skipTo(Token.START_TAG, "Procedure", 0);        	
        } else if (tagName != null && tagName.equals("CountryCode")) {
        	scanner.skipTo(Token.START_TAG, "CountryCode", 0);        	
        } 
        scanner.next();
        tagContent = scanner.getLexem();
        scanner.close();
        
        return tagContent;
    }

}

