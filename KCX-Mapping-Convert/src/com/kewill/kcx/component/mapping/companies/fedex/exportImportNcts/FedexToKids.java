package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.common.start.EKidsProcedureVersions;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.MsgDeclnInput;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
* Module		: FEDEX-ExportImportNCTS.<br>
* Created		: 17.02.2014<br>
* Description	: MapDeclnInput
* 
* @author iwaniuk
* @version 1.0.00
*/

public class FedexToKids {

	//public String readFedexDecln(XMLEventReader parser, String encoding, KidsHeader kidsHeader) throws Exception {
	public String readFedexDecln(MsgDeclnInput msgDecln, String encoding, KidsHeader kidsHeader, String procedureVersion) throws Exception {
		if (procedureVersion == null) {
			Utils.log("(FedexToKids readFedexDecln) MsgDeclnInput is empty");
			return "";
		}
		if (kidsHeader == null) {
			Utils.log("(FedexToKids readFedexDecln) KidsHeader is empty");
			return "";
		}
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);   
        kidsHeader.setWriter(writer);

        //String msg = "DECLN-INPUT";
        //Utils.log("(FedexExportToKids readFedexDecln) MessageName " + msg);
        Utils.log("(FedexToKids readFedexDecln) MessageName: DECLN-INPUT");
       
        switch (EKidsProcedureVersions.valueOf(procedureVersion)) {
        	case K21EXPORT:              
        		MapExportDeclarationFK mapExportFK = new MapExportDeclarationFK(msgDecln, kidsHeader, encoding);  
        		kidsHeader.setMessageName("ExportDeclaration");          	     
        		mapExportFK.getMessage(writer);
        	    break;
        	case K20IMPORT:                      
        		MapImportDeclarationFK mapImportFK = new MapImportDeclarationFK(msgDecln, kidsHeader, encoding);  
        		kidsHeader.setMessageName("ImportDeclaration");          	     
        		mapImportFK.getMessage(writer);
        	    break;
        	case K41NCTS:
        		MapNctsDeclarationFK mapNctsFK = new MapNctsDeclarationFK(msgDecln, kidsHeader, encoding);  
        		kidsHeader.setMessageName("NCTSDeclaration");          	     
        		mapNctsFK.getMessage(writer);
        	    break;
        	default: throw new FssException("Unknown message type " + procedureVersion);
        	   		
        }
       
        String xml = bos.toString();
        return xml;
    }

	private XMLStreamWriter getWriter(ByteArrayOutputStream bos, String encoding) throws Exception {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer   = factory.createXMLStreamWriter(osw);

	    return writer;
	}
	
   
}
