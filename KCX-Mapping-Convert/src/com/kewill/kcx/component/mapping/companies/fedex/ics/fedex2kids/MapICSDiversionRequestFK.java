package com.kewill.kcx.component.mapping.companies.fedex.ics.fedex2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.MsgCC323A;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSDiversionRequestFK<br>
 * Created		: 27.12.2010<br>
 * Description	: Mapping of KIDS-Format into Fedex-Format of ICSDiversionRequest message (IE323).
 * 				
 * @author Edwin B.	
 * @version 1.0.00
 */
public class MapICSDiversionRequestFK extends KidsMessageFDX {
	
	private BodyDiversionRequestKids body = null;
	private MsgCC323A msgFedex = null;
	private FedexHeader fHeader = null;

public MapICSDiversionRequestFK(XMLEventReader parser, 
		KidsHeader kidsHeader, String encoding, FedexHeader fedexHeader) throws XMLStreamException {
	        msgFedex = new MsgCC323A(parser);
			this.encoding = encoding;
			this.kidsHeader = kidsHeader;
			fHeader 		= fedexHeader;
	}
	
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	        try {
	            body = new BodyDiversionRequestKids(writer);

	            writeStartDocument(encoding, "1.0");
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	          	            	
	            msgFedex.parse(HeaderType.FEDEX);
				
	            if (msgFedex.getReceiver() != null && msgFedex.getReceiver().length() > 1) {
	                fHeader.setCountryCode(msgFedex.getReceiver().substring(0, 2));	            	
	            }
				
	            mapKidsHeaderFromMessage();
	            kidsHeader.writeHeader();
	            
	            MsgDiversionRequest kidsMsg = getKidsFromFedex();
	            
	            if (msgFedex.getHeahea() != null) {
		            getCommonFieldsDTO().setReferenceNumber(
		            		msgFedex.getHeahea().getReferenceNumber());	            	
	            }

	            body.setKidsHeader(kidsHeader);
	            body.setMessage(kidsMsg);
	            body.writeBody();

	            closeElement();  // soap:Envelope
	            writer.writeEndDocument();

	            writer.flush();
	            writer.close();

	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }
	}
	
	private void mapKidsHeaderFromMessage() {
	
    String kcxId = "";
    //kcxId = Utils.getKewillIdFromCustomer(fHeader.getPartyId(), "KIDS");
    //AK20120418
    kcxId = Utils.getKewillIdFromCustomer(fHeader.getPartyId(), "FEDEX");
    // EI: oder besser von fHeader.getReceiver() ??? 
    // kcxId = Utils.getKewillIdFromCustomer(this.fHeader.getReceiver(), "KIDS");
        
	this.kidsHeader.setTransmitter(fHeader.getPartyId());
	this.kidsHeader.setReceiver(kcxId);
	this.kidsHeader.setCountryCode(fHeader.getCountryCode());			

	setMapping();
	}
	

	private MsgDiversionRequest getKidsFromFedex() {
		MsgDiversionRequest msgKids = new MsgDiversionRequest();
		
		if (msgFedex.getHeahea() != null) {
			msgKids.setReferenceNumber(msgFedex.getHeahea().getReferenceNumber());
			msgKids.setMeansOfTransportBorder(setTransportMeansBorder(msgFedex.getHeahea()));
			msgKids.setDeclaredCountryOfArrival(msgFedex.getHeahea().getDeclaredCountryOfArrival());
			msgKids.setInformationType(msgFedex.getHeahea().getInformationType());
			msgKids.setDeclaredDateOfArrival(msgFedex.getHeahea().getDeclaredDateOfArrival());
			msgKids.setDeclaredDateOfArrivalFormat(EFormat.KIDS_Date);
		}
		
		if (msgFedex.getCusoffentactoff700() != null) {
			msgKids.setActualOfficeOfFirstEntry(
					msgFedex.getCusoffentactoff700().getCustomsOfficeOfFirstEntry());
		}
		
		if (msgFedex.getCusoffent730() != null) {
			msgKids.setDeclaredOfficeOfFirstEntry(
					msgFedex.getCusoffent730().getCustomsOfficeOfFirstEntry());
		}
		
		if (msgFedex.getTrarep() != null) {
			msgKids.setSubmitter(setParty(msgFedex.getTrarep(), "SubmitterAddress"));
		}

		return msgKids;
	}

	private TransportMeans setTransportMeansBorder(Heahea heahea) {
		TransportMeans trm = new TransportMeans();

		trm.setTransportMode(heahea.getTransportMode());
		trm.setTransportationNumber(heahea.getTransportationNumber());
		
		return trm;
	}
		
}

