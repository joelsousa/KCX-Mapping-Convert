package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: ---TODO--- changes 20120423
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Sea extends KCXMessage {

	 private String bookingNumber;       
	 private String f;
	 private String noBL;
	 private String noOfCopy2;
	
	 public Sea() {
		 super();  
	 }

	 public Sea(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EDocumentFormat {				
		 SONo,					
		 MoveType,
		 NoBL,
		 NoOfCopy2;							 			        		
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EDocumentFormat) tag) {
				default: return;			
			}
		} else {			
			switch ((EDocumentFormat) tag) {
			
				case SONo:
					setBookingNumber(value);
					break;
				case MoveType:
					setMoveType(value);
					break;	
				case NoBL:
					setNoBL(value);
					break;	
				case NoOfCopy2:
					setNoOfCopy2(value);
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
			return EDocumentFormat.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

   public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String argument) {
		this.bookingNumber = argument;
	}					
		
	public String getMoveType() {
		return f;
	}
	public void setMoveType(String argument) {
		this.f = argument;
	}

	public String getNoBL() {
		return noBL;
	}

	public void setNoBL(String noBL) {
		this.noBL = Utils.checkNull(noBL);
	}

	public String getNoOfCopy2() {
		return noOfCopy2;
	}

	public void setNoOfCopy2(String noOfCopy2) {
		this.noOfCopy2 = Utils.checkNull(noOfCopy2);
	}		
	
}
