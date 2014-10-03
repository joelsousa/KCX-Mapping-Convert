package com.kewill.kcx.component.mapping.formats.fr.ncts;


import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.MsgENV;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Caution;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Cdt;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Cps;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Ctr;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Dm;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.FrDocument;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.FrParty;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Gar;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Gbp;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.IE13;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Ico;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Iga;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.It;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Itinerary;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Itot;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Lap;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Lig;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Loc;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Msp;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Scl;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;

/**
* Module  : FR-NCTS<br>
* Created : 13.11.2013<br>
* Description : Body of FR-NCTS-IE13.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class BodyIE13 extends KidsMessageNCTS {

	private MsgENV msg = null;
	private IE13 msg13 = null;

	public BodyIE13(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgENV getMessage() {
		return msg;
	}

	public void setMessage(MsgENV msg) {
		this.msg = msg;
	}

	public void writeBody() {
		try {
		openElement("ENV");
			writeElement("DATESYS", msg.getDateSys());
			writeElement("SENDER", msg.getSender());
			writeElement("RECIPIENT", msg.getRecipient());
			writeElement("TYPEMESS", msg.getTypeMes());
			writeElement("LG", msg.getLg());
			
			openElement("MES");						
			openElement("IE29");
			openElement("IE13");
			if (msg.getMes() != null && msg.getMes().getIE29() != null && msg.getMes().getIE29().getIE13() != null) {
				msg13 = msg.getMes().getIE29().getIE13();
				writeElement("TYD", msg13.getTyd());
				writeElement("LNR", msg13.getLrn());
				writeElement("TIR_ID", msg13.getTirId());			
				writeLoc(msg13.getLoc());			
				writeElement("LID", msg13.getLid());
				writeElement("PAE", msg13.getPae());
				writeElement("PAD", msg13.getPad());
				writeElement("BUD", msg13.getBud());
				writeGbp(msg13.getGbp());			
				writeElement("BUE", msg13.getBue());				
				writeElement("DTD", msg13.getDtd());
				writeElement("CTN", msg13.getCtn());
				writeElement("CTL", msg13.getCtl());												
				writeGarList(msg13.getGarList());
				writeItd(msg13.getItd());
				writeItf(msg13.getItf());
				writeFrParty(msg13.getIexp(), "IEXP", 1);
				writeFrParty(msg13.getIdes(), "IDES", 1);
				writeElement("DESA", msg13.getDesa());			
				writeFrParty(msg13.getIpo(), "IPO", 1);
				writeIre(msg13.getIre());
				writeItot(msg13.getItot());
				writeScl(msg13.getScl());
				writeSecurity(msg13.getSecurity(), 1);
			
				if (msg13.getLigList() != null) {
					for (Lig goodsItem : msg13.getLigList()) {
						writeLig(goodsItem);
					}
				}
			
			}
			closeElement();
			closeElement();
			closeElement();
		closeElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	////// GoodsItems
	
	private void writeLig(Lig argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("LIG");
			writeElement("NOA", argument.getNoa());						
			writeElement("TYD", argument.getTyd());
			writeElement("PEX", argument.getPex());  //EI20110524
			writeElement("PDE", argument.getPde());
			writeFrParty(argument.getIexp(), "IEXP", 1);
			writeFrParty(argument.getIdes(), "IDES", 1);
			writeIco(argument.getIco());
			writeCdtList(argument.getCdtList());
			writeCtrList(argument.getCtrList());
			writeCaution(argument.getCaution());
			writeIdanList(argument.getIdanList());
			writeIdprList(argument.getIdprList());
			writeCpsList(argument.getCpsList());
			writeMspList(argument.getMspList());
			writeCdtList(argument.getCdtList());
			if (argument.getSecurityList() != null) {
				for (Security se : argument.getSecurityList()) {
					writeSecurity(se, 2);
				}
			}
			
		closeElement();

	}
	
//////////////
	

	private void writeFrParty(FrParty party, String tagName, int typ) throws XMLStreamException {
		if (party == null) {
			return;
		}		
		openElement(tagName);
			if (typ == 2) {
				writeElement("TIN", party.getSir());
			} else {
				writeElement("SIR", party.getSir());
			}
			writeElement("NOM", party.getNom());
			writeElement("RUE", party.getRue());	
			writeElement("VIL", party.getVil());	
			writeElement("CDP", party.getCdp());	
			writeElement("PAY", party.getPay());
		closeElement();		
	}
	private void writeFrParty(FrParty party, String tagName) throws XMLStreamException {
		if (party == null) {
			return;
		}		
		openElement(tagName);			
			writeElement(tagName + "_TIN", party.getSir());			
			writeElement(tagName + "_NOM", party.getNom());
			writeElement(tagName + "_RUE", party.getRue());	
			writeElement(tagName + "_CDP", party.getCdp());	
			writeElement(tagName + "_VIL", party.getVil());				
			writeElement(tagName + "_PAY", party.getPay());
			writeElement(tagName + "_LNG", party.getLng());
		closeElement();		
	}
	private void writeIre(FrParty party) throws XMLStreamException {
		if (party == null) {
			return;
		}		
		openElement("IRE");			
			writeElement("REP", party.getSir());						
			writeElement("PVR", party.getPvr());			
		closeElement();		
	}
	private void writeLoc(Loc argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("LOC");
			writeElement("CHGT", argument.getChgt());
			writeElement("COD", argument.getCod());
			writeElement("MCON", argument.getMcon());	
			writeElement("MAUT", argument.getMaut());
			writeElement("BUAN", argument.getBuan());			
		closeElement();		
	}
	private void writeGbp(Gbp argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}	
		if (argument.getCodList() != null) {
		openElement("GBP");
		for (String code : argument.getCodList()) {
			writeElement("COD", code);			
		}
		closeElement();		
		}
	}
	private void writeGarList(ArrayList<Gar> list) throws XMLStreamException {
		if (list == null) {
			return;
		}		
		for (Gar element : list) {			
			if (element != null) {					
				openElement("GAR");  				
					writeElement("TYP", element.getTyp());
					if (element.getIgaList() != null) {
						for (Iga iga : element.getIgaList()) {
							writeIga(iga);	
						}
					}
				closeElement();
			}
		}
	}
	private void writeItd(It argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("ITD");
			writeElement("MOD", argument.getMod());	
			writeElement("IDT", argument.getIdt());	
			writeElement("NAT", argument.getNat());			
		closeElement();		
	}
	private void writeItf(It argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("ITF");
			writeElement("MOD", argument.getMod());	
			writeElement("TYP", argument.getTyp());	
			writeElement("IDT", argument.getIdt());	
			writeElement("NAT", argument.getNat());				
		closeElement();		
	}
		
	private void writeItot(Itot argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("ITOT");
			writeElement("NBC", argument.getNbc());	
			writeElement("NBL", argument.getNbl());	
			writeElement("NBU", argument.getNbu());	
			writeElement("PDB", argument.getPdb());	
			writeElement("CAUTION_MT", argument.getCautionMt());					
		closeElement();			
	}
	private void writeScl(Scl argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("SCL");
			writeElement("NBS", argument.getNbs());	
			if (argument.getNusList() != null) {
				for (String nus : argument.getNusList()) {
					writeElement("NUS", nus);	
				}
			}
		closeElement();		
	}
	private void writeSecurity(Security argument, int typ) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("SECURITY");
		if (typ == 1) {
			writeElement("ENABLE", argument.getEnable());
			writeElement("CIRC_SPEC_IND", argument.getCircSpecInd());	
			writeElement("MOD_PAI_TRP", argument.getModPaiTrp());	
			writeElement("CONV_REF", argument.getConvRef());	
			writeElement("COM_REF", argument.getComRef());	
			writeElement("UNLOAD_CODE", argument.getUnloadCode());	
			writeElement("UNLOAD_LNG", argument.getUnloadLng());	
			writeElement("DATE_ARR", argument.getDateArr());	
			writeItineraryList(argument.getItineraryList());
			writeFrParty(argument.getCarrier(), "CARRIER");
		} else {
			writeElement("MOD_PAI_TRP", argument.getModPaiTrp());	
			writeElement("COM_REF", argument.getComRef());	
			writeElement("Dang_CODE", argument.getDangCode());	
		}
			
			writeFrParty(argument.getConsignor(), "CONSIGNOR");
			writeFrParty(argument.getConsignee(), "CONSIGNEE");
		closeElement();		
	}
	private void writeItineraryList(ArrayList<Itinerary> list) throws XMLStreamException {
		if (list == null) {
			return;
		}	
		for (Itinerary iti : list) {
			openElement("ITINERARY");
				writeElement("ROUT_COUNTRY", iti.getCountry());				
			closeElement();		
		}
	}
	private void writeIco(Ico argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("ICO");
			writeElement("NDP", argument.getNdp());
			writeDm(argument.getDm());	
			writeElement("PDB", argument.getPdb());	
			writeElement("PDN", argument.getPdn());				
		closeElement();		
	}	
	private void writeDm(Dm argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		openElement("DM");
			writeElement("LI1", argument.getLi1());
			writeElement("LI2", argument.getLi2());
			writeElement("LI3", argument.getLi3());
			writeElement("LI4", argument.getLi4());
		closeElement();		
	}	
	private void writeCdtList(ArrayList<Cdt> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (Cdt element : list) {			
			if (element != null) {					
				openElement("CDT");  				
					writeElement("TCO", element.getTco()); 
					writeElement("NBC", element.getNbc());						
					writeElement("NBP", element.getNbp());	
					writeElement("TXT", element.getTxt());	
				closeElement();
			}
		}	
	}
	private void writeCtrList(ArrayList<Ctr> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (Ctr element : list) {			
			if (element != null) {					
				openElement("CTR");  									
					writeElement("NCT", element.getNct());					
					
				closeElement();
			}
		}
	}
	private void writeCaution(Caution argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}				
		openElement("CAUTION");  									
			writeElement("MARCH_VAL", argument.getMarchVal()); 				
				
		closeElement();			
	}
	private void writeIdanList(ArrayList<FrDocument> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (FrDocument doc : list) {			
			if (doc != null) {					
				openElement("IDAN");  									
					writeElement("TYD", doc.getTyd());	
					writeElement("REF", doc.getRef());	
					writeElement("CPL", doc.getCpl());	
				closeElement();
			}
		}	
	}
	private void writeIdprList(ArrayList<FrDocument> list) throws XMLStreamException {			
		if (list == null) {
			return;
		}
		for (FrDocument doc : list) {			
			if (doc != null) {					
				openElement("IDPR");  									
					writeElement("TYD", doc.getTyd());	
					writeElement("REF", doc.getRef());	
					writeElement("CPL", doc.getCpl());	
				closeElement();
			}
		}
	}		
	private void writeCpsList(ArrayList<Cps> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (Cps cps : list) {			
			if (cps != null) {					
				openElement("CPS");  				
					writeElement("COD", cps.getCod()); 
					writeElement("QTE", cps.getQte()); 								
				closeElement();
			}
		}	
	}
	private void writeMspList(ArrayList<Msp> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (Msp msp : list) {			
			if (msp != null) {					
				openElement("MSP");  				
					writeElement("EUE", msp.getEue()); 
					writeElement("EAP", msp.getEap()); 				
					writeElement("COD", msp.getCod()); 
					writeElement("INF", msp.getInf()); 
				closeElement();
			}
		}	
	}
	private void writeIga(Iga iga) throws XMLStreamException {
		if (iga == null) {
			return;
		}
		openElement("");  				
			writeElement("NUM", iga.getNum()); 
			writeElement("COD", iga.getCod()); 				
			writeElement("AUT", iga.getAut()); 
			writeElement("LUE", iga.getLue()); 	
			writeElement("CAUTION_MT", iga.getCautionMt()); 
			writeElement("CAUTION_DEV", iga.getCautionDev()); 				
			writeLap(iga.getLap()); 	
	closeElement();
	}
	private void writeLap(Lap lap) throws XMLStreamException {
		if (lap == null) {
			return;
		}
		if (lap.getCpaList() == null) {
			return;
		}
		openElement(""); 
		for (String cpa : lap.getCpaList()) {
			writeElement("CPA", cpa); 	
		}
		closeElement();
	}
}
