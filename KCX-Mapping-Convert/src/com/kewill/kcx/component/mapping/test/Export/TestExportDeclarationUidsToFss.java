package com.kewill.kcx.component.mapping.test.Export;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToFssFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/*
 * Funktion    : TestExportDeclarationUidsToFss.java
 * Titel       :
 * Erstellt    : 16.10.2008
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
 * Modul       : TestExportDeclarationUidsToFss.java
 * Erstellt    : 16.10.2008
 * Beschreibung: Conversion of an ExportDeclaration/ADA von UIDS to KIDS to FSS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestExportDeclarationUidsToFss extends TestUidsToFssFromCustomer {

    public TestExportDeclarationUidsToFss(String name) {
        super(name);
        Utils.log("(TestExportDeclarationUidsToFss TestExportDeclarationUidsToFss) name = " + name);
    }

    protected void setFileNames() {
        // inputFileName       = "DE01NL0133785188946092058.xml";
        // inputFileName       = "DE01DE0136140000000000073299.xml";
        // inputFileName       = "ausfuhrerstattung-expdat-v7.xml";
        inputFileName       = "empf-ohne-final-uids.xml";
        referenceFileName   = "DE01DE0136140000000000073299.xml";
    }

    
    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        // 1. erwartete Differenz: Unterschiedlicher Zeitstempel im Vorlaufsatz (VOR)
        expected.add(new Difference(0, 0,  0,  0));
        
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
