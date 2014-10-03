package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: Export/aes<br>
 * Created		: 14.09.2012<br>
 * Description	: Contains the Message ExportReminder with all Fields used in UIDS and  KIDS.  
 *              : V21 - new
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExpUrg extends KCXMessage {

	private String referenceNumber;
	private String ucrNumber;     
	private String customsOfficeForCompletion;
	private String dateOfReminder;  
	private String dateOfLatestPossibleReply;		
	
	private Party   consignor;
	private TIN     consignorTIN;
	private Party   declarant;
	private TIN     declarantTIN;
	private Party   agent;
	private TIN     agentTIN;
	private Party   subcontractor;
	private TIN     subcontractorTIN;
	
	private EFormat dateOfLatestFormat;   	
	private EFormat dateOfReminderFormat;
	
	private enum EExpUrg {
		// Kids-TagNames, 			UIDS-TagNames;
		UCRNumber,					DocumentReferenceNumber,
		ReferenceNumber,			//same
		CustomsOfficeForCompletion,	OfficeOfAdditionalDeclarationExport,
		DateOfReminder,             //same
		DateOfLatestPossibleReply,	ResponseUntil,		
		Consignor, ConsignorTIN,	Exporter,
		Declarant, DeclarantTIN,    //same
		Agent, AgentTIN, 			DeclarantRepresentative,
		Subcontractor, SubcontractorTIN;
	}
	
	public MsgExpUrg(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public MsgExpUrg() {
		super();
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {	
			switch ((EExpUrg) tag) {				
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
			switch ((EExpUrg) tag) {					
				case ReferenceNumber:				
					setReferenceNumber(value);					
					break;
				case UCRNumber:
				case DocumentReferenceNumber:
					 setUCRNumber(value);
					 break;
				
				case DateOfLatestPossibleReply:
				case ResponseUntil:
					setDateOfLatestPossibleReply(value);
					if (tag == EExpUrg.DateOfLatestPossibleReply) {
   					 	setDateOfLatestFormat(EFormat.KIDS_Date);
   				 	} else {
   				 		setDateOfLatestFormat(getUidsDateAndTimeFormat(value)); 
   				 	}	
					break;
				case CustomsOfficeForCompletion:
				case OfficeOfAdditionalDeclarationExport:
					setCustomsOfficeForCompletion(value);
					break;
				case DateOfReminder:
					setDateOfReminder(value);
					if (value.indexOf('-') == -1) {
						setDateOfReminderFormat(EFormat.KIDS_Date);
					} else {
						setDateOfReminderFormat(getUidsDateAndTimeFormat(value)); 
					}
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
			return EExpUrg.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 if (value.length() < 10) {
			 eFormat = EFormat.ST_Time_Min;
		 } else if (value.length() == 10) {
			 eFormat = EFormat.ST_DateTime;
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

	public String getDateOfLatestPossibleReply() {
		return dateOfLatestPossibleReply;
	}
	public void setDateOfLatestPossibleReply(String dateOfInvestigation) {
		this.dateOfLatestPossibleReply = dateOfInvestigation;
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
	
	public void setCustomsOfficeForCompletion(String value) {
		this.customsOfficeForCompletion = value;
	}
	public String getCustomsOfficeForCompletion() {
		return customsOfficeForCompletion;
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
}

