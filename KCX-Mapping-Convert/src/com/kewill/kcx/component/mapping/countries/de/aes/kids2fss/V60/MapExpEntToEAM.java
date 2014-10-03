/*
 * Function    : MapExpEntToEAM.java
 * Date        : 24.03.2009
 * Author      : Kewill CSF / krzoska
 * Description : Mapping of KIDS format of Completion to FSS-Format EAM
 *               Completion 
 * Changes 
 * -----------
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgEAMPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpEntToEAM<br>
 * Erstellt		: 24.03.2009<br>
 * Beschreibung	: Mapping of KIDS format of Completion to FSS-Format EAM Completion.
 * 
 * @author krzoska
 * @version 6.0.00
 */
public class MapExpEntToEAM extends KidsMessage {
	
	private MsgExpEnt msgExpEnt;
	private MsgEAM msgEAM;
	
	public MapExpEntToEAM(XMLEventReader parser) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
    	msgEAM = new MsgEAM("");
	}
	
	public MapExpEntToEAM(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
    	msgEAM = new MsgEAM("");
		msgEAM.setVorSubset(tsvor);
	}
	public String getMessage() {
		StringBuffer res = new StringBuffer();
    	String referenceNr;
    	
        try {        
            msgExpEnt.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpEnt.getReferenceNumber());
                                                          
            // read MessageID from KidsHeader 
            msgEAM.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
         
            referenceNr = msgExpEnt.getReferenceNumber();
            msgEAM.setDatSubset(setDat());
            msgEAM.setEamSubset(setEam());
           
            if (msgExpEnt.getDeclarant() != null) {
            	msgEAM.setDeclarant(msgExpEnt.getDeclarant(), referenceNr);
            }
            if (msgExpEnt.getConsignee() != null) {
            	msgEAM.setConsignee(msgExpEnt.getConsignee(), referenceNr);
            }
            
            int sizePos = 0;
            if (msgExpEnt.getGoodsItemList() != null) {
                sizePos = msgExpEnt.getGoodsItemList().size();
            }
    		for (int i = 0; i < sizePos; i++) {				    			
    			MsgEAMPos tmpEamPos = new MsgEAMPos();
    			MsgExpEntPos tmpMsgExpEntPos = (MsgExpEntPos) msgExpEnt.getGoodsItemList().get(i);
 
    			tmpEamPos.setApoSubset(setApo(tmpMsgExpEntPos));  
    			tmpEamPos.setEpoSubset(setEpo(tmpMsgExpEntPos)); 
    			tmpEamPos.setConsignee(tmpMsgExpEntPos.getConsignee(), msgExpEnt.getReferenceNumber(), 
    			                                                       tmpMsgExpEntPos.getItemNumber());
    			
    			List <PreviousDocument> tmpDocList = tmpMsgExpEntPos.getPreviousDocumentList();
    			int sizeDoc = 0;
    			if (tmpDocList != null) {
    			    sizeDoc = tmpDocList.size();
    			}
    			for (int j = 0; j < sizeDoc; j++) {
    				TsAZV azvSubset = new TsAZV();
    				azvSubset = setAzv(tmpDocList.get(j), tmpMsgExpEntPos.getItemNumber());
    				tmpEamPos.addAzvList(azvSubset);
    			}
    			
    			msgEAM.addPosList(tmpEamPos);    		
    		}    		                      
            
            res = msgEAM.writeEAM();
           
            Utils.log("(MapExpEntToEAM getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
	    
	    return res.toString();
	}
	
	private TsDAT setDat() {
		TsDAT datSubset = new TsDAT();

		datSubset.setMrn(msgExpEnt.getUCRNumber());		
		datSubset.setBeznr(msgExpEnt.getReferenceNumber());		
		datSubset.setKuatnr(msgExpEnt.getOrderNumber());
		//EI29900609:datSubset.setSb(msgExpEnt.getClerk());
		if (msgExpEnt.getContact() != null) {
            datSubset.setSb(msgExpEnt.getContact().getIdentity()); //EI20090609
		}
		datSubset.setExpdst(msgExpEnt.getCustomsOfficeExport());
		datSubset.setEamdst(msgExpEnt.getCustomsOfficeForCompletion());		
		
		if (msgExpEnt.getDeclarant() != null) {
			if (msgExpEnt.getDeclarant().getPartyTIN() != null) {
			datSubset.setKdnran(msgExpEnt.getDeclarant().getPartyTIN().getCustomerIdentifier());
			datSubset.setTinan(msgExpEnt.getDeclarant().getPartyTIN().getTIN());	
			datSubset.setDtzoan(msgExpEnt.getDeclarant().getPartyTIN().getIsTINGermanApprovalNumber());
			}
		}		
		
		if (msgExpEnt.getConsignee() != null) {
			if (msgExpEnt.getConsignee().getPartyTIN() != null) {
			datSubset.setKdnrem(msgExpEnt.getConsignee().getPartyTIN().getCustomerIdentifier());
			datSubset.setTinem(msgExpEnt.getConsignee().getPartyTIN().getTIN());
			}
		}	
		
		return datSubset;
	}
	
	private TsEAM setEam() {
		TsEAM eamSubset = new TsEAM();
		TransportMeans transportMeansInland = msgExpEnt.getTransportInland();
		TransportMeans transportMeansBorder = msgExpEnt.getTransportBorder();
		Business business = msgExpEnt.getBusiness();
		
		eamSubset.setBeznr(msgExpEnt.getReferenceNumber());		
		eamSubset.setEamdat(msgExpEnt.getCompletionTime());  
		
		if (transportMeansInland != null) {
            eamSubset.setBfvkzi(transportMeansInland.getTransportMode());
		}
		
	    if (transportMeansBorder  != null) {
	    	eamSubset.setBfvkzg(transportMeansBorder.getTransportMode()); 	
	    	eamSubset.setBfartg(transportMeansBorder.getTransportationType()); 			
	    	eamSubset.setBfkzg(transportMeansBorder.getTransportationNumber()); 
	    	eamSubset.setBfnatg(transportMeansBorder.getTransportationCountry());
	    }
	    if (msgExpEnt.getBusiness() != null) {
	    	eamSubset.setGesart(business.getBusinessTypeCode());  
	    	eamSubset.setGesprs(business.getInvoicePrice());  
	    	eamSubset.setGeswrg(business.getCurrency()); 		   
	    }
	    if (msgExpEnt.getIncoTerms() != null) {
	    	eamSubset.setLibart(msgExpEnt.getIncoTerms().getIncoTerm()); 
	    	eamSubset.setLibinc(msgExpEnt.getIncoTerms().getText()); 		
	    	eamSubset.setLibort(msgExpEnt.getIncoTerms().getPlace()); 			
	    } 
	    
		return eamSubset;
	}
	
	private TsAPO setApo(MsgExpEntPos msgExpEntPos) {
		if (msgExpEntPos == null) {
		    return null;
		}
		
		TsAPO apoSubset = new TsAPO();
		
		apoSubset.setBeznr(msgExpEnt.getReferenceNumber());
		apoSubset.setPosnr(msgExpEntPos.getItemNumber());  	
		apoSubset.setUbland(msgExpEntPos.getOriginFederalState());		
		 
		if (msgExpEntPos.getStatistic() != null) {
			apoSubset.setWmahst(msgExpEntPos.getStatistic().getAdditionalUnit()); 			
			apoSubset.setAhwert(msgExpEntPos.getStatistic().getStatisticalValue()); 
		}		
					
		return apoSubset;
	}
	
	private TsEPO setEpo(MsgExpEntPos msgExpEntPos) {
		if (msgExpEntPos == null) {
		    return null;
		}
		
		TsEPO epoSubset = new TsEPO();
		
		epoSubset.setBeznr(msgExpEnt.getReferenceNumber());
		epoSubset.setPosnr(msgExpEntPos.getItemNumber());  	
		
		if (msgExpEntPos.getStatistic() != null) {
			epoSubset.setBasbtg(msgExpEntPos.getStatistic().getValue());   
			epoSubset.setBaswrg(msgExpEntPos.getStatistic().getCurrency());
		}
		
		return epoSubset;
	}
	private TsAZV setAzv(PreviousDocument doc, String posnr) {	
		if (doc == null) {
		    return null;
		}
		
		TsAZV azvSubset = new TsAZV();
		
		azvSubset.setBeznr(msgExpEnt.getReferenceNumber());
		azvSubset.setPosnr(posnr);  
		azvSubset.setAzvref(doc.getMarks());   
		azvSubset.setAzvzus(doc.getAdditionalInformation());
		
		return azvSubset;
	}
	
}

