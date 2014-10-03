package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.CustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 27.06.2013<br>
 * Description	: Contains the GoodsItem Data with all Fields used in KIDS Manifest.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class ItemProcessingResults extends KCXMessage {
	private String 		msgName;
	private String 		itemNumber;
	private String 		registrationNumber;
	private TIN 		custodianTIN;	
	private CustomsResponse customsResponse;	
	private ReferencedSpecification 	referencedSpecification;	
	private Notification notification;   //EI20130703
	
	private enum EGoodsItemS {
		//KIDS							//UIDS
		ItemNumber,
		RegistrationNumber,
		CustodianTIN,
		CustomsResponse,		
		ReferencedSpecification,	
		Notification,
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

			case CustodianTIN:
  				custodianTIN = new TIN(getScanner());
  				custodianTIN.parse(tag.name());
				break;  				

			case Notification:
				notification = new Notification(getScanner());
				notification.parse(tag.name());								
				break;
				
  			default:
  					return;
  			}
  		} else {
  			switch ((EGoodsItemS) tag) {
  			
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;
  				
  				case RegistrationNumber:
  					setRegistrationNumber(value);
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
		return (Utils.isStringEmpty(itemNumber) && custodianTIN == null &&
				customsResponse == null &&
				referencedSpecification == null);
	}

	public TIN getCustodianTIN() {
		return custodianTIN;
	}

	public void setCustodianTIN(TIN custodianTIN) {
		this.custodianTIN = custodianTIN;
	}
	
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification noti) {
		this.notification = noti;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = Utils.checkNull(registrationNumber);
	}
	
	
}
