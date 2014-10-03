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

package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 15.09.2011<br>
 * Beschreibung	: Contains the ImportPackage Data with all Fields used in KIDS and UIDS.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class ImportPackage extends KCXMessage {

	private String quantity;				 
	private String type;    			
    private String marks;	
   
	private enum EImportPackage {
	// Kids-TagNames, 			UIDS-TagNames
		Quantity,						
		Type,					 
		Marks;					
   }

	public ImportPackage() {
		super();  
	}
	
	public ImportPackage(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportPackage) tag) {
				
  			default:
  					return;  			
  			}
  		} else {
   			  	
  			switch ((EImportPackage) tag) {
  				case Quantity:
  					setQuantity(value);
  					break;  				
  				case Type:
  					setType(value);
  					break;    					
  				case Marks:  				
  					setMarks(value);
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
  			return EImportPackage.valueOf(token);
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
		
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.quantity) && Utils.isStringEmpty(this.type) && 
		        Utils.isStringEmpty(this.marks));
	}    
}
