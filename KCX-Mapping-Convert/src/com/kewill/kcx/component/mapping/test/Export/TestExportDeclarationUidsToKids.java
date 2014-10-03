package com.kewill.kcx.component.mapping.test.Export;


import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul       : TestExportDeclarationKidsToKids.java<br>
 * Erstellt    : 17.05.2010<br>
 * Beschreibung: Conversion of an ExportDeclaration/ADA von UIDS to KIDS to FSS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verf�gung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestExportDeclarationUidsToKids extends TestUidsToKidsFromCustomer {

    public TestExportDeclarationUidsToKids(String name) {
        super(name);
        Utils.log("(TestExportDeclarationKidsToKids TestExportDeclarationKidsToKids) name = " + name);
    }

    protected void setFileNames() {
//        inputFileName       = "UK_HJH_TST_201005071400_00015.xml";
//        referenceFileName   = "UK_HJH_TST_201005071400_00015.xml";
        inputFileName       = "DE01DE0121440000000000073299.xml";
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
