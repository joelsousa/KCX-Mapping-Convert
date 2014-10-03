/*
 * Funktion    : TestDeclarationUids.java
 * Titel       :
 * Erstellt    : 12.05.2010
 * Author      : krzoska
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
package com.kewill.kcx.component.mapping.test.EMCS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestDeclarationUids<br>
 * Erstellt		: 12.05.2010<br>
 * Description	: Conversion of a ExportDeclaration 
 *                from UIDS to KIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestDeclarationUids extends TestUidsToKidsFromCustomer {
    
    public TestDeclarationUids(String name) {
        super(name);
        Utils.log("(TestDeclarationUids TestDeclarationUids) name = " + name);
    }

    protected void setFileNames() {
//    	inputFileName  = "SampleEMCSDeclaration1.xml";
//    	referenceFileName = "SampleEMCSDeclaration1_reference.xml";
        // inputFileName  = "DE01DE0121810000000000647782.XML";
        // referenceFileName = "DE01DE0121810000000000647782.XML";
        inputFileName  = "DE01DE01215820100528110500000001.XML";
        referenceFileName = null;
        encoding = "UTF-8";
    }
    
//    public String otherToKids(BufferedReader br) throws Exception {
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(br);
//        UidsToKidsExtern uidsToKidsExtern = new UidsToKidsExtern();
//        return uidsToKidsExtern.readUids(parser, "No Mule call", "UTF-8", EDirections.CustomerToCountry);
//    }
//
//    public String kidsToOther(String message, File outFile, String encoding) throws Exception {
//        KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
//        return kidsToUids.removeKcxEnvelope(message, encoding);
//    }

//    public String kidsToOther(String payload) {
//        KcxToKids kcxToKids = new KcxToKids();
//        return kcxToKids.readUids(parser, "No Mule call", "UTF-8", EDirections.CustomerToCountry);
//    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(0, 0,  0,  0));
        expected.add(new Difference(3,  3,  3, 3));
        expected.add(new Difference(7,  7,  7, 7));
        errorMessage = "Number of differences is greater than three.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }
}
