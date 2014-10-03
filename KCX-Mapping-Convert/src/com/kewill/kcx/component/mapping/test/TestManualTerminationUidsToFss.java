/*
 * Funktion    : TestManualerminationFss.java
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
 * Modul       : TestManualTermination.java
 * Erstellt    : 16.10.2008
 * Beschreibung: Konvertierung einer UIDS-Completion nach KIDS und 
 *               von dort nach FSS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestManualTerminationUidsToFss extends TestUidsToFssFromCustomer {

    public TestManualTerminationUidsToFss(String name) {
        super(name);
        Utils.log("(TestManualTerminationUidsToFss TestManualTerminationUidsToFss) name = " + name);
    }

    protected void setFileNames() {
//        inputFileName       = "expdat_1.xml";
        inputFileName       = "QA_08_EXPERL_U.200811.xml";
        referenceFileName   = "unknown.fss";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than three.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
