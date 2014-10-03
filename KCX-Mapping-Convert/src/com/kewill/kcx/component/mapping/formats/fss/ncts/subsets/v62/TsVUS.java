package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVUS<br>
 * Created		: 07.09.2010
 * Description	: TsVUS model class.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class TsVUS extends Teilsatz {
	private String mrn		= "";	// MRN-Nummer
	private String posnr	= "";	// Positionsnummer
	private String sgicod	= "";	// Code "Special Goods Information"
	private String sgimng	= "";	// Menge "Special Goods Information"
	
	/// CONSTRUCTOR(s)
		public TsVUS() {
			tsTyp	= "VUS";
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
		
		public String getSgicod() {
			return this.sgicod;
		}
		public void setSgicod(String sgicod) {
			this.sgicod	= Utils.checkNull(sgicod);
		}
		
		public String getSgimng() {
			return this.sgimng;
		}
		public void setSgimng(String sgimng) {
			this.sgimng	= Utils.checkNull(sgimng);
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
			sgicod	= fields[3];
			if (size < 5) {
				return;
			}
			sgimng	= fields[4];
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
			buff.append(sgicod);
			buff.append(trenner);
			buff.append(sgimng);
			buff.append(trenner);
			
			return buff.toString();
		}

		@Override
		public boolean isEmpty() {						
			return (Utils.isStringEmpty(sgicod) && 
					Utils.isStringEmpty(sgimng));
		}
}
