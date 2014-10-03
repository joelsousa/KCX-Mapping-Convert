/*
 * Function    : ExportRefundHeader(KIDS) == ExportRestitutionHeader(UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the ExportRefundHeader Data
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 12.03.2009
 * Label       : EI20090312
 * Description : new member for V60 - ATTENTION in Body's with PaymentType,BankNumber,ApplicationType 
 *             : KIDS-Tag PaymentType should be filled with Class-Member paymentType
 *             : KIDS-Tag BankNumber  should be filled with Class-Member bankNumber              
 *             : UIDS-Body Tag ApplicationType should be filled with Class-Member paymentType
 *             : UIDS-Tag PaymentType     should be filled with Class-Member bankNumber
 *
 * Author      : EI
 * Date        : 15.05.2009
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ExportRefundHeader<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the ExportRefundHeader Data with all Fields used in  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class ExportRefundHeader extends KCXMessage {

    private String text             	 = "";
    private String paymentKids 	         = "";  //UIDS - ApplicationType
    private String paymentUids 	         = "";  //KIDS - BankNumber
    private String paymentType           = "";  //EI20120716: immer aus KIDS-Sicht, egal woher die daten gekommen sind!
    private String applicationType       = "";  //KIDS - PaymentType
    private String bankNumber  	         = "";  //UIDS - PaymentType
    private String assignee 	         = "";
    private String guarantee	         = "";
    private String reservationCode       = "";
    private String destinationCountry    = "";
    
    private boolean debug   = false;
    private String msgFlag;  	//"K" for Kids, "U" for UIDS

  	private enum EExportRefundHeader {
  	//  KIDS-Tags:   			UIDS-Tags:       	
		Text,					Remark,		
	//  PaymentType --  ApplicationType <== richtige Zuordnung:  !!! in der Body-Ausgabe berücksichtigen'!!!
	//  BankNumber  --  PaymentType     <== richtige Zuordnung:  !!! in der Body-Ausgabe berücksichtigen'!!!
				PaymentType,   
				BankNumber, 
				ApplicationType, 	           
		Assignee,				AIDACode,
		Guarantee,				AIDAAccount,
		ReservationCode,		RestitutionPending,
		DestinationCountry;
     }
  	
    public ExportRefundHeader() {
    	super();
    } 

    public ExportRefundHeader(XmlMsgScanner scanner, String msgFlag) {
  		super(scanner);  
  		this.msgFlag = msgFlag;  
    }
    
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EExportRefundHeader) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EExportRefundHeader) tag) {
  				case Text:
  					setText(value);
  					break;
  					
  				case PaymentType:
  					setPayment(value);
  					setPaymentType(value);
  					break;
  					
  				case BankNumber:
  					setBankNumber(value);
  					break;
  					
  				case Assignee:
  				case AIDACode:
  					setAssignee(value);
  					break;
  					
  				case Guarantee:
  				case AIDAAccount:
  					setGuarantee(value);
  					break;
  					
  				case ReservationCode:
  				case RestitutionPending:
  					setReservationCode(value);
  					break;
  					
  				case DestinationCountry:
  					setDestinationCountry(value);
  					break;
  					
  				case ApplicationType:
  					setApplicationType(value);
  					setPaymentType(value);
  					break;  					
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}
  	
  	public Enum translate(String token) {
  		try {
  			return EExportRefundHeader.valueOf(token);
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
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPaymentType(String value) {   //EI20120716: immer aus KIDS-Sicht, egal woher die daten gekommen sind!
		this.paymentType = value;
	}
	public String getPaymentType() {  //EI20120716: immer aus KIDS-Sicht, egal woher die daten gekommen sind!		
		return paymentType;
	}
	
	public String getPaymentKids() {
		return paymentKids;
	}
	public String getPaymentUids() {
		return paymentUids;
	}	
	public void setPayment(String value) {
		if (msgFlag.equals("U")) {
			this.paymentUids = value;
			this.bankNumber = value;
		} else if (msgFlag.equals("K")) {
			this.paymentKids = value;	
			this.paymentType = value;	
		}
	}
	public void setPaymentKids(String value) {
		this.paymentKids = value;
	}
	public void setPaymentUids(String value) {
		this.paymentUids = value;
	}


	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	
	public String getApplicationType() {
		return applicationType;
	}
	
	public void setApplicationType(String value) {
		applicationType = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(text) && Utils.isStringEmpty(paymentType) &&
				Utils.isStringEmpty(bankNumber) && Utils.isStringEmpty(assignee) &&
				Utils.isStringEmpty(guarantee) && Utils.isStringEmpty(reservationCode) &&
				Utils.isStringEmpty(destinationCountry));			
	}
	 	           
}
