package com.kewill.kcx.component.mapping.test.NCTS;

import com.kewill.kcx.component.mapping.test.TestKidsToKcxFromCountry;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TestNCTSDeclarationKidsToKids<br>
 * Created		: 07.09.2010<br>
 * Description	: Conversion of an NCTSDeclaration 
 *                from KIDS to KIDS.
 *                Original and converted KIDS-Messages must be identical. 
 * 
 * @author Lassiter Caviles
 * @version 4.0.00
 */
public class TestNCTSDeclarationKidsToKids extends TestKidsToKcxFromCountry {

	public TestNCTSDeclarationKidsToKids(String name) {
		super(name);
		Utils.log("( TestNCTSDeclarationKidsToKids " + "TestNCTSDeclarationKidsToKids ) name = " + name);
	}

	@Override
	protected void setFileNames() {
		inputFileName		= "NCTSDeclaration_20100913_123407_soap.xml";
		referenceFileName	= "NCTSDeclaration_20100913_123407_soap_reference.xml";	
	}
	

}
