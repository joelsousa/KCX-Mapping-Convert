package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgEntrySummaryDeclarationPos<br>
 * Erstellt		: 10.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used for GoodsItems of ICSEntrySummaryDeclaration.
 *                 
 * @author Alfred Krzoska
 * @version 1.0.00
 */

public class GoodsItemLong extends KCXMessage {

	private String 				msgName;
	private String 				itemNumber; 
	private String				shipmentNumber;
	private String				description;
	private String				descriptionLng;
	private String				grossMass;
	private String				dangerousGoodsNumber;
	private String				paymentType;
	private String				loadingPlace;
	private String				loadingPlaceLng;
	private String				unloadingPlace;
	private String				unloadingPlaceLng;
	private Party           	consignor;        //TIN, Address and ContactPerson
	private TIN					consignorTIN;
	private Address				consignorAdr;
	private Party          		consignee;		  //TIN, Address and ContactPerson
	private TIN					consigneeTIN;
	private Address				consigneeAdr;
	private Party           	notifyParty;      //TIN, Address and ContactPerson	
	private TIN					notifyPartyTIN;
	private Address				notifyPartyAdr;
	private String				commodityCode;
	private String				articleNumber;
	private List<String>		containersList = null;	
	private List<Packages>		packagesList = null;   //99
	private List<TransportMeans> meansOfTransportBorderList; //999 
	//CommodityCode
	private List<IcsDocument>		documentList;   //99
	private List<SpecialMention>	specialMentionList;	

	public GoodsItemLong() {
		super();				
	}

