package com.kewill.kcx.component.mapping.countries.fr.ncts.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Enveloppe;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Notification;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: MsgIE07 = KIDS-NCTSArrivalNotification
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgOperateur extends KCXMessage {

	private Enveloppe enveloppe; 	
	private Notification notification; 
	
	public MsgOperateur() {
		super();
	}

	public MsgOperateur(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgOperateur(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	private enum Eei07 {		
		EnveloppeMessage,      			
		Notification;	//Message	
	}	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((Eei07) tag) {			
			case EnveloppeMessage: 	
				enveloppe = new Enveloppe(getScanner());  	
				enveloppe.parse(tag.name());
				break;
			
			case Notification:	
				notification = new Notification(getScanner());  	
				notification.parse(tag.name());
				break;		 	
			default:
					return;
			}
		} else {
			switch ((Eei07) tag) {			
				
			default:
					return;
			}
		}		
	}
	
	public void stoppElement(Enum tag) {
	}
	
	public Enum translate(String token) {
		try {
			return Eei07.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Enveloppe getEnveloppe() {
		return enveloppe;
	}
	public void setEnveloppe(Enveloppe enveloppe) {
		this.enveloppe = enveloppe;
	}

	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public boolean isEmpty() {
		return 	(enveloppe == null || enveloppe.isEmpty()) &&				
				(notification == null || notification.isEmpty()); 
	}
}
