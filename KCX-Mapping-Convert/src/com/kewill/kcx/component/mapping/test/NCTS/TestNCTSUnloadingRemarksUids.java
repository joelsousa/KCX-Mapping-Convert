package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSUnloadingRemarksUids<br>
 * Created		: 09.02.2010<br>
 * Description	: Conversion of an NCTSUnloadingRemarks
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */

public class TestNCTSUnloadingRemarksUids extends TestUidsToUidsFromCustomer {
	
	public TestNCTSUnloadingRemarksUids(String name) {
		super(name);
        Utils.log("(TestNCTSUnloadingRemarksUids TestNCTSUnloadingRemarksUids) name = " + name);
	}

	protected void setInputFileName() {
		inputFileName     = "NCTSUnloadingRemarks_20100831_093542_soap.xml";
        encoding          = "UTF-8";
	}
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7, 7, 7));
        expected.add(new Difference(19,  Difference.NONE, 19, 19));
        expected.add(new Difference(22,  Difference.NONE, 23, 24));
        expected.add(new Difference(23,  Difference.NONE, 26, 26));
        expected.add(new Difference(26,  Difference.NONE, 30, 32));
        expected.add(new Difference(27,  Difference.NONE, 34, 37));
        expected.add(new Difference(28,  Difference.NONE, 39, 40));
        expected.add(new Difference(34,  Difference.NONE, 47, 47));
        expected.add(new Difference(35,  Difference.NONE, 49, 49));
        expected.add(new Difference(36,  Difference.NONE, 51, 58));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}

