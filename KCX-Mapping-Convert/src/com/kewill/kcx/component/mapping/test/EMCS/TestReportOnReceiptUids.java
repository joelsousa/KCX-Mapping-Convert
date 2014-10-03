/*
 * Funktion    : TestExplanationOnDelayUids.java
 * Titel       :
 * Erstellt    : 20.05.2010
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

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.RemoveKcxEnvelope;
import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.test.TestUidsToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestExplanationOnDelayUids<br>
 * Erstellt		: 20.05.2010<br>
 * Description	: Conversion of a EMCSReportOfReceipt
 *                from UIDS to KIDS .
 *                SampleEMCSReportOfReceipt.xml and SampleEMCSReportOfReceipt_reference.xml must be identical. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class TestReportOnReceiptUids extends TestUidsToKidsFromCustomer {
    
    public TestReportOnReceiptUids(String name) {
        super(name);
        Utils.log("(TestReportOnReceiptUids TestReportOnReceiptUids) name = " + name);
    }

    protected void setFileNames() {
    	inputFileName  = "SampleEMCSReportOfReceipt.xml";
    	referenceFileName = "SampleEMCSReportOfReceipt_reference.xml";
        encoding = "UTF-8";
    }
    
    public String otherToKids(BufferedReader br) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(br);
        UidsToKidsExtern uidsToKidsExtern = new UidsToKidsExtern();
        return uidsToKidsExtern.readUids(parser, "No Mule call", "UTF-8", EDirections.CustomerToCountry);
    }

    public String kidsToOther(String message, File outFile, String encoding) throws Exception {
// MS20110930 Begin        
//        KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
//        return kidsToUids.removeKcxEnvelope(message, encoding);
        RemoveKcxEnvelope removeKcxEnvelope = new RemoveKcxEnvelope();
        return removeKcxEnvelope.removeEnvelope(message, encoding);
//MS20110930 End
    }

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
