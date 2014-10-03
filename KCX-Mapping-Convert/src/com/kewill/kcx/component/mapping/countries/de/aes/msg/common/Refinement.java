/*
 * Function    : Refinement/KIDS
 * Titel       :
 * Date        : 26.04.2011
 * Author      : Kewill CSF 
 * Description : Contains Refinement Data
 * 			   : with all Fields used in KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : AK
 * Date        :
 * Label       : 26.04.2011
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Refinement<br>
 * Erstellt		: 26.04.2011<br>
 * Beschreibung	: Contains the Refinement Data with all Fields used in KIDS.
 * 
 * @author 		: AK
 * @version 1.0.00
 */
public class Refinement extends KCXMessage {

	private String direction;
	private String refinementType;
	private String processType;
	private String billingType;
	private String temporaryAdmission;
	private String positionType;
	private String freePass;           //EI20121105 new for CH
	private String exportValueAmount;          //EI20121105 new for CH
	private String exportValueCurrency;          //EI20121105 new for CH
	private String exportExchangeRate;          //EI20121105 new for CH
	private String exportExchangeFactor;          //EI20121105 new for CH
	private String labourCostAmount;          //EI20121105 new for CH
	private String labourCostCurrency;          //EI20121105 new for CH
	private String labourCostExchangeRate;          //EI20121105 new for CH
	private String labourCostFactor;          //EI20121105 new for CH
	private String freightToBorderAmount;          //EI20121105 new for CH
	private String freightToBorderCurrency;          //EI20121105 new for CH
	private String freightToBorderExchangeRate;          //EI20121105 new for CH
	private String freightToBorderFactor;          //EI20121105 new for CH
	private String newMaterialValue;          //EI20121105 new for CH
	private String newMaterialCurrency;          //EI20121105 new for CH
	private String newMaterialExchangeRate;          //EI20121105 new for CH
	private String newMaterialFactor;          //EI20121105 new for CH
	private String officeCode;          //EI20121105 new for CH
	
	private XMLEventReader parser	= null;
	private boolean debug = false;
		
	private enum ERefinementTags {
		Direction,
		RefinementType,
		ProcessType,
		BillingType,
		TemporaryAdmission,
		PositionType,
		FreePass,		
		ExportValueAmount,
		ExportValueCurrency,
		ExportExchangeRate,
		ExportExchangeFactor,
		LabourCostAmount,
		LabourCostCurrency,
		LabourCostExchangeRate,
		LabourCostFactor,
		FreightToBorderAmount,
		FreightToBorderCurrency,
		FreightToBorderExchangeRate,
		FreightToBorderFactor,
		NewMaterialValue,
		NewMaterialCurrency,
		NewMaterialExchangeRate,
		NewMaterialFactor,
		OfficeCode,
	}
	 	 
	 public Refinement(XMLEventReader parser) {
			super(parser);
			this.parser = parser;
	 }
	 
	 public Refinement(XmlMsgScanner scanner) {
	  		super(scanner);
	 }
	 
