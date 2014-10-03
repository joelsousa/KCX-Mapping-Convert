package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MeansOfTransport extends KCXMessage {
		
	private String voyageNumber;
	private String type;
	private String carrierId;
	private String carrierName;
	private String radioCallSign;
	private String vesselName;
	private String vesselNationalityCode;	
	
	private enum ECarriage {	
		ShipownerVoyageNumber,
		TypeOfMeansOfTransport,
		CarrierId,
		CarrierName,
		RadioCallSign,
		VesselName,
		VesselNationalityCode;	
   }	

	public MeansOfTransport() {
		super();  
	}

	public MeansOfTransport(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {    				
				default:
  					break;
  			}
  		} else {
  			switch((ECarriage) tag) {   
  			case ShipownerVoyageNumber:
  				setShipownerVoyageNumber(value);
  				break;
  			case TypeOfMeansOfTransport:
  				setTypeOfMeansOfTransport(value);
  				break;
  			case CarrierId:
  				setCarrierId(value);
  				break;
  			case CarrierName:
  				setCarrierName(value);
  				break;
  			case RadioCallSign:
  				setRadioCallSign(value);
  				break;
  			case VesselName:
  				setVesselName(value);
  				break;
  			case VesselNationalityCode:
  				setVesselNationalityCode(value);
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
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getShipownerVoyageNumber() {
		return voyageNumber;
	}    
	public void setShipownerVoyageNumber(String value) {
		this.voyageNumber = value;
	}

	public String getTypeOfMeansOfTransport() {
		return type;
	}    
	public void setTypeOfMeansOfTransport(String value) {
		this.type = value;
	}
	
	public String getCarrierId() {
		return carrierId;
	}    
	public void setCarrierId(String value) {
		this.carrierId = value;
	}
	
	public String getCarrierName() {
		return carrierName;
	}    
	public void setCarrierName(String value) {
		this.carrierName = value;
	}
	
	public String getRadioCallSign() {
		return radioCallSign;
	}    
	public void setRadioCallSign(String value) {
		this.radioCallSign = value;
	}
	
	public String getVesselName() {
		return vesselName;
	}    
	public void setVesselName(String value) {
		this.vesselName = value;
	}
	
	public String getVesselNationalityCode() {
		return vesselNationalityCode;
	}    
	public void setVesselNationalityCode(String value) {
		this.vesselNationalityCode = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(voyageNumber) && Utils.isStringEmpty(type) && 
		Utils.isStringEmpty(carrierId) && Utils.isStringEmpty(carrierName) && 
		Utils.isStringEmpty(radioCallSign) && Utils.isStringEmpty(vesselName) && 
		Utils.isStringEmpty(vesselNationalityCode);
	}
	
}

