package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 17.06.2014<br>
 * Description	: Consignment.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Consignment extends KCXMessage {
	
	private TransportMeans arrivalTransportMeans;      //Import
    private TransportMeans borderTransportMeans;
    private TransportMeans departureTransportMeans;   //Export
    private String ieInlandModeOfTransportCode;
    private Identifier goodslocation;
    private ArrayList<TransportEquipment> transportEquipmentList; //Export
    private String ieSummaryDeclarationIdentifier;
 
	private enum EConsignment {	
		ArrivalTransportMeans,
		DepartureTransportMeans,
		BorderTransportMeans,		
		IEInlandModeOfTransportCode,
		GoodsLocation,
		TransportEquipment,
		IESummaryDeclarationIdentifier;
   }	

	public Consignment() {
		super();  
	}
	public Consignment(String person) {
		super();  		
	}	

	public Consignment(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public Consignment(XmlMsgScanner scanner, String person) {
  		super(scanner);  		
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EConsignment) tag) {
  				case ArrivalTransportMeans:
  					arrivalTransportMeans = new TransportMeans(getScanner());  	
  					arrivalTransportMeans.parse(tag.name());
  				break;
  				case DepartureTransportMeans:
  					departureTransportMeans = new TransportMeans(getScanner());  	
  					departureTransportMeans.parse(tag.name());
  					break;
  				case BorderTransportMeans:
  					borderTransportMeans = new TransportMeans(getScanner());  	
  					borderTransportMeans.parse(tag.name());
  					break;
  				case GoodsLocation:
  					goodslocation = new Identifier(getScanner());  	
  					goodslocation.parse(tag.name());
  					break;  
  				case TransportEquipment:
 					TransportEquipment equipment = new TransportEquipment(getScanner());  	
 					equipment.parse(tag.name());
 					this.addTransportEquipmentList(equipment);
					break; 
				default:
  					return;
  			}
  		} else {
  			switch((EConsignment) tag) {   			
  				case IEInlandModeOfTransportCode:  				
  					setIEInlandModeOfTransportCode(value);
  					break;   				
  				case IESummaryDeclarationIdentifier:
  					setIESummaryDeclarationIdentifier(value);
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
  			return EConsignment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}	
	
	public TransportMeans getArrivalTransportMeans() {
		return arrivalTransportMeans;
	}
	public void setArrivalTransportMeans(TransportMeans arrivalTransportMeans) {
		this.arrivalTransportMeans = arrivalTransportMeans;
	}
	public TransportMeans getBorderTransportMeans() {
		return borderTransportMeans;
	}
	public void setBorderTransportMeans(TransportMeans borderTransportMeans) {
		this.borderTransportMeans = borderTransportMeans;
	}
	public TransportMeans getDepartureTransportMeans() {
		return departureTransportMeans;
	}
	public void setDepartureTransportMeans(TransportMeans departureTransportMeans) {
		this.departureTransportMeans = departureTransportMeans;
	}
	public String getIEInlandModeOfTransportCode() {
		return ieInlandModeOfTransportCode;
	}
	public void setIEInlandModeOfTransportCode(String ieInlandModeOfTransportCode) {
		this.ieInlandModeOfTransportCode = ieInlandModeOfTransportCode;
	}
	public Identifier getGoodslocation() {
		return goodslocation;
	}
	public void setGoodslocation(Identifier goodslocation) {
		this.goodslocation = goodslocation;
	}
	public String getIESummaryDeclarationIdentifier() {
		return ieSummaryDeclarationIdentifier;
	}
	public void setIESummaryDeclarationIdentifier(String value) {
		this.ieSummaryDeclarationIdentifier = value;
	}
	public ArrayList<TransportEquipment> getTransportEquipmentList() {
		return transportEquipmentList;
	}
	public void setTransportEquipmentList(ArrayList<TransportEquipment> list) {
		this.transportEquipmentList = list;
	}
	public void addTransportEquipmentList(TransportEquipment equ) {
		if (transportEquipmentList == null) {
			transportEquipmentList = new ArrayList<TransportEquipment>();
		}
		transportEquipmentList.add(equ);
	}  
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(ieInlandModeOfTransportCode) && Utils.isStringEmpty(ieSummaryDeclarationIdentifier) && 
		arrivalTransportMeans == null && borderTransportMeans == null && departureTransportMeans == null &&	
		goodslocation == null && transportEquipmentList == null;
	}    
}
