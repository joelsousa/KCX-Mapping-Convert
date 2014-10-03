package com.kewill.kcx.component.mapping.test.Export.mains;

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

import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToUidsTESTMM {
	public static void main(String[] args) {
		KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
//		String payload = getErrorMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
        	
			xml = (String) kidsToUids.convert(payload, new File("dummy.txt"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToUidsMM main) Converted Message = \n" + xml);
       
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

}
