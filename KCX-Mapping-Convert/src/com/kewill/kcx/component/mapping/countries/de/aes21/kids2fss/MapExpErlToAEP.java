package com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgAEP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAME;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export / AES21 <br>
 * Created		: 27.09.2012<br>
 * Description	: Mapping of KIDS-Format ManualTermination into FSS-Format of AEP Version 70.
 * 
 * @author iwaniuk
 * @version 2.1.00
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
	public MapExpErlToAEP(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		msgExpErl = new MsgExpErl(parser);
		msgAEP = new MsgAEP("");
		msgAEP.setVorSubset(tsvor);
		msgAEP.setHeadSubset(head);
	}
	
	public String getMessage() {
		String res = new String();
    	
        try {        
        	msgExpErl.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpErl.getReferenceNumber());
                                                          
            msgAEP.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
            msgAEP.setAmeSubset(setAme());
            
            //res = msgAEP.getFssString();
            /* EI20140206
            if (this.writeHead()) { 				//EI20130213
            	res = msgAEP.getFssString("HEAD");
            } else {
            	res = msgAEP.getFssString();
            }
            */
            res = msgAEP.getFssString("HEAD");
           
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
