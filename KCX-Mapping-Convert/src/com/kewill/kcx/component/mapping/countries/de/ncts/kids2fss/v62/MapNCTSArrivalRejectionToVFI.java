/*
 * Function    : MapNCTSArrivalRejectionToVFI.java
 * Date        : 31.08.2010
 * Author      : Kewill CSF / Elisabeth Iwaniuk
 * Description : Mapping of KIDS format of NCTSArrivalRejection to FSS format VFI.

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

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalRejection;
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
 * Module		: MapNCTSArrivalRejectionToVFI<br>
 * Created		: 02.09.2010<br>
 * Description	: Mapping of KIDS format of NCTSArrivalRejection to FSS format VFI.
 * 
 * @author Edwin Bautista
 * @version 6.2.00
 */
public class MapNCTSArrivalRejectionToVFI extends KidsMessage {
	
	private MsgNCTSArrivalRejection msgNCTSArrivalRejection;
	private MsgVFI msgVFI;
	
	public MapNCTSArrivalRejectionToVFI(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgNCTSArrivalRejection = new MsgNCTSArrivalRejection(parser);
		msgVFI = new MsgVFI();
		msgVFI.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";
    	
        try {        
        	msgNCTSArrivalRejection.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgNCTSArrivalRejection.getReferenceNumber());
            
            msgVFI.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            msgVFI.setVfiSubset(getVFI());
            
            List<FunctionalErrorNcts> errorList = msgNCTSArrivalRejection.getErrorList();
            
            if (errorList != null) {
            	for (FunctionalErrorNcts error : errorList) {
            		msgVFI.addVfuList(getVFU(error));
            	}
            }
            
            res = msgVFI.getFssString();
           
            Utils.log("(MapNCTSArrivalRejectionToVFI getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsVFI getVFI() {
		TsVFI vfiSubset = new TsVFI();
		
		vfiSubset.setBeznr(msgNCTSArrivalRejection.getReferenceNumber());		
		vfiSubset.setRegnr(msgNCTSArrivalRejection.getUCRNumber());
		vfiSubset.setArbnr(msgNCTSArrivalRejection.getUCRNumber());
		vfiSubset.setRegdat(msgNCTSArrivalRejection.getArrivalRejectionDate());
		
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

