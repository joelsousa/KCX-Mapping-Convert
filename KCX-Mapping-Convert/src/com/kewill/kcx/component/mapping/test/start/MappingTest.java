/*
 * Funktion    : testOneWay.java
 * Titel       :
 * Erstellt    : 14.11.2008
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
package com.kewill.kcx.component.mapping.test.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import junit.framework.Assert;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.RemoveKcxEnvelope;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.test.testUtils.FileDiff;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : MappingTest<br>
 * Erstellt     : 14.11.2008<br>
 * Description  : Conversion of an input format to KIDS or vice versa. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class MappingTest {
    /**
     * Path to the input files.
     */
    protected String inPath       = null;
    
    /**
     * Path to the KIDS intermediate files.
     */
    protected String tmpPath       = null;
    
    /**
     * Path to the output files.
     */
    protected String outPath       = null;
    
    /**
     * Path to the reference files.
     */
    protected String refPath       = null;
    
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


    
    public MappingTest(String name) {
        Utils.log("(MappingTest MappingTest) name = " + name);
    }

    protected void setUp() throws Exception {
        int extensionPointPosition = inputFileName.lastIndexOf(".");
        String fileName  = "";
        String extension = "";
        if (extensionPointPosition > -1) {
            fileName  = inputFileName.substring(0, extensionPointPosition);
            extension = inputFileName.substring(extensionPointPosition);
        } else {
            fileName = inputFileName;
        }
        outputFileName = fileName + extension;
        Utils.log("(MappingTest setUp) encoding = " + encoding);
    }

    public void convert() throws FileNotFoundException {
        String inMessage = getFileMessage(inPath + "/" + inputFileName);
        inMessage = inMessage.replaceAll("><", ">\r\n  <");
        if (Config.getLogXML()) {
            Utils.log("(MappingTest convert) inMessage = \n" + inMessage);
        }

        String kcxMessage  = "";
        String kidsMessage = "";
        String outData     = "";
        String outMessage  = "";
        try {
            // Eingabedatei lesen und in KIDS incl. Code-Konvertierung und KCX-Header umwandeln
            kcxMessage = otherToKids(new File(inPath + "/" + inputFileName), encoding);
            
            // KCX-Header entfernen und KIDS-Nachricht sichern. 
            // (Nicht unbedingt notwendig für die Konvertierung)
         // kidsMessage = new KcxToKidsExtern().removeKcxEnvelope(kcxMessage, encoding, EDirections.CustomerToCountry);
            kidsMessage = new RemoveKcxEnvelope().removeEnvelope(kcxMessage, encoding);
            outData = kidsMessage.replaceAll("><", ">\r\n  <");
            writeFileMessage(outData, tmpPath + "/" + outputFileName);
            
            // In KIDS/KCX gewndelte Nachricht in das Zielformat konvertieren
            outMessage = kidsToOther(kcxMessage, new File(outPath + "/" + outputFileName), encoding);
            
            // Ausgabedatei schreiben
            outData = outMessage.replaceAll("><", ">\r\n  <");
            writeFileMessage(outData, outPath + "/" + outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (Config.getLogXML()) {
            Utils.log("(MappingTest convert) Converted Message = \n" + outMessage);
        }
    }
     
    protected abstract String otherToKids(File inFile, String encoding) throws Exception;
    protected abstract String kidsToOther(String message, File outFile, String encoding) throws Exception;

    public void testRunDiff() {
        runDiff(false);
    }
    
    private String getFileMessage(String fileName) throws FileNotFoundException {
        File inFile = new File(fileName);
        StringBuffer payload = new StringBuffer();
         
        try {
            FileInputStream fis = new FileInputStream(inFile);
            Utils.log("(MappingTest getFileMessage) encoding = " + encoding);
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
//            e.printStackTrace();
            Utils.log("(MappingTest getFileMessage) File not found: " + inFile);
            System.err.println(e.getMessage());
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } 
         
        return payload.toString();
    }
    
    private void writeFileMessage(String message, String fileName) {
        Utils.log("(MappingTest writeFileMessage) fileName = " + fileName);
        Utils.log("(MappingTest writeFileMessage) encoding = " + encoding);
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
            Utils.log("(MappingTest runDiff) Keine Referenzdatei angegeben!");
            Utils.log("(MappingTest runDiff) Diff wird nicht durchgeführt!");
            return;
        }
        Utils.log("(MappingTest runDiff) \n" +
                "# ---------- DIFFERENZEN Begin ----------\n" +
                "# < Converted: " + outPath + "/" + outputFileName + "\n" + 
                "# > Reference: " + refPath + "/" + referenceFileName);
        FileDiff fileDiff = new FileDiff(outPath + "/" + outputFileName, 
                                         refPath + "/" + referenceFileName, split);
        System.out.println("# ---------- DIFFERENZEN End   ----------");
        Utils.log("(MappingTest runDiff) Anzahl Differenzen: " + fileDiff.getDifferenceCount());
        List actualDifferences = fileDiff.getDiffs();
        for (int i = 0; i < expectedDifferences.length; i++) {
            Difference d   = (Difference) actualDifferences.get(i);
            Difference exp = (Difference) expectedDifferences[i];
            Assert.assertEquals("expected[" + i + "]", exp, d);
        }
        Assert.assertTrue(errorMessage, fileDiff.getDifferenceCount() <= differencesExpected);
    }
}
