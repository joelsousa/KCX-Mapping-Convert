package com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.EUnisysParty;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.KidsMessageICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustFlt;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.GdsDetail;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalNotificationKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapArrivalNotificationUniK<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Mapping of UNISYS-Format into KIDS-Format of Flight Arrival Notification
 *                IE347 in UNISYS_To_KIDS.xlsx
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapArrivalNotificationUniK extends KidsMessageICS {


	private MsgCustFlt 				  		msgUnisys 	= null;
	private BodyArrivalNotificationKids 	body 		= null;

	private String countryTo = "";
		
	public MapArrivalNotificationUniK(XMLEventReader parser, KidsHeader kidsHeader, 
			String encoding) throws XMLStreamException {
			msgUnisys  = new MsgCustFlt(parser);
	        
			this.kidsHeader	= kidsHeader;
			this.encoding = encoding;
			countryTo = kidsHeader.getCountryCode();
	}
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	     try {
	            body = new BodyArrivalNotificationKids(writer);

	            writeStartDocument(encoding, "1.0");
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	          	            	
	            msgUnisys.parse(HeaderType.UNISYS);	           
	            kidsHeader.writeHeader();
	            
	            MsgArrivalNotification kidsMsg = getKidsFromUnisys();
	            countryTo = kidsHeader.getCountryCode();
	            if (msgUnisys.getCustFltInfo() != null && msgUnisys.getCustFltInfo().getCtryInfo() != null &&
	            		msgUnisys.getCustFltInfo().getCtryInfo().getRefNo() != null &&
	            		msgUnisys.getCustFltInfo().getCtryInfo().getRefNo().getArrLref() != null) {
		            getCommonFieldsDTO().setReferenceNumber(msgUnisys.getCustFltInfo().
		            		getCtryInfo().getRefNo().getArrLref());
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
	
	private MsgArrivalNotification getKidsFromUnisys() {
		MsgArrivalNotification msgKids = new MsgArrivalNotification();
		
		msgKids.setDateOfPresentation(kidsHeader.getSentAt());  //EI20110128
		if (msgUnisys != null && msgUnisys.getCustFltInfo() != null) {
			
			if (msgUnisys.getCustFltInfo().getCtryInfo() != null)  {
				if (msgUnisys.getCustFltInfo().getCtryInfo().getRefNo() != null) {
					msgKids.setReferenceNumber(msgUnisys.getCustFltInfo().getCtryInfo().getRefNo().getArrLref());
				}
				msgKids.setConveyanceReference(msgUnisys.getCustFltInfo().getCtryInfo().getConveyNo());
				msgKids.setCountryOfficeFirstEntry(msgUnisys.getCustFltInfo().getCtryInfo().getCountry());   //EI20110128
			}
			if (msgUnisys.getCustFltInfo().getArrival() != null) {
				msgKids.setDeclaredDateOfArrival(
						getDateTimeFromUnisys(msgUnisys.getCustFltInfo().getArrival().getSta()));
				msgKids.setDeclaredDateOfArrivalFormat(EFormat.KIDS_DateTime);
				msgKids.setCustomsOfficeFirstEntry(msgUnisys.getCustFltInfo().getArrival().getPortCode());
			}
			
			//AK20110214  if Participant L (PersonalLodgingENS) missing take I = Carrier 
			Party custRptInfoL = getPartyFromUnisys(msgUnisys.getCustRptInfo(), EUnisysParty.L, countryTo);
			
			if (custRptInfoL == null) {
				msgKids.setCarrier(getPartyFromUnisys(msgUnisys.getCustRptInfo(), EUnisysParty.I, countryTo));
			} else {
				//AK20110215 if Participant L (PersonalLodgingENS) present take L = Carrier
				msgKids.setCarrier(getPartyFromUnisys(msgUnisys.getCustRptInfo(), EUnisysParty.L, countryTo));
			}

			if (msgUnisys.getCustFltInfo().getFlightInfo() != null) {
				msgKids.setMeansOfTransportBorder(
						getTransportMeansBorderFromUnisys(msgUnisys.getCustFltInfo().getFlightInfo(), null));
			}

			if (msgUnisys.getDetailList() != null) {
				for (Detail detail : msgUnisys.getDetailList()) {
					if (detail != null) {
						writeGoodsItemList(detail, msgKids);
					}
				}
			}
		}

		return msgKids;
	}


	private void writeGoodsItemList(Detail detail, MsgArrivalNotification msgKids) {
		GoodsItemArn goodsItem = new GoodsItemArn();
		
		goodsItem.setItemNumber(detail.getCustItem());
		
		if (detail.getCustAwbInfo() != null && detail.getCustAwbInfo().getDocumentList() != null) {
			goodsItem.setDocument(getDocumentListFromUnisys(detail.getCustAwbInfo().getDocumentList()).get(0));
		}
		
		if (detail.getCustGoodsInfo() != null && detail.getCustGoodsInfo().getGdsDetailList() != null) {
			List <EsumaDataReference>esumaDataReferenceList = new Vector<EsumaDataReference>();
			for (GdsDetail gdsDetail : detail.getCustGoodsInfo().getGdsDetailList()) {
				EsumaDataReference esumaDataReference = new EsumaDataReference();
				if (gdsDetail != null && gdsDetail.getCtrySpec() != null && 
						gdsDetail.getCtrySpec().getCustoms() != null) {
					esumaDataReference.setItemNumberEsuma("1");
					esumaDataReference.setMrn(gdsDetail.getCtrySpec().getCustoms().getMrn());
					esumaDataReference.setCountryOfficeFirstEntry(gdsDetail.getCtrySpec().getCustoms().getCountry());
					esumaDataReferenceList.add(esumaDataReference);
					goodsItem.setEsumaDataReferenceList(esumaDataReferenceList);
					break;
				}
			}
		}
			
		msgKids.addGoodsItemList(goodsItem);
	}
}