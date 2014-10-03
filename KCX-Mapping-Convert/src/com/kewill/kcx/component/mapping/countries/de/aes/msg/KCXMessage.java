/*
 * Function    : KCXMessage.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF 
 * Description : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */
 
package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.KIDS;
import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.UIDS;
import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.FEDEX;
import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.UNISYS;
import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.CYPRUS;
import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.KFF;
import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.KEX;
import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.DECLN;
//import static com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType.Decln_Sec_Input;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.Token;

/**
 * Modul		: KCXMessage<br>
 * Erstellt		: 12.11.2008<br>
 * Beschreibung	: Oberklasse einer KCX-XML-Nachricht.
 * 
 * @author ???
 * @version 1.0.00
 */
public abstract class KCXMessage {

	private XmlMsgScanner scanner;
	
	public abstract void startElement(Enum tag, String value, Attributes attr);
	public abstract void stoppElement(Enum tag);
	public abstract Enum translate(String token);
	
	public KCXMessage() {
	}
	
	public KCXMessage(XmlMsgScanner xmlMsgScanner) {
		this.scanner = xmlMsgScanner;
	}
	
	public KCXMessage(XMLEventReader evreader) {
		try {
			this.scanner = new XmlMsgScanner(evreader);
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
	
	
	public void parse(HeaderType htype) {
		if (htype == KIDS) {
			scanner.skipTo(Token.START_TAG, "Body", 1);
		} else if (htype == UIDS) {
			// SH080915 Scanner muss aufgesetzt werden
			 scanner.next();	
		} else if (htype == FEDEX) {
			scanner.skipTo(Token.START_TAG, "MessageBody", 1);			
		} else if (htype == UNISYS) {
			scanner.skipTo(Token.START_TAG, "MSG-BODY", 1);			
		} else if (htype == CYPRUS) {	//EI20110706				
		    scanner.next();				
		} else if (htype == KFF) {	//EI20111027			
			scanner.next();		
		} else if (htype == KEX) {	//AK20111108
			scanner.skipTo(Token.START_TAG, "shheader", 1);	
		} else if (htype == DECLN) {	//EI20131029
			scanner.skipTo(Token.START_TAG, "DECLN_INPUT", 1);	
			//scanner.skipTo(Token.START_TAG, "Decln_Sec_Input", 1);
		}
		
		while (true) {
			Token token = scanner.getToken(); 			
			String value = scanner.getLexem();
			Attributes attr = scanner.getAttr();
			
			if (token == null) {                   
				return;
			}
			
//			if (token == Token.STOPP_TAG && value == "Body") {
	        if (token == Token.STOPP_TAG && value.equalsIgnoreCase("Body")) {
				return;
			}
			if (token == Token.STOPP_DOC) {
				return;
			}
						
			scan(token, value, attr);
		}
	}

	public void parse(String endTag) {
		while (true) {
			Token token = scanner.getToken(); 
			String value = scanner.getLexem();
			Attributes attr = scanner.getAttr();
			
			if (token == null) {                    //EI20100521
				return;
			}
			
//			if (token == Token.STOPP_TAG && value == endTag) {
	        if (token == Token.STOPP_TAG && value.equalsIgnoreCase(endTag)) {
				return;
			}
			if (token == Token.STOPP_DOC) {
				return;
			}
			scan(token, value, attr);
		}
	}
	
	private void scan(Token token, String value, Attributes attr) {
		scanner.next();
		if (token != Token.VALUE) {
			if (token == Token.START_TAG && scanner.getToken() == Token.VALUE) {
				map(token, value, scanner.getLexem(), attr);
			} else {
//				map(token, value, null, null);
                map(token, value, null, attr);
			}
		}
	}

	protected void map(
			Token token, 
			String tagname, 
			String value, 
			Attributes attr) {
		
		Enum en = this.translate(tagname);
		if (en != null) {
			switch (token) {
			case START_TAG:
				if (value != null) {
					this.startElement(en, value.trim(), attr);
				} else {
					this.startElement(en, value, attr);	
				}
				break;
			case STOPP_TAG:
				this.stoppElement(en);
			default:
				break;
			}
		}
	}
	public XmlMsgScanner getScanner() {
		return scanner;
	}
	public void setScanner(XmlMsgScanner scanner) {
		this.scanner = scanner;
	}

}
