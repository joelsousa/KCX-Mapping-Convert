package com.kewill.kcx.component.mapping.countries.de.aes21;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.ch.aus20.fss2kids.MapCAAToReverseDeclaration;
import com.kewill.kcx.component.mapping.countries.ch.aus20.fss2kids.MapCAEtoExpCHCancellationResponse;
import com.kewill.kcx.component.mapping.countries.de.aes.EFssMessages;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapACLToControlNotification;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapADPToExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapAMRToExpCon;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapASPToExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapAUGToExpUrg;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapAUPToExpFup;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapAZPToErrInf;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAMR;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAZP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgASP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgAUG;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgAUP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72.MsgACL;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCAA;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCAE;

/**
 * Module : Export/aes<br>
 * Created		: 25.07.2012<br>
 * Description : transformer called by FssToKids to convert ZABIS-FSS format version 70
 * 			   : to KIDS V21 (some of mapping classes are unchanges for V21).
 * 
 * 
 * @author iwaniuk
 * @version 2.1.00
 * 
 * Changes for AES22: 
 * 			MsgAUP will be imported from ...aes.messages.V70
 * 			MsgACL new for AES22
 */
public class Fss70ToKidsExport {
	
	private BufferedReader  reader      = null;
    private String          auditId     = null;
    private TsVOR           tsVor       = null;	
    private TsHead          tsHead      = null;	  //EI20121005
	private String 			msgID		= null;
	private String			procedure	= null;
	private CommonFieldsDTO commonFieldsDTO = null;

	//EI20121005: public  Fss70ToKidsExport(BufferedReader in, String auditId, TsVOR tsVor, String msgID, CommonFieldsDTO commonFieldsDTO)  {
	public  Fss70ToKidsExport(BufferedReader in, String auditId, TsVOR vor, TsHead head, 
							  String msgID, CommonFieldsDTO commonFieldsDTO)  {
			
		setReader(in);
		setAuditId(auditId);
		this.tsVor = vor;		
		this.tsHead = head;                 //EI20121005		
		this.msgID = msgID;
		this.commonFieldsDTO = commonFieldsDTO;
	}
	
