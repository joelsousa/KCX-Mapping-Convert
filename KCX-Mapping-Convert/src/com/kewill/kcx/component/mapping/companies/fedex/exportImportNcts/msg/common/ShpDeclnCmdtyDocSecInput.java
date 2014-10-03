package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Import.<br>
 * Created		: 29.10.2013<br>
 * Description	: Common class for MsgDeclnInput: List of Documents
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ShpDeclnCmdtyDocSecInput extends KCXMessage {

	private ArrayList<HdrDoc> itemShpDeclnComdtyDocList;	
	
	public ShpDeclnCmdtyDocSecInput() {
      	super();
	}

	public ShpDeclnCmdtyDocSecInput(XMLEventReader parser) {
		super(parser);
	}      

	public ShpDeclnCmdtyDocSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EShpDeclnCmdtyDocSecInput {
		ITEM_SHP_DECLN_COMDTY_DOC, //GoodsItem/Document		
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EShpDeclnCmdtyDocSecInput) tag) {		
		case ITEM_SHP_DECLN_COMDTY_DOC:
			HdrDoc hdrDoc = new HdrDoc(getScanner());  	
			hdrDoc.parse(tag.name());
			addItemShpDeclnComdtyDocList(hdrDoc);
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

	public ArrayList<HdrDoc> getItemShpDeclnComdtyDocList() {
		return itemShpDeclnComdtyDocList;
	}
	public void setItemShpDeclnComdtyDocList(
			ArrayList<HdrDoc> itemShpDeclnComdtyDocList) {
		this.itemShpDeclnComdtyDocList = itemShpDeclnComdtyDocList;
	}
	public void addItemShpDeclnComdtyDocList(HdrDoc item) {
		if (itemShpDeclnComdtyDocList == null) {
			itemShpDeclnComdtyDocList = new ArrayList<HdrDoc>();
		}
		this.itemShpDeclnComdtyDocList.add(item);
	}
	
	public boolean isEmpty() {
		return itemShpDeclnComdtyDocList == null || 
			itemShpDeclnComdtyDocList.isEmpty(); 
	}
}
