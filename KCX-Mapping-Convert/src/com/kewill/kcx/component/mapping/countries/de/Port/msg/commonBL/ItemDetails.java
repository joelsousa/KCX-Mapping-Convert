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

public class ItemDetails extends KCXMessage {
	
	private String numberOfPackages;	
	private String typeOfPackagesIdentification;	
	private String typeOfPackagesText;	
	private String harmonizedSystemCommodityCode;
	private PartyDetails shipper;
	private PartyDetails consignee;
	private String netWeight;
	private String tareWeight;
	private String volume;	
	private String bookingReferenceNumber;
	private String customsDeclarationNumber;
	private String articleNumber;
	private String cargoControlNumber;
	private List<String> marksAndNumbersList;	

	private enum ECarriage {	
		NumberOfPackages,
		TypeOfPackagesIdentification,		
		TypeOfPackagesText,
		HarmonizedSystemCommodityCode,		
		OriginalShipper,
		UltimateConsignee,
		NetWeightKilogram,
		TareWeightKilogram,		
		GrossVolumeCubicMetre,
		BookingReferenceNumber,
		CustomsDeclarationNumber,
		ArticleNumber,
		CargoControlNumber,
		MarksAndNumbers;
   }	

	public ItemDetails() {
		super();  
	}

	public ItemDetails(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case OriginalShipper:
  					shipper = new PartyDetails(getScanner());  	
  					shipper.parse(tag.name());
					break;					
  				case UltimateConsignee:
  					consignee = new PartyDetails(getScanner());  	
  					consignee.parse(tag.name());
					break; 
  				
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   
  			case NumberOfPackages:
  				setNumberOfPackages(value);
  				break;
  			case TypeOfPackagesIdentification:
  				setTypeOfPackagesIdentification(value);
  				break;
  			case TypeOfPackagesText:
  				setTypeOfPackagesText(value);
  				break;
  			case HarmonizedSystemCommodityCode:
  				setHarmonizedSystemCommodityCode(value);
  				break;
  			case NetWeightKilogram:
  				setNetWeightKilogram(value);
  				break;
  			case TareWeightKilogram:
  				setTareWeightKilogram(value);
  				break;  			
  			case GrossVolumeCubicMetre:
  				setGrossVolumeCubicMetre(value);
  				break;
  			case BookingReferenceNumber:
  				setBookingReferenceNumber(value);
  				break;
  			case CustomsDeclarationNumber:
  				setCustomsDeclarationNumber(value);
  				break;
  			case ArticleNumber:
  				setArticleNumber(value);
  				break;
  			case CargoControlNumber:
  				setCargoControlNumber(value);
  				break;
  			case MarksAndNumbers:
  				addMarksAndNumbersList(value);
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
	
  	public PartyDetails getOriginalShipper() {
		return shipper;
	}    
	public void setOriginalShipper(PartyDetails value) {
		this.shipper = value;
	}
		
	public PartyDetails getUltimateConsignee() {
		return consignee;
	}    
	public void setUltimateConsignee(PartyDetails value) {
		this.consignee = value;
	}
	
  	public String getNumberOfPackages() {
		return numberOfPackages;
	}    
	public void setNumberOfPackages(String value) {
		this.numberOfPackages = value;
	}
	
	public String getTypeOfPackagesIdentification() {
		return typeOfPackagesIdentification;
	}    
	public void setTypeOfPackagesIdentification(String value) {
		this.typeOfPackagesIdentification = value;
	}
		
	public String getTypeOfPackagesText() {
		return typeOfPackagesText;
	}    
	public void setTypeOfPackagesText(String value) {
		this.typeOfPackagesText = value;
	}
	
	public String getHarmonizedSystemCommodityCode() {
		return harmonizedSystemCommodityCode;
	}    
	public void setHarmonizedSystemCommodityCode(String value) {
		this.harmonizedSystemCommodityCode = value;
	}
	
	public String getNetWeightKilogram() {
		return netWeight;
	}    
	public void setNetWeightKilogram(String value) {
		this.netWeight = value;
	}
	
	public String getTareWeightKilogram() {
		return tareWeight;
	}    
	public void setTareWeightKilogram(String value) {
		this.tareWeight = value;
	}
	
	public String getGrossVolumeCubicMetre() {
		return volume;
	}    
	public void setGrossVolumeCubicMetre(String value) {
		this.volume = value;
	}
	
	public String getBookingReferenceNumber() {
		return bookingReferenceNumber;
	}    
	public void setBookingReferenceNumber(String value) {
		this.bookingReferenceNumber = value;
	}
	
	public String getCustomsDeclarationNumber() {
		return customsDeclarationNumber;
	}    
	public void setCustomsDeclarationNumber(String value) {
		this.customsDeclarationNumber = value;
	}
	
	public String getArticleNumber() {
		return articleNumber;
	}    
	public void setArticleNumber(String value) {
		this.articleNumber = value;
	}
	
	public String getCargoControlNumber() {
		return cargoControlNumber;
	}    
	public void setCargoControlNumber(String value) {
		this.cargoControlNumber = value;
	}
	
	public List<String> getMarksAndNumbersList() {
		return marksAndNumbersList;
	}    
	public void setMarksAndNumbersList(List<String> list) {
		this.marksAndNumbersList = list;
	}
		  
	public void addMarksAndNumbersList(String value) {
		if (marksAndNumbersList == null) {
			marksAndNumbersList = new ArrayList<String>();
		}
		this.marksAndNumbersList.add(value);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(numberOfPackages) && Utils.isStringEmpty(typeOfPackagesIdentification) &&
		shipper == null && consignee == null; 
	}
}

