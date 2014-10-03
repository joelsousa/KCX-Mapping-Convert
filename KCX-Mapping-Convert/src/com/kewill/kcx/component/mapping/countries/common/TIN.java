package com.kewill.kcx.component.mapping.countries.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: KCX<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the TIN Data with all Fields used in KIDS and UIDS 
 * 				: for all Modules.
 * 
 * @author Houdek
 * @version 1.0.00
 */

public class TIN extends KCXMessage {

	private String tin;
    private String isTINGermanApprovalNumber;
    private String customerIdentifier;
    private String identificationType;     //EI20100617: for ICS
    private String bo;                     //EI20120705 KIDS V21 Niederlassungsnummer

    private boolean debug   = false;

	private enum ETIN {
		//KIDS							//UIDS: no equivalent, will be filled directly from CT_Trader
		TIN,							//Trader.TIN
		CustomerIdentifier,				//Trader.CustomerID
		IsTINGermanApprovalNumber,		//Trader.CustomsID
		IdentificationType,	TINType,	//Trader.IdentificationType
		BO,								Branch, //in AES21: Trader.BO 
    }

	public TIN() {
		super();  
	}

    public TIN(XmlMsgScanner scanner) {
  		super(scanner);
  	}
    
  	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETIN) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((ETIN) tag) {
  				case TIN:
  					setTIN(value);
  					break;  					
  				case IsTINGermanApprovalNumber:
  					setIsTINGermanApprovalNumber(value);
  					break;  					
  				case CustomerIdentifier:
  					setCustomerIdentifier(value);
  					break;
  				case TINType:
  				case IdentificationType:
  					setIdentificationType(value);
  					break;
  				case BO:
  					setBO(value);
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
  			return ETIN.valueOf(token);
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

	public String getIsTINGermanApprovalNumber() {
		return isTINGermanApprovalNumber;
	}
	public void setIsTINGermanApprovalNumber(String argument) {
		this.isTINGermanApprovalNumber = argument;
	}
	
	public String getCustomerIdentifier() {
		return customerIdentifier;
	}
	public void setCustomerIdentifier(String argument) {
		customerIdentifier = argument;
	}
		
	public String getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(String argument) {
		identificationType = argument;
	}
			
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	
	public String getBO() {   //EI20120705
		return bo;
	}
	public void setBO(String value) {
		this.bo = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.customerIdentifier) && Utils.isStringEmpty(this.identificationType) && 
		        Utils.isStringEmpty(this.isTINGermanApprovalNumber) && Utils.isStringEmpty(this.tin) &&
		        Utils.isStringEmpty(this.bo)); 
	}
}
