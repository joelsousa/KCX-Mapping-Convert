package com.kewill.kcx.component.mapping.test;

import java.io.File;

import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul       : TestUidsToFssFromCustomer.java
 * Erstellt    : 16.10.2008
 * Beschreibung: Konvertierung einer UIDS-ExportDeclaration nach KIDS und 
 *               von dort nach UIDS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public abstract class TestUidsToFssFromCustomer extends TestTwoWay {


    public TestUidsToFssFromCustomer(String name) {
        super(name);
        Utils.log("(TestUidsToFssFromCustomer TestUidsToFssFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/uids/in";
        referencePath = "data/uids/out";
        encoding      = "UTF-8";
        
        setFileNames();
        outputExtension = ".xml";
        
        super.setUp();
    }

    protected abstract void setFileNames();
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public String inputToKids(File inFile, String encoding) throws Exception {
        UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
        return uidsToKids.convert(inFile, encoding);
    }

    public Object kidsToOutput(String message, File outFile, String encoding) throws Exception {
        KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
        return kidsToUids.convert(message, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(true);
    }
}
