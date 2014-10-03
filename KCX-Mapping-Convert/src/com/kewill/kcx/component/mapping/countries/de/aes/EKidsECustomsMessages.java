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
 * Modul        : EKidsECustomsMessages<br>
 * Erstellt     : 06.01.2012<br>
 * Beschreibung : valid Names of KIDS-Header.MessageNames in Export for ECustoms.
 *
 * @author  iwaniuk
 * @version 1.0.00
 */
public enum EKidsECustomsMessages {

	PreNotification,
	ExportDeclaration,
	Update,
	Delete,
	Amendment,
	Completion,	
	Cancellation,	
}
