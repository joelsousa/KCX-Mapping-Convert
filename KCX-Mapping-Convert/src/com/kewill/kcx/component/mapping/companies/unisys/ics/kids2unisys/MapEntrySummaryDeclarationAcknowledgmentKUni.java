package com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysMessageICS;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapEntrySummaryDeclarationAcknowledgmentKUni<br>
 * Erstellt		: 18.12.2010<br>
 * Beschreibung	: Mapping of KIDS-Format of ISCEntrySummaryDeclarationAcknowledgment message.
 * 				: into UNISYS-Format of CUST-AWB-MSG
 * 
 * @author  iwaniuk
 * @version 1.0.00
 */

public class MapEntrySummaryDeclarationAcknowledgmentKUni extends UnisysMessageICS {
    private MsgEntrySummaryDeclarationAcknowledgment msgKids;   

	public MapEntrySummaryDeclarationAcknowledgmentKUni(XMLEventReader parser, String encoding) 
														throws XMLStreamException {
		msgKids = new MsgEntrySummaryDeclarationAcknowledgment(parser);		
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	UnisysHeader unisysHeader = new UnisysHeader(writer);
           
            writeStartDocument(encoding, "1.0");
            openElement("S:Envelope");
            setAttribute("xmlns:S", "http://www.w3.org/2003/05/soap-envelope");
            openElement("S:Body");
            openElement("MSG-ENVELOPE");
            //setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapHeaderKidsToUnisys(unisysHeader, "IE328", "CUST-AWB-MSG");             

            //uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgKids.parse(HeaderType.KIDS);
            unisysHeader.writeHeader(msgKids.getReferenceNumber());
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
                                      
            writeAwbBody();

            closeElement();  // MSG-ENVELOPE
            closeElement();  // S:Body
            closeElement();  // S:Envelope
            
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("ICS UnisysMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	} 

	private void writeAwbBody() {
		
	    try {
            openElement("MSG-BODY");
            openElement("CUST-AWB-MSG");     
            
            	writeElement("CUST-AWB-ACTION", "ACKNOWLEDGMENT");    //"DeclarationAcknowledgment");            	
            	openElement("CUST-RPT-INFO");
            		openElement("TRADER");            		
            			writeParticipant("L", msgKids.getPersonLodgingSuma());
            			writeParticipant("R", msgKids.getRepresentative());
            			writeParticipant("I", msgKids.getCarrier());            		
            		closeElement();
            	closeElement();
            	   
            	openElement("CUST-RESP-INFO");
            		writeCustRespRef(msgKids.getReferenceNumber());  //EI20110118
            		writeCustReqInfo(msgKids.getRegistrationDateAndTime());
            		writeCustAckInfo(msgKids.getMrn(), msgKids.getRegistrationDateAndTime());  //TODO in excel ohne mrn
            	closeElement();
            	
            	if (msgKids.getGoodsItemList() != null) {
            		for (GoodsItemShort goodsItem : msgKids.getGoodsItemList()) {        
            			//writeDetail(shipmentNumber, msgKids.getConveyanceReference(),  msgKids.getDeclaredDateOfArrival(),
            			writeDetail(msgKids.getShipmentNumber(), msgKids.getConveyanceReference(), "",   //TODO: declared ist für kidsAcknowledgement nicht definiert
            					msgKids.getCustomsOfficeFirstEntry(), msgKids.getMeansOfTransportBorder(),
            					goodsItem.getItemNumber(), goodsItem.getMeansOfTransportBorderList(),
            					goodsItem.getContainersList(), goodsItem.getDocumentList());            			
                	}
            	}
           
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
    
}
