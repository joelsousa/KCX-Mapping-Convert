package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToCyprusFromCustomer;
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
public class TestEntrySummaryDeclarationKidsToCyprus extends TestKidsToCyprusFromCustomer {
	
	public TestEntrySummaryDeclarationKidsToCyprus(String name) {
		super(name);
		Utils.log("(TestEntrySummaryDeclarationKidsToCyprus TestEntrySummaryDeclarationKidsToCyprus) name = " +
				name);
	}
	
	 protected void setFileNames() {
	        inputFileName       = "DE_CSF__20110701135101_000000002_KIDS.xml";
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
