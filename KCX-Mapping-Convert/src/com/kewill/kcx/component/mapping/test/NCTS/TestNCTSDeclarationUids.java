package com.kewill.kcx.component.mapping.test.NCTS;

import java.io.File;
import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSDeclarationUids<br>
 * Created		: 9.01.2010<br>
 * Description	: Conversion of an NCTSDeclaration from UIDS to KIDS and back to UIDS.
 * 				  Original and converted UIDS messages must be identical.
 * 
 * @author	: Lassiter Caviles
 * @version 4.0.00
 */

public class TestNCTSDeclarationUids  extends TestUidsToUidsFromCustomer {

	
	public TestNCTSDeclarationUids(String name) {
		super(name);
        Utils.log("(TestNCTSDeclarationUids TestNCTSDeclarationUids) name = " + name);
	}
	
	protected void setInputFileName() {

		// inputFileName     = "JX66591768P030009bwck.xml";
		// inputFileName     = "CH01DE01_IRU_CHG20110324100530000000002.xml";
		// inputFileName	= "RX61131723P010029_soap2.xml";
		// inputFileName	= "IRUDE01DE0138832011040800000050CHK.xml";
		// inputFileName	= "IRUConsignorDoppelt.xml";
		// inputFileName = "DE01DE01308720110519105708000000108.xml";
		inputFileName = "DE01DE01308720110519105708000000_QA2.xml";
		inputFileName = "NCTSUnloadingRemarksDaten.xml";
		
        encoding          = "UTF-8";
	}
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        	
        expected.add(new Difference(7, 7, 7, 7));
        expected.add(new Difference(29, Difference.NONE, 29, 29));
        expected.add(new Difference(32, Difference.NONE, 33, 33));
        expected.add(new Difference(35, Difference.NONE, 37, 37));
        expected.add(new Difference(38, Difference.NONE, 41, 41));
        expected.add(new Difference(41, Difference.NONE, 45, 45));
        expected.add(new Difference(44, Difference.NONE, 49, 49));
        expected.add(new Difference(53, Difference.NONE, 59, 59));
        expected.add(new Difference(58, Difference.NONE, 65, 65));
        expected.add(new Difference(59, Difference.NONE, 67, 67));
        expected.add(new Difference(60, Difference.NONE, 69, 69));
        expected.add(new Difference(61, Difference.NONE, 71, 71));
        expected.add(new Difference(63, Difference.NONE, 74, 75));
        expected.add(new Difference(69, Difference.NONE, 82, 82));
        expected.add(new Difference(70, Difference.NONE, 84, 84));
        expected.add(new Difference(71, Difference.NONE, 86, 93));
        expected.add(new Difference(73, Difference.NONE, 96, 96));
        expected.add(new Difference(74, Difference.NONE, 98, 98));
        expected.add(new Difference(75, Difference.NONE, 100, 100));
        expected.add(new Difference(76, Difference.NONE, 102, 102));
        expected.add(new Difference(78, Difference.NONE, 105, 106));
        expected.add(new Difference(84, Difference.NONE, 113, 113));
        expected.add(new Difference(85, Difference.NONE, 115, 115));
        expected.add(new Difference(86, Difference.NONE, 117, 124));
        expected.add(new Difference(88, Difference.NONE, 127, 127));
        expected.add(new Difference(89, Difference.NONE, 129, 129));
        expected.add(new Difference(90, Difference.NONE, 131, 131));
        expected.add(new Difference(91, Difference.NONE, 133, 133));
        expected.add(new Difference(93, Difference.NONE, 136, 137));
        expected.add(new Difference(99, Difference.NONE, 144, 144));
        expected.add(new Difference(100, Difference.NONE, 146, 146));
        expected.add(new Difference(101, Difference.NONE, 148, 155));
        expected.add(new Difference(104, Difference.NONE, 159, 159));
        expected.add(new Difference(105, Difference.NONE, 161, 161));
        expected.add(new Difference(106, Difference.NONE, 163, 163));
        expected.add(new Difference(107, Difference.NONE, 165, 165));
        expected.add(new Difference(109, Difference.NONE, 168, 169));
        expected.add(new Difference(115, Difference.NONE, 176, 176));
        expected.add(new Difference(116, Difference.NONE, 178, 178));
        expected.add(new Difference(117, Difference.NONE, 180, 187));
        expected.add(new Difference(223, Difference.NONE, 294, 294));
        expected.add(new Difference(224, Difference.NONE, 296, 298));
        expected.add(new Difference(225, Difference.NONE, 300, 300));
        expected.add(new Difference(226, Difference.NONE, 302, 322));
        expected.add(new Difference(235, Difference.NONE, 332, 332));
        expected.add(new Difference(236, Difference.NONE, 334, 336));
        expected.add(new Difference(237, Difference.NONE, 338, 338));
        expected.add(new Difference(238, Difference.NONE, 340, 360));
        expected.add(new Difference(240, Difference.NONE, 363, 363));
        expected.add(new Difference(241, Difference.NONE, 365, 367));
        expected.add(new Difference(242, Difference.NONE, 369, 369));
        expected.add(new Difference(243, Difference.NONE, 371, 391));
        expected.add(new Difference(249, Difference.NONE, 398, 398));
        expected.add(new Difference(253, Difference.NONE, 403, 405));
        expected.add(new Difference(282, Difference.NONE, 435, 435));
        expected.add(new Difference(288, Difference.NONE, 442, 442));
        expected.add(new Difference(386, Difference.NONE, 541, 541));
        expected.add(new Difference(387, Difference.NONE, 543, 543));
        expected.add(new Difference(388, Difference.NONE, 545, 545));
        expected.add(new Difference(389, Difference.NONE, 547, 547));
        expected.add(new Difference(391, Difference.NONE, 550, 551));
        expected.add(new Difference(397, Difference.NONE, 558, 558));
        expected.add(new Difference(398, Difference.NONE, 560, 560));
        expected.add(new Difference(399, Difference.NONE, 562, 569));
        expected.add(new Difference(401, Difference.NONE, 572, 572));
        expected.add(new Difference(402, Difference.NONE, 574, 574));
        expected.add(new Difference(403, Difference.NONE, 576, 576));
        expected.add(new Difference(404, Difference.NONE, 578, 578));
        expected.add(new Difference(406, Difference.NONE, 581, 582));
        expected.add(new Difference(412, Difference.NONE, 589, 589));
        expected.add(new Difference(413, Difference.NONE, 591, 591));
        expected.add(new Difference(414, Difference.NONE, 593, 600));
        expected.add(new Difference(416, Difference.NONE, 603, 603));
        expected.add(new Difference(417, Difference.NONE, 605, 605));
        expected.add(new Difference(418, Difference.NONE, 607, 607));
        expected.add(new Difference(419, Difference.NONE, 609, 609));
        expected.add(new Difference(421, Difference.NONE, 612, 613));
        expected.add(new Difference(427, Difference.NONE, 620, 620));
        expected.add(new Difference(428, Difference.NONE, 622, 622));
        expected.add(new Difference(429, Difference.NONE, 624, 631));
        expected.add(new Difference(431, Difference.NONE, 634, 634));
        expected.add(new Difference(432, Difference.NONE, 636, 636));
        expected.add(new Difference(433, Difference.NONE, 638, 638));
        expected.add(new Difference(434, Difference.NONE, 640, 640));
        expected.add(new Difference(436, Difference.NONE, 643, 644));
        expected.add(new Difference(442, Difference.NONE, 651, 651));
        expected.add(new Difference(443, Difference.NONE, 653, 653));
        expected.add(new Difference(444, Difference.NONE, 655, 662));
        expected.add(new Difference(445, Difference.NONE, 664, 869));
        
//        //For IRU_Goods1CorrectedUids.xml
//        expected.add(new Difference(7, 7, 7, 7));
//        expected.add(new Difference(66, Difference.NONE, 66, 66));
//        expected.add(new Difference(68, 68, 69, 69));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }

}
