package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 05.06.2014<br>
 * Description	: CustomsGoodsItem.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class CustomsGoodsItem extends KCXMessage {
	
	private GoodsPackaging goodsPackaging;			    
    private String ieUniqueLPRef;
    private String iePcksDescSequenceIdentifier;
    private String sequenceIdentifier;	
    private Commodity commodity;
    private Origin origin;
    private GoodsMeasure goodsMeasure;
    private CustomsProcedure customsProcedure;
    private DocumentIE previousCustomsDocument;		
    private String euItemPriceAmount;
    private CustomsValuation euCustomsValuation;
    private ArrayList<Text> additionalInformationList;
	private ArrayList<DocumentIE> additionalDocumentList;
	private CustomsValuation valuationAdjustment;
    private String statisticalValueAmount;
    private ArrayList<TransportEquipment> transportEquipmentList;
   
 
	private enum ECustomsGoodsItem {	
		GoodsPackaging,
		IEUniqueLPRef,
		IEPcksDescSequenceIdentifier,		
		SequenceIdentifier,
		Commodity,
		Origin,
		GoodsMeasure,
		CustomsProcedure,
		PreviousCustomsDocument,
		EUItemPriceAmount,       //Import
		EUCustomsValuation,      //Import
		AdditionalInformation,
		AdditionalDocument,
		ValuationAdjustment,      //Import
		StatisticalValueAmount,
		TransportEquipment;
   }	

	public CustomsGoodsItem() {
		super();  
	}
	public CustomsGoodsItem(String person) {
		super();  		
	}	

	public CustomsGoodsItem(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public CustomsGoodsItem(XmlMsgScanner scanner, String person) {
  		super(scanner);  		
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECustomsGoodsItem) tag) {
				case GoodsPackaging:
					goodsPackaging = new GoodsPackaging(getScanner());  	
					goodsPackaging.parse(tag.name());
  					break; 
				case Commodity:
					commodity = new Commodity(getScanner());  	
					commodity.parse(tag.name());
  					break; 
				case Origin:
					origin = new Origin(getScanner());  	
					origin.parse(tag.name());
  					break; 
				case GoodsMeasure:
					goodsMeasure = new GoodsMeasure(getScanner());  	
					goodsMeasure.parse(tag.name());
  					break; 
				case CustomsProcedure:
					customsProcedure = new CustomsProcedure(getScanner());  	
					customsProcedure.parse(tag.name());
  					break; 
				case PreviousCustomsDocument:
					previousCustomsDocument = new DocumentIE(getScanner());  	
					previousCustomsDocument.parse(tag.name());
  					break;
				case EUCustomsValuation:
					euCustomsValuation = new CustomsValuation(getScanner());  	
					euCustomsValuation.parse(tag.name());
					break;					
				case AdditionalInformation:
 					Text text = new Text(getScanner());  	
 					text.parse(tag.name());
 					this.addAdditionalInformationList(text);
					break; 
 				case AdditionalDocument:
 					DocumentIE doc = new DocumentIE(getScanner());  	
					doc.parse(tag.name());
					this.addAdditionalDocumentList(doc);
					break; 
 				case ValuationAdjustment:
 					valuationAdjustment = new CustomsValuation(getScanner());  	
 					valuationAdjustment.parse(tag.name());
 				case TransportEquipment:
 					TransportEquipment equipment = new TransportEquipment(getScanner());  	
 					equipment.parse(tag.name());
 					this.addTransportEquipmentList(equipment);
					break; 
				default:
  					return;
  			}
  		} else {
  			switch((ECustomsGoodsItem) tag) {   			  				   			
  				case IEUniqueLPRef:
  					setIEUniqueLPRef(value);
  					break;   					
  				case IEPcksDescSequenceIdentifier:
  					setIEPcksDescSequenceIdentifier(value);
  					break; 
  				case SequenceIdentifier:
  					setSequenceIdentifier(value);
  					break;
  				case StatisticalValueAmount:  				
  					setStatisticalValueAmount(value);
  					break;  
  				case EUItemPriceAmount:
  					setEUItemPriceAmount(value);
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
  			return ECustomsGoodsItem.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public void setIEUniqueLPRef(String argument) {
		this.ieUniqueLPRef = argument;
	}
	public String getIEUniqueLPRef() {
		return ieUniqueLPRef;
	}
    
    public void setIEPcksDescSequenceIdentifier(String argument) {
		this.iePcksDescSequenceIdentifier = argument;
	}
	public String getIEPcksDescSequenceIdentifier() {
		return iePcksDescSequenceIdentifier;
	}
	
	public void setSequenceIdentifier(String argument) {
		this.sequenceIdentifier = argument;
	}
	public String getSequenceIdentifier() {
		return sequenceIdentifier;
	}
	
	public void setStatisticalValueAmount(String argument) {
		this.statisticalValueAmount = argument;
	}
	public String getStatisticalValueAmount() {
		return statisticalValueAmount;
	}	
     
    public GoodsPackaging getGoodsPackaging() {
		return goodsPackaging;
	}
	public void setGoodsPackaging(GoodsPackaging goodsPackaging) {
		this.goodsPackaging = goodsPackaging;
	}
	
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	
	public GoodsMeasure getGoodsMeasure() {
		return goodsMeasure;
	}
	public void setGoodsMeasure(GoodsMeasure goodsMeasure) {
		this.goodsMeasure = goodsMeasure;
	}
	
	public CustomsProcedure getCustomsProcedure() {
		return customsProcedure;
	}
	public void setCustomsProcedure(CustomsProcedure customsProcedure) {
		this.customsProcedure = customsProcedure;
	}
	
	public DocumentIE getPreviousCustomsDocument() {
		return previousCustomsDocument;
	}
	public void setPreviousCustomsDocument(DocumentIE previousCustomsDocument) {
		this.previousCustomsDocument = previousCustomsDocument;
	}
	
	public ArrayList<Text> getAdditionalInformationList() {
		return additionalInformationList;
	}
	public void setAdditionalInformationList(ArrayList<Text> list) {
		this.additionalInformationList = list;
	}
	public void addAdditionalInformationList(Text text) {
		if (additionalInformationList == null) {
			additionalInformationList = new ArrayList<Text>();
		}
		additionalInformationList.add(text);
	}
	
	public ArrayList<DocumentIE> getAdditionalDocumentList() {
		return additionalDocumentList;
	}
	public void setAdditionalDocumentList(ArrayList<DocumentIE> list) {
		this.additionalDocumentList = list;
	}
	public void addAdditionalDocumentList(DocumentIE doc) {
		if (additionalDocumentList == null) {
			additionalDocumentList = new ArrayList<DocumentIE>();
		}
		additionalDocumentList.add(doc);
	}  	
	 
	public String getEUItemPriceAmount() {
		return euItemPriceAmount;
	}
	public void setEUItemPriceAmount(String euItemPriceAmount) {
		this.euItemPriceAmount = euItemPriceAmount;
	}
	
	public CustomsValuation getEUCustomsValuation() {
		return euCustomsValuation;
	}
	public void setEUCustomsValuation(CustomsValuation euCustomsValuation) {
		this.euCustomsValuation = euCustomsValuation;
	}
	
	public CustomsValuation getValuationAdjustment() {
		return valuationAdjustment;
	}
	public void setValuationAdjustment(CustomsValuation valuationAdjustment) {
		this.valuationAdjustment = valuationAdjustment;
	}
	public ArrayList<TransportEquipment> getTransportEquipmentList() {
		return transportEquipmentList;
	}
	public void setTransportEquipmentList(ArrayList<TransportEquipment> list) {
		this.transportEquipmentList = list;
	}
	public void addTransportEquipmentList(TransportEquipment equ) {
		if (transportEquipmentList == null) {
			transportEquipmentList = new ArrayList<TransportEquipment>();
		}
		transportEquipmentList.add(equ);
	}  
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.ieUniqueLPRef) && Utils.isStringEmpty(this.iePcksDescSequenceIdentifier) && 
			Utils.isStringEmpty(this.statisticalValueAmount) &&   //usw.
		   (additionalInformationList  == null || (additionalDocumentList  == null && transportEquipmentList == null));
	}    
}
