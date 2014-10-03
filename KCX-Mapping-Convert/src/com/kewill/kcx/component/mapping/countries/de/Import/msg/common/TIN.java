/*
 * Function    : TIN(KIDS)  == UIDS: (simple) Tags within Party
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the TIN Data
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 *  -----------
 * Author      : EI
 * Date        :
 * Label       : EI20100617
 * Description : move from ase.common into common, added IdentificationType
 */

package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: TIN<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the TIN Data with all Fields used in  KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class TIN extends KCXMessage {

	private String tin;    
    private String customerId;
    private String vatNumber;     //EI20100617: for ICS
    private String declarantNumber;     //EI20121029: for Import/CH
    private String bo;                  //EI20121115 V20 Niederlassungsnummer
    private String identificationType;   //EI20130614 for BDP: 0=Addresse, 2=EORI-TIN

	private enum EImportTIN {
		//KIDS							
		TIN,						
		//EI20130418:CustomerIdentifier,	CustomerID,	
		CustomerID, CustomerIdentifier,    //EI20130802 ist doch wie vorher
		VATNumber,
		DeclarantNumber,       //for Import CH
		BO,                    //V20		
		IdentificationType,    //EI20130614 for BDP, wird aber in kids2fss gar nicht gemapped (ausser TsBSU)
    }
	
	public TIN() {
		super();  
	}

    public TIN(XmlMsgScanner scanner) {
  		super(scanner);
  	}
      	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportTIN) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportTIN) tag) {
  				case TIN:
  					setTIN(value);
  					break;  					  							
  				case CustomerIdentifier:   //EI20130802 doch hier das gleiche
  				case CustomerID:
  					setCustomerId(value);
  					break;
  				case VATNumber:  				
  					setVATNumber(value);
  					break;  
  				case DeclarantNumber:
  					setDeclarantNumber(value);
  					break;
  				case BO:
  					setBO(value);
  					break;
  				//EI20130802: case CustomerIdentifier:   //EI20130418
  				case IdentificationType:  //EI20130614 
  					setIdentificationType(value);
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
  			return EImportTIN.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getTIN() {
		return tin;
	}
	public void setTIN(String argument) {
		tin = argument;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String argument) {
		customerId = argument;
	}
		
	public String getVATNumber() {
		return vatNumber;
	}
	public void setVATNumber(String argument) {
		vatNumber = argument;
	}
		
	public String getDeclarantNumber() {
		return declarantNumber;
	}
	public void setDeclarantNumber(String argument) {
		vatNumber = declarantNumber;
	}
	
	public String getBO() {  
		return bo;
	}
	public void setBO(String value) {
		this.bo = value;
	}
	
	public String getIdentificationType() {  //EI20130802 
		return identificationType;
	}
	public void setIdentificationType(String value) {  //EI20130802
		this.identificationType = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.tin) && Utils.isStringEmpty(this.customerId) && 
		        Utils.isStringEmpty(this.vatNumber) && Utils.isStringEmpty(this.bo)); 
	}

}
