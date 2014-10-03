package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class FedexMessageHeader extends KCXMessage{

	 private boolean debug   = false;
	 private XMLEventReader parser	= null;
	 
	 private String messageName 	= "";
	 private String release			= "";
	 private String transmitter		= "";
	 private String messageId		= "";
	    
	 private enum EEnveloppeMessage {
	 // Fedex-TagNames, 			KIDS-TagNames
		schemaID,				MessageName,
		schemaVersion,			Release,
		partyId,				Transmitter,
		transactionId,			MessageId,
		numseq;					//Not needed
	 }

	 public FedexMessageHeader() {
	      	super();
	 }

	 public FedexMessageHeader(XMLEventReader parser) {
 		super(parser);
 		this.parser = parser;
	 }      
   
	 public FedexMessageHeader(XmlMsgScanner scanner) {
		super(scanner);
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EEnveloppeMessage) tag) {
			default:
					return;
			}
		} else {

			switch ((EEnveloppeMessage) tag) {
				case schemaID:
					setMessageName(value);
					break;  					
				case schemaVersion:
					setRelease(value);
					break;  				
				case partyId:
					setTransmitter(value);
					break;  				
				case transactionId:
					setMessageId(value);
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
			return EEnveloppeMessage.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getMessageName() {
		return messageName;
	
	}

	public void setMessageName(String messageName) {
		this.messageName = Utils.checkNull(messageName);
	}

	public String getRelease() {
		return release;
	
	}

	public void setRelease(String release) {
		this.release = Utils.checkNull(release);
	}

	public String getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = Utils.checkNull(transmitter);
	}

	public String getMessageId() {
		return messageId;
	
	}

	public void setMessageId(String messageId) {
		this.messageId = Utils.checkNull(messageId);
	}
}
