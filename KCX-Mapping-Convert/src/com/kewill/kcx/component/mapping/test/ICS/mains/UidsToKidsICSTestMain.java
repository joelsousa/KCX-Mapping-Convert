package com.kewill.kcx.component.mapping.test.ICS.mains;

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

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.ics.UidsToKidsICS;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

public class UidsToKidsICSTestMain {

	public static void main(String[] args) {
	
		UidsToKidsICS uidsToKids = new UidsToKidsICS();
		UidsHeader uidsHeader; 
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
		
        	uidsHeader = getUidsHeader(parser);	  
        	commonFieldsDTO = getCommonFieldsDTO(uidsHeader);
        	
			xml = uidsToKids.readUids(parser, "UTF-8", uidsHeader, commonFieldsDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(UidsToKidsICSTEST main) Converted Message = \n" + xml);
	}
	

	private static String getFileMessage(String fileName){
		File inFile = new File(fileName);
		StringBuffer payload = new StringBuffer();
		
		try {
			FileReader fr = new FileReader(inFile);
			BufferedReader in = new BufferedReader(fr);
			String line = null;
			line = in.readLine();
			while(line != null){
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
	
	private static UidsHeader getUidsHeader(XMLEventReader parser) throws Exception {
        UidsHeader uidsHeader = new UidsHeader();
        uidsHeader.setParser(parser);
        uidsHeader.setHeaderFields();        
        return uidsHeader;
	}

	private static CommonFieldsDTO getCommonFieldsDTO(UidsHeader kidsHeader) throws Exception {
		CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	   
		commonFieldsDTO.setFilename("filename");
		commonFieldsDTO.setKcxId("DE.CSF.ALT");
		commonFieldsDTO.setUidsId("DE01.CSFALT.2144");
		commonFieldsDTO.setCountryCode("DE");
		
		return commonFieldsDTO;
	}	
}

