package com.kewill.kcx.component.mapping.countries.de.Import20;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.ch.Import20.fss2kids.MapCTXCHtoImportTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.EFssImportMessages;
import com.kewill.kcx.component.mapping.countries.de.Import20.fss2kids.MapCRLtoImportDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Import20.fss2kids.MapRECtoImportDeclarationResponse;
import com.kewill.kcx.component.mapping.countries.de.Import20.fss2kids.MapCTXtoImportTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgCRL;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgREC;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgCTX;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70.MsgCTXCH;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module :     Import 20<br>
 * Created:     12.11.2012<br>
 * Description: transformer called by FssToKids to convert ZABIS-FSS format version 70 to KIDS. 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */


//  EI20121107:  Import 20/70 noch nicht definiert


public class Fss70ToKidsImport  {
	
	private BufferedReader  reader      	= null;
    private String          auditId     	= null;
    private TsVOR           tsVor       	= null;	
    private TsHead          tsHead       	= null;	
	private String 			msgID			= null;
	private String			procedure		= null;
	private String			country		    = null;   //EI20121107
	private CommonFieldsDTO commonFieldsDTO = null;

	public  Fss70ToKidsImport(BufferedReader in, String auditId, TsVOR vor, TsHead head, 
									String msgID, CommonFieldsDTO commonFieldsDTO)  {
		this.reader = in;
		this.auditId = auditId;
		this.tsVor = vor;
		this.tsHead = head;
		this.msgID = msgID;
		this.commonFieldsDTO = commonFieldsDTO;
		this.country = commonFieldsDTO.getCountryCode();
	}
	
	public String readFss() throws FssException {
        String xml   = null;
        String naTyp = tsVor.getNatyp();
        tsVor.setCountry("DE");
        procedure = tsVor.getModul().trim();
        
        switch (EFssImportMessages.valueOf(naTyp)) {           
            case CTX: 
            	if (!Utils.isStringEmpty(country) && country.equals("CH")) {
            		MsgCTXCH msgCH = new MsgCTXCH();
            		tsVor.setCountry("CH");
            		msgCH.setVorSubset(tsVor);
            		msgCH.readMessage(reader);
                   	MapCTXCHtoImportTaxAssessment mapCTXToImportTaxAssessment = new MapCTXCHtoImportTaxAssessment();
            		//MapCTXCHtoImportTaxAssessment mapCTXToImportTaxAssessment = new MapCTXCHtoImportTaxAssessment(commonFieldsDTO); //EI20130523
                    mapCTXToImportTaxAssessment.setMsgCTX(msgCH);
                   	mapCTXToImportTaxAssessment.setMsgID(msgID);
                   	mapCTXToImportTaxAssessment.setCommonFieldsDTO(commonFieldsDTO);
                	xml = mapCTXToImportTaxAssessment.getMessage();             	           	       
               
            	} else {
            		MsgCTX msgCTX = new MsgCTX();
                   	msgCTX.setVorSubset(tsVor);
                   	msgCTX.readMessage(reader);
                   	//MapCTXtoImportTaxAssessment mapCTXToImportTaxAssessment = new MapCTXtoImportTaxAssessment();
                   	MapCTXtoImportTaxAssessment mapCTXToImportTaxAssessment = new MapCTXtoImportTaxAssessment(commonFieldsDTO); //EI20130528
                    mapCTXToImportTaxAssessment.setMsgCTX(msgCTX);
                   	mapCTXToImportTaxAssessment.setMsgID(msgID);
                   	mapCTXToImportTaxAssessment.setCommonFieldsDTO(commonFieldsDTO);
                	xml = mapCTXToImportTaxAssessment.getMessage();   
            	}
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
                
            case REC:    
            	MsgREC msgREC = new MsgREC();
            	msgREC.setVorSubset(tsVor);
            	msgREC.readMessage(reader);
            	
            	// call one mapping to convert to localAppResult or ImportdeclarationResponse
            	// depending on content of message "REC"
            	MapRECtoImportDeclarationResponse mapRec = new MapRECtoImportDeclarationResponse(); 
            	mapRec.setMsgREC(msgREC);
            	mapRec.setMsgID(msgID);
            	mapRec.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapRec.getMessage();
                break;            
            default:
                throw new FssException("Unknown message type " + naTyp);
        }
        return xml;
	}

	
}
