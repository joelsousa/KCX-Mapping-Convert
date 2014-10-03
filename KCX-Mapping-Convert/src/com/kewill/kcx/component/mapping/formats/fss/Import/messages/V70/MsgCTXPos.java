package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.FssAbgaben;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsABW;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHI;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAKG;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsCPS;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsSOF;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsUNTCTX;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: MsgCTX Position / ImportTaxAssessment.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgCTXPos extends FssMessage {
		
	private TsCPS        cpsSubset;    // Positionssatz 		
	private List <TsTXP> txpList;      // Texte 
	private List <TsAHI> ahiList;      // Abzüge / Hinzurechnungen 
	private List <TsSOF> sofList;      // Abzüge / Hinzurechnungen 
	private List <FssAbgaben> abuList; //Abgabenblock 
	private List <TsUNTCTX> untList;      // Unterlagensatz  
	private List <TsABW> abwList;      // Abweichende Festsetzungen 
	private List <TsAKG> akgList;      // Kontingente 
	
	public MsgCTXPos() {
		super();  
	}
	
	public TsCPS getCPSSubset() {
		return cpsSubset;
	}
	public void setCPSSubset(TsCPS subset) {
		cpsSubset = subset;
	}
	
	public void setTXPList(List <TsTXP> list) {
		txpList = list;
    }
	public List <TsTXP> getTXPList() {
		return txpList;
	}
	public void addTXPList(TsTXP subset) {
		if (txpList == null) {
			txpList = new Vector<TsTXP>();
		}
		txpList.add(subset);
    }
	
	public void setAHIList(List <TsAHI> list) {
		ahiList = list;
    }
	public List <TsAHI> getAHIList() {
		return ahiList;
	}
	public void addAHIList(TsAHI subset) {
		if (ahiList == null) {
			ahiList = new Vector<TsAHI>();
		}
		ahiList.add(subset);
    }
	
	public void setABUList(List <FssAbgaben> list) {
		abuList = list;
    }
	public List <FssAbgaben> getABUList() {
		return abuList;
	}
	public void addABUList(FssAbgaben abu) {
		if (abuList == null) {
			abuList = new Vector<FssAbgaben>();
		}
		abuList.add(abu);
    }
	
	
	public List <TsUNTCTX> getUNTList() {
		return untList;
	}
	public void setUNTList(List <TsUNTCTX> list) {
		untList = list;
    }
	public void addUNTList(TsUNTCTX subset) {
		if (untList == null) {
			untList = new Vector<TsUNTCTX>();
		}
		untList.add(subset);
    }
	
	public void setABWList(List <TsABW> list) {
		abwList = list;
    }
	public List <TsABW> getABWList() {
		return abwList;
	}
	public void addABWList(TsABW subset) {
		if (abwList == null) {
			abwList = new Vector<TsABW>();
		}
		abwList.add(subset);
    }
		
	public void setAKGList(List <TsAKG> list) {
		akgList = list;
    }
	public List <TsAKG> getAKGList() {
		return akgList;
	}
	public void addAKGList(TsAKG subset) {
		if (akgList == null) {
			akgList = new Vector<TsAKG>();
		}
		akgList.add(subset);
    }
	
	public void setSOFList(List <TsSOF> list) {
		sofList = list;
    }
	public List <TsSOF> getSOFList() {
		return sofList;
	}
	public void addSOFList(TsSOF subset) {
		if (sofList == null) {
			sofList = new Vector<TsSOF>();
		}
		sofList.add(subset);
    }
}

