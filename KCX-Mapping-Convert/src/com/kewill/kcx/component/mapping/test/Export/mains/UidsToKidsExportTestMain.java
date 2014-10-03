package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : UidsToKidsEMCSiwa.java
 * Titel       :
 * Date        : 20.05.2010
 * Author      : Kewill CSF / iwaniuk
 * Description : TestMain for Export conversion of Uids messages to Kids messages
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
import com.kewill.kcx.component.mapping.countries.de.aes.UidsToKidsExport;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

//public class UidsToKidsEMCSTeisMain extends UidsToKidsEmcs {
public class UidsToKidsExportTestMain {

	public static void main(String[] args) {
	
		UidsToKidsExport uidsToKids = new UidsToKidsExport();
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
        Utils.log("(UidsToKidsTESTiwa main) Converted Message = \n" + xml);
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
   

	/*
	commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
	commonFieldsDTO.setUidsId(localId);		
	CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
                                                kidsHeader.getReceiver(), 
                                                kidsHeader.getProcedure().toUpperCase());
	commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
	CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kidsHeader.getReceiver());	
	commonFieldsDTO.setCustomerDataDTO(customerDataDTO);
	commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
	commonFieldsDTO.setFilename(filename);
	*/
	
	commonFieldsDTO.setFilename("filename");
	commonFieldsDTO.setKcxId("DE.CSF.ALT");
	commonFieldsDTO.setUidsId("DE01.CSFALT.2144");
	commonFieldsDTO.setCountryCode("DE");
	
	return commonFieldsDTO;
	}	
}


