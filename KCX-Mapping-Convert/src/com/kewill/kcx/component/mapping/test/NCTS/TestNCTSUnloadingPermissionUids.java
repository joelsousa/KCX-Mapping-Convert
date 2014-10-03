package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSUnloadingPermissionUids<br>
 * Created		: 02.09.2010<br>
 * Description	: Conversion of an NCTSUnloadingPermission from UIDS to KIDS and back to UIDS.
 * 				  Original and converted UIDS messages must be identical.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class TestNCTSUnloadingPermissionUids extends TestUidsToUidsFromCustomer {

	public TestNCTSUnloadingPermissionUids(String name) {
		super(name);
        Utils.log("(TestNCTSUnloadingPermissionUids TestNCTSUnloadingPermissionUids) name = " + name);
	}

	@Override
	protected void setInputFileName() {
		inputFileName     = "NCTSUnloadingPermission_20100831_093327_soap.xml";
        encoding          = "UTF-8";
	}
	
	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        expected.add(new Difference(7, 7, 7, 7));
        expected.add(new Difference(24,  Difference.NONE, 24, 24));
        expected.add(new Difference(27,  Difference.NONE, 28, 28));
        expected.add(new Difference(38,  Difference.NONE, 40, 40));
        expected.add(new Difference(39,  Difference.NONE, 42, 42));
        expected.add(new Difference(40,  Difference.NONE, 44, 44));
        expected.add(new Difference(41,  Difference.NONE, 46, 46));
        expected.add(new Difference(43,  Difference.NONE, 49, 50));
        expected.add(new Difference(49,  Difference.NONE, 57, 59));
        expected.add(new Difference(50,  Difference.NONE, 61, 68));
        expected.add(new Difference(52,  Difference.NONE, 71, 71));
        expected.add(new Difference(53,  Difference.NONE, 73, 73));
        expected.add(new Difference(54,  Difference.NONE, 75, 75));
        expected.add(new Difference(55,  Difference.NONE, 77, 77));
        expected.add(new Difference(57,  Difference.NONE, 80, 81));
        expected.add(new Difference(63,  Difference.NONE, 88, 90));
        expected.add(new Difference(64,  Difference.NONE, 92, 99));
        expected.add(new Difference(66,  Difference.NONE, 102, 102));
        expected.add(new Difference(67,  Difference.NONE, 104, 104));
        expected.add(new Difference(68,  Difference.NONE, 106, 106));
        expected.add(new Difference(69,  Difference.NONE, 108, 108));
        expected.add(new Difference(71,  Difference.NONE, 111, 112));
        expected.add(new Difference(77,  Difference.NONE, 119, 121));
        expected.add(new Difference(78,  Difference.NONE, 123, 130));
        expected.add(new Difference(80,  Difference.NONE, 133, 133));
        expected.add(new Difference(81,  Difference.NONE, 135, 135));
        expected.add(new Difference(82,  Difference.NONE, 137, 137));
        expected.add(new Difference(83,  Difference.NONE, 139, 139));
        expected.add(new Difference(85,  Difference.NONE, 142, 143));
        expected.add(new Difference(91,  Difference.NONE, 150, 152));
        expected.add(new Difference(92,  Difference.NONE, 154, 161));
        expected.add(new Difference(108,  Difference.NONE, 178, 178));
        expected.add(new Difference(109,  Difference.NONE, 180, 180));
        expected.add(new Difference(110,  Difference.NONE, 182, 182));
        expected.add(new Difference(111,  Difference.NONE, 184, 184));
        expected.add(new Difference(113,  Difference.NONE, 187, 188));
        expected.add(new Difference(119,  Difference.NONE, 195, 197));
        expected.add(new Difference(120,  Difference.NONE, 199, 206));
        expected.add(new Difference(122,  Difference.NONE, 209, 209));
        expected.add(new Difference(123,  Difference.NONE, 211, 211));
        expected.add(new Difference(124,  Difference.NONE, 213, 213));
        expected.add(new Difference(125,  Difference.NONE, 215, 215));
        expected.add(new Difference(127,  Difference.NONE, 218, 219));
        expected.add(new Difference(133,  Difference.NONE, 226, 228));
        expected.add(new Difference(134,  Difference.NONE, 230, 237));
        expected.add(new Difference(140,  Difference.NONE, 244, 244));
        expected.add(new Difference(146,  Difference.NONE, 251, 251));
        
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        errorMessage = String.format("Number of differences is greater than %d.",
        		differencesExpected);
        super.runDiff(true);
    }
	
}
