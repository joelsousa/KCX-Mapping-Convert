package com.kewill.kcx.component.mapping.test.Export.mains;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.tools.ant.filters.StringInputStream;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : KidsToKidsTest<br>
 * Created 		: 12.08.2008<br>
 * Description 	: Testconverter.
 *                Only characters of tag "MessageName" will be changed to "MAPPED MESSAGE NAME".
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class DEToKidsTest implements Callable {

    private boolean inMessageName = false;
	
	private StringWriter xmlOutputString = null;
	

	public String onCall(MuleEventContext muleEventContext) throws Exception {
        MuleMessage message = muleEventContext.getMessage();
        String payload = message.getPayloadAsString();
		Utils.log("(KidsToKidsTest onCall) payloadString = \n" + payload);

		readXMLEvents(message);

		String xml = xmlOutputString.toString();
		Utils.log("(KidsToKidsTest onCall) Converted Message = \n" + xml);
		return xml;
	}

    
    private void readXMLEvents(MuleMessage message) throws Exception {
        Utils.log("(KidsToKidsTest readXMLEvents) message.getEncoding() = " + message.getEncoding());
        
//      InputStream ins = (InputStream)message.getPayload(InputStream.class);
//      InputStreamReader is = new InputStreamReader(ins, "ISO-8859-1");

//      Document doc = (Document) message.getPayload(Document.class);
//      ByteArrayInputStream is = new ByteArrayInputStream(doc.asXML().getBytes());

        String payload = message.getPayloadAsString();
        InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins, message.getEncoding());


        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        XMLEvent event = null;
        XMLEventWriter writer = null;
        while (parser.hasNext()) {
            event = parser.nextEvent();
            Utils.log("(KidsToKidsTest readXMLEvents) event = " + event);
            switch(event.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    Utils.log("(KidsToKidsTest readXMLEvents) START DOCUMENT");
                    writer = openXML(event);
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    Utils.log("(KidsToKidsTest readXML) LocalName = " + startElement.getName());
                    QName qname = startElement.getName();
                    String name = qname.getLocalPart();
                    if (name.equalsIgnoreCase("MessageName")) {
                        inMessageName = true;
                    } else {
                        inMessageName = false;
                    }
                    writer.add(event);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    Utils.log("(KidsToKidsTest readXML) LocalName = /" + endElement.getName());
                    writer.add(event);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    Utils.log("(KidsToKidsTest readXML) CHARACTERS = " + characters.getData());
                    if (inMessageName) {
                        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
                        event = eventFactory.createCharacters("DE TO KIDS"); 
                        inMessageName = false;
                    }
                    writer.add(event);
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    Utils.log("(KidsToKidsTest readXML) END_DOCUMENT");
                    writer.add(event);
                    parser.close();
                    writer.close();
                    break;
                default:
                    break;
            } // end switch
          } // end while
    }
    
    private XMLEventWriter openXML(XMLEvent event) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance(); 
        xmlOutputString = new StringWriter();
        
        XMLEventWriter writer = outputFactory.createXMLEventWriter(xmlOutputString);
        
        writer.add(event);
        
        return writer;
    }

}
