package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestEntrySummaryDeclarationAcknowledgmentUids<br>
 * Erstellt		: 18.06.2010<br>
 * Description	: Conversion of a EntrySummaryDeclarationAcknowledgment
 *                from UIDS to KIDS and back to UIDS.
 *                Original and converted UIDS-Messages must be identical.
 *
 * @author Pete T
 * @version 1.0.00
 */
public class TestEntrySummaryDeclarationAcknowledgmentUids extends TestUidsToUidsFromCustomer {

    public TestEntrySummaryDeclarationAcknowledgmentUids(String name) {
        super(name);
        Utils.log("(TestEntrySummaryDeclarationAcknowledgmentUids " +
        		"TestEntrySummaryDeclarationAcknowledgmentUids) name = " + name);
    }

    protected void setInputFileName() {
    	// inputFileName     = "ICSAcceptance_20100618_140307_soap.xml";
    	inputFileName     = "CZ_UIDS_ICSDeclarationAmendmentAccepted2.xml";
    	
        encoding          = "UTF-8";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(7, 7,  7, 7));
        expected.add(new Difference(21, Difference.NONE,  21,  21));
        expected.add(new Difference(24, Difference.NONE,  25,  25));
        expected.add(new Difference(27, Difference.NONE,  29,  30));
        expected.add(new Difference(32, Difference.NONE,  36,  40));
        expected.add(new Difference(33, Difference.NONE,  42,  43));
        expected.add(new Difference(38, Difference.NONE,  49,  49));
        expected.add(new Difference(39, Difference.NONE,  51,  51));
        expected.add(new Difference(40, Difference.NONE,  53,  60));
        expected.add(new Difference(42, Difference.NONE,  63,  67));
        expected.add(new Difference(43, Difference.NONE,  69,  70));
        expected.add(new Difference(48, Difference.NONE,  76,  76));
        expected.add(new Difference(49, Difference.NONE,  78,  78));
        expected.add(new Difference(50, Difference.NONE,  80,  87));
        expected.add(new Difference(51, Difference.NONE,  89,  115));
        expected.add(new Difference(52, Difference.NONE,  117,  121));
        expected.add(new Difference(53, Difference.NONE,  123,  124));
        expected.add(new Difference(58, Difference.NONE,  130,  130));
        expected.add(new Difference(59, Difference.NONE,  132,  132));
        expected.add(new Difference(60, Difference.NONE,  134,  141));
        expected.add(new Difference(70, Difference.NONE,  152,  152));
        expected.add(new Difference(73, Difference.NONE,  156,  157));
        expected.add(new Difference(75, Difference.NONE,  160,  160));
        expected.add(new Difference(78, Difference.NONE,  164,  165));
        expected.add(new Difference(81, Difference.NONE,  169,  169));
        expected.add(new Difference(85, Difference.NONE,  174,  174));
        expected.add(new Difference(94, Difference.NONE,  184,  184));
        expected.add(new Difference(97, Difference.NONE,  188,  189));
        expected.add(new Difference(99, Difference.NONE,  192,  192));
        expected.add(new Difference(102, Difference.NONE,  196,  197));
        expected.add(new Difference(105, Difference.NONE,  201,  201));
        expected.add(new Difference(109, Difference.NONE,  206,  206));

        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
