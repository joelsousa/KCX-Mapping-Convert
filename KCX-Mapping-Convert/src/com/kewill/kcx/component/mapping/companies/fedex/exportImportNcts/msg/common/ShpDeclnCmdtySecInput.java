package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Import.<br>
 * Created		: 29.10.2013<br>
 * Description	: Common class for MsgDeclnInput
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ShpDeclnCmdtySecInput extends KCXMessage {

	private ArrayList <ItemShpDeclnComdty>itemShpDeclnComdtyList;
	//Consignor == CUSTOMER_TYP_CD = CZ
	//Consignee == CUSTOMER_TYP_CD = CN
	//Declarant == CUSTOMER_TYP_CD = CD
	
	public ShpDeclnCmdtySecInput() {
      	super();
	}

	public ShpDeclnCmdtySecInput(XMLEventReader parser) {
		super(parser);
	}      

	public ShpDeclnCmdtySecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EShpDeclnCmdtySecInput {
		ITEM_SHP_DECLN_COMDTY;		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EShpDeclnCmdtySecInput) tag) {
			case ITEM_SHP_DECLN_COMDTY:  
				ItemShpDeclnComdty item = new ItemShpDeclnComdty(getScanner());  	
				item.parse(tag.name());
				addItemShpDeclnComdtyList(item);
				break;
		default:
				return;
		}
	  } else {
		switch ((EShpDeclnCmdtySecInput) tag) {		
		
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
			return EShpDeclnCmdtySecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public ArrayList<ItemShpDeclnComdty> getItemShpDeclnComdtyList() {
		return itemShpDeclnComdtyList;
	}

	public void setItemShpDeclnComdtyList(ArrayList<ItemShpDeclnComdty> list) {
		this.itemShpDeclnComdtyList = list;
	}

	public void addItemShpDeclnComdtyList(ItemShpDeclnComdty item) {
		if (itemShpDeclnComdtyList == null) {
			itemShpDeclnComdtyList = new ArrayList<ItemShpDeclnComdty>();
		}
		this.itemShpDeclnComdtyList.add(item);
	}
	
	public boolean isEmpty() {
		return 	(itemShpDeclnComdtyList == null || itemShpDeclnComdtyList.isEmpty());
	}
}


