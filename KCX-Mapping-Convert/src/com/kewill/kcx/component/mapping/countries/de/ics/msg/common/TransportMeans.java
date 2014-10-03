/*
 * Function    : TransportMeans.java
 * Titel       :
 * Date        : 09.06.2010
 * Author      : Pete T
 * Description : Contains the MeansOfTransport Data
 * 			   : with all Fields used in UIDS and  KIDS
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: TransportMeans<br>
 * Erstellt		: 09.06.2010<br>
 * Beschreibung	: Contains the MeansOfTransport Data with all Fields used in UIDS and  KIDS.
 * 
 * @author Pete T
 * @version 1.0.00
 */
public class TransportMeans extends KCXMessage {

    private String transportMode; 
    private String transportationType; 
    private String transportationNumber; 
    private String transportationCountry; 
    private String placeOfLoading;
    private String transportTime;
    private String placeOfLoadingCode;	
    
    private EFormat transportTimeFormat;  //EI20110105

    private String msgType = "";         //EI20121024
    private XMLEventReader 	parser	= null;


	private enum ETransportMeans {
	// Kids-TagNames, 			UIDS-TagNames
		TransportMode,			Mode,
		TransportationType,     Type,
		TransportationNumber,   Identity,
		TransportationCountry,  Nationality,
		PlaceOfLoading,
		TransportTime,
		PlaceOfLoadingCode;
     }

    public TransportMeans() {
	      	super();
    }

    public TransportMeans(XMLEventReader parser) {
    		super(parser);
    		this.parser = parser;
    }      
      
    public TransportMeans(XmlMsgScanner scanner) {
  		super(scanner);
   	}
    
    public TransportMeans(XmlMsgScanner scanner, String type) {  //EI20121024
  		super(scanner);
  		msgType = type;
   	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETransportMeans) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((ETransportMeans) tag) {

  				case TransportMode:
  				case Mode:
  					setTransportMode(value);
  					if (tag == ETransportMeans.TransportMode) {  						
  						msgType = "KIDS";
					 } else {						
						msgType = "UIDS";
					 }
  					break;
  					
  				case TransportationType:
  				case Type:  					
  					setTransportationType(value);
  					if (tag == ETransportMeans.TransportationType) {  						
  						msgType = "KIDS";
					 } else {						
						msgType = "UIDS";
					 }
  					break;
  					
  				case TransportationNumber:
  				case Identity:
  					setTransportationNumber(value);
  					 if (tag == ETransportMeans.TransportationNumber) {
  						//setTransportTimeFormat(EFormat.KIDS_DateTime);
  						msgType = "KIDS";
					 } else {
						//setTransportTimeFormat(EFormat.ST_DateTimeT);	
						msgType = "UIDS";
					 }
  					break;
  					
  				case TransportationCountry:
  				case Nationality:
  					setTransportationCountry(value);
  					break;
  					
  				case PlaceOfLoading:
  					setPlaceOfLoading(value);
  					break;
  					
  				case TransportTime:
  					setTransportTime(value);
  					if (msgType.equals("KIDS")) {
  						setTransportTimeFormat(Utils.getKidsDateAndTimeFormat(value));
  					} else {
  						setTransportTimeFormat(Utils.getUidsDateAndTimeFormat(value));
  					}
  					break;
  					
  				case PlaceOfLoadingCode:
  					setPlaceOfLoadingCode(value);
  					break;
  				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ETransportMeans.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String argument) {
		this.msgType = argument;
	}
  	
	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String argument) {
		this.transportMode = argument;
	}

	public String getTransportationType() {
		return transportationType;
	}

	public void setTransportationType(String argument) {
		this.transportationType = argument;
	}

	public String getTransportationNumber() {
		return transportationNumber;
	}

	public void setTransportationNumber(String argument) {
		this.transportationNumber = argument;
	}

	public String getTransportationCountry() {
		return transportationCountry;
	}

	public void setTransportationCountry(String argument) {
		this.transportationCountry = argument;
	}

	public String getPlaceOfLoading() {
		return placeOfLoading;
	}

	public void setPlaceOfLoading(String argument) {
		this.placeOfLoading = argument;
	}

	public String getTransportTime() {
		return transportTime;
	}

	public void setTransportTime(String argument) {
		this.transportTime = argument;
	}
	
	public String getPlaceOfLoadingCode() {
		return placeOfLoadingCode;
	}
	public void setPlaceOfLoadingCode(String argument) {
		this.placeOfLoadingCode = argument;
	}
	
	public EFormat getTransportTimeFormat() {
		return transportTimeFormat;
	}

	public void setTransportTimeFormat(EFormat eFormat) {
		this.transportTimeFormat = eFormat;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.placeOfLoading) && 
		        Utils.isStringEmpty(this.placeOfLoadingCode) && 
		        Utils.isStringEmpty(this.transportationCountry) && 
		        Utils.isStringEmpty(this.transportationNumber) && 
		        Utils.isStringEmpty(this.transportationType) && 
		        Utils.isStringEmpty(this.transportMode) && 
		        Utils.isStringEmpty(this.transportTime));
	}		
}
