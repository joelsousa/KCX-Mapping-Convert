package com.kewill.kcx.component.mapping.countries.de.ics20.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: Contains Message Structure with fields used in ICSArrivalItemRejection.
 * 				: (IE349)
 * 				: new for V20 only UIDS: TransportAtBorder, EntryCarrier
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class MsgArrivalItemRejection extends KCXMessage {
	
	private String  msgName = "ICSArrivalItemRejection";
	private String  msgType = "";
	
	private String  referenceNumber;
	private String  rejectionDateAndTime;	
	private String  rejectionReasonHeader;
	private String  rejectionReasonHeaderLNG;
	private String  declaredDateOfArrival;	
	private TransportMeans meansOfTransportBorder;		
	private String  conveyanceReference;
	private Party   carrier;
	private TIN     carrierTIN;	
	private List<ImportDetails> importDetailsList;
	private List<FunctionalError> functionalErrorList;
	
	private EFormat declaredDateOfArrivalFormat;
	private EFormat rejectionDateAndTimeFormat;
	
	
	public MsgArrivalItemRejection() {
		super();				
	}

	public MsgArrivalItemRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	public MsgArrivalItemRejection(XMLEventReader parser, String type) throws XMLStreamException {
		super(parser);	
		msgType = type;
	}
	
	private enum EMsgArrivalItemRejection {
		//KIDS						//UIDS
		ReferenceNumber,			LocalReferenceNumber,		
		RejectionDateTime,       	RejectionDateAndTime, //EI20110208: ArrivalDateAndTime,
		RejectionReasonHeader,		Reason, RejectionReason,
		RejectionReasonHeaderLNG,	//none		
		DeclaredDateOfArrival, 		ExpectedArrivalDateAndTime, 
		ConveyanceReference,		ConveyanceNumber, 
		MeansOfTransportBorder,		TransportAtBorder,
		ImportDetails,				MRNDetails,
		CarrierTIN,					EntryCarrier,
		CarrierAddress,					
		FunctionalError, 			Error;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgArrivalItemRejection) tag) {
			case ImportDetails:
			case MRNDetails:
				ImportDetails tempImportDetails = new ImportDetails(getScanner());
				tempImportDetails.parse(tag.name());
				addImportDetailsList(tempImportDetails);
				break;
			case MeansOfTransportBorder:
			case TransportAtBorder:
				meansOfTransportBorder = new TransportMeans(getScanner(), msgType);
				meansOfTransportBorder.parse(tag.name());
				break;
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());
				break;
			case CarrierAddress:
			case EntryCarrier:
				carrier = new Party(getScanner());
				carrier.parse(tag.name());
				break;			
			case FunctionalError:
			case Error:
				FunctionalError tempError = new FunctionalError(getScanner());
				tempError.parse(tag.name());
				addErrorList(tempError);
				break;
			default:
				return;
			}
		} else {
			switch ((EMsgArrivalItemRejection) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			
			case RejectionDateAndTime:
			case RejectionDateTime:
				setRejectionDateTime(value);
				if (tag == EMsgArrivalItemRejection.RejectionDateTime) {
					 setRejectionDateTimeFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					
					 setRejectionDateTimeFormat(Utils.getUidsDateAndTimeFormat(value)); 
				 }
				break;
				
			case RejectionReasonHeader:
			case Reason:
			case RejectionReason:   
				setRejectionReasonHeader(value);
				break;
			
			case RejectionReasonHeaderLNG:
				setRejectionReasonHeaderLNG(value);
				break;
				
			case DeclaredDateOfArrival:
			case ExpectedArrivalDateAndTime:         
				setDeclaredDateOfArrival(value);
				if (tag == EMsgArrivalItemRejection.DeclaredDateOfArrival) {
					setDeclaredDateOfArrivalFormat(Utils.getKidsDateAndTimeFormat(value));
				 } else {					 
					 setDeclaredDateOfArrivalFormat(Utils.getUidsDateAndTimeFormat(value)); 
				 }
				break;						
				
			case ConveyanceReference:
			case ConveyanceNumber:
				setConveyanceReference(value);
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
  			return EMsgArrivalItemRejection.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	/*
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	*/
	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String argument) {
		this.msgType = argument;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public List<ImportDetails> getImportDetailsList() {
		return importDetailsList;
	}

	public void setImportDetailsList(List<ImportDetails> importDetailsList) {
		this.importDetailsList = importDetailsList;
	}

	private void addImportDetailsList(ImportDetails tempImportDetails) {
		if (this.importDetailsList == null) {
	   		this.importDetailsList = new Vector<ImportDetails>();
	   	}
	   	this.importDetailsList.add(tempImportDetails);
	}
	
	private void addErrorList(FunctionalError tempError) {
		if (this.functionalErrorList == null) {
	   		this.functionalErrorList = new Vector<FunctionalError>();
	   	}
	   	this.functionalErrorList.add(tempError);
	}

	public void setFunctionalErrorList(List<FunctionalError> functionalErrorList) {
		this.functionalErrorList = functionalErrorList;
	}

	public List<FunctionalError> getFunctionalErrorList() {
		return functionalErrorList;
	}

	public void setDeclaredDateOfArrival(String declaredDateOfArrival) {
		this.declaredDateOfArrival = declaredDateOfArrival;
	}

	public String getDeclaredDateOfArrival() {
		return declaredDateOfArrival;
	}

	public void setRejectionReasonHeader(String rejectionReasonHeader) {
		this.rejectionReasonHeader = rejectionReasonHeader;
	}

	public String getRejectionReasonHeader() {
		return rejectionReasonHeader;
	}

	public void setRejectionReasonHeaderLNG(String rejectionReasonHeaderLNG) {
		this.rejectionReasonHeaderLNG = rejectionReasonHeaderLNG;
	}

	public String getRejectionReasonHeaderLNG() {
		return rejectionReasonHeaderLNG;
	}

	public void setMeansOfTransportBorder(TransportMeans meansOfTransportBorder) {
		this.meansOfTransportBorder = meansOfTransportBorder;
	}

	public TransportMeans getMeansOfTransportBorder() {
		return meansOfTransportBorder;
	}	

	public void setConveyanceReference(String conveyanceReference) {
		this.conveyanceReference = conveyanceReference;
	}

	public String getConveyanceReference() {
		return conveyanceReference;
	}

	public void setCarrier(Party carrierAddress) {
		this.carrier = carrierAddress;
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

	public EFormat getDeclaredDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}

	public void setDeclaredDateOfArrivalFormat(EFormat declaredDateOfArrivalFormat) {
		this.declaredDateOfArrivalFormat = declaredDateOfArrivalFormat;
	}

	public void setRejectionDateTimeFormat(EFormat rejectionDateAndTimeFormat) {
		this.rejectionDateAndTimeFormat = rejectionDateAndTimeFormat;
	}

	public EFormat getRejectionDateTimeFormat() {
		return rejectionDateAndTimeFormat;
	}
	
	public String getRejectionDateTime() {
		return rejectionDateAndTime;
	}

	public void setRejectionDateTime(String rejectionDateAndTime) {
		this.rejectionDateAndTime = Utils.checkNull(rejectionDateAndTime);
	}
	
}
