/*
 * Function    : Error (KIDS, CT_ErrorType) == Error (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description : Contains the Error Data
 * 			   : with all Fields used in UIDS and  KIDS
 * Parameters  :

 * Changes
 * ------------
 * Author      : EI
 * Date        : 15.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes 
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ErrorType<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the Error Data with all Fields used in UIDS and  KIDS. 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class ErrorType extends KCXMessage {

	private String uniqueNumber;	// Laufende Nummer
	private String code;			// Fehlercode; 
	private String text;			// Fehlertext; 
	private String pointer;			// Zeiger; 
	private String number;			// Fehlernummer (nur Schweiz)
	
    private boolean debug   = false;

	private enum EBusiness {
	// Kids-TagNames, 			UIDS-TagNames
		UniqueNumber,			//not in UIDS
  		Code,					Type,
  		Text,					Reason,
  		Pointer,				//same 
  		Number;					//not in UIDS
    }
	
    public ErrorType() {
    	super();    		
    }
    
    public ErrorType(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EBusiness) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EBusiness) tag) {
  			
  			case UniqueNumber:
				setUniqueNumber(value);
				break;
				
			case Code:
			case Type:
				setCode(value);
				break;
				
			case Text:
			case Reason:
				setText(value);
				break;
				
			case Pointer:
				setPointer(value);
				break;
				
			case Number:
				setNumber(value);
				break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EBusiness.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
  	
	public String getUniqueNumber() {
		return uniqueNumber;
	}
	public void setUniqueNumber(String uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPointer() {
		return pointer;
	}
	public void setPointer(String pointer) {
		this.pointer = pointer;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
