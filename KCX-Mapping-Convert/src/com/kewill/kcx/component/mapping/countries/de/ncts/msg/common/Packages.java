package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: Packages 
 * Created     	: 31.08.2008
 * Description 	: Contains the Packages Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Paul Pagdanganan 
 * @version 1.0.00
 */
public class Packages extends KCXMessage {

	private String quantity;			
	private String sequentialNumber;  	
	private String type;    			
    private String marks;	
    private String quantityCH;			
    private TransportMeans transportMeans; 	  
    
	private enum EPackages {
		//KIDS 					//UIDS
		Quantity,				//same 
		SequentialNumber,    	Number, 
		Type,					//same 
		Marks,					MarksAndNumbers,
		QuantityCH,
		TransportMeans
   }

	public Packages() {
		super();  
	}
	
	public Packages(XmlMsgScanner scanner) {
  		super(scanner);
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
  				case QuantityCH:
  					setQuantityCH(value);					
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
    
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.marks) && 
				Utils.isStringEmpty(this.quantity) && 
				Utils.isStringEmpty(this.quantityCH) && 
				Utils.isStringEmpty(this.sequentialNumber) && 
				Utils.isStringEmpty(this.type) && 
				(this.transportMeans == null || 
						(this.transportMeans != null && 
								this.transportMeans.isEmpty()))) {
			return true;
		} else {
			return false;
		}
	}    
}
