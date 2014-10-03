package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.NCTSTrader;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: MsgNCTSArrivalNotification<br>
 * Created		: 08.25.2010<br>
 * Description	: Contains Tag Mapping for fields used in NCTSArrivalNotification message. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class MsgNCTSArrivalNotification extends KCXMessage {

	private String msgName = "NCTSArrivalNotification";
	private String mRN;
	private String localReferenceNumber;
	private String commercialReferenceNumber;
	private String customsSubPlace;
	private String arrivalNotificationPlace;
	private TIN authorisedConsigneeTIN;
	private String permitNumber;
	private String agreedLocationCode;
	private String agreedLocationOfGoods;
	private String authorisedLocationOfGoods;
	private String simplifiedProcedureFlag;
	private String arrivalNotificationDate;
	private String destLanguage;
	private NCTSTrader destinationTrader;
	private String presentationOffice;
	private EnRouteEvent enRouteEvent;
	private WriteOffSumA writeOffSumA;
	private EFormat	arrivalNotificationDateFormat;
	
	public MsgNCTSArrivalNotification() {
		super();				
	}

	public MsgNCTSArrivalNotification(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	private enum EMsgNCTSArrivalNotification {
		//UIDS							//KIDS
		MRN,							UCRNumber,
		LocalReferenceNumber,			ReferenceNumber,
		CommercialReferenceNumber,		ShipmentNumber,
		CustomsSubPlace,				//same
		ArrivalNotificationPlace,		PlaceOfUnloadingCode,
		AuthorisedConsigneeTIN,			//same
		AuthorisedConsigneeElectronicSignature, 	PermitNumber,
		AgreedLocationCode,				//same
		AgreedLocationOfGoods,			//same
		AuthorisedLocationOfGoods,		//same
		SimplifiedProcedureFlag,		SimplifiedProcedure,
		ArrivalNotificationDate,		//same
		DestLanguage,					//same
		DestinationTrader,				//same
		PresentationOffice,				//same
		EnRouteEvent,					//same
		WriteOffSumA;					//same
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgNCTSArrivalNotification) tag) {
			case DestinationTrader:
				NCTSTrader tempDestinationTrader = new NCTSTrader(getScanner());
				tempDestinationTrader.parse(tag.name());
				setDestinationTrader(tempDestinationTrader);
				break;
				
			case EnRouteEvent:
				EnRouteEvent tempEnRouteEvent = new EnRouteEvent(getScanner());
				tempEnRouteEvent.parse(tag.name());
				setEnRouteEvent(tempEnRouteEvent);
				break;
				
			case WriteOffSumA:
				WriteOffSumA tempWriteOffSumA = new WriteOffSumA(getScanner());
				tempWriteOffSumA.parse(tag.name());
				setWriteOffSumA(tempWriteOffSumA);
				break;
			
			case AuthorisedConsigneeTIN:
				authorisedConsigneeTIN = new TIN(getScanner());
				authorisedConsigneeTIN.parse(tag.name());				
				break;
				
			default:
				return;
			}
		} else {
			switch ((EMsgNCTSArrivalNotification) tag) {
			case MRN:
			case UCRNumber:
				setmRN(value);
				break;
				
			case LocalReferenceNumber:
			case ReferenceNumber:
				setLocalReferenceNumber(value);
				break;
				
			case CommercialReferenceNumber:
			case ShipmentNumber:
				setCommercialReferenceNumber(value);
				break;
				
			case CustomsSubPlace:
				setCustomsSubPlace(value);
				break;
				
			case ArrivalNotificationPlace:
			case PlaceOfUnloadingCode:
				setArrivalNotificationPlace(value);
				break;
				
			case AuthorisedConsigneeTIN:
				authorisedConsigneeTIN = new TIN();
				authorisedConsigneeTIN.setTIN(value);
				break;
				
			case AuthorisedConsigneeElectronicSignature:
			case PermitNumber:
				setPermitNumber(value);
				break;                                //EI20111006
				
			case AgreedLocationCode:
				setAgreedLocationCode(value);
				break;
				
			case AgreedLocationOfGoods:
				setAgreedLocationOfGoods(value);
				break;
				
			case AuthorisedLocationOfGoods:
				setAuthorisedLocationOfGoods(value);
				break;
				
			case SimplifiedProcedureFlag:
			case SimplifiedProcedure:
				setSimplifiedProcedureFlag(value);
				break;
				
			case ArrivalNotificationDate:
				setArrivalNotificationDate(value);
				if (value.indexOf('-') == -1) {
					setArrivalNotificationDateFormat(EFormat.KIDS_Date);
				} else {
					setArrivalNotificationDateFormat(EFormat.ST_Date);
				}
				break;
				
			case DestLanguage:
				setDestLanguage(value);
				break;
			
			case PresentationOffice:
				setPresentationOffice(value);
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
  			return EMsgNCTSArrivalNotification.valueOf(token);
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

	public String getmRN() {
		return mRN;
	}

	public void setmRN(String mRN) {
		this.mRN = mRN;
	}

	public String getLocalReferenceNumber() {
		return localReferenceNumber;
	}

	public void setLocalReferenceNumber(String localReferenceNumber) {
		this.localReferenceNumber = localReferenceNumber;
	}

	public String getCommercialReferenceNumber() {
		return commercialReferenceNumber;
	}

	public void setCommercialReferenceNumber(String commercialReferenceNumber) {
		this.commercialReferenceNumber = commercialReferenceNumber;
	}

	public String getCustomsSubPlace() {
		return customsSubPlace;
	}

	public void setCustomsSubPlace(String customsSubPlace) {
		this.customsSubPlace = customsSubPlace;
	}

	public String getArrivalNotificationPlace() {
		return arrivalNotificationPlace;
	}

	public void setArrivalNotificationPlace(String arrivalNotificationPlace) {
		this.arrivalNotificationPlace = arrivalNotificationPlace;
	}

	public String getAgreedLocationCode() {
		return agreedLocationCode;
	}

	public void setAgreedLocationCode(String agreedLocationCode) {
		this.agreedLocationCode = agreedLocationCode;
	}

	public String getAgreedLocationOfGoods() {
		return agreedLocationOfGoods;
	}

	public void setAgreedLocationOfGoods(String agreedLocationOfGoods) {
		this.agreedLocationOfGoods = agreedLocationOfGoods;
	}

	public String getAuthorisedLocationOfGoods() {
		return authorisedLocationOfGoods;
	}

	public void setAuthorisedLocationOfGoods(String authorisedLocationOfGoods) {
		this.authorisedLocationOfGoods = authorisedLocationOfGoods;
	}

	public String getSimplifiedProcedureFlag() {
		return simplifiedProcedureFlag;
	}

	public void setSimplifiedProcedureFlag(String simplifiedProcedureFlag) {
		this.simplifiedProcedureFlag = simplifiedProcedureFlag;
	}

	public String getArrivalNotificationDate() {
		return arrivalNotificationDate;
	}

	public void setArrivalNotificationDate(String arrivalNotificationDate) {
		this.arrivalNotificationDate = arrivalNotificationDate;
	}

	public String getDestLanguage() {
		return destLanguage;
	}

	public void setDestLanguage(String destLanguage) {
		this.destLanguage = destLanguage;
	}

	public NCTSTrader getDestinationTrader() {
		return destinationTrader;
	}

	public void setDestinationTrader(NCTSTrader destinationTrader) {
		this.destinationTrader = destinationTrader;
	}

	public String getPresentationOffice() {
		return presentationOffice;
	}

	public void setPresentationOffice(String presentationOffice) {
		this.presentationOffice = presentationOffice;
	}

	public EnRouteEvent getEnRouteEvent() {
		return enRouteEvent;
	}

	public void setEnRouteEvent(EnRouteEvent enRouteEvent) {
		this.enRouteEvent = enRouteEvent;
	}

	public WriteOffSumA getWriteOffSumA() {
		return writeOffSumA;
	}

	public void setWriteOffSumA(WriteOffSumA writeOffSumA) {
		this.writeOffSumA = writeOffSumA;
	}

	public EFormat getArrivalNotificationDateFormat() {
		return arrivalNotificationDateFormat;
	}

	public void setArrivalNotificationDateFormat(
			EFormat arrivalNotificationDateFormat) {
		this.arrivalNotificationDateFormat = arrivalNotificationDateFormat;
	}

	public void setAuthorisedConsigneeTIN(TIN authorisedConsigneeTIN) {
		this.authorisedConsigneeTIN = authorisedConsigneeTIN;
	}

	public TIN getAuthorisedConsigneeTIN() {
		return authorisedConsigneeTIN;
	}

	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}
	
}
