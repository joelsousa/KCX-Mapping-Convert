package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class AdditionalReferenceGroup extends KCXMessage {
		
	private List<Reference> additionalReferenceList;
	
	private enum EAdditionalReferenceGroup {		
		AdditionalReference;			       			
   }	

	public AdditionalReferenceGroup() {
		super();  
	}

	public AdditionalReferenceGroup(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAdditionalReferenceGroup) tag) {  			
				case AdditionalReference:
					Reference temp = new Reference(getScanner());  	
  					temp.parse(tag.name());
  					addAdditionalReferenceList(temp);
  					break; 
  				
				default:
  					break;
  			}
  		} else {

  			switch((EAdditionalReferenceGroup) tag) {   
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EAdditionalReferenceGroup.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public List<Reference> getAdditionalReferenceList() {
		return additionalReferenceList;
	}    
	public void setAdditionalReferenceList(List<Reference> list) {
		this.additionalReferenceList = list;
	}
	public void addAdditionalReferenceList(Reference value) {
		if (additionalReferenceList == null) {
			additionalReferenceList = new ArrayList<Reference>();
		}
		this.additionalReferenceList.add(value);
	}	
}

