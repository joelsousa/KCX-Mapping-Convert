package com.kewill.kcx.component.mapping.test.port;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToJobExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToJobTESTCK {
	
	public static void main(String[] args) {
		KidsToJobExtern kidsToJob = new KidsToJobExtern();
		String message = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + message);
    	
        String xml = "";
        try {
			xml = (String) kidsToJob.convert(message, new File("dummy"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToJob main) Converted Message = \n" + xml);
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
