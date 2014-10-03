package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 10.07.2013<br>
* Description	: DimensionDetails.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class DimensionDetails extends KCXMessage {

	private String	measurementUnitCode;
    private String	lengthDimension;
    private String	widthDimension;
    private String	heightDimension;
    
    
    private enum EDimensionDetails {
    	MeasurementUnitCode,
    	LengthDimension, LengthMeasure,
    	WidthDimension,  WidthMeasure,
    	HeightDimension, HeightMeasure; 
    }

    public DimensionDetails() {
	      	super();	       
    }
    
    public DimensionDetails(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EDimensionDetails) tag) {

    			default:
    					return;
    			}
    		} else {

    			switch ((EDimensionDetails) tag) {
    				case MeasurementUnitCode:
    					setMeasurementUnitCode(value);
    					break;
    				
    				case LengthDimension:
    				case LengthMeasure:
    					setLengthDimension(value);
    					break;
    					
    				case WidthDimension:
    				case WidthMeasure:
    					setWidthDimension(value);
    					break;
    					
    				case HeightDimension:
    				case HeightMeasure:
    					setHeightDimension(value);
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
    			return EDimensionDetails.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }

		public String getMeasurementUnitCode() {
			return measurementUnitCode;
		}
		public void setMeasurementUnitCode(String measurementUnitCode) {
			this.measurementUnitCode = Utils.checkNull(measurementUnitCode);
		}

		public String getLengthDimension() {
			return lengthDimension;
		}
		public void setLengthDimension(String lengthDimension) {
			this.lengthDimension = Utils.checkNull(lengthDimension);
		}

		public String getWidthDimension() {
			return widthDimension;
		}
		public void setWidthDimension(String widthDimension) {
			this.widthDimension = Utils.checkNull(widthDimension);
		}

		public String getHeightDimension() {
			return heightDimension;
		}
		public void setHeightDimension(String heightDimension) {
			this.heightDimension = Utils.checkNull(heightDimension);
		}

		public boolean isEmpty() {
			return (Utils.isStringEmpty(measurementUnitCode) && Utils.isStringEmpty(lengthDimension) &&
					Utils.isStringEmpty(widthDimension) && Utils.isStringEmpty(heightDimension));
		}
}
