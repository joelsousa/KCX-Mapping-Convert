package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSUnloadingRemarksKidsToKids<br>
 * Created		: 09.02.2010<br>
 * Description	: Conversion of an NCTSUnloadingRemarks
 *                from KIDS to KIDS.
 *                Original and converted KIDS-Messages must be identical. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class TestNCTSUnloadingRemarksKidsToKids extends TestKidsToKcxFromCountry {
	public TestNCTSUnloadingRemarksKidsToKids(String name) {
		super(name);
		Utils.log("(TestNCTSUnloadingRemarksKidsToKids " +
				"TestNCTSUnloadingRemarksKidsToKids) name = " + name);
	}
	
	protected void setFileNames() {
        inputFileName       = "NCTSUnloadingRemarksKK_20100902_124652_soap.xml";
        referenceFileName   = "NCTSUnloadingRemarksKK_20100902_124652_soap_reference.xml";
    }
	
	 protected void runDiff(boolean split) {
	        ArrayList<Difference> expected = new ArrayList<Difference>();
	        errorMessage = "Number of differences is greater than zero.";
	        differencesExpected = expected.size();
	        expectedDifferences = expected.toArray();
	        super.runDiff(true);
	    }
}
