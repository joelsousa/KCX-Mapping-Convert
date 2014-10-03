package com.kewill.kcx.component.mapping.countries.de.ncts20;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.countries.de.ncts.EFssNCTSMessages;
import com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids.MapVFIToNCTSArrivalRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids.MapVFIToNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids.MapVFOToNCTSDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids.MapVMRToNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids.MapVPHToNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids.MapVSOToNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVFI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVFO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVMR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVSO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFI;

/** 
 * Module 		: NCTS<br>
 * Created 		: 13.02.2013<br>
 * Description 	: transformer called by FssToKids to convert ZABIS-FSS format. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class Fss70ToKidsNCTS  {
	
	private BufferedReader  reader      	= null;
    private String          auditId     	= null;
    private TsVOR           tsVor       	= null;	
    private TsHead          tsHead       	= null;	
	private String 			msgID			= null;
	private String			procedure		= null;
	private CommonFieldsDTO commonFieldsDTO = null;

	//public  Fss70ToKidsNCTS(BufferedReader in, String auditId, TsVOR tsVor, 
	public  Fss70ToKidsNCTS(BufferedReader in, String auditId, TsVOR tsVor, TsHead tsHead,
	                                       String msgID, CommonFieldsDTO commonFieldsDTO)  {
		setReader(in);
		this.auditId = auditId;
		this.tsVor = tsVor;
		this.tsHead = tsHead;
		this.msgID = msgID;
		this.commonFieldsDTO = commonFieldsDTO;
	}
	
	public String readFss() throws FssException {
        String xml   = null;
        String naTyp = tsHead.getMsgName();
        tsVor.setCountry("DE");
        tsHead.setCountry("DE");
        
        
        switch (EFssNCTSMessages.valueOf(naTyp)) {        	
        		
        	case VMR:                                       //MRNAllocated
            	MsgVMR msgVMR = new MsgVMR();
            	msgVMR.setVorSubset(tsVor);
            	msgVMR.setHeadSubset(tsHead);
            	msgVMR.readMessage(reader);
            	MapVMRToNCTSMRNAllocated mapVMRToNCTSMRNAllocated = new MapVMRToNCTSMRNAllocated();
            	mapVMRToNCTSMRNAllocated.setMsgVMR(msgVMR);
            	mapVMRToNCTSMRNAllocated.setMsgID(msgID);
            	mapVMRToNCTSMRNAllocated.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapVMRToNCTSMRNAllocated.getMessage();
            	break;
            	
        	case VSO:                                       //WriteOffNotification
            	procedure = tsHead.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            		tsHead.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");
            		tsHead.setCountry("DE");	
            	}            	
            	MsgVSO msgVSO = new MsgVSO();
            	msgVSO.setVorSubset(tsVor);
            	msgVSO.setHeadSubset(tsHead);
            	msgVSO.readMessage(reader);
            	MapVSOToNCTSWriteOffNotification mapVso = new MapVSOToNCTSWriteOffNotification(); 
                mapVso.setMsgVSO(msgVSO);
                mapVso.setMsgID(msgID);
                mapVso.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapVso.getMessage();
                break;  
                
        	case VPH:                                        //UnloadingPermission
            	MsgVPH msgVPH = new MsgVPH();
            	msgVPH.setVorSubset(tsVor);
            	msgVPH.setHeadSubset(tsHead);
            	msgVPH.readMessage(reader);            	
            	MapVPHToNCTSUnloadingPermission mapVPHtoNCTSUnloadingPermission = new MapVPHToNCTSUnloadingPermission(commonFieldsDTO);            	
            	mapVPHtoNCTSUnloadingPermission.setMsgVPH(msgVPH);
            	mapVPHtoNCTSUnloadingPermission.setMsgID(msgID);
            	//EI20130523: mapVPHtoNCTSUnloadingPermission.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapVPHtoNCTSUnloadingPermission.getMessage();
            	break;
            	
        	case VFO:	                                     //DeclarationRejected
        		MsgVFO msgVFO	= new MsgVFO();
        		msgVFO.setVorSubset(tsVor);
        		msgVFO.setHeadSubset(tsHead);
        		msgVFO.readMessage(reader);
        		MapVFOToNCTSDeclarationRejected mapVFOToNCTSDeclarationRejected	= new MapVFOToNCTSDeclarationRejected();
        		mapVFOToNCTSDeclarationRejected.setMsgVFO(msgVFO);
        		mapVFOToNCTSDeclarationRejected.setMsgID(msgID);
        		mapVFOToNCTSDeclarationRejected.setCommonFieldsDTO(commonFieldsDTO);        		
        		xml	= mapVFOToNCTSDeclarationRejected.getMessage();
        		break;
        		
            case VFI:                                 //ArrivalRejection oder UnloadingRemarksRejection       	                	
               	MsgVFI msgVFI = new MsgVFI();
               	msgVFI.setVorSubset(tsVor);
               	msgVFI.setHeadSubset(tsHead);
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
               			(vfiSubset.getNatyp().startsWith("DEEAN") ||    //EI20140205            			 
               		     vfiSubset.getNatyp().startsWith("ANKAN"))) {   //EI20140205   
	               	 MapVFIToNCTSArrivalRejection mapVFIToNCTSArrivalRejection = new MapVFIToNCTSArrivalRejection();
	                 mapVFIToNCTSArrivalRejection.setMsgVFI(msgVFI);
	                 mapVFIToNCTSArrivalRejection.setMsgID(msgID);
	                 mapVFIToNCTSArrivalRejection.setCommonFieldsDTO(commonFieldsDTO);
	                 xml = mapVFIToNCTSArrivalRejection.getMessage();
               	} else if (vfiSubset != null && vfiSubset.getNatyp() != null &&
               			(vfiSubset.getNatyp().startsWith("DEEUR") ||   //EI20140205                 			 
               			 vfiSubset.getNatyp().startsWith("UNLRE"))) {  //EI20140205    
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
            
            case ERR:       
            	procedure = tsHead.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            		tsHead.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");
            		tsHead.setCountry("DE");	
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
            	procedure = tsHead.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            		tsHead.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");
            		tsHead.setCountry("DE");	
            	}
            	MapCONToLocalAppResult mapCONToLocalAppResult = new MapCONToLocalAppResult();
                mapCONToLocalAppResult.setMsgID(msgID);
                mapCONToLocalAppResult.setTsVOR(tsVor);
                mapCONToLocalAppResult.setTsHead(tsHead);
                mapCONToLocalAppResult.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCONToLocalAppResult.getMessage();
                break;   
                
            case STI:    
            	procedure = tsHead.getModul().trim();
            	if (procedure.equals("VCH")) {
            		tsVor.setCountry("CH");
            		tsHead.setCountry("CH");
            	} else {
            		tsVor.setCountry("DE");
            		tsHead.setCountry("DE");	
            	}            	
            	MsgSTI msgSTI = new MsgSTI();
            	msgSTI.setVorSubset(tsVor);
            	msgSTI.setHeadSubset(tsHead);
            	msgSTI.readMessage(reader);
                MapSTIToExpNck mapSti = new MapSTIToExpNck(); 
                mapSti.setMsgSTI(msgSTI);
                mapSti.setMsgID(msgID);
                mapSti.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapSti.getMessage();
                break;     
              /*EI20130425 das verursacht weiter NullPointerException            
            case VRL:
            case VAT:	
            case VIA:	
            case VUR:
            	Utils.log("Undefined message" + naTyp);   //EI20110527            	
            	break;
            	*/
            default:
                throw new FssException("NCTS FssToKids - Message not defined: " + naTyp);
        }
        return xml;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}


}
