/*
 * Funktion    : TestDiversionRequestKidsToKids.java
 * Titel       :
 * Erstellt    : 14.06.2010
 * Author      : Pete T
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

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestDiversionRequestKidsToKids<br>
 * Erstellt		: 14.06.2010<br>
 * Description	: Conversion of a DiversionRequest 
 *                from KIDS to KIDS. 
 * 
 * @author Pete T
 * @version 1.0.00
 */
public class TestDiversionRequestKidsToKids extends TestKidsToKcxFromCountry {
    
    public TestDiversionRequestKidsToKids(String name) {
        super(name);
        Utils.log("(TestDiversionRequestKidsToKids) name = " + name);
    }

    protected void setFileNames() {
        inputFileName       = "ICSDiversionRequest_20100612_161152_soap.xml";
        referenceFileName   = "ICSDiversionRequest_20100612_161152_soap_reference.xml";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
