package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Created		: 30.04.2010<br>
 * Description  : Contains the Member for save the TransportDetails.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TransportDetails extends KCXMessage {
	
	private String transportUnitCode;
	private String identityOfTransportUnits;
	private String commercialSealIdentification;
	private Text   complementaryInformation;   //??? is language (attribute) necessary? 
	private Text   sealInformation;            //??? is language (attribute) necessary? 
	
	private enum ETransportDetails {
		//KIDS:					UIDS: same as kids
		TransportUnitCode,         
		IdentityOfTransportUnits,
		CommercialSealIdentification,
		ComplementaryInformation,                   
		SealInformation;			
	}

	public TransportDetails() {
  		super();
  	}
			 
	public TransportDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((ETransportDetails) tag) {			
			default:
				return;
			}
	    } else {
	    	switch ((ETransportDetails) tag) {
			case TransportUnitCode:			
				 setTransportUnitCode(value);
				 break;	
			case IdentityOfTransportUnits:
				 setIdentityOfTransportUnits(value);
				 break;						 	 				 				 			
			case CommercialSealIdentification:			
				 setCommercialSealIdentification(value);
				 break;	
			case ComplementaryInformation:
				 //complementaryInformation = new Text(value, attr.getValue("language"));
				 complementaryInformation = new Text(value, attr);  //EI20110926
				 break;						 	 				 				 
			case SealInformation:
				 //sealInformation = new Text(value, attr.getValue("language"));
				 sealInformation = new Text(value, attr);  //EI20110926
				 break;
				default:
					break;				 	 				 			
	    	}
	    }		
	}

	public void stoppElement(Enum tag) {	
	}

	public Enum translate(String token) {
		try {
  			return ETransportDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getTransportUnitCode() {	
		return this.transportUnitCode;
	}
	public void setTransportUnitCode(String argument) {
		this.transportUnitCode = argument;
	}	

	public String getIdentityOfTransportUnits() {
		return this.identityOfTransportUnits;
	}
	public void setIdentityOfTransportUnits(String argument) {
		this.identityOfTransportUnits = argument;
	}	
 	
	public Text getComplementaryInformation() {
		return this.complementaryInformation;
	}
	
	public void setComplementaryInformation(Text complementaryInformation) {
		this.complementaryInformation = complementaryInformation;
	}
	
	public Text getSealInformation() {
		return this.sealInformation;
	}

	public void setSealInformation(Text sealInformation) {
		this.sealInformation = sealInformation;
	}		
	
	public boolean isEmpty() {
		
		String text1 = "";
		String text2 = "";
		if (this.complementaryInformation != null) {
			text1 = this.complementaryInformation.getText();
		}
		if (this.sealInformation != null) {
			//AK20110118-1
			// 	text2 = this.complementaryInformation.getText();
			text2 = this.sealInformation.getText();
		}
		
		if (Utils.isStringEmpty(this.transportUnitCode) &&
			Utils.isStringEmpty(this.identityOfTransportUnits) && 
    		Utils.isStringEmpty(this.commercialSealIdentification) &&    		
    		Utils.isStringEmpty(text1) &&   	
        	Utils.isStringEmpty(text2)) {
			return true;
		} else {
			return false;
		}
	}

	public String getCommercialSealIdentification() {
		return commercialSealIdentification;
	
	}

	public void setCommercialSealIdentification(String commercialSealIdentification) {
		this.commercialSealIdentification = Utils
				.checkNull(commercialSealIdentification);
	}
}
