package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestEntrySummaryDeclarationAcceptanceUids<br>
 * Created		: 2010.07.19<br>
 * Description	: Conversion of a EntrySummaryDeclarationAcceptance 
 *                from UIDS to KIDS to UIDS.
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class TestEntrySummaryDeclarationAcceptanceUids extends TestUidsToUidsFromCustomer {

	public TestEntrySummaryDeclarationAcceptanceUids(String name) {
		super(name);
		Utils.log(
		"(TestEntrySummaryDeclarationAcceptanceUids TestEntrySummaryDeclarationAcceptanceUids) name = " + name);
	}

	protected void setInputFileName() {
//		inputFileName     = "ICSEntrySummaryDeclarationAcceptance_20100715_131755_soap.xml";
		inputFileName     = "ICSDeclarationAccepted.xml";
        encoding          = "UTF-8";
	}
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        expected.add(new Difference(7, 7,  7,  7));
        expected.add(new Difference(21, Difference.NONE,  21,  21));
        expected.add(new Difference(24, Difference.NONE,  25,  25));
        expected.add(new Difference(27, Difference.NONE,  29,  30));
        expected.add(new Difference(32, Difference.NONE,  36,  40));
        expected.add(new Difference(33, Difference.NONE,  42,  44));
        expected.add(new Difference(38, Difference.NONE,  50,  50));
        expected.add(new Difference(39, Difference.NONE,  52,  52));
        expected.add(new Difference(40, Difference.NONE,  54,  61));
        expected.add(new Difference(42, Difference.NONE,  64,  68));
        expected.add(new Difference(43, Difference.NONE,  70,  72));
        expected.add(new Difference(48, Difference.NONE,  78,  78));
        expected.add(new Difference(49, Difference.NONE,  80,  80));
        expected.add(new Difference(50, Difference.NONE,  82,  89));
        expected.add(new Difference(51, Difference.NONE,  91,  118));
        expected.add(new Difference(52, Difference.NONE,  120,  124));
        expected.add(new Difference(53, Difference.NONE,  126,  128));
        expected.add(new Difference(58, Difference.NONE,  134,  134));
        expected.add(new Difference(59, Difference.NONE,  136,  136));
        expected.add(new Difference(60, Difference.NONE,  138,  145));
        expected.add(new Difference(63, 63,  149,  149));
        expected.add(new Difference(70, Difference.NONE,  156,  156));
        expected.add(new Difference(73, Difference.NONE,  160,  161));
        expected.add(new Difference(75, Difference.NONE,  164,  164));
        expected.add(new Difference(78, Difference.NONE,  168,  169));
        expected.add(new Difference(81, Difference.NONE,  173,  173));
        expected.add(new Difference(85, Difference.NONE,  178,  178));
        expected.add(new Difference(94, Difference.NONE,  188,  188));
        expected.add(new Difference(97, Difference.NONE,  192,  193));
        expected.add(new Difference(99, Difference.NONE,  196,  196));
        expected.add(new Difference(102, Difference.NONE,  200,  201));
        expected.add(new Difference(105, Difference.NONE,  205,  205));
        expected.add(new Difference(109, Difference.NONE,  210,  210));

        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
