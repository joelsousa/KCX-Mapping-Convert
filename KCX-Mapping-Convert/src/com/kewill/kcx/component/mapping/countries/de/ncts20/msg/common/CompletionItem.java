package com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: NCTS
 * Created     	: 27.11.2013
 * Description 	: Contains the WriteOffData/CompletionItem Data. 			   	  
 * 
 * @author iwaniuk 
 * @version 4.1.00
 */

public class CompletionItem extends KCXMessage {
	
	private String positionNumber;
	private String regNumber;	
	private String atlasCode;               //EI20130821
	private String text;
	private String tarifNumber;
	private String handling;
	private String atlasAlignment;
	
		
	private XMLEventReader parser = null;
		
	private enum ECompletionItemV20 {
		//UIDS						//KIDS		
		ItemNumber,					PositionNumber,		
		RegNumber,					UCR,			
		ATLASCode,					EntryInAtlas,	
		AdditionalText,				Text,           
		TariffCode,					Tarifnumber,    	 	//only BZL
		HandlingCode,				UsualFormOfHandling, 	//only BZL		
		//Value,Unit,UnitQualifier 	WriteDownAmount 		//only BZL
		//HandlingValueUnitQualif	TradeAmount,   		    //only BZL
		
		AtlasAlignment,				//same
	}
	/*
	TsBSU		TsBAV		TsBZL		KIDS:
	beznr  		beznr  		beznr   	ReferencNumber
	posnr  		posnr		posnr		GoodsItem.ItemNumber
	vregnr 		vregnr		vregnr		UCR
	vposnr 		vposnr		vposnr		PositionNumber
				atlas		atlas		EntryInAtlas
							kzuebl		UsualFormOfHandling
							wanr		Tarifnumber
							mgeabg		WriteDownAmount.Value
							meabg		WriteDownAmount.UnitOfMeasurement
							qmeabg		WriteDownAmount.Qualifier
							mgehdl		TradeAmount.Value
							mehdl		TradeAmount.UnitOfMeasurement
							qmehdl		TradeAmount.Qualifier							
	suastk 								NumberOfPieces
	vrwknr 								CustomerID
	verweori							Custodian
	verwid 								Custodian
	spoart 								SpoArt
	sponr  								Spo
				text		text		Text	
	azvagl 		azvagl		azvagl		AtlasAlignment

	
	*/
	public CompletionItem() {
		super();
	}	
	
	public CompletionItem(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public CompletionItem(XmlMsgScanner scanner) {
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
			switch ((ECompletionItemV20) tag) {
						
			default:
				return;
			}
		} else {
			switch((ECompletionItemV20) tag) {
			
			case ItemNumber:
			case PositionNumber:
				setPositionNumber(value);
				break;	
			case RegNumber:
			case UCR:
				setUCR(value);
				break;
			case AtlasAlignment:
				setAtlasAlignment(value);
				break;	
			
			case ATLASCode:					
			case EntryInAtlas:
				setEntryInAtlas(value);
				break;
			case AdditionalText:
			case Text:  
				setText(value);
				break;
			case TariffCode:
			case Tarifnumber:
				setTarifNumber(value);
				break;
			case HandlingCode:				
			case UsualFormOfHandling:
				setUsualFormOfHandling(value);
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
  			return ECompletionItemV20.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getText() {
		return text;
	}
	public void setText(String value) {
		this.text = value;
	}
	
	public String getTarifNumber() {
		return tarifNumber;
	}
	public void setTarifNumber(String value) {
		this.tarifNumber = value;
	}
	
	public String getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber;
	}
		
	public String getUCR() {
		return regNumber;
	}
	public void setUCR(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getAtlasAlignment() {
		return atlasAlignment;
	}
	public void setAtlasAlignment(String value) {
		this.atlasAlignment = value;
	}

	public String getEntryInAtlas() {
		return atlasCode;
	}
	public void setEntryInAtlas(String atlasCode) {
		this.atlasCode = atlasCode;
	}
	
	public String getUsualFormOfHandling() {
		return handling;
	}
	public void setUsualFormOfHandling(String handling) {
		this.handling = handling;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.atlasCode) &&
				Utils.isStringEmpty(this.regNumber) &&
				Utils.isStringEmpty(this.positionNumber));
	}
	
}
