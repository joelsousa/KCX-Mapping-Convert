package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Document Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Traders extends KCXMessage {

    private TIN declarantTIN;
	private Address declarantAddress;
	private TIN representativeTIN;
	private Address representativeAddress;
	private TIN consignorTIN;
	private Address consignorAddress;
	private TIN acquirerTIN;
	private Address acquirerAddress;
	private TIN consigneeTIN;
	private Address consigneeAddress;
    private TIN buyerTIN;
    private Address buyerAddress;                    //EI20130319
	private TIN sellerTIN;
	private Address sellerAddress;
	private TIN customsValueDeclarantTIN;
	private Address customsValueDeclarantAddress;
	private TIN representativeOfCustomsValueDeclarantTIN;
	private Address representativeOfCustomsValueDeclarantAddress;
	private TIN declarantForInvoiceTIN;
	private Address declarantForInvoiceAddress;	
		
	private XMLEventReader parser = null;
	 
	 private enum ETraders {
		 DeclarantTIN,
		 DeclarantAddress,
		 RepresentativeTIN,
		 RepresentativeAddress,
		 ConsignorTIN,
		 ConsignorAddress,
		 AcquirerTIN,
		 AcquirerAddress,
		 ConsigneeTIN,
		 ConsigneeAddress,
		 BuyerTIN,
		 BuyerAddress,
		 SellerTIN,
		 SellerAddress,
		 CustomsValueDeclarantTIN,
		 CustomsValueDeclarantAddress,
		 RepresentativeOfCustomsValueDeclarantTIN,
		 RepresentativeOfCustomsValueDeclarantAddress,
		 DeclarantForInvoiceTIN,
		 DeclarantForInvoiceAddress;
   }
	
	public Traders() {
		super();		  
	}
	 
	public Traders(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public Traders(XmlMsgScanner scanner) {
		super(scanner);
	}
		
	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
	
  	public TIN getDeclarantTIN() {
		return declarantTIN;
	}

	public void setDeclarantTIN(TIN tin) {
		this.declarantTIN = tin;
	}

	public Address getDeclarantAddress() {
		return declarantAddress;
	}

	public void setDeclarantAddress(Address address) {
		this.declarantAddress = address;
	}

	public TIN getRepresentativeTIN() {
		return representativeTIN;
	}

	public void setRepresentativeTIN(TIN tin) {
		this.representativeTIN = tin;
	}

	public Address getRepresentativeAddress() {
		return representativeAddress;
	}

	public void setRepresentativeAddress(Address address) {
		this.representativeAddress = address;
	}

	public TIN getConsignorTIN() {
		return consignorTIN;
	}

	public void setConsignorTIN(TIN tin) {
		this.consignorTIN = tin;
	}

	public Address getConsignorAddress() {
		return consignorAddress;
	}

	public void setConsignorAddress(Address address) {
		this.consignorAddress = address;
	}

	public TIN getAcquirerTIN() {
		return acquirerTIN;
	}

	public void setAcquirerTIN(TIN tin) {
		this.acquirerTIN = tin;
	}

	public Address getAcquirerAddress() {
		return acquirerAddress;
	}

	public void setAcquirerAddress(Address address) {
		this.acquirerAddress = address;
	}

	public TIN getConsigneeTIN() {
		return consigneeTIN;
	}

	public void setConsigneeTIN(TIN tin) {
		this.consigneeTIN = tin;
	}

	public Address getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(Address address) {
		this.consigneeAddress = address;
	}

	public TIN getBuyerTIN() {
		return buyerTIN;
	}	

	public void setBuyerTIN(TIN tin) {
		this.buyerTIN = tin;
	}

	public Address getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(Address address) {
		this.buyerAddress = address;
	}
	
	public TIN getSellerTIN() {
		return sellerTIN;
	}

	public void setSellerTIN(TIN tin) {
		this.sellerTIN = tin;
	}

	public Address getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(Address address) {
		this.sellerAddress = address;
	}

	public TIN getCustomsValueDeclarantTIN() {
		return customsValueDeclarantTIN;
	}

	public void setCustomsValueDeclarantTIN(TIN tin) {
		this.customsValueDeclarantTIN = tin;
	}

	public Address getCustomsValueDeclarantAddress() {
		return customsValueDeclarantAddress;
	}

	public void setCustomsValueDeclarantAddress(Address address) {
		this.customsValueDeclarantAddress = address;
	}

	public TIN getRepresentativeOfCustomsValueDeclarantTIN() {
		return representativeOfCustomsValueDeclarantTIN;
	}

	public void setRepresentativeOfCustomsValueDeclarantTIN(TIN tin) {
		this.representativeOfCustomsValueDeclarantTIN  = tin;
	}

	public Address getRepresentativeOfCustomsValueDeclarantAddress() {
		return representativeOfCustomsValueDeclarantAddress;
	}

	public void setRepresentativeOfCustomsValueDeclarantAddress(Address address) {
		this.representativeOfCustomsValueDeclarantAddress = address;			
	}

	public TIN getDeclarantForInvoiceTIN() {
		return declarantForInvoiceTIN;
	}

	public void setDeclarantForInvoiceTIN(TIN tin) {
		this.declarantForInvoiceTIN = tin;
	}

	public Address getDeclarantForInvoiceAddress() {
		return declarantForInvoiceAddress;
	}

	public void setDeclarantForInvoiceAddress(Address address) {
		this.declarantForInvoiceAddress = address;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETraders) tag) {

			case DeclarantTIN:
				declarantTIN = new TIN(getScanner()); 
				declarantTIN.parse(tag.name()); 			
				break;
			case DeclarantAddress:			
				declarantAddress = new Address(getScanner());
				declarantAddress.parse(tag.name()); 			
				break;
			case RepresentativeTIN:
				representativeTIN = new TIN(getScanner()); 
				representativeTIN.parse(tag.name()); 			
				break;
			case RepresentativeAddress:			
				representativeAddress = new Address(getScanner());
				representativeAddress.parse(tag.name()); 			
				break;
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner()); 
				consignorTIN.parse(tag.name()); 			
				break;
			case ConsignorAddress:			
				consignorAddress = new Address(getScanner());
				consignorAddress.parse(tag.name()); 			
				break;
			case AcquirerTIN:
				acquirerTIN = new TIN(getScanner()); 
				acquirerTIN.parse(tag.name()); 			
				break;	
			case AcquirerAddress:			
				acquirerAddress = new Address(getScanner());
				acquirerAddress.parse(tag.name()); 			
				break;				
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner()); 
				consigneeTIN.parse(tag.name()); 			
				break;	
			case ConsigneeAddress:			
				consigneeAddress = new Address(getScanner());
				consigneeAddress.parse(tag.name()); 			
				break;
			case BuyerTIN:
				buyerTIN = new TIN(getScanner()); 
				buyerTIN.parse(tag.name()); 			
				break;	
			case BuyerAddress:			
				buyerAddress = new Address(getScanner());
				buyerAddress.parse(tag.name()); 			
				break;
			case SellerTIN:
				sellerTIN = new TIN(getScanner()); 
				sellerTIN.parse(tag.name()); 			
				break;	
			case SellerAddress:			
				sellerAddress = new Address(getScanner());
				sellerAddress.parse(tag.name()); 			
				break;				
			case CustomsValueDeclarantTIN:
				customsValueDeclarantTIN = new TIN(getScanner()); 
				customsValueDeclarantTIN .parse(tag.name()); 			
				break;	
			case CustomsValueDeclarantAddress:			
				customsValueDeclarantAddress = new Address(getScanner());
				customsValueDeclarantAddress.parse(tag.name()); 			
				break;				
			case RepresentativeOfCustomsValueDeclarantTIN:
				representativeOfCustomsValueDeclarantTIN = new TIN(getScanner()); 
				representativeOfCustomsValueDeclarantTIN.parse(tag.name()); 			
				break;	
			case RepresentativeOfCustomsValueDeclarantAddress:			
				representativeOfCustomsValueDeclarantAddress = new Address(getScanner());
				representativeOfCustomsValueDeclarantAddress.parse(tag.name()); 			
				break;				
			case DeclarantForInvoiceTIN:
				declarantForInvoiceTIN = new TIN(getScanner()); 
				declarantForInvoiceTIN.parse(tag.name()); 			
				break;	
			case DeclarantForInvoiceAddress:			
				declarantForInvoiceAddress = new Address(getScanner());
				declarantForInvoiceAddress.parse(tag.name()); 			
				break;				
  			default:
  				return;
  			}
  		} else {

  			switch ((ETraders) tag) {
  			default:
					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return ETraders.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
  	public boolean isEmpty() {
		return (declarantTIN == null && declarantAddress == null &&
				representativeTIN == null && representativeAddress == null &&
				consignorTIN == null && consignorAddress == null &&
				consigneeTIN == null && consigneeAddress == null &&
				buyerTIN == null && buyerAddress == null &&
				sellerTIN == null &&
				// and the others ....
				declarantForInvoiceTIN == null && declarantForInvoiceAddress == null); 		      
					   	      
	}	
}
