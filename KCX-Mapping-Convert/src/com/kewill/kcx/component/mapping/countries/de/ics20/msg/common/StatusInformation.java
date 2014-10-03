package com.kewill.kcx.component.mapping.countries.de.ics20.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: ICS20<br>
 * Created		: 23.10.2012<br>
 * Description	: Contains the StatusInformation Data with all Fields used in UIDS and  KIDS.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
	
public class StatusInformation extends KCXMessage {

	    private String status; 
	    private String statusDateAndTime; 	   
	    private EFormat statusDateAndTimeFormat;  //EI20110105

	    private XMLEventReader 	parser	= null;

		private enum EStatusInformation {
		// Kids-TagNames, 			UIDS-TagNames
			Status,			
			StatusDateAndTime,  			
	     }

	    public StatusInformation() {
		      	super();
	    }

	    public StatusInformation(XMLEventReader parser) {
	    		super(parser);
	    		this.parser = parser;
	    }      
	      
	    public StatusInformation(XmlMsgScanner scanner) {
	  		super(scanner);
	   	}

	  	public void startElement(Enum tag, String value, Attributes attr) {
	  		if (value == null) {
	  			switch ((EStatusInformation) tag) {
	  			default:
	  					return;
	  			}
	  		} else {

	  			switch ((EStatusInformation) tag) {

	  				case Status:	  				
	  					setStatus(value);
	  					break;	  					
	  				case StatusDateAndTime:	  				
	  					setStatusDateAndTime(value);
	  					break;
	  				
	  				default: break;
	  			}
	  		}
	  	}

	  	public void stoppElement(Enum tag) {
	  	}

	  	public Enum translate(String token) {
	  		try {
	  			return EStatusInformation.valueOf(token);
	  		} catch (IllegalArgumentException e) {
	  			return null;
	  		}
	  	}

	  	
		public String getStatus() {
			return status;
		}
		public void setStatus(String argument) {
			this.status = argument;
		}

		public String getStatusDateAndTime() {
			return statusDateAndTime;
		}
		public void setStatusDateAndTime(String argument) {
			this.statusDateAndTime = argument;
		}

		
		public EFormat getStatusDateAndTimeFormat() {
			return statusDateAndTimeFormat;
		}
		public void setStatusDateAndTimeFormat(EFormat eFormat) {
			this.statusDateAndTimeFormat = eFormat;
		}
		
		public boolean isEmpty() {
			return (Utils.isStringEmpty(this.status) && 			       
			        Utils.isStringEmpty(this.statusDateAndTime));
		}		
}
