package com.kewill.kcx.component.mapping.countries.de.suma62.fss2kids;


import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.CustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62.MsgSCK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSCP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.manifest.BodyProcessingResultsKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 11.02.2013<br>
 * Description	: Mapping of FSS-SCK to KIDS-ProcessingResults.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapSCKtoProcessingResults extends KidsMessage {
	
	private MsgSCK msgSCK;	
	private MsgProcessingResults message;
	
	public MapSCKtoProcessingResults() {
		msgSCK = new MsgSCK();	
		message = new MsgProcessingResults();
	}

	public void setMsgSCK(MsgSCK argument) {
		this.msgSCK = argument;						
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
	        header.setHeaderFields(msgSCK.getVorSubset());
	        header.setMessageName("ProcessingResults");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyProcessingResultsKids body = new BodyProcessingResultsKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapSWVToProcessingResults getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
			
	public void setMsgFields() {	
	
		if (msgSCK.getSckSubset() != null) {
			message.setFlightNumber(msgSCK.getSckSubset().getFltnum());
			message.setRegistrationNumber(msgSCK.getSckSubset().getRegnr());
			message.setReferenceNumber(msgSCK.getSckSubset().getBeznr());
			message.setTypeOfTransaction(msgSCK.getSckSubset().getNagru());
			if (!Utils.isStringEmpty(msgSCK.getSckSubset().getVgref())) {
				HeaderExtensions he = new HeaderExtensions();
				he.setHeadPosID(msgSCK.getSckSubset().getVgref());
				//he.setPlac(msgSCK.getSckSubset().getBefnum());
				//he.setPlac(msgSCK.getSckSubset().getAnkdat());
				message.setHeaderExtensions(he);
			}
			
			ArrayList<TsSCP> scpList = msgSCK.getScpList(); 
			if (scpList != null) {						
				for (TsSCP scp : scpList) {
					if (scp != null) {
						message.addGoodsItemList(this.mapGoodsItem(scp));
					}
				}
			}
		}
    }
	
	private ItemProcessingResults mapGoodsItem(TsSCP scp) {
		if (scp == null) { 
			return null;
		}
		if (scp.isEmpty()) { 
			return null;
		}
		ItemProcessingResults item = new ItemProcessingResults();		
		item.setItemNumber(scp.getPosnr());
		item.setCustodianTIN(scp.getVrwznr());
		item.setCustomsResponse(mapCustomsResponse(scp));			
		item.setReferencedSpecification(getReferencedSpecification(scp));			
		
		return item;
	}

	private CustomsResponse mapCustomsResponse(TsSCP scp) {
		if (scp == null) {
			return null;
		}
		CustomsResponse cr = null;
		if (!Utils.isStringEmpty(scp.getFehgew()) ||
			!Utils.isStringEmpty(scp.getFehnr()) ||
			!Utils.isStringEmpty(scp.getFehtxt())) {
			
			cr = new CustomsResponse();			
			cr.setType(scp.getFehgew());
			cr.setPointer(scp.getFehnr());
			cr.setReason(scp.getFehtxt());
		}		
		return cr;
	}

	private ReferencedSpecification getReferencedSpecification(TsSCP scp) {
		if (scp == null) {
			return null;
		}
		ReferencedSpecification rs = null;
	
		if (!Utils.isStringEmpty(scp.getAwbzzz()) ||
			!Utils.isStringEmpty(scp.getKzawb())) {
			
			rs = new ReferencedSpecification();
			rs.setTypeOfSpecificationID(scp.getKzawb());
			rs.setSpecificationID(scp.getAwbzzz());
		}	
		return rs;
	}	
}
