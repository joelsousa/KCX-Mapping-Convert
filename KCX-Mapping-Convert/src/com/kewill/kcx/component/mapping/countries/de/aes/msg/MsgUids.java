/*
*
 * Function    : MsgUids.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / houdek
 * Description : Contains Message Structure with all Fields used in UIDS,
 * 			   : to use for different Messages 
 * Parameters  :

 * Changes
 * -----------
 * Author      : AK
 * Date        : 12.03.2009
 * Label       : AK20090312
 * Description : new Members for V60: Itinerary added 
 *
 * Changes
 * -----------
 * Author      : EI
 * Date        : 12.03.2009
 * Label       : EI20090312
 * Description : new Members for V60 added
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
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
 * Modul		: MsgUids<br>
 * Erstellt		: 17.09.2008<br>
 * Beschreibung	: Contains Message Structure with all Fields used in UIDS to use for different Messages. 
 * 
 * @author houdek
 * @version 1.0.00
 */
public class MsgUids extends KCXMessage {
	private  String msgName;
	
	private String typeOfRelease;
	private String applicationReference;
	private String documentReferenceNumber;
    private String dateOfAdditionalDeclaration;
    private String dateOfCancellation;
    private String dateOfDeclaration;
    private String destinationCountry;
    private String dateOfExit;
    private String dateOfRelease;
    private String dateOfAcceptance;
    private String dateOfReceipt;
    private String dateOfProcessing;
    private String dateOfRejection;
    private String dateOfTermination; 
    private String dateOfPreAdvice;                     //EI20090316
    private String shortageIndicator;
    private String containerIndicator;
    private String countryOfDispatch;                    //19.11.08
    private String grossMass;
    private String externalRegistrationNumber;
    private String remark;
    private String description;
    private String code;
    private String termination;  
    private String typeOfShipment;
    private String referenceNumber;
    private String registrationNumber;
    private String localReferenceNumber;
    private String itemsQuantity;
    private String packagesQuantity;
    private String authorisationID;
    private String participantsCombination;
    private String dateOfControl;
    private String typeOfControl;
    private String commercialReferenceNumber;
    private String alternateProofIndicator;
    private String specCircumstance;                //EI20090312 V60
    private String transportPaymentMethod;          //EI20090312 V60

    private TypeOfDeclaration typeOfDeclaration;
    private Trader consignee;
    private Trader consignor;
	private Trader declarant;
	private Trader declarantRepresentative;
	private Trader exporter;
    private Trader issuer;
    private Trader recipient;
    private Trader shipper;
    private Trader subcontractor;
    private Trader subcontractorRepresentative;

//    private Attributes attribute;
//    private XmlMsgScanner myScanner;    
    
    private TransportMeans   trmInland;
    private TransportMeans   trmDeparture;
    private TransportMeans   trmBorder;
    private PlaceOfLoading   placeOfLoading;
    private PDFInformation   pDFInformation;                                 //05.12.08
    private CustomsOffices	    customsOffices; 
    private Business			business;  //UIDS: Transaction
    private Seal	            seal;
    private LoadingTime         loadingTime;  //UIDS: ShipmentPeriod
    private String dateOfLoadingBegin;
    private String dateOfLoadingEnd;	
    private ApprovedTreatment   approvedTreatment;
    private ContactPerson       contactPerson;
    private IncoTerms           incoTerms;  
    private PreviousDocument   	previousDocument;  
    private HeaderExtensions    headerExtensions;
    private Route	            route;
    private ExportRefundHeader  exportRefundHeader;

    private MsgUidsPos goodsItem;
    private List<MsgUidsPos> goodsItemList;  //AK20090107
    
	private ErrorType error;                       
	private List <ErrorType>errorList;	
	
	private XMLEventReader 	parser;
	
    private boolean debug;

	public  MsgUids() throws XMLStreamException {
			super();
			goodsItemList = new Vector<MsgUidsPos>();
			errorList     = new Vector<ErrorType>();
	}
	
	public  MsgUids(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		this.parser = parser;
		goodsItemList = new Vector<MsgUidsPos>();
		errorList     = new Vector<ErrorType>();
	}
	
