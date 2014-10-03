package com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: Common class for MsgENV: Security
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Security extends KCXMessage {

	private String enable;	
	private String circSpecInd;
	private String modPaiTrp;	
	private String convRef;
	private String comRef;	
	private String unloadCode;
	private String unloadLng;	
	private String dateArr;
	private String dangCode;
	private ArrayList<Itinerary>  itineraryList;	
	private FrParty carrier;	
	private FrParty consignor;	
	private FrParty consignee;	

	public Security() {
      	super();
	}

	public Security(XMLEventReader parser) {
		super(parser);
	}      

	public Security(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ESecurity {
		ENABLE,
		CIRC_SPEC_IND,   
		MOD_PAI_TRP,
		CONV_REF,
		COM_REF,
		UNLOAD_CODE,
		UNLOAD_LNG,
		DATE_ARR,
		DANG_CODE,
		ITINERARY,
		CARRIER,
		CONSIGNOR,
		CONSIGNEE;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ESecurity) tag) {
			case ITINERARY:
				Itinerary itinerary = new Itinerary(getScanner());  	
				itinerary.parse(tag.name());
				addItineraryList(itinerary);
				break;
			case CARRIER:
				carrier = new FrParty(getScanner());  	
				carrier.parse(tag.name());
				break;
			case CONSIGNOR:
				consignor = new FrParty(getScanner());  	
				consignor.parse(tag.name());
				break;
			case CONSIGNEE:
				consignee = new FrParty(getScanner());  	
				consignee.parse(tag.name());
				break;
			default:
				return;
		}
	  } else {
		switch ((ESecurity) tag) {
		case ENABLE:
			setEnable(value);
			break;
		case CIRC_SPEC_IND:
			setCircSpecInd(value);
			break;
		case MOD_PAI_TRP:
			setModPaiTrp(value);
			break;
		case CONV_REF:
			setConvRef(value);
			break;
		case COM_REF:
			setComRef(value);
			break;
		case UNLOAD_CODE:
			setUnloadCode(value);
			break;
		case UNLOAD_LNG:
			setUnloadLng(value);
			break;
		case DATE_ARR:
			setDateArr(value);
			break;
		case DANG_CODE:
			setDangCode(value);
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
			return ESecurity.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}


	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getCircSpecInd() {
		return circSpecInd;
	}
	public void setCircSpecInd(String circSpecInd) {
		this.circSpecInd = circSpecInd;
	}

	public String getModPaiTrp() {
		return modPaiTrp;
	}
	public void setModPaiTrp(String modPaiTrp) {
		this.modPaiTrp = modPaiTrp;
	}
	
	public String getConvRef() {
		return convRef;
	}
	public void setConvRef(String convRef) {
		this.convRef = convRef;
	}

	public String getComRef() {
		return comRef;
	}
	public void setComRef(String comRef) {
		this.comRef = comRef;
	}

	public String getUnloadCode() {
		return unloadCode;
	}
	public void setUnloadCode(String unloadCode) {
		this.unloadCode = unloadCode;
	}

	public String getUnloadLng() {
		return unloadLng;
	}
	public void setUnloadLng(String unloadLng) {
		this.unloadLng = unloadLng;
	}
	
	public String getDateArr() {
		return dateArr;
	}
	public void setDateArr(String dateArr) {
		this.dateArr = dateArr;
	}

	public FrParty getCarrier() {
		return carrier;
	}
	public void setCarrier(FrParty carrier) {
		this.carrier = carrier;
	}

	public FrParty getConsignor() {
		return consignor;
	}
	public void setConsignor(FrParty consignor) {
		this.consignor = consignor;
	}

	public FrParty getConsignee() {
		return consignee;
	}
	public void setConsignee(FrParty consignee) {
		this.consignee = consignee;
	}

	public ArrayList<Itinerary> getItineraryList() {
		return itineraryList;
	}
	public void setItineraryList(ArrayList<Itinerary> list) {
		this.itineraryList = list;
	}
	public void addItineraryList(Itinerary iti) {
		if (itineraryList == null) {
			itineraryList = new ArrayList<Itinerary>();
		}
		itineraryList.add(iti);
	}
	
	public String getDangCode() {
		return dangCode;
	}
	public void setDangCode(String dangCode) {
		this.dangCode = dangCode;
	}

	public boolean isEmpty() {
		return 	Utils.isStringEmpty(enable) &&				
				Utils.isStringEmpty(circSpecInd) &&
				Utils.isStringEmpty(modPaiTrp) &&				
				Utils.isStringEmpty(convRef) &&
				Utils.isStringEmpty(comRef) &&				
				Utils.isStringEmpty(unloadCode) &&
				Utils.isStringEmpty(unloadLng) &&				
				Utils.isStringEmpty(dateArr) &&
				(carrier == null || carrier.isEmpty()) &&
				(consignee == null || consignee.isEmpty()) &&
				(consignor == null || consignor.isEmpty()) &&
				itineraryList == null;	
	}	
	
}
