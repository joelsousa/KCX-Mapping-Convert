package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 04.05.2010<br>
 * Description  : Contains the Member for save tags from the UIDS message.       
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class EmcsPackage extends KCXMessage {
	
	private String kindOfPackages;
	private String numberOfPackages;
	private String commercialSealIdentification;
	private Text   sealInformation;    

	
	private enum EEmcsPackage {
		//KIDS:					UIDS: same as kids
		KindOfPackages,         
		NumberOfPackages,
		CommercialSealIdentification,
		SealInformation;			
	}
	
	public EmcsPackage() {
  		super();
  	}
			 
	public EmcsPackage(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {	    	
			switch ((EEmcsPackage) tag) {	
			/*
			case SealInformation: 
				sealInformation = new Text(value, attr.getValue("language"));
				*/
			default:
				return;
			}
	    } else {
	    	switch ((EEmcsPackage) tag) {
			case KindOfPackages:			
				 setKindOfPackages(value);
				 break;	
			case NumberOfPackages:
				 setNumberOfPackages(value);
				 break;						 	 				 				 			
			case CommercialSealIdentification:			
				 setCommercialSealIdentification(value);
				 break;	
			case SealInformation: 
				//sealInformation = new Text(value, attr.getValue("language"));	
				sealInformation = new Text(value, attr);  //EI20110926
			default:
				break;
	    	}
	    }		
	}

	public void stoppElement(Enum tag) {	
	}

	public Enum translate(String token) {
		try {
  			return EEmcsPackage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getKindOfPackages() {
		return kindOfPackages;
	
	}

	public void setKindOfPackages(String kindOfPackages) {
		this.kindOfPackages = Utils.checkNull(kindOfPackages);
	}

	public String getNumberOfPackages() {
		return numberOfPackages;
	
	}

	public void setNumberOfPackages(String numberOfPackages) {
		this.numberOfPackages = Utils.checkNull(numberOfPackages);
	}

	public String getCommercialSealIdentification() {
		return commercialSealIdentification;
	
	}

	public void setCommercialSealIdentification(String commercialSealIdentification) {
		this.commercialSealIdentification = Utils
				.checkNull(commercialSealIdentification);
	}

	public Text getSealInformation() {
		return sealInformation;
	
	}

	public void setSealInformation(Text sealInformation) {
		this.sealInformation = sealInformation;
	}
	
	public boolean isEmpty() {
		String temp = "";
		if (this.sealInformation != null) {
			temp = this.sealInformation.getText();
		}
		return (Utils.isStringEmpty(this.kindOfPackages) &&	
				Utils.isStringEmpty(this.numberOfPackages) &&
				Utils.isStringEmpty(this.commercialSealIdentification)  && 		
				Utils.isStringEmpty(temp));		
	}	
	
}
