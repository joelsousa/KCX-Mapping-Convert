package com.kewill.kcx.component.mapping.countries.de.Import20.fss2kids;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.MsgError;
import com.kewill.kcx.component.mapping.countries.common.MsgErrorPos;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclarationResponse;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationResponse;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgREC;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsREP;
import com.kewill.kcx.component.mapping.formats.kids.Import.BodyImportDeclarationResponseKids;
import com.kewill.kcx.component.mapping.formats.kids.base.BodyErrorMessageKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Import<br>
 * Created		: 12.09.2011<br>
 * Description	: Mapping of FSS CTX to KIDS ImportDeclarationResponse.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapRECtoImportDeclarationResponse extends KidsMessage {
	
	private MsgREC msgREC;	
	private MsgImportDeclarationResponse message;
	//private MsgErrInf msgErrInf;
	private MsgError msgError;                   //EI20111216
	private List<TsREP> errList = null;
	
	public MapRECtoImportDeclarationResponse() {
		msgREC = new MsgREC();	
		message = new MsgImportDeclarationResponse();
		//msgErrInf = new MsgErrInf();
		msgError = new MsgError();
	}

	public void setMsgREC(MsgREC argument) {
		this.msgREC = argument;						
    	this.setMsgFields();    	
    }
	
	public String getMessage() {
	    StringWriter xmlOutputString = new StringWriter();
	    boolean recWithRegnr = false;	   //EI20130124
	    
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    try {
	        writer = factory.createXMLStreamWriter(xmlOutputString);

	        writeStartDocument(encoding, "1.0");
	        openElement("soap:Envelope");
	        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	        
	        KidsHeader  header = new KidsHeader(writer);
	        //header.setHeaderFields(msgREC.getVorSubset());
	        header.setHeaderFieldsFromHead(msgREC.getVorSubset(), msgREC.getHeadSubset());  //EI20130826
	        
	        recWithRegnr = checkRegnr(msgREC);
	        
	        // important note !
	        // CK 08.12.2011 if customs message "CUSREC" contains errors: create localAppResult with errors
	        // else create ImportDeclarationResponse
	        
	        //EI20130124: if (msgREC.isRecWithErrors()) {
	        if (msgREC.isRecWithErrors() && !recWithRegnr) {     //EI20130124
	        	
	        	header.setMessageName("ErrorMessage");
	        	//BodyErrorInformationKids body  = new BodyErrorInformationKids(writer);
	        	BodyErrorMessageKids body  = new BodyErrorMessageKids(writer);   //EI20111216
		        header.setMessageID(getMsgID());
		        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
		        commonFieldsDTO.setKcxId(header.getReceiver());
		        commonFieldsDTO.setCountryCode(header.getCountryCode());
		        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
		        header.setCommonFieldsDTO(commonFieldsDTO);

		        header.writeHeader();
		        //body.setMsgErrInf(msgErrInf);
		        body.setMsgError(msgError);   //EI20111216

	            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
	            body.writeBody();
		        
		        closeElement();  // soap:Envelope
		        writer.writeEndDocument();
		        writer.flush();
		        writer.close();

	        } else {
	        	header.setMessageName("ImportDeclarationResponse");	
	        	BodyImportDeclarationResponseKids body = new BodyImportDeclarationResponseKids(writer);
		        header.setMessageID(getMsgID());
		        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
		        commonFieldsDTO.setKcxId(header.getReceiver());
		        commonFieldsDTO.setCountryCode(header.getCountryCode());
		        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
		        header.setCommonFieldsDTO(commonFieldsDTO);

		        header.writeHeader();
		        body.setMessage(message);

	            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
	            body.writeBody();
		        
		        closeElement();  // soap:Envelope
		        writer.writeEndDocument();
		        writer.flush();
		        writer.close();

	        }
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapVFIToNCTSArrivalRejection getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
			
	public void setMsgFields() {	
	
		// message used for ImportDeclaratinResponse
		if (msgREC.getRECSubset() != null) {
			message.setReferenceNumber(msgREC.getRECSubset().getBeznr());
			message.setTemporaryMRN(msgREC.getRECSubset().getArbnr());
			message.setRegistrationNumber(msgREC.getRECSubset().getRegnr());  
			message.setRegistrationDate(msgREC.getRECSubset().getRegdat());			
		}
		
		// msgError used for ErrorMessage
		msgError.setReferenceNumber(msgREC.getRECSubset().getBeznr());
		msgError.setProcedureType("Import");
		TsREP repSubset = null;
		errList = msgREC.getREPList(); 
		
		// EI20111216: 
		if (errList  != null)  {			
			for (TsREP rep : errList) {
				if (rep != null) {
					MsgErrorPos item = new MsgErrorPos();
					item.setItemNumber(rep.getPosnr());				
					item.addPositionErrorList(this.getPositionError(rep));					
					msgError.addGoodsItemList(item);
					
					//EI20130124:
					GoodsItemDeclarationResponse itemReg = new GoodsItemDeclarationResponse();
					itemReg.setItemNumber(rep.getPosnr());		
					itemReg.setKindOfError("ERR");	
					itemReg.setErrorCode(rep.getErrcd());
					itemReg.setErrorText(rep.getErrtxt());
					message.addGoodsItemList(itemReg);										
				}
			}
		}				
    }
	
	private PositionError setItemsLocalAppResult(TsREP rep) {
	    PositionError error = new PositionError();
	    	
	    error.setKindOfError("ERR");
	    error.setErrorNumber(rep.getPosnr());
	    error.setErrorCode(rep.getErrcd());
	    error.setErrorText(rep.getErrtxt());

	    return error;
	}	
	
	private PositionError getPositionError(TsREP rep) {
		if (rep == null) {
		    return null;	
		}
    	PositionError error = new PositionError();
    	
    	error.setKindOfError("ERR");    	
    	error.setErrorCode(rep.getErrcd());
    	error.setErrorText(rep.getErrtxt());

    	return error;
    }	
	
	private ErrorType getError(String lfd, TsREP rep) {
	    ErrorType error = new ErrorType();
	    
	    error.setUniqueNumber(lfd);
	    error.setNumber(rep.getPosnr());
	    error.setCode(rep.getErrcd());
	    error.setText(rep.getErrtxt());

	    return error;
	}
	
	private boolean checkRegnr(MsgREC msgREC) {   //EI20130124
		boolean ret = false;		
		if (msgREC == null) {
			return false;
		}
		if (msgREC.getRECSubset() == null) {
			return false;
		}
		
		if (Utils.isStringEmpty(msgREC.getRECSubset().getRegnr())) {
			ret = false;
		} else {
			ret = true;
		}
				
		return ret;
	}
}
