package com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustFlt;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysMessageICS;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapLocalAppResultKUni<br>
 * Erstellt		: 09.02.2011<br>
 * Beschreibung	: Mapping of KIDS-Format of localAppResult message into Unisys-Format 
 * 					
 * @version 1.0
 */

public class MapLocalAppResultKUni extends UnisysMessageICS {
	
    private MsgFailure msgKids;
    private MsgCustFlt msgCustFlt 	= null;   

	public MapLocalAppResultKUni(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids = new MsgFailure(parser);
		msgCustFlt = new MsgCustFlt();
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
            
            mapHeaderKidsToUnisys(unisysHeader, "IE316", "CUST-AWB-MSG");               
                        
            msgKids.parse(HeaderType.KIDS);
            unisysHeader.writeHeader(msgKids.getReferenceNumber());
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
                                      
            writeAwbBody();

            closeElement();	// MSG-ENVELOPE
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
            
                writeElement("CUST-AWB-ACTION", "DECLARE");  //? DeclarationRejected
            	openElement("CUST-RESP-INFO");
            		writeCustRespRef(msgKids.getReferenceNumber());           		
            		if (msgKids.getErrorList() != null) {
            			for (Object error : msgKids.getErrorList()) {
            				PositionError  pe = (PositionError) error;
            				writeErrorInfo(pe);            		            				
            			}
            		}
        	   	closeElement();            	
                	                     
            closeElement();  //CUST-RESP-INFO
            closeElement();  //CUST-AWB-MSG
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
}