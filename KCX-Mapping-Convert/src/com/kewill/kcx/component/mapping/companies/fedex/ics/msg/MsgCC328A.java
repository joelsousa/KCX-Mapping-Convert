package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Cusofflon;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.CustomsOffice;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class MsgCC328A extends KCXMessage{
	
	private String				transmitter;
	private String				receiver;
	private String 				sentAtDate;
	private String				time;
	private String				timeZone;
	private String				messageID;
	private String				testIndicator;
	private String				shipmentNumber;
	private String				messageName;

	
	private String 				declarationPlace;
	
	private Heahea				heahea;
	
	private MsgCC328APos 		goodsItem;
	private List<MsgCC328APos> 	goodsItemList;
	
	//CustomsOfficeOfLodgement
	private Cusofflon			cusofflon;	
	
	//LODGING SUMMARY DECLARATION) PERSON
	private FedexAddress		perlodsumdec;
	
	//CustomsOfficeOfFirstEntry
	private CustomsOffice		cusoffent730;
	
	// (ENTRY CARRIER) TRADER
	private FedexAddress 		tracarent601;
	
	public MsgCC328A() {
		super();
	}
	
	public MsgCC328A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC328A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	protected enum ECC328A {
		EnveloppeMessage,
		MesSenMES3,
		MesRecMES6,
		HEAHEA,
		GOOITEGDS,
		CUSOFFLON,	
		PERLODSUMDEC,
		CUSOFFFENT730,
		TRACARENT601;
		
	}
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECC328A) tag) {
			case HEAHEA: 	heahea = new Heahea(getScanner());  	
						 	heahea.parse(tag.name());
						 	break;
			case GOOITEGDS:	goodsItem = new MsgCC328APos(getScanner());
							goodsItem.parse(tag.name());
							addGoodsItemList(goodsItem);
							break;
			case CUSOFFLON: cusofflon = new Cusofflon(getScanner());
							cusofflon.parse(tag.name());
							break;		
			case PERLODSUMDEC:  perlodsumdec = new FedexAddress(getScanner());
						    perlodsumdec.parse(tag.name());
						   	break;
			case CUSOFFFENT730: cusoffent730 = new CustomsOffice(getScanner());
							cusoffent730.parse(tag.name());
							break;
			case TRACARENT601:	tracarent601 = new FedexAddress(getScanner());
							tracarent601.parse(tag.name());
							break;	
			default:
				return;
			}
		} else {
			switch ((ECC328A) tag) {
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
			return ECC328A.valueOf(token);
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

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = Utils.checkNull(messageID);
	}

	public String getTestIndicator() {
		return testIndicator;
	}

	public void setTestIndicator(String testIndicator) {
		this.testIndicator = Utils.checkNull(testIndicator);
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

	public String getDeclarationPlace() {
		return declarationPlace;
	}

	public void setDeclarationPlace(String declarationPlace) {
		this.declarationPlace = Utils.checkNull(declarationPlace);
	}

	public Heahea getHeahea() {
		return heahea;
	}

	public void setHeahea(Heahea heahea) {
		this.heahea = heahea;
	}

	public FedexAddress getPerlodsumdec() {
		return perlodsumdec;
	}

	public CustomsOffice getCusoffent730() {
		return cusoffent730;
	}

	public FedexAddress getTracarent601() {
		return tracarent601;
	}

	public Cusofflon getCusofflon() {
		return cusofflon;
	}
	
	private void addGoodsItemList(MsgCC328APos goodsItem) {
		if (goodsItemList == null) {
            goodsItemList = new Vector<MsgCC328APos>();
		}

		goodsItemList.add(goodsItem);
	}
}
