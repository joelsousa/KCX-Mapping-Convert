package com.kewill.kcx.component.mapping.test;

import java.io.File;

import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToBdecExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul       : TestUidsToFssFromCustomer.java
 * Erstellt    : 16.10.2008
 * Beschreibung: Konvertierung einer Nachricht von UIDS nach KIDS und von dort nach BDEC.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public abstract class TestUidsToBdecFromCustomer extends TestTwoWay {


    public TestUidsToBdecFromCustomer(String name) {
        super(name);
        Utils.log("(TestUidsToBdecFromCustomer TestUidsToBdecFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/uids/in";
        referencePath = "data/bdec/out";
        encoding      = "UTF-8";
        
        setFileNames();
        outputExtension = "";
        
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

    public String kidsToOutput(String message, File outFile, String encoding) throws Exception {
        KidsToBdecExtern kidsToBdec = new KidsToBdecExtern();
        return kidsToBdec.convert(message, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(true);
    }
}
