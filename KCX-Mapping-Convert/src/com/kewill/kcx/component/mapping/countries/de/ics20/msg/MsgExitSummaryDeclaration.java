package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 23.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSExitSummaryDeclaration.
 * 				: (IE615)
 *                 
 * @author krzoska
 * @version 2.0.00
 */

public class MsgExitSummaryDeclaration extends KCXMessage {

	private String msgName = "ICSExitSummaryDeclaration";
	private String msgType = "";
	
	private String 			referenceNumber;
	private String 			shipmentNumber;
	private String 			totalNumberPositions;
	private String 			totalNumberPackages;
	private Text			locationOfGoods;
	private String			customsSubPlace;
	private String 			situationCode;
	private TransportMeans	meansOfTransportBorder;
	private String 			paymentType;
	private String 			declarationDate;		
	private Party			consignor;
	private TIN				consignorTIN;
	private Party           consignee;		  //TIN, Address 
	private TIN				consigneeTIN;
	private Party 			personLodgingSuma; //TIN, Address 
	private TIN				personLodgingSumaTIN;
	private Party			representative;
	private TIN				representativeTIN;		
	private String 			customsOfficeOfLodgement;
	private String			officeOfExit;	
	private ArrayList<SealNumber> sealIDList;
	private SealNumber 		sealsID;
	private ArrayList<String>	countryOfRoutingList;		//99
	private List<GoodsItemLong> goodsItemList;				//99999
	
	private EFormat declarationDateFormat;
	
	public MsgExitSummaryDeclaration() {
		super();				
	}

	public MsgExitSummaryDeclaration(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
 
	public MsgExitSummaryDeclaration(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);	
		msgType = type;
	}
	
