package com.kewill.kcx.component.mapping.companies.fedex.ics.fedex2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.MsgCC347A;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.ArrivalItem;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalNotificationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSArrivalNotificationFK<br>
 * Created		: 28.12.2010<br>
 * Description	: Mapping of KIDS-Format into Fedex-Format of ICSArrivalNotification message (IE347).
 * 				
 * @author Frederick T.	
 * @version 1.0.00
 */
public class MapICSArrivalNotificationFK extends KidsMessageFDX {

	private BodyArrivalNotificationKids body = null;
	private MsgCC347A 		msgFedex = null;
	private FedexHeader 	fHeader = null;
	private String          kcxId = "";         //EI20110329
	
	public MapICSArrivalNotificationFK(XMLEventReader parser, KidsHeader kidsHeader,
			String encoding, FedexHeader fedexHeader) throws XMLStreamException {
				msgFedex  = new MsgCC347A(parser);
				this.encoding 	= encoding;
				this.kidsHeader = kidsHeader;
				fHeader 		= fedexHeader;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
			try {
				body = new BodyArrivalNotificationKids(writer);
				
				writeStartDocument(encoding, "1.0");
				openElement("soap:Envelope");
				setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
				
				msgFedex.parse(HeaderType.FEDEX);
				
				if (fHeader.getPartyId() != null && fHeader.getPartyId().length() > 1) {
	                fHeader.setCountryCode(fHeader.getPartyId().substring(0, 2));	            	
	            }
				
	            mapKidsHeaderFromMessage();
	            kidsHeader.writeHeader();
	            
	            MsgArrivalNotification kidsMsg = getKidsFromFedex();
	            
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
		//this.kidsHeader.setTransmitter(this.msgFedex.getTransmitter());
		this.kidsHeader.setTransmitter(fHeader.getPartyId());
				
		this.kidsHeader.setCountryCode(fHeader.getCountryCode());		
		//this.kidsHeader.setReceiver(fHeader.getPartyId());
		//kcxId = Utils.getKewillIdFromCustomer(fHeader.getPartyId(), "KIDS");
		//AK20120418
		kcxId = Utils.getKewillIdFromCustomer(fHeader.getPartyId(), "FEDEX");
		this.kidsHeader.setReceiver(kcxId);

		setMapping();
	}
		
	private MsgArrivalNotification getKidsFromFedex() {
		MsgArrivalNotification msgKids = new MsgArrivalNotification();
		
		if (msgFedex.getHeahea() != null) {
			msgKids.setReferenceNumber(msgFedex.getHeahea().getReferenceNumber());
			msgKids.setConveyanceReference(msgFedex.getHeahea().getTransportationNumber());
			msgKids.setMeansOfTransportBorder(setTransportMeansBorder(msgFedex.getHeahea()));
			msgKids.setDateOfPresentation(msgFedex.getHeahea().getDeclaredDateOfArrival());
			msgKids.setTotalNumberPositions(msgFedex.getHeahea().getTotalNumberPositions());
			msgKids.setCustomsOfficeFirstEntry(msgFedex.getHeahea().getBureauNotification());			
			msgKids.setCountryOfficeFirstEntry(msgFedex.getHeahea().getDeclaredCountryOfArrival());
		}
		
		if (msgFedex.getTraderAtEntry() != null) {
			msgKids.setCarrier(setParty(msgFedex.getTraderAtEntry(), "CarrierAddress"));
			//AK20110331
			if (msgKids.getCarrier() == null) {
				Party party = new Party("Carrier");
				msgKids.setCarrier(party);
			}	
			//checkPartyTin(kcxId, "IE347/CarrierTIN/TIN", msgKids.getCarrier());  //EI20110330
		}
		
		if (msgFedex.getArrivalItems() != null) {
			List<GoodsItemArn> goodsItemList = new ArrayList<GoodsItemArn>();
			if (msgFedex.getArrivalItems().getListArrivalItem() != null) {
				for (ArrivalItem arrivalItem : msgFedex.getArrivalItems().getListArrivalItem()) {
					if (arrivalItem != null) {
						GoodsItemArn goodsItem = new GoodsItemArn();
						goodsItem.setItemNumber(arrivalItem.getArrivalItemNumber());
						Prodocd2 tempDocument = arrivalItem.getTransportDocumentData();
						IcsDocument icsDocument = new IcsDocument();
						icsDocument.setType(tempDocument.getType());
						icsDocument.setReference(tempDocument.getReference());
						icsDocument.setReferenceLng(tempDocument.getReferenceLng());
						goodsItem.setDocument(icsDocument);
						if (arrivalItem.getCustomsDataReferences() != null &&
							arrivalItem.getCustomsDataReferences().getCustomsDataReference() != null) {
							String mrn = arrivalItem.getCustomsDataReferences().getCustomsDataReference().getMrn();
							EsumaDataReference temp = new EsumaDataReference();
							temp.setMrn(mrn);
							goodsItem.addEsumaDataReferenceList(temp);
					
					goodsItemList.add(goodsItem);
					}
					}
				}
				msgKids.setGoodsItemList(goodsItemList);
				
			}
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
