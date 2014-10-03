/*
 * Function    : MapExpEntToEAM.java
 * Date        : 13.10.2008
 * Author      : Kewill CSF / houdek
 * Description : Mapping of KIDS format of Completion to FSS-Format EAM
 *               Completion 
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 22.04.2009
 * Description : replaced MsgKind with MsgExpEnt
 *
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53.MsgEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53.MsgEAMPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpEntToEAM<br>
 * Erstellt		: 13.10.2008<br>
 * Beschreibung	: Mapping of KIDS format of Completion to FSS-Format EAM Completion.
 * 
 * @author houdek
 * @version 1.0.00
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
    	
        try {        
            msgExpEnt.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpEnt.getReferenceNumber());
                                                          
            // read MessageID from KidsHeader 
            msgEAM.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
         
        	
            msgEAM.setDatSubset(setDat());
            msgEAM.setEamSubset(setEam());
            //V60: msgADI.setConsignee(setAdr("Consignee", msgExpEnt, "0"));
            if (msgExpEnt.getConsignee() != null) {
            	msgEAM.setConsignee(setAdresse53("2", msgExpEnt.getConsignee().getAddress(), 
            						msgExpEnt.getReferenceNumber(), "0"));  
            }
            
            int sizePos = 0;
            if (msgExpEnt.getGoodsItemList() != null) {
                sizePos = msgExpEnt.getGoodsItemList().size();
            }
    		for (int i = 0; i < sizePos; i++) {				    			
    			MsgEAMPos tmpEamPos = new MsgEAMPos();
    			MsgExpEntPos tmpKidsPos = (MsgExpEntPos) msgExpEnt.getGoodsItemList().get(i);
 
    			tmpEamPos.setApoSubset(setApo(tmpKidsPos));  
    			tmpEamPos.setEpoSubset(setEpo(tmpKidsPos)); 
                //V60: tmpEamPos.setConsignee(setAdr("2", tmpKidsPos.getConsignee().getAddress(), null, 
    			//                                       beznr, tmpKidsPos.getItemNumber()));
    			
    			List <PreviousDocument> tmpDocList = tmpKidsPos.getPreviousDocumentList();
    			int sizeDoc = 0;
    			if (tmpDocList != null) {
    			    sizeDoc = tmpDocList.size();
    			}
    			for (int j = 0; j < sizeDoc; j++) {
    				TsAZV azvSubset = new TsAZV();
    				azvSubset = setAzv(tmpDocList.get(j), tmpKidsPos.getItemNumber());
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
		//EI20090609: datSubset.setSb(msgExpEnt.getClerk());
		if (msgExpEnt.getContact() != null) {
			datSubset.setSb(msgExpEnt.getContact().getIdentity()); //EI20090609
		}
		datSubset.setExpdst(msgExpEnt.getCustomsOfficeExport());
		datSubset.setEamdst(msgExpEnt.getCustomsOfficeForCompletion());		
		
		if (msgExpEnt.getDeclarant() != null) {
			if (msgExpEnt.getDeclarant().getPartyTIN() != null) {
			datSubset.setKdnran(msgExpEnt.getDeclarant().getPartyTIN().getCustomerIdentifier());
			datSubset.setTinan(msgExpEnt.getDeclarant().getPartyTIN().getTIN());	
			//V60: datSubset.setDtzoan(msgExpEnt.getDeclarantTIN().getTIN().getIsTINGermanApprovalNumber());
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
		
		eamSubset.setBeznr(msgExpEnt.getReferenceNumber());		
		eamSubset.setEamdat(msgExpEnt.getCompletionTime());  
		if (msgExpEnt.getTransportInland() != null) {
			eamSubset.setBfvkzi(msgExpEnt.getTransportInland().getTransportMode());
		}
	    if (msgExpEnt.getTransportBorder() != null) {
	    	eamSubset.setBfvkzg(msgExpEnt.getTransportBorder().getTransportMode()); 	
	    	eamSubset.setBfartg(msgExpEnt.getTransportBorder().getTransportationType()); 			
	    	eamSubset.setBfkzg(msgExpEnt.getTransportBorder().getTransportationNumber()); 
	    	eamSubset.setBfnatg(msgExpEnt.getTransportBorder().getTransportationCountry());
	    }
	    if (msgExpEnt.getBusiness() != null) {
	    	eamSubset.setGesart(msgExpEnt.getBusiness().getBusinessTypeCode());  
	    	eamSubset.setGesprs(msgExpEnt.getBusiness().getInvoicePrice());  
	    	eamSubset.setGeswrg(msgExpEnt.getBusiness().getCurrency()); 		   
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

