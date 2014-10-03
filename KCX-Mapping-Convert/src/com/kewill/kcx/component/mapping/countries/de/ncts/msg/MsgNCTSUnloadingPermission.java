package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgNCTSUnloadingPermission<br>.
 * Erstellt		: 31.08.2010<br>
 * Beschreibung	: 
 * 
 * @author iwaniuk
 * @version 4.0.00
 */

/**
 * Description:		Added fields for the message.
 * Date changed: 	01.09.2010    
 * @author pagdanganan
 * @version 1.0.00
 */

public class MsgNCTSUnloadingPermission extends KCXMessage {
	
	private String				msgName = "NCTSUnloadingPermission";
	private String				referenceNumber;
	private String				ucrNumber;
	private String				typeOfDeclaration;
	private String				placeOfTransfer;
	private String				destinationCountry;
	private String				dispatchCountry;
	private TransportMeans  	meansOfTransportDeparture;
	private String				transportInContainer;
	private String				acceptanceTime;
	private String				totalNumberPositions;
	private String				totalNumberPackages;
	private String				totalGrossMass;
	private TIN					principalTIN;
	private PartyNCTS				principal;
	private TIN					consignorTIN;
	private PartyNCTS				consignor;
	private TIN					consigneeTIN;
	private PartyNCTS				consignee;
	private TIN					destinationTraderTIN;
	private PartyNCTS				destinationTrader;
	private String 				officeOfDeparture;
	private String				officeOfPresentation;
	private String				officeOfDestination;
	private String				endOfPresentationDate;
	private String				continueUnloading;
	private String				totalNumberSeals;
	private List<SealNumber>	sealNumbersList = new ArrayList<SealNumber>();
	private List<MsgNCTSUnloadingPermissionPos>	goodsItemList =
		 						new ArrayList<MsgNCTSUnloadingPermissionPos>();
	private EFormat				acceptanceTimeFormat;	
	private EFormat				endOfPresentationDateFormat;
	
	private boolean				debug = false;
	
	public MsgNCTSUnloadingPermission() {
		super();
	}

	public MsgNCTSUnloadingPermission(XMLEventReader parser)
			throws XMLStreamException {
		super(parser);
	}

