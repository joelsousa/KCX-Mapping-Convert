package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationAmendmentRejectionUids<br>
 * Created		: Nov. 10, 2010<br>
 * Description	: Conversion of a EntrySummaryDeclarationAmendmentRejection
 * 				  from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationAmendmentRejectionUids extends TestUidsToUidsFromCustomer {

	public TestEntrySummaryDeclarationAmendmentRejectionUids(String name) {
		super(name);
		Utils.log("(TestEntrySummaryDeclarationAmendmentRejectionUids " +
				"TestEntrySummaryDeclarationAmendmentRejectionUids) name = " + name);
	}
	
	protected void setInputFileName() {
    	inputFileName     = "ICSEntrySummaryDeclarationAmendmentRejection_20101110_123801_soap.xml";
        encoding          = "UTF-8";
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(22, 22,  22,  22));
        expected.add(new Difference(48, 48,  48,  52));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
