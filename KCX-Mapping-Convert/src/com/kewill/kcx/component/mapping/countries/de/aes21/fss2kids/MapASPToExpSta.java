package com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgASP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAFP;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyDeclarationResponseKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Export/AES21<br>
 * Created		: 30.07.2012<br>
 * Description	: Mapping of FSS-Format ASP V70 into KIDS-Format of DeclarationResponse V21.
 *
 * @author iwaniuk
 * @version 2.1.00
 * 
 * Changes:
 * ----------
 * EI20121005  : Header:MessageID and InReplyTo will be filled from TsVOR (and TsVOR from TsHEAD) 
 */

public class MapASPToExpSta extends KidsMessage {
	
	private MsgASP msgASP;
	private MsgExpSta msgExpSta;
	
	public MapASPToExpSta() {
		msgExpSta = new MsgExpSta();
	}

	public String getMessage(String auditId) {	    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(osw);
    
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            KidsHeader header = new KidsHeader(writer);
            header = new KidsHeader(writer);
            //header.setHeaderFields(msgASP.getVorSubset());
            header.setHeaderFieldsFromHead(msgASP.getVorSubset(), msgASP.getHeadSubset());     //EI201211128
            header.setMessageName("DeclarationResponse");
            
            CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
            commonFieldsDTO.setKcxId(header.getReceiver());
            commonFieldsDTO.setCountryCode(header.getCountryCode());
            commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
            header.setCommonFieldsDTO(commonFieldsDTO);	            
            
            header.writeHeader();
                
            BodyDeclarationResponseKids body  = new BodyDeclarationResponseKids(writer);
            body.setMessage(msgExpSta);
            body.setKidsHeader(header);

            getCommonFieldsDTO().setReferenceNumber(msgExpSta.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
      
        String xml = bos.toString();
        Utils.log("(MsgExportdeclarationResponse getMessage) xml = \n" + xml.replaceAll("><", ">\r\n  <"));
        return xml;
    }

	public void setMsgASP(MsgASP msgASP) {
    	this.msgASP = msgASP;
    	this.setMsgFields();
    }
		
	public void setMsgFields() {
		if (msgASP.getAskSubset() != null) {
			msgExpSta.setReferenceNumber(msgASP.getAskSubset().getBeznr());
			msgExpSta.setStatusOfCompletion(msgASP.getAskSubset().getStacde());
			msgExpSta.setReason(msgASP.getAskSubset().getGrund());
		}
		if (msgASP.getAspSubset() != null) {
			if (Utils.isStringEmpty(msgExpSta.getReferenceNumber())) {
				msgExpSta.setReferenceNumber(msgASP.getAspSubset().getBeznr());
			}
			msgExpSta.setCancellationTime(msgASP.getAspSubset().getStodat());	
			msgExpSta.setExitTime(msgASP.getAspSubset().getExtdat());	
			msgExpSta.setReleaseTime(msgASP.getAspSubset().getUebdat());	
			msgExpSta.setAcceptanceTime(msgASP.getAspSubset().getAndat());	
			msgExpSta.setReceiveTime(msgASP.getAspSubset().getAckdat());		
			msgExpSta.setTimeOfRejection(msgASP.getAspSubset().getGstdat());			
			msgExpSta.setBeginTimeOfProcessing(msgASP.getAspSubset().getBwbdat());

			if (!Utils.isStringEmpty(msgASP.getAspSubset().getTinan())) {  //new for V21				  	
				Party party = new Party();
				TIN partyTIN = new TIN();  
				party.setPartyTIN(partyTIN);  
				
				partyTIN.setTIN(msgASP.getAspSubset().getTinan());
				partyTIN.setBO(msgASP.getAspSubset().getNlan());
				msgExpSta.setDeclarant(party);    		
			}
			if (!Utils.isStringEmpty(msgASP.getAspSubset().getTinva())) {  //new for V21				
				Party party = new Party();
				TIN partyTIN = new TIN();
				party.setPartyTIN(partyTIN);    
				
				partyTIN.setTIN(msgASP.getAspSubset().getTinva());
				partyTIN.setBO(msgASP.getAspSubset().getNlva());
				msgExpSta.setAgent(party);
			}
    	
    		String tmpBeginTime = msgASP.getAspSubset().getGststr();
    		String tmpEndTime   = msgASP.getAspSubset().getGstend();
    		if (tmpBeginTime != null || tmpEndTime != null) {
    			LoadingTime tmpLoadingTime = new LoadingTime();
    			tmpLoadingTime.setBeginTime(tmpBeginTime);	
    			tmpLoadingTime.setEndTime(tmpEndTime);		
    			msgExpSta.setLoadingTime(tmpLoadingTime);
    		}
		}
    	if (msgASP.getAfpList() != null) {    		
    		for (TsAFP tmpAbf : msgASP.getAfpList()) {			    			    			
    			ErrorType tmpErrorMsg = new ErrorType();
    			tmpErrorMsg.setCode(tmpAbf.getErrcde());
    			tmpErrorMsg.setText(tmpAbf.getErrtxt());	
    			tmpErrorMsg.setPointer(tmpAbf.getZeiger());				
     			msgExpSta.addErrorList(tmpErrorMsg);
    		}
    	}	
    }

}
