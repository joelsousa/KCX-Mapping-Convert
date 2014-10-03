package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestArrivalNotificationUids<br>
 * Created		: 2010.07.20<br>
 * Description	: Conversion of a ArrivalNotfication
 * 				  from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class TestArrivalNotificationUids extends TestUidsToUidsFromCustomer {
	
	public TestArrivalNotificationUids(String name) {
		super(name);
        Utils.log("(TestArrivalNotificationUids TestArrivalNotificationUids) name = " + name);		
	}

	protected void setInputFileName() {
    	inputFileName     = "ICSArrivalNotification_20100719_092050_soap.xml";
    	inputFileName     = "ARN_FRANCEEEE.xml";
        encoding          = "UTF-8";
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(9, Difference.NONE,  9,  9));
        expected.add(new Difference(10, 10,  11,  Difference.NONE));
        expected.add(new Difference(12, Difference.NONE,  12,  13));
        expected.add(new Difference(20, Difference.NONE,  22,  22));
        expected.add(new Difference(24, Difference.NONE, 27, 27));
        expected.add(new Difference(27, Difference.NONE, 31, 32));
        expected.add(new Difference(29, 30, 35, 36));
        expected.add(new Difference(32, Difference.NONE, 38, 42));
        expected.add(new Difference(34, Difference.NONE, 45, 63));
        expected.add(new Difference(47, Difference.NONE, 77, 77));
        expected.add(new Difference(49, Difference.NONE, 80, 80));
        expected.add(new Difference(55, Difference.NONE, 87, 88));
        expected.add(new Difference(58, Difference.NONE, 92, 93));
        expected.add(new Difference(59, Difference.NONE, 95, 98));
        expected.add(new Difference(64, Difference.NONE, 104, 105));
        expected.add(new Difference(67, Difference.NONE, 109, 110));
        expected.add(new Difference(68, Difference.NONE, 112, 115));
        expected.add(new Difference(72, Difference.NONE, 120, 120));
        expected.add(new Difference(74, Difference.NONE, 123, 123));
        expected.add(new Difference(80, Difference.NONE, 130, 131));
        expected.add(new Difference(81, Difference.NONE, 133, 136));

        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
