package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: Incident 
 * Created     	: 31.08.2008
 * Description 	: Contains the Incident Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Paul Pagdanganan 
 * @version 1.0.00
 */
public class Incident extends KCXMessage {
	
	private String incidentFlag;
	private String incidentInfo;
	private String endorsementDate;
	private String endorsementAuthority;
	private String endorsementPlace;
	private String endorsementCountry;
	
	private EFormat endorsementDateFormat;
	
	private enum EIncident {
		//KIDS:					UIDS:
		IncidentFlag,			//same
		IncidentInfo,			//same
		EndorsementDate,		//same
		EndorsementAuthority,	//same
		EndorsementPlace, 		//same
		EndorsementCountry;		//same
	}
	
	public Incident() {
		super();
	}
	
	public Incident(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr){
		if (value == null) {
			switch ((EIncident) tag) {
			default:
				return;
			}
	    } else {
			switch((EIncident) tag) {
			case IncidentFlag:
				setIncidentFlag(value);
				break;
			case IncidentInfo:
				setIncidentInfo(value);
				break;
			case EndorsementDate:
				setEndorsementDate(value);
				if (value.indexOf('-') == -1) {
					//setEndorsementDateFormat(EFormat.KIDS_DateTime);
					setEndorsementDateFormat(EFormat.KIDS_Date);           //EI20110815
				} else {
					//setEndorsementDateFormat(EFormat.ST_DateTime);
					setEndorsementDateFormat(EFormat.ST_Date);             //EI20110815
				}
				 break;
			case EndorsementAuthority:
				setEndorsementAuthority(value);
				break;
			case EndorsementPlace:
				setEndorsementPlace(value);
				break;
			case EndorsementCountry:
				setEndorsementCountry(value);
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
  			return EIncident.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getIncidentFlag() {
		return incidentFlag;
	}

	public void setIncidentFlag(String incidentFlag) {
		this.incidentFlag = incidentFlag;
	}

	public String getIncidentInfo() {
		return incidentInfo;
	}

	public void setIncidentInfo(String incidentInfo) {
		this.incidentInfo = incidentInfo;
	}

	public String getEndorsementDate() {
		return endorsementDate;
	}

	public void setEndorsementDate(String endorsementDate) {
		this.endorsementDate = endorsementDate;
	}

	public String getEndorsementAuthority() {
		return endorsementAuthority;
	}

	public void setEndorsementAuthority(String endorsementAuthority) {
		this.endorsementAuthority = endorsementAuthority;
	}

	public String getEndorsementPlace() {
		return endorsementPlace;
	}

	public void setEndorsementPlace(String endorsementPlace) {
		this.endorsementPlace = endorsementPlace;
	}

	public String getEndorsementCountry() {
		return endorsementCountry;
	}

	public void setEndorsementCountry(String endorsementCountry) {
		this.endorsementCountry = endorsementCountry;
	}

	public EFormat getEndorsementDateFormat() {
		return endorsementDateFormat;
	}

	public void setEndorsementDateFormat(EFormat endorsementDateFormat) {
		this.endorsementDateFormat = endorsementDateFormat;
	}
	
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.incidentFlag) && 
				Utils.isStringEmpty(this.incidentInfo) && 
				Utils.isStringEmpty(this.endorsementDate) && 
				Utils.isStringEmpty(this.endorsementAuthority) && 
				Utils.isStringEmpty(this.endorsementPlace) && 
				Utils.isStringEmpty(this.endorsementCountry)) {
			return true;
		} else {
			return false;
		}
	}

}
