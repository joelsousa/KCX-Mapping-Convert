package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

/*
 * Function    : DocumentU.java
 * Titel       :
 * Date        : 15.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the Document/PreviousDocument/ProducedDocument Data
 * 			   : with all Fields used in UIDS 
 * Parameters  :

 * Changes
 * -----------
 * Author      : E.Iwaniuk
 * Date        : 10.03.2009
 * Label       : EI20090310
 * Description : new Member for V60
 */

import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: DocumentU<br>
 * Erstellt		: 15.09.2008<br>
 * Beschreibung	: Contains the Document/PreviousDocument/ProducedDocument Data with all Fields used in UIDS. 
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class DocumentU extends KCXMessage {

    private String amount;
    private String article;
    private String category;
    private String code;
    private CustomsOffices customsOffices;
    private String dateOfCreation;
    private String dateOfValidity;
    private String date;
    private String item;
    private String issuer;
    private String IssuerType;
    private String Location;
    private String NetMass;
    private String grossMass;
    private String Quantity;
    private String Reference;
    private String Remark;
    private String Type;
    private String Unit;
    private String CountryCodeISO;
    private String detail;     //EI20090310
    private List<WriteDownAmount> writeOffList;  //EI20090310
    
    private boolean debug   = false;
    private XMLEventReader 		parser	= null;

	private enum EDocumentU {
		Amount,
		Article,
		Category,
		Code,
		CustomsOffices,
		DateOfCreation,    //only Previous and Produced
		DateOfValidity,    //only Produced
		Date,		       //only Produced and Document  
		Item,
		Issuer,
        IssuerType,
        Location,
        NetMass,		  //only Previous
        GrossMass,        //only Previous
        Quantity,
        Reference,
        Detail,            //only Produced
        Remark,
        Type,
        Unit,
        CountryCodeISO,
        Writeoff;           //only Produced
     }

      public DocumentU() {
	      	super();
      }

      public DocumentU(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
  	}
 	 
 	 public DocumentU(XmlMsgScanner scanner) {
 	  		super(scanner);
 	 }
  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDocumentU) tag) {
  				case CustomsOffices:
  					customsOffices = new CustomsOffices(getScanner()); 
  					customsOffices.parse(tag.name()); 					
  					break;
  					
 				case Writeoff:
 					WriteDownAmount tmpWrOff = new WriteDownAmount(getScanner()); 
 					tmpWrOff.parse(tag.name()); 	
 					writeOffList.add(tmpWrOff);
  					break;
  					
  			default:
  					return;
  			}
  		} else {

  			switch ((EDocumentU) tag) {
  				case Amount:  					
  					setAmount(value);
  					break;
  					
  				case Article:
  					setArticle(value);
  					break;
  					
  				case Category:
  					setCategory(value);
  					break;
  					
  				case Code:
  					setCode(value);
  					break;
  					
  				case DateOfCreation:
  					setDateOfCreation(value);
  					break; 	
  					
  				case DateOfValidity:
  					setDateOfValidity(value);
  					break; 	
  					
  				case Date:
  					setDate(value);
  					break;  
  					
  				case Item:
  					setItem(value);
  					break;
  					
  				case Issuer:
  					setIssuer(value);
  					break;
  					
  				case IssuerType:
  					setIssuerType(value);
  					break;
  					
  				case Location:
  					setLocation(value);
  					break;
  					
  				case NetMass:
  					setNetMass(value);
  					break;  
  					
  				case GrossMass:
  					setGrossMass(value);
  					break;  
  					
  				case Quantity:
  					setQuantity(value);
  					break;
  					
  				case Reference:
  					setReference(value);
  					break;
  					
  				case Remark:
  					setRemark(value);
  					break;
  					
  				case Type:
  					setType(value);
  					break;
  					
  				case Unit:
  					setUnit(value);
  					break;
  					
  				case CountryCodeISO:
  					setCountryCodeISO(value);
  					break;
  					
  				case Detail:
 					setDetail(value);
  					break;  					
  		  }
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EDocumentU.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public String getDateOfValidity() {
		return dateOfValidity;
	}
	public void setDateOfValidity(String argument) {
		this.dateOfValidity = argument;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String argument) {
		this.date = argument;
	}	
	
	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String grossMass) {
		this.grossMass = grossMass;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssuerType() {
		return IssuerType;
	}
	public void setIssuerType(String issuerType) {
		IssuerType = issuerType;
	}

	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}

	public String getNetMass() {
		return NetMass;
	}
	public void setNetMass(String netMass) {
		NetMass = netMass;
	}

	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getReference() {
		return Reference;
	}
	public void setReference(String reference) {
		Reference = reference;
	}

	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getCountryCodeISO() {
		return CountryCodeISO;
	}
	public void setCountryCodeISO(String countryCodeISO) {
		CountryCodeISO = countryCodeISO;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String argument) {
		detail = argument;
	}
	public CustomsOffices getCustomsOfficesU() {
		return customsOffices;
	}
	public void setCustomsOfficesU(CustomsOffices argument) {
		this.customsOffices = argument;
	}
	
	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public List<WriteDownAmount> getWriteOffList() {
		return writeOffList;
	}	
	public void setWriteOffList(List<WriteDownAmount> list) {
		this.writeOffList = list;
	}		
	public void addWriteOffList(WriteDownAmount argument) {
		this.writeOffList.add(argument);
	}
	
}
