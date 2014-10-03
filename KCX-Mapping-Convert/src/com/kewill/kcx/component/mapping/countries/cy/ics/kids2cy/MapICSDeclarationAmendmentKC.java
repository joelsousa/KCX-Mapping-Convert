package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentKC<br>
 * Created		: 02.06.2011<br>
 * Description	: Mapping of KIDS-Format into Cyprus-Format of 
 * 				  ICSEntrySummaryDeclarationAmendment message (IE313).
 * 				
 * @author Frederick T.
 * @version 1.0.00
 */

public class MapICSDeclarationAmendmentKC extends CyprusMessage {

	private MsgDeclarationAmendment msgKids;
	
	public MapICSDeclarationAmendmentKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids = new MsgDeclarationAmendment(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	
            writeStartDocument(encoding, "1.0");    // MS20110629
            
            msgKids.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
            getCommonFieldsDTO().setTargetMessageType("CC313A");                    // MS20110629   
            
        	openElement("CC313A");
            	setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC313A.xsd");
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
		try {			                   
			writeHeahea(msgKids);					
			writeTraderConco(msgKids.getConsignor(), "TRACONCO1");  
			writeTraderConce(msgKids.getConsignee(), "TRACONCE1");  
			writeTraderNotpar(msgKids.getNotifyParty(), "NOTPAR670");  				
			writeGooIteGds(msgKids.getGoodsItemList(), "313", msgKids.getMeansOfTransportBorder()); 
			writeIti(msgKids.getCountryOfRoutingList());                
			writeTraderRep(msgKids.getRepresentative(), "313");    	
			writeTraderPer(msgKids.getPersonLodgingSuma(), "PERLODSUMDEC"); 
			writeSeaId(msgKids.getSealIDList());			
			writeCusOffEntry(msgKids.getCustomsOfficeFirstEntry(),  msgKids.getDeclaredDateOfArrival(), "313");  
			writeCusoffSent740(msgKids.getCustomsOfficeOfSubsequentEntryList());
			writeTraderCar(msgKids.getCarrier(), "313");   			
			
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }  
	}

	private void writeHeahea(MsgDeclarationAmendment msg) {
		if (msg == null) {
			return;
		}
		try {
			openElement("HEAHEA313");
				writeElement("DocNumHEA5", msg.getMrn());
				/*EI20110713: 
				 * writeElement("TraModAtBorHEA76", msg.getReferenceNumber());
				if (msg.getMeansOfTransportBorder() != null || !msg.getMeansOfTransportBorder().isEmpty()) {
					writeElement("IdeOfMeaOfTraCroHEA85", msg.getMeansOfTransportBorder().getTransportMode());
				*/	
				writeTransportBorder(msg.getMeansOfTransportBorder());  //EI20110714				
				writeElement("TotNumOfIteHEA305", msg.getTotalNumberPositions());
				writeElement("TotNumOfPacHEA306", msg.getTotalNumberPackages());
				writeElement("TotGroMasHEA307", msg.getTotalGrossMass());
				writeElement("AmdPlaHEA598", msg.getDeclarationPlace());
				writeElement("SpeCirIndHEA1", msg.getSituationCode());
				writeElement("TraChaMetOfPayHEA1", msg.getPaymentType());
				writeElement("ComRefNumHEA", msg.getShipmentNumber());
				writeElement("ConRefNumHEA", msg.getConveyanceReference());
				writeElement("PlaLoaGOOITE334", msg.getLoadingPlace());
				writeElement("PlaLoaGOOITE334LNG", msg.getLoadingPlaceLng());
				writeElement("PlaUnlGOOITE334", msg.getUnloadingPlace());
				writeElement("CodPlUnHEA357LNG", msg.getUnloadingPlaceLng());
				writeElement("DatTimAmeHEA113", msg.getDeclarationTime());				
														
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}	

}
