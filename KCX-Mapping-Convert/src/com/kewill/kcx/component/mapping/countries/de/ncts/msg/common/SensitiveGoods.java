package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: SensitiveGoods 
 * Created     	: 31.08.2008
 * Description 	: Contains the SensitiveGoods Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Paul Pagdanganan 
 * @version 1.0.00
 */
public class SensitiveGoods extends KCXMessage {
	
	 private String type;
	 private String weight;
	 
     private XMLEventReader parser	= null;
	 
     public SensitiveGoods() {
    	 super();
     }
     
	 private enum ESensitiveGoods {
		 Type,					Code,
		 Weight,				Amount, Quantity;
	 }
	 
	 public SensitiveGoods(XMLEventReader parser) {
			super(parser);
			this.setParser(parser);
	 }
	 
	 public SensitiveGoods(XmlMsgScanner scanner) {
	  		super(scanner);
   	 }

	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
			
				switch ((ESensitiveGoods) tag) {
				default:
						return;
				}
			} else {
			
				switch ((ESensitiveGoods) tag) {
			
					case Type:
					case Code:				//EI20090518
						setType(value);
						break;
					
					case Quantity:
					case Weight:
					case Amount:   			 //EI20090518
						setWeight(value);
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
				return ESensitiveGoods.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public XMLEventReader getParser() {
		return parser;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.type) && 
				Utils.isStringEmpty(this.weight);
	}
}
