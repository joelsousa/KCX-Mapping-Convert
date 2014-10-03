package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestCyprusToKidsFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationCyprusToKids<br>
 * Created		: 09.06.2011<br>
 * Description	: Conversion of a ICSDeclaration from Cyprus to KIDS ICSEntrySummaryDeclaration.
 * 
 * @author schmidt
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationRejectedCyrusToKids extends TestCyprusToKidsFromCountry {
	
	public TestEntrySummaryDeclarationRejectedCyrusToKids(String name) {
		super(name);
        Utils.log("(TestEntrySummaryDeclarationRejectedCyrusToKids TestEntrySummaryDeclarationRejectedCyrusToKids) " +
        		                                                                                    "name = " + name);
	}

	protected void setFileNames() {
    	inputFileName     = "cc316a_sample.xml";
        referenceFileName = "cc316a_sample.xml";
        encoding          = "UTF-8";
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
