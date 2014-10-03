/*
 * Modul    	: KidsToECustoms.java
 * Titel       	:
 * Date        	: 20.09.2009
 * @author      : Kewill CSF / Elisabeth Iwaniuk
 * Description 	: Contains the Message ExportDeclaration in the eCustoms/BellDavis  
 *
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 09.02.2010
 * Label       : EI20100209
 * Description : readMessage 
 */

package com.kewill.kcx.component.mapping.formats.Bdec.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsAI;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDB;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDC;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDD;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDI;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDL;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDO;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDP;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDS;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDT;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDU;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDV;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHH;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHR;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHS;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsNA;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsTD;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.FssMessage60;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		: MsgADA<br>
 * Erstellt		: 12.05.2009<br>
 * Beschreibung	: .
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MsgECustoms extends FssMessage60 {
	private TsVOR	vorSubset;  //zum Testzwecken
	private TsHH				hhSubset;   //1	
	private List <TsDO> 		hdList;     //40
	private List <TsHS> 		hsList;		//99	
	private List <TsHR> 		hrList;     //99
	private List <TsAI> 		haList;    	//40
	private List <MsgECustomsPos> posList;	
	private List <TsNA>    	    naList;   //CZ, CN, DT, WH, CM 
	private TsTD 				tdSubset;   //1
	
	public MsgECustoms() {
		super();
		vorSubset = new TsVOR(""); 
		hdList = new Vector<TsDO>();
		hsList = new Vector<TsHS>();
		hrList = new Vector<TsHR>();		
		haList = new Vector<TsAI>();
		naList = new Vector<TsNA>();
		posList = new Vector<MsgECustomsPos>();		
	}
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}	
	public String writeMessage() throws FssException {
		String res = "";  //kein trennen am Ende jedes TeilSatzes???
		
		if (hhSubset != null && !hhSubset.isEmpty()) {
			res = appendString(res, hhSubset.teilsatzBilden());
		}		
		if (hdList != null) {                     					 
			int hdSize = hdList.size();
			for (int j = 0; j < hdSize; j++) {
				TsDO hd = (TsDO) hdList.get(j);
				if (hd != null && !hd.isEmpty()) {
					res = appendString(res, hd.teilsatzBilden());
				}
			}
		}		
		if (hsList != null) {                     					 
			int hsSize = hsList.size();
			for (int j = 0; j < hsSize; j++) {
				TsHS hs = (TsHS) hsList.get(j);
				if (hs != null && !hs.isEmpty()) {
					res = appendString(res, hs.teilsatzBilden());
				}
			}
		}	
		
		if (hrList != null) {                     					 
			int hrSize = hrList.size();
			for (int j = 0; j < hrSize; j++) {
				TsHR hr = (TsHR) hrList.get(j);
				if (hr != null && !hr.isEmpty()) {
					res = appendString(res, hr.teilsatzBilden());
				}
			}
		}	
		if (haList != null) {                     					 
			int haSize = haList.size();
			for (int j = 0; j < haSize; j++) {				
				TsAI ha = (TsAI) haList.get(j);
				if (ha != null && !ha.isEmpty()) {
					res = appendString(res, ha.teilsatzBilden());
				}
			}
		}	
				
		int posSize = 0;
		if (posList != null) {
	        posSize = posList.size();
		}
		for (int i = 0; i < posSize; i++) {			
			MsgECustomsPos pos = posList.get(i);
			
			TsDD dd = pos.getDDSubset();
			List <TsDP> dpList = pos.getDPList();
			List <TsDT> dtList = pos.getDTList();  		
			List <TsAI> daList = pos.getDAList();  
			List <TsDV> dvList = pos.getDVList(); 
			List <TsDL> dlList = pos.getDLList();
			List <TsDS> dsList = pos.getDSList();
			List <TsDI> diList = pos.getDIList();
			List <TsDB> dbList = pos.getDBList();
			List <TsDO> doList = pos.getDOList();
			List <TsDC> dcList = pos.getDCList();
			List <TsDU> duList = pos.getDUList();
			
			if (dd != null && !dd.isEmpty()) {
				res = appendString(res, dd.teilsatzBilden());
			}										
			if (dpList != null) {
				int dpSize = dpList.size();
				for (int j = 0; j < dpSize; j++) {
					TsDP dp = (TsDP) dpList.get(j);
					if (dp != null && !dp.isEmpty()) {
						res = appendString(res, dp.teilsatzBilden()); 
					}
				}
			}			
			if (dtList != null) {
				int dtSize = dtList.size();
				for (int j = 0; j < dtSize; j++) {
					TsDT dt = (TsDT) dtList.get(j);
					if (dt != null && !dt.isEmpty()) {
						res = appendString(res, dt.teilsatzBilden()); 
					}
				}
			}			
			if (daList != null) {
				int daSize = daList.size();
				for (int j = 0; j < daSize; j++) {					
					TsAI da = (TsAI) daList.get(j);
					if (da != null && !da.isEmpty()) {
						res = appendString(res, da.teilsatzBilden()); 
					}
				}
			}		
			if (dvList != null) {
				int dvSize = dvList.size();
				for (int j = 0; j < dvSize; j++) {
					TsDV dv = (TsDV) dvList.get(j);
					if (dv != null && !dv.isEmpty()) {
						res = appendString(res, dv.teilsatzBilden()); 
					}
				}
			}
			if (dlList != null) {
				int dlSize = dlList.size();
				for (int j = 0; j < dlSize; j++) {
					TsDL dl = (TsDL) dlList.get(j);
					if (dl != null && !dl.isEmpty()) {
						res = appendString(res, dl.teilsatzBilden()); 
					}
				}
			}	
			if (dsList != null) {
				int dsSize = dsList.size();
				for (int j = 0; j < dsSize; j++) {
					TsDS ds = (TsDS) dsList.get(j);
					if (ds != null && !ds.isEmpty()) {
						res = appendString(res, ds.teilsatzBilden()); 
					}
				}
			}				
			if (diList != null) {
				int diSize = diList.size();
				for (int j = 0; j < diSize; j++) {
					TsDI di = (TsDI) diList.get(j);
					if (di != null && !di.isEmpty()) {
						res = appendString(res, di.teilsatzBilden()); 
					}
				}
			}			
			if (dbList != null) {
				int dbSize = dbList.size();
				for (int j = 0; j < dbSize; j++) {
					TsDB db = (TsDB) dbList.get(j);
					if (db != null && !db.isEmpty()) {
						res = appendString(res, db.teilsatzBilden()); 
					}
				}
			}		
		    if (doList != null) {
		    	int doSize = doList.size();
		    	for (int j = 0; j < doSize; j++) {
		    		TsDO doc = (TsDO) doList.get(j);
		    		if (doc != null && !doc.isEmpty()) {
		    			res = appendString(res, doc.teilsatzBilden()); 
		    		}
		    	}
		    }
		    if (dcList != null) {
		    	int dcSize = dcList.size();
		    	for (int j = 0; j < dcSize; j++) {
		    		TsDC dc = (TsDC) dcList.get(j);
		    		if (dc != null && !dc.isEmpty()) {
		    			res = appendString(res, dc.teilsatzBilden()); 
		    		}
		    	}
		    }				
		    if (duList != null) {
		    	int duSize = duList.size();
		    	for (int j = 0; j < duSize; j++) {
		    		TsDU du = (TsDU) duList.get(j);
		    		if (du != null && !du.isEmpty()) {
		    			res = appendString(res, du.teilsatzBilden()); 
		    		}
		    	}
		    }	
		}
		
		if (naList != null) {
	    	int naSize = naList.size();
	    	for (int j = 0; j < naSize; j++) {
	    		TsNA na = (TsNA) naList.get(j);
	    		if (na != null && !na.isEmpty()) {
	    			res = appendString(res, na.teilsatzBilden()); 
	    		}
	    	}
	    }			
		if (tdSubset != null && !tdSubset.isEmpty()) {
			res = appendString(res, tdSubset.teilsatzBilden());
		}		
		return res;
   }
	
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            MsgECustomsPos pos = null;
//            List <TsDP> dpList = null;
//            List <TsDT> dtList = null;
            List <TsAI> daList = null;
