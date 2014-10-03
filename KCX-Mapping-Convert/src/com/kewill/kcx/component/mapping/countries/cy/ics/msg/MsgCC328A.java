package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofffent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofflon;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea315;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.MessageHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC328A<br>
 * Created		: 23.06.2011<br>
 * Description 	: Contains Message Structure with fields used in MsgCC328A Cyprus.
 *                 
 * @author Jude Eco
 * @version 1.0.00
 */
public class MsgCC328A extends KCXMessage {
	private MessageHeader messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea315 heahea328;
	private List<Gooitegds> gooitegds328List = null;	
	private Cusofflon cusofflon328;
	private CyprusAddress perlodsumdec;
	private Cusofffent cusofffent730328;
	private CyprusAddress tracarent601;

	public MsgCC328A() {
		super();
	}

	public MsgCC328A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgCC328A(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	private enum EMsgCC328A {
		//CY				//GR
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA328, 			HEAHEA,
		GOOITEGDS328,		GOOITEGDS,
		CUSOFFLON328,		CUSOFFLON,
		PERLODSUMDEC,
		CUSOFFFENT730328,	CUSOFFFENT730,
		TRACARENT601;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgCC328A) tag) {			
			case HEAHEA328:
			case HEAHEA:
				heahea328 = new Heahea315(getScanner());
				heahea328.parse(tag.name());
				break;
			case GOOITEGDS328:
			case GOOITEGDS:
				Gooitegds gooitegds328 = new Gooitegds(getScanner());
				gooitegds328.parse(tag.name());
				addGooIteGdsList(gooitegds328);
				break;
			case CUSOFFLON328:
			case CUSOFFLON:
				cusofflon328 = new Cusofflon(getScanner());
				cusofflon328.parse(tag.name());
				break;
			case PERLODSUMDEC:
				perlodsumdec = new CyprusAddress(getScanner());
				perlodsumdec.parse(tag.name());
				break;
			case CUSOFFFENT730328:
			case CUSOFFFENT730:
				cusofffent730328 = new Cusofffent(getScanner());
				cusofffent730328.parse(tag.name());
				break;
			case TRACARENT601:
				tracarent601 = new CyprusAddress(getScanner());
				tracarent601.parse(tag.name());
				break;
			default:
				return;
			}
		} else {
			switch ((EMsgCC328A) tag) {
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
			return EMsgCC328A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public Heahea315 getHeahea() {
		return heahea328;
	}

	public void setHeahea(Heahea315 heahea328) {
		this.heahea328 = heahea328;
	}

	public List<Gooitegds> getGooIteGdsList() {
		return gooitegds328List;
	}

	public void setGooIteGdsList(List<Gooitegds> gooitegds328List) {
		this.gooitegds328List = gooitegds328List;
	}
	
	private void addGooIteGdsList(Gooitegds gooitegds328) {
		if (gooitegds328List == null) {
			gooitegds328List = new Vector<Gooitegds>();
		}
		gooitegds328List.add(gooitegds328);
	}
	
	public Cusofflon getCusofflon() {
		return cusofflon328;
	}

	public void setCusofflon(Cusofflon cusofflon328) {
		this.cusofflon328 = cusofflon328;
	}

	public CyprusAddress getPerlodsumdec() {
		return perlodsumdec;
	}

	public void setPerlodsumdec(CyprusAddress perlodsumdec) {
		this.perlodsumdec = perlodsumdec;
	}

	public Cusofffent getCusofffent730() {
		return cusofffent730328;
	}

	public void setCusofffent730(Cusofffent cusofffent730328) {
		this.cusofffent730328 = cusofffent730328;
	}

	public CyprusAddress getTracarent601() {
		return tracarent601;
	}

	public void setTracarent601(CyprusAddress tracarent601) {
		this.tracarent601 = tracarent601;
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}	
}
