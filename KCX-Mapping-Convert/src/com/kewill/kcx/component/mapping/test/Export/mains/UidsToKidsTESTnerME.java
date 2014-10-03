
package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : UidsToKidsTESTnerMe.java
 * Titel       :
 * Date        : 29.09.2008
 * Author      : Kewill CSF / messer
 * Description : Test class specially used by ME
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
import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class UidsToKidsTESTnerME {

	public static void main(String[] args) {
		UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
		
		String payload = getFileMessage(args[0]);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			xml = uidsToKids.readUids(parser, "TestAuditId", "UTF-8", EDirections.CustomerToCountry);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(Msg main) Converted Message = \n" + xml);
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
				line += Utils.LF;
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
}

