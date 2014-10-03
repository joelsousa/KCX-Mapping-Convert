package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TechnicalName extends KCXMessage {
		
	private TextAAD tlq;
	private TextAAD teq;
	
	private enum EBLText {	
		TLQ,
		TEQ;			       			
   }	

	public TechnicalName() {
		super();  
	}

	public TechnicalName(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EBLText) tag) {  			
				case TLQ:
  					tlq = new TextAAD(getScanner());  	
  					tlq.parse(tag.name());
  					break; 
				case TEQ:
  					teq = new TextAAD(getScanner());  	
  					teq.parse(tag.name());
  					break;
				default:
  					break;
  			}
  		} else {

  			switch((EBLText) tag) {   			  				
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EBLText.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public TextAAD getTLQ() {
		return tlq;
	}    
	public void setTLQ(TextAAD value) {
		this.tlq = value;
	}
	
	public TextAAD getTEQ() {
		return teq;
	}
    public void setTEQ(TextAAD value) {
		this.teq = value;
	}	
	
}

