package com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSUnloadingPermissionKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSUnloadingPermissionUK<br>
 * Created		: 01.09.2010<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of NCTSUnloadingPermission message. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class MapNCTSUnloadingPermissionUK extends KidsMessageNCTS {
	
	private BodyNCTSUnloadingPermissionKids 	body;
	private MsgNCTSUnloadingPermission	 		message;
	
	public MapNCTSUnloadingPermissionUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
		throws XMLStreamException {
		message = new MsgNCTSUnloadingPermission(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyNCTSUnloadingPermissionKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgNCTSUnloadingPermission(message);            
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
	
}
