package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.AmendmentInfo;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 12.03.2009<br>
 * Description	: Contains the Message Amendment with all Fields used in UIDS and  KIDS. 
 * 				: V21 - new members 
 * 
 * @author Krzoska
 * @version 1.0.00
 */

public class MsgExpAmd extends KCXMessage {

	// simple tags
	private String grossMass;
	private String referenceNumber;
	
	//new for V21 - begin
	private AmendmentInfo amendmentInfo;
	private Party declarant;
	private TIN declarantTIN;
	private ContactPerson declarantContactPerson; 
	private Party agent;
	private TIN agentTIN;
	private ContactPerson agentContactPerson; 
	//new for V21 - end
	
    private MsgExpAmdPos goodsItem;
    private List <MsgExpAmdPos>goodsItemList;

	
	
	private enum EExpAdnTags {
		// Kids-TagNames, 					UIDS-TagNames;
		  AmendmentInfo,					//
		  GrossMass,						//same 
		  ReferenceNumber,					//same 
		  Declarant, 						//same
		  DeclarantTIN, DeclarantContactPerson,
		  Agent, 							DeclarantRepresentative,
		  AgentTIN, AgentContactPerson,		  
		  GoodsItem;						//same UIDS
	}
	
	public MsgExpAmd() {
		super();		
	}
	
	public MsgExpAmd(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			
			switch ((EExpAdnTags) tag) {
			case Declarant:						
				declarant = new Party(getScanner());
				declarant.parse(tag.name());
				break;					
			case DeclarantTIN:
				declarantTIN = new TIN(getScanner());
				declarantTIN.parse(tag.name());						
				break;				
			case DeclarantContactPerson:
				declarantContactPerson = new ContactPerson(getScanner());
				declarantContactPerson.parse(tag.name());						
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
			case AgentContactPerson:
				agentContactPerson = new ContactPerson(getScanner());
				agentContactPerson.parse(tag.name());						
				break;					
			case AmendmentInfo:
				amendmentInfo = new AmendmentInfo(getScanner());
				amendmentInfo.parse(tag.name());	
				break;
			case GoodsItem:	
				goodsItem = new  MsgExpAmdPos(getScanner());
				goodsItem .parse(tag.name());
				addItemList(goodsItem);
				break;
			default:
				return;
			}
		} else {
			
			switch ((EExpAdnTags) tag) {
		
				case GrossMass: 
					setGrossMass(value);
					break;
				
				case ReferenceNumber: 
					setReferenceNumber(value);
					break;
			}
		}
	}
	
	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EExpAdnTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
    public void addItemList(MsgExpAmdPos argument) {
    	if (goodsItemList == null) {
    		goodsItemList  = new Vector<MsgExpAmdPos>();
    	}
    	this.goodsItemList.add(argument);
    }

	public String getGrossMass() {
		return grossMass;	
	}
	public void setGrossMass(String grossMass) {
		this.grossMass = Utils.checkNull(grossMass);
	}

	public String getReferenceNumber() {
		return referenceNumber;	
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
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
		if (agentContactPerson != null) {
			if (agent == null) {
				agent = new Party();
			}
			agent.setContactPerson(agentContactPerson);
		}			
		return agent;
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
		if (declarantContactPerson != null) {
			if (declarant == null) {
				declarant = new Party();
			}
			declarant.setContactPerson(declarantContactPerson);
		}			
		return declarant;
	}	

	public MsgExpAmdPos getGoodsItem() {
		return goodsItem;	
	}
	public List<MsgExpAmdPos> getGoodsItemList() {
		return goodsItemList;
	
	}

	public AmendmentInfo getAmendmentInfo() {
		return  amendmentInfo;	
	}
	public void setAmendmentInfo(AmendmentInfo argument) {
		this.amendmentInfo = argument;
	}
}
