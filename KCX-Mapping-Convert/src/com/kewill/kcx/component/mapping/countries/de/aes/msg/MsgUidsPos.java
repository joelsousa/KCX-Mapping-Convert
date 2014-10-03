/*
 * Function    : MsgUidsPos.java
 * Titel       :
 * Date        : 15.09.2008
 * Author      : Kewill CSF / houdek
 * Description : Contains Goodsitem Data with all Fields used in UIDS,
 * 			   : to use for different Messages
 * Parameters  :

 * Changes
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentU;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteOffAtlas;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgUidsPos<br>
 * Erstellt		: 15.09.2008<br>
 * Beschreibung	: Contains Goodsitem Data with all Fields used in UIDS to use for different Messages.
 * 
 * @author houdek
 * @version 1.0.00
 */
public class MsgUidsPos extends KCXMessage {

	private String dummy;	
	private String itemNumber;
	private String articleNumber;
	private String goodsDescription;
	private String externalRegistrationNumber;
	private String changedFlag;
	private String remark;
	private String originRegion;
	private String netMass;
	private String grossMass;
	private String commodityCodeKN8;
	private String commodityCodeTARIC;
	private String commodityCodeFirstAddition;
	private String commodityCodeSecondAddition;
	private String commodityCodeNationalAddition;
    private String statisticalValue;
    private String statisticalQuantity;
    private String statisticalBaseValue;
    private String statisticalBaseCurrency;
    private String typeOfControl;
    private String commercialReferenceNumber;    //EI20090312 V60
    private String dangerousGoodsNumber;         //EI20090312 V60
    private String paymentType;                  //EI20090312 V60
    
    private ApprovedTreatment approvedTreatment;
    private Packages packages;
    private List <Packages>packagesList;
    private Trader consignee;
   
    private List <DocumentU>producedDocumentUList;    
    private DocumentU documentU;
    private PreviousDocument previousDocument;
    private List <PreviousDocument>previousDocumentUList;    
    //private List <ContainerRegistrationU>containerRegistrationUList;  
    private String containerRegistration;
    private List <String>containerRegistrationList;   
    private ExportRefundItem exportRefundItem;   //EI20090312
    private WriteOffAtlas writeOffAtlas;   //EI20090312

	private XMLEventReader 	parser;
    private boolean debug;

	public MsgUidsPos() {
			super();
			createLists();
	}

	public MsgUidsPos(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
		createLists();
}
	 
	 public MsgUidsPos(XmlMsgScanner scanner) {
	  		super(scanner);
	  		createLists();
	 }
	 
	 private void createLists() {
			
		 producedDocumentUList      = new Vector<DocumentU>();
		 previousDocumentUList      = new Vector<PreviousDocument>();
		 containerRegistrationList  = new Vector<String>();
		 packagesList				= new Vector<Packages>();
	}
		
