package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestDiversionRequestAcknowledgmentKidsToKids<br>
 * Erstellt		: 15.06.2010<br>
 * Description	: Conversion of a DiversionRequestAcknowledgment
 *                from KIDS to KIDS.
 *
 * @author Pete T
 * @version 1.0.00
 */
public class TestDiversionRequestAcknowledgmentKidsToKids extends TestKidsToKcxFromCountry {

    public TestDiversionRequestAcknowledgmentKidsToKids(String name) {
        super(name);
        Utils.log("(TestDiversionRequestAcknowledgmentKidsToKids " +
        		"TestDiversionRequestAcknowledgmentKidsToKids) name = " + name);
    }

    protected void setFileNames() {
        inputFileName       = "ICSDiversionRequestAcknowledgement_20100615_173723_soap.xml";
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
