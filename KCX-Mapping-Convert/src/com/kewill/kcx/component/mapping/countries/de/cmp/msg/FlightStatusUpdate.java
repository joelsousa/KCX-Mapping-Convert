package com.kewill.kcx.component.mapping.countries.de.cmp.msg;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentFSU;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageBusinessHeader;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MessageHeaderDocument;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 29.07.2013<br>
* Description	: FlightStatusUpdate (FSU).
* 
* @author iwaniuk
* @version 1.0.00
*/

public class FlightStatusUpdate extends KCXMessage {
	
	private MessageHeaderDocument 	messageHeaderDocument;   
	private MessageBusinessHeader 	businessHeaderDocument;  
	private MasterConsignmentFSU masterConsignment;       
	
	private enum EFlightStatusUpdate {	
		MessageHeaderDocument,
		BusinessHeaderDocument,  //only one Tag: ID
		MasterConsignment,	
	}
	
	public FlightStatusUpdate() {
		super();  
	}

    public FlightStatusUpdate(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EFlightStatusUpdate) tag) {
  			
  			case MessageHeaderDocument:
  				messageHeaderDocument = new MessageHeaderDocument(getScanner());
  				messageHeaderDocument.parse(tag.name());  				
				break;
				
  			case BusinessHeaderDocument:
  				businessHeaderDocument = new MessageBusinessHeader(getScanner());
  				businessHeaderDocument.parse(tag.name());
				break;
				
  			case MasterConsignment:
  				masterConsignment = new MasterConsignmentFSU(getScanner());
  				masterConsignment.parse(tag.name());
  				break;
				  		
  			default:
  					return;
  			}
  		} else {
  			switch ((EFlightStatusUpdate) tag) {  	  			
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
				return EFlightStatusUpdate.valueOf(token);
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

	public MessageBusinessHeader getBusinessHeaderDocument() {
		return businessHeaderDocument;
	}
	public void setBusinessHeaderDocument(MessageBusinessHeader businessHeaderDocument) {
		this.businessHeaderDocument = businessHeaderDocument;
	}

	public MasterConsignmentFSU getMasterConsignment() {
		return masterConsignment;
	}

	public void setMasterConsignment(MasterConsignmentFSU argument) {
		this.masterConsignment = argument;
	}	
	
	public boolean isEmpty() {
		return 	(messageHeaderDocument == null || messageHeaderDocument.isEmpty()) &&
				(businessHeaderDocument == null || businessHeaderDocument.isEmpty()) &&
				(masterConsignment == null || masterConsignment.isEmpty()); 
	}
}
