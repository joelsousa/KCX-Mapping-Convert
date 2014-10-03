package com.kewill.kcx.component.mapping.formats.kff.msg;

/**
 * Modul        : EFedexHeaderTags<br>
 * Erstellt     : 03.12.2010<br>
 * Beschreibung : Liste der Tags die in einem Unisys-Header vorkommen können.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public enum EJobHeaderTags {
	Job,
	DocumentInformation, 
		DocumentFormat, FormatIdentifier, FormatVersion,
		TransmissionDateTime, TransmissionDate, TransmissionTime, TransmissionTimeZone,
		BatchNo,
		TestFlag,
		UserInformation,	
		SenderID,
		SenderUserID;
}
