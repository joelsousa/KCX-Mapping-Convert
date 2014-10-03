package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestCyprusToKidsFromCountry;
import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromDE;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestCC304ACyprusToKids<br>
 * Erstellt		: 06.09.2011<br>
 * Description	: Conversion of a CC304A message from Cyprus format to KIDS.
 *
 * @author schmidt
 * @version 1.0.00
 */
public class TestCC304ACyprusToKids extends TestCyprusToKidsFromCountry {

    public TestCC304ACyprusToKids(String name) {
        super(name);
        Utils.log("(TestCC304ACyprusToKids TestCC304ACyprusToKids) name = " + name);
    }

    protected void setFileNames() {
        inputFileName       = "cc304a_sample.xml";
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
