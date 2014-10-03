package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Import.<br>
 * Created		: 16.12.2013<br>
 * Description	: Common class for MsgDeclnInput: AdditionalInfo
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class AdditionalInfo extends KCXMessage {

	private String aiStmt;
	private String aiStmtTxt;
	
	public AdditionalInfo() {
      	super();
	}

	public AdditionalInfo(XMLEventReader parser) {
		super(parser);
	}      

	public AdditionalInfo(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EHdrDoc {
		AI_STMT,       
		AI_STMT_TXT,   
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EHdrDoc) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EHdrDoc) tag) {
		case AI_STMT:  
			setAiStmt(value);
			break;
		case AI_STMT_TXT:
			setAiStmtTxt(value);
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
			return EHdrDoc.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getAiStmt() {
		return aiStmt;
	}
	public void setAiStmt(String value) {
		this.aiStmt = value;
	}

	public String getAiStmtTxt() {
		return aiStmtTxt;
	}
	public void setAiStmtTxt(String value) {
		this.aiStmtTxt = value;
	}
	
	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(aiStmt) &&
				Utils.isStringEmpty(aiStmtTxt)); 
	}	

}
