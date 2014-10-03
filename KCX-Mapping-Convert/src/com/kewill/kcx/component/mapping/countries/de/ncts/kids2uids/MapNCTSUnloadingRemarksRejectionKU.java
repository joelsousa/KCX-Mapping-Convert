package com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ncts.BodyNCTSUnloadingRemarksRejectionUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSUnloadingRemarksRejectionKU
 * Created		: 31.08.2010
 * Description	: mapping of KIDS format into UIDS format of UnloadingRemarksRejection message.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class MapNCTSUnloadingRemarksRejectionKU extends UidsMessage {
	private BodyNCTSUnloadingRemarksRejectionUids body;
	private MsgNCTSUnloadingRemarksRejection message;
	
	/// CONSTRUCTOR(s)
		public MapNCTSUnloadingRemarksRejectionKU(XMLEventReader parser, 
				String encoding) throws XMLStreamException {
			message	= new MsgNCTSUnloadingRemarksRejection(parser);
			this.encoding	= encoding;
		}
	
	/// METHODS
		public String getMessage() {
			StringWriter xmlOutputString	= new StringWriter();
			XMLOutputFactory factory		= XMLOutputFactory.newInstance();
			
			try {
				writer	= factory.createXMLStreamWriter(xmlOutputString);
					uidsHeader	= new UidsHeader(writer);
					body		= new BodyNCTSUnloadingRemarksRejectionUids(writer);
					
					writeStartDocument(encoding, "1.0");
						openElement("soap:Envelope");
							setAttribute("xmlns:soap", 
									"http://www.w3.org/2003/05/soap-envelope");
								mapKidsToUidsHeader();
								
								uidsHeader.writeHeader(getCommonFieldsDTO());
								
								message.parse(HeaderType.KIDS);
								getCommonFieldsDTO().setReferenceNumber(
										message.getReferenceNumber());
								
								body.setUidsHeader(uidsHeader);
								body.setMessage(message);
								body.writeBody();
						closeElement();
					writer.writeEndDocument();
					
					writer.flush();
					writer.close();
					
					Utils.log("NCTS UnloadingRemarksRejection UidsMessage = " + 
							xmlOutputString.toString());
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			
			return xmlOutputString.toString();
		}

}
