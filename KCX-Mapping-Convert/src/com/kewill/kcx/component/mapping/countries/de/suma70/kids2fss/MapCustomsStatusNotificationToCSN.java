package com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgCustomsStatusNotification;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgCSN;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnFLT;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.CsnOCI;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Manifest<br>
 * Created		: 17.12.2013<br>
 * Description	: Mapping of KIDS CustomsStatusNotification to CSN (keine FSS).
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MapCustomsStatusNotificationToCSN extends KidsMessageManifest20 {
	
	private MsgCustomsStatusNotification message;
	private MsgCSN msgCSN;

	public MapCustomsStatusNotificationToCSN(XMLEventReader parser, TsHead tsHead) throws XMLStreamException {		
		message = new MsgCustomsStatusNotification(parser);	
		msgCSN = new MsgCSN();
	}

	public String getMessage() {
    	String res = "";    	
    	    	
        try {         	
        	message.parse(HeaderType.KIDS);         	
           //getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());

        	if (message == null) {
        		return "";
        	}
        	msgCSN.setLine1("LCAG-LINE-1");
        	msgCSN.setLine2("LCAG-LINE-2");
        	msgCSN.setLine3("CSN/3");
        	//TsWBI:
        	if (message.getReferencedSpecification() != null) {
        		msgCSN.setWbiAwb(message.getReferencedSpecification().getSpecificationID());
        	}
        	//TsFLT:
        	String airports = "";
        	if (!Utils.isStringEmpty(message.getAirportOfArrival()) && 
        			!Utils.isStringEmpty(message.getAirportOfDeparture())) {
        		airports = message.getAirportOfDeparture() + message.getAirportOfArrival();
        	}
        	String dayMon = msgCSN.calculateDayMon(message.getDateTimeOfArrival());        	
        	if (message.getTransport() != null && 
        			!Utils.isStringEmpty(message.getTransport().getTransportationNumber())) {
        		//EI2014012123; msgCSN.setFltNr(message.getTransport().getTransportationNumber());
        		CsnFLT line = new CsnFLT();
        		line.setFlightNr(message.getTransport().getTransportationNumber());           		
        		line.setAirports(airports);
        		line.setDayMon(dayMon);
        		msgCSN.setFlt(line);
        	} 
        	//TsCND:
        	msgCSN.setCanDst(message.getOfficeOfEntry());
        	if (message.getDateTimeOfReceipt() != null) {
        		msgCSN.setDtnDateTime(message.getDateTimeOfReceipt());
        	}
        	if (message.getCustomsId() != null) {
        		msgCSN.setCnd(message.getCustomsId(), message.getTotalPieces());
        	}
        	//TsOCI:
        	if (message.getCustomsNotification() != null) {  
        		//msgCSN.setOciMrn(message.getCustomsNotification().getContent());
        		CsnOCI line = new CsnOCI();
        		line.setMrn(message.getCustomsNotification().getContent());
        		line.setType(message.getCustomsNotification().getContentCode());
        		line.setSubType(message.getCustomsNotification().getContentSubCode());
        		line.setLand(message.getCustomsNotification().getCountryCode());
        		msgCSN.setOci(line);
        	}
        	 
        	//in Zabis: basman_nl	wird aus dem CSN-Dateinamen genommen
        	//EI20140206: filename = this.getFilename(message.getAirportOfArrival()); 
        	
        	/*
        	if (this.getCommonFieldsDTO() != null) {
        		this.getCommonFieldsDTO().setFilename(filename);
        	}
           */
        	
        	//EI20140120: 
        	res = msgCSN.getCsnString();
            //res = msgCSN.getCsnStringWithNewlines(); //EI20140120
           
            Utils.log("(CustomsStatusNotificationToCSN getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();	        
	    }	   
	    return res;
	}
	public String getMessageWithKidsHeader() {	//EI20140122
		//alle Tags sollen von neuen Zeile anfangen (\r=UNIX, \n=WINDOWS
		String csn = this.getMessage();
		
		StringWriter xmlOutputString = new StringWriter();        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);            
            kidsHeader.setWriter((writer));
            
            writeStartDocument(encoding, "1.0");
            writer.writeCharacters("\n");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
           // writer.writeCharacters("\n");
            writer.writeCharacters(Utils.LF);
            
            kidsHeader.setMessageName("CustomsStatusNotification");
            //EI20140206: kidsHeader.setProcedure("SUMA"); 
            if (kidsHeader.getProcedure() != null && kidsHeader.getProcedure().equalsIgnoreCase("MANIFEST")) {
            	kidsHeader.setProcedure("SUMA");
            } else if (kidsHeader.getProcedure() != null && kidsHeader.getProcedure().equalsIgnoreCase("NCTS")) {
            	kidsHeader.setProcedure("NCTS"); 
            }
            
            String filename = this.getFilename(kidsHeader.getReceiver());   
            Utils.log("CSN-filename " + filename);
            //kidsHeader.setMessageID(filename);  //EI20140123
            getCommonFieldsDTO().setMessageReferenceNumber(filename);  //EI20140206
            getCommonFieldsDTO().setTargetMessageType("CSN"); //EI20140224
            
            //EI20140206
            
            //kidsHeader.writeHeader();
            kidsHeader.writeHeader13WithNewlines(); //EI20140121
            
            //BodyCustomsStatusNotificationCSN body = new BodyCustomsStatusNotificationCSN(writer);
            //body.setMessage(csn);             
            //body.writeBody();
            
            openElement("soap:Body");
            writer.writeCharacters("\n");
            openElement("CustomsStatusNotification");  
            writer.writeCharacters("\n");
            	writeElementAllWithNewline("CsnContent", csn);             	
            closeElement();
            writer.writeCharacters("\n");
            closeElement();
            writer.writeCharacters("\n");
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
           
            writer.flush();
            writer.close();
            
            Utils.log("KidsToCsnWithKidsHeader " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
	}
	private String getFilename(String destination) {
		String filename = "";
		String messageId = "";
		boolean test = false;
		
		if (Utils.isStringEmpty(destination)) {
			return "";
		}
		//EI20140206: jetzt wird destination mit kcx_id gefuellt
		String [] kcxId = destination.split("\\.");
		int len = kcxId.length;
		if (len > 1) {
			filename = kcxId[len - 1];
		}
		if (this.getKidsHeader() != null) {			
			if (!Utils.isStringEmpty(this.getKidsHeader().getMessageID())) {
				String [] msgId = this.getKidsHeader().getMessageID().split("-");
				len = msgId.length;
				messageId = msgId[len - 1 ];
			}
			if (this.getKidsHeader().getTestIndicator() != null && 
					this.getKidsHeader().getTestIndicator().equalsIgnoreCase("T")) {
				test = true;
			}
		} 
		if (messageId == null) {
			messageId = "";
		}
				
		if (test) {			
			filename = filename + ".CSN.TST." + messageId;
		} else {
			filename = filename + ".CSN.PRD." + messageId;	
		}
		return filename;
	}
}
