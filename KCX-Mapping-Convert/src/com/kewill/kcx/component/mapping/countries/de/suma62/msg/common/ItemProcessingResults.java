package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 09.02.2013<br>
 * Description	: Contains the GoodsItem Data with all Fields used in KIDS Manifest.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ItemProcessingResults extends KCXMessage {

	private String 		msgName;
	private String 		itemNumber;
	private String 		custodianTIN;	
	private CustomsResponse customsResponse;	
	private ReferencedSpecification 	referencedSpecification;	
	
	
	private enum EGoodsItemS {
		//KIDS							//UIDS
		ItemNumber,
		CustodianTIN,
		CustomsResponse,		
		ReferencedSpecification,				
	}
	
	public ItemProcessingResults() {
		super();  
	}

    public ItemProcessingResults(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
    public ItemProcessingResults(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		this.msgName = msgName;
	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EGoodsItemS) tag) {
			  			
  			case ReferencedSpecification:
					referencedSpecification = new ReferencedSpecification(getScanner());
					referencedSpecification.parse(tag.name());
				break;
  			  			
  			case CustomsResponse:                     
  				customsResponse = new CustomsResponse(getScanner());
  				customsResponse.parse(tag.name());
  				break;
  					
  			default:
  					return;
  			}
  		} else {
  			switch ((EGoodsItemS) tag) {
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;
  				case CustodianTIN:
  					setCustodianTIN(value);
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
  			return EGoodsItemS.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getCustodianTIN() {
		return custodianTIN;
	}
	public void setCustodianTIN(String value) {
		this.custodianTIN = value;
	}

	public CustomsResponse getCustomsResponse() {
		return customsResponse;
	}
	public void setCustomsResponse(CustomsResponse value) {
		this.customsResponse = value;
	}

	public ReferencedSpecification getReferencedSpecification() {
		return referencedSpecification;
	}
	public void setReferencedSpecification(ReferencedSpecification referencedSpecification) {
		this.referencedSpecification = referencedSpecification;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(itemNumber) && Utils.isStringEmpty(custodianTIN) &&
				customsResponse == null &&
				referencedSpecification == null);
	}
}
