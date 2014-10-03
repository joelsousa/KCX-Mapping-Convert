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

public class DangerousGoodsDetails extends KCXMessage {
	
	private String hazardCodeIdentification;	
	private String hazardItemPageNumber;	
	private String hazardCodeVersionNumber;	
	private String harmonizedSystemCommodityCode;
	private String undgNumber;
	private ShipmentFlashpoint shipmentFlashpoint;
	private String levelOfDanger;
	private String emsNumber;
	private String mfag;	
	private List<String> labelMarkingList;	
	private TechnicalName technicalName;
	private TextAAC additionalInformation;
	private List<Contact> emergencyContactList;	
	private String grossWeightKilogram;
	private String netWeightKilogram;
	private String netNetWeightKilogram;	
	private String netExplosiveWeightKilogram;	
	private String radioactiveIndexOfTransportNumber;
	private String radioactivityBecquerel;
	private String radioactivityCurie;	

	private enum ECarriage {	
		HazardCodeIdentification,
		HazardItemPageNumber,		
		HazardCodeVersionNumber,
		HarmonizedSystemCommodityCode,
		UNDGNumber,		
		ShipmentFlashpoint,
		LevelOfDanger,
		EMSNumber,
		MFAG,		
		LabelMarking,
		TechnicalName,
		AdditionalInformation,
		EmergencyContact,
		GrossWeightKilogram,
		NetWeightKilogram,
		NetNetWeightKilogram,
		NetExplosiveWeightKilogram,
		RadioactiveIndexOfTransportNumber,
		RadioactivityBecquerel,		
		RadioactivityCurie;
   }	

	public DangerousGoodsDetails() {
		super();  
	}

	public DangerousGoodsDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case ShipmentFlashpoint:
  					ShipmentFlashpoint sfl = new ShipmentFlashpoint(getScanner());  	
  					sfl.parse(tag.name());
					break;	
  				case TechnicalName:
  	  				TechnicalName tn = new TechnicalName(getScanner());  	
  					tn.parse(tag.name());
  	  				break;
  				case AdditionalInformation:
  					additionalInformation = new TextAAC(getScanner());  	
  					additionalInformation.parse(tag.name());
					break; 
  				case EmergencyContact:
  					Contact emergencyContact = new Contact(getScanner());  	
  					emergencyContact.parse(tag.name());
  					addEmergencyContactList(emergencyContact);
	  				break;
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   
  			case HazardCodeIdentification:
  				setHazardCodeIdentification(value);
  				break;
  			case HazardItemPageNumber:
  				setHazardItemPageNumber(value);
  				break;
  			case HazardCodeVersionNumber:
  				setHazardCodeVersionNumber(value);
  				break;
  			case HarmonizedSystemCommodityCode:
  				setHarmonizedSystemCommodityCode(value);
  				break;
  			case UNDGNumber:
  				setUNDGNumber(value);
  				break;
  			case LevelOfDanger:
  				setLevelOfDanger(value);
  				break;  			
  			case EMSNumber:
  				setEMSNumber(value);
  				break;
  			case MFAG:
  				setMFAG(value);
  				break;
  			case LabelMarking:
  				addLabelMarkingList(value);
  				break;  			
  			case GrossWeightKilogram:
  				setGrossWeightKilogram(value);
  				break;
  			case NetWeightKilogram:
  				setNetWeightKilogram(value);
  				break;   			
  			case NetNetWeightKilogram:
  				setNetNetWeightKilogram(value);
  				break; 
  			case NetExplosiveWeightKilogram:
  				setNetWeightKilogram(value);
  				break;
  			case RadioactiveIndexOfTransportNumber:
  				setRadioactiveIndexOfTransportNumber(value);
  				break;   				
  			case RadioactivityBecquerel:
  				setRadioactivityBecquerel(value);
  				break;   				
  			case RadioactivityCurie:
  				setRadioactivityCurie(value);
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
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
  	public String getHazardCodeIdentification() {
		return hazardCodeIdentification;
	}    
	public void setHazardCodeIdentification(String value) {
		this.hazardCodeIdentification = value;
	}
  	
	public String getHazardItemPageNumber() {
		return hazardItemPageNumber;
	}    
	public void setHazardItemPageNumber(String value) {
		this.hazardItemPageNumber = value;
	}
		
