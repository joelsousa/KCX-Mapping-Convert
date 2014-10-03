/*
 * Function    : FssToKidsTEST.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF / Koschara
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

package com.kewill.kcx.component.mapping.test.Export.mains;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customs.in.FssToKidsExtern;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Testklass für die Konvertierung vom FSS nach KIDS.
 * 
 * @author koschara
 * @version 1.0.00
 *
 */
public class FssToKidsTEST extends FssToKidsExtern {

	public static void main(String[] args) {
    	char delim = 0x1d;
    	FssToKidsTEST fssToKids = new FssToKidsTEST();
    	TsVOR tsVOR = new TsVOR("A");
    	tsVOR.setMan("CSF");
    	tsVOR.setNl("TEST");
    	tsVOR.setModul("AES");
    	tsVOR.setNatyp("AZP");
    	tsVOR.setMsgid("4711");
    	String payload = tsVOR.teilsatzBilden() + "\n" +
    	"AFK" + delim + "Bezugsnummer" + delim + "MRN" + delim + "RegistriernummerFremdsystem" + delim + "\n" +
    	"AFP" + delim + "Bezugsnummer" + delim + "1" + delim + "Fehlercode" + delim + "FehlerText1" 
    	      + delim + "Zeiger" + delim + "\n" +
    	"AFP" + delim + "Bezugsnummer" + delim + "2" + delim + "Fehlercode" + delim + "FehlerText2" 
    	      + delim + "Zeiger" + delim + "\n" +
    	"AFP" + delim + "Bezugsnummer" + delim + "3" + delim + "Fehlercode" + delim + "FehlerText3" 
    	      + delim + "Zeiger" + delim + "\n";
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(is);
        
        String xml = "";
		try {
			xml = fssToKids.readFss(br, "TestAuditId", args[0], EDirections.CountryToCustomer);
		} catch (FssException e) {
			e.printStackTrace();
		}
        Utils.log("(FssToKids main) Converted Message = \n" + xml);
	}

}
