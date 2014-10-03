/*
 * Funktion    : MsgADN.java
 * Titel       :
 * Erstellt    : 02.10.2008
 * Author      : Alfred Krzoska
 * Beschreibung: AES Message Amendment
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

public class MsgADN extends FssDatei {

	private TsVOR	vorSubset;
	private TsAAK   aakSubset;  
	
	private TsAAP   aapSubset;		
	private List<TsAAP> posList;
		
		
	
	public MsgADN(String outFileName) {
		super(outFileName);		
	
		vorSubset = new TsVOR("E");	
		aakSubset = new TsAAK();
		posList = new Vector <TsAAP>();		
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	
	public TsAAK getAakSubset() {
		return aakSubset;
	}
	public void setAakSubset(TsAAK argument) {
		this.aakSubset = argument;
	}

	public StringBuffer writeADN() throws FssException {
		StringBuffer res = new StringBuffer();
		
		res = appendString(res, vorSubset.teilsatzBilden());	
		if (!aakSubset.isEmpty()){
			res = appendString(res, aakSubset.teilsatzBilden());
			}
			
		for (int i = 0 ; i < posList.size(); i++)
		{
			aapSubset = posList.get(i);
			if (!aapSubset.isEmpty()){
				res = appendString(res, aapSubset.teilsatzBilden());	
			}
		}
		return res;
	}
	
	private StringBuffer appendString ( StringBuffer in, String appendStr) throws FssException {
		StringBuffer stb = new StringBuffer();
		
		stb.append(in);
		stb.append(appendStr);
		stb.append(Utils.LF);
		return stb;
	}
	


	public void addPosList(TsAAP pos) {
		this.posList.add(pos);
	}	
}


