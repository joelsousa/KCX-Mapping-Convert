/*
 * Modul    	: KidsToECustoms
 * Titel       	:
 * Date        	: 20.09.2009
 * @author      : Kewill CSF / Elisabeth iwaniuk
 * Description 	: Message ExportDeclaration in the eCustoms/BellDavis   
 * 			    : 
 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description :
 */

package com.kewill.kcx.component.mapping.formats.Bdec.messages;

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
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.FssMessage60;


/**
 * Modul		: MsgECustomsPos<br>
 * Erstellt		: 20.09.2009<br>
 * Beschreibung	: .
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MsgECustomsPos extends FssMessage60 {

	private TsDD				ddSubset;   //1 
	private List <TsDP> 		dpList;     //99	  
	private List <TsDT> 		dtList;     //10		
	private List <TsAI> 		daList;     //99
	private List <TsDV> 		dvList;     //9
	private List <TsDL> 		dlList;     //99	
	private List <TsDS> 		dsList;     //2	
	private List <TsDI> 		diList;     //99	  
	private List <TsDB> 		dbList;     //99	 
	private List <TsDO> 		doList;     //99
	private List <TsDC> 		dcList;     //99	
	private List <TsDU> 		duList;     //SDILIN, IFDDTL, IFWDTL, ISDDTL, ISWDTL
	
	public MsgECustomsPos() {
		super();
		dpList = new Vector<TsDP>();
		dtList = new Vector<TsDT>();		
		daList = new Vector<TsAI>();
		dvList = new Vector<TsDV>();
		dlList = new Vector<TsDL>();
		dsList = new Vector<TsDS>();
		diList = new Vector<TsDI>();
		dbList = new Vector<TsDB>();
		doList = new Vector<TsDO>();
		dcList = new Vector<TsDC>();
		duList = new Vector<TsDU>();		
	}
	
	public TsDD getDDSubset() {
		return ddSubset;
	}
	public void setDDSubset(TsDD argument) {
		this.ddSubset = argument;
	}

	public List<TsDP> getDPList() {
		return dpList;
	}
	public void setDPList(List<TsDP> dpList) {
		this.dpList = dpList;
	}
	public void addDPList(TsDP argument) {
		if (dpList == null) {
			dpList = new Vector<TsDP>();
		}
		dpList.add(argument);
	}	
	public List<TsDT> getDTList() {
		return dtList;
	}
	public void setDTList(List<TsDT> dtList) {
		this.dtList = dtList;
	}
	public void addDTList(TsDT argument) {
		if (dtList == null) {
			dtList = new Vector<TsDT>();
		}
		dtList.add(argument);
	}	
	
	public List<TsAI> getDAList() {
		return daList;
	}	
	public void setDAList(List<TsAI> daList) {
		this.daList = daList;
	}	
	public void addDAList(TsAI argument) {
		if (daList == null) {			
			daList = new Vector<TsAI>();	
		}
		daList.add(argument);
	}	
	public List<TsDV> getDVList() {
		return dvList;
	}
	public void setDVList(List<TsDV> dvList) {
		this.dvList = dvList;
	}
	public void addDVList(TsDV argument) {
		if (dvList == null) {
			dvList = new Vector<TsDV>();
		}
		dvList.add(argument);
	}	
	public List<TsDL> getDLList() {
		return dlList;
	}
	public void setDLList(List<TsDL> dlList) {
		this.dlList = dlList;
	}
	public void addDLList(TsDL argument) {
		if (dlList == null) {
			dlList = new Vector<TsDL>();
		}
		dlList.add(argument);
	}	
	public List<TsDS> getDSList() {
		return dsList;
	}
	public void setDSList(List<TsDS> dsList) {
		this.dsList = dsList;
	}
	public void addDSList(TsDS argument) {
		if (dsList == null) {
			dsList = new Vector<TsDS>();
		}
		dsList.add(argument);
	}	
	public List<TsDI> getDIList() {
		return diList;
	}
	public void setDIList(List<TsDI> diList) {
		this.diList = diList;
	}
	public void addDIList(TsDI argument) {
		if (diList == null) {
			diList = new Vector<TsDI>();
		}
		diList.add(argument);
	}	
	public List<TsDB> getDBList() {
		return dbList;
	}
	public void setDBList(List<TsDB> dbList) {
		this.dbList = dbList;
	}
	public void addDBList(TsDB argument) {
		if (dbList == null) {
			dbList = new Vector<TsDB>();
		}
		dbList.add(argument);
	}	
	public List<TsDO> getDOList() {
		return doList;
	}
	public void setDOList(List<TsDO> doList) {
		this.doList = doList;
	}
	public void addDOList(TsDO argument) {
		if (doList == null) {
			doList = new Vector<TsDO>();
		}
		doList.add(argument);
	}		
	public List<TsDC> getDCList() {
		return dcList;
	}
	public void setDCList(List<TsDC> dcList) {
		this.dcList = dcList;
	}
	public void addDCList(TsDC argument) {
		if (dcList == null) {
			dcList = new Vector<TsDC>();
		}
		dcList.add(argument);
	}		
	public List<TsDU> getDUList() {
		return duList;
	}
	public void setDUList(List<TsDU> duList) {
		this.duList = duList;
	}	
	public void addDUList(TsDU argument) {
		if (duList == null) {
			duList = new Vector<TsDU>();
		}
		duList.add(argument);
	}	
}
	  

