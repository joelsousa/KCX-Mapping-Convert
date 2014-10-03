package com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVMR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSMRNAllocatedKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS<br>
 * Created		: 11.02.2013<br>
 * Description	: Mapping of FSS-VMR to KIDS format of NCTSMRNAllocated.
 * 
 * @author iwaniuk
 * @version 7000 to 4.1.00
 */
public class MapVMRToNCTSMRNAllocated extends KidsMessage {

	private MsgVMR msgVMR;
	private MsgNCTSMRNAllocated msgNCTSMRNAllocated;
	private boolean isPDF = false;              //EI20130606

	public MapVMRToNCTSMRNAllocated() {
		msgVMR = new MsgVMR();
		msgNCTSMRNAllocated = new MsgNCTSMRNAllocated();
	}

	public void setMsgVMR(MsgVMR argument) {
		this.msgVMR = argument;
		this.setMsgFields();

	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);

			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap",
					"http://www.w3.org/2003/05/soap-envelope");

			KidsHeader header = new KidsHeader(writer);
			//header.setHeaderFields(msgVMR.getVorSubset());
			header.setHeaderFieldsFromHead(msgVMR.getVorSubset(), msgVMR.getHeadSubset());  //EI20130711
			header.setMessageName("MRNAllocated");
			header.setMessageID(getMsgID());
			if (isPDF) {
				header.setJustCode("FinalPositive");
			}
			CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
			commonFieldsDTO.setKcxId(header.getReceiver());
			commonFieldsDTO.setCountryCode(header.getCountryCode());
			commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
			header.setCommonFieldsDTO(commonFieldsDTO);

			header.writeHeader();

			BodyNCTSMRNAllocatedKids body = new BodyNCTSMRNAllocatedKids(writer);
			body.setMsgNCTSMRNAllocated(msgNCTSMRNAllocated);

			getCommonFieldsDTO().setReferenceNumber(
					msgNCTSMRNAllocated.getReferenceNumber());
			body.writeBody();

			closeElement(); // soap:Envelope
			writer.writeEndDocument();

			writer.flush();
			writer.close();

			if (Config.getLogXML()) {
				Utils.log("(MapVMRToNCTSMRNAllocated getMessage) Msg = "
						+ xmlOutputString.toString());
			}

		} catch (XMLStreamException e) {

			e.printStackTrace();
		}

		return xmlOutputString.toString();
	}

	public void setMsgFields() {
		if (msgVMR.getVmrSubset() != null) {
			msgNCTSMRNAllocated.setReferenceNumber(msgVMR.getVmrSubset().getBeznr());
			// msgNCTSMRNAllocated.setUcrNumber(msgVMR.getVmrSubset().getArbnr());
			msgNCTSMRNAllocated.setUcrNumber(msgVMR.getVmrSubset().getMrn());
		}

		msgNCTSMRNAllocated.setPdfInformation(msgVMR.getPdfInformation());
		
		if (msgVMR.getPdfInformation() != null) {     //EI20130606
			if (msgVMR.getPdfInformation().getPdflist() != null && msgVMR.getPdfInformation().getPdflist().size() > 0) {
				isPDF = true;              
			}
		}
		
		if (msgVMR.getVmvSubset() != null) {        	
			msgNCTSMRNAllocated.setUcrNumber(msgVMR.getVmvSubset().getMrn());
		}
		
		//msgVMR.getVmvSubset() wurde und wird jetzt gar nicht gemapped
	}
}
