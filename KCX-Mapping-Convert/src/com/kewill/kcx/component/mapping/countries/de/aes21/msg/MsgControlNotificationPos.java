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

package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: AES<br>
 * Created		: 23.07.2013<br>
 * Description	: Contains the Message ControlNotification with all Fields used in UIDS and  KIDS. 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class MsgControlNotificationPos extends KCXMessage {
	
	private String itemNumber; 
	private String ucrOtherSystem;
	private String annotation;
	private String kindOfInspection;		
	
	public MsgControlNotificationPos() {
			super();
	}
	
	public MsgControlNotificationPos(XMLEventReader parser) {
		super(parser);		
	}		
	
	public MsgControlNotificationPos(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	 private enum EControlNotificationPos {
         //KIDS                 UIDS
		 ItemNumber,           //same 
		 UcrOtherSystem,		ExternalRegistrationNumber,		
		 Annotation,			Remark,
		 KindOfInspection,		TypeOfControl; 		 
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EControlNotificationPos) tag) {
					
				default:
						return;
				}
			} else {
				switch ((EControlNotificationPos) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;	
						
					case UcrOtherSystem:
					case ExternalRegistrationNumber:
						setUcrOtherSystem(value);
						break;	
						
					case Annotation:
					case Remark:
						setAnnotation(value);
						break;	
					
					case KindOfInspection:
					case TypeOfControl:
						setKindOfInspection(value);
						break;	
						
					default:
						return;
				}
			}
		}

 
	public void stoppElement(Enum tag) {
	}
		
	public Enum <EControlNotificationPos> translate(String token) {
		try {
				return EControlNotificationPos.valueOf(token);
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

	public String getUcrOtherSystem() {
		return ucrOtherSystem;
	}

	public void setUcrOtherSystem(String ucrOtherSystem) {
		this.ucrOtherSystem = ucrOtherSystem;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getKindOfInspection() {
		return kindOfInspection;
	}

	public void setKindOfInspection(String kindOfInspection) {
		this.kindOfInspection = kindOfInspection;
	}
   	
}
