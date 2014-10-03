package com.kewill.kcx.component.mapping.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import junit.framework.TestCase;

import com.kewill.kcx.component.mapping.common.start.RemoveKcxEnvelope;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestRemoveKcxEnvelope<br>
 * Erstellt     : 29.09.2011<br>
 * Description  : Test that a message can be processed even if the KCX envelope is missing. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestRemoveKcxEnvelope extends TestCase {

    /**
     * Path to the input and output files.
     */
    protected String dataPath       = null;
    
    /**
     * Path to the KIDS intermediate file.
     */
    protected String kidsPath       = "data/kids/out";
    
    /**
     * Name of the input file.
     */
    protected String inputFileName  = null;
    
    /**
     * Encoding der Eingabedatei.
     */
    protected String encoding = null;

    /**
     * Name of the output file.
     */
    protected String outputFileName = null;
     
    public TestRemoveKcxEnvelope(String name) {
        super(name);
        Utils.log("(TestRemoveKcxEnvelope TestRemoveKcxEnvelope) name = " + name);
    }

    protected void setUp() throws Exception {
        dataPath      = "data/kids/in";
        kidsPath      = "data/kids/out";
        
        setFileNames();
        
        encoding      = "UTF-8";
        
        super.setUp();
    }
    
    protected void setFileNames() {
//        inputFileName       = "KidsMessageWithKcxEnvelope.xml";
        inputFileName       = "KidsMessageWithoutKcxEnvelope.xml";
        
        outputFileName      = inputFileName;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConvert() throws FileNotFoundException {
        String inMessage = getFileMessage(dataPath + "/" + inputFileName);
        inMessage = inMessage.replaceAll("><", ">\r\n  <");
        Utils.log("(TestRemoveKcxEnvelope TestRemoveKcxEnvelope) inMessage = \n" + inMessage);

        String kidsMessage = "";
        String outMessage  = "";
        try {
            // KCX-Header entfernen und KIDS-Nachricht sichern. 
         // kidsMessage = new KcxToKidsExtern().removeKcxEnvelope(inMessage, encoding, EDirections.CustomerToCountry);
            kidsMessage = new RemoveKcxEnvelope().removeEnvelope(inMessage, encoding);
            outMessage = kidsMessage.replaceAll("><", ">\r\n  <");
            writeFileMessage(outMessage, kidsPath + "/" + outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utils.log("(TestOneWay testOtherToKids) Converted Message = \n" + outMessage);
    }

    private String getFileMessage(String fileName) throws FileNotFoundException {
        File inFile = new File(fileName);
        StringBuffer payload = new StringBuffer();
         
        try {
            FileInputStream fis = new FileInputStream(inFile);
            Utils.log("(TestOneWay getFileMessage) encoding = " + encoding);
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
            Utils.log("(TestOneWay getFileMessage) File not fount: " + inFile);
            System.err.println(e.getMessage());
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } 
         
        return payload.toString();
    }
    
    private void writeFileMessage(String message, String fileName) {
        Utils.log("(TestOneWay writeFileMessage) fileName = " + fileName);
        Utils.log("(TestOneWay writeFileMessage) encoding = " + encoding);
        File outFile = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(outFile);
//            OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
//            OutputStreamWriter out = new OutputStreamWriter(fos);
            OutputStreamWriter out = new OutputStreamWriter(fos, encoding);
            out.write(message);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
}

