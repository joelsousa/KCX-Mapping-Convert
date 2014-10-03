/*
 * Function    : MapExpRelKU.java
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / krzoska
 * Description : Mapping of KIDS-Format of ReverseDeclaration into UIDS-Format of ExportRelease. 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportReleaseUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpRelKU<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Mapping of KIDS-Format of ReverseDeclaration into UIDS-Format of ExportRelease. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapExpRelKU extends UidsMessage {
	private BodyExportReleaseUids 	body   = null;
	private MsgExpRel 		msgExpRel;
	
	public MapExpRelKU(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpRel = new MsgExpRel(parser);
			msgExpRel.setMsgName("ExportRelease");
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
            
//          uidsHeader.writeHeader();
            uidsHeader.writeHeader(getCommonFieldsDTO());
          
            msgExpRel.parse(HeaderType.KIDS);
            body.setMessage(msgExpRel);
            body.setUidsHeader(uidsHeader);

            getCommonFieldsDTO().setReferenceNumber(msgExpRel.getReferenceNumber());
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
