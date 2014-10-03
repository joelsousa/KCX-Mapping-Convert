package com.kewill.kcx.component.mapping.test;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestCancellationFss<br>
 * Erstellt     : 17.12.2008<br>
 * Description  : Conversion of a Cancellation message 
 *                from UIDS to KIDS and then from KIDS to FSS.
 *                The output FSS-Messages must be identical to a reference file.
 *                The reference file cannot be generated and must be provided manually. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestCancellationFss extends TestUidsToFssFromCustomer {
    
    public TestCancellationFss(String name) {
        super(name);
        Utils.log("(TestCancellationFss TestCancellationFss) name = " + name);
    }

    protected void setFileNames() {
        inputFileName  = "dummy.xml";
        referenceFileName = "dummy.fss"; 
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }

}
