package com.kewill.kcx.component.mapping.countries.ie.ie2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyLocalAppResultKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module      : IrelandToKids<br>
 * Created     : 17.06.2014<br>
 * Description : Mapping of CustomsMatters-Format into KIDS-Format of Confirm message.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapConfirmIK extends KidsMessage {
	private BodyLocalAppResultKids		body   = null;
	//todo: private MsgAcknowledgement 			msgConf;
	private PositionError 				error;
	private List <PositionError>		errorList = new ArrayList<PositionError>();
	
	public MapConfirmIK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		//todo:msgConFail = new MsgFailure();
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
        try {
        	body   = new BodyLocalAppResultKids(writer);
        	
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
            kidsHeader.writeHeader();

            /*  EI20130207: positive localApp muss ohne Body generiert werden, sonst wird als Fehler-Nachricht beim kunden interpretier,
             *  oder eben mit kcx.ini param: "message.returnConfirm   = false" ganz ausgeschaltet

             error = new PositionError();
            	error.setKindOfError("INF");
            	errorList.add(error);
            
            	msgConFail.setErrorList(errorList);
            	msgConFail.setErrorInBody(true);
            
            	body.setMessage(msgConFail);
            	body.writeBody();
             */
           
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
	            
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
