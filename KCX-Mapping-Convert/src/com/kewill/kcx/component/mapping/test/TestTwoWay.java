/*
 * Funktion    : TestTwoWay.java
 * Titel       :
 * Erstellt    : 28.10.2008
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import junit.framework.TestCase;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.test.testUtils.FileDiff;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestTwoWay<br>
 * Erstellt     : 28.10.2008<br>
 * Description  : Conversion of an input format to KIDS and back to the input format. 
 *                Original and converted Messages must be identical. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class TestTwoWay extends TestCase {
    /**
     * Path to the input and output files.
     */
    protected String dataPath       = null;
    
    /**
     * Path to the KIDS intermediate file.
     */
    protected String kidsPath       = null;
    
    /**
     * Path to the reference file.
     */
    protected String referencePath       = null;
    
    /**
     * Name of the reference file.
     * The output file must be equal to the reference file.
     */
    protected String referenceFileName  = null;
    
    /**
     * Name of the input file.
     */
    protected String inputFileName  = null;
    
    /**
     * Name of the intermediate output file in KIDS format.
     */
    protected String kidsFileName   = null;
    
    /**
     * Name of the output file.
     */
    protected String outputFileName = null;
    
    /**
     * Extension of the output file.
     */
    protected String outputExtension = null;
     
    /**
     * Expected number of differences between input and output file.
     */
    protected int differencesExpected = 0;
    
    /**
     * Error message if more than <code>expectedDifferences</code> are found.
     */
    protected String errorMessage = null;
    
    /**
     * Array of expected differences as Difference objects.
     */
    protected Object[] expectedDifferences = null;

    /**
     * Encoding of the input file.
     */
    protected String encoding = null;

    
    public TestTwoWay(String name) {
        super(name);
        Utils.log("(TestTwoWay TestTwoWay) name = " + name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        int extensionPointPosition = inputFileName.lastIndexOf(".");
        String fileName  = "";
        if (extensionPointPosition > -1) {
            fileName  = inputFileName.substring(0, extensionPointPosition);
        } else {
            fileName = inputFileName;
        }
        kidsFileName   = fileName + "_kids.xml";
        outputFileName = fileName + "_after" + outputExtension;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInputToKids() throws Exception {
        String xml = "";
        try {
            xml = inputToKids(new File(dataPath + "/" + inputFileName), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        xml = xml.replaceAll("><", ">\r\n  <");
        writeFileMessage(xml, dataPath + "/" + kidsFileName, encoding);
    }
     
    protected abstract String inputToKids(File inFile, String encoding) throws Exception;
     
    public void testKidsToOutput() throws Exception {
        String payload = getFileMessage(dataPath + "/" + kidsFileName);
         
        Object mappingResult = null;
        try {
            Utils.log("(TestTwoWay testKidsToOutput) outputFileName = " + outputFileName);
            mappingResult = kidsToOutput(payload, new File(dataPath + "/" + outputFileName), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String xml = null;
        if (mappingResult instanceof String) {
            xml = (String) mappingResult;
        } else {
            xml = getFileMessage(Config.getPdfpath() + "/" + outputFileName);
        }
        
        xml = xml.replaceAll("><", ">\r\n  <");
        Utils.log("(TestTwoWay testKidsToOutput) Converted Message = \n" + xml);
        writeFileMessage(xml, dataPath + "/" + outputFileName, encoding);
    }
     
//    protected abstract String kidsToOutput(String payload, File outFile, String encoding) throws Exception;
    protected abstract Object kidsToOutput(String payload, File outFile, String encoding) throws Exception;

    public void testRunDiff() {
        runDiff(false);
    }
    
    private String getFileMessage(String fileName) throws Exception {
        Utils.log("(TestTwoWay getFileMessage) fileName = " + fileName);
        File inFile = new File(fileName);
        StringBuffer payload = new StringBuffer();
         
        try {
            FileInputStream fis = new FileInputStream(inFile);
            Utils.log("(TestTwoWay getFileMessage) encoding = " + encoding);
            InputStreamReader isr = null;
            if (encoding == null) {
                isr = new InputStreamReader(fis);
            } else {
                isr = new InputStreamReader(fis, encoding);
            }
            BufferedReader in = new BufferedReader(isr);
            String line = null;
            line = in.readLine();
            while (line != null) {
                payload.append(line);
                payload.append(Utils.LF);
                line = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } 
         
        return payload.toString();
    }
    
    private void writeFileMessage(String message, String fileName, String encoding) {
        Utils.log("(TestTwoWay writeFileMessage) fileName = " + fileName);
        File outFile = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(outFile);
            OutputStreamWriter out = new OutputStreamWriter(fos, encoding);
            out.write(message);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    protected void runDiff(boolean split) {
        if (referenceFileName == null) {
            Utils.log("(TestTwoWay runDiff) Keine Referenzdatei angegeben!");
            Utils.log("(TestTwoWay runDiff) Diff wird nicht durchgeführt!");
            return;
        }
        Utils.log("(TestTwoWay runDiff) \n" +
                "# ---------- DIFFERENZEN Begin ----------\n" +
                "# < Converted: " + dataPath + "/" + outputFileName + "\n" + 
                "# > Reference: " + referencePath + "/" + referenceFileName);
        if (referenceFileName == null) {
            Utils.log("(TestTwoWay runDiff) Reference file is null. No comparison possible.");
            return;
        }
        FileDiff fileDiff = new FileDiff(dataPath + "/" + outputFileName, 
                                         referencePath + "/" + referenceFileName, split);
        System.out.println("# ---------- DIFFERENZEN End   ----------");
        int differenceCount = fileDiff.getDifferenceCount();
        Utils.log("(TestTwoWay runDiff) Anzahl Differenzen: " + differenceCount);
        List actualDifferences = fileDiff.getDiffs();
        if (differenceCount > 0) {
            for (int i = 0; i < expectedDifferences.length; i++) {
                Difference d   = (Difference) actualDifferences.get(i);
                Difference exp = (Difference) expectedDifferences[i];
                assertEquals("expected[" + i + "]", exp, d);
            }
        }
        assertTrue(errorMessage, fileDiff.getDifferenceCount() <= differencesExpected);
    }
}
