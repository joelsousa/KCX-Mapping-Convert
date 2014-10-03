/*
 * Function    : MsgExpSta.java
 * Titel       :
 * Date        : 24.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Contains the Message Declaration Response  
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Justification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 24.09.2008<br>
 * Description	: Contains the Message DeclarationResponse with all Fields used in UIDS and  KIDS. 
 * 				: new member for V21
 * 
 * @author Kron
 * @version 1.0.00
 */

public class MsgExpSta extends KCXMessage {

	// simple tags
	private String cancellationTime;
	private String exitTime;
	private String releaseTime;
	private String acceptanceTime;
	private String receiveTime;
	private String timeOfRejection;
	private String beginTimeOfProcessing;
	private String reason;
	private String statusOfCompletion;
	private String statusOfCompletion2;						// CK 08.02.2012
	private String statusOfCompletion3;						// CK 08.02.2012
	private String referenceNumber;
	private String timeOfRejectionOfPreannouncement;         //17.10.08
	private String timeOfCompletion;                        //17.10.08	
	private String epuNumber; 								//CK 01.02.2012
	private String entryNumber; 							//CK 01.02.2012
	 //new for V21 begin
	private String  declarationTime;
	private String  orderNumber;
	private Party   customsOffice;
	private String  customsOfficeExport;
	private String  customsOfficeForCompletion;
	private Party   declarant;
	private TIN     declarantTIN;
	private Party   agent;
	private TIN     agentTIN;
	 //new for V21 end
	
	// structure tags
	private LoadingTime loadingTime;
	private ErrorType error;
	private List<ErrorType> errorList = new ArrayList<ErrorType>();	
	//private Justification justification;
	
	//EI20110518: alle fromats
	private EFormat cancellationTimeFormat;  
    private EFormat exitTimeFormat;
    private EFormat releaseTimeFormat;
    private EFormat acceptanceTimeFormat;
    private EFormat receiveTimeFormat;
	private EFormat timeOfRejectionFormat;
    private EFormat beginTimeOfProcessingFormat;
    private EFormat timeOfRejectionOfPreannouncementFormat;
    private EFormat timeOfCompletionFormat;   
    private EFormat declarationTimeFormat;  
    
    
	private enum EExpStaTags {

		// Kids-TagNames, 					UIDS-TagNames;
		  DeclarationResponse,				ExportDeclarationResponse,
		  GoodsDeclaration,					Submit,
		  									Export,
		  CancellationTime,					DateOfCancellation,
		  DeclarationTime,					DateOfDeclaration, //  V21 fin xls nicht notwendig
		  ExitTime,							DateOfExit,
		  ReleaseTime,						DateOfRelease,
		  AcceptanceTime,					DateOfAcceptance,
		  ReceiveTime,						DateOfReceipt,
		  TimeOfRejection,					DateOfRejection,
		  BeginTimeOfProcessing,			DateOfProcessing,	
		  AdvanceNoticeTime,    			DateOfPreAdvice,          //TODO-V21 : fehlt for Kids in xls
		  TimeOfRejectionOfPreannoucement,  DateOfPreAdviceRejection,
		  TimeOfRejectionOfPreannouncement,
		  TimeOfCompletion,                 DateOfClosing,
		  Reason,							Description,
		  StatusOfCompletion,				Code,
		  									Justification,  
		  StatusOfCompletion2,				// UIDS not defined yet
		  StatusOfCompletion3,				// UIDS not defined yet
		  ReferenceNumber,					// UIDS: same
		  OrderNumber,						LocalReferenceNumber,  //V21 fehlt in xls for Uids
		  EPUNumber,						//same CK 01.02.2012
		  EntryNumber,						//same CK 01.02.2012
		  CustomsOfffice,                   //V21  ???
		  LoadingTime,						// UIDS: no structure
		  BeginTime,						DateOfLoadingBegin,						
		  EndTime,							DateOfLoadingEnd,
		  //CustomsOffice,					//V21 fehlt in xls
		  Error,							//same;
		  CustomsOfficeExport,				CustomsOffices, //.OfficeOfExport        //V21 fehlt in xls
		  CustomsOfficeForCompletion,		//.OfficeOfAdditionalDeclarationExport    //V21 fehlt in xls
		  Declarant, DeclarantTIN, 			//same  
		  Agent, AgentTIN,					DeclarantRepresentative,       //V21 fehlt in xls for Uids
	}
	
