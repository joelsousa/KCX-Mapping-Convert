package com.kewill.kcx.component.mapping.companies.unisys.ics.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustRespInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustRptInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgCustAwb<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Message CUST-AWB-MSG
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgCustAwb extends KCXMessage {

	private HashMap<String, String> enumMap = null;   
	private HashMap<String, String> enumBack = null;   
	
	private String 		custMsgAction;	
	private CustRptInfo custRptInfo;	
	private CustRespInfo custRespInfo;	
	private List <Detail> detailList;	
	
	public MsgCustAwb() {
		super();
		initEnumMap(); 
		initEnumBack(); 
	}

	public MsgCustAwb(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		initEnumMap(); 
		initEnumBack(); 
	}
	public MsgCustAwb(XmlMsgScanner scanner)  {
		super(scanner);
		initEnumMap(); 
		initEnumBack(); 
	}
		
	private void initEnumMap() {
	    enumMap = new HashMap<String, String>();
	    enumMap.put("CUST-AWB-MSG", "CustAwbMsg"); 
	    enumMap.put("CUST-MSG-ACTION", "CustMsgAction"); 
	    enumMap.put("CUST-RPT-INFO", "CustRptInfo"); 
	    enumMap.put("CUST-REPT-INFO", "CustReptInfo"); 
	    enumMap.put("CUST-RESP-INFO", "CustRespInfo");	    
	}
	private void initEnumBack() {
		enumBack = new HashMap<String, String>();
		enumBack.put("CustAwbMsg", "CUST-AWB-MSG"); 
		enumBack.put("CustMsgAction", "CUST-MSG-ACTION"); 
		enumBack.put("CustRptInfo", "CUST-RPT-INFO"); 
		enumBack.put("CustRespInfo", "CUST-RESP-INFO");	    
	}
	protected enum ECustAwb {
		CustAwbMsg,
		CustMsgAction,
		CustRptInfo, CustReptInfo,
		CustRespInfo,		
		DETAIL;		            //GoodsItem 
	}
		
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		String text;		
		if (value == null) {
			switch ((ECustAwb) tag) {
				case CustAwbMsg:
					break;
				case CustRptInfo: 	
				case CustReptInfo:
					custRptInfo = new CustRptInfo(getScanner());  	
					//custRptInfo.parse(tag.name());					
					text = enumBack.get(tag.name());
					custRptInfo.parse(text);					
					break;			
				case CustRespInfo:
					custRespInfo = new CustRespInfo(getScanner());  					
					//custRespInfo.parse(tag.name());
					text = enumBack.get(tag.name());
					custRespInfo.parse(text);
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
			switch ((ECustAwb) tag) {			
				case CustMsgAction:	
					setCustMsgAction(value);
					break;			
				default:
					return;
			}
		}	
	}

	@Override
	public void stoppElement(Enum tag) {
		String dummy;
		dummy = "";
	}

	@Override
	public Enum translate(String token) {  
		String text = enumMap.get(token);  //MS
		if (text != null) {
			token = text;
		}
		try {
			return ECustAwb.valueOf(token);  //HIER UMWANDELN	
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	

	public String getCustMsgAction() {
		return custMsgAction;	
	}
	public void setCustMsgAction(String argument) {
		this.custMsgAction = Utils.checkNull(argument);
	}

	public CustRptInfo getCustRptInfo() {
		return custRptInfo;	
	}
	public void setCustRptInfo(CustRptInfo argument) {
		this.custRptInfo = argument;
	}

	public CustRespInfo getCustRespInfo() {
		return custRespInfo;	
	}
	public void setCustRptInfo(CustRespInfo argument) {
		this.custRespInfo = argument;
	}
	
	public List<Detail> getDetailList() {
		return this.detailList;	
	}
	public void setDetailList(List<Detail> argument) {
		this.detailList = argument;
	}
	private void addDetailList(Detail detail) {
		if (detailList == null) {
			detailList = new Vector<Detail>();
		}
		detailList.add(detail);
	}
	
}
