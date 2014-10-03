/*
 * Funktion    : TestExportASP.java
 * Titel       :
 * Erstellt    : 14.11.2008
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
import com.kewill.kcx.component.mapping.common.start.customs.in.FssToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestExportASPFssToKidsFromCountry<br>
 * Erstellt     : 14.11.2008<br>
 * Description  : Conversion of a ASP-Message from FSS to KIDS. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestExportASPFssToKidsFromCountry extends TestOneWay {

    public TestExportASPFssToKidsFromCountry(String name) {
        super(name);
        Utils.log("(TestExportASPFssToKidsFromCountry TestExportASPFssToKidsFromCountry) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath = "data/fss/in";
        
        encoding = "UTF-8";
        
        inputFileName  = "adi.dat";
        referenceFileName = "adi_reference.fss";
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    public String otherToKids(File inFile, String encoding) throws Exception {
        FssToKidsExtern fssToKids = new FssToKidsExtern();
        return fssToKids.convert(inFile, encoding);
    }


    @Override
    public String kidsToOther(String message, File outFile, String encoding) throws Exception {
        KcxToKidsExtern kcxToKids = new KcxToKidsExtern();
        return kcxToKids.convert(message, outFile, encoding);
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
