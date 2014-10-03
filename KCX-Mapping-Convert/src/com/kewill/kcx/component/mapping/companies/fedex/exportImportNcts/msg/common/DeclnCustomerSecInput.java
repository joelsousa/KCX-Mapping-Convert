package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Import.<br>
 * Created		: 29.10.2013<br>
 * Description	: Common class for MsgDeclnInput
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class DeclnCustomerSecInput extends KCXMessage {

	private ArrayList <DeclnCust> declnCustList;	
	
	public DeclnCustomerSecInput() {
      	super();
	}

	public DeclnCustomerSecInput(XMLEventReader parser) {
		super(parser);
	}      

	public DeclnCustomerSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EDeclnCustomerSecInput {
		DECLN_CUST,	
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EDeclnCustomerSecInput) tag) {
			case DECLN_CUST:  
				DeclnCust customer = new DeclnCust(getScanner());  	
				customer.parse(tag.name());
				addDeclnCustList(customer);
				break;
			
		default:
				return;
		}
	  } else {
		switch ((EDeclnCustomerSecInput) tag) {
		
		default:
			return;
		} 
	  }
	}
	
	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	public Enum translate(String token) {
		try {
			return EDeclnCustomerSecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public ArrayList<DeclnCust> getDeclnCustList() {
		return declnCustList;
	}
	public void setDeclnCustList(ArrayList<DeclnCust> list) {
		this.declnCustList = list;
	}
	public void addDeclnCustList(DeclnCust item) {
		if (declnCustList == null) {
			declnCustList = new ArrayList<DeclnCust>();
		}
		this.declnCustList.add(item);
	}
	
	public boolean isEmpty() {
		return 	(declnCustList == null || declnCustList.isEmpty());
	}
}
