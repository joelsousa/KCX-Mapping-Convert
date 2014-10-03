package com.kewill.kcx.component.mapping.test;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestCompletionUids<br>
 * Erstellt     : 14.12.2008<br>
 * Description  : Conversion of a Completion message 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestCompletionUids extends TestUidsToUidsFromCustomer {

    public TestCompletionUids(String name) {
        super(name);
        Utils.log("(TestCompletionUids TestCompletionUids) name = " + name);
    }

    protected void setInputFileName() {
        inputFileName  = "QA_endtoend_4_EXPENT_U_200812.xml";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(0, 0,  0,  0));
        expected.add(new Difference(12,  12,  12, -1));
        errorMessage = "Number of differences is greater than two.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
