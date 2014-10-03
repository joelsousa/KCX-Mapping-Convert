/*
 * Funktion    : TestDb.java
 * Titel       :
 * Erstellt    : 29.10.2008
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

import junit.framework.TestCase;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.CountryConfigDTO;
import com.kewill.kcx.component.mapping.db.Db;

/**
 * Modul		: TestDb<br>
 * Erstellt		: 29.10.2008<br>
 * Beschreibung	: Functional tests for class Db.java.  
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestDb extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        Config.configure();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        Db.closeConnection();
    }

//    public void testDbConnection() {
//        Db.openConnection();
//        assertTrue("Database connection failed.", Db.isConnected());
//    }

    public void testGetCountryConfig() {
        CountryConfigDTO ccd = Db.getCountryConfig("NL");
        assertTrue("Database connection failed.", ccd != null);
    }


}
