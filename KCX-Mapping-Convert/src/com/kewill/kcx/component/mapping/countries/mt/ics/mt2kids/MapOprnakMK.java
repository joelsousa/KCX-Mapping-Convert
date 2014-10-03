package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MsgOPRNAK;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyErrorInformationKids;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyLocalAppResultKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapOprnakMK<br>
 * Created		: 30.08.2013<br>
 * Description	: Functional Error Message from Malta.
 * 
 * @author	krzoska
 * @version	1.0.00
 */

public class MapOprnakMK extends KidsMessageMT {

	private BodyErrorInformationKids body;
	private MsgOPRNAK msgMalta = null;
	private String date = null;
	private String time = null;
	private MaltaHeader maltaHeader;
	
	public MapOprnakMK(XMLEventReader parser, KidsHeader kidsHeader, String encoding,
												   MaltaHeader maltaHeader) throws XMLStreamException {
		msgMalta = new MsgOPRNAK(parser);
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;	
		if (maltaHeader != null) {
			this.maltaHeader = maltaHeader;
			date = maltaHeader.getDatOfPreMES9();
			time = maltaHeader.getTimOfPreMES10();
		} 
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body = new BodyErrorInformationKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgMalta.parse(HeaderType.CYPRUS);   
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgErrInf kidMsg = getKidsFromCyprus();
			
			//if (msgMalta.getHeahea() != null) {
	            //getCommonFieldsDTO().setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());	 
				//getCommonFieldsDTO().setReferenceNumber(msgMalta.getgetHeahea().getMrn());	
	        //}
			
			body.setKidsHeader(kidsHeader);
			body.setMsgErrInf(kidMsg);
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
        // this.kidsHeader.setInReplyTo(msgMalta.getCorIdeMES25());
		if (maltaHeader != null) {
			setInReplyTo(maltaHeader.getMesIdeMES19());
		}
		setMapping();
	}
	
	private MsgErrInf getKidsFromCyprus() {    //TODO das ganze Mapping ist unklar
		MsgErrInf msg = new MsgErrInf();
		String dateTime = "";
		
		msg.setProcedureType(this.kidsHeader.getProcedure());    //EI20110713
		
		if (msgMalta.getOperationFailure() != null) {
			if (msgMalta.getOperationFailure().getErrorType() != null) {
				
				ErrorType error = new ErrorType();
				ArrayList<ErrorType> 				errorList = new ArrayList<ErrorType>();
				error.setCode(msgMalta.getOperationFailure().getErrorType());
				error.setText(msgMalta.getOperationFailure().getErrorMessage());
				
				errorList.add(error);
				
				msg.setErrorList(errorList);
			}
		}
		return msg;
	}

}