	 private enum EGoodsItemTags {
			ItemNumber,
			//AdditionalInformation,		//not in use
			ArticleNumber,
			//ChassisNumber,				//not in use
			ContainerRegistration,
			ChangedFlag,
			//Consignor,					//not in use
			Consignee,
			CommodityCodeKN8,
			CommodityCodeTARIC,
			CommodityCodeFirstAddition,
			CommodityCodeSecondAddition,
			CommodityCodeNationalAddition,
			CommercialReferenceNumber,      //EI20090312
			DangerousGoodsNumber,           //EI20090312
			ExportRestitutionItem,          //EI20090312
			PaymentType,                    //EI20090312
			//CustomsTreatment,				//not in use
			//DeliveryTerms,				//not in use
			DestinationCountry,
			ExternalRegistrationNumber,
			MRN,
			GoodsDescription,
			GrossMass,
			NetMass,
			//NewOrOldVehiiclesIndicator,	//not in use
			OriginCountry,
			OriginRegion,			
			Packaging,
			//Price,						//not in use
			Procedure,
			PreviousDocument,
			ProducedDocument,
			SpecialGoodsInformation,
			SpecialRemarks,
			//SpecialTaxes,					//not in use
			//SupplementaryUnit,			//not in use
			Remark,
			//Restitution,					//not in use
			//RestitutionIndicator,			//not in use		
			StatisticalValue,
			StatisticalQuantity,
			StatisticalBaseValue,
			StatisticalBaseCurrency,
			//Taxation,						//not in use
			TypeOfControl,
			WriteOffAtlas;                  //EI20090312		
		}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EGoodsItemTags) tag) {

					case Consignee:
						consignee = new Trader(getScanner());
						consignee.parse(tag.name());						
						break;

					case Procedure:				
						approvedTreatment = new ApprovedTreatment(getScanner());
						approvedTreatment.parse(tag.name());												
						break;

					case Packaging:
						packages = new Packages(getScanner());
						packages.parse(tag.name());
						packagesList.add(packages);
						break;

					case ProducedDocument:
						documentU = new DocumentU(getScanner());
						documentU.parse(tag.name());						
						producedDocumentUList.add(documentU);
						break;

					case PreviousDocument:
						previousDocument = new PreviousDocument(getScanner());
						previousDocument.parse(tag.name());												
						previousDocumentUList.add(previousDocument);
						break;

					case ExportRestitutionItem:
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());
						break;
						
					case WriteOffAtlas:
						writeOffAtlas = new WriteOffAtlas(getScanner());
						writeOffAtlas.parse(tag.name());
						break;						
						
				default:
						return;
						
				}
			} else {
				switch ((EGoodsItemTags) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;
						
					case ArticleNumber:
						setArticleNumber(value);
						break;
						
					case GoodsDescription:
						setGoodsDescription(value);
						break;
						
					case ChangedFlag:
						setChangedFlag(value);	
						break;	
						
					case MRN:
					case ExternalRegistrationNumber:	
						setExternalRegistrationNumber(value);
						break;
						
					case Remark:
						setRemark(value);
						break;
						
					case OriginRegion:
						setOriginRegion(value);
						break;
						
					case NetMass:
						setNetMass(value);
						break;
						
					case GrossMass:
					    setGrossMass(value);
						break;
						
					case CommodityCodeKN8:
						setCommodityCodeKN8(value);
						break;
						
					case CommodityCodeTARIC:
						setCommodityCodeTARIC(value);
						break;
						
					case CommodityCodeFirstAddition:
						setCommodityCodeFirstAddition(value);
						break;
					case
					    CommodityCodeSecondAddition:
						setCommodityCodeSecondAddition(value);
						break;
						
					case CommodityCodeNationalAddition:
						setCommodityCodeNationalAddition(value);
						break;
						
					case StatisticalValue:
						setStatisticalValue(value);
						break;
						
					case StatisticalQuantity:
						setStatisticalQuantity(value);
						break;
						
					case StatisticalBaseValue:
						setStatisticalBaseValue(value);
						break;
						
					case ContainerRegistration:
						setContainerRegistration(value);
					     containerRegistrationList.add(containerRegistration);
						break; 
						
					case StatisticalBaseCurrency:
						setStatisticalBaseCurrency(value);
						break;
						
					case TypeOfControl:
						setTypeOfControl(value);
						break;
					case CommercialReferenceNumber:     //EI20090312
						setCommercialReferenceNumber(value);
						break;
					case DangerousGoodsNumber:           //EI20090312
						setDangerousGoodsNumber(value);
						break;
					case PaymentType:                    //EI20090312
						setPaymentType(value);
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
				return EGoodsItemTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getExternalRegistrationNumber() {
		return externalRegistrationNumber;
	}

	public void setExternalRegistrationNumber(String externalRegistrationNumber) {
		this.externalRegistrationNumber = externalRegistrationNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOriginRegion() {
		return originRegion;
	}

	public void setOriginRegion(String originRegion) {
		this.originRegion = originRegion;
	}

	public String getNetMass() {
		return netMass;
	}

	public void setNetMass(String netMass) {
		this.netMass = netMass;
	}

	public String getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(String grossMass) {
		this.grossMass = grossMass;
	}

	public String getCommodityCodeKN8() {
		return commodityCodeKN8;
	}

	public void setCommodityCodeKN8(String commodityCodeKN8) {
		this.commodityCodeKN8 = commodityCodeKN8;
	}

	public String getCommodityCodeTARIC() {
		return commodityCodeTARIC;
	}

	public void setCommodityCodeTARIC(String commodityCodeTARIC) {
		this.commodityCodeTARIC = commodityCodeTARIC;
	}

	public String getCommodityCodeFirstAddition() {
		return commodityCodeFirstAddition;
	}

	public void setCommodityCodeFirstAddition(String commodityCodeFirstAddition) {
		this.commodityCodeFirstAddition = commodityCodeFirstAddition;
	}

	public String getCommodityCodeSecondAddition() {
		return commodityCodeSecondAddition;
	}

	public void setCommodityCodeSecondAddition(String commodityCodeSecondAddition) {
		this.commodityCodeSecondAddition = commodityCodeSecondAddition;
	}

	public String getCommodityCodeNationalAddition() {
		return commodityCodeNationalAddition;
	}

	public void setCommodityCodeNationalAddition(
			String commodityCodeNationalAddition) {
		this.commodityCodeNationalAddition = commodityCodeNationalAddition;
	}

	public String getStatisticalValue() {
		return statisticalValue;
	}

	public void setStatisticalValue(String statisticalValue) {
		this.statisticalValue = statisticalValue;
	}

	public String getStatisticalQuantity() {
		return statisticalQuantity;
	}

	public void setStatisticalQuantity(String statisticalQuantity) {
		this.statisticalQuantity = statisticalQuantity;
	}

	public String getStatisticalBaseValue() {
		return statisticalBaseValue;
	}

	public void setStatisticalBaseValue(String statisticalBaseValue) {
		this.statisticalBaseValue = statisticalBaseValue;
	}

	public String getStatisticalBaseCurrency() {
		return statisticalBaseCurrency;
	}

	public void setStatisticalBaseCurrency(String statisticalBaseCurrency) {
		this.statisticalBaseCurrency = statisticalBaseCurrency;
	}


	public ApprovedTreatment getApprovedTreatment() {
		return approvedTreatment;
	}

	public void setApprovedTreatment(ApprovedTreatment argument) {
		this.approvedTreatment = argument;
	}

	public List getPackagesList() {
		return packagesList;
	}

	public void setPackages(List<Packages> argument) {
		this.packagesList = argument;
	}

	public Trader getConsignee() {
		return consignee;
	}

	public void setConsignee(Trader consignee) {
		this.consignee = consignee;
	}
	

	public String getContainerRegistration() {
		return containerRegistration;
	}

	public void setContainerRegistration(
			String argument) {
		this.containerRegistration = argument;
	}

	public List getContainerRegistrationList() {
		return containerRegistrationList;
	}

	public void setContainerRegistrationList(List<String> argument) {
		this.containerRegistrationList = argument;
	}	
	
	public List<DocumentU> getProducedDocumentUList() {
		return producedDocumentUList;
	}

	public void setProducedDocumentU(List<DocumentU> producedDocumentUList) {
	    this.producedDocumentUList = producedDocumentUList;
	}
	
	public void addProducedDocumentUList(DocumentU argument) {
		this.producedDocumentUList.add(argument);
	}

	
	public List getPreviousDocumentUList() {
		return previousDocumentUList;
	}
	public void setPreviousDocumentUList(List<PreviousDocument> previousDocumentUList) {
		this.previousDocumentUList = previousDocumentUList;
	}
	public void addPreviousDocumentUList(PreviousDocument argument) {
		this.previousDocumentUList.add(argument);
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getDummy() {
		return dummy;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public void setProducedDocumentUList(List<DocumentU> producedDocumentUList) {
		this.producedDocumentUList = producedDocumentUList;
	}

	public String getTypeOfControl() {
		return typeOfControl;
	}

	public void setTypeOfControl(String typeOfControl) {
		this.typeOfControl = typeOfControl;
	}

	public String getChangedFlag() {
		return changedFlag;
	}

	public void setChangedFlag(String changedFlag) {
		this.changedFlag = changedFlag;
	}
	public void setCommercialReferenceNumber(String argument) {
		this.commercialReferenceNumber = argument;
	}
	public String getCommercialReferenceNumber() {
		return commercialReferenceNumber;
	}
	
	public void setDangerousGoodsNumber(String argument) {
		this.dangerousGoodsNumber = argument;
	}
	public String getDangerousGoodsNumber() {
		return dangerousGoodsNumber;
	}
	
	public void setPaymentType(String argument) {
		this.paymentType = argument;
	}
	
	public String getPaymentType() {
		return paymentType;
	}	
	public void setExportRefundItem(ExportRefundItem argument) {
	   this.exportRefundItem = argument;
	}
	public ExportRefundItem getExportRefundItem() {
		return exportRefundItem;
	}
	
	public void setWriteOffAtlas(WriteOffAtlas argument) {
		   this.writeOffAtlas = argument;
		}
	public WriteOffAtlas getWriteOffAtlas() {
			return writeOffAtlas;
		}	
}
