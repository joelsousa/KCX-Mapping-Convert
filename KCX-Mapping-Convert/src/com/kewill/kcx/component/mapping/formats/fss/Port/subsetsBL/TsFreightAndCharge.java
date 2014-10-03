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

public class TsFreightAndCharge extends Teilsatz {
   
	private String count                        = "";
    private String freightAndChargeId		 	= "";	 // Transportkosten/-gebuehren/Ratenkalkulation
    private String freightAndChargeText			= "";	 // Klartext
    private String prepaidCollectIndicator		= "";	 // Fracht-Vorauszahlung-/Nachnahme-Anzeiger
    private String itemNumber		 			= "";	 // Produkt-/Leistungsnummer
    private String rateOrTariffClassId		 	= "";	 // Raten-Tarif-Klasse Identifikation
    private String rateOrTariffClassText		= "";	 // Raten-/Tarif-Klasse

    public TsFreightAndCharge() {
	    tsTyp = "FREIGHTANDCHARGE";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	
    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    count = fields[1];
	    if (size < 3) { return; }
	    freightAndChargeId = fields[2];
	    if (size < 4) { return; }
	    freightAndChargeText = fields[3];
	    if (size < 5) { return; }
	    prepaidCollectIndicator = fields[4];
	    if (size < 6) { return; }
	    itemNumber = fields[5];
	    if (size < 7) { return; }
	    rateOrTariffClassId = fields[6];	
	    if (size < 8) { return; }
	    rateOrTariffClassText = fields[7];	
    }
    
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);    	
    	buff.append(trenner);
    	buff.append(count);    	
    	buff.append(trenner);
    	buff.append(freightAndChargeId);
    	buff.append(trenner);
    	buff.append(freightAndChargeText);
    	buff.append(trenner);
    	buff.append(prepaidCollectIndicator);
    	buff.append(trenner);
    	buff.append(itemNumber);
    	buff.append(trenner);
    	buff.append(rateOrTariffClassId);
    	buff.append(trenner);
    	buff.append(rateOrTariffClassText);
    	//buff.append(trenner);

    	return new String(buff);
    }    

    public String getFreightAndChargeId() {
    	 return freightAndChargeId;
    }
    public void setFreightAndChargeId(String freightAndChargeId) {
    	this.freightAndChargeId = Utils.checkNull(freightAndChargeId);
    }

    public String getFreightAndChargeText() {
    	 return freightAndChargeText;
    }
    public void setFreightAndChargeText(String freightAndChargeText) {
    	this.freightAndChargeText = Utils.checkNull(freightAndChargeText);
    }

    public String getPrepaidCollectIndicator() {
    	 return prepaidCollectIndicator;
    }
    public void setPrepaidCollectIndicator(String prepaidCollectIndicator) {
    	this.prepaidCollectIndicator = Utils.checkNull(prepaidCollectIndicator);
    }

    public String getItemNumber() {
    	 return itemNumber;
    }
    public void setItemNumber(String itemNumber) {
    	this.itemNumber = Utils.checkNull(itemNumber);
    }
    public String getRateOrTariffClassId() {
    	 return rateOrTariffClassId;
    }

    public void setRateOrTariffClassId(String rateOrTariffClassId) {
    	this.rateOrTariffClassId = Utils.checkNull(rateOrTariffClassId);
    }
   	
    public String getRateOrTariffClassText() {
    	 return rateOrTariffClassText;
    }
    public void setRateOrTariffClassText(String rateOrTariffClassText) {
    	this.rateOrTariffClassText = Utils.checkNull(rateOrTariffClassText);
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

    public boolean isEmpty() {
		return   Utils.isStringEmpty(freightAndChargeId)	    	 
	    	&& Utils.isStringEmpty(freightAndChargeText)
	    	&& Utils.isStringEmpty(prepaidCollectIndicator)
	    	&& Utils.isStringEmpty(itemNumber)
	    	&& Utils.isStringEmpty(rateOrTariffClassId)
	    	&& Utils.isStringEmpty(rateOrTariffClassText);
    }

}
