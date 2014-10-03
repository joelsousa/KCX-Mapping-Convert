package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.NCTSTrader;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.ResultsOfControl;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.UnloadingRemark;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgNCTSUnloadingRemarks<br>
 * Created		: 09.01.2010<br>
 * Description	: Contains Tag Mapping for fields used in NCTSUnloadingRemarks message. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */

public class MsgNCTSUnloadingRemarks extends KCXMessage {
	private String msgName = "NCTSUnloadingRemarks";
	private String type;
	private String mRN;
	private Transport transportAtDeparture;
	private String totalNumberOfItems;
	private String totalNumberOfPackages;
	private String totalGrossMass;
	private NCTSTrader destinationTrader;
	private String officeOfPresentation;
	private UnloadingRemark unloadingRemark;
	private ResultsOfControl resultsOfControl;
	private String totalNumberOfSeals;
	private List<SealNumber> listSealNumbers = new ArrayList<SealNumber>();
	private EnRouteEvent enRouteEvent;
	//EI20110608: private MsgNCTSUnloadingRemarksPos goodsItem;
	private List<MsgNCTSUnloadingRemarksPos> goodsItemList = new ArrayList<MsgNCTSUnloadingRemarksPos>();

	private boolean debug   = false;
	private XMLEventReader parser = null;
	
	private enum EMsgNCTSUnloadingRemarks {
		//UIDS					//KIDS
		MRN,					UCRNumber,
		TransportAtDeparture,	MeansOfTransportDeparture,
		TotalNumberOfItems,		TotalNumberOfPositions,
		TotalNumberOfPackages,	TotalNumberPackages,
		TotalGrossMass,			//same
		TotalCrossMass,  //EI20130221 tippfehler
		DestinationTrader,		//same
		OfficeOfPresentation,	OfficeOfDeparture,
		UnloadingRemark,					//same
		ResultsOfControl,		//same
		TotalNumberOfSeals,		//same
		SealsIdentity,			SealNumbers,
		EnRouteEvent,			//same
		GoodsItem;				//same
	}
	
	public MsgNCTSUnloadingRemarks() {
		super();

	}

