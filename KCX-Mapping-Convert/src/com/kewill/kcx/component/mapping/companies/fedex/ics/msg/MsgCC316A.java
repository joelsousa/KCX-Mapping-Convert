package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea316;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC316A
 * Created		: 27.12.2010
 * Description	: ICSEntrySummaryDeclarationRejected message.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MsgCC316A extends KCXMessage {
	private String	transmitter;
	private String	receiver;
	private String	sentAtDate;
	private String	time;
	private String	timeZone;
	private String	messageID;
	private String	shipmentNumber;
	private String	messageName;
	
	private Heahea316	heahea;
	
	private FunctionalError				fcnError;
	private List < FunctionalError >	functionalErrorList;
	
	public MsgCC316A() {
		super();
	}
	
	public MsgCC316A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC316A(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public String getTransmitter() {
		return this.transmitter;
	}
	public void setTransmitter(String transmitter) {
		this.transmitter	= Utils.checkNull(transmitter);
	}
	
	public String getReceiver() {
		return this.receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver	= Utils.checkNull(receiver);
	}
	
	public String getSentAtDate() {
		return this.sentAtDate;
	}
	public void setSentAtDate(String sentAtDate) {
		this.sentAtDate	= Utils.checkNull(sentAtDate);
	}
	
	public String getTime() {
		return this.time;
	}
	public void setTime(String time) {
		this.time	= Utils.checkNull(time);
	}
	
	public String getTimeZone() {
		return this.timeZone;
	}
	public void setTimeZone(String tZone) {
		this.timeZone	= Utils.checkNull(tZone);
	}
	
	public String getMessageID() {
		return this.messageID;
	}
	public void setMessageID(String msgID) {
		this.messageID	= Utils.checkNull(msgID);
	}
	
	public String getShipmentNumber() {
		return this.shipmentNumber;
	}
	public void setShipmentNumber(String shpmentNo) {
		this.shipmentNumber	= Utils.checkNull(shpmentNo);
	}
	
	public String getMessageName() {
		return this.messageName;
	}
	public void setMessageName(String msgName) {
		this.messageName	= Utils.checkNull(msgName);
	}
	
	public Heahea316 getHeahea() {
		return this.heahea;
	}
	public void setHeahea(Heahea316 hh316) {
		this.heahea	= hh316;
	}
	
	public List< FunctionalError > getFunctionalErrorList() {
		return this.functionalErrorList;
	}
	public void addFunctionalErrorList(FunctionalError fcnError) {
		if (this.functionalErrorList == null) {
			this.functionalErrorList	= new Vector< FunctionalError >();
		}
		
		this.functionalErrorList.add(fcnError);
	}
	
	protected enum ECC316A {
		//Fedex
		EnveloppeMessage,
		MesSenMES3,
		MesRecMES6,
		HEAHEA,
		FUNERRER1
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC316A) tag) {
				case HEAHEA:	heahea	= new Heahea316(getScanner());
								heahea.parse(tag.name());
								break;
				
				case FUNERRER1:	fcnError	= new FunctionalError(getScanner());
								fcnError.parse(tag.name());
								addFunctionalErrorList(fcnError);
								break;
				
				default:
					return;
			}
		} else {
			switch((ECC316A) tag) {
				case MesSenMES3:	setTransmitter(value);
									break;
				
				case MesRecMES6:	setReceiver(value);
									break;
				
				default:
					return;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return ECC316A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
