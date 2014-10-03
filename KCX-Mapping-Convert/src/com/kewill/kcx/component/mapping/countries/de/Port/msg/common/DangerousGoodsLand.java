package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 22.11.2011<br>
 * Description	: DGLandTransport.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class DangerousGoodsLand extends KCXMessage {

	//LandTransportDetails	 
	 private String dangerFlag;
	 private String regulationsCode;
	 private String regulationsNumber;
	 private String unNumber;               //strunr Straﬂe/Bahn Un-Nummer / ZABIS int(4)
	 private String annotations;            //fzpagt_ggvlis	
	 private String kemmlerNumber;          //fzpagt_ggvkem
	 private String ggvsClass;	 	 	    //strkla Gefahrgutklasse ADR,GGVS,GGVA
	 private String ggvsNumber;             // strzif, str(6)
	 
	 public DangerousGoodsLand() {
		 super();  
	 }

	 public DangerousGoodsLand(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum ELandTransport {	
			// Kids-TagNames, 						FSS						KFF
		    LandTransportDangerFlag,
		    TransportationRegulationsCode,
		    TransportationRegulationsNumber,
		    UNNumberForLandTransport,					
		    AnnotationsForLandTransport,			//akz_bunr,           BookingNo		
		 	KemmlerNumber,     						//akz_blnr            ShpNo
		 	GGVSClass,		 		
		 	GGVSNumber;		 	
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ELandTransport) tag) {
				default: return;			
			}
		} else {			
			switch ((ELandTransport) tag) {	
				case LandTransportDangerFlag:
					setLandTransportDangerFlag(value);
					break;
				case TransportationRegulationsCode:
					setTransportationRegulationsCode(value);
					break;
				case TransportationRegulationsNumber:
					setTransportationRegulationsNumber(value);
					break;
				case UNNumberForLandTransport:
					setUNNumberForLandTransport(value);
					break;
				case AnnotationsForLandTransport:
					setAnnotationsForLandTransport(value);
					break;
				case KemmlerNumber:
					setKemmlerNumber(value);
					break;
				case GGVSClass:
					setGGVSClass(value);
					break;				
				case GGVSNumber:
					setGGVSNumber(value);
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
			return ELandTransport.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

	public String getLandTransportDangerFlag() {
		return dangerFlag;
	}
	public void setLandTransportDangerFlag(String argument) {
		this.dangerFlag = argument;
	}

	public String getTransportationRegulationsCode() {
		return regulationsCode;
	}
	public void setTransportationRegulationsCode(String argument) {
		this.regulationsCode = argument;
	}	
	
	public String getTransportationRegulationsNumber() {
		return regulationsNumber;
	}
	public void setTransportationRegulationsNumber(String argument) {
		this.regulationsNumber = argument;
	}	
	
  
    public String getUNNumberForLandTransport() {
		return unNumber;
	}
	public void setUNNumberForLandTransport(String argument) {
		this.unNumber = argument;
	}					
		
	public String getAnnotationsForLandTransport() {
		return annotations;
	}
	public void setAnnotationsForLandTransport(String argument) {
		this.annotations = argument;
	}	
	
	public String getKemmlerNumber() {
		return kemmlerNumber;
	}
	public void setKemmlerNumber(String argument) {
		this.kemmlerNumber = argument;
	}	
		
	public String getGGVSClass() {
		return ggvsClass;
	}
	public void setGGVSClass(String argument) {
		this.ggvsClass = argument;
	}					
			
	public String getGGVSNumber() {
		return ggvsNumber;
	}
	public void setGGVSNumber(String argument) {
		this.ggvsNumber = argument;
	}		
			
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.unNumber) && Utils.isStringEmpty(this.dangerFlag) && 
		        Utils.isStringEmpty(this.kemmlerNumber) &&		       
		        Utils.isStringEmpty(this.regulationsCode) && Utils.isStringEmpty(this.regulationsNumber));  
	}
}
