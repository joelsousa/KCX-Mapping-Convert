package com.kewill.kcx.component.mapping.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import junit.framework.TestCase;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.test.testUtils.FileDiff;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Modul		: TestExportCAEUIDS<br>
 * Erstellt		: 27.10.2008<br>
 * Description	: Conversion of a CancelationResponse 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author messer
 * @version 1.0.00
 */
public class TestExportCAEUIDS extends TestCase {
	private String dataPath           = null;
    private String inputUidsFileName  = null;
    private String kidsFileName       = null;
    private String outputUidsFileName = null;
    
    public TestExportCAEUIDS(String name) {
        super(name);
        Utils.log("(TestExportDeclarationUids TestExportDeclarationUids) name = " + name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        dataPath = "data/uids/in";
        // inputUidsFileName  = "DE01DE01215820080930123900010.xml";
        inputUidsFileName  = "UIDSCancelationResponse01.xml";
        kidsFileName       = "kids_" + inputUidsFileName;
        outputUidsFileName = "after_" + inputUidsFileName;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUidsToKids() {
        UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
        String payload = getFileMessage(dataPath + "/" + inputUidsFileName);
        Utils.log("(TestCancelationResponseUids testUidsToKids) payload = \n" + payload);
        
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader parser = factory.createXMLEventReader(is);
        
            xml = uidsToKids.convert(new File(dataPath + "/" + inputUidsFileName), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils.log("(TestCancelationResponseUids testUidsToKids) Converted Message = \n" + xml);
        xml = xml.replaceAll("><", ">\r\n  <");
        writeFileMessage(xml, dataPath + "/" + kidsFileName);
    }

    public void testKidsToUids() {
        KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
        String payload = getFileMessage(dataPath + "/" + kidsFileName);
        Utils.log("(TestCancelationResponseUids testKidsToUids) payload = \n" + payload);
        
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader parser = factory.createXMLEventReader(is);
        
            xml = (String) kidsToUids.convert(payload, new File(dataPath + "/" + outputUidsFileName), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils.log("(TestCancelationResponseUids testKidsToUids) Converted Message = \n" + xml);
        xml = xml.replaceAll("><", ">\r\n  <");
        writeFileMessage(xml, dataPath + "/" + outputUidsFileName);
    }
    public void testRunDiff() {
        runDiff();
    }
    private static String getFileMessage(String fileName) {
        File inFile = new File(fileName);
        StringBuffer payload = new StringBuffer();
        
        try {
            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader in = new BufferedReader(isr);
            String line = null;
            line = in.readLine();
            while (line != null) {
                payload.append(line);
                line = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return payload.toString();
    }
    private void writeFileMessage(String message, String fileName) {
        Utils.log("(TestCancelationResponseUids writeFileMessage) message = " + message);
        Utils.log("(TestCancelationResponseUids writeFileMessage) fileName = " + fileName);
        File outFile = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(outFile);
            OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
            out.write(message);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void runDiff() {
        Utils.log("(TestExportDeclarationUids runDiff) \n" +
                  "# ---------- DIFFERENZEN Begin ----------\n" +
                  "# < " + inputUidsFileName + "\n" + 
                  "# > " + outputUidsFileName);
        FileDiff fileDiff = new FileDiff(dataPath + "/" + inputUidsFileName, dataPath + "/" + outputUidsFileName);
        System.out.println("# ---------- DIFFERENZEN End   ----------");
        Utils.log("(TestExportDeclarationUids runDiff) Anzahl Differenzen: " + fileDiff.getDifferenceCount());
        assertTrue("Anzahl Differenzen ist größer Zwei.", fileDiff.getDifferenceCount() < 3);
    }
}
