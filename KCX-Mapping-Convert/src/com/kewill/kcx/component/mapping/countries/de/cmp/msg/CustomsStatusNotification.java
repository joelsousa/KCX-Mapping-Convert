package com.kewill.kcx.component.mapping.countries.de.cmp.msg;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentCSN;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageBusinessHeader;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageBusinessHeaderCSN;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageHeaderDocument;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 25.07.2013<br>
* Description	: CustomsStatusNotification (CSN).
* 
* @author iwaniuk
* @version 1.0.00
*/

public class CustomsStatusNotification extends KCXMessage {
	
	private MessageHeaderDocument 	 messageHeaderDocument;   
	private MessageBusinessHeaderCSN businessHeaderDocument;  
	private MasterConsignmentCSN	 masterConsignment;       
	
	private enum ECustomsStatusNotification {	
		MessageHeaderDocument,
		BusinessHeaderDocument,
		MasterConsignment,	
	}
	
	public CustomsStatusNotification() {
		super();  
	}

    public CustomsStatusNotification(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ECustomsStatusNotification) tag) {
  			
  			case MessageHeaderDocument:
  				messageHeaderDocument = new MessageHeaderDocument(getScanner());
  				messageHeaderDocument.parse(tag.name());  				
				break;
				
  			case BusinessHeaderDocument:
  				businessHeaderDocument = new MessageBusinessHeaderCSN(getScanner());
  				businessHeaderDocument.parse(tag.name());
				break;
				
  			case MasterConsignment:
  				masterConsignment = new MasterConsignmentCSN(getScanner());
  				masterConsignment.parse(tag.name());
  				break;
				  		
  			default:
  					return;
  			}
  		} else {
  			switch ((ECustomsStatusNotification) tag) {  	  			
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
				return ECustomsStatusNotification.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}
		
	public MessageHeaderDocument getMessageHeaderDocument() {
		return messageHeaderDocument;
	}
	public void setMessageHeaderDocument(MessageHeaderDocument messageHeaderDocument) {
		this.messageHeaderDocument = messageHeaderDocument;
	}

	public MessageBusinessHeaderCSN getBusinessHeaderDocument() {
		return businessHeaderDocument;
	}
	public void setBusinessHeaderDocument(MessageBusinessHeaderCSN businessHeaderDocument) {
		this.businessHeaderDocument = businessHeaderDocument;
	}

	public MasterConsignmentCSN getMasterConsignment() {
		return masterConsignment;
	}

	public void setMasterConsignment(MasterConsignmentCSN argument) {
		this.masterConsignment = argument;
	}	
	
	public boolean isEmpty() {
		return 	(messageHeaderDocument == null || messageHeaderDocument.isEmpty()) &&
				(businessHeaderDocument == null || businessHeaderDocument.isEmpty()) &&
				(masterConsignment == null || masterConsignment.isEmpty()); 
	}
}
