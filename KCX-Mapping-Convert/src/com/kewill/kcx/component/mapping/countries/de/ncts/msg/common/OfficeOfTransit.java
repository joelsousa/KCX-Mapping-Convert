package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: OfficeOfTransit
 * Created		: 01.09.2010
 * Description	: contains the OfficeOfTransit Data with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */

public class OfficeOfTransit  extends KCXMessage {
	
	private String			code;
	private String			arrivalDateAndTime;
	
	private EFormat			arrivalDateAndTimeFormat;
	
	private XMLEventReader parser	= null;

	public OfficeOfTransit(XMLEventReader parser) {
		super(parser);
		this.setParser(parser);
	}
	
	public OfficeOfTransit(XmlMsgScanner scanner) {
		super(scanner);
	}
	public OfficeOfTransit() {
		super();
	}
	
	private enum EOfficeOfTransit {
	 	// Kids-TagNames, 			UIDS-TagNames
	 	Code,						//same 
	 	ArrivalDateAndTime;			//same						
	 						
	    }
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
   			switch ((EOfficeOfTransit) tag) {
   			default:
   					return;
   			}
   		} else {
   			switch ((EOfficeOfTransit) tag) {

   				case Code:
   					setCode(value);
   					break;
   				case ArrivalDateAndTime:
   					setArrivalDateAndTime(value);   					
   					//if (tag == EOfficeOfTransit.ArrivalDateAndTime) {
   					if (value.indexOf('-') == -1) {     //EI20110524
   						setArrivalDateAndTimeFormat(EFormat.KIDS_DateTime);
   					} else {
   						setArrivalDateAndTimeFormat(EFormat.ST_DateTime);  //EI20110524
   					}
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
  			return EOfficeOfTransit.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getArrivalDateAndTime() {
		return arrivalDateAndTime;
	}

	public void setArrivalDateAndTime(String arrivalDateAndTime) {
		this.arrivalDateAndTime = arrivalDateAndTime;
	}

	public EFormat getArrivalDateAndTimeFormat() {
		return arrivalDateAndTimeFormat;
	}

	public void setArrivalDateAndTimeFormat(EFormat arrivalDateAndTimeFormat) {
		this.arrivalDateAndTimeFormat = arrivalDateAndTimeFormat;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

}
