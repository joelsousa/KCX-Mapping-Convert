/*
 * Function    : MapAMRToExpCon.java
 * Titel       :
 * Date        : 04.03.2009
 * Author      : Kewill CSF / messer
 * Description : Mapping of FSS-Format AMR into KIDS-Format of ExportConfirmation
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCon;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAMR;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportConfirmationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapAMRToExpCon<br>
 * Erstellt		: 04.03.2009<br>
 * Beschreibung	: Mapping of FSS-Format AMR into KIDS-Format of ExportConfirmation. 
 * 
 * @author messer
 * @version 6.0.00
 */
public class MapAMRToExpCon extends KidsMessage {
	
	private MsgAMR msgAMR;
	private MsgExpCon msgExpCon;
	
	public MapAMRToExpCon() {
		msgExpCon = new MsgExpCon();
	}

	public void setMsgAMR(MsgAMR msgAMR) {
    	this.msgAMR = msgAMR;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {

	msgExpCon.setKindOfAnswer(msgAMR.getAmrSubset().getKzart());	
	msgExpCon.setUCRNumber(msgAMR.getAmrSubset().getMrn());
	msgExpCon.setReceiveTime(msgAMR.getAmrSubset().getAmrdat());
	msgExpCon.setReferenceNumber(msgAMR.getAmrSubset().getBeznr());
	//EI20110811: msgExpCon.setPdfInformation(msgAMR.getPdfInformation());
	msgExpCon.addPdfInformationList(msgAMR.getPdfInformation());  //EI20110811
    }
	
	public String getMessage() {
    StringWriter xmlOutputString = new StringWriter();
    
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    try {
        writer = factory.createXMLStreamWriter(xmlOutputString);

        writeStartDocument(encoding, "1.0");
        openElement("soap:Envelope");
        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
        
        KidsHeader             header = new KidsHeader(writer);
        header.setHeaderFields(msgAMR.getVorSubset());
        header.setMessageName("Confirmation");
        header.setMessageID(getMsgID());
        
        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(header.getReceiver());
        commonFieldsDTO.setCountryCode(header.getCountryCode());
        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
        header.setCommonFieldsDTO(commonFieldsDTO);

        header.writeHeader();
        
        BodyExportConfirmationKids body   = new BodyExportConfirmationKids(writer);
        body.setMsgExpCon(msgExpCon);
        body.setKidsHeader(header);
        Utils.log("(MapAMRToExpCon getMessage) header = " + header);

        getCommonFieldsDTO().setReferenceNumber(msgExpCon.getReferenceNumber());
        body.writeBody();
        
        closeElement();  // soap:Envelope
        writer.writeEndDocument();
        
        writer.flush();
        writer.close();
        
        Utils.log("(MsgExportConfirmation getMessage) Msg = " + xmlOutputString.toString());
        
    } catch (XMLStreamException e) {
        
        e.printStackTrace();
    }

    
    return xmlOutputString.toString();
}

}
