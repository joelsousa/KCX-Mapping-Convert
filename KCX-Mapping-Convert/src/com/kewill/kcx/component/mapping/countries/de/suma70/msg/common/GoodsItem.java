package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.AddressType;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 24.05.2013<br>
 * Description	: Contains the GoodsItem Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 */

public class GoodsItem extends KCXMessage {

	private String 		msgName;
	private String 		itemNumber;
	
	private String itemDescription;
	private String custodyDeadline;
	private String dateTimeOfReceipt;
	private String grossMass;
	private String countryOfDispatch;	
	private String countryOfDestination;		
	private String destinationCode;
	private String customsStatusOfGoods;		
	private String confirmationCode;	
	private String routingOfCustoms;
	private String placeOfCustodyCode;
	private String rangeOfGoodsCode;
	private String rangeOfGoodsDescription;  //EI20130821
	private String freezoneFlag;
		
	private Party		disposal;
	private Party		custodian;
	private TIN			placeOfCustodyTIN;	
	private Address		placeOfCustody;			//Address-City	
	private NewCustodyValues newCustodyValues;     	
	
	private ArrayList<Packages>	packageList;
	private ReferencedSpecification referencedSpecification;	
	private ItemExtension itemExtension;
	private CustomsNotification customsNotification;
	
	private enum EGoodsItemM {
		//KIDS							//UIDS
		ItemNumber,			
		DisposalTIN,					DisposalTin,
		DisposalAddress,				Disposal,
		CustodianTIN,					CustodianTin,
		CustodianAddress,				Custodian,
		ItemDescription,          BriefCargoDescription,
		ConfirmationCode,
		CustomsStatusOfGoods,
		CountryOfDestination,
		CountryOfDispatch,
		DestinationPlace,				DestinationCode,
		GrossMass,
		Packages, Package,				Packaging, 
		PlaceOfCustody, //Address-City: 
		PlaceOfCustodyCode,
		PlaceOfCustodyTIN,
		RangeOfGoodsCode,     
		RangeOfGoodsDescription,
		ReferencedSpecification,  
		RoutingOfCustoms,           //nicht in NotificationOfPresentationConfirmed   				 	
		NewCustodyValues,		    //used only in GoodsReleasedInternal
		ItemExtension, ItemExtensions,		
		CustodyDeadline,         	//used only in GoodsReleasedInternal
		DateTimeOfReceipt,			//used only in GoodsReleasedInternal	
		CustomsNotification,
		FreezoneFlag,
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
  			
  			case DisposalTIN:
  				TIN disposalTIN = new TIN(getScanner());
  				disposalTIN.parse(tag.name());
  				if (disposal == null) {
  					disposal = new Party();
  				}
  				disposal.setPartyTIN(disposalTIN);
  				break;  			
  			case DisposalAddress:			
  				AddressType disposalAddress = new AddressType(getScanner());
  				disposalAddress.parse(tag.name());
  				if (disposal == null) {
  					disposal = new Party();
  				}
  				disposal.setAddress(disposalAddress.getAddress());
				break;
			case Disposal:   //kann nur von UIDS-seite kommen
			    Trader disTrader = new Trader(getScanner());						
			    disTrader.parse(tag.name());
			    disposal = mapTraderToParty(disTrader);
				break;
				
  			case CustodianTIN:
  				TIN custodianTIN = new TIN(getScanner());
  				custodianTIN.parse(tag.name());
  				if (custodian == null) {
  					custodian = new Party();
  				}
  				custodian.setPartyTIN(custodianTIN);
  				break;
  			case CustodianAddress:
  				AddressType custodianAddress = new AddressType(getScanner());
  				custodianAddress.parse(tag.name());	
  				if (custodian == null) {
  					custodian = new Party();
  				}
  				custodian.setAddress(custodianAddress.getAddress());
  				break;
  			case Custodian:
  				 Trader cusTrader = new Trader(getScanner());						
  				cusTrader.parse(tag.name());
  	   		    custodian = mapTraderToParty(cusTrader);
				break;
  			case PlaceOfCustodyTIN:
  				
  				break;
  			case PlaceOfCustody:
  				placeOfCustody = new Address(getScanner());
  				placeOfCustody.parse(tag.name());  				
  				break;  			
  				
  			case Packages:
  			case Package:
  			case Packaging: 
  				Packages packaging = new Packages(getScanner());
  				packaging.parse(tag.name());
  				addPackagesList(packaging);
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
  				
  			case NewCustodyValues:
  				newCustodyValues = new NewCustodyValues(getScanner());
  				newCustodyValues.parse(tag.name());
  				break;
  				
  			case CustomsNotification:
  				customsNotification = new CustomsNotification(getScanner());
  				customsNotification.parse(tag.name());
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
  				case CustodianTin:
  					setUidsCustodianTin(value);
  					break;
  				case DisposalTIN:
  				case DisposalTin:
  					setUidsDisposalTin(value);
  					break;  	
  					
