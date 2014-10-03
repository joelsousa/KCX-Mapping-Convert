/*
 * Funktion    : MsgADP.java
 * Date    : 05.09.2008
 * Author      : Elisabeth Iwaniuk
 * Description:
* -----------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 06.03.2009
 * Label       : EI20090306
 * Description : new Member: aedList
 * 
 * Author      : krzoska
 * Date        : 13.05.2009
 * Label       : AK20090513
 * Description : new Member: aedList
 * 
 * 
 * 
 * 
 *             	    
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

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
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAMP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAE;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgADP<br>
 * Erstellt		: 08.03.2010<br>
 * Beschreibung	: Einlesen einer FSS-ADP Nachricht.
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */
public class MsgADP {

	private boolean isPosition = false;
	
	private TsVOR	vorSubset;
	private TsDAT   datSubset;   
	private TsEDA   edaSubset;
	private TsDAE   daeSubset;
	private TsASP   aspSubset; 
	private TsAMP   ampSubset;
	private PDFInformation pdfInformation;	// zum Abspeichern einer PDF-Datei falls vorhanden
	private TsSAS   sasSubset;      //erst ab Version 6
	private TsABF   abfSubset; 	    //erst ab Version 6
	private List    <TsABF>abfList;	//erst ab Version 6
	private TsATK   atkSubset;		//erst ab Version 6
	private TsADR   adrSubset;
	private List    <TsADR>adrList;
	private TsEAM   eamSubset;   
	private TsAVS   avsSubset;
	private List    <TsAVS>avsList;
	private TsAED   aedSubset;		//erst ab Version 6
	private List    <TsAED>aedList; //V60 kann bis 99 mall vorkommen
	
	private List   <MsgADPos>posList;
	//private MsgADPos[]  posList;
	private MsgADPos adpPos;	
	private int iPosList;
		
	private TsAPO   apoSubset;  
	private TsSAP   sapSubset;		//erst ab Version 6
	private TsADR   adrpSubset;  	//erst ab Version 6
	private TsATP   atpSubset;   	//erst ab Version 6
	private TsATI   atiSubset;   	//erst ab Version 6
	private TsACO   acoSubset;  
	private TsACN   acnSubset;    
	private TsAZV   azvSubset;   
	private TsBZL   bzlSubset;   	//erst ab Version 6
	private TsBAV   bavSubset;   	//erst ab Version 6 
	private TsAED   aedpSubset;  
	
