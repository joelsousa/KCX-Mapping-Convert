package com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MaltaMessage;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Module		: MapICSEntrySummaryDeclarationKM
 * Created		: 15.08.2013
 * Description	: Mapping of KIDS Format to MALTA Format of ICSEntrySummaryDeclaration message (IE315A).
 * 
 * xsd smb01\home1\dat\develop\Projekte\OK\customsXchange\Countries\Malta\ICS\SD-TradersRequirementDocument-AppendixD-v1.6.9\XSD
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationKM extends MaltaMessage {
	private MsgEntrySummaryDeclaration 	msgKids;
	private String						nmsp = "";				//Namespace + :
	
	public MapICSEntrySummaryDeclarationKM(XMLEventReader parser, 
										   String encoding, 
										   String nameSpace) throws XMLStreamException {
		msgKids = new MsgEntrySummaryDeclaration(parser);
		this.encoding	= encoding;
		if(!Utils.isStringEmpty(nameSpace)) {
			this.nmsp = nameSpace + ":";
		}
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		
		XMLOutputFactory	factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);

            msgKids.parse(HeaderType.KIDS);			
	        getCommonFieldsDTO().setTargetMessageType("CC315A");
			createRootTag();			
				if (getKidsHeader() != null) {
					writeTagsFromKidsHeader(getKidsHeader(), nmsp);
				}
				writeBody();
				
			closeElement();  //  openElement(nmsp + "CC315A");
			
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
			
			writeTraderConco(msgKids.getConsignor(), "TRACONCO1", nmsp);  
			writeTraderConce(msgKids.getConsignee(), "TRACONCE1", nmsp);  
			writeTraderNotpar(msgKids.getNotifyParty(), "NOTPAR670", nmsp);  
			String kopfMode = "";
			if (msgKids.getMeansOfTransportBorder() != null) {
				kopfMode = msgKids.getMeansOfTransportBorder().getTransportMode();
				if (Utils.isStringEmpty(kopfMode)) {
					kopfMode = "";
				}
			}
			writeGooIteGds(msgKids.getGoodsItemList(), "", msgKids.getMeansOfTransportBorder(), nmsp);
			writeIti(msgKids.getCountryOfRoutingList(), nmsp);        
			writeCusOffLon(msgKids.getCustomsOfficeOfLodgment());		   	
			writeTraderRep(msgKids.getRepresentative(), "", nmsp);  		
			writeTraderPer(msgKids.getPersonLodgingSuma(), "PERLODSUMDEC", nmsp);  
			writeSeaId(msgKids.getSealIDList(), nmsp); 
			writeCusOffEntry(msgKids.getCustomsOfficeFirstEntry(), msgKids.getDeclaredDateOfArrival(), "", nmsp);  
			writeCusoffSent740(msgKids.getCustomsOfficeOfSubsequentEntryList(), nmsp);			
			writeTraderCar(msgKids.getCarrier(), "315", nmsp);  
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeHeahea() {
		if (msgKids == null) {
			return;
		}
		try {			
			openElement(nmsp + "HEAHEA");
				if (getMaltaHeader() != null) {
					String newRefnr = this.getMaltaHeader().getMesIdeMES19();						
					if (Db.updateMessageIdHistory(msgKids.getReferenceNumber(), newRefnr, "MT")) {
						writeElement(nmsp + "RefNumHEA4", newRefnr);
					} else {
						writeElement(nmsp + "RefNumHEA4", msgKids.getReferenceNumber());
					}
				}						
				writeTransportBorder(msgKids.getMeansOfTransportBorder(), nmsp);		
				writeElement(nmsp + "TotNumOfIteHEA305", msgKids.getTotalNumberPositions());
				writeElement(nmsp + "TotNumOfPacHEA306", msgKids.getTotalNumberPackages());
				writeElement(nmsp + "TotGroMasHEA307", msgKids.getTotalGrossMass());
				writeElement(nmsp + "DecPlaHEA394", msgKids.getDeclarationPlace());
				writeElement(nmsp + "SpeCirInHEA1", msgKids.getSituationCode());
				writeElement(nmsp + "TraChaMetOfPayHEA1", msgKids.getPaymentType());
				writeElement(nmsp + "ComRefNumHEA", msgKids.getShipmentNumber());
				writeElement(nmsp + "ConRefNumHEA", msgKids.getConveyanceReference());
				writeElement(nmsp + "PlaLoaGOOITE334", msgKids.getLoadingPlace());
				writeElement(nmsp + "PlaLoaGOOITE334LNG", msgKids.getLoadingPlaceLng());
				writeElement(nmsp + "PlaUnlGOOITE334", msgKids.getUnloadingPlace());
				writeElement(nmsp + "CodPlUnHEA357LNG", msgKids.getUnloadingPlaceLng());
				writeElement(nmsp + "DecDatTimHEA114", msgKids.getDeclarationTime());
			closeElement();	// openElement("HEAHEA");
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeCusOffLon(String office) {
			if (Utils.isStringEmpty(office)) {
				return;
			}
			try {
				openElement(nmsp + "CUSOFFLON315");
	            	writeElement(nmsp + "RefNumCOL1", office);
	            closeElement();	
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
    }
	
	private void createRootTag() throws XMLStreamException {
		if(Utils.isStringEmpty(this.nmsp)) {
			openElement("http://ces.gov.mt/ics/schemas/CC315A", "CC315A");
		} else {
			openElement(this.nmsp + "CC315A");
		}
	}
}
