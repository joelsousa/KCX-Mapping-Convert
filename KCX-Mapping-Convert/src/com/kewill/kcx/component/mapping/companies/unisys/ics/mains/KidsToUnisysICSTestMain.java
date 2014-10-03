package com.kewill.kcx.component.mapping.companies.unisys.ics.mains;

/*
 * Function    : KidsToUnisysICS.java
 * Titel       :
 * Date        : 15.12.2010
 * Author      : krzoska
 * Description : TestMain for ICS conversion of Kids messages to Unisys messages
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
import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUnisysMule;
import com.kewill.kcx.component.mapping.companies.unisys.ics.KidsToUnisysICS;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToUnisysICSTestMain  {
		
	public static void main(String[] args) {
		
		//FedexToKidsICS fedexToKids = new FedexToKidsICS();
		KidsToUnisysMule  kidsToUnisysMule = new KidsToUnisysMule();
		
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("UnisysMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
        	//unisysHeader = getUnisysHeader(parser);	  
        	//commonFieldsDTO = getCommonFieldsDTO(unisysHeader);
        	        	
        	Object xml = kidsToUnisysMule.readKids(parser, "UTF-8", "testfile", EDirections.CountryToCustomer);
        	//EI20110913: lesen mit KCX-Envelope (...CDATA/Content ...)
        	//kidsToUnisysMule.readKids(payload, "UTF-8", "testfile", EDirections.CountryToCustomer);  //EI20110913
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main_ALT(String[] args) {

		KidsToUnisysICS kidsToUids = new KidsToUnisysICS(); 
		KidsHeader kidsHeader; 
		CommonFieldsDTO commonFieldsDTO;
		
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
        	
    		xml = kidsToUids.readKids(parser, "UTF-8", kidsHeader, commonFieldsDTO);  	        		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(KidsToUids main) Converted Message = \n" + xml);          
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
