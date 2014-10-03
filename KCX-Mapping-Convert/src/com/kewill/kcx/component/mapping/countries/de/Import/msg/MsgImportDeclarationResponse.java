package com.kewill.kcx.component.mapping.countries.de.Import.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationResponse;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: Import<br>.
 * Created		: 12.09.2011<br>
 * Description	: KIDS MsgImportDeclarationResponse.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgImportDeclarationResponse extends KCXMessage { 
	
	private String	msgName = "NCTSDeclarationResponse";
	private String	referenceNumber;
	private String	temporaryMRN;
	private String	registrationNumber;	
	private String	registrationDate;	
	
	private List<GoodsItemDeclarationResponse>	goodsItemList = new ArrayList<GoodsItemDeclarationResponse>();
	
	public MsgImportDeclarationResponse() {
		super();
	}
	public MsgImportDeclarationResponse(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EImportDeclarationResponse {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,	
		TemporaryMRN,
		RegistrationNumber,						
		RegistrationDate,							    							
		GoodsItem;						//same
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EImportDeclarationResponse) tag) {
			
			case GoodsItem:
				GoodsItemDeclarationResponse goodsItem = new GoodsItemDeclarationResponse(getScanner(), msgName);
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
				
			default:
				return;
			}
		} else {
			switch ((EImportDeclarationResponse) tag) {
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
			case RegistrationDate:
				setRegistrationDate(value);
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
			return EImportDeclarationResponse.valueOf(token);
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
	
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}	
	
	public void addGoodsItemList(GoodsItemDeclarationResponse argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemDeclarationResponse>();
	   	}
	   	this.goodsItemList.add(argument);
	}
	public List<GoodsItemDeclarationResponse> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(List<GoodsItemDeclarationResponse> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}	

}
