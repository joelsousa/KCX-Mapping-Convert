package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.GoodsIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Notifications;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : Export/aes.<br>
 * Created      : 12.07.2012<br>
 * Description	: Kids Version 2.1.00
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgExpDatPos extends KCXMessage {
	
	private String msgName;
	private String itemNumber; 
	private String articleNumber; 
	private String description; 
	private String uCROtherSystem; 
	private String annotation; 
	private String annotation2;                
	private String shipmentNumber; 
	private String originCountry;               
	private String originFederalState; 
	private String destinationCountry;
	private String netMass;						
	private String netMassConfirmation;			  
	private String grossMass; 					
	private String grossMassConfirmation; 
	private String dangerousGoodsNumber; 
	private String paymentType; 
	private String kindOfArticle;               	
	private String typeOfArticle;               
	private String commodityBoard;              
	private String thirdQuantity;					
	private Party supervisingCustomsOffice;               
	private TIN supervisingCustomsOfficeTIN;               
	private String additionalCommodityBoardCode;	
	private String descriptionLanguage;
	private CommodityCode commodityCode;  
	private ExportRefundItem exportRefundItem;
	private ApprovedTreatment approvedTreatment;
	private Statistic statistic;
	private GoodsIdentification goodsIdentification;	
	private List<SensitiveGoods> sensitiveGoodsList;
	private SpecialMention specialMention; 
	private List <SpecialMention> specialMentionList;
	private NonCustomsLaw nonCustomsLaw;
	private String permitObligation;	
	private List <Permit> permitList;
    
	private Party consignee;
	private TIN consigneeTIN;	
	private Party consignor;
	private TIN consignorTIN;
	private Party warehouseKeeper;
    private TIN warehouseKeeperTIN;
		
	private List <Packages>packagesList;
	private Container container;	
	private List <DocumentV20>documentList; 	  
	//private List <PreviousDocumentV20>previousDocumentList;  	
	private List <PreviousDocumentV21>previousDocumentList;  
	private List <Text>addInfoStatementList;  				  
	
	private Completion bondedWarehouseCompletion;   
	private Completion inwardProcessingCompletion;  
	
	private WareHouse wareHouse;   
	private Refinement refinement;                 
	private List<Text> detailList;                 
	private String refundType; 
	private String refundQuantity; 
	
	private Notifications notifications;
	private List <String> notificationCodeList;	
	private String restitutionProcedure;    //only UIDS   
	
 //new for KIDS V2.1	begin	
	private Business  business;
	private IncoTerms incoTerms;
  	private String    addressCombination; 
  	private TIN       finalRecipientTIN;			
  	private Party     finalRecipient;    
 //new for KIDS V2.1 end
  	
  	private String procedureCode;    //EI20130710 only for BDP == geht in approvedTreatment.declared+previous


    //----------------
  	
	public MsgExpDatPos() {
			super();			
	}
	public MsgExpDatPos(XMLEventReader parser) {
		super(parser);				
		}	
	
	public MsgExpDatPos(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}	
	public MsgExpDatPos(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		setMsgName(msgName);  		
  	}

	 private enum EGoodsItemTags {
			//Kids							//Uids
		 	ItemNumber,						//same		 	
			ArticleNumber,					//same
			Description,					GoodsDescription,
			UCROtherSystem,					ExternalRegistrationNumber,
			Annotation,						Remark,
			Annotation2,					//same
			ShipmentNumber,					CommercialReferenceNumber,
			OriginCountry,  				//same
			OriginFederalState,				OriginRegion,
			DestinationCountry,				//same
			NetMass,						//same
			NetMassConfirmation,			//same
			GrossMass,						//same
			GrossMassConfirmation,			//same
			DangerousGoodsNumber,			UNDGCode,
			PaymentType,					TransportPaymentMethod,
            KindOfArticle,                  SpecialRemark, //CH: SpecialRemark.AdditionalCode
			TypeOfArticle,					EdecItem, // CH: EdecItem.CleranceCode			
			CommodityBoard,                 //--
			ThirdQuantity,					//same
			SupervisingCustomsOffice, SupervisingCustomsOfficeTIN, CustomsOffices, //.SupervisingCustomsOffice
			AdditionalCommodityBoardCode,   
			DescriptionLanguage,
			CommodityCode,					CommodityCodeKN8,
											CommodityCodeTARIC,
											CommodityCodeFirstAddition,
											CommodityCodeSecondAddition,
											CommodityCodeNationalAddition, 
											CommodityCodeNationalAddition2, //V20
											CommodityCodeNationalAddition3, //V20
											CommodityCodeConfirmation,      //V20
			ExportRefundItem,				ExportRestitutionItem,
			CustomsApprovedTreatment,		Procedure,
			Statistic,						StatisticalValue,
											StatisticalValueSendFlag,  //EI20130808 AES22
											StatisticalValueConfirmation,											
											StatisticalQuantity,
											StatisticalQuantityConfirmation,
											StatisticalBaseValue,
											StatisticalBaseCurrency,
			GoodsIdentification, 			//-
			SensitiveGoods,   				SpecialGoodsInformation, 	
			SpecialMention,                //EdecItem: SpecialMentions, ECExportIdentifier, ExportCountry
			NonCustomsLaw,         			SpecialRemarks, //CH: SpecialRemarks.RestrictionCode			
			PermitObligation,
			Permit,	                        //EdecItem.Allowance. Category; Reference, Date, Remark
			ConsigneeTIN,					//.	
			Consignee,						//same
			ConsignorTIN,					//.
			Consignor,						//same		
			WarehouseKeeperTIN,				//.
			WarehouseKeeper,				//
			Package,						Packaging,
			Container,	Containers,			ContainerRegistration,						
			Document,						ProducedDocument,			
			PreviousDocument,				//same	
			AddInfoStatement,			    //same
			BondedWarehouseCompletion,      WriteOffZL,
			InwardProcessingCompletion,     WriteOffAVUV,
			WareHouse,
			Refinement,					   //same;
	        Detail,					   	   //same;
	        RefundType,
	        RefundQuantity,
	        NotificationCode, 				//same		
	        Business,                       Transaction,
	        IncoTerms,                      Incoterms, Incoterm,
			AddressCombination,				//same	
			FinalRecipientTIN, FinalRecipient, FinalUser,						
			Notifications,   
			//EI20120921: wg. Remark, GrossMass, AuthorisationID in UIDS muessen in Enum bekannt sein:
			CustomsTreatment, Restitutions, Warehouse,
			
			CountryOfOrigin,  //only for BDP
			ProcedureCode,    //only for BDP
	}
	 
	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EGoodsItemTags) tag) {								
					case CommodityCode:
						commodityCode = new CommodityCode(getScanner());
						commodityCode.parse(tag.name());
						break;						
					case ExportRefundItem:	//Kids,					
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());
						break;
					case ExportRestitutionItem:  //Uids  						        
						                    //restitutionProcedure should fill approvedTreatment.codeForRefund
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());
						restitutionProcedure = exportRefundItem.getRestitutionProcedure();
						break;												
					case CustomsApprovedTreatment:
					case Procedure:	
						approvedTreatment = new ApprovedTreatment(getScanner());
						approvedTreatment.parse(tag.name());
						restitutionProcedure = approvedTreatment.getCodeForRefund();
						break;						
					case Statistic:
						statistic = new Statistic(getScanner());
						statistic.parse(tag.name());
						break;
					case GoodsIdentification:
						goodsIdentification = new GoodsIdentification(getScanner());
						goodsIdentification.parse(tag.name());
						break;
					case SensitiveGoods:
						SensitiveGoods sensitiveGoods = new SensitiveGoods(getScanner());
						sensitiveGoods.parse(tag.name());
						addSensitiveGoodsList(sensitiveGoods);
						break;
					case SpecialGoodsInformation:
						SensitiveGoods sensitive = new SensitiveGoods(getScanner());
						sensitive.parse(tag.name());
						addSensitiveGoodsList(sensitive);
						goodsIdentification = new GoodsIdentification(getScanner());
						goodsIdentification.parse(tag.name());
						break;
					case SpecialMention:
						SpecialMention specialMention = new SpecialMention(getScanner());
						specialMention.parse(tag.name());
						addSpecialMentionList(specialMention);
						break;						
					case Permit:
						Permit permit = new Permit(getScanner());
						permit.parse(tag.name());
						if (permitList == null) {
							permitList = new Vector <Permit>();
						}	
						addPermitList(permit);
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
						break;						
					case Document:
						DocumentV20 documentK = new DocumentV20(getScanner(), "K");
						documentK.parse(tag.name());
						addDocumentList(documentK);
						break;
					case ProducedDocument:
						DocumentV20 documentU = new DocumentV20(getScanner(), "U");
						documentU.parse(tag.name());
						addDocumentList(documentU);
						break;						
					case PreviousDocument:
						//PreviousDocumentV20 previousDocument = new PreviousDocumentV20(getScanner());
						PreviousDocumentV21 previousDocument = new PreviousDocumentV21(getScanner());
						previousDocument.parse(tag.name());
						addPreviousDocumentList(previousDocument);
						break;										
					case BondedWarehouseCompletion:  
					case WriteOffZL:
						bondedWarehouseCompletion = new Completion(getScanner());
						bondedWarehouseCompletion.parse(tag.name());
						break;							
					case InwardProcessingCompletion:   
					case WriteOffAVUV:
						inwardProcessingCompletion = new Completion(getScanner());
						inwardProcessingCompletion.parse(tag.name());
						break;							
					case EdecItem:
						EdecItem edecItem = new EdecItem(getScanner());
						edecItem.parse(tag.name());
						setKidsFromEdecItem(edecItem);
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
					case WareHouse:
						wareHouse = new WareHouse(getScanner());
						wareHouse.parse(tag.name());
						break;						
					case AddInfoStatement:
  						Text addInfoStatement = new Text(getScanner());
  						addInfoStatement.parse(tag.name());	
  						if (addInfoStatementList == null) {
  							addInfoStatementList = new Vector<Text>();
  						}					
  						addInfoStatementList.add(addInfoStatement);
  						break;  						
					case Refinement:
						refinement = new Refinement(getScanner());
						refinement.parse(tag.name());
						break;						
					case Detail:
						Text detail = new Text(getScanner());
						detail.parse(tag.name());
						addDetailList(detail);
						break;						
					case SupervisingCustomsOffice:                             
						supervisingCustomsOffice = new Party(getScanner());
						supervisingCustomsOffice.parse(tag.name());
						break;	
					case SupervisingCustomsOfficeTIN:                             
						supervisingCustomsOfficeTIN = new TIN(getScanner());
						supervisingCustomsOfficeTIN.parse(tag.name());
						break;
					case CustomsOffices:  //UIDS
						CustomsOffices co = new CustomsOffices(getScanner());
						co.parse(tag.name());
						if (co != null) {
							setSupervisingCustomsOfficeName(co.getSupervisingCustomsOffice());
						}
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
					case CustomsTreatment: case Restitutions: case Warehouse:
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
					case Remark:       //oder kommt nur einmal und soll in zwei annotations geteilt werden???
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
					case OriginCountry:
					case CountryOfOrigin:
						setOriginCountry(value);
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
					case GrossMassConfirmation:
						setGrossMassConfirmation(value);
						break;						
					case DangerousGoodsNumber:
					case UNDGCode:
						setDangerousGoodsNumber(value);
						break;						
					case PaymentType:
					case TransportPaymentMethod:
						setPaymentType(value);
						break;
					case AdditionalCommodityBoardCode:
						setAdditionalCommodityBoardCode(value);
						break;
					case DescriptionLanguage:
						setDescriptionLanguage(value);
						break;						
					case DestinationCountry:
						setDestinationCountry(value);
						break;
					case PermitObligation:
						setPermitObligation(value);
						break;					
					case KindOfArticle:
						setKindOfArticle(value);
						break;						
					case TypeOfArticle:
						setTypeOfArticle(value);
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
					case StatisticalValueConfirmation:
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setStatisticalValueConfirmation(value);
						break;
					case StatisticalValueSendFlag:        //EI20130808 AES22
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setStatisticalValueSendFlag(value);
						break;
					case StatisticalQuantity:
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setAdditionalUnit(value);
						break;		
					case StatisticalQuantityConfirmation:
						if (statistic == null) {
							statistic = new Statistic();
						}
						statistic.setAdditionalUnitConfirmation(value);
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
						break;															
  					case NetMassConfirmation:
  						setNetMassConfirmation(value);
  						break;
  					case CommodityBoard:
  						setCommodityBoard(value);
  						break;
  					case ThirdQuantity:
  						setThirdQuantity(value);
  						break;  					
  					case RefundType:
  						setRefundType(value);
  						break;  						
  					case RefundQuantity:
  						setRefundQuantity(value);
  						break;  						
  					case AddressCombination:
  						setAddressCombination(value);
  							break;	  						
  					case NotificationCode:
  						addNotificationCodeList(value);
  						break; 
  					case ProcedureCode:
  						setProcedureCode(value);
  					default:
  						break;
				}
			}
		}

 
	public void setCommodityBoard(String value) {
		this.commodityBoard = value;
	}
	public String getCommodityBoard() {
		return commodityBoard;
	}
	public void setAdditionalCommodityBoardCode(String value) {
		this.additionalCommodityBoardCode = value;
	}
	public String getAdditionalCommodityBoardCode() {
		return additionalCommodityBoardCode;
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

	//setter, getter:
	
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

	//public List<PreviousDocumentV20> getPreviousDocumentList() {
	public List<PreviousDocumentV21> getPreviousDocumentList() {
		return previousDocumentList;
	}
	//public void setPreviousDocumentList(List <PreviousDocumentV20> argument) {
	public void setPreviousDocumentList(List <PreviousDocumentV21> argument) {
		this.previousDocumentList = argument;
	}	
	//public void addPreviousDocumentList(PreviousDocumentV20 argument) {
	public void addPreviousDocumentList(PreviousDocumentV21 argument) {
		if (previousDocumentList == null) {
			//previousDocumentList = new Vector<PreviousDocumentV20>();
			previousDocumentList = new Vector<PreviousDocumentV21>();
		}
		previousDocumentList.add(argument);
	}
	
	public void addSensitiveGoodsList(SensitiveGoods argument) {
		if (sensitiveGoodsList == null) {
			sensitiveGoodsList = new Vector<SensitiveGoods>();
		}	
		this.sensitiveGoodsList.add(argument);
	}
	public List<SensitiveGoods> getSensitiveGoodsList() {
		return sensitiveGoodsList;
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
	
	public void addPermitList(Permit argument) {
		if (permitList == null) {
			permitList = new Vector<Permit>();
		}
		permitList.add(argument);
	}
	public List<Permit> getPermitList() {
		return permitList;	
	}	
	public void setPermitList(List<Permit> permitList) {
		this.permitList = permitList;
	}

	public List <DocumentV20>getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List <DocumentV20>documentList) {
		this.documentList = documentList;
	}

	public void addDocumentList(DocumentV20 argument) {
		if (documentList == null) {
			documentList = new Vector<DocumentV20>();
		}
		documentList.add(argument);
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
	
	private void setKidsFromEdecItem(EdecItem edecItem) {
		if (edecItem == null) {
			return; 
		}
				
		typeOfArticle = edecItem.getCleranceCode();		
		if (specialMentionList == null) {
			specialMentionList = new Vector <SpecialMention>();
		}
		SpecialMention specialMention = new SpecialMention();
		specialMention.setTypeOfExport(edecItem.getSpecialMentions());  
		specialMention.setExportFromEU(edecItem.getECExportIdentifier());
		specialMention.setExportFromCountry(edecItem.getExportCountry());
		addSpecialMentionList(specialMention);
		
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

	public WareHouse getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(WareHouse wareHouse) {
		this.wareHouse = wareHouse;
	}
	
	public String getNetMassConfirmation() {
  		return netMassConfirmation;
  	}
  	public void setNetMassConfirmation(String argument) {
  		this.netMassConfirmation = argument;
  	}
  	  	
  	public void setSupervisingCustomsOffice(Party argument) {
		this.supervisingCustomsOffice = argument;
	}
	public Party getSupervisingCustomsOffice() {
		if (supervisingCustomsOfficeTIN != null) {
			if (supervisingCustomsOffice == null) {
				supervisingCustomsOffice = new Party();
			}
			supervisingCustomsOffice.setPartyTIN(supervisingCustomsOfficeTIN);
		}			
		return supervisingCustomsOffice; 
	}
	public void setSupervisingCustomsOfficeName(String value) {   //EI20120711
  		if (Utils.isStringEmpty(value)) {
  			return;
  		}
  		if (supervisingCustomsOffice == null) {
			supervisingCustomsOffice = new Party();
		}
  		Address adr = new Address();
  		adr.setName(value);
  		supervisingCustomsOffice.setAddress(adr);
  	}  
	
  	public void setAddInfoStatementList(List<Text> argument) {
  		this.addInfoStatementList = argument;
  	}
  	public List<Text> getAddInfoStatementList() {  
  		return this.addInfoStatementList;
  	}
  	public void addAddInfoStatementList(Text argument) {
  		if (this.addInfoStatementList == null) {
  			this.addInfoStatementList = new Vector<Text>();
  		}
  		this.addInfoStatementList.add(argument);
  	}
  	
  	public void setThirdQuantity(String argument) {
  		this.thirdQuantity = argument;
  	}
  	public String getThirdQuantity() {
  		return this.thirdQuantity;
  	}
  	
  	public void setOriginCountry(String originCountry) {
  		this.originCountry = originCountry;
  	}
	public String getOriginCountry() {
		return originCountry;
	}

	public GoodsIdentification getGoodsIdentification() {
		return goodsIdentification;
	}
	public void setGoodsIdentification(GoodsIdentification goodsIdentification) {
		this.goodsIdentification = goodsIdentification;
	}

	public String getPermitObligation() {
		return permitObligation;	
	}
	public void setPermitObligation(String permitObligation) {
		this.permitObligation = Utils.checkNull(permitObligation);
	}

	public String getRefundType() {
		return refundType;	
	}
	public void setRefundType(String refundType) {
		this.refundType = Utils.checkNull(refundType);
	}

	public String getRefundQuantity() {
		return refundQuantity;	
	}
	public void setRefundQuantity(String refundQuantity) {
		this.refundQuantity = Utils.checkNull(refundQuantity);
	}

	public String getGrossMassConfirmation() {
		return grossMassConfirmation;	
	}
	public void setGrossMassConfirmation(String grossMassConfirmation) {
		this.grossMassConfirmation = Utils.checkNull(grossMassConfirmation);
	}		
	
	public Refinement getRefinement() {
		return refinement;	
	}
	public void setRefinement(Refinement argument) {
		this.refinement = argument;
	}
	
	public List<Text> getDetailList() {
		return detailList;	
	}
	public void setDetailList(List<Text> list) {
		this.detailList = list;
	}
	public void addDetailList(Text argument) {
  		if (this.detailList == null) {
  			this.detailList = new Vector<Text>();
  		}
  		this.detailList.add(argument);
  	}
	
	public List<String> getNotificationCodeList() {
		return notificationCodeList;	
	}
	public void setNotificationCodeList(List<String> list) {
		this.notificationCodeList = list;
	}
	public void addNotificationCodeList(String argument) {
  		if (this.notificationCodeList == null) {
  			this.notificationCodeList = new Vector<String>();
  		}
  		this.notificationCodeList.add(argument);
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
	
	public Notifications getNotifications() {
		return notifications;
	}
	public void setNotifications(Notifications argument) {
		this.notifications = argument;
	}
	
	public void setProcedureCode(String value) {
		if (Utils.isStringEmpty(value)) {
			return;
		}		
		approvedTreatment = new ApprovedTreatment(getScanner());//muss immer erst leer sein	
		int len = value.length();
		if (len > 1) {
			approvedTreatment.setDeclared(value.substring(0, 2));
		}
		if (len > 3) {
			approvedTreatment.setPrevious(value.substring(2, 4));	
		}
	}
}
