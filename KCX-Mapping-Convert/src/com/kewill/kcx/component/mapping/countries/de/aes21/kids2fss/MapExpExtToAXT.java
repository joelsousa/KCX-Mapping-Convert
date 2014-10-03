/*
 * Function    : MapExpExtToAXT.java
 * Date        : 02.10.2008
 * Author      : Kewill CSF / houdek
 * Description : Mapping of KIDS format of ConfirmInvestigation to FSS Format of AXT
 *               ConfirmInvestigation
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 22.04.2009
 * Description : replaced MsgKids with MsgExpExt
 */

package com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgAXT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAXT;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Modul		: MapExpExtToAXT<br>
 * Erstellt		: 02.10.2008<br>
 * Beschreibung	: Mapping of KIDS format of ConfirmInvestigation to FSS Format of AXT
 *                ConfirmInvestigation.
 * 
 * @author houdek
 * @version 6.0.00
 * 
 * * Changes: MsgExpExt will be imported from ...aes21.msg
 */
public class MapExpExtToAXT extends KidsMessage {
	
	private MsgAXT msgAXT;
	private MsgExpExt message;

	
	public MapExpExtToAXT(XMLEventReader parser) throws XMLStreamException {
    	msgAXT = new MsgAXT("");
	}
	
	public MapExpExtToAXT(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		message = new MsgExpExt(parser);
    	msgAXT = new MsgAXT("");
		msgAXT.setVorSubset(tsvor);
	}
	public MapExpExtToAXT(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		message = new MsgExpExt(parser);
    	msgAXT = new MsgAXT("");
		msgAXT.setVorSubset(tsvor);
		msgAXT.setHeadSubset(head);
	}
	
	public String getMessage() {
	
		String res = "";
    	
        try {          
        	message.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
                                                          
            // read MessageID from KidsHeader 
            msgAXT.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
            msgAXT.setAxtSubset(mapKidsToAxt());
            msgAXT.setDeclarant(message.getDeclarant(), message.getReferenceNumber(), "0");  // Typ 3
            msgAXT.setAgent(message.getAgent(), message.getReferenceNumber(), "0");		  // Typ 4
      
            //res = msgAXT.getFssString();
            /* EI20140206
            if (this.writeHead()) { 				//EI20130213
            	res = msgAXT.getFssString("HEAD");
            } else {
            	res = msgAXT.getFssString();
            }  
           */
            res = msgAXT.getFssString("HEAD"); //EI20130213
            
            Utils.log("(MapExpExtToAXT getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res.toString();
	}
	
	private TsAXT mapKidsToAxt() {
		TsAXT axtSubset = new TsAXT();
		
		axtSubset.setBeznr(message.getReferenceNumber());		
		axtSubset.setAsgart(message.getExitType());  
		axtSubset.setAsgdat(message.getDateOfExit());  
		axtSubset.setVetdat(message.getIntendentExitDate()); 		
		axtSubset.setTetdst(message.getRealOfficeOfExit()); 
		axtSubset.setVerm(message.getAnnotation());		
		if (message.getContact() != null) {
			axtSubset.setSb(message.getContact().getIdentity());	//EI20130808			
		}
		
		return axtSubset;
	}
}

