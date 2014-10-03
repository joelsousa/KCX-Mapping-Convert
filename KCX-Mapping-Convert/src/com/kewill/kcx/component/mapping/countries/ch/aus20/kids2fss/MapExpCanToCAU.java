package com.kewill.kcx.component.mapping.countries.ch.aus20.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCAU;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module        :   EDEC Export 20 
 * Created       :   07.11.2012
 * Description   :   Mapping of KIDS-ExpCan to FSS-CAU.
 *  
 * @author         iwaniuk
 * @version        2.0.00
 */

public class MapExpCanToCAU extends KidsMessage {
	
	private MsgCAU    msgCAU;
	private MsgExpCan message;
	
	public MapExpCanToCAU(XMLEventReader parser, TsVOR vor) throws XMLStreamException {
		message = new MsgExpCan(parser);
		msgCAU = new MsgCAU();
		msgCAU.setVorSubset(vor);			
	}
	
	public MapExpCanToCAU(XMLEventReader parser, TsVOR vor, TsHead head) throws XMLStreamException {
		message = new MsgExpCan(parser);
		msgCAU = new MsgCAU();
		msgCAU.setVorSubset(vor);	
		msgCAU.setHeadSubset(head);
	}
	
	public String getMessage() {

    	String res = "";
        try {
            message.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            msgCAU.getCauSubset().setDklart(message.getTypeOfDocument());
            msgCAU.getCauSubset().setDknrzo(message.getDeclarationNumberCustoms());
            msgCAU.getCauSubset().setAnugrd(message.getReasonOfAnnulment());
            
            if (this.writeHead()) {                        //EI20130213
            	res = msgCAU.getFssString("HEAD");
            } else {
            	res = msgCAU.getFssString();
            }
            
            Utils.log("(MapExpCanToCAU getMessage) Msg = " + res);
		
	    } catch (FssException e) {
	        
	        e.printStackTrace();
	    }
	
	    
	    return res;
	}

}
