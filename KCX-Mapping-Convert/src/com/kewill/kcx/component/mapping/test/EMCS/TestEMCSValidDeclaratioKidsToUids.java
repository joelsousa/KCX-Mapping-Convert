package com.kewill.kcx.component.mapping.test.EMCS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToUidsFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestEMCSValidDeclarationKidsToUids<br>
 * Erstellt		: 15.12.2010<br>
 * Description	: Conversion of a EMCSValidDeclaration 
 *                from KIDS to KIDS to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestEMCSValidDeclaratioKidsToUids extends TestEMCSDeclarationKidsToKcx {
    
    public TestEMCSValidDeclaratioKidsToUids(String name) {
        super(name);
        Utils.log("(TestEMCSValidDeclaratioKidsToUids TestEMCSValidDeclaratioKidsToUids) name = " + name);
    }
    
    protected void setFileNames() {
//        inputFileName  = "DE01DE01215820101214123903529.xml";
//        inputFileName  = "DE01DE01346020110105105805189.xml";
        // inputFileName  = "emcs_null.xml";
        inputFileName  = "EMCSEventReport.xml";
        
    	referenceFileName = null;
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
