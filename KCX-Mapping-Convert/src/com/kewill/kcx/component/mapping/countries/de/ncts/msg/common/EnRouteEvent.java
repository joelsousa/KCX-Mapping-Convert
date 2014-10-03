package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: EnRouteEvent 
 * Created     	: 31.08.2008
 * Description 	: Contains the EnRouteEvent Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Paul Pagdanganan 
 * @version 1.0.00
 */
public class EnRouteEvent extends KCXMessage {
	
	private String place;
	private String countryCode;
	private TransShipment transShipment = new TransShipment();
	private String totalNumberOfSeals;
	private List<String> sealsIdentityList = new ArrayList<String>();
	private Incident incident;
	private String alreadyInNCTS;
	
	private enum EEnRouteEvent {
		//KIDS				UIDS:
		Place,				//same
		CountryCode,		//same
		TransShipment,		//same
		TotalNumberOfSeals,	//same
		SealsIdentity,		//same
		Incident,			//same
		AlreadyInNCTS;		//same
	}
	
	public EnRouteEvent() {
		super();
	}
	
	public EnRouteEvent(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EEnRouteEvent) tag) {
			case TransShipment:
				TransShipment wrkTransShipment = new TransShipment(getScanner());
				wrkTransShipment.parse(tag.name());
				setTransShipment(wrkTransShipment);
				break;
			case Incident:
				Incident wrkIncident = new Incident(getScanner());
				wrkIncident.parse(tag.name());
				setIncident(wrkIncident);
				break;
			default:
				return;
			}
	    } else {
			switch ((EEnRouteEvent) tag) {
			case Place:
				setPlace(value);
				break;
			case CountryCode:
				setCountryCode(value);
				break;
			case TotalNumberOfSeals:
				setTotalNumberOfSeals(value);
				break;
			case AlreadyInNCTS:
				setAlreadyInNCTS(value);
				break;
			case SealsIdentity:
				sealsIdentityList.add(value);
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
  			return EEnRouteEvent.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public TransShipment getTransShipment() {
		return transShipment;
	}

	public void setTransShipment(TransShipment transShipment) {
		this.transShipment = transShipment;
	}

	public String getTotalNumberOfSeals() {
		return totalNumberOfSeals;
	}

	public void setTotalNumberOfSeals(String totalNumberOfSeals) {
		this.totalNumberOfSeals = totalNumberOfSeals;
	}

	public List<String> getSealsIdentityList() {
		return sealsIdentityList;
	}

	public void setSealsIdentityList(List<String> sealsIdentityList) {
		this.sealsIdentityList = sealsIdentityList;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public String getAlreadyInNCTS() {
		return alreadyInNCTS;
	}

	public void setAlreadyInNCTS(String alreadyInNCTS) {
		this.alreadyInNCTS = alreadyInNCTS;
	}
	
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.place) && 
				Utils.isStringEmpty(this.countryCode) && 
				this.transShipment.isEmpty() && 
				Utils.isStringEmpty(this.totalNumberOfSeals) && 
				this.sealsIdentityList.isEmpty() && 
				this.incident.isEmpty() && 
				Utils.isStringEmpty(this.alreadyInNCTS)) {
			return true;
		} else {
			return false;
		}
	}
}
