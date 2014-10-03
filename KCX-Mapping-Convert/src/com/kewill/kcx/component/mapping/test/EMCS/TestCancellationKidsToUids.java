package com.kewill.kcx.component.mapping.test.EMCS;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.test.TestKidsToUidsFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestCancellationUids<br>
 * Erstellt		: 12.05.2010<br>
 * Description	: Conversion of a cancellation 
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class TestCancellationKidsToUids extends TestKidsToUidsFromCountry {
    
    public TestCancellationKidsToUids(String name) {
        super(name);
        Utils.log("(TestCancellationKidsToUids TestCancellationKidsToUids) name = " + name);
    }
    
    protected void setFileNames() {
    	inputFileName  = "KIDSEMCSCancellation.xml";
    	referenceFileName = null;
        encoding = "UTF-8";
    }
    
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
