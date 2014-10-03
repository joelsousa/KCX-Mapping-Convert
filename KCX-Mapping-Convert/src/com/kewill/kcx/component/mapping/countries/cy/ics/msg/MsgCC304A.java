package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofffent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea304;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea315;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.MessageHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC304A<br>
 * Created		: 23.06.2011<br>
 * Description 	: Contains Message Structure with fields used in MsgCC304A Cyprus.
 *                 
 * @author Jude Eco
 * @version 1.0.00
 */
public class MsgCC304A extends KCXMessage {
	//private MessageHeader 	messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea304 		heahea;
	private List <Gooitegds> gooitegdsList = null;
	private CyprusAddress	trarep;
	private CyprusAddress	perlodsumdec;
	private Cusofffent		cusofffent;
	private CyprusAddress	tracarent;
	
	public MsgCC304A() {
		super();
	}
	
	public MsgCC304A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC304A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EMsgCC304A {
		//CY			//GR
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA304, 		HEAHEA,
		GOOITEGDS304,	GOOITEGDS,
		TRAREP304,		TRAREP,
		PERLODSUMDEC,
		CUSOFFFENT730304, CUSOFFFENT730,
		TRACARENT601304,  TRACARENT601;
	}
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgCC304A) tag) {			
			case HEAHEA304:
			case HEAHEA:
				heahea = new Heahea304(getScanner());
				heahea.parse(tag.name());
				break;
				
			case GOOITEGDS304:
			case GOOITEGDS:
				Gooitegds gooitegds = new Gooitegds(getScanner());
				gooitegds.parse(tag.name());
				addGooIteGdsList(gooitegds);
				break;
				
			case TRAREP304:
			case TRAREP:
				trarep = new CyprusAddress(getScanner());
				trarep.parse(tag.name());
				break;
				
			case PERLODSUMDEC:
				perlodsumdec = new CyprusAddress(getScanner());
				perlodsumdec.parse(tag.name());
				break;
				
			case CUSOFFFENT730304:
			case CUSOFFFENT730:
				cusofffent = new Cusofffent(getScanner());
				cusofffent.parse(tag.name());
				break;
				
			case TRACARENT601304:
			case TRACARENT601:
				tracarent = new CyprusAddress(getScanner());
				tracarent.parse(tag.name());
				break;
				
			default:
				return;
			}
		} else {
			switch ((EMsgCC304A) tag) {
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
			return EMsgCC304A.valueOf(token);
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

	public List<Gooitegds> getGooIteGdsList() {
		return gooitegdsList;
	}

	public void setGooIteGdsList(List<Gooitegds> gooitegdsList) {
		this.gooitegdsList = gooitegdsList;
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

	public CyprusAddress getTracarent() {
		return tracarent;
	}

	public void setTracarent(CyprusAddress tracarent) {
		this.tracarent = tracarent;
	}
	
	private void addGooIteGdsList(Gooitegds gooitegds) {
		if (gooitegdsList == null) {
			gooitegdsList = new Vector<Gooitegds>();
		}
		gooitegdsList.add(gooitegds);
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
