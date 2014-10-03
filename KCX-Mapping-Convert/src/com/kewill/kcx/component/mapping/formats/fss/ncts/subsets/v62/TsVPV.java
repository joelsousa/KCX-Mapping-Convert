package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVPV<br>
 * Created		: 2010.09.03<br>
 * Description	: VPV model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPV extends Teilsatz {
	private String seal		= "";
	
	public TsVPV() {
		tsTyp = "VPV";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(seal);
		buff.append(trenner);
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
//		String ausgabe = "FSS: " + fields[0] + " size = " + size;
//		Utils.log(ausgabe);
		
		if (size < 1) {
			return;
		}
		tsTyp = fields[0];
		if (size < 2) {
			return;
		}
		seal = fields[1];
	}

	public String getSeal() {
		return seal;
	}

	public void setSeal(String seal) {
		this.seal = Utils.checkNull(seal);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(seal));
	}
}
