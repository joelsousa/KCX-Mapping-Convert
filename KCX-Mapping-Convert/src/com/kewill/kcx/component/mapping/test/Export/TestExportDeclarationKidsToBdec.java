package com.kewill.kcx.component.mapping.test.Export;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToBdecFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul       : TestExportDeclarationKidsToBdec.java
 * Erstellt    : 16.10.2008
 * Beschreibung: Conversion of an ExportDeclaration from KIDS to KIDS to BDEC.
 *               Das Ergebniss muss gleich einer zur Eingangsdatei passenden Referenzdatei sein.
 *               Die Referenzdatei wird nicht automatisch generiert sondern muss
 *               auf andere Art und Weise zur Verfügung gestellt werden.
 *
 * @author schmidt 
 * @version 1.0.00
 */
public class TestExportDeclarationKidsToBdec extends TestKidsToBdecFromCustomer {

    public TestExportDeclarationKidsToBdec(String name) {
        super(name);
        Utils.log("(TestExportDeclarationKidsToBdec TestExportDeclarationKidsToBdec) name = " + name);
    }

    protected void setFileNames() {
        inputFileName       = "KIDSUK215820100204110700038.xml";
        referenceFileName   = "KIDSUK215820100204110700038.bdec";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        // 1. erwartete Differenz: Unterschiedlicher Zeitstempel im Vorlaufsatz (VOR)
        expected.add(new Difference(0, 0,  0,  0));
        
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