	public  MsgUids(XMLEventReader parser, String msgName) throws XMLStreamException {
		super(parser);
		this.parser = parser;
		goodsItemList = new Vector<MsgUidsPos>();
		errorList     = new Vector<ErrorType>();
		this.msgName = msgName;
	}	
	
   
	private enum EMsgUids {
		  CommunicationPartner,  
		  Consignee,
		  Consignor,
		  Contact,
		  Declarant,
		  DeclarantRepresentative,
		  Exporter,
		  Issuer,
		  Recipient,
		  Shipper,
		  Subcontractor,
		  SubcontractorRepresentative,
		  AirportCode,			
		  AnswerIndicator,		
		  AuthorisationID,
		  //CertificatesQuantity, //not in use
		  ContainerIndicator,
		  //Customs_dex,			//not in use
		  CustomsOffices,
		  ApplicationReference,
		  DateOfAcceptance,		  		 
		  DateOfAdditionalDeclaration,		
		  DateOfCancellation,	
		  DateOfDeclaration,
		  DateOfExit,
		  DateOfLoadingBegin,
		  DateOfLoadingEnd,		  
		  DateOfProcessing,
		  DateOfReceipt,
		  DateOfRejection,
		  DateOfRelease,		  
		  DestinationCountry,
		  CountryOfDispatch,            //19.11.08           
		  DateOfTermination,
		  DateOfControl,
		  DateOfPreAdvice,              //EI20090316 for Prenotification
		  DocumentReferenceNumber,
		  //DocumentInformation,		//not in use
		  //ExportAssuranceCertificate,	//not in use
		  ExportRefund,					//not in use
		  ExternalRegistrationNumber,   //MRN		 
		  Error,
		  MRN,
		  //FunctionCode,  		 		//not in use
		  GrossMass,
		  //Guarantee,					//not in use
		  Incoterms,
		  //Invoice,					//not in use
		  //IssuePlace,					//not in use
		  ItemsQuantity,
		  //LoadingList,				//not in use
		  LocalReferenceNumber,
		  MeansOfTransport,
		  //OriginCountry,				//not in use
		  PackagesQuantity,
		  ParticipantsCombination,
		  PDFInformation,
		  //PaymentDateLimit,			//not in use
		  PlaceOfLoading,
		  Procedure,	
		  ReferenceNumber,	
		  RegistrationNumber,
		  //RemittanceToOrderIndicator, //not in use
		  Remark,
		  Description,
		  Code,
		  CommercialReferenceNumber,
		  TypeOfControl,
		  RepresentationFlag, 			//not in use
		  Itinerary,		  			//AK20090312 V60, KIDS:Route
		  Seals,
		  Termination,
		  ShortageIndicator, 	
		  ShipmentPeriod,
		  TypeOfShipment, 
		  PreviousDocument, 
		  //SimplifiedProcedureAuthorization, //not in use
		  //SimplifiedProcedureIndicator,     //not in use
		  //SpecialRequests,				  //not in use
		  Transaction,
		  TypeOfDeclaration,		       
		  TypeOfRelease,		  
		  HeaderExtensions,	 
		  AlternateProofIndicator,            //EI20090312 V60
		  SpecCircumstance,                   //EI20090312 V60
		  TransportPaymentMethod,             //EI20090312 V60
		  ExportRestitutionHeader,            //EI20090312 V60  
		  GoodsItem, 
	      Goodsitem;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		
			if (value == null) {
				switch ((EMsgUids) tag) {
				
					case TypeOfDeclaration:
						typeOfDeclaration = new TypeOfDeclaration(getScanner());
						typeOfDeclaration.parse(tag.name());
						break;
				         
					case Consignee:
						consignee = new Trader(getScanner());						
						consignee.parse(tag.name());
						break;
						
					case Consignor:
					    consignor = new Trader(getScanner());	
					    consignor.parse(tag.name());					    
						break;
						
					case Declarant:
						declarant = new Trader(getScanner());
						declarant.parse(tag.name());							
						break;
						
					case DeclarantRepresentative:
						declarantRepresentative = new Trader(getScanner());
						declarantRepresentative.parse(tag.name());							 
						break;
						
					case Exporter:
						exporter = new Trader(getScanner());
						exporter.parse(tag.name());
						break;
						
					case Issuer:
						issuer = new Trader(getScanner());
						issuer.parse(tag.name());						
						break;
						
					case Recipient:
						recipient = new Trader(getScanner());
						recipient.parse(tag.name());						
						break;
						
					case Shipper:
						shipper = new Trader(getScanner());
						shipper.parse(tag.name());						
						break;
						
					case Subcontractor:
						subcontractor = new Trader(getScanner());
						subcontractor.parse(tag.name());							
						break;
						
					case SubcontractorRepresentative:
						subcontractorRepresentative = new Trader(getScanner());
						subcontractorRepresentative.parse(tag.name());						
						break;

					case CustomsOffices:
						customsOffices = new CustomsOffices(getScanner());
						customsOffices.parse(tag.name());							
						break;

					case Procedure:
						approvedTreatment = new ApprovedTreatment(getScanner());
						approvedTreatment.parse(tag.name());							
						break;

					case Contact:
						contactPerson = new ContactPerson(getScanner());
						contactPerson.parse(tag.name());						
						break;						

					case Incoterms:
						incoTerms = new IncoTerms(getScanner());
						incoTerms.parse(tag.name());						
						break;	

					case HeaderExtensions:
						headerExtensions = new HeaderExtensions(getScanner());
						headerExtensions.parse(tag.name());						
						break;		
						
					case PreviousDocument:
						previousDocument = new PreviousDocument(getScanner());
						previousDocument.parse(tag.name());						
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
						
 					case PDFInformation:
 						pDFInformation = new PDFInformation(getScanner());           //05.12.08
 						pDFInformation.parse(tag.name());						
						break;
						
					case Transaction:
						business = new Business(getScanner()); //iwa						
						business.parse(tag.name());											
						break;
						
					case Seals:
						seal = new Seal(getScanner());
						seal.parse(tag.name());							
						break;

					case ShipmentPeriod:
						loadingTime = new LoadingTime(getScanner());
						loadingTime.parse(tag.name());										
						break;	
						
					case Error:
						error = new ErrorType(getScanner());	
						error.parse(tag.name());
						addErrorList(error);
						break;
					case Itinerary:  //AK20090312
						route = new Route(getScanner());	
						route.parse(tag.name());
						break;	
						
					case ExportRestitutionHeader:
						exportRefundHeader = new ExportRefundHeader(getScanner(), "UIDS");	
						exportRefundHeader.parse(tag.name());
						break;
						  
					case GoodsItem:
					case Goodsitem:
						goodsItem = new  MsgUidsPos(getScanner());
						goodsItem.parse(tag.name());							
						addGoodsItemList(goodsItem);
						break;
						
				default:
						return;
				}
			} else {
				switch ((EMsgUids) tag) {
					case TypeOfRelease:
						setTypeOfRelease(value);
						break;
						
					case DocumentReferenceNumber:
						setDocumentReferenceNumber(value);
						break;
						
					case DateOfAdditionalDeclaration:
						setDateOfAdditionalDeclaration(value);
						break;
						
					case DateOfCancellation:
						setDateOfCancellation(value);
						break;
						
					case DateOfDeclaration:
						setDateOfDeclaration(value);
						break;
						
					case DateOfTermination:
						setDateOfTermination(value);
						break;	
						
					case DestinationCountry:
						setDestinationCountry(value);
						break;
						
					case CountryOfDispatch:                       //19.11.08
						setCountryOfDispatch(value);
						break;						
					case DateOfExit:
						setDateOfExit(value);
						break;
						
					case DateOfRelease:
						setDateOfRelease(value);
						break;
						
					case DateOfAcceptance:
						setDateOfAcceptance(value);
						break;
						
					case DateOfReceipt:
						setDateOfReceipt(value);
						break;
						
					case DateOfRejection:
						setDateOfRejection(value);
						break;	
						
					case DateOfProcessing:
						setDateOfProcessing(value);
						break;							
	
					case ShortageIndicator:
						setShortageIndicator(value);
						break;
						
					case ContainerIndicator:
						setContainerIndicator(value);
						break;
						
					case GrossMass:
						setGrossMass(value);
						break;
						
					case ExternalRegistrationNumber:
						setExternalRegistrationNumber(value);
						break;
						
					case Remark:
						setRemark(value);
						break;
						
					case Description:
						setDescription(value);
						break;	
						
					case Code:
						setCode(value);
						break;	
						
					case CommercialReferenceNumber:
						setCommercialReferenceNumber(value);
						break;	

					case Termination:
						setTermination(value);
						break;	
						
					case TypeOfShipment:
						setTypeOfShipment(value);
						break;	
						
					case ReferenceNumber:
						setReferenceNumber(value);
						break;
						
					case LocalReferenceNumber:
						setLocalReferenceNumber(value);
						break;
						
					case ItemsQuantity:
						setItemsQuantity(value);
						break;
						
					case PackagesQuantity:
						setPackagesQuantity(value);
						break;
						
					case AuthorisationID:
						setAuthorisationID(value);
						break;
						
					case ParticipantsCombination:
						setParticipantsCombination(value);
						break;
						
					case DateOfControl:
						setDateOfControl(value);
						break;
						
					case DateOfPreAdvice:
						setDateOfPreAdvice(value);
						break;
						
					case TypeOfControl:
						setTypeOfControl(value);
						break;	
						
					case ApplicationReference:
						setApplicationReference(value);
						break;	
						
					case RegistrationNumber:
						setRegistrationNumber(value);
						break;
						
					case DateOfLoadingBegin:
						setDateOfLoadingBegin(value);
						break;
						
					case DateOfLoadingEnd:		
						setDateOfLoadingEnd(value);
						break;
						
					case AlternateProofIndicator:
						setAlternateProofIndicator(value);
						break;
												  					
					case SpecCircumstance:
						setSpecCircumstance(value);
						break;
					case TransportPaymentMethod:
						setTransportPaymentMethod(value);
						break;						
				}
			}
		}

	
	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
				return EMsgUids.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getTypeOfRelease() {
		return typeOfRelease;
	}

