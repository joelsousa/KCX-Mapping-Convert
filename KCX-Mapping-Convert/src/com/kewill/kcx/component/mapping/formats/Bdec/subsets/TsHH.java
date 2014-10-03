/*
* Function    : TsHH.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : Header for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsHH<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : Header for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsHH extends Teilsatz {

    private String clientRef 		  = "";       //
    private String messageType 		  = "";       //
    private String capFlag 			  = "";       //
    private String localCleranceFlag  = "";       //
 	private String declnCrrn 		  = "";      //  
    private String decltRep 		  = "";      //  
    private String destCntry  		  = "";      // 
    private String ePlaLdg  		  = "";      // 
    private String exitOffice 		  = "";      // 
    private String gdsArrDtmInld      = "";      // 
    private String gdsDepDtmInld      = "";      // 
    private String declnUcr           = "";      // 
    private String declnPartNo        = "";      // 
    private String masterUcr          = "";      // 
    private String firDan             = "";      // 
    private String firDanPfx          = "";      // 
    private String trptModeCode       = "";      // 
    private String carrierName        = "";      //  
    private String trptId             = "";      // 
    private String trptCntry          = "";      //
    
    private String trptModeInld       = "";      //  
    private String trptIdInld         = "";  
    private String cnsgrShortname     = "";       //
    private String cnsgeShortname     = "";       //
    private String desltShortname     = "";       //
    private String gconTurn           = "";       //
 	private String rcnsgrTurn         = "";      //  
    private String rcnsgeTurn         = "";      //  
    private String permShortname      = "";      // 
    private String invTotAc           = "";      // 
    private String invCrrn            = "";      // 
    private String totPkgs            = "";      // 
    private String gdsLocn            = "";      // 
    private String gdsLocnCntry       = "";      // 
    private String gdsShedId          = "";      // 
    private String ibClaimRef         = "";      // 
    private String ibClaimType        = "";      // 
    private String ibRegNo            = "";      // 
    private String ibGan              = "";      // 
    private String clientInfo         = "";      //  
    private String declnType          = "";      //
    
    private String acptncDTM          = "";      // YYYYMMDDHHMM
    private String dispCntry          = "";      //  
    private String farpCode           = "";  
    private String tdrOwnRefEnt       = "";       //
    private String atrptCostAc        = "";       //
    private String frgtChgeAc         = "";       //
    private String frgtChgeCrrn       = "";       //
 	private String frgtAprtCode       = "";      //  
    private String insAmtAc           = "";      //  
    private String insAmtCrrn         = "";      // 
    private String ocdAc              = "";      // 
    private String ocdCrrn            = "";      // 
    private String vatAdjtAc          = "";      // 
    private String vatAdjtCrrn        = "";      // 
    private String invDamtAc          = "";      // 
    private String invDamtCrrn        = "";      // 
    private String invDpct            = "";      // 
    private String location           = "";      // 
    private String iDefaultPlace      = "";      // 
    private String templateName       = "";      // 
    private String userRef            = "";      //
    
    private String mrn                = "";      // 
    private String scndDan            = "";      // 
    private String scndDanPfx         = "";      //  
    private String notifyShortname    = "";       
    private String reprShortname      = "";       //
    private String euArrLocnCode      = "";       //
    private String plaUldgCode        = "";       //
    private String trptChgeMop        = "";       //
 	private String spoffShortname     = "";      //  
    private String stc                = "";      //  
    private String spCirc             = "";      // 
    private String reason             = "";      // 
    private String transit            = "";      // 
    private String goodsDepDT         = "";      //EI20120123;  YYYYMMDD
    private String emcsFlag           = "";      //EI20120123;  J/N   
    
    public TsHH() {
        tsTyp = "HH";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsHH: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        clientRef       = fields[1];
        if (size < 3) { return; }
        messageType     = fields[2];
        if (size < 4) { return; }
        capFlag         = fields[3];
        if (size < 5) { return; }
        localCleranceFlag = fields[4];    
        if (size < 6) { return; }
        declnCrrn       = fields[5];
        if (size < 7) { return; }
        decltRep        = fields[6];
        if (size < 8) { return; }
        destCntry       = fields[7]; 
        if (size < 9) { return; }
        ePlaLdg         = fields[8];
        if (size < 10) { return; }
        exitOffice      = fields[9];
        if (size < 11) { return; }
        gdsArrDtmInld   = fields[10];       
        if (size < 12) { return; }
        gdsDepDtmInld   = fields[11];
        if (size < 13) { return; }
        declnUcr        = fields[12];
        if (size < 14) { return; }
        declnPartNo         = fields[13];
        if (size < 15) { return; }
        masterUcr       = fields[14];
        if (size < 16) { return; }
        firDan          = fields[15];            
        if (size < 17) { return; }
        firDanPfx       = fields[16];
        if (size < 18) { return; }
        trptModeCode    = fields[17]; 
        if (size < 19) { return; }
        carrierName     = fields[18];
        if (size < 20) { return; }
        trptId          = fields[19];
        if (size < 21) { return; }
        trptCntry       = fields[20];         
        if (size < 22) { return; }
        trptModeInld    = fields[21];
        if (size < 23) { return; }
        trptIdInld      = fields[22];
        if (size < 24) { return; }
        cnsgrShortname  = fields[23];
        if (size < 25) { return; }
        cnsgeShortname  = fields[24];    
        if (size < 26) { return; }
        desltShortname  = fields[25];
        if (size < 27) { return; }
        gconTurn        = fields[26];
        if (size < 28) { return; }
        rcnsgrTurn      = fields[27]; 
        if (size < 29) { return; }
        rcnsgeTurn      = fields[28];
        if (size < 30) { return; }
        permShortname   = fields[29];
        if (size < 31) { return; }
        invTotAc        = fields[30];         
        if (size < 32) { return; }
        invCrrn         = fields[31];
        if (size < 33) { return; }
        totPkgs         = fields[32];
        if (size < 34) { return; }
        gdsLocn         = fields[33];
        if (size < 35) { return; }
        gdsLocnCntry    = fields[34];    
        if (size < 36) { return; }
        gdsShedId       = fields[35];
        if (size < 37) { return; }
        ibClaimRef      = fields[36];    		
        if (size < 38) { return; }
        ibClaimType     = fields[37]; 
        if (size < 39) { return; }
        ibRegNo         = fields[38];
        if (size < 40) { return; }
        ibGan         	= fields[39];
        if (size < 41) { return; }
        clientInfo      = fields[40]; 
        if (size < 42) { return; }
        declnType       = fields[41];
        if (size < 43) { return; }
        acptncDTM       = fields[42];
        if (size < 44) { return; }
        dispCntry       = fields[43];
        if (size < 45) { return; }
        farpCode        = fields[44];
        if (size < 46) { return; }
        tdrOwnRefEnt    = fields[45];            
        if (size < 47) { return; }
        atrptCostAc     = fields[46];
        if (size < 48) { return; }
        frgtChgeAc      = fields[47]; 
        if (size < 49) { return; }
        frgtChgeCrrn    = fields[48];
        if (size < 50) { return; }
        frgtAprtCode    = fields[49];
        if (size < 51) { return; }
        insAmtAc        = fields[50]; 
        if (size < 52) { return; }
        insAmtCrrn      = fields[51];
        if (size < 53) { return; }
        ocdAc           = fields[52];
        if (size < 54) { return; }
        ocdCrrn         = fields[53];
        if (size < 55) { return; }
        vatAdjtAc       = fields[54];    
        if (size < 56) { return; }
        vatAdjtCrrn     = fields[55];
        if (size < 57) { return; }
        invDamtAc       = fields[56];
        if (size < 58) { return; }
        invDamtCrrn     = fields[57]; 
        if (size < 59) { return; }
        invDpct         = fields[58];
        if (size < 60) { return; }
        location        = fields[59];
        if (size < 61) { return; }
        iDefaultPlace   = fields[60];    
        if (size < 62) { return; }
        templateName    = fields[61];
        if (size < 63) { return; }
        userRef         = fields[62];
        if (size < 64) { return; }
        mrn             = fields[63];
        if (size < 65) { return; }
        scndDan         = fields[64];    
        if (size < 66) { return; }
        scndDanPfx      = fields[65];
        if (size < 67) { return; }
        notifyShortname = fields[66];    
        if (size < 68) { return; }
        reprShortname   = fields[67]; 
        if (size < 69) { return; }
        euArrLocnCode   = fields[68];
        if (size < 70) { return; }
        plaUldgCode     = fields[69];
        if (size < 71) { return; }
        trptChgeMop     = fields[70];    
        if (size < 72) { return; }
        spoffShortname  = fields[71];
        if (size < 73) { return; }
        stc             = fields[72];
        if (size < 74) { return; }
        spCirc          = fields[73];
        if (size < 75) { return; }
        reason          = fields[74];    
        if (size < 76) { return; }
        transit         = fields[75];        
        if (size < 77) { return; }
        goodsDepDT      = fields[76];    
        if (size < 78) { return; }
        emcsFlag        = fields[77]; 
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(clientRef);
        buff.append(trenner);
        buff.append(messageType);
        buff.append(trenner);
        buff.append(capFlag);
        buff.append(trenner);        
        buff.append(localCleranceFlag);
        buff.append(trenner);	
        buff.append(declnCrrn);
        buff.append(trenner);	
        buff.append(decltRep);
        buff.append(trenner);	
        buff.append(destCntry);
        buff.append(trenner);	
        buff.append(ePlaLdg);
        buff.append(trenner);	
        buff.append(exitOffice);
        buff.append(trenner);	
        buff.append(gdsArrDtmInld);
        buff.append(trenner);	
        buff.append(gdsDepDtmInld);
        buff.append(trenner);	
        buff.append(declnUcr);
        buff.append(trenner);	
        buff.append(declnPartNo);
        buff.append(trenner);
        buff.append(masterUcr);
        buff.append(trenner);	
        buff.append(firDan);
        buff.append(trenner);	
        buff.append(firDanPfx);
        buff.append(trenner);	
        buff.append(trptModeCode);
        buff.append(trenner);	
        buff.append(carrierName);
        buff.append(trenner);	
        buff.append(trptId);
        buff.append(trenner);	
        buff.append(trptCntry);
        buff.append(trenner);	
        buff.append(trptModeInld);
        buff.append(trenner);	
        buff.append(trptIdInld);
        buff.append(trenner);
        buff.append(cnsgrShortname);
        buff.append(trenner);        
        buff.append(cnsgeShortname);
        buff.append(trenner);	
        buff.append(desltShortname);
        buff.append(trenner);	
        buff.append(gconTurn);
        buff.append(trenner);	
        buff.append(rcnsgrTurn);
        buff.append(trenner);	
        buff.append(rcnsgeTurn);
        buff.append(trenner);	
        buff.append(permShortname);
        buff.append(trenner);	
        buff.append(invTotAc);
        buff.append(trenner);	
        buff.append(invCrrn);
        buff.append(trenner);	
        buff.append(totPkgs);
        buff.append(trenner);	
        buff.append(gdsLocn);
        buff.append(trenner);	
        buff.append(gdsLocnCntry);
        buff.append(trenner);	 
        buff.append(gdsShedId);
        buff.append(trenner);	
        buff.append(ibClaimRef);
        buff.append(trenner);	
        buff.append(ibClaimType);
        buff.append(trenner);	
        buff.append(ibRegNo);
        buff.append(trenner);	
        buff.append(ibGan);
        buff.append(trenner);
        buff.append(clientInfo);
        buff.append(trenner);	
        buff.append(declnType);
        buff.append(trenner);
        buff.append(acptncDTM);
        buff.append(trenner);        
        buff.append(dispCntry);
        buff.append(trenner);	
        buff.append(farpCode);
        buff.append(trenner);	
        buff.append(tdrOwnRefEnt);
        buff.append(trenner);	
        buff.append(atrptCostAc);
        buff.append(trenner);	
        buff.append(frgtChgeAc);
        buff.append(trenner);	
        buff.append(frgtChgeCrrn);
        buff.append(trenner);	
        buff.append(frgtAprtCode);
        buff.append(trenner);	
        buff.append(insAmtAc);
        buff.append(trenner);	
        buff.append(insAmtCrrn);
        buff.append(trenner);	
        buff.append(ocdAc);
        buff.append(trenner);	
        buff.append(ocdCrrn);
        buff.append(trenner);
        buff.append(vatAdjtAc);
        buff.append(trenner);	
        buff.append(vatAdjtCrrn);
        buff.append(trenner);	
        buff.append(invDamtAc);
        buff.append(trenner);	
        buff.append(invDamtCrrn);
        buff.append(trenner);	
        buff.append(invDpct);
        buff.append(trenner);	
        buff.append(location);
        buff.append(trenner);	
        buff.append(iDefaultPlace);
        buff.append(trenner);	
        buff.append(templateName);
        buff.append(trenner);
        buff.append(userRef);
        buff.append(trenner);	
        buff.append(mrn);
        buff.append(trenner);        
        buff.append(scndDan);
        buff.append(trenner);	
        buff.append(scndDanPfx);
        buff.append(trenner);
        buff.append(notifyShortname);
        buff.append(trenner);	
        buff.append(reprShortname);
        buff.append(trenner);
        buff.append(euArrLocnCode);
        buff.append(trenner);
        buff.append(plaUldgCode);
        buff.append(trenner);
        buff.append(trptChgeMop);
        buff.append(trenner);
        buff.append(spoffShortname);
        buff.append(trenner);
        buff.append(stc);
        buff.append(trenner);
        buff.append(spCirc);
        buff.append(trenner);
        buff.append(reason);
        buff.append(trenner);
        buff.append(transit);        
        buff.append(trenner);
        buff.append(goodsDepDT);
        buff.append(trenner);
        buff.append(emcsFlag);
        //buff.append(trenner);       

        return new String(buff);
      }

	public boolean isEmpty() {			
		return (Utils.isStringEmpty(clientRef) && Utils.isStringEmpty(messageType) &&
			 Utils.isStringEmpty(capFlag) && Utils.isStringEmpty(localCleranceFlag) &&
		     Utils.isStringEmpty(declnCrrn) && Utils.isStringEmpty(decltRep) &&
		     Utils.isStringEmpty(destCntry) && Utils.isStringEmpty(ePlaLdg) &&
		     Utils.isStringEmpty(exitOffice) && Utils.isStringEmpty(gdsArrDtmInld) &&
			 Utils.isStringEmpty(gdsDepDtmInld) && Utils.isStringEmpty(declnUcr) &&
			 Utils.isStringEmpty(declnPartNo) && Utils.isStringEmpty(masterUcr) &&
		     Utils.isStringEmpty(firDan) && Utils.isStringEmpty(firDanPfx) &&
		     Utils.isStringEmpty(trptModeCode) && Utils.isStringEmpty(carrierName) &&
		     Utils.isStringEmpty(trptId) && Utils.isStringEmpty(trptCntry) &&
			 Utils.isStringEmpty(trptModeInld) && Utils.isStringEmpty(trptIdInld) &&
			 Utils.isStringEmpty(cnsgrShortname) && Utils.isStringEmpty(cnsgeShortname) &&
		     Utils.isStringEmpty(desltShortname) && Utils.isStringEmpty(gconTurn) &&
		     Utils.isStringEmpty(rcnsgrTurn) && Utils.isStringEmpty(rcnsgeTurn) &&
		     Utils.isStringEmpty(permShortname) && Utils.isStringEmpty(invTotAc) &&
			 Utils.isStringEmpty(invCrrn) && Utils.isStringEmpty(totPkgs) &&
			 Utils.isStringEmpty(gdsLocn) && Utils.isStringEmpty(gdsLocnCntry) &&
		     Utils.isStringEmpty(gdsShedId) && Utils.isStringEmpty(ibClaimRef) &&
		     Utils.isStringEmpty(ibClaimType) && Utils.isStringEmpty(ibRegNo) &&
		     Utils.isStringEmpty(ibGan) && Utils.isStringEmpty(clientInfo) &&		     
			 Utils.isStringEmpty(declnType) && Utils.isStringEmpty(acptncDTM) &&
			 Utils.isStringEmpty(dispCntry) && Utils.isStringEmpty(farpCode) &&
		     Utils.isStringEmpty(tdrOwnRefEnt) && Utils.isStringEmpty(atrptCostAc) &&
		     Utils.isStringEmpty(frgtChgeAc) && Utils.isStringEmpty(frgtChgeCrrn) &&
		     Utils.isStringEmpty(frgtAprtCode) && Utils.isStringEmpty(insAmtAc) &&
			 Utils.isStringEmpty(insAmtCrrn) && Utils.isStringEmpty(ocdAc) &&
			 Utils.isStringEmpty(ocdCrrn) && Utils.isStringEmpty(vatAdjtAc) &&
		     Utils.isStringEmpty(vatAdjtCrrn) && Utils.isStringEmpty(invDamtAc) &&
		     Utils.isStringEmpty(invDamtCrrn) && Utils.isStringEmpty(invDpct) &&
		     Utils.isStringEmpty(location) && Utils.isStringEmpty(iDefaultPlace) &&
			 Utils.isStringEmpty(templateName) && Utils.isStringEmpty(userRef) &&
			 Utils.isStringEmpty(mrn) && Utils.isStringEmpty(scndDan) &&
		     Utils.isStringEmpty(scndDanPfx) && Utils.isStringEmpty(notifyShortname) &&		     
		     Utils.isStringEmpty(reprShortname) && Utils.isStringEmpty(euArrLocnCode) &&
		     Utils.isStringEmpty(plaUldgCode) && Utils.isStringEmpty(trptChgeMop) &&
			 Utils.isStringEmpty(spoffShortname) && Utils.isStringEmpty(stc) &&
			 Utils.isStringEmpty(spCirc) && Utils.isStringEmpty(reason) &&
		     Utils.isStringEmpty(transit));
	}

	public String getClientRef() {
		return clientRef;
	}

	public void setClientRef(String clientRef) {
		this.clientRef = Utils.checkNull(clientRef);
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = Utils.checkNull(messageType);
	}

	public String getCapFlag() {
		return capFlag;
	}

	public void setCapFlag(String capFlag) {
		this.capFlag = Utils.checkNull(capFlag);
	}

	public String getLocalCleranceFlag() {
		return localCleranceFlag;
	}

	public void setLocalCleranceFlag(String localCleranceFlag) {
		this.localCleranceFlag = Utils.checkNull(localCleranceFlag);
	}

	public String getDeclnCrrn() {
		return declnCrrn;
	}

	public void setDeclnCrrn(String declnCrrn) {
		this.declnCrrn = Utils.checkNull(declnCrrn);
	}

	public String getDecltRep() {
		return decltRep;
	}

	public void setDecltRep(String decltRep) {
		this.decltRep = Utils.checkNull(decltRep);
	}

	public String getDestCntry() {
		return destCntry;
	}

	public void setDestCntry(String destCntry) {
		this.destCntry = Utils.checkNull(destCntry);
	}

	public String getEPlaLdg() {
		return ePlaLdg;
	}

	public void setEPlaLdg(String plaLdg) {
		ePlaLdg = Utils.checkNull(plaLdg);
	}

	public String getExitOffice() {
		return exitOffice;
	}

	public void setExitOffice(String exitOffice) {
		this.exitOffice = Utils.checkNull(exitOffice);
	}

	public String getGdsArrDtmInld() {
		return gdsArrDtmInld;
	}

	public void setGdsArrDtmInld(String gdsArrDtmInld) {
		this.gdsArrDtmInld = Utils.checkNull(gdsArrDtmInld);
	}

	public String getGdsDepDtmInld() {
		return gdsDepDtmInld;
	}

	public void setGdsDepDtmInld(String gdsDepDtmInld) {
		this.gdsDepDtmInld = Utils.checkNull(gdsDepDtmInld);
	}

	public String getDeclnUcr() {
		return declnUcr;
	}

	public void setDeclnUcr(String declnUcr) {
		this.declnUcr = Utils.checkNull(declnUcr);
	}

	public String getDeclnPartNo() {
		return declnPartNo;
	}

	public void setDeclnPartNo(String declnPartNo) {
		this.declnPartNo = Utils.checkNull(declnPartNo);
	}

	public String getMasterUcr() {
		return masterUcr;
	}

	public void setMasterUcr(String masterUcr) {
		this.masterUcr = Utils.checkNull(masterUcr);
	}

	public String getFirDan() {
		return firDan;
	}

	public void setFirDan(String firDan) {
		this.firDan = Utils.checkNull(firDan);
	}

	public String getFirDanPfx() {
		return firDanPfx;
	}

	public void setFirDanPfx(String firDanPfx) {
		this.firDanPfx = Utils.checkNull(firDanPfx);
	}

	public String getTrptModeCode() {
		return trptModeCode;
	}

	public void setTrptModeCode(String trptModeCode) {
		this.trptModeCode = Utils.checkNull(trptModeCode);
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = Utils.checkNull(carrierName);
	}

	public String getTrptId() {
		return trptId;
	}

	public void setTrptId(String trptId) {
		this.trptId = Utils.checkNull(trptId);
	}

	public String getTrptCntry() {
		return trptCntry;
	}

	public void setTrptCntry(String trptCntry) {
		this.trptCntry = Utils.checkNull(trptCntry);
	}

	public String getTrptModeInld() {
		return trptModeInld;
	}

	public void setTrptModeInld(String trptModeInld) {
		this.trptModeInld = Utils.checkNull(trptModeInld);
	}

	public String getTrptIdInld() {
		return trptIdInld;
	}

	public void setTrptIdInld(String trptIdInld) {
		this.trptIdInld = Utils.checkNull(trptIdInld);
	}

	public String getCnsgrShortname() {
		return cnsgrShortname;
	}

	public void setCnsgrShortname(String cnsgrShortname) {
		this.cnsgrShortname = Utils.checkNull(cnsgrShortname);
	}

	public String getCnsgeShortname() {
		return cnsgeShortname;
	}

	public void setCnsgeShortname(String cnsgeShortname) {
		this.cnsgeShortname = Utils.checkNull(cnsgeShortname);
	}

	public String getDesltShortname() {
		return desltShortname;
	}

	public void setDesltShortname(String desltShortname) {
		this.desltShortname = Utils.checkNull(desltShortname);
	}

	public String getGconTurn() {
		return gconTurn;
	}

	public void setGconTurn(String gconTurn) {
		this.gconTurn = Utils.checkNull(gconTurn);
	}

	public String getRcnsgrTurn() {
		return rcnsgrTurn;
	}

	public void setRcnsgrTurn(String rcnsgrTurn) {
		this.rcnsgrTurn = Utils.checkNull(rcnsgrTurn);
	}

	public String getRcnsgeTurn() {
		return rcnsgeTurn;
	}

	public void setRcnsgeTurn(String rcnsgeTurn) {
		this.rcnsgeTurn = Utils.checkNull(rcnsgeTurn);
	}

	public String getPermShortname() {
		return permShortname;
	}

	public void setPermShortname(String permShortname) {
		this.permShortname = Utils.checkNull(permShortname);
	}

	public String getInvTotAc() {
		return invTotAc;
	}

	public void setInvTotAc(String invTotAc) {
		this.invTotAc = Utils.checkNull(invTotAc);
	}

	public String getInvCrrn() {
		return invCrrn;
	}

	public void setInvCrrn(String invCrrn) {
		this.invCrrn = Utils.checkNull(invCrrn);
	}

	public String getTotPkgs() {
		return totPkgs;
	}

	public void setTotPkgs(String totPkgs) {
		this.totPkgs = Utils.checkNull(totPkgs);
	}

	public String getGdsLocn() {
		return gdsLocn;
	}

	public void setGdsLocn(String gdsLocn) {
		this.gdsLocn = Utils.checkNull(gdsLocn);
	}

	public String getGdsLocnCntry() {
		return gdsLocnCntry;
	}

	public void setGdsLocnCntry(String gdsLocnCntry) {
		this.gdsLocnCntry = Utils.checkNull(gdsLocnCntry);
	}

	public String getGdsShedId() {
		return gdsShedId;
	}

	public void setGdsShedId(String gdsShedId) {
		this.gdsShedId = Utils.checkNull(gdsShedId);
	}

	public String getIbClaimRef() {
		return ibClaimRef;
	}

	public void setIbClaimRef(String ibClaimRef) {
		this.ibClaimRef = Utils.checkNull(ibClaimRef);
	}

	public String getIbClaimType() {
		return ibClaimType;
	}

	public void setIbClaimType(String ibClaimType) {
		this.ibClaimType = Utils.checkNull(ibClaimType);
	}

	public String getIbRegNo() {
		return ibRegNo;
	}

	public void setIbRegNo(String ibRegNo) {
		this.ibRegNo = Utils.checkNull(ibRegNo);
	}

	public String getIbGan() {
		return ibGan;
	}

	public void setIbGan(String ibGan) {
		this.ibGan = Utils.checkNull(ibGan);
	}

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = Utils.checkNull(clientInfo);
	}

	public String getDeclnType() {
		return declnType;
	}

	public void setDeclnType(String declnType) {
		this.declnType = Utils.checkNull(declnType);
	}

	public String getAcptncDTM() {
		return acptncDTM;
	}

	public void setAcptncDTM(String acptncDTM) {
		this.acptncDTM = Utils.checkNull(acptncDTM);
	}

	public String getDispCntry() {
		return dispCntry;
	}

	public void setDispCntry(String dispCntry) {
		this.dispCntry = Utils.checkNull(dispCntry);
	}

	public String getFarpCode() {
		return farpCode;
	}

	public void setFarpCode(String farpCode) {
		this.farpCode = Utils.checkNull(farpCode);
	}

	public String getTdrOwnRefEnt() {
		return tdrOwnRefEnt;
	}

	public void setTdrOwnRefEnt(String tdrOwnRefEnt) {
		this.tdrOwnRefEnt = Utils.checkNull(tdrOwnRefEnt);
	}

	public String getAtrptCostAc() {
		return atrptCostAc;
	}

	public void setAtrptCostAc(String atrptCostAc) {
		this.atrptCostAc = Utils.checkNull(atrptCostAc);
	}

	public String getFrgtChgeAc() {
		return frgtChgeAc;
	}

	public void setFrgtChgeAc(String frgtChgeAc) {
		this.frgtChgeAc = Utils.checkNull(frgtChgeAc);
	}

	public String getFrgtChgeCrrn() {
		return frgtChgeCrrn;
	}

	public void setFrgtChgeCrrn(String frgtChgeCrrn) {
		this.frgtChgeCrrn = Utils.checkNull(frgtChgeCrrn);
	}

	public String getFrgtAprtCode() {
		return frgtAprtCode;
	}

	public void setFrgtAprtCode(String frgtAprtCode) {
		this.frgtAprtCode = Utils.checkNull(frgtAprtCode);
	}

	public String getInsAmtAc() {
		return insAmtAc;
	}

	public void setInsAmtAc(String insAmtAc) {
		this.insAmtAc = Utils.checkNull(insAmtAc);
	}

	public String getInsAmtCrrn() {
		return insAmtCrrn;
	}

	public void setInsAmtCrrn(String insAmtCrrn) {
		this.insAmtCrrn = Utils.checkNull(insAmtCrrn);
	}

	public String getOcdAc() {
		return ocdAc;
	}

	public void setOcdAc(String ocdAc) {
		this.ocdAc = Utils.checkNull(ocdAc);
	}

	public String getOcdCrrn() {
		return ocdCrrn;
	}

	public void setOcdCrrn(String ocdCrrn) {
		this.ocdCrrn = Utils.checkNull(ocdCrrn);
	}

	public String getVatAdjtAc() {
		return vatAdjtAc;
	}

	public void setVatAdjtAc(String vatAdjtAc) {
		this.vatAdjtAc = Utils.checkNull(vatAdjtAc);
	}

	public String getVatAdjtCrrn() {
		return vatAdjtCrrn;
	}

	public void setVatAdjtCrrn(String vatAdjtCrrn) {
		this.vatAdjtCrrn = Utils.checkNull(vatAdjtCrrn);
	}

	public String getInvDamtAc() {
		return invDamtAc;
	}

	public void setInvDamtAc(String invDamtAc) {
		this.invDamtAc = Utils.checkNull(invDamtAc);
	}

	public String getInvDamtCrrn() {
		return invDamtCrrn;
	}

	public void setInvDamtCrrn(String invDamtCrrn) {
		this.invDamtCrrn = Utils.checkNull(invDamtCrrn);
	}

	public String getInvDpct() {
		return invDpct;
	}

	public void setInvDpct(String invDpct) {
		this.invDpct = Utils.checkNull(invDpct);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = Utils.checkNull(location);
	}

	public String getIDefaultPlace() {
		return iDefaultPlace;
	}

	public void setIDefaultPlace(String defaultPlace) {
		iDefaultPlace = Utils.checkNull(defaultPlace);
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = Utils.checkNull(templateName);
	}

	public String getUserRef() {
		return userRef;
	}

	public void setUserRef(String userRef) {
		this.userRef = Utils.checkNull(userRef);
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getScndDan() {
		return scndDan;
	}

	public void setScndDan(String scndDan) {
		this.scndDan = Utils.checkNull(scndDan);
	}

	public String getScndDanPfx() {
		return scndDanPfx;
	}

	public void setScndDanPfx(String scndDanPfx) {
		this.scndDanPfx = Utils.checkNull(scndDanPfx);
	}

	public String getNotifyShortname() {
		return notifyShortname;
	}

	public void setNotifyShortname(String notifyShortname) {
		this.notifyShortname = Utils.checkNull(notifyShortname);
	}

	public String getReprShortname() {
		return reprShortname;
	}

	public void setReprShortname(String reprShortname) {
		this.reprShortname = Utils.checkNull(reprShortname);
	}

	public String getEuArrLocnCode() {
		return euArrLocnCode;
	}

	public void setEuArrLocnCode(String euArrLocnCode) {
		this.euArrLocnCode = Utils.checkNull(euArrLocnCode);
	}

	public String getPlaUldgCode() {
		return plaUldgCode;
	}

	public void setPlaUldgCode(String plaUldgCode) {
		this.plaUldgCode = Utils.checkNull(plaUldgCode);
	}

	public String getTrptChgeMop() {
		return trptChgeMop;
	}

	public void setTrptChgeMop(String trptChgeMop) {
		this.trptChgeMop = Utils.checkNull(trptChgeMop);
	}

	public String getSpoffShortname() {
		return spoffShortname;
	}

	public void setSpoffShortname(String spoffShortname) {
		this.spoffShortname = Utils.checkNull(spoffShortname);
	}

	public String getStc() {
		return stc;
	}

	public void setStc(String stc) {
		this.stc = Utils.checkNull(stc);
	}

	public String getSpCirc() {
		return spCirc;
	}

	public void setSpCirc(String spCirc) {
		this.spCirc = Utils.checkNull(spCirc);
	}

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = Utils.checkNull(reason);
	}

	public String getTransit() {
		return transit;
	}
	public void setTransit(String transit) {
		this.transit = Utils.checkNull(transit);
	}
	 	
	public String getGoodsDepDT() {
		return goodsDepDT;
	}
	public void setGoodsDepDT(String date) {
		this.goodsDepDT = Utils.checkNull(date);
	}

	public String getEmcsFlag() {
		return emcsFlag;
	}
	public void setEmcsFlag(String transit) {
		this.emcsFlag = Utils.checkNull(transit);
	}
}  		
     
            
                
            
         
    
    
      
        
       
    
               
            
            
           




