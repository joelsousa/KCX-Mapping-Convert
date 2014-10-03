/*
 * Funktion    : TestKidsToCyprusFromCustomer
 * Titel       :
 * Erstellt    : 09.06.2011
 * Author      : CSF GmbH / schmidt
 * Beschreibung: Nachricht von KIDS nach Cyprus Richtung Zoll testen.
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

import java.io.File;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToCyprusExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestKidsToCyprusFromCustomer<br>
 * Erstellt		: 09.06.2011<br>
 * Beschreibung	: Nachricht von KIDS nach Cyprus Richtung Zoll testen. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestKidsToCyprusFromCustomer extends TestOneWay {

    public TestKidsToCyprusFromCustomer(String name) {
        super(name);
        Utils.log("(TestKidsToCyprusFromCustomer TestKidsToCyprusFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/kids/in";
        kidsPath      = "data/kids/out";
        referencePath = "data/cyprus/out";
        encoding      = "UTF-8";
        
        setFileNames();
        
        super.setUp();
    }

    protected abstract void setFileNames();

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public String otherToKids(File inFile, String encoding) throws Exception {
        KidsToKidsExtern cyprusToKids = new KidsToKidsExtern();
        return cyprusToKids.convert(inFile, encoding);
    }

    public String kidsToOther(String message, File outFile, String encoding) throws Exception {
        KidsToCyprusExtern kidsToCyprusExtern = new KidsToCyprusExtern();
        Object result = kidsToCyprusExtern.convert(message, outFile, encoding);
        
        if (result instanceof String) {
            Utils.log("(TestKidsToCyprusFromCustomer kidsToOther) Ergebnis ist vom Typ String!");
            return (String) result;
        } else {
            Utils.log("(TestKidsToCyprusFromCustomer kidsToOther) Ergebnis ist vom Typ Object!");
            return "Ergebnis ist vom Typ Object!";
        }
    }
    
    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
