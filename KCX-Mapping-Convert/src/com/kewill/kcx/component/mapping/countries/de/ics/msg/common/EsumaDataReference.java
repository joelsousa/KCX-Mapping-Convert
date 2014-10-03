package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItem.EGoodsItem;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EsumaDataReference<br>
 * Created		: 2010.07.16<br>
 * Description	: EsumaDataReference tag in ICS.
 * 
 * @author Frederick T
 * @version 1.0.00
 *
 */
public class EsumaDataReference extends KCXMessage {

	private String mrn;
	private String countryOfficeFirstEntry;
	private String itemNumberEsuma;
	private List<EsumaDetails> itemNumberEsumaLists = new ArrayList<EsumaDetails>();
	private boolean debug   = false;
	
	private enum EEsumaDataReference {
		//KIDS				//UIDS
		MRN,							//Same
		CountryOfficeFirstEntry,		OriginalCountryOfFirstEntry,
		ItemNumberEsuma,				MRNItemNumber;
	}
	
	public EsumaDataReference() {
		super();
	}
	
	public EsumaDataReference(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public boolean isDebug() {
		return debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEsumaDataReference) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EEsumaDataReference) tag) {
	  			case MRN:
					if (Utils.isStringEmpty(getMrn())) {
						setMrn(value);
					}
					break;
	  			case CountryOfficeFirstEntry:
  				case OriginalCountryOfFirstEntry:	
  					if ((tag == EGoodsItem.CountryOfficeFirstEntry) ||
  							Utils.isStringEmpty(getCountryOfficeFirstEntry())) {
  						setCountryOfficeFirstEntry(value);
  					}
  					break;
  				case ItemNumberEsuma:
  				case MRNItemNumber:
  					EsumaDetails wrkEsumaDetails = new EsumaDetails(getScanner());
						wrkEsumaDetails.parse(tag.name());
						wrkEsumaDetails.setItemNumberEsuma(value);
						itemNumberEsumaLists.add(wrkEsumaDetails);
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

	public List<EsumaDetails> getItemNumberEsumaLists() {
		return itemNumberEsumaLists;
	}

	public void setItemNumberEsumaLists(List<EsumaDetails> itemNumberEsumaLists) {
		this.itemNumberEsumaLists = itemNumberEsumaLists;
	}

	public String getItemNumberEsuma() {
		return itemNumberEsuma;
	}

	public void setItemNumberEsuma(String itemNumberEsuma) {
		this.itemNumberEsuma = itemNumberEsuma;
	}


	
}