	public MsgADP() {
		super();
		vorSubset = new TsVOR(""); 
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
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
	public void addAdrList(TsADR adr) {
		this.adrList.add(adr);
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
	public TsAED getAedSubset() {
		return aedSubset;
	}
	public void setAedSubset(TsAED aed) {
		this.aedSubset = aed;
	}
	
	public List<TsAED> getAedList() {
		return aedList;
	}
	public void setAedList(List<TsAED> argument) {
		this.aedList = argument;
	}
	
	//public MsgADPos[] getPosList() {
	public List<MsgADPos> getPosList() {
		return posList;
	}	

	public int getPosListLength() {
		return iPosList;
	}
	
	/**
	 * Nachrichtendatei einlesen und Inhalt in Teilsatzklassen speichern.
	 * 
	 * @param message  Reader aus dem die Nachrichtendatei eingelesen werden kann.
	 * @throws FssException Wenn ein unbekannter Zeilentyp gelesen wurde.
	 */
	public void readMessage(BufferedReader message) throws FssException {
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
            		posList = new Vector<MsgADPos>();
            		abfList = new Vector<TsABF>();
            		aedList = new Vector<TsAED>();  //EI20090930
                    String[] dat = line.split("" + FssUtils.FSS_FS);
                    datSubset.setFields(dat);
                    isPosition = false;                                       
                    iPosList = 0;
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
                    abfSubset = new TsABF();
                    String[] abf = line.split("" + FssUtils.FSS_FS);
                    abfSubset.setFields(abf); 
                    abfList.add(abfSubset);
                } else if (lineType.equalsIgnoreCase("ATK")) {
                    atkSubset = new TsATK();
                    String[] atk = line.split("" + FssUtils.FSS_FS);
                    atkSubset.setFields(atk);                   
                } else if (lineType.equalsIgnoreCase("ADR") && !isPosition) {
                    adrSubset = new TsADR();
                    String[] adr = line.split("" + FssUtils.FSS_FS);
                    adrSubset.setFields(adr); 
                    adrList.add(adrSubset);                    
                } else if (lineType.equalsIgnoreCase("EAM")) {
                    eamSubset = new TsEAM();
                    String[] aem = line.split("" + FssUtils.FSS_FS);
                    eamSubset.setFields(aem);  
                } else if (lineType.equalsIgnoreCase("AVS")) {
                    avsSubset = new TsAVS();
                    String[] avs = line.split("" + FssUtils.FSS_FS);
                    avsSubset.setFields(avs); 
                    avsList.add(avsSubset);                 
                } else if (lineType.equalsIgnoreCase("AED") && !isPosition) {                	
                		aedSubset = new TsAED();
                		String[] aed = line.split("" + FssUtils.FSS_FS);
                		aedSubset.setFields(aed);   
                		aedList.add(aedSubset);                   //EI20090306
                } else if (lineType.equalsIgnoreCase("APO")) {
                	isPosition = true;                 	
                	adpPos = new MsgADPos();                	                	
                    apoSubset = new TsAPO();
                    String[] apo = line.split("" + FssUtils.FSS_FS);
                    apoSubset.setFields(apo);                     
                    adpPos.setApoSubset(apoSubset);  
                    posList.add(adpPos);
                    //posList[iPosList] = adpPos;
                    iPosList = iPosList + 1;                
                } else if (lineType.equalsIgnoreCase("SAP")) {
                	sapSubset = new TsSAP();
                    String[] sap = line.split("" + FssUtils.FSS_FS);
                    sapSubset.setFields(sap);  
                    adpPos.setSapSubset(sapSubset);                  
                } else if (lineType.equalsIgnoreCase("ADR") && isPosition) {
                    adrpSubset = new TsADR();
                    String[] adrp = line.split("" + FssUtils.FSS_FS);
                    adrpSubset.setFields(adrp);  
                    adpPos.addAdrList(adrpSubset);                     
                } else if (lineType.equalsIgnoreCase("ATP")) {
                    atpSubset = new TsATP();
                    String[] atp = line.split("" + FssUtils.FSS_FS);
                    atpSubset.setFields(atp);  
                    adpPos.setAtpSubset(atpSubset);  
                } else if (lineType.equalsIgnoreCase("ATI")) {
                    atiSubset = new TsATI();
                    String[] ati = line.split("" + FssUtils.FSS_FS);
                    atiSubset.setFields(ati);  
                    adpPos.addAtiList(atiSubset);                   
                } else if (lineType.equalsIgnoreCase("ACO")) {
                    acoSubset = new TsACO();
                    String[] aco = line.split("" + FssUtils.FSS_FS);
                    acoSubset.setFields(aco); 
                    adpPos.addAcoList(acoSubset);                  
                } else if (lineType.equalsIgnoreCase("ACN")) {
                    acnSubset = new TsACN();
                    String[] acn = line.split("" + FssUtils.FSS_FS);
                    acnSubset.setFields(acn);  
                    adpPos.addAcnList(acnSubset);   
                } else if (lineType.equalsIgnoreCase("AZV")) {
                    azvSubset = new TsAZV();
                    String[] azv = line.split("" + FssUtils.FSS_FS);
                    azvSubset.setFields(azv);  
                    adpPos.addAzvList(azvSubset);                   
                } else if (lineType.equalsIgnoreCase("BZL")) {
                    bzlSubset = new TsBZL();
                    String[] bzl = line.split("" + FssUtils.FSS_FS);
                    bzlSubset.setFields(bzl); 
                    adpPos.addBzlList(bzlSubset);   
                } else if (lineType.equalsIgnoreCase("BAV")) {
                    bavSubset = new TsBAV();
                    String[] bav = line.split("" + FssUtils.FSS_FS);
                    bavSubset.setFields(bav);  
                    adpPos.addBavList(bavSubset);                    
                } else if (lineType.equalsIgnoreCase("AED") && isPosition) {
                    aedpSubset = new TsAED();
                    String[] aedp = line.split("" + FssUtils.FSS_FS);
                    aedpSubset.setFields(aedp); 
                    adpPos.addAedList(aedpSubset); 
                } else if (lineType.equalsIgnoreCase("NVE")) {
                	Utils.log("FSStoKIDS MsgADP: NVE subset will not be created for V60");
                } else if (lineType.equalsIgnoreCase("NAC")) {        //AK20081117
                	// Nachlaufsatz NAC nicht verarbeiten
                } else if (lineType.equalsIgnoreCase("%PD")) {
                	
                	// PDF einlesen: diese Zeile in einen neuen StringBuffer schieben
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


