/*
 * Funktion    : TestExportDeclarationUids.java
 * Titel       :
 * Erstellt    : 02.10.2008
 * Author      : CSF GmbH / schmidt
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
package com.kewill.kcx.component.mapping.test;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestExportDeclarationUids<br>
 * Erstellt		: 02.10.2008<br>
 * Description	: Conversion of a ExportDeclaration 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestExportDeclarationUids extends TestUidsToUidsFromCustomer {
    
    public TestExportDeclarationUids(String name) {
        super(name);
        Utils.log("(TestExportDeclarationUids TestExportDeclarationUids) name = " + name);
    }

    protected void setInputFileName() {
//      inputFileName  = "expdat2.xml";
//        inputFileName  = "DE01DE0122680000000000724013.xml";
//        inputFileName  = "DE01DE0122720000000002922004.xml";
//        inputFileName  = "uids2922094.xml";
//        inputFileName  = "uids697190.xml";
//        inputFileName  = "DE01DE01215820080930123900010.xml";
//        inputFileName  = "rn_1201_a__dexpda.xml";
//        inputFileName  = "rn_0100_z__dexpda.xml";
//    	inputFileName  = "rn_codemapping_dexpda.xml";
//        inputFileName  = "DE01DE0121860000000000642997.xml";
    	inputFileName  = "DE01DE0136140000000000073299.xml";
        
//        encoding = "ISO-8859-1";
        encoding = "UTF-8";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(0, 0,  0,  0));
        expected.add(new Difference(3,  3,  3, 3));
        expected.add(new Difference(7,  7,  7, 7));
        errorMessage = "Number of differences is greater than three.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