	public void setTypeOfRelease(String typeOfRelease) {
		this.typeOfRelease = typeOfRelease;
	}

	public String getDocumentReferenceNumber() {
		return documentReferenceNumber;
	}

	public void setDocumentReferenceNumber(String documentReferenceNumber) {
		this.documentReferenceNumber = documentReferenceNumber;
	}

	public String getDateOfAdditionalDeclaration() {
		return dateOfAdditionalDeclaration;
	}

	public void setDateOfAdditionalDeclaration(String dateOfAdditionalDeclaration) {
		this.dateOfAdditionalDeclaration = dateOfAdditionalDeclaration;
	}

	public String getDateOfCancellation() {
		return dateOfCancellation;
	}

	public void setDateOfCancellation(String dateOfCancellation) {
		this.dateOfCancellation = dateOfCancellation;
	}

	public String getDateOfDeclaration() {
		return dateOfDeclaration;
	}

	public void setDateOfDeclaration(String dateOfDeclaration) {
		this.dateOfDeclaration = dateOfDeclaration;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getCountryOfDispatch() {                                 //19.11.08
		return countryOfDispatch;
	}

	public void setCountryOfDispatch(String countryOfDispatch) {          //19.11.08  
		this.countryOfDispatch = countryOfDispatch;
	}

	public String getDateOfExit() {
		return dateOfExit;
	}

	public void setDateOfExit(String dateOfExit) {
		this.dateOfExit = dateOfExit;
	}

	public String getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(String dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public String getDateOfAcceptance() {
		return dateOfAcceptance;
	}

	public void setDateOfAcceptance(String dateOfAcceptance) {
		this.dateOfAcceptance = dateOfAcceptance;
	}

	public String getDateOfReceipt() {
		return dateOfReceipt;
	}

	public void setDateOfReceipt(String dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public String getShortageIndicator() {
		return shortageIndicator;
	}

	public void setShortageIndicator(String shortageIndicator) {
		this.shortageIndicator = shortageIndicator;
	}

	public String getContainerIndicator() {
		return containerIndicator;
	}

	public void setContainerIndicator(String containerIndicator) {
		this.containerIndicator = containerIndicator;
	}

	public String getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(String grossMass) {
		this.grossMass = grossMass;
	}

	public String getExternalRegistrationNumber() {
		return externalRegistrationNumber;
	}

	public void setExternalRegistrationNumber(String externalRegistrationNumber) {
		this.externalRegistrationNumber = externalRegistrationNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getLocalReferenceNumber() {
		return localReferenceNumber;
	}

	public void setLocalReferenceNumber(String localReferenceNumber) {
		this.localReferenceNumber = localReferenceNumber;
	}

	public String getItemsQuantity() {
		return itemsQuantity;
	}

	public void setItemsQuantity(String itemsQuantity) {
		this.itemsQuantity = itemsQuantity;
	}

	public String getPackagesQuantity() {
		return packagesQuantity;
	}

	public void setPackagesQuantity(String packagesQuantity) {
		this.packagesQuantity = packagesQuantity;
	}

	public String getAuthorisationID() {
		return authorisationID;
	}

	public void setAuthorisationID(String authorisationID) {
		this.authorisationID = authorisationID;
	}

	public String getParticipantsCombination() {
		return participantsCombination;
	}

	public void setParticipantsCombination(String participantsCombination) {
		this.participantsCombination = participantsCombination;
	}

	public TypeOfDeclaration getTypeOfDeclaration() {
		return typeOfDeclaration;
	}

	public void setTypeOfDeclaration(TypeOfDeclaration typeOfDeclaration) {
		this.typeOfDeclaration = typeOfDeclaration;
	}

	public Trader getConsignee() {
		return consignee;
	}

	public void setConsignee(Trader consignee) {
		this.consignee = consignee;
	}

	public Trader getConsignor() {
		return consignor;
	}

	public void setConsignor(Trader consignor) {
		this.consignor = consignor;
	}

	public Trader getDeclarant() {
		return declarant;
	}

	public void setDeclarant(Trader declarant) {
		this.declarant = declarant;
	}

	public Trader getDeclarantRepresentative() {
		return declarantRepresentative;
	}

	public void setDeclarantRepresentative(Trader declarantRepresentative) {
		this.declarantRepresentative = declarantRepresentative;
	}

	public Trader getExporter() {
		return exporter;
	}

	public void setExporter(Trader exporter) {
		this.exporter = exporter;
	}

	public Trader getIssuer() {
		return issuer;
	}

	public void setIssuer(Trader issuer) {
		this.issuer = issuer;
	}

	public Trader getRecipient() {
		return recipient;
	}

	public void setRecipient(Trader recipient) {
		this.recipient = recipient;
	}

	public Trader getShipper() {
		return shipper;
	}

	public void setShipper(Trader shipper) {
		this.shipper = shipper;
	}

	public Trader getSubcontractor() {
		return subcontractor;
	}

	public void setSubcontractor(Trader subcontractor) {
		this.subcontractor = subcontractor;
	}

	public Trader getSubcontractorRepresentative() {
		return subcontractorRepresentative;
	}

	public void setSubcontractorRepresentative(Trader subcontractorRepresentative) {
		this.subcontractorRepresentative = subcontractorRepresentative;
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
	
	public PDFInformation getPDFInformation() {                              //05.12.08
		return pDFInformation;
	}

	public void setPDFInformation(PDFInformation argument) {                //05.12.08
		this.pDFInformation = argument;
	}
	
	public CustomsOffices getCustomsOffices() {
		return customsOffices;
	}

	public void setCustomsOffices(CustomsOffices argument) {
		this.customsOffices = argument;
	}

	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business argument) {
		this.business = argument;
	}
	public Seal getSeal() {
		return seal;
	}

	public void setSeal(Seal argument) {
		this.seal = argument;
	}

	public LoadingTime getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(LoadingTime argument) {
		this.loadingTime = argument;
	}
	public void setDateOfLoadingBegin(String argument) {
		this.dateOfLoadingBegin = argument;
	}
	public String getDateOfLoadingBegin() {
		return dateOfLoadingBegin;
	}
	public void setDateOfLoadingEnd(String argument) {
		this.dateOfLoadingEnd = argument;
	}
	public String getDateOfLoadingEnd() {
		return dateOfLoadingEnd;
	}

	public List <MsgUidsPos>getGoodsItemList() {
		return goodsItemList;
	}

    public void setGoodsItemList(List <MsgUidsPos>argument) {
    	this.goodsItemList = argument;  	
    }

	public XMLEventReader getParser() {
		return parser;
	}
	 public void addGoodsItemList(MsgUidsPos argument) {
	    	this.goodsItemList.add(argument);   	
	    }

	
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getDateOfRejection() {
		return dateOfRejection;
	}

	public void setDateOfRejection(String dateOfRejection) {
		this.dateOfRejection = dateOfRejection;
	}

	public MsgUidsPos getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(MsgUidsPos goodsItem) {
		this.goodsItem = goodsItem;
	}

	public String getDateOfProcessing() {
		return dateOfProcessing;
	}

	public void setDateOfProcessing(String dateOfProcessing) {
		this.dateOfProcessing = dateOfProcessing;
	}

	public ApprovedTreatment getApprovedTreatment() {
		return approvedTreatment;
	}

	public void setApprovedTreatment(ApprovedTreatment argument) {
		this.approvedTreatment = argument;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public IncoTerms getIncoTerms() {
		return incoTerms;
	}

	public void setIncoTerms(IncoTerms argument) {
		this.incoTerms = argument;
	}
	
	// C. Kron benötigt f. die Schweiz
	public PreviousDocument getPreviousDocument() {
		return previousDocument;
	}

	public void setPreviousDocument(PreviousDocument previousDocument) {
		this.previousDocument = previousDocument;
	}

	public String getDateOfTermination() {
		return dateOfTermination;
	}

	public void setDateOfTermination(String dateOfTermination) {
		this.dateOfTermination = dateOfTermination;
	}

	public String getDateOfControl() {
		return dateOfControl;
	}

	public void setDateOfControl(String dateOfControl) {
		this.dateOfControl = dateOfControl;
	}
	
	public String getDateOfPreAdvice() {
		return dateOfPreAdvice;
	}

	public void setDateOfPreAdvice(String argument) {
		this.dateOfPreAdvice = argument;
	}

	public String getTypeOfControl() {
		return typeOfControl;
	}

	public void setTypeOfControl(String typeOfControl) {
		this.typeOfControl = typeOfControl;
	}

	public HeaderExtensions getHeaderExtensions() {
		return headerExtensions;
	}

	public void setHeaderExtensions(HeaderExtensions headerExtensions) {
		this.headerExtensions = headerExtensions;
	}

	public String getTermination() {
		return termination;
	}

	public void setTermination(String termination) {
		this.termination = termination;
	}

	public String getTypeOfShipment() {
		return typeOfShipment;
	}

	public void setTypeOfShipment(String typeOfShipment) {
		this.typeOfShipment = typeOfShipment;
	}
	
	public ErrorType getError() {
		return error;
	}
	public void setError(ErrorType argument) {
		this.error = argument;
	}
	public List getErrorList() {
		return errorList;
	}
	public void addErrorList(ErrorType argument) {
		this.errorList.add(argument);
	}	
	
	public void setApplicationReference(String argument) {
		this.applicationReference = argument;
	}
	
	public String getApplicationReference() {
		return applicationReference;
	}
	
	public void setRegistrationNumber(String argument) {
		this.registrationNumber = argument;
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getCommercialReferenceNumber() {
		return commercialReferenceNumber;
	
	}

	public void setCommercialReferenceNumber(String commercialReferenceNumber) {
		this.commercialReferenceNumber = Utils.checkNull(commercialReferenceNumber);
	}

	public String getAlternateProofIndicator() {
		return alternateProofIndicator;	
	}

	public void setAlternateProofIndicator(String argument) {
		this.alternateProofIndicator = Utils.checkNull(argument);
	}
	
	public String getSpecCircumstance() {
		return specCircumstance;	
	}

	public void setSpecCircumstance(String argument) {
		this.specCircumstance = Utils.checkNull(argument);
		
	}
	
	public String getTransportPaymentMethod() {
			return transportPaymentMethod;	
	}

	public void setTransportPaymentMethod(String argument) {
		this.transportPaymentMethod = Utils.checkNull(argument);
		
	}
	public ExportRefundHeader getExportRefundHeader() {
		return 	exportRefundHeader;
	}
	public void setExportRefundHeader(ExportRefundHeader argument) {
		this.exportRefundHeader = argument;
	}
	public Route getRoute() {
		return 	route;
	}
	public void setRoute(Route argument) {
		this.route = argument;
	}	
}



