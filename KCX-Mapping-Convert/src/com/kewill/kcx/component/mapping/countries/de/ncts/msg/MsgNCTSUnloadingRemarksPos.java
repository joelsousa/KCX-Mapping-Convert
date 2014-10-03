package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;


import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.ResultsOfControl;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Description		:Goods item for MsgNCTSUnloadingRemarksPos.
 * @date 			:09.01.2010    
 * @author 			Jude Eco
 * @version 		1.0.00
 */

public class MsgNCTSUnloadingRemarksPos extends KCXMessage {
	private String type;
	private String itemNumber;
	private CommodityCode commodityCode;
	private String goodsDescription;
	private String grossMass;
	private String netMass;
	private String missingLineFlag;
	private List<Document> listProducedDocuments = new ArrayList<Document>();
	private ResultsOfControl resultsOfControl;
	private Container container;					//added for FSStoKids conversion
	private List<Packages> listPackages = new ArrayList<Packages>();
	private List<SensitiveGoods> sensitiveGoodsList = new ArrayList <SensitiveGoods>();

	private boolean debug   = false;
	private XMLEventReader parser = null;
	
	private enum EMsgNCTSUnloadingRemarksPos {
		//UIDS						//KIDS
		ItemNumber,					//same
		CommodityCodeKN8,			CommodityCode,
		GoodsDescription,			Description,
		GrossMass,					//same
		NetMass,					//same
		MissingLineFlag,			MissingLineIndicator,
		ProducedDocuments,			Document,
		ContainerNumber,			Containers,
		ResultsOfControl,			//same
		Packages,					Package,
		SGICodes,					SensitiveGoods;
	}
	
	public MsgNCTSUnloadingRemarksPos() {
		super();
	}

	public MsgNCTSUnloadingRemarksPos(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public MsgNCTSUnloadingRemarksPos(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EMsgNCTSUnloadingRemarksPos) tag) {
			case ProducedDocuments:
			case Document:
				Document tempDocument = new Document(getScanner());
				tempDocument.parse(tag.name());
				listProducedDocuments.add(tempDocument);
				break;
				
			case ResultsOfControl:
				ResultsOfControl tempResultsOfControl = new ResultsOfControl(getScanner());
				tempResultsOfControl.parse(tag.name());
				setResultsOfControl(tempResultsOfControl);
				break;
			
			case Package:
			case Packages:
				Packages tempPackages = new Packages(getScanner());
				tempPackages.parse(tag.name());
				listPackages.add(tempPackages);
				break;
			
			case SGICodes:
			case SensitiveGoods:
				SensitiveGoods tempSensitiveGoods = new SensitiveGoods(getScanner());
				tempSensitiveGoods.parse(tag.name());
				if (sensitiveGoodsList == null) {
					sensitiveGoodsList = new Vector <SensitiveGoods>();
				}
				sensitiveGoodsList.add(tempSensitiveGoods);
				break;
			
			case CommodityCode:
				CommodityCode tempCommodityCode = new CommodityCode(getScanner());
				tempCommodityCode.parse(tag.name());
				setCommodityCode(tempCommodityCode);
				break;
			
			case Containers:
				container = new Container(getScanner());  //EE20100914
				container.parse(tag.name());
				setContainer(container);
				break;
				
			default:
				return;
			} 
		} else {
			switch((EMsgNCTSUnloadingRemarksPos) tag) {
			case ItemNumber:
				setItemNumber(value);
				break;
				
			case CommodityCodeKN8:
				if (commodityCode == null) {
					commodityCode = new CommodityCode();
				}
				commodityCode.setTarifCode(value);
				break;
				
			case GoodsDescription:
			case Description:
				setGoodsDescription(value);
				break;
				
			case GrossMass:
				setGrossMass(value);
				break;
				
			case NetMass:
				setNetMass(value);
				break;
				
			case MissingLineFlag:
			case MissingLineIndicator:
				setMissingLineFlag(value);
				break;
				
			case ContainerNumber:
				if (getContainer() == null) {
					setContainer(new Container());
				}
				getContainer().addNumberList(value);
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
  			return EMsgNCTSUnloadingRemarksPos.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(String grossMass) {
		this.grossMass = grossMass;
	}

	public String getNetMass() {
		return netMass;
	}

	public void setNetMass(String netMass) {
		this.netMass = netMass;
	}

	public String getMissingLineFlag() {
		return missingLineFlag;
	}

	public void setMissingLineFlag(String missingLineFlag) {
		this.missingLineFlag = missingLineFlag;
	}

	public List<Document> getListProducedDocuments() {
		return listProducedDocuments;
	}

	public void setListProducedDocuments(List<Document> listProducedDocuments) {
		this.listProducedDocuments = listProducedDocuments;
	}

	public ResultsOfControl getResultsOfControl() {
		return resultsOfControl;
	}

	public void setResultsOfControl(ResultsOfControl resultsOfControl) {
		this.resultsOfControl = resultsOfControl;
	}

	public List<Packages> getListPackages() {
		return listPackages;
	}

	public void setListPackages(List<Packages> listPackages) {
		this.listPackages = listPackages;
	}

	public List<SensitiveGoods> getSensitiveGoodsList() {
		return sensitiveGoodsList;
	}

	public void setSensitiveGoodsList(List<SensitiveGoods> listSGICodes) {
		this.sensitiveGoodsList = listSGICodes;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}


	public CommodityCode getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(CommodityCode commodityCode) {
		this.commodityCode = commodityCode;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public boolean isEmpty() {    //EE20100914
//		return Utils.isStringEmpty(this.itemNumber) &&
//	    	   Utils.isStringEmpty(this.goodsDescription) &&
//	    	   Utils.isStringEmpty(this.grossMass) &&
//	    	   Utils.isStringEmpty(this.netMass) &&
//	    	   Utils.isStringEmpty(this.missingLineFlag) &&
//	    	   this.listProducedDocuments.isEmpty() &&
//	    	   this.resultsOfControl.isEmpty() &&
//	    	   this.listPackages.isEmpty() &&
//	    	   this.listSGICodes.isEmpty();
		return false;
	}

}
