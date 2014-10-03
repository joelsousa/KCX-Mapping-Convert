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

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;

/**
 * Modul		: MsgExtNot<br>
 * Erstellt		: 23.04.2009<br>
 * Beschreibung	: Contains the Message ExitNotification  with all Fields used in UIDS and  KIDS. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExtNot extends KCXMessage {

	// simple tags
	private String ucrNumber;
	private String finalCode;
	private String exitOrForwardingTime;	
	private String kindOfShipment;
	private String ucrOtherSystem;
	private String referenceNumber;
	private String intendedOfficeOfExit;
	private String realOfficeOfExit;
	private Party forwarder;
		
    private List<MsgExtNotPos>goodsItemList;
	
	private enum EExpAdnTags {

		// Kids-TagNames, 				UIDS-TagNames;
    	UCRNumber,						DocumentReferenceNumber,
    	FinalCode,						Termination,
    	ExitOrForwardingTime,			DateOfExit,
    	KindOfShipment,					TypeOfShipment,
    	UCROtherSystem,					ExternalRegistrationNumber,
		ReferenceNumber,				//same 
		IntendedOfficeOfExit,			CustomsOffices, //CustomsOffices.OfficeOfActualExit
		RealOfficeOfExit,								//CustomsOffices.OfficeOfExit
		Forwarder,
		GoodsItem;						//same 
		  
	}
	
	public MsgExtNot() {
		super();		
	}
	
	public MsgExtNot(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			
			switch ((EExpAdnTags) tag) {
			case GoodsItem:	
				MsgExtNotPos goodsItem = new  MsgExtNotPos(getScanner());
				goodsItem .parse(tag.name());
				addItemList(goodsItem);
				break;
				
			case Forwarder:	
				forwarder = new  Party(getScanner());
				forwarder .parse(tag.name());				
				break;
				
			case CustomsOffices:
				CustomsOffices customsOffices = new CustomsOffices();
				intendedOfficeOfExit = customsOffices.getOfficeOfActualExit();
				realOfficeOfExit = customsOffices.getOfficeOfExit();
				break;
				
			default:
				return;
			}
		} else {
			
			switch ((EExpAdnTags) tag) {
		
				case UCRNumber: 
				case DocumentReferenceNumber:
					setUCRNumber(value);
					break;
					
				case FinalCode:
				case Termination:
					setFinalCode(value);
					break;
					
				case ExitOrForwardingTime:
				case DateOfExit:
					setExitOrForwardingTime(value);
					break;
					
				case KindOfShipment:
				case TypeOfShipment:
					setKindOfShipment(value);
					break;
					
				case UCROtherSystem:
				case ExternalRegistrationNumber:
					setUCROtherSystem(value);
					break;
					
				case ReferenceNumber: 
					setReferenceNumber(value);
					break;
													   
				case IntendedOfficeOfExit:
					setIntendedOfficeOfExit(value);
					break;
					
				case RealOfficeOfExit:
					setRealOfficeOfExit(value);
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
	
	public String getUCRNumber() {
		return ucrNumber;	
	}
	public void setUCRNumber(String argument) {
		ucrNumber = argument;
	}

	public String getFinalCode() {
		return finalCode;	
	}
	public void setFinalCode(String argument) {
		finalCode = argument;
	}
	
	public String getExitOrForwardingTime() {
		return exitOrForwardingTime;	
	}
	public void setExitOrForwardingTime(String argument) {
		exitOrForwardingTime = argument;
	}
	
	public String getKindOfShipment() {
		return kindOfShipment;	
	}
	public void setKindOfShipment(String argument) {
		kindOfShipment = argument;
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

	public String getIntendedOfficeOfExit() {
		return intendedOfficeOfExit;	
	}
	public void setIntendedOfficeOfExit(String argument) {
		intendedOfficeOfExit = argument;
	}
	public Party getForwarder() {
		return forwarder;	
	}
	public void setForwarder(Party argument) {
		forwarder = argument;
	}	
	
	public void setGoodsItemList(List<MsgExtNotPos> list) {
		goodsItemList = list;	
	}
	public List<MsgExtNotPos> getGoodsItemList() {
		return goodsItemList;	
	}
    public void addItemList(MsgExtNotPos argument) {
    	if (goodsItemList == null) {
    		goodsItemList  = new Vector<MsgExtNotPos>();
    	}
    	this.goodsItemList.add(argument);
    }
}
