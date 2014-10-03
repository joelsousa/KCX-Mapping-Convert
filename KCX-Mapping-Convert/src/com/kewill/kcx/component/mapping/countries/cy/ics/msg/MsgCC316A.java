package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea315;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.MessageHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC316A
 * Created		: 04.07.2011
 * Description	: ICSEntrySummaryDeclarationRejected message.
 * 
 * @author	Frederick T.
 * @version	1.0.00
 *
 */

public class MsgCC316A extends KCXMessage {
	//private MessageHeader	messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea315	heahea;	
	private Funerrer1 fcnError;
	private List <Funerrer1>	fcnErrorList;
		
	public MsgCC316A() {
		super();
	}
	
	public MsgCC316A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC316A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECC316A {
		//CY             	 //GR
		CorIdeMES25, 				 //form MESSAGE316=Header
		HEAHEA316, 			HEAHEA,
		FUNERRER1316, 		FUNERRER1,
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC316A) tag) {
				case HEAHEA316:
				case HEAHEA:
					heahea = new Heahea315(getScanner());
					heahea.parse(tag.name());
					break;				
				case FUNERRER1316:	
				case FUNERRER1:	
					fcnError = new Funerrer1(getScanner());
					fcnError.parse(tag.name());
					addFunErrerList(fcnError);
					break;			
				default:
					return;
			}
		} else {
			switch((ECC316A) tag) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return ECC316A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public Heahea315 getHeahea() {
		return this.heahea;
	}
	public void setHeahea(Heahea315 heahea) {
		this.heahea	= heahea;
	}	

	public Funerrer1 getFcnError() {
		return fcnError;
	}

	public void setFcnError(Funerrer1 fcnError) {
		this.fcnError = fcnError;
	}

	public List<Funerrer1> getFunErrerList() {
		return fcnErrorList;
	}

	public void setFunErrerList(List<Funerrer1> fcnErrorList) {
		this.fcnErrorList = fcnErrorList;
	}
	public void addFunErrerList(Funerrer1 error) {   //EI20110706
		if (fcnErrorList == null) {
			fcnErrorList = new Vector<Funerrer1>();
		}
		fcnErrorList.add(error);
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