	public GoodsItemLong(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
 
	private enum EMsgEntrySummaryDeclarationPos {
		//KIDS:							UIDS:
		ItemNumber,
		ShipmentNumber,				CommercialReferenceNumber,
		Description,				GoodsDescription,
		DescriptionLng,				//---
		GrossMass,					//same
		DangerousGoodsNumber,		UNDGNumber,
		PaymentType,				TransportMethodOfPayment,
		LoadingPlace,				PlaceOfLoading,
		LoadingPlaceLng,			//---
		UnloadingPlace,				PlaceOfUnloading,
		UnloadingPlaceLng,			//---
		ConsignorTIN,				Consignor,   //.TIN + Address + EntAddress + VatId
		ConsignorAddress,			//""
		ConsigneeTIN,				Consignee,   //.TIN + Address + EntAddress + VatId
		ConsigneeAddress,			//""
		NotifyPartyTIN,				NotifyParty, //.TIN + Address + EntAddress + VatId
		NotifyPartyAddress,			// ""
		CommodityCode,				CommodityCodeKN8,
		ArticleNumber,									//AK20121019 Added for V20 
		Containers,	Container,		ContainerNumber,
		Packages,					Packaging,
		//TODO check capital F in MeansOFTransportBorder with Daggi
		MeansOFTransportBorder,		TransportAtBorder,	MeansOfTransportAtBorder, MeansOfTransportBorder,
		Document,					Documents,
		SpecialMention,				SpecialMentions;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EMsgEntrySummaryDeclarationPos) tag) {
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());	
				break;
			case ConsignorAddress:
				consignorAdr = new Address(getScanner());
				consignorAdr.parse(tag.name());
				break;
			case Consignor:		
				Trader consignorTrader = new Trader(getScanner());
				consignorTrader.parse(tag.name());	
				consignor = setPartyFromTrader(consignorTrader);	
				break;
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());		
				break;
			case ConsigneeAddress:
				consigneeAdr = new Address(getScanner());
				consigneeAdr.parse(tag.name());				
				break;
			case Consignee:
				Trader consigneeTrader = new Trader(getScanner());
				consigneeTrader.parse(tag.name());	
				consignee = setPartyFromTrader(consigneeTrader);				
				break;				
			case NotifyPartyTIN:
				notifyPartyTIN = new TIN(getScanner());
				notifyPartyTIN.parse(tag.name());	
				break;
			case NotifyPartyAddress:
				notifyPartyAdr = new Address(getScanner());
				notifyPartyAdr.parse(tag.name());	
				break;
			case NotifyParty:
				Trader notifyPartyTrader = new Trader(getScanner());
				notifyPartyTrader.parse(tag.name());	
				notifyParty = setPartyFromTrader(notifyPartyTrader);
				break;			
			case Packages:
			case Packaging:
				Packages tempPackages = new Packages(getScanner());
				tempPackages.parse(tag.name());
				if (packagesList == null) {
					packagesList = new Vector <Packages>(); 
				}
				packagesList.add(tempPackages);				
				break;
			case Document: 
			case Documents:
				IcsDocument tempDocument = new IcsDocument(getScanner());			
				tempDocument.parse(tag.name());
				addDocumentList(tempDocument);				
				break;
			case SpecialMentions:
				SpecialMention specialMention = new SpecialMention(getScanner());
				specialMention.parse(tag.name());	
				if (specialMentionList == null) {
					specialMentionList = new Vector <SpecialMention>(); 
				}
				specialMentionList.add(specialMention);				
				break;				
			case MeansOFTransportBorder:
			case TransportAtBorder:
			case MeansOfTransportAtBorder:				//PT20100618
			case MeansOfTransportBorder:
				TransportMeans tempTransportMeans = new TransportMeans(getScanner());
				tempTransportMeans.parse(tag.name());
				if (meansOfTransportBorderList == null) {
					meansOfTransportBorderList = new Vector <TransportMeans>(); 
				}
				meansOfTransportBorderList.add(tempTransportMeans);
				break;
			case Containers:  //EI20110127
			case Container:   //BDP20130425          
					Container container = new Container(getScanner());
					container.parse(tag.name());
					if (containersList == null) {
						containersList = new Vector <String>(); 
					}
					containersList = container.getNumberList();				
			break;
			default:
				return;
			}
	    } else {
	    	switch ((EMsgEntrySummaryDeclarationPos) tag) {
			case ItemNumber:
				 setItemNumber(value);
				 break;	
			case ShipmentNumber:
			case CommercialReferenceNumber:
				 setShipmentNumber(value);
				 break;		
			case Description:
			case GoodsDescription:
				 setDescription(value);
				 break;	
			case DescriptionLng:
				 setDescriptionLng(value);
				 break;
			case GrossMass:
				 setGrossMass(value);
				 break;	
			case DangerousGoodsNumber:
			case UNDGNumber:
				 setDangerousGoodsNumber(value);
				 break;
			case PaymentType:
			case TransportMethodOfPayment:
				 setPaymentType(value);
				 break;		
			case LoadingPlace:
			case PlaceOfLoading:
				 setLoadingPlace(value);
				 break;					 
			case LoadingPlaceLng:			
				 setLoadingPlaceLng(value);
				 break;					 
			case UnloadingPlace:
			case PlaceOfUnloading:
				 setUnloadingPlace(value);
				 break;					 
			case UnloadingPlaceLng:
				 setUnloadingPlaceLng(value);
				 break;				
			case CommodityCode:
			case CommodityCodeKN8:
				 setCommodityCode(value);
				 break;
			case ArticleNumber:
				setArticleNumber(value);
				break;
			case Containers:
			case ContainerNumber:
				if (containersList == null) {
					containersList = new Vector <String>(); 
				}
				containersList.add(value);
				break;	
			case SpecialMention:
				SpecialMention specialMention = new SpecialMention();
				specialMention.setCode(value);
				if (specialMentionList == null) {
					specialMentionList = new Vector <SpecialMention>(); 
				}
				specialMentionList.add(specialMention);	
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
  			return EMsgEntrySummaryDeclarationPos.valueOf(token);
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

	public String getShipmentNumber() {
		return shipmentNumber;	
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
	}

	public String getDescription() {
		return description;	
	}
	public void setDescription(String description) {
		this.description = Utils.checkNull(description);
	}

	public String getDescriptionLng() {
		return descriptionLng;	
	}
	public void setDescriptionLng(String descriptionLng) {
		this.descriptionLng = Utils.checkNull(descriptionLng);
	}

	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String grossMass) {
		this.grossMass = Utils.checkNull(grossMass);
	}

	public String getDangerousGoodsNumber() {
		return dangerousGoodsNumber;	
	}
	public void setDangerousGoodsNumber(String dangerousGoodsNumber) {
		this.dangerousGoodsNumber = Utils.checkNull(dangerousGoodsNumber);
	}

	public String getPaymentType() {
		return paymentType;	
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = Utils.checkNull(paymentType);
	}

	public String getLoadingPlace() {
		return loadingPlace;	
	}
	public void setLoadingPlace(String loadingPlace) {
		this.loadingPlace = Utils.checkNull(loadingPlace);
	}

	public String getLoadingPlaceLng() {
		return loadingPlaceLng;	
	}
	public void setLoadingPlaceLng(String loadingPlaceLng) {
		this.loadingPlaceLng = Utils.checkNull(loadingPlaceLng);
	}

	public String getUnloadingPlace() {
		return unloadingPlace;	
	}
	public void setUnloadingPlace(String unloadingPlace) {
		this.unloadingPlace = Utils.checkNull(unloadingPlace);
	}

	public String getUnloadingPlaceLng() {
		return unloadingPlaceLng;	
	}
	public void setUnloadingPlaceLng(String unloadingPlaceLng) {
		this.unloadingPlaceLng = Utils.checkNull(unloadingPlaceLng);
	}

	public Party getConsignor() {
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new Party();
			}
			consignor.setPartyTIN(consignorTIN);	
		}	
		if (consignorAdr != null) {
			if (consignor == null) {
				consignor = new Party();
			}
			consignor.setAddress(consignorAdr);	
		}	
		return this.consignor;	
	}
	public void setConsignor(Party party) {
		this.consignor = party;
	}

	public Party getConsignee() {
		if (consigneeTIN != null) {
			if (consignee == null) {
				consignee = new Party();
			}
			consignee.setPartyTIN(consigneeTIN);	
		}	
		if (consigneeAdr != null) {
			if (consignee == null) {
				consignee = new Party();
			}
			consignee.setAddress(consigneeAdr);	
		}	
		return this.consignee;	
	}
	public void setConsignee(Party party) {
		this.consignee = party;
	}

	public Party getNotifyParty() {
		if (notifyPartyTIN != null || notifyPartyAdr != null) {
			if (notifyParty == null) {
				notifyParty = new Party();
			}
		}
		if (notifyPartyTIN != null) {
			notifyParty.setPartyTIN(notifyPartyTIN);	
		}	
		if (notifyPartyAdr != null) {			
			notifyParty.setAddress(notifyPartyAdr);	
		}	
		return this.notifyParty;	
	}
	public void setNotifyParty(Party party) {
		this.notifyParty = party;
	}

	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = Utils.checkNull(commodityCode);
	}

	public List<String> getContainersList() {
		return containersList;
	}
	public void setContainersList(List<String> list) {
		this.containersList = list;
	}

	public List<Packages> getPackagesList() {
		return packagesList;
	}
	public void setPackagesList(List<Packages> list) {
		this.packagesList = list;
	}

	public List<TransportMeans> getMeansOfTransportBorderList() {
		return meansOfTransportBorderList;
	}
	public void setMeansOfTransportBorderList(List<TransportMeans> list) {
		this.meansOfTransportBorderList = list;
	}

	public List<IcsDocument> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<IcsDocument> list) {
		this.documentList = list;
	}
	public void addDocumentList(IcsDocument document) {  //EI20110407
		if (documentList == null) {
		documentList = new Vector <IcsDocument>(); 
		}
		documentList.add(document);
	}
	public List<SpecialMention> getSpecialMentionList() {
		return specialMentionList;
	}
	public void setSpecialMentionList(List<SpecialMention> list) {
		this.specialMentionList = list;
	}

	public Party setPartyFromTrader(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party  = new Party();
		
		TIN	tin = new TIN();
		tin.setTIN(trader.getTIN());
		tin.setIsTINGermanApprovalNumber(trader.getCustomsID());
		tin.setCustomerIdentifier(trader.getCustomerID());	
		tin.setIdentificationType(trader.getTINType());   //EI20110120
		
		party.setPartyTIN(tin);		
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());
		party.setContactPerson(trader.getContactPerson());
		
		return party;		
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = Utils.checkNull(articleNumber);
	}
}
