package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnCAN;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnCND;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnDTN;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnFLT;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnLine;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnOCI;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnWBI;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Manifest<br>
 * Created		: 17.12.2013<br>
 * Description	: MsgCSN (kein FSS)
 * 				  
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgCSN extends FssMessage {
	
	private CsnLine line1;
	private CsnLine line2;
	private CsnLine line3;
	private CsnWBI wbi;
	private CsnFLT flt;
	private CsnCAN can;
	private CsnDTN dtn;
	private CsnCND cnd;
	private CsnOCI oci;
	
	public MsgCSN() {
		super();  
	}

	public CsnLine getLine1() {
		return line1;
	}
	public void setLine1(CsnLine line1) {
		this.line1 = line1;
	}
	public void setLine1(String text) {
		if (Utils.isStringEmpty(text)) {
			return;	
		}
		CsnLine line = new CsnLine();
		line.setLine(text);
		this.line1 = line;
	}
	
	public CsnLine getLine2() {
		return line2;
	}
	public void setLine2(CsnLine line2) {
		this.line2 = line2;
	}
	public void setLine2(String text) {
		if (Utils.isStringEmpty(text)) {
			return;	
		}
		CsnLine line = new CsnLine();
		line.setLine(text);
		this.line2 = line;
	}
	
	public CsnLine getLine3() {
		return line3;
	}
	public void setLine3(CsnLine line3) {
		this.line3 = line3;
	}
	public void setLine3(String text) {
		if (Utils.isStringEmpty(text)) {
			return;	
		}
		CsnLine line = new CsnLine();
		line.setLine(text);
		this.line3 = line;
	}
	
	public CsnWBI getWbi() {
		return wbi;
	}
	public void setWbi(CsnWBI wbi) {
		this.wbi = wbi;
	}
	public void setWbiAwb(String awb) {
		if (Utils.isStringEmpty(awb)) {
			return;	
		}
		CsnWBI line = new CsnWBI();
		line.setAwb(awb);
		this.wbi = line;		
	}
	
	public CsnFLT getFlt() {
		return flt;
	}
	public void setFlt(CsnFLT flt) {
		this.flt = flt;
	}
	public void setFltNr(String nr) {
		if (Utils.isStringEmpty(nr)) {
			return;	
		}
		CsnFLT line = new CsnFLT();
		line.setFlightNr(nr);
		this.flt = line;		
	}	
	
	public CsnCAN getCan() {
		return can;
	}
	public void setCan(CsnCAN can) {
		this.can = can;
	}
	public void setCanDst(String dst) {
		if (Utils.isStringEmpty(dst)) {
			return;	
		}
		CsnCAN line = new CsnCAN();
		line.setDst(dst);
		this.can = line;		
	}
	
	public CsnDTN getDtn() {
		return dtn;
	}
	public void setDtn(CsnDTN dtn) {
		this.dtn = dtn;
	}
	public void setDtnDateTime(String datetime) {
		//datetime ist: YYYMMDDhhmm
		if (Utils.isStringEmpty(datetime)) {
			return;	
		}
		int len = datetime.length();
		CsnDTN line = new CsnDTN();
		if (len > 7) {
			//String day = datetime.substring(6, 8);
			//String mon = this.calculateMonth(datetime.substring(4, 6));
			//line.setDayMon(day + mon);				
			line.setDayMon(calculateDayMon(datetime));			
		}
		if (len > 11) {
			line.setTime(datetime.substring(8, 12));
		}		
		this.dtn = line;		
	}
    
	public CsnCND getCnd() {
		return cnd;
	}
	public void setCnd(CsnCND cnd) {
		this.cnd = cnd;
	}
	public void setCnd(String mrn, String anz) {
		if (Utils.isStringEmpty(mrn)) {
			return;	
		}
		CsnCND line = new CsnCND();
		line.setMrn(mrn);
		line.setAnz(anz);
		this.cnd = line;		
	}
	public CsnOCI getOci() {
		return oci;
	}
	public void setOci(CsnOCI oci) {
		this.oci = oci;
	}
	public void setOciMrn(String mrn) {
		if (Utils.isStringEmpty(mrn)) {
			return;	
		}
		CsnOCI line = new CsnOCI();
		line.setMrn(mrn);		
		this.oci = line;		
	}
	
	//public String getCsnStringOrigin() throws FssException {
	public String getCsnString() throws FssException {
		String res = "";	
		
		if (line1 != null) {	
			res = appendString(res, line1.teilsatzBilden());	
		}
		if (line2 != null) {
			res = appendString(res, line2.teilsatzBilden());
		}
		if (line3 != null) {
			res = appendString(res, line3.teilsatzBilden());
		}
		if (wbi != null) {	 
			res = appendString(res, wbi.teilsatzBilden());	
		}
		if (flt != null) {	 
			res = appendString(res, flt.teilsatzBilden());	
		}
		if (can != null) {	 
			res = appendString(res, can.teilsatzBilden());	
		}
		if (dtn != null) {	 
			res = appendString(res, dtn.teilsatzBilden());	
		}
		if (cnd != null) {	 
			res = appendString(res, cnd.teilsatzBilden());	
		}
		if (oci != null) {	 
			res = appendString(res, oci.teilsatzBilden());	
		}
		
		return res;
	}   	
	public String getCsnStringWithNewlines() throws FssException {
		String res = "";	
		
		//res = "start\n";	
		if (line1 != null) {	
			res = res + line1.teilsatzBilden() + "\n";
		}
		if (line2 != null) {
			res = res + line2.teilsatzBilden() + "\n";	
		}
		if (line3 != null) {
			res = res + line3.teilsatzBilden() + "\n";
		}
		if (wbi != null) {	 
			res = res + wbi.teilsatzBilden() + "\n";	
		}
		if (flt != null) {	 
			res = res + flt.teilsatzBilden() + "\n";	
		}
		if (can != null) {	 
			res = res + can.teilsatzBilden() + "\n";	
		}
		if (dtn != null) {	 
			res = res + dtn.teilsatzBilden() + "\n";
		}
		if (cnd != null) {	 
			res = res + cnd.teilsatzBilden() + "\n";	
		}
		if (oci != null) {	 
			res = res + oci.teilsatzBilden() + "\n";	
		}
		//res = res + "end";
		
		return res;
	}   
	public String calculateDayMon(String datetime) {
		//datetime is: YYYMMDDhhmm		
		if (Utils.isStringEmpty(datetime)) {
			return "";	
		}
		
		String dayMon = "";
		int len = datetime.length();
		
		if (len > 7) {
			String day = datetime.substring(6, 8);
			String mon = this.calculateMonth(datetime.substring(4, 6));
			if (!Utils.isStringEmpty(day) && !Utils.isStringEmpty(mon)) {		
				dayMon = day + mon;	
			}
		}
		
		return dayMon;
	}
	private String calculateMonth(String mm) {
		String mon = "";
		if (Utils.isStringEmpty(mm)) {
			return "XXX";
		}
		if (mm.length() != 2) {
			return "XXX";
		}
		
		if (mm.equals("01")) {
			mon = "JAN";
		} else if (mm.equals("02")) {
			mon = "FEB";
		} else if (mm.equals("03")) {
			mon = "MAR";
		} else if (mm.equals("04")) {
			mon = "APR";
		} else if (mm.equals("05")) {
			//mon = "MAI";
			mon = "MAY";
		} else if (mm.equals("06")) {
			mon = "JUN";
		} else if (mm.equals("07")) {
			mon = "JUL";
		} else if (mm.equals("08")) {
			mon = "AUG";
		} else if (mm.equals("09")) {
			mon = "SEP";
		} else if (mm.equals("10")) {
			mon = "OCT";
		} else if (mm.equals("11")) {
			mon = "NOV";
		} else if (mm.equals("12")) {
			mon = "DEC";
		} else {
			mon = "XXX";
		}
		
		return mon;
	}
}


