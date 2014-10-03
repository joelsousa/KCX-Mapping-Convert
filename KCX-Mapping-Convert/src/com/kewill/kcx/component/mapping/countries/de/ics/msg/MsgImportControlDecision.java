package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage; 
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgImportControlDecision IE361.<br>
 * Erstellt		: 04.02.2011<br>
 * Beschreibung : 
 *                  
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgImportControlDecision extends KCXMessage {

	private String msgName = "MsgImportControlDecision";
	private String 				referenceNumber;	
	private String 				shipmentNumber;
	private String				mrn;
	private String 				conveyanceReference;
	private Party				carrier;
	private TIN					carrierTIN;
	private Address				carrierAdr;	 	
	private String 				customsOfficeOfActualEntry;	
	private List<GoodsItemShort> goodsItemList;
	private List<CustomsIntervention> interventionList;
	private String 				totalNumberPosition;
		
	public MsgImportControlDecision() {
		super();				
	}

	public MsgImportControlDecision(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
 
	private enum EImportControlDecision {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,	
		MRN,							//same
		TotalNumberPosition,            TotalNumberOfItems,
		ShipmentNumber,                 CommercialReferenceNumber,		
		ConveyanceReference,            ConveyanceNumber, 		
		CarrierTIN,						EntryCarrier, Carrier, //.TIN		
		CarrierAddress,					//Carrier.Address + EntAddress + VatId
		CustomsOfficeFirstEntry,		OfficeOfActualEntry, OfficeOfFirstEntry,
		CustomsIntervention, Intervention,		
		GoodsItem,		 				//same;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EImportControlDecision) tag) {
			
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());
				break;
			case CarrierAddress:
				carrierAdr = new Address(getScanner());
				carrierAdr.parse(tag.name());	
				break;
			case EntryCarrier:
			case Carrier:
				Trader carrierTrader = new Trader(getScanner());
				carrierTrader.parse(tag.name());	
				carrier = setPartyFromTrader(carrierTrader);
				break;				
				 
			case Intervention:
			case CustomsIntervention:			
				CustomsIntervention intervention = new CustomsIntervention(getScanner());					
				intervention.parse(tag.name());		
				addCustomsInterventionList(intervention);
				break;
				
			case GoodsItem:
				GoodsItemShort item = new GoodsItemShort(getScanner());					
				item.parse(tag.name());	
				addGoodsItemList(item);
				break;
				
			default:
				return;
			}
	    } else {
	    	switch ((EImportControlDecision) tag) {
	    	
			case ReferenceNumber:
			case LocalReferenceNumber:				 
				 setReferenceNumber(value);
				 break;	
				 
			case ShipmentNumber:
			case CommercialReferenceNumber:
				 setShipmentNumber(value);
				 break;
				 
			case MRN:
				 setMrn(value);	
				 break;
				 
			case ConveyanceNumber:                      
			case ConveyanceReference:
				 setConveyanceReference(value);
				 break;	
							 
			case CustomsOfficeFirstEntry:
			case OfficeOfActualEntry:
				 setCustomsOfficeFirstEntry(value);
				 break;									
			
			case TotalNumberPosition:
			case TotalNumberOfItems:
				setTotalNumberPosition(value);
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
  			return EImportControlDecision.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;	
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public String getReferenceNumber() {
		return this.referenceNumber;	
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}

	public String getShipmentNumber() {
		return this.shipmentNumber;	
	}
	public void setShipmentNumber(String argument) {
		this.shipmentNumber = argument;
	}
	
	public String getMrn() {
		return this.mrn;	
	}
	public void setMrn(String argument) {
		this.mrn = argument;
	}

	public String getConveyanceReference() {
		return this.conveyanceReference;
	}
	public void setConveyanceReference(String argument) {
		this.conveyanceReference = argument;
	}

	public String getCustomsOfficeFirstEntry() {
		return this.customsOfficeOfActualEntry;
	}
	public void setCustomsOfficeFirstEntry(String argument) {
		this.customsOfficeOfActualEntry = argument;
	}

	
	public List<CustomsIntervention> getCustomsInterventionList() {
		return this.interventionList;	
	}
	public void setCustomsInterventionList(List<CustomsIntervention> list) {
		this.interventionList = list;
	}
	public void addCustomsInterventionList(CustomsIntervention argument) {
		if (interventionList == null) {
			interventionList = new ArrayList<CustomsIntervention>();
		}
		this.interventionList.add(argument);
	}
	
	public Party getCarrier() {
		if (carrierTIN != null || carrierAdr != null) { 
			if (carrier == null) {
				carrier = new Party();
			}
		}
		if (carrierTIN != null) {
			carrier.setPartyTIN(carrierTIN);		
		}
		if (carrierAdr != null) {
			carrier.setAddress(carrierAdr);		
		}		
		return this.carrier;
	}
	public void setCarrier(Party argument) {
		this.carrier = argument;
	}
	
	public String getTotalNumberPosition() {
		return this.totalNumberPosition;
	}
	public void setTotalNumberPosition(String argument) {
		this.totalNumberPosition = argument;
	}
	
	public List<GoodsItemShort> getGoodsItemList() {
		return this.goodsItemList;	
	}	
	public void setGoodsItemList(List<GoodsItemShort> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItemShort argument) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<GoodsItemShort>();
		}
		this.goodsItemList.add(argument);
	}
	
	
	public Party setPartyFromTrader(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party  = new Party();
		
		TIN	tin = new TIN();
		tin.setTIN(trader.getTIN());
		tin.setIsTINGermanApprovalNumber(trader.getCustomsID());
		tin.setCustomerIdentifier(trader.getCustomerID());	
		tin.setIdentificationType(trader.getTINType());   //EI20110120
		
		party.setPartyTIN(tin);		
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());
		party.setContactPerson(trader.getContactPerson());
		
		return party;		
	}	
	
}
