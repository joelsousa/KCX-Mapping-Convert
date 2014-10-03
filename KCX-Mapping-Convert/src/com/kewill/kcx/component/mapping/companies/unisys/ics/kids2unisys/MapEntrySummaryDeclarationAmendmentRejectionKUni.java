package com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysMessageICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapEntrySummaryDeclarationAmendmentRejectionKUni<br>.
 * Erstellt		: 18.12.2010<br>
 * Beschreibung	: Mapping of KIDS-Format  of ICSEntrySummaryDeclarationAmendmentRejection message
 * 				: into UNISYS-Format of CUST-AWB-MSG
 * 
 * 
 * @author  iwaniuk 
 * @version 1.0.00
 */

public class MapEntrySummaryDeclarationAmendmentRejectionKUni extends UnisysMessageICS {	   
    private MsgEntrySummaryDeclarationAmendmentRejection msgKids;


	public MapEntrySummaryDeclarationAmendmentRejectionKUni(XMLEventReader parser, String encoding) 
															throws XMLStreamException {
		msgKids = new MsgEntrySummaryDeclarationAmendmentRejection(parser);		
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

            mapHeaderKidsToUnisys(unisysHeader, "IE305", "CUST-AWB-MSG");              

            //uidsHeader.writeHeader(getCommonFieldsDTO());
            
            msgKids.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
            unisysHeader.writeHeader(msgKids.getReferenceNumber());                                      
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
            	writeElement("CUST-AWB-ACTION", "AMEND");    //"AmendmentRejection");            	
            	openElement("CUST-RPT-INFO");
            		openElement("TRADER");            		
            			writeParticipant("L", msgKids.getPersonLodgingSuma());
            			writeParticipant("R", msgKids.getRepresentative());            		
            		closeElement();
            	closeElement();
            	   
            	openElement("CUST-RESP-INFO");
            	    writeCustRespRef(msgKids.getReferenceNumber());  //EI20110118
            		writeCustReqInfo(msgKids.getAmendmentRejectionDateAndTime());
            		openElement("CUST-RESP-REF");            		
        				writeElement("ENS-LREF", msgKids.getReferenceNumber());             			
        			closeElement();
        			if (msgKids.getFunctionalErrorList() != null) {
        				for (FunctionalError error : msgKids.getFunctionalErrorList()) {
        					writeErrorInfo(error);            		        					
        				}
        			}
        			openElement("CUST-REJ-INFO");            		
    		    		writeDateAndTime(msgKids.getAmendmentRejectionDateAndTime());   
    		    		//writeElement("REJ-CODE", msgKids.???);   //TODO woher???
    		    		//writeElement("REJ-TEXT", msgKids.???);  
    		    	closeElement();     	
            	closeElement();
                        	
            	openElement("DETAIL");              	
     		 	writeElement("CUST-ITEM", "1");                		     		 	
    			openElement("CUST-GOODS-INFO");
    				openElement("GDS-DETAIL");    					
    					openElement("ARRIVAL");
    						writeElement("PORT-CODE", msgKids.getCustomsOfficeFirstEntry());    						
    					closeElement(); 
    					openElement("CTRY-SPEC");
    					openElement("CUSTOMS");
							writeElement("MRN", msgKids.getMrn());    						
						closeElement();
						closeElement();
    				closeElement();
    			closeElement();
    			closeElement();
            	
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	 
	}
    
}
