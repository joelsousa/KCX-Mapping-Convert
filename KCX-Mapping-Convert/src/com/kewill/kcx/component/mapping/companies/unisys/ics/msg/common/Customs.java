package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CtryInfo.<br>
 * Erstellt		: 03.12.2010<br>
 * Beschreibung	: Customs info of Unisys 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class Customs extends KCXMessage {

	private HashMap<String, String> enumMap = null;
	 
	private String country;
	private String partArrNo;
	private Place clearance;
	private String cgoCtrlNo;
	private String rtg;
	private String conveyNo;
	private String uld;
	private String ensLref;
	private String mrn;		
	
	private enum ECustoms {
	 // Unisys-TagNames, 			KIDS-TagNames
		 COUNTRY,		
		 CLEARANCE,	
		 PartArrNo,
		 CgoCtrlNo,
		 RTG,
		 ConveyNo,
		 ULD,
		 EnsLref,
		 MRN;
	 }

	private void initEnumMap() {
	    enumMap = new HashMap<String, String>();		   
	    enumMap.put("PART-ARR-NO", "PartArrNo");
	    enumMap.put("CGO-CTRL-NO", "CgoCtrlNo");
	    enumMap.put("CGO-CTRL-NO", "CgoCtrlNo");		    
	    enumMap.put("CONVEY-NO", "ConveyNo");		   
	    enumMap.put("ENS-LREF", "EnsLref");		   
     }	
	
	 public Customs() {
	      	super();
	      	initEnumMap();
	 }    
   
	 public Customs(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap();
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustoms) tag) {
			case CLEARANCE:
				clearance = new Place(getScanner());  	
				clearance.parse(tag.name());					
				break;	
			default:
					return;
			}
		} else {

			switch ((ECustoms) tag) {
				case COUNTRY:
					setCountry(value);
					break;
				case PartArrNo:
					setPartArrNo(value);
					break;  											
				case CgoCtrlNo:
					setCgoCtrlNo(value);
					break;  											
				case RTG:
					setRtg(value);
					break;  											
				case ConveyNo:
					setConveyNo(value);
					break;  											
				case ULD:
					setUld(value);
					break;  											
				case EnsLref:
					setEnsLref(value);
					break;  											
				case MRN:
					setMrn(value);
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
			return ECustoms.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	

	public String getCountry() {
		return country;	
	}
	public void setCountry(String country) {
		this.country = Utils.checkNull(country);
	}

	public String getPartArrNo() {
		return partArrNo;	
	}
	public void setPartArrNo(String partArrNo) {
		this.partArrNo = Utils.checkNull(partArrNo);
	}

	public Place getClearance() {
		return clearance;	
	}
	public void setClearance(Place argument) {
		this.clearance = argument;
	}
	
	public String getCgoCtrlNo() {
		return cgoCtrlNo;
	}
	public void setCgoCtrlNo(String cgoCtrlNo) {
		this.cgoCtrlNo = Utils.checkNull(cgoCtrlNo);
	}

	public String getRtg() {
		return rtg;	
	}	
	public void setRtg(String rtg) {
		this.rtg = Utils.checkNull(rtg);
	}

	public String getConveyNo() {
		return conveyNo;	
	}

	public void setConveyNo(String conveyNo) {
		this.conveyNo = Utils.checkNull(conveyNo);
	}

	public String getUld() {
		return uld;	
	}

	public void setUld(String uld) {
		this.uld = Utils.checkNull(uld);
	}

	public String getEnsLref() {
		return ensLref;	
	}
	public void setEnsLref(String ensLref) {
		this.ensLref = Utils.checkNull(ensLref);
	}

	public String getMrn() {
		return mrn;	
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}
}
