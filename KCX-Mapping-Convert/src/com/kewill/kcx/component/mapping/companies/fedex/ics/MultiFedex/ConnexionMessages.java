package com.kewill.kcx.component.mapping.companies.fedex.ics.MultiFedex;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexMessage;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Cusofflon;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.CustomsOffice;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Iti;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Seaide529;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class ConnexionMessages extends KCXMessage {

	private List<FedexMessage> messagesList = null;
	
	protected enum EFMessages {
		Message;       
	}
	
	public ConnexionMessages() {
		super();
	}

	public ConnexionMessages(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		messagesList = new Vector<FedexMessage>();
	}	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EFMessages) tag) {
			case Message: 	
				FedexMessage message = new FedexMessage(getScanner());  	
				message.parse(tag.name());
				messagesList.add(message);
				break;					
			default:
					return;
			}
		} 		
	}

	@Override
	public void stoppElement(Enum tag) {
	}

	@Override
	public Enum translate(String token) {
		try {
			return EFMessages.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}


	public List<FedexMessage> getMessagesList() {
		return messagesList;
	
	}
	public void setMessagesList(List<FedexMessage> list) {
		this.messagesList = list;
	}

}
