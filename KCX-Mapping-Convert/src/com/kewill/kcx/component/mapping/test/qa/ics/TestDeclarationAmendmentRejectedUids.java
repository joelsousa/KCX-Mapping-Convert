package com.kewill.kcx.component.mapping.test.qa.ics;
import java.util.ArrayList;
import org.incava.util.diff.Difference;
import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestDeclarationAmendmentRejectedUids<br>
 * Date Created	: July 19, 2010<br>
 * Description	: Conversion of a DeclarationAmendmentRejected
 * 				  from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Frederick Topico
 * @version 1.0.00
 *
 */


public class TestDeclarationAmendmentRejectedUids extends TestUidsToUidsFromCustomer {
	
	public TestDeclarationAmendmentRejectedUids(String name) {
		super(name);
		Utils.log(
			"(TestDeclarationAmendmentRejectedUids TestDeclarationAmendmentRejectedUids) name = " + name);
	}
	
	protected void setInputFileName() {
		inputFileName 	= "ICSDeclarationAmendmentRejected.xml";
		encoding		= "UTF-8";
	}
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        //Expected differences using ICSDeclarationAmendmentRejected.xml
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(21, Difference.NONE,  21,  22));
        expected.add(new Difference(23, Difference.NONE,  25,  25));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}

