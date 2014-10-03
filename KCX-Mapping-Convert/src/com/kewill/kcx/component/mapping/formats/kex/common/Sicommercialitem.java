package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : KEX<br>
 * Created      : 12.12.2012<br>
 * Description	: V03.
 * 
 * @author iwaniuk
 * @version 0.3.00
 */
public class Sicommercialitem extends KCXMessage {
	
	 private String	commercialitemSno;
	
	 public Sicommercialitem() {
		 super();  
	 }

	 public Sicommercialitem(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EShintrastats {	
			// KEX							KIDS			
		 commercialitemSno;					//GoodsItem.ItemNumber                                                    		 	
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EShintrastats) tag) {
				default: return;			
			}
		} else {			
			switch ((EShintrastats) tag) {			
			
				case commercialitemSno:
					setCommercialitemSno(value);
					break;				
				default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
				return EShintrastats.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getCommercialitemSno() {
		return commercialitemSno;
	}

	public void setCommercialitemSno(String value) {
		this.commercialitemSno = Utils.checkNull(value);
	}
	
}
