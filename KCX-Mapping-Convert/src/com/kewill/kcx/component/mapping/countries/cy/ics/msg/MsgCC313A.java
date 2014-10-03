package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofffent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusoffsent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea313;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Iti;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Seaid529;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
/**
 * Module		: MsgCC313A<br>
 * Created		: 23.06.2011<br>
 * Description 	: Contains Message Structure with fields used in MsgCC313A Cyprus.
 *                 
 * @author Frederick T.
 * @version 1.0.00
 */
public class MsgCC313A extends KCXMessage {

	//private MessageHeader	messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea313		heahea;
	private CyprusAddress	traconco;
	private CyprusAddress	traconce;
	private CyprusAddress	notpar;
	private List <Gooitegds> gooitegdsList = null;
	private Iti				iti;
	private List<Iti>		itiList = null;
	private CyprusAddress	trarep;
	private CyprusAddress	perlodsumdec;
	private Seaid529		seaid;
	private List<Seaid529>	seaidList = null;
	private Cusofffent		cusofffent;
	private Cusoffsent		cusoffsent;
	private List<Cusoffsent> cusoffsentList = null;
	private CyprusAddress	tracarent;
	
	public MsgCC313A() {
		super();
	}
	
	public MsgCC313A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC313A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ECC313A {
		//CY              //GR
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA313, 		  HEAHEA,
		TRACONCO1,
		TRACONCE1,
		NOTPAR670,
		GOOITEGDS313, 	  GOOITEGDS,
		ITI,
		TRAREP313,		  TRAREP,
		PERLODSUMDEC,
		SEAID529,
		CUSOFFFENT730313, CUSOFFFENT730,
		CUSOFFSENT740,
		TRACARENT601313,  TRACARENT601;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC313A) tag) {							
				case HEAHEA313:	
				case HEAHEA:
					heahea = new Heahea313(getScanner());
					heahea.parse(tag.name());
					break;
				
				case TRACONCO1:	
					traconco = new CyprusAddress(getScanner());
					traconco.parse(tag.name());
					break;
				
				case TRACONCE1:		
					traconce = new CyprusAddress(getScanner());
					traconce.parse(tag.name());
					break;
				
				case NOTPAR670:		
					notpar = new CyprusAddress(getScanner());
					notpar.parse(tag.name());
					break;
				
				case GOOITEGDS313:	
				case GOOITEGDS:
					Gooitegds gooitegds = new Gooitegds(getScanner());								
					gooitegds.parse(tag.name());
					addGooIteGdsList(gooitegds);
					break;
									
				case ITI:			
					iti = new Iti(getScanner());
					iti.parse(tag.name());
					addItiList(iti);
					break;
									
				case TRAREP313:	
				case TRAREP:
					trarep = new CyprusAddress(getScanner());
					trarep.parse(tag.name());
					break;
									
				case PERLODSUMDEC:	perlodsumdec = new CyprusAddress(getScanner());
									perlodsumdec.parse(tag.name());
									break;
									
				case SEAID529:		seaid = new Seaid529(getScanner());
									seaid.parse(tag.name());
									addSeaidList(seaid);
									break;
									
				case CUSOFFFENT730313:
				case CUSOFFFENT730:
					cusofffent = new Cusofffent(getScanner());
					cusofffent.parse(tag.name());
					break;
									
				case CUSOFFSENT740:	cusoffsent = new Cusoffsent(getScanner());
									cusoffsent.parse(tag.name());
									addCusoffsentList(cusoffsent);
									break;
									
				case TRACARENT601313:
				case TRACARENT601:
					tracarent = new CyprusAddress(getScanner());
					tracarent.parse(tag.name());
					break;									
				default:
					return;
			}
		} else {
			switch((ECC313A) tag) {
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
			return ECC313A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Heahea313 getHeahea() {
		return heahea;
	}

	public void setHeahea(Heahea313 heahea) {
		this.heahea = heahea;
	}

	public CyprusAddress getTraconco() {
		return traconco;
	}

	public void setTraconco(CyprusAddress traconco) {
		this.traconco = traconco;
	}

	public CyprusAddress getTraconce() {
		return traconce;
	}

	public void setTraconce(CyprusAddress traconce) {
		this.traconce = traconce;
	}

	public CyprusAddress getNotpar() {
		return notpar;
	}

	public void setNotpar(CyprusAddress notpar) {
		this.notpar = notpar;
	}

	public List<Gooitegds> getGooIteGdsList() {
		return gooitegdsList;
	}

	public void setGooIteGdsList(List<Gooitegds> gooitegdsList) {
		this.gooitegdsList = gooitegdsList;
	}

	private void addGooIteGdsList(Gooitegds gooitegds) {  //EI20110706
		if (gooitegdsList == null) {
			gooitegdsList = new Vector<Gooitegds>();
		}

		gooitegdsList.add(gooitegds);
	}
		
	public Iti getIti() {
		return iti;
	}

	public void setIti(Iti iti) {
		this.iti = iti;
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

	public Seaid529 getSeaid() {
		return seaid;
	}

	public void setSeaid(Seaid529 seaid) {
		this.seaid = seaid;
	}

	public Cusofffent getCusofffent() {
		return cusofffent;
	}

	public void setCusofffent(Cusofffent cusofffent) {
		this.cusofffent = cusofffent;
	}

	public Cusoffsent getCusoffsent() {
		return cusoffsent;
	}

	public void setCusoffsent(Cusoffsent cusoffsent) {
		this.cusoffsent = cusoffsent;
	}

	public CyprusAddress getTracarent() {
		return tracarent;
	}

	public void setTracarent(CyprusAddress tracarent) {
		this.tracarent = tracarent;
	}

	public List<Iti> getItiList() {
		return itiList;
	}
	public void setItiList(List<Iti> itiList) {
		this.itiList = itiList;
	}
	public void addItiList(Iti iti) {    //EI20110706
		if (itiList == null) {
			itiList = new Vector<Iti>();
		}
		itiList.add(iti);
	}
	
	public List<Seaid529> getSeaidList() {
		return seaidList;
	}
	public void setSeaidList(List<Seaid529> seaidList) {
		this.seaidList = seaidList;
	}
	public void addSeaidList(Seaid529 seal) {  //EI20110706
		if (seaidList == null) {
			seaidList = new Vector<Seaid529>();
		}
		seaidList.add(seal);
	}
	
	public List<Cusoffsent> getCusoffsentList() {
		return cusoffsentList;
	}
	public void setCusoffsentList(List<Cusoffsent> cusoffsentList) {
		this.cusoffsentList = cusoffsentList;
	}
	public void addCusoffsentList(Cusoffsent cus) {  //EI20110706
		if (cusoffsentList == null) {
			cusoffsentList = new Vector<Cusoffsent>();
		}
		cusoffsentList.add(cus);
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
