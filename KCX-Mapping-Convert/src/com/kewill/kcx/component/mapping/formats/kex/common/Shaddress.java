package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
/*  Addresstypes for KEX
 *  
 */

public class Shaddress extends KCXMessage {
	private String 		 contactfirstname;	
	private String 		 contactlastname;	
	private String 		 contactemailaddress;	
	private String 		 contactfaxnumber;	
	private String 		 contacttelephonenumber;	
	private String 		 contacttitle;	
	private String 		 eorino;	
	private String 		 vattaxregistrationcode;	
	private String 		 nameof;	
	private String 		 streethousename1;	
	private String 		 streethousename2;	
	private String 		 housenumber;	
	private String 		 towncity;	
	private String 		 postalcodezipcode;	
	private String 		 countrycode;             //EI20120228
	private String 		 countryname;	
	private String 		 countystate;	
	private String 		 shipmentaddresstypecode;	
	
	private String addresscode;    					//EI20120118
	private String partytypeedi;                    //EI20120124
	
	 public Shaddress() {
		 super();  
	 }

	 public Shaddress(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 
	 /*
	 Address Type: valid values
	 EXPORTER -> Consignor
	 
	 all other types are mapped as their type names indicate 
	 CONSIGNEE
	 BUYER
	 	 
	 WAREHOUSE
	 PRODUCER
	 INTERMEDIATECONSIGNEE
	 PLACEOFDELIVERY
	 AGENTS
	 FORWARDER
	 CARRIER
	 PAYMENTBILLTO
	 CUSTOMSBROKERS
	 NOTIFY1
	 NOTIFY2
	 BANKLOCAL
	 BANKOVERSEAS
	 CUSTOMSDECLARANT
	 CUSTOMSPRINCIPAL
	 EXCISEWAREHOUSE
	 CUSTOMSUPERVISORYOFF
	 */
	 
	 private enum EShaddressesTags {	
			// KEX							KIDS
		 addresscode,    //EI20120118
		 partytypeedi,
		 contactfirstname,					//concatenation of first and lastname ->Contact/Clerk
		 contactlastname,					// 
		 contactemailaddress,				//Contact / Email
		 contactfaxnumber,					//Contact / FaxNumber
		 contacttelephonenumber,			//Contact / PhoneNumber
		 contacttitle,						//Contact / Title
		 eorino,							//(Adresstype)TIN e.g for Forwarder: GoodsDeclaration / ForwarderTIN / CustomerIdentifier
		 vattaxregistrationcode,			//VATNumber
		 nameof,							//Name
		 streethousename1,					//concatenation of streethousename1+2 ->Street
		 streethousename2,
		 housenumber,						//HouseNumber
		 towncity,							//City
		 postalcodezipcode,					//PostalCode
		 countrycode,                       //EI20120228 char(2)
		 countryname,						//Country
		 countystate,						//CountrySubEntity
		 shipmentaddresstypecode			/*
		 										Address Type: valid values
		 										EXPORTER -> Consignor
		 										all other types are mapped as their type names indicate 
		 										CONSIGNEE
		 										BUYER
		 										WAREHOUSE
		 										PRODUCER
		 										INTERMEDIATECONSIGNEE
		 										PLACEOFDELIVERY
		 										AGENTS
		 										FORWARDER
		 										CARRIER
		 										PAYMENTBILLTO
		 										CUSTOMSBROKERS
		 										NOTIFY1
		 										NOTIFY2
		 										BANKLOCAL
		 										BANKOVERSEAS
		 										CUSTOMSDECLARANT
		 										CUSTOMSPRINCIPAL
		 										EXCISEWAREHOUSE
		 										CUSTOMSUPERVISORYOFF
		 									 */
		 ;
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EShaddressesTags) tag) {
				default: return;			
			}
		} else {			
			switch ((EShaddressesTags) tag) {			
				case contactfirstname:
					setContactfirstname(value);
					break;
				case contactlastname:
					setContactlastname(value);
					break;
				case contactemailaddress:
					setContactemailaddress(value);
					break;
				case contactfaxnumber:
					setContactfaxnumber(value);
					break;
				case contacttelephonenumber:
					setContacttelephonenumber(value);
					break;
				case contacttitle:
					setContacttitle(value);
					break;
				case eorino:
					setEorino(value);
					break;
				case vattaxregistrationcode:
					setVattaxregistrationcode(value);
					break;
				case nameof:
					setNameof(value);
					break;
				case streethousename1:
					setStreethousename1(value);
					break;
				case streethousename2:
					setStreethousename2(value);
					break;
				case housenumber:
					setHousenumber(value);
					break;
				case towncity:
					setTowncity(value);
					break;
				case postalcodezipcode:
					setPostalcodezipcode(value);
					break;
				case countryname:
					setCountryname(value);
					break;
				case countystate:
					setCountystate(value);
					break;
				case shipmentaddresstypecode:
					setShipmentaddresstypecode(value);
					break;
				case addresscode:
					setAddresscode(value);
					break;
				case partytypeedi:
					setPartytypeedi(value);
					break;
				case countrycode:
					setCountrycode(value);
					break;
					
				default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
				return EShaddressesTags.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getContactfirstname() {
		return contactfirstname;
	}

	public void setContactfirstname(String contactfirstname) {
		this.contactfirstname = Utils.checkNull(contactfirstname);
	}

	public String getContactlastname() {
		return contactlastname;
	}

	public void setContactlastname(String contactlastname) {
		this.contactlastname = Utils.checkNull(contactlastname);
	}

	public String getContactemailaddress() {
		return contactemailaddress;
	}

	public void setContactemailaddress(String contactemailaddress) {
		this.contactemailaddress = Utils.checkNull(contactemailaddress);
	}

	public String getContactfaxnumber() {
		return contactfaxnumber;
	}

	public void setContactfaxnumber(String contactfaxnumber) {
		this.contactfaxnumber = Utils.checkNull(contactfaxnumber);
	}

	public String getContacttelephonenumber() {
		return contacttelephonenumber;
	}

	public void setContacttelephonenumber(String contacttelephonenumber) {
		this.contacttelephonenumber = Utils.checkNull(contacttelephonenumber);
	}

	public String getContacttitle() {
		return contacttitle;
	}

	public void setContacttitle(String contacttitle) {
		this.contacttitle = Utils.checkNull(contacttitle);
	}

	public String getEorino() {
		return eorino;
	}

	public void setEorino(String eorino) {
		this.eorino = Utils.checkNull(eorino);
	}

	public String getVattaxregistrationcode() {
		return vattaxregistrationcode;
	}

	public void setVattaxregistrationcode(String vattaxregistrationcode) {
		this.vattaxregistrationcode = Utils.checkNull(vattaxregistrationcode);
	}

	public String getNameof() {
		return nameof;
	}

	public void setNameof(String nameof) {
		this.nameof = Utils.checkNull(nameof);
	}

	public String getStreethousename1() {
		return streethousename1;
	}

	public void setStreethousename1(String streethousename1) {
		this.streethousename1 = Utils.checkNull(streethousename1);
	}

	public String getStreethousename2() {
		return streethousename2;
	}

	public void setStreethousename2(String streethousename2) {
		this.streethousename2 = Utils.checkNull(streethousename2);
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = Utils.checkNull(housenumber);
	}

	public String getTowncity() {
		return towncity;
	}

	public void setTowncity(String towncity) {
		this.towncity = Utils.checkNull(towncity);
	}

	public String getPostalcodezipcode() {
		return postalcodezipcode;
	}

	public void setPostalcodezipcode(String postalcodezipcode) {
		this.postalcodezipcode = Utils.checkNull(postalcodezipcode);
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = Utils.checkNull(countryname);
	}

	public String getCountystate() {
		return countystate;
	}
	public void setCountystate(String countystate) {
		this.countystate = Utils.checkNull(countystate);
	}

	public String getShipmentaddresstypecode() {
		return shipmentaddresstypecode;
	}

	public void setShipmentaddresstypecode(String shipmentaddresstypecode) {
		this.shipmentaddresstypecode = Utils.checkNull(shipmentaddresstypecode);
	}	
	
	public String getAddresscode() {
		return addresscode;
	}
	public void setAddresscode(String value) {
		this.addresscode = Utils.checkNull(value);
	}
	
	public String getPartytypeedi() {
		return partytypeedi;
	}
	public void setPartytypeedi(String value) {
		this.partytypeedi = Utils.checkNull(value);
	}
	
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String value) {
		this.countrycode = Utils.checkNull(value);
	}
}
