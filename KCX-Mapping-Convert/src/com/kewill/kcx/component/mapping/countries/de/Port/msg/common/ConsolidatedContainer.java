package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module      : Port.<br>
 * Created     : 28.10.2011<br>
 * Description : 
 * 
 * @author Iwaniuk.
 * @version 1.0.00
 */
public class ConsolidatedContainer extends KCXMessage {

	 private String   sequenceNumber; //aks_lfdnr
	 private String   portReference;  // aks_saconr; Z/B-Nummer BHT-Reference
	 private String   ladingFlag;     // aks_savoll;  empty or full

	 public ConsolidatedContainer() {
		 super();  
	 }

	 public ConsolidatedContainer(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum ESammelContainer {	
			// Kids-TagNames, 			UIDS-TagNames;
		 		SequenceNumber,					
		 		PortReference,					
				LadingFlag;			        				
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ESammelContainer) tag) {
				default: return;			
			}
		} else {			
			switch ((ESammelContainer) tag) {			
				case SequenceNumber:
					setSequenceNumber(value);
					break;
				case PortReference:
					setPortReference(value);
					break;
				case LadingFlag:
					setLadingFlag(value);
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
			return ESammelContainer.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}					
		
	public String getPortReference() {
		return portReference;
	}
	public void setPortReference(String argument) {
		this.portReference = argument;
	}	
	
	public String getLadingFlag() {
		return ladingFlag;
	}
	public void setLadingFlag(String argument) {
		this.ladingFlag = argument;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.sequenceNumber) && Utils.isStringEmpty(this.portReference) && 		      
		        Utils.isStringEmpty(this.ladingFlag));  
	}
}
