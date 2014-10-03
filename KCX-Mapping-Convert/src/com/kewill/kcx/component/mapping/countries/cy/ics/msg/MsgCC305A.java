package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofffent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea304;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.MessageHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
/**
 * Module		: MsgCC305A<br>
 * Created		: 22.06.2011<br>
 * Description 	: Contains Message Structure with fields used in MsgCC305A Cyprus.
 *                 
 * @author Frederick T.
 * @version 1.0.00
 */
public class MsgCC305A extends KCXMessage {
	
	//private MessageHeader 			messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea304			heahea;
	private List<Funerrer1>	funerrerList;
	private CyprusAddress		trarep;
	private CyprusAddress		perlodsumdec;
	private Cusofffent			cusofffent;
	
	public MsgCC305A() {
		super();
	}
	
	public MsgCC305A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC305A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECC305A {
		//CY				//GR
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA305, 			HEAHEA,
		FUNERRER1305,		FUNERRER1,
		TRAREP305,			TRAREP,
		PERLODSUMDEC,
		CUSOFFFENT730305,   CUSOFFFENT730;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC305A) tag) {				
				case HEAHEA305:
				case HEAHEA:
					heahea	= new Heahea304(getScanner());
					heahea.parse(tag.name());
					break;
				
				case FUNERRER1305:
				case FUNERRER1:	
					Funerrer1 wrkFunerrer = new Funerrer1(getScanner());
					wrkFunerrer.parse(tag.name());
					addFunerrerList(wrkFunerrer);
					break;
				
				case TRAREP305:	
				case TRAREP:
					trarep	= new CyprusAddress(getScanner());								
					trarep.parse(tag.name());								
					break;
				
				case PERLODSUMDEC:	
					perlodsumdec	= new CyprusAddress(getScanner());
					perlodsumdec.parse(tag.name());
					break;
				
				case CUSOFFFENT730305:
				case CUSOFFFENT730:
					cusofffent	= new Cusofffent(getScanner());
					cusofffent.parse(tag.name());
					break;				
				default:
					return;
			}
		} else {
			switch((ECC305A) tag) {
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
		// TODO Auto-generated method stub
		//return null;
		//EI20110713 es fehlte die Inplementierung:
		try {
			return ECC305A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Heahea304 getHeahea() {
		return heahea;
	}

	public void setHeahea(Heahea304 heahea) {
		this.heahea = heahea;
	}

	public List<Funerrer1> getFunerrerList() {
		return funerrerList;
	}

	public void setFunerrerList(List<Funerrer1> funerrerList) {
		this.funerrerList = funerrerList;
	}
	private void addFunerrerList(Funerrer1 error) { //EI20110706
		if (funerrerList == null) {
			funerrerList = new Vector<Funerrer1>();
		}
		funerrerList.add(error);
	}
	public CyprusAddress getTrarep() {
		return trarep;
	}

	public void setTrarep(CyprusAddress trarep) {
		this.trarep = trarep;
	}

	public CyprusAddress getPerlodsumdec() {
		return perlodsumdec;
	}

	public void setPerlodsumdec(CyprusAddress perlodsumdec) {
		this.perlodsumdec = perlodsumdec;
	}

	public Cusofffent getCusofffent() {
		return cusofffent;
	}

	public void setCusofffent(Cusofffent cusofffent) {
		this.cusofffent = cusofffent;
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}	
}
