/*
 * Function    : MsgExtNot.java
 * Titel       :
 * Date        : 23.04.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains the Message ExitNotification 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNotPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: AES<br>
 * Created		: 23.07.2013<br>
 * Description	: Contains the Message ControlNotification  with all Fields used in UIDS and  KIDS. 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class MsgControlNotification extends KCXMessage {

	// simple tags
	private String ucrNumber;
	private String  timeofInspection;	
	private EFormat timeofInspectionFormat;	//EI20131203
	private String kindOfInspection;
	private String ucrOtherSystem;
	private String mrn;
	private String annotation;	
	private String referenceNumber;
	private ContactPerson contact;	
	private String realOfficeOfExit;
	private String preCustomsClearance;     //EI20130812
	private TIN forwarderTin;		
    private ArrayList<MsgControlNotificationPos>goodsItemList;
	
	private enum EControlNotification {

		// Kids-TagNames, 				UIDS-TagNames;
    	UCRNumber,						DocumentReferenceNumber,
    	TimeOfInspection,				DateOfControl,
    	KindOfInspection,			    TypeOfControl,	
    	UCROtherSystem,					ExternalRegistrationNumber,    	
    	Annotation,						Remark,
		ReferenceNumber,				//same 
		Contact, Clerk,						//same
		RealOfficeOfExit,				CustomsOffices, //CustomsOffices.OfficeOfExit
		PreCustomsClearance,			//same //EI20130812: eigentlich nur für EXIT-ControlNotification
		ForwarderTIN,					Shipper,  //Forwarder
		GoodsItem;						//same 
		  
	}
	
	public MsgControlNotification() {
		super();		
	}
	
	public MsgControlNotification(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			
			switch ((EControlNotification) tag) {
			case GoodsItem:	
				MsgControlNotificationPos goodsItem = new MsgControlNotificationPos(getScanner());
				goodsItem.parse(tag.name());
				addItemList(goodsItem);
				break;
				
			case Shipper:	//Forwarder
				Party party = new Party(getScanner());
				party.parse(tag.name());
				if (forwarderTin == null) {
					forwarderTin = new TIN();
				}
				forwarderTin = party.getPartyTIN();
				break;
				
			case ForwarderTIN:	
				forwarderTin = new TIN(getScanner());
				forwarderTin.parse(tag.name());					
				break;
				
			case CustomsOffices:
				CustomsOffices customsOffices = new CustomsOffices();	
				if (customsOffices != null) {
					realOfficeOfExit = customsOffices.getOfficeOfExit();
				}
				break;
				
			case Contact:
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());	
				break;
				
			default:
				return;
			}
		} else {
			
			switch ((EControlNotification) tag) {
		
				case ReferenceNumber: 
					setReferenceNumber(value);
					break;
					
				case UCRNumber: 
				case DocumentReferenceNumber:				
					setUCRNumber(value);
					break;
									
				case UCROtherSystem:
				case ExternalRegistrationNumber:
					setUCROtherSystem(value);
					break;
					
				case TimeOfInspection: 
				case DateOfControl:
					setTimeOfInspection(value);					
					if (value.indexOf('-') == -1) {
						setTimeOfInspectionFormat(EFormat.KIDS_DateTime);
					} else {
						setTimeOfInspectionFormat(getUidsDateAndTimeFormat(value)); 
					}	
					break;
					
				case KindOfInspection: 
				case TypeOfControl:
					setKindOfInspection(value);
					break;
					
				case Annotation: 
				case Remark:
					setAnnotation(value);
					break;
									
				case RealOfficeOfExit:
					setRealOfficeOfExit(value);
					break;
					
				case Clerk:
					setContact(value);
					break;
					
				case PreCustomsClearance:
					setPreCustomsClearance(value);
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
			return EControlNotification.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
	
	public String getUCRNumber() {
		return ucrNumber;	
	}
	public void setUCRNumber(String argument) {
		ucrNumber = argument;
	}

	public String getTimeOfInspection() {
		return timeofInspection;	
	}
	public void setTimeOfInspection(String argument) {
		timeofInspection = argument;
	}
	public EFormat getTimeOfInspectionFormat() {
		return timeofInspectionFormat;
	}
	public void setTimeOfInspectionFormat(EFormat exitTimeFormat) {
		this.timeofInspectionFormat = exitTimeFormat;
	}
	
	public String getKindOfInspection() {
		return kindOfInspection;	
	}
	public void setKindOfInspection(String argument) {
		kindOfInspection = argument;
	}
		
	public String getUCROtherSystem() {
		return ucrOtherSystem;	
	}
	public void setUCROtherSystem(String argument) {
		this.ucrOtherSystem = argument;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;	
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getRealOfficeOfExit() {
		return realOfficeOfExit;	
	}
	public void setRealOfficeOfExit(String argument) {
		this.realOfficeOfExit = argument;
	}
	
	public TIN getForwarderTin() {
		return forwarderTin;	
	}
	public void setForwarderTin(TIN argument) {
		forwarderTin = argument;
	}	
	public Party getForwarder() {
		if (forwarderTin == null) {
			return null;
		}
		Party forwarder = new Party();
		forwarder.setPartyTIN(forwarderTin);
		return forwarder;	
	}
	
	public String getAnnotation() {
		return annotation;	
	}
	public void setAnnotation(String argument) {
		this.annotation = argument;
	}
		
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson contact) {
		this.contact = contact;
	}
	public void setContact(String name) {
		if (contact == null) {			
			contact = new ContactPerson();			
		}
		contact.setName(name);
	}
	
	public String getPreCustomsClearance() {
		return preCustomsClearance;
	}
	public void setPreCustomsClearance(String preCustomsClearance) {
		this.preCustomsClearance = preCustomsClearance;
	}

	public void setGoodsItemList(ArrayList<MsgControlNotificationPos> list) {
		goodsItemList = list;	
	}
	public ArrayList<MsgControlNotificationPos> getGoodsItemList() {
		return goodsItemList;	
	}
    public void addItemList(MsgControlNotificationPos argument) {
    	if (goodsItemList == null) {
    		goodsItemList  = new ArrayList<MsgControlNotificationPos>();
    	}
    	this.goodsItemList.add(argument);
    }
    
    public EFormat getUidsDateAndTimeFormat(String value) {  
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