	private enum EDeclaration {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,		
		ShipmentNumber,                 CommercialReferenceNumber,
		TotalNumberPositions,			TotalNumberOfItems,
		TotalNumberPackages,			TotalNumberOfPackages,
		LocationOfGoods,				PlaceOfLoading,
		CustomsSubPlace,				//same
		SituationCode,					SpecificCircumstanceIndicator,
		MeansOfTransportBorder,			TransportAtBorder,
		PaymentType,					TransportMethodOfPayment,
		DeclarationDate,				//same
		DeclarationPlace,				//same
		ConsignorTIN,					Consignor, 	
		ConsignorAddress,		 			 //ConsignorAddress: Address + EntAddress + VatId
		ConsigneeTIN,					Consignee, 
		ConsigneeAddress,	      			//ConsigneeAddress: Address + EntAddress + VatId
		PersonLodgingSumaTIN,			PersonLodgingSuma,
		PersonLodgingSumaAddress, 			//PersonLodgingSumaAddress: Address + EntAddress + VatId
		RepresentativeTIN,				Representative, 	
		RepresentativeAddress,	  			//RepresentativeAddress: Address + EntAddress + VatId
		CustomsOfficeOfLodgement,		OfficeOfLodgement,
		OfficeOfExit,					CustomsOfficeOfExit,
		CountryOfRouting,				Itinerary,
		SealsID,						SealsIdentity,
		SealsIdentityLng,				//not defined
		GoodsItem;						//same
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EDeclaration) tag) {
			case MeansOfTransportBorder:
			case TransportAtBorder:
				meansOfTransportBorder = new TransportMeans(getScanner());
				meansOfTransportBorder.parse(tag.name());				
				break;
			case LocationOfGoods:					
			case PlaceOfLoading:
				locationOfGoods = new Text(getScanner());
				locationOfGoods.parse(tag.name());
				break;
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());	
				break;
			case ConsignorAddress:
			case Consignor:
				consignor = new Party(getScanner());
				consignor.parse(tag.name());
				break;
				
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());		
				break;
			case ConsigneeAddress:
			case Consignee:
				consignee = new Party(getScanner());
				consignee.parse(tag.name());				
				break;

			case PersonLodgingSumaTIN:
				personLodgingSumaTIN = new TIN(getScanner());
				personLodgingSumaTIN.parse(tag.name());				
				break;	
			case PersonLodgingSumaAddress:
			case PersonLodgingSuma:
				personLodgingSuma = new Party(getScanner());
				personLodgingSuma.parse(tag.name());	
				break;
				
			case RepresentativeTIN:
				representativeTIN = new TIN(getScanner());
				representativeTIN.parse(tag.name());	
				break;
			case RepresentativeAddress:
			case Representative:
				representative = new Party(getScanner());
				representative.parse(tag.name());	
				break;			

			case GoodsItem:
				GoodsItemLong goodsItem = 
					new  GoodsItemLong(getScanner(), getMsgName());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EDeclaration) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;	
			case ShipmentNumber:
			case CommercialReferenceNumber:
				 setShipmentNumber(value);
				 break;
			case CustomsSubPlace:
				 setCustomsSubPlace(value);
				 break;
			case TotalNumberPositions:
			case TotalNumberOfItems:
				 setTotalNumberPositions(value);
				 break;	
			case TotalNumberPackages:
			case TotalNumberOfPackages:
				 setTotalNumberPackages(value);
				 break;
			case SituationCode:
			case SpecificCircumstanceIndicator:
				 setSituationCode(value);
				 break;
			case PaymentType:
			case TransportMethodOfPayment:
				 setPaymentType(value);
				 break;					 
			case DeclarationDate:
				 setDeclarationDate(value);
				 if (msgType.equals("KIDS")) {					
					 setDeclarationDateFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					
					 setDeclarationDateFormat(Utils.getUidsDateAndTimeFormat(value)); 		
				 }
				 break;	
			case CustomsOfficeOfLodgement:
			case OfficeOfLodgement:
				 setCustomsOfficeOfLodgment(value);
				 break;		
			case CustomsOfficeOfExit:
			case OfficeOfExit:
				 setOfficeOfExit(value);
				 break;		
			case SealsIdentity:
				sealsID = new SealNumber();
				sealsID.setNumber(value);	
				addSealIDList(sealsID);
				break;
			// CK 2010-12-30
			// add Language in last SealNumber of according list: 
			case SealsIdentityLng:
				sealsID = getLastSealNum();
				if (sealsID != null) {
					sealsID.setLanguage(value);	
					addSealIDList(sealsID);
				}
				break;
			case CountryOfRouting:
			case Itinerary:				
				addCountryOfRoutingList(value);
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
  			return EDeclaration.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getMsgName() {
		return this.msgName;	
	}
	/*
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}
	*/  
	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String argument) {
		this.msgType = argument;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;	
	}
	
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
	}

	public String getShipmentNumber() {
		return this.shipmentNumber;	
	}
	
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
	}

	public String getTotalNumberPositions() {
		return this.totalNumberPositions;
	}
	
	public void setTotalNumberPositions(String totalNumberPositions) {
		this.totalNumberPositions = Utils.checkNull(totalNumberPositions);
	}

	public String getTotalNumberPackages() {
		return this.totalNumberPackages;
	}
	
	public void setTotalNumberPackages(String totalNumberPackages) {
		this.totalNumberPackages = Utils.checkNull(totalNumberPackages);
	}

	public TransportMeans getMeansOfTransportBorder() {
		return this.meansOfTransportBorder;	
	}
	
	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		this.meansOfTransportBorder = meansOfTransportBorder;
	}

	public String getPaymentType() {
		return this.paymentType;	
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = Utils.checkNull(paymentType);
	}

	public Party getConsignor() {		
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new Party();
			}		
			consignor.setPartyTIN(consignorTIN);
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
		return this.consignee;	
	}
	
	public void setConsignee(Party party) {
		this.consignee = party;
	}

	public Party getPersonLodgingSuma() {
		if (personLodgingSumaTIN != null) { 
			if (personLodgingSuma == null) {
				personLodgingSuma = new Party();
			}
			personLodgingSuma.setPartyTIN(personLodgingSumaTIN);
		}
		return this.personLodgingSuma;	
	}
	
	public void setPersonLodgingSuma(Party party) {
		this.personLodgingSuma = party;
	}
	
	public Party getRepresentative() {
		if (representativeTIN != null) { 
			if (representative == null) {
				representative = new Party();
			}
			representative.setPartyTIN(representativeTIN);
		}
		return this.representative;
	}	
	
	public void setRepresentative(Party argument) {
		this.representative = argument;
	}
	
	public String getCustomsOfficeOfLodgment() {
		return customsOfficeOfLodgement;	
	}
	
	public void setCustomsOfficeOfLodgment(String customsOfficeOfLodgment) {
		this.customsOfficeOfLodgement = Utils.checkNull(customsOfficeOfLodgment);
	}

	public List<String> getCountryOfRoutingList() {
		return countryOfRoutingList;
	}
	
	public void addCountryOfRoutingList(String argument) {
		if (countryOfRoutingList == null) {
			countryOfRoutingList = new ArrayList<String>();
		}
		countryOfRoutingList.add(argument);
	}
	
    public List <GoodsItemLong>getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(GoodsItemLong argument) {
	   	if (this.goodsItemList == null) {
	   		this.goodsItemList = new Vector<GoodsItemLong>();
	   	}
	   	this.goodsItemList.add(argument);
	}
	public void setGoodsItemList(List <GoodsItemLong> argument) {
	   	this.goodsItemList = argument;  	
	}
	
	public String getSituationCode() {
		return this.situationCode;	
	}
	
	public void setSituationCode(String argument) {
		this.situationCode = argument;
	}
	
	public List<SealNumber> getSealIDList() {
		return sealIDList;
	}
	
	public void setSealIDList(ArrayList<SealNumber> list) {
		this.sealIDList = list;
	}	
	public void addSealIDList(SealNumber argument) {
		if (sealIDList == null) {
			sealIDList = new ArrayList<SealNumber>();
		}
		sealIDList.add(argument);
	}
	
	public SealNumber getLastSealNum() {
		// CK 2010-12-30
		// returns the last SealNumber object to fill in Language
		// has to be removed because the calling method will add the updated SealNumber again
		if (sealIDList != null) {
			int s = sealIDList.size();
			SealNumber sn = sealIDList.get(s - 1);
			sealIDList.remove(s - 1);
			return sn;
		} else {
			return null;			
		}
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
	
	public String getCustomsSubPlace() {
		return customsSubPlace;
	}

	public void setCustomsSubPlace(String customsSubPlace) {
		this.customsSubPlace = Utils.checkNull(customsSubPlace);
	}

	public String getDeclarationDate() {
		return declarationDate;
	}

	public void setDeclarationDate(String declarationDate) {
		this.declarationDate = Utils.checkNull(declarationDate);
	}

	public EFormat getDeclarationDateFormat() {
		return declarationDateFormat;
	}

	public void setDeclarationDateFormat(EFormat eFormat) {
		this.declarationDateFormat = eFormat;
	}
	
	public String getCustomsOfficeOfLodgement() {
		return customsOfficeOfLodgement;
	}

	public void setCustomsOfficeOfLodgement(String customsOfficeOfLodgement) {
		this.customsOfficeOfLodgement = Utils.checkNull(customsOfficeOfLodgement);
	}

	public String getOfficeOfExit() {
		return officeOfExit;
	}

	public void setOfficeOfExit(String officeOfExit) {
		this.officeOfExit = Utils.checkNull(officeOfExit);
	}

	public Text getLocationOfGoods() {
		return locationOfGoods;
	}

	public void setLocationOfGoods(Text locationOfGoods) {
		this.locationOfGoods = locationOfGoods;
	}
}
