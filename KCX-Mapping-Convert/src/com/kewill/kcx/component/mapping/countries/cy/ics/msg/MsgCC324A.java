package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea315;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea323;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC324A
 * Created		: 22.06.2011
 * Description	: ICSDiversionRequestRejected message.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MsgCC324A extends KCXMessage {
	
	//private MessageHeader			messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
		
	private Heahea323 heahea324;       //EI20110715
	private List<Funerrer1> funErrer1List;
	
	
	public MsgCC324A() {
		super();
	}
	
	public MsgCC324A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC324A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECC324A {	
		//CY			//GR
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA324,		HEAHEA,	
		FUNERRER1324, 	FUNERRER1;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC324A) tag) {				
				case HEAHEA324:	
				case HEAHEA:	
					heahea324	= new Heahea323(getScanner());
					heahea324.parse(tag.name());
					break;
				
				case FUNERRER1324:	
				case FUNERRER1:
					Funerrer1 funErrer1	= new Funerrer1(getScanner());
					funErrer1.parse(tag.name());
					addFunErrer1List(funErrer1);
					break;				
				default:
					break;
			}
		} else {
			switch((ECC324A) tag) {
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
			return ECC324A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
	public Heahea323 getHeahea() {
		return this.heahea324;
	}
	public void setHeahea(Heahea323 hh) {
		this.heahea324	= hh;
	}
	
	public List<Funerrer1> getFunErrer1List() {
		return this.funErrer1List;
	}
	public void setFunErrer1List(List<Funerrer1> list) {
		this.funErrer1List	= list;
	}
	public void addFunErrer1List(Funerrer1 fcnError) {
		if (this.funErrer1List == null) {
			this.funErrer1List = new Vector<Funerrer1>();
		}		
		this.funErrer1List.add(fcnError);
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
