package com.kewill.kcx.component.mapping.test.ICS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestDeclarationAmendmentUids<br>
 * Created		: 2010.07.21<br>
 * Description	: Conversion of a DeclarationAmendment 
 *                from UIDS to KIDS to UIDS.
 * @author Lassiter Caviles
 * @version 1.0.00
 *
 */
public class TestDeclarationAmendmentUids extends TestUidsToUidsFromCustomer {
	public TestDeclarationAmendmentUids(String name) {
        super(name);
        Utils.log("(TestDeclarationAmendment TestDeclarationAmendment) name = " + name);
    }

    protected void setInputFileName() {
    	inputFileName     = "ICSDeclarationAmendment_20100715_143540_soap.xml";
        encoding          = "UTF-8";
    }

    protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7, 7, 7));
        expected.add(new Difference(27, Difference.NONE, 27, 27));
        expected.add(new Difference(30, Difference.NONE, 31, 32));
        expected.add(new Difference(36, 36, 39, 40));
        expected.add(new Difference(38,	Difference.NONE , 42, 46));
        expected.add(new Difference(39, Difference.NONE, 48, 67));
        expected.add(new Difference(41, Difference.NONE, 70, 74));
        expected.add(new Difference(42, Difference.NONE, 76, 95));
        expected.add(new Difference(44, Difference.NONE, 98, 102));
        expected.add(new Difference(45, Difference.NONE, 104, 123));
        expected.add(new Difference(47, Difference.NONE, 126, 130));
        expected.add(new Difference(48, Difference.NONE, 132, 143));
        expected.add(new Difference(57, Difference.NONE, 153, 236));
        expected.add(new Difference(59, 59, 239, 239));
        expected.add(new Difference(62, Difference.NONE, 242, 246));
        expected.add(new Difference(76, Difference.NONE, 261, 265));
        expected.add(new Difference(77, Difference.NONE, 267, 286));
        expected.add(new Difference(79, Difference.NONE, 289, 293));
        expected.add(new Difference(80, Difference.NONE, 295, 314));
        expected.add(new Difference(82, Difference.NONE, 317, 321));
        expected.add(new Difference(83, Difference.NONE, 323, 342));
        expected.add(new Difference(88, Difference.NONE, 348, 348));
        expected.add(new Difference(91, Difference.NONE, 352, 353));
        expected.add(new Difference(93, Difference.NONE, 356, 356));
        expected.add(new Difference(96, Difference.NONE, 360, 361));
        expected.add(new Difference(111, Difference.NONE, 377, 377));
        expected.add(new Difference(115, Difference.NONE, 382, 382));
        expected.add(new Difference(119, Difference.NONE, 387, 387));
        expected.add(new Difference(122, Difference.NONE, 391, 391));
        expected.add(new Difference(134, Difference.NONE, 404, 408));
        expected.add(new Difference(135, Difference.NONE, 410, 429));
        expected.add(new Difference(137, Difference.NONE, 432, 436));
        expected.add(new Difference(138, Difference.NONE, 438, 457));
        expected.add(new Difference(140, Difference.NONE, 460, 464));
        expected.add(new Difference(141, Difference.NONE, 466, 485));
        expected.add(new Difference(146, Difference.NONE, 491, 491));
        expected.add(new Difference(149, Difference.NONE, 495, 496));
        expected.add(new Difference(151, Difference.NONE, 499, 499));
        expected.add(new Difference(154, Difference.NONE, 503, 504));
        expected.add(new Difference(169, Difference.NONE, 520, 520));
        expected.add(new Difference(173, Difference.NONE, 525, 525));
        expected.add(new Difference(177, Difference.NONE, 530, 530));
        expected.add(new Difference(180, Difference.NONE, 534, 534));

        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
}
