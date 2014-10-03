/*
 * Function    : MapCHReverseDeclarationUK.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF/ Iwaniuk
 * Description : Mapping of UIDS-Format into KIDS-Format of Export Reverse Declaration
 * 			   :
 * Parameters  :

 * Changes
 * -------------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.ch.aus.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelCH;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgUids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyCHReverseDeclarationKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul        : MapCHReverseDeclarationUK<br>
 * Erstellt     : 17.09.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of 
 *                Export ReverseDeclaration (ExportRelease) for CH.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class MapCHReverseDeclarationUK extends KidsMessage {
	private BodyCHReverseDeclarationKids body;
	private MsgExpRelCH msgExpRelCH;
	private MsgUids msgUids;

    public MapCHReverseDeclarationUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
                                                                                        throws XMLStreamException {
        msgUids = new MsgUids(parser);
        msgExpRelCH = new MsgExpRelCH();
        this.kidsHeader = kidsHeader;
        this.encoding = encoding;
    }

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;

        try {
            body       = new BodyCHReverseDeclarationKids(writer);
            
            kidsHeader = new KidsHeader(writer);
            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
            commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
            commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
            // commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
            commonFieldsDTO.setDirection(EKidsMessages.valueOf(kidsHeader.getMessageName()).getDirection());
            kidsHeader.setCommonFieldsDTO(commonFieldsDTO);		            

            kidsHeader.writeHeader();    
            
            msgUids.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(msgUids.getReferenceNumber());
           
            mapUids2Kids();
            body.setMessage(msgExpRelCH);
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}  
	
	private void mapUids2Kids() {
		msgExpRelCH.setDeclarationKind(msgUids.getApplicationReference()); 
		msgExpRelCH.setDeclarationNumberForwarder(msgUids.getLocalReferenceNumber());
		msgExpRelCH.setDeclarationNumberCustoms(msgUids.getRegistrationNumber()); 
		msgExpRelCH.setAcceptanceTime(msgUids.getDateOfRelease());  
		if (msgUids.getHeaderExtensions() != null) {
		    msgExpRelCH.setRevisionCode(msgUids.getHeaderExtensions().getEDECRevisionFlag());
			msgExpRelCH.setCodeOfRelease(msgUids.getHeaderExtensions().getEDECReleaseFlag()); 
		}
		msgExpRelCH.setReferenceNumber(msgUids.getReferenceNumber());      		   
	}
}
