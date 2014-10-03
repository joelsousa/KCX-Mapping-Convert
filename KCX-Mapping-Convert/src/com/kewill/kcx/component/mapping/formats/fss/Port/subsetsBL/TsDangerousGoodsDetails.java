package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module                :       PORT - BL.
 * Created               :       10.04.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsDangerousGoodsDetails extends Teilsatz {

    private String count		 = "";	    
    private String ediQualifier		 = "";	 //  IMD-ADR- ANR- RID-
    private String hazardCodeIdentification	 = "";
    private String hazardItemPageNumber		 = "";
    private String hazardCodeVersionNumber	 = "";
    private String undgNumber		 = "";
    private String flashpoint		 = "";
    private String flashpointQualifier		 = "";
    private String levelOfDanger		 = "";
    private String emsNumber		 = "";
    private String mFAG		 = "";	
    private String labelMarking		 = "";  // + labelMarking2 und labelMarking§       
    private String grossWeightKilogram		 = "";
    private String netWeightKilogram		 = "";
    private String netNetWeightKilogram		 = "";
    private String netExplosiveWeightKilogram	 = "";
    private String radioactiveIndexOfTransportNumber	 = "";
    private String radioactivityBecquerel		 = "";
    private String radioactivityCurie		 = "";

    public TsDangerousGoodsDetails() {
	    tsTyp = "DANGEROUSGOODS";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    count = fields[1];
	    if (size < 3) { return; }
	    ediQualifier = fields[2];
	    if (size < 4) { return; }
	    hazardCodeIdentification = fields[3];
	    if (size < 5) { return; }
	    hazardItemPageNumber = fields[4];
	    if (size < 6) { return; }
	    hazardCodeVersionNumber = fields[5];
	    if (size < 7) { return; }
	    undgNumber = fields[6];
	    if (size < 8) { return; }
	    flashpoint = fields[7];
	    if (size < 9) { return; }
	    flashpointQualifier = fields[8];
	    if (size < 10) { return; }
	    levelOfDanger = fields[9];
	    if (size < 11) { return; }
	    emsNumber = fields[10];
	    if (size < 12) { return; }
	    mFAG = fields[11];
	    if (size < 13) { return; }
	    labelMarking = fields[12];
	    if (size < 14) { return; }
	    grossWeightKilogram = fields[13];
	    if (size < 15) { return; }
	    netWeightKilogram = fields[14];
	    if (size < 16) { return; }
	    netNetWeightKilogram = fields[15];
	    if (size < 17) { return; }
	    netExplosiveWeightKilogram = fields[16];
	    if (size < 18) { return; }
	    radioactiveIndexOfTransportNumber = fields[17];
	    if (size < 19) { return; }
	    radioactivityBecquerel = fields[18];
	    if (size < 20) { return; }
	    radioactivityCurie = fields[19];	   
    }

    public String getCtem() {
    	 return count;
    }
    public void setCtem(String count) {
    	this.count = Utils.checkNull(count);
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "0";
    	}
    }
    
    public String getEdiQualifier() {
    	 return ediQualifier;
    }
    public void setEdiQualifier(String type) {
    	this.ediQualifier = Utils.checkNull(type);
    }

    public String getHazardCodeIdentification() {
    	 return hazardCodeIdentification;
    }
    public void setHazardCodeIdentification(String hazardCodeIdentification) {
    	this.hazardCodeIdentification = Utils.checkNull(hazardCodeIdentification);
    }

    public String getHazardItemPageNumber() {
    	 return hazardItemPageNumber;
    }
    public void setHazardItemPageNumber(String hazardItemPageNumber) {
    	this.hazardItemPageNumber = Utils.checkNull(hazardItemPageNumber);
    }

    public String getHazardCodeVersionNumber() {
    	 return hazardCodeVersionNumber;
    }
    public void setHazardCodeVersionNumber(String hazardCodeVersionNumber) {
    	this.hazardCodeVersionNumber = Utils.checkNull(hazardCodeVersionNumber);
    }

    public String getUNDGNumber() {
    	 return undgNumber;
    }
    public void setUNDGNumber(String uNDGNumber) {
    	this.undgNumber = Utils.checkNull(uNDGNumber);
    }

    public String getFlashpoint() {
    	 return flashpoint;
    }
    public void setFlashpoint(String flashpoint) {
    	this.flashpoint = Utils.checkNull(flashpoint);
    }

    public String getFlashpointQualifier() {
    	 return flashpointQualifier;
    }
    public void setFlashpointQualifier(String flashpointQualifier) {
    	this.flashpointQualifier = Utils.checkNull(flashpointQualifier);
    }

    public String getLevelOfDanger() {
    	 return levelOfDanger;
    }
    public void setLevelOfDanger(String levelOfDanger) {
    	this.levelOfDanger = Utils.checkNull(levelOfDanger);
    }

    public String getEMSNumber() {
    	 return emsNumber;
    }
    public void setEMSNumber(String eMSNumber) {
    	this.emsNumber = Utils.checkNull(eMSNumber);
    }

    public String getMFAG() {
    	 return mFAG;
    }
    public void setMFAG(String mFAG) {
    	this.mFAG = Utils.checkNull(mFAG);
    }

    public String getLabelMarking() {
    	 return labelMarking;
    }
    public void setLabelMarking(String labelMarking1) {
    	this.labelMarking = Utils.checkNull(labelMarking1);
    }

    public String getGrossWeightKilogram() {
    	 return grossWeightKilogram;
    }
    public void setGrossWeightKilogram(String grossWeightKilogram) {
    	this.grossWeightKilogram = Utils.checkNull(grossWeightKilogram);
    }

    public String getNetWeightKilogram() {
    	 return netWeightKilogram;
    }
    public void setNetWeightKilogram(String netNetWeightKilogram) {
    	this.netWeightKilogram = Utils.checkNull(netNetWeightKilogram);
    }

    public String getNetNetWeightKilogram() {
   	 return netNetWeightKilogram;
   }
   public void setNetNetWeightKilogram(String netNetWeightKilogram) {
   	this.netNetWeightKilogram = Utils.checkNull(netNetWeightKilogram);
   }
   
    public String getNetExplosiveWeightKilogram() {
    	 return netExplosiveWeightKilogram;
    }
    public void setNetExplosiveWeightKilogram(String netExplosiveWeightKilogram) {
    	this.netExplosiveWeightKilogram = Utils.checkNull(netExplosiveWeightKilogram);
    }

    public String getRadioactiveIndexOfTransportNumber() {
    	 return radioactiveIndexOfTransportNumber;
    }
    public void setRadioactiveIndexOfTransportNumber(String radioactiveIndexOfTransportNumber) {
    	this.radioactiveIndexOfTransportNumber = Utils.checkNull(radioactiveIndexOfTransportNumber);
    }

    public String getRadioactivityBecquerel() {
    	 return radioactivityBecquerel;
    }
    public void setRadioactivityBecquerel(String radioactivityBecquerel) {
    	this.radioactivityBecquerel = Utils.checkNull(radioactivityBecquerel);
    }

    public String getRadioactivityCurie() {
    	 return radioactivityCurie;
    }
    public void setRadioactivityCurie(String radioactivityCurie) {
    	this.radioactivityCurie = Utils.checkNull(radioactivityCurie);
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(count);
    	buff.append(trenner);    	
    	buff.append(ediQualifier);
    	buff.append(trenner);
    	buff.append(hazardCodeIdentification);
    	buff.append(trenner);
    	buff.append(hazardItemPageNumber);
    	buff.append(trenner);
    	buff.append(hazardCodeVersionNumber);
    	buff.append(trenner);
    	buff.append(undgNumber);
    	buff.append(trenner);
    	buff.append(flashpoint);
    	buff.append(trenner);
    	buff.append(flashpointQualifier);
    	buff.append(trenner);
    	buff.append(levelOfDanger);
    	buff.append(trenner);
    	buff.append(emsNumber);
    	buff.append(trenner);
    	buff.append(mFAG);
    	buff.append(trenner);
    	buff.append(labelMarking);
    	buff.append(trenner);    	
    	buff.append(grossWeightKilogram);
    	buff.append(trenner);
    	buff.append(netWeightKilogram);
    	buff.append(trenner);
    	buff.append(netNetWeightKilogram);
    	buff.append(trenner);
    	buff.append(netExplosiveWeightKilogram);
    	buff.append(trenner);
    	buff.append(radioactiveIndexOfTransportNumber);
    	buff.append(trenner);
    	buff.append(radioactivityBecquerel);
    	buff.append(trenner);
    	buff.append(radioactivityCurie);
    	//buff.append(trenner);

    	return new String(buff);
    }



    public boolean isEmpty() {
	return  Utils.isStringEmpty(hazardCodeIdentification) 
    	&& Utils.isStringEmpty(hazardItemPageNumber) 
    	&& Utils.isStringEmpty(hazardCodeVersionNumber) 
    	&& Utils.isStringEmpty(undgNumber) 
    	&& Utils.isStringEmpty(flashpoint) 
    	&& Utils.isStringEmpty(flashpointQualifier) 
    	&& Utils.isStringEmpty(levelOfDanger) 
    	&& Utils.isStringEmpty(emsNumber) 
    	&& Utils.isStringEmpty(mFAG) 
    	&& Utils.isStringEmpty(labelMarking)     	
    	&& Utils.isStringEmpty(grossWeightKilogram) 
    	&& Utils.isStringEmpty(netNetWeightKilogram) 
    	&& Utils.isStringEmpty(netExplosiveWeightKilogram) 
    	&& Utils.isStringEmpty(radioactiveIndexOfTransportNumber) 
    	&& Utils.isStringEmpty(radioactivityBecquerel) 
    	&& Utils.isStringEmpty(radioactivityCurie);    
    }

}
