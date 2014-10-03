package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestArrivalItemRejectionKids<br>
 * Created		: 2010.07.20<br>
 * Description	: Conversion of a ArrivalItemRejection 
 *                from KIDS to UIDS.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class TestArrivalItemRejectionUids extends TestUidsToUidsFromCustomer {
	public TestArrivalItemRejectionUids(String name) {
		super(name);
        Utils.log("(TestArrivalItemRejectionUids TestArrivalItemRejectionUids) name = " + name);		
	}

	protected void setInputFileName() {
    	inputFileName     = "ICSArrivalItemRejection_20100719_154801_soap.xml";
        encoding          = "UTF-8";
    }
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(9, Difference.NONE, 9, 9));
        expected.add(new Difference(10, 10, 11, 11));
        expected.add(new Difference(12, Difference.NONE, 12, 13));
        expected.add(new Difference(19, 19, 21, 21));

        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
