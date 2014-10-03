package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Reference
 * Created		: 02.09.2010
 * Description	: contains Reference Data with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter 
 * @version 4.0.00
 */

public class Reference extends KCXMessage {
	
	private String			grn;
	private String			accessCode;
	private String			otherNumber;
	private String			issuingOffice;
	private String			ownerTin;
	private String			priceGroup;
	private String			notValidForEC;
	private List<String>	notValidForCountryList = new ArrayList<String>();
	private Amount			amount;
	
	public Reference() {
		super();
	}
	
	public Reference(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum ERerefence {
		//kids					uids
		GRN,					//same		
		AccessCode,				//same
		OtherNumber,			//same
		IssuingOffice,			//same
		OwnerTIN,				//same
		PriceGroup,				//same
		NotValidForEC,			//same
		NotValidForCountry,		//same
		Amounts;				//same
		
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
   			switch ((ERerefence) tag) {
   			case Amounts:
   				amount = new Amount(getScanner());
   				amount.parse(tag.name());
   				break;
   				
   			default:
   					return;
   			}
   		} else {
   			switch ((ERerefence) tag) {
   			case GRN:
   				setGrn(value);
   				break;
   			case AccessCode:
   				setAccessCode(value);
   				break;
   			case OtherNumber:
   				setOtherNumber(value);
   				break;
   			case IssuingOffice:
   				setIssuingOffice(value);
   				break;
   			case OwnerTIN:
   				setOwnerTin(value);
   				break;
   			case PriceGroup:
   				setPriceGroup(value);
   				break;
   			case NotValidForEC:
   				setNotValidForEC(value);
   				break;
   			case NotValidForCountry:
   				if (notValidForCountryList == null) {
   					notValidForCountryList = new ArrayList<String>();
   				}
   				notValidForCountryList.add(value);
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
  			return ERerefence.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	public String getGrn() {
		return grn;
	}

	public void setGrn(String grn) {
		this.grn = grn;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getOtherNumber() {
		return otherNumber;
	}

	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}

	public String getIssuingOffice() {
		return issuingOffice;
	}

	public void setIssuingOffice(String issuingOffice) {
		this.issuingOffice = issuingOffice;
	}

	public String getOwnerTin() {
		return ownerTin;
	}

	public void setOwnerTin(String ownerTin) {
		this.ownerTin = ownerTin;
	}

	public String getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(String priceGroup) {
		this.priceGroup = priceGroup;
	}

	public String getNotValidForEC() {
		return notValidForEC;
	}

	public void setNotValidForEC(String notValidForEC) {
		this.notValidForEC = notValidForEC;
	}

	public List<String> getNotValidForCountryList() {
		return notValidForCountryList;
	}

	public void setNotValidForCountryList(List<String> notValidForCountryList) {
		this.notValidForCountryList = notValidForCountryList;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

}
