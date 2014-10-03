package com.kewill.kcx.component.mapping.countries.mt.ics.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: MsgOPRNAK<br>
 * Created		: 30.08.2013<br>
 * Description	: Operational Error Message Malta.
 * 
 * @author	krzoska
 * @version	1.0.00
 */

public class MsgOPRNAK extends KCXMessage {
		
	private OperationFailure operationFailure;
	
	public MsgOPRNAK() {
		super();
	}

	public MsgOPRNAK(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgOPRNAK(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	private enum EOPRNAK {
		OperationFailure;
  
	}
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EOPRNAK) tag) {						
			case OperationFailure:
				operationFailure = new OperationFailure(getScanner());
				operationFailure.parse(tag.name());
				break;

			default:
					return;
			}
		} else {
			switch ((EOPRNAK) tag) {			
			default:
					return;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
	
	}

	@Override
	public Enum translate(String token) {
		try {
			return EOPRNAK.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public OperationFailure getOperationFailure() {
		return operationFailure;
	}

	public void setOperationFailure(OperationFailure operationFailure) {
		this.operationFailure = operationFailure;
	}
}
