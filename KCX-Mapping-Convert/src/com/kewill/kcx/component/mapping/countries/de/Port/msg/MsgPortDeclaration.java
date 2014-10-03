package com.kewill.kcx.component.mapping.countries.de.Port.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.AdditionalData;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.ConsolidatedContainer;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Party;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PostCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PreCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Voyage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Import<br>.
 * Created		: 12.09.2011<br>
 * Description	: KIDS MsgImportDeclaration
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgPortDeclaration extends KCXMessage { 
	
	private String			msgName = "PortDeclaration";
	private String			portSystem;              //aki_hasys: ZAPP, BHT
	//EI20120413: private String			declarationState;       //akr_artna; 9-original, 1-storno, 4=aenderungen ...
	private String			messageFunction;       //akr_artna; 9-original, 1-storno, 4=aenderungen ...
	private String			declarationType;         //aki_artauf; HDS, A06...,; LAS,AUS
	private String			declarationTypeSpecification; //aki_qartau: 001 in Verbindung mit HDS,...
	private String			declarationMode;         //aki_kzsaco: 0/leer = kein SACO,1=SACO-Anmeldung, 2=SACOKaiantrag
	private String			testMode;                //akr:knztst
		
	private String			referenceNumber;         //beznr
	private String			portRegistrationNumber;           //akr_bhtref	Z/B-Nummer BHT-Reference	
	private String			activityType;             //aki_leiart: nur ZAPP.A15
	private String			shipperReferenceNumber;  //akr_sptref
	private Party			shipper;                 //akr_spd.., aka=ContactPerson	
	private String	        terminalCode;            //akd_kaicde
	private String	        offerNumber;             //akd_offer
	private String          customsOfficeExport;     //akd_extdst	        
	private Party			agent;                   //akd_rdr..	
	private Party			invoiceConsignee;        //akd_are..
	private Party			fobShipper;              //akd_fob..	
	private Party			tally;                   //akd_fob..	
	private ContactPerson   dgsContactPerson;         //aka=ContactPerson: name, telefon	 
	private Voyage			voyage;                  //akt_...	
	private PostCarriage	postCarriage;                //akn_...	
	private PreCarriage		preCarriage;                 //akv_...	
	private AdditionalData    additionData;            //akz 
	//private List <String>   descriptionList;         //txt
	private String declarationRemark;   
	private String stockMarker;			 
	private String loadMarker;						   
	private String consolidatedContainerRemark;  
	
	private List<ConsolidatedContainer>  consolidatedContainerList;	  //aks: for declarationMode= 1		
	private List<GoodsItem>	goodsItemList = null;
	private List<Container> containerList;	         //aks:
	private List<Container> containerDetailsList;	  //EI20130508 BDP
		
	public MsgPortDeclaration() {
		super();
	}
	public MsgPortDeclaration(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EPortDeclaration {
		//KIDS:							KFF:
		ReferenceNumber,  				     		
		PortRegistrationNumber,  //EI20120427 umbenannt PortReference
		PortSystem,					
		MessageFunction,			
		DeclarationType,	
		DeclarationTypeSpecification,
		DeclarationMode,		
		ActivityType,
		TestMode,
		ShipperReferenceNumber,			
		Shipper,
		FOBShipper,
		Agent,
		InvoiceConsignee,
		Tally,
		ContactPersonForDangerousGoods,		
		TerminalCode,				
		OfferNumber,			
		CustomsOfficeExport,
		VoyageDetails,
		PostCarrige, PostCarriage,
		PreCarrige, PreCarriage,
		DeclarationRemark,      //fzptxt_txt: AAI
		StockMarker,			 //fzptxt_txt: PAC_B
		LoadMarker,				 //fzptxt_txt: PAC_V			
		AdditionInformation,		
		ConsolidatedContainer,    	
		ConsolidatedContainerRemark,  //fzptxt_txt: SACO 
		
		GoodsItem,
		Container,
		ContainerDetails,   //EI20130508 BDP: statt Container aus anderen Verfahren
		//EI20130508: BDP alle ComplexTags aufgelistet, damit nur PORT gelesen wird, parser 
		// geht naehmlich in die CT_Tags rein, die hier nicht definiert sind - kann u.U. zu faelschlichem 
        // fuellen von Variablen fueren, wenn die TagNamen zufaellig gleich sind, wie Reference, Code, Type usw.
		PlaceOfLoading, Warehouse, TransportationRoute, LoadingTime, ForwarderTIN, Forwarder,
		SubcontractorTIN, Subcontractor, SubcontractorContactPerson, CarrierTIN, Carrier, 
		PrincipalTIN, Principal, Guarantee, Business, Traders, PreviousDocument, 
		MeansOfTransportBorder, MeansOfTransportCrossingBorder, MeansOfTransportInland, MeansOfTransportArrival,
		MeansOfTransportDeparture, ContactPerson, IncoTerms, Invoice, Document, Deferment, DV1,
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EPortDeclaration) tag) {
			case Shipper:
				shipper = new Party(getScanner());
				shipper.parse(tag.name());
				break;
			case FOBShipper:		
				fobShipper = new Party(getScanner());
				fobShipper.parse(tag.name());					
				break;	
			case Agent:
				agent = new Party(getScanner());
				agent.parse(tag.name());					
				break;
			case InvoiceConsignee:
				invoiceConsignee = new Party(getScanner());
				invoiceConsignee.parse(tag.name());					
				break;
			case Tally:
				tally = new Party(getScanner());
				tally.parse(tag.name());					
				break;
			case ContactPersonForDangerousGoods:
				dgsContactPerson = new ContactPerson(getScanner());
				dgsContactPerson.parse(tag.name());						
				break;
			case VoyageDetails:
				voyage = new Voyage(getScanner());
				voyage.parse(tag.name());					
				break;
			case PostCarrige:
			case PostCarriage:
				postCarriage = new PostCarriage(getScanner());
				postCarriage.parse(tag.name());					
				break;
			case PreCarrige:
			case PreCarriage:
				preCarriage = new PreCarriage(getScanner());
				preCarriage.parse(tag.name());					
				break;
			case ConsolidatedContainer:
				ConsolidatedContainer saco = new ConsolidatedContainer(getScanner());
				saco.parse(tag.name());
				addConsolidatedContainerList(saco);
				break;
			case AdditionInformation:			
				additionData = new AdditionalData(getScanner());
				additionData.parse(tag.name());
				break;			
			case GoodsItem:
				GoodsItem goodsItem = new GoodsItem(getScanner());
				goodsItem.parse(tag.name());
				addGoodsItemList(goodsItem);
				break;			
			case Container:
				Container container = new Container(getScanner());
				container.parse(tag.name());
				addContainerList(container);
				break;
			case ContainerDetails:
				Container containerBDP = new Container(getScanner());
				containerBDP.parse(tag.name());
				addContainerDetailsList(containerBDP);
				break;
			default:
				return;
			}			 				
		} else {
			switch ((EPortDeclaration) tag) {
			case ReferenceNumber:			
				setReferenceNumber(value);
				break;
			case PortRegistrationNumber:		
				setPortRegistrationNumber(value);
				break;
			case PortSystem:		
				setPortSystem(value);
				break;
			case MessageFunction:			
				setMessageFunction(value);
				break;
			case DeclarationType:
				setDeclarationType(value);
				break;
			case DeclarationMode:	
				setDeclarationMode(value);
				break;
			case ActivityType:			
				setActivityType(value);
				break;
			case DeclarationTypeSpecification:				
				setDeclarationTypeSpecification(value);
				break;				
			case ShipperReferenceNumber:
				setShipperReferenceNumber(value);
				break;				
			case TerminalCode:				
				setTerminalCode(value);
				break;			
			case OfferNumber:				
				setOfferNumber(value);
				break;			
			case CustomsOfficeExport:
				setCustomsOfficeExport(value);
				break;	
			case DeclarationRemark:
				//declarationRemark = new Text(value, "AAI");  //EI20110926
				setDeclarationRemark(value);
				break;	
			case StockMarker:
				//stockMarker = new Text(value, "PAC_B");  //EI20110926
				setStockMarker(value);
				break;
			case LoadMarker:
				//loadMarker = new Text(value, "PAC_V");  //EI20110926
				setLoadMarker(value);
				break;
			case ConsolidatedContainerRemark:
				//consolidatedContainerRemark = new Text(value, "SAC");  //EI20110926
				setConsolidatedContainerRemark(value);
				break;
			case TestMode:
				setTestMode(value);
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
			return EPortDeclaration.valueOf(token);
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
	
	public String getTestMode() {
		return testMode;
	}
	public void setTestMode(String value) {
		this.testMode = value;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		//this.referenceNumber = referenceNumber;
		if (!Utils.isStringEmpty(referenceNumber)) {        //EI20130422
			if (Utils.isStringEmpty(this.referenceNumber)) {
				this.referenceNumber = referenceNumber;		
			}
		}
	}
	
	public String getPortRegistrationNumber() {
		return portRegistrationNumber;
	}
	public void setPortRegistrationNumber(String value) {
		this.portRegistrationNumber = value;
	}
	public String getPortSystem() {
		if (portSystem != null) {
			portSystem = portSystem.trim();
		}
		return portSystem;
	}
	public void setPortSystem(String value) {
		this.portSystem = value;
	}
	public String getMessageFunction() {
		return messageFunction;
	}
	public void setMessageFunction(String value) {
		this.messageFunction = value;
	}
	public String getDeclarationType() {
		return declarationType;
	}
	public void setDeclarationType(String value) {
		this.declarationType = value;
	}
	
	public String getDeclarationMode() {
		return declarationMode;
	}
	public void setDeclarationMode(String value) {
		this.declarationMode = value;
	}
	
	public String getDeclarationTypeSpecification() {
		return declarationTypeSpecification;
	}
	public void setDeclarationTypeSpecification(String value) {
		this.declarationTypeSpecification = value;
	}
	
	
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String value) {
		this.activityType = value;
	}
					
	public String getShipperReferenceNumber() {
		return shipperReferenceNumber;
	}
	public void setShipperReferenceNumber(String value) {
		this.shipperReferenceNumber = value;
	}
		
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String value) {
		this.terminalCode = value;
	}
		
	public String getOfferNumber() {
		return offerNumber;
	}
	public void setOfferNumber(String value) {
		this.offerNumber = value;
	}
	
	public String getCustomsOfficeExport() {
		return customsOfficeExport;
	}
	public void setCustomsOfficeExport(String value) {
		//this.customsOfficeExport = value;
		if (!Utils.isStringEmpty(value)) {                //EI20130422
			if (Utils.isStringEmpty(this.customsOfficeExport)) {
				this.customsOfficeExport = value;		
			}
		}
	}
	
	public String getDeclarationRemark() {
		return declarationRemark;
	}
	public void setDeclarationRemark(String text) {
		this.declarationRemark = text;
	}
	
	public String getStockMarker() {
		return stockMarker;
	}
	public void setStockMarker(String text) {
		this.stockMarker = text;
	}
	
	public String getLoadMarker() {
		return loadMarker;
	}
	public void setLoadMarker(String text) {
		this.loadMarker = text;
	}
	
	public String getConsolidatedContainerRemark() {
		return consolidatedContainerRemark;
	}
	public void setConsolidatedContainerRemark(String text) {
		this.consolidatedContainerRemark = text;
	}
		
	
	public Party getAgent() {
		return agent;
	}
	public void setAgent(Party party) {
		this.agent = party;
	}
	
	public Party getShipper() {
		return shipper;
	}
	public void setShipper(Party party) {
		this.shipper = party;
	}
	
	public Party getInvoiceConsignee() {
		return invoiceConsignee;
	}
	public void setInvoiceConsignee(Party party) {
		this.invoiceConsignee = party;
	}
	
	public Party getFOBShipper() {
		return fobShipper;
	}
	public void setFOBShipper(Party party) {
		this.fobShipper = party;
	}
		
	public Party getTally() {
		return tally;
	}
	public void setTally(Party party) {
		this.tally = party;
	}
	
	public ContactPerson getContactPersonForDangerousGoods() {
		return dgsContactPerson;
	}
	public void setContactPersonForDangerousGoods(ContactPerson contact) {
		dgsContactPerson = contact;
	}
	
	public Voyage getVoyage() {
		return voyage;
	}
	public void setVoyage(Voyage argument) {
		this.voyage = argument;
	}
	
	public PostCarriage getPostCarriage() {
		return postCarriage;
	}
	public void setPostCarriage(PostCarriage argument) {
		this.postCarriage = argument;
	}
	
	public PreCarriage getPreCarriage() {
		return preCarriage;
	}
	public void setPreCarriage(PreCarriage argument) {
		this.preCarriage = argument;
	}
	
	public AdditionalData getAdditionalData() {
		return additionData;
	}
	public void setAdditionalData(AdditionalData data) {
		this.additionData = data;
	}
	
	public List<ConsolidatedContainer> getConsolidatedContainerList() {
		return consolidatedContainerList;
	}
	public void setConsolidatedContainerList(List<ConsolidatedContainer> list) {
		this.consolidatedContainerList = list;
	}
	public void addConsolidatedContainerList(ConsolidatedContainer value) {
		if (consolidatedContainerList == null) {
			consolidatedContainerList = new Vector<ConsolidatedContainer>();	
		}
		this.consolidatedContainerList.add(value);
	}
	
	public List<GoodsItem> getGoodsItemList() {
		return goodsItemList;
	}
	public void setGoodsItemList(List<GoodsItem> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(GoodsItem item) {
		if (goodsItemList == null) {
			goodsItemList = new Vector<GoodsItem>();	
		}
		this.goodsItemList.add(item);
	}
	
	public List<Container> getContainerList() {
		return containerList;
	}
	public void setContainerList(List<Container> list) {
		this.containerList = list;
	}
	public void addContainerList(Container document) {
		if (containerList == null) {
			containerList = new Vector<Container>();	
		}
		this.containerList.add(document);
	}
	
	public List<Container> getContainerDetailsList() {
		return containerDetailsList;
	}
	public void setContainerDetailsList(List<Container> list) {
		this.containerDetailsList = list;
	}
	public void addContainerDetailsList(Container document) {
		if (containerDetailsList == null) {
			containerDetailsList = new Vector<Container>();	
		}
		this.containerDetailsList.add(document);
	}
}
