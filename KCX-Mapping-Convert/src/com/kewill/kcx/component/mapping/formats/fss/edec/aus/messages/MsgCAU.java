package com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages;

/**
 * Modul        :   EDEC Export NCTS (Ausfuhr)
 * Nachricht    :   CAU (Cancellation)
 * Erstellt     :   17.09.2008
 * Beschreibung :   MsgCAU
 *  
 * @author          Sven Heise
 * 
 */

import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.FssMessage60;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADIPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAU;
import com.kewill.kcx.component.mapping.util.Utils;

public class MsgCAU extends FssMessage60{

	private TsVOR	vorSubset;
	private TsCAU   cauSubset;

	public MsgCAU() {
		cauSubset = new TsCAU();
	}
	
	public String getFssString() throws FssException {
		String res = "";	
		
		if(vorSubset != null && !vorSubset.isEmpty()) 		 
			res = appendString(res, vorSubset.teilsatzBilden());	
		if(cauSubset != null && !cauSubset.isEmpty())			
			res = appendString(res, cauSubset.teilsatzBilden());
		
		return res;
	}
	/*public String writeFSS() throws FssException {
		String res = "";
		if ((getFssOutFileName() != null) && (getFssOutFileName().length() > 0)) {
			openOutput();
			writeTeilsatz(vorSubset.teilsatzBilden());
			writeTeilsatz(cauSubset.teilsatzBilden());
			closeOutput();
			res = getFssOutFileName();
		} else {
			res = vorSubset.teilsatzBilden() + Utils.LF + cauSubset.teilsatzBilden() + Utils.LF;
		}
		return res;
	}
	*/
	public TsVOR getVorSubset() {
		return vorSubset;
	}

	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}

	public TsCAU getCauSubset() {
		return cauSubset;
	}

	public void setCauSubset(TsCAU cauSubset) {
		this.cauSubset = cauSubset;
	}
	

}