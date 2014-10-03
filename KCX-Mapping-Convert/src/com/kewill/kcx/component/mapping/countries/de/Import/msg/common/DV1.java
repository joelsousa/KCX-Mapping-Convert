package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the DV1 Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DV1 extends KCXMessage {

	private String 				buyerSellerRelationship;
	private String 				buyerSellerRelationshipDetails; 
	private String				restrictionsCode;
	private String				restrictionsText;
	private String				licenseFeeDueCode;
	private String				licenseFeeText;
	private String				resaleSurrenderUsageCode;
	private String				resaleSurrenderUsageText;
	private String				agentRepresentationDV1Code;
	private String				termsServicesCode;
	private String				termsServicesText;
	private String				textPreviousDecisions;
	
	public DV1() {
		super();				
	}

	public DV1(XmlMsgScanner scanner) {
		super(scanner);		
	}
 
	private enum EImportDV1 {
		//KIDS:							UIDS:
		BuyerSellerRelationship,
		BuyerSellerRelationshipDetails,			
		RestrictionsCode,
		RestrictionsText,
		LicenseFeeDueCode,
		LicenseFeeText,
		ResaleSurrenderUsageCode,
		ResaleSurrenderUsageText,
		AgentRepresentationDV1Code,
		TermsServicesCode,
		TermsServicesText,
		TextPreviousDecisions;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EImportDV1) tag) {
			
			default:
				return;
			}
	    } else {
	    	switch ((EImportDV1) tag) {
			case BuyerSellerRelationship:
				 setBuyerSellerRelationship(value);
				 break;	
			case BuyerSellerRelationshipDetails:			
				 setBuyerSellerRelationshipDetails(value);
				 break;					
			case RestrictionsCode:
				 setRestrictionsCode(value);
				 break;	
			case RestrictionsText:
				 setRestrictionsText(value);
				 break;
			case LicenseFeeDueCode:
				 setLicenseFeeDueCode(value);
				 break;	
			case LicenseFeeText:			
				 setLicenseFeeText(value);
				 break;			
			case ResaleSurrenderUsageCode:
				 setResaleSurrenderUsageCode(value);
				 break;					
			case ResaleSurrenderUsageText:
				 setResaleSurrenderUsageText(value);
				 break;					 
			case AgentRepresentationDV1Code:			
				 setAgentRepresentationDV1Code(value);
				 break;					 			
			case TermsServicesCode:
				 setTermsServicesCode(value);
				 break;					 
			case TermsServicesText:
				 setTermsServicesText(value);
				 break;							
			case TextPreviousDecisions:
				 setTextPreviousDecisions(value);
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
  			return EImportDV1.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getBuyerSellerRelationship() {
		return buyerSellerRelationship;	
	}
	public void setBuyerSellerRelationship(String value) {
		this.buyerSellerRelationship = Utils.checkNull(value);
	}
	
	public String getBuyerSellerRelationshipDetails() {
		return buyerSellerRelationshipDetails;	
	}
	public void setBuyerSellerRelationshipDetails(String value) {
		this.buyerSellerRelationshipDetails = Utils.checkNull(value);
	}

	public String getRestrictionsCode() {
		return restrictionsCode;	
	}
	public void setRestrictionsCode(String value) {
		this.restrictionsCode = Utils.checkNull(value);
	}

	public String getRestrictionsText() {
		return restrictionsText;	
	}
	public void setRestrictionsText(String value) {
		this.restrictionsText = Utils.checkNull(value);
	}
	
	public String getLicenseFeeDueCode() {
		return licenseFeeDueCode;	
	}
	public void setLicenseFeeDueCode(String value) {
		this.licenseFeeDueCode = Utils.checkNull(value);
	}

	public String getLicenseFeeText() {
		return licenseFeeText;
	}
	public void setLicenseFeeText(String value) {
		this.licenseFeeText = Utils.checkNull(value);
	}

	public String getResaleSurrenderUsageCode() {
		return resaleSurrenderUsageCode;	
	}
	public void setResaleSurrenderUsageCode(String value) {
		this.resaleSurrenderUsageCode = Utils.checkNull(value);
	}	
	
	public String getResaleSurrenderUsageText() {
		return resaleSurrenderUsageText;	
	}
	public void setResaleSurrenderUsageText(String value) {
		this.resaleSurrenderUsageText = Utils.checkNull(value);
	}

	public String getAgentRepresentationDV1Code() {
		return agentRepresentationDV1Code;	
	}
	public void setAgentRepresentationDV1Code(String value) {
		this.agentRepresentationDV1Code = Utils.checkNull(value);
	}

	public String getTermsServicesCode() {
		return termsServicesCode;	
	}
	public void setTermsServicesCode(String value) {
		this.termsServicesCode = Utils.checkNull(value);
	}

	public String getTermsServicesText() {
		return termsServicesText;
	}
	public void setTermsServicesText(String value) {
		this.termsServicesText = Utils.checkNull(value);
	}
	
	public String getTextPreviousDecisions() {
		return textPreviousDecisions;
	}
	public void setTextPreviousDecisions(String value) {
		this.textPreviousDecisions = Utils.checkNull(value);
	}

	public boolean isEmpty() {		
		//AK20130403
		return (Utils.isStringEmpty(buyerSellerRelationship) &&
			    Utils.isStringEmpty(buyerSellerRelationshipDetails) &&
			    Utils.isStringEmpty(agentRepresentationDV1Code) &&
                Utils.isStringEmpty(restrictionsCode) && Utils.isStringEmpty(restrictionsText) && 	
                Utils.isStringEmpty(licenseFeeDueCode) && Utils.isStringEmpty(licenseFeeText) && 		       
                Utils.isStringEmpty(resaleSurrenderUsageCode) && Utils.isStringEmpty(resaleSurrenderUsageText) && 		       
                Utils.isStringEmpty(termsServicesCode) && Utils.isStringEmpty(termsServicesText) && 		       
		        Utils.isStringEmpty(textPreviousDecisions));
	}	
}



