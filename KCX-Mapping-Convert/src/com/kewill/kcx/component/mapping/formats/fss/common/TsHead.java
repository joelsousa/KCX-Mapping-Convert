package com.kewill.kcx.component.mapping.formats.fss.common; 

import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module                :       ZABIS V70.
 * Created               :       10.05.2012
 * Description    		 :       z.Z nur fuer PORT BL
 *
 * @author                      iwaniuk   
 * @version 1.0.00
 */

public class TsHead extends Teilsatz {
  
	 private String struktur      = "";   //char(3) z.Z.nru 1 zulaessig
	 private String art           = "";   //char(3) inh,fss gpo, zip,exe, usw,
	 private String richtung      = "";   //char(7) VVV_NNN;  von_nach: KD_ZO (kunde_zoll)
     private String zeichensatz   = "";   //char(4) 1-15 = ISO-8859-X
     private String dateiname     = "";   //char(256) Name der FSS-Datei.
     private String version       = "";   //char(5) VVSPP; VersionSubversionPatch: 7000
     private String kdnr     	  = "";   //char(4) zabis-kdnr
     private String tin  		  = "";   //char(17) EORI-TIN
     private String nlnr          = "";   //char(4) officielle NL des Teilnehmers bei Zoll
     private String vbtyp   	  = "";   //char(3) Verfahrensbereich bei Zoll: GPO
     private String eudstnr       = "";   //char(8) Dienststelle-ATLAS: DE009996, DE009999
     private String msgname       = "";  //char(6) EDIFACT-Name, FSS-Name: EZA...
     private String subversion    = "";  //char(5) A.12, 0.93 usw.
     private String testflag   	  = "";  //char(1): P(roduktion),  T(est)
     private String datetime   	  = "";  //char(14): YYYYMMDDHHMISS        
     private String man     	  = "";   //char(4)  Mandant
     private String nl      	  = "";   //char(3)  Niederlassung
     private String modul  	      = "";   //char(3) Zabis-Modul: ZB, VE, usw
     private String status  	  = "";   //char(1): 0=eingearbeitet, 1=unvollst. 2=sendebereit 
     private String meldungkz  	  = "";   //char(1):J=Fehlermeldungen weiterleiten, N=keine Fehlermeldungen
     private String verfahrenkz   = "";   //char(2): NP=normal procedur, SP=simplified procedur
     private String msgid         = "";   //char(35) Nachrichten-ID.        
     private String inreplyto     = "";   //char(35) msgid d. urspr. nachricht
     private String subtyp        = "";   //char(3) art von allgemein: 0=allgemein ist leer
     private String allgemein     = "";   //char(512): todo-feld 
     
     //EI20121229: falls nur noch HEAD verwendet wird, muss es alles haben was VOR hat	
     private String key     = null;  // Mandant + "-" + Niederlassung
     private String kewillId = null; // 
     private String country = null;	 // DE oder CH
     
    public TsHead() {
    	tsTyp = "HEAD";                            
    }
    public TsHead(String value) {
        tsTyp = value;       
        datetime = FssUtils.getDateTime();         
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	
    	if (size < 1) { return; }
    	tsTyp  = fields[0];
    	if (size < 2) { return; }
    	struktur    = fields[1];
    	if (size < 3) { return; }
    	art     = fields[2];
    	if (size < 4) { return; }
    	richtung  = fields[3];
    	if (size < 5) { return; }
    	zeichensatz  = fields[4];    	
    	if (size < 6) { return; }
    	dateiname  = fields[5];
    	if (size < 7) { return; }
    	version = fields[6];
    	if (size < 8) { return; }
    	kdnr = fields[7];
    	if (size < 9) { return; }
    	tin = fields[8];
    	if (size < 10) { return; }
    	nlnr = fields[9];
    	if (size < 11) { return; }
    	vbtyp  = fields[10];
    	if (size < 12) { return; }
    	eudstnr  = fields[11];
    	if (size < 13) { return; }
    	msgname  = fields[12];
    	if (size < 14) { return; }
    	subversion = fields[13];
    	if (size < 15) { return; }
    	testflag = fields[14];
    	if (size < 16) { return; }
    	datetime     = fields[15];
    	if (size < 17) { return; }
    	man = fields[16];
    	if (size < 18) { return; }
    	nl = fields[17];
    	if (size < 19) { return; }
    	modul = fields[18];
    	if (size < 20) { return; }
    	status = fields[19];  
    	if (size < 21) { return; }
    	meldungkz     = fields[20]; 
    	if (size < 22) { return; }
    	verfahrenkz     = fields[21];
    	if (size < 23) { return; }
    	msgid     = fields[22];
    	if (size < 24) { return; }
    	inreplyto     = fields[23];
    	if (size < 25) { return; }
    	subtyp     = fields[24];
    	if (size < 26) { return; }
    	allgemein     = fields[25];
    	
    	key = man.trim() + "-" + nl.trim();
    }

