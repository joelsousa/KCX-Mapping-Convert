package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 24.05.2013<br>
 * Description	: Contains the ManifestReference Data with all Fields used in KIDS Manifest.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class ManifestReference extends KCXMessage {
	
	private String itemNumber;
	//private String referenceNumber;
	//private String referenceIdentifier;	
	private String registrationNumber;
	private String edifactNumber;
	private String placeOfLoading;		
	private TIN    custodianTIN;
	private String placeOfCustodyCode;	
		
	private PreviousDocument previousDocument;
	private ReferencedSpecification referencedSpecification;
	
	private enum EReferenceM {
		//KIDS == UIDS
		ItemNumber,
		//ReferenceNumber,
		//ReferenceIdentifier,
		RegistrationNumber,
		EdifactNumber, EDIFACTNumber,		
		PlaceOfLoading,
		PreviousDocument,
		ReferencedSpecification,		
		CustodianTIN,			CustodianTin,
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
  			case CustodianTIN:
  				custodianTIN = new TIN(getScanner());
  				custodianTIN.parse(tag.name());
  				break;
  				
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
  				case PlaceOfLoading:
  					setPlaceOfLoading(value);
  					break;
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;
  				case RegistrationNumber:
  					setRegistrationNumber(value);
  					break;
  					/*
  				case ReferenceNumber:  				
  					setReferenceNumber(value);
  					break;
  				case ReferenceIdentifier:
  					setReferenceIdentifier(value);
  					break;
  					*/
  				case EDIFACTNumber:
  				case EdifactNumber:
  					setEdifactNumber(value);
  					break;
  				case PlaceOfCustodyCode:  				
  					setPlaceOfCustodyCode(value);
  					break;
  				case CustodianTIN:
  				case CustodianTin:
  					setUidsCustodianTin(value);
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
	/*
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
																			*/
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

	public TIN getCustodianTIN() {
		return custodianTIN;
	}
	public void setCustodianTIN(TIN custodianTIN) {
		this.custodianTIN = custodianTIN;
	}

	public String getEdifactNumber() {
		return edifactNumber;
	}
	public void setEdifactNumber(String edifactNumber) {
		this.edifactNumber = edifactNumber;
	}
	
	private void setUidsCustodianTin(String value) {   
		if (Utils.isStringEmpty(value)) {
			return;
		}
		this.custodianTIN = new TIN();
		this.custodianTIN.setTIN(value);		
	}
		
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.registrationNumber) && Utils.isStringEmpty(this.edifactNumber) && 
				Utils.isStringEmpty(this.placeOfLoading) && Utils.isStringEmpty(this.placeOfCustodyCode) &&
				Utils.isStringEmpty(this.placeOfLoading) && custodianTIN == null &&
				previousDocument == null && referencedSpecification == null);
	}
	
}
