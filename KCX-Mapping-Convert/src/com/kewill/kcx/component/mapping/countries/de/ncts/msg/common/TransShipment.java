package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: TransShipment
 * Created		: 31.08.2010
 * Description	: contains TransShipment data with all fields used in KIDS/UIDS
 * 
 * @author P. Pagdanganan
 * @version 1.0.00
 */
public class TransShipment extends KCXMessage {

	private String newTransportId;
	private String newTransportCountry;
	private String endorsementDate;
	private String endorsementAuthority;
	private String endorsementPlace;
	private String endorsementCountry;
	private List<String> containerNumberList = new ArrayList<String>();
	private EFormat endorsementDateFormat;
	
	private enum ETranshipment {
		//KIDS:					UIDS:
		NewTransportId,			//same
		NewTransportCountry,	//same
		EndorsementDate,		//same
		EndorsementAuthority,	//same
		EndorsementPlace,		//same
		EndorsementCountry,		//same
		ContainerNumber;		//same						
	}
	
	public TransShipment() {
		super();
	}
	
	public TransShipment(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr){
		if (value == null) {
			switch ((ETranshipment) tag) {
			default:
				return;
			}
		} else {
			switch((ETranshipment) tag) {
			case NewTransportId:
				setNewTransportId(value);
				break;
			case NewTransportCountry:
				setNewTransportCountry(value);
				break;
			case EndorsementDate:
				setEndorsementDate(value);				
				//if (tag == ETranshipment.EndorsementDate) {
				if (value.indexOf('-') == -1) {
					//setEndorsementDateFormat(EFormat.KIDS_DateTime);
					setEndorsementDateFormat(EFormat.KIDS_Date);             //EI20110815
				} else {
					//setEndorsementDateFormat(EFormat.ST_DateTime);
					setEndorsementDateFormat(EFormat.ST_Date);              //EI20110815
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
			case ContainerNumber:
				if (containerNumberList == null) {
					containerNumberList = new Vector <String>();
				}
				containerNumberList.add(value);
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
  			return ETranshipment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getNewTransportId() {
		return newTransportId;
	}

	public void setNewTransportId(String newTransportId) {
		this.newTransportId = newTransportId;
	}

	public String getNewTransportCountry() {
		return newTransportCountry;
	}

	public void setNewTransportCountry(String newTransportCountry) {
		this.newTransportCountry = newTransportCountry;
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

	public List<String> getContainerNumberList() {
		return containerNumberList;
	}

	public void setContainerNumberList(List<String> containerNumberList) {
		this.containerNumberList = containerNumberList;
	}

	public EFormat getEndorsementDateFormat() {
		return endorsementDateFormat;
	}

	public void setEndorsementDateFormat(EFormat endorsementDateFormat) {
		this.endorsementDateFormat = endorsementDateFormat;
	}
	
	public boolean isEmpty() {
		
		if (Utils.isStringEmpty(this.newTransportId) && 
				Utils.isStringEmpty(this.newTransportCountry) && 
				Utils.isStringEmpty(this.endorsementDate) && 
				Utils.isStringEmpty(this.endorsementAuthority) && 
				Utils.isStringEmpty(this.endorsementPlace) && 
				Utils.isStringEmpty(this.endorsementCountry) && 
				this.containerNumberList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
}
