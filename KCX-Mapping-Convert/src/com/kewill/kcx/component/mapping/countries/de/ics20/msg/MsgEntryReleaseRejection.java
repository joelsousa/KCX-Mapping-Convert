package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 24.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSEntryReleaseRejection 
 * 				: (IE322).
 *                 
 * @author krzoska
 * @version 2.0.00
 */

public class MsgEntryReleaseRejection extends KCXMessage {

	private String msgName = "ICSEntryReleaseRejection";
	private String msgType = "";
	
	private String 	referenceNumber;
	private String 	conveyanceReference;
	private String 	shipmentNumber;
	private String	mrn;
	private String 	customsOfficeOfActualEntry;
	private Text	motivation;
	private String	controlDate;	
	private Party	carrier;
	private TIN		carrierTIN;	
	private List<GoodsItemShort> goodsItemList;
		
	private EFormat controlDateFormat;
	
	
	public MsgEntryReleaseRejection() {
		super();				
	}

	public MsgEntryReleaseRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	public MsgEntryReleaseRejection(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);
		msgType = type;
	}
 
	private enum EEntryReleaseRejection {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		ShipmentNumber,                 CommercialReferenceNumber,
		MRN,							//same
		ConveyanceReference,            ConveyanceNumber, 		
		CarrierTIN,						EntryCarrier, Carrier, //.TIN		
		CarrierAddress,					//Carrier.Address + EntAddress + VatId
		ActualOfficeOfFirstEntry,		OfficeOfActualEntry,
		EntryRejectionMotivation,		Motivation,
		ControlResultDate,				ControlDate,
		GoodsItem;		 				//same;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EEntryReleaseRejection) tag) {
			
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());
				break;
			case CarrierAddress:
			case EntryCarrier:
			case Carrier:
				carrier = new Party(getScanner());
				carrier.parse(tag.name());	
				break;			
			case Motivation:
			case EntryRejectionMotivation:
				motivation = new Text(getScanner());					
				motivation.parse(tag.name());					
				break;				
			case GoodsItem:
				GoodsItemShort item = new GoodsItemShort(getScanner(), msgName, msgType);					
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
			case ActualOfficeOfFirstEntry:
				 setCustomsOfficeOfActualEntry(value);
				 break;						
			case ControlDate:
			case ControlResultDate:
				 setControlDate(value);
				 if (tag == EEntryReleaseRejection.ControlResultDate) {					 
					 setControlDateFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					 
					 setControlDateFormat(Utils.getKidsDateAndTimeFormat(value));					 		   
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
	/*
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}
    */
	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String argument) {
		this.msgType = argument;
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
		if (carrierTIN != null) { 
			if (carrier == null) {
				carrier = new Party();
			}		
			carrier.setPartyTIN(carrierTIN);		
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

	public EFormat getControlDateFormat() {
		return this.controlDateFormat;
	}
	
	public void setControlDateFormat(EFormat eFormat) {
		this.controlDateFormat = eFormat; 
	}
		
}