        public String teilsatzBilden() {
            StringBuffer buff = new StringBuffer();
            
            buff = buff.append(tsTyp);
            buff = buff.append(trenner);
            buff = buff.append(struktur);
            buff = buff.append(trenner);
            buff = buff.append(art);
            buff = buff.append(trenner);
            buff = buff.append(richtung);
            buff = buff.append(trenner);
            buff = buff.append(zeichensatz);
            buff = buff.append(trenner);
            buff = buff.append(dateiname);
            buff = buff.append(trenner);
            buff = buff.append(version);
            buff = buff.append(trenner);
            buff = buff.append(kdnr);
            buff = buff.append(trenner);
            buff = buff.append(tin);
            buff = buff.append(trenner);
            buff = buff.append(nlnr);
            buff = buff.append(trenner);
            buff = buff.append(vbtyp);
            buff = buff.append(trenner);
            buff = buff.append(eudstnr);
            buff = buff.append(trenner);
            buff = buff.append(msgname);
            buff = buff.append(trenner);
            buff = buff.append(subversion);
            buff = buff.append(trenner);
            buff = buff.append(testflag);
            buff = buff.append(trenner);
            buff = buff.append(datetime);
            buff = buff.append(trenner);
            buff = buff.append(man);
            buff = buff.append(trenner);
            buff = buff.append(nl);
            buff = buff.append(trenner);
            buff = buff.append(modul);
            buff = buff.append(trenner);
            buff = buff.append(status);
            buff = buff.append(trenner);
            buff = buff.append(meldungkz);
            buff = buff.append(trenner);
            buff = buff.append(verfahrenkz);
            buff = buff.append(trenner);
            buff = buff.append(msgid);
            buff = buff.append(trenner);
            buff = buff.append(inreplyto);
            buff = buff.append(trenner);
            buff = buff.append(subtyp);
            buff = buff.append(trenner);
            buff = buff.append(allgemein);
            //buff = buff.append(trenner); //EI20131001 warum ist es hier auskommentiert???
            
            return new String(buff);
        }

        public boolean isEmpty() {
            return (Utils.isStringEmpty(man) && Utils.isStringEmpty(nl) && Utils.isStringEmpty(modul));  
            //rest es eigentlich unwesentlich
        }
        
		public String getArt() {
			return art;
		}
		public void setArt(String art) {
			this.art = Utils.checkNull(art);
		}
		
		public String getStruktur() {
			return struktur;
		}
		public void setStruktur(String value) {
			this.struktur = Utils.checkNull(value);
		}
		
		public String getRichtung() {
			return richtung;
		}
		public void setRichtung(String richtung) {
			this.richtung = Utils.checkNull(richtung);
		}
		
		public String getZeichensatz() {
			return zeichensatz;
		}
		public void setZeichensatz(String zeichensatz) {
			this.zeichensatz = Utils.checkNull(zeichensatz);
		}
		
