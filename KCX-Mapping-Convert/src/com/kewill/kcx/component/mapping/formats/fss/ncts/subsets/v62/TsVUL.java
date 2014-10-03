package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVUL
 * Created		: 06.09.2010
 * Description	: VUL model class.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class TsVUL extends Teilsatz {
	private String mrn		= "";	// MRN-Nummer
	private String posnr	= "";	// Positionsnummer
	private String colan	= "";	// Anzahl Colli
									//	bei Beipack ist 0 anzugeben
	private String colar	= "";	// Packstückart
	private String colzc	= "";	// Collizeichen und-nummern
	
	/// CONSTRUCTOR(s)
		public TsVUL() {
			tsTyp	= "VUL";
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
		
		public String getColan() {
			return this.colan;
		}
		public void setColan(String colan) {
			this.colan	= Utils.checkNull(colan);
		}
		
		public String getColar() {
			return this.colar;
		}
		public void setColar(String colar) {
			this.colar	= Utils.checkNull(colar);
		}
		
		public String getColzc() {
			return this.colzc;
		}
		public void setColzc(String colzc) {
			this.colzc	= Utils.checkNull(colzc);
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
			colan	= fields[3];
			if (size < 5) {
				return;
			}
			colar	= fields[4];
			if (size < 6) {
				return;
			}
			colzc	= fields[5];
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
			buff.append(colan);
			buff.append(trenner);
			buff.append(colar);
			buff.append(trenner);
			buff.append(colzc);
			buff.append(trenner);
			
			return buff.toString();
		}

		@Override
		public boolean isEmpty() {						
			return (Utils.isStringEmpty(colan) && 
					Utils.isStringEmpty(colar) && 
					Utils.isStringEmpty(colzc));
		}
		
}
