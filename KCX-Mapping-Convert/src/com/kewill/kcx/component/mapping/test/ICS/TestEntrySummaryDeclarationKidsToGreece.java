package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToGreeceFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationKidsToGreece<br>
 * Date Started	: July 19, 2011<br>
 * Description	: Conversion of a EntrySummaryDeclaration 
 *                from KIDS to Greece.
 * @author schmidt
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationKidsToGreece extends TestKidsToGreeceFromCustomer {
	
	public TestEntrySummaryDeclarationKidsToGreece(String name) {
		super(name);
		Utils.log("(TestEntrySummaryDeclarationKidsToGreece TestEntrySummaryDeclarationKidsToGreece) name = " +
				name);
	}
	
	 protected void setFileNames() {
	        inputFileName       = "DE_CSF__201107019120701_000000001_KIDS_GR.xml";
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
