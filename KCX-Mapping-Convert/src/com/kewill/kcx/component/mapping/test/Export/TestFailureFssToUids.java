package com.kewill.kcx.component.mapping.test.Export;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestFssToUids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestFailureFssToUids<br>
 * Erstellt     : 12.05.2010<br>
 * Description  : Conversion of an ERR/Failure message from FSS to KIDS to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
 public class TestFailureFssToUids extends TestFssToUids {

     public TestFailureFssToUids(String name) {
         super(name);
         Utils.log("(TestFailureFssToUids TestFailureFssToUids) name = " + name);
     }

     protected void setFileNames() {
         inputFileName      = "DE01DE01215820100618152400000502.xml";
//         referenceFileName  = "DE01DE01215820100618152400000502.xml"; 
         referenceFileName  = null; 
         outputExtension = ".xml";
     }

     protected void runDiff(boolean split) {
         ArrayList<Difference> expected = new ArrayList<Difference>();
         errorMessage = "Number of differences is greater than zero.";
         differencesExpected = expected.size();
         expectedDifferences = expected.toArray();
         super.runDiff(true);
         
     }
}
