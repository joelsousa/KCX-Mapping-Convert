package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationAmendmentRejectionKidsToKids<br>
 * Date Started	: Nov. 10, 2010<br>
 * Description	: Conversion of a EntrySummaryDeclarationAmendmentRejection
 *                from KIDS to KIDS.
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationAmendmentRejectionKidsToKids extends TestKidsToKcxFromCountry {

	public TestEntrySummaryDeclarationAmendmentRejectionKidsToKids(String name) {
		super(name);
		Utils.log("(TestEntrySummaryDeclarationAmendmentRejectionKidsToKids " +
				"TestEntrySummaryDeclarationAmendmentRejectionKidsToKids) name = " + name);
	}
	
	protected void setFileNames() {
        inputFileName       = "ICSEntrySummaryDeclarationAmendmentRejection_20101110_170206_soap.xml";
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
