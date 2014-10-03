package com.kewill.kcx.component.mapping.test.Export;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestFssToUids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestExportDeclarationResponseFssToUids<br>
 * Erstellt     : 14.12.2008<br>
 * Description  : Conversion of an ASP/ExportDeclarationResponse message from FSS to KIDS to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestExportDeclarationResponseFssToUids extends TestFssToUids {
    
    public TestExportDeclarationResponseFssToUids(String name) {
        super(name);
        Utils.log("(TestExportDeclarationResponseFssToUids TestExportDeclarationResponseFssToUids) name = " + name);
    }

    protected void setFileNames() {
      inputFileName      = "DE01DE013614201005111604006621.xml";
      referenceFileName  = "DE01DE013614201005111604006621.xml"; 
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
