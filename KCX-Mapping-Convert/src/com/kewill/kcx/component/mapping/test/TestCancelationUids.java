package com.kewill.kcx.component.mapping.test;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestCancelationUids<br>
 * Erstellt     : 14.12.2008<br>
 * Description  : Conversion of a Cancelation message 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestCancelationUids extends TestUidsToUidsFromCustomer {

    public TestCancelationUids(String name) {
        super(name);
        Utils.log("(TestCancelationUids TestCancelationUids) name = " + name);
    }

    protected void setInputFileName() {
        inputFileName  = "QA_21_EXPCAN_U_200812.xml";
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
