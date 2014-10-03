package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.ArrivalItems;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.CustomsOffice;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Impope;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC347A<br>
 * Created		: 28.12.2010<br>
 * Description 	: Contains Message Structure with fields used in MsgCC347A.
 *                 
 * @author Frederick T.
 * @version 1.0.00
 */
public class MsgCC347A extends KCXMessage {

	private String 			transmitter;
	private String 			receiver;
	private String 			sentAtDate;
	private String 			time;
	private String 			timeZone;
	private String 			shipmentNumber;
	private String 			messageName;
	
	private Heahea				heahea;
	private CustomsOffice		customsOfficeArrival;
	private FedexAddress		traderAtEntry;
	private FedexAddress		tracarent601;
	private List<Impope>		impope200List = null;
	private CustomsOffice		customsOffice;
	private ArrivalItems		arrivalItems = null;
	
	public MsgCC347A() {
		super();
	}
	
	public MsgCC347A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC347A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	protected enum ECC347A {
		EnveloppeMessage,
		MesSenMES3,
		MesRecMES6,
		HEAHEA,                      ArrivalOperation, ArrivalItems, TRACARENT601,CUSOFFENT730,
		CUSTOMSOFFICEOFARRIVAL,
		TRADERATENTRY,
		IMPOPE200;
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch	((ECC347A) tag) {
				case HEAHEA:
				case ArrivalOperation:
					heahea = new Heahea(getScanner());
					heahea.parse(tag.name());
					break;
				case ArrivalItems:
					arrivalItems = new ArrivalItems(getScanner());
					arrivalItems.parse(tag.name());
					break;

				case CUSTOMSOFFICEOFARRIVAL:
				case CUSOFFENT730:
					customsOfficeArrival = new CustomsOffice(getScanner());
					customsOfficeArrival.parse(tag.name());
					break;
				case TRADERATENTRY:
				case TRACARENT601:
					traderAtEntry = new FedexAddress(getScanner());
					traderAtEntry.parse(tag.name());
					break;
				case IMPOPE200:
					Impope wrkImpope200 = new Impope(getScanner());
					wrkImpope200.parse(tag.name());
					impope200List.add(wrkImpope200);
					break;
				default:
					return;
			}
		} else {
			switch ((ECC347A) tag) {
				case MesSenMES3:	setTransmitter(value);
					break;
				case MesRecMES6: 	setReceiver(value);
					break;

				default:
					return;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
	}

	@Override
	public Enum translate(String token) {
		try {
			return ECC347A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = Utils.checkNull(transmitter);
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = Utils.checkNull(receiver);
	}

	public String getSentAtDate() {
		return sentAtDate;
	}

	public void setSentAtDate(String sentAtDate) {
		this.sentAtDate = Utils.checkNull(sentAtDate);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = Utils.checkNull(time);
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = Utils.checkNull(timeZone);
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = Utils.checkNull(messageName);
	}

	public Heahea getHeahea() {
		return heahea;
	}

	public void setHeahea(Heahea heahea) {
		this.heahea = heahea;
	}

	public CustomsOffice getCustomsOfficeArrival() {
		return customsOfficeArrival;
	}

	public void setCustomsOfficeArrival(CustomsOffice customsOfficeArrival) {
		this.customsOfficeArrival = customsOfficeArrival;
	}

	public FedexAddress getTraderAtEntry() {
		return traderAtEntry;
	}

	public void setTraderAtEntry(FedexAddress traderAtEntry) {
		this.traderAtEntry = traderAtEntry;
	}

	public List<Impope> getImpope200List() {
		return impope200List;
	}

	public void setImpope200List(List<Impope> impope200List) {
		this.impope200List = impope200List;
	}

	public FedexAddress getTracarent601() {
		return tracarent601;
	
	}

	public void setTracarent601(FedexAddress tracarent601) {
		this.tracarent601 = tracarent601;
	}

	public ArrivalItems getArrivalItems() {
		return arrivalItems;
	
	}

	public void setArrivalItems(ArrivalItems arrivalItems) {
		this.arrivalItems = arrivalItems;
	}

	
}
