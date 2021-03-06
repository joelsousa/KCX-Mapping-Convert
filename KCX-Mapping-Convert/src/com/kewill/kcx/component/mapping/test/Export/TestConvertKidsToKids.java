package com.kewill.kcx.component.mapping.test.Export;


import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul       : TestConvertKidsToKids.java<br>
 * Erstellt    : 17.05.2010<br>
 * Beschreibung: Conversion of an KIDS message from KIDS to KIDS to KIDS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verf�gung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestConvertKidsToKids extends TestKidsToKcxFromCountry {

    public TestConvertKidsToKids(String name) {
        super(name);
        Utils.log("(TestConvertKidsToKids TestConvertKidsToKids) name = " + name);
    }

    protected void setFileNames() {
//        inputFileName       = "reversedeclmitpdf.xml";
//        inputFileName       = "reversedeclohnepdf.xml";
//        inputFileName       = "INFORM-1281442987044-18.xml";
        inputFileName       = "20100812160500001.xml";
        referenceFileName   = null;
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        // 1. erwartete Differenz: Unterschiedlicher Zeitstempel im Vorlaufsatz (VOR)
//        expected.add(new Difference(0, 0,  0,  0));
        
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
