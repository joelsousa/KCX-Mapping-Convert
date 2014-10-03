package com.kewill.kcx.component.mapping.test;

import java.io.File;

import com.kewill.kcx.component.mapping.common.start.customer.out.KcxToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.in.FssToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestFssToKex<br>
 * Erstellt     : 15.11.2011<br>
 * Description  : Conversion of a message from FSS to KIDS and then to Kewill Export (KEX).
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestFssToKex extends TestTwoWay {

    
    public TestFssToKex(String name) {
        super(name);
        Utils.log("(TestFssToKex TestFssToKex) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath        = "data/fss/in";
        kidsPath        = "data/kids/out";
        referencePath   = "data/kex/out";
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
        KcxToKidsExtern kidsToKex = new KcxToKidsExtern();
        return kidsToKex.convert(payload, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(split);
    }
}
