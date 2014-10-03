/*
 * Function    : MapExpCanToACA.java
 * Date        : 15.10.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Mapping of KIDS format of Cancellation to FSS format ACA 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009
 * Description : checked for V60 - no changes
 *
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk/identity
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgACA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpCanToACA<br>
 * Erstellt		: 15.10.2008<br>
 * Beschreibung	: Mapping of KIDS format of Cancellation to FSS format ACA.
 * 
 * @author kron
 * @version 1.0.00
 */
public class MapExpCanToACA extends KidsMessage {
	
	private MsgExpCan msgExpCan;
	private MsgACA msgACA;
	
	public MapExpCanToACA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgExpCan = new MsgExpCan(parser);
		msgACA = new MsgACA();
		msgACA.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";
    	
        try {        
        	msgExpCan.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpCan.getReferenceNumber());
                                                          
            msgACA.setAsoSubset(setAso());
            res = msgACA.getFssString();
           
            Utils.log("(MapExpCanToACA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsASO setAso() {
		TsASO asoSubset = new TsASO();
		
		asoSubset.setBeznr(msgExpCan.getReferenceNumber());		
		asoSubset.setArtsto(msgExpCan.getTypeOfAnnulment());   
		asoSubset.setAsodat(msgExpCan.getDateOfAnnulment());
		asoSubset.setText(msgExpCan.getReasonOfAnnulment());
		//EI20090609: asoSubset.setSb(msgExpCan.getIdentity());
		if (msgExpCan.getContact() != null) {
            asoSubset.setSb(msgExpCan.getContact().getIdentity());  //EI20090609
		}
		
		return asoSubset;
	}
}

