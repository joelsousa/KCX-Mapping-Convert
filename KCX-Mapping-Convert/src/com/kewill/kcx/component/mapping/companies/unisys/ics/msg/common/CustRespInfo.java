package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustRespInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CustRespInfo extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
 
	private CustRef custRespRef;
	private DateAndTime custReqInfo;
	private CustActInfo custActInfo;
	private List <CustErrInfo> custErrInfoList;
	private CustRejInfo custRejInfo;
	private CustCtlInfo custCtlInfo;		
	private List <CodeText> custIntInfoList;
	
	 private enum ECustRespInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		 CustRespRef,				  	
		 CustReqInfo,
		 CustActInfo,
		 CustRejInfo,
		 CustCtlInfo,
		 CustErrInfo,
		 CustIntInfo;					  
	 }
	 
	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();
		    enumMap.put("CUST-RESP-REF", "CustRespRef"); 
		    enumMap.put("CUST-REQ-INFO", "CustReqInfo"); 
		    enumMap.put("CUST-ACT-INFO", "CustActInfo"); 
		    enumMap.put("CUST-ERR-INFO", "CustErrInfo"); 
		    enumMap.put("CUST-REJ-INFO", "CustRejInfo"); 
		    enumMap.put("CUST-CTL-INFO", "CustCtlInfo"); 
		    enumMap.put("CUST-INT-INFO", "CustIntInfo");
	}	

	 public CustRespInfo() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public CustRespInfo(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustRespInfo) tag) {
			case CustRespRef:
				custRespRef = new CustRef(getScanner());  	
				//custRespRef.parse(tag.name());		
				custRespRef.parse("CUST-RESP-REF");	
				break;	
			case CustReqInfo:	
				custReqInfo = new DateAndTime(getScanner());
				//custReqInfo.parse(tag.name());
				custRespRef.parse("CUST-REQ-INFO");	
				break;
			case CustActInfo:	
				custActInfo = new CustActInfo(getScanner());
				//custActInfo.parse(tag.name());	
				custRespRef.parse("CUST-ACT-INFO");	
				break;
				
			case CustRejInfo:	
				custRejInfo = new CustRejInfo(getScanner());
				//custRejInfo.parse(tag.name());	
				custRespRef.parse("CUST-REJ-INFO");	
				break;
				
			case CustCtlInfo:	
				custCtlInfo = new CustCtlInfo(getScanner());
				//custCtlInfo.parse(tag.name());	
				custRespRef.parse("CUST-CTL-INFO");	
				break;
				
			case CustErrInfo:	
				CustErrInfo custErrInfo = new CustErrInfo(getScanner());
				//custErrInfo.parse(tag.name());
				custRespRef.parse("CUST-ERR-INFO");	
				addCustErrInfoList(custErrInfo);
				break;
			case CustIntInfo:	
				CodeText custIntInfo = new CodeText(getScanner());
				//custIntInfo.parse(tag.name());
				custRespRef.parse("CUST-INT-INFO");	
				addCustIntInfoList(custIntInfo);
				break;
			default:
					return;
			}
		} else {
			switch ((ECustRespInfo) tag) {														
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		String text = enumMap.get(token);  //MS
		if (text != null) {
			token = text;
		}
		try {
			return ECustRespInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public CustRef getCustRespRef() {
		return custRespRef;	
	}
	public void setCustRespRef(CustRef argument) {
		this.custRespRef = argument;
	}

	public DateAndTime getCustReqInfo() {
		return custReqInfo;	
	}
	public void setCustReqInfo(DateAndTime argument) {
		this.custReqInfo = argument;
	}
	
	public CustActInfo getCustActInfo() {
		return custActInfo;	
	}
	public void setCustActInfo(CustActInfo argument) {
		this.custActInfo = argument;
	}

	public CustRejInfo getCustRejInfo() {
		return custRejInfo;	
	}
	public void setCustRejInfo(CustRejInfo argument) {
		this.custRejInfo = argument;
	}	
	
	public CustCtlInfo getCustCtlRef() {
		return custCtlInfo;	
	}
	public void setCustCtlInfo(CustCtlInfo argument) {
		this.custCtlInfo = argument;
	}

	public List<CustErrInfo> getCustErrInfoList() {
		return this.custErrInfoList;	
	}
	public void setCustErrInfoList(List<CustErrInfo> argument) {
		this.custErrInfoList = argument;
	}
	private void addCustErrInfoList(CustErrInfo detail) {
		if (custErrInfoList == null) {
			custErrInfoList = new Vector<CustErrInfo>();
		}
		custErrInfoList.add(detail);
	}
	
	public List<CodeText> getCustIntInfoList() {
		return this.custIntInfoList;	
	}
	public void setCustIntInfoList(List<CodeText> argument) {
		this.custIntInfoList = argument;
	}
	private void addCustIntInfoList(CodeText detail) {
		if (custIntInfoList == null) {
			custIntInfoList = new Vector<CodeText>();
		}
		custIntInfoList.add(detail);
	}
}
