package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.FssAbgaben;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAAB;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsABU;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsABW;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsADRCTX;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHI;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAKG;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsSOF;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXA;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsUNB;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsAB1;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsAB2;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsASK;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsCPS;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsUNTCTX;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsANR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Import 70<br>
 * Created		: 12.11.2012<br>
 * Description	: MsgCTX / ImportTaxAssessment.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgCTX extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;    						 //EI20121107
	private TsAB1   ab1Subset;    // Hauptsatz 1
	private TsAB2   ab2Subset;    // Hauptsatz 2
	private TsANR   anrSubset;    // Auftragsnummern
	private List<TsASK> askList;  // Aufschubkonten 
	private List<TsTXA> txaList;  // Texte 
	private TsADRCTX   anmelder; 
	private TsADRCTX   empfaenger;
	private TsADRCTX   vertreter;   
	private TsADRCTX   dienstelle;   
	private TsADRCTX   zollstelle;   
	private TsADRCTX   rechtsbehelfsHZA;   
	private TsADRCTX   fuerRechnung;   
	//V70: private List <TsSUA> suaList;  // SumA 
	private List <TsUNB> unbList;  // Unbare Sicherheiten 
	private List <MsgCTXPos> posList;
	
	public MsgCTX() {
		super();  
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
    
	public void setAB1Subset(TsAB1 argument) {
		ab1Subset = argument;
    }
	public TsAB1 getAB1Subset() {
		return ab1Subset;
	}

	public void setAB2Subset(TsAB2 argument) {
		ab2Subset = argument;
    }
	public TsAB2 getAB2Subset() {
		return ab2Subset;
	}
	
	public void setANRSubset(TsANR argument) {
		anrSubset = argument;
    }
	public TsANR getANRSubset() {
		return anrSubset;
	}
	
	public void setASKList(List <TsASK> list) {
		askList = list;
    }
	public List <TsASK> getASKList() {
		return askList;
	}
	public void addASKList(TsASK subset) {
		if (askList == null) {
			askList = new Vector<TsASK>();	
		}
		askList.add(subset);
    }
	
	public void setTXAList(List <TsTXA> list) {
		txaList = list;
    }
	public List <TsTXA> getTXAList() {
		return txaList;
	}
	public void addTXAList(TsTXA subset) {
		if (txaList == null) {
			txaList = new Vector<TsTXA>();	
		}
		txaList.add(subset);
    }
	
	public void setAnmelder(TsADRCTX argument) {
		anmelder = argument;
    }
	public TsADRCTX getAnmelder() {
		return anmelder;
	}
	
	public void setEmpfaenger(TsADRCTX argument) {
		empfaenger = argument;
    }
	public TsADRCTX getEmpfaenger() {
		return empfaenger;
	}
		
	public void setVertreter(TsADRCTX argument) {
		vertreter = argument;
    }
	public TsADRCTX getVertreter() {
		return vertreter;
	}
	
	public void setDienstelle(TsADRCTX argument) {
		dienstelle = argument;
    }
	public TsADRCTX getDienstelle() {
		return dienstelle;
	}
	
	public void setZollstelle(TsADRCTX argument) {
		zollstelle = argument;
    }
	public TsADRCTX getZollstelle() {
		return zollstelle;
	}
	
	public void setRechtsbehelfsHZA(TsADRCTX argument) {
		rechtsbehelfsHZA = argument;
    }
	public TsADRCTX getRechtsbehelfsHZA() {
		return rechtsbehelfsHZA;
	}
	
	public void setFuerRechnung(TsADRCTX argument) {
		fuerRechnung = argument;
    }
	public TsADRCTX getFuerRechnung() {
		return fuerRechnung;
	}
	
	public void setUNBList(List <TsUNB> list) {
		unbList = list;
    }
	public List <TsUNB> getUNBList() {
		return unbList;
	}
	public void addUNBList(TsUNB subset) {
		if (unbList == null) {
			unbList = new Vector<TsUNB>();	
		}
		unbList.add(subset);
    }
	
	public void setCTXPosList(List <MsgCTXPos> list) {
		posList = list;
    }
	public List <MsgCTXPos> getCTXPosList() {
		return posList;
	}
	
	public void addCTXPosList(MsgCTXPos pos) {
		if (posList == null) {
			posList = new Vector<MsgCTXPos>();	
		}
		posList.add(pos);
	}
	
	
	
	 //z.Z. nicht benoetigt:	
	public String getFssString() throws FssException {
		String res = "";	
				
		if (vorSubset != null && !vorSubset.isEmpty()) {	 
			res = appendString(res, vorSubset.teilsatzBilden());	
		}		
		if (ab1Subset != null && !ab1Subset.isEmpty()) {		
			res = appendString(res, ab1Subset.teilsatzBilden());
		}
		if (ab2Subset != null && !ab2Subset.isEmpty()) {			
			res = appendString(res, ab2Subset.teilsatzBilden());
		}
		if (anrSubset != null && !anrSubset.isEmpty()) {			
			res = appendString(res, anrSubset.teilsatzBilden());
		}
		if (askList != null) {                     					 
			int askSize = askList.size();
			for (int j = 0; j < askSize; j++) {
				TsASK ask = (TsASK) askList.get(j);
				if (ask != null && !ask.isEmpty()) {
					res = appendString(res, ask.teilsatzBilden());
				}
			}
		}
		if (txaList != null) {                     					 
			int txaSize = txaList.size();
			for (int j = 0; j < txaSize; j++) {
				TsTXA txa = (TsTXA) txaList.get(j);
				if (txa != null && !txa.isEmpty()) {
					res = appendString(res, txa.teilsatzBilden());
				}
			}
		}		
		if (anmelder != null && !anmelder.isEmpty()) {			
			res = appendString(res, anmelder.teilsatzBilden());
		}
		if (empfaenger != null && !empfaenger.isEmpty()) {			
			res = appendString(res, empfaenger.teilsatzBilden());
		}
		if (vertreter != null && !vertreter.isEmpty()) {			
			res = appendString(res, vertreter.teilsatzBilden());
		}
		if (dienstelle != null && !dienstelle.isEmpty()) {			
			res = appendString(res, dienstelle.teilsatzBilden());
		}
		if (zollstelle != null && !zollstelle.isEmpty()) {			
			res = appendString(res, zollstelle.teilsatzBilden());
		}
		if (rechtsbehelfsHZA != null && !rechtsbehelfsHZA.isEmpty()) {			
			res = appendString(res, rechtsbehelfsHZA.teilsatzBilden());
		}
		if (fuerRechnung != null && !fuerRechnung.isEmpty()) {			
			res = appendString(res, fuerRechnung.teilsatzBilden());
		}		
		
		if (unbList != null) {                     					 
			int unbSize = unbList.size();
			for (int j = 0; j < unbSize; j++) {
				TsUNB unb = (TsUNB) unbList.get(j);
				if (unb != null && !unb.isEmpty()) {
					res = appendString(res, unb.teilsatzBilden());
				}
			}
		}
		if (posList != null) {                     					 
			int posSize = posList.size();
			for (int j = 0; j < posSize; j++) {
				MsgCTXPos pos = (MsgCTXPos) posList.get(j);
				if (pos != null) {					
					res = appendString(res, getCTXPos(pos));   
				}
			}
		}	
		
		return res;
	}
	
    public void readMessage(BufferedReader message) throws FssException {
    	
    	MsgCTXPos pos = null;    	
    	FssAbgaben abgaben = null;
    	
        try {
            String line = "";
           
            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
               
                if (lineType.equalsIgnoreCase("AB1")) {
                	ab1Subset = new TsAB1();
                    String[] ab1 = line.split("" + FssUtils.FSS_FS);
                    ab1Subset.setFields(ab1);
                } else if (lineType.equalsIgnoreCase("AB2")) {
                	ab2Subset = new TsAB2();
                    String[] ab2 = line.split("" + FssUtils.FSS_FS);
                    ab2Subset.setFields(ab2);
                } else if (lineType.equalsIgnoreCase("ANR")) {
                	anrSubset = new TsANR();
                	String[] anr = line.split("" + FssUtils.FSS_FS);
                    anrSubset.setFields(anr); 
                } else if (lineType.equalsIgnoreCase("ASK")) {
                	TsASK askSubset = new TsASK();
                    String[] ask = line.split("" + FssUtils.FSS_FS);
                    askSubset.setFields(ask);                     
                    if (askList == null) {
                    	askList = new Vector<TsASK>();
                    }
                    askList.add(askSubset);
                } else if (lineType.equalsIgnoreCase("TXA")) {
                	TsTXA txaSubset = new TsTXA();
                    String[] txa = line.split("" + FssUtils.FSS_FS);
                    txaSubset.setFields(txa);                     
                    if (txaList == null) {
                    	txaList = new Vector<TsTXA>();
                    }
                    txaList.add(txaSubset);
                } else if (lineType.equalsIgnoreCase("ADR")) {  
                	TsADRCTX adrSubset = new TsADRCTX();
                    String[] adr = line.split("" + FssUtils.FSS_FS);
                    adrSubset.setFields(adr);
                    if (adrSubset.getKztyp().equals("1")) {
                    	anmelder = adrSubset;
                    } else if (adrSubset.getKztyp().equals("2")) {
                    	vertreter = adrSubset;
                    //} else if (adrSubset.getAdztyp().equals("4")) {
                    	//empfaenger = adrSubset;
                    } else if (adrSubset.getKztyp().equals("10")) {
                    	dienstelle = adrSubset;
                    } else if (adrSubset.getKztyp().equals("11")) {
                    	zollstelle = adrSubset;
                    } else if (adrSubset.getKztyp().equals("14")) {
                    	rechtsbehelfsHZA = adrSubset;
                    } else if (adrSubset.getKztyp().equals("18")) {
                    	fuerRechnung = adrSubset;
                    }                
                } else if (lineType.equalsIgnoreCase("UNB")) {   
                	TsUNB unbSubset = new TsUNB();
                    String[] unb = line.split("" + FssUtils.FSS_FS);
                    unbSubset.setFields(unb);                     
                    if (unbList == null) {
                    	unbList = new Vector<TsUNB>();
                    }
                    unbList.add(unbSubset);
                } else if (lineType.equalsIgnoreCase("CPS")) { 
                	if (posList == null) {
                		posList = new Vector<MsgCTXPos>();
                	}
                	TsCPS cpsSubset = new TsCPS();    
                	String[] cps = line.split("" + FssUtils.FSS_FS);
                	cpsSubset.setFields(cps);                 	
                	pos = new MsgCTXPos();
            		pos.setCPSSubset(cpsSubset);                	
                	posList.add(pos);
                	abgaben = null;
                } else if (lineType.equalsIgnoreCase("TXP")) {   
                	TsTXP txpSubset = new TsTXP();    
                	String[] txp = line.split("" + FssUtils.FSS_FS);
                	txpSubset.setFields(txp);                 	
                	if (pos != null) {  
                		pos.addTXPList(txpSubset);                		
                	}                       
                } else if (lineType.equalsIgnoreCase("AHI")) {  
                	TsAHI ahiSubset = new TsAHI();    
                	String[] ahi = line.split("" + FssUtils.FSS_FS);
                	ahiSubset.setFields(ahi);                 	
                	if (pos != null) {                		
                		pos.addAHIList(ahiSubset);
                	}  
                } else if (lineType.equalsIgnoreCase("SOF")) { 
                	TsSOF sofSubset = new TsSOF();    
                	String[] sof = line.split("" + FssUtils.FSS_FS);
                	sofSubset.setFields(sof);                 	
                	if (pos != null) {                		
                		pos.addSOFList(sofSubset);
                	}  
                } else if (lineType.equalsIgnoreCase("ABU")) { 
                	abgaben = new FssAbgaben();
                 	TsABU abuSubset = new TsABU();    
                	String[] abu = line.split("" + FssUtils.FSS_FS);
                	abuSubset.setFields(abu);    
                	abgaben.setABUSubset(abuSubset);
                	if (pos != null) {                		
                		pos.addABUList(abgaben);
                	}                  	
                } else if (lineType.equalsIgnoreCase("AAB")) { 
                	TsAAB aabSubset = new TsAAB();    
                	String[] aab = line.split("" + FssUtils.FSS_FS);
                	aabSubset.setFields(aab);                 	
                	if (abgaben != null) {                		
                		abgaben.addAABList(aabSubset);
                	}  
                } else if (lineType.equalsIgnoreCase("UNT")) {   
                 	TsUNTCTX untSubset = new TsUNTCTX();    
                	String[] unt = line.split("" + FssUtils.FSS_FS);
                	untSubset.setFields(unt);                 	
                	if (pos != null) {                		
                		pos.addUNTList(untSubset);
                	}  
                } else if (lineType.equalsIgnoreCase("ABW")) { 
                 	TsABW abwSubset = new TsABW();    
                	String[] abw = line.split("" + FssUtils.FSS_FS);
                	abwSubset.setFields(abw);                 	
                	if (pos != null) {                		
                		pos.addABWList(abwSubset);
                	}  
                } else if (lineType.equalsIgnoreCase("AKG")) {   
                 	TsAKG akgSubset = new TsAKG();    
                	String[] akg = line.split("" + FssUtils.FSS_FS);
                	akgSubset.setFields(akg);                 	
                	if (pos != null) {                		
                		pos.addAKGList(akgSubset);
                	}  
                } else if (lineType.equalsIgnoreCase("NAC")) {
                	// Nachlaufsatz NAC nicht verarbeiten
                } 
                else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    
    private String getCTXPos(MsgCTXPos pos) throws FssException {
		String ret = "";
		
		if (pos == null) {
			return ret;
		}
		if (pos.getCPSSubset() != null) {
			 if (!pos.getCPSSubset().isEmpty()) {
				 ret = appendString(ret, pos.getCPSSubset().teilsatzBilden());
			 }
		}
		if (pos.getTXPList() != null) {
			 if (!pos.getTXPList().isEmpty()) {
				 int txpSize = pos.getTXPList().size();
					for (int j = 0; j < txpSize; j++) {
						TsTXP txp = (TsTXP) pos.getTXPList().get(j);
						if (txp != null && !txp.isEmpty()) {
							ret = appendString(ret, txp.teilsatzBilden());
						}
					}
			 }
		}
		
		//TODO: usw.weiter <== aber z.Z. nicht benoetigt	
		ret = "";  //damit klar ist, dass es nicht fertig ist
		return ret;
	}
}

