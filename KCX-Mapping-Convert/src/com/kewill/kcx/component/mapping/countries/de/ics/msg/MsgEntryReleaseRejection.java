package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage; 
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgEntryReleaseRejection IE322<br>
 * Erstellt		: 04.02.2011<br>
 * Beschreibung : Contains Message Structure with fields used in ICSEntryReleaseRejection.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgEntryReleaseRejection extends KCXMessage {

	private String msgName = "ICSEntryReleaseRejection";
	private String 				referenceNumber;
	private String 				conveyanceReference;
	private String 				shipmentNumber;
	private String				mrn;
	private String 				customsOfficeOfActualEntry;
	private Text				motivation;
	private String				controlDate;	
	private Party				carrier;
	private TIN					carrierTIN;
	private Address				carrierAdr;	 		
	private List<GoodsItemShort> goodsItemList;
		
	private EFormat controlDateFormat;
	
	public MsgEntryReleaseRejection() {
		super();				
	}

	public MsgEntryReleaseRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
 
	private enum EEntryReleaseRejection {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		ShipmentNumber,                 CommercialReferenceNumber,
		MRN,							//same
		ConveyanceReference,            ConveyanceNumber, 		
		CarrierTIN,						EntryCarrier, Carrier, //.TIN		
		CarrierAddress,					//Carrier.Address + EntAddress + VatId
		TODO_1,							OfficeOfActualEntry,
		TODO_2,							Motivation,
		ControlResultDate,				ControlDate,
		GoodsItem,		 				//same;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EEntryReleaseRejection) tag) {
			
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
				 
			case Motivation:
			case TODO_2:
				motivation = new Text(getScanner());					
				motivation.parse(tag.name());					
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
	    	switch ((EEntryReleaseRejection) tag) {
	    	
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
				 
			case ConveyanceReference:                      
			case ConveyanceNumber:
				 setConveyanceReference(value);
				 break;	
							 			
			case OfficeOfActualEntry:
			case TODO_1:
				 setCustomsOfficeOfActualEntry(value);
				 break;
							
			case ControlDate:
			case ControlResultDate:
				 setControlDate(value);
				 if (tag == EEntryReleaseRejection.ControlResultDate) {					 
					 setControlDateFormat(EFormat.KIDS_Date);
				 } else {					 
					 setControlDateFormat(EFormat.ST_Date);					 		   
				 }
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
  			return EEntryReleaseRejection.valueOf(token);
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

	public String getCustomsOfficeOfActualEntry() {
		return this.customsOfficeOfActualEntry;
	}
	public void setCustomsOfficeOfActualEntry(String argument) {
		this.customsOfficeOfActualEntry = argument;
	}

	public Text getMotivation() {
		return this.motivation;	
	}
	public void setMotivation(Text argument) {
		this.motivation = argument;
	}

	public String getControlDate() {
		return this.controlDate;	
	}
	public void setControlDate(String argument) {
		this.controlDate = argument;
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
	
	public EFormat getControlDateFormat() {
		return this.controlDateFormat;
	}
	
	public void setControlDateFormat(EFormat eFormat) {
		this.controlDateFormat = eFormat; 
	}
	
	public EFormat getUidsDateAndTimeFormat(String value) {  //EI20110208
		EFormat eFormat;
		 
		 if (!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}
}
