/*
 * Funktion    : TsVor.java
 * Titel       :
 * Erstellt    : 27.08.2008
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.formats.fss.common;

import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TsVOR<br>
 * Erstellt		: 14.11.2008<br>
 * Beschreibung	: Vorlaufsatz. 
 * 
 * @author kron
 * @version 1.0.00
 */
public class TsVOR extends Teilsatz {
	
	// blank important because empty string means that in splitting the subset the 
	// empty fields are not ncluded in the resulting array.
	// C.K: 020908
    private String man     = " ";   // Mandant
    private String nl      = " ";   // Niederlassung
    private String modul   = " ";   // Das Modul, zum dem die Nachrichten gehören, z.B. ZB.
    private String natyp   = " ";   // Der Typ der FSS-Nachrichten in der Datei.
    private String richt   = " ";   // Die Einarbeitungsrichtung in der FSS.
    private String gendat  = " ";   // Generierungsdatum der FSS-Datei (YYYYMMDD).
    private String genzei  = " ";   // Generierungszeit der FSS-Datei (HHMMSS).
    private String datnam  = " ";   // Name der FSS-Datei.
    private String versnr  = " ";   // Versionsnummer der FSS-Nachrichtenstruktur.
    private String kzloe   = " ";   // Kennzeichen Erhaltung eingearbeiteter FSS-Dateien.
    private String kzsta   = " ";   // Kennzeichen Setzen des Status nach Einarbeitung.
    private String kzein   = " ";   // Kennzeichen Behandlung von Einarbeitungsfehlern.
    private String kzmeld  = " ";   // Kennzeichen Fehlerrückmeldung.
    private String kzprot  = " ";   // Kennzeichen Protokolldruck.
    private String kzverf  = " ";   // Normal- oder Vereinfachtes Verfahren (NP/SP).
    private String kzmeth  = " ";   // Methode für KIDS.
    // Msg-ID bei ERR oder CON - Nachrichten von ZABIS steht hier die InReplyTo-Msg-ID 
    private String msgid   = " ";   // Nachrichten-ID.
    
    private String key     = null;  // Mandant + Niederlassung
    private String kewillId = null; // key gemappt in eine KewillId
    private String country = null;	// CountryCode aus DB
    
    // CK090505 inReplyToMsgID aus Teilsatz VORINF falls vorhanden
    // wird nur bei der Richtung FssToKids benutzt
    // private String inReplyToMsgID = null;
    // wird nicht benötigt!
    
    private String inReplyTo = "";   //EI20121005

    public TsVOR(String richtung) {
        tsTyp  = "VOR";
        richt  = richtung;
        gendat = FssUtils.getToday();
        genzei = FssUtils.getNow();
    }

    public void setFields(String[] fields) {
        tsTyp  = fields[0];
        man    = fields[1];
        nl     = fields[2];
        modul  = fields[3];
        natyp  = fields[4];
        richt  = fields[5];
        gendat = fields[6];
        genzei = fields[7];
        datnam = fields[8];
        versnr = fields[9];
        kzloe  = fields[10];
        kzsta  = fields[11];
        kzein  = fields[12];
        kzmeld = fields[13];
        kzprot = fields[14];
        if (fields.length >= 16) {
        	kzverf = fields[15];	
        }
        if (fields.length >= 17) {
        	kzmeth = fields[16];	
        }
        if (fields.length >= 18) {
        	msgid = fields[17];	
        }
        
        key = man.trim() + "-" + nl.trim();
    }

    public void mapHead2Vor(TsHead head, String richtung) {
        tsTyp  = "VOR";
        man    = head.getMan();
        nl     = head.getNl();
        modul  = head.getModul();
        natyp  = head.getMsgName();
        richt  = head.getRichtung();  
          
        if (Utils.isStringEmpty(richt)) {
        	 if (!Utils.isStringEmpty(richtung)) {  // EI20121005
             	richt = richtung;
             }
        } else {
        	if (richt.contains("_KD")) {  // EI20120806
        		richt = "A";
        	} else if (richt.contains("KD_")) {
        		richt = "E";        	
        	}
        }
       
        // 20110714180201
        // 20120216134745
        Utils.log("(TsVOR setHeadFields) head.getDateTime() = " + head.getDateTime());
        gendat = head.getDateTime().substring(0, 8);
        genzei = head.getDateTime().substring(8, 14);
        datnam = head.getDateiName();
        versnr = head.getVersion();
        kzloe  = "0";
        kzsta  = head.getStatus();
        kzein  = "0";
        kzmeld = head.getMeldungKz();
        kzprot = "0";
        kzverf = head.getVerfahrenKz();    
        kzmeth = "";    
        msgid  = head.getMsgid();  
        
        inReplyTo = head.getInReplyTo(); // EI20121005 
        
        key = man.trim() + "-" + nl.trim();        
		kewillId = head.getKewillId();    // EI20121204
		country = head.getCountry();      // EI20121204
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        // Wird vermutlich nicht benötigt
        // Neu CK090505
        // immer eine VORINF mit ausgeben die im 7. Feld 
        // die 4 durch "!" getrennte Felder MsgTyp, EtnTo, EtnFrom und MsgID enthält
        // Nur dann kann Zabis die MsgID extrahieren und confirms oder failures schreiben
//        buff = buff.append("VORINF");
//        buff = buff.append(trenner);
//        buff = buff.append("B");
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append(trenner);
//        buff = buff.append("IDS" + natyp + "!etnTo!etnFrom!" + msgid + '\n');
        // CK090505 Ende
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(man);
        buff = buff.append(trenner);
        buff = buff.append(nl);
        buff = buff.append(trenner);
        buff = buff.append(modul);
        buff = buff.append(trenner);
        buff = buff.append(natyp);
        buff = buff.append(trenner);
        buff = buff.append(richt);
        buff = buff.append(trenner);
        buff = buff.append(gendat);
        buff = buff.append(trenner);
        buff = buff.append(genzei);
        buff = buff.append(trenner);
        buff = buff.append(datnam);
        buff = buff.append(trenner);
        buff = buff.append(versnr);
        buff = buff.append(trenner);
        buff = buff.append(kzloe);
        buff = buff.append(trenner);
        buff = buff.append(kzsta);
        buff = buff.append(trenner);
        buff = buff.append(kzein);
        buff = buff.append(trenner);
        buff = buff.append(kzmeld);
        buff = buff.append(trenner);
        buff = buff.append(kzprot);
        buff = buff.append(trenner);
        buff = buff.append(kzverf);
        buff = buff.append(trenner);
        buff = buff.append(kzmeth);
        buff = buff.append(trenner);
        buff = buff.append(msgid);
        buff = buff.append(trenner);
        
        return new String(buff);
    }

