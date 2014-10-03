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

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

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
public class KidsToZabisExtern  {
	public String convert(String message, File outFile, String encoding) throws Exception {
        String procedure = getProcedure(message);
        Utils.log("(KidsToZabisExtern onCall) procedure = " + procedure);
        
        if (Utils.isStringEmpty(procedure)) {
            Utils.log("(KidsToZabisExtern onCall) Could not determine customs procedure from KIDS header!");
            throw new KcxMappingException("(KidsToZabisExtern onCall) " +
            		                        "Could not determine customs procedure from KIDS header!");
        }
        
        procedure = procedure.trim().toUpperCase();
//        if (procedure.equalsIgnoreCase("EXPORT") || procedure.equalsIgnoreCase("NCTS")) {   MS20111010
          if (procedure.equalsIgnoreCase("EXPORT") || procedure.equalsIgnoreCase("NCTS") ||
                                                      procedure.equalsIgnoreCase("IMPORT")) {   // MS20111010
            KidsToFssExtern kidsToFssExtern = new KidsToFssExtern();
            return kidsToFssExtern.convert(message, outFile, encoding);
        } else {
            KcxToKidsExtern kcxToKidsExtern = new KcxToKidsExtern();
            return kcxToKidsExtern.convert(message, outFile, encoding);
        }
	}
	
    private String getProcedure(String payload) throws Exception {
        // MS20110930 Begin
//        String content = removeKcxEnvelope(payload);
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, null);
        // MS20110930 End
        
        String procedure = readKidsHeader(content);
        
        return procedure;
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

}

