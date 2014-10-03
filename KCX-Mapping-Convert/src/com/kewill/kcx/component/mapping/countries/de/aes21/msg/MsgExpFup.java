package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 25.07.2012<br>
 * Description	: Contains the Message Investigation with all Fields used in UIDS and  KIDS. 
 *              : changed name of MsgExpNer (in V12) because of all names should be created
 *              : on the same way, and member should have KIDS-Names 
 * 
 * @author iwaniuk
 * @version 1.0.00
 * 
 * Changes: new Tags fro AES22: DateOfResubmission, Resubmission
 */
public class MsgExpFup extends KCXMessage {

	private String referenceNumber;
	private String dateOfInquiry;     //AUP only
	private String dateOfLatestPossibleReply;		
	//new for V20 begin:
	private String  dateOfReminder;   //AUG only
	private String  ucrNumber;
	private String  customsOfficeExport;
	private Party   consignor;
	private TIN     consignorTIN;
	private Party   declarant;
	private TIN     declarantTIN;
	private Party   agent;
	private TIN     agentTIN;
	private Party   subcontractor;
	private TIN     subcontractorTIN;
	//new for V20 end
	private String  dateOfLatestResponseOfRequest;		//EI20130808
	private String  requestCode; 		//EI20130808
	
	private EFormat dateOfLatestFormat;   
	private EFormat dateOfInquiryFormat;   
	private EFormat dateOfReminderFormat;
	private EFormat dateOfLatestResponseOfRequestFormat;		//EI20130808
	
	private enum EExpFup {
		// Kids-TagNames, 			UIDS-TagNames;
		DateOfLatestPossibleReply,	ResponseUntil,
		DateOfInquiry,				DateOfInvestigation,
		DateOfReminder,             //same
		UCRNumber,					DocumentReferenceNumber,
		ReferenceNumber,			//same
		RequestCode,					//same           //EI20130808
		DateOfLatestResponseOfRequest,	ResponseOfRequestUntil,           //EI20130808
		//DateOfLatestPossibleResponseOfRequest,
		CustomsOfficeExport,		CustomsOffices, //.OfficeOfExport
		Consignor, ConsignorTIN,	Exporter,
		Declarant, DeclarantTIN,    //same
		Agent, AgentTIN, 			DeclarantRepresentative,
		Subcontractor, SubcontractorTIN;
	}
	
