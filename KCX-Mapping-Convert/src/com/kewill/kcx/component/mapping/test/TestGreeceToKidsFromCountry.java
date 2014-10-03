/*
 * Funktion    : TestCyprusToKidsFromCountry.java
 * Titel       :
 * Erstellt    : 09.06.2011
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

import java.io.File;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.common.start.customer.out.KcxToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.in.GreeceToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestCyprusToKidsFromCountry<br>
 * Erstellt		: 09.06.2011<br>
 * Beschreibung	: Konvertierung einer Nachricht im zypriotischen Format in KIDS Format. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestGreeceToKidsFromCountry extends TestOneWay {

    public TestGreeceToKidsFromCountry(String name) {
        super(name);
        Utils.log("(TestGreeceToKidsFromCountry TestGreeceToKidsFromCountry) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/greece/in";
        kidsPath      = "data/kids/out";
        referencePath = "data/greece/in";
        encoding      = "UTF-8";
        
        setFileNames();
        
        super.setUp();
    }

    protected abstract void setFileNames();

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public String otherToKids(File inFile, String encoding) throws Exception {
        GreeceToKidsExtern greeceToKids = new GreeceToKidsExtern();
        return greeceToKids.convert(inFile, encoding);
    }

    public String kidsToOther(String message, File outFile, String encoding) throws Exception {
//        KidsToCyprusExtern kidsToCyprusExtern = new KidsToCyprusExtern();
//        Object result = kidsToCyprusExtern.convert(message, outFile, encoding);
        KcxToKidsExtern kcxToKidsExtern = new KcxToKidsExtern();
        Object result = kcxToKidsExtern.convert(message, outFile, encoding);
        
        if (result instanceof String) {
            Utils.log("(TestCyprusToKidsFromCountry kidsToOther) Ergebnis ist vom Typ String!");
            return (String) result;
        } else {
            Utils.log("(TestCyprusToKidsFromCountry kidsToOther) Ergebnis ist vom Typ Object!");
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
