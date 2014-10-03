package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationUids<br>
 * Created		: 2010.10.29<br>
 * Description	: Conversion of a ICSDeclaration
 * 				  from UIDS to KIDS ICSEntrySummaryDeclaration and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Christine Kron
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationUids extends TestUidsToUidsFromCustomer {
	
	public TestEntrySummaryDeclarationUids(String name) {
		super(name);
        Utils.log("(TestEntrySummaryDeclarationUids TestEntrySummaryDeclarationUids) name = " + name);		
	}

	protected void setInputFileName() {
    	// inputFileName     = "AA01DE01215820101027145800001.xml";
//    	inputFileName		= "DE01PL01_PL01_SKG_TST_20101208110112000000031.xml";
    	// inputFileName      = "DE01SE01_SE01_CSF_TST_20101221125412000000001.xml";
    	inputFileName      = "CONFIRM_UIDS.xml";
    	
    	
        encoding          = "UTF-8";
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(22, Difference.NONE, 22, 22));
        expected.add(new Difference(26, Difference.NONE, 27, 27));
        expected.add(new Difference(29, Difference.NONE, 31, 32));
        expected.add(new Difference(31, 32, 35, 36));
        expected.add(new Difference(34, Difference.NONE, 38, 42));
        expected.add(new Difference(36, Difference.NONE, 45, 46));
        expected.add(new Difference(41, Difference.NONE, 52, 52));
        expected.add(new Difference(42, Difference.NONE, 54, 54));
        expected.add(new Difference(43, Difference.NONE, 56, 63));
        expected.add(new Difference(56, Difference.NONE, 77, 77));
        expected.add(new Difference(58, Difference.NONE, 80, 80));
        expected.add(new Difference(64, Difference.NONE, 87, 88));
        expected.add(new Difference(67, Difference.NONE, 92, 93));
        expected.add(new Difference(68, Difference.NONE, 95, 98));
        expected.add(new Difference(73, Difference.NONE, 104, 105));
        expected.add(new Difference(76, Difference.NONE, 109, 110));
        expected.add(new Difference(77, Difference.NONE, 112, 115));
        expected.add(new Difference(81, Difference.NONE, 120, 120));
        expected.add(new Difference(83, Difference.NONE, 123, 123));
        expected.add(new Difference(89, Difference.NONE, 130, 131));
        expected.add(new Difference(90, Difference.NONE, 133, 136));

        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
