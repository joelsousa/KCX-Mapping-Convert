/*
 * Function    : FssToKidsTESTASP.java
 * Titel       :
 * Date        : 16.10.2008
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
 * Testklasse für die Konvertierung von FSS (ASP) nach KIDS.
 * 
 * @author koschara
 * @version 1.0.00
 *
 */
public class FssToKidsTESTASP extends FssToKidsExtern {

	public static void main(String[] args) {
    	char delim = 0x1d;
    	FssToKidsExtern fssToKids = new FssToKidsExtern();
    	TsVOR tsVOR = new TsVOR("A");
    	tsVOR.setMan("CSF");
    	tsVOR.setNl("TEST");
    	tsVOR.setModul("ASP");
    	tsVOR.setNatyp("ASP");
    	tsVOR.setMsgid("4711");
    	String payload = tsVOR.teilsatzBilden() + "\n" +
    	"ASK" + delim + "Bezugsnummer" + delim + "01" + delim + "GRUNDGRUNDGRUNDGRUNG" + delim + "\n" +
    	"ASP" + delim + "Bezugsnummer" + delim + "ackdat" + delim + "andat" + delim + "uebdat" + delim + "gstdat" 
    	      + delim + "gststr" + delim + "gstend" + delim + "bwbdat" + delim + "stodat" + delim + "\n" +
        "AFP" + delim + "Bezugsnummer" + delim + "1" + delim + "Fehlercode1" + delim + "FehlerText1" 
              + delim + "Zeiger" + delim + "\n" +
    	"AFP" + delim + "Bezugsnummer" + delim + "2" + delim + "Fehlercode2" + delim + "FehlerText2" 
    	      + delim + "Zeiger" + delim + "\n" +
    	"AFP" + delim + "Bezugsnummer" + delim + "3" + delim + "Fehlercode3" + delim + "FehlerText3" 
    	      + delim + "Zeiger" + delim + "\n" +
    	"AFP" + delim + "Bezugsnummer" + delim + "4" + delim + "Fehlercode1" + delim + "FehlerText1" 
    	      + delim + "Zeiger" + delim + "\n" +
	    "AFP" + delim + "Bezugsnummer" + delim + "5" + delim + "Fehlercode2" + delim + "FehlerText2" 
	          + delim + "Zeiger" + delim + "\n" +
	    "AFP" + delim + "Bezugsnummer" + delim + "6" + delim + "Fehlercode3" + delim + "FehlerText3" 
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