		public String getDateiName() {
			return dateiname;
		}
		public void setDateiName(String dateiname) {
			this.dateiname = Utils.checkNull(dateiname);
		}
		
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = Utils.checkNull(version);
		}
		public String setVersionFromVOR(String versnr) {   //EI20121011				
			if (Utils.isStringEmpty(versnr)) {
				return "";
			}	
			String temp = Utils.removeDots(versnr);				
			if (!temp.substring(0, 1).equals("0")) {
				temp = "0" + temp;
	    	} 	
			int len = temp.length();	
	    	for (int i = 0; len < 5; i++) {
	    		temp = temp + "0";
	    		len = temp.length();
	    	}			
			return  temp;
		}
		
		public String getKdnr() {
			return kdnr;
		}
		public void setKdnr(String kdnr) {
			this.kdnr = Utils.checkNull(kdnr);
		}
		
		public String getTin() {
			return tin;
		}
		public void setTin(String tin) {
			this.tin = Utils.checkNull(tin);
		}
		
		public String getNlnr() {
			return nlnr;
		}
		public void setNlnr(String nlnr) {
			this.nlnr = Utils.checkNull(nlnr);
		}
		
		public String getVbtyp() {
			return vbtyp;
		}
		public void setVbtyp(String vbtyp) {
			this.vbtyp = Utils.checkNull(vbtyp);
		}
		
		public String getEudstnr() {
			return eudstnr;
		}
		public void setEudstnr(String eudstnr) {
			this.eudstnr = Utils.checkNull(eudstnr);
		}
		
		public String getMsgName() {
			return msgname;
		}
		public void setMsgName(String techname) {
			this.msgname = Utils.checkNull(techname);
		}
		
		public String getSubVersion() {
			return subversion;
		}
		public void setSubVersion(String subversion) {
			this.subversion = Utils.checkNull(subversion);
		}
		
		public String getTestFlag() {
			return testflag;
		}
		public void setTestFlag(String testflag) {
			this.testflag = Utils.checkNull(testflag);
		}
		
		public String getDateTime() {
			return datetime;
		}
		public void setDateTime(String datetime) {
			this.datetime = Utils.checkNull(datetime);
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
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = Utils.checkNull(status);
		}
		
		public String getMeldungKz() {
			return meldungkz;
		}
		public void setMeldungkz(String meldungkz) {
			this.meldungkz = Utils.checkNull(meldungkz);
		}
		
		public String getVerfahrenKz() {
			return verfahrenkz;
		}
		public void setVerfahrenKz(String verfahrenkz) {
			this.verfahrenkz = Utils.checkNull(verfahrenkz);
		}
		
		public String getMsgid() {
			return msgid;
		}
		public void setMsgid(String msgid) {
			this.msgid = Utils.checkNull(msgid);
		}
		
		public String getInReplyTo() {
			return inreplyto;
		}
		public void setInReplyTo(String inraplyto) {
			this.inreplyto = Utils.checkNull(inraplyto);
		}
		
		public String getSubtyp() {
			return subtyp;
		}
		public void setSubtyp(String subtyp) {
			this.subtyp = Utils.checkNull(subtyp);
		}
		
		public String getAllgemein() {
			return allgemein;
		}
		public void setAllgemein(String allgemein) {
			this.allgemein = Utils.checkNull(allgemein);
		}

		//
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
		
			
		public void mapVor2Head(TsVOR vor) {
			if (vor == null) {
				return;
			}
			String dateTime = "";
			String richt = ""; 
			
			dateTime = vor.getGendat();
			if (!Utils.isStringEmpty(vor.getGendat())) {
				dateTime = vor.getGendat();
				if (!Utils.isStringEmpty(vor.getGenzei())) {
					dateTime = dateTime + vor.getGenzei();
				}
			}
			if (!Utils.isStringEmpty(vor.getRicht())) {
				if (vor.getRicht().equalsIgnoreCase("A")) {
					richt = "ZAB_KD";
				} else {
					richt = "KD_ZAB";
				}
			}
			
			struktur      = "001";  
		    art           = "fss";  			
			richtung      = richt; 			 
			zeichensatz  = "1"; 
			dateiname    = vor.getDatnam();  
			//version      = vor.getVersnr();  //7.00 ==> 07000
			version      = this.setVersionFromVOR(vor.getVersnr()); 
			kdnr     	  = "";  
			tin          = "";  
			nlnr         = "";  
			vbtyp   	  = "";  
			eudstnr      = "";  
			msgname      = vor.getNatyp();  
			subversion   = "";  
			testflag     = "";  
			datetime     = dateTime;  
			man     	  = vor.getMan();  
			nl      	  = vor.getNl();  
			modul  	  = vor.getModul();  
			status  	  = vor.getKzsta();  
			meldungkz    = vor.getKzmeld();  
			verfahrenkz  = vor.getKzverf();  
			msgid        = vor.getMsgid();  
			inreplyto    = "";  
			subtyp       = "000";  
			allgemein    = "";
			
			key = vor.getKey();
			kewillId = vor.getKewillId();   // EI20121204
			country = vor.getCountry();     // EI20121204
		}
      
    }
