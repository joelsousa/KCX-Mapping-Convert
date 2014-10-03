package com.kewill.kcx.component.mapping.countries.de.ncts20.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVIR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIB;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: NCTS<br>
 * Created		: 05.02.2013<br>
 * Description	: Mapping of KIDS-NCTSArrivalNotification to FSS-VIA.
 * 				: V41/V70 new Contact == Ansprechpartner
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class MapNCTSArrivalNotificationToVIA extends KidsMessage {
	
	private MsgNCTSArrivalNotification msgNCTSArrivalNotification;
	private MsgVIA msgVIA;
	
	public MapNCTSArrivalNotificationToVIA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgNCTSArrivalNotification = new MsgNCTSArrivalNotification(parser);
		msgVIA = new MsgVIA();
		msgVIA.setVorSubset(tsvor);
	}
	public MapNCTSArrivalNotificationToVIA(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {		
		msgNCTSArrivalNotification = new MsgNCTSArrivalNotification(parser);
		msgVIA = new MsgVIA();
		msgVIA.setVorSubset(tsvor);
		msgVIA.setHeadSubset(head);
	}

	public String getMessage() {
    	String res = "";
    	
        try {        
        	msgNCTSArrivalNotification.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgNCTSArrivalNotification.getReferenceNumber());
            
            // read MessageID from KidsHeader.
            msgVIA.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            
            //Setting all Ts in MsgVia.
            msgVIA.setViaSubset(this.mapVia());
            msgVIA.setVibSubset(this.mapVib());
            msgVIA.setVirSubset(this.mapVir());
                                    
            res = msgVIA.getFssString("HEAD");
            
            Utils.log("(MapNCTSArrivalNotificationToVIA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsVIA mapVia() {
		TsVIA viaSubset = new TsVIA();
		
		viaSubset.setMrn(msgNCTSArrivalNotification.getUCRNumber());
		viaSubset.setBeznr(msgNCTSArrivalNotification.getReferenceNumber());
		viaSubset.setWgdat(msgNCTSArrivalNotification.getArrivalNotificationDate());
		viaSubset.setWgdatFormat(msgNCTSArrivalNotification.getArrivalNotificationDateFormat());
		
		if (msgNCTSArrivalNotification.getEnRouteEvent() != null) {
			if (msgNCTSArrivalNotification.getEnRouteEvent().getIncident() != null) {
				viaSubset.setKzerg(msgNCTSArrivalNotification.getEnRouteEvent().
						getIncident().getIncidentFlag());
			}
		}
		//EI20130325 TODO-IWA: soll es aktiviert werden??? war bisher nicht gemapped:
		viaSubset.setQuelkz("FSS");
		viaSubset.setKdnr(msgNCTSArrivalNotification.getAuthorisedConsigneeTIN().getCustomerIdentifier());
		viaSubset.setEori(msgNCTSArrivalNotification.getAuthorisedConsigneeTIN().getTIN());
		viaSubset.setNl(msgNCTSArrivalNotification.getAuthorisedConsigneeTIN().getBO());
		

		return viaSubset;
	}
	
	private TsVIB mapVib() {
		TsVIB vibSubset = new TsVIB();
		
		vibSubset.setBeznr(msgNCTSArrivalNotification.getReferenceNumber());
		vibSubset.setBewnr(msgNCTSArrivalNotification.getPermitNumber());
		
		vibSubset.setUbgort(msgNCTSArrivalNotification.getPlaceOfUnloadingCode());
		//vibSubset.setBedst(bedst); EI20130208: soll es aktiviert werden??? war bisher nicht gemapped:
		return vibSubset;
	}
	
	private TsVIR mapVir() {
		//EI20130208: Contact == Ansprechpartner new V70
		TsVIR virSubset = null;
		if (msgNCTSArrivalNotification.getContact() != null) {
			virSubset = new TsVIR();
			virSubset.setMrn(msgNCTSArrivalNotification.getUCRNumber());			
			//virSubset.setTyp(msgNCTSArrivalNotification.getContact());
			virSubset.setSbname(msgNCTSArrivalNotification.getContact().getName());
			virSubset.setStellg(msgNCTSArrivalNotification.getContact().getPosition());
			virSubset.setTel(msgNCTSArrivalNotification.getContact().getPhoneNumber());
			virSubset.setFax(msgNCTSArrivalNotification.getContact().getFaxNumber());
			virSubset.setEmail(msgNCTSArrivalNotification.getContact().getEmail());
		}
		
		return virSubset;
	}

}

