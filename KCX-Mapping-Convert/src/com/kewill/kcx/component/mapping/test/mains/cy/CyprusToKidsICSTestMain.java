package com.kewill.kcx.component.mapping.test.mains.cy;

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
import com.kewill.kcx.component.mapping.common.start.customer.in.UnisysToKidsMule;
import com.kewill.kcx.component.mapping.common.start.customs.in.CyprusToKidsMule;
import com.kewill.kcx.component.mapping.companies.unisys.ics.UnisysToKidsICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.util.Utils;

public class CyprusToKidsICSTestMain {

	public static void main(String[] args) {
				
		CyprusToKidsMule cyprusToKids = new CyprusToKidsMule();
		
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("UnisysMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
        	//unisysHeader = getUnisysHeader(parser);	  
        	//commonFieldsDTO = getCommonFieldsDTO(unisysHeader);
    	
        	xml = cyprusToKids.readCyprus(parser, "TestAuditId", "UTF-8", EDirections.CustomerToCountry);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(CyprusToKidsICSTEST main) Converted Message = \n" + xml);
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

}

