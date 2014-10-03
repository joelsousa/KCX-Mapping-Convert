/*
 * Function    : MsgExpDat.java
 * Titel       :
 * Date        : 17.12.2009
 * Author      : Kewill CSF / krzoska
 * Description : Contains Message Structure with all Fields used in KIDS,
 * 			   : to use for ExportDeclaration Messages of Belgium
 * Parameters  : 
 * -----------
 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description :  
 * 		         
 * Changes 
 * -----------
 * Author      : krzoska 
 * Date        : 
 * Label       : AK20091217
 * Description : LoadingLists 
 * 		         
 * Changes 
 * -----------
 * Author      : krzoska 
 * Date        : 
 * Label       : AK20091223
 * Description : InitialSender, InitialSenderTIN, InitialSenderContactPerson  
                 changed from single tag to complex tag
 *             
 * Author      : EI
 * Date        : 
 * Label       : EI20100208
 * Description : new Members for UK added
 * 
 * Author      : AK
 * Date        : 
 * Label       : AK20100317
 * Description : wrong getKindOfDeclarationt
 * 
 * Author      : krzoska
 * Date        : 22.03.2010
 * Label       : AK20100322
 * Description : declarationTime,injunctionType,placeofUnloading,companyNumberTaxPayer,specificCircumstanceIndicator
 * 
 * Author      : ME
 * Date        : 22.04.2010
 * Label       : ME201004XSDVAL
 * Description : Validated against xsd KIDS scheme and modified to match the scheme
 * 
 * Author      : CK
 * Date        : 22.11.2012
 * Label       : CK20121122
 * Description : Added DeclarationNumber
 */
 

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Cap;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TypeOfDeclaration;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgExpDat<br>
 * Erstellt		: 17.12.2009<br>
 * Beschreibung : Contains Message Structure with all Fields used in KIDS,
 *                for Version 1.1 with new Fields for Belgium. 
 * 
 * @author krzoska
 * @version 1.1.00
 */
public class MsgExpDat extends KCXMessage {
	//EI20110503: Reihenfolge wie in xsd-definition:
	private String msgName = "ExportDeclaration";
	private String areaCode;
	private String typeOfPermit; 
	//EI20090415:private EdecHeader edecHeader; 			//for CH only	
	private String kindOfDeclaration;                   
	private String statusCode;                          //AK20110418 CH
	private String typeOfPermitCH;						//for CH only
	private String nctsType;							//for CH only	
	private String ucrNumber;
	private String correctionReason;                    //EI20110502 
	private String finalCode;                           //ME201004XSDVAL
	private String declarationTime;
	private String dispatchCountry; 
	private String destinationCountry;
	private String situationCode;  
    private String paymentType;    
    private String transportInContainer;
    private String netMass;
    private String grossMass;	//n(11,3)    
    private String uCROtherSystem;
    private String annotation;
    private String shipmentNumber;
    private String referenceNumber;
    private String declarationNumber;	// CK20121122
    private String orderNumber;
    private String totalNumberPositions; 
    private String totalNumberOfPackages;  		
    private String authorizationNumber;   
    private String previousProcedure;  
    private String receiverCustomerNumber;  //für UIDS noch nicht definiert
    private String declarantIsConsignor;
    private String typeOfRepresentation;    //UK EI20100120
    private String correctionCode;
    private String bunchNumber;	
	private String language;	
	private String transferToTransitSystem;
	private ContactPerson contact;           //EI090608: private String clerk;  
	private String validity;                 //ME201004XSDVAL
	private WareHouse wareHouse;             //ME201004XSDVAL
	private TransportMeans meansOfTransport;
	private TransportMeans trmInland;
	private TransportMeans trmDeparture;
	private TransportMeans trmBorder;
	private PlaceOfLoading placeOfLoading;
	private String placeofUnloading;        //not in xsd
	//EI20090415: private CustomsOffices customsOffices;	
	private String customsOfficeExport;		    	
	private String customsOfficeForCompletion;	
	private String intendedOfficeOfExit;	
	//EI20120516: private String supervisingCustomsOffice;	//UK EI20100120
	//private Address supervisingCustomsOffice;               //EI20120516
	private TIN supervisingCustomsOfficeTIN;
	private Party supervisingCustomsOffice;               //EI20120620	
	//private String reason;			            //UK EI20100120	
	private String placeOfDeclaration;
	private Business business;
	private Route transportationRoute;              ////==trRoute umbenannt
    //EI20090415: private List <Route>routeList;    //eine Route mit CountryList	
    private Seal  seal;    
    private ExportRefundHeader exportRefundHeader;
    private HeaderExtensions headerExtensions;       //nur bei UIDS
    private LoadingTime loadingTime;
	
    private Party forwarder;
    private TIN   forwarderTIN;
    private TIN messageReceiverTIN;    //ME201004XSDVAL	
	private Party consignor;  
    private TIN consignorTIN;
    private ContactPerson consignorContactPerson;      
	private Party consignorSecurity;      //AK20110418
	private TIN consignorSecurityTIN;     //AK20110418
	private Party declarant; 
    private TIN declarantTIN;
    private ContactPerson declarantContactPerson;
    private String declarantNumber;      // CH	
	private Party agent; 
    private TIN agentTIN; 
    private ContactPerson agentContactPerson; 
    private Party subcontractor; 
    private TIN subcontractorTIN;
    private ContactPerson subcontractorContactPerson;
    private Party consignee; 
    private TIN consigneeTIN;
    private ContactPerson consigneeContactPerson;
    private Party consigneeSecurity;         //AK20110418
	private TIN consigneeSecurityTIN;        //AK20110418
	private TIN customsDocumentsReceiverTIN;
	private Party customsDocumentsReceiver;
	private ContactPerson customsDocumentsReceiverContactPerson;
	private Party represented;         //ME201004XSDVAL
	private TIN representedTIN;        //ME201004XSDVAL
	private Party warehousekeeper; 
    private TIN warehousekeeperTIN;
    private ContactPerson warehousekeeperContactPerson;
    private Party beneficiary; 
    private TIN beneficiaryTIN;
    private ContactPerson beneficiaryContactPerson;
    private TIN initialSenderTIN;
	private Party initialSender;
	private ContactPerson initialSenderContactPerson;
	private Party carrier;	
	private TIN   carrierTIN;
	
