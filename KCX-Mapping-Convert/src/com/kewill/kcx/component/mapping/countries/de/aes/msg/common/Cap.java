package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Cap<br>
 * Created		: 30.10.2009<br>
 * Description	: -.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Cap extends KCXMessage {

    private String ibClaimRef;			
    private String ibClaimType;	//n(15,2)	
    private String ibRegNo;
    private String ibGan;  
 
  	private enum ECap {
  		IBClaimRef,	
  		IBClaimType,
  		IBRegNo,
		IBGAN;
  	} 

    public Cap() {
    	super();    		
    }
    
    public Cap(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECap) tag) {
  				default:
  					return;
  			}
  		} else {
  			switch ((ECap) tag) {  			   
  				case IBClaimRef:
  					setIBClaimRef(value);
  					break;  				
  				case IBClaimType:
  					setIBClaimType(value);
  					break;  					
  				case IBRegNo:
  					setIBRegNo(value);
  					break;  					
  				case IBGAN:
  					setIBGAN(value);
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
  			return ECap.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getIBClaimType() {
		return ibClaimType;
	}

	public void setIBClaimType(String argument) {
		this.ibClaimType = argument;
	}
	
	public String getIBClaimRef() {
		return ibClaimRef;
	}

	public void setIBClaimRef(String argument) {
		this.ibClaimRef = argument;
	}
	
	public String getIBRegNo() {
		return ibRegNo;
	}

	public void setIBRegNo(String argument) {
		this.ibRegNo = argument;
	}
	public String getIBGAN() {
		return ibGan;
	}

	public void setIBGAN(String argument) {
		this.ibGan = argument;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.ibClaimRef) && Utils.isStringEmpty(this.ibClaimType) && 
		        Utils.isStringEmpty(this.ibRegNo) && Utils.isStringEmpty(this.ibGan));       
	}

}
