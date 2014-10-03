

/*
 * Funktion    : TestExportADPFssToKids.java
 * Titel       :
 * Erstellt    : 23.01.2009
 * Author      : CSF GmbH / Christine Kron
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
 * Modul        : TestExportADPFssToKids<br>
 * Erstellt     : 23.01.2009<br>
 * Description  : Conversion of a ADP-Message from FSS to KIDS. 
 * 
 * @author kron
 * @version 1.0.00
 */
public class TestExportADPFssToKids extends TestOneWay {

    public TestExportADPFssToKids(String name) {
        super(name);
        Utils.log("(TestExportADPFssToKids TestExportADPFssToKids) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath = "data/fss/in";
        referencePath = "data/fss/in";
        encoding = "UTF-8";
        
        // MS old inputFileName  = "DE01DE012186200905121358001040.xml";
        // Test CK am 4.6.2010
        // inputFileName  = "DE01DE013356201005141152001817.xml";
        inputFileName  = "ASP_TeilsatzASPvollst.txt";
        referenceFileName = "ADPPDF_TEST_REF.UIDS";
        
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
