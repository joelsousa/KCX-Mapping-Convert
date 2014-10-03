package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSArrivalNotificationKidsToKids<br>
 * Created		: 12.06.2010<br>
 * Description	: Conversion of an NCTSArrivalNotification 
 *                from KIDS to KIDS.
 *                Original and converted KIDS-Messages must be identical. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class TestNCTSArrivalNotificationKidsToKids extends TestKidsToKcxFromCountry {

	public TestNCTSArrivalNotificationKidsToKids(String name) {
		super(name);
		Utils.log("(TestNCTSArrivalNotificationKidsToKids " +
				"TestNCTSArrivalNotificationKidsToKids) name = " + name);
	}
	
	protected void setFileNames() {
        inputFileName       = "NCTSArrivalNotificationKK_20100823_145017_soap.xml";
        referenceFileName   = "NCTSArrivalNotificationKK_20100823_145017_soap_reference.xml";
    }
	
	 protected void runDiff(boolean split) {
	        ArrayList<Difference> expected = new ArrayList<Difference>();
	        errorMessage = "Number of differences is greater than zero.";
	        differencesExpected = expected.size();
	        expectedDifferences = expected.toArray();
	        super.runDiff(true);
	    }

}
