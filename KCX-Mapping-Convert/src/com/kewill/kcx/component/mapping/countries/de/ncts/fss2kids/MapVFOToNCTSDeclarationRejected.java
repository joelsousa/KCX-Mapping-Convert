package com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVFO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSDeclarationRejectedKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MapVFOToNCTSDeclarationRejected
 * Created		: 03.09.2010
 * Description	: mapping of FSS to KIDS of VIA.
 * 
 * @author	: Michelle Bauza
 * @version	: 6.2.00
 *
 */
public class MapVFOToNCTSDeclarationRejected extends KidsMessage {
	private MsgVFO msgVFO;
	private MsgNCTSDeclarationRejected msgNCTSDeclarationRejected;
	
	public MapVFOToNCTSDeclarationRejected() {
		msgVFO	= new MsgVFO();
		msgNCTSDeclarationRejected	= new MsgNCTSDeclarationRejected();
	}
	
	public void setMsgVFO(MsgVFO argument) {
		this.msgVFO	= argument;
		this.setMsgFields();
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		XMLOutputFactory factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			
			writeStartDocument(encoding, "1.0");
				openElement("soap:Envelope");
					setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
					
					KidsHeader header	= new KidsHeader(writer);
					header.setHeaderFields(msgVFO.getVorSubset());
					header.setMessageName("NCTSDeclarationRejected");
					header.setMessageID(getMsgID());
					CommonFieldsDTO commonFieldsDTO	= new CommonFieldsDTO();
					commonFieldsDTO.setKcxId(header.getReceiver());
					commonFieldsDTO.setCountryCode(header.getCountryCode());
					commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
					header.setCommonFieldsDTO(commonFieldsDTO);
					header.writeHeader();
					
					BodyNCTSDeclarationRejectedKids body	= 
						new BodyNCTSDeclarationRejectedKids(writer);
						body.setMessage(msgNCTSDeclarationRejected);
					
					getCommonFieldsDTO().setReferenceNumber(
							msgNCTSDeclarationRejected.getReferenceNumber());
						body.writeBody();
						
				closeElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
			
			if (Config.getLogXML()) {
				Utils.log("(MapVFOToNCTSDeclarationRejected getMessage) Msg = " + 
						xmlOutputString.toString());
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
	public void setMsgFields() {
		if (msgVFO.getVfoSubset() != null) {
			msgNCTSDeclarationRejected.setReferenceNumber(msgVFO.getVfoSubset().getBeznr());
			msgNCTSDeclarationRejected.setUcrNumber(msgVFO.getVfoSubset().getMrn());
		}
		
		if (msgVFO.getVfpSubsetList() != null) {
			for (TsVFP tsVFP : msgVFO.getVfpSubsetList()) {
				if (tsVFP != null) {
					//msgNCTSDeclarationRejected.setReferenceNumber(tsVFP.getBeznr());				
					FunctionalErrorNcts err	= new FunctionalErrorNcts();
					err.setCode(tsVFP.getErrcde());
					err.setText(tsVFP.getErrtxt());
					err.setPointer(tsVFP.getZeiger());
				
					msgNCTSDeclarationRejected.getErrorList().add(err);
				}
			}
		}
	}
}
