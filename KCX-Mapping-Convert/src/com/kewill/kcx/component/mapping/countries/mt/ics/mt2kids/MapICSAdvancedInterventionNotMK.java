package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

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
 * Created		: 20.08.2013<br>
 * Description	: ICSAdvancedInterventionNot (CC351A).
 * 
 * @author	krzoska
 * @version	1.0.00
 *
 */
public class MapICSAdvancedInterventionNotMK extends KidsMessageMT {
	
	private BodyAdvanceInterventionNotKids body;
	private MsgCC351A msgMalta = null;		
	
	public MapICSAdvancedInterventionNotMK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding) throws XMLStreamException {
			msgMalta = new MsgCC351A(parser);
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
			
			msgMalta.parse(HeaderType.CYPRUS);
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgAdvanceInterventionNot kidsMsg = getKidsFromMalta();
			
			if (msgMalta.getHeahea() != null) {
	            getCommonFieldsDTO().setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());	            	
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
        setInReplyTo(msgMalta.getCorIdeMES25());

		setMapping();
	}
	
	private MsgAdvanceInterventionNot getKidsFromMalta() {
		MsgAdvanceInterventionNot msgKids = new MsgAdvanceInterventionNot();
		
		if (msgMalta.getHeahea() != null) {
			msgKids.setMrn(msgMalta.getHeahea().getMrn());
			msgKids.setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgMalta.getHeahea()));
			msgKids.setTotalNumberPosition(msgMalta.getHeahea().getTotalNumberPositions());
			msgKids.setShipmentNumber(msgMalta.getHeahea().getShipmentNumber());
			msgKids.setConveyanceReference(msgMalta.getHeahea().getConvyanceReference());
			msgKids.setNotificationDateTime(msgMalta.getHeahea().getNotificationDateTime());
			msgKids.setRegistrationDateTime(msgMalta.getHeahea().getRegistrationdateTime());
			msgKids.setAcceptedDateTime(msgMalta.getHeahea().getAcceptedDateTime());
		}
		
		if (msgMalta.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgMalta.getTrarep(), "RepresentativeAddress"));
		}
		
		if (msgMalta.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgMalta.getPerlodsumdec(), "PersonLodgingSumaAddress"));
		}
		if (msgMalta.getTracarent() != null) {
			msgKids.setCarrier(setParty(msgMalta.getTracarent(), "CarrierAddress"));
		}
		
		if (msgMalta.getCusoffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgMalta.getCusoffent().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgMalta.getCusoffent().getDeclaredDateOfArrival());
		}
		
		if (msgMalta.getCusintList() != null) {
			List<CustomsIntervention> custIntList = new ArrayList<CustomsIntervention>();
			for (Cusint cusint : msgMalta.getCusintList()) {
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
		
		if (msgMalta.getGooIteGdsList() != null) {
			List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();
			for (Gooitegds goods : msgMalta.getGooIteGdsList()) {
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
