/*
 * Function    : MapExpDatToADA.java
 * Date        : 15.10.2008
 * Author      : Kewill CSF / krzoska
 * Description : Mapping of KIDS format of ExportDeclaration to FSS format ADA

 * Changes
 * -----------
 * Author      : EI
 * Date        : 14.05.2009
 * Label       : EI20090514
 * Description : writePrevious(...) war auskommentiert (!!!???)
 * 
 * Author      : EI
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk   
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53.MsgADA;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53.MsgADAPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul		: MapExpDatToADA<br>
 * Erstellt		: 15.10.2008<br>
 * Beschreibung	: Mapping of KIDS format of ExportDeclaration to FSS format ADA.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapExpDatToADA extends KidsMessage {
		
	private MsgExpDat msgExpDat;
	private MsgADA msgADA;
	
	public MapExpDatToADA(XMLEventReader parser) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		msgADA = new MsgADA("");
	}

	public MapExpDatToADA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		msgADA = new MsgADA("");
		msgADA.setVorSubset(tsvor);
	}

	public String getMessage() {
	
    	String res = "";        
        MsgADAPos msgADAPos = null;
        String referenceNumber = null;
        String itemNumber = "";
        

        try {        
        	//msgADA = new MsgADA("data/fss/out/MapExpIndToADA.dat");
        	
            msgExpDat.parse(HeaderType.KIDS);                      
    		referenceNumber = msgExpDat.getReferenceNumber();
            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());
    		
            // read MessageID from KidsHeader 
            msgADA.getVorSubset().setMsgid(getKidsHeader().getMessageID()); 
            
            //füllen der übrigen ADA-Elementen:                	
           	msgADA.setDatSubset(setDat(referenceNumber));            
           	msgADA.setEdaSubset(setEda(referenceNumber));
           	msgADA.setEamSubset(setEam(referenceNumber));  
       		if (msgExpDat.getSeal() != null) {
       			List <String>list = msgExpDat.getSeal().getSealNumberList();
       			if (list != null) {
       				for (int i = 0, listSize = list.size(); i < listSize; i++) {
                       msgADA.addAvsList(setAvs(referenceNumber, list.get(i)));
       				}
       			}
       		}
           
    	    // Consignor(1)
            // Consignee(2)
            // Declarant(3)
            // Agent(4)
            //Subcontractor(5) 
            msgADA.setConsignor(msgExpDat.getConsignor(), "1", referenceNumber, "0");
            msgADA.setConsignee(msgExpDat.getConsignee(), "2", referenceNumber, "0");
            msgADA.setDeclarant(msgExpDat.getDeclarant(), "3", referenceNumber, "0");
            msgADA.setAgent(msgExpDat.getAgent(), "4", referenceNumber, "0");
            msgADA.setSubcontractor(msgExpDat.getSubcontractor(), "5", referenceNumber, "0");
            
            if (msgExpDat.getGoodsItemList() != null) {  //EI20130529:
    		for (int i = 0, itemListSize = msgExpDat.getGoodsItemList().size(); i < itemListSize; i++) {
    			MsgExpDatPos msgExpDatPos = msgExpDat.getGoodsItemList().get(i);
    			itemNumber = msgExpDatPos.getItemNumber();
    			msgADAPos = new MsgADAPos();  
    			
    			msgADAPos.setApoSubset(setApo(msgExpDatPos, referenceNumber));
    			msgADAPos.setEpoSubset(setEpo(msgExpDatPos.getStatistic(), referenceNumber, itemNumber)); 
    			
    			if (msgExpDatPos.getPackagesList() != null) {
    				List <Packages> list = msgExpDatPos.getPackagesList();
    				
    				for (int j = 0, packListSize = list.size(); j < packListSize; j++) {
                        msgADAPos.addACOList(setAco(list.get(j), referenceNumber, itemNumber));
        			}
    			}

    			if (msgExpDatPos.getContainer() != null)  {
    				List <String> list = msgExpDatPos.getContainer().getNumberList();
    				
    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
        				msgADAPos.addACNList(setAcn(list.get(j), referenceNumber, itemNumber));
    				}
    			}
    							
    			if (msgExpDatPos.getPreviousDocumentList() != null) {
    				List <PreviousDocument> list =  msgExpDatPos.getPreviousDocumentList();
    				
    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
    					msgADAPos.addAZVList(setAzv(list.get(j), referenceNumber, itemNumber));
    				}    				
    			}
    			
    			if (msgExpDatPos.getDocumentList() != null)  {
    				List <Document> list = msgExpDatPos.getDocumentList();
    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
    					msgADAPos.addAEDList(setAed(list.get(j), referenceNumber, itemNumber));
    				}
    			}
    			msgADA.addPosList(msgADAPos);    		
    		}
            }
            
            res = msgADA.writeADA();
           
            //Utils.log("(MapExpDatToADA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsACN setAcn(String containerNr, String beznr, String posnr) {
		
		TsACN tsACN = new TsACN();
		if (Utils.isStringEmpty(beznr) || Utils.isStringEmpty(posnr)) {
			return null;
		}
		if (Utils.isStringEmpty(containerNr)) {
			return null;
		}
		
		tsACN.setBeznr(beznr);
		tsACN.setPosnr(posnr);
		tsACN.setConnr(containerNr);
		
		return tsACN;
	}

	private TsACO setAco(Packages packages, String beznr, String posnr) {
		TsACO tmpACO = new TsACO();
		
		if (Utils.isStringEmpty(beznr) || Utils.isStringEmpty(posnr)) {
			return null;
		}
		if (packages == null) {
			return null;
		}
		
		tmpACO.setBeznr(beznr);
		tmpACO.setPosnr(posnr);
		tmpACO.setColanz(packages.getQuantity());
		tmpACO.setLfdnr(packages.getSequentialNumber());
		tmpACO.setColart(packages.getType());
		tmpACO.setColzch(packages.getMarks());
		
		return tmpACO;
	}

	private TsEDA setEda(String beznr) {
		TsEDA tmpEda = new TsEDA();
		
		if (Utils.isStringEmpty(beznr)) {
		    return null;
		}

		LoadingTime 	loadingTime 			= msgExpDat.getLoadingTime();  		
		PlaceOfLoading 	placeOfLoading 			= msgExpDat.getPlaceOfLoading();
		Seal			seal					= msgExpDat.getSeal();
		
		TIN consignorTIN = null; 						
		TIN agentTIN = null;				
		TIN subcontractorTIN = null;
		TIN	declarantTIN = null;
		
		if (msgExpDat.getConsignor() != null) {
			consignorTIN 			= msgExpDat.getConsignor().getPartyTIN();
		}
		if (msgExpDat.getAgent() != null) {
			agentTIN				= msgExpDat.getAgent().getPartyTIN();
		}
		if (msgExpDat.getSubcontractor() != null) {
			subcontractorTIN		= msgExpDat.getSubcontractor().getPartyTIN();
		}
		if (msgExpDat.getDeclarant() != null) {
			declarantTIN			= msgExpDat.getDeclarant().getPartyTIN();
		}
					
		tmpEda.setBeznr(beznr);
		tmpEda.setBewnr(msgExpDat.getAuthorizationNumber()); 
		tmpEda.setArtueb(msgExpDat.getTypeOfPermit()); 
		 	
		tmpEda.setVerm(msgExpDat.getAnnotation()); 	
		tmpEda.setKzanau(msgExpDat.getDeclarantIsConsignor());
		tmpEda.setArtaus(msgExpDat.getAreaCode()); 						
		tmpEda.setLdbe(msgExpDat.getDestinationCountry());  
		tmpEda.setConkz(msgExpDat.getTransportInContainer()); 			
		tmpEda.setFregnr(msgExpDat.getUCROtherSystem());			 
		tmpEda.setExtdst(msgExpDat.getIntendedOfficeOfExit()); 

		
		if (loadingTime != null) {
			tmpEda.setGststr(loadingTime.getBeginTime()); 
			tmpEda.setGstend(loadingTime.getEndTime()); 
		 }
		if (consignorTIN != null) {
			tmpEda.setKdnrau(consignorTIN.getCustomerIdentifier());
			tmpEda.setTinau(consignorTIN.getTIN());  
			tmpEda.setDtzoau(consignorTIN.getIsTINGermanApprovalNumber()); 
		}
		if (agentTIN != null) {
			tmpEda.setKdnrva(agentTIN.getCustomerIdentifier()); 
			tmpEda.setTinva(agentTIN.getTIN());  
			tmpEda.setDtzova(agentTIN.getIsTINGermanApprovalNumber()); 
	    	//this.setEtnva(msgExpDat.getAgent().getETNAddress());  
		}
		
		//AK20090112
		if (declarantTIN != null) {
			tmpEda.setDtzoan(declarantTIN.getIsTINGermanApprovalNumber());
		}
		if (subcontractorTIN != null) {
			tmpEda.setKdnrsu(subcontractorTIN.getCustomerIdentifier()); 
			tmpEda.setTinsu(subcontractorTIN.getTIN());  
			tmpEda.setDtzosu(subcontractorTIN.getIsTINGermanApprovalNumber()); 
		}	
		
		if (placeOfLoading != null) {
			tmpEda.setLdocde(placeOfLoading.getCode());  
			tmpEda.setBeostr(placeOfLoading.getStreet()); 
			tmpEda.setBeoort(placeOfLoading.getCity());  
			tmpEda.setBeoplz(placeOfLoading.getPostalCode()); 
			tmpEda.setBeozus(placeOfLoading.getAgreedLocationOfGoods());  			
		}		
		
		if (seal != null) {
			tmpEda.setKztyd(seal.getUseOfTydenseals());
			tmpEda.setKzstock(seal.getUseOfTydensealStock());
			tmpEda.setAnzve(seal.getNumber());
			tmpEda.setVsart(seal.getKind());
		}
		
		return tmpEda;
	}
		
	private TsDAT setDat(String beznr) {		
		TsDAT 	tmpDat 			= new TsDAT();
		TIN   	declarantTIN 	= null;
		TIN 	consigneeTIN 	= null;
	
		if (Utils.isStringEmpty(beznr)) {
			return null;
		}
		
		if (msgExpDat.getDeclarant() != null) {
			declarantTIN 	= msgExpDat.getDeclarant().getPartyTIN();
		}
		if (msgExpDat.getConsignee() != null) {
			consigneeTIN 	= msgExpDat.getConsignee().getPartyTIN();
		}

		tmpDat.setBeznr(beznr);
		tmpDat.setKuatnr(msgExpDat.getOrderNumber());
		tmpDat.setMrn(msgExpDat.getUCRNumber());
		
		//tmpDat.setKzart(msgExpDat.getKindOfAnswer());		
		tmpDat.setExpdst(msgExpDat.getCustomsOfficeExport());
		tmpDat.setEamdst(msgExpDat.getCustomsOfficeForCompletion());
				
		if (msgExpDat.getDeclarant() != null) {
				tmpDat.setEtnan(msgExpDat.getDeclarant().getETNAddress());
				if (declarantTIN != null) {
					tmpDat.setKdnran(declarantTIN.getCustomerIdentifier());
					tmpDat.setTinan(declarantTIN.getTIN());
					//tmpDat.setDtzoan(msgExpDat.getDeclarantTIN().getIsTINGermanApprovalNumber());
				}
		}
		if (consigneeTIN != null) {
			tmpDat.setKdnrem(consigneeTIN.getCustomerIdentifier());
			tmpDat.setTinem(consigneeTIN.getTIN());
		}
		if (msgExpDat.getForwarder() != null) {
			tmpDat.setEtnsp(msgExpDat.getForwarder().getETNAddress());
			if (msgExpDat.getForwarder().getPartyTIN() != null) {
				tmpDat.setKdnrsp(msgExpDat.getForwarder().getPartyTIN().getCustomerIdentifier());
			}
		}
				
		
		tmpDat.setQuelkz(msgExpDat.getPreviousProcedure());
		//EI090608: tmpDat.setSb(msgExpDat.getClerk());
		if (msgExpDat.getContact() != null) {
	        tmpDat.setSb(msgExpDat.getContact().getIdentity()); //EI090608
		}
		tmpDat.setVekan(msgExpDat.getReceiverCustomerNumber());
		
		return tmpDat;
	}
	
	private TsEAM setEam(String beznr) {
		TsEAM 				tmpEam 			= new TsEAM();
		TransportMeans		meansBorder 	= msgExpDat.getTransportMeansBorder();  
		Business			business		= msgExpDat.getBusiness();
		IncoTerms			incoTerms		= msgExpDat.getIncoTerms();
		
		if (msgExpDat == null) {
			return null; 		
		}
		if (Utils.isStringEmpty(beznr)) {
			return null;
		}
		
		tmpEam.setBeznr(beznr);		

		if (msgExpDat.getTransportMeansInland() != null) {
			tmpEam.setBfvkzi(msgExpDat.getTransportMeansInland().getTransportMode());
		}
		
	    if (meansBorder != null)  {
	    	tmpEam.setBfvkzg(meansBorder.getTransportMode());
	    	tmpEam.setBfartg(meansBorder.getTransportationType());
	    	tmpEam.setBfkzg(meansBorder.getTransportationNumber());
	    	tmpEam.setBfnatg(meansBorder.getTransportationCountry());
	    }
	    	
	    if (business != null) {
	    	tmpEam.setGesart(business.getBusinessTypeCode());  
	    	tmpEam.setGesprs(business.getInvoicePrice());  
	    	tmpEam.setGeswrg(business.getCurrency()); 		   
	    }
	    if (incoTerms != null) {
	    	tmpEam.setLibart(incoTerms.getIncoTerm()); 
	    	tmpEam.setLibinc(incoTerms.getText()); 		
	    	tmpEam.setLibort(incoTerms.getPlace()); 			
	    } 
	    return tmpEam;
	}


	private TsAVS setAvs(String beznr, String seal) {
		TsAVS tmpAvs = new TsAVS();
				
		if (Utils.isStringEmpty(beznr)) {
			return null;
		}
		if (Utils.isStringEmpty(seal)) {
			return null;	
		}

		tmpAvs.setBeznr(beznr);  				 
		tmpAvs.setSeal(seal);
	
		return tmpAvs;
	}
	
	private TsAPO setApo(MsgExpDatPos msgExpDatPos, String beznr) {
		TsAPO 				tmpApo 				= new TsAPO();
		CommodityCode		commodityCode 		= msgExpDatPos.getCommodityCode();
		ApprovedTreatment 	approvedTreatment 	= msgExpDatPos.getApprovedTreatment();
		Statistic			statistic			= msgExpDatPos.getStatistic();
		List <PreviousDocument> previousDocList	= msgExpDatPos.getPreviousDocumentList();
		PreviousDocument	previousDoc			= null;		
 	
		if (Utils.isStringEmpty(beznr) || msgExpDatPos == null) { 
			return null;
		}
		
		tmpApo.setBeznr(beznr);
		tmpApo.setPosnr(msgExpDatPos.getItemNumber());  		
		tmpApo.setArtnr(msgExpDatPos.getArticleNumber());
		if (commodityCode != null) {
			tmpApo.setTnr(commodityCode.getTarifCode());  
			tmpApo.setTnrtrc(commodityCode.getEUTarifCode());  
			tmpApo.setTnrzu1(commodityCode.getTarifAddition1());  
			tmpApo.setTnrzu2(commodityCode.getTarifAddition2());  
			tmpApo.setTnrnat(commodityCode.getAddition());  
		}			
		
		tmpApo.setWbsch(msgExpDatPos.getDescription());  
		tmpApo.setFregnr(msgExpDatPos.getUCROtherSystem());  
		tmpApo.setVerm(msgExpDatPos.getAnnotation());   
		tmpApo.setEigmas(msgExpDatPos.getNetMass());  
		tmpApo.setRohmas(msgExpDatPos.getGrossMass());  
		if (approvedTreatment != null) {
			tmpApo.setAnmvrf(approvedTreatment.getDeclared());  
			tmpApo.setPrevrf(approvedTreatment.getPrevious());  
			tmpApo.setNatvrf(approvedTreatment.getNational());  
		}
		tmpApo.setUbland(msgExpDatPos.getOriginFederalState());  
		if (statistic != null) {
			tmpApo.setWmahst(statistic.getAdditionalUnit());  
			tmpApo.setAhwert(statistic.getStatisticalValue()); 
		}		
		
		if (previousDocList != null)  {
			for (int i = 0, prevListSize = previousDocList.size(); i < prevListSize; i++) {
				previousDoc = previousDocList.get(i);
				if (previousDoc != null) {
					tmpApo.setVptyp(previousDoc.getType());
				}
			}
		}
			
		return tmpApo;
	}

	private TsEPO setEpo(Statistic statistic, String beznr, String posnr) {
		TsEPO 			tmpEpo 		= new TsEPO();		
		
		if (Utils.isStringEmpty(beznr) || Utils.isStringEmpty(posnr)) {
            return null;
		}
		if (statistic  == null) {
            return null;        
		}
		if (Utils.isStringEmpty(statistic.getValue()) && Utils.isStringEmpty(statistic.getCurrency())) {
            return null;    
		}

		tmpEpo.setBeznr(beznr);
		tmpEpo.setPosnr(posnr);		
		tmpEpo.setBasbtg(statistic.getValue());   
		tmpEpo.setBaswrg(statistic.getCurrency());
		
		return tmpEpo;
	}

	private TsAZV setAzv(PreviousDocument prevDoc, String beznr, String posnr) {
		
		TsAZV tsAZV = new TsAZV();
				
		if  (Utils.isStringEmpty(beznr) || Utils.isStringEmpty(posnr)) {
			return null;
		}
		if (prevDoc == null) {
			return null;
		}
		
		tsAZV.setBeznr(beznr);
		tsAZV.setPosnr(posnr);
		tsAZV.setAzvref(prevDoc.getMarks());
		tsAZV.setAzvzus(prevDoc.getAdditionalInformation());

		return tsAZV;
	}

	private TsAED setAed(Document doc, String beznr, String posnr) {
		
		TsAED tsAED = new TsAED();
				
		if (Utils.isStringEmpty(beznr) || Utils.isStringEmpty(posnr)) {
			return null;
		}
		if (doc == null) {
			return null;
		}
		
		tsAED.setBeznr(beznr);
		tsAED.setPosnr(posnr);
	
		tsAED.setUntqar(doc.getQualifier());
		tsAED.setUntart(doc.getTypeKids());
		tsAED.setUntnr(doc.getReference());
		tsAED.setUntzus(doc.getAdditionalInformation());
		tsAED.setUntdat(doc.getIssueDate());
		tsAED.setGbdat(doc.getExpirationDate());

		return tsAED;
	}
}
