package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : FssToKidsTESTSH.java
 * Titel       :
 * Date        : 22.09.2008
 * Author      : Kewill CSF / Sven Heise
 * Description : 
 * Parameters  : 

VOR|CSF |MAX|ZB |STI|A|20080227|093256|CBEG206858          |05.00| | | | | |
STI|2_3_1_1_FCDEC1_NVA                 |000|ATC400000260220085865|ATA000003380220085865|20080227|0932|68|
NAC|CSF |MAX|ZB |STI|A|00001|00003|

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

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
 * Modul		: FssToKidsTESTSH<br>
 * Erstellt		: 22.09.2008<br>
 * Beschreibung	: testklasse zur Konvertierung von FSS nach KIDS.
 * 
 * @author heise
 * @version 1.0.00
 */
public class FssToKidsTESTSH extends FssToKidsExtern {

	public static void main(String[] args) {
    	char delim = 0x1d;
    	FssToKidsExtern fssToKids = new FssToKidsExtern();
    	TsVOR tsVOR = new TsVOR("A");
    	tsVOR.setMan("CSF");
    	tsVOR.setNl("TEST");
    	tsVOR.setModul("ZB");
    	tsVOR.setNatyp("STI");
    	tsVOR.setMsgid("4711");
    	String payload = tsVOR.teilsatzBilden() + "\n" +
    	"STI" + delim + "Bezugsnummer" + delim + "000" + delim + "Registriernummer" + delim +
    	"" + delim + "20080922" + delim + "1234" + delim + "42" + delim + "\n" +
    	"ANR" + delim + "Bezugsnummer" + delim + "Auftragsnummer" + delim + "\n";
    	//"Arbeitsnummer" + delim + "20080922" + delim + "1234" + delim + "42" + delim + "\n";

/*    	fsssti_stikey	STR(03)	Teilsatzschlüssel	STI	M
    	fsssti_beznr	STR(35)	Bezugsnummer		M
    	fsssti_korant	NUM(03)	Korrekturnummer		M
    	fsssti_regnr	STR(21)	Registriernummer		K
    	fsssti_arbnr	STR(21)	Arbeitsnummer		K
    	fsssti_datum	STR(08)	Datum der Statusänderung	Format JJJJMMTT	M
    	fsssti_zeit	NUM(04)	Uhrzeit der Statusänderung	Format HHMM	M
    	fsssti_status	NUM(02)	Neuer Statuswert der Zollanmeldung	s.u.	M
*/    	
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(is);
        
        String xml = "";
		try {
			xml = fssToKids.readFss(br, "TestAuditId", "testMessageID", EDirections.CountryToCustomer);
		} catch (FssException e) {
			e.printStackTrace();
		}
        Utils.log("(FssToKids main) Converted Message = \n" + xml);
	}

}
