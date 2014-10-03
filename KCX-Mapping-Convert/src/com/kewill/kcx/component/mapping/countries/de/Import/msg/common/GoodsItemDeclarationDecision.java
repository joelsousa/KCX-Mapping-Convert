package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the GoodsItemDeclaration Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class GoodsItemDeclarationDecision extends KCXMessage {

	private String 				msgName;
	private String 				itemNumber; 
	private String				acceptanceDate;
	private String 				releaseDate;
	private String 				releaseTime; 
	private String				acceptanceCode;		
	private String				releaseCode;	
	private String				returnCode;	
	private String 				rulingsCode; 
	private String				text;
	
	public GoodsItemDeclarationDecision() {
		super();				
	}

	public GoodsItemDeclarationDecision(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
 
	private enum EGoodsItemDeclaration {
		//KIDS:							UIDS:
		ItemNumber,
		AcceptanceDate,
		ReleaseDate,
		ReleaseTime,
		AcceptanceCode,
		ReleaseCode,
		ReturnCode,
		RulingsCode,
		Text;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EGoodsItemDeclaration) tag) {			
			default:
				return;
			}
	    } else {
	    	switch ((EGoodsItemDeclaration) tag) {
			case ItemNumber:
				 setItemNumber(value);
				 break;											
			case AcceptanceDate:
				 setAcceptanceDate(value);
				 break;			
			case AcceptanceCode:
				 setAcceptanceCode(value);
				 break;
			case ReleaseDate:
				 setReleaseDate(value);
				 break;
			case ReleaseTime:			
				 setReleaseTime(value);
				 break;	
			case ReleaseCode:
				 setReleaseCode(value);
				 break;
			case ReturnCode:
				 setReturnCode(value);
				 break;		
			case RulingsCode:
				setRulingsCode(value);
				break;
			case Text:			
				 setText(value);
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
  			return EGoodsItemDeclaration.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}

	public String getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(String value) {
		this.acceptanceDate = value;
	}

	public String getAcceptanceCode() {
		return acceptanceCode;
	}
	public void setAcceptanceCode(String value) {
		this.acceptanceCode = value;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String value) {
		this.releaseDate = value;
	}
	
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String value) {
		this.releaseTime = value;
	}
	
	public String getReleaseCode() {
		return releaseCode;
	}
	public void setReleaseCode(String value) {
		this.releaseCode = value;
	}
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String value) {
		this.returnCode = value;
	}
	
	public String getRulingsCode() {
		return rulingsCode;
	}
	public void setRulingsCode(String value) {
		this.rulingsCode = value;
	}
	
	
}
