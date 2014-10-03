package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Created		: 30.04.2010<br>
 * Description  : Contains the Member for save tags from the UIDS message.   
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class UpdateEaad extends KCXMessage {

	private String sequenceNumber;
	private String aadReferenceCode;
	private String journeyTime;
	private String changedTransportArrangement;
	private String transportModeCode;
	private String invoiceNumber;
	private String invoiceDate;
	private Text complementaryInformation;
	
	
	private enum EUpdateEaad {
		//KIDS:					UIDS:
								SequenceNumber,
								AadReferenceCode,         
								JourneyTime,    
								ChangedTransportArrangement,                   
								TransportModeCode,		
								InvoiceNumber,
								InvoiceDate,
								ComplementaryInformation,
	}
	
	public UpdateEaad() {
  		super();
  	}
	 
	public UpdateEaad(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EUpdateEaad) tag) {
			default:
				return;
			}
	    } else {
	    	switch ((EUpdateEaad) tag) {
	    	case SequenceNumber:
	    		 setSequenceNumber(value);
				 break;
			case AadReferenceCode:			
				 setAadReferenceCode(value);
				 break;	
			case JourneyTime:
				 setJourneyTime(value);
				 break;						 
			case ChangedTransportArrangement:
				 setChangedTransportArrangement(value);
				 break;				 
			case TransportModeCode:
				 setTransportModeCode(value);
				 break;					 										 	
			case InvoiceNumber:
				 setInvoiceNumber(value);
				 break;					 
			case InvoiceDate:
				 setInvoiceDate(value);
				 break;	
			case ComplementaryInformation:
				 //setComplementaryInformation(value);
				complementaryInformation = new Text(value, attr);
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
  			return EUpdateEaad.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}	

	public String getJourneyTime() {
		return this.journeyTime;
	}
	public void setJourneyTime(String argument) {
		this.journeyTime = argument;
	}

	public String getChangedTransportArrangement() {
		return this.changedTransportArrangement;
	}
	public void setChangedTransportArrangement(String argument) {
		this.changedTransportArrangement = argument;
	}	

	public String getTransportModeCode() {
		return this.transportModeCode;
	}
	public void setTransportModeCode(String argument) {
		this.transportModeCode = argument;
	}	

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public void setInvoiceNumber(String argument) {
		this.invoiceNumber = argument;
	}		

	public String getInvoiceDate() {
		return this.invoiceDate;
	}
	public void setInvoiceDate(String argument) {
		this.invoiceDate = argument;
	}	
	
	public Text getComplementaryInformation() {
		return this.complementaryInformation;
	}
	public void setComplementaryInformation(Text argument) {
		this.complementaryInformation = argument;
	}
	
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.sequenceNumber) &&
			Utils.isStringEmpty(this.aadReferenceCode) &&
    		Utils.isStringEmpty(this.journeyTime) &&
    		Utils.isStringEmpty(this.changedTransportArrangement) &&
    		Utils.isStringEmpty(this.transportModeCode) &&
    		Utils.isStringEmpty(this.invoiceNumber)  &&  	
    		Utils.isStringEmpty(this.invoiceDate)) {
			return true;
		} else {
			return false;
		}
	}

}