//            List <TsDV> dvList = null;
//            List <TsDL> dlList = null;
//            List <TsDS> dsList = null;
//            List <TsDI> diList = null;
//            List <TsDB> dbList = null;
//            List <TsDO> doList = null;
//            List <TsDC> dcList = null;
//            List <TsDU> duList = null;
            
            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 2);                
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("HH")) {
                	hhSubset = new TsHH();
                    String[] hh = line.split("" + FssUtils.FSS_FS);
                    hhSubset.setFields(hh);
                    hdList = new Vector<TsDO>();
                    hsList = new Vector<TsHS>();
                    hrList = new Vector<TsHR>();                    
                    haList = new Vector<TsAI>();
                    posList = new Vector<MsgECustomsPos>();
                    //pos = new MsgECustomsPos();
                } else if (lineType.equalsIgnoreCase("HD")) {    
                	TsDO hdSubset = new TsDO("HD");
                    String[] hd = line.split("" + FssUtils.FSS_FS);
                    hdSubset.setFields(hd);  
                    hdList.add(hdSubset);
                } else if (lineType.equalsIgnoreCase("HS")) {    
                	TsHS hsSubset = new TsHS();
                	String[] hs = line.split("" + FssUtils.FSS_FS);
                	hsSubset.setFields(hs);
                	hsList.add(hsSubset);
                } else if (lineType.equalsIgnoreCase("HR")) {    
                	TsHR hrSubset = new TsHR();
                    String[] hr = line.split("" + FssUtils.FSS_FS);
                    hrSubset.setFields(hr);
                    hrList.add(hrSubset);
                } else if (lineType.equalsIgnoreCase("HA")) {                    	
                	TsAI haSubset = new TsAI("HA");
                    String[] ha = line.split("" + FssUtils.FSS_FS);
                    haSubset.setFields(ha);
                    haList.add(haSubset);
                } else if (lineType.equalsIgnoreCase("DD")) {   
                	// anfang pos
                	TsDD ddSubset = new TsDD();
                    String[] dd = line.split("" + FssUtils.FSS_FS);
                    ddSubset.setFields(dd);
                    pos = new MsgECustomsPos();
                    pos.setDDSubset(ddSubset);
                    posList.add(pos);
//                    dpList = new Vector<TsDP>();
//                    dtList = new Vector<TsDT>();
                    daList = new Vector<TsAI>();
//                    dvList = new Vector<TsDV>();
//                    dlList = new Vector<TsDL>();
//                    dsList = new Vector<TsDS>();
//                    diList = new Vector<TsDI>();
//                    dbList = new Vector<TsDB>();
//                    dcList = new Vector<TsDC>();
//                    duList = new Vector<TsDU>();
//                    doList = new Vector<TsDO>();
                    pos.setDAList(daList);
                } else if (lineType.equalsIgnoreCase("DP")) {   
                	TsDP dpSubset = new TsDP();
                    String[] dp = line.split("" + FssUtils.FSS_FS);
                    dpSubset.setFields(dp);
                    //dpList.add(dpSubset); 
                    pos.addDPList(dpSubset);
                } else if (lineType.equalsIgnoreCase("DT")) {   
                	TsDT dtSubset = new TsDT();
                    String[] dt = line.split("" + FssUtils.FSS_FS);
                    dtSubset.setFields(dt);
                    //dtList.add(dtSubset); 
                    pos.addDTList(dtSubset);
                } else if (lineType.equalsIgnoreCase("DA")) {   
                	TsAI daSubset = new TsAI("DA");
                    String[] da = line.split("" + FssUtils.FSS_FS);
                    daSubset.setFields(da);
                    //daList.add(daSubset); 
                    pos.addDAList(daSubset);
                } else if (lineType.equalsIgnoreCase("DV")) {   
                	TsDV dvSubset = new TsDV();
                    String[] dv = line.split("" + FssUtils.FSS_FS);
                    dvSubset.setFields(dv);
                    //dvList.add(dvSubset); 
                    pos.addDVList(dvSubset);
                } else if (lineType.equalsIgnoreCase("DL")) {   
                	TsDL dlSubset = new TsDL();
                    String[] dl = line.split("" + FssUtils.FSS_FS);
                    dlSubset.setFields(dl);
                    //dlList.add(dlSubset); 
                    pos.addDLList(dlSubset);
                } else if (lineType.equalsIgnoreCase("DS")) {   
                	TsDS dsSubset = new TsDS();
                    String[] ds = line.split("" + FssUtils.FSS_FS);
                    dsSubset.setFields(ds);
                    //dsList.add(dsSubset);
                    pos.addDSList(dsSubset);
                } else if (lineType.equalsIgnoreCase("DI")) {   
                	TsDI diSubset = new TsDI();
                    String[] di = line.split("" + FssUtils.FSS_FS);
                    diSubset.setFields(di);
                    //diList.add(diSubset); 
                    pos.addDIList(diSubset);
                } else if (lineType.equalsIgnoreCase("DB")) {   
                	TsDB dbSubset = new TsDB();
                    String[] db = line.split("" + FssUtils.FSS_FS);
                    dbSubset.setFields(db);
                    //dbList.add(dbSubset); 
                    pos.addDBList(dbSubset);
                } else if (lineType.equalsIgnoreCase("DO")) {   
                	TsDO doSubset = new TsDO("DO");
                    String[] doc = line.split("" + FssUtils.FSS_FS);
                    doSubset.setFields(doc);
                    //doList.add(doSubset); 
                    pos.addDOList(doSubset);
                } else if (lineType.equalsIgnoreCase("DC")) {   
                	TsDC dcSubset = new TsDC();
                    String[] dc = line.split("" + FssUtils.FSS_FS);
                    dcSubset.setFields(dc);
                    //dcList.add(dcSubset); 
                    pos.addDCList(dcSubset);
                } else if (lineType.equalsIgnoreCase("DU")) {   
                	TsDU duSubset = new TsDU();
                    String[] du = line.split("" + FssUtils.FSS_FS);
                    duSubset.setFields(du);
                    //duList.add(duSubset); 
                    pos.addDUList(duSubset);
                //ende pos   
                } else if (lineType.equalsIgnoreCase("NA")) {    
                	TsNA naSubset = new TsNA();
                    String[] na = line.split("" + FssUtils.FSS_FS);
                    naSubset.setFields(na); 
                    naList.add(naSubset); 
                } else if (lineType.equalsIgnoreCase("TD")) {    
                	tdSubset = new TsTD();
                    String[] td = line.split("" + FssUtils.FSS_FS);
                    tdSubset.setFields(td);
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//
	public TsHH getHHSubset() {
		return hhSubset;
	}
	public void setHHSubset(TsHH hhSubset) {
		this.hhSubset = hhSubset;
	}
	
	public List<TsDO> getHDList() {
		return hdList;
	}
	public void setHDList(List<TsDO> hdList) {
		this.hdList = hdList;
	}
	public void addHDList(TsDO argument) {
		if (hdList == null) {
			hdList = new Vector<TsDO>();
		}
		hdList.add(argument);
	}
	
	public List<TsHS> getHSList() {
		return hsList;
	}
	public void setHSList(List<TsHS> hsList) {
		this.hsList = hsList;
	}
	public void addHSList(TsHS argument) {
		if (hsList == null) {
			hsList = new Vector<TsHS>();
		}
		hsList.add(argument);
	}
	public List<TsHR> getHRList() {
		return hrList;
	}
	public void setHRList(List<TsHR> hrList) {
		this.hrList = hrList;
	}
	public void addHRList(TsHR argument) {
		if (hrList == null) { 
			hrList = new Vector<TsHR>();
		}
		hrList.add(argument);
	}	
	public List<TsAI> getHAList() {
		return haList;
	}
	public void setHAList(List<TsAI> haList) {
		this.haList = haList;
	}	
	public void addHAList(TsAI argument) {
		if (haList == null) {			
			haList = new Vector<TsAI>();
		}
		haList.add(argument);
	}
	public List<TsNA> getNAList() {
		return naList;
	}
	public void setNAList(List<TsNA> argument) {
		this.naList = argument;		
	}
	public void addNAList(TsNA argument) {
		if (naList == null) {
			naList = new Vector<TsNA>();
		}
		naList.add(argument);
	}

	public TsTD getTDSubset() {
		return tdSubset;
	}
	public void setTDSubset(TsTD tdSubset) {
		this.tdSubset = tdSubset;
	}
	public List<MsgECustomsPos> getPosList() {
		return posList;
	}
	public void setPosList(List<MsgECustomsPos> posList) {
		this.posList = posList;
	}
	public void addPosList(MsgECustomsPos argument) {
		if (posList == null) {
			posList = new Vector<MsgECustomsPos>();
		}
		posList.add(argument);
	}
	
}
