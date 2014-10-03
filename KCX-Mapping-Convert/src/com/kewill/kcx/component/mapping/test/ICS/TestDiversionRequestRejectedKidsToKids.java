package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestDiversionRequestRejectedKidsToKids<br>
 * Created		: 09.11.2010
 * Description	: Conversion of DiversionRequestRejected from KIDS to KIDS.
 * 
 * @author	: Michelle Bauza
 * @version 1.0.00
 *
 */
public class TestDiversionRequestRejectedKidsToKids extends TestKidsToKcxFromCountry {

	public TestDiversionRequestRejectedKidsToKids(String name) {
		super(name);
		Utils.log("(TestDiversionRequestRejectedKidsToKids" + 
				"TestDiversionRequestRejectedKidsToKids) name = " + name);
	}

	@Override
	protected void setFileNames() {
		inputFileName		= "ICSDiversionRequestRejected_20101110_104504.xml";
		referenceFileName	= null;
	}
	
	protected void runDiffDifference(boolean split) {
		ArrayList<Difference> expected	= new ArrayList<Difference>();
		errorMessage		= "Number of differences is greater than zero.";
		differencesExpected	= expected.size();
		expectedDifferences	= expected.toArray();
		super.runDiff(true);
	}

}
