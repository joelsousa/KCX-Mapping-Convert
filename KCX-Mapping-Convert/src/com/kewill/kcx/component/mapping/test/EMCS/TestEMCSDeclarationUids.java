/*
 * Funktion    : TestEMCSDeclarationUids.java
 * Titel       :
 * Erstellt    : 04.05.2010
 * Author      : CSF GmbH / Christine Kron
 * Beschreibung: entspricht der TestExportDeclarationUids
 * Anmerkungen : 
 * Parameter   : 
 * R�ckgabe    : keine
 * Aufruf      : 
 *
 * �nderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.test.EMCS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestEMCSDeclarationUids<br>
 * Erstellt		: 02.10.2008<br>
 * Description	: Conversion of a EMCSDeclaration 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestEMCSDeclarationUids extends TestUidsToUidsFromCustomer {
    
    public TestEMCSDeclarationUids(String name) {
        super(name);
        Utils.log("(TestEMCSDeclarationUids TestEMCSDeclarationUids) name = " + name);
    }

    protected void setInputFileName() {
        inputFileName  = "DE01DE0121810000000000647986chk.XML";
        inputFileName  = "DE01DE0121810000000000653800.XML";
        
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
