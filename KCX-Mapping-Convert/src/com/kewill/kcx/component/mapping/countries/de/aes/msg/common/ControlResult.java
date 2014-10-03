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
 * Module		: Exit<br>
 * Created		: 10.08.2013<br>
 * Description	: Contains the ControlResult Data for UIDS ExitAuthorisation. 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class ControlResult extends KCXMessage {

	private String type;	
	private String date;			
	private String result;			
	private String status;			
	//IWA-TODO: private ArrayList<todo> inCharge;	//for ExitAuthorisation will be mapped only one Memeber: Status		
	// in future - if all Member will be used, so this part should be programmed
    private boolean debug   = false;

	private enum EBusiness {
	// Uids-TagNames, 			
		Type,			
  		Date,					
  		Result,					
  		Status,				
  		InCharge;					
    }
	
    public ControlResult() {
    	super();    		
    }
    
    public ControlResult(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EBusiness) tag) {
  			case InCharge:
  				//todo
  				break;
  				
  			default:
  					return;
  			}
  		} else {
  			switch ((EBusiness) tag) {
  			
  			case Type:
				setType(value);
				break;
							
			case Date:
				setDate(value);
				break;
							
			case Result:
				setResult(value);
				break;
				
			case Status:
				setStatus(value);
				break;
			
			default:
					return;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
 
}
