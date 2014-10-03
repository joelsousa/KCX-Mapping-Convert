package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVFP<br>
 * Created		: 03.09.2010<br>
 * Description	: VFP model class.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class TsVFP extends Teilsatz {
	private String beznr	= "";	// Bezugsnummer
	private String lfdnr	= "";	// laufende Nummer des Fehlers
	private String errcde	= "";	// Fehlercode
	private String errtxt	= "";	// Fehlertext
	private String zeiger	= "";	// Zeiger
	
	/// CONSTRUCTOR(s)
		public TsVFP() {
			tsTyp	= "VFP";
		}
	
	/// SETTER & GETTER Methods
		public String getBeznr() {
			return this.beznr;
		}
		public void setBeznr(String beznr) {
			this.beznr	= Utils.checkNull(beznr);
		}
		
		public String getLfdnr() {
			return this.lfdnr;
		}
		public void setLfdnr(String lfdnr) {
			this.lfdnr	= Utils.checkNull(lfdnr);
		}
		
		public String getErrcde() {
			return this.errcde;
		}
		public void setErrcde(String errcde) {
			this.errcde	= Utils.checkNull(errcde);
		}
		
		public String getErrtxt() {
			return this.errtxt;
		}
		public void setErrtxt(String errtxt) {
			this.errtxt	= Utils.checkNull(errtxt);
		}
		
		public String getZeiger() {
			return this.zeiger;
		}
		public void setZeiger(String zeiger) {
			this.zeiger	= Utils.checkNull(zeiger);
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
			lfdnr	= fields[2];
			if (size < 4) {
				return;
			}
			errcde	= fields[3];
			if (size < 5) {
				return;
			}
			errtxt	= fields[4];
			if (size < 6) {
				return;
			}
			zeiger	= fields[5];
		}
	
		@Override
		public String teilsatzBilden() {
			StringBuffer buff	= new StringBuffer();
			
			buff.append(tsTyp);
			buff.append(trenner);
			buff.append(beznr);
			buff.append(trenner);
			buff.append(lfdnr);
			buff.append(trenner);
			buff.append(errcde);
			buff.append(trenner);
			buff.append(errtxt);
			buff.append(trenner);
			buff.append(zeiger);
			buff.append(trenner);
			
			return buff.toString();
		}

		@Override
		public boolean isEmpty() {		
			return (Utils.isStringEmpty(errcde) && 
					Utils.isStringEmpty(errtxt) && 
					Utils.isStringEmpty(lfdnr) && 
					Utils.isStringEmpty(zeiger));
		}
}
