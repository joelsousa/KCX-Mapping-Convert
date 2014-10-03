/*
 * Function    : FssToKidsTESTAUP.java
 * Titel       :
 * Date        : 10.10.2008
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
 * Modul		: FssToKidsTESTAUP<br>
 * Erstellt		: 10.10.2008<br>
 * Beschreibung	: Testklasse zur Konvertierung von FSS (AUP) nach KIDS.
 * 
 * @author houdek
 * @version 1.0.00
 */
public class FssToKidsTESTAUP extends FssToKidsExtern {

	public static void main(String[] args) {
    	char delim = 0x1d;
    	FssToKidsExtern fssToKids = new FssToKidsExtern();
    	TsVOR tsVOR = new TsVOR("A");
    	tsVOR.setMan("CSF");
    	tsVOR.setNl("TEST");
    	tsVOR.setModul("AUP");
    	tsVOR.setNatyp("AUP");
    	tsVOR.setMsgid("4711");
    	String payload = tsVOR.teilsatzBilden() + "\n" +
    	"AUP" + delim + "Bezugsnummer" + delim + "20081010" + delim + "20081111" + delim + "\n";
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
