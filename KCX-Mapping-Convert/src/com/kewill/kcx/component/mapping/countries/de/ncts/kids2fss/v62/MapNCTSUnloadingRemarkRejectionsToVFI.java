/*
 * Function    : MapNCTSUnloadingRemarkRejectionToVFI.java
 * Date        : 31.08.2010
 * Author      : Kewill CSF / Elisabeth Iwaniuk
 * Description : Mapping of KIDS format of NCTSUnloadingRemarkRejection to FSS format VFI

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVFI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFU;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapNCTSUnloadingRemarkRejectionToVFI<br>
 * Erstellt		: 03.09.2010<br>
 * Beschreibung	: Mapping of KIDS format of NCTSUnloadingRemarkRejection to FSS format VFI.
 * 
 * @author Edwin Bautista
 * @version 6.2.00
 */
public class MapNCTSUnloadingRemarkRejectionsToVFI extends KidsMessage {
	
	private MsgNCTSUnloadingRemarksRejection msgNCTSUnloadingRemarksRejection;
	private MsgVFI msgVFI;
	
	public MapNCTSUnloadingRemarkRejectionsToVFI(XMLEventReader parser, TsVOR tsvor) 
		throws XMLStreamException {		
		
		msgNCTSUnloadingRemarksRejection = new MsgNCTSUnloadingRemarksRejection(parser);
		msgVFI = new MsgVFI();
		msgVFI.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";
    	
        try {        
        	msgNCTSUnloadingRemarksRejection.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgNCTSUnloadingRemarksRejection.getReferenceNumber());
            
            msgVFI.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            msgVFI.setVfiSubset(getVFI());
            
            List<FunctionalErrorNcts> errorList = msgNCTSUnloadingRemarksRejection.getErrorList();
            
            if (errorList != null) {
            	for (FunctionalErrorNcts error : errorList) {
            		msgVFI.addVfuList(getVFU(error));
            	}
            }
            
            res = msgVFI.getFssString();           
            Utils.log("(MapNCTSUnloadingRemarkRejectionToVFI getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsVFI getVFI() {
		TsVFI vfiSubset = new TsVFI();
		
		vfiSubset.setBeznr(msgNCTSUnloadingRemarksRejection.getReferenceNumber());		
		vfiSubset.setRegnr(msgNCTSUnloadingRemarksRejection.getUCRNumber());
		vfiSubset.setArbnr(msgNCTSUnloadingRemarksRejection.getUCRNumber());
		vfiSubset.setRegdat(msgNCTSUnloadingRemarksRejection.getArrivalRejectionDate());
		
		return vfiSubset;
	}
	
	private TsVFU getVFU(FunctionalErrorNcts error) {
		if (error == null) {
			return null;
		}
		
		TsVFU vfuSubset = new TsVFU();

		vfuSubset.setPosnr(error.getPointer());
		vfuSubset.setErrcd(error.getCode());
		vfuSubset.setErrtxt(error.getText());
		
		return vfuSubset;
	}
}

