package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: GdsDetail<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class GdsDetail extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
	 
	private FlightInfo flightInfo;	
	private Segment segment;
	private CustTotals manifested;
	private Port arrival;
	private Port offload;
	private String reportDate = "";
	private CtrySpec ctrySpec;
			
	 private enum EGdsDetail {
	 // Unisys-TagNames, 			KIDS-TagNames
		 FlightInfo,
		 SEGMENT,
		 MANIFESTED,
		 ARRIVAL,
		 OFFLOAD,
		 ReportDate,
		 CtrySpec;
	 }
	 
	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();
		    enumMap.put("FLIGHT-INFO", "FlightInfo"); 
		    enumMap.put("REPORT-DATE", "ReportDate"); 
		    enumMap.put("CTRY-SPEC", "CtrySpec"); 		  
	}	
	 
	 public GdsDetail() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public GdsDetail(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EGdsDetail) tag) {
			case FlightInfo:
				flightInfo = new FlightInfo(getScanner());  	
				//flightInfo.parse(tag.name());	
				flightInfo.parse("FLIGHT-INFO");	
				break;	
			case SEGMENT:	
				segment = new Segment(getScanner());
				segment.parse(tag.name());				
				break;	
			case MANIFESTED:
				manifested = new CustTotals(getScanner());  	
				manifested.parse(tag.name());				
				break;	
			case ARRIVAL:	
				arrival = new Port(getScanner());
				arrival.parse(tag.name());				
				break;	
			case OFFLOAD:
				offload = new Port(getScanner());  	
				offload.parse(tag.name());				
				break;	
			case CtrySpec:	
				ctrySpec = new CtrySpec(getScanner());
				//ctrySpec.parse(tag.name());		
				ctrySpec.parse("CTRY-SPEC");	
				break;	
			default:
					return;
			}
		} else {

			switch ((EGdsDetail) tag) {
				case ReportDate:
					setReportDate(value);
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
			return EGdsDetail.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getReportDate() {
		return reportDate;	
	}
	public void setReportDate(String argument) {
		this.reportDate = Utils.checkNull(argument);
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;	
	}
	public void setFlightInfo(FlightInfo argument) {
		this.flightInfo = argument;
	}
	
	public Segment getSegment() {
		return segment;	
	}
	public void setSegment(Segment argument) {
		this.segment = argument;
	}
	
	public CustTotals getManifested() {
		return manifested;	
	}
	public void setManifested(CustTotals argument) {
		this.manifested = argument;
	}
	
	public Port getArrival() {
		return arrival;	
	}
	public void setArrival(Port argument) {
		this.arrival = argument;
	}
	
	public Port getOffload() {
		return offload;	
	}
	public void setOffload(Port argument) {
		this.offload = argument;
	}
	
	public CtrySpec getCtrySpec() {
		return ctrySpec;	
	}
	public void setCrtySpec(CtrySpec argument) {
		this.ctrySpec = argument;
	}
}
