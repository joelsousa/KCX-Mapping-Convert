package com.kewill.kcx.component.mapping.countries.cy.ics;

/**
 * Module		: ECyprusICSMessages<br>
 * Date Created	: 01.06.2011<br>
 * Description	: List of Cyprus ICS Messages.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public enum ECyprusICSMessages {
	/**
	 * ENTRY SUMMARY DECLARATION.
	 */
	ICSEntrySummaryDeclaration,
	
	/**
	 * ENTRY SUMMARY DECLARATION REJECTED.
	 */
	MessageIE316,
	
	/**
	 * ENTRY SUMMARY DECLARATION ACKNOWLEDGEMENT.
	 */
	ICSEntrySummaryDeclarationAcknowledgment,
	//EI20110708:ICSEntrySummaryDeclarationAcknowledgement,
	
	/**
	 * ENTRY SUMMARY DECLARATION AMENDMENT.
	 */	
	ICSDeclarationAmendment,                         //EI20110711
	//ICSEntrySummaryDeclarationAmendment,
	/**
	 * ENTRY SUMMARY DECLARATION AMENDMENT ACCEPTED.
	 */
	ICSEntrySummaryDeclarationAmendmentAcceptance,
	//EI20110708:ICSEntrySummaryDeclarationAmendmentAccepted,	
	
	/**
	 * ENTRY SUMMARY DECLARATION AMENDMENT REJECTION.
	 */
	ICSEntrySummaryDeclarationAmendmentRejection,
	
	/**
	 * DIVERSION REQUEST ACKNOWLEDGMENT.
	 */
	ICSDiversionRequestAcknowledgment,
	//EI20110708:ICSDiversionRequestAcknowledgement,
	
	/**
	 * ADVANCED INTERVENTION NOTIFICATION.
	 */
	ICSAdvancedInterventionNot,
	
	/**
	 * DIVERSION  REQUEST.
	 */
	ICSDiversionRequest,
	
	/**
	 * DIVERSION REQUEST REJECTED.
	 */
	ICSDiversionRequestRejected,	
	
	/**
	 * ArrivalNotification.  				//EI20110708
	 */
	ICSArrivalNotification,
	
	/**
	 * localAppResult. 					   //EI20110708
	 */
	localAppResult;
}
