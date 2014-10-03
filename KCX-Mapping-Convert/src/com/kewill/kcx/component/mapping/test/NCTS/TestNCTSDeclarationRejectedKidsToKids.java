package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSDeclarationRejectedKidsToKids<br>
 * Created		: 25.08.2010<br>
 * Description	: Conversion of an ArrivalRejection 
 *                from KIDS to KIDS.
 *                Original and converted KIDS-Messages must be identical.
 * 
 * @author	: Lassiter Caviles
 * @version 1.0.00
 */
public class TestNCTSDeclarationRejectedKidsToKids extends TestKidsToKcxFromCountry {

	public TestNCTSDeclarationRejectedKidsToKids(String name) {
		super(name);
		Utils.log("(TestNCTSDeclarationRejectedKidsToKids " +
				"TestNCTSDeclarationRejectedKidsToKids) name = " + name);
	}

	@Override
	protected void setFileNames() {
		inputFileName       = "NCTSDeclarationRejected_20100825_162413_soap.xml";
		referenceFileName   = "NCTSDeclarationRejected_20100825_162413_soap_reference.xml";
	}
	
	protected void runDiff(boolean split) {
	        ArrayList<Difference> expected = new ArrayList<Difference>();
	        errorMessage = "Number of differences is greater than zero.";
	        differencesExpected = expected.size();
	        expectedDifferences = expected.toArray();
	        super.runDiff(true);
	}
}
