package com.kewill.kcx.component.mapping.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.countries.nl.aes.KidsToKidsNl;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestExportDeclarationKids<br>
 * Erstellt		: 04.12.2008<br>
 * Beschreibung	: Convert KIDS message from customer to KIDS message for country. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestKidsToKidsNlFromCustomer extends TestOneWay {

    public TestKidsToKidsNlFromCustomer(String name) {
        super(name);
        Utils.log("(TestKidsToKidsNlFromCustomer TestKidsToKidsNlFromCustomer) name = " + name);
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
        KidsToKidsNl kcxToKidsNl = new KidsToKidsNl();
        return kcxToKidsNl.readKids(message, encoding, "Dummy Audit-ID");
    }
    
    
    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        errorMessage = "Number of differences is greater than zero.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
