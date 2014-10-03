package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSMRNAllocatedUids<br>
 * Created		: 24.08.2010<br>
 * Description	: Conversion of an NCTSMRNAllocated from UIDS to KIDS and back to UIDS.
 * 				  Original and converted UIDS messages must be identical.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class TestNCTSMRNAllocatedUids extends TestUidsToUidsFromCustomer {
	
	public TestNCTSMRNAllocatedUids(String name) {
		super(name);
        Utils.log("(TestNCTSMRNAllocatedUids TestNCTSMRNAllocatedUids) name = " + name);
	}

	@Override
	protected void setInputFileName() {
		inputFileName     = "NCTSMRNAllocated_20100823_140652_soap.xml";
        encoding          = "UTF-8";
	}
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(19,  Difference.NONE, 19, 19));
        expected.add(new Difference(20,  Difference.NONE, 21, 21));
        expected.add(new Difference(21,  Difference.NONE, 23, 23));
        expected.add(new Difference(22,  Difference.NONE, 25, 25));
        expected.add(new Difference(23, 23, 27, 28));
        expected.add(new Difference(25,  Difference.NONE, 30, 31));
        expected.add(new Difference(30,  Difference.NONE, 37, 37));
        expected.add(new Difference(31,  Difference.NONE, 39, 39));
        expected.add(new Difference(32,  Difference.NONE, 41, 48));
        expected.add(new Difference(34,  Difference.NONE, 51, 76));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }

}

