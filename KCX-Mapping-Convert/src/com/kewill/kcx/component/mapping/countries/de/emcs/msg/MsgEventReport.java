package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EvidenceOfEvent;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.SubmittingPerson;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.TransportDetails;

/**
 * Module		: EMCS<br>
 * Created		: 20.07.2011<br>
 * Description  : Contains Message Structure with fields used in MsgEventReport 
 *              : IE840 / C_EVT_DAT.  
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgEventReport extends KCXMessage {

	private String  				referenceNumber;
	private String  				customerOrderNumber;
	private String  				clerk;
	private String  				aadReferenceCode;
	private String  				sequenceNumber;
	private String  				eventReportMessageType;	
	private String					dateAndTimeOfValidation; 
	private String                  exciseCustomsOffice;
	private String                  memberStateCode;
	private String                  eventReportNumber;
	private String                  submissionEventReportReference;
	private String                  changedTransportArrangement;
	private String                  dateOfEvent;  
	private Text                    placeOfEvent;
	private Text                    comments;
	private String                  customsOfficerID;
	private SubmittingPerson        submittingPerson;
	private EmcsTrader              newTransportArranger;	
	private EmcsTrader              newTransporter;	
	//private EvidenceOfEvent			evidenceOfEvent;	
	private List <EvidenceOfEvent> evidenceOfEventList;
	//private TransportDetails		transportDetails;	
	private List <TransportDetails> transportDetailsList;
	private PDFInformation  		pdfInformation;
	private List <MsgEventReportPos> goodsItemList;
 
	private enum EEventReport {
		//KIDS:									UIDS:
	   	ReferenceNumber,							LocalReferenceNumber,
    	CustomerOrderNumber,						//missing in UIDS						
    	Clerk,										//missing in UIDS
    	AadReferenceCode,							//same 
    	SequenceNumber,                             //same           		
    	EventReportMessageType,	                    //same  
    	DateAndTimeOfValidation,                    //same  
    	ExciseCustomsOffice,                        //same  
    	MemberStateCode,		                    //same		
    	EventReportNumber,                          //same	
    	SubmissionEventReportReference,             //same  
    	ChangedTransportArrangement,                //same  
    	DateOfEvent,                                //same  
    	PlaceOfEvent,                               //same  
    	Comments,                                   //same  
    	CustomsOfficerID,                           IdentificationOfSenderCustomsOfficer,
    	SubmittingPerson,                           //same  
    	NewTransportArranger,                       NewTransportArrangerTrader,
    	NewTransporter,                             NewTransporterTrader,
    	EvidenceOfEvent,                            //same					 
    	TransportDetails,                           //same	    	 
    	GoodsItem,                                  //same	     
        PDFInformation,                             PDF;
	}
	
	public MsgEventReport() {
		super();
	}

	public MsgEventReport(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EEventReport) tag) {
			case NewTransportArranger:	
			case NewTransportArrangerTrader:
				newTransportArranger = new EmcsTrader(getScanner());
				newTransportArranger.parse(tag.name());				
				break;	
			case NewTransporter:	
			case NewTransporterTrader:
				newTransporter = new EmcsTrader(getScanner());
				newTransporter.parse(tag.name());				
				break;	
			case SubmittingPerson:
				submittingPerson = new SubmittingPerson(getScanner());
				submittingPerson.parse(tag.name());				
				break;
			case EvidenceOfEvent:
				EvidenceOfEvent evidenceOfEvent = new EvidenceOfEvent(getScanner());
				evidenceOfEvent.parse(tag.name());
				addEvidenceOfEventList(evidenceOfEvent);
				break;	
			case TransportDetails:
				TransportDetails transportDetails = new TransportDetails(getScanner());
				transportDetails.parse(tag.name());	
				addTransportDetailsList(transportDetails);
				break;	
			case PDFInformation:
			case PDF:
				pdfInformation = new PDFInformation(getScanner());
				pdfInformation.parse(tag.name());				
				break;	
			case GoodsItem:				
				MsgEventReportPos item = new MsgEventReportPos(getScanner());
				item.parse(tag.name());	
				addGoodsItemList(item);
				break;	
			default:
				return;
			}
	    } else {
	    	switch ((EEventReport) tag) {
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
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;				
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;				
			case EventReportMessageType:
				 setEventReportMessageType(value);
				 break;		
			case DateAndTimeOfValidation:
				 setDateAndTimeOfValidation(value);
				 break;	
			case ExciseCustomsOffice:
				 setExciseCustomsOffice(value);
				 break;
			case MemberStateCode:
				 setMemberStateCode(value);
				 break;				
			case EventReportNumber:
				 setEventReportNumber(value);
				 break;		
			case SubmissionEventReportReference:
				 setSubmissionEventReportReference(value);
				 break;	
			case ChangedTransportArrangement:
				 setChangedTransportArrangement(value);
				 break;	
			case DateOfEvent:
				 setDateOfEvent(value);
				 break;	
			case PlaceOfEvent:
				 //placeOfEvent = new Text(value, attr.getValue("language"));
				 placeOfEvent = new Text(value, attr);  //EI20110926
				 break;	
			case Comments:
				 //comments = new Text(value, attr.getValue("language"));
				 comments = new Text(value, attr);  //EI20110926
				 break;					 				 
			case CustomsOfficerID:
			case IdentificationOfSenderCustomsOfficer:
				 setCustomsOfficerID(value);
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
  			return EEventReport.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}	
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getCustomerOrderNumber() {
		return this.customerOrderNumber;
	}
	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}	

	public String getClerk() {
		return this.clerk;
	}
	public void setClerk(String argument) {
		this.clerk = argument;
	}
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}	
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getEventReportMessageType() {
		return this.eventReportMessageType;
	}
	public void setEventReportMessageType(String argument) {
		this.eventReportMessageType = argument;
	}
	
	public String getDateAndTimeOfValidation() {
		return this.dateAndTimeOfValidation;
	}
	public void setDateAndTimeOfValidation(String argument) {
		this.dateAndTimeOfValidation = argument;
	}	
		 
	public String getExciseCustomsOffice() {
		return this.exciseCustomsOffice;
	}
	public void setExciseCustomsOffice(String argument) {
		this.exciseCustomsOffice = argument;
	}	

	public String getMemberStateCode() {
		return this.memberStateCode;
	}
	public void setMemberStateCode(String argument) {
		this.memberStateCode = argument;
	}
	public String getEventReportNumber() {
		return this.eventReportNumber;
	}
	public void setEventReportNumber(String argument) {
		this.eventReportNumber = argument;
	}
	public String getSubmissionEventReportReference() {
		return this.submissionEventReportReference;
	}
	public void setSubmissionEventReportReference(String argument) {
		this.submissionEventReportReference = argument;
	}
	public String getChangedTransportArrangement() {
		return this.changedTransportArrangement;
	}
	public void setChangedTransportArrangement(String argument) {
		this.changedTransportArrangement = argument;
	}
	public String getDateOfEvent() {
		return this.dateOfEvent;
	}
	public void setDateOfEvent(String argument) {
		this.dateOfEvent = argument;
	}
	
	public Text getPlaceOfEvent() {
		return this.placeOfEvent;
	}
	public void setPlaceOfEvent(Text argument) {
		this.placeOfEvent = argument;
	}
	
	public Text getComments() {
		return this.comments;
	}
	public void setComments(Text argument) {
		this.comments = argument;
	}
	
	public String getCustomsOfficerID() {
		return this.customsOfficerID;
	}
	public void setCustomsOfficerID(String argument) {
		this.customsOfficerID = argument;
	}
	
	public SubmittingPerson getSubmittingPerson() {
		return this.submittingPerson;
	}
	public void setSubmittingPerson(SubmittingPerson argument) {
		this.submittingPerson = argument;
	}
	
	public EmcsTrader getNewTransportArranger() {
		return this.newTransportArranger;
	}
	public void setNewTransportArranger(EmcsTrader argument) {
		this.newTransportArranger = argument;
	}	
	
	public EmcsTrader getNewTransporter() {
		return this.newTransporter;
	}
	public void setNewTransporter(EmcsTrader argument) {
		this.newTransporter = argument;
	}
	public List<EvidenceOfEvent> getEvidenceOfEventList() {
		return this.evidenceOfEventList;
	}
	public void setEvidenceOfEventList(List<EvidenceOfEvent> list) {
		this.evidenceOfEventList = list;
	}
	public void addEvidenceOfEventList(EvidenceOfEvent event) {
		if (evidenceOfEventList == null) {
			evidenceOfEventList =  new Vector <EvidenceOfEvent>();	
		}
		this.evidenceOfEventList.add(event);	
	}
	
	public List<TransportDetails> getTransportDetailsList() {
		return this.transportDetailsList;
	}
	public void setTransportDetails(List<TransportDetails> list) {
		this.transportDetailsList = list;
	}
	public void addTransportDetailsList(TransportDetails detail) {
		if (transportDetailsList == null) {
			transportDetailsList =  new Vector <TransportDetails>();	
		}
		this.transportDetailsList.add(detail);	
	}
	
	public PDFInformation getPDFInformation() {
		return this.pdfInformation;
	}
	public void setPDFInformation(PDFInformation argument) {
		this.pdfInformation = argument;
	}	
	
	public List<MsgEventReportPos> getGoodsItemList() {
		return goodsItemList;	
	}
	public void setGoodsItemList(List<MsgEventReportPos> list) {
		this.goodsItemList = list;	
	}	
	public void addGoodsItemList(MsgEventReportPos item) {
		if (goodsItemList == null) {
			goodsItemList =  new Vector <MsgEventReportPos>();	
		}
		this.goodsItemList.add(item);	
	}
	
}
