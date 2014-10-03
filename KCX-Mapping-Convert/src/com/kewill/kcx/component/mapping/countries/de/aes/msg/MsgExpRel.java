/*
 * Function    : MsgExpRel.java
 * Titel       :
 * Date        : 16.03.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : KIDS-ReverseDeclaration,
 * 		       : V60
 * -----------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 08.05.2009 
 * Label       : 
 * Description : in Enum added/changed Incoterms, CustomsOffice(s)
 *
 * Author      : krzoska 
 * Date        : 
 * Label       : AK20090512
 * Description : fexeda_fregnr = CommercialReferenceNumber, 
 * 		         fsssas_knrsdg = ExternalRegistrationNumber
 *
 * Author      : krzoska 
 * Date        : 
 * Label       : AK20090513
 * Description : fexeda_fregnr = ExternalRegistrationNumber/UCROtherSystem
 * 		         fsssas_knrsdg = CommercialReferenceNumber/ShipmentNumber
 *
 * Author      : EI
 * Date        : 25.05.2009 
 * Description : PlaceOfLoading wieder aktiviert
 * 
 * Author      : EI
 * Label       : EI09.06.08
 * Description : ContactPerson instead of clerk
 * 
 * Author      : EI
 * Label       : EI20100607
 * Description : new member: exitTime
 *
 * Author      : EI
 * Label       : EI20100810
 * Description : new members - all from xsd
 *               clearing of yellow checkstyle marks
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TypeOfDeclaration;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgExpRel<br>
 * Erstellt		: 16.03.2009<br>
 * Beschreibung	: KIDS-ReverseDeclaration. 
 *                V60
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExpRel extends KCXMessage {
	
	private String msgName;
		
	private String areaCode;
	private String typeOfPermit;
	private String kindOfAnswer;
	private String declarationKind;             //for CH only <== EI20100810: now for all
	private String ucrNumber;  
	private String completionTime;				//ST_DateTime	
	private String declarationTime;	            //ST_DateTime 
	private String declarationNumberForwarder;  //for CH only <== EI20100810: now for all
	private String declarationNumberCustoms;	//for CH only <== EI20100810: now for all
	private String exitTime; 					//EI20100607
	private String dispatchCountry;        	
	private String destinationCountry;
	private String releaseTime;						
	private String acceptanceTime;				//for DE and CH <== EI20100810: now for all
	private String receiveTime;						
	private String revisionCode;			    //for CH only <== EI20100810: now for all
	private String codeOfRelease;				//for CH only <== EI20100810: now for all
	private String shortageInQuantity;
	private String alternativeDocument;
	private String situationCode; 
	private String paymentType;  
	private String transportInContainer;
	private String grossMass;					
	private String uCROtherSystem;
	private String annotation;
	private String shipmentNumber;   
	private String referenceNumber; 
	private String orderNumber;	
	private String totalNumberPackages;
	private String totalNumberPositions;	
	private String receiverCustomerNumber;    //für UIDS noch nicht definiert
	private String declarantIsConsignor;
	private String typeOfRepresentation;      //EI20100810 
	//EI090608: private String clerk;
	private ContactPerson contact;            //EI090608
	private String validity;                  //EI20100810
	private PDFInformation pdfInformation;
	private WareHouse warehouse;              //EI20100810 
	private TransportMeans transportInland;
    private TransportMeans transportBorder; 
    private TransportMeans transportDeparture; //Departure ist für UIDS nicht definiert, aber in xls ist ein Dreh
    										   //so dass nicht sicher ist ob departure oder Border entfällt
    private PlaceOfLoading placeOfLoading;  
    private String officeOfExport; 
    private String officeForCompletion; 	
    private String intendedOfficeOfExit;      //EI20100810
    private String realOfficeOfExit;   
    private Business business;
    private Route transportationRoute;    
    private Seal  seal;
    private ExportRefundHeader expRefHeader;
    private LoadingTime loadingTime;  
       
    private Party consignor;  
    private TIN consignorTIN;
    private ContactPerson consignorContactPerson;
    private Party declarant; 
    private TIN declarantTIN;
    private ContactPerson declarantContactPerson;
    private Party agent; 
    private TIN agentTIN; 
    private ContactPerson agentContactPerson;         
    private Party subcontractor; 
    private TIN subcontractorTIN;
    private ContactPerson subcontractorContactPerson;
    private Party consignee; 
    private TIN consigneeTIN;   
    private TIN documentsReceiverTIN;         //EI20100810
    private Party documentsReceiver;          //EI20100810
    private TIN representedTIN;               //EI20100810
    private Party represented;                //EI20100810
    private TIN warehouseKeeperTIN;           //EI20100810
    private Party warehouseKeeper;            //EI20100810
   
    private IncoTerms incoTerms;
    private Document document;
    private List <Document> documentList;   
    
    private HeaderExtensions headerExtensions;  //nur bei UIDS
    private String dateOfLoadingBegin; 
    private String dateOfLoadingEnd;
    
    private MsgExpRelPos goodsItem;
    private List <MsgExpRelPos>goodsItemList;
    private Invoice invoice;                   //EI20100810
    
    private EFormat acceptanceTimeFormat;
    private EFormat completionTimeFormat;
    private EFormat declarationTimeFormat;
    private EFormat ExitTimeFormat;
    private EFormat loadingBeginFormat;
    private EFormat loadingEndFormat;
    private EFormat receiveTimeFormat;
    private EFormat releaseTimeFormat;

    
    
//	private XMLEventReader 	parser;
//    private boolean debug;
    
	public MsgExpRel() {
			super();
			goodsItemList = new Vector<MsgExpRelPos>();			
			documentList = new Vector<Document>();
	}
	
	public MsgExpRel(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		goodsItemList   = new Vector<MsgExpRelPos>();		
		documentList    = new Vector<Document>();	
	}
	 
	public MsgExpRel(XMLEventReader parser, String msgName) throws XMLStreamException {	
		super(parser);
		goodsItemList   = new Vector<MsgExpRelPos>();		
		documentList    = new Vector<Document>();
		this.msgName = msgName;	
	}
	
	private enum EDeclaration {		 
		 Agent,		  		 			DeclarantRepresentative,
		 AgentTIN,						//
		 AgentContactPerson,			// 
		 AlternativeDocument,           AlternateProofIndicator,  
		 Annotation,					Remark,
		 AreaCode,		 				TypeOfDeclaration, //TypeOfDeclaration.RegionCode	 		
		 Business,						Transaction, 		 	 
		 Clerk,							
		 ContactPerson,                 Contact,   //Contact.Identity
		//CH: CodeOfRelease,	            ExportDeclarationResponse.HeaderExtensions.EDECReleaseFlag		 
		 CompletionTime,				DateOfAdditionalDeclaration,
		 CustomsOfficeExport,			CustomsOffices, //CustomsOffice.OfficeOfExport
		 CustomsOfficeForCompletion,	//CustomsOffice.OfficeOfAdditionalDeclarationExport
		 Consignee,						//same
		 ConsigneeTIN,			 		//
		 Consignor,						Exporter,				
		 ConsignorTIN,					//
		 ConsignorContactPerson,		//
		 DeclarantIsConsignor,			ParticipantsCombination,  	
		 AcceptanceTime,				DateOfAcceptance, //CH: ExportDeclarationResponse.DateOfRelease	 
		//CH: DeclarationKind,              ExportDeclarationResponse.ApplicationReference		 
		//CH: DeclarationNumberForwarder, 	ExportDeclarationResponse.LocalReferenceNumber  		 
		//CH: DeclarationNumberCustoms,     ExportDeclarationResponse.RegistrationNumber
		//CH: RevisionCode,     			ExportDeclarationResponse.HeaderExtensions.EDECRevisionFlag		  
		 DeclarationKind,               // -
		 DeclarationNumberForwarder,    // -
		 DeclarationNumberCustoms,      // -
		 TypeOfRepresentation,          // -
		 RevisionCode,					// -
		 CodeOfRelease,                 // -
		 Validity,                      // -
		 Warehouse,                     // -
		 IntendedOfficeOfExit,          // - 
		 WarehouseKeeper,               // -
		 WarehouseKeeperTIN,            // -
		 CustomsDocumentsReceiver,      // -
		 CustomsDocumentsReceiverTIN,   // -
		 Represented,                   // -
		 RepresentedTIN,                // -
		 Invoice,                       // -
		 DeclarationTime,				DateOfDeclaration,		 
		 Declarant,	  					//same
		 DeclarantTIN,					//
		 DeclarantContactPerson,		//		 
		 DestinationCountry,			//same 
		 DispatchCountry,				CountryOfDispatch,	
		 Document,						ProducedDocument, 	
		 ExitTime,						DateOfExit,
		 ExportRefundHeader,			ExportRestitutionHeader, //TODO?
		 GrossMass,						//same
		 IncoTerms,						Incoterms,
		 KindOfAnswer,					TypeOfRelease,			 
		 LoadingTime,					ShipmentPeriod, 
		 								DateOfLoadingBegin, DateOfLoadingEnd,
		 MeansOfTransportInland,  		//MeansOfTransport, attr: Inland  			   
		 MeansOfTransportBorder,		//MeansOfTransport, attr: Border
		 MeansOfTransportDeparture,
		 								MeansOfTransport,
		 OrderNumber,					LocalReferenceNumber,	
		 PaymentType,					TransportPaymentMethod,	 
		 PlaceOfLoading,                //same  
		 PDFInformation,		 		PDF,		 		 
		 RealOfficeOfExit,				//CustomsOffice.OfficeOfExit
		 ReceiverCustomerNumber,		//TODO noch kein UIDS-Tag
		 ReceiveTime,					DateOfReceipt,		 		
		 ReferenceNumber,				//same				//for DE and CH	  
		 ReleaseTime,					DateOfRelease,		 		 		
		 Seal,							Seals, 
		 								HeaderExtensions, 
		 //AK20090513
		 ShipmentNumber,				CommercialReferenceNumber,
		 ShortageInQuantity,			ShortageIndicator,
		 SituationCode,					SpecCircumstance,
		 Subcontractor, 				//same
		 SubcontractorTIN,				//
		 SubcontractorContactPerson,	//		 				 		 
		 TotalNumberPackages,			PackagesQuantity,
		 TotalNumberPositions,			ItemsQuantity,
		 TransportInContainer,			ContainerIndicator,	  
		 TransportationRoute,			Itinerary, 
		 TypeOfPermit,					//TypeOfDeclaration.ProcedureCode
		 UCRNumber,						DocumentReferenceNumber,
		 
		 //AK20090512
		 UCROtherSystem,				ExternalRegistrationNumber,
		
		 Goodsitem,                     //same
		 GoodsItem;  	 		 
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EDeclaration) tag) {				
					case TypeOfDeclaration:
						TypeOfDeclaration typeOfDeclaration = new TypeOfDeclaration(getScanner());
						typeOfDeclaration.parse(tag.name());
						this.areaCode = typeOfDeclaration.getRegionCode();
						this.typeOfPermit = typeOfDeclaration.getProcedureCode();
						break;						
					case Contact:
					case ContactPerson:
						String clerk = "";
						if (contact != null) {
							clerk = contact.getClerk(); //EI090608 wohin damit??? 
						}
						contact = new ContactPerson(getScanner());
						contact.parse(tag.name());
						//this.clerk = contact.getClerk();
						break;					
					case CustomsOffices:
						CustomsOffices customsOffice = new CustomsOffices(getScanner());
						customsOffice.parse(tag.name());
						this.officeOfExport = customsOffice.getOfficeOfExport();
						this.officeForCompletion = customsOffice.getOfficeOfAdditionalDeclarationExport();
						this.realOfficeOfExit = customsOffice.getOfficeOfExit();
						break;						
					case PDFInformation:
					case PDF:
						pdfInformation = new PDFInformation(getScanner());
						pdfInformation.parse(tag.name());
						break;						
					case MeansOfTransportInland:
						transportInland = new TransportMeans(getScanner());  	
						transportInland.parse(tag.name());
						break;
					case MeansOfTransportBorder:	
						transportBorder = new TransportMeans(getScanner());  
						transportBorder.parse(tag.name());
						break;							
					case MeansOfTransportDeparture:	
						transportDeparture = new TransportMeans(getScanner());  
						transportDeparture.parse(tag.name());
						break;							
					case MeansOfTransport:
						if (attr != null) {  //EI20120309
						String tt = attr.getValue("TransportType");
						if (!Utils.isStringEmpty(tt)) {
							if (tt.equals("Inland")) {
								transportInland = new TransportMeans(getScanner());
								transportInland.parse(tag.name());
							} else if (tt.equals("Departure")) {
								transportDeparture = new TransportMeans(getScanner());
								transportDeparture.parse(tag.name());
							} else if (tt.equals("Border")) {
								transportBorder = new TransportMeans(getScanner());
								transportBorder.parse(tag.name());
							}
						}
						}
						break;						
					case PlaceOfLoading:	
						placeOfLoading = new PlaceOfLoading(getScanner());
						placeOfLoading.parse(tag.name());
						break;						
					case Business:
					case Transaction:
						business = new Business(getScanner());
						business.parse(tag.name());
						break;							
					case TransportationRoute:
					case Itinerary:
						transportationRoute = new Route(getScanner());
						transportationRoute.parse(tag.name());
						break;
					case Seal:
					case Seals:
						seal = new Seal(getScanner());
						seal.parse(tag.name());
						break;							
					case ExportRefundHeader:
						expRefHeader = new ExportRefundHeader(getScanner(), "K");
						expRefHeader.parse(tag.name());
						break;		
					case ExportRestitutionHeader:
						expRefHeader = new ExportRefundHeader(getScanner(), "U");
						expRefHeader.parse(tag.name());
						break;							
					case LoadingTime:
					case ShipmentPeriod:
						loadingTime = new LoadingTime(getScanner());
						loadingTime.parse(tag.name());
						break;
					case Consignor:
					case Exporter:
						consignor = new Party(getScanner());
						consignor.parse(tag.name());						
						break;							
					case ConsignorTIN:
						consignorTIN = new TIN(getScanner());
						consignorTIN.parse(tag.name());						
						break;							
					case ConsignorContactPerson:
						consignorContactPerson = new ContactPerson(getScanner());
						consignorContactPerson.parse(tag.name());
						break;						
					case Declarant:
						declarant = new Party(getScanner());
						declarant.parse(tag.name());						
						break;						
					case DeclarantTIN:
						declarantTIN = new TIN(getScanner());
						declarantTIN.parse(tag.name());					
						break;
					case DeclarantContactPerson:
						declarantContactPerson = new ContactPerson(getScanner());
						declarantContactPerson.parse(tag.name());
						break;
					case Agent:
					case DeclarantRepresentative:
						agent = new Party(getScanner());
						agent.parse(tag.name());						
						break;							
					case AgentTIN:
						agentTIN = new TIN(getScanner());
						agentTIN.parse(tag.name());						
						break;						
					case AgentContactPerson:
						agentContactPerson = new ContactPerson(getScanner());
						agentContactPerson.parse(tag.name());
						break;						
					case Subcontractor:
						subcontractor = new Party(getScanner());
						subcontractor.parse(tag.name());
						break;
					case SubcontractorTIN:
						subcontractorTIN = new TIN(getScanner());
						subcontractorTIN.parse(tag.name());
						break;							
					case SubcontractorContactPerson:
						subcontractorContactPerson = new ContactPerson(getScanner());
						subcontractorContactPerson.parse(tag.name());
						break;						
					case Consignee:
						consignee = new Party(getScanner());
						consignee.parse(tag.name());						
						break;						
					case ConsigneeTIN:
						consigneeTIN = new TIN(getScanner());
						consigneeTIN.parse(tag.name());
						break;								
					case IncoTerms:
					case Incoterms:
						incoTerms = new IncoTerms(getScanner());
						incoTerms.parse(tag.name());
						break;							
					case Document:					
						document = new Document(getScanner(), "K");
						document.parse(tag.name());
						documentList.add(document);
						break;							
					case ProducedDocument:
						document = new Document(getScanner(), "U");
						document.parse(tag.name());
						documentList.add(document);	
						break;
					case HeaderExtensions:
						headerExtensions = new HeaderExtensions(getScanner());
						headerExtensions.parse(tag.name());
						//setSealTydenseals(headerExtensions);
						break;							
					case Warehouse:
						warehouse = new WareHouse(getScanner());
						warehouse.parse(tag.name());
						break;
					case WarehouseKeeper:
						warehouseKeeper = new Party(getScanner());
						warehouseKeeper.parse(tag.name());
						break;
					case WarehouseKeeperTIN:
						warehouseKeeperTIN = new TIN(getScanner());
						warehouseKeeperTIN.parse(tag.name());
						break;
					case CustomsDocumentsReceiver:
						documentsReceiver = new Party(getScanner());
						documentsReceiver.parse(tag.name());
						break;
					case CustomsDocumentsReceiverTIN:
						documentsReceiverTIN = new TIN(getScanner());
						documentsReceiverTIN.parse(tag.name());
						break;
					case Represented:
						represented = new Party(getScanner());
						represented.parse(tag.name());
						break;
					case RepresentedTIN:
						representedTIN = new TIN(getScanner());
						representedTIN.parse(tag.name());
						break;
					case Invoice:
						invoice = new Invoice(getScanner());
						invoice.parse(tag.name());
						break;
					case Goodsitem:	
					case GoodsItem:
						goodsItem = new  MsgExpRelPos(getScanner(), msgName);
						goodsItem.parse(tag.name());
						addGoodsItemList(goodsItem);
						break;					
				default:
						return;
				}
			} else {
				switch ((EDeclaration) tag) {				
					case AreaCode:					
						setAreaCode(value);
						break;							
					case TypeOfPermit:
						setTypeOfPermit(value);
						break;
					case KindOfAnswer:
					case TypeOfRelease:
						setKindOfAnswer(value);
						break;	
					case DeclarationKind:	
						setDeclarationKind(value);
						break;
					case UCRNumber:
					case DocumentReferenceNumber:
						setUCRNumber(value);
						break;						
					case CompletionTime:
					case DateOfAdditionalDeclaration:
						if (tag == EDeclaration.CompletionTime) {
	    					 setCompletionTimeFormat(EFormat.KIDS_DateTime);
	    				 } else {
	    					 setCompletionTimeFormat(getUidsDateAndTimeFormat(value)); 
	    				 }
						setCompletionTime(value);
						break;									
					case DeclarationTime:
					case DateOfDeclaration:
						if (tag == EDeclaration.DeclarationTime) {
	    					 setDeclarationTimeFormat(EFormat.KIDS_DateTime);
	    				 } else {
	    					 setDeclarationTimeFormat(getUidsDateAndTimeFormat(value)); 
	    				 }
						setDeclarationTime(value);
						break;	
					case DeclarationNumberForwarder:
						setDeclarationNumberForwarder(value);
						break;
					case DeclarationNumberCustoms:
						setDeclarationNumberCustoms(value);
						break;
					case DispatchCountry:
					case CountryOfDispatch:
						setDispatchCountry(value);
						break;						
					case DestinationCountry:
						setDestinationCountry(value);
						break;					
					case ReleaseTime:
					case DateOfRelease:
						if (tag == EDeclaration.ReleaseTime) {
	    					 setReleaseTimeFormat(EFormat.KIDS_DateTime);
	    				 } else {
	    					 setReleaseTimeFormat(getUidsDateAndTimeFormat(value)); 
	    				 }
						setReleaseTime(value);
						break;						
					case AcceptanceTime:
					case DateOfAcceptance:
						if (tag == EDeclaration.AcceptanceTime) {
	    					 setAcceptanceTimeFormat(EFormat.KIDS_DateTime);
	    				 } else {
	    					 setAcceptanceTimeFormat(getUidsDateAndTimeFormat(value)); 
	    				 }
						setAcceptanceTime(value);
						break;					
					case ReceiveTime:
					case DateOfReceipt:
						if (tag == EDeclaration.ReceiveTime) {
	    					 setReceiveTimeFormat(EFormat.KIDS_DateTime);
	    				 } else {
	    					 setReceiveTimeFormat(getUidsDateAndTimeFormat(value)); 
	    				 }
						setReceiveTime(value);
						break;	
					case RevisionCode:
						setRevisionCode(value);
						break;
					case CodeOfRelease:
						setCodeOfRelease(value);
						break;
					case ShortageInQuantity:
					case ShortageIndicator:
						setShortageInQuantity(value);
						break;							
					case SituationCode:
					case SpecCircumstance:
						setSituationCode(value);
						break;							
					case PaymentType:
					case TransportPaymentMethod:
						setPaymentType(value);
						break;							
					case TransportInContainer:
					case ContainerIndicator:
						setTransportInContainer(value);
						break;							
					case GrossMass:
						setGrossMass(value);
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
					case ReferenceNumber:
						setReferenceNumber(value);
						break;						
					case OrderNumber:
					case LocalReferenceNumber:
						setOrderNumber(value);
						break;						
					case ReceiverCustomerNumber:
						setReceiverCustomerNumber(value);
						break;							
					case DeclarantIsConsignor:
					case ParticipantsCombination:
						setDeclarantIsConsignor(value);
						break;						
					case Clerk:
						//setClerk(value);
						if (contact == null) {						
							contact = new ContactPerson();
						    contact.setClerk(value);
						}						
						break;					
					case TotalNumberPackages:
					case PackagesQuantity:
						setTotalNumberPackages(value);
						break;						
					case TotalNumberPositions:
					case ItemsQuantity:
						setTotalNumberPositions(value);
						break;					
					case CustomsOfficeExport:
						setCustomsOfficeExport(value);
						break;						
					case CustomsOfficeForCompletion:
						setCustomsOfficeForCompletion(value);
						break;						
					case RealOfficeOfExit:
						setRealOfficeOfExit(value);
						break;					
					case AlternativeDocument:
					case AlternateProofIndicator:
						setAlternativeDocument(value);
						break;							
					case DateOfLoadingBegin:
						setDateOfLoadingBegin(value);
						break;
					case DateOfLoadingEnd:
						setDateOfLoadingEnd(value);
						break;
					case ExitTime:
					case DateOfExit:	
						if (tag == EDeclaration.ExitTime) {
	    					 setExitTimeFormat(EFormat.KIDS_DateTime);
	    				 } else {
	    					 setExitTimeFormat(getUidsDateAndTimeFormat(value)); 
	    				 }						
						setExitTime(value);
						break;
					case Validity:
						setValidity(value);
						break;
					case TypeOfRepresentation:
						setTypeOfRepresentation(value);
							break;
					case IntendedOfficeOfExit:
						setIntendedOfficeOfExit(value);
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
				return EDeclaration.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}

	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String argument) {
		this.areaCode = argument;
	}

	public String getTypeOfPermit() {
		return typeOfPermit;
	}
	public void setTypeOfPermit(String argument) {
		this.typeOfPermit = argument;
	}

	public String getKindOfAnswer() {
		return kindOfAnswer;
	}
	public void setKindOfAnswer(String argument) {
		this.kindOfAnswer = argument;
	}

	public String getDeclarationKind() {
		return declarationKind;
	}
	public void setDeclarationKind(String argument) {
		this.declarationKind = argument;
	}

	public String getUCRNumber() {
		return ucrNumber;
	}
	public void setUCRNumber(String argument) {
		this.ucrNumber = argument;
	}

	public String getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(String argument) {
		this.completionTime = argument;
	}

	public String getDeclarationTime() {
		return declarationTime;
	}
	public void setDeclarationTime(String argument) {
		this.declarationTime = argument;
	}
 
	public String getDeclarationNumberForwarder() {
		return declarationNumberForwarder;
	}
	public void setDeclarationNumberForwarder(String argument) {
		this.declarationNumberForwarder = argument;
	}
    
	public String getDeclarationNumberCustoms() {
		return declarationNumberCustoms;
	}
	public void setDeclarationNumberCustoms(String argument) {
		this.declarationNumberCustoms = argument;
	}

	public String getDispatchCountry() {
		return dispatchCountry;
	}
	public void setDispatchCountry(String argument) {
		this.dispatchCountry = argument;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String argument) {
		this.destinationCountry = argument;
	}

	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String argument) {
		this.releaseTime = argument;
	}

	public String getAcceptanceTime() {
		return acceptanceTime;
	}
	public void setAcceptanceTime(String argument) {
		this.acceptanceTime = argument;
	}

	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String argument) {
		this.receiveTime = argument;
	}
	
	public String getRevisionCode() {
		return revisionCode;
	}
	public void setRevisionCode(String argument) {
		this.revisionCode = argument;
	}

	public String getCodeOfRelease() {
		return codeOfRelease;
	}
	public void setCodeOfRelease(String argument) {
		this.codeOfRelease = argument;
	}

	public String getShortageInQuantity() {
		return shortageInQuantity;
	}
	public void setShortageInQuantity(String argument) {
		this.shortageInQuantity = argument;
	}


	public String getSituationCode() {
		return situationCode;
	}
	public void setSituationCode(String argument) {
		this.situationCode = argument;
	}

	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String argument) {
		this.paymentType = argument;
	}
	
	public String getTransportInContainer() {
		return transportInContainer;
	}
	public void setTransportInContainer(String argument) {
		this.transportInContainer = argument;
	}

	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String argument) {
		this.grossMass = argument;
	}

	public String getUCROtherSystem() {
		return uCROtherSystem;
	}
	public void setUCROtherSystem(String argument) {
		this.uCROtherSystem = argument;
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
	public void setShipmentNumber(String argument) {
		this.shipmentNumber = argument;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTotalNumberPackages() {
		return totalNumberPackages;
	}
	public void setTotalNumberPackages(String argument) {
		this.totalNumberPackages = argument;
	}

	public String getTotalNumberPositions() {
		return totalNumberPositions;
	}
	public void setTotalNumberPositions(String argument) {
		this.totalNumberPositions = argument;
	}

	public String getReceiverCustomerNumber() {
		return receiverCustomerNumber;
	}
	public void setReceiverCustomerNumber(String argument) {
		this.receiverCustomerNumber = argument;
	}
	
	public String getDeclarantIsConsignor() {
		return declarantIsConsignor;
	}
	public void setDeclarantIsConsignor(String argument) {
		this.declarantIsConsignor = argument;
	}
    /*
	public String getClerk() {
		return clerk;
	}
	public void setClerk(String argument) {
		this.clerk = argument;
	}
    */
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}
	public ContactPerson getContact() {
		return contact;
	}	
	
	public PDFInformation getPdfInformation() {
		return pdfInformation;
	}

	public void setPdfInformation(PDFInformation pdfInformation) {
		this.pdfInformation = pdfInformation;
	}

	public TransportMeans getTransportMeansInland() { 
		return transportInland;
	}
	public void setTransportMeansInland(TransportMeans argument) {
		this.transportInland = argument;
	}
	
	public TransportMeans getTransportMeansBorder() { 
		return transportBorder;
	}
	public void setTransportMeansBorder(TransportMeans argument) {
		this.transportBorder = argument;
	}
	public TransportMeans getTransportMeansDeparture() { 
		return transportDeparture;
	}
	public void setTransportMeansDeparture(TransportMeans argument) {
		this.transportDeparture = argument;
	}	

	public PlaceOfLoading getPlaceOfLoading() {
		return placeOfLoading; 
	}
	public void setPlaceOfLoading(PlaceOfLoading argument) {
		this.placeOfLoading = argument;
	}

	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business argument) {
		this.business = argument;
	}
	
    public Route getRoute() { 
    	return transportationRoute;
    }
	public void setRoute(Route argument) {
		this.transportationRoute = argument;
	}
    
    public Seal getSeal() {    	   
    	if (this.headerExtensions != null) {				 
    		if (this.seal == null) {
    			this.seal = new Seal();
    		}			 
    		this.seal.setUseOfTydenseals(this.headerExtensions.getTydenSealsFlag());
    		this.seal.setUseOfTydensealStock(this.headerExtensions.getTydenSealsStockFlag());
    	}    	
		return seal;
	}
	public void setSeal(Seal argument) {
		this.seal = argument;
	}
	
    public ExportRefundHeader getExportRefundHeader() {
    	return expRefHeader;
	}
	public void setExportRefundHeader(ExportRefundHeader argument) {
		this.expRefHeader = argument;
	}
	
    public LoadingTime getLoadingTime() {
    	return loadingTime;
    }
	public void setLoadingTime(LoadingTime argument) {
		this.loadingTime = argument;
	}
	
	public Party getConsignor() {
		if (consignorTIN != null) {
			if (consignor == null) {
				consignor = new Party();
			}
			consignor.setPartyTIN(consignorTIN);
		}
		if (consignorContactPerson != null) {
			if (consignor == null) {
				consignor = new Party();	
			}
			consignor.setContactPerson(consignorContactPerson);
		}			
		return consignor;
	}	    
	public void setConsignor(Party argument) {
		this.consignor = argument;
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
	
	public Party getDeclarant() {
		if (declarantTIN != null) {
			if (declarant == null) {
				declarant = new Party();
			}
			declarant.setPartyTIN(declarantTIN);
		}
		if (declarantContactPerson != null) {
			if (declarant == null) {
				declarant = new Party();
			}
			declarant.setContactPerson(declarantContactPerson);
		}			
		return declarant;
	}	
	public void setDeclarant(Party argument) {
		this.declarant = argument;
	}
	
	public Party getAgent() {
		if (agentTIN != null) {
			if (agent == null) {
				agent = new Party();
			}
			agent.setPartyTIN(agentTIN);
		}
		if (agentContactPerson != null) {
			if (agent == null) {
				agent = new Party();
			}
			agent.setContactPerson(agentContactPerson);
		}			
		return agent;
	}	
	public void setAgent(Party argument) {
		this.agent = argument;
	}
	
	public Party getSubcontractor() {
		if (subcontractorTIN != null) {
			if (subcontractor == null) {
				subcontractor = new Party();
			}
			subcontractor.setPartyTIN(subcontractorTIN);
		}
		if (subcontractorContactPerson != null) {
			if (subcontractor == null) {
				subcontractor = new Party();	
			}
			subcontractor.setContactPerson(subcontractorContactPerson);
		}			
		return subcontractor;
	}
	public void setSubcontractor(Party argument) {
		this.subcontractor = argument;
	}
	
    public IncoTerms getIncoTerms() {
    	return incoTerms;
    }
	public void setIncoTerms(IncoTerms argument) {
		this.incoTerms = argument;
	}
	
    public Document getDocument() {
    	return document;
    }
	public void setDocument(Document argument) {
		this.document = argument;
	}
	
    public MsgExpRelPos getMsgExpRelPos() {
    	return goodsItem;
    }
	public void setMsgExpRelPos(MsgExpRelPos argument) {
		this.goodsItem = argument;
	}
	
    public List <MsgExpRelPos>getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(MsgExpRelPos argument) {
    	this.goodsItemList.add(argument);
    }
    public void setGoodsItemList(List <MsgExpRelPos> argument) {
    	this.goodsItemList = argument;  	
    }

	public MsgExpRelPos getGoodsItem() {
    	return goodsItem;
    }
  
    public void setGoodsItemList(MsgExpRelPos argument) {
    	this.goodsItem = argument;  	
    }

	public String getMsgName() {
		return msgName;
	}

	public void setMsgType(String msgName) {
		this.msgName = msgName;
	}

	public String getCustomsOfficeExport() {
		return officeOfExport;
	}

	public void setCustomsOfficeExport(String argument) {
		this.officeOfExport = argument;
	}

	public String getCustomsOfficeForCompletion() {
		return officeForCompletion;
	}

	public void setCustomsOfficeForCompletion(String argument) {
		this.officeForCompletion = argument;
	}

	public String getRealOfficeOfExit() {
		return realOfficeOfExit;
	}
	public void setRealOfficeOfExit(String argument) {
		this.realOfficeOfExit = argument;
	}

	public List<Document> getDocumentList() {
		return documentList;	
	}
	
	public void addDocumentList(Document argument) {   //EI20090306
		this.documentList.add(argument);
	}
	
	public String getAlternativeDocument() {
		return alternativeDocument;
	
	}

	public void setAlternativeDocument(String argument) {
		this.alternativeDocument = Utils.checkNull(argument);
	}	
		
     public String getDateOfLoadingBegin() {
    	 return dateOfLoadingBegin;
     }
	 public void setDateOfLoadingBegin(String argument) {
		 dateOfLoadingBegin = argument;
	}

	 public String getDateOfLoadingEnd() {
		return dateOfLoadingEnd; 
	 }
	 public void setDateOfLoadingEnd(String argument) {
		 dateOfLoadingEnd = argument;
	 }
	 	 
	 public String getExitTime() {
		return exitTime; 
	 }
	 public void setExitTime(String argument) {
		exitTime = argument;
	 }
	 public String getValidity() {
		 return validity;
	 }
	 public void setValidity(String argument) {
		 validity = argument;
	 }
	 public String getTypeOfRepresentation() {
		 return typeOfRepresentation;
	 }
	 public void setTypeOfRepresentation(String argument) {
		 typeOfRepresentation = argument;
	 }
	 public String getIntendedOfficeOfExit() {
		 return intendedOfficeOfExit;
	 }
	 public void setIntendedOfficeOfExit(String argument) {
		 intendedOfficeOfExit = argument;
	 }
	 
     public Party getRepresented() {
		if (representedTIN != null) {
			if (represented == null) {
				represented = new Party();
			}
			represented.setPartyTIN(representedTIN);
		}			
		return represented;
	}	
	public void setRepresented(Party argument) {
		this.represented = argument;
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
		 warehouseKeeper = argument;
	 }
	
	 public Party getDocumentsReceiver() {
		if (documentsReceiverTIN != null) {
			if (documentsReceiver == null) {
				documentsReceiver = new Party();
			}
			documentsReceiver.setPartyTIN(documentsReceiverTIN);
		}				 
		 return documentsReceiver;
	 }
	 public void setDocumentsReceiver(Party argument) {
		 documentsReceiver = argument;
	 }
     public WareHouse getWarehouse() {
		 return warehouse;
	 }
	 public void setWarehouse(WareHouse argument) {
		 warehouse = argument;
	 }	 
     public Invoice getInvoice() {
		 return invoice;
	 }
	 public void setInvoice(Invoice argument) {
		 invoice = argument;
	 }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

	public EFormat getAcceptanceTimeFormat() {
		return acceptanceTimeFormat;
	}

	public void setAcceptanceTimeFormat(EFormat acceptanceTimeFormat) {
		this.acceptanceTimeFormat = acceptanceTimeFormat;
	}	 
	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 
		 if (!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}

	public EFormat getCompletionTimeFormat() {
		return completionTimeFormat;
	}

	public void setCompletionTimeFormat(EFormat completionTimeFormat) {
		this.completionTimeFormat = completionTimeFormat;
	}

	public EFormat getDeclarationTimeFormat() {
		return declarationTimeFormat;
	}

	public void setDeclarationTimeFormat(EFormat declarationTimeFormat) {
		this.declarationTimeFormat = declarationTimeFormat;
	}

	public EFormat getExitTimeFormat() {
		return ExitTimeFormat;
	}

	public void setExitTimeFormat(EFormat exitTimeFormat) {
		this.ExitTimeFormat = exitTimeFormat;
	}

	public EFormat getloadingBeginFormat() {
		return loadingBeginFormat;
	}

	public void setDateOfLoadingBeginFormat(EFormat loadingBeginFormat) {
		this.loadingBeginFormat = loadingBeginFormat;
	}

	public EFormat getloadingEndFormat() {
		return loadingEndFormat;
	}

	public void setloadingEndFormat(EFormat loadingEndFormat) {
		this.loadingEndFormat = loadingEndFormat;
	}

	public EFormat getReceiveTimeFormat() {
		return receiveTimeFormat;
	}

	public void setReceiveTimeFormat(EFormat receiveTimeFormat) {
		this.receiveTimeFormat = receiveTimeFormat;
	}

	public EFormat getReleaseTimeFormat() {
		return releaseTimeFormat;
	}

	public void setReleaseTimeFormat(EFormat releaseTimeFormat) {
		this.releaseTimeFormat = releaseTimeFormat;
	}	
}

