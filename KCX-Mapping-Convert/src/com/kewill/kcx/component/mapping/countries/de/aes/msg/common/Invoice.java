package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 30.10.2009<br>
 * Description  : created with 3 fields, some more fields was defined in xsd: CT_Invoice 					
 * 				: and have to be defined here if needed.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Invoice extends KCXMessage {
   
    private String invoicePrice;	//n(15,2)	
    private String currency;
    private String invoiceNumber;
 // new for V20 - begin:
    private IncoTerms incoTerms;
    private Party consignee;
	private TIN consigneeTIN;	
	private Party consignor;
	private TIN consignorTIN;
    private List<InvoiceItem> invoiceItemList;  
    private String rate;     //for UIDS
    private String date;   //for UIDS   
 // new for V20 end
    
    
 
  	private enum EInvoice {		
  		InvoiceNumber,      Number,
		InvoicePrice,		Amount,  //EI20120913 	Amount added
		Currency,           //ExchangeRate.Currency
		 // new for V20 - begin:
							Date,
							ExchangeRate,
		IncoTerms,			Incoterms,
		ConsigneeTIN,			
		Consignee,						//same
		ConsignorTIN,
		Consignor,	
		InvoiceItem;
		// new for V20 end
  	} 

    public Invoice() {
    	super();    		
    }
    
    public Invoice(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EInvoice) tag) {
  				case IncoTerms:
  				case Incoterms:
  					incoTerms = new IncoTerms(getScanner());
  					incoTerms.parse(tag.name());
  					break;	
  				case Consignee:
					consignee = new Party(getScanner());
					consignee.parse(tag.name());
					break;					
				case ConsigneeTIN:						
					consigneeTIN = new TIN(getScanner());
					consigneeTIN.parse(tag.name());
					break;
				case Consignor:
					consignor = new Party(getScanner());
					consignor.parse(tag.name());
					break;						
				case ConsignorTIN:						
					consignorTIN = new TIN(getScanner());
					consignorTIN.parse(tag.name());
					break;
				case InvoiceItem:
					InvoiceItem item = new InvoiceItem(getScanner());
					item.parse(tag.name());
					addInvoiceItemList(item);
				case ExchangeRate:
					ExchangeRate exRate = new ExchangeRate(getScanner());
					exRate.parse(tag.name());
					if (exRate != null) {
						currency = exRate.getCurrency();
						rate = exRate.getRate();
					}
					break;	
  			default:
  					return;
  			}
  		} else {
  			switch ((EInvoice) tag) {  			   
  				case InvoiceNumber:
  				case Number:
  					setInvoiceNumber(value);
  					break;  					
  				case InvoicePrice:
  				case Amount:
  					setInvoicePrice(value);
  					break;  					  					
  				case Currency:
  					setCurrency(value);
  					break;
  				case Date:
  					setDate(value);
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
  			return EInvoice.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String argument) {
		this.currency = argument;
	}
	
	public void setInvoicePrice(String argument) {
		this.invoicePrice = argument;
	}		
	
	public String getInvoicePrice() {
		return this.invoicePrice;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
  
	public IncoTerms getIncoTerms() {
	    return incoTerms;
	}
	public void setIncoTerms(IncoTerms argument) {
		this.incoTerms = argument;
	}	
	
	public Party getConsignee() {
		if (consigneeTIN != null) {
			if (consignee == null) {
				consignee = new Party();
			}
			consignee.setPartyTIN(consigneeTIN);
		}
		return consignee;
	}
	public void setConsignee(Party argument) {
		this.consignee = argument;
	}	
	
	public Party getConsignor() {
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new Party();
			}
			consignor.setPartyTIN(consignorTIN);
		}
		return consignor;
	}
	public void setConsignor(Party argument) {
		this.consignor = argument;
	}	
	
	public List<InvoiceItem> getInvoiceItemList() {		
		return invoiceItemList;
	}
	public void setInvoiceItemList(List<InvoiceItem> argument) {
		this.invoiceItemList = argument;
	}	
	public void addInvoiceItemList(InvoiceItem item) {
		if (item == null) {
			return;
		}
		if (item.isEmpty()) {
			return;
		}
		if (invoiceItemList == null) {
			invoiceItemList = new Vector <InvoiceItem>();
		}
		invoiceItemList.add(item);
	}
	
	public String getRate() {
		return rate;
	}
	public void setRate(String argument) {
		this.rate = argument;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String argument) {
		this.date = argument;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(invoiceNumber) && Utils.isStringEmpty(invoicePrice) && 
				Utils.isStringEmpty(currency) && Utils.isStringEmpty(rate) && 				
		        incoTerms == null && consignee != null && consigneeTIN == null && 
		        consignor != null && consignorTIN == null && invoiceItemList == null);
	}   
}
