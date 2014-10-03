package com.kewill.kcx.component.mapping.countries.de.ncts20.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransShipment;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingRemarks;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingRemarksPos;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVURPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVIR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVNE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVUZ;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVNV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUS;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUV;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: NCTS<br>
 * Created		: 07.02.2013
 * Description	: mapping of KIDS format of NCTSUnloadingRemarks to FSS format VUR.
 * 
 * @author	: iwaniuk
 * @version	: 4.1.00
 *
 */
public class MapNCTSUnloadingRemarksToVUR extends KidsMessage {
	
	private MsgNCTSUnloadingRemarks message;
	private MsgVUR msgVUR;
	private String subversion = "";      //EI20130425
	
	public MapNCTSUnloadingRemarksToVUR(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		message	= new MsgNCTSUnloadingRemarks(parser);
		msgVUR	= new MsgVUR();
		msgVUR.setVorSubset(tsvor);
	}
	public MapNCTSUnloadingRemarksToVUR(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		message	= new MsgNCTSUnloadingRemarks(parser);
		msgVUR	= new MsgVUR();
		msgVUR.setVorSubset(tsvor);
		msgVUR.setHeadSubset(head);
	}
	   
	public String getMessage() {
		String res	= "";
		
		try {
			message.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(message.getmRN());
			
			if (this.getKidsHeader() != null && !Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
				subversion = Utils.removeDots(this.getKidsHeader().getRelease());
			}
			
			// read MessageID from KidsHeader
			msgVUR.getVorSubset().setMsgid(getKidsHeader().getMessageID());
			
			// setting all Ts in MsgVUR
			msgVUR.setVurSubset(this.mapVur());
			msgVUR.setVneSubset(this.mapVne());  //V70 new: EORI-Wechsel - TODO-IWA: Mapping?
			msgVUR.setVuzSubset(this.mapVuz());
			msgVUR.setVirSubset(this.mapVir());  //V70 new: Ansprechpartner - TODO-IWA: Mapping?
			
			if (message.getListSealNumbers() != null) {				
				if (!message.getListSealNumbers().isEmpty()) {
					for (SealNumber sealNumbers : message.getListSealNumbers()) {
						if (sealNumbers != null) {
							msgVUR.addVuvSubsetList(this.mapVuv(sealNumbers.getNumber()));
						}
					}
				}
			}
			
			msgVUR.setVueSubset(this.mapVue());
			
			if (message.getEnRouteEvent() != null) {
				if (message.getEnRouteEvent().getSealsIdentityList() != null) {
					for (String sealsIdentity : message.getEnRouteEvent().getSealsIdentityList()) {
						msgVUR.addVnvSubsetList(this.mapVnv(sealsIdentity));
					}
				}
				
				if (message.getEnRouteEvent().getTransShipment() != null) {
					if (message.getEnRouteEvent().getTransShipment().getContainerNumberList() != null) {
						for (String containerNo : 
								message.getEnRouteEvent().getTransShipment().getContainerNumberList()) {
							msgVUR.addVuoSubsetList(this.mapVuo(containerNo));
						}
					}
				}
			}
			
			//EI20110608: msgVUR.setVupSubset(setVup());
			if (message.getGoodsItemList() != null) {								
				for (MsgNCTSUnloadingRemarksPos item : message.getGoodsItemList()) {
					if (item != null) {
						msgVUR.addPosList(getPos(item));						
					}
				}
			}					
						
			res = msgVUR.getFssString("HEAD");
			
			Utils.log("(MapNCTSUnloadingRemarksToVUR getMessage) Msg = " + res);
		} catch (FssException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	private TsVUR mapVur() {
		TsVUR vurSubset	= new TsVUR();
		
		vurSubset.setMrn(message.getmRN());
		if (message.getUnloadingRemark() != null) { 
			//vurSubset.setKzkonf(message.getUnloadingRemark().getConform());
			//vurSubset.setKzvsok(message.getUnloadingRemark().getStateOfSealsOk());
			if (message.getUnloadingRemark().getConform() != null) {
				if (message.getUnloadingRemark().getConform().equalsIgnoreCase("true") ||
						message.getUnloadingRemark().getConform().equals("1")) { //EI20110525
					vurSubset.setKzkonf("1");
				} else {
					vurSubset.setKzkonf("0");
				}
			} else {
				vurSubset.setKzkonf("0");   //EI20110527
			}
			if (message.getUnloadingRemark().getStateOfSealsOk() != null) {
				if (message.getUnloadingRemark().getStateOfSealsOk().equalsIgnoreCase("true") ||
						message.getUnloadingRemark().getStateOfSealsOk().equals("1")) {
					vurSubset.setKzvsok("1");
				} else {
					vurSubset.setKzvsok("0");
				}
			} else {
				vurSubset.setKzvsok("0");   
			}
			
			//V70 new vurSubset.setSb(???)
		}
		return vurSubset;
	}
	
	private TsVNE mapVne() {
		TsVNE subset = new TsVNE();
		
		 //TODO
		
		return subset;
	}
	private TsVIR mapVir() {
		TsVIR subset = new TsVIR();
		
		 //TODO
		
		return subset;
	}
	
	private TsVUZ mapVuz() {
		TsVUZ vuzSubset	= new TsVUZ();
		
		vuzSubset.setMrn(message.getmRN());
		vuzSubset.setAnzve(message.getTotalNumberOfSeals());
		
		if (message.getTransportAtDeparture() != null) {
			vuzSubset.setBfabkz(message.getTransportAtDeparture().getIdentity());
			vuzSubset.setBfabld(message.getTransportAtDeparture().getsetNationality());
		}
		
		if (message.getResultsOfControl() != null) {
			vuzSubset.setUnstm(message.getResultsOfControl().getDescription());
		}
		
		if (message.getUnloadingRemark() != null) {
			vuzSubset.setErlvm(message.getUnloadingRemark().getUnloadingRemark());
		}
		
		if (message.getTotalGrossMass() != null) {
			vuzSubset.setRohm(Utils.addZabisDecimalPlaceV7(message.getTotalGrossMass(), 3)); //V70 Kids as decimal);   		}
			if (subversion.equals("4101")) {     //EI20130425
				vuzSubset.setRohm(message.getTotalGrossMass());
			}
		}
		
		return vuzSubset;
	}
	
	private TsVUV mapVuv(String sealsIdentity) {
		TsVUV vuvSubset	= new TsVUV();
		
		vuvSubset.setMrn(message.getmRN());
		vuvSubset.setSeal(sealsIdentity);
		
		return vuvSubset;
	}
	
	private TsVUE mapVue() {
		TsVUE vueSubset	= new TsVUE();
		
		vueSubset.setMrn(message.getmRN());
		
		if (message.getEnRouteEvent() != null) {
			
			EnRouteEvent enRouteEvent = message.getEnRouteEvent();
			
			Incident incident  = enRouteEvent.getIncident();
			if (incident != null) {
				vueSubset.setKzvorf(incident.getIncidentFlag());
				vueSubset.setVrfamt(incident.getEndorsementAuthority());
				vueSubset.setVrfort(incident.getEndorsementPlace());
				vueSubset.setVrfld(incident.getEndorsementCountry());
				vueSubset.setVrfdat(incident.getEndorsementDate());
				vueSubset.setVrfmsn(incident.getIncidentInfo());
			}
						
			TransShipment transShipment = enRouteEvent.getTransShipment();
			if (transShipment != null) {
				vueSubset.setKzuml("1");	
				vueSubset.setNbfld(transShipment.getNewTransportCountry());
				vueSubset.setNbfkz(transShipment.getNewTransportId());	
				
				vueSubset.setUmlamt(transShipment.getEndorsementAuthority());
				vueSubset.setUmlort(transShipment.getEndorsementPlace());
				vueSubset.setUmlld(transShipment.getEndorsementCountry());
				vueSubset.setUmldat(transShipment.getEndorsementDate());
			}
			
			//vueSubset.setErgamt(EnRouteEvent.EndorsementAuthority);// no EndorsementAuthority
			vueSubset.setErgort(enRouteEvent.getPlace());	
			vueSubset.setErgld(enRouteEvent.getCountryCode());	
			//vueSubset.setErgdat(EnRouteEvent.EndorsementDate);	// no EndorsementDate						
		}
		
		return vueSubset;
	}
	
	private TsVNV mapVnv(String sealsIdentity) {
		TsVNV vnvSubset	= new TsVNV();
		
		vnvSubset.setMrn(message.getmRN());
		vnvSubset.setSeal(sealsIdentity);
		
		return vnvSubset;
	}
	
	private TsVUO mapVuo(String containerNo) {
		TsVUO vuoSubset	= new TsVUO();
		
		vuoSubset.setMrn(message.getmRN());
		//vuoSubset.setPosnr(0);
		vuoSubset.setConnr(containerNo);
		
		return vuoSubset;
	}
	
	/////////
	private MsgVURPos getPos(MsgNCTSUnloadingRemarksPos item) {		
		if (item == null) {
			return null;
		}
		MsgVURPos pos = new MsgVURPos();
		
		pos.setVupSubset(this.mapVup(item));
		
		if (item.getContainer() != null) {
			if (item.getContainer().getNumberList() != null) {
				for (String containerNo : item.getContainer().getNumberList()) {
					pos.addVucSubsetList(this.mapVuc(item.getItemNumber(), containerNo));
				}
			}
		}		
		if (item.getListPackages() != null) {
			for (Packages packages : item.getListPackages()) {
				pos.addVulSubsetList(this.mapVul(item.getItemNumber(), packages));
			}
		}
			
		if (item.getSensitiveGoodsList() != null) {
			for (SensitiveGoods sGoods : item.getSensitiveGoodsList()) {
				pos.addVusSubsetList(mapVus(item.getItemNumber(), sGoods));				
			}
		}		
		//not mapped: pos.setVuaSubset(getVua(item));
		
		return pos;
	}
		
	private TsVUP mapVup(MsgNCTSUnloadingRemarksPos item) {	
		if (item == null) {
			return null;
		}		
		TsVUP vupSubset	= new TsVUP();		
		vupSubset.setMrn(message.getmRN());		
		vupSubset.setPosnr(item.getItemNumber());			
		if (item.getCommodityCode() != null) {
			vupSubset.setTnr(item.getCommodityCode().getTarifCode());
		}			
		vupSubset.setWabsch(item.getGoodsDescription());		
		vupSubset.setRohmas(Utils.addZabisDecimalPlaceV7(item.getGrossMass(), 3)); //V70 Kids as decimal);  		
		vupSubset.setEigmas(Utils.addZabisDecimalPlaceV7(item.getNetMass(), 3)); //V70 Kids as decimal);
		if (subversion.equals("4101")) {     //EI20130425
			vupSubset.setRohmas(item.getGrossMass());
			vupSubset.setEigmas(item.getNetMass());
		}
		vupSubset.setPfehlt(item.getMissingLineFlag());		
		if (item.getResultsOfControl() != null) {
			vupSubset.setUnstm(item.getResultsOfControl().getDescription());
		}	
		
		return vupSubset;
	}
	
	private TsVUC mapVuc(String posnr, String containerNo) {	
		if (containerNo == null) {
			return null;
		}
		if (posnr == null) {
			return null;
		}
		TsVUC vucSubset	= new TsVUC();
		
		vucSubset.setMrn(message.getmRN());				
		vucSubset.setPosnr(posnr);		
		vucSubset.setConnr(containerNo);
		
		return vucSubset;
	}
	
	private TsVUL mapVul(String posnr, Packages packages) {
		if (packages == null) {
			return null;
		}
		if (posnr == null) {
			return null;
		}
		TsVUL vulSubset	= new TsVUL();
		
		vulSubset.setMrn(message.getmRN());		
		vulSubset.setPosnr(posnr);		
		vulSubset.setColan(packages.getQuantity());
		vulSubset.setColar(packages.getType());
		vulSubset.setColzc(packages.getMarks());
		
		return vulSubset;
	}
	
	private TsVUS mapVus(String posnr, SensitiveGoods sGoods) {
		if (sGoods == null) {
			return null;
		}
		if (posnr == null) {
			return null;
		}
		TsVUS vusSubset	= new TsVUS();
		
		vusSubset.setMrn(message.getmRN());		
		vusSubset.setPosnr(posnr);		
		vusSubset.setSgicod(sGoods.getType());	
		vusSubset.setSgimng(Utils.addZabisDecimalPlaceV7(sGoods.getWeight(), 3)); //V70 Kids as decimal);
		if (subversion.equals("4101")) {     //EI20130425
			vusSubset.setSgimng(sGoods.getWeight());
		}
		
		return vusSubset;
	}
		
}
