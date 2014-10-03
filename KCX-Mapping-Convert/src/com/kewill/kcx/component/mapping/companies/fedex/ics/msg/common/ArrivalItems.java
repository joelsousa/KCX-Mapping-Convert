package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/*
 * Function    : ArrivalItems 
 * Titel       :
 * Date        : 07.01.2011
 * Author      : krzoska
 * Description : ArrivalItems  
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

public class ArrivalItems extends KCXMessage {
	private boolean debug = false;
	private XMLEventReader parser = null;
	
	private ArrivalItem arrivalItem = null;
	private ArrayList<ArrivalItem> listArrivalItem = null;
		
	
	private enum EArrivalItems {
		ArrivalItem;
	}
	
	public ArrivalItems() {
		super();
	}
	
	public ArrivalItems(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
			switch ((EArrivalItems) tag) {
			case ArrivalItem:	arrivalItem	= new ArrivalItem(getScanner());
								arrivalItem.parse(tag.name());
								addArrivalItemList(arrivalItem);
			break;
				default:
					return;
			}
		} else {
			switch ((EArrivalItems) tag) {
				default:
					return;
			}
		}
	}
	
	private void addArrivalItemList(ArrivalItem arrivalItem) {
		if (listArrivalItem == null) {
			listArrivalItem = new ArrayList<ArrivalItem>();
		}
		listArrivalItem.add(arrivalItem);
	}

	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}
	
	public Enum translate(String token) {
		try {
			return EArrivalItems.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public ArrayList<ArrivalItem> getListArrivalItem() {
		return listArrivalItem;
	
	}
}
