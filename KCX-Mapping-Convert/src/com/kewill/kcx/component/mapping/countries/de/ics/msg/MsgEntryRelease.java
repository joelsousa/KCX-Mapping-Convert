package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperationRelease;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgEntryRelease IE330<br>
 * Erstellt		: 04.02.2011<br>
 * Beschreibung : Contains Message Structure with fields used in ICSEntryRelease (IE330)
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgEntryRelease extends KCXMessage {

	private String msgName = "ICSEntryRelease";
	private String 				referenceNumber;
	private String 				generatedLRN;
	private String 				shipmentNumber;
	private String				mrn;
	private String 				customsOfficeFirstEntry;
	private String				dateOfPresentation;
	private String				controlResultDate;	
	private Party				carrier;
	private TIN					carrierTIN;
	private Address				carrierAdr;	 		
	private List<ImportOperationRelease> importOperationList;
	private String				conveyanceReference;
	
	private EFormat dateOfPresentationFormat;
	private EFormat controlDateFormat;
	
	public MsgEntryRelease() {
		super();				
	}

	public MsgEntryRelease(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
 
	private enum EEntryRelease {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		DateOfPresentation,				PresentationDateAndTime,
		MRN,							//same
		ShipmentNumber,                 CommercialReferenceNumber, CommercialReference,
		ConveyanceReference, 			ConveyanceNumber,
		CustomsOfficeFirstEntry,		OfficeOfActualEntry,	OfficeOfFirstEntry,	
		CarrierTIN,						EntryCarrier, Carrier, //.TIN		
		CarrierAddress,					//Carrier.Address + EntAddress + VatId
		ControlResultDate,				ControlDate,				
		ImportOperation,		 		//same
										GeneratedLRN;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EEntryRelease) tag) {
			
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
				 
			case ImportOperation:
				ImportOperationRelease importOperation = new ImportOperationRelease(getScanner());					
				importOperation.parse(tag.name());	
				addImportOperationList(importOperation);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EEntryRelease) tag) {
	    	
			case ReferenceNumber:
			case LocalReferenceNumber:				 
				 setReferenceNumber(value);
				 break;	
				 
			case ShipmentNumber:
			case CommercialReferenceNumber:
			case CommercialReference:
				 setShipmentNumber(value);
				 break;
				 
			case MRN:
				 setMrn(value);	
				 break;
				 			                 
			case GeneratedLRN:
				 setGeneratedLRN(value);
				 break;	
							 
			case CustomsOfficeFirstEntry:
			case OfficeOfActualEntry:
			case OfficeOfFirstEntry:
				 setCustomsOfficeFirstEntry(value);
				 break;
			
			case DateOfPresentation:
			case PresentationDateAndTime:
				 setDateOfPresentation(value);
				 if (tag == EEntryRelease.DateOfPresentation) {
					 setDateOfPresentationFormat(EFormat.KIDS_DateTime); 
				 } else {
					 setDateOfPresentationFormat(getUidsDateAndTimeFormat(value)); //EI20110208
				 }
				 break;	
				 
			case ControlResultDate:
			case ControlDate:
				 setControlResultDate(value);
				 if (tag == EEntryRelease.ReferenceNumber) {					
					 setControlResultDateFormat(EFormat.KIDS_Date);
				 } else {					
					 setControlResultDateFormat(EFormat.ST_Date); //EI20110208				
				 }
				 break;	
			case ConveyanceReference: 			
			case ConveyanceNumber:
				setConveyanceReference(value);
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
  			return EEntryRelease.valueOf(token);
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

	public String getGeneratedLRN() {
		return this.generatedLRN;
	}
	public void setGeneratedLRN(String argument) {
		this.generatedLRN = argument;
	}

	public String getCustomsOfficeFirstEntry() {
		return this.customsOfficeFirstEntry;
	}
	public void setCustomsOfficeFirstEntry(String argument) {
		this.customsOfficeFirstEntry = argument;
	}

	public String getDateOfPresentation() {
		return this.dateOfPresentation;	
	}
	public void setDateOfPresentation(String argument) {
		this.dateOfPresentation = argument;
	}

	public String getControlResultDate() {
		return this.controlResultDate;	
	}
	public void setControlResultDate(String argument) {
		this.controlResultDate = argument;
	}
	
	public String getConveyanceReference() {
		return this.conveyanceReference;
	}
	public void setConveyanceReference(String argument) {
		this.conveyanceReference = argument;
		
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
    
	
	public List<ImportOperationRelease> getImportOperationList() {
		return this.importOperationList;	
	}
	public void setImportOperationList(List<ImportOperationRelease> list) {
		this.importOperationList = list;
	}
	public void addImportOperationList(ImportOperationRelease argument) {
		if (importOperationList == null) {
			importOperationList = new ArrayList<ImportOperationRelease>();
		}
		this.importOperationList.add(argument);
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
	
	public EFormat getDateOfPresentationFormat() {
		return this.dateOfPresentationFormat;
	}
	
	public void setDateOfPresentationFormat(EFormat eFormat) {
		this.dateOfPresentationFormat = eFormat;
	}
	
	public EFormat getControlResultDateFormat() {
		return this.controlDateFormat;
	}
	
	public void setControlResultDateFormat(EFormat eFormat) {
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
