package com.kewill.kcx.component.mapping.countries.de.ncts20.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Description		:Goods item for MsgNCTSUnloadingPermissionPos.
 * @date 			:01.09.2010    
 * @author 			pagdanganan
 * @version 		1.0.00
 */

public class MsgNCTSUnloadingPermissionPos extends KCXMessage {
	
	private String 						itemNumber;
	private CommodityCode				commodityCode;
	private String 						typeOfDeclaration;
	private String						description;
	private String						netMass;
	private String						grossMass;
	private String						missingLineIndicator;
	private String						destinationCountry;
	private String						dispatchCountry;
	private List<SpecialMention>		specialMentionList = new ArrayList<SpecialMention>();
	private List<Document>				documentList  = new ArrayList<Document>();
	private Container					containers;
	private List<Packages>				packageList = new ArrayList<Packages>();
	private List<SensitiveGoods>		sensitiveGoodsList = new ArrayList<SensitiveGoods>();
	private TIN							consignorTIN;
	private PartyNCTS						consignor;
	private TIN							consigneeTIN;
	private PartyNCTS						consignee;
	
	private boolean						debug = false;

	public MsgNCTSUnloadingPermissionPos() {
		super();
	}

	public MsgNCTSUnloadingPermissionPos(XMLEventReader parser)
			throws XMLStreamException {
		super(parser);
	}
	
	public MsgNCTSUnloadingPermissionPos(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	private enum ENCTSUnloadingPermissionPos {
		//KIDS						UIDS:
		ItemNumber,					//same
		CommodityCode, 				CommodityCodeKN8,
		TypeOfDeclaration,			//same
		Description,				GoodsDescription,
		GrossMass,					//same
		NetMass,					//same
		MissingLineIndicator,		MissingLineFlag,
		DispatchCountry,			CountryOfDispatch,
		DestinationCountry,			CountryOfDestination,
		Consignor,					//same
		ConsignorTIN,				//---
		Consignee,					//same
		ConsigneeTIN,				//---
		SpecialMentions,			SpecialMention,
		Document,					ProducedDocuments,
		Containers,					ContainerNumber,
		Package,					Packages,
		SensitiveGoods, 			SGICodes;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENCTSUnloadingPermissionPos) tag) {
			
			case CommodityCode:
				commodityCode = new CommodityCode(getScanner());
				commodityCode.parse(tag.name());
				break;
				
			case SpecialMention:
			case SpecialMentions:
				SpecialMention specialMention = new SpecialMention(getScanner());
				specialMention.parse(tag.name());	
				if (specialMentionList == null) {
					specialMentionList = new Vector <SpecialMention>();
				}
				specialMentionList.add(specialMention);				
				break;	
				
			case ProducedDocuments: 
			case Document:
				Document document = new Document(getScanner());			
				document.parse(tag.name());
				if (documentList == null) {
					documentList = new Vector <Document>(); 
				}
				documentList.add(document);
				break;
				
			case Containers:
				containers = new Container(getScanner());
				containers.parse(tag.name());
				break;
				
			case Packages:
			case Package:
				Packages tempPackage = new Packages(getScanner());
				tempPackage.parse(tag.name());
				if (packageList == null) {
					packageList = new Vector <Packages>(); 
				}
				packageList.add(tempPackage);
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
			
			case Consignor:
				consignor = new PartyNCTS(getScanner());
				consignor.parse(tag.name());				
				break;
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());			
				break;	
			
			case Consignee:
				consignee = new PartyNCTS(getScanner());
				consignee.parse(tag.name());				
				break;
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());			
				break;		
				
			default:
				return;
			}
		} else {
			switch ((ENCTSUnloadingPermissionPos) tag) {
			
			case ItemNumber:
				setItemNumber(value);
				break;
				
			case GoodsDescription:
			case Description:
				setDescription(value);
				break;
				
			case NetMass:
				setNetMass(value);
				break;
				
			case GrossMass:
				setGrossMass(value);
				break;
				
			case MissingLineFlag:
			case MissingLineIndicator:		
				setMissingLineIndicator(value);
				break;
				
			case CountryOfDestination:	
			case DestinationCountry:
				setDestinationCountry(value);
				break;
				
			case CountryOfDispatch:	
			case DispatchCountry:
				setDispatchCountry(value);
				break;
			
			case CommodityCodeKN8:
				if (commodityCode == null) {
					commodityCode = new CommodityCode();
				}
				commodityCode.setTarifCode(value);
				break;
				
			case ContainerNumber:
				if (this.containers == null) {
					containers = new Container();
				}
				containers.addNumberList(value);
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
  			return ENCTSUnloadingPermissionPos.valueOf(token);
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

	public CommodityCode getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(CommodityCode commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getTypeOfDeclaration() {
		return typeOfDeclaration;
	}

	public void setTypeOfDeclaration(String typeOfDeclaration) {
		this.typeOfDeclaration = typeOfDeclaration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getMissingLineIndicator() {
		return missingLineIndicator;
	}

	public void setMissingLineIndicator(String missingLineIndicator) {
		this.missingLineIndicator = missingLineIndicator;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getDispatchCountry() {
		return dispatchCountry;
	}

	public void setDispatchCountry(String dispatchCountry) {
		this.dispatchCountry = dispatchCountry;
	}

	public List<SpecialMention> getSpecialMentionList() {
		return specialMentionList;
	}

	public void setSpecialMentionList(List<SpecialMention> specialMentionList) {
		this.specialMentionList = specialMentionList;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

	public Container getContainers() {
		return containers;
	}

	public void setContainers(Container containers) {
		this.containers = containers;
	}

	public List<Packages> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Packages> packageList) {
		this.packageList = packageList;
	}

	public List<SensitiveGoods> getSensitiveGoodsList() {
		return sensitiveGoodsList;
	}

	public void setSensitiveGoodsList(List<SensitiveGoods> sensitiveGoodsList) {
		this.sensitiveGoodsList = sensitiveGoodsList;
	}
	
	public PartyNCTS getConsignor() {
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new PartyNCTS();
			}
			consignor.setPartyTIN(consignorTIN);
		}
		return consignor;
	}
	public void setConsignor(PartyNCTS consignor) {
		this.consignor = consignor;
	}
	
	public PartyNCTS getConsignee() {
		if (consigneeTIN != null) {
			if (consignee == null) {
				consignee = new PartyNCTS();
			}
			consignee.setPartyTIN(consigneeTIN);
		}
		return consignee;
	}
	public void setConsignee(PartyNCTS consignee) {
		this.consignee = consignee;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isEmpty() {
		/*if (Utils.isStringEmpty(this.itemNumber) &&
		Utils.isStringEmpty(this.getTypeOfDeclaration()) &&
		Utils.isStringEmpty(this.description) &&
		Utils.isStringEmpty(this.netMass) &&
		Utils.isStringEmpty(this.grossMass) &&
		Utils.isStringEmpty(this.missingLineIndicator) &&
		Utils.isStringEmpty(this.destinationCountry) &&
		Utils.isStringEmpty(this.dispatchCountry) &&
		(this.commodityCode.isEmpty()) &&
		(this.specialMentionList.size() == 0) &&
		(this.documentList.size() == 0) &&
		(this.containers.isEmpty()) &&
		(this.packageList.size() == 0) &&
		(this.sensitiveGoodsList.size() == 0) &&
		(this.consignorTIN.isEmpty()) &&
		(this.consignor.isEmpty()) &&
		(this.consigneeTIN.isEmpty()) &&
		(this.consignee.isEmpty())) {
			return false;
		} else {
			return true;
		}*/
		return false;
	}
	
}
