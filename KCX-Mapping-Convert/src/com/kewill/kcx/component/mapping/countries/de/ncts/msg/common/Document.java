package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: Document 
 * Created     	: 31.08.2008
 * Description 	: Contains the Document Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */
public class Document extends KCXMessage {

	private String msgFlag = "";  	//"K" for Kids, "U" for UIDS
	private String type;			//EE20100902
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
	
	
	private EFormat issueDateFormat;        //EI20110524
	private EFormat expirationDateFormat;   //EI20110524
	private EFormat dateFormat;             //EI20110524
	
	private XMLEventReader parser	= null;
    private boolean debug   = false;  

	private enum EDocument {
		//KIDS					//UIDS			
		Qualifier,
		Type,					//same
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
	    WritedownQuantity,
	    Number,   //BDP EI20130418
	}

	 public Document() {
	      	super();
	 }
	
 	public Document(XMLEventReader parser) {
		super(parser);
		this.parser = parser;		
	}
	 
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
  				case WritedownQuantity:
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
  					//setTypeKU(value);    					
  					setType(value);
  					break;  					
  				case Category:
  					setCategory(value);
  					break;    					
  				case Reference:
  				case Number:          //BDP EI20130418
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
  					setIssueDate(value);
  					setIssueDateFormat(EFormat.KIDS_Date);
  					break; 
  				case DateOfCreation:
  					setIssueDate(value);
  					setIssueDateFormat(EFormat.ST_Date);
  					break;  				
  				case ExpirationDate:
  					setExpirationDate(value);
  					setExpirationDateFormat(EFormat.KIDS_Date);
  					break; 
  				case DateOfValidity:
  					setExpirationDate(value);
  					setExpirationDateFormat(EFormat.ST_Date);
  					break;  					
  				case Value:
  					setValue(value);
  					break;
				case Date:
					setDate(value);
					if (value.indexOf('-') == -1) {
						setDateFormat(EFormat.KIDS_Date);
					} else {
						setDateFormat(EFormat.ST_Date);
					}
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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(this.type) && 
				Utils.isStringEmpty(this.reference) && 
				Utils.isStringEmpty(this.additionalInformation)) {
				return true;
			} else {
				return false;
			}
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public EFormat getIssueDateFormat() {
		return issueDateFormat;
	}
	public void setIssueDateFormat(EFormat format) {
		this.issueDateFormat = format;
	}
	public EFormat getExpirationDateFormat() {
		return expirationDateFormat;
	}
	public void setExpirationDateFormat(EFormat format) {
		this.expirationDateFormat = format;
	}
	public EFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(EFormat format) {
		this.dateFormat = format;
	}
	
}
