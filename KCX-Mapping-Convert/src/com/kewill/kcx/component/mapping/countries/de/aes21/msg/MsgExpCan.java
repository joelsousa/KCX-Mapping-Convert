package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CancellationInfo;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;

/**
 * Module		: Export/aes<br>
 * Created		: 20.07.2012<br>
 * Description	: Contains the Message Cancellation  with all Fields used in UIDS and  KIDS.
 * 				: new member for V21
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class MsgExpCan extends KCXMessage {
	
	private String kindOfDeclaration;
	private String cancellationTime;		
	private CancellationInfo cancellationInfo;  //V21: frueher einzelne Tags: reason and KindOfCancellation
	private String declarationNumberCustoms;
	private String referenceNumber;
	private ContactPerson contact; 
	
	//new for V21 - begin
	private String customsOfficeExport;		
	private Party agent; 
    private TIN agentTIN;     
    private Party declarant; 
    private TIN declarantTIN;
  //new for V21 - end
        
	private enum EExpCanTags {
		// Kids-TagNames, 			UIDS-TagNames;		
		KindOfDeclaration,			TypeOfDocument,  //only CH
		CancellationTime,			DateOfAnnulment,
		CancellationInfo,  			ReasonOfAnnulment, TypeOfAnnulment,
		DeclarationNumberCustoms,	//
		ReferenceNumber,			//same	
		ContactPerson,              Contact,
		CustomsOfficeExport,		CustomsOffices, //.OfficeOfExport
		Declarant, DeclarantTIN,    //same
		Agent, AgentTIN, 			DeclarantRepresentative;
	}
	
	public MsgExpCan(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public MsgExpCan() {
		super();
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {			
			switch ((EExpCanTags) tag) {
			case Contact: 				//EI20081029
			case ContactPerson: 	    //EI20090609
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());								
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
			case CancellationInfo:
				cancellationInfo = new CancellationInfo(getScanner());
				cancellationInfo.parse(tag.name());	
				break;
			case CustomsOffices:
				CustomsOffices customsOffices = new CustomsOffices(getScanner());
				customsOffices.parse(tag.name());	
				setKidsFromCustomsOffices(customsOffices);
				break;
			default:
					return;
			}
		} else {			
			switch ((EExpCanTags) tag) {
			
				case KindOfDeclaration: 
				case TypeOfDocument:
					setKindOfDeclaration(value);
					break;						
				case CancellationTime: 
				case DateOfAnnulment:
					setCancellationTime(value);
					break;													
				case DeclarationNumberCustoms:
					setDeclarationNumberCustoms(value);
					break;						
				case ReferenceNumber:
					setReferenceNumber(value);
					break;					
				case CustomsOfficeExport:						
					setCustomsOfficeExport(value);
					break;
				case ReasonOfAnnulment:   //EI20120919
					if (cancellationInfo == null) {
						cancellationInfo = new CancellationInfo();
					}
					cancellationInfo.setReason(value);
					break;
				case TypeOfAnnulment:    //EI20120919
					if (cancellationInfo == null) {
						cancellationInfo = new CancellationInfo();
					}
					cancellationInfo.setKindOfCancellation(value);  
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
			return EExpCanTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
    
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}	
	
	public String getKindOfDeclaration() {
		return kindOfDeclaration;
	}
	public void setKindOfDeclaration(String value) {
		this.kindOfDeclaration = value;
	}

	public String getCancellationTime() {
		return cancellationTime;
	}
	public void setCancellationTime(String value) {
		this.cancellationTime = value;
	}

	public CancellationInfo getCancellationInfo() {
		return cancellationInfo;
	}
	public void setCancellationInfo(CancellationInfo argument) {
		this.cancellationInfo = argument;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String value) {
		this.referenceNumber = value;
	}
	
	public String getDeclarationNumberCustoms() {
		return declarationNumberCustoms;
	}

	public void setDeclarationNumberCustoms(String declarationNumberCustoms) {
		this.declarationNumberCustoms = declarationNumberCustoms;
	}

	public void setCustomsOfficeExport(String value) {
		this.customsOfficeExport = value;
	}
	public String getCustomsOfficeExport() {
		return customsOfficeExport;
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
	
	private void setKidsFromCustomsOffices(CustomsOffices argument) {
		if (argument == null) {
			return;
		}
		this.customsOfficeExport = argument.getOfficeOfExport();        
	}
}

