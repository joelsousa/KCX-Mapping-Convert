/*
 * Function    : MapExpConKK.java
 * Titel       :
 * Date        : 04.09.2008
 * Author      : Kewill CSF / messer
 * Description : Mapping of KIDS-Format of ExportConfirmation into KIDS-Format of ExportConfirmation
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

package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCon;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportConfirmationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapExpConKK<br>
 * Erstellt     : 09.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of ExportConfirmation message.
 * 
 * @author messer
 * @version 1.0.00
 */
public class MapExpConKK extends KidsMessage {
	
	private BodyExportConfirmationKids 	body   = null;
	private MsgExpCon 					msgExpCon;
	
	public MapExpConKK(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpCon = new MsgExpCon(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportConfirmationKids(writer);
            body.setKidsHeader(kidsHeader);
            kidsHeader.setWriter((writer));
            
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpCon.parse(HeaderType.KIDS);
            body.setMsgExpCon(msgExpCon);
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportConfirmation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
