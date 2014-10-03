package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 05.06.2014<br>
 * Description	: GoodsShipment.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */

public class GoodsShipment extends KCXMessage {
		 
	 private String exportationCountryIdentifier;  
	 private String transactionNatureCode;
	 private Identifier warehouse;
	 private Identifier ucr;
	 private TradeTerm tradeTerm;
	 private Identifier entryCustomsOffice; 			//Import
	 private Identifier exitCustomsOffice;				//Export
	 private DeliveryDestination deliveryDestination;	
	 private PartyIE consignor;
	 private Consignment consignment;
	 private PartyIE consignee;
	 private String ieValueDetailsCode;					//Import
	 private String ieMethodOfPaymentCode;
	 private Identifier iePayer;
	 private ArrayList<Text> additionalInformationList;
	 private ArrayList<DocumentIE> additionalDocumentList;
	 private String transportMethodOfPaymentCode;   	//Export	 
	 private ArrayList<CustomsGoodsItem> customsGoodsItemList;
     
     public GoodsShipment() {
  		super();
  	}
     public GoodsShipment(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum EGoodsShipment {					
			ExportationCountryIdentifier,
			TransactionNatureCode,
			Warehouse,
			UCR,
			TradeTerm,
			EntryCustomsOffice,   //Import
			ExitCustomsOffice,    //Export
			DeliveryDestination,			
			CustomsGoodsItem,
			Consignor,
			Consignment,
			Consignee,
			IEValueDetailsCode,
			IEMethodOfPaymentCode,
			IEPayer,
			AdditionalInformation,
			AdditionalDocument,
			TransportMethodOfPaymentCode;
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((EGoodsShipment) tag) {
 				case CustomsGoodsItem:
 					CustomsGoodsItem item = new CustomsGoodsItem(getScanner());  	
					item.parse(tag.name());
					this.addCustomsGoodsItemList(item);
					break;  
 				case Warehouse:
 					warehouse = new Identifier(getScanner());  	
 					warehouse.parse(tag.name());
					break; 
 				case UCR:
					ucr = new Identifier(getScanner());  	
					ucr.parse(tag.name());
					break; 
 				case TradeTerm:
 					tradeTerm = new TradeTerm(getScanner());  	
 					tradeTerm.parse(tag.name());
					break; 
 				case ExitCustomsOffice:
 					exitCustomsOffice = new Identifier(getScanner());  	
 					exitCustomsOffice.parse(tag.name());
					break; 				
 				case DeliveryDestination:
 					deliveryDestination = new DeliveryDestination(getScanner());  	
 					deliveryDestination.parse(tag.name());
					break; 
 				case Consignor:
 					consignor = new PartyIE(getScanner());  	
 					consignor.parse(tag.name());
					break; 
 				case Consignment:
 					consignment = new Consignment(getScanner());  	
 					consignment.parse(tag.name());
					break;  
 				case Consignee:
 					consignee = new PartyIE(getScanner());  	
 					consignee.parse(tag.name());
					break; 
 				case IEPayer:
 					iePayer = new Identifier(getScanner());  	
 					iePayer.parse(tag.name());
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
 				case EntryCustomsOffice:
 					entryCustomsOffice = new Identifier(getScanner());  	
 					entryCustomsOffice.parse(tag.name());
 					break;
 			default:
 					return;
 			}
 		} else {
 			switch ((EGoodsShipment) tag) {
 						
 				case ExportationCountryIdentifier:
 					setExportationCountryIdentifier(value);
 					break; 				
				case TransactionNatureCode:
 					setTransactionNatureCode(value);
 					break; 
				case IEValueDetailsCode:
					setIEValueDetailsCode(value);
					break;
				case IEMethodOfPaymentCode: 				
 					setIEMethodOfPaymentCode(value);
 					break;
				case TransportMethodOfPaymentCode: 				
 					setTransportMethodOfPaymentCode(value);
 					break;
				
 			}
 		}
 	}

 	public void stoppElement(Enum tag) {
 	}