	public MsgExpSta() {
		super();		
	}
	
	public MsgExpSta(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			
			switch ((EExpStaTags) tag) {
			
			case Justification: 
				Justification justification = new Justification(getScanner());
				justification.parse(tag.name());
				setJustification(justification);
				break;			
			case LoadingTime: 
				loadingTime = new LoadingTime();
				break;			
			case Error:
				error = new ErrorType(getScanner());	
				error.parse(tag.name());
				errorList.add(error);
				/*
			case CustomsOffice:
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
			case Agent:
			case DeclarantRepresentative:						
				agent = new Party(getScanner());
				agent.parse(tag.name());
				break;
			case AgentTIN:
				agentTIN = new TIN(getScanner());
				agentTIN.parse(tag.name());	
				break;
			case CustomsOffices:
				CustomsOffices customsOffices = new CustomsOffices(getScanner());
				customsOffices.parse(tag.name());
				setKidsFromCustomsOffices(customsOffices);		
				break;
			default:
				return;
			}
		} else {
			
			switch ((EExpStaTags) tag) {		
				case CancellationTime: 
				case DateOfCancellation:
					setCancellationTime(value);
					if (tag == EExpStaTags.CancellationTime) {
   					 	setCancellationTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setCancellationTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}		
					break;
				
				case ExitTime: 
				case DateOfExit:
					setExitTime(value);
					if (tag == EExpStaTags.ExitTime) {
   					 	setExitTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setExitTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}	
					break;
				
				case ReleaseTime: 
				case DateOfRelease:
					setReleaseTime(value);
					if (tag == EExpStaTags.ReleaseTime) {
   					 	setReleaseTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setReleaseTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;
				
				case AcceptanceTime:
				case DateOfAcceptance:
					setAcceptanceTime(value);
					if (tag == EExpStaTags.AcceptanceTime) {
   					 	setAcceptanceTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setAcceptanceTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}					
					break;
					
				case ReceiveTime:
				case DateOfReceipt:
					setReceiveTime(value);
					if (tag == EExpStaTags.ReceiveTime) {
   					 	setReceiveTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setReceiveTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;
					
				case TimeOfRejection:
				case DateOfRejection:
					setTimeOfRejection(value);
					if (tag == EExpStaTags.TimeOfRejection) {
   					 	setTimeOfRejectionFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setTimeOfRejectionFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;
					
				case BeginTimeOfProcessing:
				case DateOfProcessing:
					setBeginTimeOfProcessing(value);
					if (tag == EExpStaTags.BeginTimeOfProcessing) {
   					 	setBeginTimeOfProcessingFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setBeginTimeOfProcessingFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;
					
				case Reason:
				case Description:
					setReason(value);
					break;
					
				case StatusOfCompletion:
				case Code:
					setStatusOfCompletion(value);
					break;
				//CK 08.02.2012
				case StatusOfCompletion2:
					setStatusOfCompletion2(value);
					break;

				//CK 08.02.2012
				case StatusOfCompletion3:
					setStatusOfCompletion3(value);
					break;
					
				case ReferenceNumber:
					setReferenceNumber(value);
					break;
					
				//CK 01.02.2012
				case EPUNumber:		
					setEpuNumber(value);
					break;
			
				//CK 01.02.2012
				case EntryNumber:
					setEntryNumber(value);
					break;
					
				case BeginTime:
				case DateOfLoadingBegin:
					if (loadingTime == null) {
						loadingTime = new LoadingTime();	
					}
					loadingTime.setBeginTime(value);
					if (tag == EExpStaTags.BeginTime) {
						loadingTime.setLoadingBeginFormat(EFormat.KIDS_DateTime);
					} else {
						loadingTime.setLoadingBeginFormat(getUidsDateAndTimeFormat(value));
   				 	}
					break;
					
				case EndTime:
				case DateOfLoadingEnd:
					if (loadingTime == null) {
						loadingTime = new LoadingTime();	
					}
					loadingTime.setEndTime(value);
					if (tag == EExpStaTags.EndTime) {
						loadingTime.setLoadingEndFormat(EFormat.KIDS_DateTime);
					} else {
						loadingTime.setLoadingEndFormat(getUidsDateAndTimeFormat(value));
   				 	}
					break;
					
				case TimeOfRejectionOfPreannoucement:
				case TimeOfRejectionOfPreannouncement:
				case DateOfPreAdviceRejection:
					setTimeOfRejectionOfPreannouncement(value);
					if (tag == EExpStaTags.TimeOfRejectionOfPreannoucement) {
   					 	setTimeOfRejectionOfPreannouncementFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setTimeOfRejectionOfPreannouncementFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;
					
				case TimeOfCompletion:
				case DateOfClosing:
				 	 setTimeOfCompletion(value);
				 	if (tag == EExpStaTags.TimeOfCompletion) {
   					 	setTimeOfCompletionFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setTimeOfCompletionFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
					break;	
				case DeclarationTime:
				case DateOfDeclaration:
					 setDeclarationTime(value);
					 if (value.indexOf('-') == -1) {
						 setDeclarationTimeFormat(EFormat.KIDS_DateTime);
	   				 } else {
	   					setDeclarationTimeFormat(getUidsDateAndTimeFormat(value)); 
	   				 }
					 break;
				case OrderNumber:
				case LocalReferenceNumber:
					setOrderNumber(value);
					break;
				case CustomsOfficeExport:
					setCustomsOfficeExport(value);
					break;
				case CustomsOfficeForCompletion:
					setCustomsOfficeForCompletion(value);
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
			return EExpStaTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	public String getCancellationTime() {
		return cancellationTime;
	}
	public void setCancellationTime(String cancellationTime) {
		this.cancellationTime = cancellationTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getAcceptanceTime() {
		return acceptanceTime;
	}
	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getTimeOfRejection() {
		return timeOfRejection;
	}
	public void setTimeOfRejection(String timeOfRejection) {
		this.timeOfRejection = timeOfRejection;
	}
	public String getBeginTimeOfProcessing() {
		return beginTimeOfProcessing;
	}
	public void setBeginTimeOfProcessing(String beginTimeOfProcessing) {
		this.beginTimeOfProcessing = beginTimeOfProcessing;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatusOfCompletion() {
		return statusOfCompletion;
	}
	public void setStatusOfCompletion(String statusOfCompletion) {
		this.statusOfCompletion = statusOfCompletion;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public LoadingTime getLoadingTime() {
		return loadingTime;
	}
	public void setLoadingTime(LoadingTime loadingTime) {
		this.loadingTime = loadingTime;
	}
	public ErrorType getError() {
		return error;
	}
	public void setError(ErrorType error) {
		this.error = error;
	}
	public List getErrorList() {
		return errorList;
	}
	public void setErrorList(List<ErrorType> errorList) {
		this.errorList = errorList;
	}
	public void addErrorList(ErrorType argument) {
		this.errorList.add(argument);
	}	
	public String getTimeOfRejectionOfPreannouncement() {
		return timeOfRejectionOfPreannouncement;
	}
	public void setTimeOfRejectionOfPreannouncement(String timeOfRejectionOfPreannouncement) {
		this.timeOfRejectionOfPreannouncement = timeOfRejectionOfPreannouncement;
	}

	public String getTimeOfCompletion() {
		return timeOfCompletion;
	}
	public void setTimeOfCompletion(String timeOfCompletion) {
		this.timeOfCompletion = timeOfCompletion;
	}	
	
	public void setJustification(Justification justification) {
		if (justification == null) {
			return;
		}
		this.reason = justification.getDescription();
		this.statusOfCompletion = justification.getCode();
	}	
	public Justification getJustification() {
		Justification temp = new Justification();
		temp.setCode(statusOfCompletion);
		temp.setDescription(this.reason);
		return temp;
	}
	
	/////////
	
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

	public EFormat getCancellationTimeFormat() {
		return cancellationTimeFormat;
	}
	public void setCancellationTimeFormat(EFormat argument) {
		this.cancellationTimeFormat = argument;
	}
	
	public EFormat getExitTimeFormat() {
		return exitTimeFormat;
	}
	public void setExitTimeFormat(EFormat argument) {
		this.exitTimeFormat = argument;
	}
	
	public EFormat getReleaseTimeFormat() {
		return releaseTimeFormat;
	}
	public void setReleaseTimeFormat(EFormat argument) {
		this.releaseTimeFormat = argument;
	}
	
	public EFormat getAcceptanceTimeFormat() {
		return acceptanceTimeFormat;
	}
	public void setAcceptanceTimeFormat(EFormat argument) {
		this.acceptanceTimeFormat = argument;
	}
	public EFormat getReceiveTimeFormat() {
		return receiveTimeFormat;
	}
	public void setReceiveTimeFormat(EFormat argument) {
		this.receiveTimeFormat = argument;
	}
	public EFormat getBeginTimeOfProcessingFormat() {
		return beginTimeOfProcessingFormat;
	}
	public void setBeginTimeOfProcessingFormat(EFormat argument) {
		this.beginTimeOfProcessingFormat = argument;
	}
	public EFormat getTimeOfRejectionOfPreannouncementFormat() {
		return timeOfRejectionOfPreannouncementFormat;
	}
	public void setTimeOfRejectionOfPreannouncementFormat(EFormat argument) {
		this.timeOfRejectionOfPreannouncementFormat = argument;
	}
	
	public EFormat getTimeOfCompletionFormat() {
		return timeOfCompletionFormat;
	}
	public void setTimeOfCompletionFormat(EFormat argument) {
		this.timeOfCompletionFormat = argument;
	}
		
	public EFormat getTimeOfRejectionFormat() {
		return timeOfRejectionFormat;
	}
	
	public void setTimeOfRejectionFormat(EFormat argument) {
		this.timeOfRejectionFormat = argument;
	}

	public String getEpuNumber() {
		return epuNumber;
	}

	public void setEpuNumber(String epuNumber) {
		this.epuNumber = epuNumber;
	}

	public String getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}
	
	public String getStatusOfCompletion2() {
		return statusOfCompletion2;
	}

	public void setStatusOfCompletion2(String statusOfCompletion2) {
		this.statusOfCompletion2 = statusOfCompletion2;
	}

	public String getStatusOfCompletion3() {
		return statusOfCompletion3;
	}

	public void setStatusOfCompletion3(String statusOfCompletion3) {
		this.statusOfCompletion3 = statusOfCompletion3;
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
	
	public String getDeclarationTime() {
		return declarationTime;	
	}
	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = Utils.checkNull(declarationTime);
	}
	public EFormat getDeclarationTimeFormat() {
		return declarationTimeFormat;
	}
	public void setDeclarationTimeFormat(EFormat argument) {
		this.declarationTimeFormat = argument;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String argument) {
		this.orderNumber = argument;
	}
	
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
		return agent;
	}
	/*
	public void setCustomsOffice(Party argument) {
		this.customsOffice = argument;
	}
	public Party getCustomsOffice() {			
		return customsOffice;
	}
	*/
	private void setKidsFromCustomsOffices(CustomsOffices argument) {
		if (argument == null) {
			return;
		}
		this.customsOfficeExport = argument.getOfficeOfExport();
        this.customsOfficeForCompletion = argument.getOfficeOfAdditionalDeclarationExport();		
	}
	
}
