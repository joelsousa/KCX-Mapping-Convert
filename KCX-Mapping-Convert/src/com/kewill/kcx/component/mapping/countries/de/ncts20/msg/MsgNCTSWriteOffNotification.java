package com.kewill.kcx.component.mapping.countries.de.ncts20.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: MsgNCTSWriteOffNotification<br>
 * Created		: 11.05.2011<br>
 * Description	: Contains Tag Mapping for fields used in NCTSWriteOffNotification message. 
 * 				: V41/V70: new Contact (TsVSP - Ansprechpartner)
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgNCTSWriteOffNotification extends KCXMessage {

	private String msgName = "NCTSWriteOffNotification";	
	private String referenceNumber;
	private String temporaryUCR;
	private String ucrNumber;
	private String statusOfControl;	
	private String receiveTime;
	private String acceptanceTime;
	private String releaseTime;
	private String cancellationTime;
	private String beginTimeOfProcessing;
	private String statusInformation;
	private String completionTime;
	private String writeOffType;    //new
	private String writeOffText;    //new
	private String referencedAmountsCharging;
	
	private PartyNCTS principal;
	private TIN principalTIN;  //new
	private PartyNCTS guarantor;  //new 
	private TIN guarantorTIN;  //new
	
	private ContactPerson contact;  ////EI20130207 V20==41 new: soll es neu aufgenommen werden??? 
	
	private String officeOfDeparture;     //new
	private String useOfTydenSeals;       //new
	private String representativeName;    //new
	
	private List<FunctionalErrorNcts>	grnErrorList = new ArrayList<FunctionalErrorNcts>();  //new
	private List<FunctionalErrorNcts>	functionaEerrorList = new ArrayList<FunctionalErrorNcts>();  //new
	
	private EFormat	receiveTimeFormat;
	private EFormat	acceptanceTimeFormat;
	private EFormat	releaseTimeFormat;
	private EFormat	cancellationTimeFormat;
	private EFormat	beginProcessingTimeFormat;
	private EFormat	completionTimeFormat;
	private EFormat	referencedAmountsChargingFormat;
	
	private LocalApplication localApplication;    //EI20130712 fuer CMP/LCAG
	
	public MsgNCTSWriteOffNotification() {
		super();				
	}

	public MsgNCTSWriteOffNotification(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	private enum EMsgNCTSWriteOffNotification {
		//KIDS							//UIDS	
		GoodsDeclaration,
		ReferenceNumber,			    LocalReferenceNumber,
		TemporaryUCR,					TemporaryMRN,
		UCRNumber,						MRN,	
		StatusOfControl,		        //same
		ReceiveTime,				    DateOfReceipt,
		AcceptanceTime,					DateOfAcceptance,
		ReleaseTime,					DateOfRelease,
		CancellationTime,				DateOfCancellation,
		BeginTimeOfProcessing,			DateOfBeginOfProcessing,
		StatusInformation,				// same
		CompletionTime,					WriteOffDate,
		WriteOffType,					// same
		WriteOffText,					// same
		ReferencedAmountsCharging,		// same
		UseOfTydenSeals,				TydenSealFlag,
		Principal,					    //same	
		PrincipalTIN,                   //-- 
		Guarantor,						//same
		GuarantorTIN,                   //-- 
		OfficeOfDeparture,				//same
		RepresentativeName,				//same
		GRNError,						//same
		FunctionalError,				//same
		Contact,
		LocalApplication,
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgNCTSWriteOffNotification) tag) {
			case Principal:
				principal = new PartyNCTS(getScanner());
				principal.parse(tag.name());						
				break;
			case PrincipalTIN:
				principalTIN = new TIN(getScanner());
				principalTIN.parse(tag.name());								
				break;
			case Guarantor:
				guarantor = new PartyNCTS(getScanner());
				guarantor.parse(tag.name());		
				if (guarantorTIN != null) {
					guarantor.setPartyTIN(guarantorTIN);				
				} 
				break;				
			case GuarantorTIN:
				guarantorTIN = new TIN(getScanner());
				guarantorTIN.parse(tag.name());		
				if (guarantor == null) {
					guarantor = new PartyNCTS(getScanner());
				} 
				guarantor.setPartyTIN(guarantorTIN);					
				break;
			case GRNError:
				FunctionalErrorNcts grn = new FunctionalErrorNcts(getScanner());
				grn.parse(tag.name());
				addGrnErrorList(grn);
				break;	
			case FunctionalError:
				FunctionalErrorNcts error = new FunctionalErrorNcts(getScanner());
				error.parse(tag.name());	
				addFunctionalErrorList(error);
				break;	
			case Contact:    //EI20130207
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());						
				break;
				
			case LocalApplication:
				localApplication = new LocalApplication(getScanner());
				localApplication.parse(tag.name());	
				break;
				
			default:
				return;
			}
		} else {
			switch ((EMsgNCTSWriteOffNotification) tag) {
			case MRN:
			case UCRNumber:
				setUCRNumber(value);
				break;				
			case LocalReferenceNumber:
			case ReferenceNumber:
				setReferenceNumber(value);
				break;				
			case TemporaryMRN:
			case TemporaryUCR:
				setTemporaryUCR(value);
				break;				
			case StatusOfControl:
				setStatusOfControl(value);
				break;	
				
			case DateOfReceipt:
			case ReceiveTime:
				setReceiveTime(value);								
			
				if (tag == EMsgNCTSWriteOffNotification.ReceiveTime) {
					setReceiveTimeFormat(EFormat.KIDS_DateTime);
				} else {
					// MS20110512 Begin
					// setReceiveTimeFormat(EFormat.ST_Date);
					setReceiveTimeFormat(EFormat.ST_DateTimeTNoZ);
					// MS20110512 End
				}
				break;	
				
			case AcceptanceTime:
				setAcceptanceTime(value);
				setAcceptanceTimeFormat(EFormat.KIDS_DateTime);
				break;
			case DateOfAcceptance:
				setAcceptanceTime(value);
				setAcceptanceTimeFormat(EFormat.ST_DateTimeTNoZ);
				break;	
				
			case ReleaseTime:
				setReleaseTime(value);
				setReleaseTimeFormat(EFormat.KIDS_DateTime);
			break;
			case DateOfRelease:
				setReleaseTime(value);
				setReleaseTimeFormat(EFormat.ST_DateTimeTNoZ);
				break;	
				
			case CancellationTime:
				setCancellationTime(value);
				setCancellationTimeFormat(EFormat.KIDS_DateTime);
				break;
			case DateOfCancellation:
				setCancellationTime(value);
				setCancellationTimeFormat(EFormat.ST_DateTimeTNoZ);
				break;
				
			case BeginTimeOfProcessing:
				setBeginTimeOfProcessing(value);
				setBeginProcessingTimeFormat(EFormat.KIDS_DateTime);
				break;
			case DateOfBeginOfProcessing:
				setBeginTimeOfProcessing(value);
				setBeginProcessingTimeFormat(EFormat.ST_DateTimeTNoZ);
				break;	
				
			case StatusInformation:
				setStatusInformation(value);
				break;	
				
			case CompletionTime:
				setCompletionTime(value);
				setCompletionTimeFormat(EFormat.KIDS_DateTime);
				break;
			case WriteOffDate:
				setCompletionTime(value);
				setCompletionTimeFormat(EFormat.ST_DateTimeTNoZ);
				break;
				
			case ReferencedAmountsCharging:
				setReferencedAmountsCharging(value);
				if (value.indexOf('-') == -1) {
					setReferencedAmountsChargingFormat(EFormat.KIDS_DateTime);
				} else {
					setReferencedAmountsChargingFormat(EFormat.ST_DateTimeTNoZ);
				}
				break;
				
			case WriteOffType:
				setReferencedAmountsCharging(value);
				break;
			case WriteOffText:
				setWriteOffText(value);
				break;
			case TydenSealFlag:
			case UseOfTydenSeals:
				setWriteOffText(value);
				break;
			case RepresentativeName:
				setRepresentativeName(value);
				break;
			case OfficeOfDeparture:
				setOfficeOfDeparture(value);
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
  			return EMsgNCTSWriteOffNotification.valueOf(token);
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
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
		
	public String getUCRNumber() {
		return ucrNumber;
	}
	public void setUCRNumber(String argument) {
		this.ucrNumber = argument;
	}
	
	public String getTemporaryUCR() {
		return temporaryUCR;
	}
	public void setTemporaryUCR(String argument) {
		this.temporaryUCR = argument;
	}
	
	public String getStatusOfControl() {
		return statusOfControl;
	}
	public void setStatusOfControl(String argument) {
		this.statusOfControl = argument;
	}

	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String argument) {
		this.receiveTime = argument;
	}

	public EFormat getReceiveTimeFormat() {
		return receiveTimeFormat;
	}

	public void setReceiveTimeFormat(EFormat receiveTimeFormat) {
		this.receiveTimeFormat = receiveTimeFormat;
	}
	
	public PartyNCTS getPrincipal() {
		if (principal == null && principalTIN != null) {
			principal = new PartyNCTS();
			principal.setPartyTIN(principalTIN);
		}		
		return this.principal;
	}

	public void setPrincipal(PartyNCTS party) {
		this.principal = party;
	}
	
	public PartyNCTS getGuarantor() {
		if (guarantor == null && guarantorTIN != null) {
			guarantor = new PartyNCTS();
			guarantor.setPartyTIN(guarantorTIN);
		}		
		return this.guarantor;
	}

	public void setGuarantor(PartyNCTS party) {
		this.guarantor = party;
	}
	
	public String getUcrNumber() {
		return ucrNumber;
	}

	public void setUcrNumber(String ucrNumber) {
		this.ucrNumber = ucrNumber;
	}

	public String getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getCancellationTime() {
		return cancellationTime;
	}

	public void setCancellationTime(String cancellationTime) {
		this.cancellationTime = cancellationTime;
	}

	public String getBeginTimeOfProcessing() {
		return beginTimeOfProcessing;
	}

	public void setBeginTimeOfProcessing(String beginTimeOfProcessing) {
		this.beginTimeOfProcessing = beginTimeOfProcessing;
	}

	public String getStatusInformation() {
		return statusInformation;
	}

	public void setStatusInformation(String statusInformation) {
		this.statusInformation = statusInformation;
	}

	public String getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}

	public String getReferencedAmountsCharging() {
		return referencedAmountsCharging;
	}

	public void setReferencedAmountsCharging(String referencedAmountsCharging) {
		this.referencedAmountsCharging = referencedAmountsCharging;
	}

	public EFormat getAcceptanceTimeFormat() {
		return acceptanceTimeFormat;
	}

	public void setAcceptanceTimeFormat(EFormat acceptanceTimeFormat) {
		this.acceptanceTimeFormat = acceptanceTimeFormat;
	}

	public EFormat getReleaseTimeFormat() {
		return releaseTimeFormat;
	}

	public void setReleaseTimeFormat(EFormat releaseTimeFormat) {
		this.releaseTimeFormat = releaseTimeFormat;
	}

	public EFormat getCancellationTimeFormat() {
		return cancellationTimeFormat;
	}

	public void setCancellationTimeFormat(EFormat cancellationTimeFormat) {
		this.cancellationTimeFormat = cancellationTimeFormat;
	}

	public EFormat getBeginProcessingTimeFormat() {
		return beginProcessingTimeFormat;
	}

	public void setBeginProcessingTimeFormat(EFormat beginProcessingTimeFormat) {
		this.beginProcessingTimeFormat = beginProcessingTimeFormat;
	}

	public EFormat getCompletionTimeFormat() {
		return completionTimeFormat;
	}

	public void setCompletionTimeFormat(EFormat compeltionTimeFormat) {
		this.completionTimeFormat = compeltionTimeFormat;
	}	
	
	public EFormat getReferencedAmountsChargingFormat() {
		return referencedAmountsChargingFormat;
	}

	public void setReferencedAmountsChargingFormat(EFormat format) {
		this.referencedAmountsChargingFormat = format;
	}
	
	public void setWriteOffType(String argument) {
		this.writeOffType = argument;
	}
	public String getWriteOffType() {
		return writeOffType;
	}
	
	public void setWriteOffText(String argument) {
		this.writeOffText = argument;
	}
	public String getWriteOffText() {
		return writeOffText;
	}
	
	public void setUseOfTydenSeals(String argument) {
		this.useOfTydenSeals = argument;
	}
	public String getUseOfTydenSeals() {
		return useOfTydenSeals;
	}
	
	public void setRepresentativeName(String argument) {
		this.representativeName = argument;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	
	public void setOfficeOfDeparture(String argument) {
		this.officeOfDeparture = argument;
	}
	public String getOfficeOfDeparture() {
		return officeOfDeparture;
	}
		
	public List< FunctionalErrorNcts > getGrnErrorList() {
		return this.grnErrorList;
	}
	public void setGrnErrorList(List<FunctionalErrorNcts> errList) {
		this.grnErrorList	= errList;
	}
	public void addGrnErrorList(FunctionalErrorNcts grn) {
		if (this.grnErrorList == null) {
			this.grnErrorList	= new Vector< FunctionalErrorNcts >();
		}
		
		this.grnErrorList.add(grn);
	}
	
	public List< FunctionalErrorNcts > getFunctionalErrorList() {
		return this.functionaEerrorList;
	}
	public void setFunctionalErroList(List<FunctionalErrorNcts> errList) {
		this.functionaEerrorList	= errList;
	}
	public void addFunctionalErrorList(FunctionalErrorNcts value) {
		if (this.functionaEerrorList == null) {
			this.functionaEerrorList	= new Vector< FunctionalErrorNcts >();
		}
		
		this.functionaEerrorList.add(value);
	}
	
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}
	
	public LocalApplication getLocalApplication() {
		return localApplication;
	}
	public void setLocalApplication(LocalApplication local) {
		this.localApplication = local;
	}
}
