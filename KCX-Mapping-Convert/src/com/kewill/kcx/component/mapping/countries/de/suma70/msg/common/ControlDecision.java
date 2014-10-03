package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Contains the Custodian Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class ControlDecision extends KCXMessage {
	
	private String 	controlDecisionCode;       		
	private String deliverableFlag;    
	private String additionalInformation;	

	private enum EControl {
		//KIDS							//UIDS
		ControlDecisionCode,
		DeliverableFlag,
		AdditionalInformation,
	}
	
	public ControlDecision() {
		super();  
	}

    public ControlDecision(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EControl) tag) {
  			
  			default:
  					return;
  			}
  		} else {

  			switch ((EControl) tag) {
  			case ControlDecisionCode:
  				setControlDecisionCode(value);
  				break;
  				
  			case DeliverableFlag:
  				setDeliverableFlag(value);
  				break;
  				
  			case AdditionalInformation:
  				setAdditionalInformation(value);
  				break;
  				
  	  		default:
  					return;
  			}
  		}
		
	}

	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub	
	}

	public Enum translate(String token) {
		 try {
				return EControl.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public String getControlDecisionCode() {
		return controlDecisionCode;
	}
	public void setControlDecisionCode(String controlDecisionCode) {
		this.controlDecisionCode = controlDecisionCode;
	}

	public String getDeliverableFlag() {
		return deliverableFlag;
	}
	public void setDeliverableFlag(String deliverableFlag) {
		this.deliverableFlag = deliverableFlag;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(controlDecisionCode) && Utils.isStringEmpty(deliverableFlag) &&
				Utils.isStringEmpty(additionalInformation));
	}
}
