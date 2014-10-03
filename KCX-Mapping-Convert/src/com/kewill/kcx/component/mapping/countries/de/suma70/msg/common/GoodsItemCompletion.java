package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.06.2013<br>
 * Description	: GoodsItem Data Of NotificationOfCompletion.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class GoodsItemCompletion extends KCXMessage {

	private String msgName;
	private String itemNumber;
	private ContactPerson contact;
	private TIN custodianTIN;
	private String dateOfCompletion;
	private ArrayList<Packages>	packageList;
	private String referenceNumber;	
	private String registrationNumber;		
	private ReferencedSpecification referencedSpecification;		
	private String 		cancellationCode;
	
	private enum EGoodsItemCompletion {
		//KIDS							//UIDS
		ItemNumber,	
		Contact,
		CustodianTIN,               CustodianTin,
		DateOfCompletion,
		Packages, Package,  		Packaging,
		ReferenceNumber,
		RegistrationNumber,		
		ReferencedSpecification, 
		CancellationCode,
	}
	
	public GoodsItemCompletion() {
		super();  
	}

    public GoodsItemCompletion(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
    public GoodsItemCompletion(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EGoodsItemCompletion) tag) {
  			case Contact:
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());
				break;	
				
  			case Packages:
  			case Packaging: 
  			case Package:
  				Packages packaging = new Packages(getScanner());
  				packaging.parse(tag.name());
  				addPackagesList(packaging);
  				break;
  			
  			case ReferencedSpecification:
				referencedSpecification = new ReferencedSpecification(getScanner());
				referencedSpecification.parse(tag.name());
				break;
				  			
  			case CustodianTIN:
  				custodianTIN = new TIN(getScanner());
  				custodianTIN.parse(tag.name());
  				break;
  			
  			default:
  					return;
  			}
  		} else {
  			switch ((EGoodsItemCompletion) tag) {
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;  		
  					  				
  				case ReferenceNumber:
  					setReferenceNumber(value);
  					break;
  					
  				case RegistrationNumber:                     
  					setRegistrationNumber(value);
  					break;
  					
  				case CustodianTIN:
  				case CustodianTin:
  					setUidsCustodianTin(value);
  					break;  
  					
  				case DateOfCompletion:
  					setDateOfCompletion(value);
  					break;
  				
  				case CancellationCode:
  					setCancellationCode(value);
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
  			return EGoodsItemCompletion.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}
	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public ArrayList<Packages> getPackagesList() {
		return packageList;
	}
	public void setPackagesList(ArrayList<Packages> list) {
		this.packageList = list;
	}
	public void addPackagesList(Packages packaging) {
		if (packageList == null) {
			packageList = new ArrayList<Packages>();
		}
		packageList.add(packaging);
	}

	public ReferencedSpecification getReferencedSpecification() {
		return referencedSpecification;
	}
	public void setReferencedSpecification(ReferencedSpecification referencedSpecification) {
		this.referencedSpecification = referencedSpecification;
	}
	
	public String getDateOfCompletion() {
		return dateOfCompletion;
	}
	public void setDateOfCompletion(String value) {
		this.dateOfCompletion = value;
	}

	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson contact) {
		this.contact = contact;
	}	

	public TIN getCustodianTIN() {
		return custodianTIN;
	}
	public void setCustodianTIN(TIN custodianTIN) {
		this.custodianTIN = custodianTIN;
	}

	private void setUidsCustodianTin(String value) {   
		if (Utils.isStringEmpty(value)) {
			return;
		}
		this.custodianTIN = new TIN();
		this.custodianTIN.setTIN(value);		
	}

	public ArrayList<Packages> getPackageList() {
		return packageList;
	}

	public void setPackageList(ArrayList<Packages> packageList) {
		this.packageList = packageList;
	}
	
	public String getCancellationCode() {
		return cancellationCode;
	}

	public void setCancellationCode(String cancellationCode) {
		this.cancellationCode = cancellationCode;
	}
	
}
