package com.kewill.kcx.component.mapping.countries.ie.kids2ie;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.ie.IrelandHeader;
import com.kewill.kcx.component.mapping.countries.ie.IrelandMessage;
import com.kewill.kcx.component.mapping.formats.ireland.BodyImportDeclarationIreland;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ExportImport Kids to Ireland<br>
 * Created		: 26.05.2014<br>
 * Description	: Mapping of KIDS-Format into Ireland-Format of ImportDeclaration. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapImportDeclarationKI extends IrelandMessage {
	private BodyImportDeclarationIreland body   = null;
	private MsgImportDeclaration msgKids;
	
	public MapImportDeclarationKI(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids = new MsgImportDeclaration(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            
            body = new BodyImportDeclarationIreland(writer);
            irelandHeader = new IrelandHeader(writer);
            
            writeStartDocument("UTF-8", "1.0");
            openElement("exportDeclaration");
            setAttribute("xmlns", "http://www.customsmatters.com/schemas/cab/declarationapi/exp");
            setAttribute("xmlns:v1", "http://www.ros.ie/schemas/ecs/IE515b/v1");            
            
            mapKidsToIrelandHeader();            
            irelandHeader.writeHeader(getCommonFieldsDTO());          
            
            msgKids.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());

            body.setIrelandHeader(irelandHeader);
            body.setMessage(msgKids);

            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MsgImportDeclarationIE getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }

	
}
