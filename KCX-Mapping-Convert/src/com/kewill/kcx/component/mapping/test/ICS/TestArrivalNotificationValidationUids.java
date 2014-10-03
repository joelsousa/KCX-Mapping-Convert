package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TestArrivalNotificationValidationUids<br>
 * Date Created	: July 19, 2010<br>
 * Description	: Conversion of a ArrivalNotficationValidation
 * 				  from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class TestArrivalNotificationValidationUids extends TestUidsToUidsFromCustomer {
	
	public TestArrivalNotificationValidationUids(String name) {
		super(name);
		Utils.log(
			"(TestArrivalNotificationValidationUids TestArrivalNotificationValidationUids) name = " + name);
	}
	
	protected void setInputFileName() {
//		inputFileName 	= "ICSArrivalNotificationValidation_20100719_144228_soap.xml";
		inputFileName 	= "ICSArrivalNotificationValidation.xml";
//		inputFileName 	= "QA_Test_01.xml";
		
		encoding		= "UTF-8";
	}
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
//        //Expected differences using ICSArrivalNotificationValidation.xml
//        // the following differences can be ignored
//        // Standalone "yes" missing
//        expected.add(new Difference(0, 1,  0,  1));
//        // sentat "T" missing
//        expected.add(new Difference(7, 7,  7,  7));
//        expected.add(new Difference(21, Difference.NONE,  21,  22));
//        expected.add(new Difference(23, Difference.NONE,  25,  25));
        
        //Expected differences using ICSArrivalNotificationValidation_20100719_144228_soap.xml
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
