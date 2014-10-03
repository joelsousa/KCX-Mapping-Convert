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

public class PaymentInstructionsGroup extends KCXMessage {
		
	private List<PaymentInstructions> paymentInstructionsList;
	
	private enum EPaymentInstructionsGroup {		
		PaymentInstructions;			       			
   }	

	public PaymentInstructionsGroup() {
		super();  
	}

	public PaymentInstructionsGroup(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPaymentInstructionsGroup) tag) {  			
				case PaymentInstructions:
					PaymentInstructions temp = new PaymentInstructions(getScanner());  	
  					temp.parse(tag.name());
  					addPaymentInstructionsList(temp);
  					break; 
  				
				default:
  					break;
  			}
  		} else {

  			switch((EPaymentInstructionsGroup) tag) {   
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EPaymentInstructionsGroup.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public List<PaymentInstructions> getPaymentInstructionseList() {
		return paymentInstructionsList;
	}    
	public void setPaymentInstructionsList(List<PaymentInstructions> list) {
		this.paymentInstructionsList = list;
	}
	public void addPaymentInstructionsList(PaymentInstructions value) {
		if (paymentInstructionsList == null) {
			paymentInstructionsList = new ArrayList<PaymentInstructions>();
		}
		this.paymentInstructionsList.add(value);
	}	
}

