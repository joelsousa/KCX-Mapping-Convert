package com.kewill.kcx.component.mapping.test.mains.ie;

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
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToIrelandMule;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module    	: test
 * Created     : 29.10.2012
 * Description : Kids2CustomsMatters TestMain for ExportImportNcts.
 *  
 *  @author iwaniuk
 *  @version 1.0.00
 */

public class KidsToIrelandMainMule {
	//public class KidsToUidsMainMule extends KidsToUidsMule {
		
	public static void main(String[] args) {
		
        KidsToIrelandMule  kidsToCMMule = new KidsToIrelandMule();
		
		String payload = getFileMessage(args[0]);
    	Utils.log("KidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);       
       
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		 	
        	//Object xml = kidsToUidsMule.readKids(parser, "UTF-8", "testfile", EDirections.CountryToCustomer);
        	Object xml = kidsToCMMule.readKids(payload, "UTF-8", "testfile", EDirections.CountryToCustomer);
        	Utils.log("(kidsToCMMule) Converted Message = \n" + xml);  
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
