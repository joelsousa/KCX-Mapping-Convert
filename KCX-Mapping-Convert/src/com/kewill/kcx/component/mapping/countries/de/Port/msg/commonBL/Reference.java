package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Reference extends KCXMessage {
		
	private String name;
	private String code;
	private ReferenceDetails referenceDetail;
	private String ediQualifier = "";
	
	private enum EAdditionalReference {	
		ReferenceName,
		Code,
		ReferenceDetail,
		EdiQualifier;
   }	

	public Reference() {
		super();  
	}

	public Reference(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAdditionalReference) tag) {  			
				case ReferenceDetail:
					referenceDetail = new ReferenceDetails(getScanner());	
					referenceDetail.parse(tag.name());					
  					break;   				
				default:
  					break;
  			}
  		} else {

  			switch((EAdditionalReference) tag) {   			
  				case ReferenceName:
  					setReferenceName(value);
  					break;   	
  				case Code:
  					setCode(value);
  					break;   					
  				case EdiQualifier:
  					setEdiQualifier(value);
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
  			return EAdditionalReference.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getReferenceName() {
		return name;
	}    
	public void setReferenceName(String value) {
		this.name = value;		
	}
	
	public String getCode() {
		return code;
	}    
	public void setCode(String value) {
		this.code = value;
	}
	
	public ReferenceDetails getReferenceDetail() {
		return referenceDetail;
	}		
    public void setReferenceDetail(ReferenceDetails argument) {
		this.referenceDetail = argument;
	}
    
    public String getEdiQualifier() {
		return ediQualifier;
	}    
	public void setEdiQualifier(String value) {
		this.ediQualifier = value;
	}
	
}

