/*
 * Funktion    : TestExportDeclarationKids.java
 * Titel       :
 * Erstellt    : 04.12.2008
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

import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestExportDeclarationKids<br>
 * Erstellt		: 04.12.2008<br>
 * Beschreibung	: KidsExportDeclaration testen. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestKidsToUidsFromCountry extends TestOneWay {

    public TestKidsToUidsFromCountry(String name) {
        super(name);
        Utils.log("(TestKidsToUidsFromCountry TestKidsToUidsFromCountry) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/kids/in";
        kidsPath      = "data/kids/out";
        referencePath = "data/uids/out";
        encoding      = "UTF-8";
        
        setFileNames();
        
        super.setUp();
    }

    protected abstract void setFileNames();

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public String otherToKids(File inFile, String encoding) throws Exception {
        KidsToKidsExtern kidsToKids = new KidsToKidsExtern();
        return kidsToKids.convert(inFile, encoding);
    }

    public String kidsToOther(String message, File outFile, String encoding) throws Exception {
        KidsToUidsExtern kidsToUidsExtern = new KidsToUidsExtern();
        Object result = kidsToUidsExtern.convert(message, outFile, encoding);
        
        if (result instanceof String) {
            Utils.log("(TestKidsToUidsFromCountry kidsToOther) Ergebnis ist vom Typ String!");
            return (String) result;
        } else {
            Utils.log("(TestKidsToUidsFromCountry kidsToOther) Ergebnis ist vom Typ Object!");
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
