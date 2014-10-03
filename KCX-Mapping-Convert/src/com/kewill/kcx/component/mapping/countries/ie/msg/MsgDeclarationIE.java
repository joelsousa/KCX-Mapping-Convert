package com.kewill.kcx.component.mapping.countries.ie.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Quantity;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.ie.msg.common.ControlResult;
import com.kewill.kcx.component.mapping.countries.ie.msg.common.CurrencyExchange;
import com.kewill.kcx.component.mapping.countries.ie.msg.common.GoodsShipment;
import com.kewill.kcx.component.mapping.countries.ie.msg.common.Issuing;
import com.kewill.kcx.component.mapping.countries.ie.msg.common.PartyIE;
/**
 * Module       : Export/aes.<br>
 * Created      : 04.06.2014<br>
 * Description	: 
 * 				: 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MsgDeclarationIE extends KCXMessage {	
	private String msgName = "Declaration";
	private String typeCode = "";              
	private String acceptanceDate;
	private String ieMessageSendingDate; 	
	private String ieMessageType;	
	private String ieRiskRouting;
	private String customsReferenceIdentifier;                   
	private String declarationOfficeIdentifier;                         					
	private String specificCircumstanceIndicator;	//Export						
	private Issuing issuing;						//Export
	private String goodsItemQuantity;				//Import
	private String invoiceAmount;                    
	private GoodsShipment goodsShipment;                          
	private Quantity declarationPackaging;
	private CurrencyExchange currencyExchange;
	private PartyIE agent;	
	private String totalGrossMassMeasure;           //Export	
	private Quantity numberOfSeals;					//Export	
	private ControlResult controlResult;			//Export	
		
	public MsgDeclarationIE() {
			super();
	}	
	public MsgDeclarationIE(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	 
	private enum EMsgDeclarationIE {
		//IE-Name							KIDS-Name	
		TypeCode,                     		//AreaCode	
		AcceptanceDate,
		IEMessageSendingDate,
		IEMessageType,
		IERiskRouting,
		CustomsReferenceIdentifier,
		DeclarationOfficeIdentifier,
		SpecificCircumstanceIndicator,      	
		Issuing, //CT Issuing.Place       	
		GoodsItemQuantity,
		InvoiceAmount,
		GoodsShipment,  //CT
		DeclarationPackaging, // CT DeclarationPackaging.QuantityQuantity
		CurrencyExchange,  //CT
		Agent, //CT												
		TotalGrossMassMeasure,		       
		NumberOfSeals,   //CT NumberOfSeals.QuantityQuantity  			
		ControlResult,   //CT										
		
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgDeclarationIE) tag) {					
				case Issuing:
					issuing = new Issuing(getScanner());  	
					issuing.parse(tag.name());
					break;				
				case GoodsShipment:    
					goodsShipment = new GoodsShipment(getScanner());  
					goodsShipment.parse(tag.name());
					break;	
				case DeclarationPackaging:
					declarationPackaging = new Quantity(getScanner());  
					declarationPackaging.parse(tag.name());
					break;
				case CurrencyExchange:	
					currencyExchange = new CurrencyExchange(getScanner());  
					currencyExchange.parse(tag.name());
					break;				
				case Agent:
					agent = new PartyIE(getScanner());  
					agent.parse(tag.name());
					break;
				case NumberOfSeals:
					numberOfSeals = new Quantity(getScanner());  
					numberOfSeals.parse(tag.name());
					break;
				case ControlResult:
					controlResult = new ControlResult(getScanner());  
					controlResult.parse(tag.name());
					break;						
				default:
					return;
			}
		} else {
			switch ((EMsgDeclarationIE) tag) {
					
				case TypeCode:
					setTypeCode(value);
					break;										
				case AcceptanceDate:
					setAcceptanceDate(value);
					break;
				case IEMessageSendingDate:
					setIEMessageSendingDate(value);
					break;									
				case IEMessageType:
					setIEMessageType(value);
					break;
				case IERiskRouting:
					setIERiskRouting(value);
					break;					
				case CustomsReferenceIdentifier:
					setCustomsReferenceIdentifier(value);
					break;
				case DeclarationOfficeIdentifier:					
					setDeclarationOfficeIdentifier(value);
					break;									
				case SpecificCircumstanceIndicator:					
					setSpecificCircumstanceIndicator(value);
					break;										
				case InvoiceAmount:
					setInvoiceAmount(value);
					break;																	
				case TotalGrossMassMeasure:
					setTotalGrossMassMeasure(value);
					break;		
				case GoodsItemQuantity:
					setGoodsItemQuantity(value);
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
				return EMsgDeclarationIE.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	
	//-----------------------------------------------------------
		
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public String getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	
	public String getIEMessageSendingDate() {
		return ieMessageSendingDate;
	}
	public void setIEMessageSendingDate(String ieMessageSendingDate) {
		this.ieMessageSendingDate = ieMessageSendingDate;
	}
	
	public String getIEMessageType() {
		return ieMessageType;
	}
	public void setIEMessageType(String ieMessageType) {
		this.ieMessageType = ieMessageType;
	}
	
	public String getIERiskRouting() {
		return ieRiskRouting;
	}
	public void setIERiskRouting(String value) {
		this.ieRiskRouting = value;
	}
	
	public String getCustomsReferenceIdentifier() {
		return customsReferenceIdentifier;
	}
	public void setCustomsReferenceIdentifier(String customsReferenceIdentifier) {
		this.customsReferenceIdentifier = customsReferenceIdentifier;
	}
	
	public String getDeclarationOfficeIdentifier() {
		return declarationOfficeIdentifier;
	}
	public void setDeclarationOfficeIdentifier(String declarationOfficeIdentifier) {
		this.declarationOfficeIdentifier = declarationOfficeIdentifier;
	}
	
	public String getSpecificCircumstanceIndicator() {
		return specificCircumstanceIndicator;
	}
	public void setSpecificCircumstanceIndicator(String specificCircumstanceIndicator) {
		this.specificCircumstanceIndicator = specificCircumstanceIndicator;
	}
	
	public Issuing getIssuing() {
		return issuing;
	}
	public void setIssuing(Issuing issuing) {
		this.issuing = issuing;
	}
	
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	
	public GoodsShipment getGoodsShipment() {
		return goodsShipment;
	}
	public void setGoodsShipment(GoodsShipment goodsShipment) {
		this.goodsShipment = goodsShipment;
	}
	
	public Quantity getDeclarationPackaging() {
		return declarationPackaging;
	}
	public void setDeclarationPackaging(Quantity declarationPackaging) {
		this.declarationPackaging = declarationPackaging;
	}
	
	public CurrencyExchange getCurrencyExchange() {
		return currencyExchange;
	}
	public void setCurrencyExchange(CurrencyExchange currencyExchange) {
		this.currencyExchange = currencyExchange;
	}
	
	public PartyIE getAgent() {
		return agent;
	}
	public void setAgent(PartyIE agent) {
		this.agent = agent;
	}
	
	public String getTotalGrossMassMeasure() {
		return totalGrossMassMeasure;
	}
	public void setTotalGrossMassMeasure(String totalGrossMassMeasure) {
		this.totalGrossMassMeasure = totalGrossMassMeasure;
	}
	
	public Quantity getNumberOfSeals() {
		return numberOfSeals;
	}
	public void setNumberOfSeals(Quantity numberOfSeals) {
		this.numberOfSeals = numberOfSeals;
	}
	
	public ControlResult getControlResult() {
		return controlResult;
	}
	public void setControlResult(ControlResult controlResult) {
		this.controlResult = controlResult;
	}

	public String getGoodsItemQuantity() {
		return goodsItemQuantity;
	}
	public void  setGoodsItemQuantity(String value) {
		this.goodsItemQuantity = value;
	}
}

