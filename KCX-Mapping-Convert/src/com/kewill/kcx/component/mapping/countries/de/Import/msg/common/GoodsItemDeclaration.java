package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the GoodsItemDeclaration Data with all Fields used in KIDS.
 * 				: EI20121029: new Fields for CH
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class GoodsItemDeclaration extends KCXMessage {

	private String 				msgName;
	private String 				itemNumber; 
	private String				text;
	private String 				procedureCode;
	private String 				dutyControlCode; 
	private String				supplementaryText;		
	private String				description;	
	private String				ean;
	private String 				amount;
	private String 				unitOfMeasurementAmount; 
	private String				qualifierAmount;		
	private String				countryOfOrigin;
	private String				euCode;	
	private String				placeOfIntroduction;
	private String				placeOfDeparture;
	private String				netMass;	
	private String				grossMass;
	private String				requestedPrivilege;
	private String				condition;
	private String				customsValue;
	private String				importTurnoverTax;		
	private String				processingFeeValueIncrease;
	private String				extraCostImportVAT;
	private String				tobaccoDutyCodeNumber;
	private String				additionsDeductionsDescription;	
	
	private CommodityCode 		commodityCode;
	private Quota				quota;
	private ImportPackage		importPackage;   
	private Invoice				invoice;
	private Payment				netPrice;
	private Payment				indirectPayment;
	private Statistic			statistic;
	private Statistic 			goodsQuantityCustoms;
	private Statistic 			goodsQuantityAgriculturalDuty;
	private Preference          preference; 
	
	private List<Document>		    documentList;
	private List<Salary>		    salaryList;
	private List<Excise>            exciseList;		  
	private List<AdditionalCosts>   additionsDeductionsList;	
	private List<SpecialStatement>  reductionStatementList;      
	private List<SpecialStatement>  specialValueStatementList;
	private List<SpecialStatement>  specialCaseStatementList;
	
	private CountrySpecificValues countrySpecificValues;  //EI20121030: CH V70 
	private WareHouse wahrehouse;      					  //EI20121030: CH V70 
	private Permit permit;      						  //EI20121030: CH V70 
	private NonCustomsLaw nonCustomsLaw; 				  //EI20121030: CH V70 
	private String vatValue;      						  //EI20121030: CH V70 
	private List<SpecialMention>  specialMentionList;     //EI20121030: CH V70 
	private Refinement refinement;      				  //EI20121030: CH V70 
	
	public GoodsItemDeclaration() {
		super();				
	}

	public GoodsItemDeclaration(XmlMsgScanner scanner) {
		super(scanner);
	}

	
	public GoodsItemDeclaration(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
 
	private enum EGoodsItemDeclaration {
		//KIDS:							UIDS:
		ItemNumber,
		Text,
		CommodityCode,	
		ProcedureCode,
		DutyControlCode,
		SupplementaryText,
		Description,			
		EAN,
		Amount,
		UnitOfMeasurementAmount,
		QualifierAmount,
		CountryOfOrigin,
		EUCode,
		Quota,
		PlaceOfIntroduction,
		PlaceOfDeparture,
		NetMass,
		GrossMass,					
		Package,	
		RequestedPrivilege,
		Condition,
		CustomsValue,
		ImportTurnoverTax,
		Invoice,
		NetPrice,
		IndirectPayment,
		Statistic,
		GoodsQuantityCustoms,
		GoodsQuantityAgriculturalDuty,
		ProcessingFeeValueIncrease,
		ExtraCostImportVAT,
		TobaccoDutyCodeNumber,
		Preference,
		Document,
		Salary,
		Excise,
		AdditionsDeductions,
		AdditionsDeductionsDescription,
		ReductionStatement,
		SpecialValueStatement,
		SpecialCaseStatement,
		Warehouse,
		Permit,		
		CountrySpecificValues,
		NonCustomsLaw,
		VATValue,
		SpecialMention,
		Refinement,
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EGoodsItemDeclaration) tag) {
			case CommodityCode:
				commodityCode = new CommodityCode(getScanner());
				commodityCode.parse(tag.name());	
				break;
			case Quota:
				quota = new Quota(getScanner());
				quota.parse(tag.name());
				break;
			case Package:		
				importPackage = new ImportPackage(getScanner());
				importPackage.parse(tag.name());					
				break;
			case Invoice:
				invoice = new Invoice(getScanner());
				invoice.parse(tag.name());		
				break;
			case NetPrice:
				netPrice = new Payment(getScanner());
				netPrice.parse(tag.name());							
				break;						
			case IndirectPayment:
				indirectPayment = new Payment(getScanner());
				indirectPayment.parse(tag.name());	
				break;
			case Statistic:
				statistic = new Statistic(getScanner());
				statistic.parse(tag.name());	
				break;
			case GoodsQuantityCustoms:
				goodsQuantityCustoms = new Statistic(getScanner());
				goodsQuantityCustoms.parse(tag.name());					
				break;						
			case GoodsQuantityAgriculturalDuty:
				goodsQuantityAgriculturalDuty = new Statistic(getScanner());
				goodsQuantityAgriculturalDuty.parse(tag.name());						
				break;
			case Preference:
				preference = new Preference(getScanner());
				preference.parse(tag.name());						
				break;
			case Document: 			
				Document document = new Document(getScanner());			
				document.parse(tag.name());
				addDocumentList(document);				
				break;
			case Salary: 			
				Salary salary = new Salary(getScanner());			
				salary.parse(tag.name());
				addSalaryList(salary);				
				break;
			case Excise: 			
				Excise excise = new Excise(getScanner());			
				excise.parse(tag.name());
				addExciseList(excise);				
				break;				
			case AdditionsDeductions: 			
				AdditionalCosts costs = new AdditionalCosts(getScanner());			
				costs.parse(tag.name());
				addAdditionalList(costs);				
				break;
			case ReductionStatement:
				SpecialStatement reductionStatement = new SpecialStatement(getScanner());
				reductionStatement.parse(tag.name());		
				addReductionStatementList(reductionStatement);
				break;				
			case SpecialValueStatement:
				SpecialStatement specialValueStatement = new SpecialStatement(getScanner());
				specialValueStatement.parse(tag.name());	
				addSpecialValueStatementList(specialValueStatement);
				break;	
			case SpecialCaseStatement:
				SpecialStatement specialCaseStatement = new SpecialStatement(getScanner());
				specialCaseStatement.parse(tag.name());		
				addSpecialCaseStatementList(specialCaseStatement);
				break;										
			case Warehouse:
				wahrehouse = new WareHouse(getScanner());			
				wahrehouse.parse(tag.name());
				break;
			case Permit:
				permit = new Permit(getScanner());			
				permit.parse(tag.name());
				break;	
			case CountrySpecificValues:
				countrySpecificValues = new CountrySpecificValues(getScanner());			
				countrySpecificValues.parse(tag.name());
				break;
			case NonCustomsLaw:
				nonCustomsLaw = new NonCustomsLaw(getScanner());			
				nonCustomsLaw.parse(tag.name());
				break;
			case SpecialMention:
				SpecialMention specialMention = new SpecialMention(getScanner());			
				specialMention.parse(tag.name());
				addSpecialMentionList(specialMention);
				break;
			case Refinement:
				refinement = new Refinement(getScanner());			
				refinement.parse(tag.name());
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EGoodsItemDeclaration) tag) {
			case ItemNumber:
				 setItemNumber(value);
				 break;	
			case Text:			
				 setText(value);
				 break;									
			case ProcedureCode:
				 setProcedureCode(value);
				 break;			
			case DutyControlCode:
				 setDutyControlCode(value);
				 break;
			case SupplementaryText:
				 setSupplementaryText(value);
				 break;
			case Description:			
				 setDescription(value);
				 break;	
			case EAN:
				 setEAN(value);
				 break;
			case Amount:
				 setAmount(value);
				 break;		
			case UnitOfMeasurementAmount:
				setUnitOfMeasurementAmount(value);
				break;
			case QualifierAmount:
				 setQualifierAmount(value);
				 break;					 
			case CountryOfOrigin:			
				 setCountryOfOrigin(value);
				 break;					 
			case EUCode:			
				 setEUCode(value);
				 break;					
			case PlaceOfIntroduction:
				 setPlaceOfIntroduction(value);
				 break;	
			case PlaceOfDeparture:
				 setPlaceOfDeparture(value);
				 break;	
			case NetMass:
				 setNetMass(value);
				 break;	
			case GrossMass:
				 setGrossMass(value);
				 break;					 
			case RequestedPrivilege:
				 setRequestedPrivilege(value);
				 break;							
			case Condition:
				 setCondition(value);
				 break;					
			case CustomsValue:				
				setCustomsValue(value);
				break;	
			case ImportTurnoverTax:				
				 setImportTurnoverTax(value);
				 break;				
			case ProcessingFeeValueIncrease:
				 setProcessingFeeValueIncrease(value);
				 break;	
			case ExtraCostImportVAT:
				 setExtraCostImportVAT(value);
				 break;	
			case TobaccoDutyCodeNumber:
				 setTobaccoDutyCodeNumber(value);
				 break;	
			case AdditionsDeductionsDescription:
				 setAdditionsDeductionsDescription(value);
				 break;	
			case VATValue:
				setVATValue(value);
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}

	public CommodityCode getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(CommodityCode commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getProcedureCode() {
		return procedureCode;
	}

	public void setProcedureCode(String procedureCode) {
		this.procedureCode = Utils.checkNull(procedureCode);
	}

	public String getDutyControlCode() {
		return dutyControlCode;
	}

	public void setDutyControlCode(String dutyControlCode) {
		this.dutyControlCode = Utils.checkNull(dutyControlCode);
	}

	public String getSupplementaryText() {
		return supplementaryText;
	}

	public void setSupplementaryText(String suplementaryText) {
		this.supplementaryText = Utils.checkNull(suplementaryText);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = Utils.checkNull(description);
	}

	public String getEAN() {
		return ean;
	}
	public void setEAN(String ean) {
		this.ean = Utils.checkNull(ean);
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = Utils.checkNull(amount);
	}

	public String getUnitOfMeasurementAmount() {
		return unitOfMeasurementAmount;
	}

	public void setUnitOfMeasurementAmount(String unitOfMeasurementAmount) {
		this.unitOfMeasurementAmount = Utils.checkNull(unitOfMeasurementAmount);
	}

	public String getQualifierAmount() {
		return qualifierAmount;
	}

	public void setQualifierAmount(String qualifierAmount) {
		this.qualifierAmount = Utils.checkNull(qualifierAmount);
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = Utils.checkNull(countryOfOrigin);
	}

	public String getEUCode() {
		return euCode;
	}

	public void setEUCode(String euCode) {
		this.euCode = Utils.checkNull(euCode);
	}

	public Quota getQuota() {
		return quota;
	}

	public void setQuota(Quota quota) {
		this.quota = quota;
	}

	public String getPlaceOfIntroduction() {
		return placeOfIntroduction;
	}

	public void setPlaceOfIntroduction(String placeOfIntroduction) {
		this.placeOfIntroduction = Utils.checkNull(placeOfIntroduction);
	}

	public String getPlaceOfDeparture() {
		return placeOfDeparture;
	}

	public void setPlaceOfDeparture(String placeOfDeparture) {
		this.placeOfDeparture = Utils.checkNull(placeOfDeparture);
	}

	public String getNetMass() {
		return netMass;
	}

	public void setNetMass(String netMass) {
		this.netMass = Utils.checkNull(netMass);
	}

	public String getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(String grossMass) {
		this.grossMass = Utils.checkNull(grossMass);
	}

	public ImportPackage getImportPackage() {
		return importPackage;
	}

	public void setImportPackage(ImportPackage importPackage) {
		this.importPackage = importPackage;
	}

	public String getRequestedPrivilege() {
		return requestedPrivilege;
	}

	public void setRequestedPrivilege(String requestedPrivilege) {
		this.requestedPrivilege = Utils.checkNull(requestedPrivilege);
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = Utils.checkNull(condition);
	}

	public String getCustomsValue() {
		return customsValue;
	}

	public void setCustomsValue(String customsValue) {
		this.customsValue = Utils.checkNull(customsValue);
	}

	public String getImportTurnoverTax() {
		return importTurnoverTax;
	}

	public void setImportTurnoverTax(String importTurnoverTax) {
		this.importTurnoverTax = Utils.checkNull(importTurnoverTax);
	}

	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Payment getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(Payment netPrice) {
		this.netPrice = netPrice;
	}

	public Payment getIndirectPayment() {
		return indirectPayment;
	}
	public void setIndirectPayment(Payment indirectPayment) {
		this.indirectPayment = indirectPayment;
	}

	public Statistic getStatistic() {
		return statistic;
	}
	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public Statistic getGoodsQuantityCustoms() {
		return goodsQuantityCustoms;
	}
	public void setGoodsQuantityCustoms(Statistic goodsQuantityCustoms) {
		this.goodsQuantityCustoms = goodsQuantityCustoms;
	}

	public Statistic getGoodsQuantityAgriculturalDuty() {
		return goodsQuantityAgriculturalDuty;
	}
	public void setGoodsQuantityAgriculturalDuty(Statistic argument) {
		this.goodsQuantityAgriculturalDuty = argument;
	}

	public String getProcessingFeeValueIncrease() {
		return processingFeeValueIncrease;
	}

	public void setProcessingFeeValueIncrease(String processingFeeValueIncrease) {
		this.processingFeeValueIncrease = Utils
				.checkNull(processingFeeValueIncrease);
	}

	public String getExtraCostImportVAT() {
		return extraCostImportVAT;
	}

	public void setExtraCostImportVAT(String extraCostImportVAT) {
		this.extraCostImportVAT = Utils.checkNull(extraCostImportVAT);
	}

	public String getTobaccoDutyCodeNumber() {
		return tobaccoDutyCodeNumber;
	}

	public void setTobaccoDutyCodeNumber(String tobaccoDutyCodeNumber) {
		this.tobaccoDutyCodeNumber = Utils.checkNull(tobaccoDutyCodeNumber);
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> list) {
		this.documentList = list;
	}
	public void addDocumentList(Document argument) {
		if (documentList == null) {
			documentList = new Vector<Document>();	
		}
		this.documentList.add(argument);
	}						

	public List<Salary> getSalaryList() {
		return salaryList;
	}
	public void setSalaryList(List<Salary> list) {
		this.salaryList = list;
	}
	public void addSalaryList(Salary argument) {
		if (salaryList == null) {
			salaryList = new Vector<Salary>();	
		}
		this.salaryList.add(argument);
	}
	
	public List<Excise> getExciseList() {
		return exciseList;
	}
	public void setExciseList(List<Excise> list) {
		this.exciseList = list;
	}
	public void addExciseList(Excise argument) {
		if (exciseList == null) {
			exciseList = new Vector<Excise>();	
		}
		this.exciseList.add(argument);
	}
	
	public List<AdditionalCosts> getAdditionsDeductionsList() {
		return additionsDeductionsList;
	}
	public void setAdditionsDeductionsList(
			List<AdditionalCosts> list) {
		this.additionsDeductionsList = list;
	}	
	public void addAdditionalList(AdditionalCosts argument) {
		if (additionsDeductionsList == null) {
			additionsDeductionsList = new Vector<AdditionalCosts>();	
		}
		this.additionsDeductionsList.add(argument);
	}
	
	public String getAdditionsDeductionsDescription() {
		return additionsDeductionsDescription;
	}

	public void setAdditionsDeductionsDescription(
			String additionsDeductionsDescription) {
		this.additionsDeductionsDescription = Utils
				.checkNull(additionsDeductionsDescription);
	}

	public List<SpecialStatement> getReductionStatementList() {
		return reductionStatementList;
	}
	public void setReductionStatementList(List<SpecialStatement> list) {
		this.reductionStatementList = list;
	}
	public void addReductionStatementList(SpecialStatement argument) {
		if (reductionStatementList == null) {
			reductionStatementList = new Vector<SpecialStatement>();	
		}
		this.reductionStatementList.add(argument);
	}

	public List<SpecialStatement> getSpecialValueStatementList() {
		return specialValueStatementList;
	}
	public void setSpecialValueStatementList(List<SpecialStatement> list)  {
		this.specialValueStatementList = list;
	}
	public void addSpecialValueStatementList(SpecialStatement argument) {
		if (specialValueStatementList == null) {
			specialValueStatementList = new Vector<SpecialStatement>();	
		}
		this.specialValueStatementList.add(argument);
	}

	public List<SpecialStatement> getSpecialCaseStatementList() {
		return specialCaseStatementList;
	}
	public void setSpecialCaseStatementList(List<SpecialStatement> list) {
		this.specialCaseStatementList = list;
	}
	public void addSpecialCaseStatementList(SpecialStatement argument) {
		if (specialCaseStatementList == null) {
			specialCaseStatementList = new Vector<SpecialStatement>();	
		}
		this.specialCaseStatementList.add(argument);
	}

	public void setWahrehouse(WareHouse party) {
		this.wahrehouse = party;
	}

	public WareHouse getWahrehouse() {
		return wahrehouse;
	}
	
	public void setPermit(Permit perm) {
		this.permit = perm;
	}

	public Permit getPermit() {
		return permit;
	}
	
	public void setCountrySpecificValues(CountrySpecificValues specific) {
		this.countrySpecificValues = specific;
	}

	public CountrySpecificValues getCountrySpecificValues() {
		return countrySpecificValues;
	}
		
	public void setNonCustomsLaw(NonCustomsLaw arg) {
		this.nonCustomsLaw = arg;
	}

	public NonCustomsLaw getNonCustomsLaw() {
		return nonCustomsLaw;
	}
	
	public String getVATValue() {
		return vatValue;
	}

	public void setVATValue(String customsValue) {
		this.vatValue = Utils.checkNull(customsValue);
	}
	
	public void setSpecialMentionList(List<SpecialMention>  arg) {
		this.specialMentionList = arg;
	}

	public List<SpecialMention> getSpecialMentionList() {
		return specialMentionList;
	}
	
	public void addSpecialMentionList(SpecialMention arg) {
		if (specialMentionList == null) {
			specialMentionList = new Vector<SpecialMention>();
		}
		specialMentionList.add(arg);
	}
	
	public void setRefinement(Refinement  arg) {
		this.refinement = arg;
	}

	public Refinement getRefinement() {
		return refinement;
	}
}
