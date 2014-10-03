package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

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
public class DeclnReasonSecInput extends KCXMessage {

	private String dummy;
	
	public DeclnReasonSecInput() {
      	super();
	}

	public DeclnReasonSecInput(XMLEventReader parser) {
		super(parser);
	}      

	public DeclnReasonSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EDeclnReasonSecInput {
		DECLN_dummy,
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EDeclnReasonSecInput) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EDeclnReasonSecInput) tag) {
		case DECLN_dummy:  
			//setDummy(value);
			break;
			
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
			return EDeclnReasonSecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(dummy) &&
				Utils.isStringEmpty(dummy)); 
	}
}
