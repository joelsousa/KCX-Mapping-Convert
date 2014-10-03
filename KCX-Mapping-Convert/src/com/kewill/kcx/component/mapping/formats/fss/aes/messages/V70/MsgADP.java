package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Vector;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsABF;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAMP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAE;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAEZ;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAND;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsANM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsASP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAWE;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 28.07.2012<br>
 * Description	: Einlesen einer FSS-ADP Nachricht == Kids-ReverseDeclaration == Uids-ExportRelease.
 * 				: ZABIS V70
 * 
 * @author  iwaniuk
 * @version 2.1.00
 */

public class MsgADP {

	private boolean isPosition = false;
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;     //EI20121128
	private TsDAT   datSubset;   
	private TsEDA   edaSubset;
	private TsDAE   daeSubset;
	private TsASP   aspSubset; 
	private TsAMP   ampSubset;
	private PDFInformation pdfInformation;	
	private TsSAS   sasSubset;      	  
	private List    <TsABF>abfList;	
	private TsATK   atkSubset;		
	private List    <TsADR>adrList;  //ausfuehrer, anmelder, vertreter, subunternehmer, empfaenger, endverwender	
	private List    <TsAPN>apnList;  //contact data for ADR nwe for V21
	private TsEAM   eamSubset;  
	//new for V21 begin
	private TsAPV        apvSubset;
	private List <TsAWE> aweList;	
	private List <TsANM> anmList;	
	private List <TsAEZ> aezList;
	private TsAND        andSubset;
	//new for V21 end
	private List    <TsAVS>avsList;	
	private List    <TsAED>aedList; 	
	private List   <MsgADPPos>posList;		

