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
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Iti;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Seaide529;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class MsgCC315A extends KCXMessage {

	private String 		transmitter;
	private String		receiver;
	private String 		sentAtDate;
	private String		time;
	private String		timeZone;
	private String		messageID;
	private String		testIndicator;
	private String		shipmentNumber;
	private String		messageName;

	
	private String declarationPlace;
	private Heahea heahea; 
	
	//Consignor
	private FedexAddress traconco1;
	//Consignee
	private FedexAddress traconce1;
	//NotifyPartyAddress
	private FedexAddress notpar670;
	
	private MsgCC315APos goodsItem;
	private List <MsgCC315APos>goodsItemList;
	
	//CustomsOfficeOfLodgement
	private Cusofflon			cusofflon;			
	
	//  REPRESENTATIVE) TRADER
	private FedexAddress		trarep;
	
	//  LODGING SUMMARY DECLARATION) PERSON
	private FedexAddress		perlodsumdec;

	

	//Itinerary
	private Iti					iti;
	private List<Iti>			itiList = null;
	
	//Seals
	private Seaide529 			seaide529;
	private List<Seaide529>		seaide529List = null;
	
	//CustomsOfficeOfFirstEntry
	private CustomsOffice		cusoffent730;		
	
	//CustomsOfficeOfSubsequentEntry
	private CustomsOffice		cusoffent740;
	private List<CustomsOffice> cusoffent740List = null; //99x
	
	
	public MsgCC315A() {
		super();
	}

	public MsgCC315A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgCC315A(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	// (ENTRY CARRIER) TRADER
	private FedexAddress 		tracarent601;
	
	protected enum ECC315A {
		EnveloppeMessage,
		MesSenMES3,
		MesRecMES6,
		MesIdeMES19,
		HEAHEA,
		TRACONCO1,
		TRACONCE1,
		NOTPAR670,
		GOOITEGDS,
		DecPlaHEA394,	  
		ITI,
		CUSOFFLON,
		TRAREP,
		PERLODSUMDEC,
		SEAID529,
		CUSOFFFENT730,
		CUSOFFSENT740,
		TRACARENT601;
       
	}
	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECC315A) tag) {
			case HEAHEA: 	heahea = new Heahea(getScanner());  	
						 	heahea.parse(tag.name());
						 	break;
			
			case TRACONCO1:	traconco1 = new FedexAddress(getScanner());  	
							traconco1.parse(tag.name());
							break;
		 	
			case TRACONCE1:	traconce1 = new FedexAddress(getScanner());  	
							traconce1.parse(tag.name());
							break;
			
			case NOTPAR670: notpar670 = new FedexAddress(getScanner());
							notpar670.parse(tag.name()); 
							break;
		 	
			case GOOITEGDS:	goodsItem = new MsgCC315APos(getScanner());
							goodsItem.parse(tag.name());
							addGoodsItemList(goodsItem);
							break;
			case ITI:		iti = new Iti(getScanner());
							iti.parse(tag.name());
							addItiList(iti);
							break;
			case CUSOFFLON: cusofflon = new Cusofflon(getScanner());
							cusofflon.parse(tag.name());
							break;
			case TRAREP:	trarep = new FedexAddress(getScanner());  	
							trarep.parse(tag.name());
							break;
			case PERLODSUMDEC:  perlodsumdec = new FedexAddress(getScanner());
			   				    perlodsumdec.parse(tag.name());
			   				   	break;
			
			case SEAID529: seaide529 = new Seaide529(getScanner());
						   seaide529.parse(tag.name());
						   addSeaide529List(seaide529);
						   break;
		
			case CUSOFFFENT730: cusoffent730 = new CustomsOffice(getScanner());
								cusoffent730.parse(tag.name());
							break;

			case CUSOFFSENT740: cusoffent740 = new CustomsOffice(getScanner());
								cusoffent740.parse(tag.name());
								addCusoffent740List(cusoffent740);
							break;
			
			case TRACARENT601:	tracarent601 = new FedexAddress(getScanner());
								tracarent601.parse(tag.name());
							break;
			default:
					return;
			}
		} else {
			switch ((ECC315A) tag) {			
			case MesSenMES3:	setTransmitter(value);
						break;
			case MesRecMES6: 	setReceiver(value);
						break;
			case MesIdeMES19:  setShipmentNumber(value);
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
			return ECC315A.valueOf(token);
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

	private void addGoodsItemList(MsgCC315APos goodsItem) {
		if (goodsItemList == null) {
            goodsItemList = new Vector<MsgCC315APos>();
		}

		goodsItemList.add(goodsItem);
	}


	private void addItiList(Iti iti) {
		if (itiList == null) {
			itiList = new Vector<Iti>();
		}
		itiList.add(iti);
		
	}

	private void addSeaide529List(Seaide529 seaide529) {
		if (seaide529List == null) {
			seaide529List = new Vector<Seaide529>();
		}
		seaide529List.add(seaide529);
	}
	
	private void addCusoffent740List(CustomsOffice customsOffice) {
		if (cusoffent740List == null) {
			cusoffent740List = new Vector<CustomsOffice>();
		}
		cusoffent740List.add(cusoffent740);
	}

	public FedexAddress getTraconco1() {
		return traconco1;
	
	}

	public void setTraconco1(FedexAddress traconco1) {
		this.traconco1 = traconco1;
	}

	public FedexAddress getTraconce1() {
		return traconce1;
	
	}

	public void setTraconce1(FedexAddress traconce1) {
		this.traconce1 = traconce1;
	}

	public FedexAddress getNotpar670() {
		return notpar670;
	
	}

	public void setNotpar670(FedexAddress notpar670) {
		this.notpar670 = notpar670;
	}

	public List<MsgCC315APos> getGoodsItemList() {
		return goodsItemList;
	}

	public List<Iti> getItiList() {
		return itiList;
	
	}

	public Cusofflon getCusofflon() {
		return cusofflon;
	
	}

	public FedexAddress getPerlodsumdec() {
		return perlodsumdec;
	
	}

	public FedexAddress getTrarep() {
		return trarep;
	
	}

	public List<Seaide529> getSeaide529List() {
		return seaide529List;
	
	}

	public CustomsOffice getCusoffent730() {
		return cusoffent730;
	
	}

	public CustomsOffice getCusoffent740() {
		return cusoffent740;
	
	}

	public List<CustomsOffice> getCusoffent740List() {
		return cusoffent740List;
	
	}

	public FedexAddress getTracarent601() {
		return tracarent601;
	
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

}
