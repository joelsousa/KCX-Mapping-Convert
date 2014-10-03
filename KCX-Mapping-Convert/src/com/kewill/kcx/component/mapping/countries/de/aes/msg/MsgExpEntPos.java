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

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgExpEntPos<br>
 * Erstellt		: 16.03.2009<br>
 * Beschreibung	: Contains Message Structure with all Fields used in KIDS, UIDS 
 *                to use for Completion Messages.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MsgExpEntPos extends KCXMessage {
	
	private String msgName;	
	private String itemNumber; 	    
	private String originFederalState; 	
	private Statistic statistic;   	
	private Party consignee;
	private TIN consigneeTIN;	
	private PreviousDocument previousDocument;      
	private List <PreviousDocument>previousDocumentList;  
	private Container containers;
	
	public MsgExpEntPos() {
			super();
			previousDocumentList = new Vector <PreviousDocument>();
	}
	
	public MsgExpEntPos(XMLEventReader parser) {
		super(parser);		
		previousDocumentList = new Vector <PreviousDocument>();
}	
	
	public MsgExpEntPos(XmlMsgScanner scanner) {
  		super(scanner);
  		previousDocumentList = new Vector <PreviousDocument>();
  	}
	
	public MsgExpEntPos(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		setMsgName(msgName);
  		previousDocumentList = new Vector <PreviousDocument>();
  	}
	 
	 private enum EGoodsItemTags {
		 //KIDS							UIDS		 	
			ItemNumber,					//same
			OriginFederalState,			OriginRegion,
			Statistic,					//UIDS: tags directlty within GoodsItem
										StatisticalValue, StatisticalQuantity,
                                        StatisticalBaseValue, StatisticalBaseCurrency,  //EI20100714
			Consignee,					//same
			ConsigneeTIN,				//
			Containers,  Container,     //for NL, no Tag in UIDS
			PreviousDocument,			//same					  
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
						previousDocument = new PreviousDocument(getScanner());
						previousDocument.parse(tag.name());
						previousDocumentList.add(previousDocument);
						break;
						
					case Containers:
					case Container:
						containers = new Container(getScanner());
						containers.parse(tag.name());						
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
					case StatisticalQuantity:
						setStatisticFields("StatisticalQuantity", value);
						break;
					case StatisticalBaseValue:
						setStatisticFields("StatisticalBaseValue", value);
						break;
					case StatisticalBaseCurrency:
						setStatisticFields("StatisticalBaseCurrency", value);
						break;
					default:
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
	
	public PreviousDocument getPreviousDocument() {
		return previousDocument;
	}
	public void setPreviousDocument(PreviousDocument argument) {
		this.previousDocument = argument;
	}
	
	public List<PreviousDocument> getPreviousDocumentList() {
		return previousDocumentList;
	}	
	public void setPreviousDocumentList(List <PreviousDocument> argument) {
		this.previousDocumentList = argument;
	}	
	
	public void addPreviousDocumentList(PreviousDocument argument) {
		this.previousDocumentList.add(argument);
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
			
	public void setStatisticFields(String tag, String value) {
		if (statistic == null) { statistic = new Statistic(); }
		
		if (tag.equals("StatisticalValue")) { statistic.setStatisticalValue(value); }
		if (tag.equals("StatisticalQuantity")) { statistic.setAdditionalUnit(value); }
		if (tag.equals("StatisticalBaseValue")) { statistic.setValue(value); }
		if (tag.equals("StatisticalBaseCurrency")) { statistic.setCurrency(value); }
	}
}




