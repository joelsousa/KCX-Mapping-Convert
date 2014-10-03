/*
 * Function    : MsgExpRel.java
 * Titel       :
 * Date        : 22.04.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : ConfirmInvestigation (Kids) / NonExitedExportResponse (Uids)
 * 		       : V60
 * -----------
 * Changes 
 * -----------
 * Author      : 
 * Date        :
 * Label       : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;

/**
 * Module		: MsgExpExt<br>
 * Created		: 22.04.2009<br>
 * Description	: ConfirmInvestigation (Kids) / NonExitedExportResponse (Uids).
 *  			: new member for V21
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExpExt extends KCXMessage {
	private String exitDate;
	private String intendentExitDate;	
	private String annotation;
	private String referenceNumber;
	private String exitType;
	private String realOfficeOfExit;	
	//new for V21 - begin 
	private String ucrNumber;
	private String customsOfficeExport;	
	private Party declarant; 
    private TIN declarantTIN;           
	private Party agent; 
    private TIN agentTIN; 
	//new for V21 - end 
    private ContactPerson contact;   //EI20130808 AES22: neue optionale Tag == FSS atx.sb
	
	private enum EExportEXT {
		//KIDS						//UIDS
		DateOfExit,					//same
		IntendentDateOfExit, 		DateOfAwaitedExit, //CustomsOffices			
		Annotation,					Remark,
		ReferenceNumber,			//same
		ExitType,					TypeOfExit,	
		RealOfficeOfExit,			CustomsOffices, //CustomsOffices.OfficeOfActualExit
		CustomsOfficeExport,		//CustomsOffices.OfficeOfExport
		UCRNumber,					DocumentReferenceNumber,
		DeclarantTIN, Declarant, 	//same				
		AgentTIN, Agent, 	 		DeclarantRepresentative,
		Contact,                    //same   //EI20130808     
	}
	
	public MsgExpExt() {
	
	}
	public MsgExpExt(XMLEventReader parser) {
		super(parser);
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
	if (value == null) {
		switch ((EExportEXT) tag) {
		case CustomsOffices:
			CustomsOffices customsOffices = new CustomsOffices(getScanner());			
			customsOffices.parse(tag.name());
			setKidsFromCustomsOffices(customsOffices);
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
		case Contact:
			contact = new ContactPerson(getScanner());
			contact.parse(tag.name());	
			break;
		default:
			return;
		}
	} else {
		switch ((EExportEXT) tag) {
			case DateOfExit:
				setDateOfExit(value);
				break;
							
			case DateOfAwaitedExit:
			case IntendentDateOfExit: 
				setIntendentExitDate(value);
				break;
				
			case Annotation:
			case Remark:
				setAnnotation(value);
				break;
				
			case ReferenceNumber:
				setReferenceNumber(value); 
				break;
				
			case ExitType:			
			case TypeOfExit:	
				setExitType(value); 
				break;
				
			case RealOfficeOfExit:
				setRealOfficeOfExit(value); 
				break;
			case UCRNumber:
			case DocumentReferenceNumber:
				setUCRNumber(value);
				break;	
			case CustomsOfficeExport:						
				setCustomsOfficeExport(value);
				break;	
			default:
				return;
		}
	}
	}

	@Override
	public void stoppElement(Enum tag) {
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return EExportEXT.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getDateOfExit() {
		return exitDate;
	}

	public void setDateOfExit(String argument) {
		this.exitDate = argument;
	}

	public String getIntendentExitDate() {
		return intendentExitDate;
	}

	public void setIntendentExitDate(String argument) {
		intendentExitDate = argument;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String argument) {
		annotation = argument;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}


	public String getExitType() {
		return exitType;
	}

	public void setExitType(String argument) {
		exitType = argument;
	}
	public String getRealOfficeOfExit() {
		return realOfficeOfExit;
	
	}
	public void setRealOfficeOfExit(String argument) {
		realOfficeOfExit = argument;
	}
	
	public String getUCRNumber() {
		return ucrNumber;
	}
	public void setUCRNumber(String argument) {
		this.ucrNumber = argument;
	}
	
	public void setCustomsOfficeExport(String argument) {
		this.customsOfficeExport = argument;
	}
	public String getCustomsOfficeExport() {
		return customsOfficeExport;
	}
	
	private void setKidsFromCustomsOffices(CustomsOffices argument) {
		if (argument == null) {
			return;
		}
		this.customsOfficeExport = argument.getOfficeOfExport();    //new in V21   		
		this.realOfficeOfExit = argument.getOfficeOfActualExit();   
		
		
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
	
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}
	public ContactPerson getContact() {
		return contact;
	}
}
