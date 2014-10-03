/*
 * Function    : Document(KIDS)  == UIDS: (simple) Tags 
 * Titel       :
 * Date        : 18.06.2010
 * Author      : iwaniuk
 * Description : Contains the Document Data for ICS
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: IcsDocument<br>
 * Erstellt		: 18.06.2010<br>
 * Beschreibung	: Contains the Document Data for ICS with all Fields used in  KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class IcsDocument extends KCXMessage {

	private String type;
    private String reference;
    private String referenceLng;
    private String issueDate; 
    
    private EFormat issueDateFormat;      //EI20121024

	private enum EDocu {
		Type,				//same
		Reference,			//same
		ReferenceLng,		//----
		IssueDate,			DateOfCreation,
		Number,   //BDP EI20130418
    }
	
	public IcsDocument() {
		super();  
	}

    public IcsDocument(XmlMsgScanner scanner) {
  		super(scanner);
  	}      
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDocu) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EDocu) tag) {
  				case Type:
  					setType(value);
  					break;
  				case Reference:
  				case Number:          //BDP EI20130418
  	  				setReference(value);
  	  				break;  					
  				case ReferenceLng:
  					setReferenceLng(value);
  					break;  					
  				case IssueDate:
  				case DateOfCreation:
  					setIssueDate(value);
  					if (tag == EDocu.IssueDate) {
   					 setIssueDateFormat(Utils.getKidsDateAndTimeFormat(value));
   				 } else {    				
   					setIssueDateFormat(Utils.getUidsDateAndTimeFormat(value)); 
   				 }
  					break;
  				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EDocu.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getType() {
		return type;
	}
	public void setType(String argument) {
		type = argument;
	}

	public String getReference() {
		return reference;
	}
	public void setReference(String argument) {
		this.reference = argument;
	}
	
	public String getReferenceLng() {
		return referenceLng;
	}
	public void setReferenceLng(String argument) {
		this.referenceLng = argument;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String argument) {
		issueDate = argument;
	}
	
	public EFormat getIssueDateFormat() {
		return issueDateFormat;
	}
	public void setIssueDateFormat(EFormat argument) {
		issueDateFormat = argument;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.issueDate) && 
		        Utils.isStringEmpty(this.reference) && 
		        Utils.isStringEmpty(this.referenceLng) && 
		        Utils.isStringEmpty(this.type));
	}    	
}
