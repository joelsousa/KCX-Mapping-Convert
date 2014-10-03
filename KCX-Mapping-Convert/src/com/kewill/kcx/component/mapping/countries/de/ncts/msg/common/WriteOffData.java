package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: WriteOffData
 * Created     	: 31.08.2008
 * Description 	: Contains the WriteOffData Data
 * 			   	  KIDS: CompletionItem
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */
public class WriteOffData extends KCXMessage {
	private String type;
	private String numberOfPieces;
	private String regNumber;
	private String itemNumber;
	private String aWBNumber;
	private String custodian;
	private String customerID;
	private String spoArt;
	private String spo;
	private String atlasAlignment;
	private String atlasCode;               //EI20130821
	
	private XMLEventReader parser = null;
	
	
	private enum EWriteOffData {
		//UIDS						//KIDS
		//							SequentialNumber,
		ItemNumber,					PositionNumber,		
		RegNumber,					UCR,
		NumberOfPieces,				//same
		ATLASCode,					EntryInAtlas,
		AdditionalText,				Text,
		TariffCode,					Tarifnumber, 
		HandlingCode,				UsualFormOfHandling,
		//WriteOffUnitQualifier, 	WriteDownAmount
		//HandlingUnitQualifi...	 TradeAmount,
		AWBNumber,					//???
		Custodian,					//???
		CustomerID,					//???
		SpoArt,						//???
		Spo, SpO,					//???
		AtlasAlignment;				//same
	}
	
	public WriteOffData() {
		super();
	}	
	public WriteOffData(String type) {
		super();
		this.type = type;
	}

	public WriteOffData(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public WriteOffData(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EWriteOffData) tag) {
			default:
				return;
			}
		} else {
			switch((EWriteOffData) tag) {
			case NumberOfPieces:
				setNumberOfPieces(value);
				break;
				
			case RegNumber:
			case UCR:
				setRegNumber(value);
				break;
				
			case ItemNumber:
			case PositionNumber:
				setItemNumber(value);
				break;
				
			case AWBNumber:
				setaWBNumber(value);
				break;
				
			case Custodian:
				setCustodian(value);
				break;
				
			case CustomerID:
				setCustomerID(value);
				break;
				
			case SpoArt:
				setSpoArt(value);
				break;
				
			case Spo:
			case SpO:
				setSpo(value);
				break;
				
			case AtlasAlignment:
				setAtlasAlignment(value);
				break;
			
			case ATLASCode:					
			case EntryInAtlas:
				setAtlasCode(value);
				break;
				
			default:
				break;
			}
		}
		
	}


	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
  			return EWriteOffData.valueOf(token);
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

	public String getNumberOfPieces() {
		return numberOfPieces;
	}
	public void setNumberOfPieces(String numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getaWBNumber() {
		return aWBNumber;
	}
	public void setaWBNumber(String aWBNumber) {
		this.aWBNumber = aWBNumber;
	}

	public String getCustodian() {
		return custodian;
	}
	public void setCustodian(String custodian) {
		this.custodian = custodian;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getSpoArt() {
		return spoArt;
	}
	public void setSpoArt(String spoArt) {
		this.spoArt = spoArt;
	}

	public String getSpo() {
		return spo;
	}
	public void setSpo(String spo) {
		this.spo = spo;
	}

	public String getAtlasAlignment() {
		return atlasAlignment;
	}
	public void setAtlasAlignment(String value) {
		this.atlasAlignment = value;
	}

	public String getAtlasCode() {
		return atlasCode;
	}
	public void setAtlasCode(String atlasCode) {
		this.atlasCode = atlasCode;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.numberOfPieces) &&
				Utils.isStringEmpty(this.regNumber) &&
				Utils.isStringEmpty(this.itemNumber));
	}
	
}
