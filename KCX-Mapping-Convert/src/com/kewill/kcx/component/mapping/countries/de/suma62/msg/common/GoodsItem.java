package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
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
public class GoodsItem extends KCXMessage {

	private String 		msgName;
	private String 		itemNumber;
	private String 		custodianTIN;    //EI20130527-TODO: in xsd geaendert in CT_TINType
	private String 		disposalTIN;     //EI20130527-TODO: in xsd geaendert in CT_TINType
	private String 		briefCargoDescription;
	private String 		dateOfApplication;
	private String 		dateOfReception;
	private String 		grossMass;
	private Address		placeOfCustody;			//Address-City
	private String 		placeOfCustodyCode;
	private String 		rangeOfGoodsCode;
	private Packages	packaging;
	private NewCustodyValues	newCustodyValues;	
	private ReferencedSpecification 	referencedSpecification;	
	private ItemExtension 		itemExtension;
	
	private Trader custodian;               //EI20130527-TODO: in xsd geaendert in CT_AddressType
	private Trader disposal;                //EI20130527-TODO: in xsd geaendert in CT_AddressType
	private String countryOfDispatch;		//EI20121211	
	private String countryOfDestination;	//EI20121211		
	private String destinationCode;	   		//EI20121211
	private String customsStatusOfGoods;	//EI20121211		
	private String confirmationCode;	    //EI20121211
	
	
	private enum EGoodsItemM {
		//KIDS							//UIDS
		ItemNumber,
		CustodianTIN,
		DisposalTIN,
		BriefCargoDescription,
		ConfirmationCode,
		CountryOfDispatch,
		CountryOfDestination,
		CustomsStatusOfGoods,
		DestinationCode,
		GrossMass,
		Packages,					Packaging,			//in KIDS in Packages umbennant						
		PlaceOfCustody,             //Address-City
		PlaceOfCustodyCode,
		RangeOfGoods,
		RangeOfGoodsCode,
		ReferencedSpecification,	
		RoutingOfCustoms,
		Custodian,
		Disposal,	
		ItemExtension, ItemExtensions,		
		
		NewCustodyValues,				
			
		DateOfApplication,
		DateOfReception,
		
	}
	
	public GoodsItem() {
		super();  
	}

    public GoodsItem(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
    public GoodsItem(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EGoodsItemM) tag) {
			
  			case PlaceOfCustody: 
  				placeOfCustody = new Address(getScanner());
  				placeOfCustody.parse(tag.name());	
  				break;
  			
  			case Packaging: 
  			case Packages:              //EI20130701
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
  			
  			case ItemExtension:
  			case ItemExtensions:
  					itemExtension = new ItemExtension(getScanner());
  					itemExtension.parse(tag.name());
  					break;
  			case Custodian:                     //EI20130201 
  					custodian = new Trader(getScanner());
  					custodian.parse(tag.name());
  					break;
  			case Disposal:                     //EI20130201 
  				disposal = new Trader(getScanner());
  				disposal.parse(tag.name());
  				break;
  					
  			default:
  					return;
  			}
  		} else {
  			switch ((EGoodsItemM) tag) {
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
  				case ConfirmationCode:                   //EI20130201
  					setConfirmationCode(value);
  					break;
  				case CountryOfDispatch:                  //EI20130201
  					setCountryOfDispatch(value);
  					break;
  				case CountryOfDestination:                //EI20130201 
  					setCountryOfDestination(value);
  					break;
  				case CustomsStatusOfGoods:               //EI20130201   
  					setCustomsStatusOfGoods(value);
  					break;
  				case DestinationCode:                     //EI20130201   
  					setDestinationCode(value);
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
  			return EGoodsItemM.valueOf(token);
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

	public Trader getCustodian() {
		return custodian;
	}
	public void setCustodian(Trader trader) {
		this.custodian = trader;
	}
	
	public Trader getDisposal() {
		return disposal;
	}
	public void setDisposal(Trader trader) {
		this.disposal = trader;
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

	public Address getPlaceOfCustody() {
		return placeOfCustody;
	}

	public void setPlaceOfCustody(Address placeOfCustodyCity) {
		this.placeOfCustody = placeOfCustodyCity;
	}
	
	public String getCountryOfDestination() {
		return countryOfDestination;
	}
	public void setCountryOfDestination(String value) {
		this.countryOfDestination = value;
	}
	
	public String getCountryOfDispatch() {
		return countryOfDispatch;
	}
	public void setCountryOfDispatch(String value) {
		this.countryOfDispatch = value;
	}
	
	public String getDestinationCode() {
		return destinationCode;
	}
	public void setDestinationCode(String value) {
		this.destinationCode = value;
	}
	
	public String getCustomsStatusOfGoods() {
		return customsStatusOfGoods;
	}
	public void setCustomsStatusOfGoods(String value) {
		this.customsStatusOfGoods = value;
	}
	
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String value) {
		this.confirmationCode = value;
	}
	
}
