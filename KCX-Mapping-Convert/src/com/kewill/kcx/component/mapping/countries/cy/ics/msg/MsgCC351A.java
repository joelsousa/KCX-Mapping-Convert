package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusint;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusofffent;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea351;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.MessageHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: MsgCC351A<br>
 * Created		: 22.06.2010<br>
 * Description	: ICSDeclarationAmendmentAcknowledgment.
 * 
 * @author	Lassiter Caviles
 * @version	1.0.00
 *
 */

public class MsgCC351A extends KCXMessage {
	
	//private MessageHeader messageHeader;
	private String corIdeMES25;                   //EI20110707 ist noch von Header
	
	private Heahea351 heahea;	
	//Goods ITem
	private Gooitegds 			goodsItem;
	private List<Gooitegds> 	goodsItemList;	
	private CyprusAddress		trarep;
	private CyprusAddress		perlodsumdec;	
	private Cusofffent			cusoffent;	
	private CyprusAddress		tracarent;
	private Cusint				cusint;
	private List<Cusint>		cusintList;
	
	public MsgCC351A() {
		super();
	}

	public MsgCC351A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgCC351A(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	private enum ECC351A {
		//CY				//GR
		CorIdeMES25,  				//form MESSAGE-Group=Header
		HEAHEA351, 			HEAHEA,
		GOOITEGDS351,		GOOITEGDS,
		TRAREP351,			TRAREP,
		PERLODSUMDEC,
		CUSOFFFENT730351,	CUSOFFFENT730,
		TRACARENT601351,	TRACARENT601,
		CUSINT632351,		CUSINT632;
  
	}
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECC351A) tag) {			
			case HEAHEA351:
			case HEAHEA:
				heahea = new Heahea351(getScanner());
				heahea.parse(tag.name());
				break;
			case GOOITEGDS351:
			case GOOITEGDS:
				goodsItem = new Gooitegds(getScanner());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;	
			case TRAREP351:
			case TRAREP:
				trarep	= new CyprusAddress(getScanner());
				trarep.parse(tag.name());
				break;
			case PERLODSUMDEC:
				perlodsumdec	= new CyprusAddress(getScanner());
				perlodsumdec.parse(tag.name());
				break;
			case CUSOFFFENT730351:
			case CUSOFFFENT730:
				cusoffent	= new Cusofffent(getScanner());
				cusoffent.parse(tag.name());
				break;
			case TRACARENT601351:
			case TRACARENT601:
				tracarent	= new CyprusAddress(getScanner());
				tracarent.parse(tag.name());
				break;
			case CUSINT632351:
			case CUSINT632:
				cusint	= new Cusint(getScanner());
				cusint.parse(tag.name());
				addCusIntList(cusint);
				break;
			default:
					return;
			}
		} else {
			switch ((ECC351A) tag) {			
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
			return ECC351A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	private void addGoodsItemList(Gooitegds goodsItem) {
		if (goodsItemList == null) {
            goodsItemList = new Vector<Gooitegds>();
		}
		goodsItemList.add(goodsItem);
	}
	
	private void addCusIntList(Cusint cusint) {
		if (cusintList == null) {
			cusintList = new Vector<Cusint>();
		}
		cusintList.add(cusint);
	}


	public Heahea351 getHeahea() {
		return heahea;
	}

	public void setHeahea(Heahea351 heahea351) {
		this.heahea = heahea351;
	}

	
	public List<Gooitegds> getGooIteGdsList() {
		return goodsItemList;
	}

	public void setGooIteGdsList(List<Gooitegds> goodsItemList) {
		this.goodsItemList = goodsItemList;
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

	public Cusofffent getCusoffent() {
		return cusoffent;
	}

	public void setCusoffent(Cusofffent cusoffent) {
		this.cusoffent = cusoffent;
	}

	public CyprusAddress getTracarent() {
		return tracarent;
	}

	public void setTracarent(CyprusAddress tracarent) {
		this.tracarent = tracarent;
	}

	public Cusint getCusint() {
		return cusint;
	}

	public void setCusint(Cusint cusint) {
		this.cusint = cusint;
	}

	public List<Cusint> getCusintList() {
		return cusintList;
	}

	public void setCusintList(List<Cusint> cusintList) {
		this.cusintList = cusintList;
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}
}
