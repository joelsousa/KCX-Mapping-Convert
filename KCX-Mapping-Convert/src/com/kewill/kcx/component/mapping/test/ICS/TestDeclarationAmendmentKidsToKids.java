package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestDeclarationAmendmentKidsToKids<br>
 * Date Started : July 26, 2010<br>
 * Description	: Conversion of a DeclarationAmendment 
 *                from KIDS to KIDS.
 *                
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class TestDeclarationAmendmentKidsToKids extends TestKidsToKcxFromCountry {

	public TestDeclarationAmendmentKidsToKids(String name) {
		super(name);
		Utils.log("(TestDeclarationAmendmentKidsToKids TestDeclarationAmendmentKidsToKids) name = " + name);
	}
	
	protected void setFileNames() {
        inputFileName       = "ICSDeclarationAmendment_20100825_163016_soap.xml";
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
