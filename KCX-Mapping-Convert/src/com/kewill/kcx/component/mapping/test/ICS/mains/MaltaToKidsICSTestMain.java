package com.kewill.kcx.component.mapping.test.ICS.mains;

/*
 * Function    : MaltaToKidsICSTestMain
 * Titel       :
 * Date        : 21.08.2013
 * Author      : Alfred Krzoska
 * Description : TestMain for ICS conversion of Malta messages to Kids messages
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
import com.kewill.kcx.component.mapping.common.start.customs.in.MaltaToKidsMule;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToMaltaMule;
import com.kewill.kcx.component.mapping.countries.mt.ics.MaltaToKidsICS;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.util.Utils;

public class MaltaToKidsICSTestMain  {
		
	public static void main(String[] args) {
		MaltaToKidsMule maltaToKidsMule = new MaltaToKidsMule();
		MaltaHeader maltaHeader = new MaltaHeader(); 
		CommonFieldsDTO commonFieldsDTO;
		
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        Object xml = "";
        try {
	    	XMLInputFactory factory = XMLInputFactory.newInstance();
	    	XMLEventReader parser = factory.createXMLEventReader(is);
		       
	    	maltaHeader = getMaltaHeader(parser);	  
	    	commonFieldsDTO = getCommonFieldsDTO(maltaHeader);
			xml = maltaToKidsMule.readMalta(payload, "UTF-8", EDirections.CountryToCustomer);
			//readMalta(parser, "UTF-8", maltaHeader, commonFieldsDTO);
	        		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(MaltaToKids main) Converted Message = \n" + xml);          
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
	
	private static MaltaHeader getMaltaHeader(XMLEventReader parser) throws Exception {
	        MaltaHeader maltaHeader = new MaltaHeader();
	        maltaHeader.setParser(parser);
	        maltaHeader.setHeaderFields();
	        return maltaHeader;
	}
	
	private static CommonFieldsDTO getCommonFieldsDTO(MaltaHeader maltaHeader) throws Exception {
		CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
 
		commonFieldsDTO.setKcxId(maltaHeader.getMesRecMES6());
		//commonFieldsDTO.setUidsId(localId);
		//commonFieldsDTO.setKcxId("localId");
		/*
		CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
                                                    kidsHeader.getReceiver(), 
                                                    kidsHeader.getProcedure().toUpperCase());
		commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
		*/
		CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(maltaHeader.getMesRecMES6());
		commonFieldsDTO.setCustomerDataDTO(customerDataDTO);
		commonFieldsDTO.setCountryCode("MT");
		//commonFieldsDTO.setFilename(filename);
		commonFieldsDTO.setFilename("filename");
		commonFieldsDTO.setKcxId("AE.EK.TST");
		
		return commonFieldsDTO;
	}
}
