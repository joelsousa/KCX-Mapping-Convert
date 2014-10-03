package com.kewill.kcx.component.mapping.countries.de.Port;

/**
 * Module       : Port<br>
 * Created      : 21.10.2011<br>
 * Description	: Valid Names of FSS-message names in Zabis Import.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public enum EFssPortMessages {
		
	/**
	    * Functional name:  
	    * Technical Name:   I
	    * Description (en): Hafenauftrag
	    * Description (de): Hafenauftrag
	    * Direction:        Customer to customs.
	*/
	POR,
		
	/**
	    * Functional name:  
	    * Technical Name:   
	    * Description (en): MinderMengenAngaben
	    * Description (de): MinderMengenAngaben
	    * Direction:        Customer to customs.
	*/
	MMA,
	
	/**
	    * Functional name:  
	    * Technical Name:   
	    * Description (en): 
	    * Description (de): Rück- / Fehlermeldungen zum Hafenauftrag 
	    * Direction:        Customs to customer.
	*/
	BZR,
	
	/**
	    * Functional name:  
	    * Technical Name:   
	    * Description (en): 
	    * Description (de): 4.	Statusmeldung zum Hafenauftrag 
	    * Direction:        Customs to customer.
	*/
	STR,
	
	
	/**
	    * Functional name:  E_DEC_DAT
	    * Technical Name:   IE015
	    * Description (en): ImportDeclarationResponse
	    * Description (de): ImportDeclarationResponse
	    * Direction:        Customer to customs.
	*/
	REC,
	ERR,
	CON,
	STI,	
	
}
