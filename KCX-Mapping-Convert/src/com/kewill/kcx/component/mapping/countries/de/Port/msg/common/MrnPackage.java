package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module      : Port.<br>
 * Created     : 28.10.2011<br>
 * Description : 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class MrnPackage extends KCXMessage {

	 private String packageId;           //mrn_mrnid
	 private String netMass;             // eigmas    
	 private String grossMass;           // rohmas  
	 private String destinationCountry;  // ldbe    
	 private String dispatchCountry;     // ldve  
	
	
	 public MrnPackage() {
		 super();  
	 }

	 public MrnPackage(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum ENachlauf {	
			// Kids-TagNames, 			UIDS-TagNames;
		 		PackageId,					
		 		NetMass,
				GrossMass,
				DestinationCountry,
				DispatchCountry;			        				
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ENachlauf) tag) {
				default: return;			
			}
		} else {			
			switch ((ENachlauf) tag) {			
				case PackageId:
					setPackageId(value);
					break;
				case NetMass:
					setNetMass(value);
					break;
				case GrossMass:
					setGrossMass(value);
					break;
				case DestinationCountry:
					setDestinationCountry(value);
					break;
				case DispatchCountry:
					setDispatchCountry(value);
					break;
				default:
					return;
			}
		}
	}

	 public void stoppElement(Enum tag) {
	 }

	
	 public Enum translate(String token) {
		 try {
			return ENachlauf.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String argument) {
		this.packageId = argument;
	}					
		
	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String argument) {
		this.grossMass = argument;
	}
	
	public String getNetMass() {
		return netMass;
	}
	public void setNetMass(String argument) {
		this.netMass = argument;
	}
	
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String argument) {
		this.destinationCountry = argument;
	}
	
	public String getDispatchCountry() {
		return dispatchCountry;
	}
	public void setDispatchCountry(String argument) {
		this.dispatchCountry = argument;
	}
}
