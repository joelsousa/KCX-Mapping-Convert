package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DangerousGoods extends KCXMessage {
		
	private DangerousGoodsDetails imd;	
	private DangerousGoodsDetails adr;
	private DangerousGoodsDetails anr;
	private DangerousGoodsDetails rid;
	
	private enum EDangerousGoodsBL {			
		IMD,
		ADR,
		ANR,
		RID;
   }	

	public DangerousGoods() {
		super();  
	}

	public DangerousGoods(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDangerousGoodsBL) tag) {  
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

  			switch((EDangerousGoodsBL) tag) {     			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EDangerousGoodsBL.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
  	public DangerousGoodsDetails getImd() {
		return imd;
	} 
  	public void setImd(DangerousGoodsDetails gg) {
		this.imd = gg;
	}
	
  	public DangerousGoodsDetails getAdr() {
		return adr;
	} 
  	public void setAdr(DangerousGoodsDetails gg) {
		this.adr = gg;
	}
  	
  	public DangerousGoodsDetails getAnr() {
		return anr;
	} 
  	public void setAnr(DangerousGoodsDetails gg) {
		this.anr = gg;
	}
  	
  	public DangerousGoodsDetails getRid() {
		return rid;
	} 
  	public void setRid(DangerousGoodsDetails gg) {
		this.rid = gg;
	}
	public boolean isEmpty() {
		return imd == null && adr == null &&
			   anr == null && rid == null; 
	}
}

