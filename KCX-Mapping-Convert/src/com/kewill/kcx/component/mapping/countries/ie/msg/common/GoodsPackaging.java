package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 05.06.2014<br>
 * Description	: GoodsPackaging.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class GoodsPackaging extends KCXMessage {
	
	 private String markNumber;   	
	 private String quantity; 
	 private String numberOfPieces; 
	 private String typeCode; 
	 
     public GoodsPackaging() {
  		super();
  	}
     public GoodsPackaging(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum EGoodsPackaging {
		MarkNumberText,
		QuantityQuantity,	Quantity,
		NumberOfPieces,
		TypeCode;
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((EGoodsPackaging) tag) {
 			default:
 					return;
 			}
 		} else {
 			switch ((EGoodsPackaging) tag) {
 				case MarkNumberText:
					setMarkNumberText(value);
					break; 
 				case QuantityQuantity:
 				case Quantity:
					setQuantity(value);
					break; 
 				case NumberOfPieces:
 					setNumberOfPieces(value);
 					break; 
 				case TypeCode:
 					setTypeCode(value);
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
 			return EGoodsPackaging.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}
	
 	public String getQuantity() {
		return quantity;
	} 	
	public void setQuantity(String value) {
		this.quantity = value;
	}
		
	public String getMarkNumberText() {
		return markNumber;
	}
	public void setMarkNumberText(String markNumber) {
		this.markNumber = markNumber;
	}
	
	public String getNumberOfPieces() {
		return numberOfPieces;
	}
	public void setNumberOfPieces(String numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.quantity);  
	}
		
}
