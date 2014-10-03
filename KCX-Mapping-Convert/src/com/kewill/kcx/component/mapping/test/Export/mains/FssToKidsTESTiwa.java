/*
 * Function    : FssToKidsTEST.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF / iwaniuk
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
 * Modul		: FssToKidsTESTiwa<br>
 * Erstellt		: 27.08.2008<br>
 * Beschreibung	: Testklasse zum Konvertieren von FSS nach KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class FssToKidsTESTiwa extends FssToKidsExtern {

	public static void main(String[] args) {
    	char delim = 0x1d;
    	FssToKidsExtern fssToKids = new FssToKidsExtern();
    	TsVOR tsVOR = new TsVOR("A");
    	tsVOR.setMan("CSF");
    	tsVOR.setNl("TEST");
    	tsVOR.setModul("AES");
    	tsVOR.setNatyp("ADP");   //hier beim Testen die Nachricht-Kürzel eintragen
    	tsVOR.setMsgid("4711");
    
    	String payload = tsVOR.teilsatzBilden() + "\n" +
    	"DAT" + delim + "Bezugsnummer" + delim + "kuatnr-localReferenzNumber" + delim + "MRN-documentRefNr" + delim + 
    	    "kzart-typeOfRelease" + delim + "expdst-officeOfExport" + delim + "eamdst-officeOfAddDecl" + delim + 
    	    "kdnran-declarant-customerID" + delim + "tinan-Declarant-tnr" + delim + 
    	    // V6 "dtzoan" + delim +
    	    "etnan-declarant-etn" + delim + "kdnrem-consignee-customerID" + delim + "tinem-consignee-tnr" + delim + 
    	    "kdnrsp-shipper-customerID" + delim + "etnsp-shipper-etn" + delim + 
    	    "quelkz-Procedure-previous" + delim + "sb" + delim + "vekan" + "\n" +    	
    	"EDA" + delim + "Bezugsnummer" + delim + "bewnr-authorisationID" + delim + 
    	    "ldocde-PlaceOfLoading-Code" + delim + "beostr-PlaceOfLoading-Address-Str" + delim + 
    	    "beoort--PlaceOfLoading-Address-City" + delim + "beoplz-PlaceOfLoading-Address-poco" + delim + 
    	    "beozus-PlaceOfLoading-Address-Add" + delim + "artaus-TypeOfDecl-regionCode" + delim + 
    	    "artueb-TypeOfDecl-procedureCode" + delim + "ldbe-destinationCountry" + delim + 
    	    "conkz-ContIndicator" + delim + "fregnr-ExternalRegNr" + delim + "flcde-AirportCode" + delim + 
    	    "extdst-officeOfExit" + delim + "kzanau-ParticComb" + delim + "kdnrau-Exporter-CustomerID" + delim + 
    	    "tinau-Exporter-tnr" + delim + "dtzoau--Exporter-CustomsID" + delim + 
    	    "dtzoan-Declarant-CustomsID" + delim + "kdnrva-DeclarantRepresent" + delim + 
    	    "tinva-DeclarantRepresent-tnr" + delim + "dtzova-DeclarantRepresent-CostomsID" + delim + 
    	    "etnva-DeclarantRepresent-etn" + delim + "kndrsu-Subcontractor-CustomerID" + delim + 
    	    "tinsu-Subcontractor-tnr" + delim + "dtzosu-Subcontractor-CustomsID" + delim + 
    	    "etnsu-Subcontractor-etn" + delim + "200810280000" + delim + "200810290000" + delim + 
    	    "200810300000" + delim + "verm-Remark" + delim + "bcktax-ExportRefund" + delim + 
    	    "kztyd-HeaderExt-flag" + delim + "kzstock-HeaderExt-stock" + delim + "anzve-Seals-Qu" + delim + 
    	    "vsart-Seals-type" + delim + "\n" +
    	"DAE" + delim + "Bezugsnummer" + delim + "anzpos-ItemsQuantity" + delim + "gsroh-Gross" + delim + 
    	    "anzcol-PackagesQuantity" + delim + "kzmin-ShortageIndicator" + "\n" +
    	"ASP" + delim + "Bezugsnummer" + delim + "200809220000" + delim + "200809230000" + delim + 
    	    "200809241112" + delim + "20080925" + delim + "200809261010" + delim + "200809271111" + delim + 
    	    "200809280000" + delim + "200809290000" + delim + "200809301111" + delim + "\n" +
    	"AMP" + delim + "Bezugsnummer" + delim + "pdfnam-PDF-Nmae" + delim + "pdfpfd-PDF-Directiry" + delim + 
    	"subdir-PDF-Subdir" + delim + "\n" +
    	//"SAS"+ delim + "Bezugsnummer"+ delim + "bfgkzw" + delim + "\n" +
    	//"ABF"+ delim + "Bezugsnummer"+ delim + "ldbf " + delim + "\n" +
    	//"ATK"+ delim + "Bezugsnummer"+ delim + "bfarta" + delim + "\n" +
    	"ADR" + delim + "Bezugsnummer" + delim + "0" + delim + "1" + delim + "1-name1" + delim + "1-name2" + delim + 
    	"1-name3" + delim + "1-str" + delim + "1-ort" + delim + "1-plz" + delim + "1-land" + delim + "\n" +
    	"ADR" + delim + "Bezugsnummer" + delim + "0" + delim + "2" + delim + "2-name1" + delim + "2-name2" + delim + 
    	    "2-name3" + delim + "\n" +
    	"ADR" + delim + "Bezugsnummer" + delim + "0" + delim + "3" + delim + "3-name1" + delim + "3-name2" + delim + 
    	    "3-name3" + delim + "\n" +
    	"ADR" + delim + "Bezugsnummer" + delim + "0" + delim + "4" + delim + "4-name1" + delim + "4-name2" + delim + 
    	    "4-name3" + delim + "\n" +
    	"ADR" + delim + "Bezugsnummer" + delim + "0" + delim + "5" + delim + "5-name1" + delim + "5-name2" + delim + 
    	    "5-name3" + delim + "\n" +   
    	"EAM" + delim + "Bezugsnummer" + delim + "200810110000" + delim + "bfvkzi-Tr-Inand-type" + delim + 
    	    "bfvkzg-Tr-Departure-type" + delim + "bfartg-Tr-Departure-mode" + delim + 
    	    "bfkzg-Tr-Departure-identity" + delim + "bfnatg-Tr-Departure-nat" + delim +
    	    "gesart-Transaktion-type" + delim + "gesprs-Transaktion-amount" + delim + 
    	    "geswrg-Transaktion-curr" + delim + "libart-Incoterms-code" + delim + 
    	    "libinc-Incoterms-description" + delim + "libort-Incoterms-city" + delim + "\n" +
    	"AVS" + delim + "Bezugsnummer" + delim + "seal1" + delim + "\n" +
    	"AVS" + delim + "Bezugsnummer" + delim + "seal2" + delim + "\n" +
    	"AVS" + delim + "Bezugsnummer" + delim + "seal3" + delim + "\n" +
    	"AED" + delim + "Bezugsnummer" + delim + "aed-nr" + delim +  "\n" +
    	"APO" + delim + "Bezugsnummer" + delim + "1" + delim + "artnr" + delim + "tnr-ccKN8" + delim + 
    	    "tnrtrc-ccTARIC" + delim + "tnrzu1-ccFAdd" + delim + "tnrzu2-ccSAdd" + delim + 
    	    "tnrnat-ccNat" + delim + "wbsch-description" + delim + "APO-fregnr-ExtRegNr" + delim + 
    	    "verm-Remark" + delim + "eigm-Net" + delim + "rohmas-Gross" + delim + "anmvrf-Procedure-declaered" + delim +
    	    "prevrf-Procedure-prev" + delim + "natvfr-Procedure-add" + delim + "ubland-Origin" + delim + 
    	    "wmahst-StatistQua" + delim + "ahwert-StatisValue" + delim + "vptyp-PrevDoc-type" + delim + "\n" +
    	//"SAP"+ delim + "Bezugsnummer"+ delim + "sap-bfgkzw" + delim + "\n" +
    	"ADR" + delim + "Bezugsnummer" + delim + "1" + delim + "2" + delim + "consignee" + delim + "\n" +
    	//"ATP"+ delim + "Bezugsnummer"+ delim + "pos1" + delim + "meaest" + delim + "\n" +
    	//"ATI"+ delim + "Bezugsnummer"+ delim + "pos1" + delim + "ati-lfdnr" + delim + "\n" +
    	"ACO" + delim + "Bezugsnummer" + delim + "1" + delim + "lfd-Packaging-Nr" + delim + 
    	"colanz-P-Qu" + delim + "colart-P-type" + delim + "colzch-P-MuNr" + delim + "\n" +
    	"NVE" + delim + "Bezugsnummer" + delim + "1" + delim + "nve" + delim + "nxtnve" + delim + "\n" +
    	"ACN" + delim + "Bezugsnummer" + delim + "1" + delim + "connr-1" + delim + "\n" +
    	"ACN" + delim + "Bezugsnummer" + delim + "1" + delim + "connr-2" + delim + "\n" +
    	"AZV" + delim + "Bezugsnummer" + delim + "1" + delim + "azvref-PrevDoc-ref" + delim + 
    	    "azvzus-PrevDoc-Doc-Remark" + delim + "\n" +
    	//"BZL"+ delim + "Bezugsnummer"+ delim + "pos1" + delim + "bzl" + delim + "\n" +
    	//"BAV"+ delim + "Bezugsnummer"+ delim + "pos1" + delim + "bav" + delim + "\n" +
    	"AED" + delim + "Bezugsnummer" + delim + "1" + delim + "untqar-ProdDoc-Doc-type" + delim + 
    	    "untart-ProdDoc-Doc-cat" + delim + "untnr-ProdDoc-Doc-ref" + delim + 
    	    "untzus-ProdDoc-Doc-remark" + delim + "20081027" + delim + "20081028" + delim + "\n" +
    	"APO" + delim + "Bezugsnummer" + delim + "2" + delim + " " + delim + " " + delim + "\n";
    	/*
    	String payload = tsVOR.teilsatzBilden() + "\n" +
    	"CAA"+ delim + "Bezugsnummer"+ delim + "dklart" + delim + "dksp " + delim + 
    	"dkzo " + delim + "200810211245" + delim +"cdrev" + delim + "cdfrei" +  "\n";
    	 */   	
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
