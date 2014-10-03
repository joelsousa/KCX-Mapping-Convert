package com.kewill.kcx.component.mapping.countries.ch.aus.kids2fss;

/*
 * Function    : MapExpCanToCAU.java
 * Date        : 17.09.2008
 * Author      : Kewill CSF / SH
 * Description : Mapping of KIDS format of ExpCan to CAU

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCAU;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpCanToCAU<br>
 * Erstellt		: 17.09.2008<br>
 * Beschreibung	: Mapping of KIDS format of ExpCan to CAU.
 * 
 * @author heise
 * @version 1.0.00
 */
public class MapExpCanToCAU extends KidsMessage {
	
	private MsgCAU msgCAU;
	private MsgExpCan msgExpCan;
	
	public MapExpCanToCAU(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpCan = new MsgExpCan(parser);
		msgCAU = new MsgCAU();
		msgCAU.setVorSubset(tsvor);
		
	}

	public String getMessage() {

    	String res = "";
        try {
            msgExpCan.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpCan.getReferenceNumber());
            
            msgCAU.getCauSubset().setDklart(msgExpCan.getTypeOfDocument());
            msgCAU.getCauSubset().setDknrzo(msgExpCan.getDeclarationNumberCustoms());
            msgCAU.getCauSubset().setAnugrd(msgExpCan.getReasonOfAnnulment());
            res = msgCAU.getFssString();
            
            Utils.log("(MapExpCanToCAU getMessage) Msg = " + res);
		
	    } catch (FssException e) {
	        
	        e.printStackTrace();
	    }
	
	    
	    return res;
	}

}
