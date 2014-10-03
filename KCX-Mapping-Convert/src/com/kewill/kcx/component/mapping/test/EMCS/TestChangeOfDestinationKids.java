package com.kewill.kcx.component.mapping.test.EMCS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToUidsFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestChangeOfDestinationKids<br>
 * Erstellt		: 06.07.2010<br>
 * Description	: Conversion of a ExportDeclaration 
 *                from KIDS to KIDS to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestChangeOfDestinationKids extends TestKidsToUidsFromCountry {
    
    public TestChangeOfDestinationKids(String name) {
        super(name);
        Utils.log("(TestChangeOfDestinationUids TestChangeOfDestinationUids) name = " + name);
    }

    protected void setFileNames() {
    	inputFileName     = "DE01DE01218120100705174801227.xml";
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
