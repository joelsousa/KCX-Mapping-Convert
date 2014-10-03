/*
 * Function    : MsgExpAdnPos .java
 * Titel       :
 * Date        : 13.09.2008
 * Author      : Kewill CSF / krzoska
 * Description : Contains Goodsitem Data with all Fields used in KIDS,
 * 			   : to use for different Messages
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 05.03.2009
 * Label       :
 * Description : V60 - added member: bondedWarehouseCompletion, inwardProcessingCompletion
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgExpAdnPos<br>
 * Erstellt		: 13.09.2008<br>
 * Beschreibung	: Contains Goodsitem Data with all Fields used in KIDS, to use for different Messages.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MsgExpAmdPos extends KCXMessage {
	
	private String itemNumber; 
	private String netMass;                  //n(11,3) 
	private String grossMass; 				 //n(11,3)
	private ExportRefundItem exportRefundItem;

    private boolean debug;
    
	public MsgExpAmdPos() {
			super();
	}
	
	public MsgExpAmdPos(XMLEventReader parser) {
		super(parser);		
	}	
	
	
	public MsgExpAmdPos(XmlMsgScanner scanner) {
  		super(scanner);
  	}


	 private enum EADNItem {
         //KIDS                 UIDS
		 ItemNumber,           //same in UIDS
		 NetMass,              //same in UIDS 
		 GrossMass, 		   //same in UIDS
		 ExportRefundItem,   ExportRestitutionItem;		
		}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EADNItem) tag) {
					case ExportRefundItem:
					case ExportRestitutionItem:
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());
						break;
						
				default:
						return;
				}
			} else {
				switch ((EADNItem) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;
						
					case NetMass:
						setNetMass(value);
						break;	
					case GrossMass:
						setGrossMass(value);
						break;
				}
			}
		}

 
	public void stoppElement(Enum tag) {
	}
		
	public Enum  <EADNItem>translate(String token) {
		try {
				return EADNItem.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

    public String getItemNumber() {
    	return itemNumber;
    }
    public void setItemNumber(String itemNumber) {
    	this.itemNumber = itemNumber;
    }

    public String getNetMass() {
    	return netMass;
    }
    public void setNetMass(String netMass) {
    	this.netMass = netMass;
    }
	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String grossMass) {
		this.grossMass = grossMass;
	}
	
	public ExportRefundItem getExportRefundItem() {
		return exportRefundItem;
	}	
	public void setExportRefundItem(ExportRefundItem argument) {
		this.exportRefundItem = argument;
	}	
}
