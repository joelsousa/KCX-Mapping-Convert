/*
 * Funktion    : EProcedures.java
 * Titel       :
 * Erstellt    : 03.11.2008
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      : Christine Kron
 * Datum       : 03.09.2010
 * Kennzeichen :
 * Beschreibung: added ICS and NCTS and Java Docs
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping;

/**
 * Modul		: EProcedures<br>
 * Erstellt		: 03.11.2008<br>
 * Beschreibung	: List of all proceduresin KCX analogue kids_header_1_2.xsd:
 * 					EXPORT, NCTS, EMCS, ICS.
 * @author schmidt
 * @version 1.0.00
 */

public enum EProcedures {

		/**
	    * Description (en): Export
	    * Description (de): AES.
    	*/
		EXPORT, Export,

		/**
	    * Description (en): Export
	    * Description (de): AES.
    	*/
		EMCS,
		
		/**
	    * Description (en): Export
	    * Description (de): AES.
    	*/
		ICS,
		
		
		/**
	    * Description (en): Export
	    * Description (de): AES.
    	*/
		NCTS,
		
		/**
		* Description (en): Masnifest
		* Description (de): Sum-A.
	   	*/		
		MANIFEST;
}
