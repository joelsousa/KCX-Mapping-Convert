package com.kewill.kcx.component.mapping.test.NCTS;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestArrivalRejectionKidsToKids<br>
 * Created		: 24.08.2010<br>
 * Description	: Conversion of an ArrivalRejection 
 *                from KIDS to KIDS.
 *                Original and converted KIDS-Messages must be identical.
 *                 
 * @author	: Michelle Bauza
 * @version 1.0.00
 */
public class TestNCTSArrivalRejectionKidsToKids extends TestKidsToKcxFromCountry {
	
	public TestNCTSArrivalRejectionKidsToKids(String name) {
		super(name);
		Utils.log("( TestArrivalRejectionKidsToKids " + "TestArrivalRejectionKidsToKids ) name = " + name);
	}
	
	@Override
	protected void setFileNames() {
		inputFileName		= "NCTSArrivalRejection_20100825_170151_soap.xml";
		referenceFileName	= "NCTSArrivalRejection_20100825_170151_soap_reference.xml";
	}

}
