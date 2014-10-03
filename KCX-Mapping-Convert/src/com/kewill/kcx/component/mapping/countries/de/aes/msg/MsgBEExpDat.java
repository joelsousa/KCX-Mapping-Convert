/*
 * Function    : MsgBEExpDat.java
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
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TypeOfDeclaration;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgBEExpDat<br>
 * Erstellt		: 17.12.2009<br>
 * Beschreibung : Contains Message Structure with all Fields used in KIDS,
 *                to use for ExportDeclaration Messages of Belgium. 
 * 
 * @author krzoska
 * @version 1.1.00
 */
public class MsgBEExpDat extends KCXMessage {
	private String msgName = "ExportDeclaration";
	private String areaCode; //EI20090415
	private String typeOfPermit; //EI20090415
	//EI20090415:private EdecHeader edecHeader; 			//for CH only	
	private String kindOfDeclaration; //EI20090415
	private String typeOfPermitCH;						//for CH only
	private String nctsType;							//for CH only
	private String UCRNumber;
	private String dispatchCountry; 
	private String destinationCountry;
	private String situationCode;  
    private String paymentType;
    private String descriptionLanguage;
    private String transportInContainer;
    private String grossMass;	//n(11,3)
    private String netMass;
    private String uCROtherSystem;
    private String annotation;
    private String shipmentNumber;
    private String referenceNumber;
    private String orderNumber;
    private String totalNumberPositions;    //EI090608
    private String authorizationNumber;   
    private String previousProcedure;  //EI20090415
    private String declarantIsConsignor;
    private String receiverCustomerNumber;  //für UIDS noch nicht definiert
    private String correctionCode;
    private String bunchNumber;	
	private String language;
	private String transferToTransitSystem;
	private String placeOfDeclaration;
	/* AK20091223
	private String initialSenderTIN;
	private String initialSender;
	private String initialSenderContactPerson;
	*/
	
	//AK20091223
	private TIN initialSenderTIN;
	private Party initialSender;
	private ContactPerson initialSenderContactPerson;

	
	private String accountNumber;
	private String repertoriumNumber;
	private String customsDocumentsReceiverContactPerson;
	private String controlResultCode;



	private ContactPerson contact;  //EI090608
	//EI090608: private String clerk;   
    private String advanceNoticeTime;   //EI20090422 used only in Pre-Notification
	
	
	//EI20090415: private ApprovedTreatment procedure;		
	private TransportMeans trmInland;
	private TransportMeans trmDeparture;
	private TransportMeans trmBorder;
	private PlaceOfLoading placeOfLoading;	
	//EI20090415: private CustomsOffices customsOffices;	
	private String customsOfficeExport;		    //EI20090415		
	private String customsOfficeForCompletion;	//EI20090415		
	private String intendedOfficeOfExit;		//EI20090415
	private Business business;
	private Route trRoute;
    //EI20090415: private List <Route>routeList;  //eine Route mit CountryList
    private Seal  seal;
    private HeaderExtensions headerExtensions;  //nur bei UIDS
    private ExportRefundHeader exportRefundHeader;
    private LoadingTime loadingTime;
    
    private Party forwarder;   
    private Party agent; 
    private TIN agentTIN; 
    private ContactPerson agentContactPerson; 
    private Party consignee; 
    private TIN consigneeTIN;
    //private ContactPerson consigneeContactPerson;
    private Party consignor;  
    private TIN consignorTIN;
    private ContactPerson consignorContactPerson;    
    private Party declarant; 
    private TIN declarantTIN;
    private ContactPerson declarantContactPerson;
    private Party subcontractor; 
    private TIN subcontractorTIN;
    private ContactPerson subcontractorContactPerson;
    //EI20090409  end
    
    private Party beneficiary; 
    private TIN beneficiaryTIN;
    private ContactPerson beneficiaryContactPerson;
    

    private IncoTerms incoTerms;
    private Container container;
	private List <Container>containerList;    
    private Document document;
    private List <Document>documentList;    
	private PreviousDocument previousDocument;
	private List <PreviousDocument>previousDocumentList; 
	private String loadingLists;
	private MsgBEExpDatPos goodsItem;
	private List <MsgBEExpDatPos>goodsItemList;
	
    //-------------	
	    
//	private XMLEventReader 	parser;
//    private boolean debug;
	
	//EI private Party exporter;
    
	public MsgBEExpDat() {
			super();
	}
	
