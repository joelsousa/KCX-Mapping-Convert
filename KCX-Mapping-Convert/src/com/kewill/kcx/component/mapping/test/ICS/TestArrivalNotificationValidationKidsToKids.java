package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestArrivalNotificationValidationKidsToKids<br>
 * Date Started	: 2010.08.25<br>
 * Description	: Conversion of a ArrivalNotificationValidation 
 *                from KIDS to KIDS.
 * @author Frederick Topico
 * @version 1.0.00
 */
public class TestArrivalNotificationValidationKidsToKids extends TestKidsToKcxFromCountry {

	public TestArrivalNotificationValidationKidsToKids(String name) {
		super(name);
		Utils.log(
		"(TestArrivalNotificationValidationKidsToKids TestArrivalNotificationValidationKidsToKids) name = " +
			name);
	}
	
	protected void setFileNames() {
        inputFileName       = "ICSArrivalNotificationValidation_20100825_170222_soap.xml";
        referenceFileName   = null;
    }
	
	 protected void runDiff(boolean split) {
	        ArrayList<Difference> expected = new ArrayList<Difference>();
	        errorMessage = "Number of differences is greater than zero.";
	        differencesExpected = expected.size();
	        expectedDifferences = expected.toArray();
	        super.runDiff(true);
	    }
}
