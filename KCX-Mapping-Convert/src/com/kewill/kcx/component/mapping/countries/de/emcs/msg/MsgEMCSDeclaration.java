package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

/**
Changes: new for V20: ComplementaryInformation
*/

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AttributesType;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ComplementConsignee;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.DocumentCertificate;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EaadDraft;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.HeaderEaadDraft;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.MovementGuarantee;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.TransportDetails;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module	    : EMCS<br>
 * Created		: 30.04.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSDeclaration. 
 *              : IE815 / N_EAD_SUB.
 *                 
 * @author krzoska
 * @version 1.0.00
 */
public class MsgEMCSDeclaration extends KCXMessage {

	private String  				referenceNumber;
	private String  				customerOrderNumber;
	private String  				clerk;
	private String  				deferredSubmissionFlag;
	private String  				submissionMessageType;
	private String 					dateAndTimeOfIssuanceOfReminder;
	private String 					limitDateAndTime;
	private Text					reminderInformation;
	private Text					complementaryInformation;   //EI20110705
	private String 					reminderMessageType;
	private String 					dateAndTimeOfValidationOfCancellation;
	private String  				dispatchImportOffice;
	private String  				deliveryPlaceCustomsOffice;
	private String  				competentAuthorityDispatchOffice;
	private String  				journeyTime;
	private String  				destinationTypeCode;
	private String  				transportArrangement;
	private String  				dateOfDispatch;
	private String  				timeOfDispatch;
	private String  				originTypeCode;
	private String  				invoiceNumber;
	private String  				invoiceDate; 
	private List <String>			importSadList = null;
	private String  				transportModeCode;
	private String                  previousProcedure;  //EI20100706 not defined in UIDS
	
	private String  				guarantorTypeCode;
	private List <EmcsTrader>			guarantorList = null;
	
	private List <DocumentCertificate> 	documentCertificateList;
	private EmcsTrader  				consignee;
	private ComplementConsignee 	complementConsignee;
	private EmcsTrader  				consignor;
	private EmcsTrader 					placeOfDispatch; 
	private EmcsTrader 					deliveryPlace;
	private EmcsTrader 					transportArranger;
	private EmcsTrader 					firstTransporter;
	
	//private TransportDetails 		transportDetails;
	private List <TransportDetails> transportDetailsList;
    private MsgEMCSDeclarationPos 		goodsItem;
    private List <MsgEMCSDeclarationPos>goodsItemList;
    
    public MsgEMCSDeclaration()  {
		super();
		goodsItemList =  new Vector <MsgEMCSDeclarationPos>();
	}

	public MsgEMCSDeclaration(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		goodsItemList =  new Vector <MsgEMCSDeclarationPos>();
	}

    private enum EMsgEMCSDeclaration {
    	
