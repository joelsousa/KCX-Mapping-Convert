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

public class TsAllocatedEquipment extends Teilsatz {

    private String count = "";	    //fuer AK count mit zaehler ersetzt
    private String equipmentIdentificationNumber = "";	
    private String numberOfPackages		     = "";	 
    private String grossWeightKilogram		 = "";	
    private String netWeightKilogram		 = "";
    private String tareWeightKilogram		 = "";	
    private String grossVolumeCubicMetre	 = "";

    public TsAllocatedEquipment() {
	    tsTyp = "ALLOCATEDEQUIPMENT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    count = fields[1];

	    if (size < 3) { return; }
	    equipmentIdentificationNumber = fields[2];

	    if (size < 4) { return; }
	    numberOfPackages = fields[3];

	    if (size < 5) { return; }
	    grossWeightKilogram = fields[4];

	    if (size < 6) { return; }
	    netWeightKilogram = fields[5];

	    if (size < 7) { return; }
	    tareWeightKilogram = fields[6];

	    if (size < 8) { return; }
	    grossVolumeCubicMetre = fields[7];
    }



    public String getCount() {
    	 return count;
    }
    public void setCount(String count) {
    	this.count = Utils.checkNull(count);
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "";
    	}
    }

    public String getEquipmentIdentificationNumber() {
    	 return equipmentIdentificationNumber;
    }
    public void setEquipmentIdentificationNumber(String equipmentIdentificationNumber) {
    	this.equipmentIdentificationNumber = Utils.checkNull(equipmentIdentificationNumber);
    }

    public String getNumberOfPackages() {
    	 return numberOfPackages;
    }
    public void setNumberOfPackages(String numberOfPackages) {
    	this.numberOfPackages = Utils.checkNull(numberOfPackages);
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
    public void setNetWeightKilogram(String netWeightKilogram) {
    	this.netWeightKilogram = Utils.checkNull(netWeightKilogram);
    }

    public String getTareWeightKilogram() {
    	 return tareWeightKilogram;
    }
    public void setTareWeightKilogram(String tareWeightKilogram) {
    	this.tareWeightKilogram = Utils.checkNull(tareWeightKilogram);
    }

    public String getGrossVolumeCubicMetre() {
    	 return grossVolumeCubicMetre;
    }
    public void setGrossVolumeCubicMetre(String grossVolumeCubicMetre) {
    	this.grossVolumeCubicMetre = Utils.checkNull(grossVolumeCubicMetre);
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(count);
    	buff.append(trenner);
    	buff.append(equipmentIdentificationNumber);
    	buff.append(trenner);
    	buff.append(numberOfPackages);
    	buff.append(trenner);
    	buff.append(grossWeightKilogram);
    	buff.append(trenner);
    	buff.append(netWeightKilogram);
    	buff.append(trenner);
    	buff.append(tareWeightKilogram);
    	buff.append(trenner);
    	buff.append(grossVolumeCubicMetre);
    	//buff.append(trenner);

    	return new String(buff);    
    }

    public boolean isEmpty() {
	return   Utils.isStringEmpty(equipmentIdentificationNumber) 
    	&& Utils.isStringEmpty(numberOfPackages) 
    	&& Utils.isStringEmpty(grossWeightKilogram) 
    	&& Utils.isStringEmpty(netWeightKilogram) 
    	&& Utils.isStringEmpty(tareWeightKilogram) 
    	&& Utils.isStringEmpty(grossVolumeCubicMetre);
    }

}
