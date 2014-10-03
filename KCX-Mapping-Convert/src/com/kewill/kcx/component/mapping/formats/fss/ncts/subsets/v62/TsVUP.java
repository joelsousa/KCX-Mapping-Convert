package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVUP<br>
 * Created		: 06.09.2010
 * Description	: VUP model class.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class TsVUP extends Teilsatz {
	private String mrn		= "";	// MRN-Nummer
	private String posnr	= "";	// Positionsnummer
	private String tnr		= "";	// Warennummer
	private String wabsch	= "";	// Warenbeschreibung
	private String rohmas	= "";	// Bruttogewicht
	private String eigmas	= "";	// Nettogewicht
	private String pfehlt	= "";	// Kennzeichen "Position fehlt"
	private String unstm	= "";	// Freitext "Unstimmigkeiten"
	
	/// CONSTRUCTOR(s)
		public TsVUP() {
			tsTyp	= "VUP";
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
		
		public String getTnr() {
			return this.tnr;
		}
		public void setTnr(String tnr) {
			this.tnr	= Utils.checkNull(tnr);
		}
		
		public String getWabsch() {
			return this.wabsch;
		}
		public void setWabsch(String wabsch) {
			this.wabsch	= Utils.checkNull(wabsch);
		}
		
		public String getRohmas() {
			return this.rohmas;
		}
		public void setRohmas(String rohmas) {
			this.rohmas	= Utils.checkNull(rohmas);
		}
		
		public String getEigmas() {
			return this.eigmas;
		}
		public void setEigmas(String eigmas) {
			this.eigmas	= Utils.checkNull(eigmas);
		}
		
		public String getPfehlt() {
			return this.pfehlt;
		}
		public void setPfehlt(String pfehlt) {
			this.pfehlt	= Utils.checkNull(pfehlt);
		}
		
		public String getUnstm() {
			return this.unstm;
		}
		public void setUnstm(String unstm) {
			this.unstm	= Utils.checkNull(unstm);
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
			tnr		= fields[3];
			if (size < 5) {
				return;
			}
			wabsch	= fields[4];
			if (size < 6) {
				return;
			}
			rohmas	= fields[5];
			if (size < 7) {
				return;
			}
			eigmas	= fields[6];
			if (size < 8) {
				return;
			}
			pfehlt	= fields[7];
			if (size < 9) {
				return;
			}
			unstm	= fields[8];
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
			buff.append(tnr);
			buff.append(trenner);
			buff.append(wabsch);
			buff.append(trenner);
			buff.append(rohmas);
			buff.append(trenner);
			buff.append(eigmas);
			buff.append(trenner);
			buff.append(pfehlt);
			buff.append(trenner);
			buff.append(unstm);
			buff.append(trenner);
			
			return buff.toString();
		}

		
		@Override
		public boolean isEmpty() {
			return (Utils.isStringEmpty(posnr) && 
					Utils.isStringEmpty(tnr) && 
					Utils.isStringEmpty(wabsch) && 
					Utils.isStringEmpty(rohmas) && 
					Utils.isStringEmpty(eigmas) && 
					Utils.isStringEmpty(pfehlt) && 
					Utils.isStringEmpty(unstm));
		}
}
