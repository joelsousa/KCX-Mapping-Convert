package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestDiversionRequestRejectedUids<br>
 * Created		: 09.11.2010
 * Description	: Conversion of DiversionRequestRejected from KIDS to UIDS.
 * 
 * @author	: Michelle Bauza
 * @version 1.0.00
 *
 */
public class TestDiversionRequestRejectedUids extends TestUidsToUidsFromCustomer {

	public TestDiversionRequestRejectedUids(String name) {
		super(name);
		Utils.log("(TestDiversionRequestRejectedUids TestDiversionRequestRejectedUids) name = " + name);
	}

	@Override
	protected void setInputFileName() {
		inputFileName	= "ICSDiversionRequestRejected_20101110_104504.xml";
		encoding		= "UTF-8";
	}
	
	protected void runDiff(boolean split) {
		ArrayList<Difference> expected	= new ArrayList<Difference>();
		differencesExpected	= expected.size();
		expectedDifferences	= expected.toArray();
		errorMessage		= String.format("Number of differences is greater than %d.", 
				differencesExpected);
		super.runDiff(true);
	}

}
