package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestAdvanceInterventionNotUids<br>
 * Created		: 2010.07.21<br>
 * Description	: Conversion of a AdvanceInterventionNot 
 *                from UIDS to KIDS to UIDS.
 *                
 * @author Paul Pagdanganan
 * @version 1.0.00
 *
 */

public class TestAdvanceInterventionNotUids extends TestUidsToUidsFromCustomer {

	public TestAdvanceInterventionNotUids(String name) {
		super(name);
        Utils.log("(TestAdvanceInterventionNotUids TestAdvanceInterventionNotUids) name = " + name);		
	}

	protected void setInputFileName() {
    	inputFileName     = "ICSAdvanceInterventionNot_20100719_121326_soap.xml";
        encoding          = "UTF-8";
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7, 7, 7));
        expected.add(new Difference(23, 25, 23, 25));
        expected.add(new Difference(28, Difference.NONE, 28, 28));
        expected.add(new Difference(31, Difference.NONE, 32, 33));
        expected.add(new Difference(34, Difference.NONE, 37, 41));
        expected.add(new Difference(35, Difference.NONE, 43, 45));
        expected.add(new Difference(40, Difference.NONE, 51, 51));
        expected.add(new Difference(41, Difference.NONE, 53, 53));
        expected.add(new Difference(42, Difference.NONE, 55, 62));
        expected.add(new Difference(44, Difference.NONE, 65, 69));
        expected.add(new Difference(45, Difference.NONE, 71, 73));
        expected.add(new Difference(50, Difference.NONE, 79, 79));
        expected.add(new Difference(51, Difference.NONE, 81, 81));
        expected.add(new Difference(52, Difference.NONE, 83, 90));
        expected.add(new Difference(54, Difference.NONE, 93, 97));
        expected.add(new Difference(55, Difference.NONE, 99, 101));
        expected.add(new Difference(60, Difference.NONE, 107, 107));
        expected.add(new Difference(61, Difference.NONE, 109, 109));
        expected.add(new Difference(62, Difference.NONE, 111, 118));
        expected.add(new Difference(63, Difference.NONE, 120, 120));
        expected.add(new Difference(64, 64, 122, 122));
        expected.add(new Difference(71, Difference.NONE, 129, 129));
        expected.add(new Difference(74, Difference.NONE, 133, 134));
        expected.add(new Difference(76, Difference.NONE, 137, 137));
        expected.add(new Difference(79, Difference.NONE, 141, 142));
        expected.add(new Difference(82, Difference.NONE, 146, 146));
        expected.add(new Difference(86, Difference.NONE, 151, 151));
        expected.add(new Difference(95, Difference.NONE, 161, 161));
        expected.add(new Difference(98, Difference.NONE, 165, 166));
        expected.add(new Difference(100, Difference.NONE, 169, 169));
        expected.add(new Difference(103, Difference.NONE, 173, 174));
        expected.add(new Difference(106, Difference.NONE, 178, 178));
        expected.add(new Difference(110, Difference.NONE, 183, 183));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
