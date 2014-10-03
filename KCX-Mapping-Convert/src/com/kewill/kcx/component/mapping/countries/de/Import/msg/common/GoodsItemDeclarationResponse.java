package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the GoodsItemDeclaration Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class GoodsItemDeclarationResponse extends KCXMessage {

	private String 				msgName;
	private String 				itemNumber; 
	private String				errorCode;
	private String 				kindOfError;
	private String 				reportingPrefix; 
	private String				errorText;		
	private String				registrationNumberWriteOffAmounts;	
	
	public GoodsItemDeclarationResponse() {
		super();				
	}

	public GoodsItemDeclarationResponse(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
 
	private enum EGoodsItemDeclaration {
		//KIDS:							UIDS:
		ItemNumber,
		ErrorCode,
		KindOfError,
		ReportingPrefix,
		ErrorText,
		RegistrationNumberWriteOffAmounts;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EGoodsItemDeclaration) tag) {			
			default:
				return;
			}
	    } else {
	    	switch ((EGoodsItemDeclaration) tag) {
			case ItemNumber:
				 setItemNumber(value);
				 break;											
			case ErrorCode:
				 setErrorCode(value);
				 break;			
			case ErrorText:
				 setErrorText(value);
				 break;
			case KindOfError:
				 setKindOfError(value);
				 break;
			case ReportingPrefix:			
				 setReportingPrefix(value);
				 break;	
			case RegistrationNumberWriteOffAmounts:
				 setRegistrationNumberWriteOffAmounts(value);
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
  			return EGoodsItemDeclaration.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String value) {
		this.errorCode = value;
	}

	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String value) {
		this.errorText = value;
	}
	
	public String getKindOfError() {
		return kindOfError;
	}
	public void setKindOfError(String value) {
		this.kindOfError = value;
	}
	
	public String getReportingPrefix() {
		return reportingPrefix;
	}
	public void setReportingPrefix(String value) {
		this.reportingPrefix = value;
	}
	
	public String getRegistrationNumberWriteOffAmounts() {
		return registrationNumberWriteOffAmounts;
	}
	public void setRegistrationNumberWriteOffAmounts(String value) {
		this.registrationNumberWriteOffAmounts = value;
	}
	
	
}
