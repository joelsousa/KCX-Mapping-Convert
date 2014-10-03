package com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgEVK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsEVP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyGoodsReleasedExternalKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 27.06.2013<br>
 * Description	: Mapping of FSS EVK to KIDS GoodsReleasedExternal.
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 */
public class MapEVKtoGoodsReleasedExternal extends KidsMessageManifest20 {
	
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
	        //header.setHeaderFields(msgEVK.getVorSubset());
	        header.setHeaderFieldsFromHead(msgEVK.getVorSubset(), msgEVK.getHeadSubset());  //EI20130711
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
			
	private void setMsgFields() {	
		if (msgEVK.getEvkSubset() != null) {
			message.setDateOfPresentation(msgEVK.getEvkSubset().getGstdat());
			message.setReferenceNumber(msgEVK.getEvkSubset().getMrn());
			message.setRegistrationNumber(msgEVK.getEvkSubset().getRegnr());
						
			if (msgEVK.getEvpList() != null) {			
				for (TsEVP evp : msgEVK.getEvpList()) {
					if (evp != null) {
						if (evp.getAdrtyp().equals("3")) {
							message.setCustodian(this.mapEVP(evp));
						}
						if (evp.getAdrtyp().equals("2")) {
							message.setPlaceOfCustody(this.mapEVP(evp));
						}
					}
				}
			}
			
			//EI2140314: message.setLocalApplication(this.mapLocalApplication(msgEVK.getHeadSubset(), msgEVK.getKunSubset(), "CUSTST"));
			message.setLocalApplication(mapLocalApplication("CUSTST", msgEVK.getHeadSubset(), msgEVK.getKunSubset(), msgEVK.getKupPosList()));	
			
			if (message.getLocalApplication() != null) {				
				message.setRegistrationDate(message.getLocalApplication().getRegistrationDate());				
			}
		}
    }
	
	private Address mapEVP(TsEVP evp) {
		Address address = null;
		if (evp == null) {
			return null;
		}
		address = new Address();
		address.setName(evp.getName());
		address.setStreet(evp.getStr());
		address.setPostalCode(evp.getPlz());
		address.setCity(evp.getOrt());
		address.setCountry(evp.getLdkz());
				
		return address;
	}

}
