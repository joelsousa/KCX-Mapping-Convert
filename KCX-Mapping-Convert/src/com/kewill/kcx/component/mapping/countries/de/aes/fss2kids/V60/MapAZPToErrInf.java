/*
 * Function    : MapAZPToErrInf.java
 * Titel       :
 * Date        : 24.04.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of FSS-Format AZP into KIDS-Format of ErrorMessage
 * Parameters  : 

 
 */

package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAZP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAFP;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyErrorInformationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapAZPToErrInf<br>
 * Erstellt		: 24.04.2009<br>
 * Beschreibung	: Mapping of FSS-Format AZP into KIDS-Format of ErrorMessage.
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapAZPToErrInf extends KidsMessage {

	private MsgAZP msgAZP; 
	private TsAFP[] tsAFP;
	private MsgErrInf msgErrInf;
	
	public MapAZPToErrInf() {
		msgErrInf = new MsgErrInf();
	}

	public MsgAZP getMsgAZP() {
		return msgAZP;
	}

	public void setMsgAZP(MsgAZP msgAZP) {
		this.msgAZP = msgAZP;
		this.setMsgFields();
	}
	
	public void setMsgFields() {
		tsAFP = msgAZP.getAfpSubsets();
		msgErrInf.setUCRNumber(msgAZP.getAfkSubset().getMrn());
		msgErrInf.setUCROtherSystem(msgAZP.getAfkSubset().getFregnr());
		msgErrInf.setReferenceNumber(msgAZP.getAfkSubset().getBeznr());
		msgErrInf.setProcedureType("Export");
		String lfd = "";
		for (int i = 0; i < tsAFP.length; i++) {
			if (tsAFP[i] != null) {
				ErrorType tmpError = new ErrorType();			
				//EI20090316: tmpError.setUniqueNumber(tsAFP[i].getTxtlfd());
				lfd = lfd + (i + 1);   //EI20090316
				tmpError.setUniqueNumber(lfd);
				tmpError.setCode(tsAFP[i].getErrcde());
				tmpError.setText(tsAFP[i].getErrtxt());
				tmpError.setPointer(tsAFP[i].getZeiger());
				msgErrInf.addErrorList(tmpError);
			}
		}
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
	        header.setHeaderFields(msgAZP.getVorSubset());
	        header.setMessageName("ErrorMessage");
	        header.setMessageID(getMsgID());
	        
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
	        header.writeHeader();
	        
	        BodyErrorInformationKids body   = new BodyErrorInformationKids(writer);
	        body.setMsgErrInf(msgErrInf);
	        body.setKidsHeader(header);

            getCommonFieldsDTO().setReferenceNumber(msgErrInf.getReferenceNumber());
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
