package com.kewill.kcx.component.mapping.countries.de.ncts.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSUnloadingPermissionKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSUnloadingPermissionKK<br>
 * Created		: 03.03.2010<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of NCTSUnloadingPermission message. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class MapNCTSUnloadingPermissionKK extends KidsMessageNCTS {
	
	private BodyNCTSUnloadingPermissionKids body;
	private MsgNCTSUnloadingPermission message;
	
	public MapNCTSUnloadingPermissionKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgNCTSUnloadingPermission(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyNCTSUnloadingPermissionKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            kidsHeader.setWriter((writer));
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgNCTSUnloadingPermission(message);

            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("NCTS NCTSUnloadingPermission KidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
	
}
