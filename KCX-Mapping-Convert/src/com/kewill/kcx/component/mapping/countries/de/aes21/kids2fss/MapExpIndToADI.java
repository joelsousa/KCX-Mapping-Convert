package com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADI;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADIPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module    	: Export/aes. 
 * Created    	: 24.07.2012
 * Description  : Mapping of V21 KidsMessage PreNotification to ZABIS FSS-Format.
 * 				: ZABIS FSS-Format Version 7.0.
 * 
 * @author      : iwaniuk
 * @version     : 2.1.00
 */    

public class MapExpIndToADI extends KidsMessage {
	
	private MsgExpDat msgExpDat;
	private MsgADI msgADI;
	private String subversion = "";   //EI20120422
	
	public MapExpIndToADI(XMLEventReader parser) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		msgADI = new MsgADI("");
	}
	public MapExpIndToADI(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		msgADI = new MsgADI("");
		msgADI.setVorSubset(tsvor);
	}
	public MapExpIndToADI(XMLEventReader parser, TsVOR vor, TsHead head) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		msgADI = new MsgADI("");
		msgADI.setVorSubset(vor);
		msgADI.setHeadSubset(head);
	}
	
	public String getMessage() {
		String res = "";
		String beznr = msgExpDat.getReferenceNumber();
    	
        try {        
        	//msgADI = new MsgADI("data/fss/out/MapExpIndToADI.dat");
            msgExpDat.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());
              
            if (this.getKidsHeader() != null && !Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
            	subversion = Utils.removeDots(this.getKidsHeader().getRelease());
			}
            // read MessageID from KidsHeader 
            msgADI.getVorSubset().setMsgid(getKidsHeader().getMessageID());             
            //füllen der übrigen ADI-Elemente:                      
         	msgADI.setDatSubset(mapKidsToDat());            
        	msgADI.setEdaSubset(mapKidsToEda());
        	msgADI.setAtkSubset(mapKidsToAtk()); 
        	        	
            msgADI.setConsignor(msgExpDat.getConsignor(), beznr, "0");
            msgADI.setConsignee(msgExpDat.getConsignee(), beznr, "0");
            msgADI.setDeclarant(msgExpDat.getDeclarant(), beznr, "0");
            msgADI.setAgent(msgExpDat.getAgent(), beznr, "0");
            msgADI.setSubcontractor(msgExpDat.getSubcontractor(), beznr, "0");           
                        
            if (msgExpDat.getGoodsItemList() != null) {              
    			for (MsgExpDatPos item : msgExpDat.getGoodsItemList()) {			    			
    				MsgADIPos pos = new MsgADIPos();   
    				String posnr = item.getItemNumber();
    			
    				pos.setApoSubset(mapKidsToApo(item, beznr));
    				if (item.getExportRefundItem() != null) {
    					pos.setAtpSubset(mapKidsToAtp(item.getExportRefundItem(), beznr, posnr));
    				
    					if (item.getExportRefundItem().getIngredientsList() != null) {
        				List <TsATI> tmpATIList = new Vector<TsATI>();
        				for (Ingredients ingr : item.getExportRefundItem().getIngredientsList()) {	       				  			
        					tmpATIList.add(mapKidsToAti(ingr, beznr, posnr));
        				}
        				pos.setAtiList(tmpATIList);
    					} 
    				}
    				pos.setConsignee(item.getConsignee(), beznr, item.getItemNumber());
    				if (item.getPackagesList() != null) {
        				List <TsACO>tmpACOList = new Vector<TsACO>();
        				for (Packages pack : item.getPackagesList()) {	       				  			
        					tmpACOList.add(mapKidsToAco(pack, beznr, posnr));
        				}
        				pos.setAcoList(tmpACOList);
        			}
    			
    				if (item.getDocumentList() != null) {
        				List <TsAED>tmpAEDList = new Vector<TsAED>();
        				for (DocumentV20 doc : item.getDocumentList()) {	       				  			
        					tmpAEDList.add(mapKidsToAed(doc, beznr, posnr));
        				}
        				pos.setAedList(tmpAEDList);
        			}
    			
    			
    				msgADI.addPosList(pos);    		
    			}   		                      
            }
            
            //res = msgADI.getFssString();
            /* EI20140206
            if (this.writeHead()) { 				//EI20130213
            	res = msgADI.getFssString("HEAD");
            } else {
            	res = msgADI.getFssString();
            }  
            */
            res = msgADI.getFssString("HEAD");  //EI20130213
           
            Utils.log("(MapExpIndToADI getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	    //return res.toString();
	}
	
	private TsDAT mapKidsToDat() {
		TsDAT datSubset = new TsDAT();
		
		datSubset.setBeznr(msgExpDat.getReferenceNumber());
		datSubset.setKuatnr(msgExpDat.getOrderNumber());	
		datSubset.setExpdst(msgExpDat.getCustomsOfficeExport());
		
		if (msgExpDat.getContact() != null) {
            datSubset.setSb(msgExpDat.getContact().getIdentity()); 
		}		
		
		return datSubset;
	}
	
	private TsEDA mapKidsToEda() {
		TsEDA edaSubset = new TsEDA();
		
		edaSubset.setBeznr(msgExpDat.getReferenceNumber());     
		edaSubset.setBewnr(msgExpDat.getAuthorizationNumber()); 
		edaSubset.setArtueb(msgExpDat.getTypeOfPermit());          
		edaSubset.setArtvfr(msgExpDat.getProcedure());                 //new for V21
		
		edaSubset.setInddat(msgExpDat.getAdvanceNoticeTime()); 	
		edaSubset.setVerm(msgExpDat.getAnnotation()); 	       
		edaSubset.setKzanau(msgExpDat.getDeclarantIsConsignor()); 
		
		if (msgExpDat.getLoadingTime() != null) {                                 
			edaSubset.setGststr(msgExpDat.getLoadingTime().getBeginTime()); 
			edaSubset.setGstend(msgExpDat.getLoadingTime().getEndTime()); 
		 }		
		
		if (msgExpDat.getPlaceOfLoading() != null) {                                 
			edaSubset.setLdocde(msgExpDat.getPlaceOfLoading().getCode());  
			edaSubset.setBeostr(msgExpDat.getPlaceOfLoading().getStreet()); 
			edaSubset.setBeoort(msgExpDat.getPlaceOfLoading().getCity());  
			edaSubset.setBeoplz(msgExpDat.getPlaceOfLoading().getPostalCode()); 
			edaSubset.setBeozus(msgExpDat.getPlaceOfLoading().getAgreedLocationOfGoods());
		}		
		
		if (msgExpDat.getTransportMeansDeparture() != null) {    //new for V21
			edaSubset.setBfarta(msgExpDat.getTransportMeansDeparture().getTransportationType());
		}
		
		return edaSubset;
	}
	
	private TsATK mapKidsToAtk() {
		if (msgExpDat.getExportRefundHeader() == null) {
			return null;
		}
		TsATK atkSubset = new TsATK();
		
		atkSubset.setBeznr(msgExpDat.getReferenceNumber());			
		atkSubset.setAsldbe(msgExpDat.getExportRefundHeader().getDestinationCountry());
			//new for V21 begin
		atkSubset.setText(msgExpDat.getExportRefundHeader().getText());   
		atkSubset.setAsart(msgExpDat.getExportRefundHeader().getPaymentType());
		atkSubset.setAszweg(msgExpDat.getExportRefundHeader().getBankNumber());
		atkSubset.setAszsbv(msgExpDat.getExportRefundHeader().getAssignee());
		atkSubset.setAsskto(msgExpDat.getExportRefundHeader().getGuarantee());
		atkSubset.setAskvb(msgExpDat.getExportRefundHeader().getReservationCode());
			//new for V21 end
		
		return atkSubset;
	}
	private TsAPO mapKidsToApo(MsgExpDatPos item, String beznr) {
		TsAPO apoSubset = new TsAPO();
		
		apoSubset.setBeznr(beznr);
		apoSubset.setPosnr(item.getItemNumber());  		
		apoSubset.setArtnr(item.getArticleNumber());
		apoSubset.setWbsch(item.getDescription()); //new for V21
		if (item.getCommodityCode() != null) {
			apoSubset.setTnr(item.getCommodityCode().getTarifCode());  
			apoSubset.setTnrtrc(item.getCommodityCode().getEUTarifCode());  
			apoSubset.setTnrzu1(item.getCommodityCode().getTarifAddition1());  
			apoSubset.setTnrzu2(item.getCommodityCode().getTarifAddition2());  
			apoSubset.setTnrnat(item.getCommodityCode().getAddition());  
		}			
		
		return apoSubset;
	}

	private TsATP mapKidsToAtp(ExportRefundItem refundItem, String beznr, String posnr) {	
		if (refundItem == null) {
			return null;
		}
		TsATP atpSubset = new TsATP();
		
		atpSubset.setBeznr(beznr);
		atpSubset.setPosnr(posnr);  		
		atpSubset.setMeaest(refundItem.getUnitOfMeasurement());   
		//EI20130423: atpSubset.setMenge(refundItem.getAmount());      //!!!in Zabis ist n..9,1  
		atpSubset.setMenge(Utils.addZabisDecimalPlaceV7(refundItem.getAmount(), 1)); //EI20130423
		//new for V21 begin
		atpSubset.setWberg1(refundItem.getAddition1());
		atpSubset.setWberg2(refundItem.getAddition2());
		atpSubset.setUland(refundItem.getOriginCountry());
		atpSubset.setKzwert(refundItem.getAmountCode());
		atpSubset.setZfnai(refundItem.getTypeOfRefund());
		//new for V21 end
		// CK120824 begin
		atpSubset.setAnwrta(Utils.addZabisDecimalPlaceV7(refundItem.getPartA(), 2));
		atpSubset.setAnwrtb(Utils.addZabisDecimalPlaceV7(refundItem.getPartB(), 2));
		atpSubset.setAnwrtc(Utils.addZabisDecimalPlaceV7(refundItem.getPartC(), 2));
		atpSubset.setAnwrtd(Utils.addZabisDecimalPlaceV7(refundItem.getPartD(), 2));		
		atpSubset.setApgket(Utils.addZabisDecimalPlaceV7(refundItem.getAmountCoefficient(), 4));
		// CK120824 end
		if (subversion.equals("2101")) {     //EI20130422
			atpSubset.setMenge(refundItem.getAmount());
			atpSubset.setAnwrta(refundItem.getPartA());
			atpSubset.setAnwrtb(refundItem.getPartB());
			atpSubset.setAnwrtc(refundItem.getPartC());
			atpSubset.setAnwrtd(refundItem.getPartD());		
			atpSubset.setApgket(refundItem.getAmountCoefficient());
		}
		return atpSubset;
	}
	
	private TsATI mapKidsToAti(Ingredients ingredients, String beznr, String posnr) {	//new for V21
		if (ingredients == null) {
			return null;
		}
		TsATI atiSubset = new TsATI();
		
		atiSubset.setBeznr(beznr);
		atiSubset.setPosnr(posnr);  
		atiSubset.setLfdnr(ingredients.getSequentialNumber());
		// CK120824
		// atiSubset.setUrfkt1(ingredients.getAmount1());
		atiSubset.setUrfkt1(Utils.addZabisDecimalPlaceV7(ingredients.getAmount1(), 4));
		// CK120824
		// atiSubset.setUrfkt2(ingredients.getAmount2()); 	
		atiSubset.setUrfkt2(Utils.addZabisDecimalPlaceV7(ingredients.getAmount2(), 4));
		atiSubset.setKzfkt1(ingredients.getKindOfCoefficient());
		// CK120824
		// atiSubset.setUrfkts(ingredients.getRate());
		atiSubset.setUrfkts(Utils.addZabisDecimalPlaceV7(ingredients.getRate(), 4));
		// CK120824
		// atiSubset.setGhtant(ingredients.getWeightPercent());
		atiSubset.setGhtant(Utils.addZabisDecimalPlaceV7(ingredients.getWeightPercent(), 3));
		// CK120824
		// atiSubset.setMgeant(ingredients.getWeight());
		atiSubset.setMgeant(Utils.addZabisDecimalPlaceV7(ingredients.getWeight(), 1));
		atiSubset.setHeklnr(ingredients.getUniqueFactoryNumber());
		atiSubset.setKeynr(ingredients.getTarifNumber()); 
		atiSubset.setLiznr(ingredients.getLicenceNumber());
		atiSubset.setText(ingredients.getText());
		if (subversion.equals("2101")) {     //EI20130422
			 atiSubset.setUrfkt1(ingredients.getAmount1());
			 atiSubset.setUrfkt2(ingredients.getAmount2()); 
			 atiSubset.setUrfkts(ingredients.getRate());
			 atiSubset.setGhtant(ingredients.getWeightPercent());
			 atiSubset.setMgeant(ingredients.getWeight());
		}
		
		return atiSubset;
	}
	
	private TsACO mapKidsToAco(Packages packages, String beznr, String posnr) {
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
	
	private TsAED mapKidsToAed(DocumentV20 doc, String beznr, String posnr) {
		if (doc == null) {
		    return null;
		}
		TsAED aedSubset = new TsAED();
		
		aedSubset.setBeznr(beznr);  				 
		aedSubset.setPosnr(posnr);  
		aedSubset.setUntqar(doc.getQualifier()); 
		aedSubset.setUntart(doc.getType());
		aedSubset.setUntnr(doc.getReference());
		aedSubset.setUntzus(doc.getAdditionalInformation());
		aedSubset.setDetail(doc.getDetail());
		aedSubset.setUntdat(doc.getIssueDate());
		aedSubset.setGbdat(doc.getExpirationDate());
		aedSubset.setWert(doc.getValue());
		if (doc.getWriteDownAmount() != null) {
			aedSubset.setMgeme(doc.getWriteDownAmount().getUnitOfMeasurement());
			// CK120824
			// keine virtuellen Kommas sondern ein echtes - siehe Zabis-Docs
			aedSubset.setAbgwrt(doc.getWriteDownAmount().getValue().replace('.', ',')); 
		}
		
		return aedSubset;
	}
}
