package com.kewill.kcx.component.mapping.companies.unisys.ics.msg;

import java.util.HashMap;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CodeText;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustRespInfo<br>
 * Erstellt		: 06.12.2010<br>
 * Beschreibung	: Message MSG-RESPONSE.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgResponse extends KCXMessage {

	private HashMap<String, String> enumMap = null;  
	
	private CodeText 		msgAcknowledgement;
	private CodeText		msgError;
	private CodeText 		msgWarning;	
	
	private void initEnumMap() {
	    enumMap = new HashMap<String, String>();
	    enumMap.put("MSG-ACKNOWLEDGEMENT", "MsgAcknowledgement"); 
	    enumMap.put("MSG-ERROR", "MsgError"); 
	    enumMap.put("MSG-WARNING", "MsgWarning");	    
	}	
	protected enum ECustAwb {
		MsgAcknowledgement,
		MsgError,
		MsgWarning;			          
	}
	
	public MsgResponse() {
		super();
	}

	public MsgResponse(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgResponse(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	protected enum ECC315A {
		MsgAcknowledgement,
		MsgError,
		MsgWarning;
	}
	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECC315A) tag) {
				case MsgAcknowledgement: 	
					msgAcknowledgement = new CodeText(getScanner());  	
					//msgAcknowledgement.parse(tag.name());
					msgAcknowledgement.parse("MSG-ACKNOWLEDGEMENT");
					break;			
				case MsgError:	
					msgError = new CodeText(getScanner());  	
					//msgError.parse(tag.name());
					msgError.parse("MSG-ERROR");
					break;		 	
				case MsgWarning:	
					msgWarning = new CodeText(getScanner());  	
					//msgWarning.parse(tag.name());
					msgWarning.parse("MSG-WARNING");
					break;					
			default:
					return;
			}
		} else {
			switch ((ECC315A) tag) {						
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
			return ECC315A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	

	public CodeText getMsgAcknowledgement() {
		return msgAcknowledgement;	
	}
	public void setMsgAcknowledgement(CodeText argument) {
		this.msgAcknowledgement = argument;
	}

	public CodeText getMsgError() {
		return msgError;	
	}
	public void setMsgError(CodeText argument) {
		this.msgError = argument;
	}
	
	public CodeText getMsgWarning() {
		return msgWarning;	
	}
	public void setMsgWarning(CodeText argument) {
		this.msgWarning = argument;
	}
}
