package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: RateClassType.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class RateClassType extends KCXMessage {

	private String typeCode;
    private String basisCode;
    private String appliedPercent;
    private String referenceId;
    private String referenceTypeCode;
       
    public RateClassType() {
	      	super();	       
    }
    
    public RateClassType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum ERateClassType {
    	TypeCode, 
    	BasisCode,
    	AppliedPercent,
    	ReferenceID,
    	ReferenceTypeCode,
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ERateClassType) tag) {    			
    			default:
    					return;
    			}
    		} else {
    			switch ((ERateClassType) tag) {
    				case TypeCode:
						setTypeCode(value);    					
						break;
					
    				case BasisCode:   
    					setBasisCode(value);    					
    					break; 
    					
    				case AppliedPercent:
    					setAppliedPercent(value);    					
    					break;
    					
    				case ReferenceID:
    					setReferenceId(value);    					
    					break;
    					
    				case ReferenceTypeCode:
    					setReferenceTypeCode(value);    					
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
    			return ERateClassType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		
		public String getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}

		public String getBasisCode() {
			return basisCode;
		}

		public void setBasisCode(String basisCode) {
			this.basisCode = basisCode;
		}

		public String getAppliedPercent() {
			return appliedPercent;
		}
		public void setAppliedPercent(String value) {
			appliedPercent = value;
		}

		public String getReferenceId() {
			return referenceId;
		}

		public void setReferenceId(String referenceId) {
			this.referenceId = referenceId;
		}

		public String getReferenceTypeCode() {
			return referenceTypeCode;
		}

		public void setReferenceTypeCode(String referenceTypeCode) {
			this.referenceTypeCode = referenceTypeCode;
		}

		public boolean isEmpty() {
			return 	Utils.isStringEmpty(typeCode) && Utils.isStringEmpty(basisCode) &&
					Utils.isStringEmpty(appliedPercent) && Utils.isStringEmpty(referenceId) &&					
					Utils.isStringEmpty(referenceTypeCode);
		}
}
