package com.kewill.kcx.component.mapping.test.Export;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestFssToKex;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TestConfirmFssToKex<br>
 * Erstellt     : 15.11.2011<br>
 * Description  : Conversion of a CON/Confirm message from FSS to KIDS to Keqill Export (KEX).
 * 
 * @author schmidt
 * @version 1.0.00
 */
 public class TestConfirmFssToKex extends TestFssToKex {

     public TestConfirmFssToKex(String name) {
         super(name);
         Utils.log("(TestConfirmFssToUids TestConfirmFssToUids) name = " + name);
     }

     protected void setFileNames() {
         inputFileName      = "con_tr.fss";
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
