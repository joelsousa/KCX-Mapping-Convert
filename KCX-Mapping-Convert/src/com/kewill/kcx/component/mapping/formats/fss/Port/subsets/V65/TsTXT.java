package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag Textangaben   / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsTXT extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String kenn	= "";	 // Kennung zum Text
	 private String pos		= "";	 // Positionsnummer
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3
	 private String txt 	= "";	 // Textangaben
	
    public TsTXT() {
	    tsTyp = "TXT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    kenn = fields[2];
	    if (size < 4) { return; }
	    pos = fields[3];
	    if (size < 5) { return; }
	    pe2lfd = fields[4];
	    if (size < 6) { return; }
	    pe3lfd = fields[5];
	    if (size < 7) { return; }
	    txt = fields[6];	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(kenn);
    	buff.append(trenner);
    	buff.append(pos);
    	buff.append(trenner);
    	buff.append(pe2lfd);
    	buff.append(trenner);
    	buff.append(pe3lfd);
    	buff.append(trenner);
    	buff.append(txt);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getKenn() {
    	 return kenn;
    }
    public void setKenn(String kenn) {
    	this.kenn = Utils.checkNull(kenn);
    }

    public String getPos() {
    	 return pos;
    }
    public void setPos(String pos) {
    	this.pos = Utils.checkNull(pos);
    }

    public String getPe2lfd() {
    	 return pe2lfd;
    }
    public void setPe2lfd(String pe2lfd) {
    	this.pe2lfd = Utils.checkNull(pe2lfd);
    }

    public String getPe3lfd() {
    	 return pe3lfd;
    }
    public void setPe3lfd(String pe3lfd) {
    	this.pe3lfd = Utils.checkNull(pe3lfd);
    }
    
    public String getTxt() {
   	 	return txt;
    }
    public void setTxt(String argument) {
    	this.txt = Utils.checkNull(argument);
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(txt);		  
    }

}

