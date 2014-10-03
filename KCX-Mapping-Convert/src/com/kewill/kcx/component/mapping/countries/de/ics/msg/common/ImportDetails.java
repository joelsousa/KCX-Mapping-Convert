package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ImportDetails<br>
 * Created		: 2010.07.20<br>
 * Description	: ImportDetails tag used in ICS.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class ImportDetails extends KCXMessage {
	private String mrn;
	private String itemNumber;
	private String referenceNumber;
	private String rejectionReasonPos;
	
	private boolean debug   = false;
	
	private enum EImportDetails {
		//KIDS						//UIDS
		MRN,					//same
		ItemNumber,				//same
		ReferenceNumber,		LocalReferenceNumber,
		RejectionReasonPos,		Reason;
	}
	
	public ImportDetails() {
		super();
	}
	
	public ImportDetails(XmlMsgScanner scanner) {
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
			switch ((EImportDetails) tag) {
			default:
				return;
			}
		} else {
			switch ((EImportDetails) tag) {
				case MRN:
					setMrn(value);
					break;
				case ItemNumber:
					setItemNumber(value);
					break;
				case ReferenceNumber:
				case LocalReferenceNumber:
					setReferenceNumber(value);
					break;
				case RejectionReasonPos:
				case Reason:
					setRejectionReasonPos(value);
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
  			return EImportDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  	
	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getRejectionReasonPos() {
		return rejectionReasonPos;
	}

	public void setRejectionReasonPos(String rejectionReasonPos) {
		this.rejectionReasonPos = rejectionReasonPos;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.mrn) &&
				Utils.isStringEmpty(this.itemNumber) &&
				Utils.isStringEmpty(this.referenceNumber) &&
				Utils.isStringEmpty(this.rejectionReasonPos));    		
	}

}
