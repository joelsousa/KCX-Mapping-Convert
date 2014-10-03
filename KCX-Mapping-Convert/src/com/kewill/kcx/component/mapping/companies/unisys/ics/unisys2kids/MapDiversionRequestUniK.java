package com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.EUnisysParty;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.KidsMessageICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustFlt;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapDiversionRequestUniK<br>
 * Erstellt		: 07.12.2010<br>
 * Beschreibung	: Mapping of UNISYS-Format into KIDS-Format of Flight Diversion Request
 * 					IE323 in ../customseXchange/Customers/Unisys/UNISYS_To_KIDS.xlsx
 * @author krzoska
 * @version 1.0.00
 */
public class MapDiversionRequestUniK extends KidsMessageICS {


	private MsgCustFlt 				  		msgUnisys 	= null;
	private BodyDiversionRequestKids	 	body 		= null;
	
	private String countryTo = "";
		
	public MapDiversionRequestUniK(XMLEventReader parser, KidsHeader kidsHeader, 
			String encoding) throws XMLStreamException {
			msgUnisys  = new MsgCustFlt(parser);
	        
			this.kidsHeader	= kidsHeader;
			this.encoding = encoding;
			countryTo = kidsHeader.getCountryCode();
	}
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	     try {
	            body = new BodyDiversionRequestKids(writer);

	            writeStartDocument(encoding, "1.0");
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	          	            	
	            msgUnisys.parse(HeaderType.UNISYS);	           
	            kidsHeader.writeHeader();
	            countryTo = kidsHeader.getCountryCode();
	            MsgDiversionRequest kidsMsg = getKidsFromUnisys();
	            
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
		
	private MsgDiversionRequest getKidsFromUnisys() {
		MsgDiversionRequest msgKids = new MsgDiversionRequest();
		
		if (msgUnisys != null && msgUnisys.getCustFltInfo() != null) {
			
			if (msgUnisys.getCustFltInfo().getCtryInfo() != null)  {
				if (msgUnisys.getCustFltInfo().getCtryInfo().getRefNo() != null) {
					String divRef = msgUnisys.getCustFltInfo().getCtryInfo().getRefNo().getDivLref();
					if (!Utils.isStringEmpty(divRef)) {
						msgKids.setReferenceNumber(divRef);	
					} else {
						msgKids.setReferenceNumber(msgUnisys.getCustFltInfo().getCtryInfo().getRefNo().getArrLref());
					}
					
				}
				msgKids.setDeclaredCountryOfArrival(msgUnisys.getCustFltInfo().getCtryInfo().getCountry());
			}
			
			msgKids.setSubmitter(getPartyFromUnisys(msgUnisys.getCustRptInfo(), EUnisysParty.L, countryTo));
	
			if (msgUnisys.getCustFltInfo().getFlightInfo() != null) {
				msgKids.setMeansOfTransportBorder(
						getTransportMeansBorderFromUnisys(msgUnisys.getCustFltInfo().getFlightInfo(), null));
			}
			
			if (msgUnisys.getCustFltInfo().getDiversion() != null) {
				msgKids.setDeclaredOfficeOfFirstEntry(msgUnisys.getCustFltInfo().getDiversion().getPortCode());
			}
		}

		return msgKids;
	}
}
