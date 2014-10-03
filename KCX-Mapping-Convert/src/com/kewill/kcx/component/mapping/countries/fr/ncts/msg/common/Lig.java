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
 * Description	: Common class for MsgENV: LIG = GoodsItem
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Lig extends KCXMessage {

	private String noa;	
	private String tyd;
	private String pex;	
	private String pde;
	private FrParty iexp;
	private FrParty ides;
	private Ico ico;
	private ArrayList<Cdt> cdtList;
	private ArrayList<Ctr> ctrList;
	private Caution caution;
	private ArrayList<FrDocument> idanList;
	private ArrayList<FrDocument> idprList;
	private ArrayList<Cps> cpsList;
	private ArrayList<Msp> mspList;	
	private ArrayList<Security> securityList;
	
	public Lig() {
      	super();
	}

	public Lig(XMLEventReader parser) {
		super(parser);
	}      

	public Lig(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ELig {
		NOA,
		TYD,   
		PEX,
		PDE,		
		IEXP, //Opérateur Expéditeur
		IDES, //Opérateur Destinataire
		ICO,   
		CDT,  //Opérateur Principal obligé
		CTR,  //Representant
		CAUTION,
		IDAN,
		IDPR,
		CPS,
		MSP,
		SECURITY;	
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ELig) tag) {		
			case IEXP:
				iexp = new FrParty(getScanner());  	
				iexp.parse(tag.name());
				break;
			case IDES:
				ides = new FrParty(getScanner());  	
				ides.parse(tag.name());
				break;
			case ICO:
				ico = new Ico(getScanner());  	
				ico.parse(tag.name());
				break;
			case CDT:
				Cdt cdt = new Cdt(getScanner());  	
				cdt.parse(tag.name());
				addCdtList(cdt);
				break;
			case CTR:
				Ctr ctr = new Ctr(getScanner());  	
				ctr.parse(tag.name());
				addCtrList(ctr);
				break;
			case CAUTION:
				caution = new Caution(getScanner());  	
				caution.parse(tag.name());
				break;
			case IDAN:
				FrDocument idan = new FrDocument(getScanner());  	
				idan.parse(tag.name());
				addIdanList(idan);
				break;
			case IDPR:
				FrDocument idpr = new FrDocument(getScanner());  	
				idpr.parse(tag.name());
				addIdprList(idpr);
				break;
			case CPS:
				Cps cps = new Cps(getScanner());  	
				cps.parse(tag.name());
				addCpsList(cps);
				break;
			case MSP:
				Msp msp = new Msp(getScanner());  	
				msp.parse(tag.name());
				addMspList(msp);
				break;
			case SECURITY:
				Security security = new Security(getScanner());  	
				security.parse(tag.name());
				addSecurityList(security);
				break;
			
		default:
				return;
		}
	  } else {
		switch ((ELig) tag) {
		case TYD:
			setTyd(value);
			break;
		case NOA:
			setNoa(value);
			break;
		case PEX:
			setPex(value);
			break;
		case PDE:
			setPde(value);
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
			return ELig.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getNoa() {
		return noa;
	}
	public void setNoa(String value) {
		this.noa = value;
	}
	
	public String getTyd() {
		return tyd;
	}
	public void setTyd(String value) {
		this.tyd = value;
	}
	
	public String getPex() {
		return pex;
	}
	public void setPex(String value) {
		this.pex = value;
	}

	public String getPde() {
		return pde;
	}
	public void setPde(String value) {
		this.pde = value;
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

	public Ico getIco() {
		return ico;
	}
	public void setIco(Ico ico) {
		this.ico = ico;
	}

	public ArrayList<Cdt> getCdtList() {
		return cdtList;
	}
	public void setCdtList(ArrayList<Cdt> list) {
		this.cdtList = list;
	}
	public void addCdtList(Cdt cdt) {
		if (cdtList == null) {
			cdtList = new ArrayList<Cdt>();
		}
		this.cdtList.add(cdt);
	}
	
	public ArrayList<Ctr> getCtrList() {
		return ctrList;
	}
	public void setCtrList(ArrayList<Ctr> ctrList) {
		this.ctrList = ctrList;
	}
	public void addCtrList(Ctr ctr) {
		if (ctrList == null) {
			ctrList = new ArrayList<Ctr>();
		}
		this.ctrList.add(ctr);
	}

	public Caution getCaution() {
		return caution;
	}
	public void setCaution(Caution caution) {
		this.caution = caution;
	}

	public ArrayList<FrDocument> getIdanList() {
		return idanList;
	}
	public void setIdanList(ArrayList<FrDocument> idanList) {
		this.idanList = idanList;
	}
	public void addIdanList(FrDocument idan) {
		if (idanList == null) {
			idanList = new ArrayList<FrDocument>();
		}
		this.idanList.add(idan);
	}
	
	public ArrayList<FrDocument> getIdprList() {
		return idprList;
	}
	public void setIdprList(ArrayList<FrDocument> list) {
		this.idprList = list;
	}
	public void addIdprList(FrDocument idpr) {
		if (idprList == null) {
			idprList = new ArrayList<FrDocument>();
		}
		this.idprList.add(idpr);
	}
	
	public ArrayList<Cps> getCpsList() {
		return cpsList;
	}
	public void setCpsList(ArrayList<Cps> list) {
		this.cpsList = list;
	}
	public void addCpsList(Cps cps) {
		if (cpsList == null) {
			cpsList = new ArrayList<Cps>();
		}
		this.cpsList.add(cps);
	}
	
	public ArrayList<Msp> getMspList() {
		return mspList;
	}
	public void setMspList(ArrayList<Msp> msp) {
		this.mspList = msp;
	}
	public void addMspList(Msp msp) {
		if (mspList == null) {
			mspList = new ArrayList<Msp>();
		}
		this.mspList.add(msp);
	}
	
	public ArrayList<Security> getSecurityList() {
		return securityList;
	}

	public void setSecurityList(ArrayList<Security> securityList) {
		this.securityList = securityList;
	}

	public void addSecurityList(Security sec) {
		if (securityList == null) {
			securityList = new ArrayList<Security>();
		}
		securityList.add(sec);
	}

	public boolean isEmpty() {
		return 	Utils.isStringEmpty(noa) &&				
				Utils.isStringEmpty(tyd) &&
				//TODO
				(caution == null);
						
	}	

}
