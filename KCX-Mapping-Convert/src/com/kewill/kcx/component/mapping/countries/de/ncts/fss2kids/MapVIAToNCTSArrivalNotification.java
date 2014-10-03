package com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffData;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSArrivalNotificationKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MapVIAToNCTSArrivalNotification<br>
 * Created		: 2010.09.03<br>
 * Description	: Mapping of FSS to KIDS format of VIA.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class MapVIAToNCTSArrivalNotification extends KidsMessage  {
	private MsgVIA msgVIA;
	private MsgNCTSArrivalNotification msgNCTSArrivalNotification;
	
	public MapVIAToNCTSArrivalNotification() {
		msgVIA = new MsgVIA();
		msgNCTSArrivalNotification = new MsgNCTSArrivalNotification();
	}
	
	public void setMsgVIA(MsgVIA argument) {
		this.msgVIA = argument;
		this.setMsgFields();
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			KidsHeader header = new KidsHeader(writer);
			header.setHeaderFields(msgVIA.getVorSubset());
			header.setMessageName("ArrivalNotification");
			header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
	        header.writeHeader();
	        
	        BodyNCTSArrivalNotificationKids body = new BodyNCTSArrivalNotificationKids(writer);
	        body.setMessage(msgNCTSArrivalNotification);
	        
	        getCommonFieldsDTO().setReferenceNumber(msgNCTSArrivalNotification.getLocalReferenceNumber());
	        body.writeBody();
	        
	        closeElement();	//soap:Envolope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	        	Utils.log("(MapVIAToNCTSArrivalNotification getMessage) Msg = " + xmlOutputString.toString());
	        }
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	public void setMsgFields() {
		if (msgVIA.getViaSubset() != null && !msgVIA.getViaSubset().isEmpty()) {
			msgNCTSArrivalNotification.setmRN(msgVIA.getViaSubset().getMrn());
			msgNCTSArrivalNotification.setLocalReferenceNumber(msgVIA.getViaSubset().getBeznr());
			msgNCTSArrivalNotification.setArrivalNotificationDate(msgVIA.getViaSubset().getWgdat());
			//EI20110527: 
			//msgNCTSArrivalNotification.setArrivalNotificationDateFormat(msgVIA.getViaSubset().getWgdatFormat());
			msgNCTSArrivalNotification.setArrivalNotificationDateFormat(EFormat.KIDS_Date);
			EnRouteEvent enRouteEvent = new EnRouteEvent();
			Incident incident = new Incident();
			enRouteEvent.setIncident(incident);
			enRouteEvent.getIncident().setIncidentFlag(msgVIA.getViaSubset().getKzerg());
			msgNCTSArrivalNotification.setEnRouteEvent(enRouteEvent);
//			msgNCTSArrivalNotification.getEnRouteEvent().getIncident()
//				.setIncidentFlag(msgVIA.getViaSubset().getKzerg());
		}
		
		if (msgVIA.getVibSubset() != null) {
			msgNCTSArrivalNotification.setLocalReferenceNumber(msgVIA.getVibSubset().getBeznr());
			msgNCTSArrivalNotification.setPermitNumber(msgVIA.getVibSubset().getBewnr());
			msgNCTSArrivalNotification.setArrivalNotificationPlace(msgVIA.getVibSubset().getUbgort());
		}
		
		if (msgVIA.getVisSubset() != null) {
			msgNCTSArrivalNotification.setLocalReferenceNumber(msgVIA.getVisSubset().getBeznr());
			
			WriteOffSumA writeOffSumA = new WriteOffSumA();
			writeOffSumA.setReference(msgVIA.getVisSubset().getSuabez());
			writeOffSumA.setFlightNumber(msgVIA.getVisSubset().getFltnum());
//			msgNCTSArrivalNotification.getWriteOffSumA().setReference(msgVIA.getVisSubset().getSuabez());
//			msgNCTSArrivalNotification.getWriteOffSumA().setFlightNumber(msgVIA.getVisSubset().getFltnum());
			msgNCTSArrivalNotification.setWriteOffSumA(writeOffSumA);
		}
		
		if (msgVIA.getVipSubsetList()  != null) {
			for (TsVIP tsVip : msgVIA.getVipSubsetList()) {
				msgNCTSArrivalNotification.setLocalReferenceNumber(tsVip.getBeznr());
				
				WriteOffData writeOffData = new WriteOffData();
				
				writeOffData.setaWBNumber(tsVip.getAwbzzz());
				writeOffData.setItemNumber(tsVip.getSuapos());
				
				msgNCTSArrivalNotification.getWriteOffSumA().getWriteOffDataList().add(writeOffData);
			}
		}
	}
}

