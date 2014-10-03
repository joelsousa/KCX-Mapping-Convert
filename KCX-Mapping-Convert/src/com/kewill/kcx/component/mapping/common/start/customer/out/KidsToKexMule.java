/*
 * Funktion    : KexToKidsMule.java
 * Titel       :
 * Erstellt    : 18.01.2012
 * Author      : krzoska
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.common.start.customer.out;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KcxToKidsConverter;
import com.kewill.kcx.component.mapping.common.start.RemoveSoap;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlPdfTgzIntern2;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul		: KidsToKexMule<br>
 * Erstellt		: 19.01.2012<br>
 * Beschreibung	: transformer called by Mule to convert KIDS-Format to KEX messages. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class KidsToKexMule extends KcxToKidsConverter implements Callable {
    private static Logger logger            = null;        

    public static void main(String[] args) throws Exception {
        Config.configure();
        String xml = getBase64Text();
//        String xml = getBase64();
        KidsToKexMule kex = new KidsToKexMule();
        kex.commonFieldsDTO = new CommonFieldsDTO();
        kex.commonFieldsDTO.setFilename("Testdatei.xml"); 
        kex.generatePdfTgz(xml);
    }
    
    public Object onCall(MuleEventContext muleEventContext) throws Exception {
        logger = Logger.getLogger("kcx");
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        
        String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToKexMule onCall) payload = \n" + payload);
        }
        Utils.log("(KidsToKexMule onCall) message.getEncoding() = " + message.getEncoding());
        
        Utils.createAuditContext(muleEventContext, 
                "<KidsToKexMule>" +  
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", "") +
                "</KidsToKexMule>");

        Utils.addAuditEvent(muleEventContext, 
                "KidsToKexMule: File Name", 
                message.getStringProperty("directory", ".") + "/" +
                message.getStringProperty("originalFilename", ""));

        // Zuerst den KCX-Envelope entfernen, damit die CommonFieldDTO-Felder besetzt werden. MS20120202
        // String xml = new RemoveSoap().removeSoap(payload, "Result");
// MS20210227 Begin        
//        String content = removeKcxEnvelope(payload, message.getEncoding(), EDirections.CountryToCustomer);
//        String xml     = new RemoveSoap().removeSoap(content, "Result");
//        commonFieldsDTO.setFilename(message.getStringProperty("filename", UUID.randomUUID().toString() + ".xml"));
//        Object tgz     = generatePdfTgz(xml);
//        message.setStringProperty("filename", commonFieldsDTO.getFilename());

        String filename = message.getStringProperty("filename", null);
        String xml = null;
        Object tgz = null;
        try {
            String content = removeKcxEnvelope(payload, message.getEncoding(), EDirections.CountryToCustomer);
            xml     = new RemoveSoap().removeSoap(content, "Result");
            commonFieldsDTO.setFilename(message.getStringProperty("filename", UUID.randomUUID().toString() + ".xml"));
            tgz     = generatePdfTgz(xml);
            message.setStringProperty("filename", commonFieldsDTO.getFilename());
        } catch (Exception e) {
            Utils.processException("(KidsToKidsMule onCall) Exception ", e, payload, filename, message.getCorrelationId());
        } catch (Error e) {
            Utils.processException("(KidsToKidsMule onCall) Error ", e, payload, filename, message.getCorrelationId());
        }
// MS20210227 End        
        
        if (Config.getLogXML()) {
            Utils.log("(KidsToKexMule onCall) xml = \n" + xml);
        }

        // MS20101018
        message.setStringProperty("MessageReferenceNumber", commonFieldsDTO.getMessageReferenceNumber().trim());
        
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        if (muleEventContext != null) {
//            AuditUtils.dispatchAuditEventPayload(muleEventContext, "KidsToKexMule: Message State", "converted");
            Utils.addAuditEvent(muleEventContext, "KidsToKexMule: Message State", "converted");
        }

        // MS20111208 Begin
//        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);
        if (Config.isWriteToCustomerDir()) {
            MuleUtils.writeCustomerMessage(message, tgz, commonFieldsDTO);
        } else {
            MuleUtils.writeFileMessage(message, tgz, commonFieldsDTO);
        }
        // MS20111208 End
        
        return tgz;
    }

    
    private Object generatePdfTgz(String xml) throws IOException, XMLStreamException {
        StringBuffer  outBuffer  = new StringBuffer();
        String        value      = null;
        Namespace     namespace  = null;
        String        prefix     = null;
        String        tag        = null;
        
        List<String>  pdflist    = new ArrayList<String>(); 
        
        XMLEventReader parser = createParser(xml);
        
        if (Config.getLogXML()) {
            Utils.log("(KidsToKexMule generatePdfTgz) xml    = " + xml);
        }
        
        
        while (parser.hasNext()) {
            tag = null;
            
            XMLEvent event = parser.nextEvent();
            
            if (event.isStartDocument()) {
                outBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            } else if (event.isStartElement()) {
                value = event.asStartElement().getName().getLocalPart();
                if (value.equalsIgnoreCase("base64") || value.equalsIgnoreCase("base64Text")) {
                    tag = null;
                    value = parser.nextEvent().asCharacters().getData(); 
                    pdflist.add(value);
                    parser.nextEvent();
                } else {
                    tag = "<" + value;
                    @SuppressWarnings("unchecked")
                    Iterator<Attribute> attIter = event.asStartElement().getAttributes();
                    while (attIter.hasNext()) {
                        Attribute att = attIter.next();
                        value = att.getValue();
                        tag = tag + " " + value; 
                    }
                    @SuppressWarnings("unchecked")
                    Iterator<Namespace> nsItt = event.asStartElement().getNamespaces();
                    while (nsItt.hasNext()) {
                        namespace = nsItt.next();
                        value     = namespace.getValue();
                        prefix    = namespace.getPrefix();
                        tag       = tag + " " + "xmlns:" + prefix + "=\"" + value + "\""; 
                    }
                    tag = tag + ">";
                    
                }
            } else if (event.isCharacters()) {
                value = event.asCharacters().getData();
                tag = value;
            } else if (event.isEndElement()) {
                value = event.asEndElement().getName().getLocalPart();
                tag = "</" + value + ">";
            }
            
            if (tag != null) {
//                Utils.log("(KidsToKexMule generatePdfTgz) tag = " + tag);
                outBuffer.append(tag);
            }
        }
        
        String xmlout = outBuffer.toString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToKexMule generatePdfTgz) xmlout = " + xmlout);
        }
        if (pdflist.size() > 0) {
            writePdffile(pdflist);
        } else {
            return xml;
        }
        
        Object tgz = XmlPdfTgzIntern2.createTgz(xmlout, Config.getPdfpath(), commonFieldsDTO.getFilename());
        commonFieldsDTO.updateFilename();
        return tgz;
    }
    
    private static XMLEventReader createParser(String xml) throws XMLStreamException {
        InputStream ins = new ByteArrayInputStream(xml.getBytes());
        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return parser;
    }
    
    
    public void writePdffile(List <String> pdfList) {
        StringBuffer buff = new StringBuffer();
        String str = "";
        String name = "";
        
        for (int i = 0; i < pdfList.size(); i++)  {
            str = pdfList.get(i);
            buff.append(str);
        }
        String filename = commonFieldsDTO.getFilename();

        if (filename.lastIndexOf(".xml") > 0) {
            name = filename.substring(0, filename.lastIndexOf(".xml")); 
        } else {
            name = filename;
        }
        
        byte[] out = Base64.decodeBase64(buff.toString().getBytes());
        
        try {
            FileOutputStream fos = new FileOutputStream(Config.getPdfpath() + "/" + name + ".pdf");
            fos.write(out);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
 
	private static void getConfiguration() {
        logger.debug("Konfiguration wird geholt.");
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }

	private static String getBase64Text() {
	    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
	    		"<Result><Header xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
	    		"<SentAt><Date><Year>2012</Year><Month>2</Month><Day>14</Day></Date><Time>142030</Time><TimeZone>+00:00</TimeZone></SentAt>" +
	    		"<Transmitter>DE.KEX.TST</Transmitter><Receiver>DE.KEX.TST</Receiver>" +
	    		"<MessageTP><CountryCode>GB</CountryCode><Procedure>Export</Procedure><MessageName>Confirmation</MessageName></MessageTP>" +
	    		"<MessageID>1981</MessageID></Header>" +
	    		"<Confirmation xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
	    		"<GoodsDeclaration><KindOfAnswer>0</KindOfAnswer><UCRNumber>12GB02X32684156014</UCRNumber>" +
	    		"<StatusOfCompletion>H </StatusOfCompletion><StatusOfCompletion2>1</StatusOfCompletion2>" +
	    		"<StatusOfCompletion3>A1</StatusOfCompletion3><ReferenceNumber>2GB209571166000-3-KCXT-14</ReferenceNumber>" +
	    		"<PDFInformation><Name>ESS_Copy_3-22939-20120214-1419.pdf</Name>" +
	    		"<base64Text>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uRJXbbsRjUQSokhDTH+stpudXdMH0VIfUji4SePcrbfylUzGy2TwXYaepD7Fy8F1mYfzacbhPcisw3GVs/Lw/npMSpZpSXlWOaDV8/Pmx/rpX/BeKgnE00Dwt4GPH0kIloZYbrZr9LrZbo7o4VyujYdfkjA0Y5IpTcmY9xzP2cmrshcGv6gYHv3x8LynEXwS2qrV8WS/6ra9r7o9wH5pM0O86sylr31Nr9D0ejoRCTqfTcbX19/RUl12mHy9Xy5uIjuWQWNVyq9Y9E0XaFxgjMlH8YTZh4J8IAWOHKwNGo2cRkIeOK8CyP8ABDnUQQplbmRzdHJlYW0KZW5kb2JqCjUgMCBvYmoKPDwvVHlwZS9QYWdlL01lZGlhQm94WzAgMCA1OTUgODQyXS9SZXNvdXJjZXM8PC9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJXS9Gb250PDwvRjEgMSAwIFIvRjIgMiAwIFI+Pj4+L0NvbnRlbnRzIDMgMCBSL1BhcmVudCA0IDAgUj4+CmVuZG9iagoxIDAgb2JqCjw8L1R5cGUvRm9udC9TdWJ0eXBlL1R5cGUxL0Jhc2VGb250L0hlbHZldGljYS9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKMiAwIG9iago8PC9UeXBlL0ZvbnQvU3VidHlwZS9UeXBlMS9CYXNlRm9udC9IZWx2ZXRpY2EtQm9sZC9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKNCAwIG9iago8PC9UeXBlL1BhZ2VzL0NvdW50IDEvS2lkc1s1IDAgUl0vSVRYVCg1LjAuNCk+PgplbmRvYmoKNiAwIG9iago8PC9UeXBlL0NhdGFsb2cvUGFnZXMgNCAwIFI+PgplbmRvYmoKNyAwIG9iago8PC9Qcm9kdWNlcihpVGV4dFNoYXJwIDUuMC40IFwoY1wpIDFUM1hUIEJWQkEpL0NyZWF0aW9uRGF0ZShEOjIwMTIwMjE0MTQxOTU5KzAwJzAwJykvTW9kRGF0ZShEOjIwMTIwMjE0MTQxOTU5KzAwJzAwJyk+PgplbmRvYmoKeHJlZgowIDgKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDA0OTIyIDAwMDAwIG4gCjAwMDAwMDUwMTAgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDA1MTAzIDAwMDAwIG4gCjAwMDAwMDQ3NTYgMDAwMDAgbiAKMDAwMDAwNTE2NiAwMDAwMCBuIAowMDAwMDA1MjExIDAwMDAwIG4gCnRyYWlsZXIKPDwvU2l6ZSA4L1Jvb3QgNiAwIFIvSW5mbyA3IDAgUi9JRCBbPGM4MmYxZjk0YzI2Y2M0ZDAzYTIzMmQzZWFiMWRjMmQxPjw5NTcxNmUxZGU0N2RhMjI5ZjQ0NWJlNDBkOTAzM2U5Yz5dPj4Kc3RhcnR4cmVmCjUzNDYKJSVFT0YK</base64Text>" +
	    		"</PDFInformation></GoodsDeclaration></Confirmation></Result>";
	    return xml;
	}
	
    private static String getBase64() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<Result><Header xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<SentAt><Date><Year>2012</Year><Month>2</Month><Day>14</Day></Date><Time>142030</Time><TimeZone>+00:00</TimeZone></SentAt>" +
                "<Transmitter>DE.KEX.TST</Transmitter><Receiver>DE.KEX.TST</Receiver>" +
                "<MessageTP><CountryCode>GB</CountryCode><Procedure>Export</Procedure><MessageName>Confirmation</MessageName></MessageTP>" +
                "<MessageID>1981</MessageID></Header>" +
                "<Confirmation xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<GoodsDeclaration><KindOfAnswer>0</KindOfAnswer><UCRNumber>12GB02X32684156014</UCRNumber>" +
                "<StatusOfCompletion>H </StatusOfCompletion><StatusOfCompletion2>1</StatusOfCompletion2>" +
                "<StatusOfCompletion3>A1</StatusOfCompletion3><ReferenceNumber>2GB209571166000-3-KCXT-14</ReferenceNumber>" +
                "<PDFInformation><Name>ESS_Copy_3-22939-20120214-1419.pdf</Name>" +
                "<base64>JVBERi0xLjQKJeLjz9MKMyAwIG9iago8PC9MZW5ndGggNDY3My9GaWx0ZXIvRmxhdGVEZWNvZGU+PnN0cmVhbQp4nK1bWX</base64>" +
                "<base64>MbNxJ+16/Ao/IgenDNsW8URTtcS6Qs0RW7Si+MRCnciKR2SCX2v18cjWMwAIapWm1lM010f+gLjcYM8t+zy+UZKVBNCFo+n</base64>" +
                "<base64>RXoomy4fJouz76c/Vf9Q9C/xcAn8VSop/+IX/5Wv1DcjBgRwhhtz1hRjbAmXs/uBVlqkqgxTWCqxigptJwcMwSMffHmwWYe</base64>" +
                "<base64>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h4J8IAWOHKwNGo2cRkIeOK8CyP8ABDnUQQplbmRzdHJlYW0KZW5kb2JqCjUgMCBvYmoKPDwvVHlwZS9QYWdlL01lZGlhQm94WzAgMCA1OTUgODQyXS9SZXNvdXJjZXM8PC9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJXS9Gb250PDwvRjEgMSAwIFIvRjIgMiAwIFI+Pj4+L0NvbnRlbnRzIDMgMCBSL1BhcmVudCA0IDAgUj4+CmVuZG9iagoxIDAgb2JqCjw8L1R5cGUvRm9udC9TdWJ0eXBlL1R5cGUxL0Jhc2VGb250L0hlbHZldGljYS9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKMiAwIG9iago8PC9UeXBlL0ZvbnQvU3VidHlwZS9UeXBlMS9CYXNlRm9udC9IZWx2ZXRpY2EtQm9sZC9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKNCAwIG9iago8PC9UeXBlL1BhZ2VzL0NvdW50IDEvS2lkc1s1IDAgUl0vSVRYVCg1LjAuNCk+PgplbmRvYmoKNiAwIG9iago8PC9UeXBlL0NhdGFsb2cvUGFnZXMgNCAwIFI+PgplbmRvYmoKNyAwIG9iago8PC9Qcm9kdWNlcihpVGV4dFNoYXJwIDUuMC40IFwoY1wpIDFUM1hUIEJWQkEpL0NyZWF0aW9uRGF0ZShEOjIwMTIwMjE0MTQxOTU5KzAwJzAwJykvTW9kRGF0ZShEOjIwMTIwMjE0MTQxOTU5KzAwJzAwJyk+PgplbmRvYmoKeHJlZgowIDgKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDA0OTIyIDAwMDAwIG4gCjAwMDAwMDUwMTAgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDA1MTAzIDAwMDAwIG4gCjAwMDAwMDQ3NTYgMDAwMDAgbiAKMDAwMDAwNTE2NiAwMDAwMCBuIAowMDAwMDA1MjExIDAwMDAwIG4gCnRyYWlsZXIKPDwvU2l6ZSA4L1Jvb3QgNiAwIFIvSW5mbyA3IDAgUi9JRCBbPGM4MmYxZjk0YzI2Y2M0ZDAzYTIzMmQzZWFiMWRjMmQxPjw5NTcxNmUxZGU0N2RhMjI5ZjQ0NWJlNDBkOTAzM2U5Yz5dPj4Kc3RhcnR4cmVmCjUzNDYKJSVFT0YK</base64>" +
                "</PDFInformation></GoodsDeclaration></Confirmation></Result>";
        return xml;
    }
    
}

