package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: WriteOffSumA 
 * Created     	: 31.08.2008
 * Description 	: Contains the Data of WriteOffZL and  WriteOffAVUV 
 * 			   	  KIDS: BondedWarehouseCompletion and InwardProcessingCompletion
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */
public class WriteOff extends KCXMessage {
	private String allowance;	
	private String reference;	
	private List<WriteOffData> writeOffDataList;
	private String totalNumberOfPositions;
	private String checked;

	private XMLEventReader parser = null;
	
	private enum EWriteOffSumA {
		//UIDS						//KIDS
									TotalNumberOfPositions,
									Checked,
		Allowance,				    AuthorizationNumber,
		Reference,					ReferenceNumber,		
		WriteOffData,				CompletionItem;
	}
	
	public WriteOff() {
		super();
	}
	
	public WriteOff(String type) {
		super();		
	}
		 
	public WriteOff(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EWriteOffSumA) tag) {
			case WriteOffData:
			case CompletionItem:
				WriteOffData tempWriteOffData = new WriteOffData(getScanner());
				tempWriteOffData.parse(tag.name());
				addWriteOffDataList(tempWriteOffData);
				break;				
			default:
				return;
			}
		} else {
			switch((EWriteOffSumA) tag) {
			case Allowance:
			case AuthorizationNumber:
				setAllowance(value);
				break;				
			case Reference:
			case ReferenceNumber:
				setReference(value);
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
  			return EWriteOffSumA.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getAllowance() {
		return allowance;
	}
	public void setAllowance(String argument) {
		this.allowance = argument;
	}

	
	public String getReference() {
		return reference;
	}
	public void setReference(String argument) {
		this.reference = argument;
	}	

	public List<WriteOffData> getWriteOffDataList() {
		return writeOffDataList;
	}
	public void addWriteOffDataList(WriteOffData argument) {
		if (writeOffDataList == null) {
			writeOffDataList = new ArrayList<WriteOffData>();
		}
		writeOffDataList.add(argument);
	}
	public void setWriteOffDataList(List<WriteOffData> listWriteOffData) {
		this.writeOffDataList = listWriteOffData;
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
