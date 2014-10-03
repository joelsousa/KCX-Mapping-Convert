package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationAcknowledgmentKidsToKids<br>
 * Created		: 2010.11.04<br>
 * Description	: Conversion of a EntrySummaryDeclarationAcknowledgment 
 *                from KIDS to KIDS 
 *                
 *                Codemapping has to be activated in the test file!!!
 *                
 * @author Christine Kron
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationAcknowledgmentKidsToKids extends TestKidsToKcxFromCountry {
	public TestEntrySummaryDeclarationAcknowledgmentKidsToKids(String name) {
		super(name);
	Utils.log(
	"(TestEntrySummaryDeclarationAcknowledgmentKidsToKids TestEntrySummaryDeclarationAcknowledgmentKidsToKids) name = " +
			name);
	}

    protected void setFileNames() {
        // inputFileName       = "ICSEntrySummaryDeclarationAcknowledgmentKK_20101104_111410_soap.xml";
        inputFileName		= "IT_ACO_MI_2011011315170000258.xml";
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
