package com.kewill.kcx.component.mapping.companies.unisys.ics;

/**
 * Modul        : EFedexHeaderTags<br>
 * Erstellt     : 03.12.2010<br>
 * Beschreibung : Liste der Tags die in einem Unisys-Header vorkommen können.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public enum EUnisysHeaderTags {
	MsgEnvelope,
		MsgHeader, MsgType,	MsgName, MsgFunction, MsgVersion, MsgSender, MsgRecipient, MsgDateTime, MsgId, MsgRelated, MsgRelatedDateTime,
		MsgDocType,
		MsgBody,
		MsgResponse, CustAwbMsg, CustFltMsg,
		CustMsgAction;
}
