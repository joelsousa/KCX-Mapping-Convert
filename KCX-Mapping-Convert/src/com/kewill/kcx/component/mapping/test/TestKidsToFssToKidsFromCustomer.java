package com.kewill.kcx.component.mapping.test;

import java.io.File;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.in.FssToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToFssExtern;
import com.kewill.kcx.component.mapping.util.Utils;



/**
 * Modul        : TestKidsToFssToKidsFromCustomer<br>
 * Erstellt     : 17.12.2008<br>
 * Description  : Conversion of a message from KIDS to FSS and back to KIDS.
 *                Original and converted KIDS-Messages must be identical. 
 * 
 * @author Edwin Bautista
 * @version 6.2.00
 */
public abstract class TestKidsToFssToKidsFromCustomer extends TestTwoWayFss {
    
	/**
	 * Field for setting folder of Input KIDS xml, to make datapath generic to all.
	 */
	protected String folder = "";
	
    public TestKidsToFssToKidsFromCustomer(String name) {
        super(name);
        Utils.log("(TestKidsToFssToKidsFromCustomer TestKidsToFssToKidsFromCustomer) name = " + name);
    }

    protected void setUp() throws Exception {
        
    	if (folder.length() > 0) {
        	dataPath = "data/fss/in/" + folder + "/";
    	} else {
    		dataPath = "data/fss/in/";
        }
        referencePath = dataPath;
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

    public String inputToFss(XMLEventReader parser, String payload) throws Exception {
        KidsToFssExtern kidsToFss = new KidsToFssExtern();
        return kidsToFss.readKids(payload, encoding, EDirections.CustomerToCountry);
    }

    public String inputToFss(File inFile, String encoding) throws Exception {
    	KidsToKidsExtern kidsToKids = new KidsToKidsExtern();
        String msg = kidsToKids.convert(inFile, encoding);
        
    	KidsToFssExtern kidsToFss = new KidsToFssExtern();
        return kidsToFss.convert(msg, new File(dataPath + "/" + fssFileName), encoding);
    }

    public String fssToOutput(String payload) throws Exception {
        FssToKidsExtern fssToKids = new FssToKidsExtern();
        return (String) fssToKids.readFss(payload, outputFileName, encoding, EDirections.CustomerToCountry);
    }
    
    public String fssToOutput(String payload, File outFile, String encoding) throws Exception {
    	FssToKidsExtern fssToKids = new FssToKidsExtern();
        return (String) fssToKids.convert(payload, outFile, encoding);
    }
    
    protected void runDiff(boolean split) {
        super.runDiff(split);
    }

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
