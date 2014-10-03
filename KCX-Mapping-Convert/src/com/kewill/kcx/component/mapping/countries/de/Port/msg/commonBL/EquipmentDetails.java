package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class EquipmentDetails extends KCXMessage {
		
	private String idNumber;
	private String isoCode;
	private String typeAndSizeText;
	private String equipmentSuppliedBy;
	private String fullEmptyIndicator;
	private String movementTypeCode;
	private String movementType;
	private String equipmentPlan;
	private String weightPerUnitKilogram;
	private String tareWeightKilogram;
	private String grossWeightKilogram;
	private String grossVolumeCubicMetre;
	private List<Seals> sealsList;
	private String customsDeclarationNumber;
	private List<AttachedEquipment> attachedEquipmentList;
	
	private enum EEquipmentQualifier {	
		EquipmentIdentificationNumber,
		TypeandSizeISOCode,
		TypeandSizeText,
		EquipmentSuppliedBy,
		FullEmptyIndicator,
		MovementTypeCode,
		MovementType,
		EquipmentPlan,
		WeightPerUnitKilogram,
		TareWeightKilogram,
		GrossWeightKilogram,
		GrossVolumeCubicMetre,
		Seals,
		CustomsDeclarationNumber,
		AttachedEquipment;			       		
   }	

	public EquipmentDetails() {
		super();  
	}

	public EquipmentDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEquipmentQualifier) tag) {  
  				case Seals:
  					Seals seals = new Seals(getScanner());  	
  					seals.parse(tag.name());
  					addSealsList(seals);
					break; 
  				case AttachedEquipment:
  					AttachedEquipment attachedEquipment = new AttachedEquipment(getScanner());  	
  					attachedEquipment.parse(tag.name());  
  					addAttachedEquipment(attachedEquipment);
  					break;  				
				default:
  					break;
  			}
  		} else {
  			switch((EEquipmentQualifier) tag) {  
  			case EquipmentIdentificationNumber:
  				setEquipmentIdentificationNumber(value);
  				break;
  			case TypeandSizeISOCode:
  				setTypeAndSizeISOCode(value);
  				break;  				
  			case TypeandSizeText:
  				setTypeAndSizeText(value);
  				break;
  			case EquipmentSuppliedBy:
  				setEquipmentSuppliedBy(value);
  				break;
  			case FullEmptyIndicator:
  				setFullEmptyIndicator(value);
  				break;
  			case MovementTypeCode:
  				setMovementTypeCode(value);
  				break;
  			case MovementType:
  				setMovementType(value);
  				break;  				
  			case EquipmentPlan:
  				setEquipmentPlan(value);
  				break;
  			case WeightPerUnitKilogram:
  				setWeightPerUnitKilogram(value);
  				break;
  			case TareWeightKilogram:
  				setTareWeightKilogram(value);
  				break;
  			case GrossWeightKilogram:
  				setGrossWeightKilogram(value);
  				break;
  			case GrossVolumeCubicMetre:
  				setGrossVolumeCubicMetre(value);
  				break;
  			case CustomsDeclarationNumber:
  				setCustomsDeclarationNumber(value);
  				break;
  			default:
  				break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EEquipmentQualifier.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public List<Seals> getSealsList() {
		return sealsList;
	}
	public void setSealsList(List<Seals> list) {
		this.sealsList = list;
	}
	public void addSealsList(Seals value) {
		if (sealsList == null) {
			sealsList = new ArrayList<Seals>();
		}
		this.sealsList.add(value);
	}
		
	public List<AttachedEquipment> getAttachedEquipmentList() {
		return attachedEquipmentList;
	}  
	public void setAttachedEquipment(List<AttachedEquipment> value) {
		this.attachedEquipmentList = value;
	}
	public void addAttachedEquipment(AttachedEquipment value) {
		if (attachedEquipmentList == null) {
			attachedEquipmentList = new ArrayList<AttachedEquipment>();
		}
		this.attachedEquipmentList.add(value);
	}
	
	public String getEquipmentIdentificationNumber() {
		return idNumber;
	}    
	public void setEquipmentIdentificationNumber(String value) {
		this.idNumber = value;
	}
	
	public String getTypeAndSizeISOCode() {
		return isoCode;
	}    
	public void setTypeAndSizeISOCode(String value) {
		this.isoCode = value;
	}
	
	public String getTypeAndSizeText() {
		return typeAndSizeText;
	}    
	public void setTypeAndSizeText(String value) {
		this.typeAndSizeText = value;
	}
	
	public String getEquipmentSuppliedBy() {
		return equipmentSuppliedBy;
	}    
	public void setEquipmentSuppliedBy(String value) {
		this.equipmentSuppliedBy = value;
	}
	
	public String getFullEmptyIndicator() {
		return fullEmptyIndicator;
	}    
	public void setFullEmptyIndicator(String value) {
		this.fullEmptyIndicator = value;
	}
	
	public String getMovementTypeCode() {
		return movementTypeCode;
	}    
	public void setMovementTypeCode(String value) {
		this.movementTypeCode = value;
	}
	
	public String getMovementType() {
		return movementType;
	}    
	public void setMovementType(String value) {
		this.movementType = value;
	}
	
	public String getEquipmentPlan() {
		return equipmentPlan;
	}    
	public void setEquipmentPlan(String value) {
		this.equipmentPlan = value;
	}
	
	public String getWeightPerUnitKilogram() {
		return weightPerUnitKilogram;
	}    
	public void setWeightPerUnitKilogram(String value) {
		this.weightPerUnitKilogram = value;
	}
	
	public String getTareWeightKilogram() {
		return tareWeightKilogram;
	}    
	public void setTareWeightKilogram(String value) {
		this.tareWeightKilogram = value;
	}
	
	public String getGrossWeightKilogram() {
		return grossWeightKilogram;
	}    
	public void setGrossWeightKilogram(String value) {
		this.grossWeightKilogram = value;
	}
	
	public String getGrossVolumeCubicMetre() {
		return grossVolumeCubicMetre;
	}    
	public void setGrossVolumeCubicMetre(String value) {
		this.grossVolumeCubicMetre = value;
	}
	
	public String getCustomsDeclarationNumber() {
		return customsDeclarationNumber;
	}    
	public void setCustomsDeclarationNumber(String value) {
		this.customsDeclarationNumber = value;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(idNumber) && Utils.isStringEmpty(isoCode) &&
		Utils.isStringEmpty(grossWeightKilogram) && Utils.isStringEmpty(tareWeightKilogram) &&
		Utils.isStringEmpty(grossVolumeCubicMetre) && Utils.isStringEmpty(fullEmptyIndicator) &&
		Utils.isStringEmpty(customsDeclarationNumber) && sealsList == null;
	}	
}

