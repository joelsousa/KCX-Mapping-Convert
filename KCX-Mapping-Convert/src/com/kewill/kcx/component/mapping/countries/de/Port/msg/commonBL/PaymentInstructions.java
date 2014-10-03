package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PaymentInstructions extends KCXMessage {
		
	private String chargeCategoryCode;
	private String method;
	private String indicator;
	private List<Reference> paymentReferenceList;
	private Currency currency;
	private List<Place> placeList;
	private List<MonetaryAmount> monetaryAmountList;
	
	private enum EPayment {	
		ChargeCategoryCode,
		TransportChargeMethodOfPaymentCode,
		PrepaidCollectIndicator,
		PaymentReference,
		Currency,
		Place,
		MonetaryAmount;			       		
   }	

	public PaymentInstructions() {
		super();  
	}

	public PaymentInstructions(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPayment) tag) {  			
				case PaymentReference:
					Reference payment = new Reference(getScanner());	
					payment.parse(tag.name());	
					addPaymentReferenceList(payment);
  					break;  
				case Currency:
					currency = new Currency(getScanner());	
					currency.parse(tag.name());			
					break;
				case Place:
					Place place = new Place(getScanner());	
					place.parse(tag.name());	
					addPlaceList(place);
					break;
				case MonetaryAmount:
					MonetaryAmount temp = new MonetaryAmount(getScanner());	
					temp.parse(tag.name());	
					addMonetaryAmountList(temp);
					break;
				default:
  					break;
  			}
  		} else {

  			switch((EPayment) tag) {   			
  				case ChargeCategoryCode:
  					setChargeCategoryCode(value);
  					break;
  				case TransportChargeMethodOfPaymentCode:
  					setTransportChargeMethodOfPaymentCode(value);
  					break;  					
  				case PrepaidCollectIndicator:
  					setPrepaidCollectIndicator(value);
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
  			return EPayment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getChargeCategoryCode() {
		return chargeCategoryCode;
	}    
	public void setChargeCategoryCode(String value) {
		this.chargeCategoryCode = value;
	}
		
	public String getTransportChargeMethodOfPaymentCode() {
		return method;
	}		
    public void setTransportChargeMethodOfPaymentCode(String argument) {
		this.method = argument;
	}
   
    public String getPrepaidCollectIndicator() {
		return indicator;
	}    
	public void setPrepaidCollectIndicator(String value) {
		this.indicator = value;
	}
		
	public List<Reference> getPaymentReferenceList() {
		return paymentReferenceList;
	}
	 public void setPaymentReferenceList(List<Reference> argument) {
			this.paymentReferenceList = argument;
		}
    public void addPaymentReferenceList(Reference argument) {
    	if (paymentReferenceList == null) {
    		paymentReferenceList = new ArrayList<Reference>();
    	}
		this.paymentReferenceList.add(argument);
	}
    
    public Currency getCurrency() {
		return currency;
	}    
	public void setCurrency(Currency value) {
		this.currency = value;
	}
		
	public  List<Place> getPlaceList() {
		return placeList;
	}		
    public void setPlaceList( List<Place> argument) {
		this.placeList = argument;
	}
    public void addPlaceList(Place argument) {
    	if (placeList == null) {
    		placeList = new ArrayList<Place>();
    	}
		this.placeList.add(argument);
	}
    
    public  List<MonetaryAmount> getMonetaryAmountList() {
		return monetaryAmountList;
	}		
    public void setMonetaryAmountList( List<MonetaryAmount> argument) {
		this.monetaryAmountList = argument;
	}
    public void addMonetaryAmountList(MonetaryAmount argument) {
    	if (monetaryAmountList == null) {
    		monetaryAmountList = new ArrayList<MonetaryAmount>();
    	}
		this.monetaryAmountList.add(argument);
	}
}

