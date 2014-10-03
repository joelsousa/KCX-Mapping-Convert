/*
 * Function    : KIDS: Person+PersonTIN+PersonContactPerson == Person (CT_Trader) (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description : Contains the Party Data
 * 			   : with all Fields used in KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 16.03.2009
 * Label       : EI20090316
 * Description : Party extended to read UIDS-Tags
 * 
 * Author      : EI
 * Date        : 23.04.2009
 * Label       : EI20090423
 * Description : new Member: person
 * 
 * Author      : EI
 * Date        : 18.05.2009
 * Description : Kids/Uids  checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;

/**
 * Module      : Port.<br>
 * Created     : 28.10.2011<br>
 * Description : 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Party extends KCXMessage {

	private boolean debug   = false;
	
	private String person;			         // consignee, consignor usw.
	private String customerIdentifier;       //...knr: spdknr, rdrknr, areknr, fobknr, tlyknr
    private String customsIdentifier;        //...zbnr,   in Zabis70 gelöscht
    private String terminalCustomerNumber;   //...kai: spdkai, rdrkai, arekai
    private String additionalIdentification;
    private String portCode;                 //...cde: rdrcde, arecde, fobcde, tlycde
    private String tin;                      //...tin: spdtin, V20 EORI
    private String bo;    //neu in V20
    private Address address;    
    private ContactPerson contact;      // spd, 
 
	private enum EParty {	
		//KIDS:		
		CustomerIdentifier,
		CustomsIdentifier,
		AdditionalIdentification,
		TerminalCustomerNumber,
		PortCode,
		TIN,	//V20 EORI		
		BO,    //new V20
		Address,	   		      
		ContactPerson;  			
   }	

	public Party() {
		super();  
	}
	public Party(String person) {
		super();  
		this.person = person;
	}	

	public Party(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public Party(XmlMsgScanner scanner, String person) {
  		super(scanner);
  		this.person = person;
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EParty) tag) {
				case Address:
  					address = new Address(getScanner());  	
  					address.parse(tag.name());
  					break;   				
				case ContactPerson:
					contact = new ContactPerson(getScanner());
					contact.parse(tag.name());					
					break;				
				default:
  					return;
  			}
  		} else {

  			switch ((EParty) tag) {   			
  				case CustomerIdentifier:
					setCustomerIdentifier(value);
					break; 		    				
  				case CustomsIdentifier:
  					setCustomsIdentifier(value);
  					break; 
  				case AdditionalIdentification:
  					setAdditionalIdentification(value);
  					break;
  				case TerminalCustomerNumber:  			
  					setTerminalCustomerNumber(value);
  					break;   					
  				case TIN:
  					setTin(value);
  					break;    				
  				case BO:
  					setBo(value);
  					break;  
  				case PortCode:
  					setPortCode(value);
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
  			return EParty.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  
  	public void setCustomerIdentifier(String argument) {
		this.customerIdentifier = argument;
	}
	public String getCustomerIdentifier() {
		return customerIdentifier;
	}
	
	public void setCustomsIdentifier(String argument) {
		this.customsIdentifier = argument;
	}
	public String getCustomsIdentifier() {
		return customsIdentifier;
	}
	
	public void setAdditionalIdentification(String argument) {
		this.additionalIdentification = argument;
	}
	public String getAdditionalIdentification() {
		return additionalIdentification;
	}
	
    public void setTerminalCustomerNumber(String argument) {
		this.terminalCustomerNumber = argument;
	}
	public String getTerminalCustomerNumber() {
		return terminalCustomerNumber;
	}
	
	public String getTin() {
		return tin;
	}
    public void setTin(String argument) {
		this.tin = argument;
	}
    
    public String getBo() {
		return bo;
	}
    public void setBo(String argument) {
		this.bo = argument;
	}
     					
    public String getPortCode() {
		return portCode;
	}
    public void setPortCode(String argument) {
		this.portCode = argument;
	}	   					
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address argument) {
		this.address = argument;
	}
		    
    public ContactPerson getContactPerson() {
		return contact;
	}
    public void setContactPerson(ContactPerson argument) {
		this.contact = argument;
	}    
    
    public String getPerson() {
    	return this.person;
    }
    
	public boolean isEmpty() {
		return (Utils.isStringEmpty(customerIdentifier) && Utils.isStringEmpty(terminalCustomerNumber) && 
				 Utils.isStringEmpty(portCode) && Utils.isStringEmpty(tin) && 
		        (this.address  == null || this.address.isEmpty()) && 
		        (this.contact == null || this.contact.isEmpty()));
	}    
}
