package com.kewill.kcx.component.mapping.companies.fedex.ics;

/**
 * Modul        : EFedexHeaderTags<br>
 * Erstellt     : 03.11.2010<br>
 * Beschreibung : Liste der Tags die in einem Fedex-Header vorkommen können.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public enum EFedexHeaderTags {
    
	MessageOperateur, 
	EnveloppeConnexion, connexionId, interchangeAgreementId, numEnveloppe, DateTime, applicationId,
	Messages, Message, 
	EnveloppeMessage, schemaID, schemaVersion, partyId, transactionId, numseq,
	Date, Time, date, time;
}
