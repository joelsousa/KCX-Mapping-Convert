package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Import.<br>
 * Created		: 29.10.2013<br>
 * Description	: Common class for MsgDeclnInput: Document
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class HdrDoc extends KCXMessage {

	private String docCode;
	private String docRef;
	private String docDate;
	private String docQty;
	private String docReason;
	
	public HdrDoc() {
      	super();
	}

	public HdrDoc(XMLEventReader parser) {
		super(parser);
	}      

	public HdrDoc(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EHdrDoc {
		HDR_DOC_CODE,  		//GoodsItem/Document/Type
		HDR_DOC_REF,   		//GoodsItem/Document/Number oder Reference
		HDR_DOC_REASON_CD,  //GoodsItem/Document/Reason
		DOC_DATE,			//GoodsItem/Document/IssueDate
		DOC_QTY,			//GoodsItem/Document/Value
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
		case HDR_DOC_CODE:  
			setHdrDocCode(value);
			break;
		case HDR_DOC_REF:
			setHdrDocRef(value);
			break;
		case HDR_DOC_REASON_CD:
			setHdrDocReasonCd(value);
			break;
		case DOC_DATE:
			setDocDate(value);
			break;
		case DOC_QTY:
			setDocQty(value);
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

	public String getHdrDocCode() {
		return docCode;
	}
	public void setHdrDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getHdrDocRef() {
		return docRef;
	}
	public void setHdrDocRef(String docRef) {
		this.docRef = docRef;
	}

	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	
	public String getDocQty() {
		return docQty;
	}
	public void setDocQty(String docCode) {
		this.docQty = docCode;
	}
		
	public String getHdrDocReasonCd() {
		return docReason;
	}
	public void setHdrDocReasonCd(String code) {
		this.docReason = code;
	}

	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(docCode) &&
				Utils.isStringEmpty(docRef) &&
				Utils.isStringEmpty(docDate) &&
				Utils.isStringEmpty(docQty)); 
	}	

}
