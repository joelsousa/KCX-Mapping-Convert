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

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAXT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAXT;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
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
 */
public class MapExpExtToAXT extends KidsMessage {
	
	private MsgAXT msgAXT;
	private MsgExpExt msgExpExt;

	
	public MapExpExtToAXT(XMLEventReader parser) throws XMLStreamException {
    	msgAXT = new MsgAXT("");
	}
	
	public MapExpExtToAXT(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpExt = new MsgExpExt(parser);
    	msgAXT = new MsgAXT("");
		msgAXT.setVorSubset(tsvor);
	}
	public String getMessage() {
	
		StringBuffer res = new StringBuffer();
    	
        try {          
        	msgExpExt.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpExt.getReferenceNumber());
                                                          
            // read MessageID from KidsHeader 
            msgAXT.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
            msgAXT.setAxtSubset(setAxt());
      
            res = msgAXT.writeAXT();
           
            Utils.log("(MapExpExtToAXT getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res.toString();
	}
	
	private TsAXT setAxt() {
		TsAXT axtSubset = new TsAXT();
		
		axtSubset.setBeznr(msgExpExt.getReferenceNumber());		
		axtSubset.setAsgart(msgExpExt.getExitType());  
		axtSubset.setAsgdat(msgExpExt.getDateOfExit());  
		axtSubset.setVetdat(msgExpExt.getIntendentExitDate()); 		
		axtSubset.setTetdst(msgExpExt.getRealOfficeOfExit()); 
		axtSubset.setVerm(msgExpExt.getAnnotation());		
		
		return axtSubset;
	}
}

