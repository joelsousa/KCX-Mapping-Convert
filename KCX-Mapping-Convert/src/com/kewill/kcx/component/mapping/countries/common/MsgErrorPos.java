package com.kewill.kcx.component.mapping.countries.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: KCX<br>
 * Created		: 16.12.2011<br>
 * Description	: Contains the GoodsItemError for MsgError.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgErrorPos extends KCXMessage {
	
	private String 	itemNumber; 	
	private List<Text> informationList = new ArrayList<Text>();		
	private List<Document> documentList = new ArrayList<Document>();			
	private List<PositionError> positionErrorList;
	
	public MsgErrorPos() {
		super();				
	}

	public MsgErrorPos(XmlMsgScanner scanner) {
		super(scanner);		
	}
 
	private enum EMsgErrorPos {
		//KIDS:							UIDS:
		ItemNumber,
		Information,
		Document,		
		PositionError;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EMsgErrorPos) tag) {	
			case Information:
				Text info = new Text(getScanner());	
				info.parse(tag.name());
				addInformationList(info);
				break;
			case Document:
				Document docu = new Document(getScanner());	
				docu.parse(tag.name());
				addDocumentList(docu);
				break;
			case PositionError:
				PositionError positionError = new PositionError(getScanner());	
				positionError.parse(tag.name());
				addPositionErrorList(positionError);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EMsgErrorPos) tag) {
			case ItemNumber:
				 setItemNumber(value);
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
  			return EMsgErrorPos.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public List<Text> getInformationList() {
		return informationList;
	}	
	public void setInformationList(List<Text> list) {
		this.informationList = list;
	}
	public void addInformationList(Text text) {
		if (informationList == null) {
			informationList = new ArrayList<Text>();		
		}
		this.informationList.add(text);
	}
	
	public List<Document> getDocumentList() {
		return documentList;
	}	
	public void setDocumentList(List<Document> list) {
		this.documentList = list;
	}
	public void addDocumentList(Document argument) {
		if (documentList == null) {
			documentList = new ArrayList<Document>();	
		}
		this.documentList.add(argument);
	}
	
	public List<PositionError> getPositionErrorList() {
		return positionErrorList;
	}	
	public void setPositionErrorList(List<PositionError> argument) {
		positionErrorList = argument;
	}
	public void addPositionErrorList(PositionError argument) {
		if (positionErrorList == null) {
			positionErrorList = new ArrayList<PositionError>();	
		}
		this.positionErrorList.add(argument);
	}
}
