package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSDeclarationRejectedUids<br>
 * Created		: 24.08.2010<br>
 * Description	: Conversion of an NCTSDeclarationRejected from UIDS to KIDS and back to UIDS.
 * 				  Original and converted UIDS messages must be identical.
 * 
 * @author	: Lassiter Caviles
 * @version 1.0.00
 */
public class TestNCTSDeclarationRejectedUids extends TestUidsToUidsFromCustomer {
	
	public TestNCTSDeclarationRejectedUids(String name) {
		super(name);
        Utils.log("(TestNCTSDeclarationRejectedUids TestNCTSDeclarationRejectedUids) name = " + name);
	}

	@Override
	protected void setInputFileName() {
		inputFileName     = "NCTSDeclarationRejected_20100823_135303_soap.xml";
        encoding          = "UTF-8";
	}
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7, 7, 7));
        expected.add(new Difference(20, 20, 20, 20));     
        expected.add(new Difference(26, Difference.NONE, 26, 27));
        expected.add(new Difference(31, Difference.NONE, 33, 34));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }

}
