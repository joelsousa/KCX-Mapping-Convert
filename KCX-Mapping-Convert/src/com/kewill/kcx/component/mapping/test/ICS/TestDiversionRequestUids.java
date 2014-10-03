/*
 * Funktion    : TestChangeOfDestinationUids.java
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
package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestDiversionRequestUids<br>
 * Erstellt		: 12.06.2010<br>
 * Description	: Conversion of a DiversionRequest 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Pete T
 * @version 1.0.00
 */
public class TestDiversionRequestUids extends TestUidsToUidsFromCustomer {
    
    public TestDiversionRequestUids(String name) {
        super(name);
        Utils.log("(TestDiversionRequestUids TestDiversionRequestUids) name = " + name);
    }

    protected void setInputFileName() {
    	inputFileName     = "UIDSDiversionRequest_20100610_173107_soap.xml";
        encoding          = "UTF-8";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(21, Difference.NONE, 21, 21));
        expected.add(new Difference(22, Difference.NONE, 23, 23));
        expected.add(new Difference(25, Difference.NONE, 27, 28));
        expected.add(new Difference(26, 26, 30, 31));
        expected.add(new Difference(28, Difference.NONE, 33, 37));
        expected.add(new Difference(29, Difference.NONE, 39, 40));
        expected.add(new Difference(34, Difference.NONE, 46, 46));
        expected.add(new Difference(35, Difference.NONE, 48, 48));
        expected.add(new Difference(49, Difference.NONE, 63, 70));

        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
