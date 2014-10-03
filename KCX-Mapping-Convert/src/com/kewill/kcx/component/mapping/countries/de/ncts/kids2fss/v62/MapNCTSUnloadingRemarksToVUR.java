package com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarks;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransShipment;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVURPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVNV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUS;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUZ;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSUnloadingRemarksToVUR<br>
 * Created		: 07.09.2010
 * Description	: mapping of KIDS format of NCTSUnloadingRemarks to FSS format VUR.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class MapNCTSUnloadingRemarksToVUR extends KidsMessage {
	
	private MsgNCTSUnloadingRemarks msgNCTSUnloadingRemarks;
	private MsgVUR msgVUR;
	
	public MapNCTSUnloadingRemarksToVUR(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgNCTSUnloadingRemarks	= new MsgNCTSUnloadingRemarks(parser);
		msgVUR	= new MsgVUR();
		msgVUR.setVorSubset(tsvor);
	}

	public String getMessage() {
		String res	= "";
		
		try {
			msgNCTSUnloadingRemarks.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(msgNCTSUnloadingRemarks.getmRN());
			
			// read MessageID from KidsHeader
			msgVUR.getVorSubset().setMsgid(getKidsHeader().getMessageID());
			
			// setting all Ts in MsgVUR
			msgVUR.setVurSubset(getVur());
			msgVUR.setVuzSubset(getVuz());
			
			if (msgNCTSUnloadingRemarks.getListSealNumbers() != null) {				
				if (!msgNCTSUnloadingRemarks.getListSealNumbers().isEmpty()) {
					for (SealNumber sealNumbers : msgNCTSUnloadingRemarks.getListSealNumbers()) {
						if (sealNumbers != null) {
							msgVUR.addVuvSubsetList(getVuv(sealNumbers.getNumber()));
						}
					}
				}
			}
			
			msgVUR.setVueSubset(getVue());
			
			if (msgNCTSUnloadingRemarks.getEnRouteEvent() != null) {
				if (msgNCTSUnloadingRemarks.getEnRouteEvent().getSealsIdentityList() != null) {
					for (String sealsIdentity : msgNCTSUnloadingRemarks.getEnRouteEvent().getSealsIdentityList()) {
						msgVUR.addVnvSubsetList(getVnv(sealsIdentity));
					}
				}
				
				if (msgNCTSUnloadingRemarks.getEnRouteEvent().getTransShipment() != null) {
					if (msgNCTSUnloadingRemarks.getEnRouteEvent().getTransShipment().getContainerNumberList() != null) {
						for (String containerNo : 
								msgNCTSUnloadingRemarks.getEnRouteEvent().getTransShipment().getContainerNumberList()) {
							msgVUR.addVuoSubsetList(getVuo(containerNo));
						}
					}
				}
			}
			
			//EI20110608: msgVUR.setVupSubset(setVup());
			if (msgNCTSUnloadingRemarks.getGoodsItemList() != null) {								
				for (MsgNCTSUnloadingRemarksPos item : msgNCTSUnloadingRemarks.getGoodsItemList()) {
					if (item != null) {
						msgVUR.addPosList(getPos(item));						
					}
				}
			}					
			
			res	= msgVUR.getFssString();
			
			Utils.log("(MapNCTSUnloadingRemarksToVUR getMessage) Msg = " + res);
		} catch (FssException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	private TsVUR getVur() {
		TsVUR vurSubset	= new TsVUR();
		
		vurSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());
		if (msgNCTSUnloadingRemarks.getUnloadingRemark() != null) { 
			//vurSubset.setKzkonf(msgNCTSUnloadingRemarks.getUnloadingRemark().getConform());
			//vurSubset.setKzvsok(msgNCTSUnloadingRemarks.getUnloadingRemark().getStateOfSealsOk());
			if (msgNCTSUnloadingRemarks.getUnloadingRemark().getConform() != null) {
				if (msgNCTSUnloadingRemarks.getUnloadingRemark().getConform().equalsIgnoreCase("true") ||
						msgNCTSUnloadingRemarks.getUnloadingRemark().getConform().equals("1")) { //EI20110525
					vurSubset.setKzkonf("1");
				} else {
					vurSubset.setKzkonf("0");
				}
			} else {
				vurSubset.setKzkonf("0");   //EI20110527
			}
			if (msgNCTSUnloadingRemarks.getUnloadingRemark().getStateOfSealsOk() != null) {
				if (msgNCTSUnloadingRemarks.getUnloadingRemark().getStateOfSealsOk().equalsIgnoreCase("true") ||
						msgNCTSUnloadingRemarks.getUnloadingRemark().getStateOfSealsOk().equals("1")) {
					vurSubset.setKzvsok("1");
				} else {
					vurSubset.setKzvsok("0");
				}
			} else {
				vurSubset.setKzvsok("0");   
			}
		}
		return vurSubset;
	}
	
	private TsVUZ getVuz() {
		TsVUZ vuzSubset	= new TsVUZ();
		
		vuzSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());
		vuzSubset.setAnzve(msgNCTSUnloadingRemarks.getTotalNumberOfSeals());
		
		if (msgNCTSUnloadingRemarks.getTransportAtDeparture() != null) {
			vuzSubset.setBfabkz(msgNCTSUnloadingRemarks.getTransportAtDeparture().getIdentity());
			vuzSubset.setBfabld(msgNCTSUnloadingRemarks.getTransportAtDeparture().getsetNationality());
		}
		
		if (msgNCTSUnloadingRemarks.getResultsOfControl() != null) {
			vuzSubset.setUnstm(msgNCTSUnloadingRemarks.getResultsOfControl().getDescription());
		}
		
		if (msgNCTSUnloadingRemarks.getUnloadingRemark() != null) {
			vuzSubset.setErlvm(msgNCTSUnloadingRemarks.getUnloadingRemark().getUnloadingRemark());
		}
		
		return vuzSubset;
	}
	
	private TsVUV getVuv(String sealsIdentity) {
		TsVUV vuvSubset	= new TsVUV();
		
		vuvSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());
		vuvSubset.setSeal(sealsIdentity);
		
		return vuvSubset;
	}
	
	private TsVUE getVue() {
		TsVUE vueSubset	= new TsVUE();
		
		vueSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());
		
		if (msgNCTSUnloadingRemarks.getEnRouteEvent() != null) {
			
			EnRouteEvent enRouteEvent = msgNCTSUnloadingRemarks.getEnRouteEvent();
			
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
	
	private TsVNV getVnv(String sealsIdentity) {
		TsVNV vnvSubset	= new TsVNV();
		
		vnvSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());
		vnvSubset.setSeal(sealsIdentity);
		
		return vnvSubset;
	}
	
	private TsVUO getVuo(String containerNo) {
		TsVUO vuoSubset	= new TsVUO();
		
		vuoSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());
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
		
		pos.setVupSubset(getVup(item));
		
		if (item.getContainer() != null) {
			if (item.getContainer().getNumberList() != null) {
				for (String containerNo : item.getContainer().getNumberList()) {
					pos.addVucSubsetList(getVuc(item.getItemNumber(), containerNo));
				}
			}
		}		
		if (item.getListPackages() != null) {
			for (Packages packages : item.getListPackages()) {
				pos.addVulSubsetList(getVul(item.getItemNumber(), packages));
			}
		}
			
		if (item.getSensitiveGoodsList() != null) {
			for (SensitiveGoods sGoods : item.getSensitiveGoodsList()) {
				pos.addVusSubsetList(getVus(item.getItemNumber(), sGoods));				
			}
		}		
		//not mapped: pos.setVuaSubset(getVua(item));
		
		return pos;
	}
		
	private TsVUP getVup(MsgNCTSUnloadingRemarksPos item) {	
		if (item == null) {
			return null;
		}		
		TsVUP vupSubset	= new TsVUP();		
		vupSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());		
		vupSubset.setPosnr(item.getItemNumber());			
		if (item.getCommodityCode() != null) {
			vupSubset.setTnr(item.getCommodityCode().getTarifCode());
		}			
		vupSubset.setWabsch(item.getGoodsDescription());
		vupSubset.setRohmas(item.getGrossMass());
		vupSubset.setEigmas(item.getNetMass());
		vupSubset.setPfehlt(item.getMissingLineFlag());		
		if (item.getResultsOfControl() != null) {
			vupSubset.setUnstm(item.getResultsOfControl().getDescription());
		}	
		
		return vupSubset;
	}
	
	private TsVUC getVuc(String posnr, String containerNo) {	
		if (containerNo == null) {
			return null;
		}
		if (posnr == null) {
			return null;
		}
		TsVUC vucSubset	= new TsVUC();
		
		vucSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());				
		vucSubset.setPosnr(posnr);		
		vucSubset.setConnr(containerNo);
		
		return vucSubset;
	}
	
	private TsVUL getVul(String posnr, Packages packages) {
		if (packages == null) {
			return null;
		}
		if (posnr == null) {
			return null;
		}
		TsVUL vulSubset	= new TsVUL();
		
		vulSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());		
		vulSubset.setPosnr(posnr);		
		vulSubset.setColan(packages.getQuantity());
		vulSubset.setColar(packages.getType());
		vulSubset.setColzc(packages.getMarks());
		
		return vulSubset;
	}
	
	private TsVUS getVus(String posnr, SensitiveGoods sGoods) {
		if (sGoods == null) {
			return null;
		}
		if (posnr == null) {
			return null;
		}
		TsVUS vusSubset	= new TsVUS();
		
		vusSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());		
		vusSubset.setPosnr(posnr);		
		vusSubset.setSgicod(sGoods.getType());
		vusSubset.setSgimng(sGoods.getWeight());
		
		return vusSubset;
	}
	
	private TsVUA getVua(MsgNCTSUnloadingRemarksPos item) {
		if (item == null) {
			return null;
		}
		TsVUA vuaSubset	= new TsVUA();
		
		vuaSubset.setMrn(msgNCTSUnloadingRemarks.getmRN());		
		vuaSubset.setPosnr(item.getItemNumber());		
		//vuaSubset.setAwbzzz(NCTSArrival.ApplPosExtensions.AWBNumber);			---> not mapped
		//vuaSubset.setSuapos(NCTSArrival.ApplPosExtensions.PosNumber);			---> not mapped
		
		return vuaSubset;
	}

}
