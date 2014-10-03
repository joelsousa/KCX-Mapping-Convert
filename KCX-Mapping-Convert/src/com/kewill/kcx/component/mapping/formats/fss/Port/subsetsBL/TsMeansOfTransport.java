package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.MeansOfTransport;
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

public class TsMeansOfTransport extends Teilsatz {
	
    private String shipownerVoyageNumber		= "";	 // an..17;
    private String typeOfMeansOfTransport		= "";	 // an..17;
    private String carrierId		 			= "";	 // an..17;
    private String carrierName					= "";	 // an..35;
    private String radioCallSign				= "";	 // an..9;
    private String vesselName					= "";	 // an..35;
    private String vesselNationalityCode		= "";	 // an..3;

    public TsMeansOfTransport() {	   
	    tsTyp = "MEANSOFTRANSPORT";
    }
    
    public TsMeansOfTransport(String group) {	    
    	if (Utils.isStringEmpty(group)) {
    		tsTyp = "MEANSOFTRANSPORT";
    	} else {
    		tsTyp = group + "MEANSOFTRANSPORT";
    	}
    }
    
    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];    	
	    //if (size < 2) { return; }
	    //tsGroup = fields[1];
	    if (size < 2) { return; }
	    shipownerVoyageNumber = fields[1];
	    if (size < 3) { return; }
	    typeOfMeansOfTransport = fields[2];
	    if (size < 4) { return; }
	    carrierId = fields[3];
	    if (size < 5) { return; }
	    carrierName = fields[4];
	    if (size < 6) { return; }
	    radioCallSign = fields[5];
	    if (size < 7) { return; }
	    vesselName = fields[6];
	    if (size < 8) { return; }
	    vesselNationalityCode = fields[7];
    }

    public String getShipownerVoyageNumber() {
    	 return shipownerVoyageNumber;
    }
    public void setShipownerVoyageNumber(String shipownerVoyageNumber) {
    	this.shipownerVoyageNumber = Utils.checkNull(shipownerVoyageNumber);
    }
    
    public String getTypeOfMeansOfTransport() {
    	 return typeOfMeansOfTransport;
    }
    public void setTypeOfMeansOfTransport(String typeOfMeansOfTransport) {
    	this.typeOfMeansOfTransport = Utils.checkNull(typeOfMeansOfTransport);
    }

    public String getCarrierId() {
    	 return carrierId;
    }
    public void setCarrierId(String carrierId) {
    	this.carrierId = Utils.checkNull(carrierId);
    }

    public String getCarrierName() {
    	 return carrierName;
    }
    public void setCarrierName(String carrierName) {
    	this.carrierName = Utils.checkNull(carrierName);
    }

    public String getRadioCallSign() {
    	 return radioCallSign;
    }
    public void setRadioCallSign(String radioCallSign) {
    	this.radioCallSign = Utils.checkNull(radioCallSign);
    }

    public String getVesselName() {
    	 return vesselName;
    }
    public void setVesselName(String vesselName) {
    	this.vesselName = Utils.checkNull(vesselName);
    }

    public String getVesselNationalityCode() {
    	 return vesselNationalityCode;
    }
    public void setVesselNationalityCode(String vesselNationalityCode) {
    	this.vesselNationalityCode = Utils.checkNull(vesselNationalityCode);
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	//buff.append(tsGroup);
    	//buff.append(trenner);
    	buff.append(shipownerVoyageNumber);
    	buff.append(trenner);
    	buff.append(typeOfMeansOfTransport);
    	buff.append(trenner);
    	buff.append(carrierId);
    	buff.append(trenner);
    	buff.append(carrierName);
    	buff.append(trenner);
    	buff.append(radioCallSign);
    	buff.append(trenner);
    	buff.append(vesselName);
    	buff.append(trenner);
    	buff.append(vesselNationalityCode);
    	//buff.append(trenner);

    	return new String(buff);
    }

    public void setAllFields(MeansOfTransport meansOfTransport) {
    	if (meansOfTransport == null) {
    		return;
    	}
    	shipownerVoyageNumber = Utils.checkNull(meansOfTransport.getShipownerVoyageNumber());
    	typeOfMeansOfTransport = Utils.checkNull(meansOfTransport.getTypeOfMeansOfTransport());
    	carrierId = Utils.checkNull(meansOfTransport.getCarrierId());
    	carrierName = Utils.checkNull(meansOfTransport.getCarrierName());
    	radioCallSign = Utils.checkNull(meansOfTransport.getRadioCallSign());
    	vesselName = Utils.checkNull(meansOfTransport.getVesselName());
    	vesselNationalityCode = Utils.checkNull(meansOfTransport.getVesselNationalityCode());	
    }
    
    public boolean isEmpty() {
	return   Utils.isStringEmpty(shipownerVoyageNumber)
    	&& Utils.isStringEmpty(typeOfMeansOfTransport)
    	&& Utils.isStringEmpty(carrierId)
    	&& Utils.isStringEmpty(carrierName)
    	&& Utils.isStringEmpty(radioCallSign)
    	&& Utils.isStringEmpty(vesselName)
    	&& Utils.isStringEmpty(vesselNationalityCode);

    }

}
