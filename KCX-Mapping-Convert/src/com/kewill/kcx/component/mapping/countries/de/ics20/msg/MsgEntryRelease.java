package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperationRelease;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.StatusInformation;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 23.10.2012.<br>
 * Description  : Contains Message Structure with fields used in ICSEntryRelease. 
 * 				: (IE330)
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MsgEntryRelease extends KCXMessage {

	private String msgName = "ICSEntryRelease";
	private String msgType = "";
	
	private String 	referenceNumber;
	private String 	generatedLRN;
	private String 	shipmentNumber;
	private String	mrn;
	private String 	customsOfficeFirstEntry;
	private String	dateOfPresentation;
	private String	controlResultDate;	
	private Party	carrier;
	private TIN		carrierTIN;	 
	private Party	personLodgingSuma;              //new for V20
	private TIN		personLodgingSumaTIN;	
	private Party	representative;                 //new for V20     
	private TIN		representativeTIN;	
	private List<ImportOperationRelease> importOperationList;
	private String	conveyanceReference;
	private TransportMeans meansOfTransportBorder;   //new for V20
	private String  declaredDateOfArrival;           //new for V20
	private StatusInformation statusInformation;     //new for V20
	
	private EFormat dateOfPresentationFormat;
	private EFormat controlDateFormat;
	private EFormat declaredDateOfArrivalFormat;      //new for V20
	
	
	public MsgEntryRelease() {
		super();				
	}

	public MsgEntryRelease(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	public MsgEntryRelease(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);	
		msgType = type;
	}
 
	private enum EEntryRelease {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		DateOfPresentation,				PresentationDateAndTime,
		MRN,							//same
		ShipmentNumber,                 CommercialReferenceNumber, CommercialReference,
		ConveyanceReference, 			ConveyanceNumber,
		MeansOfTransportBorder,         TransportAtBorder, 
		CustomsOfficeFirstEntry,		OfficeOfActualEntry,	OfficeOfFirstEntry,	
		CarrierTIN,						EntryCarrier, Carrier, //.TIN		
		CarrierAddress,					//Carrier.Address + EntAddress + VatId
		PersonLodgingSumaTIN,           PersonLodgingSuma,
		PersonLodgingSumaAddress,
		RepresentativeTIN,              Representative,
		RepresentativeAddress,
		ControlResultDate,				ControlDate,				
		ImportOperation,		 		//same
										GeneratedLRN,
		DeclaredDateOfArrival,          ExpectedArivalDateAndTime,
		StatusInformation;	 	   	    //same
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EEntryRelease) tag) {
			
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
			case PersonLodgingSumaTIN:			
				personLodgingSumaTIN = new TIN(getScanner());
				personLodgingSumaTIN.parse(tag.name());
				break;
			case PersonLodgingSumaAddress:
			case PersonLodgingSuma:	
				personLodgingSuma = new Party(getScanner());
				personLodgingSuma.parse(tag.name());	
				break;			
			case RepresentativeTIN:
				representativeTIN = new TIN(getScanner());
				representativeTIN.parse(tag.name());
				break;
			case RepresentativeAddress:
			case Representative:
				representative = new Party(getScanner());
				representative.parse(tag.name());	
				break;				
			case ImportOperation:
				ImportOperationRelease importOperation = new ImportOperationRelease(getScanner());					
				importOperation.parse(tag.name());	
				addImportOperationList(importOperation);
				break;
			case MeansOfTransportBorder:
			case TransportAtBorder:
				meansOfTransportBorder = new TransportMeans(getScanner(), msgType);
				meansOfTransportBorder.parse(tag.name());	
				break;
			case StatusInformation:
				statusInformation = new StatusInformation(getScanner());
				statusInformation.parse(tag.name());	
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
					 setDateOfPresentationFormat(Utils.getKidsDateAndTimeFormat(value)); 
				 } else {
					 setDateOfPresentationFormat(Utils.getUidsDateAndTimeFormat(value)); 
				 }
				 break;					 
			case ControlResultDate:
			case ControlDate:
				 setControlResultDate(value);
				 if (tag == EEntryRelease.ReferenceNumber) {					
					 setControlResultDateFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					
					 setControlResultDateFormat(Utils.getUidsDateAndTimeFormat(value)); 		
				 }
				 break;	
			case ConveyanceReference: 			
			case ConveyanceNumber:
				setConveyanceReference(value);
				 break;	
			case DeclaredDateOfArrival:
			case ExpectedArivalDateAndTime:
				setDeclaredDateOfArrival(value);
				 if (tag == EEntryRelease.DeclaredDateOfArrival) {					
					 setDeclaredDateOfArrivalFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					
					 setDeclaredDateOfArrivalFormat(Utils.getUidsDateAndTimeFormat(value)); 				
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
  			return EEntryRelease.valueOf(token);
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
	
	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}		
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);		
		}
		return this.personLodgingSuma;
	}
	
	public void setPersonLodgingSuma(Party argument) {
		this.personLodgingSuma = argument;
	}
	
	public Party getRepresentative() {
		if (representativeTIN != null) { 
			if (representative == null) {
				representative = new Party();
			}		
			representative.setPartyTIN(representativeTIN);		
		}
		return this.representative;
	}
	
	public void setRepresentative(Party argument) {
		this.representative = argument;
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
	
	public TransportMeans getMeansOfTransportBorder() {
		return this.meansOfTransportBorder;	
	}
	
	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		this.meansOfTransportBorder = meansOfTransportBorder;
	}	
	
	public String getDeclaredDateOfArrival() {
		return this.declaredDateOfArrival;	
	}
	
	public void setDeclaredDateOfArrival(String datetime) {
		this.declaredDateOfArrival = datetime;
	}
	public EFormat getDeclaredDateOfArrivalFormat() {
		return this.declaredDateOfArrivalFormat;
	}	
	public void setDeclaredDateOfArrivalFormat(EFormat eFormat) {
		this.declaredDateOfArrivalFormat = eFormat; 
	}	
	
	public StatusInformation getStatusInformation() {
		return this.statusInformation;	
	}
	public void setStatusInformation(StatusInformation status) {
		this.statusInformation = status;
	}
	
	
}
