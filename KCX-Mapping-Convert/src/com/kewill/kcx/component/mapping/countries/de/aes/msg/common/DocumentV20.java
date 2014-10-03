package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Document<br>
 * Created		: 10.07.2012<br>
 * Description	: Contains the Document Data with all Fields used in  KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class DocumentV20 extends KCXMessage {

	private String msgFlag = "";  	//"K" for Kids, "U" for UIDS
    private String qualifier;       //UIDS - Type
    private String type;		    //UIDS - Category   
    private String reference;
    private String additionalInformation; 
    private String detail;
    private String issueDate;  
    private String expirationDate;
    private String value;
    private String reason;                      
    private WriteDownAmountV20 writeDownAmount;
    private WriteDownAmountV20 writeDownQuantity;    
    private String   date;  
	private String   office;    
    private String   present;  
	private String   presentLocation;
   
	private enum EDocument {
		Qualifier,  			//Type
		Type,					Category, 		
		Reference,   			//same  
		AdditionalInformation,  Remark,
		Detail,					//same
		IssueDate,				DateOfCreation,
		ExpirationDate,			DateOfValidity,
		Value,					// - Writeoff.Value
		Reason,                 // -
		WritedownAmount,		WriteOff, Writeoff,
		WriteDownQuantity,
	    						Date,
	    Office,					//CustomsOffice.???
	    Present,				//same
	    PresentLocation,		//same	   
	    Number,   //BDP EI20130418
	}

	 public DocumentV20() {
		 super();
	 }
	
	 public DocumentV20(XmlMsgScanner scanner) {
		super(scanner);			
	}  
	public DocumentV20(XmlMsgScanner scanner, String msgFlag) {
		super(scanner);
		if (!Utils.isStringEmpty(msgFlag)) {
			this.msgFlag = msgFlag;
		}
	}
	 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDocument) tag) {
  				case WritedownAmount:
  					writeDownAmount = new WriteDownAmountV20(getScanner(), "K");	
  					writeDownAmount.parse(tag.name());  									
  					break;
  				case WriteOff:
  				case Writeoff:
  					writeDownAmount = new WriteDownAmountV20(getScanner(), "U");	
  					writeDownAmount.parse(tag.name());
  					if (writeDownAmount != null) {
  						this.value = writeDownAmount.getDocumentValue();
  					}
  					break;  					
  				case WriteDownQuantity:
  					writeDownQuantity = new WriteDownAmountV20(getScanner(), "K");
  					writeDownQuantity.parse(tag.name());
  					break;  					
  			default:
  					return;
  			}
  		} else {

  			switch ((EDocument) tag) {
  				case Qualifier:
  					setQualifier(value);    					
  					break;  					
  				case Type:  				
  					if (msgFlag.equals("K"))  {
  						setType(value); 	
  					} else if (msgFlag.equals("U")) {
  						setQualifier(value);
  					} 					
  					break;  					
  				case Category:
  					setType(value);
  					break;    					
  				case Reference:
  				case Number:
  					setReference(value);
  					break;  				
  				case AdditionalInformation:
  				case Remark:
  					setAdditionalInformation(value);
  					break;  					
  				case Detail:
  					setDetail(value);
  					break;  				
  				case IssueDate:
  				case DateOfCreation:
  					setIssueDate(value);
  					break;  				
  				case ExpirationDate:
  				case DateOfValidity:
  					setExpirationDate(value);
  					break;  					
  				case Value:
  					setValue(value);
  					break;
				case Date:
					setDate(value);
					break;
				case Office:
					setOffice(value);
					break;					
				case Present:
					setPresent(value);
					break;
				case PresentLocation:
					setPresentLocation(value);
					break;  					
				case Reason:
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
  			return EDocument.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}


	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public WriteDownAmountV20 getWriteDownAmount() {
		return writeDownAmount;
	}
	public void setWriteDownAmount(WriteDownAmountV20 argument) {
		this.writeDownAmount = argument;
	}
	public WriteDownAmountV20 getWriteDownQuantity() {
		return writeDownQuantity;
	}
	public void setWriteDownQuantity(WriteDownAmountV20 argument) {
		this.writeDownQuantity = argument;
	}
	public String getMsgFlag() {   
		return msgFlag;
	}
	public void setMsgFlag(String argument) {   
		msgFlag = argument;
	}

	public String getDate() {
		return date;
	
	}

	public void setDate(String date) {
		this.date = Utils.checkNull(date);
	}

	public String getOffice() {
		return office;
	
	}

	public void setOffice(String office) {
		this.office = Utils.checkNull(office);
	}

	public String getPresent() {
		return present;	
	}
	public void setPresent(String present) {
		this.present = Utils.checkNull(present);
	}

	public String getPresentLocation() {
		return presentLocation;	
	}
	public void setPresentLocation(String presentLocation) {
		this.presentLocation = Utils.checkNull(presentLocation);
	}
	
	public String getReason() {
		return reason;	
	}
	public void setReason(String argument) {
		this.reason = argument;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) &&  Utils.isStringEmpty(qualifier) &&  
		        Utils.isStringEmpty(reference) && Utils.isStringEmpty(additionalInformation) &&		       
		        Utils.isStringEmpty(issueDate) && Utils.isStringEmpty(expirationDate) &&
		        Utils.isStringEmpty(detail) && Utils.isStringEmpty(office) &&
		        Utils.isStringEmpty(value) && Utils.isStringEmpty(reason) &&
		        Utils.isStringEmpty(present) && Utils.isStringEmpty(presentLocation) &&         
		        writeDownAmount == null && writeDownQuantity == null);       
	}
	  
  
}
