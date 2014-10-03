package com.kewill.kcx.component.mapping.test.Export;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/*
 * Funktion    : TestReverseDeclarationKidsToKids.java
 * Titel       :
 * Erstellt    : 11.08.2010
 * Author      : Kewill GmbH / iwaniuk
 * Beschreibung: 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:

 */


/**
 * Modul       : TestReverseDeclarationKidsToKids.java<br>
 * Erstellt    : 11.08.2010<br>
 * Beschreibung: Conversion of an ReverseDeclaration message from KIDS to KIDS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestReverseDeclarationKidsToKids extends TestKidsToKcxFromCountry {

    public TestReverseDeclarationKidsToKids(String name) {
        super(name);
        Utils.log("(TestReverseDeclarationKidsToKids TestReverseDeclarationKidsToKids) name = " + name);
    }

    protected void setFileNames() {
//        inputFileName       = "UK_HJH_TST_20100507-140152_639_14.xml";
//        referenceFileName   = "UK_HJH_TST_20100507-140152_639_14.xml";
        inputFileName       = "runthroughuids2.xml";
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
