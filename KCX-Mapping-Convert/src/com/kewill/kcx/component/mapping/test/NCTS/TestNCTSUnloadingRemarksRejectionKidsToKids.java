package com.kewill.kcx.component.mapping.test.NCTS;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSUnloadingRemarksRejectionKidsToKids
 * Created		: 24.08.2010
 * Description	: .
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class TestNCTSUnloadingRemarksRejectionKidsToKids extends TestKidsToKcxFromCountry {
	
	/// CONSTRUCTOR(s)
		public TestNCTSUnloadingRemarksRejectionKidsToKids(String name) {
			super(name);
			Utils.log("(TestNCTSUnloadingRemarksRejectionKidsToKids " +
					"TestNCTSUnloadingRemarksRejectionKidsToKids) name = " + name);
		}
	
	/// METHODS
		@Override
		protected void setFileNames() {
			inputFileName		= "NCTSUnloadingRemarksRejection_20100901_101758_soap.xml";
			referenceFileName	= "NCTSUnloadingRemarksRejection_20100901_101758_soap_reference.xml";
		}

}
