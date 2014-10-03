/*
 * Function    : MsgExtNot.java
 * Titel       :
 * Date        : 23.04.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains the Message ExitNotification 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ControlResult;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: AES<br>
 * Created		: 23.07.2013<br>
 * Description	: Contains the Message ExitAuthorisation  with all Fields used in KIDS. 
 * 				: (in UIDS heisst sie ExitAuthorization) == IE518	
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class MsgExitAuthorisation extends KCXMessage {

	// simple tags
	private String ucrNumber;
	private String timeOfPermit;
	private EFormat timeOfPermitFormat;
	private String statusOfControl;	
	private String ucrOtherSystem;	
	private String referenceNumber;	
	private String realOfficeOfExit;
	private TIN forwarderTin;	
	private String totalNumberPackages;
	private String preCustomsClearance;     //EI20130812
    
	private enum ECTLEXT {

		// Kids-TagNames, 				UIDS-TagNames;
    	UCRNumber,						DocumentReferenceNumber,
    	TimeOfPermit,					DateOfPermission,
    	StatusOfControl,			    ControlResultExit,	//ControlResultExit.Status
    	UCROtherSystem,					ExternalRegistrationNumber,    	    	
		ReferenceNumber,				//same 		
		RealOfficeOfExit,				CustomsOffices, //CustomsOffices.OfficeOfExit
		ForwarderTIN,					Shipper,
		PreCustomsClearance,			//same //EI20130812
		TotalNumberPackages,			// --				  
	}
	
	public MsgExitAuthorisation() {
		super();		
	}
	
	public MsgExitAuthorisation(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			
			switch ((ECTLEXT) tag) {
			
			case Shipper:	
				Party party = new Party(getScanner());
				party.parse(tag.name());
				if (forwarderTin == null) {
					forwarderTin = new TIN();
				}
				forwarderTin = party.getPartyTIN();
				break;
				
			case ForwarderTIN:	
				forwarderTin = new TIN(getScanner());
				forwarderTin.parse(tag.name());					
				break;
				
			case CustomsOffices:
				CustomsOffices customsOffices = new CustomsOffices();	
				if (customsOffices != null) {
					realOfficeOfExit = customsOffices.getOfficeOfExit();
				}
				break;
				
			case ControlResultExit:
				ControlResult control = new ControlResult(getScanner());
				control.parse(tag.name());
				if (control != null) {
					statusOfControl = control.getStatus();
				}
				break;
				
			default:
				return;
			}
		} else {
			
			switch ((ECTLEXT) tag) {
		
				case ReferenceNumber: 
					setReferenceNumber(value);
					break;
					
				case UCRNumber: 
				case DocumentReferenceNumber:				
					setUCRNumber(value);
					break;
									
				case UCROtherSystem:
				case ExternalRegistrationNumber:
					setUCROtherSystem(value);
					break;
					
				case TimeOfPermit: 
				case DateOfPermission:
					setTimeOfPermit(value);
					if (tag == ECTLEXT.TimeOfPermit) {
						setTimeOfPermitFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setTimeOfPermitFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;
					
				case StatusOfControl: 				
					setStatusOfControl(value);
					break;
							
				case RealOfficeOfExit:
					setRealOfficeOfExit(value);
					break;
					
				case TotalNumberPackages:
					setTotalNumberPackages(value);
					break;
					
				case PreCustomsClearance:
					setPreCustomsClearance(value);
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
			return ECTLEXT.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
	
	public String getUCRNumber() {
		return ucrNumber;	
	}
	public void setUCRNumber(String argument) {
		ucrNumber = argument;
	}

	public String getTimeOfPermit() {
		return timeOfPermit;	
	}
	public void setTimeOfPermit(String argument) {
		timeOfPermit = argument;
	}
	public EFormat getTimeOfPermitFormat() {
		return timeOfPermitFormat;	
	}
	public void setTimeOfPermitFormat(EFormat argument) {
		timeOfPermitFormat = argument;
	}
	
	public String getStatusOfControl() {
		return statusOfControl;	
	}
	public void setStatusOfControl(String argument) {
		statusOfControl = argument;
	}
		
	public String getUCROtherSystem() {
		return ucrOtherSystem;	
	}
	public void setUCROtherSystem(String argument) {
		this.ucrOtherSystem = argument;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;	
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getRealOfficeOfExit() {
		return realOfficeOfExit;	
	}
	public void setRealOfficeOfExit(String argument) {
		this.realOfficeOfExit = argument;
	}
	
	public TIN getForwarderTin() {
		return forwarderTin;	
	}
	public void setForwarderTin(TIN argument) {
		forwarderTin = argument;
	}	
	public Party getForwarder() {
		if (forwarderTin == null) {
			return null;
		}
		Party forwarder = new Party();
		forwarder.setPartyTIN(forwarderTin);
		return forwarder;	
	}
	
	public String getTotalNumberPackages() {
		return totalNumberPackages;	
	}
	public void setTotalNumberPackages(String argument) {
		this.totalNumberPackages = argument;
	}	
	
	public String getPreCustomsClearance() {
		return preCustomsClearance;
	}
	public void setPreCustomsClearance(String preCustomsClearance) {
		this.preCustomsClearance = preCustomsClearance;
	}
	
	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 
		 if (!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}
}
