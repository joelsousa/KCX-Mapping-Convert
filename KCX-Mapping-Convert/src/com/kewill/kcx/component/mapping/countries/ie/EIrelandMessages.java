package com.kewill.kcx.component.mapping.countries.ie;

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
 * Module      : EKidsMessages<br>
 * Created     : 23.05.2014<br>
 * Description : valid Names of KIDS-Messagenames in Export, Import NCTS used for Ireland.
 *
 * @author  iwaniuk
 * @version 1.0.00
 */
public enum EIrelandMessages {

	// to customs
	//PreNotification(EDirections.CustomerToCountry),
	ExportDeclaration(EDirections.CustomerToCountry),
	ImportDeclaration(EDirections.CustomerToCountry),
	NCTSDeclaration(EDirections.CustomerToCountry),
	
	// from customs	
	MessageAcknowledgement(EDirections.CountryToCustomer), 
	IM528(EDirections.CountryToCustomer),   //MRNAllocated
	IM529(EDirections.CountryToCustomer),   //Release
	IM560(EDirections.CountryToCustomer),   //ControlNotification
	IM516(EDirections.CountryToCustomer),   //Rejection
	IM509(EDirections.CountryToCustomer);   //CancelationDecision

	private EDirections direction;

	EIrelandMessages(EDirections direction) {
		this.direction = direction;
	}

	EIrelandMessages() {
	}

	public EDirections getDirection() {
		return direction;
	}
}
