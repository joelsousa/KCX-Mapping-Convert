package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVFO<br>
 * Created		: 03.09.2010<br>
 * Description	: VFO model class.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class TsVFO extends Teilsatz {
	private String	beznr	= "";	// Bezugsnummer
	private String	mrn		= "";	// MRN
	private String	fregnr	= "";	// Registrienummer Fremdsystem NCTS nicht verwendet
	
	/// CONSTRUCTOR(s)
		public TsVFO() {
			tsTyp	= "VFO";
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
		
		public String getFregnr() {
			return this.fregnr;
		}
		public void setFregnr(String fregnr) {
			this.fregnr	= Utils.checkNull(fregnr);
		}
	
	/// METHODS
		public String teilsatzBilden() {
			StringBuffer buff = new StringBuffer();
			
			buff.append(tsTyp);
			buff.append(trenner);
			buff.append(beznr);
			buff.append(trenner);
			buff.append(mrn);
			buff.append(trenner);
			buff.append(fregnr);
			buff.append(trenner);
			
			return buff.toString();
		}
		
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
			mrn		= fields[2];
			if (size < 4) {
				return;
			}
			fregnr	= fields[3];
		}
		
		public boolean isEmpty() {
			return (Utils.isStringEmpty(mrn) && 					
					Utils.isStringEmpty(fregnr));
		}
	
}
