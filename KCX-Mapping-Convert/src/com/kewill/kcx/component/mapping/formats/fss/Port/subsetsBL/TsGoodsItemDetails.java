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

public class TsGoodsItemDetails extends Teilsatz {

    private String numberOfPackages	= "";
    private String typeOfPackagesIdentification = "";
    private String typeOfPackagesText = "";
    private String harmonizedSystemCommodityCode = "";
    private String netWeightKilogram = "";
    private String tareWeightKilogram = "";
    private String grossVolumeCubicMetre = "";
    private String bookingReferenceNumber = "";
    private String customsDeclarationNumber = "";   
    private String articleNumber = "";
    private String cargoControlNumber = "";
      
    public TsGoodsItemDetails() {
	    tsTyp = "DETAILSONITEM";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }	
	    numberOfPackages = fields[1];
	    if (size < 3) { return; }
	    typeOfPackagesIdentification = fields[2]; 
	    if (size < 4) { return; }
	    typeOfPackagesText = fields[3]; 
	    if (size < 5) { return; }
	    harmonizedSystemCommodityCode = fields[4]; 	   	  
	    if (size < 6) { return; }
	    netWeightKilogram = fields[5];
	    if (size < 7) { return; }
	    tareWeightKilogram = fields[6];
	    if (size < 8) { return; }
	    grossVolumeCubicMetre = fields[7];
	    if (size < 9) { return; }
	    bookingReferenceNumber = fields[8]; 
	    if (size < 10) { return; }
	    customsDeclarationNumber = fields[9];
	    if (size < 11) { return; }
	    articleNumber = fields[10]; 
	    if (size < 12) { return; }
	    cargoControlNumber = fields[11]; 	    	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(numberOfPackages);
    	buff.append(trenner);
    	buff.append(typeOfPackagesIdentification);
    	buff.append(trenner);
    	buff.append(typeOfPackagesText);
    	buff.append(trenner);
    	buff.append(harmonizedSystemCommodityCode);
    	buff.append(trenner);
    	buff.append(netWeightKilogram);
    	buff.append(trenner);
    	buff.append(tareWeightKilogram);
    	buff.append(trenner);    	
    	buff.append(grossVolumeCubicMetre);
    	buff.append(trenner);
    	buff.append(bookingReferenceNumber);
    	buff.append(trenner);
    	buff.append(customsDeclarationNumber);
    	buff.append(trenner);
    	buff.append(articleNumber);
    	buff.append(trenner);
    	buff.append(cargoControlNumber);
    	//buff.append(trenner);

    	return new String(buff);   
    }

    public String getNumberOfPackages() {
    	 return numberOfPackages;
    }
    public void setNumberOfPackages(String value) {
    	this.numberOfPackages = Utils.checkNull(value);
    } 
    
    public String getTypeOfPackagesIdentification() {
   	 	return typeOfPackagesIdentification;
    }
    public void setTypeOfPackagesIdentification(String value) {
    	this.typeOfPackagesIdentification = Utils.checkNull(value);
    } 
   
   	public String getTypeOfPackagesText() {
   		return typeOfPackagesText;
  	}
   	public void setTypeOfPackagesText(String value) {
   		this.typeOfPackagesText = Utils.checkNull(value);
  	} 
  
    public String getNetWeightKilogram() {
    	 return netWeightKilogram;
    }
    public void setNetWeightKilogram(String value) {
    	this.netWeightKilogram = Utils.checkNull(value);
    }

    public String getTareWeightKilogram() {
    	 return tareWeightKilogram;
    }
    public void setTareWeightKilogram(String value) {
    	this.tareWeightKilogram = Utils.checkNull(value);
    }

    public String getGrossVolumeCubicMetre() {
    	 return grossVolumeCubicMetre;
    }
    public void setGrossVolumeCubicMetre(String value) {
    	this.grossVolumeCubicMetre = Utils.checkNull(value);
    }

    public String getBookingReferenceNumber() {
      	return bookingReferenceNumber;
    }
    public void setBookingReferenceNumber(String item) {
    	this.bookingReferenceNumber = Utils.checkNull(item);
    }
      
    public String getCustomsDeclarationNumber() {
       	return customsDeclarationNumber;
    }
    public void setCustomsDeclarationNumber(String value) {
        this.customsDeclarationNumber = Utils.checkNull(value);
    }
     
   	public String getArticleNumber() {
   		return articleNumber;
   	}
   	public void setArticleNumber(String value) {
   		this.articleNumber = Utils.checkNull(value);
   	}

   	public String getCargoControlNumber() {
   		return cargoControlNumber;
   	}
   	public void setCargoControlNumber(String value) {
   		this.cargoControlNumber = Utils.checkNull(value);
   	}
   
   	public String getHarmonizedSystemCommodityCode() {
	   	 return harmonizedSystemCommodityCode;
	}
	public void setHarmonizedSystemCommodityCode(String value) {
	   	this.harmonizedSystemCommodityCode = Utils.checkNull(value);
	}
   
   
    public boolean isEmpty() {
	return Utils.isStringEmpty(numberOfPackages) 
    	&& Utils.isStringEmpty(typeOfPackagesIdentification) 
    	&& Utils.isStringEmpty(netWeightKilogram) 
    	&& Utils.isStringEmpty(tareWeightKilogram) 
    	&& Utils.isStringEmpty(grossVolumeCubicMetre); 
    }

}
