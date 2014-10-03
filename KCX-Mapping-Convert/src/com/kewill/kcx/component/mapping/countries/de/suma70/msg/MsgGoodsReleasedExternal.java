package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;


/**
 * Module		: Manifest<br>.
 * Created		: 27.06.2013<br>
 * Description	: KIDS GoodsReleasedExternal
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MsgGoodsReleasedExternal extends KCXMessage { 
	
	private String			 	msgName = "GoodsReleasedExternal";
	private String			 	dateOfPresentation;
	private String			 	referenceNumber;
	private String			 	registrationNumber;		
	private String 				registrationDate;
	private Address				custodianAddress;
	private Address 			placeOfCustody;	
	private LocalApplication localApplication;
		
		
	public MsgGoodsReleasedExternal() {
		super();
	}
	public MsgGoodsReleasedExternal(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EGoodsReleasedExternal {
		//KIDS:							UIDS:
		DateOfPresentation,        		
		ReferenceNumber,					
		RegistrationNumber,	
		RegistrationDate,
		Custodian,
		PlaceOfCustody,
		LocalApplication,		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EGoodsReleasedExternal) tag) {
						
			case PlaceOfCustody:
				placeOfCustody = new Address(getScanner());
				placeOfCustody.parse(tag.name());
				break;		
				
			case Custodian:
				custodianAddress = new Address(getScanner());
				custodianAddress.parse(tag.name());
				break;
				
			case LocalApplication:
				localApplication = new LocalApplication(getScanner());
				localApplication.parse(tag.name());	
				break;
				
			default:
				return;
			}
		} else {
			switch ((EGoodsReleasedExternal) tag) {
			case DateOfPresentation:		
				setDateOfPresentation(value);
				break;
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
			case RegistrationNumber:			
				setRegistrationNumber(value);
				break;
			case RegistrationDate:
				setRegistrationDate(value);
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
			return EGoodsReleasedExternal.valueOf(token);
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
	public String getDateOfPresentation() {
		return dateOfPresentation;
	}
	public void setDateOfPresentation(String dateOfPresentation) {
		this.dateOfPresentation = dateOfPresentation;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Address getCustodian() {
		return custodianAddress;
	}
	public void setCustodian(Address custodian) {
		this.custodianAddress = custodian;
	}
	public Address getPlaceOfCustody() {
		return placeOfCustody;
	}
	public void setPlaceOfCustody(Address placeOfCustody) {
		this.placeOfCustody = placeOfCustody;
	}
	
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String dateOfRegistration) {
		this.registrationDate = dateOfRegistration;
	}
	
	public LocalApplication getLocalApplication() {
		return localApplication;
	}
	public void setLocalApplication(LocalApplication local) {
		this.localApplication = local;
	}
		
}
