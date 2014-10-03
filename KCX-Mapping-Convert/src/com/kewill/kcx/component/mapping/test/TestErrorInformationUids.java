package com.kewill.kcx.component.mapping.test;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestErrorInformationUids<br>
 * Erstellt     : 14.12.2008<br>
 * Description  : Conversion of a ErrorInformation message 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestErrorInformationUids extends TestUidsToUidsFromCustomer {

    public TestErrorInformationUids(String name) {
        super(name);
        Utils.log("(TestErrorInformationUids TestErrorInformationUids) name = " + name);
    }

    protected void setInputFileName() {
        inputFileName  = "DE01DE01218520081202094402772.xml";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(0, 0,  0,  0));
        expected.add(new Difference(7,  7,  7, 7));
        errorMessage = "Number of differences is greater than two.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