	public String readFss() throws FssException {
        String xml   = null;
        String naTyp = tsVor.getNatyp();
        tsVor.setCountry("DE");
        tsHead.setCountry("DE");
        
        switch (EFssMessages.valueOf(naTyp)) {           
            case ADP:                          //changes for V21            	
                MsgADP msgADP = new MsgADP();
                msgADP.setVorSubset(tsVor); 
                msgADP.setHeadSubset(tsHead);  //EI20121128
                msgADP.readMessage(reader);
                //EI20130523: MapADPToExpRel mapADPToExpRel = new MapADPToExpRel();
                MapADPToExpRel mapADPToExpRel = new MapADPToExpRel(commonFieldsDTO); //EI20130523
                mapADPToExpRel.setMsgADP(msgADP);
                mapADPToExpRel.setMsgID(msgID);
                mapADPToExpRel.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapADPToExpRel.getMessage();
                break;
            case ASP:                         //changes for V21            	
                MsgASP msgASP = new MsgASP();
                msgASP.setVorSubset(tsVor);
                msgASP.setHeadSubset(tsHead);
                msgASP.readMessage(reader);
                MapASPToExpSta mapASPToExpSta = new MapASPToExpSta();
                mapASPToExpSta.setMsgASP(msgASP);
                mapASPToExpSta.setMsgID(msgID);
                mapASPToExpSta.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapASPToExpSta.getMessage(auditId);
                break;
            case AUP:    //laut aes-fss-70.doc AUP ist unveraendert, aber MsgExpFup neu definiert            	
                MsgAUP msgAUP = new MsgAUP();
                msgAUP.setVorSubset(tsVor);
                msgAUP.setHeadSubset(tsHead);
                msgAUP.readMessage(reader);
                MapAUPToExpFup mapAupToFup = new MapAUPToExpFup();
                mapAupToFup.setMsgAUP(msgAUP);
                mapAupToFup.setMsgID(msgID);
                mapAupToFup.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAupToFup.getMessage();
                break;
            case AUG:                         //new for V70/V21            	
                MsgAUG msgAUG = new MsgAUG();
                msgAUG.setVorSubset(tsVor);
                msgAUG.setHeadSubset(tsHead);
                msgAUG.readMessage(reader);
                MapAUGToExpUrg mapAugToFup = new MapAUGToExpUrg();
                mapAugToFup.setMsgAUG(msgAUG);
                mapAugToFup.setMsgID(msgID);
                mapAugToFup.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAugToFup.getMessage();
                break;  
            case AMR:                            	
                MsgAMR msgAMR = new MsgAMR();
                msgAMR.setVorSubset(tsVor);
                msgAMR.setHeadSubset(tsHead);
                msgAMR.readMessage(reader);
                MapAMRToExpCon mapAMRToExpCon = new MapAMRToExpCon();
                mapAMRToExpCon.setMsgAMR(msgAMR);
                mapAMRToExpCon.setMsgID(msgID);
                mapAMRToExpCon.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAMRToExpCon.getMessage();
                break;
            case ACL:      //EI20130722                            	
            	MsgACL msgACL = new MsgACL();
                msgACL.setVorSubset(tsVor);
                msgACL.setHeadSubset(tsHead);
                msgACL.readMessage(reader);
                MapACLToControlNotification mapAclToCtlNot = new MapACLToControlNotification();
                mapAclToCtlNot.setMsgACR(msgACL);
                mapAclToCtlNot.setMsgID(msgID);
                mapAclToCtlNot.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAclToCtlNot.getMessage();
                break;
            case STI:                        	
                MsgSTI msgSTI = new MsgSTI();
                msgSTI.setVorSubset(tsVor);
                msgSTI.setHeadSubset(tsHead);
                msgSTI.readMessage(reader);
                MapSTIToExpNck mapSTIToExpNck = new MapSTIToExpNck();
                mapSTIToExpNck.setMsgSTI(msgSTI);
                mapSTIToExpNck.setMsgID(msgID);
                mapSTIToExpNck.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapSTIToExpNck.getMessage();
                break;
            case AZP:                       	            	
                MsgAZP msgAZP = new MsgAZP();
                msgAZP.setVorSubset(tsVor);
                msgAZP.setHeadSubset(tsHead);
                msgAZP.readMessage(reader);
                MapAZPToErrInf mapAZPToErrInf = new MapAZPToErrInf();
                mapAZPToErrInf.setMsgAZP(msgAZP);
                mapAZPToErrInf.setMsgID(msgID);
                mapAZPToErrInf.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAZPToErrInf.getMessage(); 
                break;                    
            case CAA:
            	// V70
            	tsVor.setCountry("CH");
            	tsHead.setCountry("CH");
                MsgCAA msgCAA = new MsgCAA();
                msgCAA.setVorSubset(tsVor);
                msgCAA.setHeadSubset(tsHead);
                msgCAA.readMessage(reader);
                MapCAAToReverseDeclaration mapCAAToReverseDeclaration = new MapCAAToReverseDeclaration();
                mapCAAToReverseDeclaration.setMsgCAA(msgCAA);                
                mapCAAToReverseDeclaration.setMsgID(msgID);
                mapCAAToReverseDeclaration.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCAAToReverseDeclaration.getMessage();
                break;
            case CAE:
            	// V70
            	tsVor.setCountry("CH");
            	tsHead.setCountry("CH");
                MsgCAE msgCAE = new MsgCAE();
                msgCAE.setVorSubset(tsVor);
                msgCAE.setHeadSubset(tsHead);
                msgCAE.readMessage(reader);
                MapCAEtoExpCHCancellationResponse mapCAEtoExpCHCancellationResponse = new MapCAEtoExpCHCancellationResponse();
                mapCAEtoExpCHCancellationResponse.setMsgCAE(msgCAE);                
                mapCAEtoExpCHCancellationResponse.setMsgID(msgID);
                mapCAEtoExpCHCancellationResponse.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCAEtoExpCHCancellationResponse.getMessage();
                break;
            case ERR:       
            	procedure = tsVor.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            		tsHead.setCountry("CH");
            	} 
                MsgERR msgERR = new MsgERR();
                msgERR.setVorSubset(tsVor);
                msgERR.setHeadSubset(tsHead);
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
            		tsHead.setCountry("CH");
            	} 
            	MapCONToLocalAppResult mapCONToLocalAppResult = new MapCONToLocalAppResult();
                mapCONToLocalAppResult.setMsgID(msgID);
                mapCONToLocalAppResult.setTsVOR(tsVor);
                mapCONToLocalAppResult.setTsHead(tsHead);
                mapCONToLocalAppResult.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCONToLocalAppResult.getMessage();
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
