package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Guarantee
 * Created		: 02.09.2010
 * Description	: contains the Guarantee Data with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */

public class Guarantee extends KCXMessage {
	
	private String 				typeOfGuarantee;
	private List<Reference>		referenceList = new ArrayList<Reference>();
	
	private XMLEventReader parser	= null;
	
	public Guarantee(XMLEventReader parser) {
		super(parser);
		this.setParser(parser);
	}
	
	public Guarantee(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public Guarantee() {   //EI20130821
		super();
	}
	
	
	private enum EGuanrantee {
		//KIDS					UIDS
		TypeOfGuarantee,
		Reference;
		
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		
		if (value == null) {
   			switch ((EGuanrantee) tag) {
   			case Reference:
   				Reference ref = new Reference(getScanner());
   				ref.parse(tag.name());
   				addReferenceList(ref);
   				break;	
   				
   			default:
   				return;
   			}
   		} else {
   			switch ((EGuanrantee) tag) {
   			case TypeOfGuarantee:
   				setTypeOfGuarantee(value);
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
  			return EGuanrantee.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getTypeOfGuarantee() {
		return typeOfGuarantee;
	}

	public void setTypeOfGuarantee(String typeOfGuarantee) {
		this.typeOfGuarantee = typeOfGuarantee;
	}

	public List<Reference> getReferenceList() {
		return referenceList;
	}
	public void setReferenceList(List<Reference> reference) {
		this.referenceList = reference;
	}
	public void addReferenceList(Reference reference) {
		if (referenceList == null) {
			referenceList = new ArrayList<Reference>();
		}
		referenceList.add(reference);
	}
	
	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

}
