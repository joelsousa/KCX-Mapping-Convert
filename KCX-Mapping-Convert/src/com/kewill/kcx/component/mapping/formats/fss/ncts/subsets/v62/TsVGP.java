package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVGP.<br>
 * Created		: 18.05.2011<br>
 * Description	: 
 * 
 * @author	: iwaniuk
 * @version	: 6.4.00
 *
 */
public class TsVGP extends Teilsatz {
	private String sicbsc	= "";	// 
	private String guacde	= "";	// 
	private String guatxt	= "";	// 

	/// CONSTRUCTOR(s)
		public TsVGP() {
			tsTyp	= "VGP";
		}
	
	/// SETTER & GETTER Methods
		public String getSicbsc() {
			return this.sicbsc;
		}
		public void setSicbsc(String argument) {
			this.sicbsc	= Utils.checkNull(argument);
		}
		
		public String getGuacde() {
			return this.guacde;
		}
		public void setGuacde(String argument) {
			this.guacde	= Utils.checkNull(argument);
		}
				
		
		public String getGuatxt() {
			return this.guatxt;
		}
		public void setGuatxt(String argument) {
			this.guatxt	= Utils.checkNull(argument);
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
			sicbsc	= fields[1];
			if (size < 3) {
				return;
			}
			guacde	= fields[2];
			if (size < 4) {
				return;
			}
			guatxt	= fields[3];			
		}
	
		@Override
		public String teilsatzBilden() {
			StringBuffer buff	= new StringBuffer();
			
			buff.append(tsTyp);
			buff.append(trenner);
			buff.append(sicbsc);
			buff.append(trenner);
			buff.append(guacde);
			buff.append(trenner);
			buff.append(guatxt);
			buff.append(trenner);		
			
			return buff.toString();
		}

		@Override
		public boolean isEmpty() {
			return (Utils.isStringEmpty(sicbsc) && 
					Utils.isStringEmpty(guacde) && 
					Utils.isStringEmpty(guatxt));					
		}
}
