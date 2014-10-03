/*
 * Function    : ContactPerson.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / krzoska
 * Description : Contains the Contact Person Data 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 15.05.2009
 * Description : Kids/Uids checked - no changes
 * 
 * Author      : EI
 * Date        : 08.06.2009
 * Description : added: city, date, identity
 *             : changed mapping: clerk==name, clerk!=identity, identity is new
 *             *  -----------
 * Author      : EI
 * Date        :
 * Label       : EI20100617
 * Description : move from ase.common into common, added Initials
 */

package com.kewill.kcx.component.mapping.countries.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ContactPerson<br>
 * Erstellt		: 05.09.2008<br>
 * Beschreibung	: Contains the Contact Person Data with all Fields used in UIDS and  KIDS. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class ContactPerson extends KCXMessage {

	 private String   position;
	 private String   clerk;
	 private String   identity;
	 private String   email;
	 private String   faxNumber;
	 private String   phoneNumber;	 
	 private String   name;
	 private String   city;   //EI090608
	 private String   date;   //EI090608
	 private String   initials;   //EI20100617
	 private String   title;   //EI20100617
	
	 
	 private boolean debug   = false;
	 private XMLEventReader parser	= null;
	
	 private enum EContactPersonTags {
		// Kids-TagNames, 			UIDS-TagNames;  KIDS-ICS
		 Position,					Function,	    //same
		 Identity,                  //same          //same
	     Clerk,						Name,			//same	
		 Email,	EmailAddress,		//same			//same 
		 FaxNumber,					Fax,			//same
		 PhoneNumber,				Phone,			//same
		 City,							
		 Date,
		 											Initials, 
		 											Title;
	}
	 
	public ContactPerson() {
			super();  
	}	
		 
	public ContactPerson(XmlMsgScanner scanner) {
	  		super(scanner);
	 }
		 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
			
				switch ((EContactPersonTags) tag) {
				default:
						return;
				}
			} else {
				
				switch ((EContactPersonTags) tag) {
			
					case Position:					
					case Function:					
						setPosition(value);
						break;
						
					case Clerk:
					case Name:           //EI20090609 
						setClerk(value);	
						setName(value);
						break;
						
					//EI20090609: case Clerk:	
					case Identity:							
						setIdentity(value);	//EI20090609
						break;	
					/*EI20090609:	
					case Name:
						setName(value);
						break;  
					*/	
					case Email:
					case EmailAddress:
						setEmail(value);
						break;	
						
					case FaxNumber:						
					case Fax:
						setFaxNumber(value);
						break;	
						
					case PhoneNumber:						
					case Phone:
						setPhoneNumber(value);
						break;
					
					case City:
						setCity(value);
						break;
						
					case Date:
						setDate(value);
						break;
					case Initials:
						setInitials(value);
						break;
					case Title:
						setTitle(value);
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
				return EContactPersonTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}		
	
		public boolean isDebug() {
			return debug;
		}
		public void setDebug(boolean argument) {
			this.debug = argument;
		}

		public String getPosition() {
			return position;
		}
		public void setPosition(String argument) {
			this.position = argument;
		}
		
		public String getClerk() {
			return clerk;
		}
		public void setClerk(String argument) {
			this.clerk = argument;
		}
		public String getIdentity() {
			return identity;
		}
		public void setIdentity(String argument) {
			this.identity = argument;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String argument) {
			this.email = argument;
		}

		public String getFaxNumber() {
			return faxNumber;
		}
		public void setFaxNumber(String argument) {
			this.faxNumber = argument;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String argument) {
			this.phoneNumber = argument;
		}
				
		public String getName() {
			return name;
		}		
		public void setName(String argument) {
			this.name = argument;
		}
		
		public String getCity() {
			return city;
		}		
		public void setCity(String argument) {
			this.city = argument;
		}
		
		public String getDate() {
			return date;
		}		
		public void setDate(String argument) {
			this.date = argument;
		}	
				
		public String getInitials() {
			return this.initials;
		}		
		public void setInitials(String argument) {
			this.initials = argument;
		}
		public String getTitle() {
			return this.title;
		}		
		public void setTitle(String argument) {
			this.title = argument;
		}		
		
		public boolean isEmpty() {
			return (Utils.isStringEmpty(this.city) && Utils.isStringEmpty(this.clerk) && 
			        Utils.isStringEmpty(this.date) && Utils.isStringEmpty(this.email) && 
			        Utils.isStringEmpty(this.faxNumber) && Utils.isStringEmpty(this.identity) && 
			        Utils.isStringEmpty(this.initials) && Utils.isStringEmpty(this.name) && 
			        Utils.isStringEmpty(this.phoneNumber) && Utils.isStringEmpty(this.position) && 
			        Utils.isStringEmpty(this.title));
		}			
}
