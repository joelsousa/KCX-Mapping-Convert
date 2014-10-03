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
package com.kewill.kcx.component.mapping.test.EMCS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestChangeOfDestinationUids<br>
 * Erstellt		: 02.10.2008<br>
 * Description	: Conversion of a ExportDeclaration 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestChangeOfDestinationUids extends TestUidsToKidsFromCustomer {
    
    public TestChangeOfDestinationUids(String name) {
        super(name);
        Utils.log("(TestChangeOfDestinationUids TestChangeOfDestinationUids) name = " + name);
    }

    protected void setFileNames() {
    	inputFileName     = "DE01DE01215820100629160700000001.xml";
    	referenceFileName = null;
        encoding          = "UTF-8";
    }
    
//    public String otherToKids(BufferedReader br) throws Exception {
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(br);
//        UidsToKidsExtern uidsToKidsExtern = new UidsToKidsExtern();
//        return uidsToKidsExtern.readUids(parser, "No Mule call", "UTF-8", EDirections.CustomerToCountry);
//    }
    
//    public String kidsToOther(String payload) {
        // KCX-Envelope enfernen wir zentral in TestUidsToKidsFromCustomer gemacht
//    }

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
