package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 14.11.2013<br>
 * Description	: Common class for IE07: Enveloppe
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Enveloppe extends KCXMessage {

	private String schemaId;
	private String partyId;
	
	public Enveloppe() {
      	super();
	}

	public Enveloppe(XMLEventReader parser) {
		super(parser);
	}      

	public Enveloppe(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EEnveloppe {
		schemaId, schemaID, SchemaId,		
		partyId, partyID, PartyId;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EEnveloppe) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EEnveloppe) tag) {
		
		case schemaId:
		case schemaID:
		case SchemaId:
			setSchemaId(value);
			break;
			
		case partyId:
		case partyID:
		case PartyId:  
			setPartyId(value);
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
			return EEnveloppe.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	

	
	public String getSchemaId() {
		return schemaId;
	}
	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}

	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(schemaId) &&					
				Utils.isStringEmpty(partyId)); 
	}	

}
