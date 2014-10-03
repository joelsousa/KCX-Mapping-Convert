package com.kewill.kcx.component.mapping.test.mains.gr;

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
import com.kewill.kcx.component.mapping.common.start.customs.in.GreeceToKidsMule;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Greece to Kids conversion test class.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class GreeceToKidsICSTestMain {
    
    private GreeceToKidsICSTestMain() { }
    
	public static void main(String[] args) {
				
		GreeceToKidsMule greeceToKids = new GreeceToKidsMule();
		
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("GreeceMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
        	//unisysHeader = getUnisysHeader(parser);	  
        	//commonFieldsDTO = getCommonFieldsDTO(unisysHeader);
    	
        	xml = greeceToKids.readGreece(parser, "TestAuditId", "UTF-8", EDirections.CustomerToCountry);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Utils.log("(GreeceToKidsICSTEST main) Converted Message = \n" + xml);
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

