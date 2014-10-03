package com.kewill.kcx.component.mapping.countries.de.emcs21.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ChangedDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.TransportDetails;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.UpdateEaad;

/**
 * Modul		: EMCS<br>
 * Erstellt		: 10.02.2014<br>
 * Beschreibung : Contains Message Structure with fields used in Kids EMCSChangeOfDestination und
 * 				: Uids MT_EMCSChangeOfDestination
 *              : IE813 / C_UPD_DAT.   
 *              :  new for V21: ComplementaryInformation;  
 *               
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgChangeOfDestination extends KCXMessage {

	private String msgName = "EMCSChangeOfDestination";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;
	private String sequenceNumber;
	private String journeyTime;
	private String changedTransportArrangement;
	private String transportModeCode;
	private String invoiceNumber;
	private String invoiceDate;
	private String dateAndTimeOfValidation;
	private String destinationTypeCode;
	private String deliveryPlaceCustomsOffice;
	private EmcsTrader newConsignee; 
	private EmcsTrader deliveryPlace; 
	private EmcsTrader newTransportArranger; 
	private EmcsTrader newTransporter; 
	//private TransportDetails transportDetails;	
	private List <TransportDetails> transportDetailsList;
	private Text complementaryInformation;   //EI20140210 new for V21
	 
	public MsgChangeOfDestination() {
		super();
	}

	public MsgChangeOfDestination(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
 
	private enum EChangeOfDestination {
		//KIDS:						UIDS:
		ReferenceNumber,        	LocalReferenceNumber,  
		CustomerOrderNumber,     	//not defined
		Clerk,                  	 //not defined  
		AadReferenceCode,			UpdateEaad,   //UpdateEaad.AadReferenceCode
		SequenceNumber,                       //UpdateEaad.SequenceNumber
		JourneyTime,						  //UpdateEaad.JourneyTime
		ChangedTransportArrangement,          //UpdateEaad.ChangedTransportArrangement
		TransportModeCode,                    //UpdateEaad.TransportModeCode
		InvoiceNumber,                        //UpdateEaad.InvoiceNumber
		InvoiceDate,                          //UpdateEaad.InvoiceDate
		DateAndTimeOfValidation,  	DateAndTimeOfValidationOfEaad,
		DestinationTypeCode,	 	ChangedDestination, //ChangedDestination.DestinationTypeCode			
		DeliveryPlaceCustomsOffice,					 //ChangedDestination.DeliveryPlaceCustomsOffice
		NewConsignee,                                //ChangedDestination.NewConsigneeTrader,
		DeliveryPlace,   		                     //ChangedDestintion.DeliveryPlace
		NewTransportArranger,		NewTransportArrangerTrader,
		NewTransporter, 			NewTransporterTrader,
		TransportDetails,			//same	
		ComplementaryInformation,
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EChangeOfDestination) tag) {
			case NewConsignee:				
				newConsignee = new EmcsTrader(getScanner());
				newConsignee.parse(tag.name());
				break;
			case DeliveryPlace:				
				deliveryPlace = new EmcsTrader(getScanner());
				deliveryPlace.parse(tag.name());
				break;				
			case NewTransportArrangerTrader:
			case NewTransportArranger:
				newTransportArranger = new EmcsTrader(getScanner());
				newTransportArranger.parse(tag.name());
				break;
			case NewTransporterTrader:
			case NewTransporter:
				newTransporter = new EmcsTrader(getScanner());
				newTransporter.parse(tag.name());
				break;
			case TransportDetails:
				TransportDetails transportDetails = new TransportDetails(getScanner());
				transportDetails.parse(tag.name());
				if (transportDetailsList == null) {
					transportDetailsList = new Vector <TransportDetails>();
				}
				transportDetailsList.add(transportDetails);
				break;
			case UpdateEaad:
				UpdateEaad updateEaad = new UpdateEaad(getScanner());
				updateEaad.parse(tag.name());
				setUpdateEaad(updateEaad);					
				break;
			case ChangedDestination:
				ChangedDestination changedDestination = new ChangedDestination(getScanner());
				changedDestination.parse(tag.name());
				setChangedDestination(changedDestination);				
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EChangeOfDestination) tag) {
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
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;					 
			case JourneyTime:
				 setJourneyTime(value);
				 break;					 
			case ChangedTransportArrangement:
				 setChangedTransportArrangement(value);
				 break;					 
			case TransportModeCode:
				 setTransportModeCode(value);
				 break;					 
			case InvoiceNumber:
				 setInvoiceNumber(value);
				 break;					 
			case InvoiceDate:
				 setInvoiceDate(value);
				 break;					 
			case DateAndTimeOfValidation:
			case DateAndTimeOfValidationOfEaad:
				 setDateAndTimeOfValidation(value);
				 break;					 
			case DestinationTypeCode:
				 setDestinationTypeCode(value);
				 break;					 
			case DeliveryPlaceCustomsOffice:
				 setDeliveryPlaceCustomsOffice(value);
				 break;
			case ComplementaryInformation:
				//setComplementaryInformation(value);
				 complementaryInformation = new Text(value, attr);  
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
  			return EChangeOfDestination.valueOf(token);
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
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}	

	public String getJourneyTime() {
		return this.journeyTime;
	}
	public void setJourneyTime(String argument) {
		this.journeyTime = argument;
	}

	public String getChangedTransportArrangement() {
		return this.changedTransportArrangement;
	}
	public void setChangedTransportArrangement(String argument) {
		this.changedTransportArrangement = argument;
	}	

	public String getTransportModeCode() {
		return this.transportModeCode;
	}
	public void setTransportModeCode(String argument) {
		this.transportModeCode = argument;
	}	

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public void setInvoiceNumber(String argument) {
		this.invoiceNumber = argument;
	}	

	public String getInvoiceDate() {
		return this.invoiceDate;
	}
	public void setInvoiceDate(String argument) {
		this.invoiceDate = argument;
	}	

	public String getDateAndTimeOfValidation() {
		return this.dateAndTimeOfValidation;
	}
	public void setDateAndTimeOfValidation(String argument) {
		this.dateAndTimeOfValidation = argument;
	}	

	public String getDestinationTypeCode() {
		return this.destinationTypeCode;
	}
	public void setDestinationTypeCode(String argument) {
		this.destinationTypeCode = argument;
	}	

	public String getDeliveryPlaceCustomsOffice() {
		return this.deliveryPlaceCustomsOffice;
	}
	public void setDeliveryPlaceCustomsOffice(String argument) {
		this.deliveryPlaceCustomsOffice = argument;
	}	
	 	
	public EmcsTrader getNewConsignee() {
		return this.newConsignee;
	}
	public void setNewConsignee(EmcsTrader argument) {
		this.newConsignee = argument;
	}
	
	public EmcsTrader getDeliveryPlace() {
		return this.deliveryPlace;
	}
	public void setDeliveryPlace(EmcsTrader argument) {
		this.deliveryPlace = argument;
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
	
	public Text getComplementaryInformation() {
		return this.complementaryInformation;
	}
	public void setComplementaryInformation(Text argument) {
		this.complementaryInformation = argument;
	}
			
	public void setUpdateEaad(UpdateEaad argument) {		
		if (argument == null) {		
			return;
		}
		this.aadReferenceCode =	argument.getAadReferenceCode();
		this.journeyTime =	argument.getJourneyTime();
		this.changedTransportArrangement =	argument.getChangedTransportArrangement();
		this.transportModeCode =	argument.getTransportModeCode();
		this.invoiceNumber =	argument.getInvoiceNumber();
		this.invoiceDate =	argument.getInvoiceDate();	
		this.sequenceNumber =	argument.getSequenceNumber();   //EI20110928
	}

	public void setChangedDestination(ChangedDestination argument) {
		if (argument == null) {
			return;
		}
		this.destinationTypeCode = argument.getDestinationTypeCode();			
		this.deliveryPlaceCustomsOffice = argument.getDeliveryPlaceCustomsOffice();
		this.newConsignee = argument.getNewConsignee();
		this.deliveryPlace = argument.getDeliveryPlace();	
	}

	public List<TransportDetails> getTransportDetailsList() {
		return transportDetailsList;
	
	}
}
