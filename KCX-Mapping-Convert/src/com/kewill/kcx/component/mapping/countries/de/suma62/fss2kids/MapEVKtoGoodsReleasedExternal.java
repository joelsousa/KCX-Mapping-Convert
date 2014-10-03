package com.kewill.kcx.component.mapping.countries.de.suma62.fss2kids;


import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62.MsgEVK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsEVP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.manifest.BodyGoodsReleasedExternalKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 12.11.2012<br>
 * Description	: Mapping of FSS EVK to KIDS GoodsReleasedExternal.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class MapEVKtoGoodsReleasedExternal extends KidsMessage {
	
	private MsgEVK msgEVK;	
	private MsgGoodsReleasedExternal message;
	
	public MapEVKtoGoodsReleasedExternal() {
		msgEVK = new MsgEVK();	
		message = new MsgGoodsReleasedExternal();
	}

	public void setMsgEVK(MsgEVK argument) {
		this.msgEVK = argument;						
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
	        header.setHeaderFields(msgEVK.getVorSubset());
	        header.setMessageName("GoodsReleasedExternal");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyGoodsReleasedExternalKids body   = new BodyGoodsReleasedExternalKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapEVKToGoodsReleasedExternal getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
			
	public void setMsgFields() {	
	
		Address custodian;
		Address place;
		
		if (msgEVK.getEVKSubset() != null) {
			message.setDateOfPresentation(msgEVK.getEVKSubset().getGstdat());
			message.setReferenceNumber(msgEVK.getEVKSubset().getMrn());
			message.setRegistrationNumber(msgEVK.getEVKSubset().getRegnr());
			List<TsEVP> evplist = msgEVK.getEvpList(); 
			if (evplist == null) {
				return;
			}
			if (evplist.isEmpty()) {
				return;
			}
			String text = "";
			for (TsEVP evp : evplist) {
				if (evp.getAdrtyp().equals("3")) {
					custodian = new Address();
					custodian.setEVP(evp);
					message.setCustodian(custodian);
				}
				if (evp.getAdrtyp().equals("2")) {
					place = new Address();
					place.setEVP(evp);
					message.setCustodian(place);
				}
			}
		}
    }
}
