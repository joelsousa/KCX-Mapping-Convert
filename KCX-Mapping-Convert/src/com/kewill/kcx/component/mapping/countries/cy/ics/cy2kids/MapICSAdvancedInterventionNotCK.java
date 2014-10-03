package com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

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
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyAdvanceInterventionNotKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSAdvancedInterventionNotCK<br>
 * Created		: 23.06.2010<br>
 * Description	: ICSDeclarationAmendmentAcknowledgment (CC351A).
 * 
 * @author	Lassiter Caviles
 * @version	1.0.00
 *
 */
public class MapICSAdvancedInterventionNotCK extends KidsMessageCY {
	
	private BodyAdvanceInterventionNotKids body;
	private MsgCC351A msgCyprus = null;		
	
	public MapICSAdvancedInterventionNotCK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, CyprusHeader cyprusHeader) throws XMLStreamException {
			msgCyprus = new MsgCC351A(parser);
			this.encoding = encoding;
			this.kidsHeader = kidsHeader;			
		}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body =  new BodyAdvanceInterventionNotKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgCyprus.parse(HeaderType.CYPRUS);
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgAdvanceInterventionNot kidMsg = getKidsFromCyprus();
			
			if (msgCyprus.getHeahea() != null) {
	            getCommonFieldsDTO().setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());	            	
            }
			
			body.setKidsHeader(kidsHeader);
			body.setMessage(kidMsg);
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
        //MS20110816: this.kidsHeader.setInReplyTo(msgCyprus.getCorIdeMES25());
		//EI20140408: setInReplyTo(msgCyprus.getCorIdeMES25(), "CY");
        setInReplyTo(msgCyprus.getCorIdeMES25(), "CY");     //EI20140408 
		setMapping();
	}
	
	private MsgAdvanceInterventionNot getKidsFromCyprus() {
		MsgAdvanceInterventionNot msgKids = new MsgAdvanceInterventionNot();
		
		if (msgCyprus.getHeahea() != null) {
			msgKids.setMrn(msgCyprus.getHeahea().getMrn());
			msgKids.setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgCyprus.getHeahea()));
			msgKids.setTotalNumberPosition(msgCyprus.getHeahea().getTotalNumberPositions());
			msgKids.setShipmentNumber(msgCyprus.getHeahea().getShipmentNumber());
			msgKids.setConveyanceReference(msgCyprus.getHeahea().getConvyanceReference());
			msgKids.setNotificationDateTime(msgCyprus.getHeahea().getNotificationDateTime());
			msgKids.setRegistrationDateTime(msgCyprus.getHeahea().getRegistrationdateTime());
			msgKids.setAcceptedDateTime(msgCyprus.getHeahea().getAcceptedDateTime());
		}
		
		if (msgCyprus.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgCyprus.getTrarep(), "RepresentativeAddress"));
			/* EI20110707
			if (msgKids.getRepresentative() == null) {
				Party party = new Party("Representative");
				msgKids.setRepresentative(party);
			}
			*/	
		}
		
		if (msgCyprus.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgCyprus.getPerlodsumdec(), "PersonLodgingSumaAddress"));
			/* EI20110707
			if (msgKids.getPersonLodgingSuma() == null) {
				Party party = new Party("PersonLodgingSuma");
				msgKids.setPersonLodgingSuma(party);
			}
			*/	
		}
		if (msgCyprus.getTracarent() != null) {
			msgKids.setCarrier(setParty(msgCyprus.getTracarent(), "CarrierAddress"));
			/* EI20110707
			if (msgKids.getCarrier() == null) {
				Party party = new Party("Carrier");
				msgKids.setCarrier(party);
			}
			*/	
		}
		
		if (msgCyprus.getCusoffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgCyprus.getCusoffent().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgCyprus.getCusoffent().getDeclaredDateOfArrival());
		}
		
		if (msgCyprus.getCusintList() != null) {
			List<CustomsIntervention> custIntList = new ArrayList<CustomsIntervention>();
			for (Cusint cusint : msgCyprus.getCusintList()) {
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
		
		if (msgCyprus.getGooIteGdsList() != null) {
			List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();
			for (Gooitegds goods : msgCyprus.getGooIteGdsList()) {
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
								if (tempMeans != null) {
									TransportMeans transportMeans = new TransportMeans();
									transportMeans.setTransportationCountry(tempMeans.getTransportationCountry());
									transportMeans.setTransportationNumber(tempMeans.getTransportationNumber());
									
									transportMeansList.add(transportMeans);
								}
							}
							goodsItem.setMeansOfTransportBorderList(transportMeansList);
						}
					goodsItemList.add(goodsItem);
				}
			}
			msgKids.setGoodsItemList(goodsItemList);
		}
		
		return msgKids;
	}
	
	private TransportMeans setTransportMeans(Heahea351 heahea) {
		if (heahea == null) {  //EI20110707
			return null;
		}
		TransportMeans transport =  new TransportMeans();
		
		transport.setTransportMode(heahea.getTransportMode());
		transport.setTransportationCountry(heahea.getTransportationCountry());
		transport.setTransportationNumber(heahea.getTransportationNumber());
		
		return transport;
	}
}
