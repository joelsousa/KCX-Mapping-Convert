package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: TransportEquipment.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TransportEquipment extends KCXMessage {

	private String id;
	private String grossWeightMeasure;
	private String grossVolumeMeasure;
	private String tareWeightMeasure;
	private String quantity;	
	private String buildTypeCode;
	private String characteristicCode;
	private String characteristic;
	private String usedCapacityCode;	
	private String operationalStatusCode;
	private String loadingRemark;
	private LocationEvent postioningEvent;
	private PartyType operatingParty;	
	private TransportCarriage onCarriageTransportMovement;
	private Seal affixedLogisticsSeal; 
	
	private enum ETransportEquipment {
		ID,
		GrossWeightMeasure,
		GrossVolumeMeasure,	
		TareWeightMeasure,
		PieceQuantity, LoadedPackageQuantity,
		BuildTypeCode,
		CharacteristicCode,
		Characteristic,
		UsedCapacityCode,
		OperationalStatusCode,
		LoadingRemark,
		PositioningEvent,
		OperatingParty,		
		OnCarriageTransportMovement,
		AffixedLogisticsSeal;
	    }        

	    public TransportEquipment() {
		      	super();	       
	    }
	    
	    public TransportEquipment(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((ETransportEquipment) tag) {
	    			
	    			case PositioningEvent:   
	    				postioningEvent = new LocationEvent(getScanner());
	    				postioningEvent.parse(tag.name());					
    					break;   
    					
    				case OperatingParty:
    					operatingParty = new PartyType(getScanner());
    					operatingParty.parse(tag.name());        				
    					break;
    					
    				case OnCarriageTransportMovement:
    					onCarriageTransportMovement = new TransportCarriage(getScanner());
    					onCarriageTransportMovement.parse(tag.name());        				
    					break;
    					
    				case AffixedLogisticsSeal:   
    					affixedLogisticsSeal = new Seal(getScanner());
    					affixedLogisticsSeal.parse(tag.name());					
    					break;  
    					
    				default:
	    					return;
	    			}
	    		} else {

	    			switch ((ETransportEquipment) tag) {
	    				case ID:
	    					setId(value);
	    					break;	
	    					
	    				case GrossWeightMeasure:   
	    					setGrossWeightMeasure(value);    					
	    					break; 
	    					
	    				case GrossVolumeMeasure:   
	    					setGrossVolumeMeasure(value);    					
	    					break;  
	    					
	    				case TareWeightMeasure:
	    					setTareWeightMeasure(value);    					
	    					break;  
	    					
	    				case PieceQuantity:   
	    				case LoadedPackageQuantity:
	    					setQuantity(value);    					
	    					break;  
	    					
	    				case BuildTypeCode:   
	    					setBuildTypeCode(value);    					
	    					break;
	    					
	    				case CharacteristicCode:   
	    					setCharacteristicCode(value);    					
	    					break;
	    					
	    				case Characteristic:   
	    					setCharacteristic(value);    					
	    					break;
	    					
	    				case UsedCapacityCode:   
	    					setUsedCapacityCode(value);    					
	    					break;
	    					
	    				case OperationalStatusCode:   
	    					setOperationalStatusCode(value);    					
	    					break;  
	    					
	    				case LoadingRemark:   
	    					setLoadingRemark(value);    					
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
	    		return ETransportEquipment.valueOf(token);
	    	} catch (IllegalArgumentException e) {
	    		return null;
	    	}
	    }	      	

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getGrossWeightMeasure() {
			return grossWeightMeasure;
		}

		public void setGrossWeightMeasure(String value) {
			this.grossWeightMeasure = value;
		}

		public String getGrossVolumeMeasure() {
			return grossVolumeMeasure;
		}

		public void setGrossVolumeMeasure(String value) {
			this.grossVolumeMeasure = value;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String value) {
			this.quantity = value;
		}

		public String getBuildTypeCode() {
			return buildTypeCode;
		}

		public void setBuildTypeCode(String value) {
			this.buildTypeCode = value;
		}

		public String getCharacteristicCode() {
			return characteristicCode;
		}

		public void setCharacteristicCode(String value) {
			this.characteristicCode = value;
		}

		public String getUsedCapacityCode() {
			return usedCapacityCode;
		}

		public void setUsedCapacityCode(String value) {
			this.usedCapacityCode = value;
		}

		public String getOperationalStatusCode() {
			return operationalStatusCode;
		}

		public void setOperationalStatusCode(String value) {
			this.operationalStatusCode = value;
		}

		public String getLoadingRemark() {
			return loadingRemark;
		}

		public void setLoadingRemark(String value) {
			this.loadingRemark = value;
		}

		public LocationEvent getPostioningEvent() {
			return postioningEvent;
		}

		public void setPostioningEvent(LocationEvent postioningEvent) {
			this.postioningEvent = postioningEvent;
		}

		public PartyType getOperatingParty() {
			return operatingParty;
		}

		public void setOperatingParty(PartyType operatingParty) {
			this.operatingParty = operatingParty;
		}

		public TransportCarriage getOnCarriageTransportMovement() {
			return onCarriageTransportMovement;
		}

		public void setOnCarriageTransportMovement(
				TransportCarriage onCarriageTransportMovement) {
			this.onCarriageTransportMovement = onCarriageTransportMovement;
		}

		public String getCharacteristic() {
			return characteristic;
		}
		public void setCharacteristic(String value) {
			this.characteristic = value;
		}

		public Seal getAffixedLogisticsSeal() {
			return affixedLogisticsSeal;
		}
		public void setAffixedLogisticsSeal(Seal affixedLogisticsSeal) {
			this.affixedLogisticsSeal = affixedLogisticsSeal;
		}

		public String getTareWeightMeasure() {
			return tareWeightMeasure;
		}
		public void setTareWeightMeasure(String value) {
			this.tareWeightMeasure = value;
		}

		public boolean isEmpty() {
			return Utils.isStringEmpty(id) && Utils.isStringEmpty(grossWeightMeasure)  && 
			//TODO
			Utils.isStringEmpty(quantity) && 
			postioningEvent == null  && operatingParty == null && 
			onCarriageTransportMovement == null && affixedLogisticsSeal == null ; 
		}
		
}
