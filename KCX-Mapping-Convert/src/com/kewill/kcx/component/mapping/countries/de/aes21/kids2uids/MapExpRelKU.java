package com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.formats.uids.aes21.BodyExportReleaseUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2012<br>
 * Description	: Mapping of KIDS-Format of ReverseDeclaration into UIDS-Format of ExportRelease. 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class MapExpRelKU extends UidsMessage {
	private BodyExportReleaseUids body;
	private MsgExpRel message;
	
	public MapExpRelKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			message = new MsgExpRel(parser);
			message.setMsgName("ExportRelease");
			this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyExportReleaseUids(writer);
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
          
            message.parse(HeaderType.KIDS);
            message.setFromFormat("KIDS");      //EI20120913: for right Mapping of PreviousDocument in Kopf
            
            body.setMessage(message);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  //EI20120917
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            if (Config.getLogXML()) {
                Utils.log("(MapExpRelKU getMessage) Msg = " + xmlOutputString.toString());
            }           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
