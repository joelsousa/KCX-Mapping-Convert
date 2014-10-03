/*
 * Funktion    : TestCompletionFss.java
 * Titel       :
 * Erstellt    : 18.12.2008
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
package com.kewill.kcx.component.mapping.test;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul       : TestCompletionFss.java
 * Erstellt    : 18.12.2008
 * Beschreibung: Konvertierung einer UIDS-Completion nach KIDS und 
 *               von dort nach FSS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestCompletionFss extends TestUidsToFssFromCustomer {

    public TestCompletionFss(String name) {
        super(name);
        Utils.log("(TestCompletionFss TestCompletionFss) name = " + name);
    }

    protected void setFileNames() {
//        inputFileName       = "expdat_1.xml";
        inputFileName       = "DE01DE01215820081202110600042.xml";
        referenceFileName   = "expdat_1_reference.fss";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than three.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
