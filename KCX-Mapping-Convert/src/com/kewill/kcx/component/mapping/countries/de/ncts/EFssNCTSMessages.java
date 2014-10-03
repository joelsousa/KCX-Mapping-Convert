package com.kewill.kcx.component.mapping.countries.de.ncts;

/**
 * Module		: EKidsNCTSMessages
 * Date Started	: 02.09.2010
 * Description	: Valid Names of FSS-message names in Zabis NCTS.
 * 
 * @author Edwin Bautista
 * @version 6.2.00
 */
public enum EFssNCTSMessages {
		
	/**
	    * Functional name:  E_ARR_NOT
	    * Technical Name:   IE007
	    * Description (en): NCTS ArrivalNotification
	    * Description (de): Ankunftsanzeige
	    * Direction:        Customer to customs.
	*/
	VIA,
	
	/**
	    * Functional name:  E_ARR_REJ
	    * Technical Name:   IE008
	    * Description (en): Arrival Rejection
	    * Description (de): Fehlermeldung
	    * Direction:        Customs to customer.
	*/
	E_ARR_REJ,
	
	/**
	    * Functional name:  E_DEC_DAT
	    * Technical Name:   IE015
	    * Description (en): NCTS Declaration
	    * Description (de): Versandantrag
	    * Direction:        Customer to customs.
	*/
	VAN,
	
	/**
	    * Functional name:  E_DEC_REJ
	    * Technical Name:   IE016
	    * Description (en): Declaration Rejection
	    * Description (de): Fehlermeldung
	    * Direction:        Customs to customer.
 	*/
	VFO,
	
	/**
	    * Functional name:  E_MRN_ALL
	    * Technical Name:   IE028
	    * Description (en): NCTS MRN Allocated
	    * Description (de): Rückgabe der MRN
	    * Direction:        Customs to customer.
 	*/
	VMR,
	
	/**
	    * Functional name:  E_ULD_PER
	    * Technical Name:   IE043
	    * Description (en): NCTS Unloading Permission
	    * Description (de): Entladeerlaubnis
	    * Direction:        Customs to customer.
	*/
	VPH,
	
	/**
	    * Functional name:  E_ULD_REM
	    * Technical Name:   IE044
	    * Description (en): NCTS Unloading Remarks
	    * Description (de): Entladekommentar
	    * Direction:        Customer to customs.
	*/
	VUR,
	
	/**
	    * Functional name:  E_ULD_REJ / E_ARR_REJ
	    * Technical Name:   IE058
	    * Description (en): Unloading Rejection / Arrival_Rejection
	    * Description (de): Fehlermeldung
	    * Direction:        Customs to customer.
	*/
	VFI,
	
	/**
	    * Functional name:  
	    * Technical Name:   
	    * Description (en): Internal Status message
	    * Description (de): Fehlermeldung
	    * Direction:        ZABIS to customer.
	*/
	VSO, 

	/**
	    * Functional name:  
	    * Technical Name:   
	    * Description (en): Internal Error message
	    * Description (de): Fehlermeldung
	    * Direction:        ZABIS to customer.
	*/
	ERR,
	CON,
	STI,
	VRL,
	VAT;
	
	
}
