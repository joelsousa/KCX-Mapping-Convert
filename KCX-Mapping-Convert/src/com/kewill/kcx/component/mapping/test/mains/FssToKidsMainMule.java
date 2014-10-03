package com.kewill.kcx.component.mapping.test.mains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.customs.in.FssToKidsExtern;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module    	: test
 * Created     : 29.10.2012
 * Description : Fss2Kids TestMain for all procedures.
 *  
 *  @author iwaniuk
 *  @version 1.0.00
 */

public class FssToKidsMainMule extends FssToKidsExtern {

	public static void main(String[] args) {
		
     	FssToKidsExtern fssToKids = new FssToKidsExtern();
     	
		String payload = getFileMessage(args[0]);
		Utils.log("FssMessage : " + payload);
     	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(is);
        
        String xml = "";
		try {
			xml = fssToKids.readFss(br, "TestAuditID", args[0], EDirections.CountryToCustomer);
			Utils.log("(FssToKidsMainMule) Converted Message = \n" + xml);
			CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
			commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
			MuleUtils.writeFile(args[0], "UTF-8", xml, commonFieldsDTO);
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
