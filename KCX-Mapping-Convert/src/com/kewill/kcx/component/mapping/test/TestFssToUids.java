/*
 * Funktion    : TestUidsToFssFromCustomer.java
 * Titel       :
 * Erstellt    : 18.12.2008
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

import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.in.FssToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestUidsToFssFromCustomer<br>
 * Erstellt     : 18.12.2008<br>
 * Description  : Conversion of a message from FSS to KIDS and then to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestFssToUids extends TestTwoWay {

    
    public TestFssToUids(String name) {
        super(name);
        Utils.log("(TestFssToUids TestFssToUids) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath        = "data/fss/in";
        kidsPath        = "data/kids/out";
        referencePath   = "data/uids/out";
        encoding        = "UTF-8";
        outputExtension = ".xml";
        
        setFileNames();
        
        super.setUp();

    }
    
    protected abstract void setFileNames();

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public String inputToKids(File inFile, String encoding) throws Exception {
        FssToKidsExtern fssToKids = new FssToKidsExtern();
        return fssToKids.convert(inFile, encoding);
    }

    public Object kidsToOutput(String payload, File outFile, String encoding) throws Exception {
        KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
        return kidsToUids.convert(payload, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(split);
    }
}
