package com.kewill.kcx.component.mapping.countries.de.ics20.msg.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.25<br>
 * Description	: EsumaDataReference.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class EsumaDataReference extends KCXMessage {

	private String mrn;
	private String countryOfficeFirstEntry;
	private String itemNumberEsuma;	
	private List<EsumaDetails> esumaDetailsList;
	private String customsStatusOfGoods;                  
	
	private enum EEsumaDataReference {
		//KIDS				//UIDS
		MRN,							//Same
		CountryOfficeFirstEntry,		OriginalCountryOfFirstEntry,
		//ItemNumberEsuma,				MRNItemNumber,
		EsumaDetails,                   MRNDetails,
		CustomsStatusOfGoods,           //same
	}
	
	public EsumaDataReference() {
		super();
	}
	
	public EsumaDataReference(XmlMsgScanner scanner) {
		super(scanner);
	}	
	
	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEsumaDataReference) tag) {
  			case EsumaDetails:		
			case MRNDetails:
				EsumaDetails details = new EsumaDetails(getScanner());
				details.parse(tag.name());
				addEsumaDetailsList(details);
				break;
  			default:
  					return;
  			}
  		} else {

  			switch ((EEsumaDataReference) tag) {
	  			case MRN:					
	  				setMrn(value);					
					break;
	  			case CountryOfficeFirstEntry:
  				case OriginalCountryOfFirstEntry:	  					
  					setCountryOfficeFirstEntry(value);  					
  					break;  				
  				case CustomsStatusOfGoods:
  					setCustomsStatusOfGoods(value);
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
  			return EEsumaDataReference.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getCountryOfficeFirstEntry() {
		return countryOfficeFirstEntry;
	}

	public void setCountryOfficeFirstEntry(String countryOfficeFirstEntry) {
		this.countryOfficeFirstEntry = countryOfficeFirstEntry;
	}

	public List<EsumaDetails> getEsumaDetailsList() {
		return esumaDetailsList;
	}

	public void setEsumaDetailsList(List<EsumaDetails> details) {
		this.esumaDetailsList = details;
	}
	
	public void addEsumaDetailsList(EsumaDetails details) {
		if (esumaDetailsList == null) {
			esumaDetailsList = new ArrayList<EsumaDetails>();
		}
		esumaDetailsList.add(details);
	}

	public String getItemNumberEsuma() {
		return itemNumberEsuma;
	}

	public void setItemNumberEsuma(String itemNumberEsuma) {
		this.itemNumberEsuma = itemNumberEsuma;
	}

	public String getCustomsStatusOfGoods() {
		return customsStatusOfGoods;
	}
	
	public void setCustomsStatusOfGoods(String value) {
		this.customsStatusOfGoods = value;
	}
	
}
