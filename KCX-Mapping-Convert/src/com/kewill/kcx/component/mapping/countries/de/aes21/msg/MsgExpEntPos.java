/*
 * Function    : MsgExpEntPos.java
 * Titel       :
 * Date        : 16.03.2009
 * Author      : Kewill CSF / krzoska
 *             : 
 * Description : Contains Message Structure with all Fields used in KIDS, UIDS 
 * 				 to use for Completion Messages
 *
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 22.04.2009
 * Label       : removed Contact/DeclarantContactPerson 
 * Description : 
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgExpEntPos<br>
 * Created		: 14.09.2012<br>
 * Description	: Contains Message Structure with all Fields used in KIDS, UIDS 
 *                to use for Completion Messages.
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExpEntPos extends KCXMessage {
	
	private String msgName;	
	private String itemNumber; 	    
	private String originFederalState; 	
	private Statistic statistic;   	
	private Party consignee;
	private TIN consigneeTIN;			
	//private List <PreviousDocumentV20>previousDocumentList; 
	private List <PreviousDocumentV21>previousDocumentList;  
	private Container containers;
	//new for KIDS V2.1	begin	
	private Business  business;
	private IncoTerms incoTerms;
  	private String    addressCombination; 
  	private TIN       finalRecipientTIN;			
  	private Party     finalRecipient;    
 //new for KIDS V2.1 end
    
	public MsgExpEntPos() {
			super();
			//previousDocumentList = new Vector <PreviousDocumentV20>();
			previousDocumentList = new Vector <PreviousDocumentV21>();
	}
	
	public MsgExpEntPos(XMLEventReader parser) {
		super(parser);				
}	
	
	public MsgExpEntPos(XmlMsgScanner scanner) {
  		super(scanner);  	
  	}
	
	public MsgExpEntPos(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		setMsgName(msgName);  		
  	}
	 
	 private enum EGoodsItemTags {
		 //KIDS							UIDS		 	
			ItemNumber,					//same
			OriginFederalState,			OriginRegion,
			Statistic,					//UIDS: tags directlty within GoodsItem
										StatisticalValue, StatisticalQuantity,
                                        StatisticalBaseValue, StatisticalBaseCurrency,  //EI20100714
                                        StatisticalValueSendFlag,  						//EI20130808 AES22
			Consignee,					//same
			ConsigneeTIN,				//
			Containers,  Container,     //for NL, no Tag in UIDS
			PreviousDocument,			//same
			Business,                       Transaction,
		    IncoTerms,                      Incoterms, Incoterm,
			AddressCombination,				//same	
			FinalRecipientTIN, FinalRecipient, FinalUser,	
		}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EGoodsItemTags) tag) {					
					case Statistic:
						statistic = new Statistic(getScanner());
						statistic.parse(tag.name());
						break;
						
					case Consignee:						
						consignee = new Party(getScanner());
						consignee.parse(tag.name());
						break;
						
					case ConsigneeTIN:						
						consigneeTIN = new TIN(getScanner());
						consigneeTIN.parse(tag.name());						
						break;
						
					case PreviousDocument:
						//PreviousDocumentV20 previousDocument = new PreviousDocumentV20(getScanner());
						PreviousDocumentV21 previousDocument = new PreviousDocumentV21(getScanner());
						previousDocument.parse(tag.name());
						addPreviousDocumentList(previousDocument);
						break;
						
					case Containers:
					case Container:
						containers = new Container(getScanner());
						containers.parse(tag.name());						
						break;
						
					case FinalRecipientTIN:
						finalRecipientTIN = new TIN(getScanner());
						finalRecipientTIN.parse(tag.name());
						break;
					case FinalRecipient:
					case FinalUser:
						finalRecipient = new Party(getScanner());
						finalRecipient.parse(tag.name());
						break;
						
					case Business:
					case Transaction:
						business = new Business(getScanner());
						business.parse(tag.name());
						break;	
						
					case IncoTerms:
					case Incoterms:
					case Incoterm:
						incoTerms = new IncoTerms(getScanner());
						incoTerms.parse(tag.name());
						break;					
				default:
						return;
				}
			} else {
				switch ((EGoodsItemTags) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;
						
					case OriginFederalState:
					case OriginRegion:
						setOriginFederalState(value);
						break;
						
					case StatisticalValue: 
						setStatisticFields("StatisticalValue", value);
						break;
					case StatisticalValueSendFlag:        //EI20130808 AES22
						setStatisticFields("StatisticalValueSendFlag", value);
						break;
					case StatisticalQuantity:
						setStatisticFields("StatisticalQuantity", value);
						break;
					case StatisticalBaseValue:
						setStatisticFields("StatisticalBaseValue", value);
						break;
					case StatisticalBaseCurrency:
						setStatisticFields("StatisticalBaseCurrency", value);
						break;
					case AddressCombination:           //EI20120920
						setAddressCombination(value);
						break;
				}
			}
		}

 
	public void stoppElement(Enum tag) {
	}
		
	public Enum  <EGoodsItemTags>translate(String token) {
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
    
    public String getOriginFederalState() {
    	return originFederalState;
    }
    public void setOriginFederalState(String originFederalState) {
    	this.originFederalState = originFederalState;
    }

	public Statistic getStatistic() {
		return statistic;
	}	
	public void setStatistic(Statistic argument) {
		this.statistic = argument;
	}	
	
	public Party getConsignee() {
		return consignee;
	}
	public void setConsignee(Party argument) {
		this.consignee = argument;
	}	
		
	//public List<PreviousDocumentV20> getPreviousDocumentList() {
	public List<PreviousDocumentV21> getPreviousDocumentList() {
		return previousDocumentList;
	}	
	//public void setPreviousDocumentList(List <PreviousDocumentV20> argument) {
	public void setPreviousDocumentList(List <PreviousDocumentV21> argument) {
		this.previousDocumentList = argument;
	}	
	
	//public void addPreviousDocumentList(PreviousDocumentV20 list) {
	public void addPreviousDocumentList(PreviousDocumentV21 list) {
		if (previousDocumentList == null) {
			//previousDocumentList = new Vector <PreviousDocumentV20>();
			previousDocumentList = new Vector <PreviousDocumentV21>();
		}
		this.previousDocumentList.add(list);
	}	

	public TIN getConsigneeTIN() {
		return consigneeTIN;
	}

	public void setConsigneeTIN(TIN argument) {
		this.consigneeTIN = argument;
	}	

	public String getMsgName() {
		return msgName;	
	}

	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public Container getContainers() {
		return containers;	
	}

	public void setContainers(Container argunment) {
		this.containers = argunment;
	}
	
public Party getFinalRecipient() {
		
		if (finalRecipientTIN != null) {
			if (finalRecipient == null) {
				finalRecipient = new Party();
			}
			finalRecipient.setPartyTIN(finalRecipientTIN);
		}
		return finalRecipient;
	}
	public void setFinalRecipient(Party argument) {
		this.finalRecipient = argument;
	}
	
	public String getAddressCombination() {
		return addressCombination;
	}
	public void setAddressCombination(String value) {
		this.addressCombination = Utils.checkNull(value);
	}
	
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business argument) {
		this.business = argument;
	}
	
	public IncoTerms getIncoTerms() {
	    return incoTerms;
	}
	public void setIncoTerms(IncoTerms argument) {
		this.incoTerms = argument;
	}	
	
	private void setStatisticFields(String tag, String value) {
		if (Utils.isStringEmpty(tag)) {
			return;
		}
		if (statistic == null) { 
			statistic = new Statistic(); 
		}
		if (tag.equals("StatisticalValue")) { statistic.setStatisticalValue(value); }
		if (tag.equals("StatisticalQuantity")) { statistic.setAdditionalUnit(value); }
		if (tag.equals("StatisticalBaseValue")) { statistic.setValue(value); }
		if (tag.equals("StatisticalBaseCurrency")) { statistic.setCurrency(value); }
		if (tag.equals("StatisticalValueSendFlag")) { statistic.setStatisticalValueSendFlag(value); }  //EI20130808 AES22
	}
}




