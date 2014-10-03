package com.kewill.kcx.component.mapping.test;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestAdditionalInformationUids<br>
 * Erstellt     : 14.12.2008<br>
 * Description  : Conversion of a AdditionalInformation message 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestAdditionalInformationUids extends TestUidsToUidsFromCustomer {
    
    public TestAdditionalInformationUids(String name) {
        super(name);
        Utils.log("(TestAdditionalInformationUids TestAdditionalInformationUids) name = " + name);
    }

    protected void setInputFileName() {
        inputFileName  = "DE01DE01215820080923121700038.xml";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(0, 0,  0,  0));
        expected.add(new Difference(7, 7,  7, -1));
        expected.add(new Difference(13,  13,  12, -1));
        errorMessage = "Number of differences is greater than three.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }

}
