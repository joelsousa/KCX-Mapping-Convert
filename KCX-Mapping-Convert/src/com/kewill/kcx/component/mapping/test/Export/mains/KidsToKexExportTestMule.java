package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : kexToKidsEMCSiwa.java
 * Titel       :
 * Date        : 20.05.2010
 * Author      : Kewill CSF / iwaniuk
 * Description : TestMain for NCTS conversion of Uids messages to Kids messages
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

import com.kewill.kcx.component.mapping.common.start.RemoveSoap;
import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToKexMule;
import com.kewill.kcx.component.mapping.util.Utils;


public class KidsToKexExportTestMule {

	public static void main(String[] args) {
	
		KidsToKexMule kidsToKex = new KidsToKexMule();
		
	
		String payload = getFileMessage(args[0]);
    	Utils.log("kexMessage : " + payload);

		
		
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);
       
        try {
        	
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);

        	Object xml2 = new RemoveSoap().removeSoap(payload, "Result");
        	
        
        	String result = xml2.toString();
        	Utils.log("(kexToKidsTEST main) Converted Message = \n" + result);
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

