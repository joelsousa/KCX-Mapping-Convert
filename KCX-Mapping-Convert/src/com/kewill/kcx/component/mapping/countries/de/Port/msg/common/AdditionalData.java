package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: BookingData.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class AdditionalData extends KCXMessage {

	 private String directTakeover;        //akz_kzlib
	 private String bookingNumber;         //akz_bunr       
	 private String billOfLadingNumber;   //blnr       
	 private String password;           //spdpwd
	 private String invoiceNote;      //rgwech
	
	 public AdditionalData() {
		 super();  
	 }

	 public AdditionalData(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum EBookingData {	
			// Kids-TagNames, 			FSS						KFF 
		 		DirectTakeover,					
		 		BookingNumber,			//akz_bunr,           BookingNo		
		 		BillOfLadingNumber,     //akz_blnr            ShpNo
		 		Password,		 		
		 		InvoiceNote;		 	
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EBookingData) tag) {
				default: return;			
			}
		} else {			
			switch ((EBookingData) tag) {			
				case DirectTakeover:
					setDirectTakeover(value);
					break;
				case BookingNumber:
					setBookingNumber(value);
					break;
				case BillOfLadingNumber:
					setBillOfLadingNumber(value);
					break;
				case Password:
					setPassword(value);
					break;				
				case InvoiceNote:
					setInvoiceNote(value);
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
			return EBookingData.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getDirectTakeover() {
		return directTakeover;
	}
	public void setDirectTakeover(String argument) {
		this.directTakeover = argument;
	}					
		
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String argument) {
		this.bookingNumber = argument;
	}	
	
	public String getBillOfLadingNumber() {
		return billOfLadingNumber;
	}
	public void setBillOfLadingNumber(String argument) {
		this.billOfLadingNumber = argument;
	}	
		
	public String getPassword() {
		return password;
	}
	public void setPassword(String argument) {
		this.password = argument;
	}					
				
	public String getInvoiceNote() {
		return invoiceNote;
	}
	public void setInvoiceNote(String argument) {
		this.invoiceNote = argument;
	}		
			
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.directTakeover) && Utils.isStringEmpty(this.bookingNumber) && 
		        Utils.isStringEmpty(this.billOfLadingNumber) &&		       
		        Utils.isStringEmpty(this.password) && Utils.isStringEmpty(this.invoiceNote));  
	}
}
