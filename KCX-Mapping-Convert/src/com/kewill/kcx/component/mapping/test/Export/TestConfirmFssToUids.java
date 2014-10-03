package com.kewill.kcx.component.mapping.test.Export;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestFssToUids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestConfirmFssToUids<br>
 * Erstellt     : 11.05.2010<br>
 * Description  : Conversion of a CON/Confirm message from FSS to KIDS to UIDS.
 * 
 * @author schmidt
 * @version 1.0.00
 */
 public class TestConfirmFssToUids extends TestFssToUids {

     public TestConfirmFssToUids(String name) {
         super(name);
         Utils.log("(TestConfirmFssToUids TestConfirmFssToUids) name = " + name);
     }

     protected void setFileNames() {
//         inputFileName      = "MSTEST";
         inputFileName      = "DE01DE01361420100511160301646.xml";
         referenceFileName  = "DE01DE01361420100511160301646.xml"; 
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
