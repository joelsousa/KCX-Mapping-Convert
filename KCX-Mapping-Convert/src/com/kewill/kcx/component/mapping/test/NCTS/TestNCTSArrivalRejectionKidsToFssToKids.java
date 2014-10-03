package com.kewill.kcx.component.mapping.test.NCTS;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : TestNCTSArrivalRejectionKidsToFssToKids<br>
 * Created    	: 09.09.2010<br>
 * Description	: Test for ArrivalRejection KIDS to FSS and back to KIDS
 * 				  Input and Output Kids must be identical.
 *
 * @author Edwin Bautista 
 * @version 6.2.00
 */
public class TestNCTSArrivalRejectionKidsToFssToKids extends TestKidsToFssToKidsFromCustomer {

    public TestNCTSArrivalRejectionKidsToFssToKids(String name) {
        super(name);
        Utils.log("(TestNCTSArrivalRejectionKidsToFssToKids TestNCTSArrivalRejectionKidsToFssToKids) name = " + name);
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 8,  7,  8));
        expected.add(new Difference(10, 11,  10,  11));
        expected.add(new Difference(13, 13,  13,  13));
        expected.add(new Difference(38, Difference.NONE,  38,  39));
        expected.add(new Difference(39, Difference.NONE,  41,  41));
        expected.add(new Difference(42, Difference.NONE,  45,  45));
        expected.add(new Difference(44, Difference.NONE,  48,  48));
        expected.add(new Difference(47, Difference.NONE,  52,  52));
        
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }

	@Override
	protected void setInputFileName() {
		inputFileName	= "NCTSArrivalRejection_20100825_170151_soap.xml";
	}

	@Override
	protected void setUp() throws Exception {
		//If folder is not set, test files will be placed in "data/fss/in/".
		folder = "NCTS";
		super.setUp();
	}
}

