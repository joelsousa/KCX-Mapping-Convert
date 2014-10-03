package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL;


import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AttachedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Port BL.<br>
 * Created		: 04.05.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAttachedEquipment extends Teilsatz  {
		
	private String tsTyp2 = "";	
	private String count = "";	
	private String ediQualifier = "";	
	private String equipmentNumber = "";
	
	public TsAttachedEquipment() throws FssException {
		tsTyp = "ATTACHEDEQUIPMENT";
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
		    if (size < 4) { return; }
		    equipmentNumber = fields[3];	
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
    	buff.append(equipmentNumber);
    	//buff.append(trenner);
    	
    	return new String(buff);    
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
	    		this.count = "";
	    	}
	    }
	    
	    public String getTsTyp2() {
	    	 return tsTyp2;
	    }
	    public void setTsTyp2(String pos) {
	    	this.tsTyp2 = Utils.checkNull(pos);
	    }
	    
	    public String getEdiQualifier() {
	    	 return ediQualifier;
	    }
	    public void setEdiQualifier(String languageCoded) {
	    	this.ediQualifier = Utils.checkNull(languageCoded);
	    }

	    public String getEquipmentNumber() {
	    	 return equipmentNumber;
	    }
	    public void setEquipmentNumber(String text) {
	    	this.equipmentNumber = Utils.checkNull(text);
	    }	
	    
	    public void setAllFields(AttachedEquipment attached) {
	    	if (attached == null) {
	    		return;
	    	}
	    	if (attached.getReeferGenerator() != null) {
	    		this.tsTyp2 = "REEFERGENERATOR";
	    		this.ediQualifier = "RG";
	    		this.equipmentNumber = attached.getReeferGenerator().getEquipmentIdentificationNumber();	
	    	}  else if (attached.getTrailer() != null) {
	    		this.tsTyp2 = "TRAILER";
	    		this.ediQualifier = "TE";
	    		this.equipmentNumber = attached.getTrailer().getEquipmentIdentificationNumber();	
	    	} else if (attached.getChassis() != null) {
	    		this.tsTyp2 = "CHASIS";
	    		this.ediQualifier = "CH";
	    		this.equipmentNumber = attached.getChassis().getEquipmentIdentificationNumber();	
	    	}
	    }	    
	    
	    public boolean isEmpty() {
	    	return Utils.isStringEmpty(equipmentNumber);	    		
	    
	    }	    
}
