package com.kewill.kcx.component.mapping.countries.de.Import.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: Import<br>.
 * Created		: 12.09.2011<br>
 * Description	: KIDS MsgImportDeclarationDecision.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgImportDeclarationDecision extends KCXMessage { 
	
	private String	msgName = "NCTSDeclarationDecision";
	private String	referenceNumber;
	private String	temporaryMRN;
	private String	registrationNumber;	
	private String	customsClerkName;	
	private String 	orderNumber;	
	private List<GoodsItemDeclarationDecision>	goodsItemList = new ArrayList<GoodsItemDeclarationDecision>();
	
	public MsgImportDeclarationDecision() {
		super();
	}
	public MsgImportDeclarationDecision(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EImportDeclarationDecision {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,	
		TemporaryMRN,
		RegistrationNumber,						
		CustomsClerkName,						
	    OrderNumber,									
		GoodsItem;						//same
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EImportDeclarationDecision) tag) {
			
			case GoodsItem:
				GoodsItemDeclarationDecision goodsItem = new GoodsItemDeclarationDecision(getScanner(), msgName);
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
				
			default:
				return;
			}
		} else {
			switch ((EImportDeclarationDecision) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			case TemporaryMRN:			
				setTemporaryMRN(value);
				break;
			case RegistrationNumber:			
				setRegistrationNumber(value);
				break;			
			case CustomsClerkName:
				setCustomsClerkName(value);
				break;
			case OrderNumber:
				setOrderNumber(value);
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
			return EImportDeclarationDecision.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String value) {
		this.referenceNumber = value;
	}
	public String getTemporaryMRN() {
		return temporaryMRN;
	}
	public void setTemporaryMRN(String value) {
		this.temporaryMRN = value;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String value) {
		this.registrationNumber = value;
	}
	
	public String getCustomsClerkName() {
		return customsClerkName;
	}
	public void setCustomsClerkName(String customsClerkName) {
		this.customsClerkName = customsClerkName;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public void addGoodsItemList(GoodsItemDeclarationDecision argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemDeclarationDecision>();
	   	}
	   	this.goodsItemList.add(argument);
	}
	public List<GoodsItemDeclarationDecision> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(List<GoodsItemDeclarationDecision> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}	

}
