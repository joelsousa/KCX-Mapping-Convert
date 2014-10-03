package com.kewill.kcx.component.mapping.test.ICS.mains;

/*
 * Function    : KidsToKidsICS.java
 * Titel       :
 * Date        : 14.06.2010
 * Author      : Pete T
 * Description : TestMain for ICS conversion of Kids messages to Kids messages
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
import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsMule;
import com.kewill.kcx.component.mapping.countries.de.ics20.KidsToKidsICS20;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToKidsICSTestMain20  {
		
	public static void main(String[] args) {

		KidsToKidsMule kidsToKids = new KidsToKidsMule();; 
		KidsHeader kidsHeader; 
		CommonFieldsDTO commonFieldsDTO;
		
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";

        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		       
        	//kidsHeader = getKidsHeader(parser);	  replace
        	//commonFieldsDTO = getCommonFieldsDTO(kidsHeader);
        	
    		xml = kidsToKids.readKids(parser, payload, "", EDirections.CustomerToCountry);

    		xml = xml.substring(xml.indexOf("<Release>"),
    			  xml.indexOf("</Release>") + "</Release>".length()) + "\n" + "\n" +
    			  xml.substring(xml.indexOf("<MessageName>"),
    							xml.indexOf("</MessageName>") + "</MessageName>".length()) + "\n" + "\n" +
    			  xml.substring(xml.indexOf("<GoodsDeclaration>"), 
    			  xml.indexOf("</GoodsDeclaration>") + "</GoodsDeclaration>".length()).
    			  replace("><", ">\n<");
    		
    		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(KidsToKids main) Converted Message = \n" + xml);          
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
