package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestArrivalRejectionUidsToUids<br>
 * Created		: 24.08.2010<br>
 * Description	: Conversion of an ArrivalRejection from UIDS to KIDS and back to UIDS.
 * 				  Original and converted UIDS messages must be identical.
 * 
 * @author	: Michelle Bauza
 * @version 1.0.00
 */
public class TestNCTSArrivalRejectionUids extends TestUidsToUidsFromCustomer {
	
	public TestNCTSArrivalRejectionUids(String name) {
		super(name);
		Utils.log("(TestArrivalRejectionUids TestArrivalRejectionUids) name = " + name);
	}

	@Override
	protected void setInputFileName() {
		inputFileName	= "NCTSArrivalRejection_20100824_122305.xml";
		encoding		= "UTF-8";
	}

	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7, 7, 7));
        expected.add(new Difference(19, 19, 19, 19));
        expected.add(new Difference(26, Difference.NONE, 26, 27));
        expected.add(new Difference(31, Difference.NONE, 33, 34));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
