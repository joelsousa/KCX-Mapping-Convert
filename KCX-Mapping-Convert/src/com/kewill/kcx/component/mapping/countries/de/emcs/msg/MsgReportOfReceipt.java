package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Mode         : EMCS<br>
 * Date			: 10.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSReportOfReceipt. 
 *              : IE818 / C_DEL_DAT    
 * @author krzoska
 * @version 1.0.00
 */

public class MsgReportOfReceipt extends KCXMessage {

	private String  				referenceNumber;
	private String  				customerOrderNumber;
	private String  				clerk;
	private String  				aadReferenceCode;
	private String  				sequenceNumber;
	private String  				destinationOffice;	
	private String					dateAndTimeOfValidation;   
	private String  				dateOfArrivalOfExciseProducts;
	private String  				globalConclusionOfReceipt;
	private Text	  				complementaryInformation;
	private EmcsTrader              consignee;	
	private EmcsTrader				deliveryPlace;	
	private List <MsgReportOfReceiptPos> goodsItemList;
    
    public MsgReportOfReceipt()  {
		super();
	}

	public MsgReportOfReceipt(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

    private enum EReportOfReceipt {    	
    //KIDS                                         //UIDS
    	ReferenceNumber,							LocalReferenceNumber,
    	CustomerOrderNumber,						//missing in UIDS						
    	Clerk,										//missing in UIDS
    	AadReferenceCode,							//same 
    	SequenceNumber,                             //same           		
    	DestinationOffice,	                        //same    	    	
    	DateOfArrivalOfExciseProducts,		        //same		
    	GlobalConclusionOfReceipt,                  //same					
    	ComplementaryInformation,                   //same					 
    	Consignee,									ConsigneeTrader,    	
    	DeliveryPlace,								DeliveryPlaceTrader,
    	DateAndTimeOfValidation,   //EI20100806
    	GoodsItem;                                  //EI20100630: BodyEaad;
    }
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EReportOfReceipt) tag) {
			case Consignee:
			case ConsigneeTrader:
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());
				break;
			case DeliveryPlace:
			case DeliveryPlaceTrader:
				deliveryPlace = new EmcsTrader(getScanner());
				deliveryPlace.parse(tag.name());
				break;
			case GoodsItem:
			//case BodyEaad:
				MsgReportOfReceiptPos goodsItem = new MsgReportOfReceiptPos(getScanner());
				goodsItem.parse(tag.name());
				if (goodsItemList == null) {
					goodsItemList = new Vector<MsgReportOfReceiptPos>();
				}
				goodsItemList.add(goodsItem);
				break;				
			default:
				return;
			}
	    } else {
	    	switch ((EReportOfReceipt) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;				
			case Clerk:
				 setClerk(value);
				 break;				
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;				
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;				
			case DestinationOffice:
				 setDestinationOffice(value);
				 break;				
			case DateOfArrivalOfExciseProducts:
				 setDateOfArrivalOfExciseProducts(value);
				 break;				
			case GlobalConclusionOfReceipt:
				 setGlobalConclusionOfReceipt(value);
				 break;				
			case ComplementaryInformation:
				 //complementaryInformation = new Text(value, attr.getValue("language"));
				 complementaryInformation = new Text(value, attr);  //EI20110926
				 break;	
			case DateAndTimeOfValidation:
				setDateAndTimeOfValidation(value);
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
			return EReportOfReceipt.valueOf(token);
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

	public String getCustomerOrderNumber() {
		return customerOrderNumber;	
	}

	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = Utils.checkNull(customerOrderNumber);
	}

	public String getClerk() {
		return clerk;	
	}

	public void setClerk(String clerk) {
		this.clerk = Utils.checkNull(clerk);
	}

	public String getAadReferenceCode() {
		return aadReferenceCode;	
	}

	public void setAadReferenceCode(String aadReferenceCode) {
		this.aadReferenceCode = Utils.checkNull(aadReferenceCode);
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = Utils.checkNull(sequenceNumber);
	}

	public String getDestinationOffice() {
		return destinationOffice;	
	}

	public void setDestinationOffice(String destinationOffice) {
		this.destinationOffice = Utils.checkNull(destinationOffice);
	}

	public String getDateOfArrivalOfExciseProducts() {
		return dateOfArrivalOfExciseProducts;	
	}

	public void setDateOfArrivalOfExciseProducts(
			String dateOfArrivalOfExciseProducts) {
		this.dateOfArrivalOfExciseProducts = Utils
				.checkNull(dateOfArrivalOfExciseProducts);
	}

	public String getGlobalConclusionOfReceipt() {
		return globalConclusionOfReceipt;	
	}

	public void setGlobalConclusionOfReceipt(String globalConclusionOfReceipt) {
		this.globalConclusionOfReceipt = Utils.checkNull(globalConclusionOfReceipt);
	}

	public Text getComplementaryInformation() {
		return complementaryInformation;	
	}

	public void setComplementaryInformation(Text complementaryInformation) {
		this.complementaryInformation = complementaryInformation;
	}

	public EmcsTrader getConsignee() {
		return consignee;	
	}
	public void setConsignee(EmcsTrader argument) {
		this.consignee = argument;
	}
	
	public EmcsTrader getDeliveryPlace() {
		return deliveryPlace;	
	}
	public void setDeliveryPlace(EmcsTrader argument) {
		this.deliveryPlace = argument;
	}			
	
	public List<MsgReportOfReceiptPos> getGoodsItemList() {
		return goodsItemList;	
	}
	public void setGoodsItemList(List<MsgReportOfReceiptPos> list) {
		this.goodsItemList = list;
	}
	
	public String getDateAndTimeOfValidation() {
		return dateAndTimeOfValidation;	
	}
	public void setDateAndTimeOfValidation(String argument) {
		this.dateAndTimeOfValidation = argument;
	}	
}
