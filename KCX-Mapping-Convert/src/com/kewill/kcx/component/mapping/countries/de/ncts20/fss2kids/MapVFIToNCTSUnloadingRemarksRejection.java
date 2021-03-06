package com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVFI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSUnloadingRemarksRejectionKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: NCTS<br>
 * Created		: 11.02.2013<br>
 * Description	: Mapping of FSS-VFI to KIDS-NCTSUnloadingRemarksRejection.
 * 				: EI20140205: VFU is replaced with VFP
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MapVFIToNCTSUnloadingRemarksRejection extends KidsMessage {
	
	private MsgVFI msgVFI;	
	private MsgNCTSUnloadingRemarksRejection msgNCTSUnloadingRemarksRejection;
	
	public MapVFIToNCTSUnloadingRemarksRejection() {
		msgVFI = new MsgVFI();	
		msgNCTSUnloadingRemarksRejection = new MsgNCTSUnloadingRemarksRejection();
	}

	public void setMsgVFI(MsgVFI argument) {
		this.msgVFI = argument;						
    	this.setMsgFields();    	
    }
	
	public String getMessage() {
	    StringWriter xmlOutputString = new StringWriter();
	    
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    try {
	        writer = factory.createXMLStreamWriter(xmlOutputString);

	        writeStartDocument(encoding, "1.0");
	        openElement("soap:Envelope");
	        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	        
	        KidsHeader  header = new KidsHeader(writer);
	        //header.setHeaderFields(msgVFI.getVorSubset());
	        header.setHeaderFieldsFromHead(msgVFI.getVorSubset(), msgVFI.getHeadSubset());  //EI20130711
	        header.setMessageName("UnloadingRemarksRejection");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyNCTSUnloadingRemarksRejectionKids body   = new BodyNCTSUnloadingRemarksRejectionKids(writer);
	        body.setMessage(msgNCTSUnloadingRemarksRejection);

            getCommonFieldsDTO().setReferenceNumber(msgNCTSUnloadingRemarksRejection.getReferenceNumber());
	        body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapVFIToNCTSArrivalRejection getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    	} catch (XMLStreamException e) {
	        
	    		e.printStackTrace();
	    	}
	    
	    	return xmlOutputString.toString();
		}
			
	public void setMsgFields() {		
		if (msgVFI.getVfiSubset() != null) {
			msgNCTSUnloadingRemarksRejection.setUCRNumber(msgVFI.getVfiSubset().getRegnr());
			msgNCTSUnloadingRemarksRejection.setArrivalRejectionDate(msgVFI.getVfiSubset().getRegdat());
			//EI20110527: msgNCTSUnloadingRemarksRejection.setArrivalRejectionDateFormat(EFormat.ST_Date);
			msgNCTSUnloadingRemarksRejection.setArrivalRejectionDateFormat(EFormat.KIDS_Date);
			msgNCTSUnloadingRemarksRejection.setReferenceNumber(msgVFI.getVfiSubset().getBeznr());
		}
		
		if (msgVFI.getVfpList() != null) {
			for (int i = 0, listSize = msgVFI.getVfpList().size(); i < listSize; i++) {
				TsVFP vfpSubset = msgVFI.getVfpList().get(i);
				if (vfpSubset != null) {
					FunctionalErrorNcts error = new FunctionalErrorNcts();
				
					error.setCode(vfpSubset.getErrcde());
					error.setPointer(vfpSubset.getZeiger());
					error.setText(vfpSubset.getErrtxt());
				
					msgNCTSUnloadingRemarksRejection.addErrorList(error);
				}
			}
		}
    }
}
