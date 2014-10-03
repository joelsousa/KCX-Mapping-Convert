/*
 * Function    : Package (KIDS) == Packaging(UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description : Contains the Packages/Packaging Data
 * 			   : with all Fields used in KIDS and UIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 15.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Packages<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the Packages/Packaging Data with all Fields used in KIDS and UIDS.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Packages extends KCXMessage {

	private String quantity;			
	private String sequentialNumber;  	
	private String type;    			
    private String marks;	
    private String marksLng;				//ICS
    private String quantityCH;			
    private TransportMeans transportMeans; 	  
    private String identifier;				  
    private String itemFragmenationIndicator; 
    
    private boolean debug   = false;

	private enum EPackages {
	// Kids-TagNames, 			UIDS-TagNames
		Quantity,				//same    //AES anzcol
		SequentialNumber,    	Number,   //ICS Zabis-Hgp==anzcol!!!
		Type,					//same 
		Marks,					MarksAndNumbers,
		MarksLng,  //ICS        //----
		QuantityCH,
		TransportMeans,		
								Identifier,		
								ItemFragmenationIndicator;			

   }

	public Packages() {
		super();  
	}
	
	public Packages(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPackages) tag) {
				case TransportMeans:
  					transportMeans = new TransportMeans(getScanner());  
  					transportMeans.parse(tag.name());
  					break;	 
  					
  			default:
  					return;  			
  			}
  		} else {
   			  	
  			switch ((EPackages) tag) {
  				case Quantity:
  					setQuantity(value);
  					break;
  					
  				case SequentialNumber:
  				case Number:
  					setSequentialNumber(value);
  					break; 
  					
  				case Type:
  					setType(value);
  					break;  
  					
  				case Marks:
  				case MarksAndNumbers:
  					setMarks(value);
  					break;    					
  				case MarksLng:  
  					setMarksLng(value);
  					break;
  				case QuantityCH:
  					setQuantityCH(value);					
  					break; 
  					
  				case Identifier:
  					setIdentifier(value);					
  					break; 
  					
  				case ItemFragmenationIndicator:
  					setItemFragmenationIndicator(value);					
  					break; 
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EPackages.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public void setQuantity(String argument) {
		this.quantity = argument;
	}
	public String getQuantity() {
		return quantity;
	}

	public void setSequentialNumber(String argument) {
		this.sequentialNumber = argument;
	}
	public String getSequentialNumber() {
		return sequentialNumber;
	}

	public void setType(String argument) {
		this.type = argument;
	}
	public String getType() {
		return type;
	}
    
    public void setMarks(String argument) {
		this.marks = argument;
	}
	public String getMarks() {
		return marks;
	}
	
	public void setMarksLng(String argument) {
		this.marksLng = argument;
	}
	public String getMarksLng() {
			return marksLng;
	}	
	
	public String getQuantityCH() {
		return quantityCH;
	}
    public void setQuantityCH(String argument) {
		this.quantityCH = argument;
	}
    
	public TransportMeans getTransportMeans() {
		return transportMeans;
	}
    public void setTransportMeans(TransportMeans argument) {
		this.transportMeans = argument;
	}	
    
    public void setIdentifier(String argument) {
    	this.identifier = argument;
    }
    public String getIdentifier() {
    	return identifier;
    }
	
    public void setItemFragmenationIndicator(String argument) {
    	this.itemFragmenationIndicator = argument;
    }
    public String getItemFragmenationIndicator() {
    	return itemFragmenationIndicator;
    }
    
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.identifier) && Utils.isStringEmpty(this.itemFragmenationIndicator) && 
		        Utils.isStringEmpty(this.marks) && Utils.isStringEmpty(this.marksLng) && 
		        Utils.isStringEmpty(this.quantity) && Utils.isStringEmpty(this.quantityCH) && 
		        Utils.isStringEmpty(this.sequentialNumber) && Utils.isStringEmpty(this.type) && 
               (this.transportMeans == null || (this.transportMeans != null && this.transportMeans.isEmpty())));
	}    
}
