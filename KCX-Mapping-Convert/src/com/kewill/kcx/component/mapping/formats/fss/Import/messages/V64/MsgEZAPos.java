package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHI;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHICTX;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsBEW;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsGEH;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsKON;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsMIN;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsPO1;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsPO2;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsPRN;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsSOF;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsUNP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsVST;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: MsgEZA Position / ImportDeclaration.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgEZAPos extends FssMessage {
		
	private TsPO1        po1Subset;    // 1.Positionssatz 
	private TsPO2        po2Subset;    // 2.Positionssatz 
	private TsPRN        prnSubset;    // Angaben zur Präferenz  
	private List <TsUNP> unpList;      // Unterlagen  
	private List <TsGEH> gehList;      // Gehaltsangaben  
	private List <TsVST> vstList;      // Verbrauchssteuer  
	private TsKON        konSubset;    // Kontingentangaben  
	private List <TsAHI> ahiList;;     // Abzüge/Hinzurechnungen  
	private List <TsMIN> minList;      // Minderungsangaben  
	private List <TsBEW> bewList;      // Besondere Wertangaben  
	private List <TsSOF> sofList;      // Sonderfallangaben 
	
	
	public MsgEZAPos() {
		super();  
	}
	
	public TsPO1 getPO1Subset() {
		return po1Subset;
	}
	public void setPO1Subset(TsPO1 subset) {
		po1Subset = subset;
	}
	
	public TsPO2 getPO2Subset() {
		return po2Subset;
	}
	public void setPO2Subset(TsPO2 subset) {
		po2Subset = subset;
	}
	
	public TsPRN getPRNSubset() {
		return prnSubset;
	}
	public void setPRNSubset(TsPRN subset) {
		prnSubset = subset;
	}
	
	public void setUNPList(List <TsUNP> list) {
		unpList = list;
    }
	public List <TsUNP> getUNPList() {
		return unpList;
	}
	public void addUNPList(TsUNP subset) {
		if (unpList == null) {
			unpList = new Vector<TsUNP>();
		}
		unpList.add(subset);
    }
	
	public void setGEHList(List <TsGEH> list) {
		gehList = list;
    }
	public List <TsGEH> getGEHList() {
		return gehList;
	}
	public void addGEHList(TsGEH subset) {
		if (gehList == null) {
			gehList = new Vector<TsGEH>();
		}
		gehList.add(subset);
    }
	
	public void setVSTList(List<TsVST> list) {
		vstList = list;
    }
	public List<TsVST> getVSTList() {
		return vstList;
	}
	public void addVSTList(TsVST subset) {
		if (vstList == null) {
			vstList = new Vector<TsVST>();
		}
		vstList.add(subset);
    }
	
	public TsKON getKONSubset() {
		return konSubset;
	}
	public void setKONSubset(TsKON subset) {
		konSubset = subset;
	}
	
	public void setAHIList(List<TsAHI> list) {
		ahiList = list;
    }
	public List<TsAHI> getAHIList() {
		return ahiList;
	}
	public void addAHIList(TsAHI subset) {
		if (ahiList == null) {
			ahiList = new Vector<TsAHI>();
		}
		ahiList.add(subset);
    }
	
	public void setMINList(List<TsMIN> list) {
		minList = list;
    }
	public List<TsMIN> getMINList() {
		return minList;
	}
	public void addMINList(TsMIN min) {
		if (minList == null) {
			minList = new Vector<TsMIN>();
		}
		minList.add(min);
    }
	
	
	public List<TsBEW> getBEWList() {
		return bewList;
	}
	public void setBEWList(List<TsBEW> list) {
		bewList = list;
    }
	public void addBEWList(TsBEW subset) {
		if (bewList == null) {
			bewList = new Vector<TsBEW>();
		}
		bewList.add(subset);
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

