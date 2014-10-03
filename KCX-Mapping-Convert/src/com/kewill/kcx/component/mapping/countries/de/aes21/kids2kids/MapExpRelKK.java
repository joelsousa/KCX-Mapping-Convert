package com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.db.CountryConfigDTO;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyReverseDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.PdfReader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module		: MapExpRelKK<br>
 * Created		: 27.07.2012<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of Reverse Declaration message.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class MapExpRelKK extends KidsMessage {
	private BodyReverseDeclarationKids 	body   = null;
	private MsgExpRel 					msgExpRel;

	public MapExpRelKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpRel = new MsgExpRel(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);
			body       = new BodyReverseDeclarationKids(writer);
            kidsHeader.setWriter(writer);
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");

            kidsHeader.writeHeader();
            
            msgExpRel.parse(HeaderType.KIDS);
            
            PDFInformation pdfInformation = msgExpRel.getPdfInformation(); 
            if (pdfInformation != null) {     //EI20100811
             List<String> pdfList = pdfInformation.getPdflist();
             if (pdfList == null || pdfList.size() == 0) {
                String country = getCommonFieldsDTO().getCountryCode();
                Utils.log("(MapExpRelKK getMessage) Origin country = " + country);
                CountryConfigDTO countryConfigDTO = Utils.getCountryConfig(country);
                if (countryConfigDTO != null) { 
                    if (countryConfigDTO.getPdfMethod().equalsIgnoreCase("FILE")) {
                        PdfReader pdfReader = new PdfReader();
                        pdfList = pdfReader.readPdf(countryConfigDTO.getPdfDir(), pdfInformation.getName());
                        if (pdfList == null || pdfList.size() == 0) {
                            long now             = System.currentTimeMillis();
                            long end             = now + (countryConfigDTO.getPdfWaitTime() * 1000);
                            long pdfWaitInterval = countryConfigDTO.getPdfWaitInterval();
                            while (now < end && (pdfList == null || pdfList.size() == 0)) {
                                try {
                                    Thread.sleep(pdfWaitInterval * 1000);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                pdfList = pdfReader.readPdf(countryConfigDTO.getPdfDir(), pdfInformation.getName());
                                if (pdfList != null && pdfList.size() > 0) {
                                    pdfInformation.setPdflist(pdfList);
                                }
                                now = System.currentTimeMillis();
                            }
                        }
                        pdfInformation.setPdflist(pdfList);
                    } else {
                        Utils.log("(MapExpRelKK getMessage) PDF method is not \"FILE\". +" +
                        		                           "Did not try to read PDF." + country + ".");
                    }
                } else {
                    Utils.log("(MapExpRelKK getMessage) No PDF configuration found for country " + country + ".");
                }
             }
            }
            
            
            
            body.setMessage(msgExpRel); 
            body.setKidsHeader(kidsHeader);
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            Utils.log("(Completion getMessage) Msg = " + xmlOutputString.toString());
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }
		return xmlOutputString.toString();
	}  
}
