/*
 * Function    : MsgExpDatPos.java
 * Titel       :
 * Date        : 17.03.2009
 * Author      : Kewill CSF / messer
 * Description : Contains Goodsitem Data with all Fields used in KIDS and UIDS,
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : 
 * Date        : 15.03.2010 
 * Label       : AK20100315
 * Description : Removed definition of SpecialMention and Permit in case 
 *
 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       :
 * Description : 
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Notifications;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgExpDatPosCH<br>
 * Erstellt		: 17.03.2009<br>
 * Beschreibung	: Contains Goodsitem Data with all Fields used in KIDS and UIDS. 
 * 
 * @author messer
 * @version 1.0.00
 */
public class MsgExpDatPosCH extends KCXMessage {
	private String msgName;
	
	private String itemNumber; 	
	private String description; 	
	private String annotation; 		
	private String netMass;						//n(11,3)	
	private String grossMass; 					//n(11,3)	
	private String kindOfArticle; 
	private String typeOfArticle; 
	private String typeOfDeclaration; 	
	//private Packages packages;
	private List <Packages>packagesList;
	private Container container;
	//private Permit permit;
	private List <Permit> permitList;	
	private List <Document>documentList; 
	//private PreviousDocument previousDocument;      
	private List <PreviousDocument>previousDocumentList;  		
	
	private SensitiveGoods sensitiveGoods; 
	//private SpecialMention specialMention; 	
	private List <SpecialMention> specialMentionList;
	private NonCustomsLaw nonCustomsLaw;
	private Notifications notifications;
	private CommodityCode commodityCode;
	private Statistic statistic;
	
	private boolean debug;

    
	public MsgExpDatPosCH() {
			super();		
	}
	
	public MsgExpDatPosCH(XMLEventReader parser) {
		super(parser);				
}	
	
	public MsgExpDatPosCH(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}
	
	public MsgExpDatPosCH(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		setMsgName(msgName);  		
  	}

