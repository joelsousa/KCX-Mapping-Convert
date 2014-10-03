package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class MessageHeader extends KCXMessage {

	private String 			transmitter;
	private String 			receiver;
	private String 			sentAtDate;
	private String 			time;
	private String 			messageId;
	private String 			messageName;
	
	public MessageHeader() {
		super();
	}
	
	public MessageHeader(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MessageHeader(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	protected enum EMessageHeader {
		MesSenMES3,
		MesRecMES6,
		DatOfPreMES9,
		TimOfPreMES10,
		MesIdeMES19,
		MesTypMES20;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EMessageHeader) tag) {
					
				default:
					return;
			}
		} else {
			switch((EMessageHeader) tag) {
				
				case MesSenMES3: 	setTransmitter(value);
									break;
									
				case MesRecMES6:	setReceiver(value);
									break;
									
				case DatOfPreMES9:	setSentAtDate(value);
									break;
									
				case TimOfPreMES10: setTime(value);
									break;
									
				case MesIdeMES19:	setMessageId(value);
									break;
									
				case MesTypMES20:	setMessageName(value);
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
		// TODO Auto-generated method stub
		return null;
	}

	public String getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = Utils.checkNull(transmitter);
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = Utils.checkNull(receiver);
	}

	public String getSentAtDate() {
		return sentAtDate;
	}

	public void setSentAtDate(String sentAtDate) {
		this.sentAtDate = Utils.checkNull(sentAtDate);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = Utils.checkNull(time);
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = Utils.checkNull(messageId);
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = Utils.checkNull(messageName);
	}
	
	
}
