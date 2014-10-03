package com.kewill.kcx.component.mapping.countries.de.aes.msg;

/*
 * Function    : MsgExpCan.java
 * Titel       :
 * Date        : 05.03.2009
 * Author      : Kewill CSF / messer
 * Description : Contains the Message Cancellation 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
 

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: MsgExpNer<br>
 * Created		: 05.03.2009<br>
 * Description	: Contains the Message Investigation with all Fields used in UIDS and  KIDS. 
 * 
 * @author messer
 * @version 1.0.00
 */
public class MsgExpNer extends KCXMessage {

	private String documentReferenceNumber;
	private String responseUntil;
	private String dateOfInvestigation;		
	
	private EFormat responseUntilFormat;   //EI20110518
	private EFormat dateOfInvestigationFormat;   //EI20110518
	
	private enum EExpNerTags {
		// Kids-TagNames, 			UIDS-TagNames;
		ReferenceNumber,			DocumentReferenceNumber,
		DateOfInquiry,				DateOfInvestigation,
		DateOfLatestPossibleReply,	ResponseUntil;
	}
	
	public MsgExpNer(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public MsgExpNer() {
		super();
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			switch ((EExpNerTags) tag) {
					
				case ReferenceNumber:
				case DocumentReferenceNumber:
					setDocumentReferenceNumber(value);					
					break;
					
				case DateOfInquiry:
				case DateOfInvestigation:
					setDateOfInvestigation(value);
					if (tag == EExpNerTags.DateOfInquiry) {
   					 	setDateOfInvestigationFormat(EFormat.KIDS_Date);
   				 	} else {
   				 		setDateOfInvestigationFormat(getUidsDateAndTimeFormat(value)); 
   				 	}	
					break;
					
				case DateOfLatestPossibleReply:
				case ResponseUntil:
					setResponseUntil(value);
					if (tag == EExpNerTags.DateOfLatestPossibleReply) {
   					 	setResponseUntilFormat(EFormat.KIDS_Date);
   				 	} else {
   				 		setResponseUntilFormat(getUidsDateAndTimeFormat(value)); 
   				 	}	
					break;
					
				default:
					break;
			}
		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EExpNerTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getDocumentReferenceNumber() {
		return documentReferenceNumber;
	}

	public void setDocumentReferenceNumber(String documentReferenceNumber) {
		this.documentReferenceNumber = documentReferenceNumber;
	}

	public String getResponseUntil() {
		return responseUntil;
	}

	public void setResponseUntil(String responseUntil) {
		this.responseUntil = responseUntil;
	}

	public String getDateOfInvestigation() {
		return dateOfInvestigation;
	}

	public void setDateOfInvestigation(String dateOfInvestigation) {
		this.dateOfInvestigation = dateOfInvestigation;
	}	
	
/////////
	
	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 if (value.length() < 10) {
			 eFormat = EFormat.ST_Time_Min;
		 } else if (value.length() == 10) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() > 10 &&!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || (value.length() > 19 && !value.substring(19, 20).equals("Z"))) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}

	public EFormat getDateOfInvestigationFormat() {
		return dateOfInvestigationFormat;
	}
	public void setDateOfInvestigationFormat(EFormat argument) {
		this.dateOfInvestigationFormat = argument;
	}
	
	public EFormat getResponseUntilFormat() {
		return responseUntilFormat;
	}
	public void setResponseUntilFormat(EFormat argument) {
		this.responseUntilFormat = argument;
	}
		
}

