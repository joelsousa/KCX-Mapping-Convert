package com.kewill.kcx.component.mapping.countries.de.ncts;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVFIToNCTSArrivalRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVFIToNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVFOToNCTSDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVIAToNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVMRToNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVPHToNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVSOToNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids.MapVURToNCTSUnloadingRemarks;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVFI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVFO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVMR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVSO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFI;
import com.kewill.kcx.component.mapping.util.Utils;

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
public class Fss62ToKidsNCTS  {
	
	private BufferedReader  reader      	= null;
    private String          auditId     	= null;
    private TsVOR           tsVor       	= null;	
	private String 			msgID			= null;
	private String			procedure		= null;
	private CommonFieldsDTO commonFieldsDTO = null;

	public  Fss62ToKidsNCTS(BufferedReader in, String auditId, TsVOR tsVor, 
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
        
        switch (EFssNCTSMessages.valueOf(naTyp)) {
            case VFI:                	                	
               	MsgVFI msgVFI = new MsgVFI();
               	msgVFI.setVorSubset(tsVor);
               	msgVFI.readMessage(reader);
               	TsVFI vfiSubset = msgVFI.getVfiSubset();               
               	//ME: VFI kann eine UnloadingRemarksRejection oder eine ArrivalRejection sein, dies wird über
               	//den Natyp im VFI Satz bestimmt: 
               	//UNLREP oder  DEEURE =  Fehler auf einen Entladekommentar ( UnloadingRemarksRejection)
               	//ANKANZ  oder  DEEANC = Fehler auf Ankunftsanzeige (ArrivalRejection)                
               	//ANKANZ und UNLREP sind eigentlich falsch, wird aber derzeit in der FSS so besetzt.
               	//Das soll aber geändert werden in DEEANC, DEEURE, wurde das angepasst können hier die Prüfungen auf 
               	//ANKANZ und UNLREP entfernt werden.
               	//bis das geschehen ist soll hier aber beides verarbeitet werden. Bei fragen -> CK
               	if (vfiSubset != null && vfiSubset.getNatyp() != null &&
               			(vfiSubset.getNatyp().equalsIgnoreCase("DEEANC") || 
               			 vfiSubset.getNatyp().equalsIgnoreCase("DEEAND") ||     //EI20130213
               		     vfiSubset.getNatyp().equalsIgnoreCase("ANKANZ"))) {
	               	 MapVFIToNCTSArrivalRejection mapVFIToNCTSArrivalRejection = new MapVFIToNCTSArrivalRejection();
	                 mapVFIToNCTSArrivalRejection.setMsgVFI(msgVFI);
	                 mapVFIToNCTSArrivalRejection.setMsgID(msgID);
	                 mapVFIToNCTSArrivalRejection.setCommonFieldsDTO(commonFieldsDTO);
	                 xml = mapVFIToNCTSArrivalRejection.getMessage();
               	} else if (vfiSubset != null && vfiSubset.getNatyp() != null &&
               			(vfiSubset.getNatyp().equalsIgnoreCase("DEEURE") || 
               			 vfiSubset.getNatyp().equalsIgnoreCase("DEEURF") ||     //EI20130213
               			 vfiSubset.getNatyp().equalsIgnoreCase("UNLREP"))) {
                    MapVFIToNCTSUnloadingRemarksRejection mapVFIToNCTSUnloadingRemarksRejection = 
                    	new MapVFIToNCTSUnloadingRemarksRejection();
                    mapVFIToNCTSUnloadingRemarksRejection.setMsgVFI(msgVFI);
                    mapVFIToNCTSUnloadingRemarksRejection.setMsgID(msgID);
                    mapVFIToNCTSUnloadingRemarksRejection.setCommonFieldsDTO(commonFieldsDTO);
                    xml = mapVFIToNCTSUnloadingRemarksRejection.getMessage();
               	} else {
               		String errMsg = "";
               		if (vfiSubset == null) {
               			errMsg = "No VFI-Subset in message!";
               		} else if (vfiSubset.getNatyp() == null) {
               			errMsg = "No message type declared in VFI-Subset!";
               		} else {
               			errMsg = "VFI-Subset contains unknown message type = " + vfiSubset.getNatyp();
               		}
               		throw new FssException(errMsg);
               	}
                break;
            case VIA:
            	MsgVIA msgVIA = new MsgVIA();
            	msgVIA.setVorSubset(tsVor);
            	msgVIA.readMessage(reader);
            	MapVIAToNCTSArrivalNotification mapVIAtoNCTSArrivalNotification = new MapVIAToNCTSArrivalNotification();
            	mapVIAtoNCTSArrivalNotification.setMsgVIA(msgVIA);
            	mapVIAtoNCTSArrivalNotification.setMsgID(msgID);
            	mapVIAtoNCTSArrivalNotification.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapVIAtoNCTSArrivalNotification.getMessage();
            	break;
            case VMR:
            	MsgVMR msgVMR = new MsgVMR();
            	msgVMR.setVorSubset(tsVor);
            	msgVMR.readMessage(reader);
            	MapVMRToNCTSMRNAllocated mapVMRToNCTSMRNAllocated = new MapVMRToNCTSMRNAllocated();
            	mapVMRToNCTSMRNAllocated.setMsgVMR(msgVMR);
            	mapVMRToNCTSMRNAllocated.setMsgID(msgID);
            	mapVMRToNCTSMRNAllocated.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapVMRToNCTSMRNAllocated.getMessage();
            	break;
            case VPH:
            	MsgVPH msgVPH = new MsgVPH();
            	msgVPH.setVorSubset(tsVor);
            	msgVPH.readMessage(reader);
            	MapVPHToNCTSUnloadingPermission mapVPHtoNCTSUnloadingPermission = new MapVPHToNCTSUnloadingPermission();
            	mapVPHtoNCTSUnloadingPermission.setMsgVPH(msgVPH);
            	mapVPHtoNCTSUnloadingPermission.setMsgID(msgID);
            	mapVPHtoNCTSUnloadingPermission.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapVPHtoNCTSUnloadingPermission.getMessage();
            	break;
            case VFO:	// MB 09.09.2010
            	MsgVFO msgVFO	= new MsgVFO();
            	msgVFO.setVorSubset(tsVor);
            	msgVFO.readMessage(reader);
            	MapVFOToNCTSDeclarationRejected mapVFOToNCTSDeclarationRejected	= new MapVFOToNCTSDeclarationRejected();
            	mapVFOToNCTSDeclarationRejected.setMsgVFO(msgVFO);
            	mapVFOToNCTSDeclarationRejected.setMsgID(msgID);
            	mapVFOToNCTSDeclarationRejected.setCommonFieldsDTO(commonFieldsDTO);
            		
            	xml	= mapVFOToNCTSDeclarationRejected.getMessage();
            	break;
            case VUR:	//EE 09.13.2010	
            	MsgVUR msgVUR	= new MsgVUR();
            	msgVUR.setVorSubset(tsVor);
            	msgVUR.readMessage(reader);
            	MapVURToNCTSUnloadingRemarks mapVURToNCTSUnloadingRemarks = new MapVURToNCTSUnloadingRemarks();
            	mapVURToNCTSUnloadingRemarks.setMsgVUR(msgVUR);
            	mapVURToNCTSUnloadingRemarks.setMsgID(msgID);
            	mapVURToNCTSUnloadingRemarks.setCommonFieldsDTO(commonFieldsDTO);
            		
            	xml	= mapVURToNCTSUnloadingRemarks.getMessage();
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
            case STI:    
            	procedure = tsVor.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");	
            	}            	
            	MsgSTI msgSTI = new MsgSTI();
            	msgSTI.setVorSubset(tsVor);
            	msgSTI.readMessage(reader);
                MapSTIToExpNck mapSti = new MapSTIToExpNck(); 
                mapSti.setMsgSTI(msgSTI);
                mapSti.setMsgID(msgID);
                mapSti.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapSti.getMessage();
                break;     
            case VSO:    
            	procedure = tsVor.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");	
            	}            	
            	MsgVSO msgVSO = new MsgVSO();
            	msgVSO.setVorSubset(tsVor);
            	msgVSO.readMessage(reader);
            	MapVSOToNCTSWriteOffNotification mapVso = new MapVSOToNCTSWriteOffNotification(); 
                mapVso.setMsgVSO(msgVSO);
                mapVso.setMsgID(msgID);
                mapVso.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapVso.getMessage();
                break;   
                /*EI20130425 das verursacht weiter NullPointerException      
            case VRL:
            case VAT:	
            	Utils.log("Undefined message" + naTyp);   //EI20110527
            	break;
            	*/
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
