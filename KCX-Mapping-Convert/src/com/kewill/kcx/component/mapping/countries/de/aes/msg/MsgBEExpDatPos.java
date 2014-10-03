/*
 * Function    : MsgBEExpDatPos.java
 * Titel       :
 * Date        : 17.12.2009
 * Author      : Kewill CSF / krzoska
 * Description : Contains Goodsitem Data with all Fields used in KIDS and UIDS,
 * Parameters  : 

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
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgBEExpDatPos<br>
 * Erstellt		: 17.12.2009<br>
 * Beschreibung	: Contains Goodsitem Data with all Fields used in KIDS and UIDS.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MsgBEExpDatPos extends KCXMessage {
	private String msgName;
	
	private String itemNumber; 
	private String articleNumber; 
	private String description; 
	private String uCROtherSystem; 
	private String annotation; 
	private String annotation2;                //EI20090409
	private String shipmentNumber; 
	private String originFederalState; 
	private String netMass;						//n(11,3)	
	private String grossMass; 					//n(11,3)	
	private String dangerousGoodsNumber; 
	private String paymentType; 
	private String descriptionLanguage;
    private Party warehouseKeeper;
    private TIN warehouseKeeperTIN;
	private String destinationCountry;
	private CommodityCode commodityCode;  
	private ExportRefundItem exportRefundItem;
	private ApprovedTreatment approvedTreatment;
	private Statistic statistic;
	private Party consignee;
	private TIN consigneeTIN;
	
	private Party consignor;
	private TIN consignorTIN;

	//private Packages packages;
	private List <Packages>packagesList;
	private Container container;
	//private List <Container>containerList;  //List ist replaced with container (includs Numberlist)	
	//private Document document;
	private List <Document>documentList; 
	//private PreviousDocument previousDocument;      
	private List <PreviousDocument>previousDocumentList;  		
	private Completion bondedWarehouseCompletion;   //EI20090305
	private Completion inwardProcessingCompletion;  //EI20090305
	
	private WareHouse wareHouse;   
	

	//for CH only:
	private String kindOfArticle;        //CH
	private String typeOfArticle;        //CH
	private String typeOfDeclaration; 
	private SensitiveGoods sensitiveGoods; 
	private SpecialMention specialMention; 
	private List <SpecialMention> specialMentionList;
	private NonCustomsLaw nonCustomsLaw;
	private Permit permit;
	//private List <Permit> permitList;

	private String restitutionProcedure;    //nur bei UIDS   		
	private boolean debug;

    
	public MsgBEExpDatPos() {
			super();
			//createLists();
	}
	
	public MsgBEExpDatPos(XMLEventReader parser) {
		super(parser);		
		//createLists();
}	
	
	public MsgBEExpDatPos(XmlMsgScanner scanner) {
  		super(scanner);
  		//createLists();
  	}
	
	public MsgBEExpDatPos(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		setMsgName(msgName);
  		//createLists();
  	}

   /*
	private void createLists() {
		
		
		packagesList = new Vector <Packages>();
		containerList = new Vector<Container>();		
		previousDocumentList = new Vector <PreviousDocument>();
		documentList = new Vector <Document>();
		permitList =  new Vector <Permit>();
		specialMentionList = new Vector <SpecialMention>();
	}
	*/ 
	 private enum EGoodsItemTags {
			//Kids							//Uids
		 	ItemNumber,						//same
			ArticleNumber,					//same
			Description,					GoodsDescription,
			UCROtherSystem,					ExternalRegistrationNumber,
			Annotation,						Remark,
			Annotation2,					//same
			ShipmentNumber,					CommercialReferenceNumber,
			OriginFederalState,				OriginRegion,
			NetMass,						//same			
			GrossMass,						//same
			//AK20091222
			WarehouseKeeperTIN,
			WarehouseKeeper,
			DangerousGoodsNumber,			UNDGCode,
			PaymentType,					TransportPaymentMethod,
			DescriptionLanguage,
			DestinationCountry,
			CommodityCode,					CommodityCodeKN8,
											CommodityCodeTARIC,
											CommodityCodeFirstAddition,
											CommodityCodeSecondAddition,
											CommodityCodeNationalAddition,
			ExportRefundItem,				ExportRestitutionItem,
			CustomsApprovedTreatment,		Procedure,
			Statistic,						StatisticalValue,
											StatisticalQuantity,
											StatisticalBaseValue,
											StatisticalBaseCurrency,
			Consignee,						//same
			ConsigneeTIN,
			Package,						Packaging,
			Container,						ContainerRegistration,
			Containers,			
			Document,						ProducedDocument,			
			PreviousDocument,				//same			
			BondedWarehouseCompletion,      WriteOffZL,
			InwardProcessingCompletion,     WriteOffAVUV,
			
			KindOfArticle,                  SpecialRemark, //CH: SpecialRemark.AdditionalCode
			TypeOfArticle,					EdecItem, // CH: EdecItem.CleranceCode
			TypeOfDeclaration,				//EdecItem.CleranceTypeCode	
			SpecialMention,                 //EdecItem: SpecialMentions, ECExportIdentifier, ExportCountry
            Permit,                         //EdecItem.Allowance. Category; Reference, Date, Remark
			NonCustomsLaw,         			SpecialRemarks, //CH: SpecialRemarks.RestrictionCode
			SensitiveGoods,   				SpecialGoodsInformation, //CH
			Consignor,
			ConsignorTIN,
			WareHouse;
		}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EGoodsItemTags) tag) {
									
					case CommodityCode:
						commodityCode = new CommodityCode(getScanner());
						commodityCode.parse(tag.name());
						break;
						
					case ExportRefundItem:	//Kids2Uids,					
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());
						break;
					case ExportRestitutionItem:   //Uisd2Kids, restitutionProcedure should fill approvedTreatment.codeForRefund 						                          
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());
						restitutionProcedure = exportRefundItem.getRestitutionProcedure();
						break;						
						
					case CustomsApprovedTreatment:
					case Procedure:	
						approvedTreatment = new ApprovedTreatment(getScanner());
						approvedTreatment.parse(tag.name());
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
						SpecialMention specialMention = new SpecialMention(getScanner());
						specialMention.parse(tag.name());
						addSpecialMentionList(specialMention);
						break;

					case Permit:
						Permit permit = new Permit(getScanner());
						permit.parse(tag.name());
						//if (permitList == null)
						//	permitList = new Vector <Permit>();
						//addPermitList(permit);
						break;
						
					case Consignee:
						consignee = new Party(getScanner());
						consignee.parse(tag.name());
						break;
						
					case ConsigneeTIN:						
						consigneeTIN = new TIN(getScanner());
						consigneeTIN.parse(tag.name());
						break;

					case Consignor:
						consignor = new Party(getScanner());
						consignor.parse(tag.name());
						break;						

					case ConsignorTIN:						
						consignorTIN = new TIN(getScanner());
						consignorTIN.parse(tag.name());
						break;

					//AK20091222						
					case WarehouseKeeper:
						warehouseKeeper = new Party(getScanner());
						warehouseKeeper.parse(tag.name());
					break;						
					case WarehouseKeeperTIN:
						warehouseKeeperTIN = new TIN(getScanner());
						warehouseKeeperTIN.parse(tag.name());
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
						Document documentK = new Document(getScanner(), "K");
						documentK.parse(tag.name());
						addDocumentList(documentK);
						break;
					case ProducedDocument:
						Document documentU = new Document(getScanner(), "U");
						documentU.parse(tag.name());
						addDocumentList(documentU);
						break;
						
					case PreviousDocument:
						PreviousDocument previousDocument = new PreviousDocument(getScanner());
						previousDocument.parse(tag.name());
						addPreviousDocumentList(previousDocument);
						break;
											
					case BondedWarehouseCompletion:   //EI20090305
					case WriteOffZL:
						bondedWarehouseCompletion = new Completion(getScanner());
						bondedWarehouseCompletion.parse(tag.name());
						break;	
						
					case InwardProcessingCompletion:   //EI20090305
					case WriteOffAVUV:
						inwardProcessingCompletion = new Completion(getScanner());
						inwardProcessingCompletion.parse(tag.name());
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

					case WareHouse:
						wareHouse = new WareHouse(getScanner());
						wareHouse.parse(tag.name());
						break;
												
				default:
						return;
				}
			} else {
				switch ((EGoodsItemTags) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;
						
					case ArticleNumber:
						setArticleNumber(value);
						break;	
						
					case Description:
					case GoodsDescription:
						setDescription(value);
						break;	
						
					case UCROtherSystem:
					case ExternalRegistrationNumber:
						setUCROtherSystem(value);
						break;	
						
					case Annotation:
						setAnnotation(value);
						break;
						
					case Annotation2:
						setAnnotation2(value);
						break;	
						
					case Remark:       //TODO-EI: oder kommt nur einmal und soll in zwei annotations geteilt werden???
						if (Utils.isStringEmpty(annotation)) {
							setAnnotation(value);
						} else {
							setAnnotation2(value);
						}
						break;							
						
					case ShipmentNumber:
					case CommercialReferenceNumber:
						setShipmentNumber(value);
						break;	
						
					case OriginFederalState:
					case OriginRegion:
						setOriginFederalState(value);
						break;
						
					case NetMass:
						setNetMass(value);
						break;	
											
					case GrossMass:
						setGrossMass(value);
						break;
											
					case DangerousGoodsNumber:
					case UNDGCode:
						setDangerousGoodsNumber(value);
						break;
						
					case PaymentType:
					case TransportPaymentMethod:
						setPaymentType(value);
						break;

					case DescriptionLanguage:
						setDescriptionLanguage(value);
						break;
						
					case DestinationCountry:
						setDestinationCountry(value);
						break;

					/* AK20091222
					case WarehouseKeeperTIN:
						setWarehouseKeeperTin(value);
						break;
						
					case WarehouseKeeper:
						setWarehouseKeeper(value);
						break;
					*/
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
    public String getArticleNumber() {
    	return articleNumber;
    }
    public void setArticleNumber(String articleNumber) {
    	this.articleNumber = articleNumber;
    }
    public String getDescription() {
    	return description;
    }
    public void setDescription(String description) {
    	this.description = description;
    }
    public String getUCROtherSystem() {
    	return uCROtherSystem;
    }
    public void setUCROtherSystem(String otherSystem) {
    	uCROtherSystem = otherSystem;
    }
    public String getAnnotation() {
    	return annotation;
    }
    public void setAnnotation(String argument) {
    	this.annotation = argument;
    }
    public String getAnnotation2() {
    	return annotation2;
    }
    public void setAnnotation2(String argument) {
    	this.annotation2 = argument;
    }
   
    
    public String getShipmentNumber() {
    	return shipmentNumber;
    }
    public void setShipmentNumber(String shipmentNumber) {
    	this.shipmentNumber = shipmentNumber;
    }
    
    public String getOriginFederalState() {
    	return originFederalState;
    }
    public void setOriginFederalState(String originFederalState) {
    	this.originFederalState = originFederalState;
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

	public String getDangerousGoodsNumber() {
		return dangerousGoodsNumber;
	}
	public void setDangerousGoodsNumber(String dangerousGoodsNumber) {
		this.dangerousGoodsNumber = dangerousGoodsNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
	
	 public CommodityCode getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(CommodityCode argument) {
		this.commodityCode = argument;
	}	
	
	public ExportRefundItem getExportRefundItem() {
		return exportRefundItem;
	}	
	public void setExportRefundItem(ExportRefundItem argument) {
		this.exportRefundItem = argument;
	}	
	
	public ApprovedTreatment getApprovedTreatment() {		
		if (!(Utils.isStringEmpty(restitutionProcedure))) {
			if (approvedTreatment == null) {
				approvedTreatment = new ApprovedTreatment();
			}
			approvedTreatment.setCodeForRefund(restitutionProcedure);			
		}
		return approvedTreatment;
	}	
	public void setApprovedTreatment(ApprovedTreatment argument) {
		this.approvedTreatment = argument;
	}	
	
	public Statistic getStatistic() {
		return statistic;
	}	
	public void setStatistic(Statistic argument) {
		this.statistic = argument;
	}	
	
	public Party getConsignee() {
		if (consigneeTIN != null) {
			if (consignee == null) {
				consignee = new Party();
			}
			consignee.setPartyTIN(consigneeTIN);
		}
		return consignee;
	}
	public void setConsignee(Party argument) {
		this.consignee = argument;
	}	
	
	//AK20091222
	public Party getWarehouseKeeper() {
		if (warehouseKeeperTIN != null) {
			if (warehouseKeeper == null) {
				warehouseKeeper = new Party();
			}
			warehouseKeeper.setPartyTIN(warehouseKeeperTIN);
		}
		return warehouseKeeper;
	}
	public void setWarehouseKeeper(Party argument) {
		this.warehouseKeeper = argument;
	}	

	

	public Party getConsignor() {
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new Party();
			}
			consignor.setPartyTIN(consignorTIN);
		}
		return consignor;
	}
	public void setConsignor(Party argument) {
		this.consignor = argument;
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
		
	/*
	public Document getProducedDocument() {
		return producedDocument;
	}	
	public void setProducedDocument(Document argument) {
		this.producedDocument = argument;
	}	
	
	public List <Document>getProducedDocumentList() {
		return producedDocumentList;
	}	
	public void setProducedDocumentList(List<Document> argument) {
		this.producedDocumentList = argument;
	}
	public void addProducedDocumentList(Document argument) {
		this.producedDocumentList.add(argument);
	}		
	*/	
	
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
	
	public void setSpecialMention(SpecialMention argument) {
		this.specialMention = argument;
	}		
	public SpecialMention getSpecialMention() {
		return specialMention;
	}
	
	public void setSpecialMentionList(List<SpecialMention> argument) {
		specialMentionList = argument;
	}		
	public List <SpecialMention> getSpecialMentionList() {
		return specialMentionList;
	}
	public void addSpecialMentionList(SpecialMention argument) {
		if (specialMentionList == null) {
			specialMentionList = new Vector<SpecialMention>();
		}
		specialMentionList.add(argument);
	}
	

	public void setPermit(Permit argument) {
		this.permit = argument;
	}	
	
	public Permit getPermit() {
		return permit;
	}		

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

	public TIN getConsigneeTIN() {
		return consigneeTIN;
	}

	public void setConsigneeTIN(TIN argument) {
		this.consigneeTIN = argument;
	}	
/*
	public List<Permit> getPermitList() {
		return permitList;
	
	}
	public void setPermitList(List<Permit> permitList) {
		this.permitList = permitList;
	}
	public void addPermitList(Permit argument) {
		if (permitList == null)
			permitList = new Vector<Permit>();
		permitList.add(argument);
	}
*/
	public String getMsgName() {
		return msgName;
	
	}

	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public Completion getBondedWarehouseCompletion() {
		return bondedWarehouseCompletion;	
	}

	public void setBondedWarehouseCompletion(Completion argument) {
		this.bondedWarehouseCompletion = argument;
	}	

	public Completion getInwardProcessingCompletion() {
		return inwardProcessingCompletion;	
	}

	public void setInwardProcessingCompletion(Completion argument) {
		this.inwardProcessingCompletion = argument;
	}
	
	private void setFromEdecItem(EdecItem edecItem) {
		if (edecItem == null) {
			return; 
		}
				
		typeOfArticle = edecItem.getCleranceCode();
		typeOfDeclaration = edecItem.getCleranceTypeCode();	
		if (specialMention == null) {
			specialMention = new SpecialMention();
		}
		specialMention.setTypeOfExport(edecItem.getSpecialMentions());  
		specialMention.setExportFromEU(edecItem.getECExportIdentifier());
		specialMention.setExportFromCountry(edecItem.getExportCountry());
		//if (specialMentionList == null)
		//	specialMentionList = new Vector <SpecialMention>();
		//addSpecialMentionList(specialMention);
		if (permit == null) {
			permit = new Permit();
		}
		permit = edecItem.getAllowance();	
		//if (permitList == null)
		//	permitList = new Vector <Permit>();
		//addPermitList(permit);
	}
	
	public NonCustomsLaw getNonCustomsLaw() {
		return nonCustomsLaw;
	}
	public void setNonCustomsLaw(NonCustomsLaw argument) {
		this.nonCustomsLaw = argument;
	}

	public String getDescriptionLanguage() {
		return descriptionLanguage;
	
	}

	public void setDescriptionLanguage(String descriptionLanguage) {
		this.descriptionLanguage = Utils.checkNull(descriptionLanguage);
	}

	public String getDestinationCountry() {
		return destinationCountry;
	
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = Utils.checkNull(destinationCountry);
	}

	/*  AK20091222
	public String getWarehouseKeeperTin() {
		return warehouseKeeperTin;
	
	}

	public void setWarehouseKeeperTin(String warehouseKeeperTin) {
		this.warehouseKeeperTin = Utils.checkNull(warehouseKeeperTin);
	}

	public String getWarehouseKeeper() {
		return warehouseKeeper;
	
	}

	public void setWarehouseKeeper(String warehouseKeeper) {
		this.warehouseKeeper = Utils.checkNull(warehouseKeeper);
	}
	*/

	public WareHouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(WareHouse wareHouse) {
		this.wareHouse = wareHouse;
	}
}
