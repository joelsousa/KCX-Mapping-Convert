package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofffent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea323;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.MessageHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC325A
 * Created		: 22.06.2011
 * Description	: ICSDiversionRequestAcknowledgement message.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MsgCC325A extends KCXMessage {
	
	//private MessageHeader	messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea323			heahea;
	private Cusofffent		cusOfffEnt730;
	private CyprusAddress	traReqDiv456;	
	
	public MsgCC325A() {
		super();
	}
	
	public MsgCC325A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC325A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECC325A {
		//CY				//GR
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA325, 			HEAHEA,
		CUSOFFFENT730325,	CUSOFFFENT730,
		TRAREQDIV456;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC325A) tag) {				
				case HEAHEA325:	
				case HEAHEA:
					heahea	= new Heahea323(getScanner());
					heahea.parse(tag.name());
					break;
				
				case CUSOFFFENT730325:
				case CUSOFFFENT730:
					cusOfffEnt730	= new Cusofffent(getScanner());														
					cusOfffEnt730.parse(tag.name());
					break;
				
				case TRAREQDIV456:		traReqDiv456	= new CyprusAddress(getScanner());
										traReqDiv456.parse(tag.name());
										break;
				
				default:
					break;
			}
		} else {
			switch((ECC325A) tag) {
				case CorIdeMES25:
					setCorIdeMES25(value);
					break;
				default:
					break;
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
			return ECC325A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
	public Heahea323 getHeahea() {
		return this.heahea;
	}
	public void setHeahea(Heahea323 hh) {
		this.heahea	= hh;
	}
	
	public Cusofffent getCusOfffEnt730() {
		return this.cusOfffEnt730;
	}
	public void setCusOfffEnt730(Cusofffent cusOfffEnt) {
		this.cusOfffEnt730	= cusOfffEnt;
	}
	
	public CyprusAddress getTraReqDiv456() {
		return this.traReqDiv456;
	}
	public void setTraReqDiv456(CyprusAddress traReqDiv) {
		this.traReqDiv456	= traReqDiv;
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
