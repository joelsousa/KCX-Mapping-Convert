/*
* Function    : TsDD.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : Details for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDD<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : Details for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDD extends Teilsatz {

    private String cpc      		  = "";       //
    private String traficCmdtyCode    = "";       //
    private String ecSupplement 	  = "";       //
    private String ecSupplement2      = "";       //
 	private String itemOrigCntry 	  = "";      //  
    private String itemDestCntry 	  = "";      //  
    private String itemDispCntry	  = "";      // 
    private String itemGrossMass	  = "";      // 
    private String itemNetMass	      = "";      // 
    private String itemSuppUnits      = "";      // 
    private String itemThrdQty        = "";      // 
    private String iCnsgrShortname    = "";      // 
    private String iCnsgeShortname    = "";      // 
    private String iSpoffShortname    = "";      // 
    private String itemStatvalDc      = "";      // 
    private String itemPrcAc          = "";      // 
    private String undgCode           = "";      // 
    private String gdsDesc            = "";      //  
    private String ibRecipeCode       = "";      // 
    private String ibtsSerilaNumber   = "";      // 
    private String clientInfo         = "";      //  
    private String preference         = "";  
    private String qtaNo              = "";       //
    private String valMthdCode        = "";       //
    private String valAdjtCode        = "";       //
    private String itemValAdjt        = "";       //
 	private String templateName       = "";      //  
    private String userRef            = "";      //  
    private String productCode        = "";      // 
    private String iTrptChgeMop       = "";      // 
    private String iprProject         = "";      // 
    private String oprProject         = "";      // 
    private String confirmNetMass     = "";      // 
    private String confirmValue       = "";      // 
    private String confirmSuppUnits   = "";      // 
   
    public TsDD() {
        tsTyp = "DD";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDD: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        cpc         = fields[1];
        if (size < 3) { return; }
        traficCmdtyCode  = fields[2];
        if (size < 4) { return; }
        ecSupplement         = fields[3];
        if (size < 5) { return; }
        ecSupplement2         = fields[4];    
        if (size < 6) { return; }
        itemOrigCntry         = fields[5];
        if (size < 7) { return; }
        itemDestCntry         = fields[6];
        if (size < 8) { return; }
        itemDispCntry         = fields[7]; 
        if (size < 9) { return; }
        itemGrossMass         = fields[8];
        if (size < 10) { return; }
        itemNetMass         = fields[9];
        if (size < 11) { return; }
        itemSuppUnits         = fields[10]; 
        if (size < 12) { return; }
        itemThrdQty        = fields[11];
        if (size < 13) { return; }
        iCnsgrShortname         = fields[12];
        if (size < 14) { return; }
        iCnsgeShortname         = fields[13];
        if (size < 15) { return; }
        iSpoffShortname         = fields[14];
        if (size < 16) { return; }
        itemStatvalDc         = fields[15];            
        if (size < 17) { return; }
        itemPrcAc         = fields[16];
        if (size < 18) { return; }
        undgCode         = fields[17]; 
        if (size < 19) { return; }
        gdsDesc         = fields[18];
        if (size < 20) { return; }
        ibRecipeCode         = fields[19];
        if (size < 21) { return; }
        ibtsSerilaNumber         = fields[20];         
        if (size < 22) { return; }
        clientInfo         = fields[21];
        if (size < 23) { return; }
        preference         = fields[22];
        if (size < 24) { return; }
        qtaNo         = fields[23];
        if (size < 25) { return; }
        valMthdCode         = fields[24];    
        if (size < 26) { return; }
        valAdjtCode         = fields[25];
        if (size < 27) { return; }
        itemValAdjt         = fields[26];
        if (size < 28) { return; }
        templateName         = fields[27]; 
        if (size < 29) { return; }
        userRef         = fields[28];
        if (size < 30) { return; }
        productCode         = fields[29];
        if (size < 31) { return; }
        iTrptChgeMop         = fields[30];         
        if (size < 32) { return; }
        iprProject         = fields[31];
        if (size < 33) { return; }
        oprProject         = fields[32];
        if (size < 34) { return; }	
        confirmNetMass         = fields[33];
        if (size < 35) { return; }
        confirmValue         = fields[34];    
        if (size < 36) { return; }
        confirmSuppUnits         = fields[35];
              
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(cpc);
        buff.append(trenner);
        buff.append(traficCmdtyCode);
        buff.append(trenner);
        buff.append(ecSupplement);
        buff.append(trenner);
        buff.append(ecSupplement2);
        buff.append(trenner);
        buff.append(itemOrigCntry);
        buff.append(trenner);
        buff.append(itemDestCntry);
        buff.append(trenner);
        buff.append(itemDispCntry);
        buff.append(trenner);
        buff.append(itemGrossMass);
        buff.append(trenner);
        buff.append(itemNetMass);
        buff.append(trenner);
        buff.append(itemSuppUnits);
        buff.append(trenner);
        buff.append(itemThrdQty);
        buff.append(trenner);
        buff.append(iCnsgrShortname);
        buff.append(trenner);
        buff.append(iCnsgeShortname);
        buff.append(trenner);
        buff.append(iSpoffShortname);
        buff.append(trenner);
        buff.append(itemStatvalDc);
        buff.append(trenner);
        buff.append(itemPrcAc);
        buff.append(trenner);
        buff.append(undgCode);
        buff.append(trenner);
        buff.append(gdsDesc);
        buff.append(trenner);
        buff.append(ibRecipeCode);
        buff.append(trenner);
        buff.append(ibtsSerilaNumber);
        buff.append(trenner);
        buff.append(clientInfo);
        buff.append(trenner);
        buff.append(preference);
        buff.append(trenner);
        buff.append(qtaNo);
        buff.append(trenner);
        buff.append(valMthdCode);
        buff.append(trenner);
        buff.append(valAdjtCode);
        buff.append(trenner);
        buff.append(itemValAdjt);
        buff.append(trenner);
        buff.append(templateName);
        buff.append(trenner);
        buff.append(userRef);
        buff.append(trenner);
        buff.append(productCode);
        buff.append(trenner);
        buff.append(iTrptChgeMop);
        buff.append(trenner);
        buff.append(iprProject);
        buff.append(trenner);
        buff.append(oprProject);
        buff.append(trenner);
        buff.append(confirmNetMass);
        buff.append(trenner);
        buff.append(confirmValue);
        buff.append(trenner);
        buff.append(confirmSuppUnits);
        //buff.append(trenner);       

        return new String(buff);
      }

	public String getCpc() {
		return cpc;	
	}
	public void setCpf(String argument) {
		this.cpc = Utils.checkNull(argument);
	}

	public String getTraficCmdtyCode() {
		return traficCmdtyCode;	
	}
	public void setTraficCmdtyCode(String argument) {
		this.traficCmdtyCode = Utils.checkNull(argument);
	}
	
	public String getEcSupplement() {
		return ecSupplement;	
	}
	public void setEcSupplement(String argument) {
		this.ecSupplement = Utils.checkNull(argument);
	}

	public String getEcSupplement2() {
		return ecSupplement2;	
	}
	public void setEcSupplement2(String argument) {
		this.ecSupplement2 = Utils.checkNull(argument);
	}
	
	public String getItemOrigCntry() {
		return itemOrigCntry;	
	}
	public void setItemOrigCntry(String argument) {
		this.itemOrigCntry = Utils.checkNull(argument);
	}
	
	public String getItemDestCntry() {
		return itemDestCntry;	
	}
	public void setItemDestCntry(String argument) {
		this.itemDestCntry = Utils.checkNull(argument);
	}

	public String getItemDispCntry() {
		return itemDispCntry;	
	}
	public void setItemDispCntry(String argument) {
		this.itemDispCntry = Utils.checkNull(argument);
	}
	
	public String getItemGrossMass() {
		return itemGrossMass;	
	}
	public void setItemGrossMass(String argument) {
		this.itemGrossMass = Utils.checkNull(argument);
	}

	public String getItemNetMass() {
		return itemNetMass;	
	}
	public void setItemNetMass(String argument) {
		this.itemNetMass = Utils.checkNull(argument);
	}
	
	public String getItemSuppUnits() {
		return itemSuppUnits;	
	}
	public void setItemSuppUnits(String argument) {
		this.itemSuppUnits = Utils.checkNull(argument);
	}

	public String getItemThrdQty() {
		return itemThrdQty;	
	}
	public void setItemThrdQty(String argument) {
		this.itemThrdQty = Utils.checkNull(argument);
	}
	
	public String getICnsgrShortname() {
		return iCnsgrShortname;	
	}
	public void setICnsgrShortname(String argument) {
		this.iCnsgrShortname = Utils.checkNull(argument);
	}

	public String getICnsgeShortname() {
		return iCnsgeShortname;	
	}
	public void setICnsgeShortname(String argument) {
		this.iCnsgeShortname = Utils.checkNull(argument);
	}
	
	public String getISpoffShortname() {
		return iSpoffShortname;	
	}
	public void setISpoffShortname(String argument) {
		this.iSpoffShortname = Utils.checkNull(argument);
	}

	public String getItemStatvalDc() {
		return itemStatvalDc;	
	}
	public void setItemStatvalDc(String argument) {
		this.itemStatvalDc = Utils.checkNull(argument);
	}
	
	public String getItemPrcAc() {
		return itemPrcAc;	
	}
	public void setItemPrcAc(String argument) {
		this.itemPrcAc = Utils.checkNull(argument);
	}

	public String getUndgCode() {
		return undgCode;	
	}
	public void setUndgCode(String argument) {
		this.undgCode = Utils.checkNull(argument);
	}
	
	public String getGdsDesc() {
		return gdsDesc;	
	}
	public void setGdsDesc(String argument) {
		this.gdsDesc = Utils.checkNull(argument);
	}

	public String getIbRecipeCode() {
		return ibRecipeCode;	
	}
	public void setIbRecipeCode(String argument) {
		this.ibRecipeCode = Utils.checkNull(argument);
	}
	
	public String getIbtsSerilaNumber() {
		return ibtsSerilaNumber;	
	}
	public void setIbtsSerilaNumber(String argument) {
		this.ibtsSerilaNumber = Utils.checkNull(argument);
	}

	public String getClientInfo() {
		return clientInfo;	
	}
	public void setClientInfo(String argument) {
		this.clientInfo = Utils.checkNull(argument);
	}
	
	public String getPreference() {
		return preference;	
	}
	public void setPreference(String argument) {
		this.preference = Utils.checkNull(argument);
	}

	public String getQtaNo() {
		return qtaNo;	
	}
	public void setQtaNo(String argument) {
		this.qtaNo = Utils.checkNull(argument);
	}
	
	public String getValMthdCode() {
		return valMthdCode;	
	}
	public void setValMthdCode(String argument) {
		this.valMthdCode = Utils.checkNull(argument);
	}

	public String getValAdjtCode() {
		return valAdjtCode;	
	}
	public void setValAdjtCode(String argument) {
		this.valAdjtCode = Utils.checkNull(argument);
	}
	
	public String getItemValAdjt() {
		return itemValAdjt;	
	}
	public void setItemValAdjt(String argument) {
		this.itemValAdjt = Utils.checkNull(argument);
	}

	public String getTemplateName() {
		return templateName;	
	}
	public void setTemplateName(String argument) {
		this.templateName = Utils.checkNull(argument);
	}
	
	public String getUserRef() {
		return userRef;	
	}
	public void setUserRef(String argument) {
		this.userRef = Utils.checkNull(argument);
	}

	public String getProductCode() {
		return productCode;	
	}
	public void setProductCode(String argument) {
		this.productCode = Utils.checkNull(argument);
	}
	
	public String getITrptChgeMop() {
		return iTrptChgeMop;	
	}
	public void setITrptChgeMop(String argument) {
		this.iTrptChgeMop = Utils.checkNull(argument);
	}

	public String getIprProject() {
		return iprProject;	
	}
	public void setIprProject(String argument) {
		this.iprProject = Utils.checkNull(argument);
	}
	
	public String getOprProject() {
		return oprProject;	
	}
	public void setOprProject(String argument) {
		this.oprProject = Utils.checkNull(argument);
	}
	
	public String getConfirmNetMass() {
		return confirmNetMass;	
	}
	public void setConfirmNetMass(String argument) {
		this.confirmNetMass = Utils.checkNull(argument);
	}
	
	public String getConfirmValue() {
		return confirmValue;	
	}
	public void setConfirmValue(String argument) {
		this.confirmValue = Utils.checkNull(argument);
	}
	
	public String getConfirmSuppUnits() {
		return confirmSuppUnits;	
	}
	public void setConfirmSuppUnits(String argument) {
		this.confirmSuppUnits = Utils.checkNull(argument);
	}
	
	public boolean isEmpty() {			
		return (Utils.isStringEmpty(cpc) && Utils.isStringEmpty(traficCmdtyCode) &&
			 Utils.isStringEmpty(ecSupplement) && Utils.isStringEmpty(ecSupplement2) &&
		     Utils.isStringEmpty(itemOrigCntry) && Utils.isStringEmpty(itemDestCntry) &&
		     Utils.isStringEmpty(itemDispCntry) && Utils.isStringEmpty(itemGrossMass) &&
		     Utils.isStringEmpty(itemNetMass) && Utils.isStringEmpty(itemSuppUnits) &&
		     Utils.isStringEmpty(itemThrdQty) && Utils.isStringEmpty(iCnsgrShortname) &&
		     Utils.isStringEmpty(iCnsgeShortname) && Utils.isStringEmpty(iSpoffShortname) &&
		     Utils.isStringEmpty(itemStatvalDc) && Utils.isStringEmpty(itemPrcAc) &&
		     Utils.isStringEmpty(undgCode) && Utils.isStringEmpty(gdsDesc) &&
		     Utils.isStringEmpty(ibRecipeCode) && Utils.isStringEmpty(ibtsSerilaNumber) &&
		     Utils.isStringEmpty(clientInfo) && Utils.isStringEmpty(preference) &&
		     Utils.isStringEmpty(qtaNo) && Utils.isStringEmpty(valMthdCode) &&
		     Utils.isStringEmpty(valAdjtCode) && Utils.isStringEmpty(itemValAdjt) &&
		     Utils.isStringEmpty(templateName) && Utils.isStringEmpty(userRef) &&
		     Utils.isStringEmpty(productCode) && Utils.isStringEmpty(iTrptChgeMop) &&
		     Utils.isStringEmpty(iprProject) && Utils.isStringEmpty(oprProject) &&
		     Utils.isStringEmpty(confirmNetMass) && Utils.isStringEmpty(confirmValue) &&
			 Utils.isStringEmpty(confirmSuppUnits)); 
	}
}
           
        
       
         
         
     
       
   




