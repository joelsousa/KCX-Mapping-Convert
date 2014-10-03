package com.kewill.kcx.component.mapping.countries.de.suma70;

import java.io.BufferedReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapCONToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60.MapSTIToExpNck;
import com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids.MapERRToLocalAppResult;
import com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids.MapEVKtoGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids.MapSCKtoProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids.MapSEKtoNotificationOfCompletion;
import com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids.MapSSTtoControlDecision;
import com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids.MapSWVtoGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgEVK;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSCK;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSEK;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSST;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSWV;

/**
 * Module :     Fss62ToKidsSuma<br>
 * Created:     19.06.2013<br>
 * Description: transformer called by FssToKids to convert ZABIS-FSS format version 70 to KIDS. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class Fss70ToKidsSuma  {
	
	private BufferedReader  reader      	= null;
    private String          auditId     	= null;
    private TsVOR           tsVor       	= null;
    private TsHead          tsHead      	= null;
	private String 			msgID			= null;
	//private String			procedure		= null;
	private CommonFieldsDTO commonFieldsDTO = null;

	public  Fss70ToKidsSuma(BufferedReader in, String auditId, TsVOR tsVor, TsHead tsHead,
									String msgID, CommonFieldsDTO commonFieldsDTO)  {
		this.reader = in;
		this.auditId = auditId;
		this.tsVor = tsVor;				
		this.tsHead = tsHead;		
		this.msgID = msgID;
		
		this.commonFieldsDTO = commonFieldsDTO;
	}
	
	public String readFss() throws FssException {
        String xml   = null;
        String naTyp = tsVor.getNatyp();
        tsVor.setCountry("DE");
        //procedure = tsVor.getModul().trim();
        
        switch (EFssSumaMessages.valueOf(naTyp)) {
            case EVK:                	                	
               	MsgEVK msgEVK = new MsgEVK();
               	msgEVK.setVorSubset(tsVor);
                msgEVK.setHeadSubset(tsHead);
               	msgEVK.readMessage(reader);
               	MapEVKtoGoodsReleasedExternal mapEVKtoGoodsReleasedExternal = new MapEVKtoGoodsReleasedExternal();
               	mapEVKtoGoodsReleasedExternal.setMsgEVK(msgEVK);
               	//mapEVKtoGoodsReleasedExternal.setMsgID(msgID);
               	mapEVKtoGoodsReleasedExternal.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapEVKtoGoodsReleasedExternal.getMessage();            	        
                break;
                
            case SEK:
               	MsgSEK msgSEK = new MsgSEK();
               	msgSEK.setVorSubset(tsVor);
                msgSEK.setHeadSubset(tsHead);
               	msgSEK.readMessage(reader);
               	MapSEKtoNotificationOfCompletion mapSEKtoNotificationOfCompletion  = new MapSEKtoNotificationOfCompletion();
               	mapSEKtoNotificationOfCompletion.setMsgSEK(msgSEK);
               	//mapSEKtoNotificationOfCompletion.setMsgID(msgID);
               	mapSEKtoNotificationOfCompletion.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapSEKtoNotificationOfCompletion.getMessage();            	        
            	break;

            case SWV:
            	MsgSWV msgSWV = new MsgSWV();
            	msgSWV.setVorSubset(tsVor);
            	msgSWV.setHeadSubset(tsHead);
            	msgSWV.readMessage(reader);
            	MapSWVtoGoodsReleasedInternal mapSWVtoGoodsReleasedInternal = new MapSWVtoGoodsReleasedInternal();
            	mapSWVtoGoodsReleasedInternal.setMsgSWV(msgSWV);
            	//mapSWVtoGoodsReleasedInternal.setMsgID(msgID); 
            	mapSWVtoGoodsReleasedInternal.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapSWVtoGoodsReleasedInternal.getMessage();
            	break;            
                 
            case SST:
            	MsgSST msgSST = new MsgSST();
            	msgSST.setVorSubset(tsVor);
                msgSST.setHeadSubset(tsHead);
                msgSST.readMessage(reader);
            	MapSSTtoControlDecision mapSSTtoControlDecision = new MapSSTtoControlDecision();
            	mapSSTtoControlDecision.setMsgSST(msgSST);
            	//mapSSTtoControlDecision.setMsgID(msgID);
            	mapSSTtoControlDecision.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapSSTtoControlDecision.getMessage();

            	break;
            	
            case SCK:                               //EI20130211
            	MsgSCK msgSck = new MsgSCK();
            	msgSck.setVorSubset(tsVor);
                msgSck.setHeadSubset(tsHead);
            	msgSck.readMessage(reader);
            	MapSCKtoProcessingResults mapSCKtoProcessingResult = new MapSCKtoProcessingResults();
            	mapSCKtoProcessingResult.setMsgSCK(msgSck);
            	//mapSCKtoProcessingResult.setMsgID(msgID);
            	mapSCKtoProcessingResult.setCommonFieldsDTO(commonFieldsDTO);
            	xml = mapSCKtoProcessingResult.getMessage();
            	break;
            	
            case CON:       //hier ist Zabis-Confirmation gemeint == Kids: nur Header, ohne Body
            	//procedure = tsVor.getModul().trim();
           		MapCONToLocalAppResult mapCONToLocalAppResult = new MapCONToLocalAppResult();
                //mapCONToLocalAppResult.setMsgID(msgID);
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
                //mapSti.setMsgID(msgID);
                mapSti.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapSti.getMessage();
                break;  
                
            case ERR:                   	
            case SUK: case SBK: case SAK:	
                MsgERR msgERR = new MsgERR();
                msgERR.setVorSubset(tsVor);
                //msgERR.setHeadSubset(tsHead);
                msgERR.readMessage(reader);
                MapERRToLocalAppResult mapERRToLocalAppResult = new MapERRToLocalAppResult();
                mapERRToLocalAppResult.setMsgERR(msgERR);
                //mapERRToLocalAppResult.setMsgID(msgID);
                mapERRToLocalAppResult.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapERRToLocalAppResult.getMessage();
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

	
}
