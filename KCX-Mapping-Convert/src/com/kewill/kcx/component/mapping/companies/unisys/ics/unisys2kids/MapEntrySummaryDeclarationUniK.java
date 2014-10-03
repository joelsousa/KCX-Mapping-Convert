package com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids;

/**
 * Changes;
 * 
 */

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.EUnisysParty;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.KidsMessageICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustAwb;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustAwbInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.GdsDetail;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Segment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapCustAwbToKids<br>
 * Erstellt		: 06.12.2010<br>
 * Beschreibung	: Mapping of Unisys CUST-AWB-MSG Message to KIDS EntrySummaryDeclaration.
 * 				  IE315 in ../customseXchange/Customers/Unisys/UNISYS_To_KIDS.xlsx
 * @author iwaniuk  
 * @version 1.0.00
 */
public class MapEntrySummaryDeclarationUniK extends KidsMessageICS {


	private MsgCustAwb msgCustAwb 	= null;   	
	private MsgEntrySummaryDeclaration kidsMsg = null;
		
	private String countryTo = "";
		
	public MapEntrySummaryDeclarationUniK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																				throws XMLStreamException {
			msgCustAwb  = new MsgCustAwb(parser);
			kidsMsg = new MsgEntrySummaryDeclaration();
	        
			this.kidsHeader	= kidsHeader;			
			this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	     try {
	    	 BodyEntrySummaryDeclarationKids body = new BodyEntrySummaryDeclarationKids(writer);

	         writeStartDocument(encoding, "1.0");
	         openElement("soap:Envelope");
	         setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
	         kidsHeader.writeHeader();	         	          	            
	         msgCustAwb.parse(HeaderType.UNISYS);	 
			 countryTo = kidsHeader.getCountryCode();	         
	         getKidsFromUnisys(kidsMsg, msgCustAwb, countryTo);	         
	         getCommonFieldsDTO().setReferenceNumber(kidsMsg.getReferenceNumber());
	            
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
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            
            BodyEntrySummaryDeclarationKids body = new BodyEntrySummaryDeclarationKids(writer); 
            
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                 
            kidsHeader.setWriter((writer));
            kidsHeader.writeHeader(); 
            countryTo = kidsHeader.getCountryCode();
            msgCustAwb.parse(HeaderType.UNISYS);                                          
            getKidsFromUnisys(kidsMsg, msgCustAwb, countryTo);
            
            getCommonFieldsDTO().setReferenceNumber(kidsMsg.getReferenceNumber());
                     	
            body.setKidsHeader(kidsHeader);                  
            body.setMessage(kidsMsg); 
            body.writeBody();
                           
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapCustAwbToKids getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    } 	
	
	/*
	private void getKidsFromUnisys() {	
		
		kidsMsg.setDeclarationTime(getDeclarationTimeFromHeader()); 
		kidsMsg.setPersonLodgingSuma(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.L, countryTo));
		//EI20110407: eigentlich, in der Testdatei wurde nur "L" geschickt, "S" und "C" in (master)Details
		kidsMsg.setRepresentative(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.R, countryTo));
		kidsMsg.setCarrier(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.I, countryTo));
		//EI20110407: kidsMsg.setConsignor(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.S, countryTo));
		//EI20110407: kidsMsg.setConsignee(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.C, countryTo));
		kidsMsg.setNotifyParty(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.N, countryTo));
		
		// RESP ist fuer Response-messages
		int idx = 0;  //EI20110404:
		if (msgCustAwb.getDetailList() != null) {   // <== DETAIL kommt nur ein mal
			for (Detail detail : msgCustAwb.getDetailList()) {				
				// EI20110404: 
				//kidsMsg.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo));
				//getKopfTagsFromDetail(detail);   
				//
				idx = idx + 1;
				if (msgCustAwb.getDetailList().size() == 1) {
					getKopfTagsFromDetail(detail, "1");  
					kidsMsg.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo, 1));					 			
				} else if (msgCustAwb.getDetailList().size() > 1) {	
					if (idx == 1) {
						getKopfTagsFromDetail(detail, "" + (msgCustAwb.getDetailList().size() - 1));					
					} else {						
						kidsMsg.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo, idx));
					}
				}
				
			}
		}		
	}
	
	
	//EI20110404: private void getKopfTagsFromDetail(Detail detail) {
	private void getKopfTagsFromDetail(Detail detail, String totalNumberPositions) {
		if (detail == null) {
			return;
		}
		if (!Utils.isStringEmpty(detail.getCustItem()))  {
			Utils.log("(MapCustAwbToKids getMessage) Master = " + detail.getCustItem());
		}
		
		String originCountry = "";
		String ladingCountry = "";
		String unladingCountry = "";
		String destinationCountry = "";
		
		//EI20110404: kidsMsg.setTotalNumberPositions("1");   //Max: fix = 1
		kidsMsg.setTotalNumberPositions(totalNumberPositions); 
						
		if (detail.getCustAwbInfo() != null) {			
			kidsMsg.setPaymentType(detail.getCustAwbInfo().getPayCode());
			if (detail.getCustAwbInfo().getCustAWB() != null) {
				kidsMsg.setShipmentNumber(detail.getCustAwbInfo().getCustAWB().getAWB());
			}
			if (detail.getCustAwbInfo().getOrigin() != null) {
				originCountry = detail.getCustAwbInfo().getOrigin().getCountry();	
				if (!Utils.isStringEmpty(originCountry)) {
					kidsMsg.addCountryOfRoutingList(detail.getCustAwbInfo().getOrigin().getCountry());
				}
			}
			if (detail.getCustAwbInfo().getPartInfo() != null) {   //EI20110407
				kidsMsg.setConsignor(getPartyFromUnisys(detail.getCustAwbInfo().getPartInfo(), EUnisysParty.S, countryTo));
				kidsMsg.setConsignee(getPartyFromUnisys(detail.getCustAwbInfo().getPartInfo(), EUnisysParty.C, countryTo));
			}
			if (detail.getCustAwbInfo().getTotals() != null) {   //EI20110407				
				kidsMsg.setTotalNumberPackages(detail.getCustAwbInfo().getTotals().getPieces());
				kidsMsg.setTotalGrossMass(detail.getCustAwbInfo().getTotals().getWeight());								
			}
		}
				
		if (detail.getCustGoodsInfo() != null && detail.getCustGoodsInfo().getGdsDetailList() != null) {
			if (detail.getCustGoodsInfo().getGdsDetailList().size() > 0) {
				GdsDetail gds = detail.getCustGoodsInfo().getGdsDetailList().get(0);				
				if (gds != null) {						
					if (gds.getCtrySpec() != null && gds.getCtrySpec().getCustoms() != null) {
						kidsMsg.setReferenceNumber(gds.getCtrySpec().getCustoms().getEnsLref());	
					}
					if (gds.getManifested() != null) {
						if (Utils.isStringEmpty(kidsMsg.getTotalNumberPackages())) {
							kidsMsg.setTotalNumberPackages(gds.getManifested().getPieces());						
							kidsMsg.setTotalGrossMass(gds.getManifested().getWeight());
						}
					}
					if (gds.getFlightInfo() != null) {												
						kidsMsg.setMeansOfTransportBorder(getTransportMeansBorderFromUnisys(gds.getFlightInfo(), null));
						kidsMsg.setConveyanceReference(gds.getFlightInfo().getCcd() + gds.getFlightInfo().getFlight());
					}
					if (gds.getArrival() != null) {
						kidsMsg.setDeclarationPlace(gds.getArrival().getStation());
						kidsMsg.setCustomsOfficeFirstEntry(gds.getArrival().getPortCode());
						kidsMsg.setDeclaredDateOfArrival(getDateTimeFromUnisys(gds.getArrival().getSta()));
					}
					//EI20110407 neu, aber ist schon früher gemapped
					//if (!Utils.isStringEmpty(gds.getReportDate())) {
					//	kidsMsg.setDeclarationTime(gds.getReportDate());
					//}
				}
			
				Segment segment = detail.getCustGoodsInfo().getGdsDetailList().get(0).getSegment();						
				if (segment != null) {
					if (segment.getLading() != null) {						
						ladingCountry = segment.getLading().getCountry();					
						if (!Utils.isStringEmpty(ladingCountry) && !ladingCountry.equals(originCountry)) {
							kidsMsg.addCountryOfRoutingList(ladingCountry);
						}
					}
					if (segment.getUnlading() != null) {
						unladingCountry = segment.getUnlading().getCountry();
						if (!Utils.isStringEmpty(unladingCountry) && !unladingCountry.equals(originCountry)) {
							kidsMsg.addCountryOfRoutingList(unladingCountry);
						}
					}
				}
			}
		}
		
		if (detail.getCustAwbInfo() != null) {	
			if (detail.getCustAwbInfo().getDest() != null) {		
				destinationCountry = detail.getCustAwbInfo().getDest().getCountry();
				if (!Utils.isStringEmpty(destinationCountry) && !destinationCountry.equals(unladingCountry)) {
					kidsMsg.addCountryOfRoutingList(destinationCountry);
				}
			}
		}		
	}
	*/
		
}
