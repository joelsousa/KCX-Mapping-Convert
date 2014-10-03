package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSArrivalNotificationUids<br>
 * Created		: 12.06.2010<br>
 * Description	: Conversion of an NCTSArrivalNotification 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class TestNCTSArrivalNotificationUids extends TestUidsToUidsFromCustomer {

	public TestNCTSArrivalNotificationUids(String name) {
		super(name);
        Utils.log("(TestNCTSArrivalNotificationUids TestNCTSArrivalNotificationUids) name = " + name);
	}

	protected void setInputFileName() {
		inputFileName     = "NCTSArrivalNotification_20100823_145017_soap.xml";
		inputFileName = "DE01DE01309320111031235144000130221.xml.pri";
        encoding          = "UTF-8";
	}

	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(30, Difference.NONE,  30,  32));
        expected.add(new Difference(31, Difference.NONE,  34,  37));
        expected.add(new Difference(32, Difference.NONE,  39,  40));
        expected.add(new Difference(34, 34,  43,  43));
        expected.add(new Difference(37, 37,  46,  48));
        expected.add(new Difference(39, Difference.NONE,  50,  57));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
