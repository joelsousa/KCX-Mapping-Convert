package com.kewill.kcx.component.mapping.test;

import java.io.File;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;



/**
 * Modul        : TestUidsToUidsFromCustomer<br>
 * Erstellt     : 17.12.2008<br>
 * Description  : Conversion of a message from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestUidsToUidsFromCustomer extends TestTwoWay {
    
    public TestUidsToUidsFromCustomer(String name) {
        super(name);
        Utils.log("(TestUidsToUidsFromCustomer TestUidsToUidsFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath = "data/uids/in";
        referencePath = dataPath;			//20100614PT
        encoding = "UTF-8";
        
        setInputFileName();
        outputExtension = ".xml";
        referenceFileName = inputFileName;
        
        super.setUp();
    }
    
    protected abstract void setInputFileName();

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public String inputToKids(XMLEventReader parser, String payload) throws Exception {
        UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
        return uidsToKids.readUids(parser, "TestAuditId", encoding, EDirections.CustomerToCountry);
    }

    public String inputToKids(File inFile, String encoding) throws Exception {
        UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
        return uidsToKids.convert(inFile, encoding);
    }

    public String kidsToOutput(String payload) throws Exception {
        KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
        return (String) kidsToUids.readKids(payload, encoding, inputFileName, EDirections.CustomerToCountry);
    }
    
    public String kidsToOutput(String payload, File outFile, String encoding) throws Exception {
        KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
        return (String) kidsToUids.convert(payload, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(split);
    }
}
