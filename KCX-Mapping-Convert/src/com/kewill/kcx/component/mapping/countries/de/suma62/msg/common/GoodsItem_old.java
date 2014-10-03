package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Contains the GoodsItem Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class GoodsItem_old extends KCXMessage {

	private String 		msgName;
	private String 		itemNumber;
	private String 		custodianTIN;
	private String 		disposalTIN;
	private String 		briefCargoDescription;
	private String 		dateOfApplication;
	private String 		dateOfReception;
	private String 		grossMass;
	private Address		placeOfCustodyAddress;		//Address
	private Address		placeOfCustodyCity;			//City
	private String 		placeOfCustodyCode;
	private String 		rangeOfGoodsCode;
	private Packages	packaging;
	private NewCustodyValues	newCustodyValues;
	private Address		custodian;
	private ReferencedSpecification 	referencedSpecification;
	private ReferencedSpecification 	referencedSpecification2;
	private ItemExtension 		itemExtension;
	
	private enum EGoodsItem {
		//KIDS							//UIDS
		ItemNumber,
		CustodianTIN,
		DisposalTIN,
		BriefCargoDescription,
		DateOfApplication,
		DateOfReception,
		GrossMass,
		placeOfCustody,				//City
		PlaceOfCustody,             //Address
		PlaceOfCustodyCode,
		RangeOfGoodsCode,
		ReferencedSpecification,
		ReferencedSpecification2,
		Packaging,
		NewCustodyValues,
		Custodian,
		ItemExtension;
	}
	
	public GoodsItem_old() {
		super();  
	}

    public GoodsItem_old(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
    public GoodsItem_old(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EGoodsItem) tag) {
			case Custodian:
					custodian = new Address(getScanner());
					custodian.parse(tag.name());
				break;
  			case PlaceOfCustody: 
  					placeOfCustodyCity = new Address(getScanner());
  					placeOfCustodyCity.parse(tag.name());	
  				break;
  			case placeOfCustody: 
  					placeOfCustodyAddress  = new Address(getScanner());
					placeOfCustodyAddress.parse(tag.name());	
				break;
  			case Packaging: 
  					packaging = new Packages(getScanner());
  					packaging.parse(tag.name());
  				break;
  			case NewCustodyValues:
  					newCustodyValues = new NewCustodyValues(getScanner());
  					newCustodyValues.parse(tag.name());
  				break;
  			case ReferencedSpecification:
					referencedSpecification = new ReferencedSpecification(getScanner());
					referencedSpecification.parse(tag.name());
				break;
  			case ReferencedSpecification2:
  					referencedSpecification2 = new ReferencedSpecification(getScanner());
  					referencedSpecification2.parse(tag.name());
  				break;
  			case  ItemExtension:
  					itemExtension = new ItemExtension(getScanner());
  					itemExtension.parse(tag.name());
  					break;
  			default:
  					return;
  			}
  		} else {
  			switch ((EGoodsItem) tag) {
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;
  				case CustodianTIN:
  					setCustodianTIN(value);
  					break;
  				case DisposalTIN:
  					setDisposalTIN(value);
  					break;
  				case BriefCargoDescription:
  					setBriefCargoDescription(value);
  					break;
  				case DateOfApplication:
  					setDateOfApplication(value);
  					break;
  				case DateOfReception:
  					setDateOfReception(value);
  					break;
  				case GrossMass:
  					setGrossMass(value);
  					break;
  				case PlaceOfCustodyCode:
  					setPlaceOfCustodyCode(value);
  					break;
  				case RangeOfGoodsCode:
  					setRangeOfGoodsCode(value);
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
  			return EGoodsItem.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getCustodianTIN() {
		return custodianTIN;
	}

	public void setCustodianTIN(String custodianTIN) {
		this.custodianTIN = Utils.checkNull(custodianTIN);
	}

	public String getDisposalTIN() {
		return disposalTIN;
	}

	public void setDisposalTIN(String disposalTIN) {
		this.disposalTIN = Utils.checkNull(disposalTIN);
	}

	public String getBriefCargoDescription() {
		return briefCargoDescription;
	}

	public void setBriefCargoDescription(String briefCargoDescription) {
		this.briefCargoDescription = Utils.checkNull(briefCargoDescription);
	}

	public String getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(String dateOfApplication) {
		this.dateOfApplication = Utils.checkNull(dateOfApplication);
	}

	public String getDateOfReception() {
		return dateOfReception;
	}

	public void setDateOfReception(String dateOfReception) {
		this.dateOfReception = Utils.checkNull(dateOfReception);
	}

	public String getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(String grossMass) {
		this.grossMass = Utils.checkNull(grossMass);
	}

	public Address getPlaceOfCustody() {
		return placeOfCustodyAddress;
	}

	public void setPlaceOfCustody(Address placeOfCustody) {
		this.placeOfCustodyAddress = placeOfCustody;
	}

	public String getPlaceOfCustodyCode() {
		return placeOfCustodyCode;
	}

	public void setPlaceOfCustodyCode(String placeOfCustodyCode) {
		this.placeOfCustodyCode = Utils.checkNull(placeOfCustodyCode);
	}

	public String getRangeOfGoodsCode() {
		return rangeOfGoodsCode;
	}

	public void setRangeOfGoodsCode(String rangeOfGoodsCode) {
		this.rangeOfGoodsCode = Utils.checkNull(rangeOfGoodsCode);
	}

	public Packages getPackaging() {
		return packaging;
	}

	public void setPackaging(Packages packaging) {
		this.packaging = packaging;
	}

	public Address getCustodian() {
		return custodian;
	}

	public void setCustodian(Address custodian) {
		this.custodian = custodian;
	}

	public ReferencedSpecification getReferencedSpecification() {
		return referencedSpecification;
	}

	public void setReferencedSpecification(ReferencedSpecification referencedSpecification) {
		this.referencedSpecification = referencedSpecification;
	}

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public NewCustodyValues getNewCustodyValues() {
		return newCustodyValues;
	}

	public void setNewCustodyValues(NewCustodyValues newCustodyValues) {
		this.newCustodyValues = newCustodyValues;
	}

	public ItemExtension getItemExtension() {
		return itemExtension;
	}

	public void setItemExtension(ItemExtension itemExtension) {
		this.itemExtension = itemExtension;
	}

	public Address getPlaceOfCustodyCity() {
		return placeOfCustodyCity;
	}

	public void setPlaceOfCustodyCity(Address placeOfCustodyCity) {
		this.placeOfCustodyCity = placeOfCustodyCity;
	}

	public ReferencedSpecification getReferencedSpecification2() {
		return referencedSpecification2;
	}

	public void setReferencedSpecification2(
			ReferencedSpecification referencedSpecification2) {
		this.referencedSpecification2 = referencedSpecification2;
	}

	public void setPlaceOfCustodyCity(String vwobez) {
		if (!Utils.isStringEmpty(vwobez)) {
			placeOfCustodyCity = new Address();
			
			placeOfCustodyCity.setCity(vwobez);
		}
		
	}
}
