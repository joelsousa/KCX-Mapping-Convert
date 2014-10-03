package com.kewill.kcx.component.mapping.countries.de.aes21.msg;

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
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Cap;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.MeansOfIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.OutwardProcessing;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Product;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Reentry;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TypeOfDeclaration;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module       : Export/aes.<br>
 * Created      : 2.07.2012<br>
 * Description	: Kids Version 2.0.00
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MsgExpDat extends KCXMessage {	
	private String msgName = "ExportDeclaration";
	private String fromFormat = "";              //EI20120913: used for PreviousDocument: "UIDS" or "KIDS"
	private String areaCode;
	private String typeOfPermit; 	
	private String typeOfPermitCH;	
	private String kindOfDeclaration;                   
	private String statusCode;                         					
	private String nctsType;							
	private String ucrNumber;
	private String correctionReason;                    
	private String finalCode;                          
	private String declarationTime;
	private EFormat declarationTimeFormat;
	private String acceptanceDateTime;
	private EFormat acceptanceDateTimeFormat;  
	private String departureDate; 
	private EFormat departureDateFormat;
	private String dispatchCountry; 
	private String destinationCountry;
	private String situationCode;  
    private String paymentType;    
    private String transportInContainer;
    private String netMass;
    private String grossMass;	    
    private String uCROtherSystem;
    private String annotation;
    private String shipmentNumber;
    private String referenceNumber;
    private String orderNumber;
    private String totalNumberPositions; 
    private String totalNumberOfPackages;  		
    private String authorizationNumber;   
    private String previousProcedure;  
    private String receiverCustomerNumber;  
    private String declarantIsConsignor;
    private String typeOfRepresentation;    
    private String correctionCode;
    private String bunchNumber;	
	private String language;	
	private String transferToTransitSystem;
	private ContactPerson contact;             
	private String validity;                 
	private WareHouse wareHouse;            
	private TransportMeans meansOfTransport;
	private TransportMeans trmInland;
	private TransportMeans trmDeparture;
	private TransportMeans trmBorder;
	private PlaceOfLoading placeOfLoading;	
	private String customsOfficeExport;		    	
	private String customsOfficeForCompletion;	
	private String intendedOfficeOfExit;			
	private TIN supervisingCustomsOfficeTIN;
	private Party supervisingCustomsOffice;               
	private String placeOfDeclaration;
	private Business business;
	private Route transportationRoute;               
    private Seal  seal;    
    private ExportRefundHeader exportRefundHeader;
    private HeaderExtensions headerExtensions;       //only UIDS
    private LoadingTime loadingTime;	
    private Party forwarder;
    private TIN   forwarderTIN;
    private TIN messageReceiverTIN;   
	private Party consignor;  
    private TIN consignorTIN;
    private ContactPerson consignorContactPerson;      
	private Party consignorSecurity;      
	private TIN consignorSecurityTIN;     
	private Party declarant; 
    private TIN declarantTIN;
    private ContactPerson declarantContactPerson;
    private String declarantNumber;      	
	private Party agent; 
    private TIN agentTIN; 
    private ContactPerson agentContactPerson; 
    private Party subcontractor; 
    private TIN subcontractorTIN;
    private ContactPerson subcontractorContactPerson;
    private Party consignee; 
    private TIN consigneeTIN;
    private ContactPerson consigneeContactPerson;
    private Party consigneeSecurity;         
	private TIN consigneeSecurityTIN; 
	private TIN customsDocumentsReceiverTIN;
	private Party customsDocumentsReceiver;
	private ContactPerson customsDocumentsReceiverContactPerson;
	private Party represented;       
	private TIN representedTIN;       
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
    private DocumentV20 document;
    private List <DocumentV20>documentList;   
    private Container container;	
	//private List <PreviousDocumentV20>previousDocumentList; 
    private List <PreviousDocumentV21>previousDocumentList; 
	private List <Text>addInfoStatementList;    
	private String loadingLists;
	private String securityIndicator;           
	private String injunctionType;
	private String companyNumberTaxPayer;	
	private SpecialMention specialMention;                   
	private List <SpecialMention> specialMentionList = null;  
//new for V21 begin:
	private String invoiceCurrencyType; 
	private String authorizationTrustedExporter; 
	private String procedure; 
	private String relevantDate; 
	private EFormat relevantDateFormat;
	private String flagOfStatistic; 
	private String realOfficeOfExit; 
	private String addressCombination; 	
	private TIN    finalRecipientTIN;
	private Party  finalRecipient;
	private OutwardProcessing outwardProcessing;
	private List <Reentry> reentryList;
	private List <MeansOfIdentification> meansOfIdentificationList;
	private List <Product> productList;
	private String dateOfExit; 	
	private EFormat dateOfExitFormat;
//new for V21 end
    	
	private List <MsgExpDatPos>goodsItemList;	
	private Invoice invoice;        
	private Cap cap; 				
	private String temporaryReason;             
			
	private String advanceNoticeTime;                 //EI20090422 used only in Pre-Notification
	private EFormat advanceNoticeTimeFormat;
	
	//CK20100721 needed for NL, filled with content only if referenced ExportDeclaration
	// has a DeclarationNumber in the DB table DeclNums
	private int declarationNumber;	
	
	private Traders traders;    //new for BDP:
								// und was ist mit AdditionalInformation ist das Annotation, oder AddInfoStatement?	
	public MsgExpDat() {
			super();
	}	
	public MsgExpDat(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	 
	private enum EDeclaration {
		//KIDS-Name								//UIDS-Name
        AreaCode,                           	TypeOfDeclaration,      //TypeOfDeclaration.RegionCode,
		TypeOfPermit,                                                   //TypeOfDeclaration.ProcedureCode,
		TypeOfPermitCH, //is not in xsd                                 //CH: EdecHeader.StatusCode
        KindOfDeclaration,                  	EdecHeader,             //CH: EdecHeader.TypeOfDeclaration,
		StatusCode,								//--
		NCTSType,       //is not in xsd                                 //CH: same		
		UCRNumber,								DocumentReferenceNumber,
		CorrectionReason, Reason,				//CorrectionReason
		FinalCode, 								//--
		DeclarationTime,						DateOfDeclaration,
		AcceptanceTime,							DateOfAcceptance,
		DateOfDeparture, 						//--	
		DispatchCountry,						CountryOfDispatch,
		DestinationCountry,						//same 
		SituationCode,							SpecCircumstance,  
		PaymentType,							TransportPaymentMethod,
		TransportInContainer,					ContainerIndicator,
		NetMass,								//same
		GrossMass,								//same		
		UCROtherSystem,							ExternalRegistrationNumber,		
		Annotation,								Remark,		
		ShipmentNumber, 						CommercialReferenceNumber,
		ReferenceNumber,						//same 
		OrderNumber,							LocalReferenceNumber,
		TotalNumberPositions,			    	ItemsQuantity,
		TotalNumberOfPackages,					//same
		AuthorizationNumber,					AuthorisationID,
		PreviousProcedure,  					Procedure, // Procedure.Previous
		ReceiverCustomerNumber,			    	//??? CustomsDocumentsReceiver is CT_Trader
		DeclarantIsConsignor,					ParticipantsCombination,
		TypeOfRepresentation,					//EI20120711: warum nicht hier?  ParticipantsCombination, 
		CorrectionCode,                                             	//CH: EdecHeader.CorrectionCode		
		BunchNumber,                        	CollectionNumber,       //CH: EdecHeader.CollectionNumber
		Language,								//same              	//CH
		TransferToTransitSystem,
		Contact, ContactPerson, 				//Contact
		Validity, 								//--
		Warehouse, 								//same  
		MeansOfTransport, 
		MeansOfTransportInland,  				//MeansOfTransport, 
		MeansOfTransportDeparture,    			//MeansOfTransport 
		MeansOfTransportBorder,					//MeansOfTransport 
		PlaceOfLoading,							//same
		CustomsOfficeExport,					CustomsOffices, //OfficeOfExport
		CustomsOfficeForCompletion,			    //CustomsOffices
		IntendedOfficeOfExit,					//CustomsOffices.OfficeOfExit			
		SupervisingCustomsOffice,               //CustomsOffices
		SupervisingCustomsOfficeTIN,		
		PlaceOfDeclaration,						IssuePlace,
		Business,								Transaction,
		TransportationRoute,					Itinerary,
		Seal,									Seals,
												HeaderExtensions,
		ExportRefundHeader,						ExportRestitutionHeader,
		LoadingTime,							ShipmentPeriod,
		ForwarderTIN, Forwarder,				Shipper,     			
		MessageReceiverTIN,												
		Consignor, 								Exporter, 
		ConsignorTIN, ConsignorContactPerson, 
		ConsignorSecurity, 		 				ExporterSecurity,	
		ConsignorSecurityTIN,
		Declarant,  							//same
		DeclarantTIN,
		DeclarantContactPerson,
		DeclarantNumber,						//same					
		Agent, 			 						DeclarantRepresentative,
		AgentTIN, AgentContactPerson, 
		Subcontractor, 						 	//same	
		SubcontractorTIN, SubcontractorContactPerson,
		Consignee, 				 				//same 
		ConsigneeTIN, ConsigneeContactPerson,
		ConsigneeSecurity, 						//same 
		ConsigneeSecurityTIN, 
		CustomsDocumentsReceiver,  				//same 
		CustomsDocumentsReceiverTIN, CustomsDocumentsReceiverContactPerson,
		Represented,							//same 	
		RepresentedTIN, 
		WarehouseKeeper,							//same 
		WarehouseKeeperTIN, 
		//WarehouseKeeperContactPerson,     //is not in xsd
		BeneficiaryTIN, Beneficiary, BeneficiaryContactPerson,			//same			
		InitialSenderTIN, InitialSender, InitialSenderContactPerson, 	//same	
		CarrierTIN, Carrier,							 				//same
											Issuer, Recipient, SubcontractorRepresentative, //EI20120912: wg. Contact muessen in Enum bekannt sein  
		DescriptionLanguage,				//same
		AccountNumber,						//same
		RepertoriumNumber,					//same
		ControlResultCode,					//same
		IncoTerms,                          Incoterms, Incoterm,
		Document,							//--
		Container,                          ContainerRegistration,   		
		PreviousDocument,					//EdecHeader.PrevRegNr,	 in CH also PreviousDocument   		  
		AddInfoStatement,					//same
		LoadingLists,						LoadingList,
		SecurityIndicator,					//same
		InjunctionType,						//same
		CompanyNumberTaxPayer,              //same
		SpecialMention,						//--
		//V21-begin
		InvoiceCurrencyType,				//same
		AuthorizationTrustedExporter,		AuthorisationReliableExporter,
		//Procedure, ==> KIDS:string_Procedure == UIDS:string=TypeOfDeclaration.Procedure;  
		//conflict in Enum therefore commented:  KIDS:Procedure == UIDS:Procedure.Previous
		RelevantDate, 						DateOfRelevance,
		FlagOfStatistic,					FlagOfStatistik, //EI20120921:eigentlich ist in xsd FlagOfStatistic
		RealOfficeOfExit,					//CustomsOffices.OfficeOfActualExit,
		AddressCombination,					//same
		FinalRecipientTIN, FinalRecipient,  FinalUser,
		OutwardProcessing,                  PassiveFinishing,
		Reentry,                            //PassiveFinishing.
		MeansOfIdentification,              //PassiveFinishing.Identity
		Product,							//PassiveFinishing.Product
		DateOfExit,                         //same
											DateOfAmendmentExit,  //todo-iwa was jetzt???
		//V21-end
		GoodsItem,							//same
		Invoice,							//same
		CAP,								//same
		TemporaryReason,  			        //CH:EdecHeader.TemporaryReason
		
		AdvanceNoticeTime,					DateOfPreAdvice,   //only for PreNotification
		
		//EI20120921: wg. Remark, GrossMass, AuthorisationID in UIDS muessen in Enum bekannt sein:
		DocumentInformation, ExportAssuranceCertificate, CustomsTreatment,
		//CustomsOffices:
		Customs_dex, Guarantee,
		
		Traders,       //only for BDP
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
				case ConsigneeContactPerson:
					consigneeContactPerson = new ContactPerson(getScanner());
					consigneeContactPerson.parse(tag.name());	
					break;				
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
				case IncoTerms:
				case Incoterms:
				case Incoterm:
					incoTerms = new IncoTerms(getScanner());
					incoTerms.parse(tag.name());
					break;					
				case HeaderExtensions:
					headerExtensions = new HeaderExtensions(getScanner());
					headerExtensions.parse(tag.name());
					break;						
				case Document:
					DocumentV20 document = new DocumentV20(getScanner(), "K");
					document.parse(tag.name());						
					addDocumentList(document);
					break;					
				case Container: 					
					container = new  Container(getScanner());
					container.parse(tag.name());						
					break;				
				case PreviousDocument:
					//PreviousDocumentV20 previousDocument = new  PreviousDocumentV20(getScanner());
					PreviousDocumentV21 previousDocument = new  PreviousDocumentV21(getScanner());
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
					MsgExpDatPos goodsItem = new  MsgExpDatPos(getScanner(), msgName);
					goodsItem.parse(tag.name());
					addGoodsItemList(goodsItem);
					break;					
				case EdecHeader:
					EdecHeader edecHeader = new EdecHeader(getScanner());
					edecHeader.parse(tag.name());						
					setKidsFromEdecHeader(edecHeader); 
					break;						
				case TypeOfDeclaration:
					TypeOfDeclaration typeOfDeclaration = new TypeOfDeclaration(getScanner());
					typeOfDeclaration.parse(tag.name());
					if (typeOfDeclaration != null) {
						areaCode = typeOfDeclaration.getRegionCode();
						typeOfPermit = typeOfDeclaration.getProcedureCode();
						procedure = typeOfDeclaration.getProcedure();
					}
					break;					
				case CustomsOffices:
					CustomsOffices customsOffices = new CustomsOffices(getScanner());
					customsOffices.parse(tag.name());
					setKidsFromCustomsOffices(customsOffices);					
					break;						
				case Procedure:  //UIDS
					ApprovedTreatment procedure = new ApprovedTreatment(getScanner());
					procedure.parse(tag.name());
					if (procedure != null) {
						this.previousProcedure = procedure.getPrevious();
					}
					break;						
				case Contact:					
				case ContactPerson:		
					if (contact == null) {   //EI20120912: wenn ein UidsParty in ENum nicht bekannt ist
							//dann geht parser rein und liest hier den Contact davon, "nur" Contact steht in Uids.xsd
						    // vorne, nach bekannten Consignee und FinalUser
						//gleichzeitig werden nicht gemappten Partys abgefangen
						contact = new ContactPerson(getScanner());
						contact.parse(tag.name());	
					}
					break;	
				case Issuer: case Recipient: case SubcontractorRepresentative:
				case DocumentInformation: case ExportAssuranceCertificate: 
					case CustomsTreatment: case Guarantee: case Customs_dex:
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
				case FinalRecipientTIN:
					finalRecipientTIN = new TIN(getScanner());
					finalRecipientTIN.parse(tag.name());
					break;
				case FinalRecipient:
				case FinalUser:
					finalRecipient = new Party(getScanner());
					finalRecipient.parse(tag.name());
					break;					
				case PassiveFinishing:
					outwardProcessing = new OutwardProcessing(getScanner());
					outwardProcessing.parse(tag.name());
					this.setReentryList(outwardProcessing.getReentryList());  //EI20120910
					this.setMeansOfIdentificationList(outwardProcessing.getMeansOfIdentificationList());  //EI20120910
					this.setProductList(outwardProcessing.getProductList());  //EI20120910
					break;
				case OutwardProcessing:
					outwardProcessing = new OutwardProcessing(getScanner());
					outwardProcessing.parse(tag.name());
					break;
				case Reentry:
					Reentry reentry = new Reentry(getScanner());
					reentry.parse(tag.name());
					addReentryList(reentry);
					break;
				case MeansOfIdentification:
					MeansOfIdentification text = new MeansOfIdentification(getScanner());
					text.parse(tag.name());
					addMeansOfIdentificationList(text);
					break;
				case Product:     
					Product tarif = new Product(getScanner());
					tarif.parse(tag.name());
					addProductList(tarif);
					break;						
				case Traders:   //EI20130422: only BDP
					traders = new Traders(getScanner());
					traders.parse(tag.name());
					this.setBdpTraders(traders);
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
				case DeclarationTime:
				case DateOfDeclaration:
					setDeclarationTime(value);
					if (tag == EDeclaration.DeclarationTime) {
   					 	setDeclarationTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setDeclarationTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;	
				case InjunctionType:
					setInjunctionType(value);
					break;									
				case CompanyNumberTaxPayer:
					setCompanyNumberTaxPayer(value);
					break;					
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
				case CustomsOfficeExport:						
					setCustomsOfficeExport(value);
					break;
				case CustomsOfficeForCompletion:						
					setCustomsOfficeForCompletion(value);
					break;
				case LoadingList:					
				case LoadingLists:						
					setLoadingLists(value);
					break;
				case IntendedOfficeOfExit:						
					setIntendedOfficeOfExit(value);
					break;							
				case DateOfPreAdvice:    
				case AdvanceNoticeTime:
					setAdvanceNoticeTime(value);
					if (tag == EDeclaration.AdvanceNoticeTime) {
						setAdvanceNoticeTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setAdvanceNoticeTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
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
					if (value.indexOf('-') == -1) {  
						setDateOfDepartureFormat(EFormat.KIDS_Date);
   				 	} else {  //in UIDS gibt es den tag nicht
   				 		setDateOfDepartureFormat(getUidsDateAndTimeFormat(value));    				 	
   				 	}						
					break;
				case AcceptanceTime:
				case DateOfAcceptance:
					setAcceptanceTime(value);
					if (tag == EDeclaration.AcceptanceTime) {
						setAcceptanceTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setAcceptanceTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}							
					break;
				case InvoiceCurrencyType:	
					setInvoiceCurrencyType(value);
					break;
				case AuthorizationTrustedExporter:
				case AuthorisationReliableExporter:
					setAuthorizationTrustedExporter(value);
					break;
				case Procedure:  //KIDS	
					setProcedure(value);					
					break;
				case RelevantDate:
				case DateOfRelevance:
					setRelevantDate(value);
					if (tag == EDeclaration.RelevantDate) {
						setRelevantDateFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setRelevantDateFormat(getUidsDateAndTimeFormat(value)); 
   				 	}					
					break;
				case FlagOfStatistic:
					setFlagOfStatistic(value);
						break;
				case RealOfficeOfExit:				
					setRealOfficeOfExit(value);
						break;
				case AddressCombination:
					setAddressCombination(value);
						break;					
				case DateOfExit:
				case DateOfAmendmentExit:
					setDateOfExit(value);
					if (tag == EDeclaration.DateOfExit) {
						setDateOfExitFormat(EFormat.KIDS_Date);
   				 	} else {
   				 		setDateOfExitFormat(getUidsDateAndTimeFormat(value)); 
   				 	}					
					break;
				case ContainerRegistration:   //EI20120913: steht zwar in xsd nur CH, aber schadet nicht
					if (container == null) {
						container = new Container();
					}
					container.addNumberList(value);						
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
	
	//public List<PreviousDocumentV20> getPreviousDocumentList() {
	public List<PreviousDocumentV21> getPreviousDocumentList() {
		return previousDocumentList;	
	}
	//public void setPreviousDocumentList(List<PreviousDocumentV20> previousDocumentList) {
	public void setPreviousDocumentList(List<PreviousDocumentV21> previousDocumentList) {
		this.previousDocumentList = previousDocumentList;
	}
	//public void addPreviousDocumentList(PreviousDocumentV20 previousDocument) {
	public void addPreviousDocumentList(PreviousDocumentV21 previousDocument) {
		if (previousDocumentList == null) {
			//previousDocumentList = new Vector<PreviousDocumentV20>();
			previousDocumentList = new Vector<PreviousDocumentV21>();
		}
		this.previousDocumentList.add(previousDocument);
	}

	public List<DocumentV20> getDocumentList() {
		return documentList;	
	}	
	public void setDocumentList(List<DocumentV20> list) {
		documentList = list;	
	}
	public void addDocumentList(DocumentV20 argument) {  		
		if (documentList == null) {
			documentList = new Vector<DocumentV20>();
		}
		this.documentList.add(argument);
	}	

	public HeaderExtensions getHeaderExtensions() {
		return headerExtensions;
	}
	public void setHeaderExtensions(HeaderExtensions headerExtensions) {
		this.headerExtensions = headerExtensions;
	}
     
	public void setForwarder(Party argument) {
		this.forwarder = argument;				    	
    }
	public Party getForwarder() {
		if (forwarderTIN != null) {
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
	
	public void setDeclarant(Party argument) {
			this.declarant = argument;
	}	
	public void setDeclarantTIN(TIN argument) {   //EI20130820
		this.declarantTIN = argument;		
	}
	public void setDeclarantContactPerson(ContactPerson argument) {   //EI20130820
		this.declarantContactPerson = argument;		
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
			if (beneficiaryContactPerson != null) {   //EI20120912
				if (beneficiary == null) {
					beneficiary = new Party();
				}
				beneficiary.setContactPerson(beneficiaryContactPerson);
			}	
			return beneficiary;
	}		

		
	private void setKidsFromEdecHeader(EdecHeader edecHeader) {
		if (edecHeader == null) {
			return;
		}
		List<String> nrList = edecHeader.getPrevRegNrList();
		
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
				//PreviousDocumentV2o pd = new PreviousDocumentV2o();
				PreviousDocumentV21 pd = new PreviousDocumentV21();
				//EI20130808: pd.setMarks(nr);  
				pd.setReference(nr);  //EI20130808
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
	public EFormat getAdvanceNoticeTimeFormat() {
		return this.advanceNoticeTimeFormat;
	}
	public void setAdvanceNoticeTimeFormat(EFormat argument) {
		this.advanceNoticeTimeFormat = argument;
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
	
	public Party getCustomsDocumentsReceiver() {
		if (customsDocumentsReceiverTIN != null) {
			if (customsDocumentsReceiver == null) {
				customsDocumentsReceiver = new Party();
			}
			customsDocumentsReceiver.setPartyTIN(customsDocumentsReceiverTIN);
		}
		if (customsDocumentsReceiverContactPerson != null) {
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
			
	public void setTypeOfRepresentation(String argument) {
		this.typeOfRepresentation = argument;
	}
	public String getTypeOfRepresentation() {
		return typeOfRepresentation;
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
	public EFormat getDeclarationTimeFormat() {
		return declarationTimeFormat;	
	}
	public void setDeclarationTimeFormat(EFormat format) {
		this.declarationTimeFormat = format;
	}

	public String getInjunctionType() {
		return injunctionType;	
	}
	public void setInjunctionType(String injunctionType) {
		this.injunctionType = Utils.checkNull(injunctionType);
	}

	public String getCompanyNumberTaxPayer() {
		return companyNumberTaxPayer;	
	}
	public void setCompanyNumberTaxPayer(String companyNumberTaxPayer) {
		this.companyNumberTaxPayer = Utils.checkNull(companyNumberTaxPayer);
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
	public void setRepresentedTIN(TIN argument) {   //EI20130820
		this.representedTIN = argument;		
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
	public int getDeclarationNumber() {
		return declarationNumber;
	}
	public void setDeclarationNumber(int declarationNumber) {
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
		if (consigneeSecurityTIN  != null) {
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
		if (carrierTIN != null) {
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
	public EFormat getDateOfDepartureFormat() {
		return departureDateFormat;
	}
	public void setDateOfDepartureFormat(EFormat format) {
		this.departureDateFormat = format;
	}
	
	public String getAcceptanceTime() {
		return acceptanceDateTime;
	}
	public void setAcceptanceTime(String date) {
		this.acceptanceDateTime = Utils.checkNull(date);
	}
	public EFormat getAcceptanceTimeFormat() {
		return acceptanceDateTimeFormat;
	}
	public void setAcceptanceTimeFormat(EFormat format) {
		this.acceptanceDateTimeFormat = format;
	}
	
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String value) {
		this.procedure = Utils.checkNull(value);
	}
	
	public String getInvoiceCurrencyType() {
		return invoiceCurrencyType;
	}
	public void setInvoiceCurrencyType(String value) {
		this.invoiceCurrencyType = Utils.checkNull(value);
	}
		
	public String getAuthorizationTrustedExporter() {
		return authorizationTrustedExporter;
	}
	public void setAuthorizationTrustedExporter(String value) {
		this.authorizationTrustedExporter = Utils.checkNull(value);
	}
	
	public String getRelevantDate() {
		return relevantDate;
	}
	public void setRelevantDate(String date) {
		this.relevantDate = Utils.checkNull(date);
	}
	public EFormat getRelevantDateFormat() {
		return relevantDateFormat;
	}
	public void setRelevantDateFormat(EFormat format) {
		this.relevantDateFormat = format;
	}
	
	public String getFlagOfStatistic() {
		return flagOfStatistic;
	}
	public void setFlagOfStatistic(String value) {
		this.flagOfStatistic = Utils.checkNull(value);
	}
	
	public String getRealOfficeOfExit() {
		return realOfficeOfExit;
	}
	public void setRealOfficeOfExit(String value) {
		this.realOfficeOfExit = Utils.checkNull(value);
	}
	
	public String getAddressCombination() {
		return addressCombination;
	}
	public void setAddressCombination(String value) {
		this.addressCombination = Utils.checkNull(value);
	}
	
	public String getDateOfExit() {
		return dateOfExit;
	}
	public void setDateOfExit(String date) {
		this.dateOfExit = Utils.checkNull(date);
	}
	public EFormat getDateOfExitFormat() {
		return dateOfExitFormat;
	}
	public void setDateOfExitFormat(EFormat format) {
		this.dateOfExitFormat = format;
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
	
	public OutwardProcessing getOutwardProcessing() {
		if (reentryList != null) {
			if (outwardProcessing == null) {
				outwardProcessing = new OutwardProcessing();
			}
			outwardProcessing.setReentryList(reentryList);
		}
		if (meansOfIdentificationList != null) {
			if (outwardProcessing == null) {
				outwardProcessing = new OutwardProcessing();
			}
			outwardProcessing.setMeansOfIdentificationList(meansOfIdentificationList);
		}
		if (productList != null) {
			if (outwardProcessing == null) {
				outwardProcessing = new OutwardProcessing();
			}
			outwardProcessing.setProductList(productList);
		}
		return outwardProcessing;
	}
	public void setOutwardProcessing(OutwardProcessing argument) {
		this.outwardProcessing = argument;
	}
	
	public List <Reentry> getReentryList() {
		return reentryList;
	}
	public void setReentryList(List <Reentry> argument) {
		this.reentryList = argument;
	}
	public void addReentryList(Reentry argument) {
		if (reentryList == null) {
			reentryList = new Vector <Reentry>();
		}
		reentryList.add(argument);
	}
	
	public List <MeansOfIdentification> getMeansOfIdentificationList() {
		return meansOfIdentificationList;
	}
	public void setMeansOfIdentificationList(List <MeansOfIdentification> argument) {
		this.meansOfIdentificationList = argument;
	}
	public void addMeansOfIdentificationList(MeansOfIdentification argument) {
		if (meansOfIdentificationList == null) {
			meansOfIdentificationList = new Vector <MeansOfIdentification>();
		}
		meansOfIdentificationList.add(argument);
	}
	
	public List <Product> getProductList() {
		return productList;
	}
	public void setProductList(List <Product> argument) {
		this.productList = argument;
	}
	public void addProductList(Product argument) {
		if (productList == null) {
			productList = new Vector <Product>();
		}
		productList.add(argument);
	}
	
	private void setKidsFromCustomsOffices(CustomsOffices argument) {
		if (argument == null) {
			return;
		}
		this.customsOfficeExport = argument.getOfficeOfExport();
        this.customsOfficeForCompletion = argument.getOfficeOfAdditionalDeclarationExport();
		this.intendedOfficeOfExit = argument.getOfficeOfExit();
		this.realOfficeOfExit = argument.getOfficeOfActualExit();   //new in V20
		
		String svco = argument.getSupervisingCustomsOffice();		
	  	if (!Utils.isStringEmpty(svco)) {	  	
	  		if (supervisingCustomsOffice == null) {
				supervisingCustomsOffice = new Party();
			}
	  		Address adr = new Address();
	  		adr.setName(svco);
	  		supervisingCustomsOffice.setAddress(adr);
	  	}  
	}
	
	public String getFromFormat() {               //EI20120913
		return fromFormat;
	}
	public void setFromFormat(String value) {     //EI20120913
		this.fromFormat = Utils.checkNull(value);
	}
	
	private void setBdpTraders(Traders traders) {
		if (traders == null) {
			return;
		}
		if (traders.getDeclarantTIN() != null) {
			this.declarantTIN = new TIN();
			this.declarantTIN.setTIN(traders.getDeclarantTIN().getTIN());
			this.declarantTIN.setCustomerIdentifier(traders.getDeclarantTIN().getCustomerId());			
			this.declarantTIN.setBO(traders.getDeclarantTIN().getBO());
			//this.declarantTIN.setIdentificationType(traders.getDeclarantTIN().getCustomerIdentifier());
			this.declarantTIN.setIdentificationType(traders.getDeclarantTIN().getIdentificationType());   //EI20130802
		}
		if (traders.getDeclarantAddress() != null) {
			this.declarant = new Party();
			this.declarant.setAddress(traders.getDeclarantAddress());			
		}
		
		if (traders.getRepresentativeTIN() != null) {
			this.agentTIN = new TIN();
			this.agentTIN.setTIN(traders.getRepresentativeTIN().getTIN());
			this.agentTIN.setCustomerIdentifier(traders.getRepresentativeTIN().getCustomerId());			
			this.agentTIN.setBO(traders.getRepresentativeTIN().getBO());
			//this.agentTIN.setIdentificationType(traders.getRepresentativeTIN().getCustomerIdentifier());
			this.agentTIN.setIdentificationType(traders.getRepresentativeTIN().getIdentificationType());   //EI20130802
		}
		if (traders.getRepresentativeAddress() != null) {
			this.agent = new Party();
			this.agent.setAddress(traders.getRepresentativeAddress());			
		}
		
		if (traders.getConsigneeTIN() != null) {
			this.consigneeTIN = new TIN();
			this.consigneeTIN.setTIN(traders.getConsigneeTIN().getTIN());
			this.consigneeTIN.setCustomerIdentifier(traders.getConsigneeTIN().getCustomerId());			
			this.consigneeTIN.setBO(traders.getConsigneeTIN().getBO());
			//this.consigneeTIN.setIdentificationType(traders.getConsigneeTIN().getCustomerIdentifier());
			this.consigneeTIN.setIdentificationType(traders.getConsigneeTIN().getIdentificationType());   //EI20130802
		}
		if (traders.getConsigneeAddress() != null) {
			this.consignee = new Party();
			this.consignee.setAddress(traders.getConsigneeAddress());			
		}
		
		if (traders.getConsignorTIN() != null) {
			this.consignorTIN = new TIN();
			this.consignorTIN.setTIN(traders.getConsignorTIN().getTIN());
			this.consignorTIN.setCustomerIdentifier(traders.getConsignorTIN().getCustomerId());			
			this.consignorTIN.setBO(traders.getConsignorTIN().getBO());
			//this.consignorTIN.setIdentificationType(traders.getConsignorTIN().getCustomerIdentifier());
			this.consignorTIN.setIdentificationType(traders.getConsignorTIN().getIdentificationType());  //EI20130802
		}
		if (traders.getConsignorAddress() != null) {
			this.consignor = new Party();
			this.consignor.setAddress(traders.getConsignorAddress());			
		}
	}
	
	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 if (value.length() < 10) {
			 eFormat = EFormat.ST_Time_Min;
		 } else if (value.length() == 10) {
			 //EI20130808: eFormat = EFormat.ST_DateTime;
			 eFormat = EFormat.ST_Date;
		 } else if (value.length() > 10 && !value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || (value.length() > 19 && !value.substring(19, 20).equals("Z"))) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}

}

