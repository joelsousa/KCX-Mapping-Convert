package com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarks;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.ResultsOfControl;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransShipment;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.UnloadingRemark;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVURPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVNV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUS;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUZ;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSUnloadingRemarksKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: MapVURToNCTSUnloadingRemarks<br>
 * Created		: 09.07.2010<br>
 * Description	: Mapping of FSS to KIDS format of VUR.
 * 
 * @author Jude Eco
 * @version 6.2.00
 *
 */
public class MapVURToNCTSUnloadingRemarks extends KidsMessage {
	private MsgVUR msgVUR;
	private MsgNCTSUnloadingRemarks msgNCTSUnloadingRemarks;
	
	
	public MapVURToNCTSUnloadingRemarks() {
		msgVUR = new MsgVUR();
		msgNCTSUnloadingRemarks = new MsgNCTSUnloadingRemarks();
	}
	
	public void setMsgVUR(MsgVUR argument) {
		if (argument == null) {
			return;
		}
		this.msgVUR = argument;
		this.setMsgFields();
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			KidsHeader header = new KidsHeader(writer);
			header.setHeaderFields(msgVUR.getVorSubset());
			header.setMessageName("UnloadingRemarks");
			header.setMessageID(getMsgID());
			CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
	        header.writeHeader();
	        
	        BodyNCTSUnloadingRemarksKids body = new BodyNCTSUnloadingRemarksKids(writer);
	        body.setMessage(msgNCTSUnloadingRemarks);
	        
	        getCommonFieldsDTO().setReferenceNumber(msgNCTSUnloadingRemarks.getmRN());
	        body.writeBody();
	        
	        closeElement();	//soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	        	Utils.log("(MapVURToNCTSUnloadingRemarks getMessage) Msg = " + xmlOutputString.toString());
	        }
	} catch (XMLStreamException e) {
		e.printStackTrace();
	}
	return xmlOutputString.toString();
	}
	
	public void setMsgFields() {
		if (msgVUR == null) {
			return;
		}
		setUnloadingRemark(msgVUR.getVurSubset(), msgVUR.getVuzSubset());
		setMeansOfTransportDeparture(msgVUR.getVuzSubset());
		setSealNumber(msgVUR.getVuvSubsetList());
		setEnRouteEvent(msgVUR);
		//EI:setGoodsItem(msgVUR);
		setGoodsItemList(msgVUR.getPostList());  //EI20110608
			
	}
	
	private void setUnloadingRemark(TsVUR tsVUR, TsVUZ tsVUZ) {
		if (tsVUR == null) {
			return;
		}
		if (!tsVUR.isEmpty()) {
			msgNCTSUnloadingRemarks.setmRN(tsVUR.getMrn());
			UnloadingRemark unloadingRemark = new UnloadingRemark();
			unloadingRemark.setConform(tsVUR.getKzkonf());
			unloadingRemark.setStateOfSealsOk(tsVUR.getKzvsok());
			if (tsVUZ != null) {
				unloadingRemark.setUnloadingRemark(tsVUZ.getErlvm());
			}
			msgNCTSUnloadingRemarks.setUnloadingRemark(unloadingRemark);	
		}		
	}
			
	private void setMeansOfTransportDeparture(TsVUZ tsVUZ) {
		if (tsVUZ == null) {
			return;
		}
		if (!tsVUZ.isEmpty()) {
			msgNCTSUnloadingRemarks.setTotalNumberOfSeals(tsVUZ.getAnzve());
			Transport transport = new Transport();
			transport.setIdentity(tsVUZ.getBfabkz());
			transport.setNationality(tsVUZ.getBfabld());
			msgNCTSUnloadingRemarks.setTransportAtDeparture(transport);
			ResultsOfControl resultsOfControl = new ResultsOfControl();
			resultsOfControl.setDescription(tsVUZ.getUnstm());
			msgNCTSUnloadingRemarks.setResultsOfControl(resultsOfControl);
		}
	}
				
	private void setSealNumber(List< TsVUV > list) {	
		if (list == null) {
			return;
		}
		if (!list.isEmpty()) {
			for (TsVUV tsVuv : list) {
				SealNumber sealNumber = new SealNumber();					
				sealNumber.setNumber(tsVuv.getSeal());					
				msgNCTSUnloadingRemarks.getListSealNumbers().add(sealNumber);
			}
		}	
	}
		
	private void setEnRouteEvent(MsgVUR msgVUR) {
		if (msgVUR.getVueSubset() == null) {
			return;
		}
		if (!msgVUR.getVueSubset().isEmpty()) {
			
			EnRouteEvent enRouteEvent = new EnRouteEvent();
			Incident incident = new Incident();
			incident.setIncidentFlag(msgVUR.getVueSubset().getKzvorf());
			incident.setEndorsementAuthority(msgVUR.getVueSubset().getVrfamt());
			incident.setEndorsementPlace(msgVUR.getVueSubset().getVrfort());
			incident.setEndorsementCountry(msgVUR.getVueSubset().getVrfld());
			incident.setEndorsementDate(msgVUR.getVueSubset().getVrfdat());
			//incident.setEndorsementDateFormat(EFormat.ST_Date);
			incident.setEndorsementDateFormat(EFormat.KIDS_Date);
			incident.setIncidentInfo(msgVUR.getVueSubset().getVrfmsn());
			
			TransShipment transShipment = new TransShipment();
			transShipment.setNewTransportCountry(msgVUR.getVueSubset().getNbfld());
			transShipment.setNewTransportId(msgVUR.getVueSubset().getNbfkz());
			transShipment.setEndorsementAuthority(msgVUR.getVueSubset().getUmlamt());
			transShipment.setEndorsementPlace(msgVUR.getVueSubset().getUmlort());
			transShipment.setEndorsementCountry(msgVUR.getVueSubset().getUmlld());
			transShipment.setEndorsementDate(msgVUR.getVueSubset().getUmldat());
			//transShipment.setEndorsementDateFormat(EFormat.ST_Date);
			transShipment.setEndorsementDateFormat(EFormat.KIDS_Date);
			
			if (!msgVUR.getVuoSubsetList().isEmpty()) {
				
				for (TsVUO tsVuo : msgVUR.getVuoSubsetList()) {	
					transShipment.getContainerNumberList().add(tsVuo.getConnr());
					enRouteEvent.setTransShipment(transShipment);
				}
			}
			
			enRouteEvent.setIncident(incident);
			enRouteEvent.setTransShipment(transShipment);
			
			if (!msgVUR.getVnvSubsetList().isEmpty()) {
				for (TsVNV tsVnv : msgVUR.getVnvSubsetList()) {
					enRouteEvent.getSealsIdentityList().add(tsVnv.getSeal());
				}
			}	
			msgNCTSUnloadingRemarks.setEnRouteEvent(enRouteEvent);
		}
	}
	
	private void setGoodsItemList(List<MsgVURPos> list) {
		if (list == null) {
			return;
		}		
		for (MsgVURPos pos : list) {
			msgNCTSUnloadingRemarks.addGoodsItemList(getGoodsItem(pos));							
		}		
	}
	
	private MsgNCTSUnloadingRemarksPos getGoodsItem(MsgVURPos pos) {
		if (pos == null) {
			return null;
		}
		
		MsgNCTSUnloadingRemarksPos item = new MsgNCTSUnloadingRemarksPos();
		
		if (pos.getVupSubset() != null) {
			if (!Utils.isStringEmpty(pos.getVupSubset().getTnr())) {
				CommodityCode commodityCode = new CommodityCode();
				commodityCode.setTarifCode(pos.getVupSubset().getTnr());
				item.setCommodityCode(commodityCode);
			}
		}
		item.setItemNumber(pos.getVupSubset().getPosnr());
		item.setGoodsDescription(pos.getVupSubset().getWabsch());
		item.setGrossMass(pos.getVupSubset().getRohmas());
		item.setNetMass(pos.getVupSubset().getEigmas());
		item.setMissingLineFlag(pos.getVupSubset().getPfehlt());
			
		ResultsOfControl results = new ResultsOfControl();
		results.setDescription(pos.getVupSubset().getUnstm());
		item.setResultsOfControl(results);
			
		if (pos.getVucSubsetList() != null) {			
				List<String> list = new ArrayList<String>();
				Container container = new Container();
				for (TsVUC tsVuc : pos.getVucSubsetList()) {	
					if (tsVuc.getConnr() != null) {
						list.add(tsVuc.getConnr());		
					}
				}
				container.setNumberList(list);
				item.setContainer(container);
		}
			
		if (pos.getVulSubsetList() != null) {				
				for (TsVUL tsVul : pos.getVulSubsetList()) {	
					if (tsVul != null) {
						Packages packages = new Packages();
						packages.setQuantity(tsVul.getColan());
						packages.setType(tsVul.getColar());
						packages.setMarks(tsVul.getColzc());
						item.getListPackages().add(packages);	
					}
				}
		}
			
		if (pos.getVusSubsetList() != null) {				
				for (TsVUS tsVus : pos.getVusSubsetList()) {
					if (tsVus != null) {
						SensitiveGoods sensitiveGoods = new SensitiveGoods();
						sensitiveGoods.setType(tsVus.getSgicod());
						sensitiveGoods.setWeight(tsVus.getSgimng());
						item.getSensitiveGoodsList().add(sensitiveGoods);
					}
				}
		}
					
		return item;					
	}
	
}
