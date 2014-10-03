package com.kewill.kcx.component.mapping.countries.de.ncts;
/*
 * Function    : EUidsNCTSMessages.java
 * Titel       :
 * Date        : 23.08.2010
 * Author      : Frederick T
 * Description : Valid Names of UIDS-message names in NCTS
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       :
 * Description :  
 *
 */

/**
 * Module		: EUidsNCTSMessages
 * Date Started	: 23.08.2010
 * Description	: Valid Names of UIDS-message names in NCTS.
 * 
 * @author Frederick T
 * @version 1.0.00
 *
 */
public enum EUidsNCTSMessages {
	
	/**
	    * Functional name:  E_ARR_NOT
	    * Technical Name:   IE007
	    * Description (en): NCTS ArrivalNotification
	    * Description (de): Ankunftsanzeige
	    * Direction:        Customer to customs.
 	*/
	NCTSArrivalNotification,
	
	/**
	    * Functional name:  E_MRN_ALL
	    * Technical Name:   IE028
	    * Description (en): NCTS MRN Allocated
	    * Description (de): Rückgabe der MRN
	    * Direction:        Customs to customer.
 	*/
	NCTSMRNAllocated,
	
	/**
	    * Functional name:  E_ARR_REJ
	    * Technical Name:   IE008
	    * Description (en): Arrival Rejection
	    * Description (de): Fehlermeldung
	    * Direction:        Customs to customer.
 	*/
	NCTSArrivalRejection,
	
	/**
	    * Functional name:  E_DEC_REJ
	    * Technical Name:   IE016
	    * Description (en): Declaration Rejection
	    * Description (de): Fehlermeldung
	    * Direction:        Customs to customer.
 	*/
	NCTSDeclarationRejected,
	
	/**
	    * Functional name:  E_ULD_REJ
	    * Technical Name:   IE058
	    * Description (en): Unloading Rejection
	    * Description (de): Fehlermeldung
	    * Direction:        Customs to customer.
 	*/
	NCTSUnloadingRemarksRejection,
	
	/**
	    * Functional name:  E_ULD_REM
	    * Technical Name:   IE044
	    * Description (en): NCTS Unloading Remarks
	    * Description (de): Entladekommentar
	    * Direction:        Customer to customs.
 	*/
	NCTSUnloadingRemarks,
	
	/**
	    * Functional name:  E_DEC_DAT
	    * Technical Name:   IE015
	    * Description (en): NCTS Declaration
	    * Description (de): Versandantrag
	    * Direction:        Customer to customs.
 	*/
	NCTSDeclaration,
	
	/**
	    * Functional name:  E_ULD_PER
	    * Technical Name:   IE043
	    * Description (en): NCTS Unloading Permission
	    * Description (de): Entladeerlaubnis
	    * Direction:        Customer to customs.
	*/
	NCTSUnloadingPermission;
}
