package com.kewill.kcx.component.mapping.test.EMCS.mains;

/*
 * Function    : KidsToUidsEMCSiwa.java
 * Titel       :
 * Date        : 20.05.2010
 * Author      : Kewill CSF / iwaniuk
 * Description : TestMain for EMCS conversion of Kids messages to Uids messages
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
import javax.xml.stream.XMLStreamWriter;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.emcs.KidsToUidsEmcs;
import com.kewill.kcx.component.mapping.countries.de.emcs.kids2uids.*;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

//public class KidsToUidsEMCSTestMain extends KidsToUidsEmcs {
public class KidsToUidsEMCSTestMain  {
		
	public static void main(String[] args) {

		KidsToUidsEmcs kidsToUids = new KidsToUidsEmcs();; 
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
        	
        	if (args.length > 1 && args[1].equals("EMCS"))  {         		
        		MapCancellationKU mapKU = new MapCancellationKU(parser, "UTF-8");	
        		mapKU.setKidsHeader(kidsHeader);
        		mapKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapKU.getMessage();        		
        	} else {
        		xml = kidsToUids.readKids(parser, "UTF-8", kidsHeader, commonFieldsDTO);  	        		
            }
        	String dummy = "Dx";
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
			// TODO Auto-generated catch block
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
		commonFieldsDTO.setProcedure("EMCS");
		
		return commonFieldsDTO;
	}
}
