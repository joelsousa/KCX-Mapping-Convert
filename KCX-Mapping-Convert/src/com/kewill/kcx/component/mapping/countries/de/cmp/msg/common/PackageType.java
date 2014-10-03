package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: PackageType.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PackageType extends KCXMessage {


    private String itemQuantity;
    private String grossWeightMeasure;
    private DimensionDetails linearSpatialDimension;
        
    public PackageType() {
	      	super();	       
    }
    
    public PackageType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EPackageType {
    	ItemQuantity,
    	GrossWeightMeasure,
    	LinearSpatialDimension,    	  	
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EPackageType) tag) {
    			
    			case LinearSpatialDimension:   
    				linearSpatialDimension = new DimensionDetails(getScanner());
    				linearSpatialDimension.parse(tag.name());					
					break;    							
					
    			default:
    					return;
    			}
    		} else {

    			switch ((EPackageType) tag) {

    				case ItemQuantity:   
    					setItemQuantity(value);    					
    					break; 
    					
    				case GrossWeightMeasure:   
    					setGrossWeightMeasure(value);    					
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
    			return EPackageType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		public String getItemQuantity() {
			return itemQuantity;
		}
		public void setItemQuantity(String itemQuantity) {
			this.itemQuantity = itemQuantity;
		}

		public String getGrossWeightMeasure() {
			return grossWeightMeasure;
		}
		public void setGrossWeightMeasure(String grossWeightMeasure) {
			this.grossWeightMeasure = grossWeightMeasure;
		}

		public DimensionDetails getLinearSpatialDimension() {
			return linearSpatialDimension;
		}
		public void setLinearSpatialDimension(DimensionDetails linearSpatialDimension) {
			this.linearSpatialDimension = linearSpatialDimension;
		}

		public boolean isEmpty() {
			return 	Utils.isStringEmpty(itemQuantity) && 
			Utils.isStringEmpty(grossWeightMeasure) &&
			linearSpatialDimension == null;							
		}	
}
