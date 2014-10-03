package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCD917B;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.XmlErr805;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyLocalAppResultKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapLocalAppResultMK<br>
 * Created		: 20.08.2013<br>
 * Description	: LocalAppResultCK (CD917B).
 * 
 * @author	krzoska
 * @version	1.0.00
 */

public class MapLocalAppResultMK extends KidsMessageMT {

	private BodyLocalAppResultKids body;
	private MsgCD917B msgCyprus = null;
	private String date = null;
	private String time = null;
	
	public MapLocalAppResultMK(XMLEventReader parser, KidsHeader kidsHeader, String encoding,
												   MaltaHeader maltaHeader) throws XMLStreamException {
		msgCyprus = new MsgCD917B(parser);
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;	
		if (maltaHeader != null) {
			date = maltaHeader.getDatOfPreMES9();
			time = maltaHeader.getTimOfPreMES10();
		} 
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body = new BodyLocalAppResultKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgCyprus.parse(HeaderType.CYPRUS);   
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgFailure kidMsg = getKidsFromCyprus();
			
			if (msgCyprus.getHeahea() != null) {
	            //getCommonFieldsDTO().setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());	 
				getCommonFieldsDTO().setReferenceNumber(msgCyprus.getHeahea().getMrn());	
	        }
			
			body.setKidsHeader(kidsHeader);
			body.setMessage(kidMsg);
			body.writeBody();
			
	
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	    
			writer.flush();
			writer.close();
			
		} catch (XMLStreamException e) {
	        e.printStackTrace();
	    }
	}
	
	private void mapKidsHeaderFromMessage() {		
        // MS20110816 Begin
        // this.kidsHeader.setInReplyTo(msgCyprus.getCorIdeMES25());
        setInReplyTo(msgCyprus.getCorIdeMES25());
        // MS20110816 End
		
		setMapping();
	}
	
	private MsgFailure getKidsFromCyprus() {    //TODO das ganze Mapping ist unklar
		MsgFailure msg = new MsgFailure();
		String dateTime = "";
		
		if (msgCyprus.getHeahea() != null) {   
			msg.setUCRNumber(msgCyprus.getHeahea().getMrn());
			//msg.setReferenceNumber(msgCyprus.get????);		
			if (msgCyprus.getHeahea().getEntKeyHea200() != null) {
			dateTime = msgCyprus.getHeahea().getEntKeyHea200().getExpDatArrENTKEY201();  
			}
		} 
		
		/* EI20110714 das ist derfinitiv falsch, das feld ist 14-stellig
		if (Utils.isStringEmpty(msg.getReferenceNumber())) {    //EI20110713
			msg.setReferenceNumber(msgCyprus.getCorIdeMES25());
		}
		*/
		msg.setCorrectionNumber("0"); 							 //EI20110713
		msg.setProcedureType(this.kidsHeader.getProcedure());    //EI20110713
		
		if (msgCyprus.getErrList() != null) {
			List<PositionError> errorList = new ArrayList<PositionError>();
			for (XmlErr805 error805 : msgCyprus.getErrList()) {
				if (error805 != null) {
					PositionError error = new PositionError();
					error.setKindOfError("XML");
					error.setErrorCode(error805.getErrCodXMLER806());
					error.setModul(error805.getErrLocXMLER803());
					error.setErrorText(error805.getErrReaXMLER802());
					error.setOriginOfError(error805.getOriAttValXMLER804());
					//EI: error.setDateOfErrorMessage(dateTime);  <== ist expected date of arrival!  
					error.setDateOfErrorMessage(date);  //EI20110714
					error.setTimeOfErrorMessage(time);  //EI20110714
					
					errorList.add(error);
				}
			}
			msg.setErrorList(errorList);
		}
		return msg;
	}

}
