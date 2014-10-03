/*
 * Function    : MapConfirmUK.java
 * Titel       :
 * Date        : 25.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of UIDS-Format into KIDS-Format of Confirmation
 *             : 
 * Parameters  : 
 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.uids2kids;

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
 * Modul        : MapConfirmUK<br>
 * Erstellt     : 25.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of Confirm message.
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapConfirmUK extends KidsMessage {
	private BodyLocalAppResultKids		body   = null;
	private MsgFailure 					msgConFail;
	private PositionError 				error;
	private List <PositionError>		errorList = new ArrayList<PositionError>();
	
	public MapConfirmUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgConFail = new MsgFailure();
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
