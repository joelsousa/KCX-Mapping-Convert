package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/*
* Function    : ArrivalItem 
* Titel       :
* Date        : 07.01.2011
* Author      : krzoska
* Description : ArrivalItem  
* Parameters  :

* Changes
* ------------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*
*/

/**
* Modul		: ArrivalItems<br>
* Erstellt		: 07.01.2011<br>
* Beschreibung	: -.
* 
* @author krzoska
* @version 1.0.00
*/

public class ArrivalItem extends KCXMessage {
	private boolean debug = false;

	public ArrivalItem() {
		super();
	}
	
	public ArrivalItem(XmlMsgScanner scanner) {
		super(scanner);
 	}

	private String arrivalItemNumber;
	private Prodocd2 transportDocumentData;
	private CustomsDataReferences customsDataReferences;
	
	private enum EArrivalItem {
		ArrivalItemNumber,
		TransportDocumentData,
		CustomsDataReferences;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
			switch ((EArrivalItem) tag) {
			case TransportDocumentData:	
				transportDocumentData = new Prodocd2(getScanner());
				transportDocumentData.parse(tag.name());
			break;
			case CustomsDataReferences: 
				customsDataReferences = new CustomsDataReferences(getScanner());
				customsDataReferences .parse(tag.name());
			break;
			default: 
				return;
			}
		} else {
			switch ((EArrivalItem) tag) {
				case ArrivalItemNumber:
					setArrivalItemNumber(value);
					break;  
				default:
					return;
			}
		}
	}
	
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}
	
	public Enum translate(String token) {
		try {
			return EArrivalItem.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
	
	public String getArrivalItemNumber() {
		return arrivalItemNumber;
	}
	public void setArrivalItemNumber(String argument) {
		arrivalItemNumber = argument;
	}
	public Prodocd2 getTransportDocumentData() {
		return transportDocumentData;	
	}

	public void setTransportDocumentData(Prodocd2 transportDocumentData) {
		this.transportDocumentData = transportDocumentData;
	}

	public CustomsDataReferences getCustomsDataReferences() {
		return customsDataReferences;	
	}

	public void setCustomsDataReferences(CustomsDataReferences customsDataReferences) {
		this.customsDataReferences = customsDataReferences;
	}
}
