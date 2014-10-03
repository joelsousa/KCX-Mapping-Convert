package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Contact extends KCXMessage {
		
	private String name;
	private List<CommunicationContact> communicationContactList;
	
	private enum EContact {	
		ContactName,
		CommunicationContact;			       		
   }	

	public Contact() {
		super();  
	}

	public Contact(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EContact) tag) {  			
				case CommunicationContact:
				    CommunicationContact communicationContact = new CommunicationContact(getScanner());	
					communicationContact.parse(tag.name());
					addCommunicationContactList(communicationContact);
  					break;   				
				default:
  					break;
  			}
  		} else {

  			switch((EContact) tag) {   			
  				case ContactName:
  					setContactName(value);
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
  			return EContact.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getContactName() {
		return name;
	}    
	public void setContactName(String value) {
		this.name = value;
	}
		
	public List<CommunicationContact> getCommunicationContactList() {
		return communicationContactList;
	}		
    public void setCommunicationContactList(List<CommunicationContact> list) {
		this.communicationContactList = list;
	}
    public void addCommunicationContactList(CommunicationContact argument) {
    	if (communicationContactList == null) {
    		communicationContactList = new ArrayList<CommunicationContact>();
    	}
		this.communicationContactList.add(argument);
	}
    
    public boolean isEmpty() {
		return Utils.isStringEmpty(name) && communicationContactList == null; 
	}
    
}

