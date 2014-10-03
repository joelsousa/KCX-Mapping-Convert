package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Amount
 * Created		: 02.09.2010
 * Description	: contains the Amount Data (for Guarantee) with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */
public class Amount extends KCXMessage {
	
	private String			amount;
	private String			currency;
	private String			localAmount;
	private String			localCurrency;
	
	private XMLEventReader parser	= null;
	
	public Amount(XMLEventReader parser) {
		super(parser);
		this.setParser(parser);
	}
	public Amount(XmlMsgScanner scanner) {
		super(scanner);
	}
	public Amount() {   //EI20130821
		super();
	}
	
	private enum EAmount {
		//Kids				UIDS
		Amount,				//same
		Currency,			//same
		LocalAmount,		//same
		LocalCurrency;		//same
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
   			switch ((EAmount) tag) {
   			default:
   					return;
   			}
   		} else {

   			switch ((EAmount) tag) {
   			case Amount:
   				setAmount(value);
   				break;
   			case Currency:
   				setCurrency(value);
   				break;
   			case LocalAmount:
   				setLocalAmount(value);
   				break;
   			case LocalCurrency:
   				setLocalCurrency(value);
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
  			return EAmount.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLocalAmount() {
		return localAmount;
	}
	public void setLocalAmount(String localAmount) {
		this.localAmount = localAmount;
	}
	public String getLocalCurrency() {
		return localCurrency;
	}
	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;
	}
	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

}
