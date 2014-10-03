package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TestEntrySummaryDeclarationRejectedUids<br>
 * Created		: 2010.11.09<br>
 * Description	: Conversion of a ArrivalItemRejection 
 *                from KIDS to UIDS.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationRejectedUids extends TestUidsToUidsFromCustomer {
	public TestEntrySummaryDeclarationRejectedUids(String name) {
		super(name);
        Utils.log("(TestEntrySummaryDeclarationRejectedUids " +
        		"TestEntrySummaryDeclarationRejectedUids) name = " + name);		
	}

	protected void setInputFileName() {
    	inputFileName     = "ICSEntrySummaryDeclarationRejected_20101109_123801_soap.xml";
        encoding          = "UTF-8";
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(21, 21, 21, 21));
        expected.add(new Difference(28, Difference.NONE,  28,  28));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
