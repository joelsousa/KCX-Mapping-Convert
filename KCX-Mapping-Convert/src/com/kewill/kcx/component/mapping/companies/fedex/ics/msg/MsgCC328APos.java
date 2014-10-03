package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Connr2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Idemeatragi970;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgCC328APos<br>
 * Date		: 27.11.2010<br>
 * Beschreibung	: -.
 * 
 * @author Lassiter
 * @version 1.0.00
 */

public class MsgCC328APos extends KCXMessage {
	
	private String					itemNumber;
	private String					shipmentNumber;
	
	//Documents
	private Prodocd2 				prodocdc2;
	private List<Prodocd2> 			prodocdc2List = null;
	
	//Containers
	private Connr2					connr2;
	private List<Connr2>			connr2List = null;
	
	//Means Of Transport Border
	private Idemeatragi970 			idemeatragi970;
	private List<Idemeatragi970> 	idemeatragi970List = null;
	
	public MsgCC328APos() {
		super();
	}
	
	public MsgCC328APos(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC328APos(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EGoodsItemTags {
		IteNumGDS7,
		ComRefNumGIM1,
		PRODOCDC2,
		CONNR2,
		IDEMEATRAGI970; 
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
			switch ((EGoodsItemTags) tag) {
			case PRODOCDC2: prodocdc2 = new Prodocd2(getScanner());
				prodocdc2.parse(tag.name());
				addProdocdc2List(prodocdc2);
				break;
			case CONNR2: connr2 = new Connr2(getScanner());
				connr2.parse(tag.name());
				addConnr2List(connr2);
				break;
			case IDEMEATRAGI970: idemeatragi970 = new Idemeatragi970(getScanner());
				idemeatragi970.parse(tag.name());
				addIdemeatragi970List(idemeatragi970);
				break;
			default:
				return;
			}
		} else {
			switch ((EGoodsItemTags) tag) {
			case IteNumGDS7: 	setItemNumber(value);
				break;
			case ComRefNumGIM1: setShipmentNumber(value);
				break;
			default:
				return;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return EGoodsItemTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
	}
	
	private void addConnr2List(Connr2 connr2) {
		if (connr2List == null) {
			connr2List = new Vector<Connr2>();
		}
		connr2List.add(connr2);
	}
	
	private void addProdocdc2List(Prodocd2 prodocdc2) {
		if (prodocdc2List == null) {
			prodocdc2List = new Vector<Prodocd2>();
		}
		
		prodocdc2List.add(prodocdc2);
	}
	
	private void addIdemeatragi970List(Idemeatragi970 idemeatragi970) {
		if (idemeatragi970List == null) {
			idemeatragi970List = new Vector<Idemeatragi970>();
		}
		idemeatragi970List.add(idemeatragi970);
	}
}
