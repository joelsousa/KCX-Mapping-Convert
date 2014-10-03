/*
 * Function    : Document.java
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the Document Data
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 16.03.2009
 * Label       : EI20090316
 * Description : Party extended to read UIDS-Tags
 *
 *
 * Changes
 * -----------
 * Author      : krzoska
 * Date        : 12.05.2009
 * Label       : AK20090512
 * Description : equals("U") instead of =="U"
 * 
 * Author      : EI
 * Date        : 14.05.2009
 * Label       : EI20090514
 * Description : DateOfVality -> DateOfValidity
 * 
 * Author      : EI
 * Date        : 15.05.2009
 * Label       : EI20090515
 * Description : typeUids had a wrong get-mehtod
 * 			   : Kids/Uids checked 
 * 
 * Author      : EI
 * Date        : 08.02.2010
 * Label       : EI2010208
 * Description : new member: writeDownQuantity
 * 
 * Author      : EI
 * Date        : 11.08.2010
 * Label       : EI20100811
 * Description : new member: reason
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Document<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the Document Data with all Fields used in  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Document extends KCXMessage {

	private String msgFlag = "";  	//"K" for Kids, "U" for UIDS
    private String qualifier;       //UIDS - Type
    private String typeKids;		//UIDS - Category
    private String typeUids;		//KIDS - Qualifier
    private String category;        //KIDS - Type
    private String reference;
    private String additionalInformation; //UIDS <= Remark
    private String detail;
    private String issueDate;  //UIDS <= DateOfCreation
    private String expirationDate;
    private String value;
    private String reason;                      //EI20100811
    private WriteDownAmount writeDownAmount;
    private WriteDownAmount writeDownQuantity;  //EI20100208
    
    private String   date;  
	private String   office;
    
    private String   present;  
	private String   presentLocation;
    
    private boolean debug   = false;  

	private enum EDocument {
		Qualifier,  			//Type
					Type,
		//Type,    			 Category,   
								Category,
		Reference,  			//same
		AdditionalInformation,  Remark,
		Detail,					//same
		IssueDate,				DateOfCreation,
		ExpirationDate,			DateOfValidity,
		Value,					// - Writeoff.Value
		Reason,                 // -
		WritedownAmount,		WriteOff, Writeoff,
	    Date,
	    Office,
	    Present,
	    PresentLocation,
	    WriteDownQuantity;
	}

	 public Document() {
	      	super();
	 }
	 /*
 	public Document (XMLEventReader parser) {
		super(parser);
		this.parser = parser;		
	}
	*/
	 
	 public Document(XmlMsgScanner scanner) {
			super(scanner);			
	}  
	 
	 public Document(XmlMsgScanner scanner, String msgFlag) {
		super(scanner);
		this.msgFlag = msgFlag;
	}  
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDocument) tag) {
  				case WritedownAmount:
  				case WriteOff:
  				case Writeoff:
  					writeDownAmount = new WriteDownAmount(getScanner(), msgFlag);	
  					writeDownAmount.parse(tag.name());
  					/*EI20090430: if (msgFlag.equals("U")) 
  						this.value = writeDownAmount.getValueU();	  
  						*/					
  					break;
  				case WriteDownQuantity:
  					writeDownQuantity = new WriteDownAmount(getScanner(), msgFlag);
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
  					setTypeKU(value);    					
  					break;  					
  				case Category:
  					setCategory(value);
  					break;    					
  				case Reference:
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

  	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
  	
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getTypeKids() {
		return typeKids;
	}
	public void setTypeKids(String type) {
		this.typeKids = type;
	}
	public String getTypeUids() {
		//EI20090515: return typeKids;
		return typeUids;
	}
	public void setTypeUids(String type) {
		//EI20090515: this.typeKids = type;
		this.typeUids = type;
	}	
	
	public void setTypeKU(String argument) {
		if (msgFlag == null) {
			msgFlag = "";
		}
		//AK20090512
		if (msgFlag.equals("K"))  {
			setTypeKids(argument); 	
		} else if (msgFlag.equals("U")) {
			setTypeUids(argument);
		}
	}
	
	public void setCategory(String argument) {
		category = argument;
	}
	
	public String getCategory() {
		return category;
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

	public WriteDownAmount getWriteDownAmount() {
		return writeDownAmount;
	}
	public void setWriteDownAmount(WriteDownAmount argument) {
		this.writeDownAmount = argument;
	}
	public WriteDownAmount getWriteDownQuantity() {
		return writeDownQuantity;
	}
	public void setWriteDownQuantity(WriteDownAmount argument) {
		this.writeDownQuantity = argument;
	}
	public String getMsgFlag() {   //EI20090817
		return msgFlag;
	}
	public void setMsgFlag(String argument) {   //EI20090817
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
		return (Utils.isStringEmpty(this.qualifier) &&  
		        Utils.isStringEmpty(this.category) && Utils.isStringEmpty(this.reference));       
	}
}