	public String getHazardCodeVersionNumber() {
		return hazardCodeVersionNumber;
	}    
	public void setHazardCodeVersionNumber(String value) {
		this.hazardCodeVersionNumber = value;
	}
	
	public String getUNDGNumber() {
		return undgNumber;
	}    
	public void setUNDGNumber(String value) {
		this.undgNumber = value;
	}
		
	public String getHarmonizedSystemCommodityCode() {
		return harmonizedSystemCommodityCode;
	}    
	public void setHarmonizedSystemCommodityCode(String value) {
		this.harmonizedSystemCommodityCode = value;
	}
		
	public ShipmentFlashpoint getShipmentFlashpoint() {
		return shipmentFlashpoint;
	}    
	public void setShipmentFlashpoint(ShipmentFlashpoint value) {
		this.shipmentFlashpoint = value;
	}	
	
	public String getLevelOfDanger() {
		return levelOfDanger;
	}    
	public void setLevelOfDanger(String value) {
		this.levelOfDanger = value;
	}
	
	public String getEMSNumber() {
		return emsNumber;
	}    
	public void setEMSNumber(String value) {
		this.emsNumber = value;
	}
		
	public String getMFAG() {
		return mfag;
	}    
	public void setMFAG(String value) {
		this.mfag = value;
	}
	
	public List<String> getLabelMarkingList() {
		return labelMarkingList;
	}    
	public void setLabelMarkingList(List<String> list) {
		this.labelMarkingList = list;
	}
	public void addLabelMarkingList(String value) {
		if (labelMarkingList == null) {
			labelMarkingList = new ArrayList<String>();
		}
		this.labelMarkingList.add(value);	
	}
		
	public TechnicalName getTechnicalName() {
		return technicalName;
	}    
	public void setTechnicalName(TechnicalName value) {
		this.technicalName = value;
	}
	
	public TextAAC getAdditionalInformation() {
		return additionalInformation;
	}    
	public void setAdditionalInformation(TextAAC value) {
		this.additionalInformation = value;
	}
	
	public List<Contact> getEmergencyContactList() {
		return emergencyContactList;
	}    
	public void setEmergencyContactList(List<Contact> list) {
		this.emergencyContactList = list;
	}
	public void addEmergencyContactList(Contact contact) {
		if (emergencyContactList == null) {
			emergencyContactList = new ArrayList<Contact>();	
		}
		emergencyContactList.add(contact);	
	}
	
	public String getGrossWeightKilogram() {
		return grossWeightKilogram;
	}    
	public void setGrossWeightKilogram(String value) {
		this.grossWeightKilogram = value;
	}
	
	public String getNetWeightKilogram() {
		return netWeightKilogram;
	}    
	public void setNetWeightKilogram(String value) {
		this.netWeightKilogram = value;
	}
	
	public String getNetNetWeightKilogram() {
		return netNetWeightKilogram;
	}    
	public void setNetNetWeightKilogram(String value) {
		this.netNetWeightKilogram = value;
	}
	
	public String getNetExplosiveWeightKilogram() {
		return netExplosiveWeightKilogram;
	}    
	public void setNetExplosiveWeightKilogram(String value) {
		this.netExplosiveWeightKilogram = value;
	}
	
  	public String getRadioactiveIndexOfTransportNumber() {
		return radioactiveIndexOfTransportNumber;
	}    
	public void setRadioactiveIndexOfTransportNumber(String value) {
		this.radioactiveIndexOfTransportNumber = value;
	}
	
	public String getRadioactivityBecquerel() {
		return radioactivityBecquerel;
	}    
	public void setRadioactivityBecquerel(String value) {
		this.radioactivityBecquerel = value;
	}
		
	public String getRadioactivityCurie() {
		return radioactivityCurie;
	}    
	public void setRadioactivityCurie(String value) {
		this.radioactivityCurie = value;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(hazardCodeIdentification) && Utils.isStringEmpty(undgNumber) &&
		Utils.isStringEmpty(grossWeightKilogram) && Utils.isStringEmpty(levelOfDanger) &&
		Utils.isStringEmpty(mfag) && Utils.isStringEmpty(emsNumber) &&
		Utils.isStringEmpty(harmonizedSystemCommodityCode) && technicalName == null;
	}	
}

