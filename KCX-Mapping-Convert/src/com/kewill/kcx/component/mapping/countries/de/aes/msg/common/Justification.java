/*
 * Function    : Justification(UIDS)
 * Titel       :
 * Date        : 09.03.2009
 * Author      : Kewill CSF / Krzoska
 * Description : Contains the Justification Data
 * 			   : with all Fields used in UIDS (no Kids Data because not Structure Tag in KIDS)
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
 * Modul		: Justification<br>
 * Erstellt		: 09.03.2009<br>
 * Beschreibung	: Contains the Justification data with all Fields used in UIDS 
 *                (no KIDS data because no structure tag in KIDS).
 * 
 * @author Krzoska
 * @version 1.0.00
 */
public class Justification extends KCXMessage {

    private String description;
    private String code;						
  
    private boolean debug   = false;

    public Justification() {
    	super();
    }

    public Justification(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}

  	private enum EJustification {
    //UIDS
  		Description,
  		Code;
     }

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EJustification) tag) {  			
			default:
					return;				
  			}
  		} else {

  			switch ((EJustification) tag) {
  				case Description:
  					setDescription(value);
  					break;
  				
  				case Code:
  					setCode(value);
  					break;  
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EJustification.valueOf(token);
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

	public String getDescription() {
		return description;
	
	}

	public void setDescription(String description) {
		this.description = Utils.checkNull(description);
	}

	public String getCode() {
		return code;
	
	}

	public void setCode(String code) {
		this.code = Utils.checkNull(code);
	}
 }
