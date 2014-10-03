package com.kewill.kcx.component.mapping.countries.de.Port20;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.Port.EFssPortMessages;
import com.kewill.kcx.component.mapping.countries.de.Port20.fss2kids.MapBZRtoResponse;
import com.kewill.kcx.component.mapping.countries.de.Port20.fss2kids.MapSTRtoPortDeclarationStatus;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.MsgBZR;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.MsgSTR;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
 * Module       : Port<br>
 * Created      : 21.08.2013<br>
 * Description: transformer called by FssToKids to convert ZABIS-FSS format version 70 to KIDS. 
 * 				: new package because of - HEAD to Header: msgId and InReply
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Fss70ToKidsPort  {
	
	private BufferedReader  reader      	= null;
    private String          auditId     	= null;
    private TsVOR           tsVor       	= null;	
    private TsHead          tsHead      	= null;	  //EI201400505
	private String 			msgID			= null;
	private String			procedure		= null;
	private CommonFieldsDTO commonFieldsDTO = null;

	public  Fss70ToKidsPort(BufferedReader in, String auditId, TsVOR tsVor, TsHead tsHead,
									String msgID, CommonFieldsDTO commonFieldsDTO)  {
		setReader(in);
		setAuditId(auditId);
		setTsVor(tsVor);
		setMsgID(msgID);
		this.commonFieldsDTO = commonFieldsDTO;
		this.tsHead = tsHead;
	}
	
	public String readFss() throws FssException {
        String xml   = null;
        String naTyp = tsVor.getNatyp();
        tsVor.setCountry("DE");
        procedure = tsVor.getModul().trim();
        
        switch (EFssPortMessages.valueOf(naTyp)) {           
            case BZR:                                    //EI20120425   	
               	MsgBZR msgBZR = new MsgBZR();
               	msgBZR.setVorSubset(tsVor);
               	msgBZR.readMessage(reader);       
               	//EI20120426               
                MapBZRtoResponse mapBZRToResponse = new MapBZRtoResponse();
                mapBZRToResponse.setMsgBZR(msgBZR);
                mapBZRToResponse.setMsgID(msgID);
                mapBZRToResponse.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapBZRToResponse.getMessage();
                break;    
                
        	case STR:        	
        		MsgSTR msgSTR = new MsgSTR();
        		msgSTR.setVorSubset(tsVor);
        		msgSTR.readMessage(reader);
        		
        		MapSTRtoPortDeclarationStatus mapStrToDeclarationStatus = new MapSTRtoPortDeclarationStatus();
        		mapStrToDeclarationStatus.setMsgSTR(msgSTR);
        		mapStrToDeclarationStatus.setMsgID(msgID);
        		mapStrToDeclarationStatus.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapStrToDeclarationStatus.getMessage();        	
        		break;     
            
            case ERR:                   	
          		MsgERR msgERR = new MsgERR();
                msgERR.setVorSubset(tsVor);
                msgERR.readMessage(reader);
                MapERRToLocalAppResult mapERRToLocalAppResult = new MapERRToLocalAppResult();
                mapERRToLocalAppResult.setMsgERR(msgERR);
                mapERRToLocalAppResult.setMsgID(msgID);
                mapERRToLocalAppResult.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapERRToLocalAppResult.getMessage();
                break;                                
               
            case CON:    
            	procedure = tsVor.getModul().trim();
           		MapCONToLocalAppResult mapCONToLocalAppResult = new MapCONToLocalAppResult();
                mapCONToLocalAppResult.setMsgID(msgID);
                mapCONToLocalAppResult.setTsVOR(tsVor);
                mapCONToLocalAppResult.setTsHead(tsHead);
                mapCONToLocalAppResult.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCONToLocalAppResult.getMessage();
                break;   
               
            case STI:    
            	MsgSTI msgSTI = new MsgSTI();
            	msgSTI.setVorSubset(tsVor);
            	msgSTI.readMessage(reader);
                MapSTIToExpNck mapSti = new MapSTIToExpNck(); 
                mapSti.setMsgSTI(msgSTI);
                mapSti.setMsgID(msgID);
                mapSti.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapSti.getMessage();
                break;    
                
            default:
                throw new FssException("Unknown message type " + naTyp);
        }
        return xml;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public TsVOR getTsVor() {
		return tsVor;
	}

	public void setTsVor(TsVOR tsVor) {
		this.tsVor = tsVor;
	}
	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getProcedure() {
		return procedure;
	}
}
