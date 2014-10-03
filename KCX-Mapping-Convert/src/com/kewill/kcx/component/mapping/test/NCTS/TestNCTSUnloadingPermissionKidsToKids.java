package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSUnloadingPermissionKidsToKids<br>
 * Created		: 03.09.2010<br>
 * Description	: Conversion of an NCTSUnloadingPermission 
 *                from KIDS to KIDS.
 *                Original and converted KIDS-Messages must be identical.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class TestNCTSUnloadingPermissionKidsToKids extends TestKidsToKcxFromCountry {

	public TestNCTSUnloadingPermissionKidsToKids(String name) {
		super(name);
		Utils.log("(TestNCTSUnloadingPermissionKidsToKids TestNCTSUnloadingPermissionKidsToKids) name = " + name);
	}

	@Override
	protected void setFileNames() {
		inputFileName = "UnloadingPermission_20100831_093008_soap.xml";
		referenceFileName = "UnloadingPermission_20100831_093008_soap_reference.xml";
	}

	protected void runDiff(boolean split) {
		ArrayList<Difference> expected = new ArrayList<Difference>();
		errorMessage = "Number of differences is greater than zero.";
		differencesExpected = expected.size();
		expectedDifferences = expected.toArray();
		super.runDiff(true);
	}
	
}
