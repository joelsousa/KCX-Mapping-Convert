/*
 * Function    : 
 * Titel       :
 * Date        : 08.02.2010
 * Author      : Kewill CSF / iwaniuk
 * Description : 
 * Parameters  : 
 */

package com.kewill.kcx.component.mapping.test.Export.mains.V60;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.common.start.customs.in.FssToKidsExtern;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * @author iwaniuk 
 *
 */
public class BDecToKidsTESTiwa {

	public static void main(String[] args) {
    	
    	TsVOR tsVOR = new TsVOR("A");
    	tsVOR.setMan("CSF");
    	tsVOR.setNl("");
    	tsVOR.setModul("AES");
    	tsVOR.setNatyp("HH");   //hier beim Testen die Nachricht-Kürzel eintragen
    	tsVOR.setMsgid("4711");
    	tsVOR.setVersnr("6.00");    	    
    
    	String payload = getFileMessage(args[0]);    	
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(is);
        
        String dummy = "";        
        FssToKidsExtern fssToKids = new FssToKidsExtern();
        
        String xml = "";
		try {
			xml = fssToKids.convert(new File(args[0]), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(FssToKids main) Converted Message = \n" + xml);
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
