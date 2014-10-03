package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/*
 * Funktion    : TestNctsDeclarationKidsToFss.java
 * Titel       :
 * Erstellt    : 07.09.2010
 * Author      : Kewill / Christine Kron
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */


/**
 * Modul       : TestNctsDeclarationKidsToFss.java
 * Erstellt    : 07.09.2010
 * Beschreibung: Conversion of an NCTSDeclaration/VAN  von KIDS to KIDS to FSS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author Kron 
 * @version 1.0.00
 */
public class TestNctsDeclarationKidsToFss extends TestKidsToFssFromCustomer {

    public TestNctsDeclarationKidsToFss(String name) {
        super(name);
        Utils.log("(TestNctsDeclarationKidsToFss TestNctsDeclarationKidsToFss) name = " + name);
    }

    protected void setFileNames() {
    	// inputFileName       = "JX66591768P030009bwck_kids.xml";
    	// inputFileName       = "CH01DE01_IRU_CHG20110324100530000000002_kids.xml";
    	// inputFileName       = "IRUConsignorDoppelt_kids.xml";
    	inputFileName		= "DE01DE01308720110519105708000000108_kids.xml";
    	inputFileName = "ncts-codes-2.xml";
    	inputFileName = "ncts_decl_vag.xml";
    	inputFileName = "ausfuhrerstattung-expdat-v7_kids.xml";
        referenceFileName   = null;
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        // 1. erwartete Differenz: Unterschiedlicher Zeitstempel im Vorlaufsatz (VOR)
        expected.add(new Difference(0, 0,  0,  0));
        
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
