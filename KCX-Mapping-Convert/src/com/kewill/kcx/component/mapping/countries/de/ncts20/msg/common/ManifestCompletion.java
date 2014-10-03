package com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: WriteOffSumA 
 * Created     	: 28.11.2013
 * Description 	: Contains the WriteOffSumA Data.
 * 			   	  KIDS: ManifestCompletion
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ManifestCompletion extends KCXMessage {
	private String type;
	private String identification;
	private String reference;
	private String flightNumber;
	private ArrayList<ManifestCompletionItem> writeOffDataList;
	
	private XMLEventReader parser = null;
	
	private enum EWriteOffSumA {
		//KIDS						/UIDS
		Identification,				//same
		ReferenceNumber,      		 Reference,					
		FlightNumber,				//same																		
		CompletionItem, 			WriteOffData,				
	}
	
	public ManifestCompletion() {
		super();
	}
	
	public ManifestCompletion(String type) {
		super();
		this.type = type;
	}

	public ManifestCompletion(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public ManifestCompletion(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EWriteOffSumA) tag) {
			case WriteOffData:
			case CompletionItem:
				ManifestCompletionItem tempWriteOffData = new ManifestCompletionItem(getScanner());
				tempWriteOffData.parse(tag.name());
				addCompletionItemList(tempWriteOffData);
				break;
				
			default:
				return;
			}
		} else {
			switch((EWriteOffSumA) tag) {
			case Identification:
				setIdentification(value);
				break;
				
			case Reference:
			case ReferenceNumber:        //EI20111011
				setReference(value);
				break;
				
			case FlightNumber:
				setFlightNumber(value);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public ArrayList<ManifestCompletionItem> getCompletionItemList() {
		return writeOffDataList;
	}
	public void setCompletionItemList(ArrayList<ManifestCompletionItem> list) {
		this.writeOffDataList = list;
	}
	public void addCompletionItemList(ManifestCompletionItem argument) {
		if (writeOffDataList == null) {
			writeOffDataList = new ArrayList<ManifestCompletionItem>();
		}
		writeOffDataList.add(argument);
	}
	
	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
			
}
