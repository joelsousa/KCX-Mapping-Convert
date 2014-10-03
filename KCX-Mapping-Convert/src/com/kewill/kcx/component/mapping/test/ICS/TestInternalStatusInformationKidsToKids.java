package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestDiversionRequestRejectedKidsToKids<br>
 * Created		: 12.11.2010
 * Description	: Conversion of InternalStatusInformation from KIDS to KIDS.
 * 
 * @author	: Christine Kron
 * @version 1.0.00
 *
 */
public class TestInternalStatusInformationKidsToKids extends TestKidsToKcxFromCountry {

	public TestInternalStatusInformationKidsToKids(String name) {
		super(name);
		Utils.log("(TestInternalStatusInformationKidsToKids" + 
				"TestInternalStatusInformationKidsToKids) name = " + name);
	}

	@Override
	protected void setFileNames() {
		inputFileName		= "ICSInternalStatusInformation_20101112_121212.xml";
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
