package com.kewill.kcx.component.mapping.countries.de.cmp.msg;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentFWB;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageBusinessHeader;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageHeaderDocument;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: WaybillData (FWB).
* 
* @author iwaniuk
* @version 1.0.00
*/

public class FreightWayBill extends KCXMessage {
	
	private MessageHeaderDocument 	messageHeaderDocument;   
	private MessageBusinessHeader 	businessHeaderDocument;  
	private MasterConsignmentFWB masterConsignment;       
	
	private enum EWaybillData {	
		MessageHeaderDocument,
		BusinessHeaderDocument,
		MasterConsignment,		
	}
	
	public FreightWayBill() {
		super();  
	}

    public FreightWayBill(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EWaybillData) tag) {
  			
  			case MessageHeaderDocument:
  				messageHeaderDocument = new MessageHeaderDocument(getScanner());
  				messageHeaderDocument.parse(tag.name());  				
				break;
				
  			case BusinessHeaderDocument:
  				businessHeaderDocument = new MessageBusinessHeader(getScanner());
  				businessHeaderDocument.parse(tag.name());
				break;
				
  			case MasterConsignment:
  				masterConsignment = new MasterConsignmentFWB(getScanner());
  				masterConsignment.parse(tag.name());
  				break;
				  			
  			default:
  					return;
  			}
  		} else {
  			switch ((EWaybillData) tag) {  	
  			
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
				return EWaybillData.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}
		

	public MessageHeaderDocument getMessageHeaderDocument() {
		return messageHeaderDocument;
	}
	public void setMessageHeaderDocument(MessageHeaderDocument argument) {
		this.messageHeaderDocument = argument;
	}

	public MessageBusinessHeader getBusinessHeaderDocument() {
		return businessHeaderDocument;
	}
	public void setBusinessHeaderDocument(MessageBusinessHeader argument) {
		this.businessHeaderDocument = argument;
	}

	public MasterConsignmentFWB getMasterConsignment() {
		return masterConsignment;
	}
	public void setMasterConsignment(MasterConsignmentFWB argument) {
		this.masterConsignment = argument;
	}	
	
	public boolean isEmpty() {
		return 	(messageHeaderDocument == null || messageHeaderDocument.isEmpty()) &&
				(businessHeaderDocument == null || businessHeaderDocument.isEmpty()) &&
				(masterConsignment == null || masterConsignment.isEmpty());
				
	}
}
