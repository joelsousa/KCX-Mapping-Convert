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

import com.kewill.kcx.component.mapping.common.start.customer.out.KcxToKidsExtern;
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
public abstract class TestKidsToKcxFromDE extends TestOneWay {

    public TestKidsToKcxFromDE(String name) {
        super(name);
        Utils.log("(TestKidsToKcxFromDE TestKidsToKcxFromDE) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/DE/in";
        kidsPath      = "data/kids/out";
        referencePath = "data/kids/out";
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
        KcxToKidsExtern kcxToKidsExtern = new KcxToKidsExtern();
        return kcxToKidsExtern.convert(message, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
