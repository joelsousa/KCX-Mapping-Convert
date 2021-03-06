package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.CustomsOffice;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC323A<br>
 * Created		: 27.12.2010<br>
 * Description 	: Contains Message Structure with fields used in MsgCC323A.
 *                 
 * @author Edwin B.
 * @version 1.0.00
 */
public class MsgCC323A extends KCXMessage {

	private String 	transmitter;
	private String	receiver;
	private String 	sentAtDate;
	private String	time;
	private String	timeZone;
	private String	messageID;
	private String	testIndicator;
	private String	shipmentNumber;
	private String	messageName;
	
	private String 			declarationPlace;
	private Heahea	 		heahea; 
	private CustomsOffice 	cusoffentactoff700;
	private CustomsOffice 	cusoffent730;
	private FedexAddress	trarep;
	
	public MsgCC323A() {
		super();
	}

	public MsgCC323A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC323A(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	protected enum ECC323A {
		EnveloppeMessage,
		MesSenMES3,
		MesRecMES6,
		HEAHEA,
		CUSOFFENTACTOFF700,
		CUSOFFFENT730,
		TRAREQDIV456
    }
	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECC323A) tag) {
			case HEAHEA: 	
				heahea = new Heahea(getScanner());  	
				heahea.parse(tag.name());
				break;
			
			case CUSOFFENTACTOFF700:
				cusoffentactoff700 = new CustomsOffice(getScanner());
				cusoffentactoff700.parse(tag.name());
				break;
				
			case CUSOFFFENT730: 
				cusoffent730 = new CustomsOffice(getScanner());
				cusoffent730.parse(tag.name());
				break;
			case TRAREQDIV456:
				trarep = new FedexAddress(getScanner());
				trarep.parse(tag.name());
				break;
				
			default:
					return;
			}
		} else {
			switch ((ECC323A) tag) {			
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
			return ECC323A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
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


	public FedexAddress getTrarep() {
		return trarep;
	
	}

	public CustomsOffice getCusoffent730() {
		return cusoffent730;
	
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

	public String getMessageID() {
		return messageID;
	
	}

	public void setMessageID(String messageID) {
		this.messageID = Utils.checkNull(messageID);
	}

	public void setCusoffentactoff700(CustomsOffice cusoffentactoff700) {
		this.cusoffentactoff700 = cusoffentactoff700;
	}

	public CustomsOffice getCusoffentactoff700() {
		return cusoffentactoff700;
	}

}