	public MsgExpFup(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public MsgExpFup() {
		super();
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {	
			switch ((EExpFup) tag) {
				case CustomsOffices:
					CustomsOffices customsOffices = new CustomsOffices(getScanner());
					customsOffices.parse(tag.name());	
					setKidsFromCustomsOffices(customsOffices);
					break;
				case Exporter:
				case Consignor:
					consignor = new Party(getScanner());
					consignor.parse(tag.name());						
					break;					
				case ConsignorTIN:
					consignorTIN = new TIN(getScanner());
					consignorTIN.parse(tag.name());					
					break;					
				case Declarant:
					declarant = new Party(getScanner());
					declarant.parse(tag.name());						
					break;	
				case DeclarantTIN:
					declarantTIN = new TIN(getScanner());
					declarantTIN.parse(tag.name());					
					break;	
				case Agent:
				case DeclarantRepresentative:						
					agent = new Party(getScanner());
					agent.parse(tag.name());
					break;						
				case AgentTIN:
					agentTIN = new TIN(getScanner());
					agentTIN.parse(tag.name());						
					break;
				case Subcontractor:
					subcontractor = new Party(getScanner());
					subcontractor.parse(tag.name());						
					break;					
				case SubcontractorTIN:
					subcontractorTIN = new TIN(getScanner());
					subcontractorTIN.parse(tag.name());					
					break;
				default:
					break;
			}
		} else {
			switch ((EExpFup) tag) {					
				case ReferenceNumber:				
					setReferenceNumber(value);					
					break;
					
				case UCRNumber:
				case DocumentReferenceNumber:
					 setUCRNumber(value);
					 break;
					 
				case DateOfInquiry:
				case DateOfInvestigation:
					setDateOfInquiry(value);
					if (tag == EExpFup.DateOfInquiry) {
   					 	setDateOfInquiryFormat(EFormat.KIDS_Date);
   				 	} else {
   				 		setDateOfInquiryFormat(getUidsDateAndTimeFormat(value)); 
   				 	}	
					break;
					
				case DateOfLatestPossibleReply:
				case ResponseUntil:
					setDateOfLatestPossibleReply(value);
					if (tag == EExpFup.DateOfLatestPossibleReply) {
   					 	setDateOfLatestFormat(EFormat.KIDS_Date);
   				 	} else {
   				 		setDateOfLatestFormat(getUidsDateAndTimeFormat(value)); 
   				 	}	
					break;
					
				case CustomsOfficeExport:
					setCustomsOfficeExport(value);
					 break;
					 
				case DateOfReminder:
					setDateOfReminder(value);
					if (value.indexOf('-') == -1) {
						setDateOfReminderFormat(EFormat.KIDS_DateTime);
					} else {
						setDateOfReminderFormat(getUidsDateAndTimeFormat(value)); 
					}
					break;
					
				case DateOfLatestResponseOfRequest:       //EI20130808
					setDateOfLatestResponseOfRequest(value);					
					setDateOfLatestResponseOfRequestFormat(EFormat.KIDS_Date);					
					break;
				case ResponseOfRequestUntil:
					setDateOfLatestResponseOfRequest(value);
					setDateOfLatestResponseOfRequestFormat(getUidsDateAndTimeFormat(value)); 
					break;
					
				case RequestCode:         //EI20130808
					setRequestCode(value);
					break;
					
				default:
					break;
			}
		}		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EExpFup.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 if (value.length() < 10) {
			 eFormat = EFormat.ST_Time_Min;
		 } else if (value.length() == 10) {
			 //EI20130808: eFormat = EFormat.ST_DateTime;
			 eFormat = EFormat.ST_Date;
		 } else if (value.length() > 10 && !value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || (value.length() > 19 && !value.substring(19, 20).equals("Z"))) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String documentReferenceNumber) {
		this.referenceNumber = documentReferenceNumber;
	}

	public String getDateOfInquiry() {
		return dateOfInquiry;
	}

	public void setDateOfInquiry(String responseUntil) {
		this.dateOfInquiry = responseUntil;
	}

	public String getDateOfLatestPossibleReply() {
		return dateOfLatestPossibleReply;
	}

	public void setDateOfLatestPossibleReply(String dateOfInvestigation) {
		this.dateOfLatestPossibleReply = dateOfInvestigation;
	}	
	public EFormat getDateOfInquiryFormat() {
		return dateOfInquiryFormat;
	}
	public void setDateOfInquiryFormat(EFormat argument) {
		this.dateOfInquiryFormat = argument;
	}
	
	public EFormat getDateOfLatestFormat() {
		return dateOfLatestFormat;
	}
	public void setDateOfLatestFormat(EFormat argument) {
		this.dateOfLatestFormat = argument;
	}
	
	public String getUCRNumber() {
		return ucrNumber;
	}
	public void setUCRNumber(String argument) {
		this.ucrNumber = argument;
	}
	
	public void setCustomsOfficeExport(String value) {
		this.customsOfficeExport = value;
	}
	public String getCustomsOfficeExport() {
		return customsOfficeExport;
	}
	
	private void setKidsFromCustomsOffices(CustomsOffices argument) {
		if (argument == null) {
			return;
		}
		this.customsOfficeExport = argument.getOfficeOfExport();		
	}
	
	public void setConsignor(Party argument) {
		this.consignor = argument;
	}
	public Party getConsignor() {
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new Party();
			}
			consignor.setPartyTIN(consignorTIN);
		}			
		return consignor;
	}	
	  	
	public void setDeclarant(Party argument) {
		this.declarant = argument;
	}
	public Party getDeclarant() {
		if (declarantTIN != null) {
			if (declarant == null) {
				declarant = new Party();
			}
			declarant.setPartyTIN(declarantTIN);
		}			
		return declarant;
	}	
	public void setAgent(Party argument) {
		this.agent = argument;
	}
	public Party getAgent() {
		if (agentTIN != null) {
			if (agent == null) {
				agent = new Party();
			}
			agent.setPartyTIN(agentTIN);
		}			
		return agent;
	}	
	
	public void setSubcontractor(Party argument) {
		this.subcontractor = argument;
	}
	public Party getSubcontractor() {
		if (subcontractorTIN != null) {
			if (subcontractor == null) {
				subcontractor = new Party();
			}
			subcontractor.setPartyTIN(subcontractorTIN);
		}			
		return subcontractor;
	}	
		
	public void setDateOfReminder(String value) {   //EI20120730
		this.dateOfReminder = value;
	}
	public String getDateOfReminder() {
		return dateOfReminder;
	}
	
	public EFormat getDateOfReminderFormat() {
		return dateOfReminderFormat;
	}
	public void setDateOfReminderFormat(EFormat exitTimeFormat) {
		this.dateOfReminderFormat = exitTimeFormat;
	}

	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String type) {
		this.requestCode = type;
	}
	
	public String getDateOfLatestResponseOfRequest() {
		return dateOfLatestResponseOfRequest;
	}
	public void setDateOfLatestResponseOfRequest(String date) {
		this.dateOfLatestResponseOfRequest = date;
	}

	public EFormat getDateOfLatestResponseOfRequestFormat() {
		return dateOfLatestResponseOfRequestFormat;
	}
	public void setDateOfLatestResponseOfRequestFormat(EFormat dateFormat) {
		this.dateOfLatestResponseOfRequestFormat = dateFormat;
	}
	
	public boolean isSubVersion22() { 		//EI20140318
		return (!Utils.isStringEmpty(dateOfLatestResponseOfRequest) ||		
				!Utils.isStringEmpty(requestCode));
	}
}

