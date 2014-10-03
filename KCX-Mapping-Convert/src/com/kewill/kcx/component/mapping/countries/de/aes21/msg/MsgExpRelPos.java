package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.GoodsIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ProducedDocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteOffAtlas;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgExpRelPos<br>
 * Created		: 16.03.2009<br>
 * Description	: Contains Goodsitem Data for KIDS-ReverseDeclaration.
 *              : V21 new members
 *              : EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExpRelPos extends KCXMessage {
	private String msgName;
	
	private String itemNumber; 
	private String articleNumber; 
	private String description; 
	private String uCROtherSystem; 
	private String annotation; 
	private String shipmentNumber; 	
	private String originCountry;                        
	private String originFederalState; 
	private String netMass;						
	private String grossMass; 			
	private String dangerousGoodsNumber; 			
	private String paymentType; 
	private String commodityBoard;                       
	private String commodityBoardCode;                   
	private CommodityCode commodityCode; 
	private ExportRefundItem exportRefundItem;
	private ApprovedTreatment approvedTreatment;
	private Statistic statistic;  	            
	private GoodsIdentification goodsIdentification; 
	private List <SpecialMention> specialMentionList;      
	private List <Permit> permitList;                     
	private Party consignee;
	private TIN consigneeTIN;	
	private List <Packages>packagesList;
	private Container container;
	private List <Container>containerList;	
	private List <DocumentV20> documentList;  	    		
	//private List <PreviousDocumentV20>previousDocumentList; 
	private List <PreviousDocumentV21>previousDocumentList;  
	private Completion bondedWarehouseCompletion;   
	private Completion inwardProcessingCompletion;
	
	private String restitutionProcedure;   // UIDS;	

	//new for KIDS V2.1	beginn	
	private String    originItemNumber;
	private Business  business;
	private IncoTerms incoTerms;
  	private String    addressCombination; 
  	private TIN       finalRecipientTIN;			
  	private Party     finalRecipient;    
  	private String    watermark;  
 //new for KIDS V2.1 end
	
    
	public MsgExpRelPos() {
			super();		
	}
	
	public MsgExpRelPos(XMLEventReader parser) {
		super(parser);				
}	
	
	public MsgExpRelPos(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}
	
	public MsgExpRelPos(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		setMsgName(msgName);  		
  	}
	 
	private enum EGoodsItemTags {
		 //KIDS							UIDS
			ItemNumber,					//same
			OriginItemNumber,			OriginalItemNumber,			
			ArticleNumber,              //same
			Description,				GoodsDescription,
			UCROtherSystem,				ExternalRegistrationNumber,
			Annotation,  				Remark,
			ShipmentNumber,				CommercialReferenceNumber,
			OriginCountry,              // -      
			OriginFederalState,			OriginRegion,
			NetMass,					//same		
			GrossMass,					//same
			DangerousGoodsNumber,		UNDGCode,		
			PaymentType,				TransportPaymentMethod,	
			CommodityBoard,               // -   
			AdditionalCommodityBoardCode, // -   
			CommodityCode,				//UIDS: tags directlty within GoodsItem
										CommodityCodeKN8, CommodityCodeTARIC,
										CommodityCodeFirstAddition, CommodityCodeSecondAddition,
										CommodityCodeNationalAddition,
			ExportRefundItem,			ExportRestitutionItem, 
			CustomsApprovedTreatment,	Procedure,
			//CustomsApprovedTreatment.codeForRefund == ExportRfundItem.restitutionProcedure
			Statistic,					//UIDS: tags directlty within GoodsItem
										StatisticalValue, StatisticalValueConfirmation,
										StatisticalQuantity, StatisticalQuantityConfirmation,
										StatisticalBaseValue, StatisticalBaseCurrency,
			GoodsIdentification,        // -
			SpecialMention,            EdecItem,  //SpecialMentions, ECExportIdentifier, ExportCountry
			Permit,                     //EdecItem.Allowance. Category; Reference, Date, Remark 
			Consignee,					//same
			ConsigneeTIN,				
			Package,					Packaging,
			Container,					//UIDS: tags directlty within GoodsItem
										ContainerRegistration,		
			Document,					ProducedDocument, 
			PreviousDocument,			//same
										WriteOffZL, WriteOffAVUV,  
			BondedWarehouseCompletion,  WriteOffAtlas, //WriteOffAtlas.writeOffZL
			InwardProcessingCompletion,  			   //WriteOffAtlas.writeOffAVUV
			Business,                    Transaction,
	        IncoTerms,                   Incoterms,
	        AddressCombination,	         //same
			FinalRecipientTIN,			FinalUser,	
			FinalRecipient,	
			Watermark,                  //same
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EGoodsItemTags) tag) {							
					case CommodityCode:
						commodityCode = new CommodityCode(getScanner());
						commodityCode.parse(tag.name());
						break;						
					case ExportRefundItem:		  //Kids 		
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());		
						break;
					case ExportRestitutionItem:   
					    //Uisd restitutionProcedure should fill approvedTreatment.codeForRefund
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());		
						//if (approvedTreatment != null) approvedTreatment.setCodeForRefund(exportRefundItem.getRestitutionProcedure());
						restitutionProcedure = exportRefundItem.getRestitutionProcedure();
						break;						
					case CustomsApprovedTreatment: //Kids codeForRefund filled - OK	
					case Procedure:                //Uisd, codeForRefund
						approvedTreatment = new ApprovedTreatment(getScanner());
						approvedTreatment.parse(tag.name());
						break;					
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
					case Package:
					case Packaging:
						Packages packages = new Packages(getScanner());
						packages.parse(tag.name());
						addPackagesList(packages);
						break;						
					case Container:				
						container = new Container(getScanner());
						container.parse(tag.name());						
						break;						
					case Document:
						DocumentV20 doc = new DocumentV20(getScanner(), "K");
						doc.parse(tag.name());
						addDocumentList(doc);
						break;
					case ProducedDocument:
						ProducedDocumentV20 producedDoc = new ProducedDocumentV20(getScanner());
						producedDoc.parse(tag.name());						
					    addDocumentList(producedDoc.getDocument());		
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
					case WriteOffAtlas:   
						WriteOffAtlas writeOffAtlas = new WriteOffAtlas(getScanner());
						writeOffAtlas.parse(tag.name());
						setCompletions(writeOffAtlas);
						break;							
					case SpecialMention:
						SpecialMention specialMention = new SpecialMention(getScanner());
						specialMention.parse(tag.name());
						addSpecialMentionList(specialMention);
						break;
					case Permit:
						Permit permit = new Permit(getScanner());
						permit.parse(tag.name());
						addPermitList(permit);
						break;								
					case GoodsIdentification:   
						goodsIdentification = new GoodsIdentification(getScanner());
						goodsIdentification.parse(tag.name());
						break;				
					case Business:
					case Transaction:
						business = new Business(getScanner());
						business.parse(tag.name());
						break;	
						
					case IncoTerms:
					case Incoterms:
						incoTerms = new IncoTerms(getScanner());
						incoTerms.parse(tag.name());
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
					case EdecItem:
						EdecItem edecItem = new EdecItem(getScanner());
						edecItem.parse(tag.name());
						setKidsFromEdecItem(edecItem);
						break;		
				default:
						return;
				}
			} else {
				switch ((EGoodsItemTags) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;	
					case OriginItemNumber:
					case OriginalItemNumber:
						setOriginItemNumber(value);
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
					case Remark:
						setAnnotation(value);
						break;							
					case ShipmentNumber:
					case CommercialReferenceNumber:
						setShipmentNumber(value);
						break;	
					case OriginCountry:
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
					case DangerousGoodsNumber:
					case UNDGCode:
						setDangerousGoodsNumber(value);
						break;						
					case PaymentType:
					case TransportPaymentMethod:
						setPaymentType(value);
						break;	
					case CommodityBoard:
						setCommodityBoard(value);
						break;
					case AdditionalCommodityBoardCode:
						setAdditionalCommodityBoardCode(value);
						break;
					case CommodityCodeKN8:
						setComodityFields("CommodityCodeKN8", value);
						break;					
					case CommodityCodeTARIC:
						setComodityFields("CommodityCodeTARIC", value);
						break;
					case CommodityCodeFirstAddition:
						setComodityFields("CommodityCodeFirstAddition", value);
						break;
					case CommodityCodeSecondAddition:
						setComodityFields("CommodityCodeSecondAddition", value);
						break;
					case CommodityCodeNationalAddition:
						setComodityFields("CommodityCodeNationalAddition", value);
						break;						
					case StatisticalValue: 
						setStatisticFields("StatisticalValue", value);
						break;
					case StatisticalQuantity:
						setStatisticFields("StatisticalQuantity", value);
						break;						
					case ContainerRegistration:
						setContainerNumber(value);
						break;
					case AddressCombination:
  						setAddressCombination(value);
  						break;
					case Watermark:
						setWatermark(value);
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
	
	public List <Packages>getPackagesList() {
		return packagesList;
	}
	public void setPackagesList(List <Packages>argument) {
		this.packagesList  = argument;
	}
	public void addPackagesList(Packages argument) {
		if (packagesList == null) {
			packagesList = new Vector <Packages>();			
		}
		this.packagesList.add(argument);
	}	
	
	public Container getContainer() {
		return container;
	}	
	public void setContainer(Container argument) {
		this.container = argument;
	}	
	
	public List <Container>getContainerList() {
		return containerList;
	}	
	public void setContainerList(List <Container> argument) {
		this.containerList = argument;
	}
	public void addContainerList(Container argument) {
		if (containerList == null) {
			containerList = new Vector<Container>();							
		}
		this.containerList.add(argument);
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
			//previousDocumentList = new Vector <PreviousDocumentV20>();
			previousDocumentList = new Vector <PreviousDocumentV21>();
		}
		this.previousDocumentList.add(argument);
	}	

	public List <DocumentV20>getDocumentList() {
		return documentList;
	}

	//public void setDocumentList(List <DocumentV21>documentList) {
	public void setDocumentList(List <DocumentV20>documentList) {
		this.documentList = documentList;
	}

	//public void addDocumentList(DocumentV21 argument) {
	public void addDocumentList(DocumentV20 argument) {
		if (documentList == null) {
			documentList = new Vector <DocumentV20>();				
		}
		this.documentList.add(argument);
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
	
	public void setComodityFields(String tag, String value) {
		if (commodityCode == null) {
			commodityCode = new CommodityCode();
		}			
		if (tag.equals("CommodityCodeKN8")) {
			commodityCode.setTarifCode(value);
		}
		if (tag.equals("CommodityCodeTARIC")) {
			commodityCode.setEUTarifCode(value);
		}
		if (tag.equals("CommodityCodeFirstAddition")) {
			commodityCode.setTarifAddition1(value);
		}
		if (tag.equals("CommodityCodeSecondAddition")) {
			commodityCode.setTarifAddition2(value);
		}
		if (tag.equals("CommodityCodeNationalAddition")) {
			commodityCode.setAddition(value);
		}
	}
	
	public void setStatisticFields(String tag, String value) {
		if (statistic == null) {
			statistic = new Statistic();
		}		
		if (tag.equals("StatisticalValue")) {
			statistic.setStatisticalValue(value);
		}
		if (tag.equals("StatisticalQuantity")) {
			statistic.setAdditionalUnit(value);	
		}
	}
 
	public void setContainerNumber(String value) {
		if (container == null) {
			container = new Container();
		}
		container.addNumberList(value);		
	}
	
	public void setCompletions(WriteOffAtlas argument) {		
		if (argument == null) {
			return;
		}
		bondedWarehouseCompletion  = argument.getWriteOffZL();
		inwardProcessingCompletion = argument.getWriteOffAVUV();
	}

	public List<SpecialMention> getSpecialMentionList() {
		return specialMentionList;
	}	
	public void setSpecialMentionList(List<SpecialMention> list) {
		this.specialMentionList = list;
	}
	public void addSpecialMentionList(SpecialMention argument) {
		if (specialMentionList == null) {
			specialMentionList = new Vector <SpecialMention>();				
		}
		this.specialMentionList.add(argument);
	}	
	public List<Permit> getPermitList() {
		return permitList;
	}	
	public void setPermitList(List<Permit> list) {
		this.permitList = list;
	}	
	public void addPermitList(Permit argument) {
		if (permitList == null) {
			permitList = new Vector <Permit>();
		}
		this.permitList.add(argument);
	}	

	public String getCommodityBoard() {
		return commodityBoard;
	}	
	public void setCommodityBoard(String argument) {
		this.commodityBoard = argument;
	}	
	public String getAdditionalCommodityBoardCode() {
		return commodityBoardCode;
	}	
	public void setAdditionalCommodityBoardCode(String argument) {
		this.commodityBoardCode = argument;
	}	
	public String getOriginCountry() {
		return originCountry;
	}	
	public void setOriginCountry(String argument) {
		this.originCountry = argument;
	}

	public GoodsIdentification getGoodsIdentification() {
		return goodsIdentification;
	
	}
	
	public String getOriginItemNumber() {
    	return originItemNumber;
    }
    public void setOriginItemNumber(String itemNumber) {
    	this.originItemNumber = itemNumber;
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
	
	public String getWatermark() {
    	return watermark;
    }
    public void setWatermark(String itemNumber) {
    	this.watermark = itemNumber;
    }
    
    private void setKidsFromEdecItem(EdecItem edecItem) {
		if (edecItem == null) {
			return; 
		}
		
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
}




