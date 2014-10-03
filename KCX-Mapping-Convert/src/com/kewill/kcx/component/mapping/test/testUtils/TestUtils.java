/*
 * Funktion    : TestUtils.java
 * Titel       :
 * Erstellt    : 02.06.2009
 * Author      : CSF GmbH / schmidt
 * Beschreibung: Testklasse für Methoden der Klasse Utils.java.
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
package com.kewill.kcx.component.mapping.test.testUtils;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestUtils<br>
 * Erstellt		: 02.06.2009<br>
 * Beschreibung	: Testklasse für Methoden der Klasse Utils.java. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class TestUtils {

    private TestUtils() {
    }
    
    public static void main(String[] args) {
        String erg = Utils.removeDots("DE.TEST.FRA");
        Utils.log("(TestUtils main) erg = " + erg);
        Utils.removeDots("");
        Utils.removeDots(null);
    }

}