    public boolean isEmpty() {
        /*
        if ( Utils.isStringEmpty(man)  && Utils.isStringEmpty(nl) && Utils.isStringEmpty(modul)
          && Utils.isStringEmpty(natyp) && Utils.isStringEmpty(richt) && Utils.isStringEmpty(gendat)
          && Utils.isStringEmpty(genzei) && Utils.isStringEmpty(datnam) && Utils.isStringEmpty(versnr)
          && Utils.isStringEmpty(kzloe)  && Utils.isStringEmpty(kzsta) && Utils.isStringEmpty(kzein)
          && Utils.isStringEmpty(kzmeld) && Utils.isStringEmpty(kzprot) && Utils.isStringEmpty(kzverf)
          && Utils.isStringEmpty(kzmeth) && Utils.isStringEmpty(msgid) )          
            return true;
        else
            return false;
            */
        return false;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = Utils.checkNull(man);
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = Utils.checkNull(nl);
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = Utils.checkNull(modul);
    }

    public String getNatyp() {
        return natyp;
    }

    public void setNatyp(String natyp) {
        this.natyp = Utils.checkNull(natyp);
    }

    public String getRicht() {
        return richt;
    }

    public void setRicht(String richt) {
        this.richt = Utils.checkNull(richt);
    }

    public String getGendat() {
        return gendat;
    }

    public void setGendat(String gendat) {
        this.gendat = Utils.checkNull(gendat);
    }

    public String getGenzei() {
        return genzei;
    }

    public void setGenzei(String genzei) {
        this.genzei = Utils.checkNull(genzei);
    }

    public String getDatnam() {
        return datnam;
    }

    public void setDatnam(String datnam) {
        this.datnam = Utils.checkNull(datnam);
    }

    public String getVersnr() {
        return versnr;
    }
    /* EI20121011
    public void setVersnr(String versnr) {
       this.versnr = versnr;
    }
    */
    public void setVersnr(String version) {         //EI20121011
    	if (Utils.isStringEmpty(version)) {  
    		return;    		
    	}
    	
    	if (!version.substring(0, 1).equals("0")) {
    		version = "0" + version;
    	} 
    	int len = version.length();	    		
    	for (int i = 0; len < 5; i++) {
    		version = version + "0";
    		len = version.length();
    	}
    	
    	this.versnr = version;
    }

    public String getKzloe() {
        return kzloe;
    }

    public void setKzloe(String kzloe) {
        this.kzloe = Utils.checkNull(kzloe);
    }

    public String getKzsta() {
        return kzsta;
    }

    public void setKzsta(String kzsta) {
        this.kzsta = Utils.checkNull(kzsta);
    }

    public String getKzein() {
        return kzein;
    }

    public void setKzein(String kzein) {
        this.kzein = Utils.checkNull(kzein);
    }

    public String getKzmeld() {
        return kzmeld;
    }

    public void setKzmeld(String kzmeld) {
        this.kzmeld = Utils.checkNull(kzmeld);
    }

    public String getKzprot() {
        return kzprot;
    }

    public void setKzprot(String kzprot) {
        this.kzprot = Utils.checkNull(kzprot);
    }

    public String getKzverf() {
        return kzverf;
    }

    public void setKzverf(String kzverf) {
        this.kzverf = Utils.checkNull(kzverf);
    }

    public String getKzmeth() {
        return kzmeth;
    }

    public void setKzmeth(String kzmeth) {
        this.kzmeth = Utils.checkNull(kzmeth);
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
    	//EI20130603: this.msgid = msgid;
        this.msgid = Utils.checkNull(msgid);
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKewillId() {
        return kewillId;
    }

    public void setKewillId(String kewillId) {
        this.kewillId = kewillId;
    }

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
// wird nicht benötigt CK090505
//	public String getInReplyToMsgID() {
//		return inReplyToMsgID;
//	}
//
//	public void setInReplyToMsgID(String inReplyToMsgID) {
//		this.inReplyToMsgID = inReplyToMsgID;
//	}
	
	public String getInReplyTo() {
		return inReplyTo;
	}
	public void setInReplyTo(String value) {
		this.inReplyTo = Utils.checkNull(value);
	}
}
