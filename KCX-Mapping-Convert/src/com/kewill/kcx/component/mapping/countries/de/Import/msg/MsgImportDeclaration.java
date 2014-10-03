package com.kewill.kcx.component.mapping.countries.de.Import.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.ch.aus20.common.Infos;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalInfoStatement;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.DV1;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: Import<br>.
 * Created		: 12.09.2011<br>
 * Description	: KIDS MsgImportDeclaration
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgImportDeclaration extends KCXMessage { 
	
	private String			msgName = "ImportDeclaration";
	private String			referenceNumber;
	private String			declarationPriorPresentation;
	private String			agentRepresentationCode;
	private String			preTaxDeductionCode;
	private String			transportInContainer;
	private String			paymentType;
	private String			customsOfficeOfDeclaration;			
	private String			customsOfficeEntry;	
	private String			importerLocation;
	private String			goodsLocation;
	private String			destinationFederalState;	
	private String 			taxOffice;
	private String			customsStatus;
	private String			statisticalStatus;
	private String			grossMass;
	private String			writeOffSumAType;
	private String			writeOffBonWarAvuvAuthNum;
	private String			writeOffBonWarRefNum;
	private String			customerOrderNumber;
	private String			additionalInformation;
	private AdditionalInfoStatement additionalInfo; //EI20131028 added for NL (KCXSM82): will replace additionalInformation
	private String			destinationCountry;            //EI20131028 added for NL (KCXSM83)
	
	private Traders     	traders;
	private Document        previousDocument;
	private TransportMeans	meansOfTransportBorder;
	private TransportMeans	meansOfTransportInland;
	private TransportMeans  meansOfTransportArrival;
	private ContactPerson 	contactPerson;	
	private IncoTerms		incoTerms;
	private Invoice			invoice;
	private Container		container;
	private DV1             dv1;	
	
	private List<Document>  documentList;	
	private List<Deferment> defermentList; 
	private Business business;                       //EI20120123
	private String placeOfDeclaration;               //EI20120123
	private String dispatchCountry;                  //EI20120123
	private String totalNumberPositions;             //EI20120308
	
	private String languageCode;                   //EI20121029: new for CH V20
	private String bunchNumber;                    //EI20121029: new for CH V20
	private String reason;                         //EI20121029: new for CH V20
	private String injunctionType;                 //EI20121029: new for CH V20
	private List<Infos> administrativeInfosList;    //EI20121029: new for CH V20
	//private Dispatch dispatch;                     //EI20121029: new for CH V20
	private String dispatchCountryConfirmation;     //EI20130304
	private String declarantIsConsignee;     //EI20130611: in xsd 1/0 or empty, in FSS J/N or empty (initially for BDP)
	
	//private List<GoodsItemDeclaration>	goodsItemList = new ArrayList<GoodsItemDeclaration>();
	private List<GoodsItemDeclaration>	goodsItemList = null;
	
	private EFormat         declarationDateFormat;
	
	public MsgImportDeclaration() {
		super();
	}
	public MsgImportDeclaration(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EImportDeclaration {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		DeclarationPriorPresentation,					
		AgentRepresentationCode,			
		PreTaxDeductionCode,	
		TransportInContainer,	
		Business,                       //EI20120123
		PaymentType,
		PlaceOfDeclaration,             //EI20120123
		CustomsOfficeOfDeclaration,			
		DeclarantIsConsignee, DeclarantIsConsignor,	//EI20130611
		Traders,	
		DispatchCountry,
		DispatchCountryConfirmation,
		CustomsOfficeEntry,				
		ImporterLocation,			
		GoodsLocation,
		DestinationCountry,
		DestinationFederalState,
		TaxOffice,
		CustomsStatus,
		StatisticalStatus,
		TotalNumberPositions,            //EI20120308 new Tag in xsd
		GrossMass,
		PreviousDocument,
		MeansOfTransportInland,			
		MeansOfTransportBorder,			
		MeansOfTransportArrival,		
		ContactPerson,					
		IncoTerms,			
		Invoice,			
		WriteOffSumAType,			
		WriteOffBonWarAvuvAuthNum,					
		WriteOffBonWarRefNum,				
		CustomerOrderNumber,				
		Document,					
		Container,					
		Deferment,
		AdditionalInformation, AdditionalInfoStatement,
		DV1,	
		LanguageCode,
		BunchNumber,
		Reason,
		InjunctionType,
		AdministrativeInfos,
		Dispatch,
		GoodsItem;						//same
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EImportDeclaration) tag) {
			case Traders:
				traders = new Traders(getScanner());
				traders.parse(tag.name());
				break;
			case PreviousDocument:		
				previousDocument = new Document(getScanner());
				previousDocument.parse(tag.name());					
				break;	
			case IncoTerms:
				incoTerms = new IncoTerms(getScanner());
				incoTerms.parse(tag.name());					
				break;
			case Invoice:
				invoice = new Invoice(getScanner());
				invoice.parse(tag.name());					
				break;
			case Document:
				Document document = new Document(getScanner());
				document.parse(tag.name());		
				addDocumentList(document);
				break;
			case Container:
				container = new Container(getScanner());
				container.parse(tag.name());					
				break;
			case Deferment:
				Deferment deferment = new Deferment(getScanner());
				deferment.parse(tag.name());
				addDefermentList(deferment);
				break;
			case MeansOfTransportInland:			
				meansOfTransportInland = new TransportMeans(getScanner());
				meansOfTransportInland.parse(tag.name());
				break;
			case MeansOfTransportBorder:			
				meansOfTransportBorder = new TransportMeans(getScanner());
				meansOfTransportBorder.parse(tag.name());
				break;
			case MeansOfTransportArrival:			
				meansOfTransportArrival = new TransportMeans(getScanner());
				meansOfTransportArrival.parse(tag.name());
				break;			
			case ContactPerson:
				contactPerson = new ContactPerson(getScanner());
				contactPerson.parse(tag.name());
				break;
			case DV1:
				dv1 = new DV1(getScanner());
				dv1.parse(tag.name());
				break;
			case Business:
				business = new Business(getScanner());
				business.parse(tag.name());
				break;
			case AdministrativeInfos:
				Infos info = new Infos(getScanner());
				info.parse(tag.name());
				addAdministrativeInfosList(info);
				break;
				/*
			case DispatchCountry:
			case Dispatch:
				dispatch = new Dispatch(getScanner());
				dispatch.parse(tag.name());
				break;	
				*/
				
			case AdditionalInfoStatement:
				additionalInfo = new AdditionalInfoStatement(getScanner());
				additionalInfo.parse(tag.name());
				break;
				
			case GoodsItem:
				GoodsItemDeclaration goodsItem = new GoodsItemDeclaration(getScanner());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;			
				
			default:
				return;
			}
		} else {
			switch ((EImportDeclaration) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			case DeclarationPriorPresentation:		
				setDeclarationPriorPresentation(value);
				break;
			case AgentRepresentationCode:			
				setAgentRepresentationCode(value);
				break;
			case PreTaxDeductionCode:
				setPreTaxDeductionCode(value);
				break;
			case TransportInContainer:	
				setTransportInContainer(value);
				break;
			case PaymentType:			
				setPaymentType(value);
				break;
			case CustomsOfficeOfDeclaration:				
				setCustomsOfficeOfDeclaration(value);
				break;				
			case CustomsOfficeEntry:
				setCustomsOfficeEntry(value);
				break;
			case ImporterLocation:				
				setImporterLocation(value);
				break;			
			case GoodsLocation:				
				setGoodsLocation(value);
				break;
			case DestinationCountry:
				setDestinationCountry(value);
				break;
			case DestinationFederalState:
				setDestinationFederalState(value);
				break;
			case TaxOffice:			
				setTaxOffice(value);
				break;
			case CustomsStatus:
				setCustomsStatus(value);
				break;			
			case StatisticalStatus:
				setStatisticalStatus(value);
				break;			
			case GrossMass:
				setGrossMass(value);
				break;				
			case WriteOffSumAType:
				setWriteOffSumAType(value);
				break;
			case WriteOffBonWarAvuvAuthNum:
				setWriteOffBonWarAvuvAuthNum(value);
				break;						
			case WriteOffBonWarRefNum:
				setWriteOffBonWarRefNum(value);
				break;			
			case CustomerOrderNumber:
				setCustomerOrderNumber(value);
				break;			
			case AdditionalInformation:
				setAdditionalInformation(value);
				break;
			case PlaceOfDeclaration:
				setPlaceOfDeclaration(value);
				break;
			case DispatchCountry:
				setDispatchCountry(value);
				break;
			case DispatchCountryConfirmation:
				setDispatchCountryConfirmation(value);
				break;
			case TotalNumberPositions:
				setTotalNumberPositions(value);
				break;
			case LanguageCode:
				setLanguageCode(value);
				break;
			case BunchNumber:
				setBunchNumber(value);
				break;
			case Reason:
				setReason(value);
				break;
			case InjunctionType:
				setInjunctionType(value);
				break;
			case DeclarantIsConsignee:
			case DeclarantIsConsignor:    //for BDP_Declaration is only one Tag for all procerures 
				setDeclarantIsConsignee(value);
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
			return EImportDeclaration.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getDeclarationPriorPresentation() {
		return declarationPriorPresentation;
	}
	public void setDeclarationPriorPresentation(String value) {
		this.declarationPriorPresentation = value;
	}
	public String getAgentRepresentationCode() {
		return agentRepresentationCode;
	}
	public void setAgentRepresentationCode(String value) {
		this.agentRepresentationCode = value;
	}
	public String getPreTaxDeductionCode() {
		return preTaxDeductionCode;
	}
	public void setPreTaxDeductionCode(String value) {
		this.preTaxDeductionCode = value;
	}
	
	public String getTransportInContainer() {
		return transportInContainer;
	}
	public void setTransportInContainer(String value) {
		this.transportInContainer = value;
	}
	
	public String getPaymentType() {
		return paymentType;}
	public void setPaymentType(String value) {
		this.paymentType = value;
	}
		
	public String getCustomsOfficeOfDeclaration() {
		return customsOfficeOfDeclaration;
	}
	public void setCustomsOfficeOfDeclaration(String value) {
		this.customsOfficeOfDeclaration = value;
	}
	
	public Traders getTraders() {
		return traders;
	}
	public void setTraders(Traders traders) {
		this.traders = traders;
	}
			
	public String getCustomsOfficeEntry() {
		return customsOfficeEntry;
	}
	public void setCustomsOfficeEntry(String value) {
		this.customsOfficeEntry = value;
	}
		
	public String getImporterLocation() {
		return importerLocation;
	}
	public void setImporterLocation(String value) {
		this.importerLocation = value;
	}
	
	public String getGoodsLocation() {
		return goodsLocation;
	}
	public void setGoodsLocation(String value) {
		this.goodsLocation = value;
	}
	
	public String getDestinationFederalState() {
		return destinationFederalState;
	}
	public void setDestinationFederalState(String value) {
		this.destinationFederalState = value;
	}
	
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String value) {
		this.destinationCountry = value;
	}
	
	public String getTaxOffice() {
		return taxOffice;
	}
	public void setTaxOffice(String value) {
		this.taxOffice = value;
	}
	
	public String getCustomsStatus() {
		return customsStatus;
	}
	public void setCustomsStatus(String value) {
		this.customsStatus = value;
	}
	
	public String getStatisticalStatus() {
		return statisticalStatus;
	}
	public void setStatisticalStatus(String value) {
		this.statisticalStatus = value;
	}
	
	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String value) {
		this.grossMass = value;
	}
		
	public Document getPreviousDocument() {
		return previousDocument;
	}
	public void setPreviousDocument(Document document) {
		this.previousDocument = document;
	}
		
	public TransportMeans getMeansOfTransportInland() {
		return meansOfTransportInland;
	}
	public void setMeansOfTransportInland(TransportMeans meansOfTransportInland) {
		this.meansOfTransportInland = meansOfTransportInland;
	}
	public TransportMeans getMeansOfTransportBorder() {
		return meansOfTransportBorder;
	}
	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		this.meansOfTransportBorder = meansOfTransportBorder;
	}
		
	public TransportMeans getMeansOfTransportArrival() {
		return meansOfTransportArrival;
	}
	public void setMeansOfTransportArrival(TransportMeans arrival) {
		this.meansOfTransportArrival = arrival;
	}
		
	public ContactPerson getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}
			
	public IncoTerms getIncoterms() {
		return incoTerms;
	}
	public void setIncoterms(IncoTerms incoterms) {
		this.incoTerms = incoterms;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
		
	public String getWriteOffSumAType() {
		return writeOffSumAType;
	}
	public void setWriteOffSumAType(String value) {
		this.writeOffSumAType = value;
	}
			
	public String getWriteOffBonWarAvuvAuthNum() {
		return writeOffBonWarAvuvAuthNum;
	}
	public void setWriteOffBonWarAvuvAuthNum(String value) {
		this.writeOffBonWarAvuvAuthNum = value;
	}
		
	public String getWriteOffBonWarRefNum() {
		return writeOffBonWarRefNum;
	}
	public void setWriteOffBonWarRefNum(String value) {
		this.writeOffBonWarRefNum = value;
	}
	
	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}
	public void setCustomerOrderNumber(String value) {
		this.customerOrderNumber = value;
	}
	

	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> list) {
		this.documentList = list;
	}
	public void addDocumentList(Document document) {
		if (documentList == null) {
			documentList = new Vector<Document>();	
		}
		this.documentList.add(document);
	}
		
	public Container getContainer() {
		return container;
	}
	public void setContainer(Container container) {
		this.container = container;
	}
	
	public List<Deferment> getDefermentList() {
		return defermentList;
	}
	public void setDefermentList(List<Deferment> list) {
		this.defermentList = list;
	}	
	public void addDefermentList(Deferment deferment) {
		if (defermentList == null) {
			defermentList = new Vector<Deferment>();	
		}
		this.defermentList.add(deferment);
	}	
	
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String value) {
		this.additionalInformation = value;
	}
	
	public AdditionalInfoStatement getAdditionalInfoStatement() {
		return additionalInfo;
	}
	public void setAdditionalInfoStatement(AdditionalInfoStatement statement) {
		this.additionalInfo = statement;
	}
	
	public DV1 getDV1() {
		return dv1;
	}
	public void setDV1(DV1 dv1) {
		this.dv1 = dv1;
	}
	
	public List<GoodsItemDeclaration> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(List<GoodsItemDeclaration> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItemDeclaration item) {
		if (goodsItemList == null) {
			goodsItemList = new Vector<GoodsItemDeclaration>();	
		}
		this.goodsItemList.add(item);
	}
	
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business argument) {
		this.business = argument;
	}
	
	public String getPlaceOfDeclaration() {
		return placeOfDeclaration;
	}
	public void setPlaceOfDeclaration(String value) {
		this.placeOfDeclaration = value;
	}
	
	public String getDispatchCountry() {
		return dispatchCountry;
	}
	public void setDispatchCountry(String value) {
		this.dispatchCountry = value;
	} 	
	
	public String getDispatchCountryConfirmation() {
		return dispatchCountryConfirmation;
	}
	public void setDispatchCountryConfirmation(String value) {
		this.dispatchCountryConfirmation = value;
	} 
	
	public String getTotalNumberPositions() {
		return totalNumberPositions;
	}
	public void setTotalNumberPositions(String value) {
		this.totalNumberPositions = value;
	}
	
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String value) {
		this.languageCode = value;
	}
	
	public String getBunchNumber() {
		return bunchNumber;
	}
	public void setBunchNumber(String value) {
		this.bunchNumber = value;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String value) {
		this.reason = value;
	}
	
	public String getInjunctionType() {
		return injunctionType;
	}
	public void setInjunctionType(String value) {
		this.injunctionType = value;
	}
		
	public List<Infos> getAdministrativeInfosList() {
		return administrativeInfosList;
	}
	public void setAdministrativeInfosList(List<Infos> list) {
		this.administrativeInfosList = list;
	}
	public void addAdministrativeInfosList(Infos info) {
		if (administrativeInfosList == null) {
			administrativeInfosList = new Vector<Infos>();	
		}
		administrativeInfosList.add(info);
	}
	
	public String getDeclarantIsConsignee() {   //EI20130611
		return declarantIsConsignee;
	}
	public void setDeclarantIsConsignee(String value) {
		this.declarantIsConsignee = value;
	}
	/*
	public Dispatch getDispatch() {
		return dispatch;
	}
	public void getDispatch(Dispatch dis) {
		this.dispatch = dis;
	}
	*/
}
