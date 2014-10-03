package com.kewill.kcx.component.mapping.countries.de.suma70.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.manifest.BodyGoodsReleasedExternalUids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module        : Manifest / Suma<br>
 * Created       : 06.09.2013<br>
 * Description   : UidsBody of GoodsReleasedExternal.
 * 				 

 * @author iwaniuk
 * @version 2.0.00
 */

public class MapGoodsReleasedExternalKU extends UidsMessage {
	
	private BodyGoodsReleasedExternalUids body = null;
	private MsgGoodsReleasedExternal message = null;
	
	public MapGoodsReleasedExternalKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgGoodsReleasedExternal(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyGoodsReleasedExternalUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();           
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            body.setUidsHeader(uidsHeader);
            body.setMsgGoodsReleasedExternal(message);

            this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
}
