/*
 * Function    : GoodsItem.java
 * Titel       :
 * Date        : 16.06.2010
 * Author      : Pete T
 * Description : Contains the GoodsItem Data 
 * 			   : with all Fields used in UIDS and KIDS 
 * Parameters  : 
 * 
 * Changes
 * -----------
 * Author      : krzoska
 * Date        : 07.12.2010
 * Label       : AK20101207
 * Description : added Document  
 *
 *
 * Changes
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 *
 * 
 */

package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: GoodsItem<br>
 * Created		: 2010.07.16<br>
 * Description	: GoodsItem tag that is used in ICS.
 * 
 * @author Frederick T
 * @version 1.0.00
 *
 */
public class GoodsItem extends KCXMessage {

	private String itemNumber;
	private String type;
    private String reference;
    private String referenceLng;
	private String mrn;
    private String countryOfficeFirstEntry;
    private List<String> itemNumberEsumaList = new ArrayList<String>();
    private List<EsumaDataReference> esumaDataReferenceList = new ArrayList<EsumaDataReference>(); //FT2010.07.16
    private List<IcsDocument>docList = null;

    private boolean debug   = false;

	protected enum EGoodsItem {
		//KIDS:							UIDS:
		ItemNumber,						//Same
		Type,							//Same
		Reference,						//Same
		ReferenceLng,					//No equivalent
		MRN,							//Same
		CountryOfficeFirstEntry,		OriginalCountryOfFirstEntry,
		EsumaDataReference,				CustomsDataReference, //FT2010.07.16
		ItemNumberEsuma,				MRNItemNumber,
		Document,						Documents;
    }
	
	public GoodsItem() {
		super();
	}

    public GoodsItem(XmlMsgScanner scanner) {
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
  			switch ((EGoodsItem) tag) {
  			case EsumaDataReference:		//FT2010.07.16
			case CustomsDataReference:
					EsumaDataReference wrkEsumaDataReference = new EsumaDataReference(getScanner());
					wrkEsumaDataReference.parse(tag.name());
					esumaDataReferenceList.add(wrkEsumaDataReference);
				break;
			//AK20101207
			case Document:
			case Documents:
					IcsDocument doc = new IcsDocument(getScanner());
					doc.parse(tag.name());
					addDocument(doc);
				break;
  			default:
  					return;
  			}
  		} else {

  			switch ((EGoodsItem) tag) {
				case ItemNumber:
  					setItemNumber(value);
  					break;
  				case Type:
  					setType(value);
  					break;
  				case Reference:
  					setReference(value);
  					break;
  				case ReferenceLng:
  					setReferenceLng(value);
  					break;
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
  					itemNumberEsumaList.add(value);
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
  			return EGoodsItem.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String argument) {
		itemNumber = argument;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReferenceLng() {
		return referenceLng;
	}

	public void setReferenceLng(String referenceLng) {
		this.referenceLng = referenceLng;
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

	public List<String> getItemNumberEsumaList() {
		return itemNumberEsumaList;
	}

	public void setItemNumberEsumaList(List<String> itemNumberEsuma) {
		this.itemNumberEsumaList = itemNumberEsuma;
	}
	public List<EsumaDataReference> getEsumaDataReferenceList() {		//FT2010.07.16
		return esumaDataReferenceList;
	}

	public void setEsumaDataReferenceList(
			List<EsumaDataReference> esumaDataReferenceList) {			//FT2010.07.16
		this.esumaDataReferenceList = esumaDataReferenceList;
	}

	//AK20101207
  	private void addDocument(IcsDocument doc) {
  		if (docList == null) {
  			docList = new ArrayList<IcsDocument>(); 
  		}
		docList.add(doc);
	}

	//AK20101207
	public List<IcsDocument> getDocList() {
		return docList;
	
	}
	
	//AK20101207
	public void setDocList(List<IcsDocument> docList) {
		this.docList = docList;
	}


}
