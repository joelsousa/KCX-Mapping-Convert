package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module       : TestNCTSUnloadingRemarksRejectionKidsToFssToKids<br>
 * Created    	: 09.09.2010<br>
 * Description	: Test for UnloadingRemarksRejection KIDS to FSS and back to KIDS
 * 				  Input and Output Kids must be identical.
 *
 * @author Jude Eco 
 * @version 6.2.00
 */
public class TestNCTSUnloadingRemarksRejectionKidsToFssToKids extends TestKidsToFssToKidsFromCustomer {
	public TestNCTSUnloadingRemarksRejectionKidsToFssToKids(String name) {
        super(name);
        Utils.log("(TestNCTSUnloadingRemarksRejectionKidsToFssToKids " +
        		"TestNCTSUnloadingRemarksRejectionKidsToFssToKids) name = " + name);
    }
	
	protected void setInputFileName() {
		inputFileName	= "NCTSUnloadingRemarksRejection_20100901_101758_soap.xml";
	}
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 8, 7, 8));
        expected.add(new Difference(10, 11, 10, 11));
        expected.add(new Difference(13, 13, 13, 13));
        expected.add(new Difference(38, Difference.NONE, 38, 38));
        expected.add(new Difference(39, Difference.NONE, 40, 40));
        expected.add(new Difference(42, Difference.NONE, 44, 44));
        expected.add(new Difference(44, Difference.NONE, 47, 47));
        expected.add(new Difference(47, Difference.NONE, 51, 51));
        
        
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
	
	@Override
	protected void setUp() throws Exception {
		//If folder is not set, test files will be placed in "data/fss/in/".
		folder = "NCTS";
		super.setUp();
	}
}
