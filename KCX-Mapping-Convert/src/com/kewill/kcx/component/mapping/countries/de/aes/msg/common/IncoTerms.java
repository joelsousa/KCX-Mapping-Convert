/*
 * Function    : IncoTerms (KIDS) == Incoterms (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the IncoTerms Data
 * 			   : with all Fields used in UIDS and KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 15.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: IncoTerms<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the IncoTerms Data with all Fields used in UIDS and KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class IncoTerms extends KCXMessage {
    private String incoTerm      	     = "";
    private String text  	             = "";
    private String place 	             = "";
    private String locationCode 	     = ""; // C.K. 10.02.2010

    private boolean debug   = false;

    
  	private enum EIncoTerms {
  	// Kids-TagNames, 		UIDS-TagNames
		IncoTerm,   		Code,
		Text,				Description,  Details,
		Place,				City,
		LocationCode;		// same C.K. 10.02.2012
     }

    public IncoTerms() {
    	super();
    }
    
    public IncoTerms(XmlMsgScanner scanner) {
  		super(scanner);
  	}
    
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
			return;
  		} else {
  			switch ((EIncoTerms) tag) {
  				case IncoTerm:
  				case Code:
  					setIncoTerm(value);
  					break;

  				case Text:
  				case Description:
  				case Details:                //BDP 20130425
  					setText(value);
  					break;

  				case Place:
  				case City:
  					setPlace(value);
  					break;
  					
  				case LocationCode:
  					setLocationCode(value);
  					break;
  				default:
  				    break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EIncoTerms.valueOf(token);
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

	public String getIncoTerm() {
		return incoTerm;
	}

	public void setIncoTerm(String incoTerm) {
		this.incoTerm = incoTerm;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	public boolean isEmpty() {
		return  Utils.isStringEmpty(incoTerm) && Utils.isStringEmpty(text) && 				
		        Utils.isStringEmpty(place) && Utils.isStringEmpty(locationCode);  		       		        
	}

}
