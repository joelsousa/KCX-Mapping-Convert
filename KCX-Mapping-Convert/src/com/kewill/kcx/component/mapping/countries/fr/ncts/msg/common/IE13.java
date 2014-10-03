package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: IE13 = NCTSDeclaration
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class IE13 extends KCXMessage {

	private Ademar ademar;	
	private String tyd;
	private String lrn;	
	private String tirId;
	private Loc loc;
	private String lid;
	private String pae;
	private String pad;
	private String bud;
	private Gbp gbp;
	private String bue;
	private String dtd;
	private String ctn;
	private String ctl;
	private ArrayList<Gar> garList;
	private It itd;
	private It itf;
	private FrParty iexp;
	private FrParty ides;
	private FrParty ipo;
	private FrParty ire;
	private String desa;
	private Itot itot;
	private Scl scl;
	private Security security;
	private ArrayList<Lig> ligList;
	
	public IE13() {
      	super();
	}

	public IE13(XMLEventReader parser) {
		super(parser);
	}      

	public IE13(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EIE13 {
		ADEMAR,
		TYD,   
		LRN,
		TIR_ID,
		LOC,   //CHGT,
		LID,
		PAE,
		PAD,
		BUD,
		GBP,  //COD-list
		BUE,
		DTD,
		CTN,
		CTL,
		GAR,   //TYP, IGA
		CPA,  //???
		ITD,  //MOD, IDT, NAT
		ITF, //MOD, TYP, IDT, NAT
		IEXP, //Opérateur Expéditeur
		IDES, //Opérateur Destinataire
		DESA,   
		IPO,  //Opérateur Principal obligé
		IRE,  //Representant
		ITOT,
		SCL,
		SECURITY,
		LIG;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EIE13) tag) {
			case ADEMAR:
				ademar = new Ademar(getScanner());  	
				ademar.parse(tag.name());
				break;
			case LOC:
				loc = new Loc(getScanner());  	
				loc.parse(tag.name());
				break;
			case GBP:
				gbp = new Gbp(getScanner());  	
				gbp.parse(tag.name());
				break;
			case GAR:
				Gar gar = new Gar(getScanner());  	
				gar.parse(tag.name());
				addGarList(gar);
				break;
			case ITD:
				itd = new It(getScanner());  	
				itd.parse(tag.name());
				break;
			case ITF:
				itf = new It(getScanner());  	
				itf.parse(tag.name());
				break;
			case IEXP:
				iexp = new FrParty(getScanner());  	
				iexp.parse(tag.name());
				break;
			case IDES:
				ides = new FrParty(getScanner());  	
				ides.parse(tag.name());
				break;
			case IPO:
				ipo = new FrParty(getScanner());  	
				ipo.parse(tag.name());
				break;
			case IRE:
				ire = new FrParty(getScanner());  	
				ire.parse(tag.name());
				break;
			case ITOT:
				itot = new Itot(getScanner());  	
				itot.parse(tag.name());
				break;
			case SCL:
				scl = new Scl(getScanner());  	
				scl.parse(tag.name());
				break;
			case SECURITY:
				security = new Security(getScanner());  	
				security.parse(tag.name());
				break;
			case LIG:
				Lig lig = new Lig(getScanner());  	
				lig.parse(tag.name());
				addLigList(lig);
				break;
		default:
				return;
		}
	  } else {
		switch ((EIE13) tag) {
		case TYD:
			setTyd(value);
			break;
		case LRN:
			setLrn(value);
			break;
		case TIR_ID:
			setTirId(value);
			break;
		case LID:
			setLid(value);
			break;
		case PAE:
			setPae(value);
			break;
		case PAD:
			setPad(value);
			break;
		case BUD:
			setBud(value);
			break;
		case BUE:
			setBue(value);
			break;		
		case DTD:
			setDtd(value);
			break;
		case CTN:
			setCtn(value);
			break;
		case CTL:
			setCtl(value);
			break;
		case DESA:
			setDesa(value);
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

	public Enum translate(String token) {
		try {
			return EIE13.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public Ademar getAdemar() {
		return ademar;
	}

	public void setAdemar(Ademar ademar) {
		this.ademar = ademar;
	}

	public String getTyd() {
		return tyd;
	}

	public void setTyd(String tyd) {
		this.tyd = tyd;
	}

	public String getLrn() {
		return lrn;
	}

	public void setLrn(String lrn) {
		this.lrn = lrn;
	}

	public String getTirId() {
		return tirId;
	}
	public void setTirId(String triId) {
		this.tirId = triId;
	}

	public Loc getLoc() {
		return loc;
	}
	public void setLoc(Loc loc) {
		this.loc = loc;
	}
	
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getPae() {
		return pae;
	}
	public void setPae(String pea) {
		this.pae = pea;
	}

	public String getPad() {
		return pad;
	}
	public void setPad(String pad) {
		this.pad = pad;
	}

	public String getBud() {
		return bud;
	}
	public void setBud(String bud) {
		this.bud = bud;
	}

	public Gbp getGbp() {
		return gbp;
	}
	public void setGbp(Gbp gbp) {
		this.gbp = gbp;
	}
	
	public String getBue() {
		return bue;
	}
	public void setBue(String bue) {
		this.bue = bue;
	}
	
	public String getDtd() {
		return dtd;
	}
	public void setDtd(String dtd) {
		this.dtd = dtd;
	}

	public String getCtl() {
		return ctl;
	}
	public void setCtl(String cln) {
		this.ctl = cln;
	}

	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}

	public ArrayList<Gar> getGarList() {
		return garList;
	}
	public void setGarList(ArrayList<Gar> garList) {
		this.garList = garList;
	}
	public void addGarList(Gar gar) {
		if (this.garList == null) {
			this.garList = new ArrayList<Gar>();
		}
		this.garList.add(gar);
	}

	public It getItd() {
		return itd;
	}
	public void setItd(It itd) {
		this.itd = itd;
	}

	public It getItf() {
		return itf;
	}
	public void setItf(It itf) {
		this.itf = itf;
	}

	public FrParty getIexp() {
		return iexp;
	}
	public void setIexp(FrParty iexp) {
		this.iexp = iexp;
	}

	public FrParty getIdes() {
		return ides;
	}
	public void setIdes(FrParty ides) {
		this.ides = ides;
	}

	public FrParty getIpo() {
		return ipo;
	}
	public void setIpo(FrParty ipo) {
		this.ipo = ipo;
	}

	public FrParty getIre() {
		return ire;
	}
	public void setIre(FrParty ire) {
		this.ire = ire;
	}

	public Itot getItot() {
		return itot;
	}
	public void setItot(Itot itot) {
		this.itot = itot;
	}

	public Scl getScl() {
		return scl;
	}
	public void setScl(Scl scl) {
		this.scl = scl;
	}

	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	
	public String getDesa() {
		return desa;
	}
	public void setDesa(String desa) {
		this.desa = desa;
	}

	public ArrayList<Lig> getLigList() {
		return ligList;
	}
	public void setLigList(ArrayList<Lig> ligList) {
		this.ligList = ligList;
	}	
	public void addLigList(Lig lig) {
		if (this.ligList == null) {
			this.ligList = new ArrayList<Lig>();
		}
		this.ligList.add(lig);
	}

	public boolean isEmpty() {
		return 	(ademar == null || ademar.isEmpty()) && (loc == null || loc.isEmpty()) &&				
				(gbp == null || gbp.isEmpty()) && (itot == null || itot.isEmpty()) && 			
				(itd == null || itd.isEmpty()) && (itf == null || itf.isEmpty()) &&
				(iexp == null || iexp.isEmpty()) && (ides == null || ides.isEmpty()) &&
				(ipo == null || ipo.isEmpty()) && (ire == null || ire.isEmpty()) &&
				(scl == null || scl.isEmpty()) && (security == null || security.isEmpty()) &&
				Utils.isStringEmpty(tyd) &&				
				Utils.isStringEmpty(lrn) &&
				//TODO
				(garList == null || garList.isEmpty()) &&	
				(ligList == null || ligList.isEmpty());	
	}	

}
