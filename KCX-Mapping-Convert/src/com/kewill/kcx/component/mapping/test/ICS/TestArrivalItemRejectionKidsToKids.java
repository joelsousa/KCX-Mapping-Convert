package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestArrivalItemRejectionKidsToKids<br>
 * Created		: 2010.08.25<br>
 * Description	: Conversion of a ArrivalItemRejection 
 *                from KIDS to KIDS.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class TestArrivalItemRejectionKidsToKids extends TestKidsToKcxFromCountry {
	public TestArrivalItemRejectionKidsToKids(String name) {
		super(name);
		Utils.log("(TestArrivalItemRejectionKidsToKids TestArrivalItemRejectionKidsToKids) name = " + name);
	}

    protected void setFileNames() {
        inputFileName       = "ICSArrivalItemRejectionKK_20100727_104757_soap.xml";
        referenceFileName   = null;
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
