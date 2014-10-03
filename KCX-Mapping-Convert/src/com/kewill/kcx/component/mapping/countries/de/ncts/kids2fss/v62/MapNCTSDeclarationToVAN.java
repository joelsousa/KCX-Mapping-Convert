/*
 * Function    : MapNCTSDeclarationToVAN.java
 * Date        : 02.09.2010
 * Author      : Kewill  / Christine Kron
 * Description : Mapping of KIDS format of NCTSDeclaration to FSS format VAN

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclarationPos;
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
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOff;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffData;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVAN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVANPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAG;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVAP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVBR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCN;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVCO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVDZ;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVED;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVTV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVVS;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapNCTSDeclarationToVAN<br>
 * Erstellt		: 01.09.2010<br>
 * Beschreibung	: Mapping of KIDS format of NCTSDeclaration to FSS format VAN.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class MapNCTSDeclarationToVAN extends KidsMessage {
	
	private MsgNCTSDeclaration msgNCTSDeclaration;
	private MsgVAN msgVAN;
	private String referenceNumber = null;
	private String itemNumber = "";	
	private String transmitter = "";  //EI20120206: wird nur fuer KFF ausgewertet
	
	public MapNCTSDeclarationToVAN(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgNCTSDeclaration = new MsgNCTSDeclaration(parser);
		msgVAN = new MsgVAN();
		msgVAN.setVorSubset(tsvor);
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
		}
		
        try {        
        	msgNCTSDeclaration.parse(HeaderType.KIDS);                      

        	// extract referenceNumber to pass it to several methods building subsets
            referenceNumber = msgNCTSDeclaration.getReferenceNumber();
            
            getCommonFieldsDTO().setReferenceNumber(referenceNumber);
            
			// read MessageID from KidsHeader 
			msgVAN.getVorSubset().setMsgid(getKidsHeader().getMessageID());

			msgVAN.setVanSubset(getVan());

			// 4 address types can be in the message:
			msgVAN.setConsignor(msgNCTSDeclaration.getConsignor(), referenceNumber, "0");  
			msgVAN.setConsignee(msgNCTSDeclaration.getConsignee(), referenceNumber, "0");  
			msgVAN.setPrincipal(msgNCTSDeclaration.getPrincipal(), referenceNumber, "0");
			// authorised consignee: only TIN and ETNAddress used - both in subset VAN
			msgVAN.setConsignorSecurity(msgNCTSDeclaration.getConsignorSecurity(), referenceNumber, "0");  //EI20110523
			msgVAN.setConsigneeSecurity(msgNCTSDeclaration.getConsigneeSecurity(), referenceNumber, "0");  //EI20110523 

			List<OfficeOfTransit> list = msgNCTSDeclaration.getOfficeOfTransitList();
			if (list != null) {
				for (int i = 0, size = list.size(); i < size; i++) {
					OfficeOfTransit office = list.get(i);
					if (office != null) {
						msgVAN.addVdzList(getVdz(office.getCode()));	
					}
					
				}
			}
			List<Guarantee> guaranteeListHead = msgNCTSDeclaration.getGuaranteeListH();
			if (guaranteeListHead != null) {
				for (Guarantee guarantee : guaranteeListHead) {					
					if (guarantee != null) {
						List<Reference> reflist = guarantee.getReferenceList();
						for (Reference ref : reflist) {														
							//msgVAN.addVsiList(getVsi(grn.getTypeOfGuarantee(),ref.getGrn(), ref.getOtherNumber()));
							msgVAN.addVsiList(getVsi(guarantee.getTypeOfGuarantee(), ref));  //EI20111122
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
							msgVAN.addVvsList(getVvs(sn.getNumber()));
						}
					}
				}
			}
 
			msgVAN.setVsuSubset(getVsu());			
			msgVAN.setSasSubset(getSas());
			
			Security security = msgNCTSDeclaration.getSecurity(); 
			if (security != null) {
				List<String> itinerary = security.getItineraryList();
				if (itinerary != null) {
					for (int i = 0, size = itinerary.size(); i < size; i++) {
						msgVAN.addVbrList(getVbr(itinerary.get(i)));
					}
				}
			}
			
			// Positionsteil = GoodsItems			
			if (msgNCTSDeclaration.getGoodsItemList() != null) {
			    int listSizeItem = msgNCTSDeclaration.getGoodsItemList().size();						
			    for (int i = 0; i < listSizeItem; i++) {
			    	MsgNCTSDeclarationPos msgNCTSDeclarationPos = msgNCTSDeclaration.getGoodsItemList().get(i);
			    	MsgVANPos msgVANPos =  getPosition(msgNCTSDeclarationPos, guaranteeListHead, i);
			    	msgVAN.addPosList(msgVANPos); 
			    }
			}

			// build the complete FSS-message-string
			//--------------------------------------
            res = msgVAN.getFssString();
           
            Utils.log("(MapNCTSDeclarationToVIA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}

	private MsgVANPos getPosition(MsgNCTSDeclarationPos goodsItem, List<Guarantee> guaranteeListHead, int i) {
		if (goodsItem == null) {
			return null;
		}
		MsgVANPos msgVANPos = new MsgVANPos();
		itemNumber = goodsItem.getItemNumber();
	
		msgVANPos.setVpoSubset(getVpo(goodsItem));
		
		if (goodsItem.getConsignor() != null) {
			TsVAP vap = new TsVAP();
			vap.setAdrSubset(goodsItem.getConsignor(), "1", referenceNumber, itemNumber);
			msgVANPos.addVAP(vap);	
		}		
		if (goodsItem.getConsignee() != null) {
			TsVAP vap = new TsVAP();
			vap.setAdrSubset(goodsItem.getConsignee(), "2", referenceNumber, itemNumber);
			msgVANPos.addVAP(vap);	
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
				msgVANPos.addVCO(getVco(packlist.get(j)));	
			}
		}
		
		Container container = goodsItem.getContainer();
		if (container != null) {
			List<String>numbers = container.getNumberList();
			for (int j = 0, size = numbers.size(); j < size; j++) {
				msgVANPos.addVCN(getVcn(numbers.get(j)));	
			}
		}
		
		List<PreviousDocument> prevdocs = goodsItem.getPreviousDocumentList();
		if (prevdocs != null) {
			for (int k = 0, size = prevdocs.size(); k < size; k++) {
				msgVANPos.addVTV(getVtv(prevdocs.get(k)));	
			}
		}
		
		List<Document> doclist = goodsItem.getDocumentList();
		if (doclist != null) {
			for (int l = 0, size = doclist.size(); l < size; l++) {
				msgVANPos.addVED(getVed(doclist.get(l)));	
			}
		}
		
		if (goodsItem.getWriteOffInfo() != null) {
			List<WriteOffSumA> sumaList =  goodsItem.getWriteOffInfo().getWriteOffSumAList();
			if (sumaList != null) {		
				for (WriteOffSumA suma : sumaList) {
					if (suma != null) {
						List <WriteOffData> dataList = suma.getWriteOffDataList();
						//EI20111207-beginn																	
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
					   } else {			//EI20111207-end	
						    if (suma.getIdentification() != null && suma.getIdentification().equals("REG")) { //EI120117
							   msgVANPos.getVpoSubset().setSuaart("REG");
						    }
							if (suma.getIdentification() != null && suma.getIdentification().equals("AWB")) { //EI120117
								   msgVANPos.getVpoSubset().setSuaart("AWB");								  
							}
							if (dataList != null) {
								for (WriteOffData data : dataList) {
									if (data != null) {        //EI20111011: check if null
										msgVANPos.addBSU(getBsu(data));	
									}
								}
							}
						}
					}
				}
			}
			//EI20111011-beginn   - aber z.Z nicht fuer MsgVANPos vorgesehen
			List<WriteOff> bzlList =  goodsItem.getWriteOffInfo().getWriteOffZLList();
			if (bzlList != null) {		
				for (WriteOff bzl : bzlList) {
					if (bzl != null) {
						List <WriteOffData> dataList = bzl.getWriteOffDataList();
						if (dataList != null) {
							for (WriteOffData data : dataList) {
								if (data != null) {                       
									msgVANPos.addBZL(getBzl(data));	
								}
							}
						}
					}
				}
			}
			List<WriteOff> bavList =  goodsItem.getWriteOffInfo().getWriteOffAVUVList();
			if (bavList != null) {		
				for (WriteOff bav : bavList) {
					if (bav != null) {
						List <WriteOffData> dataList = bav.getWriteOffDataList();
						if (dataList != null) {
							for (WriteOffData data : dataList) {
								if (data != null) {                       
									msgVANPos.addBAV(getBav(data));	
								}
							}
						}
					}
				}
			}
		}
		
		//EI-20111205: wenn Guarantien auf KopfEbene geschickt werden, dann werden sie die 1.Position
		//noch mal gemapped wg. Betraege, die man im VSI nicht erfassen kann,
		//wenn keine Guarantien auf KopfEben, dann können Guarantien auf ItemEbene eingelesen werden
		if (guaranteeListHead != null && guaranteeListHead.size() > 0) {
			if (i == 0) {
				msgVANPos.setVagList(getVagList(guaranteeListHead));	
			}
		} 
		List<Guarantee> guaranteeListItem = goodsItem.getGuaranteeList();
		if (guaranteeListItem != null) {
			for (Guarantee guarantee : guaranteeListItem) {
				msgVANPos.setVagList(getVags(guarantee));	
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
	
	private TsVAN getVan() {
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
		van.setGsroh(Utils.addZabisDecimalPlace(msgNCTSDeclaration.getTotalGrossMass(), 3));
		van.setStvnam(msgNCTSDeclaration.getRepresentativeName());
		van.setStvstl(msgNCTSDeclaration.getRepresentativeCapacity());
		if (msgNCTSDeclaration.getTransRoute() != null) {
			van.setRoute(msgNCTSDeclaration.getTransRoute().getFirstCountry());
			//EI20110524: van.setKzraus(msgNCTSDeclaration.getTransRoute().getFirstCountry());
			van.setKzraus(msgNCTSDeclaration.getTransRoute().getSuspensionIndicator());
		}
		if (msgNCTSDeclaration.getSeals() != null) {
			van.setKztyd(msgNCTSDeclaration.getSeals().getUseOfTydenseals());	
			van.setKzstock(msgNCTSDeclaration.getSeals().getUseOfTydensealStock());
			van.setVsanz(msgNCTSDeclaration.getSeals().getNumber());
			van.setVsart(msgNCTSDeclaration.getSeals().getKind());   //EI20110524
		}
		if (msgNCTSDeclaration.getMeansOfTransportDeparture() != null) {
			// CK20111104 added bfgrcd
			van.setBfgrcd(msgNCTSDeclaration.getMeansOfTransportDeparture().getTransportationType());
			van.setBfabkz(msgNCTSDeclaration.getMeansOfTransportDeparture().getTransportationNumber());
			van.setBfabld(msgNCTSDeclaration.getMeansOfTransportDeparture().getTransportationCountry());
		}
		if (msgNCTSDeclaration.getMeansOfTransportBorder() != null) {
			van.setBfgrcd(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportationType());
			van.setBfgrkz(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportationNumber());
			van.setBfgrld(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportationCountry());
			van.setBfvkzg(msgNCTSDeclaration.getMeansOfTransportBorder().getTransportMode());
		}
//		Party consignee	 = msgNCTSDeclaration.getConsignee();
//		Party consignor	 = msgNCTSDeclaration.getConsignor();
		PartyNCTS auth		 = msgNCTSDeclaration.getAuthorisedConsignee();
//		Party principal	 = msgNCTSDeclaration.getPrincipal();
//
//		TIN consigneeTIN = null; 
//		TIN consignorTIN = null;
//		TIN authTIN 	 = null;
//		TIN principalTIN = null;

		// CK hier sind die TINs bestückt, nicht unterhalb von Conisgnor usw. 
		TIN consigneeTIN = msgNCTSDeclaration.getConsigneeTIN(); 
		TIN consignorTIN = msgNCTSDeclaration.getConsignorTIN();
		TIN authTIN 	 = msgNCTSDeclaration.getAuthorisedConsigneeTIN();
		TIN principalTIN = msgNCTSDeclaration.getPrincipalTIN();
				
//		if (consignee != null) {
//			consigneeTIN = consignee.getPartyTIN();
//		}
//		if (consignor != null) {
//			consignorTIN = consignor.getPartyTIN();
//		}
//		if (auth != null) {
//			authTIN = auth.getPartyTIN();
//		}
//		if (principal != null) {
//			principalTIN = principal.getPartyTIN();
//		}
		if (consignorTIN != null) {
			van.setTinve(consignorTIN.getTIN());
			van.setKdnrve(consignorTIN.getCustomerIdentifier());
		}
		if (consigneeTIN != null) {
			van.setTinem(consigneeTIN.getTIN());
			van.setKdnrem(consigneeTIN.getCustomerIdentifier());
		}
		if (authTIN != null) {
			van.setTinze(authTIN.getTIN());
			van.setKdnrze(authTIN.getCustomerIdentifier());
		}
		van.setAnzcol(msgNCTSDeclaration.getTotalNumberPackages());
		//EI20110520:van.setUebort(msgNCTSDeclaration.getPlaceOfLoadingCode());
		if (msgNCTSDeclaration.getPlaceOfLoading() != null) {   //EI20110523	
			van.setUebort(msgNCTSDeclaration.getPlaceOfLoading().getCode());  //EI20110520	
		}
		if (auth != null) {
			van.setEtnadr(auth.getETNAddress());
		}
		if (msgNCTSDeclaration.getClerk() != null) {
			van.setSb(msgNCTSDeclaration.getClerk().getIdentity());
		} else {
			van.setSb("FSS");     //EI20121108
		}
		if (principalTIN != null) {
			van.setTinhp(principalTIN.getTIN());
			van.setKdnrhp(principalTIN.getCustomerIdentifier());
			van.setDtzohp(principalTIN.getIsTINGermanApprovalNumber());
		}
		van.setCarnhp(msgNCTSDeclaration.getCarnetID());
		van.setKzsusi(msgNCTSDeclaration.getSecurityIndicator());    //EI20110502 new
		//van.setSuaart(msgNCTSDeclaration.getIdentificationCode()); //EI110517 has to be filled depend on in VPO.vptyp 
		van.setZlbez(msgNCTSDeclaration.getBondedWareHouseRefNum()); //EI110517 new
		//van.setAzvbew(msgNCTSDeclaration.getBondedWareHouseAuthorisationID());  //EI20110517 new, is only in VPO
		
		return van;
	}

	private TsVDZ getVdz(String office) {
		if (Utils.isStringEmpty(office)) {
			return null;
		}
		TsVDZ vdz = new TsVDZ();

		vdz.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vdz.setDgzst(office);
		return vdz;
	}
	
	private TsVVS getVvs(String seal) {
		if (Utils.isStringEmpty(seal)) {
			return null;
		}
		TsVVS vvs = new TsVVS();

		vvs.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vvs.setSeal(seal);
		return vvs;
	}

	private TsVSU getVsu() {
		TsVSU vsu = new TsVSU();

		vsu.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vsu.setPosnr("0"); // wird nur auf Kopf-Ebene verwendet
		
        /*  EI20110524:
		if (msgNCTSDeclaration.getCarrier() != null) {
			if (msgNCTSDeclaration.getCarrier().getPartyTIN() != null) {
				vsu.setKnrbf(msgNCTSDeclaration.getCarrier().getPartyTIN().getCustomerIdentifier());
				vsu.setTinbf(msgNCTSDeclaration.getCarrier().getPartyTIN().getTIN());
			}			
		}
		*/
		if (msgNCTSDeclaration.getCarrierTIN() != null) {			
			vsu.setKnrbf(msgNCTSDeclaration.getCarrierTIN().getCustomerIdentifier());
			vsu.setTinbf(msgNCTSDeclaration.getCarrierTIN().getTIN());					
		}
	
		if (msgNCTSDeclaration.getSecurity() != null) {			
			vsu.setBfgnr(msgNCTSDeclaration.getSecurity().getShipmentNumber());
			if (transmitter.equals("KFF")) {                //EI20120206
				vsu.setBfgnr("");
			}
			vsu.setLdeort(msgNCTSDeclaration.getSecurity().getPlaceOfLoading());
			vsu.setEldort(msgNCTSDeclaration.getSecurity().getPlaceOfUnloading());
		}
		
		 /* EI20110524:
		if (msgNCTSDeclaration.getConsignorSecurity() != null) {
			if (msgNCTSDeclaration.getConsignorSecurity().getPartyTIN() != null) {
				vsu.setKnrvsu(
				msgNCTSDeclaration.getConsignorSecurity().getPartyTIN().getCustomerIdentifier());	
				vsu.setTinvsu(msgNCTSDeclaration.getConsignorSecurity().getPartyTIN().getTIN());	
			}
		}
		*/
		if (msgNCTSDeclaration.getConsignorSecurityTIN() != null) {			
			vsu.setKnrvsu(msgNCTSDeclaration.getConsignorSecurityTIN().getCustomerIdentifier());	
			vsu.setTinvsu(msgNCTSDeclaration.getConsignorSecurityTIN().getTIN());			
		}
		
		/* EI20110524:
		if (msgNCTSDeclaration.getConsigneeSecurity() != null) {
			if (msgNCTSDeclaration.getConsigneeSecurity().getPartyTIN() != null) {
				vsu.setKnresu(
				msgNCTSDeclaration.getConsigneeSecurity().getPartyTIN().getCustomerIdentifier());
				vsu.setTinesu(msgNCTSDeclaration.getConsigneeSecurity().getPartyTIN().getTIN());
			}
		}
		*/
		if (msgNCTSDeclaration.getConsigneeSecurityTIN() != null) {			
			vsu.setKnresu(msgNCTSDeclaration.getConsigneeSecurityTIN().getCustomerIdentifier());
			vsu.setTinesu(msgNCTSDeclaration.getConsigneeSecurityTIN().getTIN());			
		}
		return vsu;
	}
	
	private TsSAS getSas() {
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

	private TsVBR getVbr(String ld) {
		if (Utils.isStringEmpty(ld)) {
			return null;
		}
		TsVBR vbr = new TsVBR();

		vbr.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vbr.setLdbf(ld);
		return vbr;
	}
	/*
	private TsVSI getVsi(String sicart, String grn, String ots) {
		if (Utils.isStringEmpty(sicart)) {
			return null;
		}
		TsVSI vsi = new TsVSI();

		vsi.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vsi.setSicart(sicart);
		// assign GRN to field sicbsc - Nummer der Bürgschaftsbescheinigung - if not empty
		// else assign otherNumber to field sicbsc
		// 
		if (grn == null) {
			if (ots != null) {
				vsi.setSicbsc(ots);
				vsi.setKzncts("0");
			}
		} else {
			vsi.setSicbsc(grn);	
			vsi.setKzncts("1");
		}
		
		return vsi;
	}
	*/
	private TsVSI getVsi(String sicart, Reference ref) {   //EI20111122
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
	private TsVPO getVpo(MsgNCTSDeclarationPos goodsItem) {
		if (goodsItem == null) {
			return null;
		}
		
		TsVPO 	vpo = new TsVPO();
		
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
		vpo.setRohm(Utils.addZabisDecimalPlace(goodsItem.getGrossMass(), 3));
		vpo.setEigm(Utils.addZabisDecimalPlace(goodsItem.getNetMass(), 3));
		
		/*	EI20110524:										
		PartyNCTS consignee	 = goodsItem.getConsignee();
		PartyNCTS consignor	 = goodsItem.getConsignor();
		TIN consigneeTIN = null;
		TIN consignorTIN = null;
				
		if (consignee != null) {
			consigneeTIN = consignee.getPartyTIN();
		}
		if (consignor != null) {
			consignorTIN = consignor.getPartyTIN();
		}
		*/
		TIN consigneeTIN = goodsItem.getConsigneeTIN();  //EI20110524
		TIN consignorTIN = goodsItem.getConsignorTIN();  //EI20110524
		if (consignorTIN != null) {
			vpo.setTinve(consignorTIN.getTIN());
			vpo.setKdnrve(consignorTIN.getCustomerIdentifier());
		}
		if (consigneeTIN != null) {
			vpo.setTinem(consigneeTIN.getTIN());
			vpo.setKdnrem(consigneeTIN.getCustomerIdentifier());
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
				vpo.setKzexp(spec.getExport());                     //EI20130111  new Tag
			}
		}			
		List<SensitiveGoods> sgilist = goodsItem.getSensitiveGoodsList();
		if (sgilist != null) {
			SensitiveGoods sgi = sgilist.get(0);	
			if (sgi != null) {
				vpo.setSgicd(sgi.getType());
				vpo.setSgimng(Utils.addZabisDecimalPlace(sgi.getWeight(), 3));
			}	
		}							
		
		//vpo.setSuaart(msgNCTSDeclaration.getIdentificationCode());  		 //EI20110517 new
		vpo.setSuaart(goodsItem.getIdentificationCode());     //EI20110607
		vpo.setZlbez(msgNCTSDeclaration.getBondedWareHouseRefNum());   		 //EI20110517 new
		vpo.setAzvbew(msgNCTSDeclaration.getBondedWarehousePermitNumber());  //EI20110517 new
		
		return vpo;
	}
	
	private TsVCO getVco(Packages packages) {
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

	private TsVCN getVcn(String number) {
		if (number == null) {
			return null;
		}
		TsVCN vcn = new TsVCN();

		vcn.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		vcn.setPosnr(itemNumber);
		vcn.setConnr(number);
		
		return vcn;
	}

	private TsVTV getVtv(PreviousDocument doc) {
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
	
	private TsVED getVed(Document doc) {
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
	
	private TsBSU getBsu(WriteOffData writeOffData) {
		if (writeOffData == null) {
			return null;
		}
		TsBSU bsu = new TsBSU();

		bsu.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		bsu.setPosnr(itemNumber);
		bsu.setVregnr(writeOffData.getRegNumber());
		bsu.setVposnr(writeOffData.getItemNumber());
		bsu.setSuastk(writeOffData.getNumberOfPieces());
		
		return bsu;
	}
	
	private TsBAV getBav(WriteOffData writeOffData) {
		if (writeOffData == null) {
			return null;
		}
		TsBAV bav = new TsBAV();
/* TODO ist das notwendig ??
		bav.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		bav.setPosnr(itemNumber);
		bav.setVregnr(writeOffData.getRegNumber());
		bav.setVposnr(writeOffData.getItemNumber());
		bav.setSuastk(writeOffData.getNumberOfPieces());
		
*/		return bav;
	}
	
	private TsBZL getBzl(WriteOffData writeOffData) {
		if (writeOffData == null) {
			return null;
		}
		TsBZL bzl = new TsBZL();
/* TODO ist das notwendig ??
		bzl.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
		bzl.setPosnr(itemNumber);
		bzl.setVregnr(writeOffData.getRegNumber());
		bzl.setVposnr(writeOffData.getItemNumber());
		bzl.setSuastk(writeOffData.getNumberOfPieces());
	*/	
		return bzl;
	}
	
	private List<TsVAG> getVags(Guarantee guarantee) {
		if (guarantee == null) {
			return null;
		}
		if (guarantee.getReferenceList() == null || guarantee.getReferenceList().size() == 0) {
			return null;
		}
		List<TsVAG> vagList = new Vector<TsVAG>();
		for (Reference ref : guarantee.getReferenceList()) {
			// CK 02.02.2012 LocalAmount must not be null
			// if (ref.getAmount() != null && !Utils.isStringEmpty(ref.getAmount().getAmount())) {
			if (ref.getAmount() != null && !Utils.isStringEmpty(ref.getAmount().getLocalAmount())) {
				TsVAG vag = new TsVAG();

				vag.setBeznr(this.msgNCTSDeclaration.getReferenceNumber());
				vag.setPosnr(itemNumber);
				if (!Utils.isStringEmpty(ref.getGrn())) {	
					vag.setSicbsc(ref.getGrn());					
				} else {
					vag.setSicbsc(ref.getOtherNumber());				
				}	
				// CK 02.02.2012 LocalAmount must be used for sicbtg in VAG
				// vag.setSicbtg(ref.getAmount().getAmount());
				vag.setSicbtg(ref.getAmount().getLocalAmount());
				vag.setAbggrp(ref.getPriceGroup());
				vag.setBasbtg(ref.getAmount().getAmount());
				vag.setWaehrg(ref.getAmount().getCurrency());

				// in Zabis muss der Betrag in Euro angegeben werden,
				// ist hier also Euro angegeben wird der Betrag übernommen
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
	private List<TsVAG> getVagList(List<Guarantee> guarlist) {
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
						vag.setSicbtg(ref.getAmount().getAmount());
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

