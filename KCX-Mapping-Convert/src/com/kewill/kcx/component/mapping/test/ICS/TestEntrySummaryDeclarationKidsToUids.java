package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationKidsToUids<br>
 * Date Started	: July 26, 2010<br>
 * Description	: Conversion of a EntrySummaryDeclaration 
 *                from KIDS to KIDS.
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationKidsToUids extends TestKidsToUidsFromCustomer {
	
	public TestEntrySummaryDeclarationKidsToUids(String name) {
		super(name);
		Utils.log("(TestEntrySummaryDeclarationKidsToUids TestEntrySummaryDeclarationKidsToUids) name = " +
				name);
	}
	
	 protected void setFileNames() {
	        //inputFileName       = "ICSEntrySummaryDeclaration_toPL.xml";
	        //inputFileName       = "AE_EK_TST_201012290930000_QA001.xml";
            inputFileName       = "ICSEntrySummaryDeclaration_toCY.xml";
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
