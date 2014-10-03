package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : TestNCTSArrivalNotificationKidsToFssToKids<br>
 * Created    	: 09.09.2010<br>
 * Description	: Test for ArrivalNotification KIDS to FSS and back to KIDS
 * 				  Input and Output Kids must be identical.
 *
 * @author Paul Pagdanganan
 * @version 6.2.00
 */

public class TestNCTSArrivalNotificationKidsToFssToKids extends TestKidsToFssToKidsFromCustomer {

	public TestNCTSArrivalNotificationKidsToFssToKids(String name) {
        super(name);
        Utils.log("(TestNCTSArrivalNotificationKidsToFssToKids TestNCTSArrivalNotificationKidsToFssToKids) name = " 
        		+ name);
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        //Expected difference: Different timestamps in the flow rate (VOR)
//        expected.add(new Difference(0, 0,  0,  0));
        
        expected.add(new Difference(7, 8, 7, 8));
        expected.add(new Difference(10, 11, 10, 11));
        expected.add(new Difference(13, 13, 13, 13));
        expected.add(new Difference(37, Difference.NONE, 37, 38));
        expected.add(new Difference(40, Difference.NONE, 42, 46));
        expected.add(new Difference(41, Difference.NONE, 48, 62));
        expected.add(new Difference(43, Difference.NONE, 65, 69));
        expected.add(new Difference(44, Difference.NONE, 71, 71));
        expected.add(new Difference(45, Difference.NONE, 73, 73));
        expected.add(new Difference(46, Difference.NONE, 75, 75));
        expected.add(new Difference(49, Difference.NONE, 79, 80));
        expected.add(new Difference(51, Difference.NONE, 83, 87));
        expected.add(new Difference(53, Difference.NONE, 90, 91));
        expected.add(new Difference(55, Difference.NONE, 94, 98));
        expected.add(new Difference(57, Difference.NONE, 101, 112));
        
        
        
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }

	@Override
	protected void setInputFileName() {
		inputFileName	= "NCTSArrivalNotification_20110520_soap.xml";
		inputFileName	= "DE01DE01309320111031235144000130221.xml";
		encoding      	= "UTF-8";
	}
	
	@Override
	protected void setUp() throws Exception {
		//If folder is not set, test files will be placed in "data/fss/in/".
		folder = "NCTS";
		super.setUp();
	}
}
