package com.kewill.kcx.component.mapping.countries.de.ncts20.kids2fss;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.OfficeOfTransit;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Reference;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletionItem;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVAN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVANPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVAN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVPO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVSP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVSU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAG;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVBR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVDZ;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVED;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVTV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVVS;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module 		: NCTS OUT<br>
 * Created 		: 13.11.2012<br>
 * Description 	: Mapping of KIDS format of NCTSDeclaration to FSS format VAN.
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class MapNCTSDeclarationToVAN extends KidsMessage {
	
	private MsgNCTSDeclaration msgNCTSDeclaration;
	private MsgVAN msgVAN;
	private String referenceNumber = null;
	private String itemNumber = "";	
	private String transmitter = "";  //EI20120206: wird nur fuer KFF ausgewertet
	private String subversion = "";      //EI20130425
	
	public MapNCTSDeclarationToVAN(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgNCTSDeclaration = new MsgNCTSDeclaration(parser);
		msgVAN = new MsgVAN();
		msgVAN.setVorSubset(tsvor);
	}

	public MapNCTSDeclarationToVAN(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {		
		msgNCTSDeclaration = new MsgNCTSDeclaration(parser);
		msgVAN = new MsgVAN();
		msgVAN.setVorSubset(tsvor);
		msgVAN.setHeadSubset(head);
	}
	
	public String getMessage() {
		String res = "";
		
		if (this.getKidsHeader() != null) {                   //EI20120206:
			transmitter = this.getKidsHeader().getTransmitter();
			if (Utils.isStringEmpty(transmitter)) {
				transmitter = this.getKidsHeader().getReceiver();
			}
			if (transmitter.contains("TOLL") || transmitter.contains("KFF") || 
					transmitter.contains("HANK")) {          //EI20120420
				transmitter = "KFF";
			}
			if (this.getCommonFieldsDTO() != null && !Utils.isStringEmpty(this.getCommonFieldsDTO().getBOB())) {  //EI20130215
				transmitter = this.getCommonFieldsDTO().getBOB();				
			}
			if (!Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
				subversion = Utils.removeDots(this.getKidsHeader().getRelease());
			}
		}
		
        try {        
        	msgNCTSDeclaration.parse(HeaderType.KIDS);                      

        	// extract referenceNumber to pass it to several methods building subsets
            referenceNumber = msgNCTSDeclaration.getReferenceNumber();
            
            getCommonFieldsDTO().setReferenceNumber(referenceNumber);
            
			// read MessageID from KidsHeader 
			msgVAN.getVorSubset().setMsgid(getKidsHeader().getMessageID());
						
			msgVAN.setVanSubset(this.mapVan());
			// 6 address types can be in the message:
			msgVAN.setConsignor(msgNCTSDeclaration.getConsignor(), referenceNumber, "0");  
			msgVAN.setConsignee(msgNCTSDeclaration.getConsignee(), referenceNumber, "0");  
			msgVAN.setPrincipal(msgNCTSDeclaration.getPrincipal(), referenceNumber, "0");
			// authorised consignee: only TIN and ETNAddress used - both in subset VAN
			msgVAN.setConsignorSecurity(msgNCTSDeclaration.getConsignorSecurity(), referenceNumber, "0");  
			msgVAN.setConsigneeSecurity(msgNCTSDeclaration.getConsigneeSecurity(), referenceNumber, "0");  
			msgVAN.setCarrier(msgNCTSDeclaration.getCarrier(), referenceNumber, "0");  //EI20130325

			List<OfficeOfTransit> list = msgNCTSDeclaration.getOfficeOfTransitList();
			if (list != null) {
				for (int i = 0, size = list.size(); i < size; i++) {
					OfficeOfTransit office = list.get(i);
					if (office != null) {
						msgVAN.addVdzList(this.mapVdz(office.getCode()));	
					}
					
				}
			}
			List<Guarantee> guaranteeListHead = msgNCTSDeclaration.getGuaranteeListH();
			if (guaranteeListHead != null) {
				for (Guarantee guarantee : guaranteeListHead) {					
					if (guarantee != null) {
						List<Reference> reflist = guarantee.getReferenceList();
						for (Reference ref : reflist) {																					
							msgVAN.addVsiList(this.mapVsi(guarantee.getTypeOfGuarantee(), ref));  //EI20111122
						}
					}
				}
			}
			Seal  seal = msgNCTSDeclaration.getSeals();
			if (seal != null) {
				List<SealNumber> seals = seal.getSealNumbersList();
				if (seals != null) {					
					//EI20110524: for (int i = 0, size = list.size(); i < size; i++) {
					for (int i = 0, size = seals.size(); i < size; i++) {
						SealNumber sn = seals.get(i);
						if (sn != null)  {
							msgVAN.addVvsList(this.mapVvs(sn.getNumber()));
						}
					}
				}
			}
 
			msgVAN.setVsuSubset(this.mapVsu());			
			msgVAN.setSasSubset(this.mapSas());
			
			Security security = msgNCTSDeclaration.getSecurity(); 
			if (security != null) {
				List<String> itinerary = security.getItineraryList();
				if (itinerary != null) {
					for (int i = 0, size = itinerary.size(); i < size; i++) {
						msgVAN.addVbrList(this.mapVbr(itinerary.get(i)));
					}
				}
			}
			
			// Positionsteil = GoodsItems			
			if (msgNCTSDeclaration.getGoodsItemList() != null) {
			    int listSizeItem = msgNCTSDeclaration.getGoodsItemList().size();						
			    for (int i = 0; i < listSizeItem; i++) {
			    	MsgNCTSDeclarationPos msgNCTSDeclarationPos = msgNCTSDeclaration.getGoodsItemList().get(i);
			    	MsgVANPos msgVANPos =  this.mapPosition(msgNCTSDeclarationPos, guaranteeListHead, i);
			    	msgVAN.addPosList(msgVANPos); 
			    }
			}

			// build the complete FSS-message-string
			//--------------------------------------
            //res = msgVAN.getFssString();
			 /* EI20140206:
            if (this.writeHead()) { 				//EI20130213
            	res = msgVAN.getFssString("HEAD");
            } else {
            	res = msgVAN.getFssString();
            }  
            */
			res = msgVAN.getFssString("HEAD");
           
            Utils.log("(MapNCTSDeclarationToVIA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}

	private MsgVANPos mapPosition(MsgNCTSDeclarationPos goodsItem, List<Guarantee> guaranteeListHead, int i) {
		if (goodsItem == null) {
			return null;
		}
		MsgVANPos msgVANPos = new MsgVANPos();
		itemNumber = goodsItem.getItemNumber();
	
		msgVANPos.setVpoSubset(this.mapVpo(goodsItem));
		
		if (goodsItem.getConsignor() != null) {
			if (goodsItem.getConsignor().getAddress() != null) {
				TsVAP vap = new TsVAP();
				vap.setAdrSubset(goodsItem.getConsignor(), "1", referenceNumber, itemNumber);
				msgVANPos.addVAP(vap);	
			}
			/*
			if (goodsItem.getConsignor().getContactPerson() != null) {
				TsVSP vsp = new TsVSP();
				vsp.setVspSubset(goodsItem.getConsignor().getContactPerson(), "1", referenceNumber, itemNumber);
				msgVANPos.addVSP(vsp);	
			}	
			*/		
			if (goodsItem.getConsignor().getContactPersonList() != null) {				
				for (ContactPerson cpr : goodsItem.getConsignor().getContactPersonList()) { 					
					TsVSP vsp = new TsVSP();
					vsp.setVspSubset(cpr, "1", referenceNumber, itemNumber);
					msgVANPos.addVSP(vsp);						
				}
			}
		}		
		if (goodsItem.getConsignee() != null) {
			if (goodsItem.getConsignee().getAddress() != null) {
				TsVAP vap = new TsVAP();
				vap.setAdrSubset(goodsItem.getConsignee(), "2", referenceNumber, itemNumber);
				msgVANPos.addVAP(vap);	
			}
			/*
			if (goodsItem.getConsignee().getContactPerson() != null) {
				TsVSP vsp = new TsVSP();
				//EI20131014: vsp.setVspSubset(goodsItem.getConsignee().getContactPerson(), "1", referenceNumber, itemNumber);
				vsp.setVspSubset(goodsItem.getConsignee().getContactPerson(), "2", referenceNumber, itemNumber); //EI20131014
				msgVANPos.addVSP(vsp);	
			}
			*/
			if (goodsItem.getConsignee().getContactPersonList() != null) {				
				for (ContactPerson cpe : goodsItem.getConsignee().getContactPersonList()) { 					
					TsVSP vsp = new TsVSP();
					vsp.setVspSubset(cpe, "2", referenceNumber, itemNumber);
					msgVANPos.addVSP(vsp);						
				}
			}
		}	
		if (goodsItem.getConsignorSecurity() != null) {
			TsVAP vap = new TsVAP();
			vap.setAdrSubset(goodsItem.getConsignorSecurity(), "5", referenceNumber, itemNumber);
			msgVANPos.addVAP(vap);	
		}
		if (goodsItem.getConsigneeSecurity() != null) {
			TsVAP vap = new TsVAP();
			vap.setAdrSubset(goodsItem.getConsigneeSecurity(), "6", referenceNumber, itemNumber);
			msgVANPos.addVAP(vap);	
		}
		
		List<Packages> packlist = goodsItem.getPackagesList();
		if (packlist != null) {
			for (int j = 0, size = packlist.size(); j < size; j++) {
				msgVANPos.addVCO(this.mapVco(packlist.get(j)));	
			}
		}
		
		Container container = goodsItem.getContainer();
		//if (container != null ) {
		if (container != null && container.getNumberList() != null) {   //EI20140508
			List<String>numbers = container.getNumberList();
			
			for (int j = 0, size = numbers.size(); j < size; j++) {
				msgVANPos.addVCN(this.mapVcn(numbers.get(j)));	
			}
		}
		
		List<PreviousDocument> prevdocs = goodsItem.getPreviousDocumentList();
		if (prevdocs != null) {
			for (int k = 0, size = prevdocs.size(); k < size; k++) {
				msgVANPos.addVTV(this.mapVtv(prevdocs.get(k)));	
			}
		}
		
		List<Document> doclist = goodsItem.getDocumentList();
		if (doclist != null) {
			for (int l = 0, size = doclist.size(); l < size; l++) {
				msgVANPos.addVED(this.mapVed(doclist.get(l)));	
			}
		}
		
		//if (goodsItem.getWriteOffSumAList() != null) {
			//BSU			
			if (goodsItem.getManifestCompletionList() != null) {
				for (ManifestCompletion suma : goodsItem.getManifestCompletionList()) {
					if (suma != null) {
						List <ManifestCompletionItem> dataList = suma.getCompletionItemList();
						int listSize = 0;
						if (dataList != null) {
							listSize = dataList.size();
						}
					   if (suma.getIdentification() != null && suma.getIdentification().equals("FV") && listSize == 0) {
							if (!Utils.isStringEmpty(suma.getReference())) {
								msgVANPos.getVpoSubset().setVptyp("FV");
								TsVTV vtv = new TsVTV();
								vtv.setBeznr(referenceNumber); 
								vtv.setPosnr(itemNumber);
								vtv.setVregnr(suma.getReference());
								msgVANPos.addVTV(vtv);
							}
					   } else {				
						    if (suma.getIdentification() != null && suma.getIdentification().equals("REG")) { 
							   msgVANPos.getVpoSubset().setSuaart("REG");
						    }
							if (suma.getIdentification() != null && suma.getIdentification().equals("AWB")) { 
								   msgVANPos.getVpoSubset().setSuaart("AWB");								  
							}
							if (dataList != null) {
								for (ManifestCompletionItem data : dataList) {									   
									msgVANPos.addBSU(this.mapBsu(data, suma));										
								}
							}
						}
					}
				}
			}
			//BZL
			List<Completion> bzlList =  goodsItem.getBondedWarehouseCompletionList();
			if (bzlList != null) {		
				for (Completion bzl : bzlList) {
					if (bzl != null) {
						List <CompletionItem> dataList = bzl.getCompletionItemList();
						if (dataList != null) {
							for (CompletionItem data : dataList) {								                     
								msgVANPos.addBZL(this.mapBzl(data));									
							}
						}
					}
				}
			}
			//BAV
			List<Completion> bavList =  goodsItem.getInwardProcessingCompletionList();
			if (bavList != null) {		
				for (Completion bav : bavList) {
					if (bav != null) {
						List <CompletionItem> dataList = bav.getCompletionItemList();
						if (dataList != null) {
							for (CompletionItem data : dataList) {
								if (data != null) {                       
									msgVANPos.addBAV(this.mapBav(data));	
								}
							}
						}
					}
				}
			}
		//}
		
		//EI-20111205: wenn Guarantien auf KopfEbene geschickt werden, dann werden sie die 1.Position
		//noch mal gemapped wg. Betraege, die man im VSI nicht erfassen kann,
		//wenn keine Guarantien auf KopfEben, dann können Guarantien auf ItemEbene eingelesen werden
		if (guaranteeListHead != null && guaranteeListHead.size() > 0) {
			if (i == 0) {
				msgVANPos.setVagList(this.mapVagList(guaranteeListHead));	
			}
		} 
		List<Guarantee> guaranteeListItem = goodsItem.getGuaranteeList();
		if (guaranteeListItem != null) {
			for (Guarantee guarantee : guaranteeListItem) {
				msgVANPos.setVagList(this.mapVags(guarantee));	
			}
		} 
		

		/* EI: keine Mapping-Angaben definiert
		List<D> vsulist = goodsItem.getList();
		if (vsulist != null) {
			for (int l = 0, size = vsulist.size(); l < size; l++) {
				msgVANPos.addVSU(getVsu(vsulist.get(l)));	
			}
		}
		List<D> saplist = goodsItem.getList();
		if (saplist != null) {
			for (int l = 0, size = saplist.size(); l < size; l++) {
				msgVANPos.addSAP(getSap(saplist.get(l)));	
			}
		}
		*/
		//EI20111011-end
		return msgVANPos;
	}
	
	private TsVAN mapVan() {
		TsVAN van = new TsVAN();
		van.setBeznr(msgNCTSDeclaration.getReferenceNumber());
		van.setBewnr(msgNCTSDeclaration.getAuthorisationNumber());
		van.setAnmart(msgNCTSDeclaration.getTypeOfDeclaration());
		van.setLdvs(msgNCTSDeclaration.getDispatchCountry());
		van.setLdbe(msgNCTSDeclaration.getDestinationCountry());
		van.setAbgdst(msgNCTSDeclaration.getOfficeOfDeparture());
		van.setBedst(msgNCTSDeclaration.getOfficeOfDestination());
		van.setWgdat(msgNCTSDeclaration.getControlResultDateLimit());
		van.setKzzv(msgNCTSDeclaration.getSimplifiedProcedureIndicator());
		// ZABIS-Nachkommastellen anhängen
		van.setGsroh(Utils.addZabisDecimalPlaceV7(msgNCTSDeclaration.getTotalGrossMass(), 3)); //V70 Kids as decimal
		if (subversion.equals("4101")) {     //EI20130422
			van.setGsroh(msgNCTSDeclaration.getTotalGrossMass());
		}
		
		if (msgNCTSDeclaration.getTransRoute() != null) {
			van.setRoute(msgNCTSDeclaration.getTransRoute().getFirstCountry());			
			van.setKzraus(msgNCTSDeclaration.getTransRoute().getSuspensionIndicator());
		}
		if (msgNCTSDeclaration.getSeals() != null) {
			van.setKztyd(msgNCTSDeclaration.getSeals().getUseOfTydenseals());	
			van.setKzstock(msgNCTSDeclaration.getSeals().getUseOfTydensealStock());
			van.setVsanz(msgNCTSDeclaration.getSeals().getNumber());
			van.setVsart(msgNCTSDeclaration.getSeals().getKind());   
		}
		//EI20140512: KLs schiccken immer MeansOfTransportInland statt  MeansOfTransportDeparture:
		if (msgNCTSDeclaration.getMeansOfTransportInland() != null && this.getCommonFieldsDTO() != null &&  
			this.getCommonFieldsDTO().getBOB() != null && this.getCommonFieldsDTO().getBOB().equalsIgnoreCase("KL")) {			
				van.setBfgrcd(msgNCTSDeclaration.getMeansOfTransportInland().getTransportationType());
				van.setBfabkz(msgNCTSDeclaration.getMeansOfTransportInland().getTransportationNumber());
				van.setBfabld(msgNCTSDeclaration.getMeansOfTransportInland().getTransportationCountry());			
		} else {		
			if (msgNCTSDeclaration.getMeansOfTransportDeparture() != null) {			
				van.setBfgrcd(msgNCTSDeclaration.getMeansOfTransportDeparture().getTransportationType());
				van.setBfabkz(msgNCTSDeclaration.getMeansOfTransportDeparture().getTransportationNumber());
				van.setBfabld(msgNCTSDeclaration.getMeansOfTransportDeparture().getTransportationCountry());
			}
		}
		if (msgNCTSDeclaration.getMeansOfTransportBorder() != null) {
			van.setBfgrcd(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportationType());
			van.setBfgrkz(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportationNumber());
			van.setBfgrld(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportationCountry());
			van.setBfvkzg(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportMode());
		}
		PartyNCTS consignee	 = msgNCTSDeclaration.getConsignee();
		PartyNCTS consignor	 = msgNCTSDeclaration.getConsignor();
		PartyNCTS authorised = msgNCTSDeclaration.getAuthorisedConsignee();
		PartyNCTS principal	 = msgNCTSDeclaration.getPrincipal();
	
		if (consignor != null && consignor.getPartyTIN() != null) {
			van.setTinve(consignor.getPartyTIN().getTIN());
			van.setKdnrve(consignor.getPartyTIN().getCustomerIdentifier());
			van.setNlve(consignor.getPartyTIN().getBO());                     //V70
			van.setDtzove(consignor.getPartyTIN().getIdentificationType());   //V70
		}
			
		if (consignee != null && consignee.getPartyTIN() != null) {
			van.setTinem(consignee.getPartyTIN().getTIN());
			van.setKdnrem(consignee.getPartyTIN().getCustomerIdentifier());
			van.setNlem(consignee.getPartyTIN().getBO());                     //V70
			van.setDtzoem(consignee.getPartyTIN().getIdentificationType());   //V70
		}
		
		if (authorised != null && authorised.getPartyTIN() != null) {
			van.setTinze(authorised.getPartyTIN().getTIN());
			van.setKdnrze(authorised.getPartyTIN().getCustomerIdentifier());
			//van.setNlze(authorised.getPartyTIN().getBO());                    //das gibt es nicht
			//van.setDtzoze(authorised.getPartyTIN().getIdentificationType());  //das gibt es nicht 
		}
		
		van.setAnzcol(msgNCTSDeclaration.getTotalNumberPackages());		
		if (msgNCTSDeclaration.getPlaceOfLoading() != null) {  	
			van.setUebort(msgNCTSDeclaration.getPlaceOfLoading().getCode()); 
		}
		if (authorised != null) {
			van.setEtnadr(authorised.getETNAddress());
		}
		if (msgNCTSDeclaration.getClerk() != null) {
			van.setSb(msgNCTSDeclaration.getClerk().getIdentity());
		} else {
			van.setSb("FSS");     
		}
		if (principal != null && principal.getPartyTIN() != null) {
			van.setTinhp(principal.getPartyTIN().getTIN());
			van.setKdnrhp(principal.getPartyTIN().getCustomerIdentifier());
			//van.setDtzohp(principal.getPartyTIN().getIsTINGermanApprovalNumber());
			van.setNlhp(principal.getPartyTIN().getBO());                     //V70
			van.setDtzohp(principal.getPartyTIN().getIdentificationType());   //V70
		}
		
		van.setCarnhp(msgNCTSDeclaration.getCarnetID());
		van.setKzhpbf(msgNCTSDeclaration.getPrincipalIsCarrier());       //V70
		van.setKzsusi(msgNCTSDeclaration.getSecurityIndicator());    		
		van.setZlbez(msgNCTSDeclaration.getBondedWareHouseRefNum()); 
		
		return van;
	}

	private TsVDZ mapVdz(String office) {
		if (Utils.isStringEmpty(office)) {
			return null;
		}
		TsVDZ vdz = new TsVDZ();

		vdz.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vdz.setDgzst(office);
		return vdz;
	}
	
	private TsVVS mapVvs(String seal) {
		if (Utils.isStringEmpty(seal)) {
			return null;
		}
		TsVVS vvs = new TsVVS();

		vvs.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vvs.setSeal(seal);
		return vvs;
	}

	private TsVSU mapVsu() {
		TsVSU vsu = new TsVSU();
		PartyNCTS carrier = msgNCTSDeclaration.getCarrier();
		PartyNCTS consignorSecurity = msgNCTSDeclaration.getConsignorSecurity();
		PartyNCTS consigneeSecurity = msgNCTSDeclaration.getConsigneeSecurity();

		vsu.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vsu.setPosnr("0"); // wird nur auf Kopf-Ebene verwendet
		        
		if (carrier != null && carrier.getPartyTIN() != null) {			
			vsu.setKnrbf(carrier.getPartyTIN().getCustomerIdentifier());
			vsu.setTinbf(carrier.getPartyTIN().getTIN());	
			vsu.setNlbf(carrier.getPartyTIN().getBO());	                  //EI20130325 new V70
			vsu.setDtzobf(carrier.getPartyTIN().getIdentificationType()); //EI20130325 new V70
		}
	
		if (msgNCTSDeclaration.getSecurity() != null) {			
			vsu.setBfgnr(msgNCTSDeclaration.getSecurity().getShipmentNumber());
			if (transmitter.equals("KFF")) {                //EI20120206
				vsu.setBfgnr("");
			}
			vsu.setLdeort(msgNCTSDeclaration.getSecurity().getPlaceOfLoading());
			vsu.setEldort(msgNCTSDeclaration.getSecurity().getPlaceOfUnloading());
		}
				
		if (consignorSecurity != null && consignorSecurity.getPartyTIN() != null) {			
			vsu.setKnrvsu(consignorSecurity.getPartyTIN().getCustomerIdentifier());	
			vsu.setTinvsu(consignorSecurity.getPartyTIN().getTIN());
			vsu.setNlvsu(consignorSecurity.getPartyTIN().getBO());	                 //EI20130325 new V70
			vsu.setDtzovsu(consignorSecurity.getPartyTIN().getIdentificationType()); //EI20130325 new V70
		}
		
		if (consigneeSecurity != null && consigneeSecurity.getPartyTIN() != null) {			
			vsu.setKnresu(consigneeSecurity.getPartyTIN().getCustomerIdentifier());
			vsu.setTinesu(consigneeSecurity.getPartyTIN().getTIN());		
			vsu.setNlesu(consigneeSecurity.getPartyTIN().getBO());	                 //EI20130325 new V70
			vsu.setDtzoesu(consigneeSecurity.getPartyTIN().getIdentificationType()); //EI20130325 new V70
		}
		return vsu;
	}
	
	private TsSAS mapSas() {
		TsSAS sas = new TsSAS();

		sas.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());

		sas.setBfgkzw(msgNCTSDeclaration.getPaymentType());
		sas.setKnrsdg(msgNCTSDeclaration.getShipmentNumber());
		//EI20120206 JIRA-Ticket KCX-93 von AG/DH: für KFF - NCTS  Shipments nach DE. Das Feld
		//   		                    "Kenn Nr"/<ShipmentNumber> darf nicht an ZABIS übertragen werden.    
		if (transmitter.equals("KFF")) {   			
			sas.setKnrsdg("");                //<== "Kenn Nr"/<ShipmentNumber> 
		}
		//EI20120206-end
		sas.setBesust(msgNCTSDeclaration.getSituationCode());

		return sas;
	}

	private TsVBR mapVbr(String ld) {
		if (Utils.isStringEmpty(ld)) {
			return null;
		}
		TsVBR vbr = new TsVBR();

		vbr.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vbr.setLdbf(ld);
		return vbr;
	}
	
	private TsVSI mapVsi(String sicart, Reference ref) {   //EI20111122
		if (Utils.isStringEmpty(sicart)) {
			return null;
		}
		TsVSI vsi = new TsVSI();

		vsi.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vsi.setSicart(sicart);	
		
		String grn = ref.getGrn();
		String ots = ref.getOtherNumber();
		if (Utils.isStringEmpty(grn)) {	
				vsi.setSicbsc(ots);
				vsi.setKzncts("0");			
		} else {
			vsi.setSicbsc(grn);	
			vsi.setKzncts("1");
		}
		vsi.setAccd(ref.getAccessCode());
		vsi.setSictin(ref.getOwnerTin());
				
		return vsi;
	}
	private TsVPO mapVpo(MsgNCTSDeclarationPos goodsItem) {
		if (goodsItem == null) {
			return null;
		}
		
		TsVPO 	vpo = new TsVPO();
		PartyNCTS consignee	 = goodsItem.getConsignee();
		PartyNCTS consignor	 = goodsItem.getConsignor();
		
		vpo.setBeznr(referenceNumber);
		vpo.setPosnr(itemNumber);
		vpo.setAnmart(goodsItem.getTypeOfDeclaration());
		
		CommodityCode commodityCode = goodsItem.getCommodityCode();
		if (commodityCode != null) {
			vpo.setTnr(commodityCode.getTarifCode());	
		}
		vpo.setWbsch(goodsItem.getDescription());
		vpo.setArtnr(goodsItem.getArticleNumber());
		vpo.setLdvs(goodsItem.getDispatchCountry());
		vpo.setLdbe(goodsItem.getDestinationCountry());
		vpo.setRohm(Utils.addZabisDecimalPlaceV7(goodsItem.getGrossMass(), 3));  //V70 Kids as decimal
		vpo.setEigm(Utils.addZabisDecimalPlaceV7(goodsItem.getNetMass(), 3));    //V70 Kids as decimal
		if (subversion.equals("4101")) {     //EI20130422
			vpo.setRohm(goodsItem.getGrossMass());
			vpo.setEigm(goodsItem.getNetMass());
		}
		
		//	EI20121114:			nicht nur TIN sondern auch Contact-Daten									
		if (consignee != null && consignee.getPartyTIN() != null) {
			vpo.setTinem(consignee.getPartyTIN().getTIN());
			vpo.setKdnrem(consignee.getPartyTIN().getCustomerIdentifier());
		}
		
		if (consignor != null && consignor.getPartyTIN() != null) {
			vpo.setTinve(consignor.getPartyTIN().getTIN());
			vpo.setKdnrve(consignor.getPartyTIN().getCustomerIdentifier());
		}
		
		List<PreviousDocument> doclist = goodsItem.getPreviousDocumentList();
		if (doclist != null) {
			PreviousDocument doc = doclist.get(0);	
			if (doc != null) {
				vpo.setVptyp(doc.getType());	
			}
		}			
		List<SpecialMention> speclist = goodsItem.getSpecialMentionList();
		if (speclist != null) {
			SpecialMention spec = speclist.get(0);	
			if (spec != null) {
				vpo.setKzexeu(spec.getExportFromEU());
				vpo.setLdexp(spec.getExportFromCountry());					
				vpo.setKzvub(spec.getBansAndLimitations());  //EI20130823: LCAG				
				//EI20130823: LCAG und wohin mit spec.getText()?
			}
		}			
		List<SensitiveGoods> sgilist = goodsItem.getSensitiveGoodsList();
		if (sgilist != null) {
			SensitiveGoods sgi = sgilist.get(0);	
			if (sgi != null) {
				vpo.setSgicd(sgi.getType());
				vpo.setSgimng(Utils.addZabisDecimalPlaceV7(sgi.getWeight(), 3)); //V70 Kids as decimal
				if (subversion.equals("4101")) {     //EI20130422
					vpo.setSgimng(sgi.getWeight());
				}
			}	
		}							
				
		vpo.setSuaart(goodsItem.getIdentificationCode());     
		vpo.setZlbez(msgNCTSDeclaration.getBondedWareHouseRefNum());   		 
		vpo.setAzvbew(msgNCTSDeclaration.getBondedWarehousePermitNumber());  
		
		return vpo;
	}
	
	private TsVCO mapVco(Packages packages) {
		if (packages == null) {
			return null;
		}
		TsVCO vco = new TsVCO();

		vco.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vco.setPosnr(itemNumber);
		vco.setColanz(packages.getQuantity());
		vco.setColart(packages.getType());
		vco.setColzs(packages.getMarks());
		
		return vco;
	}

	private TsVCN mapVcn(String number) {
		if (number == null) {
			return null;
		}
		TsVCN vcn = new TsVCN();

		vcn.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vcn.setPosnr(itemNumber);
		vcn.setConnr(number);
		
		return vcn;
	}

	private TsVTV mapVtv(PreviousDocument doc) {
		if (doc == null) {
			return null;
		}
		TsVTV vtv = new TsVTV();

		vtv.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vtv.setPosnr(itemNumber);
		vtv.setVregnr(doc.getMarks());
		vtv.setTxzus(doc.getAdditionalInformation());
		
		return vtv;
	}
	
	private TsVED mapVed(Document doc) {
		if (doc == null) {
			return null;
		}
		TsVED ved = new TsVED();

		ved.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		ved.setPosnr(itemNumber);
		ved.setUntar(doc.getType());
		ved.setUntre(doc.getReference());
		ved.setUntzu(doc.getAdditionalInformation());
		
		return ved;
	}
	
	private TsBSU mapBsu(ManifestCompletionItem writeOffData, ManifestCompletion suma) {
		if (writeOffData == null) {
			return null;
		}
		TsBSU bsu = new TsBSU();

		bsu.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		bsu.setPosnr(itemNumber);
		bsu.setVregnr(writeOffData.getUCR());
		bsu.setVposnr(writeOffData.getPositionNumber());
		bsu.setSuastk(writeOffData.getNumberOfPieces());		
		if (suma != null && suma.getIdentification() != null && suma.getIdentification().equalsIgnoreCase("AWB")) {  //EI20130821
			bsu.setSpoart("AWB");
			bsu.setSponr(suma.getReference());
			bsu.setAzvagl(writeOffData.getAtlasAlignment());  //bedeutet: Abgleich in ATLAS erfolgt
			// Kids.EntryInAtlas == uids.AtlasCode und ist nur für BZL definiert, bedeutet Kennzeichen Zugang ATLAS,
			//                      bzw(aus xsd): Flag if the inward processing declaration was made in atlas
			
			if (writeOffData.getCustodianTIN() != null) {   //EI20140527
				bsu.setVrwknr(writeOffData.getCustodianTIN().getCustomerIdentifier());  //EI20140616
				bsu.setVerweori(writeOffData.getCustodianTIN().getTin());  
				bsu.setVerwId(writeOffData.getCustodianTIN().getIdentificationType());  //EI20140612
			}
		}
		return bsu;
	}
	
	private TsBAV mapBav(CompletionItem data) {
		TsBAV bav = null;
		if (data == null) {
			return null;
		}
		/*
		bav = new TsBAV();
		bav.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		bav.setPosnr(itemNumber);
		bav.setVregnr(writeOffData.getRegNumber());
		bav.setVposnr(writeOffData.getItemNumber());
		bav.setSuastk(writeOffData.getNumberOfPieces());
		*/
		return bav;
	}
	
	private TsBZL mapBzl(CompletionItem data) {
		TsBZL bzl = null;
		if (data == null) {
			return null;
		}
		/*
		bzl = new TsBZL();
		bzl.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		bzl.setPosnr(itemNumber);
		bzl.setVregnr(writeOffData.getRegNumber());
		bzl.setVposnr(writeOffData.getItemNumber());
		bzl.setSuastk(writeOffData.getNumberOfPieces());
		//decimal: Abgangsmenge-3, Handelsmenge-3
		*/
		return bzl;
	}
	
	private List<TsVAG> mapVags(Guarantee guarantee) {
		if (guarantee == null) {
			return null;
		}
		if (guarantee.getReferenceList() == null || guarantee.getReferenceList().size() == 0) {
			return null;
		}
		List<TsVAG> vagList = new Vector<TsVAG>();
		for (Reference ref : guarantee.getReferenceList()) {			
			if (ref.getAmount() != null && !Utils.isStringEmpty(ref.getAmount().getLocalAmount())) {
				TsVAG vag = new TsVAG();

				vag.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
				vag.setPosnr(itemNumber);
				if (!Utils.isStringEmpty(ref.getGrn())) {	
					vag.setSicbsc(ref.getGrn());					
				} else {
					vag.setSicbsc(ref.getOtherNumber());				
				}									
				vag.setSicbtg(Utils.addZabisDecimalPlaceV7(ref.getAmount().getLocalAmount(), 2)); //V70 Kids as decimal
				vag.setAbggrp(ref.getPriceGroup());				
				vag.setBasbtg(Utils.addZabisDecimalPlaceV7(ref.getAmount().getAmount(), 2)); //V70 Kids as decimal
				vag.setWaehrg(ref.getAmount().getCurrency());
				if (subversion.equals("4101")) {     //EI20130422
					vag.setSicbtg(ref.getAmount().getLocalAmount());
					vag.setBasbtg(ref.getAmount().getAmount());
				}

				// in Zabis muss der Betrag in Euro angegeben werden,
				// ist hier also Euro angegeben, wird der Betrag übernommen
				// ist keine Währung angegeben wird Euro angenommen und der Betrag übernommen
				// ist eine andere Währung als Euro angegeben, kann der Betrag nicht übernommen werden
				// nach Absprache mit AL am 07.02.2012
				
				if (Utils.isStringEmpty(ref.getAmount().getCurrency())) {
					vagList.add(vag);
				} else {														
					if (ref.getAmount().getCurrency().equals("EURO") || ref.getAmount().getCurrency().equals("EUR")) {
						vagList.add(vag);
					} else {
						Utils.log("(KidsToFss) Guarantee/VAG Betrag muss in EURO angegeben werden: " +  
								ref.getAmount().getAmount() + " in " + ref.getAmount().getCurrency() + 
								" wird nicht uebernommen"); 
					}
				}
			}
		}		
		return vagList;
	}
	private List<TsVAG> mapVagList(List<Guarantee> guarlist) {
		if (guarlist == null) {
			return null;
		}
		if (guarlist.size() == 0) {
			return null;
		}
		List<TsVAG> vagList = new Vector<TsVAG>();
		for (Guarantee guarantee : guarlist) {			
			if (guarantee.getReferenceList() != null) {				
				for (Reference ref : guarantee.getReferenceList()) {
					if (ref.getAmount() != null && !Utils.isStringEmpty(ref.getAmount().getAmount())) {
						TsVAG vag = new TsVAG();
						
						vag.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
						vag.setPosnr(itemNumber);								
						if (!Utils.isStringEmpty(ref.getGrn())) {
							vag.setSicbsc(ref.getGrn());
						} else {
							vag.setSicbsc(ref.getOtherNumber());
						}			
						
						//EI20130325: vag.setSicbtg(ref.getAmount().getAmount());
						vag.setSicbtg(Utils.addZabisDecimalPlaceV7(ref.getAmount().getLocalAmount(), 2)); //EI20130325: statt getAmount -> getLocalAmount - ist es OK???
						vag.setBasbtg(Utils.addZabisDecimalPlaceV7(ref.getAmount().getAmount(), 2)); //EI20130325: neu aufgenommen - ist es OK???
						if (subversion.equals("4101")) {     //EI20130422
							vag.setSicbtg(ref.getAmount().getLocalAmount());
							vag.setBasbtg(ref.getAmount().getAmount());
						}
						if (Utils.isStringEmpty(ref.getAmount().getCurrency())) {
							vagList.add(vag);
						} else {														
							if (ref.getAmount().getCurrency().equals("EURO") || 
										ref.getAmount().getCurrency().equals("EUR")) {
								vagList.add(vag);
							} else {
								Utils.log("(KidsToFss) Guarantee/VAG Betrag muss in EURO angegeben werden: " +  
										ref.getAmount().getAmount() + " " + ref.getAmount().getCurrency() + 
										" wird nicht uebernommen"); 
							}
						}
					}
				}
			}
		}
		
		return vagList;
	}	
	
	/*
	private TsVSU getVsu(??? guarantee) {
		TsVSU vsu = null;
		
		if (guarantee == null) {
			return null;
		}
		TsVSU vsu = new TsVSU();

		vsu.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vsu.setPosnr(itemNumber);
		vsu.setVregnr(guarantee.getRegNumber());
		vsu.setVposnr(guarantee.getItemNumber());
		vsu.setSuastk(guarantee.getNumberOfPieces());
		
		return vsu;
	}
	private TsSAP getSap(??? guarantee) {
		TsSAP sap = null;
		
		if (guarantee == null) {
			return null;
		}
		sap = new TsSAP();

		sap.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		sap.setPosnr(itemNumber);
		sap.setVregnr(guarantee.getRegNumber());
		sap.setVposnr(guarantee.getItemNumber());
		sap.setSuastk(guarantee.getNumberOfPieces());
		
		return sap;
	}
	*/
}

