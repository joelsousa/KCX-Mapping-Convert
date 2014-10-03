package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVPC<br>
 * Created		: 2010.09.03<br>
 * Description	: VPC model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPC extends Teilsatz {
	private String posnr		= "";
	private String connr		= "";
	
	public TsVPC() {
		tsTyp = "VPC";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(connr);
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
		posnr = fields[1];
		if (size < 3) {
			return;
		}
		connr =  fields[2];
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getConnr() {
		return connr;
	}

	public void setConnr(String connr) {
		this.connr = Utils.checkNull(connr);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(connr));
	}
}
