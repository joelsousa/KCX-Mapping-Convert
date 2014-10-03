package com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgACA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 ** Module		: Export/aes<br>
 * Created		: 10.09.2012<br>
 * Description	: Mapping of KIDS format of Cancellation to FSS format ACA.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapExpCanToACA extends KidsMessage {
	
	private MsgExpCan msgExpCan;
	private MsgACA msgACA;
	
	public MapExpCanToACA(XMLEventReader parser, TsVOR vor) throws XMLStreamException {		
		msgExpCan = new MsgExpCan(parser);
		msgACA = new MsgACA("");
		msgACA.setVorSubset(vor);			
	}
	public MapExpCanToACA(XMLEventReader parser, TsVOR vor, TsHead head) throws XMLStreamException {		
		msgExpCan = new MsgExpCan(parser);
		msgACA = new MsgACA("");
		msgACA.setVorSubset(vor);	
		msgACA.setHeadSubset(head);
	}
	
	public String getMessage() {
    	String res = "";
    	
        try {        
        	msgExpCan.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpCan.getReferenceNumber());
                                                          
            msgACA.setAsoSubset(setAso());
            
            //res = msgACA.getFssString();
            /* EI20140206
            if (this.writeHead()) { 				//EI20130213
            	res = msgACA.getFssString("HEAD");
            } else {
            	res = msgACA.getFssString();
            }  
            */
            res = msgACA.getFssString("HEAD");  //EI20140206
            
            Utils.log("(MapExpCanToACA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsASO setAso() {
		TsASO asoSubset = new TsASO();
		
		asoSubset.setBeznr(msgExpCan.getReferenceNumber());	
		asoSubset.setAsodat(msgExpCan.getCancellationTime());
		if (msgExpCan.getCancellationInfo() != null) {
			asoSubset.setArtsto(msgExpCan.getCancellationInfo().getKindOfCancellation());   		
			asoSubset.setText(msgExpCan.getCancellationInfo().getReason());       
		}
		if (msgExpCan.getContact() != null) {
            asoSubset.setSb(msgExpCan.getContact().getIdentity());  
		}
		
		return asoSubset;
	}
}

