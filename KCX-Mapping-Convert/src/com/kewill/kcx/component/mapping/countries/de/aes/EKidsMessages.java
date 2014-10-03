package com.kewill.kcx.component.mapping.countries.de.aes;

import com.kewill.kcx.component.mapping.EDirections;

/*
 * Function    : EKidsMessages.java
 * Titel       :
 * Date        : 02.09.2008
 * Author      : Kewill CSF / kron
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to the docs kiff_export.xls and kiff-export-reply.xls
 * Parameters  :

 * Changes
 * -----------
 * Author      : Sven Heise
 * Date        : 08.09.2008
 * Description : InternalStatusInformation, Cancellation, Completion (25.09.08)
 *
 * Author      : 20.11.2008
 * Date        : Kewill CSF / krzoska
 * Label       : ReverseDeclaration instead of ExportRelease
 * Description :
 *
 * Author      : Christine Kron
 * Date        : 27.04.2010
 * Label       :
 * Description : added VerificationResult
 *
 */

/**
 * Modul        : EKidsMessages<br>
 * Erstellt     : 2.09.2008<br>
 * Beschreibung : valid Names of KIDS-Messagenames in Export.
 *
 * @author  kron
 * @version 1.0.00
 */
public enum EKidsMessages {

	// to customs
	PreNotification(EDirections.CustomerToCountry),
	ExportDeclaration(EDirections.CustomerToCountry),
	Amendment(EDirections.CustomerToCountry),
	Completion(EDirections.CustomerToCountry),
	ConfirmInvestigation(EDirections.CustomerToCountry),
	Cancellation(EDirections.CustomerToCountry),
	ManualTermination(EDirections.CustomerToCountry),
	// from customs
	ReverseDeclaration(EDirections.CountryToCustomer),
	Confirm(EDirections.CountryToCustomer),
	Failure(EDirections.CountryToCustomer),
	InternalStatusInformation(EDirections.CountryToCustomer),
	ErrorMessage(EDirections.CountryToCustomer),
	DeclarationResponse(EDirections.CountryToCustomer),
	CancelationResponse(EDirections.CountryToCustomer),
    Confirmation(EDirections.CountryToCustomer),
	Investigation(EDirections.CountryToCustomer),
	localAppResult(EDirections.CountryToCustomer),
	VerificationResult(EDirections.CountryToCustomer),

    ExitPresentation(EDirections.CustomerToCountry),           //EI20120706
	ExitNotification(EDirections.CustomerToCountry),           //EI20120724
	ExportReminder(EDirections.CountryToCustomer),           //EI20120914
	ControlNotification(EDirections.CountryToCustomer),       //EI20130722
	ExportControlNotification(EDirections.CountryToCustomer), //EI20131202: is same with ControlNotification
	
	ExitAuthorisation(EDirections.CountryToCustomer),      	 //EI20130809
	ExitAuthorization(EDirections.CountryToCustomer),      	 //EI20130809
	
	Declaration(EDirections.CustomerToCountry); 	//EI20130417: "global" KIDS, contains all Tags of all Procedures
	
//	the following names are defined in EKidsICSMessages and EKidsEMCSMessages and EKidsNCTSMessages
//  Christine Kron 2.9.2010

//	EMCSDeclaration,             //EI20100519
//	EMCSValidDeclaration,
//	EMCSCancellation,
//	EMCSReminderMessage,
//	EMCSDiversionNotification,
//	EMCSChangeOfDestination,
//	EMCSReportOfReceipt,
//	EMCSAcceptedExport,
//	EMCSExplanationOnDelay,
//	EMCSExportRejection,
//	EMCSGenericRefusalMessage,


//	ICSDiversionRequest,					//PT20100612
//	ICSDiversionRequestAcknowledgment,		//PT20100615
//	ICSEntrySummaryDeclarationAcknowledgment,	//PT20100618
//	ICSDeclarationAmendment,						//PP20100715
//	ICSEntrySummaryDeclarationAcceptance,
//	ICSArrivalNotification,		//FT20100714
//	ICSDeclarationProhibited,
//	ICSArrivalItemRejection,
//	ICSArrivalNotificationValidation,	//FT20100720

//	//NCTS
//	NCTSDeclarationRejected,	//LC20100824
//	NCTSArrivalRejection,	//MB20100524
//	NCTSArrivalNotification, //EE20100525
//	NCTSMRNAllocated,		 //PP20100525
//	NCTSUnloadingRemarksRejection	//MB20100831

	private EDirections direction;

	EKidsMessages(EDirections direction) {
		this.direction = direction;
	}

	EKidsMessages() {
	}

	public EDirections getDirection() {
		return direction;
	}
}
