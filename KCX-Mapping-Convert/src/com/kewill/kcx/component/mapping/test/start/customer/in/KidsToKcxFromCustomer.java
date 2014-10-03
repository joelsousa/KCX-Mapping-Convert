package com.kewill.kcx.component.mapping.test.start.customer.in;

import java.io.File;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KcxToKidsExtern;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.test.start.MappingTest;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestExportDeclarationKids<br>
 * Erstellt		: 04.12.2008<br>
 * Beschreibung	: Convert KIDS message from customer to KIDS message for country. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToKcxFromCustomer extends MappingTest {

    public KidsToKcxFromCustomer(String name) throws Exception {
        super(name);
        Utils.log("(KidsToKcxFromCustomer KidsToKcxFromCustomer) name = " + name);
        setUp(name);
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            Utils.log("(KidsToKcxFromCustomer main) Es muss eine Eingabedatei angegeben werden.");
            System.exit(0);
        }
        
        Config.configure("conf", "kcx.ini");
        
        KidsToKcxFromCustomer mapper = new KidsToKcxFromCustomer(args[0]);
        mapper.convert();
        mapper.runDiff(true);
    }

    protected void setUp(String name) throws Exception {
        inPath      = "testdata/KIDS/in";
        tmpPath     = "testdata/KIDS/tmp";
        outPath     = "testdata/KIDS/out";
        refPath     = "testdata/KIDS/ref";
        encoding    = "UTF-8";

        setFileNames(name);
        
        super.setUp();
    }

    protected void setFileNames(String name) {
      inputFileName       = name;
      referenceFileName   = name;
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
        super.runDiff(split);
        
    }
}
