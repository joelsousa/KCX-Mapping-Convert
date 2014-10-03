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

public class TsEquipmentDetails extends Teilsatz {
     	
	private String tsTyp2 = "";
	private String count = "";
	private String ediQualifier = "";
    private String equipmentIdentificationNumber = "";	
    private String typeAndSizeISOCode	= "";
    private String typeAndSize = "";
    private String equipmentSuppliedBy = "";
    private String fullEmptyIndicator = "";
    private String movementTypeCode = "";
    private String movementType = "";
    private String equipmentPlan = "";
    private String weightPerUnitKilogram = "";
    private String tareWeightKilogram = "";
    private String grossWeightKilogram = "";
    private String grossVolumeCubicMetre = "";
    private String customsDeclarationNumber = "";
    
    public TsEquipmentDetails(String type, String edi) {
    	tsTyp = "EQUIPMENT";
	    tsTyp2 = type;
	    ediQualifier = edi;
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    tsTyp2 = fields[1];
	    if (size < 3) { return; }	    
	    count = fields[2];
	    if (size < 4) { return; }
	    ediQualifier = fields[3];
	    if (size < 5) { return; }
	    equipmentIdentificationNumber = fields[4];
	    if (size < 6) { return; }
	    typeAndSizeISOCode = fields[5];
	    if (size < 7) { return; }
	    typeAndSize = fields[6];
	    if (size < 8) { return; }
	    equipmentSuppliedBy = fields[7];
	    if (size < 9) { return; }	    
	    fullEmptyIndicator = fields[8]; 
	    if (size < 10) { return; }
	    movementTypeCode = fields[9];
	    if (size < 11) { return; }
	    movementType = fields[10]; 
	    if (size < 12) { return; }
	    equipmentPlan = fields[11]; 		    
		if (size < 13) { return; }
		weightPerUnitKilogram = fields[12]; 	     
		if (size < 14) { return; }
		tareWeightKilogram = fields[13]; 
		if (size < 15) { return; }
		grossWeightKilogram = fields[14]; 
		if (size < 16) { return; }
		grossVolumeCubicMetre = fields[15]; 
		if (size < 17) { return; }
		customsDeclarationNumber = fields[16]; 
    }

 
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(tsTyp2);
    	buff.append(trenner);
    	buff.append(count);    	
    	buff.append(trenner);
    	buff.append(ediQualifier);
    	buff.append(trenner);
    	buff.append(equipmentIdentificationNumber);    	
    	buff.append(trenner);
    	buff.append(typeAndSizeISOCode);
    	buff.append(trenner);
    	buff.append(typeAndSize);
    	buff.append(trenner);
    	buff.append(equipmentSuppliedBy);
    	buff.append(trenner);
    	buff.append(fullEmptyIndicator);
    	buff.append(trenner);
    	buff.append(movementTypeCode);
    	buff.append(trenner);
    	buff.append(movementType);
    	buff.append(trenner);
    	buff.append(equipmentPlan);
    	buff.append(trenner);    	
    	buff.append(weightPerUnitKilogram);
    	buff.append(trenner);
    	buff.append(tareWeightKilogram);    	
    	buff.append(trenner);
    	buff.append(grossWeightKilogram);
    	buff.append(trenner);
    	buff.append(grossVolumeCubicMetre);
    	buff.append(trenner);
    	buff.append(customsDeclarationNumber);

    	return new String(buff);   
    }

    public String getEquipmentPlan() {
		return equipmentPlan;
	}

	public void setEquipmentPlan(String equipmentPlan) {
		this.equipmentPlan = Utils.checkNull(equipmentPlan);
	}

	public String getGrossWeightKilogram() {
		return grossWeightKilogram;
	}

	public void setGrossWeightKilogram(String grossWeightKilogram) {
		this.grossWeightKilogram = Utils.checkNull(grossWeightKilogram);
	}

	public String getEquipmentIdentificationNumber() {
		return equipmentIdentificationNumber;
	}

	public void setEquipmentIdentificationNumber(
			String equipmentIdentificationNumber) {
		this.equipmentIdentificationNumber = equipmentIdentificationNumber;
	}

	public String getTypeAndSizeISOCode() {
		return typeAndSizeISOCode;
	}

	public void setTypeAndSizeISOCode(String typeAndSizeISOCode) {
		this.typeAndSizeISOCode = Utils.checkNull(typeAndSizeISOCode);
	}

	public String getTypeAndSize() {
		return typeAndSize;
	}

	public void setTypeAndSize(String typeAndSize) {
		this.typeAndSize = Utils.checkNull(typeAndSize);
	}

	public String getEquipmentSuppliedBy() {
		return equipmentSuppliedBy;
	}

	public void setEquipmentSuppliedBy(String equipmentSuppliedBy) {
		this.equipmentSuppliedBy = Utils.checkNull(equipmentSuppliedBy);
	}

	public String getFullEmptyIndicator() {
		return fullEmptyIndicator;
	}

	public void setFullEmptyIndicator(String fullEmptyIndicator) {
		this.fullEmptyIndicator = Utils.checkNull(fullEmptyIndicator);
	}

	public String getMovementTypeCode() {
		return movementTypeCode;
	}

	public void setMovementTypeCode(String movementTypeCode) {
		this.movementTypeCode = Utils.checkNull(movementTypeCode);
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = Utils.checkNull(movementType);
	}

	public String getWeightPerUnitKilogram() {
		return weightPerUnitKilogram;
	}

	public void setWeightPerUnitKilogram(String weightPerUnitKilogram) {
		this.weightPerUnitKilogram = Utils.checkNull(weightPerUnitKilogram);
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
	public String getCount() {
   	 	return count;
    }
    public void setCount(String unLocode) {
    	this.count = Utils.checkNull(unLocode);
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "0";
    	}
    }

    public void setCustomsDeclarationNumber(String value) {
		this.customsDeclarationNumber = Utils.checkNull(value);
	}
	public String getCustomsDeclarationNumber() {
		return customsDeclarationNumber;
	}
	
    public boolean isEmpty() {
    	return Utils.isStringEmpty(equipmentIdentificationNumber) 
    	&& Utils.isStringEmpty(typeAndSizeISOCode) 
    	&& Utils.isStringEmpty(grossWeightKilogram) 
    	&& Utils.isStringEmpty(tareWeightKilogram) 
    	&& Utils.isStringEmpty(grossVolumeCubicMetre); 
    }

}