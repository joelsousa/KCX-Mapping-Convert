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

public class TsTotalsOnEntireBL extends Teilsatz {

    private String grossWeightKilogram		 = "";	 //  Gesamtgewicht der Sendung (brutto)
    private String numberOfPieces		 	 = "";	 //  Gesamt-Stückzahl der Sendung
    private String numberOfPackages		 	 = "";	 //  Summe der Packstücke
    private String grossVolumeCubicMetre	 = "";	 //  Gesamtvolumen der Einzelsendungen
    private String numberOfEquipment		 = "";	 //  Gesamtanzahl des Equipments (Container) angegeben

    public TsTotalsOnEntireBL() {
	    tsTyp = "TOTALSONENTIREBL";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	
    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    grossWeightKilogram = fields[1];
	    if (size < 3) { return; }
	    numberOfPieces = fields[2];
	    if (size < 4) { return; }
	    numberOfPackages = fields[3];
	    if (size < 5) { return; }
	    grossVolumeCubicMetre = fields[4];
	    if (size < 6) { return; }
	    numberOfEquipment = fields[5];
    }

    public String getGrossWeightKilogram() {
    	 return grossWeightKilogram;
    }
    public void setGrossWeightKilogram(String grossWeightKilogram) {
    	this.grossWeightKilogram = Utils.checkNull(grossWeightKilogram);
    }

    public String getNumberOfPieces() {
    	 return numberOfPieces;
    }
    public void setNumberOfPieces(String numberOfPieces) {
    	this.numberOfPieces = Utils.checkNull(numberOfPieces);
    }
    
    public String getNumberOfPackages() {
    	 return numberOfPackages;
    }
    public void setNumberOfPackages(String numberOfPackages) {
    	this.numberOfPackages = Utils.checkNull(numberOfPackages);
    }

    public String getGrossVolumeCubicMetre() {
    	 return grossVolumeCubicMetre;
    }
    public void setGrossVolumeCubicMetre(String grossVolumeCubicMetre) {
    	this.grossVolumeCubicMetre = Utils.checkNull(grossVolumeCubicMetre);
    }

    public String getNumberOfEquipment() {
    	 return numberOfEquipment;
    }
    public void setNumberOfEquipment(String numberOfEquipment) {
    	this.numberOfEquipment = Utils.checkNull(numberOfEquipment);
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(grossWeightKilogram);
    	buff.append(trenner);
    	buff.append(numberOfPieces);
    	buff.append(trenner);
    	buff.append(numberOfPackages);
    	buff.append(trenner);
    	buff.append(grossVolumeCubicMetre);
    	buff.append(trenner);
    	buff.append(numberOfEquipment);
    	//buff.append(trenner);

    	return new String(buff);    
    }

    public boolean isEmpty() {
	return   Utils.isStringEmpty(grossWeightKilogram) 
    	&& Utils.isStringEmpty(numberOfPieces)
    	&& Utils.isStringEmpty(numberOfPackages)
    	&& Utils.isStringEmpty(grossVolumeCubicMetre)
    	&& Utils.isStringEmpty(numberOfEquipment);
    }
}

