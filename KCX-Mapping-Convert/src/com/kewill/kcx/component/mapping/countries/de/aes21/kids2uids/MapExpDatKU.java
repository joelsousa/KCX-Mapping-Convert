package com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.uids.aes21.BodyExportDeclarationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 20.07.2012<br>
 * Description	: Mapping of KIDS-Format into UIDS-Format of ExportDeclaration. 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapExpDatKU extends UidsMessage {
	private BodyExportDeclarationUids body;
	private MsgExpDat message;
	
	public MapExpDatKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgExpDat(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);           
            body   = new BodyExportDeclarationUids(writer);
            uidsHeader = new UidsHeader(writer);
            
            writeStartDocument("UTF-8", "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            mapKidsToUidsHeader();          
            uidsHeader.writeHeader(getCommonFieldsDTO());
          
            message.parse(HeaderType.KIDS);
            message.setFromFormat("KIDS");    //EI20120913
            
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  //EI20120917            
            body.setUidsHeader(uidsHeader);
            
            body.setMessage(message);           
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgExportDeclaration getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

	
}
