package com.kewill.kcx.component.mapping.companies.unisys.ics.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustFltInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustRespInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustRptInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgCustFlt.<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys CUST-FLT-MSG to KIDS ICSArrivalNotification
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class MsgCustFlt extends KCXMessage {

	private HashMap<String, String> enumMap = null;   
	
	
	private boolean debug   = false;
	 
	private String custMsgAction;
	private CustRptInfo custRptInfo;
	private CustFltInfo custFltInfo;
	private CustRespInfo custRespInfo;
	private List <Detail> detailList;  

	
	private enum EMsgCustFlt {
	 // Unisys-TagNames, 			KIDS-TagNames
		CustMsgAction,
		CustRptInfo,      CustReptInfo,
		CustFltInfo,
		DETAIL;
	}

	public MsgCustFlt() {
		super();
	   	initEnumMap();
	}    
   
	public MsgCustFlt(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		initEnumMap(); 
	}
	public MsgCustFlt(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap();
	}
	 
	private void initEnumMap() {
		enumMap = new HashMap<String, String>();
		enumMap.put("CUST-MSG-ACTION", "CustMsgAction"); 
		enumMap.put("CUST-RPT-INFO", "CustRptInfo");
		enumMap.put("CUST-REPT-INFO", "CustReptInfo");
		enumMap.put("CUST-FLT-INFO", "CustFltInfo");		
	}	


	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgCustFlt) tag) {
			case CustRptInfo:
			case CustReptInfo:
				custRptInfo = new CustRptInfo(getScanner());  	
				//custRptInfo.parse(tag.name());	
				custRptInfo.parse("CUST-RPT-INFO");
				break;					
			case CustFltInfo:
				custFltInfo = new CustFltInfo(getScanner());
				//custFltInfo.parse(tag.name());
				custFltInfo.parse("CUST-FLT-INFO");
				break;
			case DETAIL:	
				Detail detail = new Detail(getScanner());
				detail.parse(tag.name());
				addDetailList(detail);
				break;						
			default:
					return;
			}
		} else {

			switch ((EMsgCustFlt) tag) {
				case CustMsgAction:
					setCustMsgAction(value);
					break;  											
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		String text = enumMap.get(token);
		if (text != null) {
			token = text;
		}
		try {
			return EMsgCustFlt.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCustMsgAction() {
		return custMsgAction;
	
	}

	public void setCustMsgAction(String custMsgAction) {
		this.custMsgAction = custMsgAction;
	}

	public CustRptInfo getCustRptInfo() {
		return custRptInfo;
	
	}

	public void setCustRptInfo(CustRptInfo custRptInfo) {
		this.custRptInfo = custRptInfo;
	}

	public CustFltInfo getCustFltInfo() {
		return custFltInfo;
	
	}

	public void setCustFltInfo(CustFltInfo custFltInfo) {
		this.custFltInfo = custFltInfo;
	}

	public CustRespInfo getCustRespInfo() {
		return custRespInfo;
	
	}

	public void setCustRespInfo(CustRespInfo custRespInfo) {
		this.custRespInfo = custRespInfo;
	}

	private void addDetailList(Detail detail) {
		if (detailList == null) {
			detailList = new Vector<Detail>();
		}
		detailList.add(detail);
	}

	public List<Detail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<Detail> detailList) {
		this.detailList = detailList;
	}

}
