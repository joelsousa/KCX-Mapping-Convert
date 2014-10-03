package com.kewill.kcx.component.mapping.countries.de.aes;
/*
 * Function    : EUidsMessages.java
 * Titel       :
 * Date        : 04.09.2008
 * Author      : Kewill CSF / kron
 * Description : valid Names of UIDS-Messagenames in Export
 * 			   : relates to etn_export_V20.xsd
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
public enum EUidsMessages {
	ExportPreAdvice,
	ExportDeclaration,
	ExportAmendment,
	Completion,
	NonExitedExportResponse,
	Cancelation,
	ManualTerminationExport,
	ExportRelease, 
	ExportDeclarationResponse,
	Confirmation,
	NonExitedExportRequest,
	ErrorInformation,
	InternalStatusInformation,  // ?????
	Failure,
	Confirm,
	ExitNotification,
	
	EMCSNotificationOfAcceptedExport,
	EMCSCancellationEAAD,
	EMCSChgOfDestinationInfo,
	EMCSDeclaration,
	EMCSNotificationDivertedEAAD,
	EMCSExplanationOnDelayForDelivery,
	EMCSRejectionOfEAADForExport,
	EMCSDeclarationRejected,
	EMCSReminderMessageForExciseMovement,
	EMCSReportOfReceipt,
	EMCSValidDeclaration;
}
