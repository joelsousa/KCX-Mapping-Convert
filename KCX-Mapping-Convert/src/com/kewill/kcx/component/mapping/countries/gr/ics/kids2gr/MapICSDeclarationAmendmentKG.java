package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentKG<br>
 * Created		: 02.06.2011<br>
 * Description	: Mapping of KIDS-Format into Greece-Format of 
 * 				  ICSEntrySummaryDeclarationAmendment message (IE313).
 * 				
 * @author Lassiter Caviles.
 * @version 1.0.00
 */

public class MapICSDeclarationAmendmentKG extends GreeceMessage {

	private MsgDeclarationAmendment msgKids;
	
	public MapICSDeclarationAmendmentKG(XMLEventReader parser, String encoding)
	throws XMLStreamException {
		msgKids = new MsgDeclarationAmendment(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	
            writeStartDocument(encoding, "1.0");    

            msgKids.parse(HeaderType.KIDS);         
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
            getCommonFieldsDTO().setTargetMessageType("DCL_IE_313");      

            openElement("CC313A");
                writeHeaderFields();   
                //EI20110803: writeESDBody()
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
			writeGooIteGds(msgKids.getGoodsItemList(), "", msgKids.getMeansOfTransportBorder()); 
			writeIti(msgKids.getCountryOfRoutingList());                
			writeTraderRep(msgKids.getRepresentative(), "");    	
			writeTraderPer(msgKids.getPersonLodgingSuma(), "PERLODSUMDEC"); 
			writeSeaId(msgKids.getSealIDList());			
			writeCusOffEntry(msgKids.getCustomsOfficeFirstEntry(),  msgKids.getDeclaredDateOfArrival(), "");  
			writeCusoffSent740(msgKids.getCustomsOfficeOfSubsequentEntryList());
			writeTraderCar(msgKids.getCarrier(), "");   			
			
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }  
	}

	private void writeHeahea(MsgDeclarationAmendment msg) {
		if (msg == null) {
			return;
		}
		try {
			openElement("HEAHEA");
				writeElement("DocNumHEA5", msg.getMrn());				
				writeTransportBorder(msg.getMeansOfTransportBorder());  				
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
