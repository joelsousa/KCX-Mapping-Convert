package com.kewill.kcx.component.mapping.countries.de.Import;

/**
 * Module		: EKidsImportMessages
 * Created  	: 13.09.2011
 * Description	: Valid Names of KIDS-message names in Import.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public enum EKidsImportMessages {

		/**
	    * Functional name:  E_DEC_DAT
	    * Technical Name:   IE015
	    * Description (en): ImportDeclaration
	    * Description (de): ImportDeclaration
	    * Direction:        Customer to customs.
    	*/
		ImportDeclaration,
		
		/**
		    * Functional name:  
		    * Technical Name:   
		    * Description (en): ImportDeclarationConfirmation
		    * Description (de): ImportDeclarationConfirmation
		    * Direction:        Customer to customs.
	    	*/
		ImportDeclarationConfirmation,
		
		/**
		    * Functional name:  
		    * Technical Name:   
		    * Description (en): ImportTaxAssessment
		    * Description (de): ImportTaxAssessment
		    * Direction:        Customs to customer.
	    	*/
		ImportTaxAssessment,   //EI20120224
		
		localAppResult;        //EI20120224
}
