package com.kewill.kcx.component.mapping.companies.fedex.ics;

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
import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsMule;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module    	: KidsToFedexICS.java
 * Created     : 29.12.2010
 * Description : TestMain for ICS conversion of Kids messages to Fedex messages.
 *  
 *  @author Frederick T.
 *  @version 1.0.00
 */
public class KidsToKidsICSTestMain {

	public static void main(String[] args) {
		
		KidsToKidsMule kidsTokids = new KidsToKidsMule();
		
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);
        
        Object xml = "";

        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		       
    		xml = kidsTokids.readKids(parser, "UTF-8", "testfile", 
    				EDirections.CountryToCustomer);  	        		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(KidsToKidsICSTest main) Converted Message = \n" + xml);          
	}
	
	public static void main_alt(String[] args) {
		KidsToFedexICS kidsToFedex = new KidsToFedexICS();
		KidsHeader kidsHeader; 
		CommonFieldsDTO commonFieldsDTO;
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        Object xml = "";
        
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
        	
        	kidsHeader = getKidsHeader(parser);
        	commonFieldsDTO = getCommonFieldsDTO(kidsHeader);
        	
        	xml = kidsToFedex.readKids(parser, "UTF-8", kidsHeader, commonFieldsDTO);
        } catch (Exception e) {
			e.printStackTrace();
		}
        
        Utils.log("(KidsToFedexICSTEST main) Converted Message = \n" + xml);
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
	
	private static KidsHeader getKidsHeader(XMLEventReader parser) throws Exception {
        KidsHeader kidsHeader = new KidsHeader();
        kidsHeader.setParser(parser);
        kidsHeader.setHeaderFields();
        return kidsHeader;
	}
	
	private static CommonFieldsDTO getCommonFieldsDTO(KidsHeader kidsHeader) throws Exception {
		CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
 
		commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
		//commonFieldsDTO.setUidsId(localId);
		commonFieldsDTO.setUidsId("localId");
		/*
		CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
                                                    kidsHeader.getReceiver(), 
                                                    kidsHeader.getProcedure().toUpperCase());
		commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
		*/
		CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kidsHeader.getReceiver());
		commonFieldsDTO.setCustomerDataDTO(customerDataDTO);
		commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
		//commonFieldsDTO.setFilename(filename);
		commonFieldsDTO.setFilename("filename");
		commonFieldsDTO.setUidsId("DE01.CSFALT.2144");
		
		return commonFieldsDTO;
	}
}
