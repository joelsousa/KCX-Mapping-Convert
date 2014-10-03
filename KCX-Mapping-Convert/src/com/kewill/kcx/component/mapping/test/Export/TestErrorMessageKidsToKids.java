package com.kewill.kcx.component.mapping.test.Export;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/*
 * Funktion    : testExportDeclarationKidsToKids.java
 * Titel       :
 * Erstellt    : 17.05.2010
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */


/**
 * Modul       : TestErrorMessageKidsToKids.java<br>
 * Erstellt    : 17.05.2010<br>
 * Beschreibung: Conversion of an localAppResult message from KIDS to KIDS to KIDS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestErrorMessageKidsToKids extends TestKidsToKcxFromCountry {

    public TestErrorMessageKidsToKids(String name) {
        super(name);
        Utils.log("(TestErrorMessageKidsToKids TestErrorMessageKidsToKids) name = " + name);
    }

    protected void setFileNames() {
        inputFileName       = "UK_HJH_TST_20100506-091018_23_6.xml";
        referenceFileName   = "UK_HJH_TST_20100506-091018_23_6.xml";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
