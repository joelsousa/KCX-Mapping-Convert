package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : 
 * Titel       :
 * Date        : 09.10.2008
 * Author      : Kewill CSF / Houdek
 * Description : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
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
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToBdecExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToBdecMule;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KidsToBDecTESTiwa<br>
 * Erstellt		: 16.06.2010<br>
 * Beschreibung	: Test für UK-Customs BDEC Format. 
 * 
 * @author ???
 * @version 1.0.00
 */
public class KidsToBDecTESTiwa extends KidsToBdecExtern {
    /*
	public static void main(String[] args) {

		KidsToBdecExtern kidsToBdec = new KidsToBdecExtern();  		
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		        	
        	if (args.length > 1 && args[1].equals("UK"))  {         		
        		MapExpDatToECustoms mapExpDatToECustoms = new MapExpDatToECustoms(parser);
        		xml = mapExpDatToECustoms.getMessage();        		
        	} else {
        		xml = kidsToBdec.convert(payload, null, "UTF-8");  	
            }
//        	String dummy = "Dx";
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToFss main) Converted Message = \n" + xml);          
	}
	*/
	public static void main(String[] args) {
		
		//FedexToKidsICS fedexToKids = new FedexToKidsICS();
		KidsToBdecMule  kidsToBdecMule = new KidsToBdecMule();
		
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("KidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);		        	       	
        	Object xml = kidsToBdecMule.readKids(parser, "UTF-8", "testfile", EDirections.CountryToCustomer);			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return payload.toString();
	}	
}
