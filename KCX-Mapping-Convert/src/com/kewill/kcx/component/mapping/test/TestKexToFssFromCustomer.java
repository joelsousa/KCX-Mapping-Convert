package com.kewill.kcx.component.mapping.test;

import java.io.File;

import com.kewill.kcx.component.mapping.common.start.customer.in.KexToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToFssExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul       : TestKexToFssFromCustomer.java
 * Erstellt    : 17.11.2011
 * Beschreibung: Konvertierung einer Kewill Export (KEX) DAtei nach KIDS und von dort nach FSS.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public abstract class TestKexToFssFromCustomer extends TestTwoWay {


    public TestKexToFssFromCustomer(String name) {
        super(name);
        Utils.log("(TestKexToFssFromCustomer TestUidsToFssFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/kex/in";
        referencePath = "data/kex/ref";
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
        KexToKidsExtern inputToKids = new KexToKidsExtern();
        return inputToKids.convert(inFile, encoding);
    }

    public Object kidsToOutput(String message, File outFile, String encoding) throws Exception {
        KidsToFssExtern kidsToOutput = new KidsToFssExtern();
        return kidsToOutput.convert(message, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(true);
    }
}
