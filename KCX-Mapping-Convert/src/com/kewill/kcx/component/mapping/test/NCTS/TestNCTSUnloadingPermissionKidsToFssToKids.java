package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : TestNCTSUnloadingPermissionKidsToFssToKids<br>
 * Created    	: 09.09.2010<br>
 * Description	: Test for UnloadingPermission KIDS to FSS and back to KIDS
 * 				  Input and Output Kids must be identical.
 *
 * @author Frederick T 
 * @version 6.2.00
 */
public class TestNCTSUnloadingPermissionKidsToFssToKids extends TestKidsToFssToKidsFromCustomer {

	public TestNCTSUnloadingPermissionKidsToFssToKids(String name) {
        super(name);
        Utils.log(
        "(TestNCTSUnloadingPermissionKidsToFssToKids TestNCTSUnloadingPermissionKidsToFssToKids) name = " + name);
    }
	
	protected void setInputFileName() {
		inputFileName	= "UnloadingPermission_20100831_093008_soap.xml";
		encoding      	= "UTF-8";
	}

	protected void runDiff(boolean split) {
        ArrayList<Difference> expected = new ArrayList<Difference>();
        
        //Expected difference: Different timestamps in the flow rate (VOR)
        expected.add(new Difference(7, 8, 7, 8));
        expected.add(new Difference(10, 11, 10, 11));
        expected.add(new Difference(13, 13, 13, 13));
        expected.add(new Difference(15, Difference.NONE, 15, 15));
        expected.add(new Difference(41, Difference.NONE, 42, 44));
        expected.add(new Difference(43, Difference.NONE, 47, 48));
        expected.add(new Difference(44, Difference.NONE, 50, 51));
        expected.add(new Difference(49, Difference.NONE, 57, 58));
        expected.add(new Difference(51, Difference.NONE, 61, 62));
        expected.add(new Difference(57, Difference.NONE, 69, 69));
        expected.add(new Difference(61, Difference.NONE, 74, 75));
        expected.add(new Difference(62, Difference.NONE, 78, 78));
        expected.add(new Difference(70, Difference.NONE, 86, 86));
        expected.add(new Difference(74, Difference.NONE, 91, 92));
        expected.add(new Difference(76, Difference.NONE, 95, 96));
        expected.add(new Difference(82, Difference.NONE, 103, 103));
        expected.add(new Difference(84, Difference.NONE, 106, 122));
        expected.add(new Difference(85, Difference.NONE, 124, 124));
        expected.add(new Difference(86, Difference.NONE, 126, 126));
        expected.add(new Difference(89, Difference.NONE, 130, 130));
        expected.add(new Difference(92, Difference.NONE, 134, 134));
        expected.add(new Difference(97, Difference.NONE, 140, 145));
        expected.add(new Difference(98, Difference.NONE, 147, 147));
        expected.add(new Difference(102, Difference.NONE, 152, 153));
        expected.add(new Difference(103, Difference.NONE, 155, 155));
        expected.add(new Difference(106, Difference.NONE, 159, 159));
        expected.add(new Difference(108, Difference.NONE, 162, 162));
        expected.add(new Difference(111, Difference.NONE, 166, 166));
        expected.add(new Difference(114, Difference.NONE, 170, 170));
        expected.add(new Difference(116, Difference.NONE, 173, 190));
        expected.add(new Difference(119, Difference.NONE, 194, 194));
        expected.add(new Difference(121, Difference.NONE, 197, 214));
        expected.add(new Difference(128, Difference.NONE, 222, 222));
        expected.add(new Difference(130, Difference.NONE, 225, 234));
        expected.add(new Difference(135, Difference.NONE, 240, 244));
        expected.add(new Difference(136, Difference.NONE, 246, 247));
        expected.add(new Difference(142, Difference.NONE, 254, 254));
        expected.add(new Difference(144, Difference.NONE, 257, 261));
        expected.add(new Difference(145, Difference.NONE, 263, 264));
        expected.add(new Difference(151, Difference.NONE, 271, 271));
        errorMessage = "Number of differences is greater than one.";
        differencesExpected = expected.size();
        expectedDifferences = expected.toArray();
        super.runDiff(true);
    }
}