	private enum ENCTSUnloadingPermission {
		// KIDS: 					UIDS:
		ReferenceNumber,			LocalReferenceNumber,
		UCRNumber,					MRN,
		TypeOfDeclaration,			//same
		PlaceOfTransfer,			//same
		DestinationCountry,			CountryOfDestination,
		DispatchCountry,			CountryOfDispatch,
		MeansOfTransportDeparture,	TransportAtDeparture,
		TransportInContainer,		ContainerIndicator,
		AcceptanceTime,				DateOfAcceptance,
		TotalNumberPositions,		TotalNumberOfItems,
		TotalNumberPackages,		TotalNumberOfPackages,
		TotalCrossMass,		//EI20130221: tippfehler			
		TotalGrossMass,				//same
		SealNumbers,				SealsIdentity,
		Principal,					//same
		PrincipalTIN,				//---
		Consignor,					//same
		ConsignorTIN,				//---
		Consignee,					//same
		ConsigneeTIN,				//----
		DestinationTrader,			//same
		DestinationTraderTIN,		//---
		OfficeOfDeparture,			//same
		OfficeOfPresentation,		//same
		OfficeOfDestination,		//same
		ContinueUnloading,	ContinueUnlaoding,		//same
		TotalNumberSeals,			TotalNumberOfSeals,
		EndOfPresentationDate,		PresentationOfConsignment,
		GoodsItem;					//same
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENCTSUnloadingPermission) tag) {
			case TransportAtDeparture:
			case MeansOfTransportDeparture:
				meansOfTransportDeparture = new TransportMeans(getScanner());
				meansOfTransportDeparture.parse(tag.name());
				break;
			case Principal:
				principal = new PartyNCTS(getScanner());
				principal.parse(tag.name());
				if (principal.getPartyTIN() != null) {
					setPrincipalTIN(principal.getPartyTIN());
				}
				break;
			case PrincipalTIN:
				principalTIN = new TIN(getScanner());
				principalTIN.parse(tag.name());			
				break;
			case Consignor:
				consignor = new PartyNCTS(getScanner());
				consignor.parse(tag.name());
				if (consignor.getPartyTIN() != null) {
					setConsignorTIN(consignor.getPartyTIN());
				}
				break;
			case ConsignorTIN:
				consignorTIN = new TIN(getScanner());
				consignorTIN.parse(tag.name());			
				break;	
			case Consignee:
				consignee = new PartyNCTS(getScanner());
				consignee.parse(tag.name());
				if (consignee.getPartyTIN() != null) {
					setConsigneeTIN(consignee.getPartyTIN());
				}
				break;
			case ConsigneeTIN:
				consigneeTIN = new TIN(getScanner());
				consigneeTIN.parse(tag.name());			
				break;
			case DestinationTrader:
				destinationTrader = new PartyNCTS(getScanner());
				destinationTrader.parse(tag.name());
				if (destinationTrader.getPartyTIN() != null) {
					setDestinationTraderTIN(destinationTrader.getPartyTIN());
				}
				break;
			case DestinationTraderTIN:
				destinationTraderTIN = new TIN(getScanner());
				destinationTraderTIN.parse(tag.name());			
				break;
			case SealNumbers:
				SealNumber sealNumber = new SealNumber(getScanner());
				sealNumber.parse(tag.name());	
				if (sealNumbersList == null) {
					sealNumbersList = new Vector <SealNumber>();
				}
				sealNumbersList.add(sealNumber);				
				break;
			case GoodsItem:
				MsgNCTSUnloadingPermissionPos item = new MsgNCTSUnloadingPermissionPos(getScanner());
				item.parse(tag.name());	
				if (goodsItemList == null) {
					goodsItemList = new Vector <MsgNCTSUnloadingPermissionPos>();
				}
				goodsItemList.add(item);				
				break;
			default:
				return;
			}
			
		} else {
			switch ((ENCTSUnloadingPermission) tag) {
			case LocalReferenceNumber:
			case ReferenceNumber:
				setReferenceNumber(value);
				break;
			case MRN:
			case UCRNumber:
				setUcrNumber(value);
				break;
			case TypeOfDeclaration:
				setTypeOfDeclaration(value);
				break;
			case PlaceOfTransfer:
				setPlaceOfTransfer(value);
				break;
			case CountryOfDestination:
			case DestinationCountry:
				setDestinationCountry(value);
				break;
			case CountryOfDispatch:
			case DispatchCountry:
				setDispatchCountry(value);
				break;
			case ContainerIndicator:
			case TransportInContainer:
				setTransportInContainer(value);
				break;
			case DateOfAcceptance:
			case AcceptanceTime:
				setAcceptanceTime(value);
				if (tag == ENCTSUnloadingPermission.DateOfAcceptance) {
					 setAcceptanceTimeFormat(EFormat.ST_Date);
				} else {
					 setAcceptanceTimeFormat(EFormat.KIDS_DateTime);   //EI20110525
				}
				break;
			case TotalNumberOfItems:
			case TotalNumberPositions:
				setTotalNumberPositions(value);
				break;
			case TotalNumberOfPackages:
			case TotalNumberPackages:
				setTotalNumberPackages(value);
				break;
			case TotalGrossMass:
			case TotalCrossMass:
				setTotalGrossMass(value);
				break;
			case OfficeOfDeparture:
				setOfficeOfDeparture(value);
				break;
			case OfficeOfPresentation:
				setOfficeOfPresentation(value);
				break;
			case OfficeOfDestination:
				setOfficeOfDestination(value);
				break;
			case ContinueUnlaoding:
			case ContinueUnloading:
				setContinueUnloading(value);
				break;
			case TotalNumberOfSeals:
			case TotalNumberSeals:
				setTotalNumberSeals(value);
				break;
			case SealsIdentity:
				SealNumber sealNumber = new SealNumber();
				sealNumber.setNumber(value);
				if (sealNumbersList == null) {
					sealNumbersList = new Vector <SealNumber>();
				}
				sealNumbersList.add(sealNumber);				
				break;
			
			case PresentationOfConsignment:
				setEndOfPresentationDate(value);                 //EI20110525
				setEndOfPresentationDateFormat(EFormat.ST_Date);
				break;
			case EndOfPresentationDate:
				setEndOfPresentationDate(value);
				setEndOfPresentationDateFormat(EFormat.KIDS_Date);
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
			return ENCTSUnloadingPermission.valueOf(token);
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

	public String getUcrNumber() {
		return ucrNumber;
	}

	public void setUcrNumber(String ucrNumber) {
		this.ucrNumber = ucrNumber;
	}

	public String getTypeOfDeclaration() {
		return typeOfDeclaration;
	}

	public void setTypeOfDeclaration(String typeOfDeclaration) {
		this.typeOfDeclaration = typeOfDeclaration;
	}

	public String getPlaceOfTransfer() {
		return placeOfTransfer;
	}

	public void setPlaceOfTransfer(String placeOfTransfer) {
		this.placeOfTransfer = placeOfTransfer;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getDispatchCountry() {
		return dispatchCountry;
	}

	public void setDispatchCountry(String dispatchCountry) {
		this.dispatchCountry = dispatchCountry;
	}

	public TransportMeans getMeansOfTransportDeparture() {
		return meansOfTransportDeparture;
	}

	public void setMeansOfTransportDeparture(
			TransportMeans meansOfTransportDeparture) {
		this.meansOfTransportDeparture = meansOfTransportDeparture;
	}

	public String getTransportInContainer() {
		return transportInContainer;
	}

	public void setTransportInContainer(String transportInContainer) {
		this.transportInContainer = transportInContainer;
	}

	public String getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}

	public String getTotalNumberPositions() {
		return totalNumberPositions;
	}

	public void setTotalNumberPositions(String totalNumberPositions) {
		this.totalNumberPositions = totalNumberPositions;
	}

	public String getTotalNumberPackages() {
		return totalNumberPackages;
	}

	public void setTotalNumberPackages(String totalNumberPackages) {
		this.totalNumberPackages = totalNumberPackages;
	}

	public String getTotalGrossMass() {
		return totalGrossMass;
	}

	public void setTotalGrossMass(String value) {
		this.totalGrossMass = value;
	}

	public TIN getPrincipalTIN() {
		return principalTIN;
	}

	public void setPrincipalTIN(TIN principalTIN) {
		this.principalTIN = principalTIN;
	}

	public PartyNCTS getPrincipal() {
		return principal;
	}

	public void setPrincipal(PartyNCTS principal) {
		this.principal = principal;
	}

	public TIN getConsignorTIN() {
		return consignorTIN;
	}

	public void setConsignorTIN(TIN consignorTIN) {
		this.consignorTIN = consignorTIN;
	}

	public PartyNCTS getConsignor() {
		return consignor;
	}

	public void setConsignor(PartyNCTS consignor) {
		this.consignor = consignor;
	}

	public TIN getConsigneeTIN() {
		return consigneeTIN;
	}

	public void setConsigneeTIN(TIN consigneeTIN) {
		this.consigneeTIN = consigneeTIN;
	}

	public PartyNCTS getConsignee() {
		return consignee;
	}

	public void setConsignee(PartyNCTS consignee) {
		this.consignee = consignee;
	}

	public TIN getDestinationTraderTIN() {
		return destinationTraderTIN;
	}

	public void setDestinationTraderTIN(TIN destinationTraderTIN) {
		this.destinationTraderTIN = destinationTraderTIN;
	}

	public PartyNCTS getDestinationTrader() {
		return destinationTrader;
	}

	public void setDestinationTrader(PartyNCTS destinationTrader) {
		this.destinationTrader = destinationTrader;
	}

	public String getOfficeOfDeparture() {
		return officeOfDeparture;
	}

	public void setOfficeOfDeparture(String officeOfDeparture) {
		this.officeOfDeparture = officeOfDeparture;
	}

	public String getOfficeOfPresentation() {
		return officeOfPresentation;
	}

	public void setOfficeOfPresentation(String officeOfPresentation) {
		this.officeOfPresentation = officeOfPresentation;
	}

	public String getOfficeOfDestination() {
		return officeOfDestination;
	}

	public void setOfficeOfDestination(String officeOfDestination) {
		this.officeOfDestination = officeOfDestination;
	}

	public String getContinueUnloading() {
		return continueUnloading;
	}

	public void setContinueUnloading(String argument) {
		this.continueUnloading = argument;
	}

	public String getTotalNumberSeals() {
		return totalNumberSeals;
	}

	public void setTotalNumberSeals(String totalNumberSeals) {
		this.totalNumberSeals = totalNumberSeals;
	}

	public List<SealNumber> getSealNumbersList() {
		return sealNumbersList;
	}

	public void setSealNumbersList(List<SealNumber> sealNumbersList) {
		this.sealNumbersList = sealNumbersList;
	}

	public List<MsgNCTSUnloadingPermissionPos> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<MsgNCTSUnloadingPermissionPos> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}

	public EFormat getAcceptanceTimeFormat() {
		return acceptanceTimeFormat;
	}

	public void setAcceptanceTimeFormat(EFormat acceptanceTimeFormat) {
		this.acceptanceTimeFormat = acceptanceTimeFormat;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setEndOfPresentationDate(String endOfPresentationDate) {
		this.endOfPresentationDate = endOfPresentationDate;
	}

	public String getEndOfPresentationDate() {
		return endOfPresentationDate;
	}

	public void setEndOfPresentationDateFormat(
			EFormat endOfPresentationDateFormat) {
		this.endOfPresentationDateFormat = endOfPresentationDateFormat;
	}

	public EFormat getEndOfPresentationDateFormat() {
		return endOfPresentationDateFormat;
	}

}
