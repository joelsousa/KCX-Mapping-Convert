package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the GoodsItemTaxAssessment Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class GoodsItemTaxAssessment extends KCXMessage {

	private String 	msgName;
	private String  itemNumber; 
	private String 	examinationText;
	private String 	additionalProof; 
	private String 	timeLimitAdditionalProof;
	private String 	messageOfDischarge; 
	private String 	acceptanceDate;
	private String 	releaseDate; 
	private String 	grantedBenefitCode;
	private String 	examinationCode; 
	private String 	dischargeCode;
	private String 	acceptanceCode; 
	private String 	deviantAssessmentCode;
	private String 	vatValue; 
	private String 	amountOfGarantee;
	private String 	exchangeRate; 
	private String 	customsValue;
	private String 	findings; 
	
	private List<Duties> dutiesList;
	private List<Document> documentList;
	
	public GoodsItemTaxAssessment() {
		super();				
	}

	public GoodsItemTaxAssessment(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
 
	private enum EGoodsItemDeclaration {
		//KIDS:							UIDS:
		ItemNumber,
		ExaminationText,
		AdditionalProof,
		TimeLimitAdditionalProof,
		MessageOfDischarge,
		AcceptanceDate,
		ReleaseDate,
		GrantedBenefitCode,
		ExaminationCode,
		DischargeCode,
		AcceptanceCode,
		DeviantAssessmentCode,
		VATValue,
		AmountOfGarantee,
		ExchangeRate,
		CustomsValue,		
		Findings,
		Duties,
		Document;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EGoodsItemDeclaration) tag) {	
			case Duties:
				Duties duty = new Duties(getScanner());
				duty.parse(tag.name());
				addDutiesList(duty);
				 break;	
			case Document:
				Document document = new Document(getScanner());
				document.parse(tag.name());
				addDocumentList(document);
				 break;	
			default:
				return;
			}
	    } else {
	    	switch ((EGoodsItemDeclaration) tag) {
			case ItemNumber:
				 setItemNumber(value);
				 break;											
			case ExaminationText:
				 setExaminationText(value);
				 break;	
			case AdditionalProof:
				 setAdditionalProof(value);
				 break;	
			case TimeLimitAdditionalProof:
				 setTimeLimitAdditionalProof(value);
				 break;											
			case MessageOfDischarge:
				 setMessageOfDischarge(value);
				 break;	
			case AcceptanceDate:
				 setAcceptanceDate(value);
				 break;	
			case ReleaseDate:
				 setReleaseDate(value);
				 break;
			case GrantedBenefitCode:
				 setGrantedBenefitCode(value);
				 break;
			case ExaminationCode:
				 setExaminationCode(value);
				 break;
			case DischargeCode:
				 setDischargeCode(value);
				 break;
			case AcceptanceCode:
				 setAcceptanceCode(value);
				 break;
			case DeviantAssessmentCode:
				 setDeviantAssessmentCode(value);
				 break;				
			case VATValue:
				 setVATValue(value);
				 break;
			case AmountOfGarantee:
				 setAmountOfGarantee(value);
				 break;
			case ExchangeRate:
				 setExchangeRate(value);
				 break;
			case CustomsValue:
				 setCustomsValue(value);
				 break;
			case Findings:
				 setFindings(value);
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
  			return EGoodsItemDeclaration.valueOf(token);
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

	public String getExaminationText() {
		return examinationText;
	}
	public void setExaminationText(String value) {
		this.examinationText = Utils.checkNull(value);
	}
	
	public String getAdditionalProof() {
		return additionalProof;
	}
	public void setAdditionalProof(String value) {
		this.additionalProof = Utils.checkNull(value);
	}
	
	public String getTimeLimitAdditionalProof() {
		return timeLimitAdditionalProof;
	}
	public void setTimeLimitAdditionalProof(String value) {
		this.timeLimitAdditionalProof = Utils.checkNull(value);
	}
	
	public String getMessageOfDischarge() {
		return messageOfDischarge;
	}
	public void setMessageOfDischarge(String value) {
		this.messageOfDischarge = Utils.checkNull(value);
	}
	
	public String getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(String value) {
		this.acceptanceDate = Utils.checkNull(value);
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String value) {
		this.releaseDate = Utils.checkNull(value);
	}
	
	public String getGrantedBenefitCode() {
		return grantedBenefitCode;
	}
	public void setGrantedBenefitCode(String value) {
		this.grantedBenefitCode = Utils.checkNull(value);
	}
	
	public String getExaminationCode() {
		return examinationCode;
	}
	public void setExaminationCode(String value) {
		this.examinationCode = Utils.checkNull(value);
	}
	
	public String getDischargeCode() {
		return dischargeCode;
	}
	public void setDischargeCode(String value) {
		this.dischargeCode = Utils.checkNull(value);
	}
	
	public String getAcceptanceCode() {
		return acceptanceCode;
	}
	public void setAcceptanceCode(String value) {
		this.acceptanceCode = Utils.checkNull(value);
	}
	
	public String getDeviantAssessmentCode() {
		return deviantAssessmentCode;
	}
	public void setDeviantAssessmentCode(String value) {
		this.deviantAssessmentCode = Utils.checkNull(value);
	}
	
	public String getVATValue() {
		return vatValue;
	}
	public void setVATValue(String value) {
		this.vatValue = Utils.checkNull(value);
	}
	
	public String getAmountOfGarantee() {
		return amountOfGarantee;
	}
	public void setAmountOfGarantee(String value) {
		this.amountOfGarantee = Utils.checkNull(value);
	}
		
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String value) {
		this.exchangeRate = Utils.checkNull(value);
	}
	
	public String getCustomsValue() {
		return customsValue;
	}
	public void setCustomsValue(String value) {
		this.customsValue = Utils.checkNull(value);
	}
	
	public String getFindings() {
		return findings;
	}
	public void setFindings(String value) {
		this.findings = Utils.checkNull(value);
	}
	
	public List<Duties> getDutiesList() {
		return dutiesList;
	}
	public void setDutiesList(List<Duties> list) {
		this.dutiesList = list;
	}
	public void addDutiesList(Duties argument) {
	   	if (this.dutiesList == null) {
	   		this.dutiesList = new Vector<Duties>();
	   	}
	   	this.dutiesList.add(argument);
	}		
	
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> list) {
		this.documentList = list;
	}
	public void addDocumentList(Document argument) {
	   	if (this.documentList == null) {
	   		this.documentList = new Vector<Document>();
	   	}
	   	this.documentList.add(argument);
	}		
	

}
