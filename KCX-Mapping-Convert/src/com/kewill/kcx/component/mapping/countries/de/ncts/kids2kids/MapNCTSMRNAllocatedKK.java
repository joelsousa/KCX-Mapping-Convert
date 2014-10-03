package com.kewill.kcx.component.mapping.countries.de.ncts.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSMRNAllocatedKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSMRNAllocatedKK<br>
 * Created		: 08.25.2010<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of NCTSMRNAllocated message. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */
public class MapNCTSMRNAllocatedKK extends KidsMessageNCTS {

	private BodyNCTSMRNAllocatedKids body;
	private MsgNCTSMRNAllocated message;
	
	public MapNCTSMRNAllocatedKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgNCTSMRNAllocated(parser);
		this.encoding = encoding;
	}
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyNCTSMRNAllocatedKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            kidsHeader.setWriter((writer));
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMsgNCTSMRNAllocated(message);

            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("NCTS NCTSMRNAllocated KidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	} 
	
}