	private String descriptionLanguage;
	private String accountNumber;
	private String repertoriumNumber;
	private String controlResultCode;  
    private IncoTerms incoTerms;
    private Document document;
    private List <Document>documentList;   
    private Container container;
	//EI20120605: private List <Container>containerList;     <== in xsd not as a list defined: 
    //                                                           one container with list of ContainerNumber
	private PreviousDocument previousDocument;
	private List <PreviousDocument>previousDocumentList; 
	private List <Text>addInfoStatementList;    //UK EI20100120
	private String loadingLists;
	private String securityIndicator;           //AK20110418
	private String injunctionType;
	private String companyNumberTaxPayer;	
	private SpecialMention specialMention;                    //AK20110415
	private List <SpecialMention> specialMentionList = null;  //AK20110415
	
	private MsgExpDatPos goodsItem;
	private List <MsgExpDatPos>goodsItemList;
	
	private Invoice invoice;        //UK EI20100120
	private Cap cap; 				//UK EI20100120
	private String temporaryReason;             //AK20110418
		
	
	private String specificCircumstanceIndicator;
	private String advanceNoticeTime;                 //EI20090422 used only in Pre-Notification
	private String departureDate;                     //EI20120123  for UK-eCustoms
	private String acceptanceDateTime;                //EI20120123  for UK-eCustoms

    //-------------	
	//CK20100721 needed for NL, filled with content only if referenced ExportDeclaration
	// has a DeclarationNumber in the DB table DeclNums
	// CK20121122
	// added into Schema and changed to STRING-Field
	// private int declarationNumber;
	
	
	public MsgExpDat() {
			super();
	}
	
