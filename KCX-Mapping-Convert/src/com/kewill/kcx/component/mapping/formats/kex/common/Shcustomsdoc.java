package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : KEX<br>
 * Created      : 15.12.2011<br>
 * Description	: V03.
 * 
 * @author iwaniuk
 * @version 0.3.00
 */
public class Shcustomsdoc extends KCXMessage {
	
	//v03:
	private String shdocumentUnid;   			
	private String documentCode;          // gbnesdocumentcode;		
	private String documentPart;          // gbnesdocumentref;	
	private String documentQuantity;      //
	private String documentReason;        // gbnesdocumentreason;	
	private String documentRef;  
	private String documentStatus;        // gbnesdocumentstatus;	
	
	 public Shcustomsdoc() {
		 super();  
	 }

	 public Shcustomsdoc(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EShcustomsdoc {	
			// KEX							KIDS
			
		 	shdocumentUnid,
		 	documentcode, 	
		 	documentpart, 
		 	documentquantity,
		 	documentreason, 
		 	documentref,				 	  		 	
		 	documentstatus;
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EShcustomsdoc) tag) {
				default: return;			
			}
		} else {			
			switch ((EShcustomsdoc) tag) {			
			
				case shdocumentUnid:
					setShdocumentUnid(value);
					break;
				
				case documentcode:
					setDocumentCode(value);
					break;
				
				case documentpart:
					setDocumentPart(value);
					break;
				
				case documentquantity:
					setDocumentQuantity(value);
					break;
				
				case documentreason:
					setDocumentReason(value);
					break;
				
				case documentref:
					setDocumentRef(value);
					break;
				
				case documentstatus:
					setDocumentStatus(value);
					break;							
				
				default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
				return EShcustomsdoc.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getShdocumentUnid() {
		return shdocumentUnid;
	}

	public void setShdocumentUnid(String value) {
		this.shdocumentUnid = Utils.checkNull(value);
	}

	public String getDocumentCode() {
		return documentCode;
	}

	public void setDocumentCode(String value) {
		this.documentCode = Utils.checkNull(value);
	}

	public String getDocumentPart() {
		return documentPart;
	}

	public void setDocumentPart(String value) {
		this.documentPart = Utils.checkNull(value);
	}

	public String getDocumentReason() {
		return documentReason;
	}
	public void setDocumentReason(String value) {
		this.documentReason = Utils.checkNull(value);
	}

	public String getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(String value) {
		this.documentStatus = Utils.checkNull(value);
	}
	
	public String getDocumentQuantity() {
		return documentQuantity;
	}
	public void setDocumentQuantity(String value) {
		this.documentQuantity = Utils.checkNull(value);
	}
	
	public String getDocumentRef() {
		return documentRef;
	}
	public void setDocumentRef(String value) {
		this.documentRef = Utils.checkNull(value);
	}	
		
}
