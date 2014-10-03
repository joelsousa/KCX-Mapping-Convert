package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AadVal;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.Diagnosis;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExportCrossCheckingDiagnoses;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.Rejection;

/**
 * Module		: EMCS<br>
 * Created		: 12.05.2010<br>
 * Description  : Contains Message Structure with fields used in Kids EMCSExportRejection message and
 *              : Uids EMCSRejectionOfEAADForExport 
 *              : IE839 / C_EXP_REJ.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgExportRejection extends KCXMessage {

	private String msgName = "EMCSExportRejection";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String deliveryPlaceCustomsOffice;   //EI20111111
	private String dateAndTimeOfIssuance;	
	private String documentReferenceNumber;
	private String cancelExport;
	private List <Diagnosis> diagnosisList;
	private String rejectionReasonCode;
	private String rejectionDateAndTime;	
	private EmcsTrader consignee;                   //EI20110819
	//private List <FunctionalError> functionalErrorList; 
	private List <AadVal> functionalErrorList;      //Tag: complex tag FunctionalError, von typ ST_FunctionalError  
 
	private enum EExportRejection {
		//KIDS:									UIDS:  
		ReferenceNumber,        		ExportCrossCheckingDiagnoses, //.LocalReferenceNumber,		  
		CustomerOrderNumber,     		//not defined		
		Clerk,                  		//not defined	
		DeliveryPlaceCustomsOffice,     //same
		DateAndTimeOfIssuance,			//same
		DocumentReferenceNumber,  		//ExportCrossCheckingDiagnoses.DocumentReferenceNumber
		CancelExport,				   	//ExportCrossCheckingDiagnoses.CancelExport,
		Diagnosis,						//ExportCrossCheckingDiagnoses.Diagnosis
		RejectionReasonCode,            Rejection, //Rejection.RejectionReasonCode
		RejectionDateAndTime,			//Rejection.RejectionDateAndTime
		Consignee, 						ConsigneeTrader,
		FunctionalError,				CAadVal,    
		                                NAadSub;   // what with it?
	}
	
	public MsgExportRejection() {
		super();
	}

	public MsgExportRejection(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EExportRejection) tag) {
			case Diagnosis:
				Diagnosis tempDiagnosis = new Diagnosis(getScanner());
				tempDiagnosis.parse(tag.name());
				if (diagnosisList == null) {
					diagnosisList = new Vector<Diagnosis>();
				}
				diagnosisList.add(tempDiagnosis);
				break;
			case ExportCrossCheckingDiagnoses:
				ExportCrossCheckingDiagnoses tempExCCDiagnosis = new ExportCrossCheckingDiagnoses(getScanner());
				tempExCCDiagnosis.parse(tag.name());
				setReferenceNumber(tempExCCDiagnosis.getLocalReferenceNumber());
				setDocumentReferenceNumber(tempExCCDiagnosis.getDocumentReferenceNumber());
				setCancelExport(tempExCCDiagnosis.getCancelExport());
				diagnosisList = tempExCCDiagnosis.getDiagnosisList();
				break;
			case Rejection:
				Rejection tempRejection = new Rejection(getScanner());	
				tempRejection.parse(tag.name());
				setRejectionReasonCode(tempRejection.getRejectionReasonCode());
				setRejectionDateAndTime(tempRejection.getRejectionDateAndTime());
				break;
			case FunctionalError:    
			case CAadVal:
			//case NAadSub:			
				AadVal tempAadVal = new AadVal(getScanner());
				tempAadVal.parse(tag.name());				
				if (functionalErrorList == null) {
					functionalErrorList = new Vector<AadVal>();
				}
				functionalErrorList.add(tempAadVal);
				break;
				/*
			case FunctionalError:
				FunctionalError tempFError = new FunctionalError(getScanner());
				tempFError.parse(tag.name());
				if (functionalErrorList == null)
					functionalErrorList = new Vector<FunctionalError>();
				functionalErrorList.add(tempFError);
				break;	
				*/	
			case Consignee:	
			case ConsigneeTrader:
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());
				break;	
			default:
				return;
			}
	    } else {
	    	switch ((EExportRejection) tag) {
			case ReferenceNumber:				
				 setReferenceNumber(value);
				 break;	
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;	
			case DeliveryPlaceCustomsOffice:
				setDeliveryPlaceCustomsOffice(value);
				break;
			case Clerk:
				 setClerk(value);
				 break;	
			case DateAndTimeOfIssuance:
				 setDateAndTimeOfIssuance(value);
				 break;
			case DocumentReferenceNumber:
				 setDocumentReferenceNumber(value);
				 break;					 
			case CancelExport:
				 setCancelExport(value);
				 break;							
			case RejectionReasonCode:			
				 setRejectionReasonCode(value);
				 break;	
			case RejectionDateAndTime:
				 setRejectionDateAndTime(value);
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
  			return EExportRejection.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
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
	
	public String getDeliveryPlaceCustomsOffice() {
		return this.deliveryPlaceCustomsOffice;
	}
	public void setDeliveryPlaceCustomsOffice(String argument) {
		this.deliveryPlaceCustomsOffice = argument;  //EI20111115
	}
	
	public String getDateAndTimeOfIssuance() {
		return this.dateAndTimeOfIssuance;
	}
	public void setDateAndTimeOfIssuance(String argument) {
		this.dateAndTimeOfIssuance = argument;
	}
	
	public String getDocumentReferenceNumber() {
		return this.documentReferenceNumber;
	}
	public void setDocumentReferenceNumber(String argument) {
		this.documentReferenceNumber = argument;
	}
	
	public String getCancelExport() {
		return this.cancelExport;
	}
	public void setCancelExport(String argument) {
		this.cancelExport = argument;
	}	
	
	public List<Diagnosis> getDiagnosisList() {
		return this.diagnosisList;
	}
	public void setDiagnosisList(Diagnosis argument) {
		this.diagnosisList.add(argument);
	}	

	public String getRejectionReasonCode() {
		return this.rejectionReasonCode;
	}
	public void setRejectionReasonCode(String argument) {
		this.rejectionReasonCode = argument;
	}	
	
	public String getRejectionDateAndTime() {
		return this.rejectionDateAndTime;
	}
	public void setRejectionDateAndTime(String argument) {
		this.rejectionDateAndTime = argument;
	}		
	
	public List<AadVal> getFunctionalErrorList() {
		return this.functionalErrorList;
	}
	public void setFunctionalErrorList(AadVal argument) {
		this.functionalErrorList.add(argument);
	}
	public EmcsTrader getConsignee() {
		return this.consignee;
	}
	public void setConsignee(EmcsTrader argument) {
		this.consignee = argument;
	}
}
