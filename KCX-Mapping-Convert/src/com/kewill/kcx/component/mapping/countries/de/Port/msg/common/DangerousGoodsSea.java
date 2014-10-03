package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 22.11.2011<br>
 * Description	: SeaTransportSpecification of DangerousGoods.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class DangerousGoodsSea extends KCXMessage {

	 private String imdgClass;        	    //agr_imdg , agi agt agx
	 private String imdgClassAmendments;    //fzpagi_imdgc	 
	 private String unNumber;               //UN-Nummer	
	 private String emsNumber;			    //fzpagi_ems
	 private String emsSecondNumber;			    //fzpagi_ems
	 private String marinePollutantFlag;    // marpol  (J/N)
	 private String watterPollutionClass;  //fzpagi_wgkcde: WGK-Code, Wassergefährdungsklasse 0,1,2,3	
	 private String mfag1;        //fzpagi_mfag: Medical First Aid Guide, Nr. 1
	 private String mfag2;       //fzpagi_mfag: Medical First Aid Guide, Nr. 2
	 private String label1;       //fzpagi_label1
	 private String label2;     //fzpagi_label2
	 private String label3;     //fzpagi_label3	 	
	 private String packagingSafetyGroup;
	 private String stuffingCategory;	            // staukt Staukategorie bzw. -methode, only for ImdgClass 1 
	 private String stuffingDescription;	    // fzpagt_stautx 

	 public DangerousGoodsSea() {
		 super();  
	 }

	 public DangerousGoodsSea(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum ESeaTransport {	
		// Kids-TagNames, 		     FSS	  KFF;		    
		 	IMDGClass,					
		 	ImdgClassAmendments,
		 	EmsNumber,		
		 	EmsSecondNumber,		
		 	UnNumber,				 	
		 	MarinePollutantFlag,  
		 	WatterPollutionClass,
		 	MFAG1,
		 	MFAG2,
		 	DangerLabel1,
		 	DangerLabel2,  DangeLabel2,
		 	DangerLabel3,  DangeLabel3,		 	
		 	PackagingSafetyGroup, PackingSafetyGroup,		 	                    
		 	StuffingCategory,                
		 	StuffingDescription;
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ESeaTransport) tag) {
				default: return;			
			}
		} else {			
			switch ((ESeaTransport) tag) {			
				case IMDGClass:
					setImdgClass(value);
					break;
				case ImdgClassAmendments:
					setImdgClassAmendments(value);
					break;
				case EmsNumber:
					setEmsNumber(value);
					break;	
				case EmsSecondNumber:
					setEmsSecondNumber(value);
					break;
				case UnNumber:
					setUnNumber(value);
					break;	
				case MarinePollutantFlag:
					setMarinePollutantFlag(value);
					break;
				case WatterPollutionClass:
					setWatterPollutionClass(value);
					break;					
				case MFAG1:
					setMfag1(value);
					break;
				case MFAG2:
					setMfag2(value);
					break;
				case DangerLabel1:
					setDangerLabel1(value);
					break;				
				case DangerLabel2:
				case DangeLabel2:
					//EI20130821: setDangerLabel3(value);
					setDangerLabel2(value);    //EI20130821
					break;	
				case DangerLabel3:
				case DangeLabel3:
					setDangerLabel3(value);
					break;
				case PackagingSafetyGroup:
				case PackingSafetyGroup:
					setPackagingSafetyGroup(value);
					break;
				case StuffingCategory:
					setStuffingCategory(value);
					break;
				case StuffingDescription:
					setStuffingDescription(value);
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
			return ESeaTransport.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

	 
	public String getImdgClass() {
		return imdgClass;
	}
	public void setImdgClass(String argument) {
		this.imdgClass = argument;
	}					
		
	public String getImdgClassAmendments() {
		return imdgClassAmendments;
	}
	public void setImdgClassAmendments(String argument) {
		this.imdgClassAmendments = argument;
	}	
	
	public String getEmsNumber() {
		return emsNumber;
	}
	public void setEmsNumber(String argument) {
		this.emsNumber = argument;
	}
	
	public String getEmsSecondNumber() {
		return emsSecondNumber;
	}
	public void setEmsSecondNumber(String argument) {
		this.emsSecondNumber = argument;
	}
	
	public String getUnNumber() {
		return unNumber;
	}
	public void setUnNumber(String argument) {
		this.unNumber = argument;
	}	
		
	public String getMarinePollutantFlag() {
			return marinePollutantFlag;
	}
	public void setMarinePollutantFlag(String argument) {
			this.marinePollutantFlag = argument;
	}	
	
	public String getWatterPollutionClass() {
		return watterPollutionClass;
	}
	public void setWatterPollutionClass(String argument) {
		this.watterPollutionClass = argument;
	}	
		
	public String getMfag1() {
		return mfag1;
	}
	public void setMfag1(String argument) {
		this.mfag1 = argument;
	}	
	
	public String getMfag2() {
		return mfag2;
	}
	public void setMfag2(String argument) {
		this.mfag2 = argument;
	}	
    public String getDangerLabel1() {
		return label1;
	}
	public void setDangerLabel1(String argument) {
		this.label1 = argument;
	}					
	
	 public String getDangerLabel2() {
		return label2;
	}
	public void setDangerLabel2(String argument) {
		this.label2 = argument;
	}	
		
	 public String getDangerLabel3() {
		return label3;
	}
	public void setDangerLabel3(String argument) {
		this.label3 = argument;
	}	
				
	public String getPackagingSafetyGroup() {
		return packagingSafetyGroup;
	}
	public void setPackagingSafetyGroup(String argument) {
		this.packagingSafetyGroup = argument;
	}	
		
	public String getStuffingCategory() {
		return stuffingCategory;
	}
	public void setStuffingCategory(String argument) {
		this.stuffingCategory = argument;
	}					
				
	public String getStuffingDescription() {
		return stuffingDescription;
	}
	public void setStuffingDescription(String argument) {
		this.stuffingDescription = argument;
	}		
			
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.imdgClass) && Utils.isStringEmpty(this.emsNumber) && 
		        Utils.isStringEmpty(this.marinePollutantFlag) &&		       
		        Utils.isStringEmpty(this.mfag1) && Utils.isStringEmpty(this.label1));  
	}
}
