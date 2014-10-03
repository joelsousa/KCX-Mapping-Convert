package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC351A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Connr2;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusint;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea351;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Idemeatragi970;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgAdvanceInterventionNot;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyAdvanceInterventionNotKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS GREECE<br>
 * Created		: 27.06.2011<br>
 * Description	: ICSAdvancedInterventionNotification.
 * 
 * @author	Frederick T.
 * @version	1.0.00
 *
 */
public class MapICSAdvancedInterventionNotGK extends KidsMessageCY {

	private BodyAdvanceInterventionNotKids body;
	private MsgCC351A msgGreece = null;
	
	public MapICSAdvancedInterventionNotGK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, GreeceHeader greeceHeader) throws XMLStreamException {
		
		msgGreece = new MsgCC351A(parser);
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;		
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body = new BodyAdvanceInterventionNotKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
//			msgGreece.parse(HeaderType.GREECE);
			msgGreece.parse(HeaderType.CYPRUS);   //EI20110802
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgAdvanceInterventionNot kidsMsg = getKidsFromGreece();
			
			if (msgGreece.getHeahea() != null) {
				getCommonFieldsDTO().setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			}
			
			body.setKidsHeader(kidsHeader);
			body.setMessage(kidsMsg);
			body.writeBody();
			
			closeElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void mapKidsHeaderFromMessage() {       
        //MS20110816: this.kidsHeader.setInReplyTo(msgGreece.getCorIdeMES25());
		//EI20140408: setInReplyTo(msgGreece.getCorIdeMES25()); 
        setInReplyTo(msgGreece.getCorIdeMES25(), "GR");       
		setMapping();
	}
	
	private MsgAdvanceInterventionNot getKidsFromGreece() {
		MsgAdvanceInterventionNot msgKids = new MsgAdvanceInterventionNot();
		
		if (msgGreece.getHeahea() != null) {
			msgKids.setMrn(msgGreece.getHeahea().getMrn());
			msgKids.setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgGreece.getHeahea()));
			msgKids.setTotalNumberPosition(msgGreece.getHeahea().getTotalNumberPositions());
			msgKids.setShipmentNumber(msgGreece.getHeahea().getShipmentNumber());
			msgKids.setConveyanceReference(msgGreece.getHeahea().getConvyanceReference());
			msgKids.setNotificationDateTime(msgGreece.getHeahea().getNotificationDateTime());
			msgKids.setRegistrationDateTime(msgGreece.getHeahea().getRegistrationdateTime());
			msgKids.setAcceptedDateTime(msgGreece.getHeahea().getAcceptedDateTime());
		}
		
		if (msgGreece.getGooIteGdsList() != null) {
			List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();
			for (Gooitegds goods : msgGreece.getGooIteGdsList()) {
				if (goods != null) {	
					
					GoodsItemShort goodsItem = new GoodsItemShort();
					goodsItem.setItemNumber(goods.getItemNumber());
					goodsItem.setShipmentNumber(goods.getShipmentNumber());
					
					if (goods.getProdocdc2List() != null) {
						List<IcsDocument> docList = new ArrayList<IcsDocument>();
							
						for (Prodocd2 tempDoc : goods.getProdocdc2List()) {
							if (tempDoc != null) {
								IcsDocument icsDocument = new IcsDocument();
								icsDocument.setType(tempDoc.getType());
								icsDocument.setReference(tempDoc.getReference());
								icsDocument	.setReferenceLng(tempDoc.getReferenceLng());
						
								docList.add(icsDocument);
							}
						}
						goodsItem.setDocumentList(docList);
					}
						
					if (goods.getConnr2List() != null) {
						List<String> container =  new ArrayList<String>();
								
						for (Connr2 tempCon : goods.getConnr2List()) {
							if (tempCon != null) {
								container.add(tempCon.getContainer());
							}
						}
						goodsItem.setContainersList(container);
					}
					if (goods.getIdemeatragi970List() != null) {
						List<TransportMeans> transportMeansList = new ArrayList<TransportMeans>();
							
						for (Idemeatragi970 tempMeans : goods.getIdemeatragi970List()) {
							TransportMeans transportMeans = new TransportMeans();
							transportMeans.setTransportationCountry(tempMeans.getTransportationCountry());
							transportMeans.setTransportationNumber(tempMeans.getTransportationNumber());
							transportMeansList.add(transportMeans);
						}
						goodsItem.setMeansOfTransportBorderList(transportMeansList);
					}
					goodsItemList.add(goodsItem);
				}
			}
			msgKids.setGoodsItemList(goodsItemList);
		}
	
		if (msgGreece.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgGreece.getTrarep(), "RepresentativeAddress"));		
		}
		
		if (msgGreece.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgGreece.getPerlodsumdec(), "PersonLodgingSumaAddress"));			
		}
		
		if (msgGreece.getCusoffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgGreece.getCusoffent().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgGreece.getCusoffent().getDeclaredDateOfArrival());
		}
		
		if (msgGreece.getTracarent() != null) {
			msgKids.setCarrier(setParty(msgGreece.getTracarent(), "CarrierAddress"));			
		}
		
		if (msgGreece.getCusintList() != null) {
			List<CustomsIntervention> custIntList = new ArrayList<CustomsIntervention>();
			for (Cusint cusint : msgGreece.getCusintList()) {
				if (cusint != null) {
					CustomsIntervention customsIntervention = new CustomsIntervention();
					customsIntervention.setItemNumber(cusint.getItemNumber());
					customsIntervention.setCode(cusint.getCode());
					customsIntervention.setText(cusint.getText());
					customsIntervention.setLng(cusint.getLng());
					
					custIntList.add(customsIntervention);
				}
			}
			msgKids.setCustomsInterventionList(custIntList);
		}
		return msgKids;
	}
	
	private TransportMeans setTransportMeans(Heahea351 heahea) {
		TransportMeans transport =  new TransportMeans();
		
		transport.setTransportMode(heahea.getTransportMode());
		transport.setTransportationCountry(heahea.getTransportationCountry());
		transport.setTransportationNumber(heahea.getTransportationNumber());
		return transport;
	}
}
