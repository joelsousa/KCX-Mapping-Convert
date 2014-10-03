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
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToGreeceMule;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * KIDS to Greece conversion test class.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class KidsToGreeceICSTestMain  {

    private KidsToGreeceICSTestMain() { }
		
	public static void main(String[] args) {
		
		KidsToGreeceMule  kidsToGreeceMule = new KidsToGreeceMule();
		
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("Greece message : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
        	//unisysHeader = getUnisysHeader(parser);	  
        	//commonFieldsDTO = getCommonFieldsDTO(unisysHeader);
        	        	
        	kidsToGreeceMule.readKids(parser, "UTF-8", "testfile", EDirections.CountryToCustomer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
