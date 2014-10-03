/*
 * Function    : MapNCTSMRNAllocatedToVAN.java
 * Date        : 31.08.2010
 * Author      : Kewill CSF / Elisabeth Iwaniuk
 * Description : Mapping of KIDS format of MRNAllocated to FSS format VMR

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVMR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVMP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVMR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVMV;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapNCTSMRNAllocatedToVMR<br>
 * Erstellt		: 31.08.2010<br>
 * Beschreibung	: Mapping of KIDS format of MRNAllocated to FSS format VMR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapNCTSMRNAllocatedToVMR extends KidsMessage {
	
	private MsgNCTSMRNAllocated msgNCTSMRNAllocated;
	private MsgVMR msgVMR;
	
	public MapNCTSMRNAllocatedToVMR(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgNCTSMRNAllocated = new MsgNCTSMRNAllocated(parser);
		msgVMR = new MsgVMR();
		msgVMR.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";
    	
        try {        
        	msgNCTSMRNAllocated.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgNCTSMRNAllocated.getReferenceNumber());
                                                          
            msgVMR.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            
            msgVMR.setVmrSubset(setVmr());
            msgVMR.setVmpSubset(setVmp());
            msgVMR.setVmvSubset(setVmv());
            
            res = msgVMR.getFssString();
           
            Utils.log("(MapNCTSMRNAllocatedToVMR getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsVMR setVmr() {
		TsVMR vmrSubset = new TsVMR();
	
		vmrSubset.setBeznr(msgNCTSMRNAllocated.getReferenceNumber());
		vmrSubset.setArbnr(msgNCTSMRNAllocated.getUcrNumber());
		vmrSubset.setMrn(msgNCTSMRNAllocated.getUcrNumber());
	//	vmrSubset.setlbez(); no assigned value in overview
	//	vmrSubset.setAvbez(avbez); no assigned value in overview
		return vmrSubset;
	}
	
	private TsVMP setVmp() {
		TsVMP vmpSubset = new TsVMP();

		vmpSubset.setBeznr(msgNCTSMRNAllocated.getReferenceNumber());
		vmpSubset.setMrn(msgNCTSMRNAllocated.getUcrNumber());
		
		if (msgNCTSMRNAllocated.getPdfInformation() != null) {
			vmpSubset.setPdfnam(msgNCTSMRNAllocated.getPdfInformation().getName());
			vmpSubset.setPdfpfd(msgNCTSMRNAllocated.getPdfInformation().getDirectory());
			vmpSubset.setSubdir(msgNCTSMRNAllocated.getPdfInformation().getNewDirectory());
		}
		
		return vmpSubset;
	}
	
	private TsVMV setVmv() {
		TsVMV vmvSubset = new TsVMV();

		vmvSubset.setBeznr(msgNCTSMRNAllocated.getReferenceNumber());
		vmvSubset.setMrn(msgNCTSMRNAllocated.getUcrNumber());
	//	vmvSubset.setTyd(tyd); no assigned value in overview
		return vmvSubset;
	}
	
}

