package com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgEAMPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module    	: Export/aes. 
 * Created    	: 24.07.2012
 * Description  : Mapping of V21 KidsMessage Completion to ZABIS FSS-Format.
 * 				: ZABIS FSS-Format Version 7.0.
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author      : iwaniuk
 * @version     : 2.1.00
 */

public class MapExpEntToEAM extends KidsMessage {
	
	private MsgExpEnt msgExpEnt;
	private MsgEAM msgEAM;
	private String subversion = "";   //EI20120422
	
	public MapExpEntToEAM(XMLEventReader parser) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
    	msgEAM = new MsgEAM("");
	}
	
	public MapExpEntToEAM(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
    	msgEAM = new MsgEAM("");
		msgEAM.setVorSubset(tsvor);
	}
	public MapExpEntToEAM(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
    	msgEAM = new MsgEAM("");
		msgEAM.setVorSubset(tsvor);
		msgEAM.setHeadSubset(head);
	}
	
	public String getMessage() {
		String res = "";
    	String referenceNr;
    	
        try {        
            msgExpEnt.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpEnt.getReferenceNumber());
               
            if (this.getKidsHeader() != null && !Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
            	subversion = Utils.removeDots(this.getKidsHeader().getRelease());
			}
            
            // read MessageID from KidsHeader 
            msgEAM.getVorSubset().setMsgid(getKidsHeader().getMessageID());          
            referenceNr = msgExpEnt.getReferenceNumber();
            msgEAM.setDatSubset(mapKidsToDat());
            msgEAM.setEamSubset(mapKidsToEam());
               
            msgEAM.setDeclarant(msgExpEnt.getDeclarant(), referenceNr);           
            msgEAM.setConsignee(msgExpEnt.getConsignee(), referenceNr);            
            msgEAM.setFinalUser(msgExpEnt.getFinalRecipient(), referenceNr);  //new for V21
                       
            if (msgExpEnt.getGoodsItemList() != null) {           
    			for (MsgExpEntPos item : msgExpEnt.getGoodsItemList()) {
    				if (item != null) {
    					MsgEAMPos pos = new MsgEAMPos();    					
    					pos.setApoSubset(mapKidsToApo(item));  
    					pos.setEpoSubset(mapKidsToEpo(item)); 
    					pos.setConsignee(item.getConsignee(), referenceNr, item.getItemNumber());
    					pos.setFinalUser(item.getFinalRecipient(), referenceNr, item.getItemNumber());  //new for V21
    			    								
    					if (item.getPreviousDocumentList() != null) {    					
    						//for (PreviousDocumentV20 prev : item.getPreviousDocumentList()) {
    						for (PreviousDocumentV21 prev : item.getPreviousDocumentList()) {
    							if (prev != null) {    						
    								TsAZV azvSubset = new TsAZV();
    								azvSubset = mapKidsToAzv(item.getItemNumber(), prev);
    								pos.addAzvList(azvSubset);
    							}
    						}    			
    					    //EI20130108: msgEAM.addPosList(pos); 
    					}
    					msgEAM.addPosList(pos);  //EI20130108
    				}
    			}
    		}    		                      
            
            //res = msgEAM.getFssString();
            /* EI20140206
            if (this.writeHead()) { 				//EI20130213
            	res = msgEAM.getFssString("HEAD");
            } else {
            	res = msgEAM.getFssString();
            }  
           */
            res = msgEAM.getFssString("HEAD"); // EI20140206
            
            Utils.log("(MapExpEntToEAM getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
	   
	    return res;
	}
	
	private TsDAT mapKidsToDat() {
		TsDAT datSubset = new TsDAT();

		datSubset.setMrn(msgExpEnt.getUCRNumber());		
		datSubset.setBeznr(msgExpEnt.getReferenceNumber());		
		datSubset.setKuatnr(msgExpEnt.getOrderNumber());		
		if (msgExpEnt.getContact() != null) {
            datSubset.setSb(msgExpEnt.getContact().getIdentity()); //EI20090609
		}
		datSubset.setExpdst(msgExpEnt.getCustomsOfficeExport());
		datSubset.setEamdst(msgExpEnt.getCustomsOfficeForCompletion());		
		
		return datSubset;
	}
	
	private TsEAM mapKidsToEam() {
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
	    	// CK120824
	    	// eamSubset.setGesprs(business.getInvoicePrice());  
	    	eamSubset.setGesprs(Utils.addZabisDecimalPlaceV7(business.getInvoicePrice(), 2));
	    	if (subversion.equals("2101")) {     //EI20130422
	    		eamSubset.setGesprs(business.getInvoicePrice());
	    	}
	    	eamSubset.setGeswrg(business.getCurrency()); 		   
	    }
	    if (msgExpEnt.getIncoTerms() != null) {
	    	eamSubset.setLibart(msgExpEnt.getIncoTerms().getIncoTerm()); 
	    	eamSubset.setLibinc(msgExpEnt.getIncoTerms().getText()); 		
	    	eamSubset.setLibort(msgExpEnt.getIncoTerms().getPlace()); 			
	    } 
	    eamSubset.setAdrkon(msgExpEnt.getAddressCombination());      //new for v21	
	    
		return eamSubset;
	}
	
	private TsAPO mapKidsToApo(MsgExpEntPos msgExpEntPos) {
		if (msgExpEntPos == null) {
		    return null;
		}
		
		TsAPO apoSubset = new TsAPO();
		
		apoSubset.setBeznr(msgExpEnt.getReferenceNumber());
		apoSubset.setPosnr(msgExpEntPos.getItemNumber());  	
		apoSubset.setUbland(msgExpEntPos.getOriginFederalState());		
		 
		if (msgExpEntPos.getStatistic() != null) {
			// CK120824
			// apoSubset.setWmahst(msgExpEntPos.getStatistic().getAdditionalUnit()); 			
			apoSubset.setWmahst(Utils.addZabisDecimalPlaceV7(msgExpEntPos.getStatistic().getAdditionalUnit(), 3));
			if (subversion.equals("2101")) {     //EI20130422
				apoSubset.setWmahst(msgExpEntPos.getStatistic().getAdditionalUnit()); 
			}
			apoSubset.setAhwert(msgExpEntPos.getStatistic().getStatisticalValue()); 
			apoSubset.setAhrico(msgExpEntPos.getStatistic().getStatisticalValueSendFlag());	//EI20130808: fuer ASE22
		}		
		apoSubset.setAdrkon(msgExpEntPos.getAddressCombination());   //EI20120920: new for V21
		if (msgExpEntPos.getBusiness() != null) {
			apoSubset.setGesart(msgExpEntPos.getBusiness().getBusinessTypeCode());  //new for V21
		}
		if (msgExpEntPos.getIncoTerms() != null) {								//new for V21
			apoSubset.setLibart(msgExpEntPos.getIncoTerms().getIncoTerm());  
			apoSubset.setLibinc(msgExpEntPos.getIncoTerms().getText());
			apoSubset.setLibort(msgExpEntPos.getIncoTerms().getPlace());
		}		
		
		return apoSubset;
	}
	
	private TsEPO mapKidsToEpo(MsgExpEntPos msgExpEntPos) {
		if (msgExpEntPos == null) {
		    return null;
		}
		
		TsEPO epoSubset = new TsEPO();
		
		epoSubset.setBeznr(msgExpEnt.getReferenceNumber());
		epoSubset.setPosnr(msgExpEntPos.getItemNumber());  	
		
		if (msgExpEntPos.getStatistic() != null) {
			// CK120824
			// epoSubset.setBasbtg(msgExpEntPos.getStatistic().getValue());   
			epoSubset.setBasbtg(Utils.addZabisDecimalPlaceV7(msgExpEntPos.getStatistic().getValue(), 2));
			if (subversion.equals("2101")) {     //EI20130422
				epoSubset.setBasbtg(msgExpEntPos.getStatistic().getValue()); 
			}
			epoSubset.setBaswrg(msgExpEntPos.getStatistic().getCurrency());
		}
		
		return epoSubset;
	}
	//private TsAZV mapKidsToAzv(String posnr, PreviousDocumentV20 doc) {
	private TsAZV mapKidsToAzv(String posnr, PreviousDocumentV21 doc) {	
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

