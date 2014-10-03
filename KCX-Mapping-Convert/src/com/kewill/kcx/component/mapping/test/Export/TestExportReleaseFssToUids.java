package com.kewill.kcx.component.mapping.test.Export;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestFssToUids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestExportReleaseFssToUids<br>
 * Erstellt     : 12.05.2010<br>
 * Description  : Conversion of a ADP/ExportReleas message from FSS to KIDS to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestExportReleaseFssToUids extends TestFssToUids {
    
    public TestExportReleaseFssToUids(String name) {
        super(name);
        Utils.log("(TestExportReleaseFssToUids TestExportReleaseFssToUids) name = " + name);
    }

    protected void setFileNames() {
      inputFileName      = "adp1.fss";
      referenceFileName  = null; 
      outputExtension = ".xml";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(0, 0,  0,  0));
        expected.add(new Difference(7,  7,  7, 7));
        errorMessage = "Number of differences is greater than two.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
        
    }

}
