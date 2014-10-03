package com.kewill.kcx.component.mapping.countries.de.ncts20.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.formats.uids.ncts20.BodyNCTSUnloadingPermissionUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: NCTS<br>
 * Created		: 08.02.2013<br>
 * Description	: Mapping of KIDS-NCTSUnloadingPermission into UIDS-NCTSUnloadingPermission. 
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class MapNCTSUnloadingPermissionKU extends UidsMessageNCTS {

	private BodyNCTSUnloadingPermissionUids 	body;
	private MsgNCTSUnloadingPermission	 		message;
	
	public MapNCTSUnloadingPermissionKU(XMLEventReader parser, String encoding)
		throws XMLStreamException {
		message = new MsgNCTSUnloadingPermission(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyNCTSUnloadingPermissionUids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();   
            
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
           	body.setUidsHeader(uidsHeader);
           	body.setMsgNCTSUnloadingPermission(message);          
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("NCTS NCTSUnloadingPermission UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	} 
}
