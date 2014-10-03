package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: ItemDetails.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ItemDetails extends KCXMessage {
   
    private String lineItemNumber;
    private String lineStatus;
    private String waybillNumber;
    private String customsRegistration;
    private String registrationDate;
    private String itemDescription;    
    private String numberOfPieces;
    private String itemUnit;
    private String weight;
    private String statusCode;
    private String customsStatus;
    private String vub;
    private String cancellationCode;
    private String cancellationReason;
    private String custodyDeadline;
    private PartyDetails custodyDetails; 
    private PartyDetails disposalDetails; 
    private PartyDetails custodyWarehouse; 
    private String actionCode;
    private String actionInformation;
    private ItemNotification itemNotification;
    
       
    private enum EItemDetails {    	  			     	
    	LineItemNumber,
    	LineStatus,
    	WaybillNumber,
    	CustomsRegistration,
    	RegistrationDate,
    	ItemDescription,
    	NumberOfPieces,
    	ItemUnit,
    	Weight,    	
    	StatusCode,
    	CustomsStatus,
    	VuB,
    	CancellationCode,
    	CancellationReason,
    	ActionInformation,
    	ActionCode,
    	CustodyDeadline,
    	CustodyDetails,  
    	DisposalDetails,
    	CustodyWarehouse,
    	ItemNotification,
    }     

    public ItemDetails() {
	      	super();	       
    }
    
    public ItemDetails(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EItemDetails) tag) {	
    			case CustodyDetails:
    				custodyDetails = new PartyDetails(getScanner());
    				custodyDetails.parse(tag.name());
    				break;	
    			
    			case DisposalDetails:
    				disposalDetails = new PartyDetails(getScanner());
    				disposalDetails.parse(tag.name());
    				break;	
    				
    			case CustodyWarehouse:
    				custodyWarehouse = new PartyDetails(getScanner());
    				custodyWarehouse.parse(tag.name());
    				break;	
    				
    			case ItemNotification:
    				itemNotification = new ItemNotification(getScanner());
    				itemNotification.parse(tag.name());
    				break;	
    			
    			default:
    					return;
    			}
    		} else {
    			switch ((EItemDetails) tag) {
    			case LineItemNumber:
    				setLineItemNumber(value);
    				break;
    				
    			case LineStatus:
    				setLineStatus(value);
    				break;
    			
    			case WaybillNumber:
    				setWaybillNumber(value);
    				break;
    				
    			case ItemDescription:
    				setItemDescription(value);
    				break;
    				
    			case RegistrationDate:
    				setRegistrationDate(value);
    				break;
    				
    			case CustomsRegistration:
    				setCustomsRegistration(value);
    				break;
    				
    			case NumberOfPieces:
    				setNumberOfPieces(value);
    				break;
    				
    			case ItemUnit:
    				setItemUnit(value);
    				break;
    			
    			case Weight:
    				setWeight(value);
    				break;
    			
    			case CustomsStatus:
    				setCustomsStatus(value);
    				break;
    			
    			case VuB:
    				setVub(value);
    				break;
    				
    			case StatusCode:
    				setStatusCode(value);
    				break;
    				
    			case CancellationCode:
    				setCancellationCode(value);
    				break;
    				
    			case CancellationReason:
    				setCancellationReason(value);
    				break;
    				
    			case CustodyDeadline:
    				setCustodyDeadline(value);
    				break;
    			
    			case ActionInformation:
    				setActionInformation(value);
    				break;
    				
    			case ActionCode:
    				setActionCode(value);
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
    		return EItemDetails.valueOf(token);
    	} catch (IllegalArgumentException e) {
    		return null;
    	}
    }

	public String getLineItemNumber() {
		return lineItemNumber;
	}

	public void setLineItemNumber(String lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(String numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public PartyDetails getCustodyDetails() {
		return custodyDetails;
	}

	public void setCustodyDetails(PartyDetails custodyDetails) {
		this.custodyDetails = custodyDetails;
	}

	public String getCustomsRegistration() {
		return customsRegistration;
	}

	public void setCustomsRegistration(String customsRegistration) {
		this.customsRegistration = customsRegistration;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCustomsStatus() {
		return customsStatus;
	}

	public void setCustomsStatus(String customsStatus) {
		this.customsStatus = customsStatus;
	}

	public String getVub() {
		return vub;
	}

	public void setVub(String vub) {
		this.vub = vub;
	}

	public String getCancellationCode() {
		return cancellationCode;
	}

	public void setCancellationCode(String cancellationCode) {
		this.cancellationCode = cancellationCode;
	}

	public String getCustodyDeadline() {
		return custodyDeadline;
	}

	public void setCustodyDeadline(String custodyDeadline) {
		this.custodyDeadline = custodyDeadline;
	}

	public PartyDetails getDisposalDetails() {
		return disposalDetails;
	}

	public void setDisposalDetails(PartyDetails disposalDetails) {
		this.disposalDetails = disposalDetails;
	}

	public PartyDetails getCustodyWarehouse() {
		return custodyWarehouse;
	}

	public void setCustodyWarehouse(PartyDetails custodyWarehouse) {
		this.custodyWarehouse = custodyWarehouse;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionInformation() {
		return actionInformation;
	}

	public void setActionInformation(String actionInformation) {
		this.actionInformation = actionInformation;
	}

	public ItemNotification getItemNotification() {
		return itemNotification;
	}

	public void setItemNotification(ItemNotification itemNotification) {
		this.itemNotification = itemNotification;
	}	
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(lineStatus) && Utils.isStringEmpty(waybillNumber) &&
		Utils.isStringEmpty(customsRegistration) && Utils.isStringEmpty(registrationDate) &&
		Utils.isStringEmpty(itemDescription) && Utils.isStringEmpty(numberOfPieces) &&
		Utils.isStringEmpty(weight) && Utils.isStringEmpty(itemUnit) &&		
		Utils.isStringEmpty(statusCode) && Utils.isStringEmpty(customsStatus);
		//TODO
	}
	
}
