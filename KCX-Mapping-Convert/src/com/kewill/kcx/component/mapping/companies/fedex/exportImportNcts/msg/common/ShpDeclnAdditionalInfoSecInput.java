package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Export.<br>
 * Created		: 16.12.2013<br>
 * Description	: Common class for MsgDeclnInput: List of AdditionalInfo
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ShpDeclnAdditionalInfoSecInput extends KCXMessage {

	private ArrayList<AdditionalInfo> additionalInfoList;	
	
	public ShpDeclnAdditionalInfoSecInput() {
      	super();
	}

	public ShpDeclnAdditionalInfoSecInput(XMLEventReader parser) {
		super(parser);
	}      

	public ShpDeclnAdditionalInfoSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EShpDeclnCmdtyDocSecInput {
		ITEM_SHP_DECLN_ADDITIONAL_INFO, //GoodsItem/AdditionalInfo		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EShpDeclnCmdtyDocSecInput) tag) {		
		case ITEM_SHP_DECLN_ADDITIONAL_INFO:
			AdditionalInfo additionalInfo = new AdditionalInfo(getScanner());  	
			additionalInfo.parse(tag.name());
			addItemShpDeclnAdditionalInfoList(additionalInfo);
			break;
		default:
				return;
		}
	  } else {
		switch ((EShpDeclnCmdtyDocSecInput) tag) {
		
		default:
			return;
		} 
	  }
	}
	
	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	public Enum translate(String token) {
		try {
			return EShpDeclnCmdtyDocSecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public ArrayList<AdditionalInfo> getItemShpDeclnAdditionalInfoList() {
		return additionalInfoList;
	}
	public void setItemShpDeclnAdditionalInfoList(ArrayList<AdditionalInfo> list) {
		this.additionalInfoList = list;
	}
	public void addItemShpDeclnAdditionalInfoList(AdditionalInfo item) {
		if (additionalInfoList == null) {
			additionalInfoList = new ArrayList<AdditionalInfo>();
		}
		this.additionalInfoList.add(item);
	}	
	public boolean isEmpty() {
		return additionalInfoList == null || additionalInfoList.isEmpty(); 
	}
	
}
