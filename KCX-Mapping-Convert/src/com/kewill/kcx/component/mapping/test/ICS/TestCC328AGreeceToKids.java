package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestGreeceToKidsFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestCC328ACyprusToKids<br>
 * Erstellt		: 20.10.2011<br>
 * Description	: Conversion of a CC328A message from Greece format to KIDS.
 *
 * @author schmidt
 * @version 1.0.00
 */
public class TestCC328AGreeceToKids extends TestGreeceToKidsFromCountry {

    public TestCC328AGreeceToKids(String name) {
        super(name);
        Utils.log("(TestCC328AGreeceToKids TestCC328AGreeceToKids) name = " + name);
    }

    protected void setFileNames() {
        inputFileName       = "CC328A_GR.xml";
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