	public MsgExpDat(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	 
	private enum EDeclaration {
		//KIDS-Name							//UIDS-Name
        AreaCode,                           TypeOfDeclaration,      //TypeOfDeclaration.RegionCode,
		TypeOfPermit,                                               //TypeOfDeclaration.ProcedureCode,
        KindOfDeclaration,                  EdecHeader,             //CH: EdecHeader.TypeOfDeclaration,
		TypeOfPermitCH,                                             //CH: EdecHeader.StatusCode
		NCTSType,                                                   //CH: same		
		UCRNumber,							DocumentReferenceNumber,
		FinalCode, ////ME201004XSDVAL Neu in V11 NL		
		DeclarationTime,		
		DateOfDeparture, 
		AcceptanceTime,
		DispatchCountry,					CountryOfDispatch,
		DestinationCountry,					//same 
		SituationCode,						SpecCircumstance,  
		PaymentType,						TransportPaymentMethod,
		TransportInContainer,				ContainerIndicator,
		NetMass,
		GrossMass,							//same		
		UCROtherSystem,						ExternalRegistrationNumber,		
		Annotation,							Remark,		
		ShipmentNumber, 					CommercialReferenceNumber,
		ReferenceNumber,					//same 
		DeclarationNumber,					// same	CK20121122
		OrderNumber,						LocalReferenceNumber,
		TotalNumberPositions,			    ItemsQuantity,
		TotalNumberOfPackages,
		AuthorizationNumber,				AuthorisationID,
		PreviousProcedure,  				Procedure, // Procedure.Previous
		ReceiverCustomerNumber,			    //TODO noch kein UIDS-Tag
		DeclarantIsConsignor,				ParticipantsCombination,
		TypeOfRepresentation,
		CorrectionCode,                                             //CH: EdecHeader.CorrectionCode		
		BunchNumber,                        CollectionNumber,       //CH: EdecHeader.CollectionNumber
		Language,							//same              	//CH
		TransferToTransitSystem,
		ContactPerson,				        Contact, //Contact.Identity
		Validity, //ME201004XSDVAL Neu in V11 NL
		Warehouse, //ME201004XSDVAL Neu in V11 NL
		MeansOfTransport, //ME201004XSDVAL
		MeansOfTransportInland,  			//MeansOfTransport, 
		MeansOfTransportDeparture,    		//MeansOfTransport 
		MeansOfTransportBorder,				//MeansOfTransport 
		PlaceOfLoading,						//same
		CustomsOfficeExport,				CustomsOffices,
		CustomsOfficeForCompletion,			
		IntendedOfficeOfExit,				
		SupervisingCustomsOffice,
		SupervisingCustomsOfficeTIN,
		Reason,   CorrectionReason, 
		PlaceOfDeclaration,					IssuePlace,
		Business,							Transaction,
		TransportationRoute,				Itinerary,
		Seal,								Seals,
											HeaderExtensions,
		ExportRefundHeader,					ExportRestitutionHeader,
		LoadingTime,						ShipmentPeriod,
		Forwarder,							Shipper,     			//CH
		MessageReceiverTIN,
		ConsignorTIN,				
		Consignor,							Exporter, //EI: Address,
		ConsignorContactPerson,	
		DeclarantTIN,
		Declarant,                          //same
		DeclarantContactPerson,				
		AgentTIN,
		Agent,								DeclarantRepresentative,
		AgentContactPerson,
		SubcontractorTIN,
		Subcontractor,						//same
		SubcontractorContactPerson,			
		ConsigneeTIN,
		Consignee, 							//same 
		ConsigneeContactPerson, 
		CustomsDocumentsReceiverTIN,
		CustomsDocumentsReceiver,
		CustomsDocumentsReceiverContactPerson,
		RepresentedTIN,
		Represented,
		WarehouseKeeperTIN,
		WarehouseKeeper,
		WarehouseKeeperContactPerson,
		BeneficiaryTIN,
		Beneficiary,
		BeneficiaryContactPerson,
		InitialSenderTIN,
		InitialSender,
		InitialSenderContactPerson,
		DescriptionLanguage,
		AccountNumber,
		RepertoriumNumber,
		ControlResultCode,
		IncoTerms,                          Incoterms,
		Document,							// from GoodsItems !!!???
		Container,                          ContainerRegistration,    //CH; nur bei KIDS im Kopf
		Containers,							// Übergangsweise für Heinz akzeptieren wir das falsche (mit "s")
		PreviousDocument,					PrevRegNr,				  //CH
		AddInfoStatement,
		LoadingLists,						LoadingList,
		Goodsitem,							GoodsItem,
		Invoice,
		CAP,
		////---//ME201004XSDVAL---
		//These doesnt show up in xsd scheme for export declaration for what are they for?
		InjunctionType,
		PlaceofUnloading,
		CompanyNumberTaxPayer,
		SpecificCircumstanceIndicator,
		//AK20090512:
		//ShipmentNumber,					ExternalRegistrationNumber,
		                                    //CH: EdecHeader.NCTSIdentifier
		Clerk,	
		AdvanceNoticeTime,					DateOfPreAdvice,
		////---//ME201004XSDVAL---
		////---//AK20110415  for Switzerland
        //CorrectionReason,					//same
        ConsigneeSecurityTIN,
        ConsigneeSecurity,					//same
        DeclarantNumber,					//same
        SecurityIndicator,					//same
        ConsignorSecurityTIN,
        ConsignorSecurity,					ExporterSecurity,
        Carrier,							//same
        CarrierTIN,
        ForwarderTIN,        
		SpecialMention,
		TemporaryReason,  			//CH:EdecHeader.TemporaryReason
		StatusCode;       
		////---//AK20110415
		
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EDeclaration) tag) {
					
				case MeansOfTransportInland:
					trmInland = new TransportMeans(getScanner());  	
					trmInland.parse(tag.name());
					break;
				
				case MeansOfTransportDeparture:    
					trmDeparture = new TransportMeans(getScanner());  
					trmDeparture.parse(tag.name());
					break;	
					
				case MeansOfTransportBorder:	
					trmBorder = new TransportMeans(getScanner());  
					trmBorder.parse(tag.name());
					break;	
					
				case MeansOfTransport:
					if (attr != null) {
					String tt = attr.getValue("TransportType");
					if (!Utils.isStringEmpty(tt)) {
						if (tt.equals("Inland")) {
							trmInland = new TransportMeans(getScanner());
							trmInland.parse(tag.name());
						} else if (tt.equals("Departure")) {
							trmDeparture = new TransportMeans(getScanner());
							trmDeparture.parse(tag.name());
						} else if (tt.equals("Border")) {
							trmBorder = new TransportMeans(getScanner());
							trmBorder.parse(tag.name());
						}
					}
					//ME201004XSDVAL
					} else {
						meansOfTransport = new TransportMeans(getScanner());
						meansOfTransport.parse(tag.name());
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
					exportRefundHeader = new ExportRefundHeader(getScanner(), "K");
					exportRefundHeader.parse(tag.name());
					break;							
				case ExportRestitutionHeader:
					exportRefundHeader = new ExportRefundHeader(getScanner(), "U");
					exportRefundHeader.parse(tag.name());
					break;						
				
				case LoadingTime:
				case ShipmentPeriod:
					loadingTime = new LoadingTime(getScanner());
					loadingTime.parse(tag.name());
					break;	
					
				case Forwarder:
				case Shipper:
					forwarder = new Party(getScanner());
					forwarder.parse(tag.name());
					break;	
				//AK20110419	
				case ForwarderTIN:
					forwarderTIN = new TIN(getScanner());
					forwarderTIN.parse(tag.name());
					break;
					
				case MessageReceiverTIN:
					messageReceiverTIN = new TIN(getScanner());
					messageReceiverTIN.parse(tag.name());
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
				/* EI
				case Exporter:
				case Address:
					if(exporter==null)
					 exporter = new Party(getScanner());						
					exporter.parse(tag.name());
					/*  EI
					TIN tinExp0=new TIN(getScanner());
					tinExp0.parse(tag.name());
					exporter.setPartyTIN(tinExp0); 						
					break;
					
				case ConsignorTIN:
					if(exporter==null){
						exporter = new Party(getScanner());
					}
					TIN tinExp=new TIN(getScanner());
					tinExp.parse(tag.name());
					exporter.setPartyTIN(tinExp); 
					break;		
										
				case ConsignorContactPerson:
					if(consignor==null){
						consignor = new Party(getScanner());
					}
					ContactPerson conP=new ContactPerson(getScanner());
					conP.parse(tag.name());
					consignor.setContactPerson(new ContactPerson(getScanner()));
					break;	
				*/	
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
				/*	EI
				case DeclarantTIN:
					if(declarant==null){
						declarant = new Party(getScanner());
					}
					TIN tinDec=new TIN(getScanner());
					tinDec.parse(tag.name());
					declarant.setPartyTIN(tinDec); 
					break;
					
				case DeclarantContactPerson:
					if(declarant==null){
						declarant = new Party(getScanner());
					}
					ContactPerson conPDec=new ContactPerson(getScanner());
					conPDec.parse(tag.name());
					declarant.setContactPerson(conPDec);
					break;		
				*/
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
				/*	EI
				case AgentTIN:
					if(agent==null){
						agent = new Party(getScanner());
					}
					TIN tinAge=new TIN(getScanner());
					tinAge.parse(tag.name());
					agent.setPartyTIN(tinAge);
					break;	
				*/	
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
				/* EI	
				case SubcontractorTIN:
					if(subcontractor==null){
						subcontractor = new Party(getScanner());
					}
					TIN tinSub=new TIN(getScanner());
					tinSub.parse(tag.name());
					subcontractor.setPartyTIN(tinSub);
					break;		
				case SubcontractorContactPerson:
					if(subcontractor==null){
						subcontractor = new Party(getScanner());
					}
					ContactPerson conPSub=new ContactPerson(getScanner());
					conPSub.parse(tag.name());
					subcontractor.setContactPerson(conPSub);
					break;
				*/	
				case Consignee:
					consignee = new Party(getScanner());	
					consignee.parse(tag.name());
					break;
					
				case ConsigneeTIN:
					consigneeTIN = new TIN(getScanner());						
					consigneeTIN.parse(tag.name());
					break;						
				case ConsigneeContactPerson:
					consigneeContactPerson = new ContactPerson(getScanner());
					consigneeContactPerson.parse(tag.name());	
					break;
				/*
				case ConsigneeTIN:
					if(consignee==null){
						consignee = new Party(getScanner());
					}
					TIN tinConsignee=new TIN(getScanner());
					tinConsignee.parse(tag.name());
					consignee.setPartyTIN(tinConsignee);						
					break;	
				*/
				case Beneficiary:
					beneficiary = new Party(getScanner());	
					beneficiary.parse(tag.name());
				break;

				case BeneficiaryTIN:
					beneficiaryTIN = new TIN(getScanner());						
					beneficiaryTIN.parse(tag.name());
				break;						

				case BeneficiaryContactPerson:
					beneficiaryContactPerson = new ContactPerson(getScanner());
					beneficiaryContactPerson.parse(tag.name());					
					break;						

				case WarehouseKeeper:
					warehousekeeper = new Party(getScanner());	
					warehousekeeper.parse(tag.name());
				break;

				case WarehouseKeeperTIN:
					warehousekeeperTIN = new TIN(getScanner());
					warehousekeeperTIN.parse(tag.name());
				break;						

				case WarehouseKeeperContactPerson:
					warehousekeeperContactPerson = new ContactPerson(getScanner());
					warehousekeeperContactPerson.parse(tag.name());					
					break;						
					
				case IncoTerms:
				case Incoterms:
					incoTerms = new IncoTerms(getScanner());
					incoTerms.parse(tag.name());
					break;	
				
				case HeaderExtensions:
					headerExtensions = new HeaderExtensions(getScanner());
					headerExtensions.parse(tag.name());
					break;		
				
				case Document:
					document = new Document(getScanner(), "K");
					document.parse(tag.name());						
					addDocumentList(document);
					break;	
				case Containers:
				case Container:   //ist nur bei KIDS im Kopf
					container = new  Container(getScanner());
					container.parse(tag.name());						
					break;
				
				case PreviousDocument:
					previousDocument = new  PreviousDocument(getScanner());
					previousDocument.parse(tag.name());						
					addPreviousDocumentList(previousDocument);
					break;	

					
				case InitialSender:
					initialSender = new Party(getScanner());	
					initialSender.parse(tag.name());
					break;					

				case InitialSenderTIN:
					initialSenderTIN = new TIN(getScanner());
					initialSenderTIN.parse(tag.name());
					break;						

				case InitialSenderContactPerson:
					initialSenderContactPerson = new ContactPerson(getScanner());
					initialSenderContactPerson.parse(tag.name());					
					break;						
					
				case CustomsDocumentsReceiver:
					customsDocumentsReceiver = new Party(getScanner());	
					customsDocumentsReceiver.parse(tag.name());
					break;					

				case CustomsDocumentsReceiverTIN:
					customsDocumentsReceiverTIN = new TIN(getScanner());
					customsDocumentsReceiverTIN.parse(tag.name());
					break;						

				case CustomsDocumentsReceiverContactPerson:
					customsDocumentsReceiverContactPerson = new ContactPerson(getScanner());
					customsDocumentsReceiverContactPerson.parse(tag.name());
					break;						
				case Represented:
					represented = new Party(getScanner());
					represented.parse(tag.name());
					break;
				
				case RepresentedTIN:
					representedTIN = new TIN(getScanner());
					representedTIN.parse(tag.name());
					break;
				
				case GoodsItem:
				case Goodsitem:
					goodsItem = new  MsgExpDatPos(getScanner(), msgName);
					goodsItem.parse(tag.name());

					if (goodsItemList == null) {
                        goodsItemList = new Vector<MsgExpDatPos>();
					}
					
					addGoodsItemList(goodsItem);
					break;
					
				case EdecHeader:
					EdecHeader edecHeader = new EdecHeader(getScanner());
					edecHeader.parse(tag.name());						
					setFromEdecHeader(edecHeader); 
					break;	
					
				case TypeOfDeclaration:
					TypeOfDeclaration typeOfDeclaration = new TypeOfDeclaration(getScanner());
					typeOfDeclaration.parse(tag.name());
					areaCode = typeOfDeclaration.getRegionCode();
					typeOfPermit = typeOfDeclaration.getProcedureCode();
					break;	
				
				case CustomsOffices:
					CustomsOffices customsOffices = new CustomsOffices(getScanner());
					customsOffices.parse(tag.name());
					this.customsOfficeExport = customsOffices.getOfficeOfExport();
                    this.customsOfficeForCompletion = customsOffices.getOfficeOfAdditionalDeclarationExport();
					this.intendedOfficeOfExit = customsOffices.getOfficeOfExit();
					break;	
					
				case Procedure:
					ApprovedTreatment procedure = new ApprovedTreatment(getScanner());
					procedure.parse(tag.name());	
					this.previousProcedure = procedure.getPrevious();
					break;	
					
				case Contact:
					//ContactPerson contactPerson = new ContactPerson(getScanner());
					//contactPerson.parse(tag.name());
					//this.clerk = contactPerson.getClerk();
					//break;
				case ContactPerson:
					String clerk = "";
					if (contact != null) {
						clerk = contact.getClerk(); //EI090608 wohin damit???
					}
					contact = new ContactPerson(getScanner());
					contact.parse(tag.name());
					//this.clerk = contactPerson.getClerk();
					break;	
				
				case Warehouse:
					wareHouse = new WareHouse(getScanner());
					wareHouse.parse(tag.name());
					break;
					
				case Invoice:
					invoice = new  Invoice(getScanner());
					invoice.parse(tag.name());						
					break;
					
				case CAP:
					cap = new  Cap(getScanner());
					cap.parse(tag.name());						
					break;
				
				case AddInfoStatement:
					Text addInfoStatement = new Text(getScanner());
					addInfoStatement.parse(tag.name());	
					if (addInfoStatementList == null) {
						addInfoStatementList = new Vector<Text>();
					}					
					addInfoStatementList.add(addInfoStatement);
					break;

				case CarrierTIN:
					carrierTIN = new TIN(getScanner());
					carrierTIN .parse(tag.name());
					break;						

				case Carrier:
					carrier = new Party(getScanner());
					carrier.parse(tag.name());
					break;						
					
				case SpecialMention:
					specialMention = new SpecialMention(getScanner());
					specialMention.parse(tag.name());						
					addSpecialMentionList(specialMention);
					break;	
					
				case ConsignorSecurity:
				case ExporterSecurity:
					consignorSecurity = new Party(getScanner());
					consignorSecurity.parse(tag.name());
					break;						

				case ConsignorSecurityTIN:
					consignorSecurityTIN = new TIN(getScanner());
					consignorSecurityTIN.parse(tag.name());
					break;						

				case ConsigneeSecurity:
					consigneeSecurity = new Party(getScanner());
					consigneeSecurity.parse(tag.name());
					break;						
					
				case ConsigneeSecurityTIN:
					consigneeSecurityTIN = new TIN(getScanner());
					consigneeSecurityTIN.parse(tag.name());
					break;	
				
				case SupervisingCustomsOfficeTIN:
					supervisingCustomsOfficeTIN = new TIN(getScanner());
					supervisingCustomsOfficeTIN.parse(tag.name());
					break;
				case SupervisingCustomsOffice:                              
					//EI20120620: supervisingCustomsOffice = new Address(getScanner());					
					supervisingCustomsOffice = new Party(getScanner());   //EI20120620
					supervisingCustomsOffice.parse(tag.name());										
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
				
				case KindOfDeclaration:
					setKindOfDeclaration(value);						
					break;
					
				case TypeOfPermitCH:						
					setTypeOfPermitCH(value);
					break;	
				case CorrectionCode:						
					setCorrectionCode(value);
					break;	
				case BunchNumber:
				//AK20110415
				case CollectionNumber:
					setBunchNumber(value);
					break;
				case TransferToTransitSystem:
					setTransferToTransitSystem(value);
					break;
				
				case NCTSType:
					setNCTSType(value);
					break;	
					
				case UCRNumber:
				case DocumentReferenceNumber:
					setUCRNumber(value);
					break;
					
				case DispatchCountry:
				case CountryOfDispatch:
					setDispatchCountry(value);
					break;
					
				case DestinationCountry:
					setDestinationCountry(value);
					break;
			
				case SituationCode:
				case SpecCircumstance:	
					setSituationCode(value);
					break;	
					
				case PaymentType:
				case TransportPaymentMethod:
					setPaymentType(value);
					break;
					
				case DescriptionLanguage:
					setDescriptionLanguage(value);
					break;
					
				case TransportInContainer:
				case ContainerIndicator:
					setTransportInContainer(value);
					break;	
					
				case GrossMass:
					setGrossMass(value);
					break;

				case NetMass:
					setNetMass(value);
					break;
					
				case PlaceOfDeclaration:
				case IssuePlace:
					setPlaceOfDeclaration(value);
					break;

				case AccountNumber:
					setAccountNumber(value);
					break;
					
				case RepertoriumNumber:
					setRepertoriumNumber(value);
					break;

				case ControlResultCode:					
					setControlResultCode(value);
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
					
				// CK20121122
				case DeclarationNumber:
					setDeclarationNumber(value);
					break;
					
				case OrderNumber:
				case LocalReferenceNumber:
					setOrderNumber(value);
					break;
					
				case TotalNumberPositions:
				case ItemsQuantity:
					setTotalNumberPositions(value);
					break;
				
				case ReceiverCustomerNumber:
					setReceiverCustomerNumber(value);
					break;
				case FinalCode:
					setFinalCode(value);
					break;
				//AK20100322
				case DeclarationTime:
					setDeclarationTime(value);
					break;	

				case InjunctionType:
					setInjunctionType(value);
					break;	
					
				case PlaceofUnloading:
					setPlaceofUnloading(value);
					break;	

				case CompanyNumberTaxPayer:
					setCompanyNumberTaxPayer(value);
					break;	

				case SpecificCircumstanceIndicator:
					setSpecificCircumstanceIndicator(value);
					break;	
					//AK20100322 end
				
				case AuthorizationNumber:
				case AuthorisationID:
					setAuthorizationNumber(value);
					break;	
				
				case PreviousProcedure:
					setPreviousProcedure(value);
					break;
					
				case DeclarantIsConsignor:
				case ParticipantsCombination:	
					setDeclarantIsConsignor(value);
					break;	
					
				case Language:
					setLanguage(value);
					break;
				case Validity:
					setValidity(value);
					break;
				case Clerk:
					//setClerk(value);
					if (contact == null) {					
						contact = new ContactPerson();
					    contact.setClerk(value);
					}
					break;
				
				case CustomsOfficeExport:						
					setCustomsOfficeExport(value);
					break;
				case CustomsOfficeForCompletion:						
					setCustomsOfficeForCompletion(value);
					break;
				case LoadingList:
					//AK20091217	
				case LoadingLists:						
					setLoadingLists(value);
					break;

				case IntendedOfficeOfExit:						
					setIntendedOfficeOfExit(value);
					break;		
					
				case DateOfPreAdvice:     //EI20090422
				case AdvanceNoticeTime:
					setAdvanceNoticeTime(value);
					break;	
					
				case TypeOfRepresentation:
					setTypeOfRepresentation(value);						
					break;
				
				case TotalNumberOfPackages:				
					setTotalNumberOfPackages(value);
					break;							
				
				case SupervisingCustomsOffice:		
					//EI20120516: setSupervisingCustomsOffice(value); <== it could also be send as a string
					if (supervisingCustomsOffice == null) {
						supervisingCustomsOffice = new Party();						
					}
					Address adr = new Address();
					adr.setName(value);						
					supervisingCustomsOffice.setAddress(adr);						
					break;
											
				case Reason:												
				case CorrectionReason:
					setCorrectionReason(value);
					break;
				case DeclarantNumber:
					setDeclarantNumber(value);
					break;		
					
				case SecurityIndicator:
					setSecurityIndicator(value);
					break;		
					
				case StatusCode:
					setStatusCode(value);
					break;
				case TemporaryReason:
					setTemporaryReason(value);
					break;					
				case DateOfDeparture:
					setDateOfDeparture(value);
					break;
				case AcceptanceTime:
					setAcceptanceTime(value);
					break;
				default: break;
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
	
	//-----------------------------------------------------------
	public void setAreaCode(String argument) {
		this.areaCode = argument;
	}
	public String getAreaCode() {
		return areaCode;
	}
	
	public void setTypeOfPermit(String argument) {
		this.typeOfPermit = argument;
	}
	public String getTypeOfPermit() {
		return typeOfPermit;
	}
	
	public void setKindOfDeclaration(String argument) {
		this.kindOfDeclaration = argument;
	}
	
	//AK20100317
	public String getKindOfDeclaration() {
		return kindOfDeclaration;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getUCRNumber() {
		return ucrNumber;
	}
	public void setUCRNumber(String argument) {
		this.ucrNumber = argument;
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
	public void setOrderNumber(String argument) {
		this.orderNumber = argument;
	}

	public String getTotalNumberPositions() {
		return totalNumberPositions;
	}
	public void setTotalNumberPositions(String argument) {
		this.totalNumberPositions = argument;
	}
	
	public String getAuthorizationNumber() {
		return authorizationNumber;
	}
	public void setAuthorizationNumber(String argument) {
		this.authorizationNumber = argument;
	}

	public String getDeclarantIsConsignor() {
		return declarantIsConsignor;
	}
	public void setDeclarantIsConsignor(String argument) {
		this.declarantIsConsignor = argument;
	}
	
	public TransportMeans getTransportMeansInland() { 
		return trmInland;
	}
	public void setTransportMeansInland(TransportMeans argument) {
		this.trmInland = argument;
	}
	
	public TransportMeans getTransportMeansDeparture() { 
		return trmDeparture;
	}
	public void setTransportMeansDeparture(TransportMeans argument) {
		this.trmDeparture = argument;
	}
	
	public TransportMeans getTransportMeansBorder() { 
		return trmBorder;
	}
	public void setTransportMeansBorder(TransportMeans argument) {
		this.trmBorder = argument;
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
	
    public Route getTransportationRoute() { 
    	return transportationRoute;
    }
	public void setTransportationRoute(Route argument) {
		this.transportationRoute = argument;
	}	
		    
	public void setSeal(Seal argument) {
		this.seal = argument;
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
	/*
	public Seal getSeal() {
    	return seal;
    }
	*/
	
	public ExportRefundHeader getExportRefundHeader() {
    	return exportRefundHeader;
	}
	public void setExportRefundHeader(ExportRefundHeader argument) {
		this.exportRefundHeader = argument;
	}
	
    public LoadingTime getLoadingTime() {
    	return loadingTime;
    }
	public void setLoadingTime(LoadingTime argument) {
		this.loadingTime = argument;
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
	
    public MsgExpDatPos getMsgExpRelPos() {
    	return goodsItem;
    }
	public void setMsgExpRelPos(MsgExpDatPos argument) {
		this.goodsItem = argument;
	}
	
    public List <MsgExpDatPos>getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(MsgExpDatPos argument) {
    	if (this.goodsItemList == null) {
    		this.goodsItemList = new Vector<MsgExpDatPos>();
    	}
    	this.goodsItemList.add(argument);
    }
    public void setGoodsItemList(List <MsgExpDatPos> argument) {
    	this.goodsItemList = argument;  	
    }

	public MsgExpDatPos getGoodsItem() {
    	return goodsItem;
    }  
    public void setGoodsItemList(MsgExpDatPos argument) {
    	this.goodsItem = argument;  	
    }
		
	public String getTypeOfPermitCH() {
		return typeOfPermitCH;	
	}
	public void setTypeOfPermitCH(String typeOfPermitCH) {
		this.typeOfPermitCH = Utils.checkNull(typeOfPermitCH);
	}

	public String getCorrectionCode() {
		return correctionCode;	
	}
	public void setCorrectionCode(String correctionCode) {
		this.correctionCode = Utils.checkNull(correctionCode);
	}
	
	public String getBunchNumber() {
		return bunchNumber;	
	}
	public void setBunchNumber(String bunchNumber) {
		this.bunchNumber = Utils.checkNull(bunchNumber);
	}

	public String getNCTSType() {
		return nctsType;	
	}
	public void setNCTSType(String type) {
		nctsType = Utils.checkNull(type);
	}

	public String getLanguage() {
		return language;	
	}
	public void setLanguage(String language) {
		this.language = Utils.checkNull(language);
	}

	public String getTransferToTransitSystem() {
		return transferToTransitSystem;	
	}
	public void setTransferToTransitSystem(String transferToTransitSystem) {
		this.transferToTransitSystem = Utils.checkNull(transferToTransitSystem);
	}

	public Container getContainer() {
		return container;	
	}
	public void setContainer(Container container) {
		this.container = container;
	}
    /*
	public List<Container> getContainerList() {
		return containerList;	
	}
	public void setContainerList(List<Container> containerList) {
		this.containerList = containerList;
	}	
	public void addContainerList(Container argument) {
		this.containerList.add(argument);
	}
    */
	public PreviousDocument getPreviousDocument() {
		return previousDocument;	
	}
	public void setPreviousDocument(PreviousDocument previousDocument) {
		this.previousDocument = previousDocument;
	}
	public List<PreviousDocument> getPreviousDocumentList() {
		return previousDocumentList;	
	}
	
	public void setPreviousDocumentList(List<PreviousDocument> previousDocumentList) {
		this.previousDocumentList = previousDocumentList;
	}
	public void addPreviousDocumentList(PreviousDocument previousDocument) {
		if (previousDocumentList == null) {
			previousDocumentList = new Vector<PreviousDocument>();
		}
		this.previousDocumentList.add(previousDocument);
	}

	public List<Document> getDocumentList() {
		return documentList;	
	}	
	public void addDocumentList(Document argument) {  		
		if (documentList == null) {
			documentList = new Vector<Document>();
		}
		this.documentList.add(argument);
	}	

	public HeaderExtensions getHeaderExtensions() {
		return headerExtensions;
	}
	public void setHeaderExtensions(HeaderExtensions headerExtensions) {
		this.headerExtensions = headerExtensions;
	}
        /*
		public Party getExporter() {
			return exporter;
		}
		public void setExporter(Party exporter) {
			this.exporter = exporter;
		}
		*/
	
	public void setForwarder(Party argument) {
		this.forwarder = argument;				    	
    }
	public Party getForwarder() {		
		if (forwarderTIN != null) {        //EI20120912
			if (forwarder == null) {
				forwarder = new Party();
			}
			forwarder.setPartyTIN(forwarderTIN);
		}			
		return forwarder;
	}
	
		public void setConsignor(Party argument) {
			this.consignor = argument;				    	
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
		/*
		 public Party getConsignor() {			
			return consignor;
		}
		 */
		public void setDeclarant(Party argument) {
			this.declarant = argument;
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
		/*
	    public Party getDeclarant() {
	    	return declarant;
	    }
		*/
			   
		public void setAgent(Party argument) {
			this.agent = argument;
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
		/*
		public Party getAgent() {
	    	return agent;
	    }
		*/
		public void setSubcontractor(Party argument) {
			this.subcontractor = argument;
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
		/*
	    public Party getSubcontractor() {
	    	return subcontractor;
	    }
		*/			

		public void setInitialSender(Party argument) {
			this.initialSender = argument;				    	
	    }
		public Party getInitialSender() {
			if (initialSenderTIN != null) {
				if (initialSender == null) {
					initialSender = new Party();
				}
				initialSender.setPartyTIN(initialSenderTIN);
			}
			if (initialSenderContactPerson != null) {
				if (initialSender == null) {
					initialSender = new Party();
				}
				initialSender.setContactPerson(initialSenderContactPerson);
			}			
			return initialSender;
		}
		
		public void setConsignee(Party argument) {
			this.consignee = argument;
		}		
		public Party getConsignee() {
			if (consigneeTIN != null) {
				if (consignee == null) {
					consignee = new Party();
				}
				consignee.setPartyTIN(consigneeTIN);
			}
			
			if (consigneeContactPerson != null) {
				if (consignee == null) {
					consignee = new Party();
				}
				consignee.setContactPerson(consigneeContactPerson);
			}	
			return consignee;
		}
		/*
	    public Party getConsignee() {
	    	return consignee;
	    }
	    */
		
		public void setBeneficiary(Party argument) {
			this.beneficiary = argument;
		}		
		public Party getBeneficiary() {
			if (beneficiaryTIN != null) {
				if (beneficiary == null) {
					beneficiary = new Party();
				}
				beneficiary.setPartyTIN(beneficiaryTIN);
			}					
			if (beneficiaryContactPerson != null) {    //EI20120912
				if (beneficiary == null) {
					beneficiary = new Party();
				}
				beneficiary.setContactPerson(beneficiaryContactPerson);
			}			
			return beneficiary;
		}		

		
	private void setFromEdecHeader(EdecHeader edecHeader) {
		if (edecHeader == null) {
			return;
		}
		List nrList = edecHeader.getPrevRegNrList();
		
		this.kindOfDeclaration = edecHeader.getTypeOfDeclaration();
		this.typeOfPermitCH = edecHeader.getStatuscode();
		this.correctionCode = edecHeader.getCorrectionCode();
		this.bunchNumber = edecHeader.getCollectionNumber();
		this.transferToTransitSystem = edecHeader.getNctsIdentifier();
		
		if (nrList != null) {
			int size = nrList.size();
			String nr;			
			for (int i = 0; i < size; i++) { 
				nr = (String) nrList.get(i);
				PreviousDocument pd = new PreviousDocument();
				pd.setMarks(nr);
				addPreviousDocumentList(pd);
			}
		}
	}
	
	public void setPreviousProcedure(String argument) {
		this.previousProcedure = argument;
	}
	
	public String getPreviousProcedure() {
		return this.previousProcedure;
	}
	/*
	public void setClerk(String argument) {
		this.clerk = argument;
	}
	public String getClerk() {
		return clerk;
	}
	*/
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}
	public ContactPerson getContact() {
		return contact;
	}	
	
	public void setCustomsOfficeExport(String argument) {
		this.customsOfficeExport = argument;
	}
	public String getCustomsOfficeExport() {
		return customsOfficeExport;
	}
						
	public void setCustomsOfficeForCompletion(String argument) {
		this.customsOfficeForCompletion = argument;
	}
	public String getCustomsOfficeForCompletion() {
		return customsOfficeForCompletion;
	}
	
	public void setIntendedOfficeOfExit(String argument) {
		this.intendedOfficeOfExit = argument;
	}
	public String getIntendedOfficeOfExit() {
		return intendedOfficeOfExit;
	}
	
	public String getReceiverCustomerNumber() {
		return receiverCustomerNumber;
	}
	public void setReceiverCustomerNumber(String argument) {
		this.receiverCustomerNumber = argument;
	}	
	
	public String getAdvanceNoticeTime() {
		return this.advanceNoticeTime;
	}
	public void setAdvanceNoticeTime(String argument) {
		this.advanceNoticeTime = argument;
	}	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}

	public String getLoadingLists() {
		return loadingLists;
	
	}

	public void setLoadingLists(String loadingLists) {
		this.loadingLists = Utils.checkNull(loadingLists);
	}

	public String getNetMass() {
		return netMass;
	
	}

	public void setNetMass(String netMass) {
		this.netMass = Utils.checkNull(netMass);
	}

	public String getDescriptionLanguage() {
		return descriptionLanguage;
	
	}

	public void setDescriptionLanguage(String descriptionLanguage) {
		this.descriptionLanguage = Utils.checkNull(descriptionLanguage);
	}

	public String getPlaceOfDeclaration() {
		return placeOfDeclaration;
	
	}

	public void setPlaceOfDeclaration(String placeOfDeclaration) {
		this.placeOfDeclaration = Utils.checkNull(placeOfDeclaration);
	}
	public String getAccountNumber() {
		return accountNumber;
	
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = Utils.checkNull(accountNumber);
	}

	public String getRepertoriumNumber() {
		return repertoriumNumber;
	
	}

	public void setRepertoriumNumber(String repertoriumNumber) {
		this.repertoriumNumber = Utils.checkNull(repertoriumNumber);
	}

	public String getControlResultCode() {
		return controlResultCode;
	
	}

	public void setControlResultCode(String controlResultCode) {
		this.controlResultCode = Utils.checkNull(controlResultCode);
	}

	public TIN getCustomsDocumentsReceiverTIN() {
		return customsDocumentsReceiverTIN;
	}

	public void setCustomsDocumentsReceiverTIN(TIN customsDocumentsReceiverTIN) {
		this.customsDocumentsReceiverTIN = customsDocumentsReceiverTIN;
	}

	public Party getCustomsDocumentsReceiver() {		
		if (customsDocumentsReceiverTIN != null) {                    //EI20120912
			if (customsDocumentsReceiver == null) {
				customsDocumentsReceiver = new Party();
			}
			customsDocumentsReceiver.setPartyTIN(customsDocumentsReceiverTIN);
		}
		if (customsDocumentsReceiverContactPerson != null) {           //EI20120912
			if (customsDocumentsReceiver == null) {
				customsDocumentsReceiver = new Party();
			}
			customsDocumentsReceiver.setContactPerson(customsDocumentsReceiverContactPerson);
		}			
		return customsDocumentsReceiver;
	}

	public void setCustomsDocumentsReceiver(Party customsDocumentsReceiver) {
		this.customsDocumentsReceiver = customsDocumentsReceiver;
	}

	public void setCustomsDocumentsReceiverContactPerson(
			ContactPerson customsDocumentsReceiverContactPerson) {
		this.customsDocumentsReceiverContactPerson = customsDocumentsReceiverContactPerson;
	}

	public Party getWarehousekeeper() {
		if (warehousekeeperTIN != null) {
			if (warehousekeeper == null) {
				warehousekeeper = new Party();
			}
			warehousekeeper.setPartyTIN(warehousekeeperTIN);
		}
		if (warehousekeeperContactPerson != null) {
			if (warehousekeeper == null) {
				warehousekeeper = new Party();
			}
			warehousekeeper.setContactPerson(warehousekeeperContactPerson);
		}				
		return warehousekeeper;
	}

	public void setWarehousekeeper(Party warehousekeeper) {
		this.warehousekeeper = warehousekeeper;
	}

	public TIN getWarehousekeeperTIN() {
		return warehousekeeperTIN;
	}

	public void setWarehousekeeperTIN(TIN warehousekeeperTIN) {
		this.warehousekeeperTIN = warehousekeeperTIN;
	}

	public ContactPerson getWarehousekeeperContactPerson() {
		return warehousekeeperContactPerson;
	}

	public void setWarehousekeeperContactPerson(
			ContactPerson warehousekeeperContactPerson) {
		this.warehousekeeperContactPerson = warehousekeeperContactPerson;
	}

	public ContactPerson getCustomsDocumentsReceiverContactPerson() {
		return customsDocumentsReceiverContactPerson;
	}	
	//EI20200120:
	public void setTypeOfRepresentation(String argument) {
		this.typeOfRepresentation = argument;
	}
	public String getTypeOfRepresentation() {
		return typeOfRepresentation;
	}
	/*
	public void setSupervisingCustomsOffice(String argument) {
		this.supervisingCustomsOffice = argument;
	}
	public String getSupervisingCustomsOffice() {
		return supervisingCustomsOffice; 
	}
	*/
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
	
	public String getTotalNumberOfPackages() {
		return totalNumberOfPackages;
	}	
	public void setTotalNumberOfPackages(String argument) {
		this.totalNumberOfPackages = argument;
	}
  
	public void setInvoice(Invoice argument) {
		this.invoice = argument;
	}	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public void setCap(Cap argument) {
		this.cap = argument;
	}	
	public Cap getCap() {
		return cap;
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

	public String getDeclarationTime() {
		return declarationTime;
	
	}

	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = Utils.checkNull(declarationTime);
	}

	public String getInjunctionType() {
		return injunctionType;	
	}

	public void setInjunctionType(String injunctionType) {
		this.injunctionType = Utils.checkNull(injunctionType);
	}

	public String getPlaceofUnloading() {
		return placeofUnloading;	
	}

	public void setPlaceofUnloading(String placeofUnloading) {
		this.placeofUnloading = Utils.checkNull(placeofUnloading);
	}

	public String getCompanyNumberTaxPayer() {
		return companyNumberTaxPayer;	
	}

	public void setCompanyNumberTaxPayer(String companyNumberTaxPayer) {
		this.companyNumberTaxPayer = Utils.checkNull(companyNumberTaxPayer);
	}

	public String getSpecificCircumstanceIndicator() {
		return specificCircumstanceIndicator;	
	}

	public void setSpecificCircumstanceIndicator(
			String specificCircumstanceIndicator) {
		this.specificCircumstanceIndicator = Utils
				.checkNull(specificCircumstanceIndicator);
	}

	public ContactPerson getAgentContactPerson() {
		return agentContactPerson;	
	}

	public void setAgentContactPerson(ContactPerson agentContactPerson) {
		this.agentContactPerson = agentContactPerson;
	}

	public String getFinalCode() {	
		return finalCode;
	}

	public void setFinalCode(String finalCode) {
		this.finalCode = finalCode;
	}

	public TransportMeans getMeansOfTransport() {
		return meansOfTransport;
	}

	public void setMeansOfTransport(TransportMeans meansOfTransport) {
		this.meansOfTransport = meansOfTransport;
	}

	public TIN getMessageReceiverTIN() {
		return messageReceiverTIN;
	}
	public void setMessageReceiverTIN(TIN messageReceiverTIN) {
		this.messageReceiverTIN = messageReceiverTIN;
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
	public void setRepresented(Party represented) {
		this.represented = represented;
	}

	public TIN getRepresentedTIN() {
		return representedTIN;
	}

	public void setRepresentedTIN(TIN representedTIN) {
		this.representedTIN = representedTIN;
	}
	public void setValidity(String value) {
		this.validity = value;
	}
	public String getValidity() {
		return validity;
	}

	public WareHouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(WareHouse wareHouse) {
		this.wareHouse = wareHouse;
	}
	// CK20121122 return value String instead of int
	public String getDeclarationNumber() {
		return declarationNumber;
	}
	// CK20121122 type String instead of int
	public void setDeclarationNumber(String declarationNumber) {
		this.declarationNumber = declarationNumber;
	}

	public String getDeclarantNumber() {
		return declarantNumber;
	
	}

	public void setDeclarantNumber(String declarantNumber) {
		this.declarantNumber = Utils.checkNull(declarantNumber);
	}

	public SpecialMention getSpecialMention() {
		return specialMention;	
	}	
	public void setSpecialMention(SpecialMention specialMention) {
		this.specialMention = specialMention;
	}

	public void setSpecialMentionList(List<SpecialMention> list) {  //EI20110429
		this.specialMentionList = list;
	}
	public List<SpecialMention> getSpecialMentionList() {  //EI20110429
		return specialMentionList;	
	}
	private void addSpecialMentionList(SpecialMention specialMention) {
		if (this.specialMentionList == null) {
    		this.specialMentionList  = new Vector<SpecialMention>();
    	}
    	this.specialMentionList.add(specialMention);
	}
	
	public String getSecurityIndicator() {
		return securityIndicator;	
	}

	public void setSecurityIndicator(String securityIndicator) {
		this.securityIndicator = Utils.checkNull(securityIndicator);
	}

	//AK20110418
	public TIN getConsignorSecurityTIN() {
		return consignorSecurityTIN;
	}

	public void setConsignorSecurityTIN(TIN consignorSecurityTIN) {
		this.consignorSecurityTIN = consignorSecurityTIN;
	}

	public TIN getConsigneeSecurityTIN() {
		return consigneeSecurityTIN;
	}

	public void setConsigneeSecurityTIN(TIN consigneeSecurityTIN) {
		this.consigneeSecurityTIN = consigneeSecurityTIN;
	}
	
	public TIN getCarrierTIN() {
		return carrierTIN;
	}

	public void setCarrierTIN(TIN carrierTIN) {
		this.carrierTIN = carrierTIN;
	}
	public TIN getForwarderTIN() {
		return forwarderTIN;
	}

	public void setForwarderTIN(TIN forwarderTIN) {
		this.forwarderTIN = forwarderTIN;
	}
	//AK20110418  end

	public String getStatusCode() {
		return statusCode;	
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = Utils.checkNull(statusCode);
	}

	public String getTemporaryReason() {
		return temporaryReason;
	}

	public void setTemporaryReason(String temporaryReason) {
		this.temporaryReason = Utils.checkNull(temporaryReason);
	}

	public Party getConsignorSecurity() {
		if (consignorSecurityTIN != null) {
			if (consignorSecurity == null) {
				consignorSecurity = new Party();
			}
			consignorSecurity.setPartyTIN(consignorSecurityTIN);
		}
		return consignorSecurity;
	}
	public void setConsignorSecurity(Party consignorSecurity) {
		this.consignorSecurity = consignorSecurity;
	}

	public Party getConsigneeSecurity() {		
		if (consigneeSecurityTIN != null) {    //EI20120912
			if (consigneeSecurity == null) {
				consigneeSecurity = new Party();
			}
			consigneeSecurity.setPartyTIN(consigneeSecurityTIN);
		}		
		return consigneeSecurity;	
	}
	public void setConsigneeSecurity(Party consigneeSecurity) {
		this.consigneeSecurity = consigneeSecurity;
	}

	public Party getCarrier() {
		if (carrierTIN != null) {             //EI20120912
			if (carrier == null) {
				carrier = new Party();
			}
			carrier.setPartyTIN(carrierTIN);
		}
		return carrier;
	}
	public void setCarrier(Party carrier) {
		this.carrier = carrier;
	}

	public String getCorrectionReason() {
		return correctionReason;
	}
	public void setCorrectionReason(String argument) {
		this.correctionReason = Utils.checkNull(argument);
	}
		
	public String getDateOfDeparture() {
		return departureDate;
	}
	public void setDateOfDeparture(String date) {
		this.departureDate = Utils.checkNull(date);
	}
	
	public String getAcceptanceTime() {
		return acceptanceDateTime;
	}
	public void setAcceptanceTime(String date) {
		this.acceptanceDateTime = Utils.checkNull(date);
	}
}