	 private enum EGoodsItemTags {
			//Kids							//Uids
		 	ItemNumber,						//same			
			Description,					GoodsDescription,			
			Annotation,						Remark,		
			NetMass,						//same			
			GrossMass,						//same	
			CommodityCode,					CommodityCodeKN8,
			CommodityCodeTARIC,
			CommodityCodeFirstAddition,
			CommodityCodeSecondAddition,
			CommodityCodeNationalAddition,			
			Package,						Packaging,
			Container,						ContainerRegistration,
			Statistic,						StatisticalValue,
											StatisticalQuantity,
											StatisticalBaseValue,
											StatisticalBaseCurrency,
			Containers,			
			Document,						ProducedDocument,			
			PreviousDocument,				//same						
			KindOfArticle,					SpecialRemark, //SpecialRemark.AdditionalCode
			TypeOfArticle,					EdecItem, // EdecItem.CleranceCode
			TypeOfDeclaration,				//EdecItem.CleranceTypeCode	
            SpecialMention,                 //EdecItem: SpecialMentions, ECExportIdentifier, ExportCountry
            Permit,                         //EdecItem.Allowance. Category; Reference, Date, Remark
			NonCustomsLaw,         			SpecialRemarks, // SpecialRemarks.RestrictionCode
			Notifications,                    //TODO
			SensitiveGoods,   				SpecialGoodsInformation;
		}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EGoodsItemTags) tag) {					
						
				 	case CommodityCode:
				 		commodityCode = new CommodityCode(getScanner());
				 		commodityCode.parse(tag.name());
				 		break;
				 		
				 	case Statistic:
						statistic = new Statistic(getScanner());
						statistic.parse(tag.name());
						break;
					
					case SensitiveGoods:
						sensitiveGoods = new SensitiveGoods(getScanner());
						sensitiveGoods.parse(tag.name());
						break;
						
					case SpecialMention:
						//AK20100315
						SpecialMention specialMention = new SpecialMention(getScanner());
						specialMention.parse(tag.name());
						if (specialMentionList == null) {
							specialMentionList = new Vector <SpecialMention>();
				        }
						addSpecialMentionList(specialMention);
						break;
						
					case Permit:
						//AK20100315
						Permit permit = new Permit(getScanner());
						permit.parse(tag.name());
						if (permitList == null) {
							permitList = new Vector <Permit>();
						}
						addPermitList(permit);
						break;									
						
					case Package:
					case Packaging:
						Packages packages = new Packages(getScanner());
						packages.parse(tag.name());
						addPackagesList(packages);
						break;
						
					case Container:
					case Containers:              
						container = new Container(getScanner());
						container.parse(tag.name());
						//EI20090415: addContainerList(container);
						break;
						
					case Document:
					case ProducedDocument:
						Document document = new Document(getScanner(), "K");
						document.parse(tag.name());
						addDocumentList(document);
						break;
						
					case PreviousDocument:
						PreviousDocument previousDocument = new PreviousDocument(getScanner());
						previousDocument.parse(tag.name());
						addPreviousDocumentList(previousDocument);
						break;
						
					case EdecItem:
						EdecItem edecItem = new EdecItem(getScanner());
						edecItem.parse(tag.name());
						setFromEdecItem(edecItem);
						break;	
						
					case NonCustomsLaw:
					case SpecialRemarks:
						nonCustomsLaw = new NonCustomsLaw(getScanner());
						nonCustomsLaw.parse(tag.name());
						break;
						
					case Notifications:
						notifications = new Notifications(getScanner());
						notifications.parse(tag.name());
						break;						
												
				default:
						return;
				}
			} else {
				switch ((EGoodsItemTags) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;
						
					
					case Description:
					case GoodsDescription:
						setDescription(value);
						break;	
						
					
					case Annotation:
						setAnnotation(value);
						break;
						
					case Remark:
						setAnnotation(value);						
						break;							
											
					case NetMass:
						setNetMass(value);
						break;	
											
					case GrossMass:
						setGrossMass(value);
						break;

					case KindOfArticle:
						setKindOfArticle(value);
						break;
						
					case TypeOfArticle:
						setTypeOfArticle(value);
						break;
						
					case TypeOfDeclaration:
						setTypeOfDeclaration(value);
						break;	
						
					case CommodityCodeKN8:
						if (commodityCode == null) {
							commodityCode = new CommodityCode();
						}
						commodityCode.setTarifCode(value);
						break;
					case CommodityCodeTARIC:
						if (commodityCode == null) {
							commodityCode = new CommodityCode();
						}
						commodityCode.setEUTarifCode(value);
						break;
					case CommodityCodeFirstAddition:
						if (commodityCode == null) {
							commodityCode = new CommodityCode();
						}
						commodityCode.setTarifAddition1(value);
						break;
					case CommodityCodeSecondAddition:
						if (commodityCode == null) {
							commodityCode = new CommodityCode();
						}
						commodityCode.setTarifAddition2(value);
						break;
					case CommodityCodeNationalAddition:
						if (commodityCode == null) {
							commodityCode = new CommodityCode();
						}
						commodityCode.setAddition(value);
						break;
						
					case StatisticalValue:
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setStatisticalValue(value);
						break;
						
					case StatisticalQuantity:
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setAdditionalUnit(value);
						break;
						
					case StatisticalBaseValue:
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setValue(value);
						break;
					case StatisticalBaseCurrency:
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setCurrency(value);
						break;
						
					case ContainerRegistration:
						if (container == null) {
							container = new Container();
						}
						container.addNumberList(value);
						//EI20090415: addContainerList(container);
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
   
    public String getDescription() {
    	return description;
    }
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getAnnotation() {
    	return annotation;
    }
    public void setAnnotation(String argument) {
    	this.annotation = argument;
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

	public String getKindOfArticle() {
		return kindOfArticle;
	}
	public void setKindOfArticle(String kindOfArticle) {
		this.kindOfArticle = kindOfArticle;
	}
	public String getTypeOfArticle() {
		return typeOfArticle;
	}
	public void setTypeOfArticle(String typeOfArticle) {
		this.typeOfArticle = typeOfArticle;
	}
	public String getTypeOfDeclaration() {
		return typeOfDeclaration;
	}
	public void setTypeOfDeclaration(String typeOfDeclaration) {
		this.typeOfDeclaration = typeOfDeclaration;
	}	
		
	public List <Packages>getPackagesList() {
		return packagesList;
	}
	public void setPackagesList(List <Packages>argument) {
		this.packagesList  = argument;
	}
	public void addPackagesList(Packages argument) {
		if (packagesList == null) {
			packagesList = new Vector<Packages>();
		}
		this.packagesList.add(argument);
	}	
	
	public Container getContainer() {
		return container;
	}	
	public void setContainer(Container argument) {
		this.container = argument;
	}	
			
	public List<PreviousDocument> getPreviousDocumentList() {
		return previousDocumentList;
	}	
	public void setPreviousDocumentList(List <PreviousDocument> argument) {
		this.previousDocumentList = argument;
	}	
	
	public void addPreviousDocumentList(PreviousDocument argument) {
		if (previousDocumentList == null) {
			previousDocumentList = new Vector<PreviousDocument>();
		}
		previousDocumentList.add(argument);
	}
		
	public void setSensitiveGoods(SensitiveGoods argument) {
		this.sensitiveGoods = argument;
	}		
	public SensitiveGoods getSensitiveGoods() {
		return sensitiveGoods;
	}
	
	public void addSpecialMentionList(SpecialMention argument) {
		if (specialMentionList == null) {
			specialMentionList  = new Vector<SpecialMention>();
		}
		specialMentionList .add(argument);
	}

	
	public void setSpecialMentionList(List <SpecialMention> argument) {
		this.specialMentionList = argument;
	}		
	public List <SpecialMention> getSpecialMentionList() {
		return specialMentionList;
	}

	public void addPermitList(Permit argument) {
		if (permitList == null) {
			permitList = new Vector<Permit>();
		}
		permitList.add(argument);
	}

/*	public void setPermit(Permit argument) {
		this.permit = argument;
	}	
	
	public Permit getPermit() {
		return permit;
	}		
*/
	public List <Document>getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List <Document>documentList) {
		this.documentList = documentList;
	}

	public void addDocumentList(Document argument) {
		if (documentList == null) {
			documentList = new Vector<Document>();
		}
		documentList.add(argument);
	}

	public List<Permit> getPermitList() {
		return permitList;
	
	}
	public void setPermitList(List<Permit> permitList) {
		this.permitList = permitList;
	}
	public String getMsgName() {
		return msgName;	
	}

	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}
	
	private void setFromEdecItem(EdecItem edecItem) {
		if (edecItem == null) {
			return; 
		}
				
		typeOfArticle = edecItem.getCleranceCode();
		typeOfDeclaration = edecItem.getCleranceTypeCode();	
		if (specialMentionList == null) {
			specialMentionList = new Vector <SpecialMention>();
		}
		SpecialMention specialMention = new SpecialMention();
		specialMention.setTypeOfExport(edecItem.getSpecialMentions());  
		specialMention.setExportFromEU(edecItem.getECExportIdentifier());
		specialMention.setExportFromCountry(edecItem.getExportCountry());
		addSpecialMentionList(specialMention);
		
		//AK20100316
		//if (permit == null)
			//permit = new Permit();
		//permit = edecItem.getAllowance();
		
		Permit 	permit = edecItem.getAllowance(); 
		if (permitList == null) {
			permitList = new Vector <Permit>();
		}
		addPermitList(permit);
	}
	
	public NonCustomsLaw getNonCustomsLaw() {
		return nonCustomsLaw;
	}
	public void setNonCustomsLaw(NonCustomsLaw argument) {
		this.nonCustomsLaw = argument;
	}
	
	public Notifications getNotifications() {
		return notifications;
	
	}

	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}
	
	public Statistic getStatistic() {
		return statistic;
	}	
	public void setStatistic(Statistic argument) {
		this.statistic = argument;
	}
	
	public CommodityCode getCommodityCode() {
			return commodityCode;
	}
		
	public void setCommodityCode(CommodityCode argument) {
			this.commodityCode = argument;		
	}	
}




