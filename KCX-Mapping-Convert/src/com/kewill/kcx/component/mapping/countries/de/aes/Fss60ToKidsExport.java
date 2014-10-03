package com.kewill.kcx.component.mapping.countries.de.aes;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.ch.aus.fss2kids.MapCAAToReverseDeclaration;
import com.kewill.kcx.component.mapping.countries.ch.aus.fss2kids.MapCAEtoExpCHCancellationResponse;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.MapAZPToErrInf;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapADPToExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapAMRToExpCon;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapASPToExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapAUPToExpFup;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.countries.uk.exp.MapBDecToExpDat_not_used;
import com.kewill.kcx.component.mapping.formats.Bdec.messages.MsgECustoms;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAMR;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgASP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAUP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAZP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCAA;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCAE;

/**
 * Modul : Fss60ToKidsExport<br>
 * Erstellt : 04.03.2009<br>
 * Beschreibung : transformer called by FssToKids to convert ZABIS-FSS format 
 * 					version 60 to KIDS.
 * 
 * 
 * @author kron
 * @version 1.0.00
 */
public class Fss60ToKidsExport {
	
	private BufferedReader  reader      = null;
    private String          auditId     = null;
    private TsVOR           tsVor       = null;	
	private String 			msgID		= null;
	private String			procedure	= null;
	private CommonFieldsDTO commonFieldsDTO = null;

	public  Fss60ToKidsExport(BufferedReader in, String auditId, TsVOR tsVor, 
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
        switch (EFssMessages.valueOf(naTyp)) {
            case AMR:
            	tsVor.setCountry("DE");
                MsgAMR msgAMR = new MsgAMR();
                msgAMR.setVorSubset(tsVor);
                msgAMR.readMessage(reader);
                MapAMRToExpCon mapAMRToExpCon = new MapAMRToExpCon();
                mapAMRToExpCon.setMsgAMR(msgAMR);
                mapAMRToExpCon.setMsgID(msgID);
                mapAMRToExpCon.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAMRToExpCon.getMessage();
                break;
            case ADP:
            	tsVor.setCountry("DE");
                MsgADP msgADP = new MsgADP();
                msgADP.setVorSubset(tsVor);
                msgADP.readMessage(reader);
                MapADPToExpRel mapADPToExpRel = new MapADPToExpRel();
                mapADPToExpRel.setMsgADP(msgADP);
                mapADPToExpRel.setMsgID(msgID);
                mapADPToExpRel.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapADPToExpRel.getMessage();
                break;
            case STI:
            	tsVor.setCountry("DE");
                MsgSTI msgSTI = new MsgSTI();
                msgSTI.setVorSubset(tsVor);
                msgSTI.readMessage(reader);
                MapSTIToExpNck mapSTIToExpNck = new MapSTIToExpNck();
                mapSTIToExpNck.setMsgSTI(msgSTI);
                mapSTIToExpNck.setMsgID(msgID);
                mapSTIToExpNck.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapSTIToExpNck.getMessage();
                break;
            case AZP:
            	// noch V50: V6 fehlt!
            	tsVor.setCountry("DE");
                MsgAZP msgAZP = new MsgAZP();
                msgAZP.setVorSubset(tsVor);
                msgAZP.readMessage(reader);
                MapAZPToErrInf mapAZPToErrInf = new MapAZPToErrInf();
                mapAZPToErrInf.setMsgAZP(msgAZP);
                mapAZPToErrInf.setMsgID(msgID);
                mapAZPToErrInf.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAZPToErrInf.getMessage(); 
                break;
            case AUP:
            	tsVor.setCountry("DE");
                MsgAUP msgAUP = new MsgAUP();
                msgAUP.setVorSubset(tsVor);
                msgAUP.readMessage(reader);
                MapAUPToExpFup mapExpFupToAUP = new MapAUPToExpFup();
                mapExpFupToAUP.setMsgAUP(msgAUP);
                mapExpFupToAUP.setMsgID(msgID);
                mapExpFupToAUP.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpFupToAUP.getMessage();
                break;
            case ASP:
            	tsVor.setCountry("DE");
                MsgASP msgASP = new MsgASP();
                msgASP.setVorSubset(tsVor);
                msgASP.readMessage(reader);
                MapASPToExpSta mapASPToExpSta = new MapASPToExpSta();
                mapASPToExpSta.setMsgASP(msgASP);
                mapASPToExpSta.setMsgID(msgID);
                mapASPToExpSta.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapASPToExpSta.getMessage(auditId);
                break;
            case CAA:
            	// V50 bleibt
            	tsVor.setCountry("CH");
                MsgCAA msgCAA = new MsgCAA();
                msgCAA.setVorSubset(tsVor);
                msgCAA.readMessage(reader);
                MapCAAToReverseDeclaration mapCAAToReverseDeclaration = new MapCAAToReverseDeclaration();
                mapCAAToReverseDeclaration.setMsgCAA(msgCAA);
                tsVor.setCountry("CH");
                mapCAAToReverseDeclaration.setMsgID(msgID);
                mapCAAToReverseDeclaration.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCAAToReverseDeclaration.getMessage();
                break;
            case CAE:
            	// V50 bleibt
            	tsVor.setCountry("CH");
                MsgCAE msgCAE = new MsgCAE();
                msgCAE.setVorSubset(tsVor);
                msgCAE.readMessage(reader);
                MapCAEtoExpCHCancellationResponse mapCAEtoExpCHCancellationResponse = 
                                                       new MapCAEtoExpCHCancellationResponse();
                mapCAEtoExpCHCancellationResponse.setMsgCAE(msgCAE);
                tsVor.setCountry("CH");
                mapCAEtoExpCHCancellationResponse.setMsgID(msgID);
                mapCAEtoExpCHCancellationResponse.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCAEtoExpCHCancellationResponse.getMessage();
                break;
            case ERR:       
            	procedure = tsVor.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");	
            	}
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
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");	
            	}
            	MapCONToLocalAppResult mapCONToLocalAppResult = new MapCONToLocalAppResult();
                mapCONToLocalAppResult.setMsgID(msgID);
                mapCONToLocalAppResult.setTsVOR(tsVor);
                mapCONToLocalAppResult.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCONToLocalAppResult.getMessage();
                break;
            case HH:                	                	
               	tsVor.setCountry("DE");
               	MsgECustoms msgECustoms = new MsgECustoms();
               	msgECustoms.setVorSubset(tsVor);
               	//msgECustoms.setVorSubset(tsVor);
               	msgECustoms.readMessage(reader);
                MapBDecToExpDat_not_used mapBDecToExpDat = new MapBDecToExpDat_not_used();
                mapBDecToExpDat.setMessage(msgECustoms);
                mapBDecToExpDat.setMsgID(msgID);
                mapBDecToExpDat.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapBDecToExpDat.getMessage();
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
}
