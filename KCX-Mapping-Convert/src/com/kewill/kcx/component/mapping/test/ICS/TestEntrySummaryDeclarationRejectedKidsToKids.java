package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationRejectedKidsToKids<br>
 * Created		: 2010.11.09<br>
 * Description	: Conversion of a EntrySummaryDeclarationRejected 
 *                from KIDS to KIDS.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationRejectedKidsToKids extends TestKidsToKcxFromCountry {

	public TestEntrySummaryDeclarationRejectedKidsToKids(String name) {
		super(name);
		Utils.log("(TestEntrySummaryDeclarationRejectedKidsToKids " +
				"TestEntrySummaryDeclarationRejectedKidsToKids) name = " + name);
	}

    protected void setFileNames() {
        inputFileName       = "ICSEntrySummaryDeclarationRejectedKK_20101109_112111_soap.xml";
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
