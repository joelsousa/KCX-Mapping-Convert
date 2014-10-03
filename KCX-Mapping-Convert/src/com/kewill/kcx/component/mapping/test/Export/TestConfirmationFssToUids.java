package com.kewill.kcx.component.mapping.test.Export;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestFssToUids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestConfirmationUids<br>
 * Erstellt     : 14.12.2008<br>
 * Description  : Conversion of a AMR-Confirmation message from FSS to KIDS to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
 public class TestConfirmationFssToUids extends TestFssToUids {

     public TestConfirmationFssToUids(String name) {
         super(name);
         Utils.log("(TestConfirmationFssToUids TestConfirmationFssToUids) name = " + name);
     }

     protected void setFileNames() {
//         inputFileName      = "AMR_20100727.fss";
         inputFileName      = "DE_CSF_Small";
         inputFileName      = "AUG-HEAD.xml";
         
         // referenceFileName  = "DE01DE013614201005111605006627.xml"; 
         referenceFileName  = null;
     }

     protected void runDiff(boolean split) {
         ArrayList<Difference> expected = new ArrayList<Difference>();
         expected.add(new Difference(0, 0,  0,  0));
         expected.add(new Difference(4,  4,  4, 4));
         expected.add(new Difference(7,  7,  7, 7));
         errorMessage = "Number of differences is greater than three.";
         differencesExpected = expected.size();
         expectedDifferences = expected.toArray();
         super.runDiff(true);
         
     }
}
