/*
 * Function    : MapASPToExpSta.java
 * Titel       :
 * Date        : 16.10.2008
 * Author      : Kewill CSF / houdek
 * Description : Mapping of FSS-Format ASP into KIDS-Format of DeclarationResponse
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

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgASP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAFP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASP;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyDeclarationResponseKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;



/**
 * Modul		: MapASPToExpSta<br>
 * Erstellt		: 16.10.2008<br>
 * Beschreibung	: Mapping of FSS-Format ASP into KIDS-Format of DeclarationResponse.
 * 
 * @author houdek
 * @version 6.0.00
 */
public class MapASPToExpSta extends KidsMessage {
	
	private MsgASP msgASP;
	private MsgExpSta msgExpSta;
	
	public MapASPToExpSta() {
		msgExpSta = new MsgExpSta();
	}

	public void setMsgASP(MsgASP msgASP) {
    	this.msgASP = msgASP;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {
		TsASP aspSubset = null;
		List<TsAFP> tmpAfpList = null;
		TsASK askSubset = null;
		if (msgASP == null) {
		  return;
		}
		
		aspSubset = msgASP.getAspSubset();
		if (aspSubset == null) {
			return;
		}
		
    	msgExpSta.setCancellationTime(aspSubset.getStodat());	
    	msgExpSta.setExitTime(aspSubset.getExtdat());	
    	msgExpSta.setReleaseTime(aspSubset.getUebdat());	
    	msgExpSta.setAcceptanceTime(aspSubset.getAndat());	
    	msgExpSta.setReceiveTime(aspSubset.getAckdat());		
    	msgExpSta.setTimeOfRejection(aspSubset.getGstdat());			
    	msgExpSta.setBeginTimeOfProcessing(aspSubset.getBwbdat());
    	
    	askSubset = msgASP.getAskSubset();
    	
    	if (askSubset != null)  {
    		msgExpSta.setReason(askSubset.getGrund());
    		msgExpSta.setStatusOfCompletion(askSubset.getStacde());
    	}
    	msgExpSta.setReferenceNumber(aspSubset.getBeznr());
    	
    	String tmpBeginTime = aspSubset.getGststr();
    	String tmpEndTime   = aspSubset.getGstend();
    	if (tmpBeginTime != null || tmpEndTime != null) {
    		LoadingTime tmpLoadingTime = new LoadingTime();
    		tmpLoadingTime.setBeginTime(tmpBeginTime);	
    		tmpLoadingTime.setEndTime(tmpEndTime);		
    		msgExpSta.setLoadingTime(tmpLoadingTime);
    	 }
    	
    	 tmpAfpList = msgASP.getAfpList();
    	 if (tmpAfpList != null) {
    		for (int i = 0; i < tmpAfpList.size(); i++) {			
    			TsAFP tmpAfp = new TsAFP();
    			tmpAfp = tmpAfpList.get(i);
    			
    			ErrorType tmpErrorMsg = new ErrorType();
    			tmpErrorMsg.setCode(tmpAfp.getErrcde());
    			tmpErrorMsg.setText(tmpAfp.getErrtxt());	
    			tmpErrorMsg.setPointer(tmpAfp.getZeiger());				
     			msgExpSta.addErrorList(tmpErrorMsg);
    		}
    	}	
    }
	
	
	public String getMessage(String auditId) {
	    
//        StringWriter kcxEnvelopeString = createKcxHeader(msgASP.getVorSubset().getKewillId(),
//                EProcedures.Export.toString().toUpperCase(), 
//                null, 
//                auditId);
        
//        StringWriter xmlOutputString = new StringWriter();
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
            
            KidsHeader             header = new KidsHeader(writer);
            header.setHeaderFields(msgASP.getVorSubset());
            header.setMessageName("DeclarationResponse");
            header.setMessageID(getMsgID());
            CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
            header.writeHeader();
            
            
     //      BodyExportASPtoKids body   = new BodyExportASPtoKids(writer);
            BodyDeclarationResponseKids body  = new BodyDeclarationResponseKids(writer);
            body.setMsgExpSta(msgExpSta);
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

}
