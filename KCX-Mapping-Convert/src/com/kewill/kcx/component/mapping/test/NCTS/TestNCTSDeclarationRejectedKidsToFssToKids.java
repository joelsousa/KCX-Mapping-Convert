package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestKidsToFssToKidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSDeclarationRejectedKidsToFssToKids<br>
 * Created		: 09.09.2010
 * Description	: Test for DeclarationRejected KIDS to FSS and back to KIDS
 * 				:	Input and Ouput KIDS must be identical.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class TestNCTSDeclarationRejectedKidsToFssToKids extends TestKidsToFssToKidsFromCustomer {
	
	/// CONSTRUCTOR(s)
		public TestNCTSDeclarationRejectedKidsToFssToKids(String name) {
			super(name);
			Utils.log("(TestNCTSDeclarationRejectedKidsToFssToKids " + 
					"TestNCTSDeclarationRejectedKidsToFssToKids) name = " + name);
		}
	
	/// METHODS
		@Override
		protected void setInputFileName() {
			inputFileName	= "NCTSDeclarationRejected_20100825_162413_soap.xml";
		}
		
		protected void runDiff(boolean split) {
			ArrayList< Difference > expected	= new ArrayList< Difference >();
	        //Expected difference: Different timestamps in the flow rate (VOR)
//	        expected.add(new Difference(0, 0,  0,  0));
			
			expected.add(new Difference(36, Difference.NONE, 37, 39));
			expected.add(new Difference(37, Difference.NONE, 41, 41));
			expected.add(new Difference(40, Difference.NONE, 45, 45));
			expected.add(new Difference(42, Difference.NONE, 48, 48));
			expected.add(new Difference(45, Difference.NONE, 52, 52));
	        
	        errorMessage = "Number of differences is greater than one.";
	        differencesExpected = expected.size();
	        expectedDifferences = expected.toArray();
	        super.runDiff(true);
		}

		@Override
		protected void setUp() throws Exception {
			folder	= "NCTS";
			super.setUp();
		}

}
