package com.kewill.kcx.component.mapping.countries.de.Import;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.Import.fss2kids.V64.MapCRLtoImportDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Import.fss2kids.V64.MapCTXtoImportTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.fss2kids.V64.MapRECtoImportDeclarationResponse;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgCRL;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgCTX;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgREC;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
 * Module :     FssToKidsImport<br>
 * Created:     12.09.2011<br>
 * Description: transformer called by FssToKids to convert ZABIS-FSS format version 70 to KIDS. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Fss64ToKidsImport  {
	
	private BufferedReader  reader      	= null;
    private String          auditId     	= null;
    private TsVOR           tsVor       	= null;	
	private String 			msgID			= null;
	private String			procedure		= null;
	private CommonFieldsDTO commonFieldsDTO = null;

	public  Fss64ToKidsImport(BufferedReader in, String auditId, TsVOR tsVor, 
									String msgID, CommonFieldsDTO commonFieldsDTO)  {
		setReader(in);
		setAuditId(auditId);
		setTsVor(tsVor);
		setMsgID(msgID);
		this.commonFieldsDTO = commonFieldsDTO;
	}
	
	public String readFss() throws FssException {
        String xml   = null;
        String naTyp = tsVor.getNatyp();
        tsVor.setCountry("DE");
        //procedure = tsVor.getModul().trim();
        
        switch (EFssImportMessages.valueOf(naTyp)) {
            case CTX:                	                	
               	MsgCTX msgCTX = new MsgCTX();
               	msgCTX.setVorSubset(tsVor);
               	msgCTX.readMessage(reader);
               	MapCTXtoImportTaxAssessment mapCTXToImportTaxAssessment = new MapCTXtoImportTaxAssessment();
               	mapCTXToImportTaxAssessment.setMsgCTX(msgCTX);
               	mapCTXToImportTaxAssessment.setMsgID(msgID);
               	mapCTXToImportTaxAssessment.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapCTXToImportTaxAssessment.getMessage();            	        
                break;
               
            case CRL:
            	MsgCRL msgCRL = new MsgCRL();
            	msgCRL.setVorSubset(tsVor);
            	msgCRL.readMessage(reader);
            	MapCRLtoImportDeclarationDecision mapCRLToDeclarationDecision = new MapCRLtoImportDeclarationDecision();
            	mapCRLToDeclarationDecision.setMsgCRL(msgCRL);
            	mapCRLToDeclarationDecision.setMsgID(msgID);
            	mapCRLToDeclarationDecision.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapCRLToDeclarationDecision.getMessage();
            	break;            
                
            case REC:
            	MsgREC msgREC = new MsgREC();
            	msgREC.setVorSubset(tsVor);
            	msgREC.readMessage(reader);
            	MapRECtoImportDeclarationResponse mapRECtoDeclarationResponse = new MapRECtoImportDeclarationResponse();
            	mapRECtoDeclarationResponse.setMsgREC(msgREC);
            	mapRECtoDeclarationResponse.setMsgID(msgID);
            	mapRECtoDeclarationResponse.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapRECtoDeclarationResponse.getMessage();
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
                
            case CON:       //hier ist Zabis-Confirmation gemeint == Kids: nur Header, ohne Body
            	procedure = tsVor.getModul().trim();
           		MapCONToLocalAppResult mapCONToLocalAppResult = new MapCONToLocalAppResult();
                mapCONToLocalAppResult.setMsgID(msgID);
                mapCONToLocalAppResult.setTsVOR(tsVor);
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
