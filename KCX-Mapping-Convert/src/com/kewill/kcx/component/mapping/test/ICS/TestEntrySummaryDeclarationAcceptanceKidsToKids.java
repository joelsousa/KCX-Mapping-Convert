package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationAcceptanceKidsToKids<br>
 * Created		: 2010.08.25<br>
 * Description	: Conversion of a EntrySummaryDeclarationAcceptance 
 *                from KIDS to KIDS.
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationAcceptanceKidsToKids extends TestKidsToKcxFromCountry {
	public TestEntrySummaryDeclarationAcceptanceKidsToKids(String name) {
		super(name);
	Utils.log(
	"(TestEntrySummaryDeclarationAcceptanceKidsToKids TestEntrySummaryDeclarationAcceptanceKidsToKids) name = " +
			name);
	}

    protected void setFileNames() {
        inputFileName       = "ICSEntrySummaryDeclarationAcceptanceKK_20100726_122710_soap.xml";
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