	public MsgNCTSUnloadingRemarks(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public MsgNCTSUnloadingRemarks(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
  	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgNCTSUnloadingRemarks) tag) {
			case TransportAtDeparture:
			case MeansOfTransportDeparture:
				Transport tempTransport = new Transport(getScanner());
				tempTransport.parse(tag.name());
				setTransportAtDeparture(tempTransport);
				break;
				
			case DestinationTrader:
				NCTSTrader tempDestinationTrader = new NCTSTrader(getScanner());
				tempDestinationTrader.parse(tag.name());
				setDestinationTrader(tempDestinationTrader);
				break;
			
			case UnloadingRemark:
				unloadingRemark = new UnloadingRemark(getScanner()); //EE20100914
				unloadingRemark.parse(tag.name());
				setUnloadingRemark(unloadingRemark);
				break;
				
			case ResultsOfControl:
				ResultsOfControl tempResultsOfControl = new ResultsOfControl(getScanner());
				tempResultsOfControl.parse(tag.name());
				setResultsOfControl(tempResultsOfControl);
				break;
			
			case SealNumbers:
				SealNumber tempSealNumber = new SealNumber(getScanner());
				tempSealNumber.parse(tag.name());
				listSealNumbers.add(tempSealNumber);
				break;
				
			case EnRouteEvent:
				EnRouteEvent tempEnRouteEvent = new EnRouteEvent(getScanner());
				tempEnRouteEvent.parse(tag.name());
				setEnRouteEvent(tempEnRouteEvent);
				break;
				
			case GoodsItem:				
				MsgNCTSUnloadingRemarksPos tempGoodsItem = new MsgNCTSUnloadingRemarksPos(getScanner());
				tempGoodsItem.parse(tag.name());
				addGoodsItemList(tempGoodsItem);
				break;
				
			default:
				return;
			}
		} else {
			switch ((EMsgNCTSUnloadingRemarks) tag) {
			case MRN:
			case UCRNumber:
				setmRN(value);
				break;
				
			case TotalNumberOfItems:
			case TotalNumberOfPositions:
				setTotalNumberOfItems(value);
				break;
				
			case TotalNumberOfPackages:
			case TotalNumberPackages:
				setTotalNumberOfPackages(value);
				break;
				
			case TotalGrossMass:
			case TotalCrossMass:
				setTotalGrossMass(value);
				break;
				
			case OfficeOfPresentation:
			case OfficeOfDeparture:
				setOfficeOfPresentation(value);
				break;
				
			case TotalNumberOfSeals:
				setTotalNumberOfSeals(value);
				break;
				
			case SealsIdentity:	
				SealNumber sealNumber = new SealNumber();
				
				sealNumber.setNumber(value);
				this.listSealNumbers.add(sealNumber);
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
  			return EMsgNCTSUnloadingRemarks.valueOf(token);
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

	public Transport getTransportAtDeparture() {
		return transportAtDeparture;
	}

	public void setTransportAtDeparture(Transport transportAtDeparture) {
		this.transportAtDeparture = transportAtDeparture;
	}

	public String getTotalNumberOfItems() {
		return totalNumberOfItems;
	}

	public void setTotalNumberOfItems(String totalNumberOfItems) {
		this.totalNumberOfItems = totalNumberOfItems;
	}

	public String getTotalNumberOfPackages() {
		return totalNumberOfPackages;
	}

	public void setTotalNumberOfPackages(String totalNumberOfPackages) {
		this.totalNumberOfPackages = totalNumberOfPackages;
	}

	public String getTotalGrossMass() {
		return totalGrossMass;
	}

	public void setTotalGrossMass(String totalGrossMass) {
		this.totalGrossMass = totalGrossMass;
	}

	public NCTSTrader getDestinationTrader() {
		return destinationTrader;
	}

	public void setDestinationTrader(NCTSTrader destinationTrader) {
		this.destinationTrader = destinationTrader;
	}

	public String getOfficeOfPresentation() {
		return officeOfPresentation;
	}

	public void setOfficeOfPresentation(String officeOfPresentation) {
		this.officeOfPresentation = officeOfPresentation;
	}

	public UnloadingRemark getUnloadingRemark() {
		return unloadingRemark;
	}

	public void setUnloadingRemark(UnloadingRemark unloadingRemark) {
		this.unloadingRemark = unloadingRemark;
	}

	public ResultsOfControl getResultsOfControl() {
		return resultsOfControl;
	}

	public void setResultsOfControl(ResultsOfControl resultsOfControl) {
		this.resultsOfControl = resultsOfControl;
	}

	public String getTotalNumberOfSeals() {
		return totalNumberOfSeals;
	}

	public void setTotalNumberOfSeals(String totalNumberOfSeals) {
		this.totalNumberOfSeals = totalNumberOfSeals;
	}

	public EnRouteEvent getEnRouteEvent() {
		return enRouteEvent;
	}

	public void setEnRouteEvent(EnRouteEvent enRouteEvent) {
		this.enRouteEvent = enRouteEvent;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MsgNCTSUnloadingRemarksPos> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<MsgNCTSUnloadingRemarksPos> list) {
		this.goodsItemList = list;
	}
	
	public void addGoodsItemList(MsgNCTSUnloadingRemarksPos item) {
		if (item == null) {
			return;
		}
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<MsgNCTSUnloadingRemarksPos>();
		}	
		goodsItemList.add(item);	
	}
	
	public List<SealNumber> getListSealNumbers() {
		return listSealNumbers;
	}

	public void setListSealNumbers(List<SealNumber> listSealNumbers) {
		this.listSealNumbers = listSealNumbers;
	}
}
