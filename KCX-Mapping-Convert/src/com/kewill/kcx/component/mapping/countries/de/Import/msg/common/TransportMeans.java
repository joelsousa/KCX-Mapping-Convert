package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the TransportMeans Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class TransportMeans extends KCXMessage {

    private String transportMode; 
    private String transportationType; 
    private String transportationNumber; 
    private String transportationCountry;     
    private String description;					   		

    private XMLEventReader 	parser	= null;


	private enum EImportTransportMeans {
	// Kids-TagNames, 			UIDS-TagNames
		TransportMode,			
		TransportationType,     
		TransportationNumber,   
		TransportationCountry,  		
		Description;	
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
  			switch ((EImportTransportMeans) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportTransportMeans) tag) {
  				case TransportMode:  			
  					setTransportMode(value);
  					break;  					
  				case TransportationType:  				
  					setTransportationType(value);
  					break;  				
  				case TransportationNumber:  				
  					setTransportationNumber(value);
  					break;  				
  				case TransportationCountry:  		
  					setTransportationCountry(value);
  					break;  				  				 				
  				case Description:
  					setDescription(value);
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
  			return EImportTransportMeans.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String argument) {
		this.description = argument;
	}
		
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.transportMode) && Utils.isStringEmpty(this.transportationType) && 
                Utils.isStringEmpty(this.transportationCountry) && Utils.isStringEmpty(this.transportationNumber) &&
		        Utils.isStringEmpty(this.description));
	}					
}
