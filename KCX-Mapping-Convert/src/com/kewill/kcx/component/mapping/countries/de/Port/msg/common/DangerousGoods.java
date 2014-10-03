package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * description	: DangerousGoods.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class DangerousGoods extends KCXMessage {
    	
	 private String imdgClass;        	    
	 private String imdgAmendments;       
	 private String unNumber;               
	 private String technicalSpecification; 
	 private String dangerDescription;	 //fzpagt_eigens; wie ätzend, ..
	 private String dangerReleaser;
	 private String annotations;	
	 private String marinePollutantFlag;    // marpol  (J/N)
	 private String netWeight;	            //netgew 
	 private String netWeightExplosive;
	 private String explosiveGoodsCertificateFlag;
	 private String dangerToleranceFlag;
	 private String packagingSafetyGroup;	        // vpsist Verpackungssicherheits(fetigkeit)stufe: 0,1,2,3 ..
	 private String packagingType;    
	 private String limitetQuantitiesCode;  //fzpagi_limqu: TLQ = Ja, leer = Nein	  
	 private String expectedQuantitiesCode;     //fzpagi_excqu: TEQ = Ja, leer = Nein
	 private String flashpoint;	 		    // flamp Flammpunkt
	 private String flashpointQualifier;		    
	 		 	 
	 private DangerousGoodsSea  seaDetails;
	 private DangerousGoodsLand landDetails; 
	 private RadioactiveGoods radioactiveGoods;
	
	 public DangerousGoods() {
		 super();  
	 }

	 public DangerousGoods(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum EDangerousGoods {	
			// Kids-TagNames, 		     FSS	  		KFF;
		 	IMDGClass,              //fzpagr_imdg
		 	IMDGAmendments,         //fzpagi_imdgc	
		 	UNNumber,
		 	TechnicalSpecification, 
		 	DangerDescription,
		 	DangerReleaser,
		 	Annotations,
		 	MarinePollutantFlag,
		 	NetMass,	
		 	NetMassExplosive,
		 	ExplosiveGoodsCertificateFlag,
		 	DangerToleranceFlag,
		 	PackagingSafetyGroup, PackingSafetyGroup,
		 	PackagingType, PackingType,  
		 	LimitetQuantitiesCode,  LimitedQuantitiesCode, limitedQuantitiesCode,     
		 	ExpectedQuantitiesCode,  expectedQuantitiesCode,             		    		   
		    Flashpoint,	 	
		    FlashpointQualifier,	
		    WatterPollutionHazard,  			    
		    SeaTransportDetails,
		    LandTransportDetails,
		    RadioactiveGoodsDetails;
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EDangerousGoods) tag) {
				case SeaTransportDetails:
					seaDetails = new DangerousGoodsSea(getScanner());
					seaDetails.parse(tag.name());
					break;
				case LandTransportDetails:
					landDetails = new DangerousGoodsLand(getScanner());
					landDetails.parse(tag.name());
					break;				
				case RadioactiveGoodsDetails:
					radioactiveGoods = new RadioactiveGoods(getScanner());
					radioactiveGoods.parse(tag.name());
					break;
				default: return;
			}
		} else {			
			switch ((EDangerousGoods) tag) {
				case IMDGClass:
					setImdgClass(value);
					break;
				case IMDGAmendments:
					setImdgAmendments(value);
					break;				
				case UNNumber:
					setUnNumber(value);
					break;
				case TechnicalSpecification:
					setTechnicalSpecification(value);
					break;
				case DangerDescription:
					setDangerDescription(value);
					break;
				case DangerReleaser:
					setDangerReleaser(value);
					break;
				case Annotations:
					setAnnotations(value);
					break;
				case MarinePollutantFlag:
					setMarinePollutantFlag(value);
					break;
				case PackagingSafetyGroup:
				case PackingSafetyGroup:   //EI20130821
					setPackagingSafetyGroup(value);
					break;
				case PackagingType:
				case PackingType:   //EI20130821
					setPackagingType(value);
					break;
				case NetMass:
					setNetWeight(value);
					break;
				case NetMassExplosive:
					setNetWeightExplosive(value);
					break;
				case ExplosiveGoodsCertificateFlag:
					setExplosiveGoodsCertificateFlag(value);
					break;
				case DangerToleranceFlag:
					setDangerToleranceFlag(value);
					break;
				case ExpectedQuantitiesCode:
				case expectedQuantitiesCode:   //EI20130820
					setExpectedQuantitiesCode(value);
					break;
				case LimitetQuantitiesCode:
				case LimitedQuantitiesCode:    //EI20130820
				case limitedQuantitiesCode:    //EI20130820
					setLimitetQuantitiesCode(value);
					break;
				case Flashpoint:
					setFlashpoint(value);
					break;
				case FlashpointQualifier:	
					setFlashpointQualifier(value);
					break;
								
				default:
					return;
			}
		}
	}

	 public void stoppElement(Enum tag) {
	 }

	
	 public Enum translate(String token) {
		 try {
			return EDangerousGoods.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }
	
	 ///////////
	 
	public String getImdgClass() {
		return imdgClass;
	}
	public void setImdgClass(String value) {
		this.imdgClass = value;
	}
		
	public String getImdgAmendments() {
		return imdgAmendments;
	}
	public void setImdgAmendments(String value) {
		this.imdgAmendments = value;
	}
	
	public String getUnNumber() {
		return unNumber;
	}
	public void setUnNumber(String value) {
		this.unNumber = value;
	}
		
	public String getTechnicalSpecification() {
		return technicalSpecification;
	}
	public void setTechnicalSpecification(String argument) {
		this.technicalSpecification = argument;
	}

	public String getDangerDescription() {
		return dangerDescription;
	}
	public void setDangerDescription(String argument) {
		this.dangerDescription = argument;
	}	
	
	public String getDangerReleaser() {
		return dangerReleaser;
	}
	public void setDangerReleaser(String argument) {
		this.dangerReleaser = argument;
	}	
	
	public String getAnnotations() {
		return annotations;
	}
	public void setAnnotations(String argument) {
		this.annotations = argument;
	}	
	public String getMarinePollutantFlag() {
		return marinePollutantFlag;
	}
	public void setMarinePollutantFlag(String argument) {
		this.marinePollutantFlag = argument;
	}	
	
	public String getPackagingSafetyGroup() {
		return packagingSafetyGroup;
	}
	public void setPackagingSafetyGroup(String value) {
		this.packagingSafetyGroup = value;
	}
	
	public String getPackagingType() {
		return packagingType;
	}
	public void setPackagingType(String argument) {
		this.packagingType = argument;
	}	
		
    public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String argument) {
		this.netWeight = argument;
	}
	
	public String getNetWeightExplosive() {
		return netWeightExplosive;
	}
	public void setNetWeightExplosive(String argument) {
		this.netWeightExplosive = argument;
	}
	
	public String getExplosiveGoodsCertificateFlag() {
		return explosiveGoodsCertificateFlag;
	}
	public void setExplosiveGoodsCertificateFlag(String argument) {
		this.explosiveGoodsCertificateFlag = argument;
	}	
		
	public String getDangerToleranceFlag() {
		return dangerToleranceFlag;
	}
	public void setDangerToleranceFlag(String argument) {
		this.dangerToleranceFlag = argument;
	}
	
	public String getExpectedQuantitiesCode() {
		return expectedQuantitiesCode;
	}
	public void setExpectedQuantitiesCode(String argument) {
		this.expectedQuantitiesCode = argument;
	}	
	
	public String getLimitetQuantitiesCode() {
		return limitetQuantitiesCode;
	}
	public void setLimitetQuantitiesCode(String argument) {
		this.limitetQuantitiesCode = argument;
	}
	
	public String getFlashpoint() {
		return flashpoint;
	}
	public void setFlashpoint(String argument) {
		this.flashpoint = argument;
	}
	
	public String getFlashpointQualifier() {
		return flashpointQualifier;
	}
	public void setFlashpointQualifier(String argument) {
		this.flashpointQualifier = argument;
	}
   
	public DangerousGoodsSea getSeaTransportDetails() {
		return seaDetails;
	}
	public void setSeaTransportDetails(DangerousGoodsSea argument) {
		this.seaDetails = argument;
	}	
	
	public DangerousGoodsLand getLandTransportDetails() {
		return landDetails;
	}
	public void setLandTransportDetails(DangerousGoodsLand argument) {
		this.landDetails = argument;
	}
	
	public RadioactiveGoods getRadioactiveGoodsDetails() {
		return radioactiveGoods;
	}
	public void setRadioactiveGoodsDetails(RadioactiveGoods argument) {
		this.radioactiveGoods = argument;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.imdgClass) &&
				Utils.isStringEmpty(this.unNumber) &&
				Utils.isStringEmpty(this.technicalSpecification) &&
				Utils.isStringEmpty(this.marinePollutantFlag) &&
				Utils.isStringEmpty(this.netWeight) &&				
				seaDetails == null && landDetails == null);			
		        
	}
}