	 public Refinement() {  
			super();			
	 }
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((ERefinementTags) tag) {
			default:
				return;
			}
		} else {				
			switch ((ERefinementTags) tag) {
				case Direction:
					setDirection(value);
					break;
					
				case RefinementType:
					setRefinementType(value);
					break;
					
				case ProcessType:
					setProcessType(value);
					break;
					
				case BillingType:
					setBillingType(value);
					break;

				case TemporaryAdmission:
					setTemporaryAdmission(value);
					break;
					
				case PositionType:
					setPositionType(value);
					break;
				case FreePass:
					setFreePass(value);
					break;
				case ExportValueAmount:
					setExportValueAmount(value);
					break;
				case ExportValueCurrency:
					setExportValueCurrency(value);
					break;
				case ExportExchangeRate:
					setExportExchangeRate(value);
					break;
				case ExportExchangeFactor:
					setExportExchangeFactor(value);
					break;
				case LabourCostAmount:
					setLabourCostAmount(value);
					break;
				case LabourCostCurrency:
					setLabourCostCurrency(value);
					break;
				case LabourCostExchangeRate:
					setLabourCostExchangeRate(value);
					break;
				case LabourCostFactor:
					setLabourCostFactor(value);
					break;
				case FreightToBorderAmount:
					setFreightToBorderAmount(value);
					break;
				case FreightToBorderCurrency:
					setFreightToBorderCurrency(value);
					break;
				case FreightToBorderExchangeRate:
					setFreightToBorderExchangeRate(value);
					break;
				case FreightToBorderFactor:
					setFreightToBorderFactor(value);
					break;
				case NewMaterialValue:
					setNewMaterialValue(value);
					break;
				case NewMaterialCurrency:
					setNewMaterialCurrency(value);
					break;
				case NewMaterialExchangeRate:
					setNewMaterialExchangeRate(value);
					break;
				case NewMaterialFactor:
					setNewMaterialFactor(value);
					break;
				case OfficeCode:
				setOfficeCode(value);
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
			return ERefinementTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getDirection() {
		return direction;	
	}

	public void setDirection(String direction) {
		this.direction = Utils.checkNull(direction);
	}

	public String getRefinementType() {
		return refinementType;	
	}

	public void setRefinementType(String refinementType) {
		this.refinementType = Utils.checkNull(refinementType);
	}

	public String getProcessType() {
		return processType;	
	}

	public void setProcessType(String processType) {
		this.processType = Utils.checkNull(processType);
	}

	public String getBillingType() {
		return billingType;	
	}

	public void setBillingType(String billingType) {
		this.billingType = Utils.checkNull(billingType);
	}

	public String getTemporaryAdmission() {
		return temporaryAdmission;	
	}

	public void setTemporaryAdmission(String temporaryAdmission) {
		this.temporaryAdmission = Utils.checkNull(temporaryAdmission);
	}

	public String getPositionType() {
		return positionType;	
	}

	public void setPositionType(String positionType) {
		this.positionType = Utils.checkNull(positionType);
	}
	
	public String getFreePass() {
		return freePass;
	}

	public void setFreePass(String freePass) {
		this.freePass = freePass;
	}

	public String getExportValueAmount() {
		return exportValueAmount;
	}

	public void setExportValueAmount(String exportValueAmount) {
		this.exportValueAmount = exportValueAmount;
	}

	public String getExportValueCurrency() {
		return exportValueCurrency;
	}

	public void setExportValueCurrency(String exportValueCurrency) {
		this.exportValueCurrency = exportValueCurrency;
	}

	public String getExportExchangeRate() {
		return exportExchangeRate;
	}

	public void setExportExchangeRate(String exportExchangeRatet) {
		this.exportExchangeRate = exportExchangeRatet;
	}

	public String getExportExchangeFactor() {
		return exportExchangeFactor;
	}

	public void setExportExchangeFactor(String exportExchangeFactor) {
		this.exportExchangeFactor = exportExchangeFactor;
	}

	public String getLabourCostAmount() {
		return labourCostAmount;
	}

	public void setLabourCostAmount(String labourCostAmount) {
		this.labourCostAmount = labourCostAmount;
	}

	public String getLabourCostCurrency() {
		return labourCostCurrency;
	}

	public void setLabourCostCurrency(String labourCostCurrency) {
		this.labourCostCurrency = labourCostCurrency;
	}

	public String getLabourCostExchangeRate() {
		return labourCostExchangeRate;
	}

	public void setLabourCostExchangeRate(String labourCostExchangeRate) {
		this.labourCostExchangeRate = labourCostExchangeRate;
	}

	public String getLabourCostFactor() {
		return labourCostFactor;
	}

	public void setLabourCostFactor(String labourCostFactor) {
		this.labourCostFactor = labourCostFactor;
	}

	public String getFreightToBorderAmount() {
		return freightToBorderAmount;
	}

	public void setFreightToBorderAmount(String freightToBorderAmount) {
		this.freightToBorderAmount = freightToBorderAmount;
	}

	public String getFreightToBorderCurrency() {
		return freightToBorderCurrency;
	}

	public void setFreightToBorderCurrency(String freightToBorderCurrency) {
		this.freightToBorderCurrency = freightToBorderCurrency;
	}

	public String getFreightToBorderExchangeRate() {
		return freightToBorderExchangeRate;
	}

	public void setFreightToBorderExchangeRate(String freightToBorderExchangeRate) {
		this.freightToBorderExchangeRate = freightToBorderExchangeRate;
	}

	public String getFreightToBorderFactor() {
		return freightToBorderFactor;
	}

	public void setFreightToBorderFactor(String frreightToBorderFactor) {
		this.freightToBorderFactor = frreightToBorderFactor;
	}

	public String getNewMaterialValue() {
		return newMaterialValue;
	}

	public void setNewMaterialValue(String value) {
		newMaterialValue = value;
	}

	public String getNewMaterialCurrency() {
		return newMaterialCurrency;
	}

	public void setNewMaterialCurrency(String value) {
		newMaterialCurrency = value;
	}
	
	public String getNewMaterialExchangeRate() {
		return newMaterialExchangeRate;
	}

	public void setNewMaterialExchangeRate(String value) {
		newMaterialExchangeRate = value;
	}

	public String getNewMaterialFactor() {
		return newMaterialFactor;
	}

	public void setNewMaterialFactor(String value) {
		newMaterialFactor = value;
	}
	
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String value) {
		officeCode = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.direction) && Utils.isStringEmpty(this.refinementType) && 
		        Utils.isStringEmpty(this.processType) && Utils.isStringEmpty(this.billingType) &&
		        Utils.isStringEmpty(this.temporaryAdmission) && Utils.isStringEmpty(this.positionType));  		
	}
}
