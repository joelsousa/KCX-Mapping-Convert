package com.kewill.kcx.component.mapping.countries.de.ncts20;

/*
 * Function    : EKidsNCTSMessages.java
 * Title       :
 * Date        : 23.08.2010
 * Author      : Frederick T
 * Description : Valid Names of KIDS-message names in NCTS
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Christine Kron
 * Date        : 02.09.2010
 * Label       :
 * Description :  added Java Doc
 *
 */

/**
 * Module		: EKidsNCTSMessages
 * Date Started	: 23.08.2010
 * Description	: Valid Names of KIDS-message names in NCTS.
 * 
 * @author Frederick T
 * @version 1.0.00
 */

public enum EKidsNCTSMessages {

		/**
	    * Functional name:  E_DEC_DAT
	    * Technical Name:   IE015
	    * Description (en): NCTS Declaration
	    * Description (de): Versandantrag
	    * Direction:        Customer to customs.
    	*/
		NCTSDeclaration,

		/**
	    * Functional name:  E_ARR_NOT
	    * Technical Name:   IE007
	    * Description (en): NCTS ArrivalNotification
	    * Description (de): Ankunftsanzeige
	    * Direction:        Customer to customs.
    	*/
		ArrivalNotification,
		
		/**
	    * Functional name:  E_MRN_ALL
	    * Technical Name:   IE028
	    * Description (en): NCTS MRN Allocated
	    * Description (de): Rückgabe der MRN
	    * Direction:        Customs to customer.
    	*/
		MRNAllocated,
		
		
		
		/**
	    * Functional name:  E_ARR_REJ
	    * Technical Name:   IE008
	    * Description (en): Arrival Rejection
	    * Description (de): Fehlermeldung
	    * Direction:        Customs to customer.
    	*/
		ArrivalRejection,
		
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
		UnloadingRemarksRejection,

		/**
	    * Functional name:  E_ULD_REM
	    * Technical Name:   IE044
	    * Description (en): NCTS Unloading Remarks
	    * Description (de): Entladekommentar
	    * Direction:        Customer to customs.
    	*/
		UnloadingRemarks, 
		
		/**
		* Functional name:  E_ULD_PER
		* Technical Name:   IE043
		* Description (en): NCTS Unloading Permission
		* Description (de): Entladeerlaubnis
		* Direction:        Customs to customer.
	    */
		UnloadingPermission,

		/**
		    * Functional name:  E_ARR_NOT
		    * Technical Name:   IE045
		    * Description (en): NCTS WriteOffNotification
		    * Description (de): WriteOffNotification
		    * Direction:        Customs to customer.
	    	*/
		NCTSWriteOffNotification,
			
		/**
	     * Functional name:  -
	     * Technical Name:   -
	     * Description (en): Local application failure message.
	     * Description (de): Einarbeitungsprotokoll.
	     * Direction:        Application to customer
	     */
		localAppResult,
				
	    /**
	     * Functional name:  -
	     * Technical Name:   -
	     * Description (en): Technical error message of an EDIFACT message.
	     * Description (de): Technische Fehlermeldung einer EDIFACT Nachricht.
	     * Direction:        Customs or application to customer
	     */
	    InternalStatusInformation,
		
		CustomsStatusNotification,   //EI29131217

}
