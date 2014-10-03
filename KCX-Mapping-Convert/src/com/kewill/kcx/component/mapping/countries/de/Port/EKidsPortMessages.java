package com.kewill.kcx.component.mapping.countries.de.Port;

/**
 * Module       : Port<br>
 * Created      : 21.10.2011<br>
 * Description	: Valid Names of KIDS-message names in Port/Import.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public enum EKidsPortMessages {

		/**
	    * Functional name:  E_DEC_DAT
	    * Technical Name:   IE015
	    * Description (en): ImportDeclaration
	    * Description (de): ImportDeclaration
	    * Direction:        Customer to customs.
    	*/
		PortDeclaration,		
		/**
		    * Functional name:  
		    * Technical Name:   
		    * Description (en): 
		    * Description (de): 
		    * Direction:        Customer to customs.
	    	*/
		
		PortDeclarationConfirmation,
		PortDeclarationRejected,
		PortDeclarationAcknowledgment,
		PortDeclarationStatus,   	//EI20120120
		localAppResult,
		ErrorMessage,            	//EI20120426
		PortDeclarationRegistration, //EI20120427
		
		ImportTaxAssessment,    	//EI20120228
		
		BillOfLading,     			//EI20120410
}