	public MsgBEExpDat(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	 
	private enum EDeclaration {
		//KIDS-Name							//UIDS-Name
        AreaCode,                           TypeOfDeclaration,      //TypeOfDeclaration.RegionCode,
		TypeOfPermit,                                               //TypeOfDeclaration.ProcedureCode,
        KindOfDeclaration,                  EdecHeader,             //CH: EdecHeader.TypeOfDeclaration,
		TypeOfPermitCH,                                             //CH: EdecHeader
		NCTSType,                                                   //CH: same
		UCRNumber,							DocumentReferenceNumber,
		DispatchCountry,					CountryOfDispatch,
		DestinationCountry,					//same 
		SituationCode,						SpecCircumstance,  
		PaymentType,						TransportPaymentMethod,
		DescriptionLanguage,
		TransportInContainer,				ContainerIndicator,
		GrossMass,							//same
		NetMass,
		PlaceOfDeclaration,
		BeneficiaryTIN,
		Beneficiary,
		BeneficiaryContactPerson,
		InitialSenderTIN,
		InitialSender,
		InitialSenderContactPerson,
		AccountNumber,
		RepertoriumNumber,
		CustomsDocumentsReceiverContactPerson,
		ControlResultCode,
		//AK20090512:
		UCROtherSystem,						ExternalRegistrationNumber,
		Annotation,							Remark,
		//AK20090512:
		//ShipmentNumber,					ExternalRegistrationNumber,
		//AK20090513
		ShipmentNumber, 					CommercialReferenceNumber,
		ReferenceNumber,					//same 
		OrderNumber,						LocalReferenceNumber,
		TotalNumberPositions,			    ItemsQuantity,
		AuthorizationNumber,				AuthorisationID,
		PreviousProcedure,  				Procedure, // Procedure.Previous
		DeclarantIsConsignor,				ParticipantsCombination,
		CorrectionCode,                                             //CH: EdecHeader.CorrectionCode		
		BunchNumber,                                                //CH: EdecHeader.CollectionNumber
		Language,							//same              	//CH
		TransferToTransitSystem,                                    //CH: EdecHeader.NCTSIdentifier
		Clerk,	
		ContactPerson,				        Contact, //Contact.Identity
		MeansOfTransportInland,  			MeansOfTransport, 
		MeansOfTransportDeparture,    		//MeansOfTransport 
		MeansOfTransportBorder,				//MeansOfTransport 
		PlaceOfLoading,						//same
		ReceiverCustomerNumber,		//TODO noch kein UIDS-Tag
		CustomsOfficeExport,				CustomsOffices,
		CustomsOfficeForCompletion,			
		IntendedOfficeOfExit,				
		Business,							Transaction,
		TransportationRoute,				Itinerary,
		Seal,								Seals,
											HeaderExtensions,
		ExportRefundHeader,					ExportRestitutionHeader,
		LoadingTime,						ShipmentPeriod,
		Forwarder,							Shipper,     			//CH
		Consignor,							Exporter, //EI: Address,
		ConsignorTIN,						
		ConsignorContactPerson,	
		Declarant,                          //same
		DeclarantTIN,							
		DeclarantContactPerson,				
		Agent,								DeclarantRepresentative,
		AgentTIN,							
		AgentContactPerson,
		Subcontractor,						//same
		SubcontractorTIN,					
		SubcontractorContactPerson,			
		Consignee, 							//same 
		ConsigneeTIN,
		IncoTerms,                          Incoterms,
		Document,							// from GoodsItems !!!???
		Container,                          ContainerRegistration,    //CH; nur bei KIDS im Kopf
		PreviousDocument,					PrevRegNr,				  //CH
		AdvanceNoticeTime,					DateOfPreAdvice,
		LoadingLists,
		Goodsitem,							GoodsItem;
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
					if (attr != null) {  //EI20120309
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
					trRoute = new Route(getScanner());
					trRoute.parse(tag.name());						
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
					
				case GoodsItem:
				case Goodsitem:
					goodsItem = new  MsgBEExpDatPos(getScanner(), msgName);
					goodsItem.parse(tag.name());

					if (goodsItemList == null) {
                        goodsItemList = new Vector<MsgBEExpDatPos>();
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
					setPlaceOfDeclaration(value);
					break;

				case AccountNumber:
					setAccountNumber(value);
					break;
					
				case RepertoriumNumber:
					setRepertoriumNumber(value);
					break;

				case CustomsDocumentsReceiverContactPerson:
					setCustomsDocumentsReceiverContactPerson(value);
					break;

				case ControlResultCode:					
					setCustomsDocumentsReceiverContactPerson(value);
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
	public String getKindOfDeclarationt() {
		return kindOfDeclaration;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getUCRNumber() {
		return UCRNumber;
	}
	public void setUCRNumber(String argument) {
		this.UCRNumber = argument;
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
    	return trRoute;
    }
	public void setTransportationRoute(Route argument) {
		this.trRoute = argument;
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
	
    public MsgBEExpDatPos getMsgExpRelPos() {
    	return goodsItem;
    }
	public void setMsgExpRelPos(MsgBEExpDatPos argument) {
		this.goodsItem = argument;
	}
	
    public List <MsgBEExpDatPos>getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(MsgBEExpDatPos argument) {
    	this.goodsItemList.add(argument);
    }
    public void setGoodsItemList(List <MsgBEExpDatPos> argument) {
    	this.goodsItemList = argument;  	
    }

	public MsgBEExpDatPos getGoodsItem() {
    	return goodsItem;
    }  
    public void setGoodsItemList(MsgBEExpDatPos argument) {
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

	public List<Container> getContainerList() {
		return containerList;	
	}
	public void setContainerList(List<Container> containerList) {
		this.containerList = containerList;
	}	
	public void addContainerList(Container argument) {
		this.containerList.add(argument);
	}

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

	public String getCustomsDocumentsReceiverContactPerson() {
		return customsDocumentsReceiverContactPerson;
	
	}

	public void setCustomsDocumentsReceiverContactPerson(
			String customsDocumentsReceiverContactPerson) {
		this.customsDocumentsReceiverContactPerson = Utils
				.checkNull(customsDocumentsReceiverContactPerson);
	}

	public String getControlResultCode() {
		return controlResultCode;
	
	}

	public void setControlResultCode(String controlResultCode) {
		this.controlResultCode = Utils.checkNull(controlResultCode);
	}		
	
	
}

