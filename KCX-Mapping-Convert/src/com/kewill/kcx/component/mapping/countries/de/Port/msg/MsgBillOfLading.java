package com.kewill.kcx.component.mapping.countries.de.Port.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AdditionalReferenceGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Carriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CarriageGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Contact;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CurrencyGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DocumentDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Equipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.FreightAndChargeGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Goods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.MonetaryAmountGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Parties;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PaymentInstructionsGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PlaceAndDateOf;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ReferenceDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TextOnEntireBL;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Totals;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TransportTerm;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: PORT - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	: 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgBillOfLading extends KCXMessage {
		//TODO
	private String portSystem = "";
	private String applicationSenderId = "";
	private String applicationRecipientId = "";
	private String referenceNumber = "";
	private String messageFunction = "";     //9-Original, 5=Replace, 1-Cancellation, ...
	private String testFlag;    //EI20120726
	private Contact senderContact;
	private CurrencyGroup currencyGroup;
	private MonetaryAmountGroup monetaryAmountGroup;
	//private TextOnEntireBLGroup textOnEntireBLGroup;
	private TextOnEntireBL textOnEntireBL;	
	private Totals totals;
	private DocumentDetails documentDetails;
	private PlaceAndDateOf placeAndDateOf;	
    private List<TransportTerm> termsOfTransportList = new ArrayList<TransportTerm>();
    private ReferenceDetails forwardersReference;
    private AdditionalReferenceGroup additionalReferenceGroup;
    private PaymentInstructionsGroup paymentInstructionsGroup;
    private FreightAndChargeGroup freightAndChargeGroup;
    //private CarriageGroup carriageGroup;
    private Carriage carriage;
    private Parties parties;
	private Goods goods;
	private Equipment equipment;

	//private EFormat zeitstempelFormat;
	
	public MsgBillOfLading() {
		super();				
	}
	public MsgBillOfLading(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}

	private enum EDakBL {
		PortSystem,
		ApplicationSenderId,
		ApplicationRecipientId,		
		ReferenceNumber,
		MessageFunction,
		TestFlag,    //EI20120726
		SenderContact,
		CurrencyGroup,
		MonetaryAmountGroup,
		//TextOnEntireBLGroup,
		TextOnEntireBL,
		TotalsOnEntireBL,
		DetailsOnDocument,
		PlaceAndDateOf,
		TermsOfTransport,
		ForwardersReference,
		AdditionalReferenceGroup,
		PaymentInstructionsGroup,
		FreightAndChargeGroup,
		//CarriageGroup,  
		Carriage, 
		Party,
		Goods,
		Equipment;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EDakBL) tag) {
  			
  				case SenderContact:
  					senderContact = new Contact(getScanner());  	
  					senderContact.parse(tag.name());  					
					break; 
  				case CurrencyGroup:
					currencyGroup = new CurrencyGroup(getScanner());  	
					currencyGroup.parse(tag.name());  					
  					break; 
  				case MonetaryAmountGroup:
  					monetaryAmountGroup = new MonetaryAmountGroup(getScanner());  	
  					monetaryAmountGroup.parse(tag.name());  					
  					break;  
  					/*
				case TextOnEntireBLGroup:
					textOnEntireBLGroup = new TextOnEntireBLGroup(getScanner());  	
					textOnEntireBLGroup.parse(tag.name());  					
  					break; 
  					*/
				case TextOnEntireBL:
					textOnEntireBL = new TextOnEntireBL(getScanner());  	
					textOnEntireBL.parse(tag.name());  
					break;
				case TotalsOnEntireBL:
					totals = new Totals(getScanner());  	
					totals.parse(tag.name());  					
  					break;  
				case DetailsOnDocument:
					documentDetails = new DocumentDetails(getScanner());  	
					documentDetails.parse(tag.name());  					
  					break;  				
				case PlaceAndDateOf:
					placeAndDateOf = new PlaceAndDateOf(getScanner());  	
					placeAndDateOf.parse(tag.name());  					
  					break;  
				case TermsOfTransport:
					TransportTerm temp = new TransportTerm(getScanner());  	
  					temp.parse(tag.name());
  					addTermsOfTransportList(temp);
  					break;
				case ForwardersReference:
					forwardersReference = new ReferenceDetails(getScanner());	
					forwardersReference.parse(tag.name());					
  					break;  
				case AdditionalReferenceGroup:
					additionalReferenceGroup = new AdditionalReferenceGroup(getScanner());  	
					additionalReferenceGroup.parse(tag.name()); 
					break;
				case PaymentInstructionsGroup:
					paymentInstructionsGroup = new PaymentInstructionsGroup(getScanner());  	
					paymentInstructionsGroup.parse(tag.name()); 
					break;
				case FreightAndChargeGroup:
					freightAndChargeGroup = new FreightAndChargeGroup(getScanner());  	
					freightAndChargeGroup.parse(tag.name()); 
					break;
					/*
				case CarriageGroup:
					carriageGroup = new CarriageGroup(getScanner());  	
					carriageGroup.parse(tag.name()); 
					break;
					*/
				case Carriage:
					carriage = new Carriage(getScanner());  	
					carriage.parse(tag.name()); 
					break;
				case Party:
					parties = new Parties(getScanner());  	
					parties.parse(tag.name());  					
  					break;  
				case Goods:
					goods = new Goods(getScanner());  	
					goods.parse(tag.name());  					
  					break;  
				case Equipment:
					equipment = new Equipment(getScanner());  	
					equipment.parse(tag.name());  					
  					break;  
				default:
  					break;
  			}
  		} else {

  			switch((EDakBL) tag) { 
  				case PortSystem:
  					setPortSystem(value);
  					break;
  				case ApplicationSenderId:
  					setApplicationSenderId(value);
  					break; 
  				case ApplicationRecipientId:
  					setApplicationRecipientId(value);
  					break; 
  				case ReferenceNumber:
  					setReferenceNumber(value);
  					break;
  				case MessageFunction:
  					setMessageFunction(value);
  					break;
  				case TestFlag:
  					setTestFlag(value);
  					break;
  				default:
  					break;  				
  			}
  		}
	}

	public void stoppElement(Enum tag) {		
		//empty
	}

	public Enum translate(String token) {
 		try {
  			return EDakBL.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getApplicationSenderId() {
		return applicationSenderId;
	}
	public void setApplicationSenderId(String applicationSenderId) {
		this.applicationSenderId = applicationSenderId;
	}
	
	public String getApplicationRecipientId() {
		return applicationRecipientId;
	}
	public void setApplicationRecipientId(String applicationRecipientId) {
		this.applicationRecipientId = applicationRecipientId;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getMessageFunction() {
		return messageFunction;
	}
	public void setMessageFunction(String messageFunction) {
		this.messageFunction = messageFunction;
	}
	
	
	public String getTestFlag() {
		return testFlag;
	}
	public void setTestFlag(String value) {
		this.testFlag = value;
	}
	
	public Contact getSenderContact() {
		return senderContact;
	}
	public void setSenderContact(Contact senderContact) {
		this.senderContact = senderContact;
	}
	
	public CurrencyGroup getCurrencyGroup() {
		return currencyGroup;
	}
	public void setCurrencyGroup(CurrencyGroup currencyGroup) {
		this.currencyGroup = currencyGroup;
	}
	
	public MonetaryAmountGroup getMonetaryAmountGroup() {
		return monetaryAmountGroup;
	}
	public void setMonetaryAmountGroup(MonetaryAmountGroup monetaryAmountGroup) {
		this.monetaryAmountGroup = monetaryAmountGroup;
	}
	/*
	public TextOnEntireBLGroup getTextOnEntireBLGroup() {
		return textOnEntireBLGroup;
	}
	public void setTextOnEntireBLGroup(TextOnEntireBLGroup textOnEntireBLGroup) {
		this.textOnEntireBLGroup = textOnEntireBLGroup;
	}
	*/
	public TextOnEntireBL getTextOnEntireBL() {
		return textOnEntireBL;
	}
	public void setTextOnEntireBL(TextOnEntireBL texte) {
		this.textOnEntireBL = texte;
	}
	
	public Totals getTotals() {
		return totals;
	}
	public void setTotals(Totals totals) {
		this.totals = totals;
	}
	
	public DocumentDetails getDocumentDetails() {
		return documentDetails;
	}
	public void setDocumentDetails(DocumentDetails documentDetails) {
		this.documentDetails = documentDetails;
	}
	
	public PlaceAndDateOf getPlaceAndDateOf() {
		return placeAndDateOf;
	}
	public void setPlaceAndDateOf(PlaceAndDateOf placeAndDateOf) {
		this.placeAndDateOf = placeAndDateOf;
	}
	
	public List<TransportTerm> getTermsOfTransportList() {
		return termsOfTransportList;
	}
	public void setTermsOfTransportList(List<TransportTerm> termsOfTransportList) {
		this.termsOfTransportList = termsOfTransportList;
	}
	public void addTermsOfTransportList(TransportTerm value) {
		if (termsOfTransportList == null) {
			termsOfTransportList = new ArrayList<TransportTerm>();
		}
		this.termsOfTransportList.add(value);
	}
	
	public ReferenceDetails getForwardersReference() {
		return forwardersReference;
	}
	public void setForwardersReference(ReferenceDetails referenceDetails) {
		this.forwardersReference = referenceDetails;
	}
	
	public AdditionalReferenceGroup getAdditionalReferenceGroup() {
		return additionalReferenceGroup;
	}
	public void setAdditionalReferenceGroup(AdditionalReferenceGroup additionalReferenceGroup) {
		this.additionalReferenceGroup = additionalReferenceGroup;
	}
	
	public PaymentInstructionsGroup getPaymentInstructionsGroup() {
		return paymentInstructionsGroup;
	}
	public void setPaymentInstructionsGroup(PaymentInstructionsGroup paymentInstructionsGroup) {
		this.paymentInstructionsGroup = paymentInstructionsGroup;
	}
	
	public FreightAndChargeGroup getFreightAndChargeGroup() {
		return freightAndChargeGroup;
	}
	public void setFreightAndChargeGroup(FreightAndChargeGroup freightAndChargeGroup) {
		this.freightAndChargeGroup = freightAndChargeGroup;
	}
	/*
	public CarriageGroup getCarriageGroup() {
		return carriageGroup;
	}
	public void setCarriageGroup(CarriageGroup carriageGroup) {
		this.carriageGroup = carriageGroup;
	}
	*/
	public Carriage getCarriage() {
		return carriage;
	}
	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}
	
	public Parties getParties() {
		return parties;
	}	
	public void setParties(Parties parties) {
		this.parties = parties;
	}
	
	public Goods getGoods() {
		return goods;
	}	
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
	public String getPortSystem() {
		return portSystem;
	}
	public void setPortSystem(String value) {
		this.portSystem = value;
	}
}
