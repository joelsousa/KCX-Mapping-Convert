package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : TestNCTSMRNAllocatedKidsToFssToKids<br>
 * Created    	: 09.09.2010<br>
 * Description	: Test for NCTSMRNAllocated KIDS to FSS and back to KIDS
 * 				  Input and Output Kids must be identical.
 *
 * @author Lassiter Caviles 
 * @version 6.2.00
 */

public class TestNCTSMRNAllocatedKidsToFssToKids extends
		TestKidsToFssToKidsFromCustomer {

	public TestNCTSMRNAllocatedKidsToFssToKids(String name) {
		super(name);
		Utils
				.log("(TestNCTSMRNAllocatedKidsToFssToKids TestNCTSMRNAllocatedKidsToFssToKids) name = "
						+ name);
	}

	protected void runDiff(boolean split) {
		ArrayList<Difference> expected = new ArrayList<Difference>();

		// Expected difference: Different timestamps in the flow rate (VOR)
		// expected.add(new Difference(0, 0, 0, 0));
		
		expected.add(new Difference(7, 8, 7, 8));
		expected.add(new Difference(10, 11, 10, 11));
		expected.add(new Difference(13, 13, 13, 13));
		expected.add(new Difference(33, 33, 33, 33));
		expected.add(new Difference(37, Difference.NONE, 37, 55));
		expected.add(new Difference(41, Difference.NONE, 60, 61));
		expected.add(new Difference(42, Difference.NONE, 63, 64));
		expected.add(new Difference(43, 43, 66, 66));
		errorMessage = "Number of differences is greater than one.";
		differencesExpected = expected.size();
		expectedDifferences = expected.toArray();
		super.runDiff(true);
	}

	@Override
	protected void setInputFileName() {
		inputFileName = "NCTSMRNAllocated_20100825_162234_soap.xml";
	}
	@Override
	protected void setUp() throws Exception {
		//If folder is not set, test files will be placed in "data/fss/in/".
		folder = "NCTS";
		super.setUp();
	}
}
