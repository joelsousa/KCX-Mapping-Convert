package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.AddressType;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.06.2013<br>
 * Description	: GoodsItem Data Of ReExport.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class GoodsItemControl extends KCXMessage {

	private String msgName;
	private String itemDescription;
	private String itemNumber;
	private String referenceNumber;	
	private String registrationNumber;	
	
	private ArrayList<Packages>	packageList;
	private ReferencedSpecification referencedSpecification;	
	private ControlDecision controlDecision;
	private TIN 		custodianTIN;
	private AddressType	custodianAddress;
	private Party		custodian;
	
	private enum EGoodsItemM {
		//KIDS							//UIDS
		ItemNumber,
		ItemDescription,
		ReferenceNumber, 
		RegistrationNumber, 
		Packages,	Package,	Packaging,
		CustodianTIN,               CustodianTin,
		CustodianAddress,           Custodian,
		ReferencedSpecification, 		    
		ControlDecision,      		
	}
	
	public GoodsItemControl() {
		super();  
	}

    public GoodsItemControl(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
    public GoodsItemControl(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EGoodsItemM) tag) {
  			case Packages:
  			case Packaging: 
  			case Package:
  				Packages packaging = new Packages(getScanner());
  				packaging.parse(tag.name());
  				addPackagesList(packaging);
  				break;
  			
  			case ReferencedSpecification:
				referencedSpecification = new ReferencedSpecification(getScanner());
				referencedSpecification.parse(tag.name());
				break;
				
  			case ControlDecision:  			
  				controlDecision = new ControlDecision(getScanner());
  				controlDecision.parse(tag.name());
  				break;
  			
  			case CustodianTIN:
  				custodianTIN = new TIN(getScanner());
  				custodianTIN.parse(tag.name());
  				if (custodian == null) {
  					custodian = new Party();
  				}
  				custodian.setPartyTIN(custodianTIN);
  				break;
  				
  			case CustodianAddress:
  				custodianAddress = new AddressType(getScanner());
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
				
  			default:
  					return;
  			}
  		} else {
  			switch ((EGoodsItemM) tag) {
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;
  					
  				case ItemDescription:
  					setItemDescription(value);
  					break;
  					
  				case ReferenceNumber:
  					setReferenceNumber(value);
  					break;
  					
  				case RegistrationNumber:                     
  					setRegistrationNumber(value);
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
  			return EGoodsItemM.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}
	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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
	
	public ControlDecision getControlDecision() {
		return controlDecision;
	}
	public void setControlDecision(ControlDecision controlDecision) {
		this.controlDecision = controlDecision;
	}

	private void setUidsCustodianTin(String value) {   
		if (Utils.isStringEmpty(value)) {
			return;
		}
		this.custodianTIN = new TIN();
		this.custodianTIN.setTIN(value);
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

	public ArrayList<Packages> getPackageList() {
		return packageList;
	}

	public void setPackageList(ArrayList<Packages> packageList) {
		this.packageList = packageList;
	}
		
}
