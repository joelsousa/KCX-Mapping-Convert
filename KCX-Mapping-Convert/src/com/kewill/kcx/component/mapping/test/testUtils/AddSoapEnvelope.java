package com.kewill.kcx.component.mapping.test.testUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Functions to wrap XML data in a named file 
 * with SOAP envelope data read from another named file.
 *  
 * @author Pete.thomas
 * @version 1.0.00
 *
 */
public final class AddSoapEnvelope {
//	private static final String UIDS_ADDRESSING_URI = "http://www.eurtradenet.com/schemas/header/200310";
	
	private AddSoapEnvelope() { }
	
	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println(
					"Parameters: xml_header_file_name xml_body_file_name soap_envelope_file_name " +
					"[message_type_for_insertion_in_soap_header " +
					"[soap_body_data_root_tag]]");
			return;
		}
		
		try {
			File outFile = parseToFile(args[0], args[1], args[2],
					(args.length > 3 ? args[3] : null),
					(args.length > 4 ? args[4] : null));
			System.out.println("SOAP-wrapped XML data written to " + outFile.getAbsolutePath());
//			System.out.println(parse(args[0], args[1], (args.length > 2 ? args[2] : null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read XML data from a named file and wrap it with SOAP envelope data read
	 * from another named file.  The combined data will be written to a new file
	 * named as the xml data file except with "_soap" inserted before the file
	 * extension
	 * 
	 * @param xmlHeaderFileName - Name of file containing XML header data
     * @param xmlBodyFileName - Name of file containing XML body data
	 * @param soapEnvelopeFileName - Name of file containing SOAP envelope data
	 * @param messageType - If not null then this value is placed in the <code>MessageType</code>
	 * 						element of the SOAP header
	 * @param dataRootElemTag - If not null then this value is used as the tag for an element that will
	 *                          be created, added as a child of the SOAP body element and used as the
	 *                          parent of the data merged in from <code>xmlDataFileName</code> 
	 * @return File object representing new combined data file 
	 * @throws Exception Exception
	 */
	public static File parseToFile(String xmlHeaderFileName, String xmlBodyFileName, 
	                               String soapEnvelopeFileName, String messageType, 
	                               String dataRootElemTag) throws Exception {
		String targetFileName;
		String[] xmlBodyFileNameParts;
		File xmlBodyFile;
		File targetFile;
		FileWriter fileWriter;
		String xml;
		
		xmlBodyFile = new File(xmlBodyFileName);
		xmlBodyFileNameParts = xmlBodyFile.getName().split("\\.");
		
		targetFileName =
			String.format("%s_soap%s",
			        xmlBodyFileNameParts[0],
					(xmlBodyFileNameParts.length > 1 ? ("." + xmlBodyFileNameParts[1]) : ""));
		
		xml = parse(xmlHeaderFileName, xmlBodyFileName, soapEnvelopeFileName, messageType, dataRootElemTag);
		targetFile = new File(xmlBodyFile.getParent(), targetFileName);
		fileWriter = new FileWriter(targetFile);
		fileWriter.write(xml);
		fileWriter.flush();
		fileWriter.close();

		return targetFile;
	}
	
	/**
	 * Read XML data from a named file and wrap it with SOAP envelope data read
	 * from another named file.  The combined data will be returned as a string
	 * 
	 * @param xmlHeaderFileName - Name of file containing XML header data
     * @param xmlBodyFileName - Name of file containing XML body data
	 * @param soapEnvelopeFileName - Name of file containing SOAP envelope data
	 * @param messageType - If not null then this value is placed in the <code>MessageType</code>
	 * 						element of the SOAP header
	 * @param dataRootElemTag - If not null then this value is used as the tag for an element that will
	 *                          be created, added as a child of the SOAP body element and used as the
	 *                          parent of the data merged in from <code>xmlDataFileName</code> 
	 * @return	String containing new combined data
	 * @throws Exception Exception
	 */
	public static String parse(String xmlHeaderFileName, String xmlBodyFileName, String soapEnvelopeFileName,
			                   String messageType,     String dataRootElemTag) throws Exception {
		SAXReader reader = new SAXReader();
		Document soapDOM;
        Document headerDOM;
		Document bodyDOM;

		try {
			soapDOM = reader.read(new File(soapEnvelopeFileName));
		} catch (DocumentException e) {
			throw new Exception(
					String.format("Problem reading SOAP envelope file '%s' - %s",
							soapEnvelopeFileName,
							e.getLocalizedMessage()),
					e);
		}
		
        try {
            headerDOM = reader.read(new File(xmlHeaderFileName));
        } catch (DocumentException e) {
            throw new Exception(
                    String.format("Problem reading XML header file '%s' - %s",
                            xmlHeaderFileName,
                            e.getLocalizedMessage()),
                    e);
        }
        
		try {
			bodyDOM = reader.read(new File(xmlBodyFileName));
		} catch (DocumentException e) {
			throw new Exception(
					String.format("Problem reading XML body file '%s' - %s",
					        xmlBodyFileName,
							e.getLocalizedMessage()),
					e);
		}
		
		if (messageType != null) {
//			setMessageType(soapDOM, messageType);
            setMessageType(headerDOM, messageType);
		}
		
        Element headerElem = soapDOM.getRootElement().element("Header");
		Element bodyElem   = soapDOM.getRootElement().element("Body");
		
		if (headerElem == null) {
			Namespace soapNamespace = soapDOM.getRootElement().getNamespace();
			headerElem = soapDOM.getRootElement().addElement(new QName("Header", soapNamespace));
		}

        if (bodyElem == null) {
            Namespace soapNamespace = soapDOM.getRootElement().getNamespace();
            bodyElem = soapDOM.getRootElement().addElement(new QName("Body", soapNamespace));
        }

        Node headerNode = headerDOM.getRootElement().detach();
		Node bodyNode = bodyDOM.getRootElement().detach();
		
		if ((dataRootElemTag != null) && (dataRootElemTag.trim().length() > 0)) {
			bodyElem = bodyElem.addElement(dataRootElemTag);
		}
		
        headerElem.add(headerNode);
		bodyElem.add(bodyNode);

		StringWriter sw = new StringWriter();
		OutputFormat outformat = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(sw, outformat);
		writer.write(soapDOM);
		writer.flush();

		return sw.toString();
	}

	private static void setMessageType(Document headerDOM, String messageType) {
		Element headerElem;
		Element messageTypeElem;
		Namespace addressingNamespace = null;

		// get the <Header> element
		headerElem = headerDOM.getRootElement();
		
		if (headerElem == null) {
			headerElem = headerDOM.getRootElement().addElement(new QName("Header"));
		} else {
			addressingNamespace = headerElem.getNamespace(); 
		}

		//first check to see if this may be a KIDS header
		messageTypeElem = headerElem.element("MessageTP");
		
		//if not then lets see if it's a UIDS header
		if (messageTypeElem == null) {
			messageTypeElem = headerElem.element("MessageType");

			//if not then lets assume that it's a UIDS header that is missing
			//it's message type element, so create one
			if (messageTypeElem == null) {
				messageTypeElem = headerElem.addElement(new QName("MessageType", 
				                                                            addressingNamespace));
			}
		} else {
			//if here then it seems to be a KIDS header, so let's find the message type name element
			headerElem = messageTypeElem;
			messageTypeElem = headerElem.element("MessageName");

			//if we can't find the message type name then create one
			if (messageTypeElem == null) {
				messageTypeElem = headerElem.addElement(new QName("MessageName", 
				                                                            addressingNamespace));
			}
		}
		
		messageTypeElem.setText(messageType);
	}


//    private static void setMessageTypeSoap(Document soapDOM, String messageType) {
//        Element headerElem;
//        Element addressingHeaderElem;
//        Element messageTypeElem;
//        Namespace soapNamespace = soapDOM.getRootElement().getNamespace();
//        Namespace addressingNamespace = null;
//
//        // first get soap header element
//        headerElem = soapDOM.getRootElement().element("Header");
//        
//        if (headerElem == null) {
//            headerElem = soapDOM.getRootElement().addElement(new QName("Header", soapNamespace));
//        }
//
//        // then get addressing  header element
//        addressingHeaderElem = headerElem.element("Header");
//        
//        if (addressingHeaderElem == null) {
//            addressingNamespace = new Namespace(null, UIDS_ADDRESSING_URI);
//            addressingHeaderElem = headerElem.addElement(new QName("Header", addressingNamespace));
//        } else {
//            addressingNamespace = addressingHeaderElem.getNamespace(); 
//        }
//
//        //first check to see if this may be a KIDS header
//        messageTypeElem = addressingHeaderElem.element("MessageTP");
//        
//        //if not then lets see if it's a UIDS header
//        if (messageTypeElem == null) {
//            messageTypeElem = addressingHeaderElem.element("MessageType");
//
//            //if not then lets assume that it's a UIDS header that is missing
//            //it's message type element, so create one
//            if (messageTypeElem == null) {
//                messageTypeElem = addressingHeaderElem.addElement(new QName("MessageType", 
//                                                                            addressingNamespace));
//            }
//        } else {
//            //if here then it seems to be a KIDS header, so let's find the message type name element
//            addressingHeaderElem = messageTypeElem;
//            messageTypeElem = addressingHeaderElem.element("MessageName");
//
//            //if we can't find the message type name then create one
//            if (messageTypeElem == null) {
//                messageTypeElem = addressingHeaderElem.addElement(new QName("MessageName", 
//                                                                            addressingNamespace));
//            }
//        }
//        
//        messageTypeElem.setText(messageType);
//    }
}
