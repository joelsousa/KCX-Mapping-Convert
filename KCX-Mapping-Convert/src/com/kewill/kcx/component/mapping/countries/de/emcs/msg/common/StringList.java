package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module     	: EMCS<br>
 * Title		: StringList.
 * Date			: 04.07.2011<br>
 * Description  : ReferenceReportList for MsgInteruptionOfMovement
 * 				: ReasonCodeList for MsgAlertOrRejection
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class StringList extends KCXMessage {

	private List <String> 			aadReferenceCodeList;
	private List <String>			controlReportReferenceList;
	private List <String>			eventReportNumberList;
	private List <String>			reasonCodeList;
    
	private enum EStringList {	
		//KIDS:                  UIDS :
		AadReferenceCode,            //same	
		ControlReportReference,		//same	        
		EventReportNumber,   		//same 	  
		ReasonCode;    				//same 	 
		
   }	
	
	public StringList() {
  		super();
  	}	
	 
	public StringList(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EStringList) tag) {  							
				default:
  					return;
  			}
  		} else {
  			switch ((EStringList) tag) {   
  				case ControlReportReference:  					  					
  					addControlReportReferenceList(value);
					break;   			
  				case EventReportNumber:  					
  					addEventReportNumberList(value);
  					break;   
  				case ReasonCode:  					  					
  					addReasonCodeList(value);
  				case AadReferenceCode:
					addAadReferenceCodeList(value);
					break;	
  				default:
  					break;  
  			}
  		}
		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
  			return EStringList.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public List<String> getEventReportNumberList() {
		return this.eventReportNumberList;
	}
	public void setEventReportNumberList(List<String> list) {
		this.eventReportNumberList = list;
	}
	public void addEventReportNumberList(String argument) {
		if (eventReportNumberList == null) {
			eventReportNumberList = new Vector <String>(); 
		}
		eventReportNumberList.add(argument);	
	}

	public List<String> getControlReportReferenceList() {
		return controlReportReferenceList;
	}
	public void setControlReportReferenceList(List<String> list) {
		this.controlReportReferenceList = list;
	}
	public void addControlReportReferenceList(String argument) {
		if (controlReportReferenceList == null) {
			controlReportReferenceList = new Vector <String>(); 
		}
		controlReportReferenceList.add(argument);	
	}
	
	public List<String> getReasonCodeList() {
		return this.reasonCodeList;
	}
	public void setReasonCodeList(List<String> list) {
		this.reasonCodeList = list;
	}
	public void addReasonCodeList(String argument) {
		if (reasonCodeList == null) {
			reasonCodeList = new Vector <String>(); 
		}
		reasonCodeList.add(argument);	
	}
	
	public List<String> getAadReferenceCodeList() {
		return aadReferenceCodeList;	
	}
	public void setAadReferenceCodeList(List<String> list) {
		this.aadReferenceCodeList = list;
	}
	public void addAadReferenceCodeList(String argument) {
		if (aadReferenceCodeList == null) {
			aadReferenceCodeList = new Vector <String>(); 
		}
		aadReferenceCodeList.add(argument);	
	}
	
	
}
   
