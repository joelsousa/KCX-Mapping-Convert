/*
 * Function    : MapErrInfKK.java
 * Titel       :
 * Date        : 11.03.2009
 * Author      : Kewill CSF / messer
 * Description : Mapping of KIDS-Format of ErrorInformation into KIDS-Format of ExportConfirmation
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

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyErrorInformationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapErrInfKK<br>
 * Erstellt     : 11.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of ErrorInformation message.
 * 
 * @author messer
 * @version 1.0.00
 */
public class MapErrInfKK extends KidsMessage {
	
	private BodyErrorInformationKids body = null;
	private MsgErrInf msgErrInf = null;
	
	public MapErrInfKK(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgErrInf = new MsgErrInf(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyErrorInformationKids(writer);
            body.setKidsHeader(kidsHeader);
            kidsHeader.setWriter((writer));
            
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgErrInf.parse(HeaderType.KIDS);
            body.setMsgErrInf(msgErrInf);
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgErrorInformation getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
