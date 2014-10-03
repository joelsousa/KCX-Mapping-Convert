package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgArrivalItemRejection<br>
 * Created		: 2010.07.20<br>
 * Description	: Contains Message Structure with fields used in ICSArrivalItemRejection.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class MsgArrivalItemRejection extends KCXMessage {
	
	private String msgName = "ICSArrivalItemRejection";
	private String referenceNumber;
	private String rejectionDateAndTime;	
	private String rejectionReasonHeader;
	private String rejectionReasonHeaderLNG;
	private String declaredDateOfArrival;
	private EFormat declaredDateOfArrivalFormat;
	private TransportMeans meansOfTransportBorder;
	private String time;
	private EFormat timeFormat;
	private String conveyanceReference;
	private Party carrier;
	private TIN carrierTIN;
	private Address carrierAddress;
	private List<ImportDetails> importDetailsList;
	private List<FunctionalError> functionalErrorList;
	

	public MsgArrivalItemRejection() {
		super();				
	}

	public MsgArrivalItemRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	private enum EMsgArrivalItemRejection {
		//KIDS						//UIDS
		ReferenceNumber,			LocalReferenceNumber,		
		RejectionDateTime,       	RejectionDateAndTime, //EI20110208: ArrivalDateAndTime,
		RejectionReasonHeader,		Reason, RejectionReason,
		RejectionReasonHeaderLNG,	//none		
		DeclaredDateOfArrival, 		ExpectedArrivalDateAndTime, //EI20110208 new
		ConveyanceReference,		ConveyanceNumber, //EI20110208 new
		MeansOfTransportBorder,		//none
		ImportDetails,				MRNDetails,
		CarrierTIN,					//none
		CarrierAddress,				//none		
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
				meansOfTransportBorder = new TransportMeans(getScanner());
				meansOfTransportBorder.parse(tag.name());
				break;
			case CarrierTIN:
				carrierTIN = new TIN(getScanner());
				carrierTIN.parse(tag.name());
				break;
			case CarrierAddress:
				carrierAddress = new Address(getScanner());
				carrierAddress.parse(tag.name());
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
					 setRejectionDateTimeFormat(EFormat.KIDS_DateTime);
				 } else {
					//EI20110208: setTimeFormat(EFormat.ST_DateTimeT);
					 setRejectionDateTimeFormat(getUidsDateAndTimeFormat(value)); //EI20110208
				 }
				break;
				
			case RejectionReasonHeader:
			case Reason:
			case RejectionReason:   //EI20110208
				setRejectionReasonHeader(value);
				break;
			
			case RejectionReasonHeaderLNG:
				setRejectionReasonHeaderLNG(value);
				break;
				
			case DeclaredDateOfArrival:
			case ExpectedArrivalDateAndTime:         //EI20110208
				setDeclaredDateOfArrival(value);
				if (tag == EMsgArrivalItemRejection.DeclaredDateOfArrival) {
					setDeclaredDateOfArrivalFormat(EFormat.KIDS_DateTime);
				 } else {
					 //EI20110208: setTimeFormat(EFormat.ST_DateTimeT);
					 setDeclaredDateOfArrivalFormat(getUidsDateAndTimeFormat(value)); //EI20110208
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
	public void setMsgName(String msgName) {
		this.msgName = msgName;
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
		if (carrierTIN != null || carrierAddress != null) {
			if (carrier == null) {
				carrier = new Party();
			}
		}
		if (carrierTIN != null) {
			carrier.setPartyTIN(carrierTIN);				
		}
		if (carrierAddress != null) {
			carrier.setAddress(carrierAddress);				
		}		
		return carrier;
	}
	
	public EFormat getDeclaredDateOfArrivalFormat() {
		return declaredDateOfArrivalFormat;
	}

	public void setDeclaredDateOfArrivalFormat(EFormat declaredDateOfArrivalFormat) {
		this.declaredDateOfArrivalFormat = declaredDateOfArrivalFormat;
	}

	public void setRejectionDateTimeFormat(EFormat timeFormat) {
		this.timeFormat = timeFormat;
	}

	public EFormat getRejectionDateTimeFormat() {
		return timeFormat;
	}
	public Party setPartyFromTrader(Trader trader) {
		if (trader == null) {
			return null;
		}
		Party party  = new Party();
		
		TIN	tin = new TIN();
		tin.setTIN(trader.getTIN());
		tin.setIsTINGermanApprovalNumber(trader.getCustomsID());
		tin.setCustomerIdentifier(trader.getCustomerID());	
		tin.setIdentificationType(trader.getTINType());   //EI20110120
		
		party.setPartyTIN(tin);		
		party.setVATNumber(trader.getVATID());
		party.setETNAddress(trader.getETNAddress());
		party.setAddress(trader.getAddress());
		party.setContactPerson(trader.getContactPerson());
		
		return party;		
	}

	public String getRejectionDateTime() {
		return rejectionDateAndTime;
	}

	public void setRejectionDateTime(String rejectionDateAndTime) {
		this.rejectionDateAndTime = Utils.checkNull(rejectionDateAndTime);
	}
	
	public EFormat getUidsDateAndTimeFormat(String value) {  //EI20110208
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
}
