package com.kewill.kcx.component.mapping.countries.de.ncts20.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingRemarks;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.formats.uids.ncts20.BodyNCTSUnloadingRemarksUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: NCTS<br>
 * Created		: 09.02.2013<br>
 * Description	: Mapping of KIDS-Format into UIDS-Format of NCTSUnloadingRemarks message. 
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class MapNCTSUnloadingRemarksKU extends UidsMessageNCTS {
	private BodyNCTSUnloadingRemarksUids body;
	private MsgNCTSUnloadingRemarks	message;
	
	public MapNCTSUnloadingRemarksKU(XMLEventReader parser, String encoding)throws XMLStreamException {
		message = new MsgNCTSUnloadingRemarks(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
            uidsHeader = new UidsHeader(writer);
            body = new BodyNCTSUnloadingRemarksUids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();   
            
            uidsHeader.writeHeader(getCommonFieldsDTO());
            
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getmRN());
            
           	body.setUidsHeader(uidsHeader);
           	body.setMessage(message);            
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("NCTS NCTSUnloadingRemarks UidsMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return xmlOutputString.toString();
	}
}
