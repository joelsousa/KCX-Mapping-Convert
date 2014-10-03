/*
 * Function    : TransportMeans.java
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the MeansOfTransport Data
 * 			   : with all Fields used in UIDS and  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 * 
 * Author      : EI
 * Date        : 11.08.2010
 * Label       : EI20100811
 * Description : new member for KIDS: PlaceOfLoadingCode
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Modul		: TransportMeans<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the MeansOfTransport Data with all Fields used in UIDS and  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class TransportMeans extends KCXMessage {

    private String transportMode; 
    private String transportationType; 
    private String transportTime;
    private String transportationNumber; 
    private String transportationCountry; 
    private String placeOfLoading;
    private String placeOfLoadingCode;  //EI201011
    
    private String description;					
    private String shippingLine;				

    private boolean debug   = false;
    private XMLEventReader 	parser	= null;


	private enum ETransportMeans {
	// Kids-TagNames, 			UIDS-TagNames
		TransportMode,			Type,
		TransportationType,     Mode,
		TransportationNumber,   Identity,
		TransportationCountry,  Nationality,
		PlaceOfLoading,         // -
		PlaceOfLoadingCode,     // -
		TransportTime,          // -
								Description,
								ShippingLine;
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

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETransportMeans) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((ETransportMeans) tag) {
  				case TransportMode:
  				case Type:
  					setTransportMode(value);
  					break;  					
  				case TransportationType:
  				case Mode:
  					setTransportationType(value);
  					break;  				
  				case TransportationNumber:
  				case Identity:
  					setTransportationNumber(value);
  					break;  				
  				case TransportationCountry:
  				case Nationality:
  					setTransportationCountry(value);
  					break;  				
  				case PlaceOfLoading:
  					setPlaceOfLoading(value);
  					break;
  				case PlaceOfLoadingCode:
  					setPlaceOfLoadingCode(value);
  					break;
  				case TransportTime:
  					setTransportTime(value);
  					break;  					
  				case Description:
  					setDescription(value);
  					break;  					
  				case ShippingLine:
  					setShippingLine(value);
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
  			return ETransportMeans.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
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
	
	public String getPlaceOfLoadingCode() {
		return placeOfLoadingCode;
	}
	public void setPlaceOfLoadingCode(String argument) {
		this.placeOfLoadingCode = argument;
	}
	
	public String getTransportTime() {
		return transportTime;
	}

	public void setTransportTime(String argument) {
		this.transportTime = argument;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String argument) {
		this.description = argument;
	}
		
	public String getShippingLine() {
		return shippingLine;
	}
	public void setShippingLine(String argument) {
		this.shippingLine = argument;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.description) && Utils.isStringEmpty(this.placeOfLoading) && 
		        Utils.isStringEmpty(this.placeOfLoadingCode) && Utils.isStringEmpty(this.shippingLine) && 
                Utils.isStringEmpty(this.transportationCountry) && Utils.isStringEmpty(this.transportationNumber) && 
		        Utils.isStringEmpty(this.transportationType) && Utils.isStringEmpty(this.transportMode) && 
		        Utils.isStringEmpty(this.transportTime));
	}					
}
