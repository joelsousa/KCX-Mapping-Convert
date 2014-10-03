package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVRL<br>
 * Created		: 18.05.2011<br>
 * Description	: VRL model class.
 * 
 * @author	: iwaniuk
 * @version	: 6.2.00
 *
 */
public class TsVRL extends Teilsatz {
	private String beznr = "";	// Bezugsnummer
	private String mrn	= "";	    // 
	private String erldat = "";	// 
	private String erlart = "";	// 
	private String text	= "";	// 
	private String kztyd	= "";
	private String sb	= "";
	
	/// CONSTRUCTOR(s)
		public TsVRL() {
			tsTyp	= "VRL";
		}
	
	/// SETTER & GETTER Methods
		public String getBeznr() {
			return this.beznr;
		}
		public void setBeznr(String beznr) {
			this.beznr	= Utils.checkNull(beznr);
		}
		
		public String getMrn() {
			return this.mrn;
		}
		public void setMrn(String mrn) {
			this.mrn	= Utils.checkNull(mrn);
		}
		
		public String getErrdat() {
			return this.erldat;
		}
		public void setErrdat(String dat) {
			this.erldat	= Utils.checkNull(dat);
		}
		
		public String getErlart() {
			return this.erlart;
		}
		public void setErlart(String art) {
			this.erlart	= Utils.checkNull(art);
		}
		
		public String getText() {
			return this.text;
		}
		public void setText(String txt) {
			this.text	= Utils.checkNull(txt);
		}
		
		public String getKztyd() {
			return this.kztyd;
		}
		public void setKztyd(String kz) {
			this.kztyd	= Utils.checkNull(kz);
		}
		
		public String getSb() {
			return this.sb;
		}
		public void setSb(String sb) {
			this.sb	= Utils.checkNull(sb);
		}
	
	/// METHODS		
	
		@Override
		public void setFields(String[] fields) {
			int size	= fields.length;
			
			if (size < 1) {
				return;
			}
			tsTyp	= fields[0];
			if (size < 2) {
				return;
			}
			beznr	= fields[1];
			if (size < 3) {
				return;
			}
			mrn	= fields[2];
			if (size < 4) {
				return;
			}
			erldat	= fields[3];
			if (size < 5) {
				return;
			}
			erlart	= fields[4];
			if (size < 6) {
				return;
			}
			text	= fields[5];
			if (size < 7) {
				return;
			}
			kztyd	= fields[6];
			if (size < 8) {
				return;
			}
			sb	= fields[7];
		}
	
		@Override
		public String teilsatzBilden() {
			StringBuffer buff	= new StringBuffer();
			
			buff.append(tsTyp);
			buff.append(trenner);
			buff.append(beznr);
			buff.append(trenner);
			buff.append(mrn);
			buff.append(trenner);
			buff.append(erldat);
			buff.append(trenner);
			buff.append(erlart);
			buff.append(trenner);			
			buff.append(text);
			buff.append(trenner);	
			buff.append(kztyd);
			buff.append(trenner);	
			buff.append(sb);
			buff.append(trenner);	
			
			return buff.toString();
		}

		public boolean isEmpty() {			
			return (Utils.isStringEmpty(mrn) && 
					Utils.isStringEmpty(erldat) && 
					Utils.isStringEmpty(erlart) && 
					Utils.isStringEmpty(text) &&
					Utils.isStringEmpty(kztyd) &&
					Utils.isStringEmpty(sb));
		}
}
