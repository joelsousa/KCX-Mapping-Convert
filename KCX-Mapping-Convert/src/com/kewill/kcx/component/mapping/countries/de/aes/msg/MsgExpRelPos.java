/*
 * Function    : MsgExpRelPos.java
 * Titel       :
 * Date        : 16.03.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains Goodsitem Data for KIDS-ReverseDeclaration,
 *             : V60
 *
 * Changes 
 * -----------
 * Author      : iwaniuk
 * Date        : 10.08.2010
 * Label       : EI20108010
 * Description : several new Tags (new in xsd-definition)
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
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.GoodsIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ProducedDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteOffAtlas;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgExpRelPos<br>
 * Erstellt		: 16.03.2009<br>
 * Beschreibung	: Contains Goodsitem Data for KIDS-ReverseDeclaration.
 *              : V60
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
	private String originCountry;                         //EI20100810
	private String originFederalState; 
	private String netMass;						//n(11,3)	
	private String grossMass; 			
	private String dangerousGoodsNumber; 		//n(11,3)	
	private String paymentType; 
	private String commodityBoard;                        //EI20100810
	private String commodityBoardCode;                    //EI20100810
	private CommodityCode commodityCode; 
	private ExportRefundItem exportRefundItem;
	private ApprovedTreatment approvedTreatment;
	private Statistic statistic;  
	//String goodsIdentification;                    //EI20100810
	private GoodsIdentification goodsIdentification; 
	private List <SpecialMention> specialMentionList;      //EI20100810
	private List <Permit> permitList;                      //EI20100810
	private Party consignee;
	private TIN consigneeTIN;
	//private Packages packages;
	private List <Packages>packagesList;
	private Container container;
	private List <Container>containerList;
	private Document document;		
	private List <Document> documentList;  
	private PreviousDocument previousDocument;      
	private List <PreviousDocument>previousDocumentList;  
	private Completion bondedWarehouseCompletion;   
	private Completion inwardProcessingCompletion;
	
	private String restitutionProcedure;   //nur bei UIDS;	
//	private boolean debug;
    
	public MsgExpRelPos() {
			super();
			createLists();
	}
	
	public MsgExpRelPos(XMLEventReader parser) {
		super(parser);		
		createLists();
}	
	
	public MsgExpRelPos(XmlMsgScanner scanner) {
  		super(scanner);
  		createLists();
  	}
	
	public MsgExpRelPos(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		setMsgName(msgName);
  		createLists();
  	}


	private void createLists() {		
		packagesList = new Vector <Packages>();
		containerList = new Vector<Container>();		
		previousDocumentList = new Vector <PreviousDocument>();
		documentList = new Vector <Document>();	
		specialMentionList = new Vector <SpecialMention>();	
		permitList = new Vector <Permit>();
	}
	 
	private enum EGoodsItemTags {
		 //KIDS							UIDS
			ItemNumber,					//same
			ArticleNumber,              //same
			Description,				GoodsDescription,
			UCROtherSystem,				ExternalRegistrationNumber,
			Annotation,  				Remark,
			ShipmentNumber,				CommercialReferenceNumber,
			OriginCountry,                // -      
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
										StatisticalValue, StatisticalQuantity,
			GoodsIdentification,        // -
			SpecialMention,             // -
			Permit,                     // - 
			Consignee,					//same
			ConsigneeTIN,				//
			Package,					Packaging,
			Container,					//UIDS: tags directlty within GoodsItem
										ContainerRegistration,		
			Document,					ProducedDocument, 
			PreviousDocument,			//same
										WriteOffZL, WriteOffAVUV,  //EI20090818
			BondedWarehouseCompletion,  WriteOffAtlas, //WriteOffAtlas.writeOffZL
			InwardProcessingCompletion;  			   //WriteOffAtlas.writeOffAVUV
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EGoodsItemTags) tag) {							
					case CommodityCode:
						commodityCode = new CommodityCode(getScanner());
						commodityCode.parse(tag.name());
						break;						
					case ExportRefundItem:		  //Kids2Uids, 		
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());		
						break;
					case ExportRestitutionItem:   
					    //Uisd2Kids, restitutionProcedure should fill approvedTreatment.codeForRefund
						exportRefundItem = new ExportRefundItem(getScanner());
						exportRefundItem.parse(tag.name());		
						//if (approvedTreatment != null) approvedTreatment.setCodeForRefund(exportRefundItem.getRestitutionProcedure());
						restitutionProcedure = exportRefundItem.getRestitutionProcedure();
						break;						
					case CustomsApprovedTreatment: //Kids2Uids, codeForRefund filled - OK	
					case Procedure:                //Uisd2Kids, codeForRefund
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
						//addContainerList(container);
						break;						
					case Document:
						document = new Document(getScanner(), "K");
						document.parse(tag.name());
						addDocumentList(document);
						break;
					case ProducedDocument:
						ProducedDocument producedDoc = new ProducedDocument(getScanner());
						producedDoc.parse(tag.name());						
					    addDocumentList(producedDoc.getDocument());		
						break;						
					case PreviousDocument:
						previousDocument = new PreviousDocument(getScanner());
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
// AK20101013-1						
					case GoodsIdentification:   
						goodsIdentification = new GoodsIdentification(getScanner());
						goodsIdentification.parse(tag.name());
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
	
	/*
	public TIN getConsigneeTIN() {
		return consigneeTIN;
	}

	public void setConsigneeTIN(TIN argument) {
		this.consigneeTIN = argument;
	}		
    */	
	
	public List <Packages>getPackagesList() {
		return packagesList;
	}
	public void setPackagesList(List <Packages>argument) {
		this.packagesList  = argument;
	}
	public void addPackagesList(Packages argument) {
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
		this.containerList.add(argument);
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

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List <Document>getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List <Document>documentList) {
		this.documentList = documentList;
	}

	public void addDocumentList(Document argument) {
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
	/*
	public void setConsigneePartyTIN(TIN argument) {
		if (consignee == null) consignee = new Party();		
		consignee.setPartyTIN(consigneeTIN);
	}
	*/
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
		this.specialMentionList.add(argument);
	}	
	public List<Permit> getPermitList() {
		return permitList;
	}	
	public void setPermitList(List<Permit> list) {
		this.permitList = list;
	}	
	public void addPermitList(Permit argument) {
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
}




