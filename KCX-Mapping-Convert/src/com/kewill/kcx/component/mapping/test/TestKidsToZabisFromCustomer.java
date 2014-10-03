package com.kewill.kcx.component.mapping.test;

import java.io.File;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToZabisExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestKidsToZabisFromCustomer<br>
 * Erstellt		: 07.12.2010<br>
 * Beschreibung	: Convert KIDS message from customer to ZABIS message for country DE. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestKidsToZabisFromCustomer extends TestOneWay {

    public TestKidsToZabisFromCustomer(String name) {
        super(name);
        Utils.log("(TestKidsToZabisFromCustomer TestKidsToZabisFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/kids/in";
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
        KidsToZabisExtern kidsToZabisExtern = new KidsToZabisExtern();
        return kidsToZabisExtern.convert(message, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
