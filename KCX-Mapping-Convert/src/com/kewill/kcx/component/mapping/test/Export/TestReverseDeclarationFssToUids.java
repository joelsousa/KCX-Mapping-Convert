package com.kewill.kcx.component.mapping.test.Export;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestFssToUids;
import com.kewill.kcx.component.mapping.util.Utils;

/*
 * Funktion    : TestReverseDeclarationFssToUids.java
 * Titel       :
 * Erstellt    : 03.11.2010
 * Author      : Kewill GmbH / schmidt
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
 * Modul       : TestReverseDeclarationFssToUids.java<br>
 * Erstellt    : 03.11.2010<br>
 * Beschreibung: Conversion of an ReverseDeclaration message from FSS to UIDS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestReverseDeclarationFssToUids extends TestFssToUids {

    public TestReverseDeclarationFssToUids(String name) {
        super(name);
        Utils.log("(TestReverseDeclarationFssToUids TestReverseDeclarationKidsToKids) name = " + name);
    }

    protected void setFileNames() {
//        inputFileName       = "UK_HJH_TST_20100507-140152_639_14.xml";
//        referenceFileName   = "UK_HJH_TST_20100507-140152_639_14.xml";
//        inputFileName       = "DE01DE012158201011031450097310.xml";
//        inputFileName       = "KADE01DE013784201105031332003453.xml";
        inputFileName       = "DE01DE013241201212200951356933.xml";
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
