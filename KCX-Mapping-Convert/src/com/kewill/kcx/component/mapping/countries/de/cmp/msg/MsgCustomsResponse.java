package com.kewill.kcx.component.mapping.countries.de.cmp.msg;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseBody;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseHeader;


/**
 * Module		: Manifest<br>.
 * Created		: 05.02.2013<br>
 * Description	: MsgCustomsResponse Kids - all Responses to CMP/LCAG
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgCustomsResponse extends KCXMessage { 
	
	
	private ResponseHeader  messageHeader;
	private ResponseBody messageBody;    
		
	public MsgCustomsResponse() {
		super();
	}
	public MsgCustomsResponse(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum ECustomsResponse {
		//KIDS:							
		MessageHeader,        				
		MessageBody;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustomsResponse) tag) {
			case MessageHeader:
				messageHeader = new ResponseHeader(getScanner());
				messageHeader.parse(tag.name());
				break;						
			case MessageBody:
				messageBody = new ResponseBody(getScanner());
				messageBody.parse(tag.name());								
				break;							
			default:
				return;
			}
		} else {
			switch ((ECustomsResponse) tag) {
			
			default:
				return;	
			}
		}
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
			return ECustomsResponse.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public ResponseHeader getMessageHeader() {
		return messageHeader;
	}
	public void setMessageHeader(ResponseHeader messageHeader) {
		this.messageHeader = messageHeader;
	}
	
	public ResponseBody getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(ResponseBody messageBody) {
		this.messageBody = messageBody;
	}
	
	
}
