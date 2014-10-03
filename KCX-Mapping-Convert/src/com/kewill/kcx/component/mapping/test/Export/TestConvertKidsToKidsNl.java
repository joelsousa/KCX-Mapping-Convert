package com.kewill.kcx.component.mapping.test.Export;


import java.io.File;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.countries.nl.aes.KidsToKidsNl;
import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul       : TestConvertKidsToKidsNlNl.java<br>
 * Erstellt    : 17.05.2010<br>
 * Beschreibung: Conversion of an KIDS message from KIDS to KIDS to KIDS NL Version.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestConvertKidsToKidsNl extends TestKidsToKcxFromCustomer {

    public TestConvertKidsToKidsNl(String name) {
        super(name);
        Utils.log("(TestConvertKidsToKidsNl TestConvertKidsToKidsNl) name = " + name);
    }

    protected void setFileNames() {
//        inputFileName       = "reversedeclmitpdf.xml";
//        inputFileName       = "reversedeclohnepdf.xml";
//        inputFileName       = "INFORM-1281442987044-18.xml";
        inputFileName       = "20100812160500001.xml";
        referenceFileName   = null;
    }

    public String kidsToOther(String message, File outFile, String encoding) throws Exception {
        KidsToKidsNl kcxToKids = new KidsToKidsNl();
        return kcxToKids.readKids(message, encoding, "Dummy Audit-ID");
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
