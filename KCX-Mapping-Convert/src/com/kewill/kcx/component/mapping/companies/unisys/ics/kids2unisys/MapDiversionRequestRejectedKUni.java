package com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustAwb;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustFlt;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysMessageICS;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapEntrySummaryDeclarationKU<br>
 * Erstellt		: 20.12.2010<br>
 * Beschreibung	: Mapping of KIDS-Format ICSDiversionRequestRejected into Unisys-Format of IE324.
 * 
 * @author  krzoska
 * @version 1.0
 */

public class MapDiversionRequestRejectedKUni extends UnisysMessageICS {
	
    private MsgDiversionRequestRejected msgKids;
    private MsgCustFlt msgCustFlt 	= null;   

	public MapDiversionRequestRejectedKUni(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids = new MsgDiversionRequestRejected(parser);
		msgCustFlt = new MsgCustFlt();
		this.encoding = encoding;
	}

	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	UnisysHeader unisysHeader = new UnisysHeader(writer);
            //body = new BodyEntrySummaryDeclarationUids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("S:Envelope");
            setAttribute("xmlns:S", "http://www.w3.org/2003/05/soap-envelope");
            openElement("S:Body");
            openElement("MSG-ENVELOPE");

            mapHeaderKidsToUnisys(unisysHeader, "IE324", "CUST-FLT-MSG");              

            //uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgKids.parse(HeaderType.KIDS);
            unisysHeader.writeHeader(msgKids.getReferenceNumber());
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
                                      
            writeDivBody();

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

	private void writeDivBody() {
	    try {
	    	openElement("MSG-BODY");
	      		openElement("CUST-FLT-MSG");    
	      			writeElement("CUST-MSG-ACTION", "Reject");
	        		if (!Utils.isStringEmpty(msgKids.getReferenceNumber()) || 
	        			!Utils.isStringEmpty(msgKids.getDeclarationRejectionDateAndTime()) || 
	        			msgKids.getFunctionalErrorList() != null) {
	            		writeCustRespInfoHead();
	        		}
	        	closeElement();
	        closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
	
	private void writeCustRespInfoHead() {
		try {
	    	openElement("CUST-RESP-INFO");
	    		if (!Utils.isStringEmpty(msgKids.getReferenceNumber())) {
		    		openElement("CUST-RESP-REF");
		    			writeElement("DIV-LREF", msgKids.getReferenceNumber());
		    		closeElement();
	    			
	    		}
 	 			if (msgKids.getFunctionalErrorList() != null) {
 	 				//writing CUST-ERR-INFO
 	 				for (FunctionalError error : msgKids.getFunctionalErrorList()) {
 			 	 		writeErrorInfo(error);
 	 				}
 	 			}
	    		if (!Utils.isStringEmpty(msgKids.getDeclarationRejectionDateAndTime())) {
	 		    	openElement("CUST-REJ-INFO");
			    		writeElement("DATE", getUnisysDate(msgKids.getDeclarationRejectionDateAndTime()));
			    		writeElement("TIME", getUnisysTime(msgKids.getDeclarationRejectionDateAndTime()));
			    		writeElement("REJ-TEXT", msgKids.getDeclarationRejectionReason());
		    		closeElement();
	    		}
	    	closeElement();  //CUST-RESP-INFO
		} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
	}
}
