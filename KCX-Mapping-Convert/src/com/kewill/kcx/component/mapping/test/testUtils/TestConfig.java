/*
 * Funktion    : TestConfig.java
 * Titel       :
 * Erstellt    : 16.06.2009
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
package com.kewill.kcx.component.mapping.test.testUtils;

import java.io.IOException;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestConfig<br>
 * Erstellt		: 16.06.2009<br>
 * Beschreibung	: Testklasse für Methoden der Klasse Config.java. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class TestConfig {
    
    private TestConfig() {
    }

    public static void main(String[] args) {
        boolean ret = Config.configure();
        Utils.log("(TestConfig main) ret1 = " + ret);
        ret = Config.configure();
        Utils.log("(TestConfig main) ret2 = " + ret);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ret = Config.configure();
        Utils.log("(TestConfig main) ret3 = " + ret);
    }

}
