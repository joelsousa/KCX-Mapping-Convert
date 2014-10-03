package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.XmlErr805;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: MsgCB917B<br>
 * Created		: 08.07.2011<br>
 * Description	: localAppResult.
 * 
 * @author	iwaniuk
 * @version	1.0.00
 */

public class MsgCD917B extends KCXMessage {
		
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea heahea;		
	private List<XmlErr805>	errList;
	
	public MsgCD917B() {
		super();
	}

	public MsgCD917B(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgCD917B(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	private enum ECD917B {
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA,		
		XMLERR805;
  
	}
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECD917B) tag) {						
			case HEAHEA:
				heahea = new Heahea(getScanner());
				heahea.parse(tag.name());
				break;
			case XMLERR805:  
				XmlErr805 err = new XmlErr805(getScanner());
				err.parse(tag.name());
				addErrList(err);
				break;				
			default:
					return;
			}
		} else {
			switch ((ECD917B) tag) {			
				case CorIdeMES25:
					setCorIdeMES25(value);
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
			return ECD917B.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Heahea getHeahea() {
		return heahea;
	}

	public void setHeahea(Heahea heahea) {
		this.heahea = heahea;
	}	

	public List<XmlErr805> getErrList() {
		return errList;
	}

	public void setErrList(List<XmlErr805> goodsItemList) {
		this.errList = goodsItemList;
	}

	private void addErrList(XmlErr805 error) {
		if (errList == null) {
			errList = new Vector<XmlErr805>();
		}
		errList.add(error);
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
