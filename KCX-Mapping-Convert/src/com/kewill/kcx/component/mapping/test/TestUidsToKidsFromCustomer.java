package com.kewill.kcx.component.mapping.test;

import java.io.File;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KcxToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;



/**
 * Modul        : TestUidsToKidsFromCustomer<br>
 * Erstellt     : 10.05.2010<br>
 * Description  : Conversion of a message from UIDS to KIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestUidsToKidsFromCustomer extends TestOneWay {
    
    public TestUidsToKidsFromCustomer(String name) {
        super(name);
        Utils.log("(TestUidsToKidsFromCustomer TestUidsToKidsFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/uids/in";
        kidsPath      = "data/uids/in";
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
        UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
        return uidsToKids.convert(inFile, encoding);
    }

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
