package com.kewill.kcx.component.mapping.countries.de.Port;

/**
 * Module       : Port<br>
 * Created      : 21.10.2011<br>
 * Description	: Valid Names of KIDS-message names in Import.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public enum EKffMessages {

		/**
	    * Functional name:  Job_EXP
	    * Technical Name:   
	    * Description (en): PortDeclaration
	    * Description (de): PortDeclaration
	    * Direction:        Customer to customs.
    	*/
		POR, PORT,
		
		/**
		    * Functional name:  E_DEC_DAT
		    * Technical Name:   IE015
		    * Description (en): ImportDeclarationConfirmation
		    * Description (de): ImportDeclarationConfirmation
		    * Direction:        Customer to customs.
	    	*/
		PortDeclarationConfirmation,
		 
		/**
		    * Functional name:  Job_EXP
		    * Technical Name:   
		    * Description (en): BillOfLading
		    * Description (de): BillOfLading
		    * Direction:        Customer to Reeder.
	    	*/
		BL,             //EI20120410	

}
