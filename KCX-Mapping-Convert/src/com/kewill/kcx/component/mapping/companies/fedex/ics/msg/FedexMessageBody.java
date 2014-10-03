package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class FedexMessageBody extends KCXMessage {

	//private MsgCC313A msg313 = null;
	private MsgCC315A msg315 = null;
	//private MsgCC316A msg316 = null;
	//usw...
	
	protected enum EFMessagesBody {
		CC313A,
		CC315A,
		CC316A;   //usw...
	}
	
	public FedexMessageBody() {
		super();
	}

	public FedexMessageBody(XmlMsgScanner scanner) {
  		super(scanner);
  	}	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EFMessagesBody) tag) {
			case CC313A:								
				break;	
			case CC315A:
				msg315 = new MsgCC315A(getScanner());  	
				msg315.parse(tag.name());				
				break;
			case CC316A:
				break;
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
			return EFMessagesBody.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public void setMsgCC315A(MsgCC315A argument) {
		this.msg315 = argument;
	}
	public MsgCC315A getMsgCC315A() {
		return msg315;
	}
			
}
