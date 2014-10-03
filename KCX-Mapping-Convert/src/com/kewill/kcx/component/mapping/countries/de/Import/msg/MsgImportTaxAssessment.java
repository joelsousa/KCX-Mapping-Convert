package com.kewill.kcx.component.mapping.countries.de.Import.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Account;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Office;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: Import<br>.
 * Created		: 12.09.2011<br>
 * Description	: KIDS ImportTaxAssessment
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgImportTaxAssessment extends KCXMessage { 
	
	private String		msgName = "ImportTaxAssessment";
	private String		referenceNumber;
	private String		ucr;
	private String		registrationCode;	
	private String		customsOfficeOfRemedies;
	private String		dateOfRegistration;
	private String		totalAmountOfExemption;
	private String		totalAmountOfSecurity;
	private String		totalAmountOfCashSecurity;
	private String		totalAmountOfNonCashSecurity;
	private String		taxDeductionCode;
	private String		findings;
	private String		dutyType;
	
	private Traders     traders;
	private Office		customsOfficeOfClearance;
	private Office		customsOfficeOfPayment;
	private Address		customsOfficeOfClearanceAddress;
	private Address		customsOfficeOfPaymentAddress;	                    
	private Address		customsOfficeOfRemediesAddress;
	private Address		addressForInvoice;
	private String		accessCode;                      //EI20130305 for CH
	
	private List<Account> defermentAccountList;		      				
	private List<GoodsItemTaxAssessment> goodsItemList = new ArrayList<GoodsItemTaxAssessment>();
	
	public MsgImportTaxAssessment() {
		super();
	}
	public MsgImportTaxAssessment(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EImportTaxAssessment {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,				
		UCR,	
		RegistrationCode,	
		CustomsOfficeOfClearance,		
		CustomsOfficeOfRemedies,	
		CustomsOfficeOfPayment,	
		//Representative,	
		//Consignee,
		DateOfRegistration,	RegistrationDate,
		TotalAmountOfExemption,
		TotalAmountOfSecurity,
		TotalAmountOfCashSecurity,
		TotalAmountOfNonCashSecurity,
		TaxDeductionCode,
		Findings,
		DutyType,
		DefermentAccount,
		Traders,
		CustomsOfficeOfClearanceAddress,
		CustomsOfficeOfRemediesAddress,	
		CustomsOfficeOfPaymentAddress,	
		AddressForInvoice,
		AccessCode,
		GoodsItem;						
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EImportTaxAssessment) tag) {
			case Traders:
				traders = new Traders(getScanner());
				traders.parse(tag.name());
				break;						
			case CustomsOfficeOfClearance:
				customsOfficeOfClearance = new Office(getScanner());
				customsOfficeOfClearance.parse(tag.name());						
				break;
			case CustomsOfficeOfPayment:
				customsOfficeOfPayment = new Office(getScanner());
				customsOfficeOfPayment.parse(tag.name());						
				break;
			case CustomsOfficeOfRemediesAddress:
				customsOfficeOfRemediesAddress  = new Address(getScanner());
				customsOfficeOfRemediesAddress.parse(tag.name());						
				break;
			case CustomsOfficeOfClearanceAddress:
				customsOfficeOfClearanceAddress = new Address(getScanner());
				customsOfficeOfClearanceAddress.parse(tag.name());						
				break;
			case CustomsOfficeOfPaymentAddress:
				customsOfficeOfPaymentAddress = new Address(getScanner());
				customsOfficeOfPaymentAddress.parse(tag.name());						
				break;				
			case AddressForInvoice:                                          //EI20110923 new
				addressForInvoice = new Address(getScanner());
				addressForInvoice.parse(tag.name());				
				break;			
			case DefermentAccount:
				Account defermentAccount = new Account(getScanner());
				defermentAccount.parse(tag.name());		
				this.addDefermentAccountList(defermentAccount);
				break;					
			case GoodsItem:
				GoodsItemTaxAssessment item = new GoodsItemTaxAssessment(getScanner(), msgName);
				item.parse(tag.name());	
				this.addGoodsItemList(item);
				break;	
				
			default:
				return;
			}
		} else {
			switch ((EImportTaxAssessment) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			case UCR:			
				setUCR(value);
				break;
			case RegistrationCode:		
				setRegistrationCode(value);
				break;			
			case CustomsOfficeOfRemedies:
				setCustomsOfficeOfRemedies(value);
				break;
			case DateOfRegistration:	
			case RegistrationDate:                //EI20110927
				setDateOfRegistration(value);
				break;
			case TotalAmountOfExemption:
				setTotalAmountOfExemption(value);
				break;			
			case TotalAmountOfSecurity:
				setTotalAmountOfSecurity(value);
				break;			
			case TotalAmountOfCashSecurity:
				setTotalAmountOfCashSecurity(value);
				break;				
			case TotalAmountOfNonCashSecurity:
				setTotalAmountOfNonCashSecurity(value);
				break;
			case TaxDeductionCode:		
				setTaxDeductionCode(value);						
				break;	
			case Findings:
				setFindings(value);
				break;
			case DutyType:
				setDutyType(value);
				break;
			case AccessCode:
				setAccessCode(value);
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
			return EImportTaxAssessment.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getRegistrationCode() {
		return registrationCode;
	}
	public void setRegistrationCode(String value) {
		this.registrationCode = value;
	}
	public String getUCR() {
		return ucr;
	}
	public void setUCR(String value) {
		this.ucr = value;
	}
	public String getCustomsOfficeOfRemedies() {
		return customsOfficeOfRemedies;
	}
	public void setCustomsOfficeOfRemedies(String value) {
		this.customsOfficeOfRemedies = value;
	}
	
	public String getFindings() {
		return findings;
	}
	public void setFindings(String value) {
		this.findings = value;
	}
	
	public String getDutyType() {
		return dutyType;
	}
	public void setDutyType(String value) {
		this.dutyType = value;
	}
	
	public String getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(String value) {
		this.dateOfRegistration = value;
	}
				
	public String getTotalAmountOfExemption() {
		return totalAmountOfExemption;
	}
	public void setTotalAmountOfExemption(String value) {
		this.totalAmountOfExemption = value;
	}
	
	public String getTotalAmountOfSecurity() {
		return totalAmountOfSecurity;
	}
	public void setTotalAmountOfSecurity(String value) {
		this.totalAmountOfSecurity = value;
	}
	
	public String getTotalAmountOfCashSecurity() {
		return totalAmountOfCashSecurity;
	}
	public void setTotalAmountOfCashSecurity(String value) {
		this.totalAmountOfCashSecurity = value;
	}
	
	public String getTotalAmountOfNonCashSecurity() {
		return totalAmountOfNonCashSecurity;
	}
	public void setTotalAmountOfNonCashSecurity(String value) {
		this.totalAmountOfNonCashSecurity = value;
	}
	
	public String getTaxDeductionCode() {
		return taxDeductionCode;
	}
	public void setTaxDeductionCode(String document) {
		this.taxDeductionCode = document;
	}
		
	public Office getCustomsOfficeOfClearance() {
		return customsOfficeOfClearance;
	}
	public void setCustomsOfficeOfClearance(Office argument) {
		this.customsOfficeOfClearance = argument;
	}
		
	public Office getCustomsOfficeOfPayment() {
		return customsOfficeOfPayment;
	}
	public void setCustomsOfficeOfPayment(Office argument) {
		this.customsOfficeOfPayment = argument;
	}

	public Address getAddressForInvoice() {
		return addressForInvoice;
	}
	public void setAddressForInvoice(Address argument) {
		addressForInvoice = argument;
	}
	
	public Traders getTraders() {
		return traders;
	}
	public void setTraders(Traders argument) {
		this.traders = argument;
	}
			

	public List<Account> getDefermentAccountList() {
		return defermentAccountList;
	}
	public void setDefermentAccountList(List<Account> list) {
		this.defermentAccountList = list;
	}	
	public void addDefermentAccountList(Account argument) {
	   	if (this.defermentAccountList == null) {
	   		this.defermentAccountList = new Vector<Account>();
	   	}
	   	this.defermentAccountList.add(argument);
	}
	
	public Address getCustomsOfficeOfClearanceAddress() {
		return customsOfficeOfClearanceAddress;
	}
	public void setCustomsOfficeOfClearanceAddress(Address argument) {
		this.customsOfficeOfClearanceAddress = argument;
	}
	
	public Address getCustomsOfficeOfPaymentAddress() {
		return customsOfficeOfPaymentAddress;
	}
	public void setCustomsOfficeOfPaymentAddress(Address argument) {
		this.customsOfficeOfPaymentAddress = argument;
	}
	
	public Address getCustomsOfficeOfRemediesAddress() {
		return customsOfficeOfRemediesAddress;
	}
	public void setCustomsOfficeOfRemediesAddress(Address argument) {
		this.customsOfficeOfRemediesAddress = argument;
	}
	
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String value) {
		this.accessCode = value;
	}
	
	public List<GoodsItemTaxAssessment> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(List<GoodsItemTaxAssessment> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}
	public void addGoodsItemList(GoodsItemTaxAssessment argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemTaxAssessment>();
	   	}
	   	this.goodsItemList.add(argument);
	}
	
}
