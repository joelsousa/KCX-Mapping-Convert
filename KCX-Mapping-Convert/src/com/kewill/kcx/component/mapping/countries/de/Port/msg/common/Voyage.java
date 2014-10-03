package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module      : Port.<br>
 * Created     : 28.10.2011<br>
 * Description : 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class Voyage extends KCXMessage {

	 private String   shipReferenceNumber;  //akt_crnnr == Schiffsabfahrtsnummer
	 private String   voyageNumber;          //rswnr
	 private String   shipName;   			//shpnam
	 private String   shippCallSign;         //rufzei
	 private String   shipOwner;   			//frafco	 
	 private String   arrivalDate;    //eta
	 private String   departureDate;   //ets	 
	 private String   loadingPort;   //polloc
	 private String   dischargePort; //podloc
	 private String   finalPort;       //poeloc
	 private String   destinationPlaceCode;  //ortco UN-Code
	 private String   destinationPlaceName;  //ortna
	 private String   imoNumber;          //imonr
	
	 public Voyage() {
		 super();  
	 }

	 public Voyage(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum EVoyage {	
			// Kids-TagNames, 			UIDS-TagNames;
		 ShipDepartureNumber, ShipReferenceNumber,
		 VoyageNumber,      
		 ShipName,   		
		 ShippCallSign,     
		 ShipOwner,	
		 ArrivalDate,    
		 DepartureDate,  		
		 LoadingPort,  
		 DischargePort,
		 FinalPort,      
		 DestinationPlaceCode,
		 DestinationPlaceName,
		 IMONumber;
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EVoyage) tag) {
				default: return;			
			}
		} else {			
			switch ((EVoyage) tag) {			
				case ShipDepartureNumber:
				case ShipReferenceNumber:
					setShipReferenceNumber(value);
					break;
				case VoyageNumber:
					setVoyageNumber(value);
					break;
				case ShipName:
					setShipName(value);
					break;
				case ShippCallSign:
					setShipCallSign(value);
					break;
				case ShipOwner:
					setShipOwner(value);
					break;
				case ArrivalDate:
					setArrivalDate(value);
					break;
				case DepartureDate:
					setDepartureDate(value);
					break;
				
				case LoadingPort:
					setLoadingPort(value);
					break;
				case DischargePort:
					setDischargePort(value);
					break;
				case FinalPort:
					setFinalPort(value);
					break;
				case DestinationPlaceCode:
					setDestinationPlaceCode(value);
					break;
				case DestinationPlaceName:
					setDestinationPlaceName(value);
					break;
				case IMONumber:
					setImoNumber(value);
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
			return EVoyage.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getShipReferenceNumber() {
		return shipReferenceNumber;
	}
	public void setShipReferenceNumber(String argument) {
		this.shipReferenceNumber = argument;
	}					
		
	public String getVoyageNumber() {
		return voyageNumber;
	}
	public void setVoyageNumber(String argument) {
		this.voyageNumber = argument;
	}	
	
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String argument) {
		this.shipName = argument;
	}
	 
    public String getShipCallSign() {
		return shippCallSign;
	}
	public void setShipCallSign(String argument) {
		this.shippCallSign = argument;
	}					
		
	public String getShipOwner() {
		return shipOwner;
	}
	public void setShipOwner(String argument) {
		this.shipOwner = argument;
	}	
	
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String argument) {
		this.arrivalDate = argument;
	}

    public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String argument) {
		this.departureDate = argument;
	}					
		
	public String getImoNumber() {
		return imoNumber;
	}
	public void setImoNumber(String argument) {
		this.imoNumber = argument;
	}	
	
	public String getLoadingPort() {
		return loadingPort;
	}
	public void setLoadingPort(String argument) {
		this.loadingPort = argument;
	}

    public String getDischargePort() {
		return dischargePort;
	}
	public void setDischargePort(String argument) {
		this.dischargePort = argument;
	}					
	
	public String getFinalPort() {
		return finalPort;
	}
	public void setFinalPort(String argument) {
		this.finalPort = argument;
	}	
	
	public String getDestinationPlaceCode() {
		return destinationPlaceCode;
	}
	public void setDestinationPlaceCode(String argument) {
		this.destinationPlaceCode = argument;
	}
	
	public String getDestinationPlaceName() {
		return destinationPlaceName;
	}
	public void setDestinationPlaceName(String argument) {
		this.destinationPlaceName = argument;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.shipReferenceNumber) && Utils.isStringEmpty(this.voyageNumber) && 
		        Utils.isStringEmpty(this.shipName) &&
		        Utils.isStringEmpty(this.arrivalDate) && Utils.isStringEmpty(this.departureDate) && 
		        Utils.isStringEmpty(this.loadingPort) && Utils.isStringEmpty(this.dischargePort) && 
		        Utils.isStringEmpty(this.dischargePort) && Utils.isStringEmpty(this.imoNumber));  
	}
}
