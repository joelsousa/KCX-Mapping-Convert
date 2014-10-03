/*
 * Function    : MapExpIndToADI.java
 * Date        : 02.10.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description : Mapping of KIDS format of ExpInd (Prenotification) to FSS Format of ADI
 * ------------              
 * Changes 
 * ------------
 * Author      : E.Iwaniuk
 * Date        : 10.03.2009
 * Label       : EI20090310
 * Description : V60 checked
 * 
 * Author      : E.Iwaniuk
 * Date        : 22.04.2009
 * Label       : EI20090422
 * Description : replaced MsgKids with MsgExpDat
 * 
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk   
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADI;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADIPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpIndToADI<br>
 * Erstellt		: 02.10.2008<br>
 * Beschreibung	: Mapping of KIDS format of ExpInd (Prenotification) to FSS Format of ADI.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MapExpIndToADI extends KidsMessage {
	
	//private MsgKids msgExpDat;
	private MsgExpDat msgExpDat;
	private MsgADI msgADI;
	
	public MapExpIndToADI(XMLEventReader parser) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		msgADI = new MsgADI("");
	}
	public MapExpIndToADI(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		msgADI = new MsgADI("");
		msgADI.setVorSubset(tsvor);
	}

	public String getMessage() {
		StringBuffer res = new StringBuffer();
    	
        try {        
        	//msgADI = new MsgADI("data/fss/out/MapExpIndToADI.dat");
            msgExpDat.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());
                                                          
            // read MessageID from KidsHeader 
            msgADI.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
            
            //füllen der übrigen ADI-Elemente:                      
         	msgADI.setDatSubset(setDat());            
        	msgADI.setEdaSubset(setEda());
        	msgADI.setAtkSubset(setAtk()); 
        	//msgADI.setForwarder( msgExpDat, beznr));
        	String beznr = msgExpDat.getReferenceNumber();
            msgADI.setConsignor(msgExpDat.getConsignor(), beznr);
            msgADI.setConsignee(msgExpDat.getConsignee(), beznr);
            msgADI.setDeclarant(msgExpDat.getDeclarant(), beznr);
            msgADI.setAgent(msgExpDat.getAgent(), beznr);
            msgADI.setSubcontractor(msgExpDat.getSubcontractor(), beznr);           
            
            int sizePos = 0;
            if (msgExpDat.getGoodsItemList() != null) {
                sizePos = msgExpDat.getGoodsItemList().size();
            }
    		for (int i = 0; i < sizePos; i++) {				    			
    			MsgADIPos msgAdiPos = new MsgADIPos();    			    			 
    			MsgExpDatPos tmpKidsPos = (MsgExpDatPos) msgExpDat.getGoodsItemList().get(i);
    			    				
    			//adiPos.setApoSubset(tmpKidsPos, msgExpDat.getReferenceNumber());     			
    			//adiPos.setAtpSubset(tmpKidsPos, msgExpDat.getReferenceNumber());
    			msgAdiPos.setApoSubset(setApo(tmpKidsPos));
    			msgAdiPos.setAtpSubset(setAtp(tmpKidsPos));
    			  			
    			List <TsACO>tmpACOList = new Vector<TsACO>();
    			int sizeAco  = 0;
    			if (tmpKidsPos.getPackagesList() != null) {
    			    sizeAco = tmpKidsPos.getPackagesList().size();
    			}
    			for (int j = 0; j < sizeAco; j++) {	       				  			
    				tmpACOList.add(setAco((Packages) tmpKidsPos.getPackagesList().get(j),
							 		tmpKidsPos.getItemNumber()));
    			}
    			msgAdiPos.setAcoList(tmpACOList);
    			
    			msgADI.addPosList(msgAdiPos);    		
    		}    		                      
            
            //res = msgADI.writeFSS();
            res = msgADI.writeADI();
           
            Utils.log("(MapExpIndToADI getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    //return res;
	    return res.toString();
	}
	
	private TsDAT setDat() {
		TsDAT datSubset = new TsDAT();
		
		datSubset.setBeznr(msgExpDat.getReferenceNumber());
		datSubset.setKuatnr(msgExpDat.getOrderNumber());
		//EI20090609: datSubset.setSb(msgExpDat.getClerk());
		if (msgExpDat.getContact() != null) {
            datSubset.setSb(msgExpDat.getContact().getIdentity());  //EI20090609
		}
		datSubset.setExpdst(msgExpDat.getCustomsOfficeExport());
		
		if (msgExpDat.getDeclarant() != null) {
			if (msgExpDat.getDeclarant().getPartyTIN() != null) {
			datSubset.setKdnran(msgExpDat.getDeclarant().getPartyTIN().getCustomerIdentifier());
			datSubset.setTinan(msgExpDat.getDeclarant().getPartyTIN().getTIN());
            datSubset.setDtzoan(msgExpDat.getDeclarant().getPartyTIN().getIsTINGermanApprovalNumber()); //KCX0001
			}
		}
		
		return datSubset;
	}
	
	private TsEDA setEda() {
		TsEDA edaSubset = new TsEDA();
		
		edaSubset.setBeznr(msgExpDat.getReferenceNumber());     
		edaSubset.setBewnr(msgExpDat.getAuthorizationNumber()); 
		edaSubset.setArtueb(msgExpDat.getTypeOfPermit());           //KCX0026
		edaSubset.setInddat(msgExpDat.getAdvanceNoticeTime()); 	
		edaSubset.setVerm(msgExpDat.getAnnotation()); 	       
		edaSubset.setKzanau(msgExpDat.getDeclarantIsConsignor()); 
		
		if (msgExpDat.getLoadingTime() != null) {                                 
			edaSubset.setGststr(msgExpDat.getLoadingTime().getBeginTime()); 
			edaSubset.setGstend(msgExpDat.getLoadingTime().getEndTime()); 
		 }
		if (msgExpDat.getConsignor() != null) {  
			if (msgExpDat.getConsignor().getPartyTIN() != null) {
    			edaSubset.setKdnrau(msgExpDat.getConsignor().getPartyTIN().getCustomerIdentifier());
    			edaSubset.setTinau(msgExpDat.getConsignor().getPartyTIN().getTIN());  
                edaSubset.setDtzoau(msgExpDat.getConsignor().getPartyTIN().getIsTINGermanApprovalNumber()); //KCX0001
			}
		}
		if (msgExpDat.getAgent() != null) {  
			if (msgExpDat.getAgent().getPartyTIN() != null) { 
    			edaSubset.setKdnrva(msgExpDat.getAgent().getPartyTIN().getCustomerIdentifier()); 
    			edaSubset.setTinva(msgExpDat.getAgent().getPartyTIN().getTIN());  
                edaSubset.setDtzova(msgExpDat.getAgent().getPartyTIN().getIsTINGermanApprovalNumber());     //KCX0001
    	    	//this.setEtnva(msgExpDat.getAgent().getETNAddress());
			}
		}
		if (msgExpDat.getSubcontractor() != null) {   
			if (msgExpDat.getSubcontractor().getPartyTIN() != null) {    
    			edaSubset.setKdnrsu(msgExpDat.getSubcontractor().getPartyTIN().getCustomerIdentifier()); 
    			edaSubset.setTinsu(msgExpDat.getSubcontractor().getPartyTIN().getTIN());  
                edaSubset.setDtzosu(msgExpDat.getSubcontractor().getPartyTIN().getIsTINGermanApprovalNumber());
			}
		}	
		
		if (msgExpDat.getPlaceOfLoading() != null) {                                 
			edaSubset.setLdocde(msgExpDat.getPlaceOfLoading().getCode());  
			edaSubset.setBeostr(msgExpDat.getPlaceOfLoading().getStreet()); 
			edaSubset.setBeoort(msgExpDat.getPlaceOfLoading().getCity());  
			edaSubset.setBeoplz(msgExpDat.getPlaceOfLoading().getPostalCode()); 
			edaSubset.setBeozus(msgExpDat.getPlaceOfLoading().getAgreedLocationOfGoods());
		}		
		
		return edaSubset;
	}
	
	private TsATK setAtk() {
		TsATK atkSubset = new TsATK();
		
		atkSubset.setBeznr(msgExpDat.getReferenceNumber());	
		
		if (msgExpDat.getTransportMeansDeparture() != null) {
			atkSubset.setBfarta(msgExpDat.getTransportMeansDeparture().getTransportationType());  //KCX0004
		}
		
		if (msgExpDat.getExportRefundHeader() != null) {
			atkSubset.setAsldbe(msgExpDat.getExportRefundHeader().getDestinationCountry());
		}
		
		return atkSubset;
	}
	private TsAPO setApo(MsgExpDatPos msgExpDatPos) {
		TsAPO apoSubset = new TsAPO();
		
		apoSubset.setBeznr(msgExpDat.getReferenceNumber());
		apoSubset.setPosnr(msgExpDatPos.getItemNumber());  		
		apoSubset.setArtnr(msgExpDatPos.getArticleNumber());
		if (msgExpDatPos.getCommodityCode() != null) {
			apoSubset.setTnr(msgExpDatPos.getCommodityCode().getTarifCode());  
			apoSubset.setTnrtrc(msgExpDatPos.getCommodityCode().getEUTarifCode());  
			apoSubset.setTnrzu1(msgExpDatPos.getCommodityCode().getTarifAddition1());  
			apoSubset.setTnrzu2(msgExpDatPos.getCommodityCode().getTarifAddition2());  
			apoSubset.setTnrnat(msgExpDatPos.getCommodityCode().getAddition());  
		}			
		
		return apoSubset;
	}

	private TsATP setAtp(MsgExpDatPos msgExpDatPos) {		
		TsATP atpSubset = new TsATP();
		
		atpSubset.setBeznr(msgExpDat.getReferenceNumber());
		atpSubset.setPosnr(msgExpDatPos.getItemNumber());  
		
		if (msgExpDatPos.getExportRefundItem() != null) {
			atpSubset.setMeaest(msgExpDatPos.getExportRefundItem().getUnitOfMeasurement());   
			atpSubset.setMenge(msgExpDatPos.getExportRefundItem().getAmount());    			
		}		
		return atpSubset;
	}
	
	private TsACO setAco(Packages packages, String posnr) {
		if (packages == null) {
		    return null;
		}
		TsACO acoSubset = new TsACO();
		
		acoSubset.setBeznr(msgExpDat.getReferenceNumber());  				 
		acoSubset.setPosnr(posnr);  
		acoSubset.setLfdnr(packages.getSequentialNumber());  
		acoSubset.setColanz(packages.getQuantity()); 
		acoSubset.setColart(packages.getType()); 
		acoSubset.setColzch(packages.getMarks());			
		
		return acoSubset;
	}
}