  				case ItemDescription:
  					setItemDescription(value);
  					break;
  					
  				case CustodyDeadline:
  					setCustodyDeadline(value);
  					break;
  					
  				case DateTimeOfReceipt:
  					setDateTimeOfReceipt(value);
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
  					
  				case RangeOfGoodsDescription:
  					setRangeOfGoodsDescription(value);
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
  				case DestinationPlace:					  //EI20130828 umbenannt	
  					setDestinationPlace(value);
  					break;
  			
  				case FreezoneFlag:
  					setFreezoneFlag(value);
  					break;
  					
  				case RoutingOfCustoms:
  					setRoutingOfCustoms(value);
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
		this.itemNumber = itemNumber;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String briefCargoDescription) {
		this.itemDescription = briefCargoDescription;
	}

	public String getCustodyDeadline() {
		return custodyDeadline;
	}
	public void setCustodyDeadline(String dateOfApplication) {
		this.custodyDeadline = dateOfApplication;
	}

	public String getDateTimeOfReceipt() {
		return dateTimeOfReceipt;
	}
	public void setDateTimeOfReceipt(String dateOfReception) {
		this.dateTimeOfReceipt = dateOfReception;
	}

	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String grossMass) {
		this.grossMass = grossMass;
	}

	public String getPlaceOfCustodyCode() {
		return placeOfCustodyCode;
	}
	public void setPlaceOfCustodyCode(String placeOfCustodyCode) {
		this.placeOfCustodyCode = placeOfCustodyCode;
	}

	public String getRangeOfGoodsCode() {
		return rangeOfGoodsCode;
	}
	public void setRangeOfGoodsCode(String rangeOfGoodsCode) {
		this.rangeOfGoodsCode = rangeOfGoodsCode;
	}
	
	public String getRangeOfGoodsDescription() {
		return rangeOfGoodsDescription;
	}
	public void setRangeOfGoodsDescription(String description) {
		this.rangeOfGoodsDescription = description;
	}

	public ArrayList<Packages> getPackagesList() {
		return packageList;
	}
	public void setPackagesList(ArrayList<Packages> list) {
		this.packageList = list;
	}
	public void addPackagesList(Packages packaging) {
		if (packageList == null) {
			packageList = new ArrayList<Packages>();
		}
		packageList.add(packaging);
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
		this.msgName = msgName;
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
	public void setPlaceOfCustody(Address custody) {
		this.placeOfCustody = custody;
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
	
	public String getDestinationPlace() {
		return destinationCode;
	}
	public void setDestinationPlace(String value) {
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

	public String getRoutingOfCustoms() {
		return routingOfCustoms;
	}
	public void setRoutingOfCustoms(String routingOfCustoms) {
		this.routingOfCustoms = routingOfCustoms;
	}
		
	private void setUidsDisposalTin(String value) {   
		if (Utils.isStringEmpty(value)) {
			return;
		}
		TIN disposalTIN = new TIN();
		disposalTIN.setTIN(value);
		this.disposal = new Party();
		disposal.setPartyTIN(disposalTIN);
	}
	
	public Party getDisposal() {			
		return this.disposal;		
	}
	public void setDisposal(Party disposal) {
		this.disposal = disposal;
	}

	private void setUidsCustodianTin(String value) {   
		if (Utils.isStringEmpty(value)) {
			return;
		}
		TIN custodianTIN = new TIN();
		custodianTIN.setTIN(value);
		this.custodian = new Party();
		custodian.setPartyTIN(custodianTIN);
	}
	public Party getCustodian() {		
		return this.custodian;
	}
	public void setCustodian(Party custodian) {
		this.custodian = custodian;
	}
	
	private Party mapTraderToParty(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party = new Party();
		if (!Utils.isStringEmpty(trader.getTIN())) {
			TIN partyTIN = new TIN();
			partyTIN.setTIN(trader.getTIN());
			partyTIN.setBO(trader.getBranch());
			partyTIN.setCustomerIdentifier(trader.getCustomerID());
			partyTIN.setIdentificationType(trader.getTINType());
			party.setPartyTIN(partyTIN);
		}
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());	
		
		return party;
	}

	public TIN getPlaceOfCustodyTIN() {
		return placeOfCustodyTIN;
	}
	public void setPlaceOfCustodyTIN(TIN placeOfCustodyTIN) {
		this.placeOfCustodyTIN = placeOfCustodyTIN;
	}	

	public String getFreezoneFlag() {
		return freezoneFlag;
	}
	public void setFreezoneFlag(String freezoneFlag) {
		this.freezoneFlag = freezoneFlag;
	}

	public CustomsNotification getCustomsNotification() {
		return customsNotification;
	}
	public void setCustomsNotification(CustomsNotification entrySuma) {
		this.customsNotification = entrySuma;
	}
	
}