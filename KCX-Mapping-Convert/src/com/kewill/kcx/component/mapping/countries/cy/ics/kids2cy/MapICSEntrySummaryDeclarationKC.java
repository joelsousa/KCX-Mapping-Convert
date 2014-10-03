package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MapICSEntrySummaryDeclarationKC
 * Created		: 01.06.2011
 * Description	: Mapping of KIDS Format to CYPRUS Format of ICSEntrySummaryDeclaration message (IE315A).
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationKC extends CyprusMessage {
	private MsgEntrySummaryDeclaration msgKids;
	
	public MapICSEntrySummaryDeclarationKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids = new MsgEntrySummaryDeclaration(parser);
		this.encoding	= encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		
		XMLOutputFactory	factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			
            writeStartDocument(encoding, "1.0");    // MS20110629
            
            msgKids.parse(HeaderType.KIDS);			
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
	        getCommonFieldsDTO().setTargetMessageType("CC315A");    // MS20110629
	        /*
	        Utils.updateMessageIdHistory(this.getKidsHeader().getMessageID(), this.getCyprusHeader().getMesIdeMES19(),
	        		this.getKidsHeader().getReceiver(), this.getKidsHeader().getCountryCode(),
	        		msgKids.getReferenceNumber(), "CC315A");	
	        */
			openElement("CC315A");			
				setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC315A.xsd");
				setAttribute("xmlns", "http://www.eurodyn.com");
				setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
					
				writeHeaderFields();
				writeBody();
				
			closeElement();
			
			writer.writeEndDocument();			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
	private void writeBody() {
		if (msgKids == null) {
			return;
		}
		try {				
			writeHeahea();
			
			/* EI20110707
			writeTraconco(msgKids.getConsignor());
			writeTraconce(msgKids.getConsignee());
			writeNotPar(msgKids.getNotifyParty());			
			writeGooIteGds(msgKids.getGoodsItemList());
			writeITI(msgKids.getCountryOfRoutingList());			
			writeCusOffLon(msgKids);
			writeTrarep(msgKids.getRepresentative());			
			writePerLodSumDec(msgKids.getPersonLodgingSuma());			
			writeSeaId(msgKids.getSealIDList());
			writeCusOfffEnt(msgKids);
			writeCusOffSent(msgKids.getCustomsOfficeOfSubsequentEntryList());
			writeTraCaRent(msgKids.getCarrier());
			*/			
			writeTraderConco(msgKids.getConsignor(), "TRACONCO1");  
			writeTraderConce(msgKids.getConsignee(), "TRACONCE1");  
			writeTraderNotpar(msgKids.getNotifyParty(), "NOTPAR670");  
			String kopfMode = "";  //EI20110714
			if (msgKids.getMeansOfTransportBorder() != null) {
				kopfMode = msgKids.getMeansOfTransportBorder().getTransportMode();
				if (Utils.isStringEmpty(kopfMode)) {
					kopfMode = "";
				}
			}
			writeGooIteGds(msgKids.getGoodsItemList(), "315", msgKids.getMeansOfTransportBorder());
			writeIti(msgKids.getCountryOfRoutingList());        
			writeCusOffLon(msgKids.getCustomsOfficeOfLodgment());		   	
			writeTraderRep(msgKids.getRepresentative(), "315");  		
			writeTraderPer(msgKids.getPersonLodgingSuma(), "PERLODSUMDEC");  
			writeSeaId(msgKids.getSealIDList()); 
			writeCusOffEntry(msgKids.getCustomsOfficeFirstEntry(), msgKids.getDeclaredDateOfArrival(), "315");  
			writeCusoffSent740(msgKids.getCustomsOfficeOfSubsequentEntryList());			
			writeTraderCar(msgKids.getCarrier(), "315");  
			//EI20110707-end
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeHeahea() {
		if (msgKids == null) {
			return;
		}
		try {			
			openElement("HEAHEA315");
			/*EI20111103: 
			  //writeElement("RefNumHEA4", msgKids.getReferenceNumber());			 
			String kcxId = getCommonFieldsDTO().getKcxId();
			String newRefnr = Utils.replaceRefnr(msgKids.getReferenceNumber(), kcxId);
			writeElement("RefNumHEA4", newRefnr); 
			*/
			if (this.getCyprusHeader() != null) {
				String newRefnr = this.getCyprusHeader().getMesIdeMES19();						
				if (Db.updateMessageIdHistory(msgKids.getReferenceNumber(), newRefnr, "CY")) {
					writeElement("RefNumHEA4", newRefnr);
				} else {
					writeElement("RefNumHEA4", msgKids.getReferenceNumber());
				}
			}						
			//EI20111103/04-end
			
			/* EI20110714:
			if (msgKids.getMeansOfTransportBorder() != null) {
                writeElement("TraModAtBorHEA76", msgKids.getMeansOfTransportBorder().getTransportMode());
                writeElement("IdeOfMeaOfTraCroHEA85", msgKids.getMeansOfTransportBorder().getTransportationNumber());
                writeElement("NatOfMeaOfTraCroHEA87", msgKids.getMeansOfTransportBorder().getTransportationCountry());
			}
			*/
			writeTransportBorder(msgKids.getMeansOfTransportBorder());  			//EI20110714		
			writeElement("TotNumOfIteHEA305", msgKids.getTotalNumberPositions());
			writeElement("TotNumOfPacHEA306", msgKids.getTotalNumberPackages());
			writeElement("TotGroMasHEA307", msgKids.getTotalGrossMass());
			writeElement("DecPlaHEA394", msgKids.getDeclarationPlace());
			//EI:writeElement("SpeCirInHEA1", msgKids.getSituationCode());
			writeElement("SpeCirInHEA1", msgKids.getSituationCode());                //EI20110713
			writeElement("TraChaMetOfPayHEA1", msgKids.getPaymentType());
			writeElement("ComRefNumHEA", msgKids.getShipmentNumber());
			writeElement("ConRefNumHEA", msgKids.getConveyanceReference());
			writeElement("PlaLoaGOOITE334", msgKids.getLoadingPlace());
			writeElement("PlaLoaGOOITE334LNG", msgKids.getLoadingPlaceLng());
			writeElement("PlaUnlGOOITE334", msgKids.getUnloadingPlace());
			writeElement("CodPlUnHEA357LNG", msgKids.getUnloadingPlaceLng());
			writeElement("DecDatTimHEA114", msgKids.getDeclarationTime());
			closeElement();			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeCusOffLon(String office) {
			if (Utils.isStringEmpty(office)) {     //EI20110712
				return;
			}
			try {
				openElement("CUSOFFLON315");
	            	writeElement("RefNumCOL1", office);
	            closeElement();	
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
	   }     
	 		
	

}
