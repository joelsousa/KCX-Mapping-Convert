/*
 * Function    : MapNCTSArrivalNotificationToVAN.java
 * Date        : 31.08.2010
 * Author      : Kewill CSF / Elisabeth Iwaniuk
 * Description : Mapping of KIDS format of NCTSArrivalNotification to FSS format VAN

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIB;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffData;

/**
 * Modul		: MapNCTSArrivalNotificationToVIA<br>
 * Erstellt		: 2010.09.03<br>
 * Beschreibung	: Mapping of KIDS format of NCTSArrivalNotification to FSS format VIA.
 * 
 * @author Frederick T
 * @version 6.0.00
 */
public class MapNCTSArrivalNotificationToVIA extends KidsMessage {
	
	private MsgNCTSArrivalNotification msgNCTSArrivalNotification;
	private MsgVIA msgVIA;
	
	public MapNCTSArrivalNotificationToVIA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgNCTSArrivalNotification = new MsgNCTSArrivalNotification(parser);
		msgVIA = new MsgVIA();
		msgVIA.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";
    	
        try {        
        	msgNCTSArrivalNotification.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgNCTSArrivalNotification.getLocalReferenceNumber());
            
            // read MessageID from KidsHeader.
            msgVIA.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            
            //Setting all Ts in MsgVia.
            msgVIA.setViaSubset(setVia());
            msgVIA.setVibSubset(setVib());
            msgVIA.setVisSubset(setVis());
            
            if (msgNCTSArrivalNotification.getWriteOffSumA() != null) {
            	if (msgNCTSArrivalNotification.getWriteOffSumA().getWriteOffDataList() != null) {
		            if (!msgNCTSArrivalNotification.getWriteOffSumA().getWriteOffDataList().isEmpty()) {
			            for (WriteOffData writeOffData : 
			            		msgNCTSArrivalNotification.getWriteOffSumA().getWriteOffDataList()) {
			            	msgVIA.addVipSubsetList(setVip(writeOffData));
			            }
		            }
            	}
            }
            
            res = msgVIA.getFssString();
           
            Utils.log("(MapNCTSArrivalNotificationToVIA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsVIA setVia() {
		TsVIA viaSubset = new TsVIA();
		
		viaSubset.setMrn(msgNCTSArrivalNotification.getmRN());
		viaSubset.setBeznr(msgNCTSArrivalNotification.getLocalReferenceNumber());
		viaSubset.setWgdat(msgNCTSArrivalNotification.getArrivalNotificationDate());
		viaSubset.setWgdatFormat(msgNCTSArrivalNotification.getArrivalNotificationDateFormat());
		
		if (msgNCTSArrivalNotification.getEnRouteEvent() != null) {
			if (msgNCTSArrivalNotification.getEnRouteEvent().getIncident() != null) {
				viaSubset.setKzerg(msgNCTSArrivalNotification.getEnRouteEvent().
						getIncident().getIncidentFlag());
			}
		}
//		viaSubset.setQuelkz(quelkz);	No assigned field in Overview document.
//		viaSubset.setKdnrze(kdnrze);
//		viaSubset.setTinze(tinze);
		return viaSubset;
	}
	
	private TsVIB setVib() {
		TsVIB vibSubset = new TsVIB();
		
		vibSubset.setBeznr(msgNCTSArrivalNotification.getLocalReferenceNumber());
		vibSubset.setBewnr(msgNCTSArrivalNotification.getPermitNumber());
		
		vibSubset.setUbgort(msgNCTSArrivalNotification.getArrivalNotificationPlace());
//		vibSubset.setBedst(bedst); No assigned field in Overview document.
		return vibSubset;
	}
	
	private TsVIS setVis() {
		TsVIS visSubset = new TsVIS();
		
		visSubset.setBeznr(msgNCTSArrivalNotification.getLocalReferenceNumber());
		
		if (msgNCTSArrivalNotification.getWriteOffSumA() != null) {
			visSubset.setSuabez(msgNCTSArrivalNotification.getWriteOffSumA().getReference());
			visSubset.setFltnum(msgNCTSArrivalNotification.getWriteOffSumA().getFlightNumber());
		}

		return visSubset;
	}
	
	private TsVIP setVip(WriteOffData writeOffData) {
		TsVIP vipSubset = new TsVIP();
		
		vipSubset.setBeznr(msgNCTSArrivalNotification.getLocalReferenceNumber());
		vipSubset.setAwbzzz(writeOffData.getaWBNumber());
		vipSubset.setSuapos(writeOffData.getItemNumber());

		return vipSubset;
	}
}

