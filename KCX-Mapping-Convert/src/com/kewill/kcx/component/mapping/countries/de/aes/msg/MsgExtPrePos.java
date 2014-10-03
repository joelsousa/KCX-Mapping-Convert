package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Export<br>
 * Erstellt		: 06.07.2012.<br>
 * Beschreibung : KIDS MsgExitPresentationPos              
 * 
 * @author iwaniuk
 * @version 1.1.00
 */
public class MsgExtPrePos extends KCXMessage {
	
	private String msgName = "ExitPresentationPos";	
	private String itemNumber;	
	private TransportMeans meansOfTransport;
	
	public MsgExtPrePos() {
			super();
	}
	
	public MsgExtPrePos(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgExtPrePos(XmlMsgScanner scanner) {
		super(scanner);		
	}

	private enum EExitPresentationPos {
		//KIDS-Name							//UIDS-Name      
		ItemNumber,								
		MeansOfTransport, 		
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EExitPresentationPos) tag) {
			
				case MeansOfTransport:
					meansOfTransport = new TransportMeans(getScanner());  	
					meansOfTransport.parse(tag.name());
					break;	
				default:
					return;
			}
		} else {
			switch ((EExitPresentationPos) tag) {
				case ItemNumber:
					setItemNumber(value);
					break;
				
				default: break;
				}
		}		
	}

	public void stoppElement(Enum tag) {
	}
		
	public Enum translate(String token) {
		try {
				return EExitPresentationPos.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	
	//-----------------------------------------------------------
	
	public void setItemNumber(String argument) {
		this.itemNumber = argument;
	}
	public String getItemNumber() {
		return itemNumber;
	}
	
	public void setMeansOfTransport(TransportMeans argument) {
		this.meansOfTransport = argument;
	}
	public TransportMeans getMeansOfTransport() {
		return meansOfTransport;
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}

}