 	public Enum translate(String token) {
 		try {
 			return EGoodsShipment.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}

	
 	
	public String getExportationCountryIdentifier() {
		return exportationCountryIdentifier;
	}
	public void setExportationCountryIdentifier(String exportationCountryIdentifier) {
		this.exportationCountryIdentifier = exportationCountryIdentifier;
	}
	public String getTransactionNatureCode() {
		return transactionNatureCode;
	}
	public void setTransactionNatureCode(String transactionNatureCode) {
		this.transactionNatureCode = transactionNatureCode;
	}
	public String getIEMethodOfPaymentCode() {
		return ieMethodOfPaymentCode;
	}
	public void setIEMethodOfPaymentCode(String ieMethodOfPaymentCode) {
		this.ieMethodOfPaymentCode = ieMethodOfPaymentCode;
	}
	public String getTransportMethodOfPaymentCode() {
		return transportMethodOfPaymentCode;
	}
	public void setTransportMethodOfPaymentCode(String transportMethodOfPaymentCode) {
		this.transportMethodOfPaymentCode = transportMethodOfPaymentCode;
	}

	public Identifier getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Identifier warehouse) {
		this.warehouse = warehouse;
	}
	
	public Identifier getEntryCustomsOffice() {
		return entryCustomsOffice;
	}
	public void setEntryCustomsOffice(Identifier entryCustomsOffice) {
		this.entryCustomsOffice = entryCustomsOffice;
	}
	
	public Identifier getUcr() {
		return ucr;
	}
	public void setUcr(Identifier ucr) {
		this.ucr = ucr;
	}
	
	public TradeTerm getTradeTerm() {
		return tradeTerm;
	}
	public void setTradeTerm(TradeTerm tradeTerm) {
		this.tradeTerm = tradeTerm;
	}
	
	public Identifier getExitCustomsOffice() {
		return exitCustomsOffice;
	}
	public void setExitCustomsOffice(Identifier exitCustomsOffice) {
		this.exitCustomsOffice = exitCustomsOffice;
	}
	
	public DeliveryDestination getDeliveryDestination() {
		return deliveryDestination;
	}
	public void setDeliveryDestination(DeliveryDestination deliveryDestination) {
		this.deliveryDestination = deliveryDestination;
	}
	
	public PartyIE getConsignor() {
		return consignor;
	}
	public void setConsignor(PartyIE consignor) {
		this.consignor = consignor;
	}
	
	public Consignment getConsignment() {
		return consignment;
	}
	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
	}
	
	public PartyIE getConsignee() {
		return consignee;
	}
	public void setConsignee(PartyIE consignee) {
		this.consignee = consignee;
	}
	
	public String getIEValueDetailsCode() {
		return ieValueDetailsCode;
	}
	public void setIEValueDetailsCode(String value) {
		this.ieValueDetailsCode = value;
	}
	
	public String getIeMethodOfPaymentCode() {
		return ieMethodOfPaymentCode;
	}
	public void setIeMethodOfPaymentCode(String ieMethodOfPaymentCode) {
		this.ieMethodOfPaymentCode = ieMethodOfPaymentCode;
	}
	
	public Identifier getIePayer() {
		return iePayer;
	}
	public void setIePayer(Identifier iePayer) {
		this.iePayer = iePayer;
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
	
	public ArrayList<CustomsGoodsItem> getCustomsGoodsItemList() {
		return customsGoodsItemList;
	}
	public void setCustomsGoodsItemList(ArrayList<CustomsGoodsItem> list) {
		customsGoodsItemList = list;
	}
	public void addCustomsGoodsItemList(CustomsGoodsItem text) {
		if (customsGoodsItemList == null) {
			customsGoodsItemList = new ArrayList<CustomsGoodsItem>();
		}
		customsGoodsItemList.add(text);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(exportationCountryIdentifier) && Utils.isStringEmpty(transactionNatureCode) && 
				Utils.isStringEmpty(ieMethodOfPaymentCode) && Utils.isStringEmpty(transportMethodOfPaymentCode) &&
				customsGoodsItemList == null);
		//usw.
	}	
}
