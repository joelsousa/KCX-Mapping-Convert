/*
* Function    : MsgExtNotPos.java
 * Titel       :
 * Date        : 23.04.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains the Message ExitNotification 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgExtNotPos<br>
 * Erstellt		: 23.04.2009<br>
 * Beschreibung	: Contains the Message ExitNotification with all Fields used in UIDS and  KIDS. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExtNotPos extends KCXMessage {
	
	private String itemNumber; 
	private List <Packages> packagesList;
	
//    private boolean debug;
    
	public MsgExtNotPos() {
			super();
	}
	
	public MsgExtNotPos(XMLEventReader parser) {
		super(parser);		
	}		
	
	public MsgExtNotPos(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	 private enum EADNItem {
         //KIDS                 UIDS
		 ItemNumber,           //same in UIDS
		 Package,              Packaging; 		 
		}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EADNItem) tag) {
					case Packaging:
					case Package:
						Packages packages = new Packages(getScanner());
						packages.parse(tag.name());
						addPackagesList(packages);
						break;
						
				default:
						return;
				}
			} else {
				switch ((EADNItem) tag) {
					case ItemNumber:
						setItemNumber(value);
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
   
	public void setPackagesList(List<Packages> list) {
		this.packagesList = list;
	}
	
	public List getPackagesList() {
		return packagesList;
	}	
	public void addPackagesList(Packages argument) {
		if (packagesList == null) {
			packagesList = new Vector<Packages>();
		}
		this.packagesList.add(argument); 
	}	
}