	public MsgADP() {
		super();
		vorSubset = new TsVOR(""); 
		headSubset = new TsHead("");  //EI20121204
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
	
	public PDFInformation getPdfInformation() {
		return pdfInformation;
	}
	public void setPdfInformation(PDFInformation pdfInformation) {
		this.pdfInformation = pdfInformation;
	}
	
	public TsDAT getDatSubset() {
		return datSubset;
	}
	public void setDatSubset(TsDAT dat) {
		this.datSubset = dat;
	}
	
	public TsEDA getEdaSubset() {
		return edaSubset;
	}
	public void setEdaSubset(TsEDA eda) {
		this.edaSubset = eda;
	}
	public TsDAE getDaeSubset() {
		return daeSubset;
	}
	public void setDaeSubset(TsDAE dae) {
		this.daeSubset = dae;
	}	
	public TsASP getAspSubset() {
		return aspSubset;
	}
	public void setAspSubset(TsASP asp) {
		this.aspSubset = asp;
	}	
	public TsAMP getAmpSubset() {
		return ampSubset;
	}
	public void setAmpSubset(TsAMP amp) {
		this.ampSubset = amp;
	}

	public List<TsADR> getAdrList() {
		return adrList;
	}	
	public void setAdrList(List<TsADR> list) {
		this.adrList = list;
	}
	public void addAdrList(TsADR adr) {
		if (adrList == null) {
			adrList = new Vector<TsADR>();
		}
		this.adrList.add(adr);
	}
	
	public List<TsAPN> getApnList() {
		return apnList;
	}	
	public void setApnList(List<TsAPN> list) {
		this.apnList = list;
	}
	public void addApnList(TsAPN adr) {
		if (apnList == null) {
			apnList = new Vector<TsAPN>();
		}
		this.apnList.add(adr);
	}
	
	public TsEAM getEamSubset() {
		return eamSubset;
	}
	public void setEamSubset(TsEAM eam) {
		this.eamSubset = eam;
	}
	public List<TsAVS> getAvsList() {
		return avsList;
	}	
	public void addAvsList(TsAVS avs) {
		this.avsList.add(avs);
	}	
	public TsSAS getSasSubset() {
		return sasSubset;
	}
	public void setSasSubset(TsSAS sas) {
		this.sasSubset = sas;
	}
	public List<TsABF> getAbfList() {
		return abfList;
	}
	public void addAbfList(TsABF abf) {
		this.abfList.add(abf);
	}
	public TsATK getAtkSubset() {
		return atkSubset;
	}	
	public void setAtkSubset(TsATK atk) {
		this.atkSubset = atk;
	}		
	
	public List<TsAED> getAedList() {
		return aedList;
	}
	public void setAedList(List<TsAED> argument) {
		this.aedList = argument;
	}
	
	public void setApvSubset(TsAPV tsApn) {
		this.apvSubset = tsApn;
	}
	public TsAPV getApvSubset() {
		return apvSubset;
	}
	
	public List<TsAWE> getAweList() {
		return aweList;	
	}
	public void setAweList(List<TsAWE> list) {
		this.aweList = list;
	}
	public void addAweList(TsAWE awe) {
		if (awe == null) {
		    return;
		}
		if (aweList == null) {
			aweList = new Vector<TsAWE>();			
		}
		this.aweList.add(awe);
	}
		
	public List<TsANM> getAnmList() {
		return anmList;	
	}
	public void setAnmList(List<TsANM> list) {
		this.anmList = list;
	}
	public void addAnmList(TsANM anm) {
		if (anm == null) {
		    return;
		}
		if (anmList == null) {
			anmList = new Vector<TsANM>();			
		}
		this.anmList.add(anm);
	}

	public List<TsAEZ> getAezList() {
		return aezList;	
	}
	public void setAezList(List<TsAEZ> list) {
		this.aezList = list;
	}
	public void addAezList(TsAEZ aez) {
		if (aez == null) {
		    return;
		}
		if (aezList == null) {
			aezList = new Vector<TsAEZ>();			
		}
		this.aezList.add(aez);
	}			
	   
	public void setAndSubset(TsAND tsAnd) {
		this.andSubset = tsAnd;
	}
	public TsAND getAndSubset() {
		return andSubset;
	} 	
	
	//public MsgADPos[] getPosList() {
	public List<MsgADPPos> getPosList() {
		return posList;
	}	
	
	/**
	 * Nachrichtendatei einlesen und Inhalt in Teilsatzklassen speichern.
	 * 
	 * @param message  Reader aus dem die Nachrichtendatei eingelesen werden kann.
	 * @throws FssException Wenn ein unbekannter Zeilentyp gelesen wurde.
	 */
	public void readMessage(BufferedReader message) throws FssException {
		MsgADPPos adpPos = null;	
		
        try {
            String line = "";
            byte[] out = null;

            while ((line = message.readLine()) != null) {
            	String lineType = "";
            	if (line.length() > 3) {
            		lineType = line.substring(0, 3);
            	}
            	
            	if (Config.getLogXML()) {
                    Utils.log("(MsgADP readMessage) linetype " + lineType);
            	}
                if (lineType.equalsIgnoreCase("DAT")) {
                	datSubset = new TsDAT();
            		adrList = new Vector<TsADR>();
            		avsList = new Vector<TsAVS>();
            		posList = new Vector<MsgADPPos>();
            		abfList = new Vector<TsABF>();
            		aedList = new Vector<TsAED>();  //EI20090930
            		aweList = new Vector<TsAWE>();  //EI20121005
            		anmList = new Vector<TsANM>();  //EI20121005
            		aezList = new Vector<TsAEZ>();  //EI20121005
            		apnList = new Vector<TsAPN>();  //EI20121005
            		
                    String[] dat = line.split("" + FssUtils.FSS_FS);
                    datSubset.setFields(dat);
                    isPosition = false;                                                           
                } else if (lineType.equalsIgnoreCase("EDA")) {
                    edaSubset = new TsEDA();
                    String[] eda = line.split("" + FssUtils.FSS_FS);
                    edaSubset.setFields(eda);
                } else if (lineType.equalsIgnoreCase("DAE")) {
                    daeSubset = new TsDAE();
                    String[] dae = line.split("" + FssUtils.FSS_FS);
                    daeSubset.setFields(dae);
                } else if (lineType.equalsIgnoreCase("ASP")) {
                    aspSubset = new TsASP();
                    String[] asp = line.split("" + FssUtils.FSS_FS);
                    aspSubset.setFields(asp);
                } else if (lineType.equalsIgnoreCase("AMP")) {
                    ampSubset = new TsAMP();
                    String[] amp = line.split("" + FssUtils.FSS_FS);
                    ampSubset.setFields(amp);   
                    pdfInformation = new PDFInformation();
                    pdfInformation.setName(ampSubset.getPdfnam());
                    pdfInformation.setDirectory(ampSubset.getPdfpfd());
                    pdfInformation.setNewDirectory(ampSubset.getSubdir());
                } else if (lineType.equalsIgnoreCase("SAS")) {
                    sasSubset = new TsSAS();
                    String[] sas = line.split("" + FssUtils.FSS_FS);
                    sasSubset.setFields(sas);   
                } else if (lineType.equalsIgnoreCase("ABF")) {
                	TsABF abfSubset = new TsABF();
                    String[] abf = line.split("" + FssUtils.FSS_FS);
                    abfSubset.setFields(abf); 
                    abfList.add(abfSubset);
                } else if (lineType.equalsIgnoreCase("ATK")) {
                    atkSubset = new TsATK();
                    String[] atk = line.split("" + FssUtils.FSS_FS);
                    atkSubset.setFields(atk);                   
                } else if (lineType.equalsIgnoreCase("ADR") && !isPosition) {
                	TsADR adrSubset = new TsADR();
                    String[] adr = line.split("" + FssUtils.FSS_FS);
                    adrSubset.setFields(adr); 
                    adrList.add(adrSubset);                         
                } else if (lineType.equalsIgnoreCase("APN") && !isPosition) {
                	TsAPN apnSubset = new TsAPN();
                    String[] apn = line.split("" + FssUtils.FSS_FS);
                    apnSubset.setFields(apn); 
                    addApnList(apnSubset);    
                } else if (lineType.equalsIgnoreCase("EAM")) {
                    eamSubset = new TsEAM();
                    String[] aem = line.split("" + FssUtils.FSS_FS);
                    eamSubset.setFields(aem); 
                } else if (lineType.equalsIgnoreCase("APV")) {
                    apvSubset = new TsAPV();
                    String[] apv = line.split("" + FssUtils.FSS_FS);
                    apvSubset.setFields(apv);  
                } else if (lineType.equalsIgnoreCase("AWE")) {
                	TsAWE aweSubset = new TsAWE();
                    String[] awe = line.split("" + FssUtils.FSS_FS);
                    aweSubset.setFields(awe); 
                    addAweList(aweSubset);   
                } else if (lineType.equalsIgnoreCase("ANM")) {
                	TsANM anmSubset = new TsANM();
                    String[] anm = line.split("" + FssUtils.FSS_FS);
                    anmSubset.setFields(anm); 
                    addAnmList(anmSubset);   
                } else if (lineType.equalsIgnoreCase("AEZ")) {
                	TsAEZ aezSubset = new TsAEZ();
                    String[] aez = line.split("" + FssUtils.FSS_FS);
                    aezSubset.setFields(aez); 
                    addAezList(aezSubset);      
                } else if (lineType.equalsIgnoreCase("AND")) {
                    andSubset = new TsAND();
                    String[] and = line.split("" + FssUtils.FSS_FS);
                    andSubset.setFields(and);  
                } else if (lineType.equalsIgnoreCase("AVS")) {
                	TsAVS avsSubset = new TsAVS();
                    String[] avs = line.split("" + FssUtils.FSS_FS);
                    avsSubset.setFields(avs); 
                    avsList.add(avsSubset);                 
                } else if (lineType.equalsIgnoreCase("AED") && !isPosition) {                	
                	TsAED aedSubset = new TsAED();
                		String[] aed = line.split("" + FssUtils.FSS_FS);
                		aedSubset.setFields(aed);   
                		aedList.add(aedSubset);                  
                } else if (lineType.equalsIgnoreCase("APO")) {
                	isPosition = true;                 	
                	adpPos = new MsgADPPos();                	                	
                	TsAPO apoSubset = new TsAPO();
                    String[] apo = line.split("" + FssUtils.FSS_FS);
                    apoSubset.setFields(apo);                     
                    adpPos.setApoSubset(apoSubset);  
                    posList.add(adpPos);                                                  
                } else if (lineType.equalsIgnoreCase("SAP")) {
                	TsSAP sapSubset = new TsSAP();
                    String[] sap = line.split("" + FssUtils.FSS_FS);
                    sapSubset.setFields(sap);  
                    adpPos.setSapSubset(sapSubset);                  
                } else if (lineType.equalsIgnoreCase("ADR") && isPosition) {
                	TsADR adrpSubset = new TsADR();
                    String[] adrp = line.split("" + FssUtils.FSS_FS);
                    adrpSubset.setFields(adrp);  
                    adpPos.addAdrList(adrpSubset);                     
                } else if (lineType.equalsIgnoreCase("ATP")) {
                	TsATP atpSubset = new TsATP();
                    String[] atp = line.split("" + FssUtils.FSS_FS);
                    atpSubset.setFields(atp);  
                    adpPos.setAtpSubset(atpSubset);  
                } else if (lineType.equalsIgnoreCase("ATI")) {
                	TsATI atiSubset = new TsATI();
                    String[] ati = line.split("" + FssUtils.FSS_FS);
                    atiSubset.setFields(ati);  
                    adpPos.addAtiList(atiSubset);                   
                } else if (lineType.equalsIgnoreCase("ACO")) {
                	TsACO acoSubset = new TsACO();
                    String[] aco = line.split("" + FssUtils.FSS_FS);
                    acoSubset.setFields(aco); 
                    adpPos.addAcoList(acoSubset);                  
                } else if (lineType.equalsIgnoreCase("ACN")) {
                	TsACN acnSubset = new TsACN();
                    String[] acn = line.split("" + FssUtils.FSS_FS);
                    acnSubset.setFields(acn);  
                    adpPos.addAcnList(acnSubset);   
                } else if (lineType.equalsIgnoreCase("AZV")) {
                	TsAZV azvSubset = new TsAZV();
                    String[] azv = line.split("" + FssUtils.FSS_FS);
                    azvSubset.setFields(azv);  
                    adpPos.addAzvList(azvSubset);                   
                } else if (lineType.equalsIgnoreCase("BZL")) {
                	TsBZL bzlSubset = new TsBZL();
                    String[] bzl = line.split("" + FssUtils.FSS_FS);
                    bzlSubset.setFields(bzl); 
                    adpPos.addBzlList(bzlSubset);   
                } else if (lineType.equalsIgnoreCase("BAV")) {
                	TsBAV bavSubset = new TsBAV();
                    String[] bav = line.split("" + FssUtils.FSS_FS);
                    bavSubset.setFields(bav);  
                    adpPos.addBavList(bavSubset);                    
                } else if (lineType.equalsIgnoreCase("AED") && isPosition) {
                	TsAED aedpSubset = new TsAED();
                    String[] aedp = line.split("" + FssUtils.FSS_FS);
                    aedpSubset.setFields(aedp); 
                    adpPos.addAedList(aedpSubset); 
                } else if (lineType.equalsIgnoreCase("NVE")) {
                	Utils.log("FSStoKIDS MsgADP: NVE subset will not be created for V60");
                } else if (lineType.equalsIgnoreCase("NAC")) {   
                	String nac = "NAC";                          // Nachlaufsatz NAC nicht verarbeiten                	
                } else if (lineType.equalsIgnoreCase("%PD")) {   // PDF einlesen               	
                	// diese Zeile in einen neuen StringBuffer schieben
                	// danach alle weiteren Zeilen einlesen
                	StringBuffer buff = new StringBuffer();
                	buff.append(line + Utils.LF);
                	
                	while ((line = message.readLine()) != null) {
                		buff.append(line + Utils.LF);
                	}
                	out = Base64.encodeBase64Chunked(buff.toString().getBytes());
            	    String str = new String(out);
            	    BufferedReader br = new BufferedReader(new StringReader(str));
            	    String line76 = null;
            	    //AK20090513
            	    if (pdfInformation == null) {
            	    	pdfInformation = new PDFInformation();
            	    }
            		while ((line76 = br.readLine()) != null)  {
                        if (Config.getLogXML()) {
                            System.out.println(line76); 
                        }
            		   	
            		   	pdfInformation.getPdflist().add(line76);
            	    }
                } else if (lineType.equalsIgnoreCase("")) {
                	 //falls es eine leere Zeile am Ende kommt
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}


