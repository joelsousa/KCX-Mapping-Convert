package com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustFlt;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysMessageICS;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalItemRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapArrivalItemRejection<br>
 * Erstellt		: 13.12.2010<br>
 * Beschreibung	: Mapping of KIDS-Format into Unisys-Format of ICSArrivalItemRejection message.
 * 					IE349
 * @version 1.0
 */

public class MapArrivalItemRejectionKUni extends UnisysMessageICS {
	
    //private BodyEntrySummaryDeclarationUids body;
    private MsgArrivalItemRejection msgKids;
    private MsgCustFlt msgCustFlt 	= null;   

	public MapArrivalItemRejectionKUni(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids = new MsgArrivalItemRejection(parser);
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
            
            mapHeaderKidsToUnisys(unisysHeader, "IE349", "CUST-FLT-MSG");               
                        
            msgKids.parse(HeaderType.KIDS);
            unisysHeader.writeHeader(msgKids.getReferenceNumber());
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
                                      
            writeFltBody();

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
	
	private void writeFltBody() {
		try {
            openElement("MSG-BODY");
            	openElement("CUST-FLT-MSG");    
            		writeElement("CUST-MSG-ACTION", "Reject");
            		writeCustRespInfoHead();
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
		    			writeElement("ARR-LREF", msgKids.getReferenceNumber());
	    			closeElement();
				}

	    		if ((!Utils.isStringEmpty(msgKids.getRejectionDateTime()) ||
		    		  !Utils.isStringEmpty(msgKids.getRejectionReasonHeader()))) {
 		    		openElement("CUST-REJ-INFO");
		    			writeElement("DATE", getUnisysDate(msgKids.getRejectionDateTime()));
		    			writeElement("TIME", getUnisysTime(msgKids.getRejectionDateTime()));
		    			writeElement("REJ-TEXT", msgKids.getRejectionReasonHeader());
	    			closeElement();
	    		}
	    		if (msgKids.getImportDetailsList() != null && msgKids.getImportDetailsList().size() > 0) {
	    			for (ImportDetails importDetails : msgKids.getImportDetailsList()) {
	    				if (!Utils.isStringEmpty(importDetails.getMrn())) {
		    				openElement("CUST-ACK-INFO");
		    					writeElement("MRN", importDetails.getMrn());
		    				closeElement();
	    				}
	    			}
	    		}
	    		writeFltDetail(msgKids.getImportDetailsList(), msgKids.getFunctionalErrorList());
	    	closeElement();  //CUST-RESP-INFO
		} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
	}

	private void writeFltDetail(List<ImportDetails>importDetailsList, List<FunctionalError>functionalErrorList) {
		try {
	 		 if (importDetailsList != null && importDetailsList.size() > 0) {
	  			openElement("DETAIL");  
	  			for (ImportDetails importDetails : importDetailsList) {
	  				if (Utils.isStringEmpty(importDetails.getItemNumber())) {
	  					writeElement("CUST-ITEM", importDetails.getItemNumber());
	  				} else {
	  					writeElement("CUST-ITEM", "1");
	  				}
	 	 			 
	 	 			openElement("CUST-RESP-INFO");
			 	 		if (!Utils.isStringEmpty(importDetails.getReferenceNumber())) {
			 	 			openElement("CUST-RESP-REF");
	 	 						writeElement("ENS-LREF", importDetails.getReferenceNumber());
	 	 					closeElement();
			 	 		}
			 	 		if (!Utils.isStringEmpty(importDetails.getRejectionReasonPos())) {
				 			openElement("CUST-REJ-INFO");
				 				writeElement("REJ-TEXT", importDetails.getRejectionReasonPos());
			 	 			closeElement();
			 	 		}
			 	 			 
		 	 			if (functionalErrorList != null && functionalErrorList.size() > 0) {
		 	 			 	for (FunctionalError error : functionalErrorList) {
		 			 	 		writeErrorInfo(error);
		 	 				}
		 	 			}
		 	 		closeElement();  //CUST-RESP-INFO
	 	 		}
	  			closeElement();
	  		 }			
		} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
	}
}