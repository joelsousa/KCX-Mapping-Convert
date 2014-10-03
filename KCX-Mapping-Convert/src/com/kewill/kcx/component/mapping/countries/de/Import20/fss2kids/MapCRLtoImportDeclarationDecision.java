package com.kewill.kcx.component.mapping.countries.de.Import20.fss2kids;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationDecision;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgCRL;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgCRLPos;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCRP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXT;
import com.kewill.kcx.component.mapping.formats.kids.Import.BodyImportDeclarationDecisionKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Import<br>
 * Created		: 12.09.2011<br>
 * Description	: Mapping of FSS CTX to KIDS ImportDeclarationDecision.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapCRLtoImportDeclarationDecision extends KidsMessage {
	
	private MsgCRL msgCRL;	
	private MsgImportDeclarationDecision message;
	
	public MapCRLtoImportDeclarationDecision() {
		msgCRL = new MsgCRL();	
		message = new MsgImportDeclarationDecision();
	}

	public void setMsgCRL(MsgCRL argument) {
		this.msgCRL = argument;						
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
	        //header.setHeaderFields(msgCRL.getVorSubset());
	        header.setHeaderFieldsFromHead(msgCRL.getVorSubset(), msgCRL.getHeadSubset());  //EI20130826
	        header.setMessageName("ImportDeclarationDecision");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyImportDeclarationDecisionKids body   = new BodyImportDeclarationDecisionKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
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
	
		if (msgCRL.getCRLSubset() != null) {
			message.setReferenceNumber(msgCRL.getCRLSubset().getBeznr());
			message.setTemporaryMRN(msgCRL.getCRLSubset().getArbnr());
			message.setRegistrationNumber(msgCRL.getCRLSubset().getRegnr());  
			message.setCustomsClerkName(msgCRL.getCRLSubset().getZollsb());
			//AK20120716
			if (msgCRL.getANTSubset() != null) {
				message.setOrderNumber(msgCRL.getANTSubset().getAufnr());
			}
		}
		
		if (msgCRL.getCRLPosList() != null) {
			for (MsgCRLPos pos : msgCRL.getCRLPosList()) {
				GoodsItemDeclarationDecision item = new GoodsItemDeclarationDecision();
				TsCRP crpSubset = pos.getCRPSubset();
				List <TsTXT> txtList = pos.getTXTList();
				
				if (crpSubset != null) {   
					item.setItemNumber(crpSubset.getPosnr());
					item.setAcceptanceDate(crpSubset.getAndat());
					item.setAcceptanceCode(crpSubset.getKzann());
					item.setReleaseDate(crpSubset.getUebdat());
					item.setReleaseTime(crpSubset.getUebzei());
					item.setReleaseCode(crpSubset.getKzueb());					
					item.setReturnCode(crpSubset.getKzrgb());
					item.setRulingsCode(crpSubset.getKzaor());
					
					if (txtList != null) {
						String text = "";					
						for (TsTXT subset : txtList) {	
						   text = text + subset.getText() + " "; 
						}
						item.setText(text);
					}					
					message.addGoodsItemList(item);			
				}
				
			}
		}		
    }
	
}
