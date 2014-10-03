package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Iti;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Seaide529;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofffent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofflon;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusoffsent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea315;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.MessageHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC315A
 * Created		: 23.06.2011
 * Description	: ICSEntrySummaryDeclaration message.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MsgCC315A extends KCXMessage {
	
	//private MessageHeader		messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea315				heahea;
	
	private CyprusAddress		traConco1;			// Consignor
	private CyprusAddress		traConce1;			// Consignee
	private CyprusAddress		notPar670;			// NotifyParty	
	private List<Gooitegds>		gooIteGdsList;
	private Iti					iti;
	private List<Iti>			itiList;
	private Cusofflon			cusOffLon;
	private CyprusAddress		traRep;			// Representative
	private CyprusAddress		perLodSumDec;		// PersonLodgingSuma
	private Seaide529			seaId529;
	private List<Seaide529>		seaId529List;
	private Cusofffent			cusOfffEnt730;
	private Cusoffsent			cusOffSEnt740;
	private List<Cusoffsent>	cusOffSEnt740List;
	private CyprusAddress		traCarEnt601;	// CarrierAddress
	
	
	public MsgCC315A() {
		super();
	}
	
	public MsgCC315A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC315A(XmlMsgScanner scanner) {
		super(scanner);
	}	
	
	private enum ECC315A {
		//CY			//GR
		CorIdeMES25,  				//form MESSAGE-Group=Header	
		HEAHEA315, 		HEAHEA,
		TRACONCO1,
		TRACONCE1,
		NOTPAR670,
		GOOITEGDS315,   GOOITEGDS,
		ITI,
		CUSOFFLON315,   CUSOFFLON,
		TRAREP315,		TRAREP,
		PERLODSUMDEC,
		SEAID529,
		CUSOFFFENT730315, CUSOFFFENT730,
		CUSOFFSENT740,
		TRACARENT601315,  TRACARENT601;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC315A) tag) {			
				case HEAHEA315:		
				case HEAHEA:
					heahea	= new Heahea315(getScanner());
					heahea.parse(tag.name());
					break;
				
				case TRACONCO1:			
					traConco1	= new CyprusAddress(getScanner());
					traConco1.parse(tag.name());
					break;
				
				case TRACONCE1:			
					traConce1	= new CyprusAddress(getScanner());
					traConce1.parse(tag.name());
					break;
				
				case NOTPAR670:			
					notPar670	= new CyprusAddress(getScanner());
					notPar670.parse(tag.name());
					break;
				
				case GOOITEGDS315:
				case GOOITEGDS:
					Gooitegds gooIteGds = new Gooitegds(getScanner());
					gooIteGds.parse(tag.name());
					addGooIteGdsList(gooIteGds);
					break;
				
				case ITI:				
					iti	= new Iti(getScanner());
					iti.parse(tag.name());
					addItiList(iti);
					break;
				
				case CUSOFFLON315:	
				case CUSOFFLON:
					cusOffLon = new Cusofflon(getScanner());
					cusOffLon.parse(tag.name());
					break;
				
				case TRAREP315:	
				case TRAREP:
					traRep = new CyprusAddress(getScanner());
					traRep.parse(tag.name());
					break;
				
				case PERLODSUMDEC:		
					perLodSumDec = new CyprusAddress(getScanner());
					perLodSumDec.parse(tag.name());
					break;
				
				case SEAID529:			
					seaId529 = new Seaide529(getScanner());
					seaId529.parse(tag.name());
					addSeaId529List(seaId529);
					break;
				
				case CUSOFFFENT730315:
				case CUSOFFFENT730:
					cusOfffEnt730 = new Cusofffent(getScanner());
					cusOfffEnt730.parse(tag.name());
					break;
				
				case CUSOFFSENT740:		
					cusOffSEnt740 = new Cusoffsent(getScanner());
					cusOffSEnt740.parse(tag.name());
					break;
				
				case TRACARENT601315:
				case TRACARENT601:
					traCarEnt601	= new CyprusAddress(getScanner());
					traCarEnt601.parse(tag.name());
					break;			
				default:
					return;
			}
		} else {
			switch((ECC315A) tag) {
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
			return ECC315A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public Heahea315 getHeahea() {
		return this.heahea;
	}
	public void setHeahea(Heahea315 hh) {
		this.heahea	= hh;
	}
	
	public CyprusAddress getTraConco1() {
		return this.traConco1;
	}
	public void setTraConco1(CyprusAddress consignor) {
		this.traConco1	= consignor;
	}
	
	public CyprusAddress getTraConce1() {
		return this.traConce1;
	}
	public void setTraConce1(CyprusAddress consignee) {
		this.traConce1	= consignee;
	}
	
	public CyprusAddress getNotPar670() {
		return this.notPar670;
	}
	public void setNotPar670(CyprusAddress notifyParty) {
		this.notPar670	= notifyParty;
	}
	
	public List<Gooitegds> getGooIteGdsList() {
		return this.gooIteGdsList;
	}
	public void setGooIteGdsList(List<Gooitegds> list) {
		this.gooIteGdsList = list;
	}
	public void addGooIteGdsList(Gooitegds gig) {
		if (this.gooIteGdsList == null) {
			this.gooIteGdsList	= new Vector<Gooitegds>();
		}
		
		this.gooIteGdsList.add(gig);
	}
	
	public List<Iti> getItiList() {
		return this.itiList;
	}
	public void addItiList(Iti iItem) {
		if (this.itiList == null) {
			this.itiList	= new Vector<Iti>();
		}
		
		this.itiList.add(iItem);
	}
	
	public Cusofflon getCusOffLon() {
		return this.cusOffLon;
	}
	public void setCusOffLon(Cusofflon cusOffLon) {
		this.cusOffLon	= cusOffLon;
	}
	
	public CyprusAddress getTraRep() {
		return this.traRep;
	}
	public void setTraRep(CyprusAddress traRep) {
		this.traRep	= traRep;
	}
	
	public CyprusAddress getPerLodSumDec() {
		return this.perLodSumDec;
	}
	public void setPerLodSumDec(CyprusAddress perLSD) {
		this.perLodSumDec	= perLSD;
	}
	
	public List<Seaide529> getSeaId529List() {
		return this.seaId529List;
	}
	public void addSeaId529List(Seaide529 seaId) {
		if (this.seaId529List == null) {
			this.seaId529List	= new Vector<Seaide529>();
		}
		
		this.seaId529List.add(seaId);
	}
	
	public Cusofffent getCusOfffEnt730() {
		return this.cusOfffEnt730;
	}
	public void setCusOfffEnt730(Cusofffent cusOfffEnt) {
		this.cusOfffEnt730	= cusOfffEnt;
	}
	
	public List<Cusoffsent> getCusOffSEnt740List() {
		return this.cusOffSEnt740List;
	}
	public void setCusOffSEnt740List(List<Cusoffsent> cusOffSEnt) {
		this.cusOffSEnt740List	= cusOffSEnt;
	}
	
	public CyprusAddress getTraCarEnt601() {
		return this.traCarEnt601;
	}
	public void setTraCarEnt601(CyprusAddress traCarEnt) {
		this.traCarEnt601	= traCarEnt;
	}	
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
