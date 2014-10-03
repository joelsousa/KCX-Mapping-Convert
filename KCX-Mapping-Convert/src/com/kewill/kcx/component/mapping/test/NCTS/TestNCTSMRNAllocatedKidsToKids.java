package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSMRNAllocatedKidsToKids<br>
 * Created		: 25.08.2010<br>
 * Description	: Conversion of an NCTSMRNAllocated 
 *                from KIDS to KIDS.
 *                Original and converted KIDS-Messages must be identical.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */
public class TestNCTSMRNAllocatedKidsToKids extends TestKidsToKcxFromCountry {

	public TestNCTSMRNAllocatedKidsToKids(String name) {
		super(name);
		Utils.log("(TestNCTSMRNAllocatedKidsToKids TestNCTSMRNAllocatedKidsToKids) name = " + name);
	}

	@Override
	protected void setFileNames() {
		inputFileName = "NCTSMRNAllocated_20100825_162234_soap.xml";
		referenceFileName = "NCTSMRNAllocated_20100825_162234_soap_reference.xml";
	}

	protected void runDiff(boolean split) {
		ArrayList<Difference> expected = new ArrayList<Difference>();
		errorMessage = "Number of differences is greater than zero.";
		differencesExpected = expected.size();
		expectedDifferences = expected.toArray();
		super.runDiff(true);
	}

}
