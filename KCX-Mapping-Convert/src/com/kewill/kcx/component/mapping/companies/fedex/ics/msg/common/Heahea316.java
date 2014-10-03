/**
 * 
 */
package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class Heahea316 extends KCXMessage {
	
	private boolean			debug	= false;
	private XMLEventReader	parser	= null;
	
	private String	referenceNumber;
	private String	declarationRejectionReason;
	private String	declarationRejectionReasonLNG;
	private String	declarationRejectionDateAndTime;
	
	public Heahea316() {
		super();
	}
	
	public Heahea316(XMLEventReader parser) {
		super(parser);
		this.parser	= parser;
	}
	
	public Heahea316(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String refNo) {
		this.referenceNumber	= Utils.checkNull(refNo);
	}
	
	public String getDeclarationRejectionReason() {
		return this.declarationRejectionReason;
	}
	public void setDeclarationRejectionReason(String decRejRea) {
		this.declarationRejectionReason	= Utils.checkNull(decRejRea);
	}
	
	public String getDeclarationRejectionReasonLNG() {
		return this.declarationRejectionReasonLNG;
	}
	public void setDeclarationRejectionReasonLNG(String decRejReaLNG) {
		this.declarationRejectionReasonLNG	= Utils.checkNull(decRejReaLNG);
	}
	
	public String getDeclarationRejectionDateAndTime() {
		return this.declarationRejectionDateAndTime;
	}
	public void setDeclarationRejectionDateAndTime(String decRejReaDT) {
		this.declarationRejectionDateAndTime	= Utils.checkNull(decRejReaDT);
	}
	
	private enum EHeaheaTags {
		// Fedex,
		RefNumHEA4,
		DecRejReaHea252,
		DecRejReahea252LNG,
		DecRejDatTimHEA116
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EHeaheaTags) tag) {
				default:
						return;
			}
		} else {
			switch((EHeaheaTags) tag) {
				case RefNumHEA4:
						setReferenceNumber(value);
					break;
				
				case DecRejReaHea252:
						setDeclarationRejectionReason(value);
					break;
				
				case DecRejReahea252LNG:
						setDeclarationRejectionReasonLNG(value);
					break;
				
				case DecRejDatTimHEA116:
						setDeclarationRejectionDateAndTime(value);
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

	@Override
	public Enum translate(String token) {
		try {
			return EHeaheaTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
