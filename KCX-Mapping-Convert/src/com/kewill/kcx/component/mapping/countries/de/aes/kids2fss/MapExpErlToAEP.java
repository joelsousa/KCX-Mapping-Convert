/*
 * Function    : MapExpErlToAEP.java
 * Date        : 02.10.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of KIDS-Format ManualTermination into FSS-Format of AEP

 * Changes 
 * -----------
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53.MsgAEP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAME;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpErlToAEP<br>
 * Erstellt		: 02.10.2008<br>
 * Beschreibung	: Mapping of KIDS-Format ManualTermination into FSS-Format of AEP.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapExpErlToAEP extends KidsMessage {

	private MsgExpErl msgExpErl;
	private MsgAEP msgAEP;
	
	public MapExpErlToAEP(XMLEventReader parser) throws XMLStreamException {
		msgExpErl = new MsgExpErl(parser);
		msgAEP = new MsgAEP("");
	}
	public MapExpErlToAEP(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpErl = new MsgExpErl(parser);
		msgAEP = new MsgAEP("");
		msgAEP.setVorSubset(tsvor);
	}

	public String getMessage() {
		StringBuffer res = new StringBuffer();
    	
        try {        
        	//msgADI = new MsgADI("data/fss/out/MapExpIndToADI.dat");
        	msgExpErl.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpErl.getReferenceNumber());
                                                          
            // read MessageID from KidsHeader 
            msgAEP.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
            msgAEP.setAmeSubset(setAme());
            
            res = msgAEP.writeAEP();
           
            Utils.log("(MapExpErlToAEP getMessage) Msg = " + res);
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res.toString();
	}
	
	private TsAME setAme() {
		TsAME ameSubset = new TsAME();
		
		ameSubset.setBeznr(msgExpErl.getReferenceNumber());		
		ameSubset.setErldat(msgExpErl.getTerminationTime());  
		ameSubset.setText(msgExpErl.getAnnotation());  
		if (msgExpErl.getSeal() != null) {
			ameSubset.setKztyd(msgExpErl.getSeal().getUseOfTydenseals());
		}
		//EI20090609: ameSubset.setSb(msgExpErl.getClerk()); 	
		if (msgExpErl.getContact() != null) {
			ameSubset.setSb(msgExpErl.getContact().getIdentity()); //EI20090609
		}
		
		return ameSubset;
	}
}
