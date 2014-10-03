package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : TestNCTSUnloadingRemarksKidsToFssToKids<br>
 * Created    	: 09.13.2010<br>
 * Description	: Test for UnloadingRemarks KIDS to FSS and back to KIDS
 * 				  Input and Output Kids must be identical.
 *
 * @author Jude Eco 
 * @version 6.2.00
 */
public class TestNCTSUnloadingRemarksKidsToFssToKids extends TestKidsToFssToKidsFromCustomer {
	public TestNCTSUnloadingRemarksKidsToFssToKids(String name) {
        super(name);
        Utils.log("(TestNCTSUnloadingRemarksKidsToFssToKids TestNCTSUnloadingRemarksKidsToFssToKids) name = " + name);
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 8, 7, 8));
        expected.add(new Difference(10, 11, 10, 11));
        expected.add(new Difference(13, 13, 13, 13));
        expected.add(new Difference(37, Difference.NONE, 37, 39));
        expected.add(new Difference(39, Difference.NONE, 42, 43));
        expected.add(new Difference(40, Difference.NONE, 45, 66));
        expected.add(new Difference(44, Difference.NONE, 71, 72));
        expected.add(new Difference(47, Difference.NONE, 76, 78));
        expected.add(new Difference(51, Difference.NONE, 83, 83));
        expected.add(new Difference(54, Difference.NONE, 87, 87));
        expected.add(new Difference(56, Difference.NONE, 90, 91));
        expected.add(new Difference(66, Difference.NONE, 102, 102));
        expected.add(new Difference(76, Difference.NONE, 113, 113));
        expected.add(new Difference(81, Difference.NONE, 119, 124));
        expected.add(new Difference(86, Difference.NONE, 130, 177));
        expected.add(new Difference(88, Difference.NONE, 180, 182));
        expected.add(new Difference(95, Difference.NONE, 190, 190));
        expected.add(new Difference(97, Difference.NONE, 193, 202));
        
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
	
	protected void setInputFileName() {
		inputFileName	= "NCTSUnloadingRemarks_20100902_124652_soap.xml";
	}
	
	protected void setUp() throws Exception {
		folder	= "NCTS";
		super.setUp();
	}
}
