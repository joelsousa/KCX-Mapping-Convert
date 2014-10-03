package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.AddressType;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemReexport;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.FlightDetails;
/**
 * Module		: Manifest<br>.
 * Created		: 24.05.2013<br>
 * Description	: KIDS-ReExport, Uids-ReExportation 
 * 
 * @author krzoska
 * @version 2.0.00
 */

public class MsgReExport extends KCXMessage { 
	
	private String msgName = "ReExport";
	private String referenceNumber;
	private String referenceIdentifier;	
	private String typeOfTransaction;  
	private String placeOfUnloading;
	private String procedureType;
		
	private Party 				agent;
	private ContactPerson		contact;
	private Transport			transport;     //flightNumber;
	private HeaderExtensions	headerExtensions;
	private FlightDetails       flightDetails;               //EI20131017
	
	private ArrayList<GoodsItemReexport> goodsItemList;
	
	public MsgReExport() {
		super();
	}
	public MsgReExport(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EReExport {
		//KIDS 					 	UIDS:		
		AgentTIN,					AgentTin,
		AgentAddress,				
		Contact,		
		ProcedureType,
		//FlightNumber,
		PlaceOfUnloading,
		ReferenceNumber,
		ReferenceIdentifier,
		TypeOfTransaction,
		MeansOfTransport,           Transport,	
		FlightDetails,				
		HeaderExtensions,
		GoodsItem;
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EReExport) tag) {
								
			case AgentTIN:	
			case AgentTin:	
				TIN agentTIN = new TIN(getScanner());
				agentTIN.parse(tag.name());
				if (agent == null) {
					agent = new Party();
				}
				agent.setPartyTIN(agentTIN);
				break;
				
			case AgentAddress:			
				AddressType agentAddress = new AddressType(getScanner());
				agentAddress.parse(tag.name());
				if (agent == null) {
					agent = new Party();
				}
				agent.setAddress(agentAddress.getAddress());
				break;
				
			case Contact:
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());
				break;	
								
			case Transport:
			case MeansOfTransport:      
				transport = new Transport(getScanner());
				transport.parse(tag.name());
				break;	
				
			case FlightDetails:
				flightDetails = new FlightDetails(getScanner());
				flightDetails.parse(tag.name());
				break;	
				
			case HeaderExtensions:
				headerExtensions = new HeaderExtensions(getScanner());
				headerExtensions.parse(tag.name());
				break;
				
			case GoodsItem:
				GoodsItemReexport goodsItem = new GoodsItemReexport(getScanner());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
			default:
				return;
			}
		} else {
			switch ((EReExport) tag) {
			
			case AgentTIN:	//kann nur von UIDS-seite kommen
			case AgentTin:	
				setUidsAgentTin(value);
				break;
				
			case PlaceOfUnloading:
				setPlaceOfUnloading(value);
				break;

			case ReferenceNumber:
				setReferenceNumber(value);
				break;
				
			case ReferenceIdentifier:
				setReferenceIdentifier(value);
				break;
			
			case ProcedureType:
				setProcedureType(value);
				break;
				
			case TypeOfTransaction:
				setTypeOfTransaction(value);
				break;
				
			default:
				return;	
			}
		}
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
			return EReExport.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
		
	public String getReferenceIdentifier() {
		return referenceIdentifier;
	}
	public void setReferenceIdentifier(String referenceIdentifier) {
		this.referenceIdentifier = referenceIdentifier;
	}
	
	public String getPlaceOfUnloading() {
		return placeOfUnloading;
	}
	public void setPlaceOfUnloading(String placeOfUnloading) {
		this.placeOfUnloading = placeOfUnloading;
	}
	
	public HeaderExtensions getHeaderExtensions() {
		return headerExtensions;
	}
	public void setHeaderExtensions(HeaderExtensions headerExtensions) {
		this.headerExtensions = headerExtensions;
	}
	
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson contact) {
		this.contact = contact;
	}
	
	
	public ArrayList<GoodsItemReexport> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(ArrayList<GoodsItemReexport> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItemReexport goodsItem) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<GoodsItemReexport>();
		}
		this.goodsItemList.add(goodsItem);
	}
	
	private Party mapTraderToParty(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party = new Party();
		if (!Utils.isStringEmpty(trader.getTIN())) {
			TIN partyTIN = new TIN();
			partyTIN.setTIN(trader.getTIN());
			partyTIN.setBO(trader.getBranch());
			partyTIN.setCustomerIdentifier(trader.getCustomerID());
			partyTIN.setIdentificationType(trader.getTINType());
			party.setPartyTIN(partyTIN);
		}
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());	
		
		return party;
	}
	
	public Party getAgent() {		
		return this.agent;
	}
	public void setAgent(Party party) {
		this.agent = party;
	}
	private void setUidsAgentTin(String value) {   
		if (Utils.isStringEmpty(value)) {
			return;
		}
		TIN agentTIN = new TIN();
		agentTIN.setTIN(value);
		this.agent = new Party();
		agent.setPartyTIN(agentTIN);
	}
	
	public void setProcedureType(String value) {
		this.procedureType = value;
	}
	public String getProcedureType() {
		return procedureType;
	}
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}
	public FlightDetails getFlightDetails() {
		return flightDetails;
	}
	public void setFlightDetails(FlightDetails flightDetails) {
		this.flightDetails = flightDetails;
	}
	
	
}
