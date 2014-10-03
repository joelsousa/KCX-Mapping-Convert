package com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: NCTS 
 * Created     	: 27.11.2013
 * Description 	: Contains the Data of WriteOffZL and  WriteOffAVUV 
 * 			   	  KIDS: BondedWarehouseCompletion and InwardProcessingCompletion.
 * 		   	   
 * @author iwaniuk 
 * @version 4.1.00
 */

public class Completion extends KCXMessage {
	private String allowance;	
	private String reference;	
	private List<CompletionItem> completionItemList;
	private String totalNumberOfPositions;
	private String checked;

	private XMLEventReader parser = null;
	
	private enum ECompletion20 {
		//UIDS						//KIDS
									TotalNumberOfPositions,
									Checked,
		Allowance,				    AuthorizationNumber,
		Reference,					ReferenceNumber,		
		WriteOffData,				CompletionItem;
	}
	
	public Completion() {
		super();
	}
	
	public Completion(String type) {
		super();		
	}
		 
	public Completion(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECompletion20) tag) {
			case WriteOffData:
			case CompletionItem:
				CompletionItem item = new CompletionItem(getScanner());
				item.parse(tag.name());
				addCompletionItemList(item);
				break;				
			default:
				return;
			}
		} else {
			switch((ECompletion20) tag) {
			case Allowance:
			case AuthorizationNumber:
				setAuthorizationNumber(value);
				break;				
			case Reference:
			case ReferenceNumber:
				setReferenceNumber(value);
				break;	
			case TotalNumberOfPositions:
				setTotalNumberOfPositions(value);
				break;
			case Checked:
				setChecked(value);
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
  			return ECompletion20.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getAuthorizationNumber() {
		return allowance;
	}
	public void setAuthorizationNumber(String argument) {
		this.allowance = argument;
	}

	
	public String getReferenceNumber() {
		return reference;
	}
	public void setReferenceNumber(String argument) {
		this.reference = argument;
	}	

	public List<CompletionItem> getCompletionItemList() {
		return completionItemList;
	}
	public void addCompletionItemList(CompletionItem argument) {
		if (completionItemList == null) {
			completionItemList = new ArrayList<CompletionItem>();
		}
		completionItemList.add(argument);
	}
	public void setCompletionItemList(List<CompletionItem> listCompletionItem) {
		this.completionItemList = listCompletionItem;
	}

	public void setTotalNumberOfPositions(String argument) {
		totalNumberOfPositions = argument;
	}
	public String getTotalNumberOfPositions() {
		return totalNumberOfPositions;
	}

	public void setChecked(String argument) {
		checked = argument;
	}
	public String getChecked() {
		return checked;
	}
}
