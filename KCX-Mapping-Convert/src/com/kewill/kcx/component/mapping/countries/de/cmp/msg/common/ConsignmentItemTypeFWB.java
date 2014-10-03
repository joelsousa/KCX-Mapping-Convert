package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: FWB-ConsignmentItemType.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ConsignmentItemTypeFWB extends KCXMessage {
	
	private String sequenceNumeric;
    private ArrayList<String> typeCodeList;
	private String grossWeightMeasure;
	private String grossVolumeMeasure;
	private String packageQuantity;
	private String pieceQuantity;
	private String volumetricFactor;
	private String information;
	private TransportCargo natureIdentificationTransportCargo;
	private Location originCountry;
	private ArrayList<TransportEquipment> associatedUnitLoadTransportEquipmentList;
	private ArrayList<PackageType> transportLogisticsPackagelist;
	private ChargeType applicableFreightRateServiceCharge;
	private Location specifiedRateCombinationPointLocation;
	private RateClassType applicableUnitLoadDeviceRateClass;	
	       
    private enum EFwbConsignmentItemType {
    	SequenceNumeric,
    	TypeCode,
    	GrossWeightMeasure,
    	GrossVolumeMeasure,
    	PackageQuantity,
    	PieceQuantity,
    	VolumetricFactor,
    	Information,
    	NatureIdentificationTransportCargo,
    	OriginCountry,
    	AssociatedUnitLoadTransportEquipment,
    	TransportLogisticsPackage,
    	ApplicableFreightRateServiceCharge,
    	SpecifiedRateCombinationPointLocation,
    	ApplicableUnitLoadDeviceRateClass;
    }

    public ConsignmentItemTypeFWB() {
	      	super();	       
    }
    
    public ConsignmentItemTypeFWB(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EFwbConsignmentItemType) tag) {
    			case NatureIdentificationTransportCargo:
    				natureIdentificationTransportCargo = new TransportCargo(getScanner());
    				natureIdentificationTransportCargo.parse(tag.name());        				
    				break;
    				
    			case OriginCountry:
    				originCountry = new Location(getScanner());
    				originCountry.parse(tag.name());        				
    				break;
    				
    			case AssociatedUnitLoadTransportEquipment:
    				TransportEquipment equ = new TransportEquipment(getScanner());
					equ.parse(tag.name());    
					addAssociatedUnitLoadTransportEquipmentList(equ);
					break;
					
    			case TransportLogisticsPackage:
    				PackageType pack = new PackageType(getScanner());
					pack.parse(tag.name());    
					addTransportLogisticsPackageList(pack);
					break;
					
    			case ApplicableFreightRateServiceCharge:
    				applicableFreightRateServiceCharge = new ChargeType(getScanner());
    				applicableFreightRateServiceCharge.parse(tag.name());        				
					break;
				
    			case SpecifiedRateCombinationPointLocation:
    				specifiedRateCombinationPointLocation = new Location(getScanner());
    				specifiedRateCombinationPointLocation.parse(tag.name());        				
					break;
					
    			case ApplicableUnitLoadDeviceRateClass:
    				applicableUnitLoadDeviceRateClass = new RateClassType(getScanner());
    				applicableUnitLoadDeviceRateClass.parse(tag.name());        				
				break;
				
    			default:
    					return;
    			}
    		} else {

    			switch ((EFwbConsignmentItemType) tag) {
    				case SequenceNumeric :
    					setSequenceNumeric(value);
    					break;
    					
    				case TypeCode :
    					addTypeCodeList(value);
    					break;
    					
    				case GrossWeightMeasure :
    					setGrossWeightMeasure(value);
    					break;    					
    					
    				case GrossVolumeMeasure :
    					setGrossVolumeMeasure(value);
    					break;
    					
    				case PackageQuantity :
    					setPackageQuantity(value);
    					break;
    					
    				case PieceQuantity :
    					setPieceQuantity(value);
    					break;
    					
    				case VolumetricFactor :
    					setVolumetricFactor(value);
    					break;
    					
    				case Information :
    					setInformation(value);
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
    			return EFwbConsignmentItemType.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }
	    
	public String getSequenceNumeric() {
		return sequenceNumeric;
	}
	public void setSequenceNumeric(String sequenceNumeric) {
		this.sequenceNumeric = sequenceNumeric;
	}

	public ArrayList<String> getTypeCodeList() {
		return typeCodeList;
	}
	public void setTypeCodeList(ArrayList<String> list) {
		this.typeCodeList = list;
	}
	public void addTypeCodeList(String value) {
		if (typeCodeList == null) {
			typeCodeList = new ArrayList<String>();
		}
		this.typeCodeList.add(value);;
	}
		
	public String getGrossWeightMeasure() {
		return grossWeightMeasure;
	}

	public void setGrossWeightMeasure(String grossWeightMeasure) {
		this.grossWeightMeasure = grossWeightMeasure;
	}

	public String getGrossVolumeMeasure() {
		return grossVolumeMeasure;
	}

	public void setGrossVolumeMeasure(String grossVolumeMeasure) {
		this.grossVolumeMeasure = grossVolumeMeasure;
	}

	public String getPackageQuantity() {
		return packageQuantity;
	}

	public void setPackageQuantity(String packageQuantity) {
		this.packageQuantity = packageQuantity;
	}

	public String getPieceQuantity() {
		return pieceQuantity;
	}

	public void setPieceQuantity(String pieceQuantity) {
		this.pieceQuantity = pieceQuantity;
	}

	public String getVolumetricFactor() {
		return volumetricFactor;
	}

	public void setVolumetricFactor(String volumetricFactor) {
		this.volumetricFactor = volumetricFactor;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public TransportCargo getNatureIdentificationTransportCargo() {
		return natureIdentificationTransportCargo;
	}

	public void setNatureIdentificationTransportCargo(
			TransportCargo natureIdentificationTransportCargo) {
		this.natureIdentificationTransportCargo = natureIdentificationTransportCargo;
	}

	public Location getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(Location originCountry) {
		this.originCountry = originCountry;
	}

	public ArrayList<PackageType> getTransportLogisticsPackagelist() {
		return transportLogisticsPackagelist;
	}

	public void setTransportLogisticsPackagelist(
			ArrayList<PackageType> transportLogisticsPackagelist) {
		this.transportLogisticsPackagelist = transportLogisticsPackagelist;
	}

	public ChargeType getApplicableFreightRateServiceCharge() {
		return applicableFreightRateServiceCharge;
	}

	public void setApplicableFreightRateServiceCharge(
			ChargeType applicableFreightRateServiceCharge) {
		this.applicableFreightRateServiceCharge = applicableFreightRateServiceCharge;
	}

	public Location getSpecifiedRateCombinationPointLocation() {
		return specifiedRateCombinationPointLocation;
	}

	public void setSpecifiedRateCombinationPointLocation(
			Location specifiedRateCombinationPointLocation) {
		this.specifiedRateCombinationPointLocation = specifiedRateCombinationPointLocation;
	}

	public RateClassType getApplicableUnitLoadDeviceRateClass() {
		return applicableUnitLoadDeviceRateClass;
	}

	public void setApplicableUnitLoadDeviceRateClass(
			RateClassType applicableUnitLoadDeviceRateClass) {
		this.applicableUnitLoadDeviceRateClass = applicableUnitLoadDeviceRateClass;
	}

	public ArrayList<TransportEquipment> getAssociatedUnitLoadTransportEquipment() {
		return associatedUnitLoadTransportEquipmentList;
	}
	public void setAssociatedUnitLoadTransportEquipment(ArrayList<TransportEquipment> list) {
		this.associatedUnitLoadTransportEquipmentList = list;
	}           
	public void addAssociatedUnitLoadTransportEquipmentList(TransportEquipment type) {
		if (associatedUnitLoadTransportEquipmentList == null) {
			associatedUnitLoadTransportEquipmentList = new ArrayList<TransportEquipment>();
		}
		this.associatedUnitLoadTransportEquipmentList.add(type);
	}
	
	public ArrayList<PackageType> getTransportLogisticsPackageList() {
		return transportLogisticsPackagelist;
	}
	public void setTransportLogisticsPackageList(ArrayList<PackageType> list) {
		this.transportLogisticsPackagelist = list;
	}	
	public void addTransportLogisticsPackageList(PackageType type) {
		if (transportLogisticsPackagelist == null) {
			transportLogisticsPackagelist = new ArrayList<PackageType>();
		}
		this.transportLogisticsPackagelist.add(type);
	}
	
	public boolean isEmpty() {
		//todo
		return false;
	}

}
