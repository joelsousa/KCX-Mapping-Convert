package com.kewill.kcx.component.mapping.test;

import java.io.File;

import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToFssExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul       : TestKidsToFssFromCustomer.java
 * Erstellt    : 16.10.2008
 * Beschreibung: Konvertierung einer KIDS-ExportDeclaration nach KIDS und 
 *               von dort nach FSS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public abstract class TestKidsToFssFromCustomer extends TestTwoWay {


    public TestKidsToFssFromCustomer(String name) {
        super(name);
        Utils.log("(TestKidsToFssFromCustomer TestKidsToFssFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/kids/in";
        referencePath = "data/fss/out";
        encoding      = "UTF-8";
        
        setFileNames();
        outputExtension = ".fss";
        
        super.setUp();
    }

    protected abstract void setFileNames();
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public String inputToKids(File inFile, String encoding) throws Exception {
        KidsToKidsExtern kidsToKids = new KidsToKidsExtern();
        return kidsToKids.convert(inFile, encoding);
    }

    public String kidsToOutput(String message, File outFile, String encoding) throws Exception {
        KidsToFssExtern kidsToFss = new KidsToFssExtern();
        return kidsToFss.convert(message, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(true);
    }
}