    //KIDS                                         //UIDS
    	ReferenceNumber,							LocalReferenceNumber,
    	CustomerOrderNumber,						//missing in UIDS						
    	Clerk,										//missing in UIDS
    	DeferredSubmissionFlag,						Attributes,		//Attributes.DeferredSubmissionFlag
    	SubmissionMessageType,                        				//Attributes.SubmissionMessageType
    		DateAndTimeOfIssuanceOfReminder,							//Attributes.DateAndTimeOfIssuanceOfReminder
    		LimitDateAndTime,											//Attributes.LimitDateAndTime
    		ReminderInformation,										//Attributes.ReminderInformation
    		ReminderMessageType,										//Attributes.ReminderMessageType
    	DispatchImportOffice,						//same
    	DeliveryPlaceCustomsOffice,					//same
    	CompetentAuthorityDispatchOffice,			//same
    	JourneyTime,								HeaderEaad,  	//HeaderEaad.JourneyTime 
    	DestinationTypeCode,										//HeaderEaad.DestinationTypeCode 
    	TransportArrangement,										//HeaderEaad.TransportArrangement
    	DateOfDispatch,								EaadDraft,      //EaadDraft.DateOfDispatch
    	TimeOfDispatch,												//EaadDraft.TimeOfDispatch
    	OriginTypeCode,												//EaadDraft.OriginTypeCode
    	InvoiceNumber,												//EaadDraft.InvoiceNumber
    	InvoiceDate, 												//EaadDraft.InvoiceDate
    	ImportSad,													//EaadDraft.ImportSad
    	TransportModeCode,							Transport,		//Transport.TransportModeCode
    	ComplementaryInformation,                   //same
    	GuarantorTypeCode,							MovementGuarantee, //MovementGuarantee.GuarantorTypeCode
    	Guarantor,									                   //MovementGuarantee.GuarantorTrader
    	DocumentCertificate,						//same
    	Consignee,									ConsigneeTrader,
    	ComplementConsignee,						ComplementConsigneeTrader,
    	Consignor,									ConsignorTrader,
    	PlaceOfDispatch,							PlaceOfDispatchTrader, 
    	DeliveryPlace,								DeliveryPlaceTrader,
    	TransportArranger,							TransportArrangerTrader,
    	FirstTransporter,							FirstTransporterTrader,
    	TransportDetails,							//same
    	PreviousProcedure,  //EI20100706
    	GoodsItem,									BodyEaad;
    }
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EMsgEMCSDeclaration) tag) {
			case Attributes:
				AttributesType attribute  = new AttributesType(getScanner());
				attribute.parse(tag.name());
				setAttributes(attribute);
				break;
			case EaadDraft:
				EaadDraft eaadDraft  = new EaadDraft(getScanner());
				eaadDraft.parse(tag.name());
				setEaadDraft(eaadDraft);						
				break;
			case HeaderEaad:
				HeaderEaadDraft headerEaad = new HeaderEaadDraft(getScanner());
				headerEaad.parse(tag.name());
				setHeaderEaadDraft(headerEaad);				
				break;
			case MovementGuarantee:
				MovementGuarantee movementGuarantee = new MovementGuarantee(getScanner());
				movementGuarantee.parse(tag.name());
				setMovementGuarantee(movementGuarantee);
				break;
			case Guarantor:
				EmcsTrader guarantor = new EmcsTrader(getScanner());
				guarantor.parse(tag.name());
				if (guarantorList == null) {
					guarantorList = new Vector<EmcsTrader>();
				}
				guarantorList.add(guarantor);
				break;
			case Transport:
				Transport transport = new Transport(getScanner());
				transport.parse(tag.name());
				this.transportModeCode = transport.getMode();
				this.complementaryInformation = transport.getComplementaryInformation(); //EI20110928
				break;
			case DocumentCertificate:
				DocumentCertificate documentCertificate = new DocumentCertificate(getScanner());
				documentCertificate.parse(tag.name());
				if (documentCertificateList == null) {
					documentCertificateList = new Vector<DocumentCertificate>();
				}
				documentCertificateList.add(documentCertificate);
				break;
			case Consignee:
			case ConsigneeTrader:
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());
				break;
			case ComplementConsignee:
			case ComplementConsigneeTrader:
				complementConsignee = new ComplementConsignee(getScanner());
				complementConsignee.parse(tag.name());
				break;
			case Consignor:
			case ConsignorTrader:
				consignor = new EmcsTrader(getScanner());
				consignor.parse(tag.name());
				break;
			case PlaceOfDispatch:
			case PlaceOfDispatchTrader:
				placeOfDispatch = new EmcsTrader(getScanner());
				placeOfDispatch.parse(tag.name());
				break;
			case DeliveryPlace:
			case DeliveryPlaceTrader:
				deliveryPlace = new EmcsTrader(getScanner());
				deliveryPlace.parse(tag.name());
				break;
			case TransportArranger:
			case TransportArrangerTrader:
				transportArranger = new EmcsTrader(getScanner());
				transportArranger.parse(tag.name());
				break;
			case FirstTransporter:
			case FirstTransporterTrader:
				firstTransporter = new EmcsTrader(getScanner());
				firstTransporter.parse(tag.name());
				break;
			case TransportDetails:
				TransportDetails transportDetails = new TransportDetails(getScanner());
				transportDetails.parse(tag.name());
				if (transportDetailsList == null) {
					transportDetailsList = new Vector <TransportDetails>();
				}
				transportDetailsList.add(transportDetails);
				break;

			case GoodsItem:
			case BodyEaad:
				goodsItem = new MsgEMCSDeclarationPos(getScanner());
				goodsItem.parse(tag.name());
				if (goodsItemList == null) {
					goodsItemList = new Vector <MsgEMCSDeclarationPos>();
				}
				goodsItemList.add(goodsItem);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EMsgEMCSDeclaration) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;				
			case Clerk:
				 setClerk(value);
				 break;				
			case DeferredSubmissionFlag:
				 setDeferredSubmissionFlag(value);
				 break;				
			case SubmissionMessageType:
				 setSubmissionMessageType(value);
				 break;				
			case DispatchImportOffice:
				 setDispatchImportOffice(value);
				 break;				
			case DeliveryPlaceCustomsOffice:
				 setDeliveryPlaceCustomsOffice(value);
				 break;				
			case CompetentAuthorityDispatchOffice:
				 setCompetentAuthorityDispatchOffice(value);
				 break;				
			case JourneyTime:
				 setJourneyTime(value);
				 break;				
			case DestinationTypeCode:
				 setDestinationTypeCode(value);
				 break;				
			case TransportArrangement:
				 setTransportArrangement(value);
				 break;				
			case DateOfDispatch:
				 setDateOfDispatch(value);
				 break;				
			case TimeOfDispatch:
				 setTimeOfDispatch(value);
				 break;				
			case OriginTypeCode:
				 setOriginTypeCode(value);
				 break;				
			case InvoiceNumber:
				 setInvoiceNumber(value);
				 break;				
			case InvoiceDate:
				 setInvoiceDate(value);
				 break;				
			case ImportSad:
				 if (importSadList == null) {
					 importSadList = new Vector <String>();
				 }
				 importSadList.add(value);
				 break;				
			case TransportModeCode:
				 setTransportModeCode(value);
				 break;				
			case GuarantorTypeCode:
				 setGuarantorTypeCode(value);
				 break;
			case ReminderInformation:
				//reminderInformation = new Text(value, attr.getValue("language"));
				reminderInformation = new Text(value, attr);  //EI20110926
				break;	
			case PreviousProcedure:
				setPreviousProcedure(value);
				break;	
			case ComplementaryInformation:
				 //complementaryInformation = new Text(value, attr.getValue("language"));
				 complementaryInformation = new Text(value, attr);  //EI20110926
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
			return EMsgEMCSDeclaration.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getReferenceNumber() {
		return referenceNumber;	
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = Utils.checkNull(referenceNumber);
	}

	public String getCustomerOrderNumber() {
		return customerOrderNumber;	
	}

	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = Utils.checkNull(customerOrderNumber);
	}

	public String getClerk() {
		return clerk;	
	}

	public void setClerk(String clerk) {
		this.clerk = Utils.checkNull(clerk);
	}

	public String getDeferredSubmissionFlag() {
		return deferredSubmissionFlag;	
	}

	public void setDeferredSubmissionFlag(String deferredSubmissionFlag) {
		this.deferredSubmissionFlag = Utils.checkNull(deferredSubmissionFlag);
	}

	public String getSubmissionMessageType() {
		return submissionMessageType;	
	}

	public void setSubmissionMessageType(String submissionMessageType) {
		this.submissionMessageType = Utils.checkNull(submissionMessageType);
	}

	public String getDispatchImportOffice() {
		return dispatchImportOffice;	
	}

	public void setDispatchImportOffice(String dispatchImportOffice) {
		this.dispatchImportOffice = Utils.checkNull(dispatchImportOffice);
	}

	public String getDeliveryPlaceCustomsOffice() {
		return deliveryPlaceCustomsOffice;	
	}

	public void setDeliveryPlaceCustomsOffice(String deliveryPlaceCustomsOffice) {
		this.deliveryPlaceCustomsOffice = Utils
				.checkNull(deliveryPlaceCustomsOffice);
	}

	public String getCompetentAuthorityDispatchOffice() {
		return competentAuthorityDispatchOffice;	
	}

	public void setCompetentAuthorityDispatchOffice(
			String competentAuthorityDispatchOffice) {
		this.competentAuthorityDispatchOffice = Utils
				.checkNull(competentAuthorityDispatchOffice);
	}

	public String getJourneyTime() {
		return journeyTime;	
	}

	public void setJourneyTime(String journeyTime) {
		this.journeyTime = Utils.checkNull(journeyTime);
	}

	public String getDestinationTypeCode() {
		return destinationTypeCode;	
	}

	public void setDestinationTypeCode(String destinationTypeCode) {
		this.destinationTypeCode = Utils.checkNull(destinationTypeCode);
	}

	public String getTransportArrangement() {
		return transportArrangement;
	
	}

	public void setTransportArrangement(String transportArrangement) {
		this.transportArrangement = Utils.checkNull(transportArrangement);
	}

	public String getDateOfDispatch() {
		return dateOfDispatch;	
	}

	public void setDateOfDispatch(String dateOfDispatch) {
		this.dateOfDispatch = Utils.checkNull(dateOfDispatch);
	}

	public String getTimeOfDispatch() {
		return timeOfDispatch;	
	}

	public void setTimeOfDispatch(String timeOfDispatch) {
		this.timeOfDispatch = Utils.checkNull(timeOfDispatch);
	}

	public String getOriginTypeCode() {
		return originTypeCode;	
	}

	public void setOriginTypeCode(String originTypeCode) {
		this.originTypeCode = Utils.checkNull(originTypeCode);
	}

	public String getInvoiceNumber() {
		return invoiceNumber;	
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = Utils.checkNull(invoiceNumber);
	}

	public String getInvoiceDate() {
		return invoiceDate;	
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = Utils.checkNull(invoiceDate);
	}

	public String getTransportModeCode() {
		return transportModeCode;	
	}

	public void setTransportModeCode(String transportModeCode) {
		this.transportModeCode = Utils.checkNull(transportModeCode);
	}

	public String getGuarantorTypeCode() {
		return guarantorTypeCode;	
	}

	public void setGuarantorTypeCode(String guarantorTypeCode) {
		this.guarantorTypeCode = Utils.checkNull(guarantorTypeCode);
	}

	public EmcsTrader getConsignee() {
		return consignee;	
	}

	public void setConsignee(EmcsTrader consignee) {
		this.consignee = consignee;
	}

	public ComplementConsignee getComplementConsignee() {
		return complementConsignee;	
	}

	public void setComplementConsignee(ComplementConsignee complementConsignee) {
		this.complementConsignee = complementConsignee;
	}

	public EmcsTrader getConsignor() {
		return consignor;	
	}

	public void setConsignor(EmcsTrader consignor) {
		this.consignor = consignor;
	}

	public EmcsTrader getPlaceOfDispatch() {
		return placeOfDispatch;
	
	}

	public void setPlaceOfDispatch(EmcsTrader placeOfDispatch) {
		this.placeOfDispatch = placeOfDispatch;
	}

	public EmcsTrader getDeliveryPlace() {
		return deliveryPlace;	
	}

	public void setDeliveryPlace(EmcsTrader deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

	public EmcsTrader getTransportArranger() {
		return transportArranger;	
	}

	public void setTransportArranger(EmcsTrader transportArranger) {
		this.transportArranger = transportArranger;
	}

	public EmcsTrader getFirstTransporter() {
		return firstTransporter;	
	}

	public void setFirstTransporter(EmcsTrader firstTransporter) {
		this.firstTransporter = firstTransporter;
	}	

	public MsgEMCSDeclarationPos getGoodsItem() {
		return goodsItem;	
	}

	public void setGoodsItem(MsgEMCSDeclarationPos goodsItem) {
		this.goodsItem = goodsItem;
	}

	public List<String> getImportSadList() {
		return importSadList;
	}

	public List<MsgEMCSDeclarationPos> getGoodsItemList() {
		return goodsItemList;	
	}

	public void setGuarantorList(List<EmcsTrader> list) {
		this.guarantorList = list;	
	}
	public List<EmcsTrader> getGuarantorList() {
		return guarantorList;	
	}	

	public void setDocumentCertificateList(List<DocumentCertificate> list) {
		this.documentCertificateList = list;	
	}
	public List<DocumentCertificate> getDocumentCertificateList() {
		return documentCertificateList;	
	}
	
	public List<TransportDetails> getTransportDetailsList() {
		return transportDetailsList;	
	}
	public void setTransportDetailsList(List<TransportDetails> list) {
		this.transportDetailsList = list;	
	}
	
	public void setEaadDraft(EaadDraft argument) {
		this.dateOfDispatch = argument.getDateOfDispatch();
		this.timeOfDispatch = argument.getTimeOfDispatch();
		this.originTypeCode = argument.getOriginTypeCode();
		this.invoiceNumber = argument.getInvoiceNumber();
		this.invoiceDate = argument.getInvoiceDate();
		this.importSadList = argument.getImportSadList();	
	}
	
	public void setHeaderEaadDraft(HeaderEaadDraft argument) {
		this.journeyTime = argument.getJourneyTime();
		this.destinationTypeCode = argument.getDestinationTypeCode();
		this.transportArrangement = argument.getTransportArrangement();		
	}
	
	private void setAttributes(AttributesType argument) {
		this.submissionMessageType = argument.getSubmissionMessageType();
		this.dateAndTimeOfIssuanceOfReminder = argument.getDateAndTimeOfIssuance();     	        
		this.reminderInformation = argument.getReminderInformation();	
		this.limitDateAndTime = argument.getLimitDateAndTime();	   		      
		this.reminderMessageType = argument.getReminderMessageType();				      
		this.dateAndTimeOfValidationOfCancellation = argument.getDateAndTimeOfValidationOfCancellation();	
		this.deferredSubmissionFlag = argument.getDeferredSubmissionFlag();								
	}

	private void setMovementGuarantee(MovementGuarantee argument) {
		this.guarantorTypeCode = argument.getGuarantorTypeCode();
		this.guarantorList = argument.getGuarantorList();
	}
	
	public String getDateAndTimeOfIssuanceOfReminder() {
		return dateAndTimeOfIssuanceOfReminder;	
	}

	public void setDateAndTimeOfIssuanceOfReminder(
			String dateAndTimeOfIssuanceOfReminder) {
		this.dateAndTimeOfIssuanceOfReminder = Utils
				.checkNull(dateAndTimeOfIssuanceOfReminder);
	}

	public String getLimitDateAndTime() {
		return limitDateAndTime;	
	}

	public void setLimitDateAndTime(String limitDateAndTime) {
		this.limitDateAndTime = Utils.checkNull(limitDateAndTime);
	}

	public Text getReminderInformation() {
		return reminderInformation;	
	}

	public void setReminderInformation(Text reminderInformation) {
		this.reminderInformation = reminderInformation;
	}

	public String getReminderMessageType() {
		return reminderMessageType;
	
	}

	public void setReminderMessageType(String reminderMessageType) {
		this.reminderMessageType = Utils.checkNull(reminderMessageType);
	}

	public String getDateAndTimeOfValidationOfCancellation() {
		return dateAndTimeOfValidationOfCancellation;	
	}

	public void setDateAndTimeOfValidationOfCancellation(
			String dateAndTimeOfValidationOfCancellation) {
		this.dateAndTimeOfValidationOfCancellation = Utils
				.checkNull(dateAndTimeOfValidationOfCancellation);
	}
	
	public String getPreviousProcedure() {
		return previousProcedure;	
	}

	public void setPreviousProcedure(String argument) {
		this.previousProcedure = argument;
	}	

	public Text getComplementaryInformation() {
		return complementaryInformation;	
	}
	public void setComplementaryInformation(Text complementaryInformation) {
		this.complementaryInformation = complementaryInformation;
	}
}
