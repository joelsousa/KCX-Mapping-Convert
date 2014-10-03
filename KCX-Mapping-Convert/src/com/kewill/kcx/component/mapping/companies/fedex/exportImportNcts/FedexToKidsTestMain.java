package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts;

/*
 * Function    : UidsToKidsICS.java
 * Titel       :
 * Date        : 10.06.2010
 * Author      : Pete T
 * Description : TestMain for EMCS conversion of Uids messages to Kids messages
 * 			   : 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.FedexExportImportToKidsConverter;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.MsgDeclnInput;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

public class FedexToKidsTestMain extends FedexExportImportToKidsConverter {

	public static void main(String[] args) {
		FedexToKidsTestMain converter = new FedexToKidsTestMain();
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("FedexMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
            MsgDeclnInput msgDecln = converter.parseFedexMessage(parser);
            converter.commonFieldsDTO.setMessageReferenceNumber("originalFilename");
            KidsHeader kidsHeader = converter.createKidsHeader(msgDecln, "utf-8", EDirections.CustomerToCountry);
                    
            xml = converter.createKidsXMLFromFedexDeclaration(msgDecln, kidsHeader, "utf-8", EDirections.CustomerToCountry);
            		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(FedexToKidsICSTEST main) Converted Message = \n" + xml);
	}
	
	

	private static String getFileMessage(String fileName) {
		File inFile = new File(fileName);
		StringBuffer payload = new StringBuffer();
		
		try {
			FileReader fr = new FileReader(inFile);
			BufferedReader in = new BufferedReader(fr);
			String line = null;
			line = in.readLine();
			while (line != null) {
				payload.append(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return payload.toString();
	}
	
	private static FedexHeader getFedexHeader(XMLEventReader parser) throws Exception {
		FedexHeader fedexHeader = new FedexHeader();
		fedexHeader.setParser(parser);
		fedexHeader.setHeaderFields();        
        return fedexHeader;
	}

	private static CommonFieldsDTO getCommonFieldsDTO(FedexHeader fedexHeader) throws Exception {
		CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	   
		commonFieldsDTO.setFilename("filename");
		commonFieldsDTO.setKcxId("DE.CSF.ALT");
		commonFieldsDTO.setUidsId("");
		commonFieldsDTO.setCountryCode("DE");
		commonFieldsDTO.setDirection(EDirections.CustomerToCountry);  //CountryToCustomer	
		commonFieldsDTO.setMessageReferenceNumber(fedexHeader.getTransactionId()); 
		
		return commonFieldsDTO;
	}	
}

