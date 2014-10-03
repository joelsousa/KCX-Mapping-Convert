package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVUC
 * Created		: 06.09.2010
 * Description	: VUC model class.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class TsVUC extends Teilsatz {
	private String mrn		= "";	// MRN-Nummer
	private String posnr	= "";	// Positionsnummer
	private String connr	= "";	// Containernummer
	
	/// CONSTRUCTOR(s)
		public TsVUC() {
			tsTyp	= "VUC";
		}
	
	/// SETTER & GETTER Methods
		public String getMrn() {
			return this.mrn;
		}
		public void setMrn(String mrn) {
			this.mrn	= Utils.checkNull(mrn);
		}
		
		public String getPosnr() {
			return this.posnr;
		}
		public void setPosnr(String posnr) {
			this.posnr	= Utils.checkNull(posnr);
		}
		
		public String getConnr() {
			return this.connr;
		}
		public void setConnr(String connr) {
			this.connr	= Utils.checkNull(connr);
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
			mrn		= fields[1];
			if (size < 3) {
				return;
			}
			posnr	= fields[2];
			if (size < 4) {
				return;
			}
			connr	= fields[3];
		}
	
		@Override
		public String teilsatzBilden() {
			StringBuffer buff	= new StringBuffer();
			
			buff.append(tsTyp);
			buff.append(trenner);
			buff.append(mrn);
			buff.append(trenner);
			buff.append(posnr);
			buff.append(trenner);
			buff.append(connr);
			buff.append(trenner);
			
			return buff.toString();
		}

		
		@Override
		public boolean isEmpty() {			
			return (Utils.isStringEmpty(connr));
		}
}
