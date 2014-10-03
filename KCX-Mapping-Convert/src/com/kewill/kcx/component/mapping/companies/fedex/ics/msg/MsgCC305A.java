package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.CustomsOffice;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea305;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC305A<br>
 * Created		: 30.12.2010<br>
 * Description	: ICSDeclarationAmendmentAcknowledgment.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MsgCC305A extends KCXMessage {
	private String	transmitter;
	private String	receiver;
	private String	sentAtDate;
	private String	time;
	private String	timeZone;
	private String	messageID;
	private String	shipmentNumber;
	private String	messageName;
	
	private Heahea305	heahea;
	
	private FunctionalError			fcnError;
	private List< FunctionalError >	functionalErrorList;
	
	// REPRESENTATIVE) TRADER
	private FedexAddress	trarep;
	
	// LODGING SUMMARY DECLARATION) PERSON
	private FedexAddress	perlodsumdec;
	
	//CustomsOfficeOfFirstEntry
	private CustomsOffice	cusofffent730;
	
	public MsgCC305A() {
		super();
	}
	
	public MsgCC305A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgCC305A(XmlMsgScanner scanner) {
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
	public void setShipmentNumber(String shipmentNo) {
		this.shipmentNumber	= Utils.checkNull(shipmentNo);
	}
	
	public String getMessageName() {
		return this.messageName;
	}
	public void setMessageName(String msgName) {
		this.messageName	= Utils.checkNull(msgName);
	}
	
	public Heahea305 getHeahea() {
		return this.heahea;
	}
	public void setHeahea(Heahea305 hh305) {
		this.heahea	= hh305;
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
	
	public FedexAddress getTrarep() {
		return this.trarep;
	}
	public void setTrarep(FedexAddress trarep) {
		this.trarep	= trarep;
	}
	
	public FedexAddress getPerlodsumdec() {
		return this.perlodsumdec;
	}
	public void setPerlodsumdec(FedexAddress perlodsumdec) {
		this.perlodsumdec	= perlodsumdec;
	}
	
	public CustomsOffice getCusofffent730() {
		return this.cusofffent730;
	}
	public void setCusofffent730(CustomsOffice cusofffent730) {
		this.cusofffent730	= cusofffent730;
	}
	
	protected enum ECC305A {
		EnveloppeMessage,
		MesSenMES3,
		MesRecMES6,
		HEAHEA,
		FUNERRER1,
		TRAREP,
		PERLODSUMDEC,
		CUSOFFFENT730
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((ECC305A) tag) {
				case HEAHEA:		heahea	= new Heahea305(getScanner());
									heahea.parse(tag.name());
									break;
				
				case FUNERRER1:		fcnError	= new FunctionalError(getScanner());
									fcnError.parse(tag.name());
									addFunctionalErrorList(fcnError);
									break;
				
				case TRAREP:		trarep	= new FedexAddress(getScanner());
									trarep.parse(tag.name());
									break;
				
				case PERLODSUMDEC:	perlodsumdec	= new FedexAddress(getScanner());
									perlodsumdec.parse(tag.name());
									break;
				
				case CUSOFFFENT730:	cusofffent730	= new CustomsOffice(getScanner());
									cusofffent730.parse(tag.name());
									break;
				
				default:
					return;
			}
		} else {
			switch((ECC305A) tag) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
