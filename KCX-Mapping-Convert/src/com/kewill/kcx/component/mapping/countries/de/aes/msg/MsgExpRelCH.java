/*
 * Function    : MsgExpRelCH.java
 * Titel       :
 * Date        : 29.04.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains Message Structure with Fields used in KIDS,
 * 			   : for Switzerland
 * -----------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 18.05.2009
 * Label       : 
 * Description : deleted: cancellationType, reason (not used)
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

/**
 * Modul		: MsgExpRelCH<br>
 * Erstellt		: 29.04.2009<br>
 * Beschreibung	: Contains Message Structure with Fields used in KIDS for Switzerland.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExpRelCH extends KCXMessage {
	
	private String msgName;
	
	private String acceptanceTime;				
	private String codeOfRelease;		
    private String declarationKind;
	private String declarationNumberForwarder;
	private String declarationNumberCustoms;				
	private String revisionCode;	
	private String referenceNumber; 		
    
//	private XMLEventReader 	parser;
    // private boolean debug;
    
	public MsgExpRelCH() {
			super();			
	}
	
	public MsgExpRelCH(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}
	 
	public MsgExpRelCH(XMLEventReader parser, String msgName) throws XMLStreamException {
		super(parser);		
		this.msgName = msgName;	
	}
	
	private enum EDeclaration {
										//V70:  EI20121026
		  AcceptanceTime,              AcceptanceDateTime,
          CodeOfRelease,
          DeclarationNumberCustoms,    CustomsDeclarationNumber,
		  DeclarationNumberForwarder,  TraderDeclarationNumber,
		  DeclarationKind,
		  ReferenceNumber,
		  RevisionCode;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EDeclaration) tag) {
					default:
						return;
				}
			} else {
				switch ((EDeclaration) tag) {
					
					case AcceptanceTime:	
					case AcceptanceDateTime:	
						setAcceptanceTime(value);
						break;	
	
					case CodeOfRelease:
						setCodeOfRelease(value);
						break;
						
					case DeclarationNumberCustoms:
					case CustomsDeclarationNumber:
						setDeclarationNumberCustoms(value);
						break;	
						
					case DeclarationNumberForwarder:
					case TraderDeclarationNumber:
						setDeclarationNumberForwarder(value);
						break;	
		
					case DeclarationKind:
						setDeclarationKind(value);
						break;										
						
					case ReferenceNumber:
						setReferenceNumber(value);
						break;																			
						
					case RevisionCode:
						setRevisionCode(value);
						break;												
				}
			}
	}	 	 

	public void stoppElement(Enum tag) {
	}
		
	public Enum translate(String token) {
		try {
				return EDeclaration.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}

	public String getDeclarationKind() {
		return declarationKind;
	}
	public void setDeclarationKind(String argument) {
		this.declarationKind = argument;
	}
 
	public String getDeclarationNumberForwarder() {
		return declarationNumberForwarder;
	}
	public void setDeclarationNumberForwarder(String argument) {
		this.declarationNumberForwarder = argument;
	}
    
	public String getDeclarationNumberCustoms() {
		return declarationNumberCustoms;
	}
	public void setDeclarationNumberCustoms(String argument) {
		this.declarationNumberCustoms = argument;
	}

	public String getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(String argument) {
		this.acceptanceTime = argument;
	}
	public String getRevisionCode() {
		return revisionCode;
	}
	public void setRevisionCode(String argument) {
		this.revisionCode = argument;
	}

	public String getCodeOfRelease() {
		return codeOfRelease;
	}
	public void setCodeOfRelease(String argument) {
		this.codeOfRelease = argument;
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgType(String msgName) {
		this.msgName = msgName;
	}

}

