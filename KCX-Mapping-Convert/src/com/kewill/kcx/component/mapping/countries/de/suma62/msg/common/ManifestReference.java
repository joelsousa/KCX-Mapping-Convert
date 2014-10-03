package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 11.12.2012<br>
 * Description	: Contains the ManifestReference Data with all Fields used in KIDS Manifest.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ManifestReference extends KCXMessage {
	
	private String flightNumber;
	private String placeOfLoading;
	private String registrationNumber;
	private String itemNumber;
	private String referenceNumber;
	private String custodianTIN;
	private String placeOfCustodyCode;	
	private String referenceIdentifier;		
	private PreviousDocument previousDocument; //EI20130527: previousDoc. ist gleich wie message.getPreviousDoc.                     
	private ReferencedSpecification referencedSpecification;
	
	private enum EReferenceM {
		//KIDS == UIDS		
		ItemNumber,
		ReferenceNumber,
		ReferenceIdentifier,
		RegistrationNumber,
		FlightNumber,
		PlaceOfLoading,		
		PreviousDocument,
		ReferencedSpecification,
		CustodianTIN,
		PlaceOfCustodyCode,
	}
	
	public ManifestReference() {
		super();  
	}

    public ManifestReference(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EReferenceM) tag) {
  			case PreviousDocument:
  				previousDocument = new PreviousDocument(getScanner());
  				previousDocument.parse(tag.name());
				break;						
  			case ReferencedSpecification:
  				referencedSpecification = new ReferencedSpecification(getScanner());
  				referencedSpecification.parse(tag.name());
				break;						
  			default:
  					return;
  			}
  		} else {
  			switch ((EReferenceM) tag) {
  				case FlightNumber:  				
  					setFlightNumber(value);
  					break;
  				case PlaceOfLoading:
  					setPlaceOfLoading(value);
  					break;
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;
  				case RegistrationNumber:
  					setRegistrationNumber(value);
  					break;
  				case ReferenceNumber:  				
  					setReferenceNumber(value);
  					break;
  				case CustodianTIN:
  					setCustodianTIN(value);
  					break;
  				case PlaceOfCustodyCode:  				
  					setPlaceOfCustodyCode(value);
  					break;
  				case ReferenceIdentifier:
  					setReferenceIdentifier(value);
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
  			return EReferenceM.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}	
  	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String value) {
		this.referenceNumber = Utils.checkNull(value);
	}

	public String getReferenceIdentifier() {
		return referenceIdentifier;
	}
	public void setReferenceIdentifier(String value) {
		this.referenceIdentifier = value;
	}	

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getPlaceOfLoading() {
		return placeOfLoading;
	}
	public void setPlaceOfLoading(String placeOfLoading) {
		this.placeOfLoading = placeOfLoading;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getCustodianTIN() {
		return custodianTIN;
	}

	public void setCustodianTIN(String custodianTIN) {
		this.custodianTIN = custodianTIN;
	}

	public String getPlaceOfCustodyCode() {
		return placeOfCustodyCode;
	}
	public void setPlaceOfCustodyCode(String placeOfCustodyCode) {
		this.placeOfCustodyCode = placeOfCustodyCode;
	}

	public PreviousDocument getPreviousDocument() {
		return previousDocument;
	}
	public void setPreviousDocument(PreviousDocument previousDocument) {
		this.previousDocument = previousDocument;
	}

	public ReferencedSpecification getReferencedSpecification() {
		return referencedSpecification;
	}
	public void setReferencedSpecification(
			ReferencedSpecification referencedSpecification) {
		this.referencedSpecification = referencedSpecification;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.flightNumber) && Utils.isStringEmpty(this.registrationNumber) &&
				Utils.isStringEmpty(this.referenceNumber) && Utils.isStringEmpty(this.referenceIdentifier) 
				);
	}
}
