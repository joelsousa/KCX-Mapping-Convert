package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgSplitDetailsPos;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 27.07.2011<br>
 * Description  : Contains the Member for save tags from the KIDS/UIDS message   
 * 				: for SplitDetailsEAD.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class SplitDetailsEAD extends KCXMessage {
	
	private String  				referenceNumber;
	private String  				journeyTime;
	private String  				changedTransportArrangement;
	private String  				destinationTypeCode;
	private String  				deliveryPlaceCustomsOffice;
	private EmcsTrader  			consignee;
	private EmcsTrader  			deliveryPlace;
	private EmcsTrader 				transportArranger;
	private EmcsTrader 				transporter;	
	private List<TransportDetails>  transportDetailsList;   
    private List<MsgSplitDetailsPos> goodsItemList;
   
	private enum ESplitDetailsEAD {
		//KIDS                                         //UIDS
    	ReferenceNumber,							LocalReferenceNumber,    					
    	JourneyTime,                                //same
    	ChangedTransportArrangement,				//same
    	DestinationTypeCode,					    ChangedDestination, //ChangedDestination-DestinationTypeCode
    	DeliveryPlaceCustomsOffice,                 //ChangedDestination-DeliveryPlaceCustomsOffice
    	NewConsignee,                               //ChangedDestination-NewConsigneeTrader,
    	DeliveryPlace,                              //ChangedDestination-DeliveryPlaceTrader,
    	NewTransportArranger,						NewTransportArrangerTrader,
    	NewTransporter,								NewTransporterTrader,
    	TransportDetails,							//same    	
    	GoodsItem,									BodyEaad;
	}

	public SplitDetailsEAD() {
  		super();
  	}
	 
	public SplitDetailsEAD(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((ESplitDetailsEAD) tag) {
			case NewConsignee:			
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());
				break;
			case ChangedDestination:			
				ChangedDestination changedDestination = new ChangedDestination(getScanner());
				changedDestination.parse(tag.name());
				setTagsFromChangedDestination(changedDestination);
				break;
			case DeliveryPlace:			
				deliveryPlace = new EmcsTrader(getScanner());
				deliveryPlace.parse(tag.name());
				break;
			case NewTransportArranger:
			case NewTransportArrangerTrader:
				transportArranger = new EmcsTrader(getScanner());
				transportArranger.parse(tag.name());
				break;
			case NewTransporter:
			case NewTransporterTrader:
				transporter = new EmcsTrader(getScanner());
				transporter.parse(tag.name());
				break;
			case TransportDetails:
				TransportDetails transportDetails = new TransportDetails(getScanner());
				transportDetails.parse(tag.name());
				if (transportDetailsList == null) {
					transportDetailsList = new Vector <TransportDetails>();
				}
				transportDetailsList.add(transportDetails);
				break;
			case GoodsItem:
			case BodyEaad:
				MsgSplitDetailsPos goodsItem = new MsgSplitDetailsPos(getScanner());
				goodsItem.parse(tag.name());
				if (goodsItemList == null) {
					goodsItemList = new Vector <MsgSplitDetailsPos>();
				}
				goodsItemList.add(goodsItem);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((ESplitDetailsEAD) tag) {
	    	case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;
			case JourneyTime:
				 setJourneyTime(value);
				 break;				
			case ChangedTransportArrangement:
				 setChangedTransportArrangement(value);
				 break;	
	    	case DestinationTypeCode:
	    		 setDestinationTypeCode(value);
				 break;
			case DeliveryPlaceCustomsOffice:			
				 setDeliveryPlaceCustomsOffice(value);
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
  			return ESplitDetailsEAD.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getReferenceNumber() {
		return referenceNumber;	
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
	}

	public String getJourneyTime() {
		return journeyTime;	
	}

	public void setJourneyTime(String value) {
		this.journeyTime = value;
	}

	public String getChangedTransportArrangement() {
		return changedTransportArrangement;	
	}

	public void setChangedTransportArrangement(String value) {
		this.changedTransportArrangement = value;
	}

	public String getDestinationTypeCode() {
		return destinationTypeCode;	
	}

	public void setDestinationTypeCode(String value) {
		this.destinationTypeCode = value;
	}
	
	public String getDeliveryPlaceCustomsOffice() {
		return this.deliveryPlaceCustomsOffice;
	}
	public void setDeliveryPlaceCustomsOffice(String value) {
		this.deliveryPlaceCustomsOffice = value;
	}
	
	public EmcsTrader getNewConsignee() {
		return consignee;	
	}

	public void setNewConsignee(EmcsTrader consignee) {
		this.consignee = consignee;
	}
	
	public EmcsTrader getDeliveryPlace() {
		return deliveryPlace;	
	}

	public void setDeliveryPlace(EmcsTrader deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

	public EmcsTrader getNewTransportArranger() {
		return transportArranger;	
	}

	public void setNewTransportArranger(EmcsTrader transportArranger) {
		this.transportArranger = transportArranger;
	}

	public EmcsTrader getNewTransporter() {
		return transporter;	
	}
	public void setNewTransporter(EmcsTrader trader) {
		this.transportArranger = trader;
	}
	
	public List<TransportDetails> getTransportDetailsList() {
		return transportDetailsList;	
	}
	public void setTransportDetailsList(List<TransportDetails> list) {
		this.transportDetailsList = list;
	}
	
	public List<MsgSplitDetailsPos> getGoodsItemList() {
		return goodsItemList;	
	}
	public void setGoodsItemList(List<MsgSplitDetailsPos> list) {
		this.goodsItemList = list;
	}
	
	public void setTagsFromChangedDestination(ChangedDestination changedDestination) {
		if (changedDestination == null) { 
			return;
		}
		if (changedDestination.isEmpty()) { 
			return;
		}
		destinationTypeCode = changedDestination.getDestinationTypeCode();
    	deliveryPlaceCustomsOffice = changedDestination.getDeliveryPlaceCustomsOffice();
    	consignee = changedDestination.getNewConsignee();
    	deliveryPlace = changedDestination.getDeliveryPlace();
	}
	
	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.referenceNumber) &&
			Utils.isStringEmpty(this.journeyTime) &&	
			Utils.isStringEmpty(this.destinationTypeCode) &&
			Utils.isStringEmpty(this.deliveryPlaceCustomsOffice) &&
    		Utils.isStringEmpty(this.changedTransportArrangement));			
	}	
}
