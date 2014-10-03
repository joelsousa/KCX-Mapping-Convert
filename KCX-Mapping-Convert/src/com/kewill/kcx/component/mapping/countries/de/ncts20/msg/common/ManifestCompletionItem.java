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

public class ManifestCompletionItem extends KCXMessage {
	
	private String positionNumber;
	private String regNumber;
	private String atlasAlignment;
		
	private String numberOfPieces;
	private String awb;
	private TIN custodianTin;	
	private String spoArt;
	private String spo;
		
	private XMLEventReader parser = null;
		
	private enum ECompletionItemV20 {
		//KIDS						//UIDS		
		PositionNumber,				ItemNumber,					
		UCR,						RegNumber,					
		NumberOfPieces,				//same				
		AWB,						AWBNumber,
		CustodianTIN,				CustodianTin ,CustodianBO,			
									CustomerID,	//Kids: CustodianTIN.CustomerId 
		SpoArt,						//same				
		Spo,						 SpO,		
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
	public ManifestCompletionItem() {
		super();
	}	
	
	public ManifestCompletionItem(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public ManifestCompletionItem(XmlMsgScanner scanner) {
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
			case CustodianTIN:
				custodianTin = new TIN(getScanner());
				custodianTin.parse(tag.name());				
				break;				
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
			case NumberOfPieces:
				setNumberOfPieces(value);
				break;	
			case AWBNumber:
				setAWB(value);
				break;	
			case CustodianTIN:
				setCustodianTin(value);
				break;
			case CustodianBO:
				setCustodianBo(value);
				break;
			case CustomerID:
				setCustodianId(value);
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

	
	public String getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber;
	}
	
	public String getNumberOfPieces() {
		return numberOfPieces;
	}
	public void setNumberOfPieces(String numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public String getUCR() {
		return regNumber;
	}
	public void setUCR(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getAWB() {
		return awb;
	}
	public void setAWB(String aWBNumber) {
		this.awb = aWBNumber;
	}

	public TIN getCustodianTIN() {
		return custodianTin;
	}
	public void setCustodianTIN(TIN tin) {
		this.custodianTin = tin;
	}
		
	public void setCustodianTin(String tin) {		
		if (custodianTin == null) {
			custodianTin = new TIN();
		}
		this.custodianTin.setTin(tin);		
	}
	public void setCustodianBo(String bo) {		
		if (custodianTin == null) {
			custodianTin = new TIN();
		}
		this.custodianTin.setBO(bo);		
	}
	public void setCustodianId(String id) {		
		if (custodianTin == null) {
			custodianTin = new TIN();
		}
		this.custodianTin.setCustomerIdentifier(id);		
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

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.numberOfPieces) &&
				Utils.isStringEmpty(this.regNumber) &&
				Utils.isStringEmpty(this.positionNumber));
	}
	
}
