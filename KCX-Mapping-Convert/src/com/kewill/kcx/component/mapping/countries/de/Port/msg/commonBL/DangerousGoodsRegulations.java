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

public class DangerousGoodsRegulations extends KCXMessage {
		
	private DangerousGoodsDetails imd;	
	private DangerousGoodsDetails adr;
	private DangerousGoodsDetails anr;
	private DangerousGoodsDetails rid;
	
	private enum ECarriage {	
		IMD,
		ADR,
		ANR,
		RID;		
   }	

	public DangerousGoodsRegulations() {
		super();  
	}

	public DangerousGoodsRegulations(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case IMD:
  					imd = new DangerousGoodsDetails(getScanner());  	
  					imd.parse(tag.name());
  					break;					
  				case ADR:
  					adr = new DangerousGoodsDetails(getScanner());  	
  					adr.parse(tag.name());
					break; 
  				case ANR:
  					anr = new DangerousGoodsDetails(getScanner());  	
  					anr.parse(tag.name());
					break; 
				case RID:
					rid = new DangerousGoodsDetails(getScanner());  	
  					rid.parse(tag.name());
					break;
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {     			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public DangerousGoodsDetails getIMD() {
		return imd;
	}    
	public void setIMD(DangerousGoodsDetails value) {
		this.imd = value;
	}
		
	public DangerousGoodsDetails getADR() {
		return adr;
	}    
	public void setADR(DangerousGoodsDetails value) {
		this.adr = value;
	}
	
	public DangerousGoodsDetails getANR() {
		return anr;
	}    
	public void setANR(DangerousGoodsDetails value) {
		this.anr = value;
	}
	
	public DangerousGoodsDetails getRID() {
		return rid;
	}    
	public void setRID(DangerousGoodsDetails value) {
		this.rid = value;
	}
}

