package com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyConfirmInvestigationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Module      : Export/aes<br>
 * Created     : 21.07.2012<br>
 * Description : Mapping of KIDS-Format into KIDS-Format of ExportConfirmInvestigation message.
 * 
 * @author iwaniuk
 * @version 1.0.00
 * 
 * Changes: MsgExpExt will be imported from ...aes21.msg
 */
public class MapExpExtKK extends KidsMessage {
	
	private MsgExpExt msgExpExt;
    private BodyConfirmInvestigationKids    body;
	
	public MapExpExtKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpExt = new MsgExpExt(parser);
			this.encoding = encoding;
	}
	
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyConfirmInvestigationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpExt.parse(HeaderType.KIDS);
            body.setMessage(msgExpExt);
            body.setKidsHeader(kidsHeader);
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportPrenotificationKids getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

}
