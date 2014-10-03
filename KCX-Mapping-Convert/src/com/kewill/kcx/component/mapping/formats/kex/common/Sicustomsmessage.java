package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : KEX<br>
 * Created      : 15.11.2012<br>
 * Description	: V03.
 * 
 * @author krzoska
 * @version 0.3.00
 */
public class Sicustomsmessage extends KCXMessage {
	
	 private String prodOrganizationCode;
	
	 public Sicustomsmessage() {
		 super();  
	 }

	 public Sicustomsmessage(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum ESicustomsmessage {	
			// KEX							KIDS			
		 prodOrganizationCode;					//GoodsDeclaration/CommodityBoard		 	
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ESicustomsmessage) tag) {
				default: return;			
			}
		} else {			
			switch ((ESicustomsmessage) tag) {			
			
				case prodOrganizationCode:
					setProdOrganizationCode(value);
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
				return ESicustomsmessage.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getProdOrganizationCode() {
		return prodOrganizationCode;
	}

	public void setProdOrganizationCode(String prodOrganizationCode) {
		this.prodOrganizationCode = Utils.checkNull(prodOrganizationCode);
	}

	
	
}
