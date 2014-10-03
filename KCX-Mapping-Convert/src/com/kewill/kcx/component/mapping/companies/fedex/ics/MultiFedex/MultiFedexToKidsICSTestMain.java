package com.kewill.kcx.component.mapping.companies.fedex.ics.MultiFedex;

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
import com.kewill.kcx.component.mapping.companies.fedex.ics.*;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

public class MultiFedexToKidsICSTestMain {

	public static void main(String[] args) {
	
		MultiFedexToKidsICS fedexToKids = new MultiFedexToKidsICS();
		FedexHeader fedexHeader; 
		CommonFieldsDTO commonFieldsDTO;
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("uidsErrorInformation : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
        	fedexHeader = getFedexHeader(parser);	  
        	commonFieldsDTO = getCommonFieldsDTO(fedexHeader);
        	
			xml = fedexToKids.readFedex(parser, "UTF-8", fedexHeader, commonFieldsDTO);
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

